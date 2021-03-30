package gregicadditions.widgets.monitor;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.util.Position;
import gregtech.api.util.Size;

public class WidgetPluginConfig extends WidgetGroup {
    protected TextureArea textureArea = GuiTextures.BOXED_BACKGROUND;

    public WidgetPluginConfig setSize(int width, int height) {
        setSelfPosition(new Position(-width, 0));
        setSize(new Size(width, height));
        onPositionUpdate();
        return this;
    }

    public WidgetPluginConfig setBackGround(TextureArea textureArea){
        this.textureArea = textureArea;
        return this;
    }

    public WidgetPluginConfig widget(Widget widget){
        addWidget(widget);
        return this;
    }

    public void removePluginWidget() {
        clearAllWidgets();
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        if (widgets.size() > 0) {
            Position pos = this.getPosition();
            Size size = this.getSize();
            textureArea.draw(pos.x, pos.y, size.width, size.height);
        }
        super.drawInBackground(mouseX, mouseY, context);
    }

}
