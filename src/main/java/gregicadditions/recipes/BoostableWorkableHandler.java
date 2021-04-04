package gregicadditions.recipes;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class BoostableWorkableHandler extends FuelRecipeLogic {

    private boolean boosted = false;
    FluidStack booster;
    int fuelMultiplier;
    int euMultiplier;

    public BoostableWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer,
                           Supplier<IMultipleTankHandler> fluidTank, long maxVoltage, FluidStack booster, int fuelMultiplier,
                                    int euMultiplier) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
        this.booster = booster;
        this.fuelMultiplier = fuelMultiplier;
        this.euMultiplier = euMultiplier;
    }

    public FluidStack getFuelStack() {
        if (previousRecipe == null)
            return null;
        FluidStack fuelStack = previousRecipe.getRecipeFluid();
        return fluidTank.get().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
    }

    @Override
    protected boolean checkRecipe(FuelRecipe recipe) {
        return true;
    }

    @Override
    protected int calculateFuelAmount(FuelRecipe currentRecipe) {
        FluidStack drainBooster = fluidTank.get().drain(booster, false);
        this.boosted = drainBooster != null && drainBooster.amount >= booster.amount;
        return super.calculateFuelAmount(currentRecipe) * (boosted ? this.fuelMultiplier : 1);
    }

    @Override
    protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        if (boosted) {
            fluidTank.get().drain(booster, true);
        }
        return maxVoltage * (boosted ? this.euMultiplier : 1);
    }

    public boolean isBoosted() {
        return boosted;
    }
}
