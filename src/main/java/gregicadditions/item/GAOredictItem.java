package gregicadditions.item;

import com.google.common.base.CaseFormat;
import gnu.trove.map.hash.TIntObjectHashMap;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import static gregtech.api.unification.material.MaterialIconType.*;


public class GAOredictItem extends StandardMetaItem {


    private static Map<Short, OreDictItem> ITEMS = new HashMap<>();
    private static final List<MaterialIconType> DISALLOWED_TYPES = new ArrayList<>();


    static {
        DISALLOWED_TYPES.addAll(Arrays.asList(block,
                                              foilBlock,
                                              wire,
                                              ore,
                                              frameGt,
                                              frameSide,
                                              frameTop,
                                              pipeSide,
                                              pipeTiny,
                                              pipeSmall,
                                              pipeMedium,
                                              pipeLarge,
                                              pipeHuge));
    }

    public GAOredictItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        for (OreDictItem item : ITEMS.values()) {
            addItem(item.id, item.getName());
            OreDictUnifier.registerOre(new ItemStack(this, 1, item.id), item.getOre());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            OreDictItem item = ITEMS.get((short) stack.getItemDamage());
            if (item == null) return 0xFFFFFF;
            return item.rgb;
        }
        return super.getColorForItemStack(stack, tintIndex);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        super.registerModels();
        TIntObjectHashMap<ModelResourceLocation> alreadyRegistered = new TIntObjectHashMap<>();
        for (Map.Entry<Short, OreDictItem> metaItem : ITEMS.entrySet()) {
            OrePrefix prefix = metaItem.getValue().orePrefix;
            MaterialIconSet materialIconSet = metaItem.getValue().materialIconSet;
            if (prefix.materialIconType == null || DISALLOWED_TYPES.contains(prefix.materialIconType))
                continue;
            int registrationKey = prefix.ordinal() * 1000 + materialIconSet.ordinal();
            if (!alreadyRegistered.containsKey(registrationKey)) {
                prefix.materialIconType.getItemModelPath(materialIconSet);
                ResourceLocation resourceLocation = prefix.materialIconType.getItemModelPath(materialIconSet);
                ModelBakery.registerItemVariants(this, resourceLocation);
                alreadyRegistered.put(registrationKey, new ModelResourceLocation(resourceLocation, "inventory"));
            }
            ModelResourceLocation resourceLocation = alreadyRegistered.get(registrationKey);
            metaItemsModels.put(metaItem.getKey(), resourceLocation);
        }
    }

    public static class OreDictItem {

        private final String materialName;
        private final int rgb;
        private final MaterialIconSet materialIconSet;
        private final short id;
        private final OrePrefix orePrefix;

        public OreDictItem(short id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix orePrefix) {
            this.materialName = materialName;
            this.rgb = rgb;
            this.materialIconSet = materialIconSet;
            this.id = id;
            this.orePrefix = orePrefix;
            ITEMS.put(id, this);
        }

        public OreDictItem(int id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix orePrefix) {
            this((short) id, materialName, rgb, materialIconSet, orePrefix);
        }

        public String getOre() {
            return orePrefix.name() + toCamelCaseString(materialName);
        }

        String toCamelCaseString(String string) {
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
        }

        String toLowerUnderString(String string) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string);
        }

        public ItemStack getItemStack(int amount) {
            ItemStack itemStack = OreDictUnifier.get(this.getOre());
            itemStack.setCount(amount);
            return itemStack;
        }

        public String getName() {
            return materialName + '_' + toLowerUnderString(orePrefix.name());
        }

        public ItemStack getItemStack() {
            return getItemStack(1);
        }

    }
}
