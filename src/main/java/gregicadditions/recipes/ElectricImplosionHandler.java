package gregicadditions.recipes;

import gregicadditions.utils.GALog;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.ItemStackHashStrategy;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ElectricImplosionHandler {

    private static final Hash.Strategy<ItemStack> strategy = new ItemStackHashStrategy.ItemStackHashStrategyBuilder()
            .compareItem(true)
            .compareDamage(true)
            .compareCount(true)
            .build();

    // TODO Verify that this does in fact ignore explosives
    public static void buildElectricImplosionRecipes() {
        Set<ItemStack> recipesUsed = new ObjectOpenCustomHashSet<>(strategy);

        RecipeMaps.IMPLOSION_RECIPES.getRecipeList().forEach(recipe -> {

            List<ItemStack> inputs =
                    recipe.getInputs().stream()
                                      .map(CountableIngredient::getIngredient)
                                      .map(Ingredient::getMatchingStacks)
                                      .filter(stack -> stack.length != 0)
                                      .map(array -> array[0])
                                      .collect(Collectors.toList());

            // TODO handle this better than just the first item, as it could have 2 inputs
            if (inputs.size() > 1) {

                if (!recipesUsed.contains(inputs.get(1))) {

                    GARecipeMaps.ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                            .inputs(inputs.subList(1, inputs.size()))
                            .outputs(recipe.getOutputs())
                            .buildAndRegister();

                    recipesUsed.add(inputs.get(1));
                }
            } else GALog.logger.error("Recipe failed to register: " // TODO Remove this logging once done
                    + recipe.getOutputs().stream()
                                         .map(ItemStack::getDisplayName)
                                         .collect(Collectors.toList())
            );
        });
    }
}
