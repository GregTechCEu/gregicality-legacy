package gregicadditions.item;

import gnu.trove.map.hash.TShortObjectHashMap;
import gregicadditions.materials.SimpleDustMaterial;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregicadditions.materials.SimpleDustMaterial.GA_DUSTS;

public class GADustItem extends StandardMetaItem {


    public GADustItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        for (SimpleDustMaterial material : GA_DUSTS) {
            addItem(material.id, material.name);
            OreDictUnifier.registerOre(new ItemStack(this, 1, material.id), material.getOre());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            SimpleDustMaterial mat = GA_DUSTS.get(stack.getItemDamage());
            if (mat == null) return 0xFFFFFF;
            return mat.rgb;
        }
        return super.getColorForItemStack(stack, tintIndex);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        super.registerModels();
        TShortObjectHashMap<ModelResourceLocation> alreadyRegistered = new TShortObjectHashMap<>();
        for (SimpleDustMaterial metaItem : GA_DUSTS) {
            OrePrefix prefix = OrePrefix.dust;
            MaterialIconSet materialIconSet = metaItem.materialIconSet;
            short registrationKey = (short) materialIconSet.ordinal();
            if (!alreadyRegistered.containsKey(registrationKey)) {
                ResourceLocation resourceLocation = prefix.materialIconType.getItemModelPath(materialIconSet);
                ModelBakery.registerItemVariants(this, resourceLocation);
                alreadyRegistered.put(registrationKey, new ModelResourceLocation(resourceLocation, "inventory"));
            }
            ModelResourceLocation resourceLocation = alreadyRegistered.get(registrationKey);
            metaItemsModels.put((short) GA_DUSTS.indexOf(metaItem), resourceLocation);
        }
    }


}
