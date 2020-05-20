package gregicadditions.item.behavior;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemColorProvider;
import gregtech.api.items.metaitem.stats.IItemNameProvider;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DustNuclear implements IItemColorProvider, IItemNameProvider {

    public static DustNuclear getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        }
        MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
        MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
        if (valueItem == null) {
            return null;
        }
        IItemColorProvider colorProvider = valueItem.getColorProvider();
        if (!(colorProvider instanceof DustNuclear)) {
            return null;
        }
        return (DustNuclear) colorProvider;
    }

    protected NBTTagCompound getOrCreatePartStatsTag(ItemStack itemStack) {
        return itemStack.getOrCreateSubCompound("GT.PartStats");
    }

    public void setPartMaterial(ItemStack itemStack, IngotMaterial material) {
        NBTTagCompound compound = getOrCreatePartStatsTag(itemStack);
        compound.setString("Material", material.toString());
    }


    protected NBTTagCompound getPartStatsTag(ItemStack itemStack) {
        return itemStack.getSubCompound("GT.PartStats");
    }

    public IngotMaterial getPartMaterial(ItemStack itemStack) {
        NBTTagCompound compound = this.getPartStatsTag(itemStack);
        IngotMaterial defaultMaterial = Materials.Darmstadtium;
        if (compound != null && compound.hasKey("Material", 8)) {
            String materialName = compound.getString("Material");
            Material material = Material.MATERIAL_REGISTRY.getObject(materialName);
            return !(material instanceof IngotMaterial) ? defaultMaterial : (IngotMaterial) material;
        } else {
            return defaultMaterial;
        }
    }

    public int getItemStackColor(ItemStack itemStack, int tintIndex) {
        IngotMaterial material = this.getPartMaterial(itemStack);
        return material.materialRGB;
    }

    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack itemStack, String unlocalizedName) {
        IngotMaterial material = this.getPartMaterial(itemStack);
        return I18n.format(unlocalizedName, material.getLocalizedName());
    }
}
