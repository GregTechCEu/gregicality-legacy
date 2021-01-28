package gregicadditions.machines.multi.steam;

import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SteamMultiblockRecipeLogic extends AbstractRecipeLogic {

    private final IFluidTank steamFluidTank;

    // EU per mB
    private final double conversionRate;

    public SteamMultiblockRecipeLogic(RecipeMapSteamMultiblockController tileEntity, RecipeMap<?> recipeMap, IFluidTank steamFluidTank, double conversionRate) {
        super(tileEntity, recipeMap);
        this.steamFluidTank = steamFluidTank;
        this.conversionRate = conversionRate;
    }

    public IFluidTank getSteamFluidTank() {
        return steamFluidTank;
    }

    @Override
    protected IItemHandlerModifiable getInputInventory() {
        RecipeMapSteamMultiblockController controller = (RecipeMapSteamMultiblockController) metaTileEntity;
        return controller.getInputInventory();
    }

    @Override
    protected IItemHandlerModifiable getOutputInventory() {
        RecipeMapSteamMultiblockController controller = (RecipeMapSteamMultiblockController) metaTileEntity;
        return controller.getOutputInventory();
    }

    @Override
    protected long getEnergyStored() {
        return (long) Math.ceil(steamFluidTank.getFluidAmount() * conversionRate);
    }

    @Override
    protected long getEnergyCapacity() {
        return (long) Math.floor(steamFluidTank.getCapacity() * conversionRate);
    }

    @Override
    protected boolean drawEnergy(int recipeEUt) {
        int resultDraw = (int) Math.ceil(recipeEUt / conversionRate);
        return resultDraw >= 0 && steamFluidTank.getFluidAmount() >= resultDraw &&
                steamFluidTank.drain(resultDraw, true) != null;
    }

    @Override
    protected long getMaxVoltage() {
        return GTValues.V[GTValues.LV];
    }

    public void updateWorkable() {
        super.update();
    }

    @Override
    protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
        RecipeMapSteamMultiblockController controller = (RecipeMapSteamMultiblockController) metaTileEntity;
        if (controller.checkRecipe(recipe, false) &&
            super.setupAndConsumeRecipeInputs(recipe)) {
            controller.checkRecipe(recipe, true);
            return true;
        } else return false;
    }

    // Do this to casually ignore fluids from steam multiblocks
    @Override
    protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs) {
        return super.checkRecipeInputsDirty(inputs, new FluidTankList(false));
    }
}
