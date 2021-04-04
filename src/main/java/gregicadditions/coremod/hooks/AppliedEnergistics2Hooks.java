package gregicadditions.coremod.hooks;

import appeng.api.config.Actionable;
import appeng.api.networking.energy.IEnergySource;
import appeng.api.networking.security.IActionSource;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEItemStack;
import appeng.util.Platform;
import appeng.util.inv.AdaptorItemHandler;
import appeng.util.inv.IInventoryDestination;
import appeng.util.inv.ItemSlot;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.item.ItemStack;

public class AppliedEnergistics2Hooks {
    public static final String hooks = "gregicadditions/coremod/hooks/AppliedEnergistics2Hooks";

    //origin: appeng.util.Platform.poweredExtraction()
    public static IAEItemStack poweredExtraction(IEnergySource energy, IMEMonitor<IAEItemStack> cell, IAEItemStack request, IActionSource src ) {
        if (request != null && request.getDefinition().getItem() instanceof MetaItem) {
            for (IAEItemStack itemStack : cell.getStorageList()) {
                if (itemStack != null && itemStack.getItem() == request.getItem() && itemStack.getItemDamage() == request.getItemDamage() && itemStack.getDefinition().getCount() >= request.getDefinition().getCount()) {
                    request.getDefinition().setTagCompound(itemStack.getDefinition().getTagCompound());
                    break;
                }
            }
        }
        return Platform.poweredExtraction( energy, cell, request, src, Actionable.MODULATE );
    }

    //origin: appeng.util.inv.AdaptorItemHandler.removeItems()
    public static ItemStack removeItems (AdaptorItemHandler ad, int amount, ItemStack request, IInventoryDestination destination) {
        if (request != null && request.getItem() instanceof MetaItem) {
            for (ItemSlot itemSlot : ad) {
                IAEItemStack itemStack = itemSlot.getAEItemStack();
                if (itemStack != null && itemStack.getItem() == request.getItem() && itemStack.getItemDamage() == request.getItemDamage() &&
                        itemStack.getDefinition().getCount() >= request.getCount()) {
                    request.setTagCompound(itemStack.getDefinition().getTagCompound());
                    break;
                }
            }
        }
        return ad.removeItems(amount, request, destination);
    }

}
