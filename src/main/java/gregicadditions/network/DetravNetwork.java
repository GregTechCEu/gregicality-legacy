package gregicadditions.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;

import java.util.EnumMap;
import java.util.List;

@ChannelHandler.Sharable
public class DetravNetwork extends MessageToMessageCodec<FMLProxyPacket, DetravPacket> {

    static public DetravNetwork INSTANCE;
    private final EnumMap<Side, FMLEmbeddedChannel> mChannel;
    private final DetravPacket[] mSubChannels;

    public DetravNetwork() {
        INSTANCE = this;
        this.mChannel = NetworkRegistry.INSTANCE.newChannel("DetravScanner", this, new HandlerShared());
        this.mSubChannels = new DetravPacket[]
                {
                        new DetravProPickPacket00(),
                };
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, DetravPacket msg, List<Object> out) throws Exception {
        out.add(new FMLProxyPacket(new PacketBuffer(Unpooled.buffer().writeByte(msg.getPacketID()).writeBytes(msg.encode()).copy()), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get()));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        ByteArrayDataInput aData = ByteStreams.newDataInput(msg.payload().array());
        out.add(this.mSubChannels[aData.readByte()].decode(aData));
    }

    public void sendToPlayer(DetravPacket aPacket, EntityPlayerMP aPlayer) {
        this.mChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        this.mChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(aPlayer);
        this.mChannel.get(Side.SERVER).writeAndFlush(aPacket);
    }

    public void sendToServer(DetravPacket aPacket) {
        this.mChannel.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.mChannel.get(Side.CLIENT).writeAndFlush(aPacket);
    }

    @Sharable
    static final class HandlerShared
            extends SimpleChannelInboundHandler<DetravPacket> {
        protected void channelRead0(ChannelHandlerContext ctx, DetravPacket aPacket)
                throws Exception {
            //EntityPlayer aPlayer = GT_Values.GT.getThePlayer();
            aPacket.process();
        }
    }
}
