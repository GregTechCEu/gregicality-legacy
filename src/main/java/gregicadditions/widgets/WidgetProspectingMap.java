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

public class WidgetProspectingMap extends Widget {
    private final int chunkRadio;

    public WidgetProspectingMap(int xPosition, int yPosition, int chunkRadio) {
        super(new Position(xPosition, yPosition), new Size(17 * (chunkRadio * 2 + 1) + 1, 17 * (chunkRadio * 2 + 1) + 1));
        this.chunkRadio = chunkRadio;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        drawRect(0, 0, this.getSize().width, this.getSize().height, new Color(0xFFFFFF));
        for (int i = 0; i <= this.getSize().width; i += 17) {
            drawHorizontalLine(0, i, this.getSize().width, Color.RED);
            drawVerticalLine(i, 0, this.getSize().height, Color.BLUE);
        }
    }

    @Override
    public void updateScreen() {

    }

    protected void drawRect(int x, int y, int width, int height, Color color) {
        if (x < 0 || y < 0)
            return;
        Position position = this.getPosition();
        Size size = this.getSize();
        x = position.x + x;
        y = position.y + y;
        if (x > position.add(size).x || y > position.add(size).y)
            return;
        width = Math.min(width + x, size.width + position.x);
        height = Math.min(height + y, size.height + position.y);
        Gui.drawRect(x, y, width, height, color.getRGB());
    }

    protected void drawHorizontalLine(int x, int y, int length, Color color)
    {
        if (length < 0)
            return;
        drawRect(x, y, x + length, 1, color);
    }

    protected void drawVerticalLine(int x, int y, int length, Color color)
    {
        if (length < 0)
            return;
        drawRect(x, y, 1, y + length, color);
    }
}
