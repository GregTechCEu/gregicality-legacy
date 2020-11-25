package gregicadditions.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicadditions.Gregicality;
import gregicadditions.recipes.LargeRecipeMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.crafttweaker.CTRecipe;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @ZenMethod("findRecipe")
    @Optional.Method(modid = "crafttweaker")
    @Nullable
    public CTRecipe ctFindRecipe(long maxVoltage, IItemStack[] itemInputs, ILiquidStack[] fluidInputs, @stanhebben.zenscript.annotations.Optional(valueLong = 2147483647L) int outputFluidTankCapacity) {
        return largeRecipeMap.ctFindRecipe(maxVoltage, itemInputs, fluidInputs, outputFluidTankCapacity);
    }

    @ZenGetter("recipes")
    @Optional.Method(modid = "crafttweaker")
    public List<CTRecipe> ctGetRecipeList() {
        return largeRecipeMap.ccGetRecipeList();
    }

    @SideOnly(Side.CLIENT)
    @ZenGetter("localizedName")
    public String getLocalizedName() {
        return largeRecipeMap.getLocalizedName();
    }

    @ZenGetter("unlocalizedName")
    public String getUnlocalizedName() {
        return largeRecipeMap.getUnlocalizedName();
    }

    @ZenGetter("minInputs")
    public int getMinInputs() {
        return largeRecipeMap.getMinInputs();
    }

    @ZenGetter("maxInputs")
    public int getMaxInputs() {
        return largeRecipeMap.getMaxInputs();
    }

    @ZenGetter("minOutputs")
    public int getMinOutputs() {
        return largeRecipeMap.getMinOutputs();
    }

    @ZenGetter("maxOutputs")
    public int getMaxOutputs() {
        return largeRecipeMap.getMaxOutputs();
    }

    @ZenGetter("minFluidInputs")
    public int getMinFluidInputs() {
        return largeRecipeMap.getMinFluidInputs();
    }

    @ZenGetter("maxFluidInputs")
    public int getMaxFluidInputs() {
        return largeRecipeMap.getMaxFluidInputs();
    }

    @ZenGetter("minFluidOutputs")
    public int getMinFluidOutputs() {
        return largeRecipeMap.getMinFluidOutputs();
    }

    @ZenGetter("maxFluidOutputs")
    public int getMaxFluidOutputs() {
        return largeRecipeMap.getMaxFluidOutputs();
    }

    @ZenMethod
    public String toString() {
        return largeRecipeMap.toString();
    }

}
