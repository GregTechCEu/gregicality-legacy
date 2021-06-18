package gregicadditions.machines.multi.multiblockpart;

import appeng.api.AEApi;
import appeng.api.config.Actionable;
import appeng.api.networking.*;
import appeng.api.networking.security.IActionHost;
import appeng.api.networking.security.IActionSource;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import appeng.api.util.AEPartLocation;
import appeng.api.util.DimensionalCoord;
import appeng.me.helpers.MachineSource;
import appeng.util.Platform;
import appeng.util.item.AEItemStack;
import codechicken.lib.raytracer.CuboidRayTraceResult;
import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.IRSAPI;
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNode;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNodeManager;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNodeProxy;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import com.raoulvdberge.refinedstorage.capability.CapabilityNetworkNodeProxy;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MetaTileEntityDigitalItemBus extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IItemHandlerModifiable>, INetworkNode, INetworkNodeProxy, IGridBlock, IGridHost, IActionHost {

    protected INetwork network;
    public static final String ID = "digital_item_bus";
    protected IGridNode node;
    private Mode mode;
    protected final IActionSource actionSource;

    public MetaTileEntityDigitalItemBus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 1);
        this.mode = Mode.AE2;
        this.actionSource = new MachineSource(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityDigitalItemBus(metaTileEntityId);
    }

    @Override
    public void onLoad() {
        IRSAPI instance = API.instance();
        World world = getWorld();
        INetworkNodeManager manager = instance.getNetworkNodeManager(world);
        BlockPos blockPos = getPos();
        manager.setNode(blockPos, this);
        if (Platform.isServer()) {
            node = AEApi.instance().grid().createGridNode(this);
        }
        super.onLoad();
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return MultiblockAbility.EXPORT_ITEMS;
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> list) {
        list.add(this.exportItems);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(0);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(16) {
            @Override
            @Nonnull
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.isEmpty()) {
                    return ItemStack.EMPTY;
                }

                //Do we need this?
                validateSlotIndex(slot);

                if (mode == Mode.AE2) {
                    if (!node.isActive()) {
                        return stack;
                    }
                    IGrid grid = node.getGrid();
                    IStorageGrid inv = grid.getCache(IStorageGrid.class);
                    IMEMonitor<IAEItemStack> items = inv.getInventory(AEApi.instance().storage().getStorageChannel(IItemStorageChannel.class));
                    Actionable action = simulate ? Actionable.SIMULATE : Actionable.MODULATE;
                    IAEItemStack result = items.injectItems(AEItemStack.fromItemStack(stack), action, actionSource);
                    if (result == null) {
                        return ItemHandlerHelper.copyStackWithSize(stack, 0);
                    } else {
                        return result.createItemStack();
                    }
                }
                if (mode == Mode.RS) {
                    ItemStack result;
                    if (simulate) {
                        result = network.insertItem(stack, stack.getCount(), Action.SIMULATE);
                    } else {
                        result = network.insertItemTracked(stack, stack.getCount());
                    }
                    if (result == null) {
                        return ItemHandlerHelper.copyStackWithSize(stack, 0);
                    } else {
                        return result;
                    }
                }
                return stack;
            }
        };
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        mode = Mode.values()[(mode.ordinal() + 1) % 2];
        if (!getWorld().isRemote) {
            playerIn.sendStatusMessage(new TextComponentTranslation("gtadditions.machine.digital_item_bus.mode", mode == Mode.AE2 ? "AE2" : "RS"), false);
        }
        return true;
    }

    @Override
    public int getEnergyUsage() {
        return 0;
    }

    @Nonnull
    @Override
    public ItemStack getItemStack() {
        return this.getStackForm();
    }

    @Override
    public void onConnected(INetwork network) {
        this.network = network;
    }

    @Override
    public void onDisconnected(INetwork network) {
        this.network = null;
    }

    @Override
    public boolean canUpdate() {
        if (network != null) {
            return network.canRun();
        }
        return false;
    }

    @Nullable
    @Override
    public INetwork getNetwork() {
        return network;
    }

    @Override
    public NBTTagCompound write(NBTTagCompound tag) {
        tag.setString("Version", RS.VERSION);
        tag.setInteger("Direction", frontFacing.ordinal());
        return tag;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
        if (capability == CapabilityNetworkNodeProxy.NETWORK_NODE_PROXY_CAPABILITY) {
            return CapabilityNetworkNodeProxy.NETWORK_NODE_PROXY_CAPABILITY.cast(this);
        }

        return super.getCapability(capability, side);
    }

    @Nonnull
    @Override
    public INetworkNode getNode() {
        return this;
    }

    public boolean isTickable() {
        return false;
    }

    @Override
    public double getIdlePowerUsage() {
        return 0;
    }

    @Nonnull
    @Override
    public EnumSet<GridFlags> getFlags() {
        return EnumSet.of(GridFlags.REQUIRE_CHANNEL);
    }

    @Override
    public boolean isWorldAccessible() {
        return true;
    }

    @Nonnull
    @Override
    public DimensionalCoord getLocation() {
        return new DimensionalCoord(getWorld(), getPos());
    }

    @Nonnull
    @Override
    public AEColor getGridColor() {
        return AEColor.TRANSPARENT;
    }

    @Override
    public void onGridNotification(@Nonnull GridNotification gridNotification) {

    }

    @Override
    public void setNetworkStatus(IGrid iGrid, int i) {

    }

    @Nonnull
    @Override
    public EnumSet<EnumFacing> getConnectableSides() {
        return EnumSet.allOf(EnumFacing.class);
    }

    @Nonnull
    @Override
    public IGridHost getMachine() {
        return this;
    }

    @Override
    public void gridChanged() {

    }

    @Nonnull
    @Override
    public ItemStack getMachineRepresentation() {
        return getStackForm();
    }

    @Nullable
    @Override
    public IGridNode getGridNode(@Nonnull AEPartLocation aePartLocation) {
        return node;
    }

    @Nonnull
    @Override
    public AECableType getCableConnectionType(@Nonnull AEPartLocation aePartLocation) {
        return AECableType.SMART;
    }

    @Override
    public void securityBreak() {
        getWorld().destroyBlock(getPos(), true);
    }

    @Nonnull
    @Override
    public IGridNode getActionableNode() {
        return node;
    }

    private enum Mode {
                RS,
                AE2
    }
}
