package gregicadditions.theoneprobe;

import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class QubitContainerInfoProvider extends CapabilityInfoProvider<IQubitContainer> {

    @Override
    protected Capability<IQubitContainer> getCapability() {
        return GregicalityCapabilities.QBIT_CAPABILITY;
    }

    @Override
    public String getID() {
        return "gtadditions:qubit_container_provider";
    }

    @Override
    protected boolean allowDisplaying(IQubitContainer capability) {
        return !capability.isOneProbeHidden();
    }

    @Override
    protected void addProbeInfo(IQubitContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        long qubitStored = capability.getQubitStored();
        long maxStorage = capability.getQubitCapacity();
        if (maxStorage == 0) return; //do not add empty max storage progress bar
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        String additionalSpacing = tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, sideHit) ? "   " : "";
        horizontalPane.text(TextStyleClass.INFO + "{*gtaddition.top.qubit_stored*} " + additionalSpacing);
        horizontalPane.progress(qubitStored, maxStorage, probeInfo.defaultProgressStyle()
                .suffix("/" + maxStorage + " qubit")
                .borderColor(0x00000000)
                .backgroundColor(0x00000000)
                .filledColor(0xFFFFE000)
                .alternateFilledColor(0xFFEED000));
    }

}
