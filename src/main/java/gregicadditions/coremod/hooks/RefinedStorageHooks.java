package gregicadditions.coremod.hooks;

import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.api.util.IComparer;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.item.ItemStack;

public class RefinedStorageHooks {

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
