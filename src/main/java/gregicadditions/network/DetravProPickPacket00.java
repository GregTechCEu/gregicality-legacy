package gregicadditions.network;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregicadditions.gui.DetravGuiProPick;
import gregicadditions.gui.textures.DetravMapTexture;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_LanguageManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;


public class DetravProPickPacket00 extends DetravPacket {
    public static HashMap<Integer, short[]> fluidColors = new HashMap<>();
    public int chunkX;
    public int chunkZ;
    public int size;
    public int ptype;
    public int level = -1;
    private HashMap<Byte, Short>[][] map = null;
    private HashMap<String, Integer> ores = null;

    private static String getLocalizedNameForItem(String mDefaultLocalName, String aFormat) {
        return String.format(aFormat.replace("%s", "%temp").replace("%material", "%s"), mDefaultLocalName).replace("%temp", "%s");
    }

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
                        tOut.writeShort(map[i][j].get(key));
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
                    packet.map[i][j].put(aData.readByte(), aData.readShort());
                    checkOut++;
                }
            }
        int checkOut2 = aData.readInt();
        if (checkOut != checkOut2) return new DetravProPickPacket00();
        return packet;
    }

    @Override
    public void process() {
        DetravGuiProPick.newMap(new DetravMapTexture(this));
    }

    public void addBlock(int x, int y, int z, short metaData) {
        if (map == null)
            map = new HashMap[(size * 2 + 1) * 16][(size * 2 + 1) * 16];
        int aX = x - (chunkX - size) * 16;
        int aZ = z - (chunkZ - size) * 16;
        if (map[aX][aZ] == null)
            map[aX][aZ] = new HashMap<>();
        map[aX][aZ].put((byte) y, metaData);
    }

    public BufferedImage getImage(int posX, int posZ) {
        int wh = (size * 2 + 1) * 16;
        BufferedImage image = new BufferedImage(wh, wh, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = image.getRaster();

        int playerI = posX - (chunkX - size) * 16 - 1; // Correct player offset
        int playerJ = posZ - (chunkZ - size) * 16 - 1;

        if (ores == null) ores = new HashMap<>();
        int exception = 0;
        switch (ptype) {
            case 0:
            case 1:
                for (int i = 0; i < wh; i++)
                    for (int j = 0; j < wh; j++) {
                        if (map[i][j] == null) {
                            raster.setSample(i, j, 0, 255);
                            raster.setSample(i, j, 1, 255);
                            raster.setSample(i, j, 2, 255);
                            raster.setSample(i, j, 3, 255);
                        } else {
                            for (short meta : map[i][j].values()) {
                                String name;
                                short[] rgba;
                                Materials tMaterial = null;
                                try {
                                    if (meta < 7000 || meta > 7500) {
                                        tMaterial = GregTech_API.sGeneratedMaterials[meta % 1000];
                                    }
                                } catch (Exception e) {
                                    tMaterial = null;
                                }
                                if ((meta > 0 && meta < 7000) || meta > 7500) {
                                    if (tMaterial == null) {
                                        exception++;
                                        continue;
                                    }
                                    rgba = tMaterial.getRGBA();
                                    //ores.put(GT_Ore)

                                    name = getLocalizedNameForItem(tMaterial.mDefaultLocalName, GT_LanguageManager.getTranslation("gt.blockores." + meta + ".name"));

                                    raster.setSample(i, j, 0, rgba[0]);
                                    raster.setSample(i, j, 1, rgba[1]);
                                    raster.setSample(i, j, 2, rgba[2]);
                                    raster.setSample(i, j, 3, 255);
                                    if (!ores.containsKey(name))
                                        ores.put(name, ((rgba[0] & 0xFF) << 16) + ((rgba[1] & 0xFF) << 8) + ((rgba[2] & 0xFF)));
                                }
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
            case 2:

                short[] metas = new short[2];
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
                            String name = null;
                            short[] rgba = null;

                            rgba = fluidColors.get(((int) metas[0]));
                            if (rgba == null) {
                                DetravScannerMod.proxy.sendPlayerExeption("Unknown fluid ID = " + metas[0] + " Please add to DetravProPickPacket00.java!");
                                rgba = new short[]{0x00, 0xff, 0x00};
                            }

                            name = FluidRegistry.getFluid(metas[0]).getLocalizedName(new FluidStack(FluidRegistry.getFluid(metas[0]), 0));
                            if (name == null) {
                                name = "Unknown Fluid name!";
                            }

                            if (!ores.containsKey(name))
                                ores.put(name, ((rgba[0] & 0xFF) << 16) + ((rgba[1] & 0xFF) << 8) + ((rgba[2] & 0xFF)));

                            int k = (i % 16); // Variables used to locate within a chunk.
                            int l = (j % 16);

                            if (((k + l * 16) * 3) < (metas[1] + 48)) { // draw an indicator in the chunk about how large the field is at this chunk.
                                raster.setSample(i, j, 0, rgba[0]);
                                raster.setSample(i, j, 1, rgba[1]);
                                raster.setSample(i, j, 2, rgba[2]);
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
            case 3:
                ores.put("Pollution", ((0 & 0xFF) << 16) + ((0 & 0xFF) << 8) + ((0 & 0xFF)));
                for (int i = 0; i < wh; i++)
                    for (int j = 0; j < wh; j++) {
                        if (map[i][j] == null) {
                            raster.setSample(i, j, 0, 255);
                            raster.setSample(i, j, 1, 255);
                            raster.setSample(i, j, 2, 255);
                            raster.setSample(i, j, 3, 255);
                        } else {
                            for (short meta : map[i][j].values()) {
                                raster.setSample(i, j, 0, meta);
                                raster.setSample(i, j, 1, meta);
                                raster.setSample(i, j, 2, meta);
                                raster.setSample(i, j, 3, 255);
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
            default:
                DetravScannerMod.proxy.sendPlayerExeption("Not been realized YET!");
                break;
        }
        if (exception > 0)
            DetravScannerMod.proxy.sendPlayerExeption("null matertial exception: " + exception);
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