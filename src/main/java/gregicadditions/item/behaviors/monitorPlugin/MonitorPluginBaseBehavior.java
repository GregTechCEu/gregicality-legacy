package gregicadditions.item.behaviors.monitorPlugin;

import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MonitorPluginBaseBehavior implements IItemBehaviour {
    MetaTileEntityMonitorScreen screen;

    abstract public MonitorPluginBaseBehavior createPlugin();

    public void writeToNBT(NBTTagCompound data) {
    }

    public void readFromNBT(NBTTagCompound data) {
    }

    protected void markDirty() {
        if (screen != null) {
            screen.pluginDirty();
        }
    }

    public static MonitorPluginBaseBehavior getBehavior(ItemStack itemStack) {
        if (itemStack.getItem() instanceof MetaItem<?>){
           MetaItem<?> item = (MetaItem<?>) itemStack.getItem();
            for (IItemBehaviour behaviour : item.getBehaviours(itemStack)) {
                if (behaviour instanceof MonitorPluginBaseBehavior) {
                    return (MonitorPluginBaseBehavior) behaviour;
                }
            }
        }
        return null;
    }

    // should call updatePlugin when the monitor valid
    public boolean shouldUpdate() {
        return false;
    }

    // update logic
    public void update() {

    }

    // render
    @SideOnly(Side.CLIENT)
    abstract public void renderPlugin(float partialTicks);

    // trigger when monitor valid modified
    public void onMonitorValid(MetaTileEntityMonitorScreen screen, boolean valid) {
        if (valid) {
           this.screen = screen;
        } else {
            this.screen = null;
        }
    }

}
