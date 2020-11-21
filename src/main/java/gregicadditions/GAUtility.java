package gregicadditions;


import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.ConfigHolder;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class GAUtility {

    public static byte getTierByVoltage(long voltage) {
        byte tier = 0;

        do {
            ++tier;
            if (tier >= GAValues.V.length) {
                return (byte)Math.min(GAValues.V.length - 1, tier);
            }

            if (voltage == GAValues.V[tier]) {
                return tier;
            }
        } while(voltage >= GAValues.V[tier]);

        return (byte)Math.max(0, tier - 1);
    }

    public static void doOvervoltageExplosion(MetaTileEntity metaTileEntity, long voltage) {
        BlockPos pos = metaTileEntity.getPos();
        metaTileEntity.getWorld().setBlockToAir(pos);
        if (!metaTileEntity.getWorld().isRemote) {
            double posX = (double)pos.getX() + 0.5D;
            double posY = (double)pos.getY() + 0.5D;
            double posZ = (double)pos.getZ() + 0.5D;
            ((WorldServer)metaTileEntity.getWorld()).spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, 10, 0.2D, 0.2D, 0.2D, 0.0D);
            metaTileEntity.getWorld().createExplosion(null, posX, posY, posZ, getTierByVoltage(voltage), ConfigHolder.doExplosions);
        }

    }


}
