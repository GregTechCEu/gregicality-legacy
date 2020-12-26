package gregicadditions.network;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Objects;


public class DetravProPickPacket00 extends DetravPacket {
    public int chunkX;
    public int chunkZ;
    public int size;
    public int ptype;
    public int level = -1;
    private HashMap<Byte, String>[][] map = null;
    private HashMap<String, Integer> ores = null;

    @Override
    public int getPacketID() {
        return 0;
    }

    @Override
    public byte[] encode() {
        ByteArrayDataOutput tOut = ByteStreams.newDataOutput(1);
        tOut.writeInt(ptype);
        tOut.writeInt(level);
        tOut.writeInt(chunkX);
        tOut.writeInt(chunkZ);
        tOut.writeInt(size);
        int aSize = (size * 2 + 1) * 16;
        int checkOut = 0;
        for (int i = 0; i < aSize; i++)
            for (int j = 0; j < aSize; j++) {
                if (map[i][j] == null)
                    tOut.writeByte(0);
                else {
                    tOut.writeByte(map[i][j].keySet().size());
                    for (byte key : map[i][j].keySet()) {
                        tOut.writeByte(key);
                        tOut.writeChars(map[i][j].get(key));
                        checkOut++;
                    }
                }
            }
        tOut.writeInt(checkOut);
        return tOut.toByteArray();
    }

    @Override
    public Object decode(ByteArrayDataInput aData) {
        DetravProPickPacket00 packet = new DetravProPickPacket00();
        packet.ptype = aData.readInt();
        packet.level = aData.readInt();
        packet.chunkX = aData.readInt();
        packet.chunkZ = aData.readInt();
        packet.size = aData.readInt();
        packet.map = new HashMap[(packet.size * 2 + 1) * 16][(packet.size * 2 + 1) * 16];
        int aSize = (packet.size * 2 + 1) * 16;
        int checkOut = 0;
        for (int i = 0; i < aSize; i++)
            for (int j = 0; j < aSize; j++) {
                byte kSize = aData.readByte();
                if (kSize == 0) continue;
                packet.map[i][j] = new HashMap<>();
                for (int k = 0; k < kSize; k++) {
                    packet.map[i][j].put(aData.readByte(), aData.readLine());
                    checkOut++;
                }
            }
        int checkOut2 = aData.readInt();
        if (checkOut != checkOut2) return new DetravProPickPacket00();
        return packet;
    }

    @Override
    public void process() {
//        DetravGuiProPick.newMap(new DetravMapTexture(this));
    }

    public void addBlock(int x, int y, int z, String orePrefix) {
        if (map == null)
            map = new HashMap[(size * 2 + 1) * 16][(size * 2 + 1) * 16];
        int aX = x - (chunkX - size) * 16;
        int aZ = z - (chunkZ - size) * 16;
        if (map[aX][aZ] == null)
            map[aX][aZ] = new HashMap<>();
        map[aX][aZ].put((byte) y, orePrefix);
    }

    public BufferedImage getImage(int posX, int posZ) {
        int wh = (size * 2 + 1) * 16;
        BufferedImage image = new BufferedImage(wh, wh, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = image.getRaster();

        int playerI = posX - (chunkX - size) * 16 - 1; // Correct player offset
        int playerJ = posZ - (chunkZ - size) * 16 - 1;

        if (ores == null) ores = new HashMap<>();
        switch (ptype) {
            case 0:
                for (int i = 0; i < wh; i++)
                    for (int j = 0; j < wh; j++) {
                        if (map[i][j] == null) {
                            raster.setSample(i, j, 0, 255);
                            raster.setSample(i, j, 1, 255);
                            raster.setSample(i, j, 2, 255);
                            raster.setSample(i, j, 3, 255);
                        } else {
                            for (String orePrefix : map[i][j].values()) {
                                String name;
                                Material material = Objects.requireNonNull(OreDictUnifier.getMaterial(OreDictUnifier.get(orePrefix))).material;
                                int rgba = material.materialRGB;
                                //ores.put(GT_Ore)

                                name = OreDictUnifier.get(orePrefix).getDisplayName();

                                raster.setSample(i, j, 0, (rgba & 0xFF0000) >> 16);
                                raster.setSample(i, j, 1, (rgba & 0xFF00) >> 8);
                                raster.setSample(i, j, 2, rgba & 0xFF);
                                raster.setSample(i, j, 3, 255);
                                if (!ores.containsKey(name))
                                    ores.put(name, rgba);

                            }
                        }
                        if (playerI == i || playerJ == j) {
                            raster.setSample(i, j, 0, (raster.getSample(i, j, 0) + 255) / 2);
                            raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                            raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                        }
                        if ((i) % 16 == 0 || (j) % 16 == 0) {  // Draw grid on screen
                            raster.setSample(i, j, 0, raster.getSample(i, j, 0) / 2);
                            raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                            raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                        }
                    }
                break;
            case 1:

                String[] metas = new String[2];
                for (int i = 0; i < wh; i++)
                    for (int j = 0; j < wh; j++) {
                        if (map[i][j] == null) {
                            raster.setSample(i, j, 0, 255);
                            raster.setSample(i, j, 1, 255);
                            raster.setSample(i, j, 2, 255);
                            raster.setSample(i, j, 3, 255);
                        } else {
                            metas[0] = map[i][j].get((byte) 1);   //  fluidID
                            metas[1] = map[i][j].get((byte) 2);   //  Size of the field
                            Fluid fluid = FluidRegistry.getFluid(metas[0]);
                            String name = fluid.getLocalizedName(new FluidStack(fluid, 0));
                            int rgba = fluid.getColor();


                            if (!ores.containsKey(name))
                                ores.put(name, rgba);

                            int k = (i % 16); // Variables used to locate within a chunk.
                            int l = (j % 16);

                            if (((k + l * 16) * 3) < (Integer.getInteger(metas[1]) + 48)) { // draw an indicator in the chunk about how large the field is at this chunk.
                                raster.setSample(i, j, 0, (rgba & 0xFF0000) >> 16);
                                raster.setSample(i, j, 1, (rgba & 0xFF00) >> 8);
                                raster.setSample(i, j, 2, rgba & 0xFF);
                                raster.setSample(i, j, 3, 255);
                            } else {
                                raster.setSample(i, j, 0, 255);
                                raster.setSample(i, j, 1, 255);
                                raster.setSample(i, j, 2, 255);
                                raster.setSample(i, j, 3, 255);
                            }

                        }
                        if (playerI == i || playerJ == j) {  // Draw red line on screen
                            raster.setSample(i, j, 0, (raster.getSample(i, j, 0) + 255) / 2);
                            raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                            raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                        }
                        if ((i) % 16 == 0 || (j) % 16 == 0) {  // Draw grid on screen
                            raster.setSample(i, j, 0, raster.getSample(i, j, 0) / 2);
                            raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                            raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                        }
                    }
                break;
            default:
                break;
        }
        return image;
    }

    public HashMap<String, Integer> getOres() {
        if (ores == null) return new HashMap<>();
        return ores;
    }

    public int getSize() {
        return (size * 2 + 1) * 16;
    }
}