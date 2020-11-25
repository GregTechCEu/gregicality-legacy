package gregicadditions.machines.multi.override;

import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.Invar;

public class MetaTileEntityElectricBlastFurnace extends RecipeMapMultiblockController {
	public MetaTileEntityElectricBlastFurnace(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityElectricBlastFurnace(this.metaTileEntityId);
	}

	private int blastFurnaceTemperature;

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
			MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
			MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
			MultiblockAbility.INPUT_ENERGY
	};

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Invar);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "CCC", "CCC", "XXX")
				.aisle("XXX", "C#C", "C#C", "XXX")
				.aisle("XSX", "CCC", "CCC", "XXX")
				.setAmountAtLeast('L', 10)
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
			int blastFurnaceTemperature = coilType.getCoilTemperature();
			int currentTemperature = blockWorldState.getMatchContext().getOrPut("blastFurnaceTemperature", blastFurnaceTemperature);
			return currentTemperature == blastFurnaceTemperature;
		};
	}

	public static Predicate<BlockWorldState> heatingCoilPredicate2() {
		return blockWorldState -> {
			IBlockState blockState = blockWorldState.getBlockState();
			if (!(blockState.getBlock() instanceof GAHeatingCoil))
				return false;
			GAHeatingCoil blockWireCoil = (GAHeatingCoil) blockState.getBlock();
			GAHeatingCoil.CoilType coilType = blockWireCoil.getState(blockState);
			int blastFurnaceTemperature = coilType.getCoilTemperature();
			int currentTemperature = blockWorldState.getMatchContext().getOrPut("blastFurnaceTemperature", blastFurnaceTemperature);
			return currentTemperature == blastFurnaceTemperature;
		};
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		if (isStructureFormed()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature));
		}
		super.addDisplayText(textList);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		blastFurnaceTemperature = context.getOrDefault("blastFurnaceTemperature", 0);
	}

	@Override
	public void invalidateStructure() {
		super.invalidateStructure();
		this.blastFurnaceTemperature = 0;
	}

	@Override
	public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
		int recipeRequiredTemp = recipe.getIntegerProperty("blast_furnace_temperature");
		return this.blastFurnaceTemperature >= recipeRequiredTemp;
	}

	public IBlockState getCasingState() {
		return GAMetaBlocks.getMetalCasingBlockState(Invar);
	}
}
