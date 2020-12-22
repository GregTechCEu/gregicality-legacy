package gregicadditions.coremod.hooks;

import appeng.api.config.Actionable;
import appeng.api.networking.energy.IEnergySource;
import appeng.api.networking.security.IActionSource;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEItemStack;
import appeng.util.Platform;
import gregtech.api.items.metaitem.MetaItem;

public class CoreModHooks {

    public static IAEItemStack poweredExtraction(IEnergySource energy, IMEMonitor<IAEItemStack> cell, IAEItemStack request, IActionSource src ) {
        if (request != null && request.getDefinition().getItem() instanceof MetaItem){
            for (IAEItemStack itemStack : cell.getStorageList()) {
                if (itemStack.getItem() == request.getItem() && itemStack.getItemDamage() == request.getItemDamage() && itemStack.getDefinition().getCount() >= request.getDefinition().getCount()) {
                    request.getDefinition().setTagCompound(itemStack.getDefinition().getTagCompound());
                    break;
                }
            }
        }
        return Platform.poweredExtraction( energy, cell, request, src, Actionable.MODULATE );
    }

}
