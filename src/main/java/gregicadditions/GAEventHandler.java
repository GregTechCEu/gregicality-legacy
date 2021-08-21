package gregicadditions;

import gregicadditions.machines.multi.centralmonitor.MetaTileEntityCentralMonitor;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GAEventHandler {

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().getTileEntity(event.getPos()) instanceof MetaTileEntityHolder) {
            event.setUseBlock(Event.Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntityPlayer().isCreative()) {
            TileEntity holder = event.getWorld().getTileEntity(event.getPos());
            if (holder instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) holder).getMetaTileEntity() instanceof MetaTileEntityCentralMonitor) {
                ((MetaTileEntityCentralMonitor) ((MetaTileEntityHolder) holder).getMetaTileEntity()).invalidateStructure();
            }
        }
    }
}
