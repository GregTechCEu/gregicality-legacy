package gregicadditions.machines.multi.uumatter;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.multi.MultiUtils;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class TileEntityLargeReplicator extends LargeSimpleRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    public TileEntityLargeReplicator(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap,
                GAConfig.multis.largeReplicator.euPercentage,
                GAConfig.multis.largeReplicator.durationPercentage,
                GAConfig.multis.largeReplicator.chancedBoostPercentage,
                GAConfig.multis.largeReplicator.stack);
    }

    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeReplicator(this.metaTileEntityId, GARecipeMaps.REPLICATOR_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#####XXEXX#####", "#####XXEXX#####", "#######X#######")
                .aisle("###XXXXXXXXX###", "###XXCCCCCXX###", "#####XPXPX#####")
                .aisle("##XXXXXEXXXXX##", "##XCCCXSXCCCX##", "###XXF#s#FXX###")
                .aisle("#XXXXX###XXXXX#", "#XCCXX###XXCCX#", "##XF#######FX##")
                .aisle("#XXX#######XXX#", "#XCX#######XCX#", "##X#########X##")
                .aisle("XXXX#######XXXX", "XCCX#######XCCX", "#XF#########FX#")
                .aisle("XXX#########XXX", "XCX#########XCX", "#P###########P#")
                .aisle("EXE#########EXE", "ECE#########ECE", "XXs#########sXX")
                .aisle("XXX#########XXX", "XCX#########XCX", "#P###########P#")
                .aisle("XXXX#######XXXX", "XCCX#######XCCX", "#XF#########FX#")
                .aisle("#XXX#######XXX#", "#XCX#######XCX#", "##X#########X##")
                .aisle("#XXXXX###XXXXX#", "#XCCXX###XXCCX#", "##XF#######FX##")
                .aisle("##XXXXXEXXXXX##", "##XCCCXEXCCCX##", "###XXF#s#FXX###")
                .aisle("###XXXXXXXXX###", "###XXCCCCCXX###", "#####XPXPX#####")
                .aisle("#####XXEXX#####", "#####XXEXX#####", "#######X#######")
                .setAmountAtLeast('L', 10)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', statePredicate(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR)))
                .where('F', fieldGenPredicate())
                .where('P', pumpPredicate())
                .where('s', sensorPredicate())
                .where('E', emitterPredicate())
                .where('#', (tile) -> true)
                .build();
    }

    private static final IBlockState defaultCasingState = GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeReplicator.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeReplicator.casingMaterial, ClientHandler.TRITANIUM_CASING);
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
        return ClientHandler.REPLICATOR_OVERLAY;
    }
}
