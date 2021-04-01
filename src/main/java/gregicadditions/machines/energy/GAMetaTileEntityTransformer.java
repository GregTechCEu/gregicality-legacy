package gregicadditions.machines.energy;


import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GAEnergyContainerHandler;
import gregicadditions.client.ClientHandler;
import gregicadditions.machines.overrides.GATieredMetaTileEntity;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.tool.ISoftHammerItem;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.render.Textures;
import gregtech.api.util.PipelineUtil;
import gregtech.common.tools.DamageValues;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GAMetaTileEntityTransformer extends GATieredMetaTileEntity {

    private boolean isTransformUp;
    private int ampsIn = 1;
    private int ampsOut = 1;

    public GAMetaTileEntityTransformer(ResourceLocation metaTileEntityId, int tier, int ampsIn, int ampsOut) {
        super(metaTileEntityId, tier);
        this.ampsIn = ampsIn;
        this.ampsOut = ampsOut;
        reinitializeEnergyContainer();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new GAMetaTileEntityTransformer(metaTileEntityId, getTier(), getAmpsIn(), getAmpsOut());
    }

    public int getAmpsIn() {
        return ampsIn;
    }

    public int getAmpsOut() {
        return ampsOut;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("Inverted", isTransformUp);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isTransformUp = data.getBoolean("Inverted");
        reinitializeEnergyContainer();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isTransformUp);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isTransformUp = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 100) {
            this.isTransformUp = buf.readBoolean();
        }
    }

    public boolean isInverted() {
        return isTransformUp;
    }

    public void setTransformUp(boolean inverted) {
        isTransformUp = inverted;
        if (!getWorld().isRemote) {
            reinitializeEnergyContainer();
            writeCustomData(100, b -> b.writeBoolean(isTransformUp));
            getHolder().notifyBlockUpdate();
            markDirty();
        }
    }

    @Override
    protected void reinitializeEnergyContainer() {
        if (ampsIn == 0 || ampsOut == 0) {
            return;
        }
        long tierVoltage = GAValues.V[getTier()];
        if (isTransformUp) {
            //storage = 1 amp high; input = tier / 4; amperage = 4; output = tier; amperage = 1
            this.energyContainer = new GAEnergyContainerHandler(this, tierVoltage * ampsOut * 2, tierVoltage / 4, ampsOut, tierVoltage, ampsIn);
        } else {
            //storage = 1 amp high; input = tier; amperage = 1; output = tier / 4; amperage = 4
            this.energyContainer = new GAEnergyContainerHandler(this, tierVoltage * ampsOut * 2, tierVoltage, ampsIn, tierVoltage / 4, ampsOut);
        }
        ((GAEnergyContainerHandler) this.energyContainer).setSideInputCondition(s -> s == getFrontFacing());
        ((GAEnergyContainerHandler) this.energyContainer).setSideOutputCondition(s -> s == getFrontFacing().getOpposite());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (isTransformUp) {
            Textures.ENERGY_OUT_MULTI.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier() - 1]));
            ClientHandler.HIGH_ENERGY_IN.renderSided(getFrontFacing().getOpposite(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier()]));
        } else {
            Textures.ENERGY_IN_MULTI.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier()]));
            ClientHandler.HIGH_ENERGY_OUT.renderSided(getFrontFacing().getOpposite(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier() - 1]));
        }
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing) {
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        ItemStack itemStack = playerIn.getHeldItem(hand);
        if(!itemStack.isEmpty() && itemStack.hasCapability(GregtechCapabilities.CAPABILITY_MALLET, null)) {
            ISoftHammerItem softHammerItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_MALLET, null);

            if (getWorld().isRemote) {
                return true;
            }
            if(!softHammerItem.damageItem(DamageValues.DAMAGE_FOR_SOFT_HAMMER, false)) {
                return false;
            }

            if (isTransformUp) {
                setTransformUp(false);
                playerIn.sendMessage(new TextComponentTranslation("gregtech.machine.transformer.message_transform_down",
                        energyContainer.getInputVoltage(), energyContainer.getInputAmperage(), energyContainer.getOutputVoltage(), energyContainer.getOutputAmperage()));
            } else {
                setTransformUp(true);
                playerIn.sendMessage(new TextComponentTranslation("gregtech.machine.transformer.message_transform_up",
                        energyContainer.getInputVoltage(), energyContainer.getInputAmperage(), energyContainer.getOutputVoltage(), energyContainer.getOutputAmperage()));
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        String lowerTierName = GAValues.VN[getTier() - 1];
        String higherTierName = GAValues.VN[getTier()];
        long lowerVoltage = energyContainer.getOutputVoltage();
        long higherVoltage = energyContainer.getInputVoltage();
        long lowerAmperage = energyContainer.getInputAmperage();
        long higherAmperage = energyContainer.getOutputAmperage();

        tooltip.add(I18n.format("gregtech.machine.transformer.tooltip_tool_usage"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
        tooltip.add(I18n.format("gregtech.machine.transformer.tooltip_transform_down"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", higherVoltage, higherTierName));
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", lowerVoltage, lowerTierName));
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in", lowerAmperage));
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out", higherAmperage));
        tooltip.add(I18n.format("gregtech.machine.transformer.tooltip_transform_up"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", lowerVoltage, lowerTierName));
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", higherVoltage, higherTierName));
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in", higherAmperage));
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out", lowerAmperage));
    }
}

