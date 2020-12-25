package gregicadditions.network;

import com.google.common.io.ByteArrayDataInput;

public abstract class DetravPacket {

    public abstract int getPacketID();

    public abstract byte[] encode();

    public abstract Object decode(ByteArrayDataInput aData);

    public abstract void process();
}
