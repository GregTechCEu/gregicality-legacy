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
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TileEntityChemicalPlant extends MultiRecipeMapMultiblockController { //todo chemical plant coil requirements

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};


	public TileEntityChemicalPlant(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId,
				GARecipeMaps.LARGE_CHEMICAL_RECIPES,
				GAConfig.multis.chemicalPlant.euPercentage,
				GAConfig.multis.chemicalPlant.durationPercentage,
				GAConfig.multis.chemicalPlant.chancedBoostPercentage,
				GAConfig.multis.chemicalPlant.stack,
				new RecipeMap[]{GARecipeMaps.LARGE_CHEMICAL_RECIPES, GARecipeMaps.CHEMICAL_PLANT_RECIPES});
		this.recipeMapWorkable = new AdvancedChemicalReactorWorkableHandler(this);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityChemicalPlant(metaTileEntityId);
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

	@Override
	public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
		return Textures.CHEMICAL_REACTOR_OVERLAY;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(I18n.format("gtadditions.multiblock.chemical_plant.tooltip.1"));
	}

	protected static class AdvancedChemicalReactorWorkableHandler extends MultiRecipeMapMultiblockRecipeLogic {

		public AdvancedChemicalReactorWorkableHandler(RecipeMapMultiblockController tileEntity) {
			super(tileEntity,
					GAConfig.multis.chemicalPlant.euPercentage,
					GAConfig.multis.chemicalPlant.durationPercentage,
					GAConfig.multis.chemicalPlant.chancedBoostPercentage,
					GAConfig.multis.chemicalPlant.stack,
					((TileEntityChemicalPlant) tileEntity).recipeMaps);
		}

		@Override
		protected int getMinRatioItem(Set<ItemStack> countIngredients, Recipe r, int maxItemsLimit) {
			if (((TileEntityChemicalPlant) metaTileEntity).getRecipeMapIndex() == 1)
				return 1;
			int minMultiplier = Integer.MAX_VALUE;
			for (CountableIngredient ci : r.getInputs()) {
				if (ci.getCount() == 0) {
					continue;
				}
				for (ItemStack wholeItemStack : countIngredients) {
					if (ci.getIngredient().apply(wholeItemStack)) {
						int ratio = Math.min(maxItemsLimit, wholeItemStack.getCount() / ci.getCount());
						if (ratio < minMultiplier) {
							minMultiplier = ratio;
						}
						break;
					}
				}
			}
			return minMultiplier;
		}

		@Override
		protected int getMinRatioFluid(Map<String, Integer> countFluid, Recipe r, int maxItemsLimit) {
			if (((TileEntityChemicalPlant) metaTileEntity).getRecipeMapIndex() == 1)
				return 1;
			int minMultiplier = Integer.MAX_VALUE;
			for (FluidStack fs : r.getFluidInputs()) {
				if (fs.amount != 0) { // skip notConsumable fluids
					String name = fs.getFluid().getUnlocalizedName();
					int ratio = Math.min(maxItemsLimit, countFluid.get(name) / fs.amount);
					if (ratio < minMultiplier) {
						minMultiplier = ratio;
					}
				}
			}
			return minMultiplier;
		}

	}
}
