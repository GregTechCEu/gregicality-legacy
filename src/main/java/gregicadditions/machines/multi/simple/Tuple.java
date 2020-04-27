package gregicadditions.machines.multi.simple;

import gregtech.api.recipes.Recipe;
import net.minecraftforge.items.IItemHandlerModifiable;

public class Tuple {
    private final Recipe recipe;
    private final IItemHandlerModifiable input;

    public Tuple(Recipe recipe, IItemHandlerModifiable input) {
        this.recipe = recipe;
        this.input = input;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public IItemHandlerModifiable getInput() {
        return input;
    }
}
