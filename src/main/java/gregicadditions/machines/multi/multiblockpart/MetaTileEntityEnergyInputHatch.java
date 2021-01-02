package gregicadditions.machines.multi.multiblockpart;

import gregicadditions.GAValues;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityEnergyHatch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class MetaTileEntityEnergyInputHatch extends MetaTileEntityEnergyHatch {

    private final int amps;

    public MetaTileEntityEnergyInputHatch(ResourceLocation metaTileEntityId, int tier, int amps) {
        super(metaTileEntityId, tier, false);
        this.amps = amps;
        Field energyContainer = ObfuscationReflectionHelper.findField(MetaTileEntityEnergyHatch.class, "energyContainer");
        try {
            energyContainer.set(this, EnergyContainerHandler.receiverContainer(this, GAValues.V[tier] * amps * 16, GAValues.V[tier], amps));
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityEnergyInputHatch(metaTileEntityId, getTier(), amps);
    }
}
