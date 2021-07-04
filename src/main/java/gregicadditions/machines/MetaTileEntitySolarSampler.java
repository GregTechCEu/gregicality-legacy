package gregicadditions.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.behaviors.DataStickFluidSamplerBehavior;
import gregicadditions.utils.GALog;
import gregicadditions.worldgen.DimensionChunkCoords;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.render.SimpleSidedCubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.covers.CoverSolarPanel;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;
import javax.annotation.Nonnull;

import static gregtech.common.items.MetaItems.TOOL_DATA_STICK;

public class MetaTileEntitySolarSampler extends MetaTileEntity implements IWorkable {
    private final IEnergyContainer energyContainer;

    private boolean isPaused = false;
    private boolean isActive = false;
    private final int maxProgress = 6000 * GAConfig.Extraction.timeToScanFactor / 100;
    private int progressTime = 0;

    public MetaTileEntitySolarSampler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.energyContainer = new EnergyContainerHandler(this, 32, 0, 0, 0L, 0L) {
            @Override
            public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
                return 0;
            }
        };

    }


    @Override
    public void update() {
        super.update();
        if (getWorld().isRemote) {
            return;
        }
        if (isPaused) {
            if (isActive)
                setActive(false);
            return;
        }

        if (!drainEnergy()) {
            if (isActive)
                setActive(false);
            return;
        }

        if (progressTime == 0) {
            ItemStack dataStick = importItems.extractItem(0, 1, false);
            if (dataStick.isEmpty()) {
                return;
            }
        }


        if (!isActive)
            setActive(true);


        if (++progressTime > maxProgress) {
            PumpjackHandler.OilWorldInfo oilWorldInfo = PumpjackHandler.getOilWorldInfo(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);
            DimensionChunkCoords coords = new DimensionChunkCoords(getWorld().provider.getDimension(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);
            ItemStack dataStick = TOOL_DATA_STICK.getStackForm(1);
            DataStickFluidSamplerBehavior behavior = DataStickFluidSamplerBehavior.getInstanceFor(dataStick);

            if (behavior == null) {
                GALog.logger.info("DataStickFluidSamplerBehavior is null");
                return;
            }

            behavior.setDimensionChunkCoords(dataStick, coords);
            behavior.setOilWorldInfo(dataStick, oilWorldInfo);

            ItemStack leftItem = exportItems.insertItem(0, dataStick, false);

            if (leftItem.isEmpty()) {
                progressTime = 0;
            }
            --progressTime;
        }
    }

    public boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= 32) {
            energyContainer.removeEnergy(32);
            return true;
        }
        return false;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.isItemEqualIgnoreDurability(TOOL_DATA_STICK.getStackForm(1));
            }
        };
    }


    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return false;
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntitySolarSampler(metaTileEntityId);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        IVertexOperation[] colouredPipeline = ArrayUtils.add(pipeline, new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering())));
        getBaseRenderer().render(renderState, translation, colouredPipeline);
    }

    @SideOnly(Side.CLIENT)
    private SimpleSidedCubeRenderer getBaseRenderer() {
        return Textures.VOLTAGE_CASINGS[GAValues.HV];
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.widget(new LabelWidget(6, 6, getMetaFullName()));
        builder.widget(new SlotWidget(importItems, 0, 53, 34).setBackgroundTexture(GuiTextures.SLOT));
        builder.widget(new SlotWidget(exportItems, 0, 107, 34).setBackgroundTexture(GuiTextures.SLOT));
        builder.widget(new ProgressWidget(() -> (getProgress() * 1.0) / (getMaxProgress() * 1.0), 78, 34, 20, 16, GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL));
        if (!(getCoverAtSide(EnumFacing.UP) instanceof CoverSolarPanel)) {
            builder.widget(new LabelWidget(6, 56, I18n.format("gtadditions.machine.solar_fluid_sampler.missing_solar"), 0xDD0000));
        }
        builder.bindPlayerInventory(entityPlayer.inventory);
        return builder.build(getHolder(), entityPlayer);
    }

    @Override
    public int getProgress() {
        return progressTime;
    }

    @Override
    public int getMaxProgress() {
        return maxProgress;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("isPaused", new NBTTagString(Boolean.valueOf(isPaused).toString()));
        data.setInteger("Progress", progressTime);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isPaused = Boolean.parseBoolean(data.getString("isPaused"));
        this.progressTime = data.getInteger("Progress");
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public boolean isWorkingEnabled() {
        return !isPaused;
    }

    @Override
    public void setWorkingEnabled(boolean b) {
        isPaused = !b;
        getHolder().notifyBlockUpdate();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE) {
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        } else if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }
}
