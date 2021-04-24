package gregicadditions.widgets;

import gregicadditions.client.renderer.ProspectingTexture;
import gregicadditions.item.behaviors.ProspectingToolBehaviour;
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
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WidgetProspectingMap extends Widget {
    private final int chunkRadius;
    private final PlayerInventoryHolder holder;
    private static ProspectingTexture map;
    private WidgetOreList oreList = null;
    private ProspectingPacket packet = null;
    private boolean darkMode = false;

    public WidgetProspectingMap(int xPosition, int yPosition, int chunkRadius, PlayerInventoryHolder playerInventoryHolder) {
        super(new Position(xPosition, yPosition), new Size(16 * (chunkRadius * 2 - 1), 16 * (chunkRadius * 2 - 1)));
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

    public void setDarkMode(boolean mode) {
        if (darkMode != mode) {
            darkMode = mode;
            if (map != null) {
                map.loadTexture(null, darkMode? Color.darkGray.getRGB(): Color.WHITE.getRGB());
            }
        }
    }

    public boolean getDarkMode() {
        return darkMode;
    }

    @Override
    public void detectAndSendChanges() {
        EntityPlayer player = holder.player;
        World world = player.world;
        if (!world.isRemote && packet == null) {
            ItemStack itemStack = holder.getCurrentItem();
            ProspectingToolBehaviour behavior = ProspectingToolBehaviour.getInstanceFor(itemStack);
            int data = behavior.getToolGTDetravData(itemStack);

            int cX = ((int) player.posX) >> 4;
            int cZ = ((int) player.posZ) >> 4;
            List<Chunk> chunks = new ArrayList<Chunk>();
            for (int i = -chunkRadius; i <= chunkRadius; i++)
                for (int j = -chunkRadius; j <= chunkRadius; j++)
                    if (i != -chunkRadius && i != chunkRadius && j != -chunkRadius && j != chunkRadius)
                        chunks.add(world.getChunk(cX + i, cZ + j));
            packet = new ProspectingPacket(cX, cZ, (int) player.posX, (int) player.posZ, chunkRadius - 1, data);
            if (data > 1) return;
            for (Chunk c : chunks) {
                switch (data) {
                    case 0:
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                int ySize = c.getHeightValue(x, z);
                                for (int y = 1; y < ySize; y++) {
                                    Block block = c.getBlockState(x, y, z).getBlock();
                                    if (Miner.isOre(block)) {
                                        packet.addBlock(c.x * 16 + x, y, c.z * 16 + z,
                                                OreDictUnifier.getOreDictionaryNames(new ItemStack(block)).stream()
                                                        .findFirst().get());
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        PumpjackHandler.OilWorldInfo fStack = PumpjackHandler.getOilWorldInfo(world, c.x, c.z);
                        if (fStack != null && fStack.getType() != null) {
                            packet.addBlock(c.x, 2, c.z, fStack.current + "");
                            packet.addBlock(c.x, 1, c.z, fStack.getType().fluid);
                        }
                        break;
                    default:
                        break;
                }
            }
            writeUpdateInfo(2, packet::writePacketData);
            if (oreList != null) {
                oreList.addOres(packet);
            }
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
            packet = ProspectingPacket.readPacketData(buffer);
            map = new ProspectingTexture(packet);
            map.loadTexture(null);
            if (oreList != null) {
                oreList.addOres(packet);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        // draw tooltips
        if (this.isMouseOverElement(mouseX, mouseY) && packet != null && map!= null) {
            List<String> tooltips = new ArrayList<>();
            int cX = (mouseX - this.getPosition().x) / 16;
            int cZ = (mouseY - this.getPosition().y) / 16;
            if (cX >= chunkRadius * 2 - 1 || cZ >= chunkRadius * 2 - 1)
                return;
            // draw hover layer
            Gui.drawRect(cX * 16 + this.getPosition().x,
                    cZ * 16 + this.getPosition().y,
                    (cX + 1) * 16 + this.getPosition().x,
                    (cZ + 1) * 16 + this.getPosition().y,
                    new Color(0x4B6C6C6C, true).getRGB());
            if (packet.pType == 0) { // draw ore
                tooltips.add(I18n.format("metaitem.tool.prospect.tooltips.ore"));
                HashMap<String, Integer> oreInfo = new HashMap<>();
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {
                        if (packet.map[cX * 16 + i][cZ * 16 + j] != null) {
                            packet.map[cX * 16 + i][cZ * 16 + j].values().forEach(dict -> {
                                String name = OreDictUnifier.get(dict).getDisplayName();
                                if (map.getSelected().equals("all") || map.getSelected().equals(dict)) {
                                    oreInfo.put(name, oreInfo.getOrDefault(name, 0) + 1);
                                }
                            });
                        }
                    }
                }
                oreInfo.forEach((name, count)->tooltips.add(name + " --- " + count));
            } else if(packet.pType == 1){
                tooltips.add(I18n.format("metaitem.tool.prospect.tooltips.fluid"));
                if (packet.map[cX][cZ] != null) {
                    String name = FluidRegistry.getFluidStack(packet.map[cX][cZ].get((byte) 1),1).getLocalizedName();
                    if (map.getSelected().equals("all") || map.getSelected().equals(packet.map[cX][cZ].get((byte) 1))) {
                        tooltips.add(name + " --- " + packet.map[cX][cZ].get((byte) 2));
                    }
                }
            }
            this.drawHoveringText(ItemStack.EMPTY, tooltips, 300, mouseX, mouseY);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
