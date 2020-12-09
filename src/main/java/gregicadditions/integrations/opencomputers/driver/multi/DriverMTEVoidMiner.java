package gregicadditions.integrations.opencomputers.driver.multi;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
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

public class DriverMTEVoidMiner extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return MetaTileEntityVoidMiner.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityVoidMiner;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentMetaTileEntityVoidMiner((MetaTileEntityHolder) tileEntity,
                    (MetaTileEntityVoidMiner) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentMetaTileEntityVoidMiner extends
            EnvironmentMetaTileEntity<MetaTileEntityVoidMiner> {

        public EnvironmentMetaTileEntityVoidMiner(MetaTileEntityHolder holder, MetaTileEntityVoidMiner tileEntity) {
            super(holder, tileEntity, "gtce_metaTileEntityVoidMiner");
        }

        @Callback(doc = "function():boolean --  Returns is over heat.")
        public Object[] isOverHeat(final Context context, final Arguments args) {
            return new Object[] {ObfuscationReflectionHelper.getPrivateValue(MetaTileEntityVoidMiner.class, tileEntity,"overheat")};
        }

        @Callback(doc = "function():number --  Returns the temperature of machine.")
        public Object[] getTemperature(final Context context, final Arguments args) {
            return new Object[] {ObfuscationReflectionHelper.getPrivateValue(MetaTileEntityVoidMiner.class, tileEntity,"temperature")};
        }

        @Callback(doc = "function():number --  Returns the max temperature of machine.")
        public Object[] getMaxTemperature(final Context context, final Arguments args) {
            return new Object[] {ObfuscationReflectionHelper.getPrivateValue(MetaTileEntityVoidMiner.class, tileEntity,"maxTemperature")};
        }

    }
}
