package gregicadditions.capabilities.impl;


import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import net.minecraft.nbt.NBTTagCompound;

public class QubitConsumeRecipeLogic extends GAMultiblockRecipeLogic {


    private int recipeQubit;
    private boolean hasEnoughQubit = true;

    public QubitConsumeRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
        super(metaTileEntity);
    }

    public IQubitContainer getInputQubitContainer() {
        QubitRecipeMapMultiblockController controller = (QubitRecipeMapMultiblockController) metaTileEntity;
        return controller.getInputQubitContainer();
    }

    @Override
    protected int[] calculateOverclock(int EUt, long voltage, int duration) {
        if (recipeQubit > 0) {
            return new int[]{EUt, duration};
        }
        return super.calculateOverclock(EUt, voltage, duration);
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        recipeQubit = recipe.getIntegerProperty("qubitConsume");
    }

    @Override
    protected void completeRecipe() {
        super.completeRecipe();
        recipeQubit = 0;
        hasEnoughQubit = true;
    }

    @Override
    public void updateRecipeProgress() {
        boolean drawQubit = this.drawQubit(this.recipeQubit);
        if (drawQubit || this.recipeQubit == 0) {
            super.updateRecipeProgress();
            hasEnoughQubit = true;
        } else {
            hasEnoughQubit = false;
        }
    }

    protected boolean drawQubit(int qubit) {
        long resultQubit = this.getInputQubitContainer().getQubitStored() - (long) qubit;
        if (resultQubit >= 0L && resultQubit <= this.getInputQubitContainer().getQubitCapacity()) {
            this.getInputQubitContainer().changeQubit(-qubit);
            return true;
        } else {
            return false;
        }
    }

    public boolean isHasEnoughQubit() {
        return hasEnoughQubit;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (progressTime > 0) {
            compound.setInteger("RecipeQubit", this.recipeQubit);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.isActive = false;
        if (progressTime > 0) {
            this.isActive = true;
            this.recipeQubit = compound.getInteger("RecipeQubit");
        }
    }

}
