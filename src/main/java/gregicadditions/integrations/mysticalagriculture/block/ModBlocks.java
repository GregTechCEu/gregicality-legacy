package gregicadditions.integrations.mysticalagriculture.block;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagradditions.blocks.BlockTier6InferiumCrop;
import gregicadditions.integrations.mysticalagriculture.CropType;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;

public class ModBlocks {

    public static BlockTier6InferiumCrop blockTier7InferiumCrop = new BlockTier6InferiumCrop("tier7_inferium_crop");

    public static void init() {
        final ModRegistry registry = MysticalCommonProxy.REGISTRY;

        registry.register(blockTier7InferiumCrop, "tier7_inferium_crop");

        for (CropType.Type type : CropType.Type.values()) {
            if (type.isEnabled()) {
                registry.register(type.getPlant(), type.getName() + "_crop");
            }
        }
    }
}
