package gregicadditions.machines.multi.advance;

import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class RocketEngineWorkableHandler extends FuelRecipeLogic {

    private boolean isUsingHydrogen = false;
    private int hydrogenNeededToBoost;
    private static final int AIR_INTAKE_PER_SEC = 37500;
    private static final int CO2_INTAKE_PER_SEC = 10;
    private static final int TICK_PER_SEC = 20;
    private FluidStack fuelStack;

    public RocketEngineWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                       Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
    }

    public FluidStack getFuelStack() {
        if (fuelStack != null && fuelStack.amount == 0)
            fuelStack = null;
        return fuelStack;
    }

    // Override to remove the "Boosted!" from the GUI when not running
    // Also resets hydrogenNeededToBoost, just for the GUI really.
    @Override
    protected void setActive(boolean active) {
        if (!active && this.isActive()) {
            isUsingHydrogen = false;
            hydrogenNeededToBoost = 0;
        }
        super.setActive(active);
    }

    @Override
    protected boolean checkRecipe(FuelRecipe recipe) {
        refreshFuelStack(recipe);
        return checkAirCO2();
    }

    private boolean checkAirCO2() {
        FluidStack airResult = fluidTank.get().drain(Materials.Air.getFluid(AIR_INTAKE_PER_SEC), false);
        FluidStack co2Result = fluidTank.get().drain(Materials.CarbonDioxide.getFluid(CO2_INTAKE_PER_SEC), false);

        return co2Result != null && co2Result.amount >= CO2_INTAKE_PER_SEC
                && airResult != null && airResult.amount == AIR_INTAKE_PER_SEC;
    }

    private boolean checkBoost() {
        FluidStack hydrogenStack = fluidTank.get().drain(GAMaterials.LiquidHydrogen.getFluid(hydrogenNeededToBoost), false);
        this.isUsingHydrogen = hydrogenStack != null && hydrogenStack.amount >= hydrogenNeededToBoost;
        return isUsingHydrogen;
    }

    private int refreshFuelStack(FuelRecipe recipe) {
        FluidStack rocketFuel = recipe.getRecipeFluid().copy();
        rocketFuel.amount = Integer.MAX_VALUE;
        this.fuelStack = fluidTank.get().drain(rocketFuel, false);

        return fuelStack == null ? 0 : fuelStack.amount;
    }

    @Override
    protected int calculateFuelAmount(FuelRecipe recipe) {
        checkBoost();
        return refreshFuelStack(recipe);
    }

    @Override
    protected int calculateRecipeDuration(FuelRecipe recipe) {
        return TICK_PER_SEC;
    }

    @Override
    protected long startRecipe(FuelRecipe recipe, int fuelUsed, int recipeDuration) {
        refreshFuelStack(recipe);
        long totalEUPerAmount = recipe.getMinVoltage() * recipe.getDuration() / recipe.getRecipeFluid().amount;
        long EUt = totalEUPerAmount * fuelUsed / TICK_PER_SEC;

        // Drain tanks
        if (checkAirCO2()) {
            fluidTank.get().drain(Materials.Air.getFluid(AIR_INTAKE_PER_SEC), true);
            fluidTank.get().drain(Materials.CarbonDioxide.getFluid(CO2_INTAKE_PER_SEC), true);
        } else return 0;

        // Check boosted status and drain if needed
        hydrogenNeededToBoost = 4 * (int)Math.ceil(fuelUsed / 1000.0);
        if (checkBoost()) {
            fluidTank.get().drain(GAMaterials.LiquidHydrogen.getFluid(hydrogenNeededToBoost), true);
            EUt *= 3;
        }

        // Apply efficiency
        EUt = EUt * 60 / 100;
        if (EUt > GAValues.V[GAValues.LuV])
            EUt = GAValues.V[GAValues.LuV] + ((EUt - GAValues.V[GAValues.LuV]) * 40 / 100);
        if (EUt > GAValues.V[GAValues.ZPM])
            EUt = GAValues.V[GAValues.ZPM] + ((EUt - GAValues.V[GAValues.ZPM]) * 50 / 100);
        if (EUt > GAValues.V[GAValues.UV])
            EUt = GAValues.V[GAValues.UV] + ((EUt - GAValues.V[GAValues.UV]) * 50 / 100);

        // Refresh our internal FluidStack
        fuelStack.amount -= fuelUsed;

        return EUt;
    }

    public int getHydrogenNeededToBoost() {
        return hydrogenNeededToBoost;
    }

    public boolean isUsingHydrogen() {
        return isUsingHydrogen;
    }
}
