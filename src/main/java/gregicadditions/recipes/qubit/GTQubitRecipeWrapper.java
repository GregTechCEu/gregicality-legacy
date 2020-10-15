package gregicadditions.recipes.qubit;

import codechicken.lib.util.ItemNBTUtils;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.integration.jei.utils.JEIHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GTQubitRecipeWrapper implements IRecipeWrapper {


    private static final int lineHeight = 10;
    private final QubitRecipeMap recipeMap;
    private final QubitRecipe recipe;

    public GTQubitRecipeWrapper(QubitRecipeMap recipeMap, QubitRecipe recipe) {
        this.recipeMap = recipeMap;
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        if (!recipe.getInputs().isEmpty()) {
            List<CountableIngredient> recipeInputs = recipe.getInputs();
            List<List<ItemStack>> matchingInputs = new ArrayList<>(recipeInputs.size());
            for (CountableIngredient ingredient : recipeInputs) {
                List<ItemStack> ingredientValues = Arrays.stream(ingredient.getIngredient().getMatchingStacks())
                        .map(ItemStack::copy)
                        .sorted(OreDictUnifier.getItemStackComparator())
                        .collect(Collectors.toList());
                ingredientValues.forEach(stack -> {
                    if (ingredient.getCount() == 0) {
                        ItemNBTUtils.setBoolean(stack, "not_consumed", true);
                        stack.setCount(1);
                    } else stack.setCount(ingredient.getCount());
                });
                matchingInputs.add(ingredientValues);
            }
            ingredients.setInputLists(VanillaTypes.ITEM, matchingInputs);
        }

        if (!recipe.getFluidInputs().isEmpty()) {
            List<FluidStack> recipeInputs = recipe.getFluidInputs()
                    .stream().map(FluidStack::copy)
                    .collect(Collectors.toList());
            recipeInputs.forEach(stack -> {
                if (stack.amount == 0) {
                    if (stack.tag == null)
                        stack.tag = new NBTTagCompound();
                    stack.tag.setBoolean("not_consumed", true);
                    stack.amount = 1;
                }
            });
            ingredients.setInputs(VanillaTypes.FLUID, recipeInputs);
        }
    }

    public void addTooltip(int slotIndex, boolean input, Object ingredient, List<String> tooltip) {
        NBTTagCompound tagCompound;
        if (ingredient instanceof ItemStack) {
            tagCompound = ((ItemStack) ingredient).getTagCompound();
        } else if (ingredient instanceof FluidStack) {
            tagCompound = ((FluidStack) ingredient).tag;
        } else {
            throw new IllegalArgumentException("Unknown ingredient type: " + ingredient.getClass());
        }
        if (tagCompound != null && tagCompound.hasKey("chance")) {
            String chanceString = Recipe.formatChanceValue(tagCompound.getInteger("chance"));
            String boostString = Recipe.formatChanceValue(tagCompound.getInteger("boost_per_tier"));
            tooltip.add(I18n.format("gregtech.recipe.chance", chanceString, boostString));

        } else if (tagCompound != null && tagCompound.hasKey("not_consumed")) {
            tooltip.add(I18n.format("gregtech.recipe.not_consumed"));
        }
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        int duration = recipe.getDuration();
        long qbit = recipe.getMinQubit();
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.total", Math.abs((long) recipe.getEUt()) * recipe.getDuration()), 0, recipeHeight - 40, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format(recipe.getEUt() >= 0 ? "gregtech.recipe.eu" : "gregtech.recipe.eu_inverted", Math.abs(recipe.getEUt()), JEIHelpers.getMinTierForVoltage(recipe.getEUt())), 0, recipeHeight - 50, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.qbit", qbit), 0, 80, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.duration", duration / 20.0), 0, 90, 0x111111);
    }


}
