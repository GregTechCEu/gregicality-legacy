package gregicadditions.machines.multi.advance;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.Materials;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

import static gregtech.api.unification.material.Materials.Naquadria;

public class NaquadahReactorWorkableHandler extends FuelRecipeLogic {


    private boolean isUsingPotassium = false;

    public NaquadahReactorWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                          Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
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
        FluidStack potassiumStack = Materials.Potassium.getFluid(2);
        FluidStack drainPotassiumStack = fluidTank.get().drain(potassiumStack, false);
        this.isUsingPotassium = drainPotassiumStack != null && drainPotassiumStack.amount >= 2;
        return super.calculateFuelAmount(currentRecipe) * (isUsingPotassium ? 2 : 1);
    }

    @Override
    protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        if (isUsingPotassium && currentRecipe.getRecipeFluid().isFluidEqual(Naquadria.getFluid(Integer.MAX_VALUE))) {
            FluidStack potassiumStack = Materials.Potassium.getPlasma(2);
            fluidTank.get().drain(potassiumStack, true);
        }
        return maxVoltage * (isUsingPotassium && currentRecipe.getRecipeFluid().isFluidEqual(Naquadria.getFluid(Integer.MAX_VALUE)) ? 10 : 1);
    }

    public boolean isUsingPotassium() {
        return isUsingPotassium;
    }

}
