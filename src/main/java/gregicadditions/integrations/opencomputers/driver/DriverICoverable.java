package gregicadditions.integrations.opencomputers.driver;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregicadditions.integrations.opencomputers.driver.environment.cover.*;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IWorkable;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.covers.*;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverICoverable extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return ICoverable.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_COVERABLE, side);
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentICoverable((MetaTileEntityHolder) tileEntity,
                    tileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_COVERABLE, null));
        }
        return null;
    }

    public final static class EnvironmentICoverable extends EnvironmentMetaTileEntity<ICoverable> {

        public EnvironmentICoverable(MetaTileEntityHolder holder, ICoverable capability) {
            super(holder, capability, "gtce_workable");
        }

        @Callback(doc = "function(side:number):table --  Returns cover of side!")
        public Object[] getCover(final Context context, final Arguments args) {
            EnumFacing side = EnumFacing.values()[args.checkInteger(0)];
            CoverBehavior coverBehavior = tileEntity.getCoverAtSide(side);
            if (coverBehavior instanceof CoverRoboticArm)
                return new Object[] {new ValueCoverRoboticArm((CoverRoboticArm) coverBehavior, side)};
            if (coverBehavior instanceof CoverConveyor)
                return new Object[] {new ValueCoverConveyor((CoverConveyor) coverBehavior, side)};
            if (coverBehavior instanceof CoverPump)
                return new Object[] {new ValueCoverPump((CoverPump) coverBehavior, side)};
            if (coverBehavior instanceof CoverFluidFilter)
                return new Object[] {new ValueCoverFluidFilter((CoverFluidFilter) coverBehavior, side)};
            if (coverBehavior instanceof CoverItemFilter)
                return new Object[] {new ValueCoverItemFilter((CoverItemFilter) coverBehavior, side)};
            if (coverBehavior != null)
                return new Object[] {new ValueCoverBehavior(coverBehavior, side)};
            return new Object[] {null};
        }

    }
}
