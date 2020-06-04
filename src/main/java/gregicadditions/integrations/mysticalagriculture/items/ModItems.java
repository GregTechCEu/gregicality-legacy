package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import forestry.core.items.IColoredItem;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;
import gregicadditions.integrations.mysticalagriculture.block.BlockCrop;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class ModItems {

    public static final Map<Material, ItemSeeds> SEEDS = new HashMap<>();
    public static final Map<Material, Item> ESSENCES = new HashMap<>();
    public static final Map<Material, BlockCrop> CROPS = new HashMap<>();


    public static void preInit() {
        final ModRegistry registry = MysticalCommonProxy.REGISTRY;


        for (Material material : Material.MATERIAL_REGISTRY) {
            if (GAMetaBlocks.getCompressedFromMaterial(material) == null) {
                continue;
            }

            BlockCrop blockCrop = new BlockCrop(material.toString() + ".crop", material);
            blockCrop.setRegistryName("gregtech:crop_" + material.toString());
            CROPS.put(material, blockCrop);
            ItemTier7Seed itemTier7Seed = new ItemTier7Seed(material.toString() + ".seeds", blockCrop, material);
            SEEDS.put(material, itemTier7Seed);
            ItemEssence itemEssence = new ItemEssence(material);
            ESSENCES.put(material, itemEssence);

            blockCrop.setCrop(itemEssence);
            blockCrop.setSeed(itemTier7Seed);
            blockCrop.setRoot(GAMetaBlocks.getCompressedFromMaterial(material));


            registry.register(itemTier7Seed, material.toString() + "_seeds");
            registry.register(itemEssence, material.toString() + "_essence");
            registry.register(blockCrop, material.toString() + "_crop");

            registry.addOre(itemTier7Seed, "listAllSeed");

        }
    }

    public static void registerOreDict() {
        SEEDS.forEach((material, itemSeeds) -> {
            OreDictUnifier.registerOre(new ItemStack(itemSeeds, 1), OrePrefix.valueOf("seed"), material);
        });
        ESSENCES.forEach((material, item) -> {
            OreDictUnifier.registerOre(new ItemStack(item, 1), OrePrefix.valueOf("essence"), material);
        });

    }

    @SideOnly(Side.CLIENT)
    public static void registerColor() {
        SEEDS.values().stream().distinct().forEach(itemSeeds -> {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                Item item = stack.getItem();
                if (item instanceof IColoredItem && Loader.isModLoaded(MysticalAgriculture.MOD_ID)) {
                    return ((IColoredItem) item).getColorFromItemstack(stack, tintIndex);
                }
                return 0xffffff;
            }, itemSeeds);
        });

        ESSENCES.values().stream().distinct().forEach(essence -> {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                Item item = stack.getItem();
                if (item instanceof IColoredItem && Loader.isModLoaded(MysticalAgriculture.MOD_ID)) {
                    return ((IColoredItem) item).getColorFromItemstack(stack, tintIndex);
                }
                return 0xffffff;
            }, essence);
        });

        CROPS.values().stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> ((BlockCrop) (state.getBlock())).getMaterial().materialRGB, block);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> ((BlockCrop) (((ItemBlock) stack.getItem()).getBlock())).getMaterial().materialRGB, block);
        });
    }

    public static void registerModels() {
        SEEDS.values().stream().distinct().forEach(itemSeeds -> {
            ModelLoader.setCustomModelResourceLocation(itemSeeds, 0, new ModelResourceLocation(new ResourceLocation(Gregicality.MODID, "material_sets/seed"), "inventory"));
        });

        ESSENCES.forEach((material, essence) -> {
            ModelLoader.setCustomModelResourceLocation(essence, 0, new ModelResourceLocation(OrePrefix.valueOf("essence").materialIconType.getItemModelPath(material.materialIconSet), "inventory"));
        });
        CROPS.values().stream().distinct().forEach(GAMetaBlocks::registerItemModel);
    }


}
