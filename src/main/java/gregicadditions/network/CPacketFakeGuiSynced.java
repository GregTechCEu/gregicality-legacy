package gregicadditions.network;

import gregicadditions.item.behaviors.monitorPlugin.FakeGuiPluginBehavior;
import gregicadditions.item.behaviors.monitorPlugin.MonitorPluginBaseBehavior;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.net.NetworkHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.function.Consumer;

public class CPacketFakeGuiSynced implements NetworkHandler.Packet {
    public FakeGuiPluginBehavior fakeGui;
    public boolean isClickAction;
    public Consumer<PacketBuffer> payloadWriter;
    public PacketBuffer buf;


    public CPacketFakeGuiSynced(FakeGuiPluginBehavior fakeGui, boolean isClickAction, Consumer<PacketBuffer> payloadWriter) {
        this.fakeGui = fakeGui;
        this.isClickAction = isClickAction;
        this.payloadWriter = payloadWriter;
    }

    public CPacketFakeGuiSynced(FakeGuiPluginBehavior fakeGui) {
        this(fakeGui, false, null);
    }

    public CPacketFakeGuiSynced(FakeGuiPluginBehavior fakeGui, PacketBuffer buf) {
        this(fakeGui, true, null);
        this.buf = buf;
    }

    public static void registerPacket(int packetId) {
        NetworkHandler.registerPacket(packetId, CPacketFakeGuiSynced.class, new NetworkHandler.PacketCodec<>(
                (packet, buf) -> {
                    MetaTileEntityMonitorScreen screen = packet.fakeGui.getScreen();
                    buf.writeInt(screen.getWorld().provider.getDimension());
                    buf.writeBlockPos(screen.getPos());
                    buf.writeBoolean(packet.isClickAction);
                    if(packet.isClickAction) {
                        packet.payloadWriter.accept(buf);
                    }
                },
                (buf) -> {
                    int dim = buf.readInt();
                    BlockPos pos = buf.readBlockPos();
                    boolean isAction = buf.readBoolean();
                    TileEntity te = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim).getTileEntity(pos);
                    if(te instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) te).getMetaTileEntity() instanceof MetaTileEntityMonitorScreen) {
                        MonitorPluginBaseBehavior pluginBaseBehavior = ((MetaTileEntityMonitorScreen) ((MetaTileEntityHolder) te).getMetaTileEntity()).plugin;
                        if (pluginBaseBehavior instanceof FakeGuiPluginBehavior) {
                            if (!isAction)
                                return new CPacketFakeGuiSynced((FakeGuiPluginBehavior) pluginBaseBehavior);
                            else {
                                return new CPacketFakeGuiSynced((FakeGuiPluginBehavior) pluginBaseBehavior, buf);
                            }
                        }
                    }
                    return new CPacketFakeGuiSynced(null);
                }
        ));
    }

    public static void registerExecutor() {
        NetworkHandler.registerServerExecutor(CPacketFakeGuiSynced.class, (packet, handler) -> {
            if (packet.fakeGui != null) {
                if (packet.isClickAction) {
                    packet.fakeGui.handleClickActionSync(packet.buf);
                } else
                    packet.fakeGui.createFakeGui();
            }
        });
    }

}
