package gregicadditions.item.behaviors.monitorPlugin;

import gregicadditions.item.behaviors.monitorPlugin.fakegui.FakeEntityPlayer;
import gregicadditions.item.behaviors.monitorPlugin.fakegui.FakeModularGui;
import gregicadditions.item.behaviors.monitorPlugin.fakegui.FakeModularUIContainer;
import gregicadditions.network.CPacketFakeGuiSynced;
import gregicadditions.utils.GALog;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.net.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FakeGuiPluginBehavior extends ProxyHolderPluginBehavior {

    //run-time
    @SideOnly(Side.CLIENT)
    private FakeModularGui fakeModularGui;
    private FakeModularUIContainer fakeModularUIContainer;
    private FakeEntityPlayer fakePlayer;
    private static final Method method = ObfuscationReflectionHelper.findMethod(MetaTileEntity.class, "createUI", ModularUI.class, EntityPlayer.class);
    static{
        method.setAccessible(true);
    }

    public void CreateFakeGui() {
        if (this.holder == null || this.screen == null || !this.screen.isValid()) return;
        try {
            fakePlayer = new FakeEntityPlayer(this.screen.getWorld());
            ModularUI ui = (ModularUI) method.invoke(this.holder.getMetaTileEntity(), fakePlayer);
            if (ui == null) return;
            List<Widget> widgets = new ArrayList<>();
            boolean hasPlayerInventory = false;
            for (Widget widget : ui.guiWidgets.values()) {
                if (widget instanceof SlotWidget) {
                    SlotItemHandler handler = ((SlotWidget) widget).getHandle();
                    if (handler.getItemHandler() instanceof PlayerMainInvWrapper) {
                        hasPlayerInventory = true;
                        continue;
                    }
                }
                widgets.add(widget);
            }
            ModularUI.Builder builder = new ModularUI.Builder(ui.backgroundPath, ui.getWidth(), ui.getHeight() - (hasPlayerInventory? 80:0));
            for (Widget widget : widgets) {
                builder.widget(widget);
            }
            ui = builder.build(ui.holder, ui.entityPlayer);
            fakeModularUIContainer = new FakeModularUIContainer(ui, this);
            if (this.screen.getWorld().isRemote) {
                fakeModularGui = new FakeModularGui(ui, fakeModularUIContainer);
                NetworkHandler.channel.sendToServer(new CPacketFakeGuiSynced(this).toFMLPacket());
            }
        } catch (Exception e) {
            GALog.logger.error(e);
        }
    }

    @Override
    public void onHolderChanged(MetaTileEntityHolder lastHolder) {
        if (holder == null) {
            if (this.screen.getWorld() != null && this.screen.getWorld().isRemote) {
                fakeModularGui = null;
            }
            fakeModularUIContainer = null;
            fakePlayer = null;
        } else {
            if (this.screen.getWorld().isRemote) {
                CreateFakeGui();
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (this.screen.getWorld().isRemote) {
            if (fakeModularGui != null)
                fakeModularGui.updateScreen();
        } else {
            if (fakeModularUIContainer != null)
                fakeModularUIContainer.detectAndSendChanges();
        }
    }

    @Override
    public MonitorPluginBaseBehavior createPlugin() {
        return new FakeGuiPluginBehavior();
    }

    @Override
    public void renderPlugin(float partialTicks) {
        if (fakeModularGui != null) {
            fakeModularGui.drawScreen(0, 0, partialTicks);
        }
    }

    @Override
    public void readPluginData(int id, PacketBuffer buf) {
        super.readPluginData(id, buf);
        if (id == 0 && fakeModularGui != null) {
            int windowID = buf.readVarInt();
            int widgetID = buf.readVarInt();
            fakeModularGui.handleWidgetUpdate(windowID, widgetID, buf);
        } else if (id == -1 && fakeModularUIContainer != null) {
            fakeModularUIContainer.handleSlotUpdate(buf);
        }
    }
}
