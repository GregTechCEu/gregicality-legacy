package gregicadditions.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.ImmutableMap;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipes.PrimitiveBlastFurnaceRecipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.PI;

public class MetaTileEntityIndustrialPrimitiveBlastFurnace extends MultiblockWithDisplayBase {

    private static final Map<String, MaterialStack> ALTERNATIVE_MATERIAL_NAMES = ImmutableMap.of(
            "fuelCoke", new MaterialStack(Materials.Coke, GTValues.M),
            "blockFuelCoke", new MaterialStack(Materials.Coke, GTValues.M * 9L));

    private static final Map<Material, Float> MATERIAL_FUEL_MAP = ImmutableMap.of(
            Materials.Lignite, 0.7f,
            Materials.Charcoal, 1.0f,
            Materials.Coal, 1.0f,
            Materials.Coke, 2.0f);

    private int maxProgressDuration;
    private int currentProgress;
    private NonNullList<ItemStack> outputsList;
    private float fuelUnitsLeft;
    private boolean isActive;
    private boolean wasActiveAndNeedUpdate;
    protected IItemHandlerModifiable outputInventory;
    protected IItemHandlerModifiable inputInventory;
    protected int size = 0;
    protected final static int MAX_SIZE = 64;

    private ItemStack lastInputStack = ItemStack.EMPTY;
    private PrimitiveBlastFurnaceRecipe previousRecipe;
    private int efficiency;


    public MetaTileEntityIndustrialPrimitiveBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    protected void updateFormedValid() {
        if (maxProgressDuration == 0) {
            if (tryPickNewRecipe()) {
                if (wasActiveAndNeedUpdate) {
                    this.wasActiveAndNeedUpdate = false;
                } else setActive(true);
            }
        } else {
            if (consumeFuel()) {
                currentProgress += size;
                if (currentProgress >= maxProgressDuration) {
                    finishCurrentRecipe(false);
                    return;
                }
                fuelUnitsLeft -= previousRecipe == null ? 0 : (((previousRecipe.getFuelAmount()) * 1.0) / ((previousRecipe.getDuration()) * 1.0)) * size;
            }
        }
        if (wasActiveAndNeedUpdate) {
            this.wasActiveAndNeedUpdate = false;
            setActive(false);
        }
    }

    private void finishCurrentRecipe(boolean isFail) {
        this.maxProgressDuration = 0;
        this.currentProgress = 0;
        if (!isFail) {
            MetaTileEntity.addItemsToItemHandler(outputInventory, false, outputsList);
        }
        this.outputsList = null;
        this.wasActiveAndNeedUpdate = true;
        markDirty();
    }

    private boolean consumeFuel() {
        if (fuelUnitsLeft > 0) {
            return true;
        }
        List<ItemStack> fuelStacks = IntStream.range(0, inputInventory.getSlots())
                .mapToObj(value -> inputInventory.getStackInSlot(value))
                .filter(itemStack -> OreDictUnifier.getMaterial(itemStack) != null)
                .filter(itemStack -> MATERIAL_FUEL_MAP.containsKey(OreDictUnifier.getMaterial(itemStack).material)).collect(Collectors.toList());

        if (fuelStacks.isEmpty()) {
            finishCurrentRecipe(true);
            return false;
        }
        ItemStack itemStack = fuelStacks.get(0);
        fuelUnitsLeft = getFuelUnits(itemStack) * efficiency / 100;
        itemStack.shrink(1);
        return true;
    }

    private PrimitiveBlastFurnaceRecipe getOrRefreshRecipe(ItemStack inputStack) {
        PrimitiveBlastFurnaceRecipe currentRecipe = null;
        if (previousRecipe != null &&
                previousRecipe.getInput().getIngredient().apply(inputStack)) {
            currentRecipe = previousRecipe;
        } else if (!areItemStacksEqual(inputStack, lastInputStack)) {
            this.lastInputStack = inputStack.isEmpty() ? ItemStack.EMPTY : inputStack.copy();
            currentRecipe = RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES.stream()
                    .filter(it -> it.getInput().getIngredient().test(inputStack))
                    .findFirst().orElse(null);
            if (currentRecipe != null) {
                this.previousRecipe = currentRecipe;
            }
        }
        return currentRecipe;
    }

    private static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
        return (stackA.isEmpty() && stackB.isEmpty()) ||
                (ItemStack.areItemsEqual(stackA, stackB) &&
                        ItemStack.areItemStackTagsEqual(stackA, stackB));
    }

    private boolean setupRecipe(ItemStack inputStack, int fuelAmount, PrimitiveBlastFurnaceRecipe recipe) {
        return inputStack.getCount() >= recipe.getInput().getCount() && fuelAmount >= recipe.getFuelAmount() &&
                ItemHandlerHelper.insertItemStacked(outputInventory, recipe.getOutput(), true).isEmpty();
    }

    private boolean tryPickNewRecipe() {
        ItemStack inputStack = IntStream.range(0, inputInventory.getSlots())
                .mapToObj(value -> inputInventory.getStackInSlot(value))
                .filter(itemStack -> OreDictUnifier.getMaterial(itemStack) != null)
                .filter(itemStack -> !MATERIAL_FUEL_MAP.containsKey(OreDictUnifier.getMaterial(itemStack).material))
                .findFirst().orElse(null);

        if (inputStack == null || inputStack.isEmpty()) {
            return false;
        }

        PrimitiveBlastFurnaceRecipe currentRecipe = getOrRefreshRecipe(inputStack);
        if (currentRecipe != null && setupRecipe(inputStack, Integer.MAX_VALUE, currentRecipe)) {
            if (!consumeFuel()) {
                return false;
            }

            inputStack.shrink(currentRecipe.getInput().getCount());
            this.maxProgressDuration = currentRecipe.getDuration();
            this.currentProgress = 0;
            NonNullList<ItemStack> outputs = NonNullList.create();
            outputs.add(currentRecipe.getOutput().copy());
            outputs.add(getAshForRecipeFuelConsumption(currentRecipe.getFuelAmount()));
            this.outputsList = outputs;
            markDirty();
            return true;
        }
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("Active", isActive);
        data.setBoolean("WasActive", wasActiveAndNeedUpdate);
        data.setFloat("FuelUnitsLeft", fuelUnitsLeft);
        data.setInteger("MaxProgress", maxProgressDuration);
        if (maxProgressDuration > 0) {
            data.setInteger("Progress", currentProgress);
            NBTTagList itemOutputs = new NBTTagList();
            for (ItemStack itemStack : outputsList) {
                itemOutputs.appendTag(itemStack.writeToNBT(new NBTTagCompound()));
            }
            data.setTag("Outputs", itemOutputs);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isActive = data.getBoolean("Active");
        this.wasActiveAndNeedUpdate = data.getBoolean("WasActive");
        this.fuelUnitsLeft = data.getFloat("FuelUnitsLeft");
        this.maxProgressDuration = data.getInteger("MaxProgress");
        if (maxProgressDuration > 0) {
            this.currentProgress = data.getInteger("Progress");
            NBTTagList itemOutputs = data.getTagList("Outputs", NBT.TAG_COMPOUND);
            this.outputsList = NonNullList.create();
            for (int i = 0; i < itemOutputs.tagCount(); i++) {
                this.outputsList.add(new ItemStack(itemOutputs.getCompoundTagAt(i)));
            }
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 100) {
            this.isActive = buf.readBoolean();
            getWorld().checkLight(getPos());
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
        if (!getWorld().isRemote) {
            writeCustomData(100, b -> b.writeBoolean(isActive));
            getWorld().checkLight(getPos());
        }
    }

    public boolean isActive() {
        return isActive;
    }


    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null && isActive ? 15 : 0;
    }

    public static float getFuelUnits(ItemStack fuelType) {
        if (fuelType.isEmpty()) {
            return 0;
        }
        MaterialStack materialStack = OreDictUnifier.getMaterial(fuelType);
        if (materialStack == null) {
            //try alternative material names if we can't find valid standard one
            materialStack = OreDictUnifier.getOreDictionaryNames(fuelType).stream()
                    .map(ALTERNATIVE_MATERIAL_NAMES::get)
                    .filter(Objects::nonNull)
                    .findFirst().orElse(null);
        }

        if (materialStack != null && materialStack.amount >= GTValues.M) {
            int materialAmount = (int) (materialStack.amount / GTValues.M);
            Float materialMultiplier = MATERIAL_FUEL_MAP.get(materialStack.material);
            return materialMultiplier == null ? 0 : materialAmount * materialMultiplier;
        }
        return 0;
    }

    public static ItemStack getAshForRecipeFuelConsumption(int fuelUnitsConsumed) {
        int ashAmount = MathHelper.clamp(1 + fuelUnitsConsumed, 2, 8);
        return OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, ashAmount);
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.PRIMITIVE_BRICKS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.PRIMITIVE_BRICKS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive());
        if (isActive() && isStructureFormed()) {
            EnumFacing back = getFrontFacing().getOpposite();
            Matrix4 offset = translation.copy().translate(back.getXOffset(), -0.3, back.getZOffset());
            TextureAtlasSprite sprite = TextureUtils.getBlockTexture("lava_still");
            renderState.brightness = 0xF000F0;
            renderState.colour = 0xFFFFFFFF;
            Textures.renderFace(renderState, offset, new IVertexOperation[0], EnumFacing.UP, Cuboid6.full, sprite);
        }
    }

    private void initializeAbilities() {
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));

        BlockPos pos;
        EnumFacing direction = this.getFrontFacing().getOpposite();
        int length = 0;
        do {
            pos = this.getPos().offset(direction, ++length);
        } while (getWorld().getBlockState(pos).getBlock().equals(Blocks.AIR));

        this.size = Math.min(MAX_SIZE, length - 1);
        this.efficiency = (int) ((((-Math.atan(size / 4.0 / PI - MAX_SIZE / 4.0 / PI / 2) + (PI / 2)) / PI + ((-Math.atan(MAX_SIZE / 4.0 / PI / 2) + PI / 2) / PI)/2)) * 100.0);
    }

    private void resetTileAbilities() {
        this.outputInventory = new ItemStackHandler(0);
        this.inputInventory = new ItemStackHandler(0);
        this.size = 0;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (isActive)
            setActive(false);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        //basically check minimal requirements for inputs count
        int itemImportCount = abilities.getOrDefault(MultiblockAbility.IMPORT_ITEMS, Collections.emptyList()).size();
        int itemExportCount = abilities.getOrDefault(MultiblockAbility.EXPORT_ITEMS, Collections.emptyList()).size();
        return itemImportCount >= 1 && itemExportCount >= 1;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(2) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot == 1 && getFuelUnits(stack) == 0)
                    return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(2);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("YYY", "YOY", "YYY", "YYY")
                .aisle("YYY", "I#I", "Y#Y", "Y#Y").setRepeatable(1, MAX_SIZE)
                .aisle("YYY", "YXY", "YYY", "YYY")
                .where('X', selfPredicate())
                .where('#', isAirPredicate())
                .where('I', statePredicate(getCasingState()).or(tilePredicate((state, tile) -> tile.metaTileEntityId.equals(MetaTileEntities.ITEM_IMPORT_BUS[1].metaTileEntityId))))
                .where('O', statePredicate(getCasingState()).or(tilePredicate((state, tile) -> tile.metaTileEntityId.equals(MetaTileEntities.ITEM_EXPORT_BUS[1].metaTileEntityId))))
                .where('Y', statePredicate(getCasingState()))
                .build();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityIndustrialPrimitiveBlastFurnace(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.machine.primitive_blast_furnace.industrial.size", size));
            textList.add(new TextComponentTranslation("gregtech.multiblock.machine.primitive_blast_furnace.industrial.fuelUnitsLeft", (int) (fuelUnitsLeft * 100)));
            textList.add(new TextComponentTranslation("gregtech.multiblock.machine.primitive_blast_furnace.industrial.fuelEfficiency", efficiency));
            if (currentProgress > 0)
                textList.add(new TextComponentTranslation("gregtech.multiblock.progress", (int) (currentProgress / (double) (maxProgressDuration) * 100)));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.machine.primitive_blast_furnace.industrial.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.primitive_blast_furnace.industrial.tooltip.2"));
    }

}
