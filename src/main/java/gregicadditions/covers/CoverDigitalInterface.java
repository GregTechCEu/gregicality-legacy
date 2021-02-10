package gregicadditions.covers;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Rotation;
import gregicadditions.client.ClientHandler;
import gregicadditions.renderer.RenderHelper;
import gregicadditions.widgets.WidgetOreList;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.FluidHandlerProxy;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.ICoverable;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.storage.MetaTileEntityQuantumChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CoverDigitalInterface extends CoverBehavior implements IFastRenderMetaTileEntity, ITickable, CoverWithUI {

    public static String path = "cover.digital";
    private static boolean flag = false; // sneaking clicking triggers right-clicking twice
    private static IEnergyContainer proxyCapability = new IEnergyContainer() {
        @Override
        public long acceptEnergyFromNetwork(EnumFacing enumFacing, long l, long l1) {
            return 0;
        }

        @Override
        public boolean inputsEnergy(EnumFacing enumFacing) {
            return false;
        }

        @Override
        public long changeEnergy(long l) {
            return 0;
        }

        @Override
        public long getEnergyStored() {
            return 0;
        }

        @Override
        public long getEnergyCapacity() {
            return 0;
        }

        @Override
        public long getInputAmperage() {
            return 0;
        }

        @Override
        public long getInputVoltage() {
            return 0;
        }
    };

    public enum MODE {
        FLUID,
        ITEM,
        ENERGY,
        MACHINE,
        PROXY;
        public static MODE[] VALUES;
        static {
            VALUES = MODE.values();
        }
    }

    // run-time data
    private FluidTankProperties[] fluids = new FluidTankProperties[0];
    private ItemStack[] items = new ItemStack[0];
    private int quantumChestCapability = 0;
    private long energyStored = 0;
    private long energyCapability = 0;
    private long energyInputPerDur = 0;
    private long energyOutputPerDur = 0;
    private final List<Long> inputEnergyList = new ArrayList<>();
    private final List<Long> outputEnergyList = new ArrayList<>();
    private int progress = 0;
    private int maxProgress = 0;
    private boolean isActive = true;
    private boolean isWorkingEnable = false;
    // persistent data
    private int slot = 0;
    private MODE mode = MODE.FLUID;
    private EnumFacing spin = EnumFacing.EAST;

    public CoverDigitalInterface(ICoverable coverHolder, EnumFacing attachedSide) {
        super(coverHolder, attachedSide);
    }

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode, int slot, EnumFacing spin) {
        if ((this.mode == mode && this.slot == slot && this.spin == spin) || slot < 0) return;
        this.markAsDirty();
        if (!isRemote()) {
            writeUpdateData(1, packetBuffer -> {
                packetBuffer.writeByte(mode.ordinal());
                packetBuffer.writeInt(slot);
                packetBuffer.writeByte(spin.getIndex());
            });
        } else {
            if ((this.mode != mode || this.spin != spin) && this.coverHolder != null) {
                this.coverHolder.scheduleRenderUpdate();
            }
        }
        this.mode = mode;
        this.slot = slot;
        this.spin = spin;
    }

    public void setMode(EnumFacing spin){
        this.setMode(this.mode, this.slot, spin);
    }

    public void setMode(int slot){
        this.setMode(this.mode, slot, this.spin);
    }

    public void setMode(MODE mode){
        this.setMode(mode, this.slot, this.spin);
    }

    public void setEnergyChanged(long energyAdded) {
        if (!isRemote() && this.mode == MODE.ENERGY) {
            if (energyAdded > 0) {
                energyInputPerDur = energyInputPerDur + energyAdded;
            } else if (energyAdded < 0) {
                energyOutputPerDur = energyOutputPerDur - energyAdded;
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setByte("cdiMode", (byte) this.mode.ordinal());
        tagCompound.setByte("cdiSpin", (byte) this.spin.ordinal());
        tagCompound.setInteger("cdiSlot", this.slot);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.mode = tagCompound.hasKey("cdiMode")? MODE.VALUES[tagCompound.getByte("cdiMode")] : MODE.FLUID;
        this.spin = tagCompound.hasKey("cdiSpin")? EnumFacing.byIndex(tagCompound.getByte("cdiSpin")) : EnumFacing.EAST;
        this.slot = tagCompound.hasKey("cdiSlot")? tagCompound.getInteger("cdiSlot"): 0;
    }

    @Override
    public void onAttached(ItemStack itemStack) { // called when cover placed.
        super.onAttached(itemStack);
        if (this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide) != null) {
            fluids = new FluidTankProperties[this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide).getTankProperties().length];
            this.mode = MODE.FLUID;
        } else if (this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide) != null) {
            items = new ItemStack[this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide).getSlots()];
            this.mode = MODE.ITEM;
        } else if (this.coverHolder.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, this.attachedSide) != null) {
            this.mode = MODE.ENERGY;
        } else if (this.coverHolder.getCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, this.attachedSide) != null) {
            this.mode = MODE.MACHINE;
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer packetBuffer) {
        super.writeInitialSyncData(packetBuffer);
        packetBuffer.writeEnumValue(mode);
        packetBuffer.writeEnumValue(spin);
        packetBuffer.writeInt(slot);
        syncAllInfo();
        writeFluids(packetBuffer);
        writeItems(packetBuffer);
        packetBuffer.writeInt(quantumChestCapability);
        packetBuffer.writeLong(energyStored);
        packetBuffer.writeLong(energyCapability);
        packetBuffer.writeInt(inputEnergyList.size());
        for (int i = 0; i < inputEnergyList.size(); i++) {
            packetBuffer.writeLong(inputEnergyList.get(i));
            packetBuffer.writeLong(outputEnergyList.get(i));
        }
        packetBuffer.writeInt(progress);
        packetBuffer.writeInt(maxProgress);
        packetBuffer.writeBoolean(isActive);
        packetBuffer.writeBoolean(isWorkingEnable);
    }

    @Override
    public void readInitialSyncData(PacketBuffer packetBuffer) {
        super.readInitialSyncData(packetBuffer);
        this.mode = packetBuffer.readEnumValue(MODE.class);
        this.spin = packetBuffer.readEnumValue(EnumFacing.class);
        this.slot = packetBuffer.readInt();
        readFluids(packetBuffer);
        readItems(packetBuffer);
        quantumChestCapability = packetBuffer.readInt();
        energyStored = packetBuffer.readLong();
        energyCapability = packetBuffer.readLong();
        int size = packetBuffer.readInt();
        inputEnergyList.clear();
        outputEnergyList.clear();
        for (int i = 0; i < size; i++) {
            inputEnergyList.add(packetBuffer.readLong());
            outputEnergyList.add(packetBuffer.readLong());
        }
        progress = packetBuffer.readInt();
        maxProgress = packetBuffer.readInt();
        isActive = packetBuffer.readBoolean();
        isWorkingEnable = packetBuffer.readBoolean();
    }

    @Override
    public void update() {
        if (!isRemote()) {
            syncAllInfo();
        }
    }

    @Override
    public EnumActionResult onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, CuboidRayTraceResult hitResult) {
        if (!this.coverHolder.getWorld().isRemote) {
            this.openUI((EntityPlayerMP)playerIn);
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult onRightClick(EntityPlayer playerIn, EnumHand hand, CuboidRayTraceResult hitResult) {
        if (!isRemote()) {
            if (playerIn.isSneaking() && playerIn.getHeldItemMainhand().isEmpty()) {
                RayTraceResult rayTraceResult = playerIn.rayTrace(3, 1);
                if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    double x = 0;
                    double y =  1 - rayTraceResult.hitVec.y + rayTraceResult.getBlockPos().getY();
                    if (this.attachedSide == EnumFacing.EAST) {
                        x = 1 - rayTraceResult.hitVec.z + rayTraceResult.getBlockPos().getZ();
                    } else if (this.attachedSide == EnumFacing.SOUTH) {
                        x = rayTraceResult.hitVec.x - rayTraceResult.getBlockPos().getX();
                    } else if (this.attachedSide == EnumFacing.WEST) {
                        x = rayTraceResult.hitVec.z - rayTraceResult.getBlockPos().getZ();
                    } else if (this.attachedSide == EnumFacing.NORTH) {
                        x = 1 - rayTraceResult.hitVec.x + rayTraceResult.getBlockPos().getX();
                    }
                    if (1f / 16 < x && x < 4f / 16 && 1f / 16 < y && y < 4f / 16) {
                        flag = !flag;
                        if (flag) {
                            this.setMode(this.slot - 1);
                        }
                        return EnumActionResult.SUCCESS;
                    } else if (12f / 16 < x && x < 15f / 16 && 1f / 16 < y && y < 4f / 16) {
                        flag = !flag;
                        if (flag) {
                            this.setMode(this.slot + 1);
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
            IFluidHandler fluidHandler = this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide);
            if (this.mode == MODE.FLUID &&  fluidHandler!= null) {
                if (!FluidUtil.interactWithFluidHandler(playerIn, hand, fluidHandler) && fluidHandler instanceof FluidHandlerProxy) {
                    if(!FluidUtil.interactWithFluidHandler(playerIn, hand, ((FluidHandlerProxy) fluidHandler).input)) {
                        return super.onRightClick(playerIn, hand, hitResult);
                    }
                }
                return EnumActionResult.SUCCESS;
            }
            IItemHandler itemHandler = this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide);
            if (this.mode == MODE.ITEM && itemHandler != null) {
                if (itemHandler.getSlots() > slot && slot >= 0) {
                    ItemStack hold = playerIn.getHeldItemMainhand();
                    if (!hold.isEmpty()) {
                        ItemStack origin = hold.copy();
                        if (playerIn.isSneaking()) {
                            playerIn.setHeldItem(EnumHand.MAIN_HAND, itemHandler.insertItem(slot, hold, false));
                        } else {
                            ItemStack itemStack = hold.copy();
                            itemStack.setCount(1);
                            if (itemHandler.insertItem(slot, itemStack, false).isEmpty()){
                                hold.setCount(hold.getCount() - 1);
                            }
                        }
                        if (playerIn.getHeldItemMainhand().isEmpty()) {
                            for (ItemStack itemStack : playerIn.inventory.mainInventory) {
                                if (origin.isItemEqual(itemStack)) {
                                    playerIn.setHeldItem(EnumHand.MAIN_HAND, itemStack.copy());
                                    itemStack.setCount(0);
                                    break;
                                }
                            }
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
            IWorkable workable = this.coverHolder.getCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, this.attachedSide);
            if (this.mode == MODE.MACHINE && workable != null) {
                if (playerIn.isSneaking()) {
                    workable.setWorkingEnabled(!isWorkingEnable);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return super.onRightClick(playerIn, hand, hitResult);
    }

    @Override
    public boolean onLeftClick(EntityPlayer entityPlayer, CuboidRayTraceResult hitResult) {
        if (!isRemote()) {
            IItemHandler itemHandler = this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide);
            if (this.mode == MODE.ITEM && itemHandler != null) {
                if (itemHandler.getSlots() > slot && slot >= 0) {
                    ItemStack itemStack;
                    if (entityPlayer.isSneaking()) {
                        itemStack = itemHandler.extractItem(slot, 64, false);
                    } else {
                         itemStack = itemHandler.extractItem(slot, 1, false);
                    }
                    if (itemStack.isEmpty() && itemHandler instanceof ItemHandlerProxy) {
                        IItemHandler insertHandler = ObfuscationReflectionHelper.getPrivateValue(ItemHandlerProxy.class, (ItemHandlerProxy)itemHandler, "insertHandler");
                        if (slot < insertHandler.getSlots()) {
                            if (entityPlayer.isSneaking()) {
                                itemStack = insertHandler.extractItem(slot, 64, false);
                            } else {
                                itemStack = insertHandler.extractItem(slot, 1, false);
                            }
                        }
                    }
                    if (!itemStack.isEmpty()) {
                        EntityItem entity = new EntityItem(entityPlayer.world, entityPlayer.posX + .5f, entityPlayer.posY + .3f, entityPlayer.posZ + .5f, itemStack);
                        entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
                        entityPlayer.world.spawnEntity(entity);
                    }
                }
                return true;
            }
        }
        return super.onLeftClick(entityPlayer, hitResult);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        WidgetGroup primaryGroup = new WidgetGroup();
        primaryGroup.addWidget(new LabelWidget(10, 5, "Digital Interface", 0));
        ToggleButtonWidget[] buttons = new ToggleButtonWidget[5];
        buttons[0] = new ToggleButtonWidget(40, 20, 20, 20, ClientHandler.BUTTON_FLUID, () -> this.mode != MODE.FLUID, (pressed) -> {
            setMode(MODE.FLUID);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[0]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.FLUID) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[1] = new ToggleButtonWidget(60, 20, 20, 20, ClientHandler.BUTTON_ITEM, () -> this.mode != MODE.ITEM, (pressed) -> {
            setMode(MODE.ITEM);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[1]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.ITEM) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[2] = new ToggleButtonWidget(80, 20, 20, 20, ClientHandler.BUTTON_ENERGY, () -> this.mode != MODE.ENERGY, (pressed) -> {
            setMode(MODE.ENERGY);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[2]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.ENERGY) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[3] = new ToggleButtonWidget(100, 20, 20, 20, ClientHandler.BUTTON_MACHINE, () -> this.mode != MODE.MACHINE, (pressed) -> {
            setMode(MODE.MACHINE);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[3]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.MACHINE) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[4] = new ToggleButtonWidget(120, 20, 20, 20, ClientHandler.BUTTON_INTERFACE, () -> this.mode != MODE.PROXY, (pressed) -> {
            setMode(MODE.PROXY);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[4]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.PROXY) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        primaryGroup.addWidget(buttons[0]);
        primaryGroup.addWidget(buttons[1]);
        primaryGroup.addWidget(buttons[2]);
        primaryGroup.addWidget(buttons[3]);
        primaryGroup.addWidget(buttons[4]);
        primaryGroup.addWidget(new LabelWidget(10, 25, "Mode:", 0));
        primaryGroup.addWidget(new LabelWidget(10, 50, "Slot:", 0));
        primaryGroup.addWidget(new ClickButtonWidget(40, 45, 20, 20, "-1", (data) -> setMode(slot - (data.isShiftClick? 10 : 1))));
        primaryGroup.addWidget(new ClickButtonWidget(140, 45, 20, 20, "+1", (data) -> setMode(slot + (data.isShiftClick? 10 : 1))));
        primaryGroup.addWidget(new ImageWidget(60, 45, 80, 20, GuiTextures.DISPLAY));
        primaryGroup.addWidget(new SimpleTextWidget(100, 55, "", 16777215, () -> Integer.toString(this.slot)));

        primaryGroup.addWidget(new LabelWidget(10, 75, "Spin:", 0));
        primaryGroup.addWidget(new ClickButtonWidget(40, 70, 20, 20, "R", (data) -> setMode(this.spin.rotateY())));
        primaryGroup.addWidget(new ImageWidget(60, 70, 80, 20, GuiTextures.DISPLAY));
        primaryGroup.addWidget(new SimpleTextWidget(100, 80, "", 16777215, () -> this.spin.toString()));
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 202).widget(primaryGroup).bindPlayerInventory(player.inventory, GuiTextures.SLOT, 8, 120);
        return builder.build(this, player);
    }

    private boolean syncAllInfo() {
        boolean syncFlag = false;
        if (mode == MODE.FLUID) {
            IFluidHandler fluidHandler = this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide);
            if (fluidHandler != null) {
                IFluidTankProperties[] fluidTankProperties = fluidHandler.getTankProperties();
                if (fluidTankProperties.length != fluids.length) {
                    fluids = new FluidTankProperties[fluidTankProperties.length];
                    syncFlag = true;
                }
                for (int i = 0; i < fluidTankProperties.length; i++) {
                    FluidStack content = fluidTankProperties[i].getContents();
                    if (fluids[i] == null || (content == null && fluids[i].getContents() != null) || (content != null && fluids[i].getContents() == null) ||
                            fluidTankProperties[i].getCapacity() != fluids[i].getCapacity() ||
                            fluidTankProperties[i].canDrain() != fluids[i].canDrain() ||
                            fluidTankProperties[i].canFill() != fluids[i].canFill()) {
                        syncFlag = true;
                        fluids[i] = new FluidTankProperties(content, fluidTankProperties[i].getCapacity(), fluidTankProperties[i].canFill(), fluidTankProperties[i].canDrain());
                    } else if(content != null && (content.amount != fluids[i].getContents().amount || !content.isFluidEqual(fluids[i].getContents()))) {
                        syncFlag = true;
                        fluids[i] = new FluidTankProperties(content, fluidTankProperties[i].getCapacity(), fluidTankProperties[i].canFill(), fluidTankProperties[i].canDrain());
                    }
                }
            }
            if (syncFlag) writeUpdateData(2, this::writeFluids);
        }
        else if(mode == MODE.ITEM) {
            IItemHandler itemHandler = this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide);
            if (this.coverHolder instanceof MetaTileEntityQuantumChest) {
                long maxStoredItems = ObfuscationReflectionHelper.getPrivateValue(MetaTileEntityQuantumChest.class, (MetaTileEntityQuantumChest)this.coverHolder, "maxStoredItems");
                if (maxStoredItems != quantumChestCapability) {
                    quantumChestCapability = (int) maxStoredItems;
                    syncFlag = true;
                }
            } else {
                if (quantumChestCapability != 0) {
                    quantumChestCapability = 0;
                    syncFlag = true;
                }
            }
            if(itemHandler != null) {
                int size = itemHandler.getSlots();
                if (items.length != size) {
                    items = new ItemStack[size];
                    syncFlag = true;
                }
                for (int i = 0; i < size; i++) {
                    if (items[i] == null) {
                        items[i] = ItemStack.EMPTY;
                    }
                    ItemStack content = itemHandler.getStackInSlot(i);
                    if (!ItemStack.areItemStacksEqual(items[i], content)) {
                        syncFlag = true;
                        items[i] = content.copy();
                    }
                }
            }
            if (syncFlag) writeUpdateData(3, this::writeItems);
        }
        else if (this.mode == MODE.ENERGY) {
            IEnergyContainer energyContainer = this.coverHolder.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, this.attachedSide);
            if (energyContainer != null) {
                if (energyStored != energyContainer.getEnergyStored() || energyCapability != energyContainer.getEnergyCapacity()) {
                    energyStored = energyContainer.getEnergyStored();
                    energyCapability = energyContainer.getEnergyCapacity();
                    writeUpdateData(4, packetBuffer -> {
                        packetBuffer.writeLong(energyStored);
                        packetBuffer.writeLong(energyCapability);
                    });
                }
                if (this.coverHolder.getTimer() % 20 == 0) { //per second
                    writeUpdateData(5, packetBuffer -> {
                        packetBuffer.writeLong(energyInputPerDur);
                        packetBuffer.writeLong(energyOutputPerDur);
                        inputEnergyList.add(energyInputPerDur);
                        outputEnergyList.add(energyOutputPerDur);
                        if (inputEnergyList.size() > 13) {
                            inputEnergyList.remove(0);
                            outputEnergyList.remove(0);
                        }
                        energyInputPerDur = 0;
                        energyOutputPerDur = 0;
                    });
                }
            }
        }
        else if (this.mode == MODE.MACHINE) {
            IWorkable workable = this.coverHolder.getCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, this.attachedSide);
            if (workable != null) {
                int progress = workable.getProgress();
                int maxProgress = workable.getMaxProgress();
                boolean isActive = workable.isActive();
                boolean isWorkingEnable = workable.isWorkingEnabled();
                if (isActive != this.isActive || isWorkingEnable != this.isWorkingEnable || this.progress != progress || this.maxProgress != maxProgress) {
                    this.progress = progress;
                    this.maxProgress = maxProgress;
                    this.isWorkingEnable = isWorkingEnable;
                    this.isActive = isActive;
                    writeUpdateData(6, packetBuffer -> {
                        packetBuffer.writeInt(progress);
                        packetBuffer.writeInt(maxProgress);
                        packetBuffer.writeBoolean(isActive);
                        packetBuffer.writeBoolean(isWorkingEnable);
                    });
                }
                if (this.coverHolder.getTimer() % 20 == 0) {
                    IEnergyContainer energyContainer = this.coverHolder.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, this.attachedSide);
                    if (energyContainer != null) {
                        if (energyStored != energyContainer.getEnergyStored() || energyCapability != energyContainer.getEnergyCapacity()) {
                            energyStored = energyContainer.getEnergyStored();
                            energyCapability = energyContainer.getEnergyCapacity();
                            writeUpdateData(4, packetBuffer -> {
                                packetBuffer.writeLong(energyStored);
                                packetBuffer.writeLong(energyCapability);
                            });
                        }
                    }
                }
            }
        }
        return syncFlag;
    }

    private void writeFluids(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(fluids.length);
        for (FluidTankProperties fluid : fluids) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Capacity", fluids[0].getCapacity());
            if (fluid.getContents() != null) {
                fluid.getContents().writeToNBT(nbt);
            }
            packetBuffer.writeCompoundTag(nbt);
        }
    }

    private void readFluids(PacketBuffer packetBuffer) {
        fluids = new FluidTankProperties[packetBuffer.readInt()];
        try {
            for (int i = 0; i < fluids.length; i++) {
                NBTTagCompound nbt = packetBuffer.readCompoundTag();
                if (nbt != null) {
                    fluids[i] = new FluidTankProperties(FluidStack.loadFluidStackFromNBT(nbt), nbt.getInteger("Capacity"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeItems(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(quantumChestCapability);
        packetBuffer.writeInt(items.length);
        for (ItemStack item : items) {
            packetBuffer.writeCompoundTag(fixItemStackSer(item));
        }
    }

    private void readItems(PacketBuffer packetBuffer) {
        quantumChestCapability = packetBuffer.readInt();
        items = new ItemStack[packetBuffer.readInt()];
        try {
            for (int i = 0; i < items.length; i++) {
                NBTTagCompound nbt = packetBuffer.readCompoundTag();
                if (nbt != null) {
                    items[i] = new ItemStack(nbt);
                    items[i].setCount(nbt.getInteger("count"));
                } else {
                    items [i] = ItemStack.EMPTY;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NBTTagCompound fixItemStackSer(ItemStack itemStack) {
        NBTTagCompound nbt = itemStack.serializeNBT();
        nbt.setInteger("count", itemStack.getCount());
        return nbt;
    }

    @Override
    public void readUpdateData(int id, PacketBuffer packetBuffer) {
        super.readUpdateData(id, packetBuffer);
        if (id == 1) { // set mode
            setMode(MODE.VALUES[packetBuffer.readByte()], packetBuffer.readInt(), EnumFacing.byIndex(packetBuffer.readByte()));
        } else if (id == 2) { // sync fluids
            readFluids(packetBuffer);
        } else if (id == 3) {
            readItems(packetBuffer);
        } else if (id == 4) {
            energyStored = packetBuffer.readLong();
            energyCapability = packetBuffer.readLong();
        } else if (id == 5) {
            energyInputPerDur = packetBuffer.readLong();
            energyOutputPerDur = packetBuffer.readLong();
            inputEnergyList.add(energyInputPerDur);
            outputEnergyList.add(energyOutputPerDur);
            if (inputEnergyList.size() > 13) {
                inputEnergyList.remove(0);
                outputEnergyList.remove(0);
            }
        } else if (id == 6) {
            this.progress = packetBuffer.readInt();
            this.maxProgress = packetBuffer.readInt();
            this.isActive = packetBuffer.readBoolean();
            boolean isWorkingEnable = packetBuffer.readBoolean();
            if (this.isWorkingEnable != isWorkingEnable && this.mode == MODE.MACHINE) {
                this.isWorkingEnable = isWorkingEnable;
                this.coverHolder.scheduleRenderUpdate();
            }
            this.isWorkingEnable = isWorkingEnable;
        }
    }

    @Override
    public boolean canAttach() {
        return this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide) != null ||
                this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide) != null ||
                this.coverHolder.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, this.attachedSide) != null ||
                this.coverHolder.getCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, this.attachedSide) != null;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, T defaultValue) {
        if (this.mode == MODE.PROXY) {
            if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER && defaultValue == null) {
                return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(proxyCapability);
            }
        }
        return defaultValue;
    }

    @Override
    public void renderCoverPlate(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 plateBox, BlockRenderLayer layer) {
    }

    @Override
    public void renderCover(CCRenderState ccRenderState, Matrix4 translation, IVertexOperation[] ops, Cuboid6 cuboid6, BlockRenderLayer blockRenderLayer) {
        Rotation rotation = new Rotation(0, 0, 1, 0);
        if (this.attachedSide == EnumFacing.UP || this.attachedSide == EnumFacing.DOWN) {
            if (this.spin == EnumFacing.WEST) {
                translation.translate(0 , 0, 1);
                rotation = new Rotation(Math.PI/2, 0, 1, 0);
            } else if (this.spin == EnumFacing.EAST) {
                translation.translate(1 , 0, 0);
                rotation = new Rotation(-Math.PI/2, 0, 1, 0);
            } else if (this.spin == EnumFacing.SOUTH) {
                translation.translate(1 , 0, 1);
                rotation = new Rotation(Math.PI, 0, 1, 0);
            }
            translation.apply(rotation);
        }
        if (mode == MODE.FLUID) {
            ClientHandler.COVER_INTERFACE_FLUID.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
            ClientHandler.COVER_INTERFACE_FLUID_GLASS.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 3));
        } else if (mode == MODE.ITEM) {
            ClientHandler.COVER_INTERFACE_ITEM.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
        } else if (mode == MODE.ENERGY) {
            ClientHandler.COVER_INTERFACE_ENERGY.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
        } else if (mode == MODE.MACHINE) {
            if (isWorkingEnable) {
                ClientHandler.COVER_INTERFACE_MACHINE_ON.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
            } else {
                ClientHandler.COVER_INTERFACE_MACHINE_OFF.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
            }
        }
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 0 && this.mode != MODE.PROXY;
    }

    @Override
    public void renderMetaTileEntityFast(CCRenderState ccRenderState, Matrix4 translation, float partialTicks) {
        GlStateManager.pushMatrix();
        /* hack the lightmap */
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        float lastBrightnessX = OpenGlHelper.lastBrightnessX;
        float lastBrightnessY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

        RenderHelper.moveToFace(translation.m03, translation.m13, translation.m23, this.attachedSide);
        RenderHelper.rotateToFace(this.attachedSide, this.spin);

        if (!renderSneakingLookAt(partialTicks)) {
            if (mode == MODE.FLUID && fluids.length > slot && slot >= 0 && fluids[slot].getContents() != null) {
                renderFluidMode(ccRenderState, translation);
            } else if (mode == MODE.ITEM && items.length > slot && slot >= 0) {
                renderItemMode();
            } else if (mode == MODE.ENERGY) {
                renderEnergyMode();
            } else if (mode == MODE.MACHINE) {
                renderMachineMode(partialTicks);
            }
        }


        /* restore the lightmap  */
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
        GL11.glPopAttrib();
        GlStateManager.popMatrix();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    private boolean renderSneakingLookAt(float partialTicks) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null && player.isSneaking()) {
            RayTraceResult rayTraceResult = player.rayTrace(3, partialTicks);
            if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK && rayTraceResult.sideHit == this.attachedSide && rayTraceResult.getBlockPos().equals(this.coverHolder.getPos())) {
                if ((this.mode != MODE.ITEM && this.mode != MODE.FLUID) || player.getHeldItemMainhand().isEmpty()) {
                    RenderHelper.renderRect(-7f / 16, -7f / 16, 3f / 16, 3f / 16, 0.0002f, 0XFF838583);
                    RenderHelper.renderRect(4f / 16, -7f / 16, 3f / 16, 3f / 16, 0.0002f, 0XFF838583);
                    RenderHelper.renderText(-5.5f / 16, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, "<", true);
                    RenderHelper.renderText(5.7f / 16, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, ">", true);
                    RenderHelper.renderText(0, -0.37f, 0, 1.0f / 120, 0XFFFFFFFF, "Slot: " + slot, true);
                    if (this.coverHolder instanceof MetaTileEntity){
                        RenderHelper.renderRect(-7f / 16, -4f / 16, 14f / 16, 1f / 16, 0.0002f, 0XFF000000);
                        RenderHelper.renderText(0, -0.24f, 0, 1.0f / 200, 0XFFFFFFFF, I18n.format(((MetaTileEntity) this.coverHolder).getMetaFullName()), true);
                    }
                    RenderHelper.renderItemOverLay(-8f/16, -5f/16, 0.002f, 1f/32, this.coverHolder.getStackForm());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    private void renderMachineMode(float partialTicks) {
        int color = energyCapability > 10 * energyStored ? 0XFFFF2F39 : isWorkingEnable? 0XFF00FF00 : 0XFFFF662E;
        if (isActive && maxProgress != 0) {
            float offset = ((this.coverHolder.getTimer() % 20 + partialTicks) * 0.875f / 20);
            float start = Math.max(-0.4375f, -0.875f + 2 * offset);
            float width = Math.min(0.4375f, -0.4375f + 2 * offset) - start;
            int startAlpha = 0X00;
            int endAlpha = 0XFF;
            if (offset < 0.4375f) {
                startAlpha = (int) (255 - 255 / 0.4375 * offset);
            } else if(start > 0.4375) {
                endAlpha = (int) (510 - 255 / 0.4375 * offset);
            }
            RenderHelper.renderRect(-7f / 16, -7f / 16, progress * 14f / (maxProgress * 16), 3f / 16, 0.0002f, 0XFFFF5F44);
            RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(progress * 100 / maxProgress), true);
            RenderHelper.renderGradientRect(start, -4f / 16, width, 1f / 16, 0.0002f, (color & 0X00FFFFFF) | (startAlpha << 24), (color & 0X00FFFFFF) | (endAlpha << 24), true);
        } else {
            RenderHelper.renderRect(-7f / 16, -4f / 16, 14f / 16, 1f / 16, 0.0002f, color);
        }
    }

    @SideOnly(Side.CLIENT)
    private void renderEnergyMode() {
        RenderHelper.renderLineChart(inputEnergyList,-5.5f/16, 5.5f/16, 12f/16,6f/16,0.005f,0XFF03FF00);
        RenderHelper.renderLineChart(outputEnergyList,-5.5f/16, 5.5f/16, 12f/16,6f/16,0.005f,0XFFFF2F39);
        RenderHelper.renderText(-5.7f/16, -2.8f/16, 0, 1.0f / 270, 0XFF03FF00, "EU I: " + energyInputPerDur + "EU/s", false);
        RenderHelper.renderText(-5.7f/16, -2.1f/16, 0, 1.0f / 270, 0XFFFF0000, "EU O: " + energyOutputPerDur + "EU/s", false);
        RenderHelper.renderRect(-7f / 16, -7f / 16, energyStored * 14f / (energyCapability * 16), 3f / 16, 0.0002f, 0XFFFFD817);
        RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(energyStored), true);
    }

    @SideOnly(Side.CLIENT)
    private void renderItemMode() {
        ItemStack itemStack = items[slot];
        if (!itemStack.isEmpty()) {
           RenderHelper.renderItemOverLay(-8f/16, -5f/16, 0, 1f/32, itemStack);
           if(quantumChestCapability != 0) {
               RenderHelper.renderRect(-7f / 16, -7f / 16, Math.max(itemStack.getCount() * 14f / (quantumChestCapability * 16), 0.001f), 3f / 16, 0.0002f, 0XFF25B9FF);
           }
           RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(itemStack.getCount()), true);

        }
    }

    @SideOnly(Side.CLIENT)
    private void renderFluidMode(CCRenderState ccRenderState, Matrix4 translation) {
        FluidStack fluidStack = fluids[slot].getContents();
        assert fluidStack != null;
        RenderHelper.renderFluidOverLay(ccRenderState, translation, new IVertexOperation[]{}, this.attachedSide, Math.max(fluidStack.amount * 1.0D / fluids[slot].getCapacity(), 0.01D), fluidStack, this.spin);
        int fluidColor = WidgetOreList.getFluidColor(fluidStack.getFluid());
        int textColor = ((fluidColor & 0xff) + ((fluidColor >> 8) & 0xff) + ((fluidColor >> 16) & 0xff)) / 3 > (255 / 2) ? 0X0 : 0XFFFFFFFF;
        RenderHelper.renderRect(-7f / 16, -7f / 16, 14f / 16, 3f / 16, 0.0002f, fluidColor | (255 << 24));
        RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, textColor, readAmountOrCountOrEnergy(fluidStack.amount), true);
    }

    static String[][] units = {
            {"", "mB", "", "EU"},
            {"", "B", "K", "KEU"},
            {"", "KB", "M", "MEU"},
    };
    @SideOnly(Side.CLIENT)
    private String readAmountOrCountOrEnergy(long number) {
        int unit = this.mode == MODE.FLUID ? 1 : this.mode == MODE.ITEM ? 2 : this.mode == MODE.ENERGY ? 3 : 0;
        if (this.mode == MODE.MACHINE) {
            return number + "%";
        }
        if (number / 1000 == 0) {
            return number + units[0][unit];
        } else if (number / 1000000 == 0) {
            return new DecimalFormat("#.#").format(number * 1.0f / 1000) + units[1][unit];
        }
        return new DecimalFormat("#.#").format(number * 1.0f / 1000000) + units[2][unit];
    }

}
