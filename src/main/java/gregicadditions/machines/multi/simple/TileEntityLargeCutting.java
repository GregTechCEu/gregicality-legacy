package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.item.components.ConveyorCasing;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.metal.MetalCasing2;
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
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;

import static gregicadditions.client.ClientHandler.STELLITE_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityLargeCutting extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};


	public TileEntityLargeCutting(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.CUTTER_RECIPES, GAConfig.multis.largeCutting.euPercentage, GAConfig.multis.largeCutting.durationPercentage, GAConfig.multis.largeCutting.chancedBoostPercentage, GAConfig.multis.largeCutting.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeCutting(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XXX", "XXX")
				.aisle("XXX", "XMX", "XXX")
				.aisle("XXX", "X#X", "XXX")
				.aisle("XXX", "XCX", "XXX")
				.aisle("XXX", "XSX", "XXX")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('#', isAirPredicate())
				.where('M', motorPredicate())
				.where('C', conveyorPredicate())
				.build();
	}

	private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.STELLITE);
	public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeCutting.casingMaterial, defaultCasingState);


	public IBlockState getCasingState() {
		return casingState;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeCutting.casingMaterial, STELLITE_CASING);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
		ConveyorCasing.CasingType conveyor = context.getOrDefault("Conveyor", ConveyorCasing.CasingType.CONVEYOR_LV);
		int min = Collections.min(Arrays.asList(motor.getTier(), conveyor.getTier()));
		maxVoltage = (long) (Math.pow(4, min) * 8);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.CUTTER_OVERLAY;
	}
}
