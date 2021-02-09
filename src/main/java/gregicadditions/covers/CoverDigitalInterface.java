package gregicadditions.covers;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Translation;
import gregicadditions.renderer.GATextures;
import gregicadditions.renderer.RenderHelper;
import gregicadditions.widgets.WidgetOreList;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.FluidHandlerProxy;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.ICoverable;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.common.metatileentities.storage.MetaTileEntityQuantumChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
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

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CoverDigitalInterface extends CoverBehavior implements IFastRenderMetaTileEntity, ITickable, CoverWithUI {

    public enum MODE {
        FLUID,
        ITEM,
        ENERGY,
        MACHINE,
        COMPUTER;
        public static MODE[] VALUES;
        static {
            VALUES = MODE.values();
        }
    }

    // runtime data (fluids and items not be fucking synced in the client)
    private FluidTankProperties[] fluids = new FluidTankProperties[0];
    private ItemStack[] items = new ItemStack[0];
    private int quantumChestCapability = 0;
    private long energyStored = 0;
    private long energyCapability = 0;
    private long energyInputPerDur = 0;
    private long energyOutputPerDur = 0;
    private final List<Long> inputEnergyList = new ArrayList<>();
    private final List<Long> outputEnergyList = new ArrayList<>();
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
        if (this.mode == mode && this.slot == slot && this.spin == spin && slot < 0) return;
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
        buttons[0] = new ToggleButtonWidget(30, 20, 20, 20, GATextures.BUTTON_FLUID, () -> this.mode != MODE.FLUID, (pressed) -> {
            setMode(MODE.FLUID);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[0]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.FLUID) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[1] = new ToggleButtonWidget(50, 20, 20, 20, GATextures.BUTTON_ITEM, () -> this.mode != MODE.ITEM, (pressed) -> {
            setMode(MODE.ITEM);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[1]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.ITEM) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[2] = new ToggleButtonWidget(70, 20, 20, 20, GATextures.BUTTON_ENERGY, () -> this.mode != MODE.ENERGY, (pressed) -> {
            setMode(MODE.ENERGY);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[2]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.ENERGY) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[3] = new ToggleButtonWidget(90, 20, 20, 20, GATextures.BUTTON_MACHINE, () -> this.mode != MODE.MACHINE, (pressed) -> {
            setMode(MODE.MACHINE);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[3]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.MACHINE) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        buttons[4] = new ToggleButtonWidget(110, 20, 20, 20, GATextures.BUTTON_INTERFACE, () -> this.mode != MODE.COMPUTER, (pressed) -> {
            setMode(MODE.COMPUTER);
            if (!pressed) return;
            Stream.of(buttons).forEach(button -> { if (button != buttons[4]) button.handleClientAction(1, new PacketBuffer(null){@Override public boolean readBoolean() { return false; }});});
        }){ @Override public boolean mouseClicked(int mouseX, int mouseY, int button) { if (mode == MODE.COMPUTER) return false;return super.mouseClicked(mouseX, mouseY, button); }};
        primaryGroup.addWidget(buttons[0]);
        primaryGroup.addWidget(buttons[1]);
        primaryGroup.addWidget(buttons[2]);
        primaryGroup.addWidget(buttons[3]);
        primaryGroup.addWidget(buttons[4]);
        primaryGroup.addWidget(new LabelWidget(5, 50, "Slot:", 0));
        primaryGroup.addWidget(new ClickButtonWidget(30, 45, 20, 20, "-1", (data) -> setMode(slot - (data.isShiftClick? 10 : 1))));
        primaryGroup.addWidget(new ClickButtonWidget(126, 45, 20, 20, "+1", (data) -> setMode(slot + (data.isShiftClick? 10 : 1))));
        primaryGroup.addWidget(new ImageWidget(50, 45, 76, 20, GuiTextures.DISPLAY));
        primaryGroup.addWidget(new SimpleTextWidget(88, 55, "", 16777215, () -> Integer.toString(this.slot)));

        primaryGroup.addWidget(new LabelWidget(5, 75, "Spin:", 0));
        primaryGroup.addWidget(new ClickButtonWidget(30, 70, 20, 20, "R", (data) -> setMode(this.spin.rotateY())));
        primaryGroup.addWidget(new ImageWidget(50, 70, 76, 20, GuiTextures.DISPLAY));
        primaryGroup.addWidget(new SimpleTextWidget(88, 80, "", 16777215, () -> this.spin.toString()));
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 272).widget(primaryGroup).bindPlayerInventory(player.inventory, GuiTextures.SLOT, 8, 190);
        return builder.build(this, player);
    }

    private void syncAllInfo() {
        if (mode == MODE.FLUID) {
            boolean syncFlag = false;
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
        if(mode == MODE.ITEM) {
            boolean syncFlag = false;
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
        if (this.mode == MODE.ENERGY) {
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
        }
    }

    @Override
    public boolean canAttach() {
        return this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide) != null ||
                this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide) != null ||
                this.coverHolder.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, this.attachedSide) != null;
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
            GATextures.COVER_INTERFACE_FLUID.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
            GATextures.COVER_INTERFACE_FLUID_GLASS.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 3));
        } else if (mode == MODE.ITEM) {
            GATextures.COVER_INTERFACE_ITEM.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
        } else if (mode == MODE.ENERGY) {
            GATextures.COVER_INTERFACE_ENERGY.renderSided(this.attachedSide, cuboid6, ccRenderState, ArrayUtils.addAll(ops, rotation), RenderHelper.adjustTrans(translation, this.attachedSide, 1));
        }
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

        if (mode == MODE.FLUID && fluids.length > slot && slot >= 0 && fluids[slot].getContents() != null) {
            renderFluidMode(ccRenderState, translation);
            // renderIO(translation, partialTicks);
        } else if (mode == MODE.ITEM && items.length > slot && slot >= 0) {
            renderItemMode(translation);
            // renderIO(translation, partialTicks);
        } else if (mode == MODE.ENERGY) {
            renderEnergyMode(translation);
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
    @Deprecated
    private void renderIO(Matrix4 translation, float partialTicks) {
        if (Minecraft.getMinecraft().player != null) {
            RayTraceResult rayTraceResult = Minecraft.getMinecraft().player.rayTrace(2, partialTicks);
            if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK && rayTraceResult.sideHit == this.attachedSide && this.coverHolder != null && rayTraceResult.getBlockPos().equals(this.coverHolder.getPos())) {
                if (this.mode == MODE.FLUID) {
                    RenderHelper.renderGradientRect(-7f / 16, -3f / 16, 3f / 16, 10f / 16, 0.0004f, Color.LIGHT_GRAY.getRGB(),Color.LIGHT_GRAY.getRGB());
                    RenderHelper.renderGradientRect(4f / 16, -3f / 16, 3f / 16, 10f / 16, 0.0004f, Color.LIGHT_GRAY.getRGB(),Color.LIGHT_GRAY.getRGB());
                    RenderHelper.renderTextureArea(GATextures.FLUID_IMPORT, -7f / 16, 0, 3f / 16, 3f /16, 0.0005f);
                    RenderHelper.renderTextureArea(GATextures.FLUID_EXPORT, 4f / 16, 0, 3f / 16, 3f /16, 0.0005f);
                } else if (this.mode == MODE.ITEM) {
                    RenderHelper.renderGradientRect(-7f / 16, -3f / 16, 3f / 16, 10f / 16, 0.0004f, Color.LIGHT_GRAY.getRGB(),Color.LIGHT_GRAY.getRGB());
                    RenderHelper.renderGradientRect(4f / 16, -3f / 16, 3f / 16, 10f / 16, 0.0004f, Color.LIGHT_GRAY.getRGB(),Color.LIGHT_GRAY.getRGB());
                    RenderHelper.renderTextureArea(GATextures.ITEM_IMPORT, -7f / 16, 0, 3f / 16, 3f /16, 0.0005f);
                    RenderHelper.renderTextureArea(GATextures.ITEM_EXPORT, 4f / 16, 0, 3f / 16, 3f /16, 0.0005f);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void renderEnergyMode(Matrix4 translation) {
        RenderHelper.renderLineChart(inputEnergyList,-5.5f/16, 5.5f/16, 12f/16,6f/16,0.005f,0XFF03FF00);
        RenderHelper.renderLineChart(outputEnergyList,-5.5f/16, 5.5f/16, 12f/16,6f/16,0.005f,0XFFFF0000);
        RenderHelper.renderText(-5.7f/16, -2.8f/16, 0, 1.0f / 270, 0XFF03FF00, "EU I: " + energyInputPerDur + "EU/s", false);
        RenderHelper.renderText(-5.7f/16, -2.1f/16, 0, 1.0f / 270, 0XFFFF0000, "EU O: " + energyOutputPerDur + "EU/s", false);
        RenderHelper.renderGradientRect(-7f / 16, -7f / 16, energyStored * 14f / (energyCapability * 16), 3f / 16, 0.0002f, 0XFFFFD817, 0XFFFFD817);
        RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(energyStored), true);
    }

    @SideOnly(Side.CLIENT)
    private void renderItemMode(Matrix4 translation) {
        ItemStack itemStack = items[slot];
        if (!itemStack.isEmpty()) {
           RenderHelper.renderItemOverLay(new Translation(-8f/16, -5f/16, 0), 1f/32, itemStack);
           if(quantumChestCapability != 0) {
               //todo example
               RenderHelper.renderGradientRect(-7f / 16, -7f / 16, 1409600 * 14f / (quantumChestCapability * 16), 3f / 16, 0.0002f, Color.BLUE.getRGB(),Color.BLUE.getRGB());
               RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(1409600), true);
           } else {
               RenderHelper.renderText(0, -0.4f, 0, 1.0f / 70, 0XFFFFFFFF, readAmountOrCountOrEnergy(itemStack.getCount()), true);
           }

        }
    }

    @SideOnly(Side.CLIENT)
    private void renderFluidMode(CCRenderState ccRenderState, Matrix4 translation) {
        FluidStack fluidStack = fluids[0].getContents();
        assert fluidStack != null;
        RenderHelper.renderFluidOverLay(ccRenderState, translation, new IVertexOperation[]{}, this.attachedSide, Math.max(fluidStack.amount * 1.0D / fluids[0].getCapacity(), 0.01D), fluidStack, this.spin);
        int fluidColor = WidgetOreList.getFluidColor(fluidStack.getFluid());
        int textColor = ((fluidColor & 0xff) + ((fluidColor >> 8) & 0xff) + ((fluidColor >> 16) & 0xff)) / 3 > (255 / 2) ? 0X0 : 0XFFFFFFFF;
        RenderHelper.renderGradientRect(-7f / 16, -7f / 16, 14f / 16, 3f / 16, 0.0002f, fluidColor | (255 << 24), fluidColor | (255 << 24));
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
        if (number / 1000 == 0) {
            return number + units[0][unit];
        } else if (number / 1000000 == 0) {
            return new DecimalFormat("#.#").format(number * 1.0f / 1000) + units[1][unit];
        }
        return new DecimalFormat("#.#").format(number * 1.0f / 1000000) + units[2][unit];
    }

}
