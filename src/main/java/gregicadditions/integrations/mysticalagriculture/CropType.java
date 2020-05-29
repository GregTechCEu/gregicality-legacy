package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import gregicadditions.GAMaterials;
import gregicadditions.integrations.mysticalagriculture.block.BlockTier7Crop;
import gregicadditions.integrations.mysticalagriculture.items.ItemTier7Seed;
import gregicadditions.item.GAMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class CropType {

    public static void init() {
        for (CropType.Type type : CropType.Type.values()) {
            if (type.isEnabled()) {
                type.set();
            }
        }
    }


    public static enum Type implements IStringSerializable {

        NEUTRONIUM("neutronium", GAMetaBlocks.getCompressedFromMaterial(GAMaterials.Neutronium), 0, true);

        private final String name;
        private final boolean enabled;
        private final BlockTier7Crop plant;
        private final IBlockState root;
        private final ItemBase crop;
        private final ItemTier7Seed seed;
        private final int rootMeta;

        private boolean debug = false;

        Type(String name, IBlockState root, int rootMeta, boolean enabled) {
            this.name = name;
            this.enabled = enabled;
            this.plant = new BlockTier7Crop(getName() + "_crop");
            this.root = root;
            this.crop = new ItemBase("ma." + getName() + "_essence");
            this.crop.setCreativeTab(MysticalAgradditions.tabMysticalAgradditions);
            this.seed = new ItemTier7Seed(getName() + "_seeds", getPlant());
            this.rootMeta = rootMeta;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getTier() {
            return 6;
        }

        public boolean isEnabled() {
            return this.enabled || this.debug;
        }

        public BlockTier7Crop getPlant() {
            return this.plant;
        }

        public IBlockState getRoot() {
            return this.root;
        }


        public ItemBase getCrop() {
            return this.crop;
        }

        public ItemTier7Seed getSeed() {
            return this.seed;
        }

        public int getRootMeta() {
            return rootMeta;
        }


        public Type set() {
            this.getPlant().setRoot(this.getRoot());
            this.getPlant().setCrop(this.getCrop());
            this.getPlant().setSeed(this.getSeed());
            return this;
        }
    }
}
