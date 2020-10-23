package gregicadditions.capabilities.impl;


import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.nbt.NBTTagCompound;

public class QubitConsumeRecipeLogic extends MultiblockRecipeLogic {


    private long recipeQubit;

    public QubitConsumeRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
        super(metaTileEntity);
        setAllowOverclocking(false);
    }

    public IQubitContainer getInputQubitContainer() {
        QubitRecipeMapMultiblockController controller = (QubitRecipeMapMultiblockController) metaTileEntity;
        return controller.getInputQubitContainer();
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        if (progressTime > 0) {
            compound.setLong("RecipeQubit", this.recipeQubit);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.isActive = false;
        if (progressTime > 0) {
            this.isActive = true;
            this.recipeQubit = compound.getLong("RecipeQubit");
        }
    }

}
