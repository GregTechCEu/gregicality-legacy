package gregicadditions.integrations.mysticalagriculture.items;

import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;

public class MysticalAgricultureMetaItems extends MaterialMetaItem {

    public static void preInit() {
        MysticalAgricultureMetaItems mysticalAgricultureMetaItems = new MysticalAgricultureMetaItems();
        mysticalAgricultureMetaItems.setRegistryName("mystical_agriculture_meta_item");
    }

    public MysticalAgricultureMetaItems() {
        super(OrePrefix.valueOf("seed"), OrePrefix.valueOf("crop"), null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null);
    }

    public void registerOreDict() {
        super.registerOreDict();

        ArrayList<Short> generatedItems = ObfuscationReflectionHelper.getPrivateValue(MaterialMetaItem.class, this, "generatedItems");

        for (short metaItem : generatedItems) {
            OrePrefix prefix = this.orePrefixes[metaItem / 1000];
            Material material = Material.MATERIAL_REGISTRY.getObjectById(metaItem % 1000);

            if (prefix == OrePrefix.valueOf("seed"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "seed" + (material == null ? "" : material.toCamelCaseString()));

            if (prefix == OrePrefix.valueOf("crop"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "crop" + (material == null ? "" : material.toCamelCaseString()));

        }

    }
}
