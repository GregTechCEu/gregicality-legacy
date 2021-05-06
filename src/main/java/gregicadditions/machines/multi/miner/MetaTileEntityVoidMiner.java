package gregicadditions.machines.multi.miner;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.GAMaterials;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.categories.handlers.VoidMinerHandler.*;
import static gregicadditions.client.ClientHandler.*;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregicadditions.recipes.VoidMinerOres.*;
import static gregtech.api.unification.material.Materials.TungstenSteel;


public class MetaTileEntityVoidMiner extends MultiblockWithDisplayBase {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};
    private final int maxTemperature;
    private static final int CONSUME_START = 100;
    private final int tier;
    private IEnergyContainer energyContainer;
    private IMultipleTankHandler importFluidHandler;
    protected IItemHandlerModifiable outputInventory;
    private boolean isActive = false;
    private boolean overheat = false;
    private boolean usingPyrotheum = true;
    private int temperature = 0;
    private double currentDrillingFluid = CONSUME_START;
    private final long energyDrain;


    public MetaTileEntityVoidMiner(ResourceLocation metaTileEntityId, int tier, int temp) {
        super(metaTileEntityId);
        this.tier = tier;
        this.energyDrain = GAValues.V[tier];
        this.maxTemperature = temp;
        this.reinitializeStructurePattern();
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

    private void initializeAbilities() {
        this.importFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    @Override
    public int getActualComparatorValue() {
        float f = temperature / (maxTemperature * 1.0f);
        return MathHelper.floor(f * 14.0f) + (temperature > 0 ? 1 : 0);
    }

    private void resetTileAbilities() {
        this.importFluidHandler = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    public boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= energyDrain) {
            energyContainer.removeEnergy(energyDrain);
            return true;
        }
        return false;
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (overheat || !drainEnergy()) {
                if (temperature > 0) {
                    temperature--;
                }
                if (temperature == 0) {
                    overheat = false;
                }
                if (currentDrillingFluid > CONSUME_START) {
                    currentDrillingFluid--;
                }
                if (currentDrillingFluid < CONSUME_START) {
                    currentDrillingFluid = CONSUME_START;
                }

                if (isActive)
                    setActive(false);
                return;
            }

            if (!isActive)
                setActive(true);


            if (getTimer() % 20 == 0) {
                FluidStack pyrotheumFluid = GAMaterials.Pyrotheum.getFluid((int) currentDrillingFluid);
                FluidStack cryotheumFluid = GAMaterials.Cryotheum.getFluid((int) currentDrillingFluid);
                FluidStack canDrainPyrotheum = importFluidHandler.drain(pyrotheumFluid, false);
                FluidStack canDrainCryotheum = importFluidHandler.drain(cryotheumFluid, false);
                boolean hasConsume = false;
                //consume fluid
                if (usingPyrotheum && canDrainPyrotheum != null && canDrainPyrotheum.amount == (int) currentDrillingFluid) {
                    importFluidHandler.drain(pyrotheumFluid, true);
                    temperature += currentDrillingFluid / 100;
                    currentDrillingFluid = currentDrillingFluid * 1.02;
                    hasConsume = true;
                } else if (temperature > 0 && canDrainCryotheum != null && canDrainCryotheum.amount == (int) currentDrillingFluid) {
                    importFluidHandler.drain(cryotheumFluid, true);
                    temperature -= currentDrillingFluid / 100;
                    currentDrillingFluid = currentDrillingFluid * 0.98;
                }
                if (temperature < 0) {
                    temperature = 0;
                }
                if (currentDrillingFluid < CONSUME_START) {
                    currentDrillingFluid = CONSUME_START;
                }
                if (temperature > maxTemperature) {
                    overheat = true;
                    currentDrillingFluid = CONSUME_START;
                    return;
                }
                usingPyrotheum = !usingPyrotheum;

                //mine

                int nbOres = temperature / 1000;

                if (nbOres == 0 || !hasConsume) {
                    return;
                }

                List<ItemStack> ores = getOres();
                Collections.shuffle(ores);
                ores.stream().limit(10).peek(itemStack -> itemStack.setCount(getWorld().rand.nextInt(nbOres * nbOres) + 1)).forEach(itemStack -> {
                    addItemsToItemHandler(outputInventory, false, Collections.singletonList(itemStack));
                });


            }
        }
    }

    List<ItemStack> getOres() {
        switch(tier) {
            case 8:
                return ORES;
            case 9:
                return ORES_2;
            case 10:
            default:
                return ORES_3;
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCCCCCC", "CCCCCCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("C###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C##D#D##C", "C##D#D##C", "###D#D###", "##D###D##", "##D###D##", "CDD###DDC", "FD#####DF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("CCCCCCCCC", "CCCCSCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('D', statePredicate(getSecondaryCasingState()))
                .where('F', statePredicate(getFrameState()))
                .where('#', blockWorldState -> true)
                .build();
    }

    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        //basically check minimal requirements for inputs count
        int fluidInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        return fluidInputsCount >= 1 &&
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.void_miner.description.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.void_miner.description.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.void_miner.description.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.void_miner.description.4"));
        tooltip.add(I18n.format("gtadditions.multiblock.void_miner.description.5"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_used", energyDrain));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.temperature", temperature, maxTemperature));
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.drilling_fluid_amount", (int) currentDrillingFluid));
            if (overheat) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.universal.overheat").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }

        super.addDisplayText(textList);
    }

    public IBlockState getCasingState() {
        switch (tier) {
            case 8:
                return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
            case 9:
                return METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY);
            case 10:
            default:
                return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_K243);
        }
    }

    public IBlockState getSecondaryCasingState() {
        switch (tier) {
            case 8:
                return METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
            case 9:
                return METAL_CASING_1.getState(MetalCasing1.CasingType.INCOLOY_813);
            case 10:
            default:
                return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_X78);
        }
    }

    public IBlockState getFrameState() {
        switch (tier) {
            case 8:
                return MetaBlocks.FRAMES.get(TungstenSteel).getDefaultState();
            case 9:
                return MetaBlocks.FRAMES.get(Seaborgium).getDefaultState();
            case 10:
            default:
                return MetaBlocks.FRAMES.get(Bohrium).getDefaultState();
        }
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        switch (tier) {
            case 9:
                return ENRICHED_NAQUADAH_ALLOY_CASING;
            case 10:
                return HASTELLOY_K243_CASING;
            case 8:
            default:
                return HASTELLOY_N_CASING;
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityVoidMiner(metaTileEntityId, this.tier, this.maxTemperature);
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
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("temperature", new NBTTagInt(temperature));
        data.setTag("currentDrillingFluid", new NBTTagDouble(currentDrillingFluid));
        data.setTag("overheat", new NBTTagInt(overheat ? 1 : 0));
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        temperature = data.getInteger("temperature");
        currentDrillingFluid = data.getDouble("currentDrillingFluid");
        overheat = data.getInteger("overheat") != 0;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public boolean isOverheat() {
        return overheat;
    }

    public int getTemperature() {
        return temperature;
    }

    public double getCurrentDrillingFluid() {
        return currentDrillingFluid;
    }

}
