package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicadditions.client.ClientHandler.RED_STEEL_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityLargeThermalCentrifuge extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


	public TileEntityLargeThermalCentrifuge(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, GAConfig.multis.largeThermalCentrifuge.euPercentage, GAConfig.multis.largeThermalCentrifuge.durationPercentage, GAConfig.multis.largeThermalCentrifuge.chancedBoostPercentage, GAConfig.multis.largeThermalCentrifuge.stack, true, true, true);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeThermalCentrifuge(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("AXXXA", "AXHXA", "AXXXA", "AAAAA")
				.aisle("XXXXX", "XCCCX", "X###X", "AXXXA")
				.aisle("XXMXX", "HCPCH", "X#P#X", "AXHXA")
				.aisle("XXXXX", "XCCCX", "X###X", "AXXXA")
				.aisle("AXXXA", "AXSXA", "AXXXA", "AAAAA")
				.setAmountAtLeast('L', 12)
				.setAmountAtLeast('G', 3)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', statePredicate(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.CUPRONICKEL)))
				.where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)))
				.where('H', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH).or(statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))))
				.where('#', isAirPredicate())
				.where('A', (tile) -> true)
				.where('M', motorPredicate())
				.build();
	}

	private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.RED_STEEL);
	public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeThermalCentrifuge.casingMaterial, defaultCasingState);


	public IBlockState getCasingState() {
		return casingState;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeThermalCentrifuge.casingMaterial, RED_STEEL_CASING);
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
		return Textures.THERMAL_CENTRIFUGE_OVERLAY;
	}

}
