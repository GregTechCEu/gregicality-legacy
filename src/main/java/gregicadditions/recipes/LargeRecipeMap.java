package gregicadditions.recipes;

import crafttweaker.annotations.ZenRegister;
import gregicadditions.Gregicality;
import gregicadditions.recipes.crafttweaker.CTLargeRecipeBuilder;
import gregicadditions.recipes.map.LargeRecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import net.minecraftforge.fml.common.Optional.Method;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtadditions.recipe.LargeRecipeMap")
@ZenRegister
public class LargeRecipeMap extends RecipeMap<LargeRecipeBuilder> {

    public LargeRecipeMap(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, LargeRecipeBuilder defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
    }

    @ZenMethod("recipeBuilder")
    @Method(modid = Gregicality.MODID)
    public CTRecipeBuilder ctRecipeBuilder() {
        return new CTLargeRecipeBuilder(recipeBuilder());
    }

}
