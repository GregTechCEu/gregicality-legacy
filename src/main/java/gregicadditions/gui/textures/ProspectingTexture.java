package gregicadditions.gui.textures;

import gregicadditions.network.ProspectingPacket;
import gregtech.api.gui.resources.RenderUtil;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Objects;


public class ProspectingTexture extends AbstractTexture {

    private final ProspectingPacket packet;
    private String selected = "all";
    private int imageWidth = -1;
    private int imageHeight = -1;

    public ProspectingTexture(ProspectingPacket aPacket) {
        packet = aPacket;
    }

    private BufferedImage getImage() {
        int wh = (packet.radius * 2 + 1) * 16;
        BufferedImage image = new BufferedImage(wh, wh, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = image.getRaster();

        int playerI = packet.posX - (packet.chunkX - packet.radius) * 16 - 1; // Correct player offset
        int playerJ = packet.posZ - (packet.chunkZ - packet.radius) * 16 - 1;

        for (int i = 0; i < wh; i++){
            for (int j = 0; j < wh; j++) {
                // draw bg
                image.setRGB(i, j, Color.WHITE.getRGB());
                //draw ore
                if (packet.pType == 0 && packet.map[i][j] != null) {
                    for (String orePrefix : packet.map[i][j].values()) {
                        Material material = Objects.requireNonNull(
                                OreDictUnifier.getMaterial(OreDictUnifier.get(orePrefix))).material;
                        String name = OreDictUnifier.get(orePrefix).getDisplayName();
                        if (selected.equals("all") || selected.equals(name)) {
                            image.setRGB(i, j, material.materialRGB | 0XFF000000);
                        }
                    }
                }
                // draw player pos
                if (i == playerI || j == playerJ) {
                    raster.setSample(i, j, 0, (raster.getSample(i, j, 0) + 255) / 2);
                    raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                    raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                }
                // draw grid
                if ((i) % 16 == 0 || (j) % 16 == 0) {
                    raster.setSample(i, j, 0, raster.getSample(i, j, 0) / 2);
                    raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                    raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                }
            }
        }
        return image;
    }

    @Override
    public void loadTexture(@Nullable IResourceManager resourceManager) {
        this.deleteGlTexture();
        if (packet != null) {
            int tId = getGlTextureId();
            if (tId < 0) return;
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), getImage(), false, false);
            imageWidth = packet.getSize();
            imageHeight = packet.getSize();
        }
    }

    public void loadTexture(@Nullable IResourceManager resourceManager, String selected){
        this.selected = selected;
        loadTexture(resourceManager);
    }

    public void draw(int x, int y) {
        if (packet != null) {
            GlStateManager.bindTexture(this.getGlTextureId());
            Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
            if(packet.pType == 1) { // draw fluids in grid
                for (int cx = 0; cx < packet.radius * 2 + 1; cx++){
                    for (int cz = 0; cz < packet.radius * 2 + 1; cz++){
                        if (packet.map[cx][cz] != null) {
                            Fluid fluid = FluidRegistry.getFluid(packet.map[cx][cz].get((byte) 1));
                            String name = fluid.getLocalizedName(new FluidStack(fluid, 0));
                            if (selected.equals("all") || selected.equals(name)) {
                                //if ((cx + cz * 3) < (Integer.parseInt(packet.map[cx][cz].get((byte) 2)) + 48)) { // draw an indicator in the chunk about how large the field is at this chunk.
                                RenderUtil.drawFluidForGui(new FluidStack(fluid, 1), 1, x + cx * 16 + 1, y + cz * 16 + 1, 16, 16);
                                //}
                            }
                        }
                    }
                }
            }
        }
    }

}
