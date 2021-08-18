package gregicadditions.machines.multi.uumatter;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.components.*;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.multi.CasingUtils;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
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
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class TileEntityLargeMassFabricator extends LargeSimpleRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY,
            GregicalityCapabilities.MAINTENANCE_HATCH
    };

    public TileEntityLargeMassFabricator(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap,
                GAConfig.multis.largeMassFabricator.euPercentage,
                GAConfig.multis.largeMassFabricator.durationPercentage,
                GAConfig.multis.largeMassFabricator.chancedBoostPercentage,
                GAConfig.multis.largeMassFabricator.stack);
    }

    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeMassFabricator(this.metaTileEntityId, RecipeMaps.MASS_FABRICATOR_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "#P#P#", "#P#P#", "#P#P#", "XXXXX")
                .aisle("XXXXX", "PCGCP", "PCGCP", "PCGCP", "XXXXX")
                .aisle("XXXXX", "#pUp#", "#psp#", "#pUp#", "XGGGX")
                .aisle("XXXXX", "#pcp#", "#FEF#", "#pcp#", "XGGGX")
                .aisle("XXXXX", "#pcp#", "#FEF#", "#pcp#", "XGGGX")
                .aisle("XXXXX", "#pUp#", "#psp#", "#pUp#", "XGGGX")
                .aisle("XXXXX", "PCGCP", "PCGCP", "PCGCP", "XXXXX")
                .aisle("XXSXX", "#P#P#", "#P#P#", "#P#P#", "XXXXX")
                .setAmountAtLeast('L', 10)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', statePredicate(getCasingState()))
                .where('P', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE)))
                .where('p', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('G', statePredicate(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('c', statePredicate(MetaBlocks.FUSION_COIL.getState(BlockFusionCoil.CoilType.SUPERCONDUCTOR)))
                .where('F', fieldGenPredicate())
                .where('U', pumpPredicate())
                .where('s', sensorPredicate())
                .where('E', emitterPredicate())
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .build();
    }

    private static final IBlockState defaultCasingState = GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM);
    public static final IBlockState casingState = CasingUtils.getConfigCasingBlockState(GAConfig.multis.largeMassFabricator.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return CasingUtils.getConfigCasingTexture(GAConfig.multis.largeMassFabricator.casingMaterial, ClientHandler.TRITANIUM_CASING);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        FieldGenCasing.CasingType fieldGen = context.getOrDefault("FieldGen", FieldGenCasing.CasingType.FIELD_GENERATOR_LV);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        SensorCasing.CasingType sensor = context.getOrDefault("Sensor", SensorCasing.CasingType.SENSOR_LV);
        EmitterCasing.CasingType emitter = context.getOrDefault("Emitter", EmitterCasing.CasingType.EMITTER_LV);
        int min1 = Math.min(fieldGen.getTier(), pump.getTier());
        int min2 = Math.min(sensor.getTier(), emitter.getTier());
        int min = Math.min(min1, min2);
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.MASS_FAB_OVERLAY;
    }
}
