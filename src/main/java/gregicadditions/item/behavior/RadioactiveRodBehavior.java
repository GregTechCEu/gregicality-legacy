package gregicadditions.item.behavior;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemColorProvider;
import gregtech.api.items.metaitem.stats.IItemDurabilityManager;
import gregtech.api.items.metaitem.stats.IItemMaxStackSizeProvider;
import gregtech.api.items.metaitem.stats.IItemNameProvider;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.common.items.behaviors.AbstractMaterialPartBehavior;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RadioactiveRodBehavior implements IItemColorProvider, IItemNameProvider {

    protected NBTTagCompound getPartStatsTag(ItemStack itemStack) {
        return itemStack.getSubCompound("GT.PartStats");
    }

    protected NBTTagCompound getOrCreatePartStatsTag(ItemStack itemStack) {
        return itemStack.getOrCreateSubCompound("GT.PartStats");
    }

    public static RadioactiveRodBehavior getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        }
        MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
        MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
        if (valueItem == null) {
            return null;
        }
        IItemDurabilityManager durabilityManager = valueItem.getDurabilityManager();
        if (!(durabilityManager instanceof RadioactiveRodBehavior)) {
            return null;
        }
        return (RadioactiveRodBehavior) durabilityManager;
    }

    public void setPartMaterial(ItemStack itemStack, IngotMaterial material) {
        NBTTagCompound compound = getOrCreatePartStatsTag(itemStack);
        compound.setString("Material", material.toString());
    }


    public IngotMaterial getPartMaterial(ItemStack itemStack) {
        NBTTagCompound compound = getPartStatsTag(itemStack);
        IngotMaterial defaultMaterial = Materials.Darmstadtium;
        if (compound == null || !compound.hasKey("Material", Constants.NBT.TAG_STRING)) {
            return defaultMaterial;
        }
        String materialName = compound.getString("Material");
        Material material = Material.MATERIAL_REGISTRY.getObject(materialName);
        if (!(material instanceof IngotMaterial)) {
            return defaultMaterial;
        }
        return (IngotMaterial) material;
    }

    @Override
    public int getItemStackColor(ItemStack itemStack, int i) {
        IngotMaterial material = getPartMaterial(itemStack);
        return material.materialRGB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack itemStack, String unlocalizedName) {
        IngotMaterial material = getPartMaterial(itemStack);
        return I18n.format(unlocalizedName, material.getLocalizedName());
    }
}
