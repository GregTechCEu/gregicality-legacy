package gregicadditions.integrations.bees.alveary;

import forestry.api.multiblock.IAlvearyComponent;
import forestry.apiculture.multiblock.TileAlveary;
import forestry.core.tiles.IActivatable;
import gregicadditions.integrations.bees.alveary.gui.ContainerGTAlveary;
import gregicadditions.integrations.bees.alveary.gui.GuiGTAlveary;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TileGTAlveary extends TileAlveary implements IActivatable, IEnergyContainer, IAlvearyComponent.Active {
    private long energyStored;
    private final FluidTank fluidTank;
    private final ItemStackHandler itemStackHandler;
    private boolean active;

    public TileGTAlveary() {
        energyStored = 0;
        fluidTank = new FluidTank(5000){
            @Override
            protected void onContentsChanged() {
                markDirty();
            }

            @Override
            public boolean canFill() {
                return false;
            }
        };
        itemStackHandler = new ItemStackHandler(4) {
            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return isItemValid(slot, stack)? super.insertItem(slot, stack, simulate) : stack;
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return stack.getItem() instanceof MetaItem;
            }

            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot >= 0)
                    return ItemStack.EMPTY;
                return super.extractItem(-slot - 1, amount, simulate);
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
        this.setActive(false);
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        this.markDirty();
    }

    @Override
    public String getUnlocalizedTitle() {
        return "tile.gtadditions:gt_alveary.name";
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return super.hasCapability(capability, facing) ||
                capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ||
                capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER ||
                capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidTank);
        else if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER)
            return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this);
        else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        return super.getCapability(capability, facing);
    }

    public ItemStackHandler getItemHandler() {
        return itemStackHandler;
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }

    public List<ItemStack> getDrops() {
        List<ItemStack> drops = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            if (!itemStackHandler.getStackInSlot(i).isEmpty()) {
                drops.add(itemStackHandler.getStackInSlot(i));
            }
        }
        return drops;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        energyStored = data.getLong("energyStored");
        fluidTank.readFromNBT((NBTTagCompound) data.getTag("fluidTank"));
        itemStackHandler.deserializeNBT((NBTTagCompound) data.getTag("itemStackHandler"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        data.setTag("itemStackHandler", itemStackHandler.serializeNBT());
        return data;
    }

    @Override
    public boolean allowsAutomation() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiContainer getGui(EntityPlayer player, int data) {
        return new GuiGTAlveary(player.inventory, this);
    }

    @Override
    public Container getContainer(EntityPlayer player, int data) {
        return new ContainerGTAlveary(player.inventory, this);
    }

    @Override
    public void updateServer(int tickCount) {
        for (EnumFacing side : EnumFacing.values()) {
            TileEntity tileEntity = this.getWorld().getTileEntity(this.getPos().offset(side));
            EnumFacing oppositeSide = side.getOpposite();
            if (tileEntity != null && tileEntity.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, oppositeSide)) {
                IEnergyContainer energyContainer = tileEntity.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, oppositeSide);
                if (energyContainer != null && energyContainer.inputsEnergy(oppositeSide) && getEnergyStored() > energyContainer.getInputVoltage()) {
                    if (energyContainer.acceptEnergyFromNetwork(oppositeSide, energyContainer.getInputVoltage(), 1) > 0){
                        energyStored -= energyContainer.getInputVoltage();
                        this.markDirty();
                    }
                }
            }
        }
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing enumFacing, long l, long l1) {
        return 0;
    }

    @Override
    public boolean inputsEnergy(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public boolean outputsEnergy(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public long changeEnergy(long energyToAdd) {
        long oldEnergyStored = this.getEnergyStored();
        long newEnergyStored = this.getEnergyCapacity() - oldEnergyStored < energyToAdd ? this.getEnergyCapacity() : oldEnergyStored + energyToAdd;
        if (newEnergyStored < 0L) {
            newEnergyStored = 0L;
        }

        energyStored = newEnergyStored;
        this.markDirty();
        return newEnergyStored - oldEnergyStored;
    }

    @Override
    public long getEnergyStored() {
        return energyStored;
    }

    @Override
    public long getEnergyCapacity() {
        return 262144L;
    }

    @Override
    public long getInputAmperage() {
        return 0;
    }

    @Override
    public long getInputVoltage() {
        return 0;
    }

    @Override
    public void updateClient(int tickCount) {

    }
}
