package gregicadditions.item.behaviors;

import gregicadditions.widgets.WidgetOreList;
import gregicadditions.widgets.WidgetProspectingMap;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.ToggleButtonWidget;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

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

    public boolean getToolInUsed(ItemStack itemStack) {
        NBTTagCompound compound = this.getOrCreatePartStatsTag(itemStack);
        return compound != null && compound.hasKey("Used", 99) && compound.getBoolean("Used");
    }

    public void setToolGTDetravOpen(ItemStack itemStack, boolean inUsed) {
        NBTTagCompound compound = this.getOrCreatePartStatsTag(itemStack);
        compound.setBoolean("Used", inUsed);
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
            if (player.isSneaking() && chunkRaduis >= 6) { // switch only luv and zpm
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
            } else if (getEuStored(itemStack) == 0) {
                player.sendMessage(new TextComponentTranslation("metaitem.tool.prospect.low_power"));
            } else {
                PlayerInventoryHolder holder = new PlayerInventoryHolder(player, hand);
                holder.openUI();
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public void onUpdate(ItemStack itemStack, Entity entity) {
        if (getToolInUsed(itemStack)) {
            IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (electricItem != null) {
                electricItem.discharge(costs, chunkRaduis, false, false, false);
                if (electricItem.getCharge() == 0 && entity instanceof EntityPlayer) {
                    setToolGTDetravOpen(itemStack, false);
                    ((EntityPlayer) entity).closeScreen();
                    entity.sendMessage(new TextComponentTranslation("metaitem.tool.prospect.low_power"));
                }
            }
        }
    }

    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        WidgetOreList widgetItemFluidList = new WidgetOreList(32 * chunkRaduis + 30, 32, 150, Math.max(((32 * chunkRaduis) / 18) - 1, 1));
        WidgetProspectingMap widgetProspectingMap = new WidgetProspectingMap(30, 32, chunkRaduis, playerInventoryHolder, widgetItemFluidList);
        return ModularUI.builder(GuiTextures.BOXED_BACKGROUND, 32 * chunkRaduis + 220, 32 * chunkRaduis + 30)
                .bindOpenListener(() -> setToolGTDetravOpen(playerInventoryHolder.getCurrentItem(), true))
                .bindCloseListener(() -> setToolGTDetravOpen(playerInventoryHolder.getCurrentItem(), false))
                .label(20, 17, "metaitem.tool.prospect.gui.title", Color.WHITE.getRGB())
                .widget(new ProgressWidget(() -> getEuStored(playerInventoryHolder.getCurrentItem()), 32 * chunkRaduis + 30, 13, 150, 18,
                        TextureArea.fullImage("textures/gui/progress_bar/progress_bar_energy.png"),
                        ProgressWidget.MoveType.HORIZONTAL))
                .widget(widgetProspectingMap)
                .widget(widgetItemFluidList)
                .widget(new ToggleButtonWidget(0, -18, 18, 18,
                        GuiTextures.BUTTON_BLACKLIST, ()->false, (pressed)->{}) {
                    @Override
                    public boolean mouseClicked(int mouseX, int mouseY, int button) {
                        if (this.isMouseOverElement(mouseX, mouseY)) {
                            this.isPressed = !this.isPressed;
                            widgetProspectingMap.setDarkMode(isPressed);
                            this.playButtonClickSound();
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .build(playerInventoryHolder, entityPlayer);
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        if (chunkRaduis >= 6)
            lines.add(I18n.format("metaarmor.energy_share.tooltip.guide"));
    }

    private double getEuStored(ItemStack itemStack) {
        if (itemStack == null) return 0;
        IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null) {
            return electricItem.getCharge() * 1.0D / electricItem.getMaxCharge();
        }
        return 0;
    }
}
