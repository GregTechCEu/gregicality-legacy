package gregicadditions.integrations.opencomputers.driver.multi;

import gregicadditions.integrations.opencomputers.driver.environment.EnvironmentMetaTileEntity;
import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
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

import java.util.HashMap;
import java.util.Map;

public class DriverQubitMultiblockController extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return QubitRecipeMapMultiblockController.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof QubitRecipeMapMultiblockController;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentQubitRecipeMapMultiblockController((MetaTileEntityHolder) tileEntity,
                    (QubitRecipeMapMultiblockController) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentQubitRecipeMapMultiblockController extends
            EnvironmentMetaTileEntity<QubitRecipeMapMultiblockController> {

        public EnvironmentQubitRecipeMapMultiblockController(MetaTileEntityHolder holder, QubitRecipeMapMultiblockController tileEntity) {
            super(holder, tileEntity, "gtce_qubitRecipeMapMultiblockController");
        }

        @Callback(doc = "function():number --  Returns the input Qubit of machine.")
        public Object[] getInputQubitContainer(final Context context, final Arguments args) {
            Map<String, Object> map = new HashMap<>();
            map.put("stored", tileEntity.getInputQubitContainer().getQubitStored());
            map.put("capacity", tileEntity.getInputQubitContainer().getQubitCapacity());
            return new Object[] {map};
        }

        @Callback(doc = "function():number --  Returns the output Qubit of machine.")
        public Object[] getOutputQubitContainer(final Context context, final Arguments args) {
            Map<String, Object> map = new HashMap<>();
            map.put("stored", tileEntity.getOutputQubitContainer().getQubitStored());
            map.put("capacity", tileEntity.getOutputQubitContainer().getQubitCapacity());
            return new Object[] {map};
        }

    }
}
