package gregicadditions.gui.textures;

import gregicadditions.network.DetravProPickPacket00;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by wital_000 on 21.03.2016.
 */
public class DetravMapTexture extends AbstractTexture {

    private final DetravProPickPacket00 packet;

    public DetravMapTexture(DetravProPickPacket00 aPacket) {
        packet = aPacket;
    }

    public int width = -1;
    public int height = -1;
    public HashMap<String, Integer> ores = null;

    @Override
    public void loadTexture(IResourceManager resourceManager) {
        this.deleteGlTexture();
        if (packet != null) {
            int tId = getGlTextureId();
            if (tId < 0) return;
            BufferedImage bufferedimage = packet.getImage((int) Minecraft.getMinecraft().player.posX, (int) Minecraft.getMinecraft().player.posZ);
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, false, false);
            width = packet.getSize();
            height = packet.getSize();
            ores = packet.getOres();
        }
        //GL11.glDrawPixels();
    }

    public int glBindTexture() {
        if (this.glTextureId < 0) return this.glTextureId;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.getGlTextureId());
        return this.glTextureId;
    }

    public void draw(int x, int y) {
        float f = 1F / (float) width;
        float f1 = 1F / (float) height;
        int u = 0;
        int v = 0;
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder builder = tess.getBuffer();
        builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        builder.normal(x, y + height, 0).tex((float) (u) * f, (float) (v + height) * f1).endVertex();
        tess.draw();
        builder.normal((x + width), (y + height), 0).tex((float) (u + width) * f, (float) (v + height) * f1);
        tess.draw();
        builder.normal((x + width), (y), 0).tex((float) (u + width) * f, (float) (v) * f1);
        tess.draw();
        builder.normal((x), (y), 0).tex((float) (u) * f, (float) (v) * f1);
        tess.draw();
        builder.finishDrawing();
    }

}
