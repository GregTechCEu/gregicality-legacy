package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.ConveyorCasing;
import gregicadditions.item.components.RobotArmCasing;
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
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class TileEntityLargeAssembler extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


	public TileEntityLargeAssembler(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.ASSEMBLER_RECIPES, GAConfig.multis.largeAssembler.euPercentage, GAConfig.multis.largeAssembler.durationPercentage, GAConfig.multis.largeAssembler.chancedBoostPercentage, GAConfig.multis.largeAssembler.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeAssembler(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start(FRONT, UP, RIGHT)
				.aisle("XXXXX", "XXXXX", "XXXXX", "#XXXX")
				.aisle("XXXXX", "SCPCX", "XRPRX", "#XXXX")
				.aisle("XXXXX", "GKPKX", "G#CRX", "#GGXX").setRepeatable(0, 9)
				.aisle("XXXXX", "XXXXX", "XXXXX", "#XXXX")
				.setAmountAtLeast('X', 30)
				.where('S', selfPredicate())
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('#', state -> true)
				.where('R', robotArmPredicate())
				.where('C', conveyorPredicate())
				.where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS)))
				.where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
				.where('K', statePredicate(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR)))
				.build();
	}

	public IBlockState getCasingState() {
		return GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return ClientHandler.LARGE_ASSEMBLER;
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		ConveyorCasing.CasingType conveyor = context.getOrDefault("Conveyor", ConveyorCasing.CasingType.CONVEYOR_LV);
		RobotArmCasing.CasingType robotArm = context.getOrDefault("RobotArm", RobotArmCasing.CasingType.ROBOT_ARM_LV);
		int min = Collections.min(Arrays.asList(conveyor.getTier(), robotArm.getTier()));
		maxVoltage = (long) (Math.pow(4, min) * 8);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.ASSEMBLER_OVERLAY;
	}

}
