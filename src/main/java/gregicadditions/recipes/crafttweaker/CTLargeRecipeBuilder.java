package gregicadditions.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicadditions.recipes.map.LargeRecipeBuilder;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.stream.Collectors;

@ZenClass("mods.gtadditions.recipe.LargeRecipeBuilder")
@ZenRegister
public class CTLargeRecipeBuilder extends CTRecipeBuilder {

    private final LargeRecipeBuilder largeRecipeBuilder;


    public CTLargeRecipeBuilder(LargeRecipeBuilder backingBuilder) {
        super(backingBuilder);
        this.largeRecipeBuilder = backingBuilder;
    }

    @ZenMethod
    public CTLargeRecipeBuilder dupeForSmall() {
        this.largeRecipeBuilder.dupeForSmall();
        return this;
    }


}
