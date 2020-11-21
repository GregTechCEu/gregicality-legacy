package gregicadditions.integrations.opencomputers.driver;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverSimpleMachineMetaTileEntity extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return SimpleMachineMetaTileEntity.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof SimpleMachineMetaTileEntity;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentSimpleMachineMetaTileEntity((MetaTileEntityHolder) tileEntity,
                    (SimpleMachineMetaTileEntity) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentSimpleMachineMetaTileEntity extends EnvironmentMetaTileEntity<SimpleMachineMetaTileEntity> {

        public EnvironmentSimpleMachineMetaTileEntity(MetaTileEntityHolder holder, SimpleMachineMetaTileEntity tileEntity) {
            super(holder, tileEntity, "gtce_simpleMachineMetaTileEntity");
        }

        @Callback(doc = "function():number --  Returns the tier of machine.")
        public Object[] getTier(final Context context, final Arguments args) {
            return new Object[] {tileEntity.getTier()};
        }

        @Callback(doc = "function():boolean --  Returns is autoOutputItems enabled.")
        public Object[] isAutoOutputItems(final Context context, final Arguments args) {
            return new Object[] {tileEntity.isAutoOutputItems()};
        }

        @Callback(doc = "function(autoOutputItems:boolean):boolean -- Sets autoOutputItems enabled.")
        public Object[] setAutoOutputItems(final Context context, final Arguments args) {
            tileEntity.setAutoOutputItems(args.checkBoolean(0));
            return new Object[] {};
        }

        @Callback(doc = "function():boolean --  Returns is autoOutputFluids enabled.")
        public Object[] isAutoOutputFluids(final Context context, final Arguments args) {
            return new Object[] {tileEntity.isAutoOutputFluids()};
        }

        @Callback(doc = "function(autoOutputFluids:boolean):boolean -- Sets autoOutputFluids enabled.")
        public Object[] setAutoOutputFluids(final Context context, final Arguments args) {
            tileEntity.setAutoOutputFluids(args.checkBoolean(0));
            return new Object[] {};
        }

        @Callback(doc = "function():boolean --  Returns is allowInputFromOutputSide enabled.")
        public Object[] isAllowInputFromOutputSide(final Context context, final Arguments args) {
            return new Object[] {tileEntity.isAllowInputFromOutputSide()};
        }

        @Callback(doc = "function(allowInputFromOutputSide:boolean):boolean -- Sets allowInputFromOutputSide enabled.")
        public Object[] setAllowInputFromOutputSide(final Context context, final Arguments args) {
            tileEntity.setAllowInputFromOutputSide(args.checkBoolean(0));
            return new Object[] {};
        }

        @Callback(doc = "function():number --  Returns is outputFacing.")
        public Object[] getOutputFacing(final Context context, final Arguments args) {
            return new Object[] {tileEntity.getOutputFacing().ordinal()};
        }

        @Callback(doc = "function(side:number) -- Sets outputFacing.")
        public Object[] setOutputFacing(final Context context, final Arguments args) {
            tileEntity.setOutputFacing(EnumFacing.values()[args.checkInteger(0)]);
            return new Object[] {};
        }

    }
}
