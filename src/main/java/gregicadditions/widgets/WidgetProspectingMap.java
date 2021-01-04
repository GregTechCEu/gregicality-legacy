package gregicadditions.widgets;

import gregicadditions.gui.textures.ProspectingTexture;
import gregicadditions.item.behaviors.BehaviourDetravToolElectricProPick;
import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.network.ProspectingPacket;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class WidgetProspectingMap extends Widget {
    private final int chunkRadius;
    private final PlayerInventoryHolder holder;
    private boolean hasSend = false;
    private static ProspectingTexture map;
    private WidgetOreList oreList = null;

    public WidgetProspectingMap(int xPosition, int yPosition, int chunkRadius, PlayerInventoryHolder playerInventoryHolder) {
        super(new Position(xPosition, yPosition), new Size(17 * (chunkRadius * 2 + 1) + 1, 17 * (chunkRadius * 2 + 1) + 1));
        this.chunkRadius = chunkRadius;
        this.holder = playerInventoryHolder;
    }

    public WidgetProspectingMap(int xPosition, int yPosition, int chunkRadius, PlayerInventoryHolder playerInventoryHolder, WidgetOreList widgetOreList) {
        this(xPosition,yPosition,chunkRadius,playerInventoryHolder);
        oreList = widgetOreList;
        oreList.onSelected = name->{
            if (map != null) {
                map.loadTexture(null, name);
            }
        };
    }

    @Override
    public void detectAndSendChanges() {
        EntityPlayer player = holder.player;
        World world = player.world;
        if (!world.isRemote && !hasSend) {
            ItemStack itemStack = holder.getCurrentItem();
            BehaviourDetravToolElectricProPick behavior = BehaviourDetravToolElectricProPick.getInstanceFor(itemStack);
            int data = behavior.getToolGTDetravData(itemStack);

            int cX = ((int) player.posX) >> 4;
            int cZ = ((int) player.posZ) >> 4;
            List<Chunk> chunks = new ArrayList<Chunk>();
            for (int i = -chunkRadius; i <= chunkRadius; i++)
                for (int j = -chunkRadius; j <= chunkRadius; j++)
                    if (i != -chunkRadius && i != chunkRadius && j != -chunkRadius && j != chunkRadius)
                        chunks.add(world.getChunk(cX + i, cZ + j));
            ProspectingPacket packet = new ProspectingPacket();
            packet.pType = data;
            packet.chunkX = cX;
            packet.chunkZ = cZ;
            packet.posX = (int) player.posX;
            packet.posZ = (int) player.posZ;
            packet.radius = chunkRadius - 1;
            if (data > 1) return;
            for (Chunk c : chunks) {
                for (int x = 0; x < 16; x++)
                    for (int z = 0; z < 16; z++) {
                        switch (data) {
                            case 0:
                                int ySize = c.getHeightValue(x, z);
                                for (int y = 1; y < ySize; y++) {
                                    Block block = c.getBlockState(x, y, z).getBlock();
                                    if (Miner.isOre(block)) {
                                        packet.addBlock(c.x * 16 + x, y, c.z * 16 + z, OreDictUnifier.getOreDictionaryNames(new ItemStack(block)).stream().findFirst().get());
                                    }
                                }
                                break;
                            case 1:
                                if ((x == 0) || (z == 0)) { //Skip doing the locations with the grid on them.
                                    break;
                                }
                                PumpjackHandler.OilWorldInfo fStack = PumpjackHandler.getOilWorldInfo(world, c.x, c.z);
                                if (fStack != null && fStack.getType() != null) {
                                    packet.addBlock(c.x * 16 + x, 2, c.z * 16 + z, fStack.current + "");
                                    packet.addBlock(c.x * 16 + x, 1, c.z * 16 + z, fStack.getType().fluid);
                                }
                                break;
                            default:
                                break;
                        }
                    }
            }
            writeUpdateInfo(2, packet::writePacketData);
            if (oreList != null) {
                oreList.addOres(packet);
            }
            hasSend = true;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        if(map!=null) {
            map.draw(this.getPosition().x, this.getPosition().y);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void readUpdateInfo(int id, PacketBuffer buffer) {
        super.readUpdateInfo(id, buffer);
        if (id == 2) {
            if (map != null) {
                map.deleteGlTexture();
                map = null;
            }
            ProspectingPacket packet = ProspectingPacket.readPacketData(buffer);
            map = new ProspectingTexture(packet);
            map.loadTexture(null);
            if (oreList != null) {
                oreList.addOres(packet);
            }
        }
    }
}
