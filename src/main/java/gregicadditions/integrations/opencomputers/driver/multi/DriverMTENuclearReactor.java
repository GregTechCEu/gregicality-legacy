//package gregicadditions.integrations.opencomputers.driver.multi;
//
//import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
//import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
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
//
//public class DriverMTENuclearReactor extends DriverSidedTileEntity { todo OC integration
//    @Override
//    public Class<?> getTileEntityClass() {
//        return MetaTileEntityNuclearReactor.class;
//    }
//
//    @Override
//    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
//        TileEntity tileEntity = world.getTileEntity(pos);
//        if (tileEntity instanceof MetaTileEntityHolder) {
//            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityNuclearReactor;
//        }
//        return false;
//    }
//
//    @Override
//    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
//        TileEntity tileEntity = world.getTileEntity(pos);
//        if (tileEntity instanceof MetaTileEntityHolder) {
//            return new EnvironmentMetaTileEntityNuclearReactor((MetaTileEntityHolder) tileEntity,
//                    (MetaTileEntityNuclearReactor) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
//            );
//        }
//        return null;
//    }
//
//    public final static class EnvironmentMetaTileEntityNuclearReactor extends
//            EnvironmentMetaTileEntity<MetaTileEntityNuclearReactor> {
//
//        public EnvironmentMetaTileEntityNuclearReactor(MetaTileEntityHolder holder, MetaTileEntityNuclearReactor tileEntity) {
//            super(holder, tileEntity, "gtce_metaTileEntityNuclearReactor");
//        }
//
//        @Callback(doc = "function():number --  Returns the coolant needed of machine.")
//        public Object[] getCoolantNeeded(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.coolantNeeded()};
//        }
//
//        @Callback(doc = "function():number --  Returns the coolant ratio of machine.")
//        public Object[] getCoolantRatio(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.coolantRatio()};
//        }
//
//        @Callback(doc = "function():number --  Returns the recipe base heat of machine.")
//        public Object[] getRecipeBaseHeat(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.getRecipeBaseHeat()};
//        }
//
//        @Callback(doc = "function():number --  Returns the rod additional temperature of machine.")
//        public Object[] getRodAdditionalTemperature(final Context context, final Arguments args) {
//            return new Object[] {tileEntity.getRodAdditionalTemperature()};
//        }
//
//    }
//}
