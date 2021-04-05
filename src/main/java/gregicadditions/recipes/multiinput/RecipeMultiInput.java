package gregicadditions.recipes.multiinput;

import com.google.common.collect.ImmutableList;
import gregicadditions.recipes.multiinput.impl.MultiInputFluid;
import gregicadditions.recipes.multiinput.impl.IMultiInputItem;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.MatchingMode;
import gregtech.api.recipes.Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class RecipeMultiInput extends Recipe {

    private final List<IMultiInputItem> multiInputItems;
    private final List<MultiInputFluid> multiInputFluids;
    private final int recipeTier;

    public RecipeMultiInput(List<CountableIngredient> inputs,
                            List<ItemStack> outputs,
                            List<ChanceEntry> chancedOutputs,
                            List<FluidStack> fluidInputs,
                            List<FluidStack> fluidOutputs,
                            Map<String, Object> recipeProperties,
                            int duration,
                            int EUt,
                            boolean hidden,
                            List<IMultiInputItem> multiInputItems,
                            List<MultiInputFluid> multiInputFluids,
                            int recipeTier) {

        super(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs, recipeProperties, duration, EUt, hidden);

        this.multiInputItems = ImmutableList.copyOf(multiInputItems);
        this.multiInputFluids = ImmutableList.copyOf(multiInputFluids);
        this.recipeTier = recipeTier;
    }

    @Override
    public boolean matches(boolean consumeIfSuccessful, List<ItemStack> inputs, List<FluidStack> fluidInputs, MatchingMode matchingMode) {
        Pair<Boolean, Integer[]> fluids = null;
        Pair<Boolean, Integer[]> items = null;

        if (matchingMode == MatchingMode.IGNORE_FLUIDS) {
            if (getInputs().isEmpty() && getMultiInputItems().isEmpty()) {
                return false;
            }
        } else {
            fluids = matchesFluid(fluidInputs);
            if (!fluids.getKey()) {
                return false;
            }
        }

        if (matchingMode == MatchingMode.IGNORE_ITEMS) {
            if (getFluidInputs().isEmpty() && getMultiInputFluids().isEmpty()) {
                return false;
            }
        } else {
            items = matchesItems(inputs); // TODO
            if (!items.getKey()) {
                return false;
            }
        }

        if (consumeIfSuccessful && matchingMode == MatchingMode.DEFAULT) {
            Integer[] fluidAmountInTank = fluids.getValue();
            Integer[] itemAmountInSlot = items.getValue();
            for (int i = 0; i < fluidAmountInTank.length; i++) {
                FluidStack fluidStack = fluidInputs.get(i);
                int fluidAmount = fluidAmountInTank[i];
                if (fluidStack == null || fluidStack.amount == fluidAmount)
                    continue;
                fluidStack.amount = fluidAmount;
                if (fluidStack.amount == 0)
                    fluidInputs.set(i, null);
            }
            for (int i = 0; i < itemAmountInSlot.length; i++) {
                ItemStack itemInSlot = inputs.get(i);
                int itemAmount = itemAmountInSlot[i];
                if (itemInSlot.isEmpty() || itemInSlot.getCount() == itemAmount)
                    continue;
                itemInSlot.setCount(itemAmountInSlot[i]);
            }
        }

        return true;
    }

    // TODO
    private Pair<Boolean, Integer[]> matchesItems(List<ItemStack> inputs) {
        Integer[] itemAmountInSlot = new Integer[inputs.size()];

        for (int i = 0; i < itemAmountInSlot.length; i++) {
            ItemStack itemInSlot = inputs.get(i);
            itemAmountInSlot[i] = itemInSlot.isEmpty() ? 0 : itemInSlot.getCount();
        }

        for (CountableIngredient ingredient : this.getInputs()) {
            int ingredientAmount = ingredient.getCount();
            boolean isNotConsumed = false;
            if (ingredientAmount == 0) {
                ingredientAmount = 1;
                isNotConsumed = true;
            }
            for (int i = 0; i < inputs.size(); i++) {
                ItemStack inputStack = inputs.get(i);
                if (inputStack.isEmpty() || !ingredient.getIngredient().apply(inputStack))
                    continue;
                int itemAmountToConsume = Math.min(itemAmountInSlot[i], ingredientAmount);
                ingredientAmount -= itemAmountToConsume;
                if (!isNotConsumed) itemAmountInSlot[i] -= itemAmountToConsume;
                if (ingredientAmount == 0) break;
            }
            if (ingredientAmount > 0)
                return Pair.of(false, itemAmountInSlot);
        }

        return Pair.of(true, itemAmountInSlot);
    }

    private Pair<Boolean, Integer[]> matchesFluid(List<FluidStack> fluidInputs) {
        Integer[] fluidAmountInTank = new Integer[fluidInputs.size()];

        for (int i = 0; i < fluidAmountInTank.length; i++) {
            FluidStack fluidInTank = fluidInputs.get(i);
            fluidAmountInTank[i] = fluidInTank == null ? 0 : fluidInTank.amount;
        }

        for (FluidStack fluid : this.getFluidInputs()) {
            int fluidAmount = fluid.amount;
            boolean isNotConsumed = false;
            if (fluidAmount == 0) {
                fluidAmount = 1;
                isNotConsumed = true;
            }
            for (int i = 0; i < fluidInputs.size(); i++) {
                FluidStack tankFluid = fluidInputs.get(i);
                if (tankFluid == null || !tankFluid.isFluidEqual(fluid))
                    continue;
                int fluidAmountToConsume = Math.min(fluidAmountInTank[i], fluidAmount);
                fluidAmount -= fluidAmountToConsume;
                if (!isNotConsumed) fluidAmountInTank[i] -= fluidAmountToConsume;
                if (fluidAmount == 0) break;
            }
            if (fluidAmount > 0)
                return Pair.of(false, fluidAmountInTank);
        }

        // Check for MultiInput fluids
        for (MultiInputFluid fluid : this.getMultiInputFluids()) {
            int fluidAmount = fluid.getBaseAmount();

            for (int i = 0; i < fluidInputs.size(); i++) {
                FluidStack tankFluid = fluidInputs.get(i);
                if (tankFluid == null || !fluid.matches(tankFluid))
                    continue;
                fluidAmount = fluid.getRecipeInput(tankFluid, recipeTier).amount;
                int fluidAmountToConsume = Math.min(fluidAmountInTank[i], fluidAmount);
                fluidAmount -= fluidAmountToConsume;
                fluidAmountInTank[i] -= fluidAmountToConsume;
                if (fluidAmount == 0) break;
            }
            if (fluidAmount > 0)
                return Pair.of(false, fluidAmountInTank);
        }

        return Pair.of(true, fluidAmountInTank);
    }

    // TODO Override this if needed for custom JEI page
    @Override
    public boolean hasValidInputsForDisplay() {
        return super.hasValidInputsForDisplay();
    }

    public List<IMultiInputItem> getMultiInputItems() {
        return multiInputItems;
    }

    public List<MultiInputFluid> getMultiInputFluids() {
        return multiInputFluids;
    }
}
