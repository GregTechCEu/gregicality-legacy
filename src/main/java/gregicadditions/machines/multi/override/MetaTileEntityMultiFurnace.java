package gregicadditions.machines.multi.override;

import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.Invar;

public class MetaTileEntityMultiFurnace extends gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiFurnace {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
			MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY
	};

	public MetaTileEntityMultiFurnace(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityMultiFurnace(this.metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "CCC", "XXX")
				.aisle("XXX", "C#C", "XXX")
				.aisle("XSX", "CCC", "XXX")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', heatingCoilPredicate().or(heatingCoilPredicate2()))
				.where('#', isAirPredicate())
				.build();
	}

	public static Predicate<BlockWorldState> heatingCoilPredicate() {
		return blockWorldState -> {
			IBlockState blockState = blockWorldState.getBlockState();
			if (!(blockState.getBlock() instanceof BlockWireCoil))
				return false;
			BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
			BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
			int heatingCoilDiscount = coilType.getEnergyDiscount();
			int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
			int heatingCoilLevel = coilType.getLevel();
			int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
			return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
		};
	}

	public static Predicate<BlockWorldState> heatingCoilPredicate2() {
		return blockWorldState -> {
			IBlockState blockState = blockWorldState.getBlockState();
			if (!(blockState.getBlock() instanceof GAHeatingCoil))
				return false;
			GAHeatingCoil blockWireCoil = (GAHeatingCoil) blockState.getBlock();
			GAHeatingCoil.CoilType coilType = blockWireCoil.getState(blockState);
			int heatingCoilDiscount = coilType.getEnergyDiscount();
			int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
			int heatingCoilLevel = coilType.getLevel();
			int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
			return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
		};
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		this.heatingCoilLevel = context.getOrDefault("heatingCoilLevel", 0);
		this.heatingCoilDiscount = context.getOrDefault("heatingCoilDiscount", 0);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Invar);
	}

	@Override
	public IBlockState getCasingState() {
		return GAMetaBlocks.getMetalCasingBlockState(Invar);
	}
}
