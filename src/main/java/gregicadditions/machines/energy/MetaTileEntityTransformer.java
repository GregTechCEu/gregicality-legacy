package gregicadditions.machines.energy;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class MetaTileEntityTransformer extends gregtech.common.metatileentities.electric.MetaTileEntityTransformer {

    private int ampsIn = 1;
    private int ampsOut = 1;

    public MetaTileEntityTransformer(ResourceLocation metaTileEntityId, int tier, int ampsIn, int ampsOut) {
        super(metaTileEntityId, tier);
        this.ampsIn = ampsIn;
        this.ampsOut = ampsOut;
        this.reinitializeEnergyContainer();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityTransformer(metaTileEntityId, getTier(), ampsIn, ampsOut);
    }

    @Override
    protected void reinitializeEnergyContainer() {
        if (ampsIn == 0 || ampsOut == 0) {
            return;
        }
        long tierVoltage = GTValues.V[getTier()];
        Field isTransformUp = ObfuscationReflectionHelper.findField(gregtech.common.metatileentities.electric.MetaTileEntityTransformer.class, "isTransformUp");
        try {
            if (isTransformUp.getBoolean(this)) {
                //storage = 1 amp high; input = tier / 4; amperage = 4; output = tier; amperage = 1
                this.energyContainer = new EnergyContainerHandler(this, tierVoltage * ampsOut * 2, tierVoltage / 4, ampsOut, tierVoltage, ampsIn);
            } else {

                //storage = 1 amp high; input = tier; amperage = 1; output = tier / 4; amperage = 4
                this.energyContainer = new EnergyContainerHandler(this, tierVoltage * ampsOut * 2, tierVoltage, ampsIn, tierVoltage / 4, ampsOut);
            }
            ((EnergyContainerHandler) this.energyContainer).setSideInputCondition(s -> s == getFrontFacing());
            ((EnergyContainerHandler) this.energyContainer).setSideOutputCondition(s -> s == getFrontFacing().getOpposite());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
