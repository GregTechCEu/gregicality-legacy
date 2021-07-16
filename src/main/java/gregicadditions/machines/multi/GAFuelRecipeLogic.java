package gregicadditions.machines.multi;

import gregtech.api.capability.*;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Iterator;
import java.util.function.Supplier;

public class GAFuelRecipeLogic extends FuelRecipeLogic {
    public final FuelRecipeMap recipeMap;
    protected FuelRecipe previousRecipe;
    protected final Supplier<IEnergyContainer> energyContainer;
    protected final Supplier<IMultipleTankHandler> fluidTank;
    public final long maxVoltage;
    private int recipeDurationLeft;
    private long recipeOutputVoltage;
    private boolean workingEnabled = true;
    private boolean wasActiveAndNeedsUpdate = false;

    public GAFuelRecipeLogic(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
        this.recipeMap = recipeMap;
        this.energyContainer = energyContainer;
        this.fluidTank = fluidTank;
        this.maxVoltage = maxVoltage;
    }

    @Override
    protected int calculateRecipeDuration(FuelRecipe currentRecipe) {
        if (metaTileEntity instanceof GAFueledMultiblockController) {
            GAFueledMultiblockController gaController = (GAFueledMultiblockController) metaTileEntity;
            if (gaController.hasMaintenanceHatch()) {
                int numMaintenanceProblems = gaController.getNumProblems();
                double maintenanceDurationMultiplier = 1.0 - (0.2 * numMaintenanceProblems);
                int durationModified = (int) (currentRecipe.getDuration() * maintenanceDurationMultiplier);

                gaController.calculateMaintenance(durationModified);
                return durationModified;
            }
        }

        return currentRecipe.getDuration();
    }

    @Override
    public void update() {
        if (!this.getMetaTileEntity().getWorld().isRemote) {
            if (this.workingEnabled) {
                if (this.recipeDurationLeft > 0 && (this.energyContainer.get().getEnergyCanBeInserted() >= this.recipeOutputVoltage || this.shouldVoidExcessiveEnergy())) {
                    this.energyContainer.get().addEnergy(this.recipeOutputVoltage);
                    if (--this.recipeDurationLeft == 0) {
                        this.wasActiveAndNeedsUpdate = true;
                    }
                }

                if (this.recipeDurationLeft == 0 && this.isReadyForRecipes()) {
                    // do not run recipes if no problems fixed
                    if (metaTileEntity instanceof GAFueledMultiblockController) {
                        GAFueledMultiblockController gaController = (GAFueledMultiblockController) metaTileEntity;
                        if (gaController.hasMaintenanceHatch()) {
                            int numMaintenanceProblems = gaController.getNumProblems();
                            if (numMaintenanceProblems != 6)
                                this.tryAcquireNewRecipe();
                        }
                    } else {
                        this.tryAcquireNewRecipe();
                    }
                }
            }

            if (this.wasActiveAndNeedsUpdate) {
                this.setActive(false);
                this.wasActiveAndNeedsUpdate = false;
            }

        }
    }

    private void tryAcquireNewRecipe() {
        IMultipleTankHandler fluidTanks = this.fluidTank.get();

        for (IFluidTank fluidTank : fluidTanks) {
            FluidStack tankContents = fluidTank.getFluid();
            if (tankContents != null && tankContents.amount > 0) {
                int fuelAmountUsed = this.tryAcquireNewRecipe(tankContents);
                if (fuelAmountUsed > 0) {
                    fluidTank.drain(fuelAmountUsed, true);
                    break;
                }
            }
        }

    }

    private int tryAcquireNewRecipe(FluidStack fluidStack) {
        FuelRecipe currentRecipe;
        if (this.previousRecipe != null && this.previousRecipe.matches(this.getMaxVoltage(), fluidStack)) {
            currentRecipe = this.previousRecipe;
        } else {
            currentRecipe = this.recipeMap.findRecipe(this.getMaxVoltage(), fluidStack);
            if (currentRecipe != null) {
                this.previousRecipe = currentRecipe;
            }
        }

        if (currentRecipe != null && this.checkRecipe(currentRecipe)) {
            int fuelAmountToUse = this.calculateFuelAmount(currentRecipe);
            if (fluidStack.amount >= fuelAmountToUse) {
                this.recipeDurationLeft = this.calculateRecipeDuration(currentRecipe);
                this.recipeOutputVoltage = this.startRecipe(currentRecipe, fuelAmountToUse, this.recipeDurationLeft);
                if (this.wasActiveAndNeedsUpdate) {
                    this.wasActiveAndNeedsUpdate = false;
                } else {
                    this.setActive(true);
                }

                return fuelAmountToUse;
            }
        }

        return 0;
    }
}
