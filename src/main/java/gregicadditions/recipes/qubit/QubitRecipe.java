package gregicadditions.recipes.qubit;

import com.google.common.collect.ImmutableList;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import stanhebben.zenscript.annotations.ZenClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@ZenClass("mods.gtadditions.recipe.QubitRecipe")
@ZenRegister
public class QubitRecipe {

    private final List<CountableIngredient> inputs;
    private final List<FluidStack> fluidInputs;
    private final int duration;
    private final long minQubit;
    private final int EUt;
    private final boolean hidden;


    public QubitRecipe(List<CountableIngredient> inputs, List<FluidStack> fluidInputs, int duration, int EUt, long minQubit, boolean hidden) {
        this.inputs = NonNullList.create();
        this.inputs.addAll(inputs);
        this.fluidInputs = ImmutableList.copyOf(fluidInputs);
        this.duration = duration;
        this.EUt = EUt;
        this.hidden = hidden;
        this.minQubit = minQubit;
        //sort input elements in descending order (i.e not consumables inputs are last)
        this.inputs.sort(Comparator.comparing(CountableIngredient::getCount).reversed());
    }


    public final boolean matches(boolean consumeIfSuccessful, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
        return matches(consumeIfSuccessful, GTUtility.itemHandlerToList(inputs), GTUtility.fluidHandlerToList(fluidInputs));
    }

    public boolean matches(boolean consumeIfSuccessful, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        int[] fluidAmountInTank = new int[fluidInputs.size()];
        int[] itemAmountInSlot = new int[inputs.size()];

        for (int i = 0; i < fluidAmountInTank.length; i++) {
            FluidStack fluidInTank = fluidInputs.get(i);
            fluidAmountInTank[i] = fluidInTank == null ? 0 : fluidInTank.amount;
        }
        for (int i = 0; i < itemAmountInSlot.length; i++) {
            ItemStack itemInSlot = inputs.get(i);
            itemAmountInSlot[i] = itemInSlot.isEmpty() ? 0 : itemInSlot.getCount();
        }


        for (CountableIngredient ingredient : this.inputs) {
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
                return false;
        }

        if (consumeIfSuccessful) {
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

    ///////////////////
    //    Getters    //
    ///////////////////

    public List<CountableIngredient> getInputs() {
        return inputs;
    }


    public List<FluidStack> getFluidInputs() {
        return fluidInputs;
    }

    public boolean hasInputFluid(FluidStack fluid) {
        for (FluidStack fluidStack : fluidInputs) {
            if (fluidStack.isFluidEqual(fluid)) {
                return true;
            }
        }
        return false;
    }

    public long getMinQubit() {
        return minQubit;
    }

    public int getDuration() {
        return duration;
    }

    public int getEUt() {
        return EUt;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean hasValidInputsForDisplay() {
        boolean hasValidInputs = true;
        for (CountableIngredient ingredient : inputs) {
            ItemStack[] matchingItems = ingredient.getIngredient().getMatchingStacks();
            hasValidInputs &= Arrays.stream(matchingItems).anyMatch(s -> !s.isEmpty());
        }
        return hasValidInputs;
    }
}
