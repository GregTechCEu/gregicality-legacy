package gregicadditions.machines.multi;

import gregtech.api.capability.*;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import java.util.function.Supplier;

public class GAFuelRecipeLogic extends FuelRecipeLogic {

    public GAFuelRecipeLogic(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
    }

    @Override
    protected int calculateRecipeDuration(FuelRecipe currentRecipe) {
        if (metaTileEntity instanceof GAFueledMultiblockController) {
            GAFueledMultiblockController gaController = (GAFueledMultiblockController) metaTileEntity;
            if (gaController.hasMaintenanceHatch()) {
                int numMaintenanceProblems = gaController.getNumProblems();
                System.out.println("numMaintenanceProblems " + numMaintenanceProblems);
                double maintenanceDurationMultiplier = 1.0 - (0.2 * numMaintenanceProblems);
                int durationModified = (int) (currentRecipe.getDuration() * maintenanceDurationMultiplier);

                gaController.calculateMaintenance(durationModified);
                return durationModified;
            }
        }

        return currentRecipe.getDuration();
    }
}
