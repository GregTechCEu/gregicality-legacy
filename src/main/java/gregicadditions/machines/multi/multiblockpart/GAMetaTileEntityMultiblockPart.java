package gregicadditions.machines.multi.multiblockpart;

import gregicadditions.client.ClientHandler;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.util.ResourceLocation;

public abstract class GAMetaTileEntityMultiblockPart extends MetaTileEntityMultiblockPart {

    public GAMetaTileEntityMultiblockPart(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
    }

    @Override
    public ICubeRenderer getBaseTexture() {
        MultiblockControllerBase controller = this.getController();
        return controller == null ? ClientHandler.VOLTAGE_CASINGS[this.getTier()] : controller.getBaseTexture(this);
    }

}
