package gregicadditions.item;

import gnu.trove.map.hash.TShortObjectHashMap;
import gregicadditions.materials.SimpleDustMaterial;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import javax.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gregicadditions.materials.SimpleDustMaterial.GA_DUSTS;

public class GADustItem extends StandardMetaItem {

    public final static Map<String, SimpleDustMaterial> oreDictToSimpleDust = new HashMap<>();


    public GADustItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        for (SimpleDustMaterial material : GA_DUSTS.values()) {
            addItem(material.id, material.name);
            String ore = material.getOre();
            OreDictUnifier.registerOre(new ItemStack(this, 1, material.id), material.getOre());
            oreDictToSimpleDust.put(ore, material);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            SimpleDustMaterial mat = GA_DUSTS.get((short) stack.getItemDamage());
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
        for (Map.Entry<Short, SimpleDustMaterial> metaItem : GA_DUSTS.entrySet()) {
            OrePrefix prefix = OrePrefix.dust;
            MaterialIconSet materialIconSet = metaItem.getValue().materialIconSet;
            short registrationKey = (short) materialIconSet.ordinal();
            if (!alreadyRegistered.containsKey(registrationKey)) {
                ResourceLocation resourceLocation = prefix.materialIconType.getItemModelPath(materialIconSet);
                ModelBakery.registerItemVariants(this, resourceLocation);
                alreadyRegistered.put(registrationKey, new ModelResourceLocation(resourceLocation, "inventory"));
            }
            ModelResourceLocation resourceLocation = alreadyRegistered.get(registrationKey);
            metaItemsModels.put(metaItem.getKey(), resourceLocation);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> lines, ITooltipFlag tooltipFlag) {
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);
        String oreDict = OreDictUnifier.getOreDictionaryNames(itemStack).stream().filter(oreDictToSimpleDust::containsKey).findFirst().orElse("");
        if (!oreDict.isEmpty() && !oreDictToSimpleDust.get(oreDict).chemicalFormula.isEmpty()) {
            lines.add(oreDictToSimpleDust.get(oreDict).chemicalFormula);
        }
    }
}
