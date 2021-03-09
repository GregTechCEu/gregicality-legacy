package gregicadditions.theoneprobe;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.machines.energy.GAMetaTileEntityTransformer;
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

public class GATransformerProvider extends ElectricContainerInfoProvider {

    @Override
    public String getID () {
        return "gtadditions:transformer_info_provider";
    }

    @Override
    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        if (tileEntity instanceof MetaTileEntityHolder) {
            MetaTileEntity metaTileEntity = ((MetaTileEntityHolder) tileEntity).getMetaTileEntity();
            if (metaTileEntity instanceof GAMetaTileEntityTransformer) {
                GAMetaTileEntityTransformer mteTransformer = (GAMetaTileEntityTransformer)metaTileEntity;
                String inputVoltageN = GAValues.VN[GAUtility.getTierByVoltage(capability.getInputVoltage())];
                String outputVoltageN = GAValues.VN[GAUtility.getTierByVoltage(capability.getOutputVoltage())];
                long inputAmperage = capability.getInputAmperage();
                long outputAmperage = capability.getOutputAmperage();
                IProbeInfo horizontalPane = probeInfo.vertical(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                String transformInfo;

                // Step Up/Step Down line
                String transformUpKey = "gregtech.top.transform_up";
                String transformDownKey = "gregtech.top.transform_down";
                if (mteTransformer.isInverted()) {
                    transformInfo = I18n.format(transformUpKey,
                            inputVoltageN, inputAmperage, outputVoltageN, outputAmperage);
                } else {
                    transformInfo = I18n.format(transformDownKey,
                            inputVoltageN, inputAmperage, outputVoltageN, outputAmperage);
                }

                // Gets hit if our GT version is below 1.11.1
                if (transformInfo.equals(transformUpKey)
                        || transformInfo.equals(transformDownKey)) {
                    return;
                }
                horizontalPane.text(TextStyleClass.INFO + transformInfo);

                // Input/Output side line
                horizontalPane = probeInfo.vertical(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
                if (capability.inputsEnergy(sideHit)) {
                    transformInfo = I18n.format("gregtech.top.transform_input", inputVoltageN, inputAmperage);
                    horizontalPane.text(TextStyleClass.INFO + transformInfo);
                } else if (capability.outputsEnergy(sideHit)) {
                    transformInfo = I18n.format("gregtech.top.transform_output", outputVoltageN, outputAmperage);
                    horizontalPane.text(TextStyleClass.INFO + transformInfo);
                }
            }
        }
    }
}
