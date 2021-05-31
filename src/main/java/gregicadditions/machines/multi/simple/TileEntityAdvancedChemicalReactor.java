package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.components.MotorCasing;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class TileEntityAdvancedChemicalReactor extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


	public TileEntityAdvancedChemicalReactor(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, GARecipeMaps.LARGE_CHEMICAL_RECIPES, GAConfig.multis.advancedChemicalReactor.euPercentage, GAConfig.multis.advancedChemicalReactor.durationPercentage, GAConfig.multis.advancedChemicalReactor.chancedBoostPercentage, GAConfig.multis.advancedChemicalReactor.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityAdvancedChemicalReactor(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("X###X", "XXXXX", "X###X", "XXXXX", "X###X")
				.aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
				.aisle("X###X", "XPPPX", "XMMMX", "XPPPX", "X###X")
				.aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
				.aisle("X###X", "SXXXX", "X###X", "XXXXX", "X###X")
				.setAmountAtLeast('L', 22)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', TileEntityLargeChemicalReactor.heatingCoilPredicate())
				.where('P', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE)))
				.where('#', (tile) -> true)
				.where('M', motorPredicate())
				.build();
	}

	public IBlockState getCasingState() {
		return GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CHEMICALLY_INERT);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return ClientHandler.CHEMICALLY_INERT;
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
		return Textures.CHEMICAL_REACTOR_OVERLAY;
	}
}
