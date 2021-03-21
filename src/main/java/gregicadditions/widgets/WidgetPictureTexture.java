package gregicadditions.widgets;

import gregicadditions.item.behaviors.monitorPlugin.OnlinePicPluginBehavior;
import gregicadditions.renderer.RenderHelper;
import gregicadditions.renderer.onlinepictexture.PictureTexture;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import net.minecraft.client.renderer.GlStateManager;

public class WidgetPictureTexture extends Widget {
    private PictureTexture texture;
    private final OnlinePicPluginBehavior plugin;

    public WidgetPictureTexture(int x, int y, int width, int height, OnlinePicPluginBehavior pluginBehavior) {
        super(new Position(x, y), new Size(width, height));
        this.plugin = pluginBehavior;
    }

    public WidgetPictureTexture(int x, int y, int width, int height) {
        this(x, y, width, height, null);
    }

    public WidgetPictureTexture setPictureTexture(PictureTexture texture){
        this.texture = texture;
        return this;
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        Position position = this.getPosition();
        Size size = this.getSize();
        RenderHelper.renderRect(position.x, position.y, size.width, size.height, 0, 0XFF000000);
        GlStateManager.pushMatrix();
        GlStateManager.translate(position.x + 0.5 * size.width, position.y + 0.5 * size.height,0);
        if (plugin != null) {
            if (plugin.texture != null && plugin.texture.hasTexture()) {
                plugin.texture.render(-0.5f * size.width, -0.5f * size.height, size.width, size.height, plugin.rotation, plugin.scaleX, plugin.scaleY, plugin.flippedX, plugin.flippedY);
            }
        }
        else if (texture != null && texture.hasTexture()) {
            texture.render(-0.5f * size.width, -0.5f * size.height, size.width, size.height, 0, 1, 1, false, false);
        }
        GlStateManager.popMatrix();
    }
}
