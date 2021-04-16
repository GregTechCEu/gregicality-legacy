package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.type.Material;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicadditions.GAMaterials.Potin;

public class TileEntityLargeElectrolyzer extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};


	public TileEntityLargeElectrolyzer(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.ELECTROLYZER_RECIPES, GAConfig.multis.largeElectrolyzer.euPercentage, GAConfig.multis.largeElectrolyzer.durationPercentage, GAConfig.multis.largeElectrolyzer.chancedBoostPercentage, GAConfig.multis.largeElectrolyzer.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeElectrolyzer(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XXX", "XXX")
				.aisle("XMX", "X#X", "XPX")
				.aisle("XXX", "XSX", "XXX")
				.setAmountAtLeast('L', 7)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate())
				.where('#', isAirPredicate())
				.where('M', motorPredicate())
				.where('P', pumpPredicate())
				.build();
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
		PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
		int min = Math.min(motor.getTier(), pump.getTier());
		maxVoltage = (long) (Math.pow(4, min) * 8);
	}

	private static final Material defaultMaterial = Potin;
	public static final Material casingMaterial = getCasingMaterial(defaultMaterial, GAConfig.multis.largeElectrolyzer.casingMaterial);


	public IBlockState getCasingState() {
		return GAMetaBlocks.getMetalCasingBlockState(casingMaterial);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(casingMaterial);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.ELECTROLYZER_OVERLAY;
	}
}
