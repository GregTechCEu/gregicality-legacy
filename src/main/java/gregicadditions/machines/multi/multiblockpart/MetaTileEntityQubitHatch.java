package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.capabilities.impl.QubitContainerHandler;
import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.render.SimpleOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.PipelineUtil;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityQubitHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IQubitContainer> {

    private final boolean isExportHatch;
    private final int parallel;
    private final IQubitContainer qubitContainer;

    public MetaTileEntityQubitHatch(ResourceLocation metaTileEntityId, int tier, int parallel, boolean isExportHatch) {
        super(metaTileEntityId, tier);
        this.parallel = parallel;
        this.isExportHatch = isExportHatch;
        if (isExportHatch) {
            this.qubitContainer = QubitContainerHandler.emitterContainer(this, GAValues.QUBIT[tier] * parallel, GAValues.QUBIT[tier], parallel);
        } else {
            this.qubitContainer = QubitContainerHandler.receiverContainer(this, GAValues.QUBIT[tier] * parallel, GAValues.QUBIT[tier], parallel);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityQubitHatch(metaTileEntityId, getTier(), parallel, isExportHatch);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer = isExportHatch ? Textures.ENERGY_OUT_MULTI : Textures.ENERGY_IN_MULTI;
            renderer.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
        }
    }

    @Override
    public MultiblockAbility<IQubitContainer> getAbility() {
        return isExportHatch ? GregicAdditionsCapabilities.OUTPUT_QBIT : GregicAdditionsCapabilities.INPUT_QBIT;
    }

    @Override
    public void registerAbilities(List<IQubitContainer> abilityList) {
        abilityList.add(qubitContainer);
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
        String tierName = GTValues.VN[getTier()];

        if (isExportHatch) {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", qubitContainer.getOutputQubit(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out_till", qubitContainer.getOutputParallel()));
        } else {
            tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", qubitContainer.getInputQubit(), tierName));
            tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in_till", qubitContainer.getInputParallel()));
        }
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", qubitContainer.getQubitCapacity()));
    }
}
