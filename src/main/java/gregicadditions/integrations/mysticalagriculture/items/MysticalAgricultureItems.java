package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.iface.IColoredItem;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.google.common.base.CaseFormat;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.CropType;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;
import gregicadditions.integrations.mysticalagriculture.block.MaterialBlockCrop;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.MaterialRegistry;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static gregtech.api.unification.material.Materials.*;

public class MysticalAgricultureItems {


    public static final Map<Material, Item> ESSENCES = new HashMap<>();
    public static final Map<Material, MaterialBlockCrop> CROPS = new HashMap<>();


    public static void preInit1() {
        final ModRegistry registry = MysticalCommonProxy.REGISTRY;

        for (Material material : MaterialRegistry.MATERIAL_REGISTRY) {
            if (!material.hasProperty(PropertyKey.DUST)) {
                continue;
            }
            MaterialBlockCrop materialBlockCrop = new MaterialBlockCrop(material.toString() + ".crop", material);
            materialBlockCrop.setRegistryName("gregtech:crop_" + material.toString());
            CROPS.put(material, materialBlockCrop);
            ItemEssence itemEssence = new ItemEssence(material);
            ESSENCES.put(material, itemEssence);
            registry.register(ESSENCES.get(material), material.toString() + "_essence");
        }
    }

    public static void preInit2() {
        final ModRegistry registry = MysticalCommonProxy.REGISTRY;
        for (Material material : MaterialRegistry.MATERIAL_REGISTRY) {
            if (CropType.SEEDS.get(material) == null) {
                continue;
            }

            CROPS.get(material).setCrop(ESSENCES.get(material));
            CROPS.get(material).setSeed(CropType.SEEDS.get(material));


            registry.register(CropType.SEEDS.get(material), material.toString() + "_seeds");
            registry.register(CROPS.get(material), material.toString() + "_crop");

            registry.addOre(CropType.SEEDS.get(material), "listAllSeed");

        }
        Arrays.stream(com.blakebr0.mysticalagriculture.lib.CropType.Type.values())
                .filter(com.blakebr0.mysticalagriculture.lib.CropType.Type::isEnabled)
                .forEach(type -> {
                    registry.addOre(type.getCrop(), "essence" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName()));
                    registry.addOre(type.getSeed(), "seed" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.getName()));
                });

    }

    public static void registerOreDict() {
        CropType.SEEDS.forEach((material, itemSeeds) -> {
            OreDictUnifier.registerOre(new ItemStack(itemSeeds, 1), OrePrefix.seed, material);
            OreDictUnifier.registerOre(new ItemStack(itemSeeds, 1), "seedsTier" + itemSeeds.getTier());
        });
        ESSENCES.forEach((material, item) -> {
            OreDictUnifier.registerOre(new ItemStack(item, 1), OrePrefix.essence, material);
        });
    }

    public static void removeMARecipe() {
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:coal_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:iron_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:nether_quartz_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:gold_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:diamond_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:emerald_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:lapis_lazuli_seeds"));
        ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:redstone_seeds"));
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.COPPER.isEnabled())
            ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:copper_seeds"));
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.BRONZE.isEnabled())
            ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:bronze_seeds"));
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.TIN.isEnabled())
            ModHandler.removeRecipeByName(new ResourceLocation("mysticalagriculture:tin_seeds"));
    }

    public static void registerRecipe() {
        ItemSeed coalSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.COAL.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Coal.toString(), new ItemStack(coalSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(coalSeed.getTier()),
                'S', getCraftingSeed(coalSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Coal));
        ItemSeed ironSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.IRON.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Iron.toString(), new ItemStack(ironSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(ironSeed.getTier()),
                'S', getCraftingSeed(ironSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Iron));
        ItemSeed quartzSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.NETHER_QUARTZ.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + NetherQuartz.toString(), new ItemStack(quartzSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(quartzSeed.getTier()),
                'S', getCraftingSeed(quartzSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, NetherQuartz));
        ItemSeed goldSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.GOLD.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Gold.toString(), new ItemStack(goldSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(goldSeed.getTier()),
                'S', getCraftingSeed(goldSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Gold));
        ItemSeed diamondSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.DIAMOND.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Diamond.toString(), new ItemStack(diamondSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(diamondSeed.getTier()),
                'S', getCraftingSeed(diamondSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Diamond));
        ItemSeed emeraldSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.EMERALD.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Emerald.toString(), new ItemStack(emeraldSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(emeraldSeed.getTier()),
                'S', getCraftingSeed(emeraldSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Emerald));
        ItemSeed lapisSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.LAPIS_LAZULI.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Lapis.toString(), new ItemStack(lapisSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(lapisSeed.getTier()),
                'S', getCraftingSeed(lapisSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Lapis));
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.COPPER.isEnabled()) {
            ItemSeed copperSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.COPPER.getSeed();
            ModHandler.addShapedRecipe("gtadditions:seed_" + copperSeed.toString(), new ItemStack(copperSeed, 1),
                    "MEM", "ESE", "MEM",
                    'E', getEssence(copperSeed.getTier()),
                    'S', getCraftingSeed(copperSeed.getTier()),
                    'M', OreDictUnifier.get(OrePrefix.block, Copper));
        }
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.BRONZE.isEnabled()) {
            ItemSeed bronzeSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.BRONZE.getSeed();
            ModHandler.addShapedRecipe("gtadditions:seed_" + Bronze.toString(), new ItemStack(bronzeSeed, 1),
                    "MEM", "ESE", "MEM",
                    'E', getEssence(bronzeSeed.getTier()),
                    'S', getCraftingSeed(bronzeSeed.getTier()),
                    'M', OreDictUnifier.get(OrePrefix.block, Bronze));
        }
        if (com.blakebr0.mysticalagriculture.lib.CropType.Type.TIN.isEnabled()) {
            ItemSeed tinSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.TIN.getSeed();
            ModHandler.addShapedRecipe("gtadditions:seed_" + Tin.toString(), new ItemStack(tinSeed, 1),
                    "MEM", "ESE", "MEM",
                    'E', getEssence(tinSeed.getTier()),
                    'S', getCraftingSeed(tinSeed.getTier()),
                    'M', OreDictUnifier.get(OrePrefix.block, Tin));
        }
        ItemSeed redstoneSeed = com.blakebr0.mysticalagriculture.lib.CropType.Type.REDSTONE.getSeed();
        ModHandler.addShapedRecipe("gtadditions:seed_" + Redstone.toString(), new ItemStack(redstoneSeed, 1),
                "MEM", "ESE", "MEM",
                'E', getEssence(redstoneSeed.getTier()),
                'S', getCraftingSeed(redstoneSeed.getTier()),
                'M', OreDictUnifier.get(OrePrefix.block, Redstone));

        OrePrefix.nugget.addProcessingHandler(IngotMaterial.class, (nugget, ingotMaterial) -> {
            if (ESSENCES.get(ingotMaterial) == null) {
                return;
            }
            RecipeMaps.PACKER_RECIPES.recipeBuilder().EUt(16).duration(30).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(ESSENCES.get(ingotMaterial), 8)).outputs(OreDictUnifier.get(nugget, ingotMaterial)).buildAndRegister();
        });
        OrePrefix.nugget.addProcessingHandler(GemMaterial.class, (nugget, gemMaterial) -> {
            if (ESSENCES.get(gemMaterial) == null) {
                return;
            }
            RecipeMaps.PACKER_RECIPES.recipeBuilder().EUt(16).duration(30).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(ESSENCES.get(gemMaterial), 8)).outputs(OreDictUnifier.get(nugget, gemMaterial)).buildAndRegister();
        });
        OrePrefix.dustTiny.addProcessingHandler(DustMaterial.class, (dustTiny, dustMaterial) -> {
            if (dustMaterial instanceof IngotMaterial || dustMaterial instanceof GemMaterial || ESSENCES.get(dustMaterial) == null)
                return;
            RecipeMaps.PACKER_RECIPES.recipeBuilder().EUt(16).duration(30).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(ESSENCES.get(dustMaterial), 8)).outputs(OreDictUnifier.get(dustTiny, dustMaterial)).buildAndRegister();
        });

        OrePrefix.block.addProcessingHandler(DustMaterial.class, (nugget, dustMaterial) -> {
            if (CropType.SEEDS.get(dustMaterial) == null) {
                return;
            }
            ModHandler.addShapedRecipe("gtadditions:seed_" + dustMaterial.toString(), new ItemStack(CropType.SEEDS.get(dustMaterial), 1),
                    "MEM", "ESE", "MEM",
                    'E', getEssence(CropType.SEEDS.get(dustMaterial).getTier()),
                    'S', getCraftingSeed(CropType.SEEDS.get(dustMaterial).getTier()),
                    'M', OreDictUnifier.get(OrePrefix.block, dustMaterial));
        });

        ModHandler.addShapedRecipe("hyperium_essence", GAMetaItems.HYPERIUM_ESSENCE.getStackForm()," E ", "ECE", " E ", 'E', getEssence(6), 'C', ModItems.itemInfusionCrystal);
        ModHandler.addShapedRecipe("ludicium_essence", GAMetaItems.LUDICIUM_ESSENCE.getStackForm()," E ", "ECE", " E ", 'E', getEssence(7), 'C', ModItems.itemInfusionCrystal);
        ModHandler.addShapedRecipe("hyperium_essence_m", GAMetaItems.HYPERIUM_ESSENCE.getStackForm()," E ", "ECE", " E ", 'E', getEssence(6), 'C', ModItems.itemInfusionCrystalMaster);
        ModHandler.addShapedRecipe("ludicium_essence_m", GAMetaItems.LUDICIUM_ESSENCE.getStackForm()," E ", "ECE", " E ", 'E', getEssence(7), 'C', ModItems.itemInfusionCrystalMaster);
        ModHandler.addShapedRecipe("hyperium_seed", GAMetaItems.HYPERIUM_SEED.getStackForm(), " E ", "ESE", " E ", 'E', getEssence(7), 'S', getCraftingSeed(6));
        ModHandler.addShapedRecipe("ludicium_seed", GAMetaItems.LUDICIUM_SEED.getStackForm(), " E ", "ESE", " E ", 'E', getEssence(8), 'S', getCraftingSeed(7));
        ModHandler.addShapelessRecipe("hyperium_down", new ItemStack(com.blakebr0.mysticalagradditions.items.ModItems.itemInsanium, 4, 0), GAMetaItems.HYPERIUM_ESSENCE.getStackForm());
        ModHandler.addShapelessRecipe("ludicium_down", GAMetaItems.HYPERIUM_ESSENCE.getStackForm(4), GAMetaItems.LUDICIUM_ESSENCE.getStackForm());
    }

    @SideOnly(Side.CLIENT)
    public static void registerColor() {
        CropType.SEEDS.values().stream().distinct().forEach(itemSeeds -> {
            if (itemSeeds.getRegistryName() == null)
                return;
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                Item item = stack.getItem();
                if (item instanceof IColoredItem && Loader.isModLoaded(MysticalAgradditions.MOD_ID)) {
                    return ((IColoredItem) item).color();
                }
                return 0xffffff;
            }, itemSeeds);
        });

        ESSENCES.values().stream().distinct().forEach(essence -> {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                Item item = stack.getItem();
                if (item instanceof IColoredItem && Loader.isModLoaded(MysticalAgradditions.MOD_ID)) {
                    return ((IColoredItem) item).color();
                }
                return 0xffffff;
            }, essence);
        });

        CROPS.values().stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> ((MaterialBlockCrop) (state.getBlock())).getMaterial().getMaterialRGB(), block);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> ((MaterialBlockCrop) (((ItemBlock) stack.getItem()).getBlock())).getMaterial().getMaterialRGB(), block);
        });
    }

    public static void registerModels() {
        CropType.SEEDS.values().stream().distinct().forEach(itemSeeds -> {
            ModelLoader.setCustomModelResourceLocation(itemSeeds, 0, new ModelResourceLocation(new ResourceLocation(Gregicality.MODID, "material_sets/seed"), "inventory"));
        });

        ESSENCES.forEach((material, essence) -> {
            ModelLoader.setCustomModelResourceLocation(essence, 0, new ModelResourceLocation(OrePrefix.essence.materialIconType.getItemModelPath(material.getMaterialIconSet()), "inventory"));
        });
        CROPS.values().stream().distinct().forEach(GAMetaBlocks::registerItemModel);
    }


    public static ItemStack getEssence(int tier) {
        ItemStack essence = null;
        switch (tier - 1) {
            case 0:
                essence = ModItems.itemCrafting.itemInferiumEssence;
                break;
            case 1:
                essence = ModItems.itemCrafting.itemPrudentiumEssence;
                break;
            case 2:
                essence = ModItems.itemCrafting.itemIntermediumEssence;
                break;
            case 3:
                essence = ModItems.itemCrafting.itemSuperiumEssence;
                break;
            case 4:
                essence = ModItems.itemCrafting.itemSupremiumEssence;
                break;
            case 5:
                essence = new ItemStack(com.blakebr0.mysticalagradditions.items.ModItems.itemInsanium, 1, 0);
                break;
            case 6:
                essence = GAMetaItems.HYPERIUM_ESSENCE.getStackForm();
                break;
            case 7:
                essence = GAMetaItems.LUDICIUM_ESSENCE.getStackForm();
                break;
            default:
                essence = GAMetaItems.LUDICIUM_ESSENCE.getStackForm();
        }
        return essence;
    }

    public static ItemStack getCraftingSeed(int tier) {
        ItemStack craftingSeed = null;
        switch (tier - 1) {
            case 0:
                craftingSeed = ModItems.itemCrafting.itemTier1CraftingSeed;
                break;
            case 1:
                craftingSeed = ModItems.itemCrafting.itemTier2CraftingSeed;
                break;
            case 2:
                craftingSeed = ModItems.itemCrafting.itemTier3CraftingSeed;
                break;
            case 3:
                craftingSeed = ModItems.itemCrafting.itemTier4CraftingSeed;
                break;
            case 4:
                craftingSeed = ModItems.itemCrafting.itemTier5CraftingSeed;
                break;
            case 5:
                craftingSeed = new ItemStack(com.blakebr0.mysticalagradditions.items.ModItems.itemInsanium, 1, 1);
                break;
            case 6:
                craftingSeed = GAMetaItems.HYPERIUM_SEED.getStackForm();
                break;
            case 7:
                craftingSeed = GAMetaItems.LUDICIUM_SEED.getStackForm();
                break;
            default:
                craftingSeed = GAMetaItems.LUDICIUM_ESSENCE.getStackForm();
        }
        return craftingSeed;
    }


}
