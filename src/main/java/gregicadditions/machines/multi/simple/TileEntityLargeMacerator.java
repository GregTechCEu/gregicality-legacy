package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
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
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;

import static gregicadditions.GAMaterials.Potin;
import static gregicadditions.client.ClientHandler.BABBIT_ALLOY_CASING;
import static gregicadditions.client.ClientHandler.POTIN_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class TileEntityLargeMacerator extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};


	public TileEntityLargeMacerator(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.MACERATOR_RECIPES, GAConfig.multis.largeMacerator.euPercentage, GAConfig.multis.largeMacerator.durationPercentage, GAConfig.multis.largeMacerator.chancedBoostPercentage, GAConfig.multis.largeMacerator.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeMacerator(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XXX","XXX","XXX", "XXX")
				.aisle("XXX", "XMX","X#X","XPX", "XXX")
				.aisle("XXX", "XSX","XXX","XXX", "XXX")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('#', isAirPredicate())
				.where('M', motorPredicate())
				.where('P', pistonPredicate())
				.build();
	}

	private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.POTIN);
	public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeMacerator.casingMaterial, defaultCasingState);


	public IBlockState getCasingState() {
		return casingState;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeMacerator.casingMaterial, POTIN_CASING);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
		PistonCasing.CasingType piston = context.getOrDefault("Piston", PistonCasing.CasingType.PISTON_LV);
		int min = Collections.min(Arrays.asList(motor.getTier(), piston.getTier()));
		maxVoltage = (long) (Math.pow(4, min) * 8);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return ClientHandler.PULVERIZER_OVERLAY;
	}
}
