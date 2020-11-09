package gregicadditions.capabilities.impl;


import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
import gregicadditions.utils.GALog;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
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
    protected void completeRecipe() {
        super.completeRecipe();
        this.recipeOutputQubit = 0;
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        this.recipeOutputQubit = recipe.getIntegerProperty("qubitProduce");
    }

    @Override
    protected void updateRecipeProgress() {
        GALog.logger.info("ici1 " + recipeOutputQubit);
        if (getOutputQubitContainer().getQubitCanBeInserted() < recipeOutputQubit) {
            GALog.logger.info("ici2");
            return;
        }

        GALog.logger.info("ici3");
        boolean drawEnergy = drawEnergy(recipeEUt);
        GALog.logger.info("ici4 " + drawEnergy);
        if (drawEnergy || (recipeEUt < 0)) {
            GALog.logger.info("ic5");
            getOutputQubitContainer().addQubit(recipeOutputQubit);
            if (++progressTime >= maxProgressTime) {
                GALog.logger.info("ici6 " + previousRecipe);
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
