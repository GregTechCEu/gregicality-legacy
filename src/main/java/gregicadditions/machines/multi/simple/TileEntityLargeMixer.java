package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.components.MotorCasing;
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
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicadditions.client.ClientHandler.STABALLOY_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityLargeMixer extends LargeSimpleRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


    public TileEntityLargeMixer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.LARGE_MIXER_RECIPES, GAConfig.multis.largeMixer.euPercentage, GAConfig.multis.largeMixer.durationPercentage, GAConfig.multis.largeMixer.chancedBoostPercentage, GAConfig.multis.largeMixer.stack);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeMixer(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("AXXXA", "AXXXA", "AXXXA")
                .aisle("XXXXX", "X#M#X", "AXXXA")
                .aisle("XXXXX", "X#Y#X", "AXXXA").setRepeatable(2, 4)
                .aisle("AXXXA", "AXSXA", "AXXXA")
                .setAmountAtLeast('L', 9)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('Y', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING)))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate())
                .where('A', (tile) -> true)
                .where('#', isAirPredicate())
                .where('M', motorPredicate())
                .build();
    }

    private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeMixer.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeMixer.casingMaterial, STABALLOY_CASING);
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
        return Textures.MIXER_OVERLAY;
    }

}
