package gregicadditions.item.behaviors.monitorPlugin;

import gregicadditions.renderer.RenderHelper;
import gregicadditions.widgets.WidgetARGB;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.TextFieldWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.util.Position;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class TextPluginBehavior extends MonitorPluginBaseBehavior {
    public String[] texts;
    public int[] colors;

    public void setText(int line, String text, int color) {
        if (line < 0 || line > texts.length || (texts[line].equals(text) && colors[line] == color)) return;
        this.texts[line] = text;
        this.colors[line] = color;
        writePluginData(1, packetBuffer -> {
            packetBuffer.writeInt(texts.length);
            for (String s : texts) {
                packetBuffer.writeString(s);
                packetBuffer.writeInt(color);
            }
        });
        markDirty();
    }

    @Override
    public void readPluginData(int id, PacketBuffer buf) {
        if(id == 1){
            texts = new String[buf.readInt()];
            colors = new int[texts.length];
            for (int i = 0; i < texts.length; i++) {
                texts[i] = buf.readString(100);
                colors[i] = buf.readInt();
            }
        }
    }

    @Override
    public MonitorPluginBaseBehavior createPlugin() {
        TextPluginBehavior plugin =  new TextPluginBehavior();
        plugin.texts = new String[16];
        plugin.colors = new int[16];
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
            texts[i] = data.hasKey("t" + i)? data.getString("t" + i) : "";
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
        for (int i = 0; i < texts.length; i++) {
            RenderHelper.renderText(-0.5f, -0.4625f + i / 16f, 0.002f, 1/128f, colors[i], texts[i], false);
        }
    }

    @Override
    public boolean hasUI() {
        return true;
    }

    @Override
    public ModularUI customUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer, NBTTagCompound tag) {
        WidgetGroup widgets = new WidgetGroup(new Position(20, 20));
        for (int i = 0; i < texts.length; i++) {
            int finalI = i;
            widgets.addWidget(new TextFieldWidget(0, i * 10, 100, 10, true, ()-> this.texts[finalI], (text)->{
                this.texts[finalI] = text;
                this.writeToNBT(tag);
            }).setValidator((data)->true));
            widgets.addWidget(new WidgetARGB(110, i * 10, 10, colors[i], color->{
                this.colors[finalI] = color;
                this.writeToNBT(tag);
            }));
        }
        return ModularUI.builder(GuiTextures.BOXED_BACKGROUND, 260, 210)
                .widget(widgets)
                .build(playerInventoryHolder, entityPlayer);
    }

}
