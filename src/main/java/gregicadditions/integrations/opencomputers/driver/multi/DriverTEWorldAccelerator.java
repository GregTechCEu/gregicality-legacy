package gregicadditions.integrations.opencomputers.driver.multi;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregicadditions.machines.TileEntityWorldAccelerator;
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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class DriverTEWorldAccelerator extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return TileEntityWorldAccelerator.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof TileEntityWorldAccelerator;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentTileEntityWorldAccelerator((MetaTileEntityHolder) tileEntity,
                    (TileEntityWorldAccelerator) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentTileEntityWorldAccelerator extends
            EnvironmentMetaTileEntity<TileEntityWorldAccelerator> {

        public EnvironmentTileEntityWorldAccelerator(MetaTileEntityHolder holder, TileEntityWorldAccelerator tileEntity) {
            super(holder, tileEntity, "gtce_tileEntityWorldAccelerator");
        }

        @Callback(doc = "function():boolean --  Returns the mode of machine.")
        public Object[] isTileMode(final Context context, final Arguments args) {
            return new Object[] {tileEntity.isTileMode()};
        }

        @Callback(doc = "function(isTile:boolean) --  Sets the mode of machine.")
        public Object[] setTileMode(final Context context, final Arguments args) {
            tileEntity.setTileMode(args.checkBoolean(0));
            return new Object[] {};
        }

        @Callback(doc = "function():boolean --  Returns is working enabled.")
        public Object[] isWorkingEnabled(final Context context, final Arguments args) {
            return new Object[] {tileEntity.isWorkingEnabled()};
        }

        @Callback(doc = "function(workingEnabled:boolean):boolean -- "
                + "Sets working enabled, return last working enabled.")
        public Object[] setWorkingEnabled(final Context context, final Arguments args) {
            boolean lsatState = tileEntity.isWorkingEnabled();
            tileEntity.setWorkingEnabled(args.checkBoolean(0));
            return new Object[] {lsatState};
        }

    }
}
