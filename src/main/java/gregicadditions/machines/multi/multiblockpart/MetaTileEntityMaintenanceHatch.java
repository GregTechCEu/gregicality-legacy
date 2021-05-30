package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaItems;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.client.ClientHandler.MAINTENANCE_ICON;

public class MetaTileEntityMaintenanceHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<MetaTileEntityMaintenanceHatch> {

    private ItemStackHandler inventory;
    private final byte type; // Type 0 is regular, 1 is auto taping, 2 is full auto

    private final List<MetaToolValueItem> wrenches = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.WRENCH);
        add(MetaItems.WRENCH_LV);
        add(MetaItems.WRENCH_MV);
        add(MetaItems.WRENCH_HV);
    }};

    private final List<MetaToolValueItem> screwdrivers = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.SCREWDRIVER);
        add(MetaItems.SCREWDRIVER_LV);
    }};

    private final List<MetaToolValueItem> softHammers = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.SOFT_HAMMER);
    }};

    private final List<MetaToolValueItem> hardHammers = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.HARD_HAMMER);
    }};

    private final List<MetaToolValueItem> wireCutters = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.WIRE_CUTTER);
    }};

    private final List<MetaToolValueItem> crowbars = new ArrayList<MetaToolValueItem>() {{
        add(MetaItems.CROWBAR);
    }};


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

            SimpleOverlayRenderer renderer = getTier() == 9 ? ClientHandler.FULLAUTO_MAINTENANCE_OVERLAY : getTier() == 5 ? ClientHandler.AUTO_MAINTENANCE_OVERLAY : ClientHandler.MAINTENANCE_OVERLAY;
            renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }

    //todo don't accept items if not type 1
    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        this.inventory = new ItemStackHandler(1);
        this.itemInventory = this.inventory;
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
        if (!(this.getController() instanceof GARecipeMapMultiblockController))
            return;
        GARecipeMapMultiblockController controller = (GARecipeMapMultiblockController) this.getController();

        if (!controller.hasProblems())
            return;

        switch (this.type) {
            case 0: { // Manual
                if (entityPlayer == null)
                    break;

                // For every slot in the player's main inventory, try to duct tape fix
                for (int i = 0; i < entityPlayer.inventory.mainInventory.size(); i++) {
                    if (consumeDuctTape(new ItemStackHandler(entityPlayer.inventory.mainInventory), i)) { //todo duct tape overlay on hatch
                        fixEverything();
                        break;
                    }
                }

                // For each problem the multi has, try to fix with tools
                byte problems = controller.getProblems();

                for (byte i = 0; i < 6; i++) {
                    if (((problems >> i) & 1) == 0)
                        fixProblemWithTool(i, entityPlayer);
                }
                break;
            }
            case 1: { // Consume Duct Tape for auto taping repair, then fix everything
                if(!consumeDuctTape(this.inventory, 0)) //todo make this do something (redstone?) if it fails or is out of tape
                    break;
            }
            case 2: { // Fix everything for full auto repair
                fixEverything();
                break;
            }
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
                tools = wrenches;
                break;
            case 1:
                tools = screwdrivers;
                break;
            case 2:
                tools = softHammers;
                break;
            case 3:
                tools = hardHammers;
                break;
            case 4:
                tools = wireCutters;
                break;
            case 5:
                tools = crowbars;
                break;
        }

        if (tools == null)
            return;

        for (MetaToolValueItem tool : tools) {
            for (ItemStack itemStack : entityPlayer.inventory.mainInventory) {
                if (itemStack.isItemEqualIgnoreDurability(tool.getStackForm())) {
                    ((GARecipeMapMultiblockController) this.getController()).setMaintenanceFixed(problemIndex);
                    damageTool(itemStack);
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
        if (this.getController() instanceof GARecipeMapMultiblockController)
            for (int i = 0; i < 6; i++) ((GARecipeMapMultiblockController) this.getController()).setMaintenanceFixed(i);
    }

    @Override
    public void update() {
        super.update();
        if (this.type != 0) {
            if (this.getController() instanceof GARecipeMapMultiblockController) {
                if (getOffsetTimer() % 20 == 0 && ((GARecipeMapMultiblockController) this.getController()).hasProblems() && this.getController().isStructureFormed()) {
                    fixProblems(null);
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("Inventory", this.inventory.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.inventory.deserializeNBT(data.getCompoundTag("Inventory"));
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
        return GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY;
    }

    @Override
    public void registerAbilities(List<MetaTileEntityMaintenanceHatch> list) {
        list.add(this);
    }
}
