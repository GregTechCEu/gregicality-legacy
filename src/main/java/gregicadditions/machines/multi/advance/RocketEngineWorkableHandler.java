package gregicadditions.machines.multi.advance;

import gregicadditions.GAMaterials;
import gregtech.api.GTValues;
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
    private static final int HYDROGEN_PER_SEC = 4;
    private static final int AIR_INTAKE_PER_SEC = 37500;
    private static final int TICK_PER_SEC = 20;
    private int hydrogenNeededToBoost = 0;
    private FluidStack fuelStack;

    public RocketEngineWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                       Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
    }

    public FluidStack getFuelStack() {
        return fuelStack;
    }

    @Override
    protected boolean checkRecipe(FuelRecipe recipe) {
        FluidStack drainCarbonDioxide = fluidTank.get().drain(Materials.CarbonDioxide.getFluid(10), false);
        FluidStack drainAir = fluidTank.get().drain(Materials.Air.getFluid(AIR_INTAKE_PER_SEC), false);

        return drainCarbonDioxide != null && drainCarbonDioxide.amount >= 10 && drainAir != null && drainAir.amount == AIR_INTAKE_PER_SEC;
    }


    @Override
    protected int calculateFuelAmount(FuelRecipe currentRecipe) {
        //Is boosted
        FluidStack hydrogenFluid = GAMaterials.LiquidHydrogen.getFluid(1000);
        FluidStack drainHydrogenStack = fluidTank.get().drain(hydrogenFluid, false);
        this.isUsingHydrogen = drainHydrogenStack != null && drainHydrogenStack.amount >= 1000;

        //drain all rocket fuel
        FluidStack rocketFuel = currentRecipe.getRecipeFluid().copy();
        rocketFuel.amount = Integer.MAX_VALUE;
        this.fuelStack = fluidTank.get().drain(rocketFuel, false);
        return fuelStack.amount;
    }

    @Override
    protected int calculateRecipeDuration(FuelRecipe currentRecipe) {
        //per sec
        return TICK_PER_SEC;
    }

    @Override
    protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        long totalEUPerAmount = currentRecipe.getMinVoltage() * currentRecipe.getDuration() / currentRecipe.getRecipeFluid().amount;
        long EUt = totalEUPerAmount * fuelAmountUsed / TICK_PER_SEC;

        FluidStack result = fluidTank.get().drain(Materials.Air.getFluid(AIR_INTAKE_PER_SEC), true);

        if (result != null && result.amount != AIR_INTAKE_PER_SEC) {
            return 0;
        }

        if (isUsingHydrogen) {
            hydrogenNeededToBoost = (int) (HYDROGEN_PER_SEC * EUt / GTValues.V[GTValues.EV]);
            FluidStack hydrogenFluid = fluidTank.get().drain(GAMaterials.LiquidHydrogen.getFluid(hydrogenNeededToBoost), true);
            if (hydrogenFluid != null && hydrogenFluid.amount == hydrogenNeededToBoost)
                EUt *= 3;
        }

        //apply 60% efficiency
        EUt = EUt * 60 / 100;

        if (EUt > GTValues.V[GTValues.LuV]) {
            EUt = GTValues.V[GTValues.LuV] + ((EUt - GTValues.V[GTValues.LuV]) * 40 / 100);
        }
        if (EUt > GTValues.V[GTValues.ZPM]) {
            EUt = GTValues.V[GTValues.ZPM] + ((EUt - GTValues.V[GTValues.ZPM]) * 50 / 100);
        }
        if (EUt > GTValues.V[GTValues.UV]) {
            EUt = GTValues.V[GTValues.UV] + ((EUt - GTValues.V[GTValues.UV]) * 50 / 100);
        }
        return EUt;
    }



    public int getHydrogenNeededToBoost() {
        return hydrogenNeededToBoost;
    }

    public boolean isUsingHydrogen() {
        return isUsingHydrogen;
    }


}
