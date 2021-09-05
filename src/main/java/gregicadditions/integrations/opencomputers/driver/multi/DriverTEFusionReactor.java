package gregicadditions.integrations.opencomputers.driver.multi;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregicadditions.machines.multi.TileEntityFusionReactor;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverTEFusionReactor extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntityFusionReactor.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof TileEntityFusionReactor;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentTileEntityFusionReactor((MetaTileEntityHolder) tileEntity,
                    (TileEntityFusionReactor) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentTileEntityFusionReactor extends
            EnvironmentMetaTileEntity<TileEntityFusionReactor> {

        public EnvironmentTileEntityFusionReactor(MetaTileEntityHolder holder, TileEntityFusionReactor tileEntity) {
            super(holder, tileEntity, "gtce_tileEntityFusionReactor");
        }

        @Callback(doc = "function():number --  Returns the heat of machine.")
        public Object[] getHeat(final Context context, final Arguments args) {
            return new Object[] {tileEntity.getHeat()};
        }

    }
}
