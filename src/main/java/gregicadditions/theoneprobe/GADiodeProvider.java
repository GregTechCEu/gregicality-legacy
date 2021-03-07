package gregicadditions.theoneprobe;

import gregicadditions.machines.energy.GAMetaTileEntityDiode;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.integration.theoneprobe.provider.ElectricContainerInfoProvider;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class GADiodeProvider extends ElectricContainerInfoProvider {

    @Override
    public String getID() {
        return "gtadditions:diode_info_provider";
    }

    @Override
    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        if (tileEntity instanceof MetaTileEntityHolder) {
            MetaTileEntity metaTileEntity = ((MetaTileEntityHolder) tileEntity).getMetaTileEntity();
            if (metaTileEntity instanceof GAMetaTileEntityDiode) {
                long inputAmperage = capability.getInputAmperage();
                long outputAmperage = capability.getOutputAmperage();
                IProbeInfo horizontalPane = probeInfo.vertical(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                String transformInfo;
                if (capability.inputsEnergy(sideHit)) {
                    transformInfo = I18n.format("gtadditions.top.diode_input", inputAmperage);
                    horizontalPane.text(TextStyleClass.INFO + transformInfo);
                } else if (capability.outputsEnergy(sideHit)) {
                    transformInfo = I18n.format("gtadditions.top.diode_output", outputAmperage);
                    horizontalPane.text(TextStyleClass.INFO + transformInfo);
                }
            }
        }
    }
}
