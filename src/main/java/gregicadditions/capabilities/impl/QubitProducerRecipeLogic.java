package gregicadditions.capabilities.impl;


import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.nbt.NBTTagCompound;

public class QubitProducerRecipeLogic extends MultiblockRecipeLogic {

    private long recipeOutputQubit;

    public QubitProducerRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
        super(metaTileEntity);
        setAllowOverclocking(false);
    }

    public IQubitContainer getOutputQubitContainer() {
        QubitRecipeMapMultiblockController controller = (QubitRecipeMapMultiblockController) metaTileEntity;
        return controller.getOutputQubitContainer();
    }


    @Override
    protected void updateRecipeProgress() {
        if (getOutputQubitContainer().getQubitCanBeInserted() < recipeOutputQubit) {
            return;
        }

        boolean drawEnergy = drawEnergy(recipeEUt);
        if (drawEnergy || (recipeEUt < 0)) {
            getOutputQubitContainer().addQubit(recipeOutputQubit);
            if (++progressTime >= maxProgressTime) {
                completeRecipe();
            }
        } else if (recipeEUt > 0) {
            //only set hasNotEnoughEnergy if this recipe is consuming recipe
            //generators always have enough energy
            this.hasNotEnoughEnergy = true;
            progressTime = 0;
        }
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (progressTime > 0) {
            compound.setLong("RecipeQubit", this.recipeOutputQubit);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.isActive = false;
        if (progressTime > 0) {
            this.isActive = true;
            this.recipeOutputQubit = compound.getLong("RecipeQubit");
        }
    }

}
