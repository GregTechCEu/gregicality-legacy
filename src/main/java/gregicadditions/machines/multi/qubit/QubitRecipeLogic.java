package gregicadditions.machines.multi.qubit;


import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.recipes.qubit.QubitRecipe;
import gregicadditions.recipes.qubit.QubitRecipeMap;
import gregtech.api.capability.*;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Arrays;
import java.util.function.Supplier;

public class QubitRecipeLogic extends MTETrait implements IControllable, IWorkable {

    public final QubitRecipeMap recipeMap;
    protected QubitRecipe previousRecipe;

    protected ItemStack[] lastItemInputs;
    protected FluidStack[] lastFluidInputs;
    protected boolean forceRecipeRecheck;

    protected int progressTime;
    protected int maxProgressTime;
    protected int recipeEUt;

    protected final Supplier<IEnergyContainer> energyContainer;
    protected final Supplier<IQubitContainer> qubitContainer;
    public final long maxVoltage;
    public final long maxQubit;

    private long recipeOutputQubit;

    private boolean isActive;
    private boolean workingEnabled = true;
    protected boolean hasNotEnoughEnergy;
    private boolean wasActiveAndNeedsUpdate = false;

    public QubitRecipeLogic(MetaTileEntity metaTileEntity, QubitRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IQubitContainer> qubitContainer, long maxVoltage, long maxQubit) {
        super(metaTileEntity);
        this.recipeMap = recipeMap;
        this.energyContainer = energyContainer;
        this.qubitContainer = qubitContainer;
        this.maxVoltage = maxVoltage;
        this.maxQubit = maxQubit;
    }

    @Override
    public void update() {
        if (getMetaTileEntity().getWorld().isRemote) return;
        if (workingEnabled) {
            if (progressTime > 0) {
                updateRecipeProgress();
            }
            if (progressTime == 0) {
                trySearchNewRecipe();
            }
        }
        if (wasActiveAndNeedsUpdate) {
            setActive(false);
            this.wasActiveAndNeedsUpdate = false;
        }
    }

    protected void updateRecipeProgress() {
        if (qubitContainer.get().getQubitCanBeInserted() < recipeOutputQubit || !shouldVoidExcessiveQubit()) {
            return;
        }

        boolean drawEnergy = drawEnergy(recipeEUt);
        if (drawEnergy || (recipeEUt < 0)) {
            qubitContainer.get().addQubit(recipeOutputQubit);
            if (++progressTime >= maxProgressTime) {
                completeRecipe();
            }
        } else if (recipeEUt > 0) {
            //only set hasNotEnoughEnergy if this recipe is consuming recipe
            //generators always have enough energy
            this.hasNotEnoughEnergy = true;
            if (progressTime >= 2) {
                this.progressTime = 1;
            }
        }
    }

    protected void trySearchNewRecipe() {
        long maxVoltage = getMaxVoltage();
        QubitRecipe currentRecipe = null;
        IItemHandlerModifiable importInventory = getInputInventory();
        IMultipleTankHandler importFluids = getInputTank();
        if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
            //if previous recipe still matches inputs, try to use it
            currentRecipe = previousRecipe;
        } else {
            boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
            if (dirty || forceRecipeRecheck) {
                this.forceRecipeRecheck = false;
                //else, try searching new recipe for given inputs
                currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            }
        }
        if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
            setupRecipe(currentRecipe);
        }
    }

    protected void setupRecipe(QubitRecipe recipe) {
        this.progressTime = 1;
        this.recipeOutputQubit = Math.min(maxQubit, recipe.getMinQubit());
        setMaxProgress(recipe.getDuration());
        this.recipeEUt = recipe.getEUt();
        if (this.wasActiveAndNeedsUpdate) {
            this.wasActiveAndNeedsUpdate = false;
        } else {
            this.setActive(true);
        }
    }

    protected boolean setupAndConsumeRecipeInputs(QubitRecipe recipe) {
        int totalEUt = recipe.getEUt() * recipe.getDuration();
        IItemHandlerModifiable importInventory = getInputInventory();
        IMultipleTankHandler importFluids = getInputTank();
        return (totalEUt >= 0 ? getEnergyStored() >= (totalEUt > getEnergyCapacity() / 2 ? recipe.getEUt() : totalEUt) :
                (getEnergyStored() - recipe.getEUt() <= getEnergyCapacity())) && recipe.matches(true, importInventory, importFluids);
    }

    protected QubitRecipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
        return recipeMap.findRecipe(maxVoltage, inputs, fluidInputs);
    }

    protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs) {
        boolean shouldRecheckRecipe = false;
        if (lastItemInputs == null || lastItemInputs.length != inputs.getSlots()) {
            this.lastItemInputs = new ItemStack[inputs.getSlots()];
            Arrays.fill(lastItemInputs, ItemStack.EMPTY);
        }
        if (lastFluidInputs == null || lastFluidInputs.length != fluidInputs.getTanks()) {
            this.lastFluidInputs = new FluidStack[fluidInputs.getTanks()];
        }
        for (int i = 0; i < lastItemInputs.length; i++) {
            ItemStack currentStack = inputs.getStackInSlot(i);
            ItemStack lastStack = lastItemInputs[i];
            if (!areItemStacksEqual(currentStack, lastStack)) {
                this.lastItemInputs[i] = currentStack.isEmpty() ? ItemStack.EMPTY : currentStack.copy();
                shouldRecheckRecipe = true;
            } else if (currentStack.getCount() != lastStack.getCount()) {
                lastStack.setCount(currentStack.getCount());
                shouldRecheckRecipe = true;
            }
        }
        for (int i = 0; i < lastFluidInputs.length; i++) {
            FluidStack currentStack = fluidInputs.getTankAt(i).getFluid();
            FluidStack lastStack = lastFluidInputs[i];
            if ((currentStack == null && lastStack != null) || (currentStack != null && !currentStack.isFluidEqual(lastStack))) {
                this.lastFluidInputs[i] = currentStack == null ? null : currentStack.copy();
                shouldRecheckRecipe = true;
            } else if (currentStack != null && lastStack != null && currentStack.amount != lastStack.amount) {
                lastStack.amount = currentStack.amount;
                shouldRecheckRecipe = true;
            }
        }
        return shouldRecheckRecipe;
    }

    protected void completeRecipe() {
        this.progressTime = 0;
        setMaxProgress(0);
        this.recipeEUt = 0;
        this.hasNotEnoughEnergy = false;
        this.wasActiveAndNeedsUpdate = true;
        //force recipe recheck because inputs could have changed since last time
        //we checked them before starting our recipe, especially if recipe took long time
        this.forceRecipeRecheck = true;
    }

    @Override
    public int getProgress() {
        return progressTime;
    }

    @Override
    public int getMaxProgress() {
        return maxProgressTime;
    }

    public double getProgressPercent() {
        return getMaxProgress() == 0 ? 0.0 : getProgress() / (getMaxProgress() * 1.0);
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgressTime = maxProgress;
        metaTileEntity.markDirty();
    }

    protected IItemHandlerModifiable getInputInventory() {
        return metaTileEntity.getImportItems();
    }

    protected IMultipleTankHandler getInputTank() {
        return metaTileEntity.getImportFluids();
    }


    protected long getEnergyStored() {
        return energyContainer.get().getEnergyStored();
    }


    protected long getEnergyCapacity() {
        return energyContainer.get().getEnergyCapacity();
    }


    protected boolean drawEnergy(int recipeEUt) {
        long resultEnergy = getEnergyStored() - recipeEUt;
        if (resultEnergy >= 0L && resultEnergy <= getEnergyCapacity()) {
            energyContainer.get().changeEnergy(-recipeEUt);
            return true;
        } else return false;
    }

    protected long getMaxVoltage() {
        return energyContainer.get().getInputVoltage();
    }

    public long getRecipeOutputQubit() {
        return recipeOutputQubit;
    }

    @Override
    public String getName() {
        return "RecipeMapWorkable";
    }

    protected static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
        return (stackA.isEmpty() && stackB.isEmpty()) ||
                (ItemStack.areItemsEqual(stackA, stackB) &&
                        ItemStack.areItemStackTagsEqual(stackA, stackB));
    }

    @Override
    public int getNetworkID() {
        return TraitNetworkIds.TRAIT_ID_WORKABLE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE) {
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        } else if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return null;
    }


    protected boolean shouldVoidExcessiveQubit() {
        return false;
    }


    public boolean isActive() {
        return isActive;
    }

    private void setActive(boolean active) {
        this.isActive = active;
        if (!metaTileEntity.getWorld().isRemote) {
            metaTileEntity.markDirty();
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        this.workingEnabled = workingEnabled;
        metaTileEntity.markDirty();
    }

    @Override
    public boolean isWorkingEnabled() {
        return workingEnabled;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getMetaTileEntity().getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public void writeInitialData(PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
    }

    @Override
    public void receiveInitialData(PacketBuffer buf) {
        this.isActive = buf.readBoolean();
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("WorkEnabled", workingEnabled);
        if (progressTime > 0) {
            compound.setInteger("Progress", progressTime);
            compound.setInteger("MaxProgress", maxProgressTime);
            compound.setInteger("RecipeEUt", this.recipeEUt);
            compound.setLong("RecipeOutputQubit", this.recipeOutputQubit);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        this.workingEnabled = compound.getBoolean("WorkEnabled");
        this.progressTime = compound.getInteger("Progress");

        this.isActive = false;
        if (progressTime > 0) {
            this.isActive = true;
            this.maxProgressTime = compound.getInteger("MaxProgress");
            this.recipeEUt = compound.getInteger("RecipeEUt");
            this.recipeOutputQubit = compound.getLong("RecipeOutputQubit");

        }
    }

}
