//package gregicadditions.integrations.opencomputers.driver.multi;
//
//import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
//import gregicadditions.machines.multi.MetaTileEntityBatteryTower;
//import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
//import gregtech.api.metatileentity.MetaTileEntityHolder;
//import li.cil.oc.api.machine.Arguments;
//import li.cil.oc.api.machine.Callback;
//import li.cil.oc.api.machine.Context;
//import li.cil.oc.api.network.ManagedEnvironment;
//import li.cil.oc.api.prefab.DriverSidedTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
//
//public class DriverMTEBatteryTower extends DriverSidedTileEntity { todo OC integration
//    @Override
//    public Class<?> getTileEntityClass() {
//        return MetaTileEntityBatteryTower.class;
//    }
//
//    @Override
//    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
//        TileEntity tileEntity = world.getTileEntity(pos);
//        if (tileEntity instanceof MetaTileEntityHolder) {
//            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityBatteryTower;
//        }
//        return false;
//    }
//
//    @Override
//    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
//        TileEntity tileEntity = world.getTileEntity(pos);
//        if (tileEntity instanceof MetaTileEntityHolder) {
//            return new EnvironmentMetaTileEntityBatteryTower((MetaTileEntityHolder) tileEntity,
//                    (MetaTileEntityBatteryTower) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
//            );
//        }
//        return null;
//    }
//
//    public final static class EnvironmentMetaTileEntityBatteryTower extends
//            EnvironmentMetaTileEntity<MetaTileEntityBatteryTower> {
//
//        public EnvironmentMetaTileEntityBatteryTower(MetaTileEntityHolder holder, MetaTileEntityBatteryTower tileEntity) {
//            super(holder, tileEntity, "gtce_metaTileEntityBatteryTower");
//        }
//
//        @Callback(doc = "function():boolean --  Returns the energy input per tick of machine.")
//        public Object[] getEnergyInputPerTick(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.getEnergyInputPerTick()};
//        }
//
//        @Callback(doc = "function():number --  Returns the energy output per tick of machine.")
//        public Object[] getEnergyOutputPerTick(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.getEnergyOutputPerTick()};
//        }
//
//
//    }
//}
