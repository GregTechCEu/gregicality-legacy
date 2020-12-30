package gregicadditions.item.behaviors;

import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.network.DetravProPickPacket00;
import gregicadditions.widgets.WidgetGroupProspectingMap;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.DynamicLabelWidget;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wital_000 on 19.03.2016.
 */
public class BehaviourDetravToolElectricProPick implements IItemBehaviour, ItemUIFactory {

    public static BehaviourDetravToolElectricProPick getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        } else {
            MetaItem<?> metaItem = (MetaItem) itemStack.getItem();
            MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
            if (valueItem == null) {
                return null;
            } else {
                ItemUIFactory itemUIFactory = valueItem.getUIManager();
                return !(itemUIFactory instanceof BehaviourDetravToolElectricProPick) ? null : (BehaviourDetravToolElectricProPick) itemUIFactory;
            }
        }
    }

    public int getToolGTDetravData(ItemStack itemStack) {
        NBTTagCompound compound = this.getOrCreatePartStatsTag(itemStack);
        return compound != null && compound.hasKey("Data", 99) ? compound.getInteger("Data") : 0;
    }

    public void setToolGTDetravData(ItemStack itemStack, int data) {
        NBTTagCompound compound = this.getOrCreatePartStatsTag(itemStack);
        compound.setInteger("Data", data);
    }

    protected NBTTagCompound getOrCreatePartStatsTag(ItemStack itemStack) {
        return itemStack.getOrCreateSubCompound("GT.Detrav");
    }

    static final String[] foundTexts = new String[]{
            "Found nothing of interest",        //0
            " traces.",                 //1-9
            " small sample.",         //10-29
            " medium sample.",        //30-59
            " large sample.",         //60-99
            " very large sample.",    //100-**
            "Found "
    };

    static final String[] DISTANCETEXTS = new String[]{
            " next to you,",     // 0 chunks away
            " close to you,",    // 1-2 chunks aways
            " at medium range,", // 3 - 5 chunks away
            " at long range,",   // 6 -8 chunks away
            " far away,",        // 9 + chunks away
    };

    static final int[] DISTANCEINTS = new int[]{
            0,
            4,
            25,
            64,
    };
    int distTextIndex;

    HashMap<String, Integer> ores;
    int badluck;

    protected final int costs;

    public BehaviourDetravToolElectricProPick(int costs) {
        this.costs = costs;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        BehaviourDetravToolElectricProPick behavior = getInstanceFor(itemStack);
        if (!world.isRemote) {
            int data = behavior.getToolGTDetravData(itemStack);
            if (player.isSneaking()) {
                data++;
                if (data > 1) data = 0;
                switch (data) {
                    case 0:
                        player.sendMessage(new TextComponentString("Set Mode: Ore, Any Rock Block"));
                        break;
                    case 1:
                        player.sendMessage(new TextComponentString("Set Mode: Oil, Any Block"));
                        break;
                    default:
                        player.sendMessage(new TextComponentString("Set Mode: ERROR"));
                        break;
                }
                behavior.setToolGTDetravData(itemStack, data);
                return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
            }


            //aPlayer.openGui();
            //aWorld.getChunkFromBlockCoords()
            int cX = ((int) player.posX) >> 4;
            int cZ = ((int) player.posZ) >> 4;
//            int size = aItem.getHarvestLevel(aStack, "") + 1;
            int size = 4;
            List<Chunk> chunks = new ArrayList<Chunk>();
            //aPlayer.addChatMessage(new ChatComponentText("Scanning Begin, wait!"));
            //DetravProPickPacket00 packet = new DetravProPickPacket00();
            for (int i = -size; i <= size; i++)
                for (int j = -size; j <= size; j++)
                    if (i != -size && i != size && j != -size && j != size)
                        chunks.add(world.getChunk(cX + i, cZ + j));
            size = size - 1;
            //c.gene
            DetravProPickPacket00 packet = new DetravProPickPacket00();
            packet.ptype = (int) data;
            packet.chunkX = cX;
            packet.chunkZ = cZ;
            packet.size = size;
            for (Chunk c : chunks) {
                for (int x = 0; x < 16; x++)
                    for (int z = 0; z < 16; z++) {
                        int ySize = c.getHeightValue(x, z);//(int)aPlayer.posY;//c.getHeightValue(x, z);
                        for (int y = 1; y < ySize; y++) {
                            switch (data) {
                                case 0:
                                    Block block = c.getBlockState(x, y, z).getBlock();
                                    if (Miner.isOre(block)) {
                                        packet.addBlock(c.x * 16 + x, y, c.z * 16 + z, OreDictUnifier.getOreDictionaryNames(new ItemStack(block)).stream().findFirst().get());
                                    }
                                    break;
                                case 1:
                                    if ((x == 0) || (z == 0)) { //Skip doing the locations with the grid on them.
                                        break;
                                    }
                                    PumpjackHandler.OilWorldInfo fStack = PumpjackHandler.getOilWorldInfo(world, c.x * 16 + x, c.z * 16 + z);
                                    if (fStack != null) {
                                        packet.addBlock(c.x * 16 + x, 2, c.z * 16 + z, fStack.current + "");
                                        packet.addBlock(c.x * 16 + x, 1, c.z * 16 + z, fStack.getType().fluid);
                                    }
                                    break;

                            }
                            if (data > 1)
                                break;
                        }
                    }
            }

            PlayerInventoryHolder holder = new PlayerInventoryHolder(player, hand);
            holder.openUI();
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        return ModularUI.builder(
                GuiTextures.BOXED_BACKGROUND, 400, 300).label(9, 8, "Prospecting Tool (Unlocalized)")
                .widget(new WidgetGroupProspectingMap(10, 10, 200, 100))
                .build(playerInventoryHolder, entityPlayer);
    }
}
