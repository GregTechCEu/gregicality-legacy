package gregicadditions.item.behaviors.monitorPlugin.fakegui;

import gregicadditions.renderer.RenderHelper;
import gregicadditions.utils.Tuple;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.net.PacketUIWidgetUpdate;
import gregtech.api.util.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class FakeModularGui implements IRenderContext {
    public final ModularUI modularUI;
    public FakeModularUIContainer container;

    public FakeModularGui(ModularUI modularUI, FakeModularUIContainer fakeModularUIContainer){
        this.modularUI = modularUI;
        this.container = fakeModularUIContainer;
        Position leftTop = new Position(0, 0);
        for (Widget widget : modularUI.guiWidgets.values()) {
            widget.setParentPosition(leftTop);
        }
    }

    public void updateScreen() {
        modularUI.guiWidgets.values().forEach(Widget::updateScreen);
    }

    public void handleWidgetUpdate(int windowId, int widgetId, PacketBuffer updateData) {
        if (windowId == container.windowId) {
            Widget widget = modularUI.guiWidgets.get(widgetId);
            int updateId = updateData.readVarInt();
            if (widget != null) {
                widget.readUpdateInfo(updateId, updateData);
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        float halfW = modularUI.getWidth() / 2f;
        float halfH = modularUI.getHeight() / 2f;
        float scale = 0.5f / Math.max(halfW, halfH);
        GlStateManager.translate(-scale * halfW, -scale * halfH, 0.01);
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.depthMask(false);

        drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        for (int i = 0; i < this.container.inventorySlots.size(); ++i) {
            Slot slot = this.container.inventorySlots.get(i);
            ItemStack itemstack = slot.getStack();
            if (!itemstack.isEmpty() && slot.isEnabled()) {
                net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
                GlStateManager.pushMatrix();
                GlStateManager.scale(1, 1, 0);
                GlStateManager.translate(slot.xPos - halfW, slot.yPos - halfH, 0);
                RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
                renderItem.renderItemAndEffectIntoGUI(itemstack, 0, 0);
                String count = itemstack.getCount() > 1? Integer.toString(itemstack.getCount()) : null;
                renderItem.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, itemstack, 0, 0, count);
                GlStateManager.popMatrix();
                net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            }
        }

        drawGuiContainerForegroundLayer(mouseX, mouseY);

        GlStateManager.depthMask(true);
    }

    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        modularUI.backgroundPath.draw(0, 0, modularUI.getWidth(), modularUI.getHeight());
        for (Widget widget : modularUI.guiWidgets.values()) {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            widget.drawInBackground(mouseX, mouseY, this);
            GlStateManager.popMatrix();
        }
    }

    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        for (Widget widget : modularUI.guiWidgets.values()) {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f);
            widget.drawInForeground(mouseX, mouseY);
            GlStateManager.popMatrix();
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return modularUI.guiWidgets.values().stream().anyMatch(widget -> widget.mouseClicked(mouseX, mouseY, mouseButton));
    }

}
