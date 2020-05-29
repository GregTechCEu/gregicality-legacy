package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.registry.ModRegistry;
import gregicadditions.integrations.mysticalagriculture.CropType;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;
import gregicadditions.integrations.mysticalagriculture.block.ModBlocks;

public class ModItems {

    public static ItemTier7Seed itemTier7InferiumSeeds = new ItemTier7Seed("tier7_inferium_seeds", ModBlocks.blockTier7InferiumCrop);

    public static void init() {
        final ModRegistry registry = MysticalCommonProxy.REGISTRY;

        for (CropType.Type type : CropType.Type.values()) {
            if (type.isEnabled()) {
                registry.register(type.getCrop(), type.getName() + "_essence");
                registry.addOre(type.getCrop(), "essenceTier7");
            }
        }

        registry.register(itemTier7InferiumSeeds, "tier7_inferium_seeds");

        for (CropType.Type type : CropType.Type.values()) {
            if (type.isEnabled()) {
                registry.register(type.getSeed(), type.getName() + "_seeds");
                registry.addOre(type.getSeed(), "seedsTier7");
            }
        }
    }
}
