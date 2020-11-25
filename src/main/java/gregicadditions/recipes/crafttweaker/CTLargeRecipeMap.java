package gregicadditions.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import gregicadditions.Gregicality;
import gregicadditions.recipes.LargeRecipeMap;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtadditions.recipe.LargeRecipeMap")
@ZenRegister
public class CTLargeRecipeMap {

    private LargeRecipeMap largeRecipeMap;

    CTLargeRecipeMap(LargeRecipeMap largeRecipeMap) {
        this.largeRecipeMap = largeRecipeMap;
    }

    @ZenMethod("recipeBuilder")
    @Optional.Method(modid = Gregicality.MODID)
    public CTLargeRecipeBuilder ctLargeRecipeBuilder() {
        return largeRecipeMap.ctLargeRecipeBuilder();
    }

    @ZenMethod("getByName")
    public static CTLargeRecipeMap ctGetByName(String unlocalizedName) {
        return new CTLargeRecipeMap(LargeRecipeMap.getLargeMapByName(unlocalizedName));
    }

}
