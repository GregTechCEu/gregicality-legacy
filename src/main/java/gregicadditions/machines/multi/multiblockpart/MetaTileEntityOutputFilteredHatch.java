package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.widgets.WidgetGroupFluidFilter;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.render.SimpleOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.covers.filter.FilterTypeRegistry;
import gregtech.common.covers.filter.FluidFilter;
import gregtech.common.covers.filter.SimpleFluidFilter;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityOutputFilteredHatch extends GAMetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IFluidTank> {

    private static final int INITIAL_INVENTORY_SIZE = 8000;
    private final ItemStackHandler containerInventory;
    private FluidFilter currentFluidFilter;
    private boolean isBlacklistFilter = false;
    private final ItemStackHandler filterInventory;

    public MetaTileEntityOutputFilteredHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.containerInventory = new ItemStackHandler(2);
        this.filterInventory = new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return FilterTypeRegistry.getFluidFilterForStack(stack) != null;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            protected void onLoad() {
                onFilterSlotChange();
            }

            @Override
            protected void onContentsChanged(int slot) {
                onFilterSlotChange();
            }
        };
        initializeInventory();
    }

    protected void onFilterSlotChange() {
        ItemStack filterStack = filterInventory.getStackInSlot(0);
        FluidFilter newFluidFilter = FilterTypeRegistry.getFluidFilterForStack(filterStack);
        if (newFluidFilter == null) {
            if (currentFluidFilter != null) {
                this.currentFluidFilter = null;
            }
        } else if (currentFluidFilter == null ||
                newFluidFilter.getClass() != currentFluidFilter.getClass()) {
            this.currentFluidFilter = new SimpleFluidFilter();
        }

    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityOutputFilteredHatch(metaTileEntityId, getTier());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("ContainerInventory", containerInventory.serializeNBT());
        data.setTag("FilterInventory", filterInventory.serializeNBT());
        data.setBoolean("IsBlacklist", isBlacklistFilter);
        if (currentFluidFilter != null) {
            NBTTagCompound filterInventory = new NBTTagCompound();
            currentFluidFilter.writeToNBT(filterInventory);
            data.setTag("Filter", filterInventory);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        containerInventory.deserializeNBT(data.getCompoundTag("ContainerInventory"));
        filterInventory.deserializeNBT(data.getCompoundTag("FilterInventory"));
        isBlacklistFilter = data.getBoolean("IsBlacklist");
        if (currentFluidFilter != null) {
            NBTTagCompound filterInventory = data.getCompoundTag("Filter");
            currentFluidFilter.readFromNBT(filterInventory);
        }
    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        super.clearMachineInventory(itemBuffer);
        clearInventory(itemBuffer, containerInventory);
    }

    @Override
    public void onRemoval() {
        int slots = filterInventory.getSlots();
        for (int i = 0; i < slots; i++)
            Block.spawnAsEntity(getWorld(), getPos(), filterInventory.getStackInSlot(i));
    }

    public void setBlacklistFilter(boolean blacklistFilter) {
        isBlacklistFilter = blacklistFilter;
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            fillContainerFromInternalTank(containerInventory, containerInventory, 0, 1);
            pushFluidsIntoNearbyHandlers(getFrontFacing());
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer = Textures.PIPE_OUT_OVERLAY;
            renderer.renderSided(getFrontFacing(), renderState, translation, pipeline);
        }
    }

    private int getInventorySize() {
        return INITIAL_INVENTORY_SIZE * (1 << getTier());
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false, new FluidTank(getInventorySize()) {
            @Override
            public boolean canFill() {
                return super.canFill();
            }

            @Override
            public boolean canFillFluidType(FluidStack fluid) {
                boolean result = true;
                if (currentFluidFilter != null) {
                    result = currentFluidFilter.testFluid(fluid);
                }
                if (isBlacklistFilter) {
                    result = !result;
                }
                return result;
            }
        });
    }


    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return MultiblockAbility.EXPORT_FLUIDS;
    }

    @Override
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.addAll(this.exportFluids.getFluidTanks());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createTankUI((exportFluids).getTankAt(0), containerInventory, getMetaFullName(), entityPlayer)
                .build(getHolder(), entityPlayer);
    }

    public ModularUI.Builder createTankUI(IFluidTank fluidTank, IItemHandlerModifiable containerInventory, String title, EntityPlayer entityPlayer) {
        ModularUI.Builder builder = new ModularUI.Builder(GuiTextures.BACKGROUND, 190, 156);
        builder.image(7, 16, 81, 55, GuiTextures.DISPLAY);
        TankWidget tankWidget = new TankWidget(fluidTank, 69, 52, 18, 18)
                .setHideTooltip(true).setAlwaysShowFull(true);
        builder.widget(tankWidget);
        builder.label(11, 20, "gregtech.gui.fluid_amount", 0xFFFFFF);
        builder.dynamicLabel(11, 30, tankWidget::getFormattedFluidAmount, 0xFFFFFF);
        builder.dynamicLabel(11, 40, tankWidget::getFluidLocalizedName, 0xFFFFFF);

        builder.widget(new ToggleButtonWidget(110, 36, 18, 18, GuiTextures.BUTTON_BLACKLIST,
                () -> isBlacklistFilter, this::setBlacklistFilter).setTooltipText("cover.filter.blacklist"));
        builder.widget(new SlotWidget(filterInventory, 0, 110, 17)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.FILTER_SLOT_OVERLAY));
        builder.widget(new WidgetGroupFluidFilter(120, 17, () -> currentFluidFilter));

        return builder.label(6, 6, title)
                .widget(new FluidContainerSlotWidget(containerInventory, 0, 90, 17, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.IN_SLOT_OVERLAY))
                .widget(new ImageWidget(91, 36, 14, 15, GuiTextures.TANK_ICON))
                .widget(new SlotWidget(containerInventory, 1, 90, 54, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 14, 74);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.universal.tooltip.fluid_storage_capacity", getInventorySize()));
    }

}
