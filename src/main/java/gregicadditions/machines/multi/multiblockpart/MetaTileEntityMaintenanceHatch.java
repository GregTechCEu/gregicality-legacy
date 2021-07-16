package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaItems;
import gregicadditions.machines.multi.IMaintenance;
import gregicadditions.tools.GTToolTypes;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.items.toolitem.ToolMetaItem.MetaToolValueItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.SimpleOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import gregtech.api.gui.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.client.ClientHandler.MAINTENANCE_ICON;
import static gregicadditions.capabilities.MultiblockDataCodes.*;

public class MetaTileEntityMaintenanceHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<MetaTileEntityMaintenanceHatch> {

    private ItemStackHandler inventory;
    private final byte type; // Type 0 is regular, 1 is auto taping, 2 is full auto
    private boolean isTaped;

    // Used to store state temporarily if the Controller is broken
    private byte problems = -1;
    private int timeActive = -1;

    public MetaTileEntityMaintenanceHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.initializeInventory();

        if (tier == 9) type = 2;
        else if (tier == 5) type = 1;
        else type = 0;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityMaintenanceHatch(metaTileEntityId, getTier());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);

        if (shouldRenderOverlay()) {

            SimpleOverlayRenderer renderer =
                    getTier() == 9 ? ClientHandler.FULLAUTO_MAINTENANCE_OVERLAY
                  : getTier() == 5 ? ClientHandler.AUTO_MAINTENANCE_OVERLAY
                  : isTaped ? ClientHandler.MAINTENANCE_OVERLAY_TAPED
                  : ClientHandler.MAINTENANCE_OVERLAY;
            renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }

    //todo don't accept items if not type 1 (currently doesn't work)
    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        if (this.type != 1)
            return super.createImportItemHandler();
        return new ItemStackHandler(1);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        if (this.type != 1)
            return super.createExportItemHandler();
        return new ItemStackHandler(1);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        this.inventory = new ItemStackHandler(1);
        this.itemInventory = this.inventory;
    }

    public void setTaped(boolean isTaped) {
        this.isTaped = isTaped;
        if (!getWorld().isRemote) {
            writeCustomData(IS_TAPED, buf -> buf.writeBoolean(isTaped));
            markDirty();
        }
    }

    public void storeMaintenanceData(byte problems, int timeActive) {
        this.problems = problems;
        this.timeActive = timeActive;
        if (!getWorld().isRemote) {
            writeCustomData(STORE_MAINTENANCE, buf -> {
                buf.writeByte(problems);
                buf.writeInt(timeActive);
            });
        }
    }

    public boolean hasMaintenanceData() {
        return problems != -1;
    }

    public Tuple<Byte, Integer> readMaintenanceData() {
        Tuple<Byte, Integer> data = new Tuple<>(problems, timeActive);
        storeMaintenanceData((byte) -1, -1);
        return data;
    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        clearInventory(itemBuffer, this.inventory);
    }

    /**
     * Fixes maintenance problems according to which hatch the MTE is
     *
     * @param entityPlayer The player to get duct tape or tools from when a regular hatch
     */
    private void fixProblems(EntityPlayer entityPlayer) {
        byte problems;

        if (!(this.getController() instanceof IMaintenance))
            return;

        if (!((IMaintenance) this.getController()).hasProblems())
            return;

        problems = ((IMaintenance) this.getController()).getProblems();

        switch (this.type) {
            case 0: { // Manual
                if (entityPlayer == null)
                    break;

                // For every slot in the player's main inventory, try to duct tape fix
                for (int i = 0; i < entityPlayer.inventory.mainInventory.size(); i++) {
                    if (consumeDuctTape(new ItemStackHandler(entityPlayer.inventory.mainInventory), i)) {
                        fixEverything();
                        setTaped(true);
                        break;
                    }
                }

                if (isTaped)
                    break;

                // For each problem the multi has, try to fix with tools
                for (byte i = 0; i < 6; i++) {
                    if (((problems >> i) & 1) == 0)
                        fixProblemWithTool(i, entityPlayer);
                }
                break;
            }
            case 1: { // Consume Duct Tape for auto taping repair, then fix everything
                if(!consumeDuctTape(this.inventory, 0)) //todo make this do something (redstone?) if it fails or is out of tape
                    fixEverything();
                    break;
            }
            // Fully automatic hatch never lets maintenance change elsewhere
        }
    }

    /**
     *
     * Handles duct taping for manual and auto-taping use
     *
     * @param handler The handler to get duct tape from
     * @param slot The inventory slot to check for tape
     * @return true if tape was consumed, else false
     */
    private boolean consumeDuctTape(ItemStackHandler handler, int slot) {
        if (handler == null)
            return false;

        ItemStack itemStack = handler.getStackInSlot(slot);
        if (!itemStack.isEmpty() && itemStack.isItemEqual(GAMetaItems.INSULATING_TAPE.getStackForm()))
            if (itemStack.getCount() - 1 >= 0) {
                itemStack.shrink(1);
                return true;
            }
        return false;
    }

    /**
     *
     * Fixes all maintenance problems that the inventory (should be a player) has tools to fix for
     *
     * @param problemIndex index of the maintenance problem
     * @param entityPlayer target player whose inventory is used to scan for tools
     */
    private void fixProblemWithTool(int problemIndex, EntityPlayer entityPlayer) {
        List<MetaToolValueItem> tools = null;

        switch (problemIndex) {
            case 0:
                tools = GTToolTypes.wrenches;
                break;
            case 1:
                tools = GTToolTypes.screwdrivers;
                break;
            case 2:
                tools = GTToolTypes.softHammers;
                break;
            case 3:
                tools = GTToolTypes.hardHammers;
                break;
            case 4:
                tools = GTToolTypes.wireCutters;
                break;
            case 5:
                tools = GTToolTypes.crowbars;
                break;
        }

        if (tools == null)
            return;

        for (MetaToolValueItem tool : tools) {
            for (ItemStack itemStack : entityPlayer.inventory.mainInventory) {
                if (itemStack.isItemEqualIgnoreDurability(tool.getStackForm())) {
                    ((IMaintenance) this.getController()).setMaintenanceFixed(problemIndex);
                    damageTool(itemStack);
                    this.setTaped(false);
                }
            }
        }
    }

    /**
     * Applies damage to toon upon its use for maintenance
     *
     * @param itemStack item to apply damage to
     */
    private void damageTool(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ToolMetaItem) {
            ToolMetaItem<?> toolMetaItem = (ToolMetaItem<?>) itemStack.getItem();
            toolMetaItem.damageItem(itemStack, 1, true, false);
        }
    }

    /**
     * Fixes every maintenance problem
     */
    private void fixEverything() {
        if (this.getController() instanceof IMaintenance)
            for (int i = 0; i < 6; i++) ((IMaintenance) this.getController()).setMaintenanceFixed(i);
    }

    /**
     * @return maintenance hatch type, bounded [0, 3)
     */
    public int getType() {
        return this.type;
    }

    @Override
    protected boolean canMachineConnectRedstone(EnumFacing side) {
        return super.canMachineConnectRedstone(side);
    }

    @Override
    public void onRemoval() {
        IMaintenance controller = (IMaintenance) getController();
        if (!getWorld().isRemote && controller != null)
            controller.storeTaped(isTaped);
        super.onRemoval();
    }

    @Override
    public void update() {
        super.update();
        if (this.type != 0) {
            if (this.getController() instanceof IMaintenance) {
                if (getOffsetTimer() % 20 == 0 && ((IMaintenance) this.getController()).hasProblems() && this.getController().isStructureFormed()) {
                    fixProblems(null);
                }
            }
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == IS_TAPED) {
            this.isTaped = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == STORE_MAINTENANCE) {
            this.problems = buf.readByte();
            this.timeActive = buf.readInt();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("Inventory", this.inventory.serializeNBT());
        data.setBoolean("Taped", this.isTaped);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.inventory.deserializeNBT(data.getCompoundTag("Inventory"));
        this.isTaped = data.getBoolean("Taped");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isTaped);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isTaped = buf.readBoolean();
    }

    @Override
    public ICubeRenderer getBaseTexture() {
        MultiblockControllerBase controller = getController();
        return controller == null ? getTier() == 9 ? ClientHandler.VOLTAGE_CASINGS[getTier()] : Textures.VOLTAGE_CASINGS[getTier()] : controller.getBaseTexture(this);
    }

    /**
     * Do not open the gui if the hatch is a full auto hatch
     */
    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (type == 2) {
            return false;
        }
        return super.onRightClick(playerIn, hand, facing, hitResult);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 18 + 18 + 94).label(10, 5, this.getMetaFullName());
        if (type == 1) {
            builder.widget(new SlotWidget(this.getItemInventory(), 0, 89 - 9, 18, true, true)
                    .setBackgroundTexture(GuiTextures.SLOT));
        } else if (type == 0) {
            builder.widget(new ClickButtonWidget(89 - 9 - 1, 18 - 1, 20, 20, "", data -> fixProblems(entityPlayer))
                    .setButtonTexture(MAINTENANCE_ICON));
        }

        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 8, 18 + 18 + 12);
        return builder.build(this.getHolder(), entityPlayer);
    }

    @Override
    public MultiblockAbility<MetaTileEntityMaintenanceHatch> getAbility() {
        return GregicAdditionsCapabilities.MAINTENANCE_HATCH;
    }

    @Override
    public void registerAbilities(List<MetaTileEntityMaintenanceHatch> list) {
        list.add(this);
    }

    public boolean isTaped() {
        return isTaped;
    }
}
