package gregicadditions.integrations.exnihilocreatio.items;

import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;

public class ChunkMetaItem extends MaterialMetaItem {

    public ChunkMetaItem() {
        super(OrePrefix.valueOf("oreChunk"), OrePrefix.valueOf("oreEnderChunk"), OrePrefix.valueOf("oreNetherChunk"), OrePrefix.valueOf("oreSandyChunk"),
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

            if (prefix == OrePrefix.valueOf("oreChunk"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "oreGravel" + (material == null ? "" : material.toCamelCaseString()));

            if (prefix == OrePrefix.valueOf("oreEnderChunk"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "oreEndstone" + (material == null ? "" : material.toCamelCaseString()));

            if (prefix == OrePrefix.valueOf("oreNetherChunk"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "oreNetherrack" + (material == null ? "" : material.toCamelCaseString()));

            if (prefix == OrePrefix.valueOf("oreSandyChunk"))
                OreDictUnifier.registerOre(new ItemStack(this, 1, metaItem), "oreSand" + (material == null ? "" : material.toCamelCaseString()));
        }

    }
}