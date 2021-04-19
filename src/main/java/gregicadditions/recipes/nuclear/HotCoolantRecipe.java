package gregicadditions.recipes.nuclear;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.liquid.MCLiquidStack;
import gregtech.api.GTValues;
import gregtech.api.recipes.recipes.FuelRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional.Method;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtadditions.recipe.HotCoolantRecipe")
@ZenRegister
public class HotCoolantRecipe {

    private final FluidStack recipeFluid;
    private final int duration;
    private final long minVoltage;
    private final FluidStack fluidOutput;

    public HotCoolantRecipe(FluidStack recipeFluid, int duration, long minVoltage, FluidStack fluidOutput) {
        this.recipeFluid = recipeFluid.copy();
        this.duration = duration;
        this.minVoltage = minVoltage;
        this.fluidOutput = fluidOutput;
    }

    @ZenMethod("create")
    @Method(modid = GTValues.MODID_CT)
    @SuppressWarnings("unused")
    public static FuelRecipe craftTweakerCreate(ILiquidStack liquidStack, int duration, long minVoltage) {
        return new FuelRecipe(CraftTweakerMC.getLiquidStack(liquidStack), duration, minVoltage);
    }

    public FluidStack getRecipeFluid() {
        return recipeFluid.copy();
    }

    public FluidStack getOutputFluid() {
        return fluidOutput.copy();
    }

    @ZenGetter("duration")
    public int getDuration() {
        return duration;
    }

    @ZenGetter("minVoltage")
    public long getMinVoltage() {
        return minVoltage;
    }

    public boolean matches(long maxVoltage, FluidStack inputFluid) {
        return maxVoltage >= getMinVoltage() && getRecipeFluid().isFluidEqual(inputFluid);
    }

    @ZenGetter("fluid")
    @Method(modid = GTValues.MODID_CT)
    @SuppressWarnings("unused")
    public ILiquidStack ctGetFluid() {
        return new MCLiquidStack(getRecipeFluid());
    }
}
