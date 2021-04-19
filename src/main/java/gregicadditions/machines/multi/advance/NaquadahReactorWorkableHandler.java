package gregicadditions.machines.multi.advance;

import gregicadditions.GAMaterials;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.function.Supplier;

public class NaquadahReactorWorkableHandler extends FuelRecipeLogic {


    private boolean isUsingPotassium = false;

    static final HashMap<FluidStack, Integer> FUELS = new HashMap<>();

    public NaquadahReactorWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                          Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
        FUELS.put(GAMaterials.HeavyENaquadahFuel.getFluid(Integer.MAX_VALUE), 3);
        FUELS.put(GAMaterials.MediumENaquadahFuel.getFluid(Integer.MAX_VALUE), 2);
        FUELS.put(GAMaterials.HeavyNaquadahFuel.getFluid(Integer.MAX_VALUE), 2);
        FUELS.put(GAMaterials.MediumNaquadahFuel.getFluid(Integer.MAX_VALUE), 1);

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
        FluidStack potassiumStack = Materials.Potassium.getPlasma(2);
        FluidStack drainPotassiumStack = fluidTank.get().drain(potassiumStack, false);
        this.isUsingPotassium = drainPotassiumStack != null && drainPotassiumStack.amount >= 2;
        return super.calculateFuelAmount(currentRecipe) * (isUsingPotassium ? 2 : 1);
    }

    @Override
    protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        if (isUsingPotassium && FUELS.containsKey(currentRecipe.getRecipeFluid())) {
            FluidStack potassiumStack = Materials.Potassium.getPlasma(2);
            fluidTank.get().drain(potassiumStack, true);
        }
        return maxVoltage * (isUsingPotassium && FUELS.containsKey(currentRecipe.getRecipeFluid()) ? FUELS.get(currentRecipe.getRecipeFluid()) : 1);
    }

    public boolean isUsingPotassium() {
        return isUsingPotassium;
    }

}
