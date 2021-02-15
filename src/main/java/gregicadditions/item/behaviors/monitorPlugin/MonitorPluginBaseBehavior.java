package gregicadditions.item.behaviors.monitorPlugin;

import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class MonitorPluginBaseBehavior implements IItemBehaviour, ItemUIFactory {
    MetaTileEntityMonitorScreen screen;

    abstract public MonitorPluginBaseBehavior createPlugin();

    // server. nbt will be synced to client when init so.... yeah you don't need to write init
    public void writeToNBT(NBTTagCompound data) {
    }

    // server
    public void readFromNBT(NBTTagCompound data) {
    }

    public void writePluginData(int id, @NotNull Consumer<PacketBuffer> buf) {
        if (screen != null) {
            screen.writeCustomData(2, packetBuffer->{
                packetBuffer.writeInt(id);
                buf.accept(packetBuffer);
            });
        }
    }

    public void readPluginData(int id, PacketBuffer buf) {

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

    abstract public ModularUI customUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer, NBTTagCompound nbtTagCompound);

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(!world.isRemote) {
            if(hand != EnumHand.MAIN_HAND) return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
            ItemStack itemStack = player.getHeldItem(hand);
            if (getBehavior(itemStack) != null) {
                PlayerInventoryHolder holder = new PlayerInventoryHolder(player, hand);
                holder.openUI();
                return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        ItemStack itemStack = entityPlayer.getHeldItemMainhand();
        MonitorPluginBaseBehavior behavior = MonitorPluginBaseBehavior.getBehavior(itemStack);
        if (behavior != null) {
            behavior = behavior.createPlugin();
            NBTTagCompound tag = itemStack.getOrCreateSubCompound("monitor_plugin");
            behavior.readFromNBT(tag);
           return behavior.customUI(playerInventoryHolder, entityPlayer, tag);
        }
        return null;
    }

    public boolean onClickLogic(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, boolean isRight, double x, double y){
        return false;
    }
}
