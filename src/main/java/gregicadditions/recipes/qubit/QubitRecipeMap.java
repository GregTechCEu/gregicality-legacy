package gregicadditions.recipes.qubit;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

public class QubitRecipeMap {

    private static final List<QubitRecipeMap> RECIPE_MAPS = new ArrayList<>();

    public final String unlocalizedName;

    private final Map<CountableIngredient, QubitRecipe> recipeQubitMap = new HashMap<>();
    private final List<QubitRecipe> recipeList = new ArrayList<>();

    public QubitRecipeMap(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        RECIPE_MAPS.add(this);
    }

    @ZenGetter("recipeMaps")
    public static List<QubitRecipeMap> getRecipeMaps() {
        return RECIPE_MAPS;
    }


    @ZenMethod
    public void addRecipe(QubitRecipe qubitRecipe) {
        CountableIngredient key = qubitRecipe.getRecipeIngredient();
        if (recipeQubitMap.containsKey(key)) {
            QubitRecipe oldRecipe = recipeQubitMap.remove(key);
            recipeList.remove(oldRecipe);
        }
        recipeQubitMap.put(key, qubitRecipe);
        recipeList.add(qubitRecipe);
    }

    @ZenMethod
    public boolean removeRecipe(QubitRecipe recipe) {
        if (recipeList.contains(recipe)) {
            this.recipeList.remove(recipe);
            this.recipeQubitMap.remove(recipe.getRecipeIngredient());
            return true;
        }
        return false;
    }

    public QubitRecipe findRecipe(long maxQubit, CountableIngredient ingredient) {
        if (ingredient == null) return null;
        QubitRecipe qubitRecipe = recipeQubitMap.get(ingredient);
        return qubitRecipe != null && qubitRecipe.matches(maxQubit, ingredient) ? qubitRecipe : null;
    }

    @ZenMethod("findRecipe")
    @Optional.Method(modid = GTValues.MODID_CT)
    public QubitRecipe ctFindRecipe(long maxQubit, IIngredient ingredient) {
        return findRecipe(maxQubit, CountableIngredient.from(CraftTweakerMC.getItemStack(ingredient)));
    }

    @ZenGetter("recipes")
    public List<QubitRecipe> getRecipeList() {
        return Collections.unmodifiableList(recipeList);
    }

    @SideOnly(Side.CLIENT)
    @ZenGetter("localizedName")
    public String getLocalizedName() {
        return I18n.format("recipemap." + unlocalizedName + ".name");
    }

    @ZenGetter("unlocalizedName")
    public String getUnlocalizedName() {
        return unlocalizedName;
    }


}
