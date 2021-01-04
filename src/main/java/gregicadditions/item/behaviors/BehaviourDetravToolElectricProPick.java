package gregicadditions.item.behaviors;

import gregicadditions.widgets.WidgetOreList;
import gregicadditions.widgets.WidgetProspectingMap;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.awt.*;

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

    protected final int costs;
    protected final int chunkRaduis;

    public BehaviourDetravToolElectricProPick(int tier) {
        this.costs = tier * 2;
        this.chunkRaduis = tier;
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
            PlayerInventoryHolder holder = new PlayerInventoryHolder(player, hand);
            holder.openUI();
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        WidgetOreList widgetItemFluidList = new WidgetOreList(32 * chunkRaduis + 30, 32, 100, Math.max(((32 * chunkRaduis) / 18) - 1, 1));
        return ModularUI.builder(
                GuiTextures.BOXED_BACKGROUND, 32 * chunkRaduis + 220, 32 * chunkRaduis + 30).label(15, 15, "Prospecting Tool (Unlocalized)", Color.WHITE.getRGB())
                .widget(new WidgetProspectingMap(30, 32, chunkRaduis, playerInventoryHolder, widgetItemFluidList))
                .widget(widgetItemFluidList)
                .build(playerInventoryHolder, entityPlayer);
    }
}
