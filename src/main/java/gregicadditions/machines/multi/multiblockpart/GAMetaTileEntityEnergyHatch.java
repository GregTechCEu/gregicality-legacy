package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GAEnergyContainerHandler;
import gregicadditions.client.ClientHandler;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.render.SimpleOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.PipelineUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GAMetaTileEntityEnergyHatch extends GAMetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IEnergyContainer> {

    private final int amps;
    private final boolean isExportHatch;
    private final IEnergyContainer energyContainer;

    public GAMetaTileEntityEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amps, boolean isExportHatch) {
        super(metaTileEntityId, tier);
        this.isExportHatch = isExportHatch;
        this.amps = amps;
        if (isExportHatch) {
            this.energyContainer = GAEnergyContainerHandler.emitterContainer(this, GAValues.V[tier] * (long) amps * 128L, GAValues.V[tier], amps);
        } else {
            this.energyContainer = GAEnergyContainerHandler.receiverContainer(this, GAValues.V[tier] * (long) amps * 16L, GAValues.V[tier], amps);
        }
    }

    public boolean isExportHatch() {
        return isExportHatch;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new GAMetaTileEntityEnergyHatch(metaTileEntityId, getTier(), amps, isExportHatch);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer;
            if (amps > 4)
                renderer = isExportHatch ? ClientHandler.HIGH_ENERGY_OUT : ClientHandler.HIGH_ENERGY_IN;
            else
                renderer = isExportHatch ? Textures.ENERGY_OUT_MULTI : Textures.ENERGY_IN_MULTI;

            renderer.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier()]));
        }
    }

    @Override
    public MultiblockAbility<IEnergyContainer> getAbility() {
        return isExportHatch ? MultiblockAbility.OUTPUT_ENERGY : MultiblockAbility.INPUT_ENERGY;
    }

    @Override
    public void registerAbilities(List<IEnergyContainer> abilityList) {
        abilityList.add(energyContainer);
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
        String tierName = GAValues.VN[getTier()];

        if (isExportHatch) {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", energyContainer.getOutputVoltage(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out_till", energyContainer.getOutputAmperage()));
        } else {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in_till", energyContainer.getInputAmperage()));
        }
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
    }
}
