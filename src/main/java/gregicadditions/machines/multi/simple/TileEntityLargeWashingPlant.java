package gregicadditions.machines.multi.simple;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IMultiRecipe;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.type.Material;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static gregicadditions.GAMaterials.Grisium;

public class TileEntityLargeWashingPlant extends LargeSimpleRecipeMapMultiblockController implements IMultiRecipe {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    public RecipeMap<?> recipeMap;
    private static final RecipeMap<?>[] possibleRecipe = new RecipeMap<?>[]{
            RecipeMaps.ORE_WASHER_RECIPES,
            RecipeMaps.CHEMICAL_BATH_RECIPES
    };
    private int pos;

    public TileEntityLargeWashingPlant(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeWashingPlant.euPercentage, GAConfig.multis.largeWashingPlant.durationPercentage, GAConfig.multis.largeWashingPlant.chancedBoostPercentage, GAConfig.multis.largeWashingPlant.stack);
        this.recipeMap = recipeMap;
        pos = Arrays.asList(possibleRecipe).indexOf(recipeMap);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeWashingPlant(metaTileEntityId, RecipeMaps.ORE_WASHER_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X")
                .aisle("XXMXX", "XXSXX", "XXXXX")
                .setAmountAtLeast('L', 9)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate())
                .where('#', statePredicate(Blocks.WATER.getDefaultState()))
                .where('M', motorPredicate())
                .build();
    }

    private static final Material defaultMaterial = Grisium;
    public static final Material casingMaterial = getCasingMaterial(defaultMaterial, GAConfig.multis.largeWashingPlant.casingMaterial);

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(casingMaterial);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(casingMaterial);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.recipe", new TextComponentTranslation("recipemap." + this.recipeMap.getUnlocalizedName() + ".name")));
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!getWorld().isRemote) {
            boolean isEmpty = IntStream.range(0, getInputInventory().getSlots())
                    .mapToObj(i -> getInputInventory().getStackInSlot(i))
                    .allMatch(ItemStack::isEmpty);
            if (!isEmpty) {
                return false;
            }

            if (playerIn.isSneaking())
                this.pos = (pos - 1 < 0 ? possibleRecipe.length - 1 : pos) % possibleRecipe.length;
            else
                this.pos = (pos + 1) % possibleRecipe.length;

            ((LargeSimpleMultiblockRecipeLogic) (this.recipeMapWorkable)).recipeMap = possibleRecipe[pos];
            this.recipeMap = possibleRecipe[pos];
        }

        return true; // return true here on the server to keep the GUI closed
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("Recipe", new NBTTagInt(pos));
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.pos = data.getInteger("Recipe");
        ((LargeSimpleMultiblockRecipeLogic) (this.recipeMapWorkable)).recipeMap = possibleRecipe[pos];
        this.recipeMap = possibleRecipe[pos];
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        T capabilityResult = super.getCapability(capability, side);
        if (capabilityResult == null && capability == GregicAdditionsCapabilities.MULTI_RECIPE_CAPABILITY) {
            return (T) this;
        }
        return capabilityResult;
    }

    @Override
    public RecipeMap<?>[] getRecipes() {
        return possibleRecipe;
    }

    @Override
    public int getCurrentRecipe() {
        return pos;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        int min = motor.getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return (pos == 1) ? Textures.CHEMICAL_BATH_OVERLAY : Textures.ORE_WASHER_OVERLAY;
    }
}
