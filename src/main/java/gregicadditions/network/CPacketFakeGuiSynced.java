package gregicadditions.network;

import gregicadditions.item.behaviors.monitorPlugin.FakeGuiPluginBehavior;
import gregicadditions.item.behaviors.monitorPlugin.MonitorPluginBaseBehavior;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.net.NetworkHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CPacketFakeGuiSynced implements NetworkHandler.Packet {
    public FakeGuiPluginBehavior fakeGui;

    public CPacketFakeGuiSynced(FakeGuiPluginBehavior fakeGui) {
        this.fakeGui = fakeGui;
    }

    public static void registerPacket(int packetId) {
        NetworkHandler.registerPacket(packetId, CPacketFakeGuiSynced.class, new NetworkHandler.PacketCodec<>(
                (packet, buf) -> {
                    MetaTileEntityMonitorScreen screen = packet.fakeGui.getScreen();
                    buf.writeInt(screen.getWorld().provider.getDimension());
                    buf.writeBlockPos(screen.getPos());
                },
                (buf) -> {
                    int dim = buf.readInt();
                    BlockPos pos = buf.readBlockPos();
                    TileEntity te = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim).getTileEntity(pos);
                    if(te instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) te).getMetaTileEntity() instanceof MetaTileEntityMonitorScreen) {
                        MonitorPluginBaseBehavior pluginBaseBehavior = ((MetaTileEntityMonitorScreen) ((MetaTileEntityHolder) te).getMetaTileEntity()).plugin;
                        if (pluginBaseBehavior instanceof FakeGuiPluginBehavior) {
                            return new CPacketFakeGuiSynced((FakeGuiPluginBehavior) pluginBaseBehavior);
                        }
                    }
                    return new CPacketFakeGuiSynced(null);
                }
        ));
    }

    public static void registerExecutor() {
        NetworkHandler.registerServerExecutor(CPacketFakeGuiSynced.class, (packet, handler) -> {
            if (packet.fakeGui != null) {
                packet.fakeGui.CreateFakeGui();
            }
        });
    }

}
