package gregicadditions.capabilities.impl;

import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMaintenanceHatch;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;


public class GAMultiblockRecipeLogic extends MultiblockRecipeLogic {

    private int previousRecipeDuration;

    public GAMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    protected int[] calculateOverclock(int EUt, long voltage, int duration) {
        int numMaintenanceProblems = (this.metaTileEntity instanceof GARecipeMapMultiblockController) ?
                ((GARecipeMapMultiblockController) metaTileEntity).getNumProblems() : 0;

        double maintenanceDurationMultiplier = 1.0 + (0.1 * numMaintenanceProblems);
        int durationModified = (int) (duration * maintenanceDurationMultiplier);

        if (!allowOverclocking) {
            return new int[]{EUt, durationModified};
        }
        boolean negativeEU = EUt < 0;
        int tier = getOverclockingTier(voltage);
        if (GAValues.V[tier] <= EUt || tier == 0)
            return new int[]{EUt, durationModified};
        if (negativeEU)
            EUt = -EUt;
        if (EUt <= 16) {
            int multiplier = EUt <= 8 ? tier : tier - 1;
            int resultEUt = EUt * (1 << multiplier) * (1 << multiplier);
            int resultDuration = durationModified / (1 << multiplier);
            previousRecipeDuration = resultDuration;
            return new int[]{negativeEU ? -resultEUt : resultEUt, resultDuration};
        } else {
            int resultEUt = EUt;
            double resultDuration = durationModified;
            //do not overclock further if duration is already too small
            while (resultDuration >= 3 && resultEUt <= GAValues.V[tier - 1]) {
                resultEUt *= 4;
                resultDuration /= 2.8;
            }
            previousRecipeDuration = (int) resultDuration;
            return new int[]{negativeEU ? -resultEUt : resultEUt, (int) Math.ceil(resultDuration)};
        }
    }

    @Override
    protected void completeRecipe() {
        super.completeRecipe();
        RecipeMapMultiblockController controller = (RecipeMapMultiblockController) getMetaTileEntity();
        if (controller instanceof GARecipeMapMultiblockController) {
            GARecipeMapMultiblockController gaController = (GARecipeMapMultiblockController) controller;
            if (gaController.hasMufflerHatch()) {
                gaController.outputRecoveryItems();
            }
            if (gaController.hasMaintenanceHatch()) {
                gaController.calculateMaintenance(previousRecipeDuration);
                previousRecipeDuration = 0;
            }
        }
    }

    @Override
    protected void trySearchNewRecipe() {
        if (this.metaTileEntity instanceof GARecipeMapMultiblockController &&
                ((GARecipeMapMultiblockController) this.metaTileEntity).getNumProblems() > 5)
            return;

        super.trySearchNewRecipe();
    }


}
