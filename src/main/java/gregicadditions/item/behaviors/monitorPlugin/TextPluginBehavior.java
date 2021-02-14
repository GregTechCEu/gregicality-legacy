package gregicadditions.item.behaviors.monitorPlugin;

import gregicadditions.renderer.RenderHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class TextPluginBehavior extends MonitorPluginBaseBehavior {
    private String[] texts;
    private int[] colors;

    public void setText(int line, String text, int color) {
        if (line < 0 || line > texts.length || (texts[line].equals(text) && colors[line] == color)) return;
        this.texts[line] = text;
        this.colors[line] = color;
        markDirty();
    }

    @Override
    public MonitorPluginBaseBehavior createPlugin() {
        TextPluginBehavior plugin =  new TextPluginBehavior();
        plugin.texts = new String[8];
        plugin.colors = new int[8];
        return plugin;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        for (int i = 0; i < texts.length; i++) {
            data.setString("t" + i, texts[i]);
        }
        data.setIntArray("color", colors);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        for (int i = 0; i < texts.length; i++) {
            texts[i] = data.hasKey("t" + i)? data.getString("t" + i) : "empty";
        }
        if(data.hasKey("color")) {
            colors = data.getIntArray("color");
        } else {
            Arrays.fill(colors, 0XFFFFFFFF);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderPlugin(float partialTicks) {
        RenderHelper.renderText(0, 0, 0.002f, 1/64f, colors[0], texts[0], false);
    }
}
