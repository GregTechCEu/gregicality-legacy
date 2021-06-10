package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
import gregicadditions.recipes.GARecipeMaps;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;

import static gregicadditions.client.ClientHandler.GRISIUM_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class TileEntityLargeWashingPlant extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};

    public TileEntityLargeWashingPlant(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeWashingPlant.euPercentage, GAConfig.multis.largeWashingPlant.durationPercentage, GAConfig.multis.largeWashingPlant.chancedBoostPercentage, GAConfig.multis.largeWashingPlant.stack,
                new RecipeMap<?>[]{RecipeMaps.ORE_WASHER_RECIPES, RecipeMaps.CHEMICAL_BATH_RECIPES, GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES, RecipeMaps.AUTOCLAVE_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeWashingPlant(metaTileEntityId, RecipeMaps.ORE_WASHER_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "XP#PX", "X###X")
                .aisle("XXXXX", "XP#PX", "X###X")
                .aisle("XXXXX", "XP#PX", "X###X")
                .aisle("XXXXX", "XP#PX", "X###X")
                .aisle("XXXXX", "XP#PX", "X###X")
                .aisle("XXMXX", "XXSXX", "XXXXX")
                .setAmountAtLeast('L', 9)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('#', statePredicate(Blocks.WATER.getDefaultState()))
                .where('M', motorPredicate())
                .build();
    }

    private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.GRISIUM);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeWashingPlant.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeWashingPlant.casingMaterial, GRISIUM_CASING);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        int min = motor.getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.recipe", this.recipeMap.getLocalizedName()));
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        switch (this.getRecipeMapIndex()) {
            case 1:
                return Textures.CHEMICAL_BATH_OVERLAY;
            case 3:
                return Textures.AUTOCLAVE_OVERLAY;
            default:
                return Textures.ORE_WASHER_OVERLAY;
        }
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        switch (this.getRecipeMapIndex()) {
            case 1:
                return Textures.CHEMICAL_BATH_OVERLAY;
            case 3:
                return Textures.AUTOCLAVE_OVERLAY;
            default:
                return Textures.ORE_WASHER_OVERLAY;
        }
    }
}
