package gregicadditions.integrations.bees.alveary;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.DefaultBeeModifier;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.multiblock.IAlvearyComponent;
import forestry.apiculture.multiblock.TileAlveary;
import forestry.core.network.packets.PacketActiveUpdate;
import forestry.core.tiles.IActivatable;
import forestry.core.utils.NetworkUtil;
import gregicadditions.integrations.bees.alveary.gui.ContainerGTAlveary;
import gregicadditions.integrations.bees.alveary.gui.GuiGTAlveary;
import gregicadditions.integrations.bees.effects.GTBeesEffects;
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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TileGTAlveary extends TileAlveary implements IActivatable, IEnergyContainer, IAlvearyComponent.Active, IAlvearyComponent.BeeModifier {
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
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return isItemValid(slot, stack)? super.insertItem(slot, stack, simulate) : stack;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() instanceof MetaItem;
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
        if (this.active == active) {
            return;
        }
        this.active = active;
        if (world != null) {
            if (world.isRemote) {
                world.markBlockRangeForRenderUpdate(getPos(), getPos());
            } else {
                NetworkUtil.sendNetworkPacket(new PacketActiveUpdate(this), pos, world);
            }
        }
    }

    @Override
    public IBeeModifier getBeeModifier() {
        return new DefaultBeeModifier(){
            @Override
            public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
                return checkNuclearWaste() == -1? super.getMutationModifier(genome, mate, currentModifier) : 0.5F;
            }

            @Override
            public float getProductionModifier(IBeeGenome genome, float currentModifier) {
                return checkNuclearWaste() == -1? super.getProductionModifier(genome, currentModifier) : 0.1F;
            }

            @Override
            public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
                int slot = checkNuclearWaste();
                if (slot == -1) {
                    return super.getMutationModifier(genome, mate, currentModifier);
                }
                itemStackHandler.extractItem(slot, 1, false);
                return 2F;
            }
        };
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

    /* Network */
    @Override
    protected void encodeDescriptionPacket(NBTTagCompound packetData) {
        super.encodeDescriptionPacket(packetData);
        packetData.setBoolean("Active", active);
    }

    @Override
    protected void decodeDescriptionPacket(NBTTagCompound packetData) {
        super.decodeDescriptionPacket(packetData);
        setActive(packetData.getBoolean("Active"));
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey("energyStored") && data.hasKey("fluidTank") && data.hasKey("itemStackHandler") && data.hasKey("active")) {
            energyStored = data.getLong("energyStored");
            fluidTank.readFromNBT((NBTTagCompound) data.getTag("fluidTank"));
            itemStackHandler.deserializeNBT((NBTTagCompound) data.getTag("itemStackHandler"));
            setActive(data.getBoolean("active"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data = super.writeToNBT(data);
        data.setLong("energyStored", energyStored);
        data.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        data.setTag("itemStackHandler", itemStackHandler.serializeNBT());
        data.setBoolean("active", active);
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
        if (getBeekeepingLogic().canWork()) {
            ItemStack princessStack = getPrincessStack();
            setActive(
                    princessStack != null && BeeManager.beeRoot.getMember(princessStack).getGenome()
                            .getEffect() instanceof GTBeesEffects || checkNuclearWaste() != -1
            );
        } else {
            setActive(false);
        }
        if (this.getEnergyStored() <= 0)
            return;
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

    private ItemStack getPrincessStack() {
        ItemStack princessStack = getMultiblockLogic().getController().getBeeInventory().getQueen();

        if (BeeManager.beeRoot.isMated(princessStack)) {
            return princessStack;
        }

        return null;
    }

    private int checkNuclearWaste() {
        for (int slot = 0; slot < itemStackHandler.getSlots(); slot++) {
            ItemStack itemStack = itemStackHandler.getStackInSlot(slot);
            if (itemStack.getItem() instanceof MetaItem && itemStack.getCount() > 0){
                MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
                if (311 <= metaItem.getItem(itemStack).getMetaValue() && metaItem.getItem(itemStack).getMetaValue() <= 332) { //nuclear waste
                    return slot;
                }
            }
        }
        return -1;
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
