package gregicadditions.coremod.hooks;

import gregtech.api.capability.GregtechCapabilities;
import mcjty.theoneprobe.api.IProbeInfo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

@SuppressWarnings("all")
public class TheOneProbeHooks {

    public static void blockMetaTileEntities(IProbeInfo info, World world, BlockPos pos) {

        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null) {
            if (tileEntity.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)
                    && tileEntity.hasCapability(CapabilityEnergy.ENERGY, null)) {

                return;
            }
        }
    }
}
