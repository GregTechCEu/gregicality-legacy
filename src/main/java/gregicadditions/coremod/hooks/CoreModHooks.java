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
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.apiimpl.util.Comparer;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

public class CoreModHooks {

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

    //origin: com.raoulvdberge.refinedstorage.api.network.INetwork.extractItem()
    public static ItemStack extractItem(INetwork network, ItemStack request, int size, int flag, Action action) {
        if (request != null && request.getItem() instanceof MetaItem) {
            for (ItemStack itemStack : network.getItemStorageCache().getList().getStacks()) {
                if (itemStack.getItem() == request.getItem() && itemStack.getItemDamage() == request.getItemDamage() && itemStack.getCount() >= request.getCount()) {
                    request.setTagCompound(itemStack.getTagCompound());
                    break;
                }
            }
        }
        return network.extractItem(request, size, flag, action);
    }

    //origin: com.raoulvdberge.refinedstorage.api.util.IComparer.isEqual()
    public static boolean isEqual(IComparer comparer, ItemStack request, ItemStack itemStack, int flags) {
        if (request != null && itemStack != null && request.getItem() instanceof MetaItem && itemStack.getItem() == request.getItem() && itemStack.getItemDamage() == request.getItemDamage() && itemStack.getCount() >= request.getCount()) {
            request.setTagCompound(itemStack.getTagCompound());
        }
        return comparer.isEqual(request, itemStack, flags);
    }

}
