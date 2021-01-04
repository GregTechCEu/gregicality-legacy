package gregicadditions.network;

import net.minecraft.network.PacketBuffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ProspectingPacket {
    public int chunkX;
    public int chunkZ;
    public int posX;
    public int posZ;
    public int radius;
    public int pType;
    public int level = -1;
    public HashMap<Byte, String>[][] map = null;
    public Set<String> ores = null;

    public static ProspectingPacket readPacketData(PacketBuffer buffer) {
        ProspectingPacket packet = new ProspectingPacket();
        packet.pType = buffer.readInt();
        packet.level = buffer.readInt();
        packet.chunkX = buffer.readInt();
        packet.chunkZ = buffer.readInt();
        packet.posX = buffer.readInt();
        packet.posZ = buffer.readInt();
        packet.radius = buffer.readInt();
        packet.map = new HashMap[(packet.radius * 2 + 1) * 16][(packet.radius * 2 + 1) * 16];
        packet.ores = new HashSet<>();
        int aSize = (packet.radius * 2 + 1) * 16;
        int checkOut = 0;
        for (int i = 0; i < aSize; i++)
            for (int j = 0; j < aSize; j++) {
                byte kSize = buffer.readByte();
                if (kSize == 0) continue;
                packet.map[i][j] = new HashMap<>();
                for (int k = 0; k < kSize; k++) {
                    byte y = buffer.readByte();
                    String name = buffer.readString(1000);
                    packet.map[i][j].put(y, name);
                    if (packet.pType != 1 || y == 1)
                        packet.ores.add(name);
                    checkOut++;
                }
            }
        int checkOut2 = buffer.readInt();
        if (checkOut != checkOut2) return new ProspectingPacket();
        return packet;
    }

    public void writePacketData(PacketBuffer buffer) {
        buffer.writeInt(pType);
        buffer.writeInt(level);
        buffer.writeInt(chunkX);
        buffer.writeInt(chunkZ);
        buffer.writeInt(posX);
        buffer.writeInt(posZ);
        buffer.writeInt(radius);
        int aSize = (radius * 2 + 1) * 16;
        int checkOut = 0;
        for (int i = 0; i < aSize; i++)
            for (int j = 0; j < aSize; j++) {
                if (map[i][j] == null)
                    buffer.writeByte(0);
                else {
                    buffer.writeByte(map[i][j].keySet().size());
                    for (byte key : map[i][j].keySet()) {
                        buffer.writeByte(key);
                        buffer.writeString(map[i][j].get(key));
                        checkOut++;
                    }
                }
            }
        buffer.writeInt(checkOut);
    }

    public void addBlock(int x, int y, int z, String orePrefix) {
        if (map == null)
            map = new HashMap[(radius * 2 + 1) * 16][(radius * 2 + 1) * 16];
        if (ores == null)
            ores = new HashSet<>();
        int aX = x - (chunkX - radius) * 16;
        int aZ = z - (chunkZ - radius) * 16;
        if (map[aX][aZ] == null)
            map[aX][aZ] = new HashMap<>();
        map[aX][aZ].put((byte) y, orePrefix);
        if (pType != 1 || y == 1)
            ores.add(orePrefix);
    }

    public int getSize() {
        return (radius * 2 + 1) * 16;
    }

}
