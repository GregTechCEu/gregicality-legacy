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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.awt.*;

public class ProspectingToolBehaviour implements IItemBehaviour, ItemUIFactory {

    public static ProspectingToolBehaviour getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        } else {
            MetaItem<?> metaItem = (MetaItem) itemStack.getItem();
            MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
            if (valueItem == null) {
                return null;
            } else {
                ItemUIFactory itemUIFactory = valueItem.getUIManager();
                return !(itemUIFactory instanceof ProspectingToolBehaviour) ? null : (ProspectingToolBehaviour) itemUIFactory;
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

    public ProspectingToolBehaviour(int tier) {
        this.costs = tier * 2;
        this.chunkRaduis = tier;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        ProspectingToolBehaviour behavior = getInstanceFor(itemStack);
        if (!world.isRemote) {
            int data = behavior.getToolGTDetravData(itemStack);
            if (player.isSneaking()) {
                data++;
                if (data > 1) data = 0;
                switch (data) {
                    case 0:
                        player.sendMessage(new TextComponentTranslation("metaitem.tool.prospect.mode.0"));
                        break;
                    case 1:
                        player.sendMessage(new TextComponentTranslation("metaitem.tool.prospect.mode.1"));
                        break;
                    default:
                        player.sendMessage(new TextComponentTranslation("metaitem.tool.prospect.mode.default"));
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
                GuiTextures.BOXED_BACKGROUND, 32 * chunkRaduis + 220, 32 * chunkRaduis + 30).label(15, 15, "metaitem.tool.prospect.gui.title", Color.WHITE.getRGB())
                .widget(new WidgetProspectingMap(30, 32, chunkRaduis, playerInventoryHolder, widgetItemFluidList))
                .widget(widgetItemFluidList)
                .build(playerInventoryHolder, entityPlayer);
    }
}
