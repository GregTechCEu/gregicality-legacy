package gregicadditions.widgets;

import forestry.core.gui.GuiUtil;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.function.Consumer;

public class WidgetGroupProspectingMap extends Widget {
    public WidgetGroupProspectingMap(int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), new Size(width, height));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        super.drawInBackground(mouseX, mouseY, context);
//        Position position = this.getPosition();
//        Size size = this.getSize();
//        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
//
//        String text = "asdf";
//        fontRenderer.drawString(text, position.x + size.width / 2 - fontRenderer.getStringWidth(text) / 2, position.y + size.height / 2 - fontRenderer.FONT_HEIGHT / 2, 16777215);
//        GlStateManager.color(1.0F, 1.0F, 1.0F);
    }

    @Override
    public void updateScreen() {

    }

    /**
     * Draws a thin horizontal line between two points.
     */
    protected void drawHorizontalLine(int startX, int endX, int y, Color color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        Gui.drawRect(startX, y, endX + 1, y + 1, color.getRGB());
    }

    /**
     * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
     */
    protected void drawVerticalLine(int x, int startY, int endY, Color color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        Gui.drawRect(x, startY + 1, x + 1, endY, color.getRGB());
    }
}
