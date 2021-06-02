package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.components.*;
import gregicadditions.item.metal.MetalCasing2;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static gregicadditions.client.ClientHandler.STABALLOY_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityLargeMultiUse extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};

    public TileEntityLargeMultiUse(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeMultiUse.euPercentage, GAConfig.multis.largeMultiUse.durationPercentage, GAConfig.multis.largeMultiUse.chancedBoostPercentage, GAConfig.multis.largeMultiUse.stack, new RecipeMap<?>[]{
                RecipeMaps.COMPRESSOR_RECIPES, RecipeMaps.LATHE_RECIPES, RecipeMaps.POLARIZER_RECIPES,
                RecipeMaps.FERMENTING_RECIPES, RecipeMaps.FLUID_EXTRACTION_RECIPES, RecipeMaps.EXTRACTOR_RECIPES,
                RecipeMaps.AUTOCLAVE_RECIPES, GARecipeMaps.REPLICATOR_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeMultiUse(metaTileEntityId, RecipeMaps.COMPRESSOR_RECIPES);
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        switch (recipeMapIndex) {
            case 1: return Textures.LATHE_OVERLAY;
            case 2: return Textures.POLARIZER_OVERLAY;
            case 3: return Textures.FERMENTER_OVERLAY;
            case 4: return Textures.FLUID_EXTRACTOR_OVERLAY;
            case 5: return Textures.EXTRACTOR_OVERLAY;
            case 6: return Textures.AUTOCLAVE_OVERLAY;
            case 7: return ClientHandler.REPLICATOR_OVERLAY;
            default: return Textures.COMPRESSOR_OVERLAY;
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CMP", "XXX")
                .aisle("XXX", "p#s", "XXX")
                .aisle("XXX", "ESR", "XXX")
                .setAmountAtLeast('L', 2)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .where('M', motorPredicate())
                .where('C', conveyorPredicate())
                .where('P', pistonPredicate())
                .where('p', pumpPredicate())
                .where('s', sensorPredicate())
                .where('E', emitterPredicate())
                .where('R', robotArmPredicate())
                .build();
    }

    private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeMultiUse.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeMultiUse.casingMaterial, STABALLOY_CASING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.large_multi_use.description"));
        tooltip.add(I18n.format("gregtech.multiblock.recipe", this.recipeMap.getLocalizedName()));
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        ConveyorCasing.CasingType conveyor = context.getOrDefault("Conveyor", ConveyorCasing.CasingType.CONVEYOR_LV);
        RobotArmCasing.CasingType robotArm = context.getOrDefault("RobotArm", RobotArmCasing.CasingType.ROBOT_ARM_LV);
        EmitterCasing.CasingType emitter = context.getOrDefault("Emitter", EmitterCasing.CasingType.EMITTER_LV);
        SensorCasing.CasingType sensor = context.getOrDefault("Sensor", SensorCasing.CasingType.SENSOR_LV);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        PistonCasing.CasingType piston = context.getOrDefault("Piston", PistonCasing.CasingType.PISTON_LV);
        int min = Collections.min(Arrays.asList(motor.getTier(), conveyor.getTier(), robotArm.getTier(), emitter.getTier(),
                sensor.getTier(), pump.getTier(), piston.getTier()));
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        switch (this.getRecipeMapIndex()) {
            case 1: return Textures.LATHE_OVERLAY;
            case 2: return Textures.POLARIZER_OVERLAY;
            case 3: return Textures.FERMENTER_OVERLAY;
            case 4: return Textures.FLUID_EXTRACTOR_OVERLAY;
            case 5: return Textures.EXTRACTOR_OVERLAY;
            case 6: return Textures.AUTOCLAVE_OVERLAY;
            case 7: return ClientHandler.REPLICATOR_OVERLAY;
            default: return Textures.COMPRESSOR_OVERLAY;
        }
    }
}
