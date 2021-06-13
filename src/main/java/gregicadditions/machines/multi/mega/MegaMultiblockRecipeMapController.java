package gregicadditions.machines.multi.mega;

import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.InventoryUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public abstract class MegaMultiblockRecipeMapController extends LargeSimpleRecipeMapMultiblockController {

    public MegaMultiblockRecipeMapController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
        super(metaTileEntityId, recipeMap, EUtPercentage, durationPercentage, chancePercentage, stack);
        this.recipeMapWorkable = new MegaMultiblockRecipeLogic(this, EUtPercentage, durationPercentage, chancePercentage);
    }

    public MegaMultiblockRecipeMapController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack, boolean canDistinct, boolean hasMuffler, boolean hasMaintenance) {
        super(metaTileEntityId, recipeMap, EUtPercentage, durationPercentage, chancePercentage, stack, canDistinct, hasMuffler, hasMaintenance);
        this.recipeMapWorkable = new MegaMultiblockRecipeLogic(this, EUtPercentage, durationPercentage, chancePercentage);
    }

    public static Predicate<BlockWorldState> frameworkPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing)) {
                return false;
            } else {
                GAMultiblockCasing framework = (GAMultiblockCasing) blockState.getBlock();
                GAMultiblockCasing.CasingType tieredCasingType = framework.getState(blockState);
                GAMultiblockCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType);

                int tier = tieredCasingType.getTier();
                int currentTier = blockWorldState.getMatchContext().getOrPut("casingTier", tier);

                return currentCasing.getName().equals(tieredCasingType.getName()) && tier == currentTier;
            }
        };
    }

    public static Predicate<BlockWorldState> frameworkPredicate2() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing2)) {
                return false;
            } else {
                GAMultiblockCasing2 framework = (GAMultiblockCasing2) blockState.getBlock();
                GAMultiblockCasing2.CasingType tieredCasingType = framework.getState(blockState);
                GAMultiblockCasing2.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("framework", tieredCasingType);

                int tier = tieredCasingType.getTier();
                int currentTier = blockWorldState.getMatchContext().getOrPut("casingTier", tier);

                return currentCasing.getName().equals(tieredCasingType.getName()) && tier == currentTier;
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.3", (double) ((MegaMultiblockRecipeLogic) this.recipeMapWorkable).getDurationPercentage()/100));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("casingTier", -1);
        if (tier < 0)
            maxVoltage = 0;
        else
            maxVoltage = (long) (Math.pow(4, tier) * 8);
    }

    public static class MegaMultiblockRecipeLogic extends LargeSimpleMultiblockRecipeLogic {

        protected static final int OVERCLOCK_FACTOR = 2;
        protected static final int MAX_ITEMS_LIMIT = 256;

        public MegaMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage, 256);

        }

        @Override
        protected Recipe createRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe matchingRecipe) {
            int EUt;
            int duration;
            int minMultiplier = Integer.MAX_VALUE;

            Set<ItemStack> countIngredients = new HashSet<>();
            if (matchingRecipe.getInputs().size() != 0) {
                this.findIngredients(countIngredients, inputs);
                minMultiplier = Math.min(MAX_ITEMS_LIMIT, this.getMinRatioItem(countIngredients, matchingRecipe, MAX_ITEMS_LIMIT));
            }

            Map<String, Integer> countFluid = new HashMap<>();
            if (matchingRecipe.getFluidInputs().size() != 0) {

                this.findFluid(countFluid, fluidInputs);
                minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, matchingRecipe, MAX_ITEMS_LIMIT));
            }

            if (minMultiplier == Integer.MAX_VALUE) {
                GALog.logger.error("Cannot calculate ratio of items for mega multiblocks");
                return null;
            }

            EUt = matchingRecipe.getEUt();
            duration = matchingRecipe.getDuration();

            // Get parallel recipes to run: [0, 256]
            int multiplier = Math.min(minMultiplier, (int) (getMaxVoltage() / EUt));

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, matchingRecipe, multiplier);

            // determine if there is enough room in the output to fit all of this
            boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(outputI, this.getOutputInventory());
            // if there isn't, we can't process this recipe.
            if (!canFitOutputs)
                return null;

            // Get EUt for the recipe, pre overclocking
            long totalEUt = (long) multiplier * EUt;

            EUt = (int) (totalEUt / OVERCLOCK_FACTOR);
            duration /= OVERCLOCK_FACTOR;

            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt(Math.max(1, EUt * this.getEUtPercentage() / 100))
                    .duration((int) Math.max(3, duration * (this.getDurationPercentage() / 100.0)));

            copyChancedItemOutputs(newRecipe, matchingRecipe, multiplier);

            return newRecipe.build().getResult();
        }
    }

}
