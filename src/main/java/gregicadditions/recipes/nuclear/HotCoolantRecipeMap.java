package gregicadditions.recipes.nuclear;

import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.GTValues;
import gregtech.api.recipes.FluidKey;
import gregtech.api.recipes.recipes.FuelRecipe;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

public class HotCoolantRecipeMap {

    private static final List<HotCoolantRecipeMap> RECIPE_MAPS = new ArrayList<>();

    public final String unlocalizedName;

    private final Map<FluidKey, HotCoolantRecipe> recipeFluidMap = new HashMap<>();
    private final List<HotCoolantRecipe> recipeList = new ArrayList<>();

    public HotCoolantRecipeMap(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        RECIPE_MAPS.add(this);
    }

    @ZenGetter("recipeMaps")
    public static List<HotCoolantRecipeMap> getRecipeMaps() {
        return RECIPE_MAPS;
    }

    @ZenMethod
    public void addRecipe(HotCoolantRecipe fuelRecipe) {
        FluidKey fluidKey = new FluidKey(fuelRecipe.getRecipeFluid());
        if (recipeFluidMap.containsKey(fluidKey)) {
            HotCoolantRecipe oldRecipe = recipeFluidMap.remove(fluidKey);
            recipeList.remove(oldRecipe);
        }
        recipeFluidMap.put(fluidKey, fuelRecipe);
        recipeList.add(fuelRecipe);
    }

    @ZenMethod
    // TODO Pretty sure this doesn't work
    public boolean removeRecipe(FuelRecipe recipe) {
        if (recipeList.contains(recipe)) {
            this.recipeList.remove(recipe);
            this.recipeFluidMap.remove(new FluidKey(recipe.getRecipeFluid()));
            return true;
        }
        return false;
    }

    public HotCoolantRecipe findRecipe(long maxVoltage, FluidStack inputFluid) {
        if (inputFluid == null) return null;
        FluidKey fluidKey = new FluidKey(inputFluid);
        HotCoolantRecipe fuelRecipe = recipeFluidMap.get(fluidKey);
        return fuelRecipe != null && fuelRecipe.matches(maxVoltage, inputFluid) ? fuelRecipe : null;
    }

    @ZenMethod("findRecipe")
    @Method(modid = GTValues.MODID_CT)
    @SuppressWarnings("unused")
    public HotCoolantRecipe ctFindRecipe(long maxVoltage, ILiquidStack inputFluid) {
        return findRecipe(maxVoltage, CraftTweakerMC.getLiquidStack(inputFluid));
    }

    @ZenGetter("recipes")
    public List<HotCoolantRecipe> getRecipeList() {
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
