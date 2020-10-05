package gregicadditions.machines.overrides;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAValues;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.render.Textures;
import gregtech.api.util.PipelineUtil;
import net.minecraft.util.ResourceLocation;

public class GAMetaTileEntityHull extends gregtech.common.metatileentities.electric.MetaTileEntityHull {

    public GAMetaTileEntityHull(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
    }

    @Override
    protected void reinitializeEnergyContainer() {
        long tierVoltage = GAValues.V[getTier()];
        this.energyContainer = new EnergyContainerHandler(this, tierVoltage * 16L, tierVoltage, 1L, tierVoltage, 1L);
        ((EnergyContainerHandler) this.energyContainer).setSideOutputCondition(s -> s == getFrontFacing());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.ENERGY_OUT.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GAValues.VC[getTier()]));
    }
}
