package gregicadditions.item.behaviors;

import gregicadditions.item.DetravMetaGeneratedTool01;
import gregicadditions.item.GAMetaItems;
import gregicadditions.network.DetravProPickPacket00;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.items.metaitem.stats.IItemDurabilityManager;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.common.GT_UndergroundOil;
import gregtech.common.blocks.GT_Block_Ores_Abstract;
import gregtech.common.blocks.GT_TileEntity_Ores;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import gregtech.common.tools.ToolBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SplittableRandom;

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
                ItemUIFactory durabilityManager = valueItem.getUIManager();
                return !(durabilityManager instanceof BehaviourDetravToolElectricProPick) ? null : (BehaviourDetravToolElectricProPick) durabilityManager;
            }
        }
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
        if (!world.isRemote) {
            int data = GAMetaItems.INSTANCE.getToolGTDetravData(itemStack).intValue();
            if (player.isSneaking()) {
                data++;
                if (data > 3) data = 0;
                switch (data) {
                    case 0:
                        player.sendMessage(new TextComponentString("Set Mode: Ore, Any Rock Block"));
                        break;
                    case 1:
                        player.sendMessage(new TextComponentString("Set Mode: Ore (with small), Any Rock Block"));
                        break;
                    case 2:
                        player.sendMessage(new TextComponentString("Set Mode: Oil, Any Block"));
                        break;
                    case 3:
                        player.sendMessage(new TextComponentString("Set Mode: Pollution, Any Block"));
                        break;
                    default:
                        player.sendMessage(new TextComponentString("Set Mode: ERROR"));
                        break;
                }
                DetravMetaGeneratedTool01.INSTANCE.setToolGTDetravData(itemStack, (long) data);
                return ActionResult.newResult(EnumActionResult.PASS, itemStack);
            }


            //aPlayer.openGui();
            DetravMetaGeneratedTool01 tool = (DetravMetaGeneratedTool01) aItem;
            //aWorld.getChunkFromBlockCoords()
            int cX = ((int) player.posX) >> 4;
            int cZ = ((int) player.posZ) >> 4;
            int size = aItem.getHarvestLevel(aStack, "") + 1;
            List<Chunk> chunks = new ArrayList<Chunk>();
            //aPlayer.addChatMessage(new ChatComponentText("Scanning Begin, wait!"));
            //DetravProPickPacket00 packet = new DetravProPickPacket00();
            for (int i = -size; i <= size; i++)
                for (int j = -size; j <= size; j++)
                    if (i != -size && i != size && j != -size && j != size)
                        chunks.add(world.getChunkFromChunkCoords(cX + i, cZ + j));
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
                                case 1:
                                    Block tBlock = c.getBlock(x, y, z);
                                    short tMetaID = (short) c.getBlockMetadata(x, y, z);
                                    if (tBlock instanceof GT_Block_Ores_Abstract) {
                                        TileEntity tTileEntity = c.getTileEntityUnsafe(x, y, z);
                                        if ((tTileEntity != null)
                                                && (tTileEntity instanceof GT_TileEntity_Ores)
                                                && ((GT_TileEntity_Ores) tTileEntity).mNatural == true) {
                                            tMetaID = (short) ((GT_TileEntity_Ores) tTileEntity).getMetaData();
                                            try {

                                                String name = GT_LanguageManager.getTranslation(
                                                        tBlock.getUnlocalizedName() + "." + tMetaID + ".name");
                                                if (name.startsWith("Small")) if (data != 1) continue;
                                                packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, tMetaID);
                                            } catch (Exception e) {
                                                String name = tBlock.getUnlocalizedName() + ".";
                                                if (name.contains(".small.")) if (data != 1) continue;
                                                packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, tMetaID);
                                            }
                                        }
                                    } else if (data == 1) {
                                        ItemData tAssotiation = GT_OreDictUnificator.getAssociation(new ItemStack(tBlock, 1, tMetaID));
                                        if ((tAssotiation != null) && (tAssotiation.mPrefix.toString().startsWith("ore"))) {
                                            packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, (short) tAssotiation.mMaterial.mMaterial.mMetaItemSubID);
                                        }
                                    }
                                    break;
                                case 2:
                                    if ((x == 0) || (z == 0)) { //Skip doing the locations with the grid on them.
                                        break;
                                    }
                                    FluidStack fStack = GT_UndergroundOil.undergroundOil(aWorld.getChunkFromBlockCoords(c.xPosition * 16 + x, c.zPosition * 16 + z), -1);
                                    if (fStack.amount > 0) {
                                        packet.addBlock(c.xPosition * 16 + x, 2, c.zPosition * 16 + z, (short) fStack.amount);
                                        packet.addBlock(c.xPosition * 16 + x, 1, c.zPosition * 16 + z, (short) fStack.getFluidID());
                                    }
                                    break;
                                case 3:
                                    float polution = (float) getPolution(aWorld, c.xPosition * 16 + x, c.zPosition * 16 + z);
                                    polution /= 2000000;
                                    polution *= -0xFF;
                                    if (polution > 0xFF)
                                        polution = 0xFF;
                                    polution = 0xFF - polution;
                                    packet.addBlock(c.xPosition * 16 + x, 1, c.zPosition * 16 + z, (short) polution);
                                    break;
                            }
                            if (data > 1)
                                break;
                        }
                    }
            }
            packet.level = ((DetravMetaGeneratedTool01) aItem).getHarvestLevel(aStack, "");
            DetravNetwork.INSTANCE.sendToPlayer(packet, (EntityPlayerMP) aPlayer);
            if (!aPlayer.capabilities.isCreativeMode)
                tool.doDamage(aStack, this.mCosts * chunks.size());
        }
        return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
    }

    void addChatMassageByValue(EntityPlayer aPlayer, int value, String name) {
        if (value < 0) {
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[6] + name));
        } else if (value < 1) {
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[0]));
        } else
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[6] + name + " " + value));
    }

    public boolean onItemUse(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
        long data = DetravMetaGeneratedTool01.INSTANCE.getToolGTDetravData(aStack);
        if (data < 2) {
            if (aWorld.getBlock(aX, aY, aZ) == Blocks.bedrock) {
                if (!aWorld.isRemote) {
                    FluidStack fStack = GT_UndergroundOil.undergroundOil(aWorld.getChunkFromBlockCoords(aX, aZ), -1);
                    addChatMassageByValue(aPlayer, fStack.amount, fStack.getLocalizedName());
                    if (!aPlayer.capabilities.isCreativeMode)
                        ((DetravMetaGeneratedTool01) aItem).doDamage(aStack, this.mCosts);
                }
                return true;
            } else {
                //if (aWorld.getBlock(aX, aY, aZ).getMaterial() == Material.rock || aWorld.getBlock(aX, aY, aZ) == GregTech_API.sBlockOres1) {
                if (!aWorld.isRemote) {
                    prospectSingleChunk((DetravMetaGeneratedTool01) aItem, aStack, aPlayer, aWorld, aX, aY, aZ);
                }
                return true;
            }
            //}
            //return false;
        }
        if (data < 3)
            if (!aWorld.isRemote) {
                FluidStack fStack = GT_UndergroundOil.undergroundOil(aWorld.getChunkFromBlockCoords(aX, aZ), -1);
                addChatMassageByValue(aPlayer, fStack.amount, fStack.getLocalizedName());
                if (!aPlayer.capabilities.isCreativeMode)
                    ((DetravMetaGeneratedTool01) aItem).doDamage(aStack, this.mCosts);
                return true;
            }
        if (!aWorld.isRemote) {
            int polution = getPolution(aWorld, aX, aZ);
            addChatMassageByValue(aPlayer, polution, "Pollution");
        }
        return true;
    }

    protected void prospectChunks(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, SplittableRandom aRandom, int chance) {
        int bX = aX;
        int bZ = aZ;

        badluck = 0;
        ores = new HashMap<String, Integer>();

        int range = ((DetravMetaGeneratedTool01) aItem).getHarvestLevel(aStack, "") / 2 + (aStack.getItemDamage() / 4);
        if ((range % 2) == 0) {
            range += 1;   // kinda not needed here, divide takes it out, but we put it back in with the range+1 in the loop
        }
        range = range / 2; // Convert range from diameter to radius

        aPlayer.sendMessage(new TextComponentString("Prospecting at (" + bX + ", " + bZ + ")").setStyle(new Style().setColor(TextFormatting.WHITE)));
        for (int x = -(range); x < (range + 1); ++x) {
            aX = bX + (x * 16);
            for (int z = -(range); z < (range + 1); ++z) {

                aZ = bZ + (z * 16);
                int dist = x * x + z * z;

                for (distTextIndex = 0; distTextIndex < DISTANCEINTS.length; distTextIndex++) {
                    if (dist <= DISTANCEINTS[distTextIndex]) {
                        break;
                    }
                }
                if (DetravScannerMod.DEBUGBUILD)
                    aPlayer.sendMessage(new TextComponentString("Chunk at " + aX + "|" + aZ + " to " + (aX + 16) + "|" + (aZ + 16) + DISTANCETEXTS[distTextIndex]).setStyle(new Style().setColor(TextFormatting.WHITE)));
                processOreProspecting((DetravMetaGeneratedTool01) aItem, aStack, aPlayer, aWorld.getChunkFromBlockCoords(aX, aZ), aWorld.getTileEntity(aX, aY, aZ), GT_OreDictUnificator.getAssociation(new ItemStack(aWorld.getBlock(aX, aY, aZ), 1, aWorld.getBlockMetadata(aX, aY, aZ))), aRandom, chance);
            }
        }

        for (String key : ores.keySet()) {
            int value = ores.get(key);
            addChatMassageByValue(aPlayer, value, key);
        }

        if (badluck == 0) {
            aPlayer.sendMessage(new TextComponentString("All chunks scanned successfully!").setStyle(new Style().setColor(TextFormatting.WHITE)));
        } else {
            aPlayer.sendMessage(new TextComponentString("Failed on " + badluck + " chunks. Better luck next time!").setStyle(new Style().setColor(TextFormatting.WHITE)));
        }
    }

    // Used by Electric scanner when scanning the chunk whacked by the scanner. 100% chance find rate
    protected void prospectSingleChunk(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
        ores = new HashMap<String, Integer>();
        aPlayer.sendMessage(new TextComponentString("Prospecting at(" + aX + ", " + aZ + ")").setStyle(new Style().setColor(TextFormatting.GOLD)));
        processOreProspecting((DetravMetaGeneratedTool01) aItem, aStack, aPlayer, aWorld.getChunkFromBlockCoords(aX, aZ), aWorld.getTileEntity(aX, aY, aZ), GT_OreDictUnificator.getAssociation(new ItemStack(aWorld.getBlock(aX, aY, aZ), 1, aWorld.getBlockMetadata(aX, aY, aZ))), new SplittableRandom(), 1000);

        for (String key : ores.keySet()) {
            int value = ores.get(key);
            addChatMassageByValue(aPlayer, value, key);
        }
    }

    protected void processOreProspecting(DetravMetaGeneratedTool01 aItem, ItemStack aStack, EntityPlayer aPlayer, Chunk aChunk, TileEntity aTileEntity, ItemData tAssotiation, SplittableRandom aRandom, int chance)//TileEntity aTileEntity)
    {
        if (aTileEntity != null) {
            if (aTileEntity instanceof GT_TileEntity_Ores) {
                GT_TileEntity_Ores gt_entity = (GT_TileEntity_Ores) aTileEntity;
                short meta = gt_entity.getMetaData();
                String name = Materials.getLocalizedNameForItem(GT_LanguageManager.getTranslation("gt.blockores." + meta + ".name"), meta % 1000);
                addOreToHashMap(name, aPlayer);
                if (!aPlayer.capabilities.isCreativeMode)
                    aItem.doDamage(aStack, this.mCosts);
                return;
            }
        } else if (tAssotiation != null) {
            //if (aTileEntity instanceof GT_TileEntity_Ores) {
            try {
                GT_TileEntity_Ores gt_entity = (GT_TileEntity_Ores) aTileEntity;
                String name = tAssotiation.toString();
                addChatMassageByValue(aPlayer, -1, name);
                if (!aPlayer.capabilities.isCreativeMode)
                    aItem.doDamage(aStack, this.mCosts);
                return;
            } catch (Exception e) {
                addChatMassageByValue(aPlayer, -1, "ERROR, lol ^_^");
            }
        } else if (aRandom.nextInt(100) < chance) {
            int data = DetravMetaGeneratedTool01.INSTANCE.getToolGTDetravData(aStack).intValue();
            for (int x = 0; x < 16; x++)
                for (int z = 0; z < 16; z++) {
                    int ySize = aChunk.getHeightValue(x, z);
                    for (int y = 1; y < ySize; y++) {

                        Block tBlock = aChunk.getBlock(x, y, z);
                        short tMetaID = (short) aChunk.getBlockMetadata(x, y, z);
                        if (tBlock instanceof GT_Block_Ores_Abstract) {
                            TileEntity tTileEntity = aChunk.getTileEntityUnsafe(x, y, z);
                            if ((tTileEntity != null)
                                    && (tTileEntity instanceof GT_TileEntity_Ores)
                                    && ((GT_TileEntity_Ores) tTileEntity).mNatural == true) {
                                tMetaID = (short) ((GT_TileEntity_Ores) tTileEntity).getMetaData();
                                try {
                                    String name = Materials.getLocalizedNameForItem(
                                            GT_LanguageManager.getTranslation(tBlock.getUnlocalizedName() + "." + tMetaID + ".name"), tMetaID % 1000);
                                    if (name.startsWith("Small")) if (data != 1) continue;
                                    if (name.startsWith("Small")) if (data != 1) continue;
                                    addOreToHashMap(name, aPlayer);
                                } catch (Exception e) {
                                    String name = tBlock.getUnlocalizedName() + ".";
                                    if (name.contains(".small.")) if (data != 1) continue;
                                    if (name.startsWith("Small")) if (data != 1) continue;
                                    addOreToHashMap(name, aPlayer);
                                }
                            }
                        } else if (data == 1) {
                            tAssotiation = GT_OreDictUnificator.getAssociation(new ItemStack(tBlock, 1, tMetaID));
                            if ((tAssotiation != null) && (tAssotiation.mPrefix.toString().startsWith("ore"))) {
                                try {
                                    try {
                                        tMetaID = (short) tAssotiation.mMaterial.mMaterial.mMetaItemSubID;

                                        String name = Materials.getLocalizedNameForItem(GT_LanguageManager.getTranslation(
                                                "gt.blockores." + tMetaID + ".name"), tMetaID % 1000);
                                        addOreToHashMap(name, aPlayer);
                                    } catch (Exception e1) {
                                        String name = tAssotiation.toString();
                                        addOreToHashMap(name, aPlayer);
                                    }
                                } catch (Exception e) {

                                }
                            }
                        }

                    }
                }

            if (!aPlayer.capabilities.isCreativeMode)
                aItem.doDamage(aStack, this.mCosts);

            return;
        } else {
            if (DetravScannerMod.DEBUGBUILD)
                aPlayer.sendMessage(new TextComponentString(" Failed on this chunk").setStyle(new Style().setColor(TextFormatting.RED)));
            badluck++;
            if (!aPlayer.capabilities.isCreativeMode)
                aItem.doDamage(aStack, this.mCosts / 4);
        }
        // addChatMassageByValue(aPlayer,0,null);
    }

    void addOreToHashMap(String orename, EntityPlayer aPlayer) {
        String oreDistance = orename + DISTANCETEXTS[distTextIndex]; // orename + the textual distance of the ore
        if (!ores.containsKey(oreDistance)) {
            if (DetravScannerMod.DEBUGBUILD)
                aPlayer.sendMessage(new TextComponentString(" Adding to oremap " + oreDistance).setStyle(new Style().setColor(TextFormatting.GREEN)));
            ores.put(oreDistance, 1);
        } else {
            int val = ores.get(oreDistance);
            ores.put(oreDistance, val + 1);
        }
    }

    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        return null;
    }
}
