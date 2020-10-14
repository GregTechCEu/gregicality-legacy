package gregicadditions.recipes.qubit;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import gregtech.api.recipes.crafttweaker.InputIngredient;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtadditions.recipe.QubitRecipe")
@ZenRegister
public class QubitRecipe {

    private final CountableIngredient ingredient;
    private final int duration;
    private final long minQubit;


    public QubitRecipe(String oredict, int count, int duration, long minQubit) {
        this(CountableIngredient.from(oredict, count), duration, minQubit);
    }

    public QubitRecipe(OrePrefix oredict, Material material, int count, int duration, long minQubit) {
        this(CountableIngredient.from(oredict, material, count), duration, minQubit);
    }

    public QubitRecipe(OrePrefix oredict, Material material, int duration, long minQubit) {
        this(CountableIngredient.from(oredict, material), duration, minQubit);
    }

    public QubitRecipe(CountableIngredient ingredient, int duration, long minQubit) {
        this.ingredient = ingredient;
        this.duration = duration;
        this.minQubit = minQubit;
    }

    @ZenMethod("create")
    @Optional.Method(modid = GTValues.MODID_CT)
    public static QubitRecipe craftTweakerCreate(IIngredient ingredient, int duration, long minQubit) {
        return new QubitRecipe(new CountableIngredient(new CTRecipeBuilder.CraftTweakerIngredientWrapper(ingredient), ingredient.getAmount()), duration, minQubit);
    }

    public CountableIngredient getRecipeIngredient() {
        return ingredient;
    }

    @ZenGetter("duration")
    public int getDuration() {
        return duration;
    }

    @ZenGetter("minQubit")
    public long getMinQubit() {
        return minQubit;
    }

    public boolean matches(long maxQubit, CountableIngredient ingredient) {
        return maxQubit >= getMinQubit() && getRecipeIngredient().equals(ingredient);
    }

    @ZenGetter("input")
    @Optional.Method(modid = GTValues.MODID_CT)
    public InputIngredient ctGetFluid() {
        return new InputIngredient(ingredient);
    }
}
