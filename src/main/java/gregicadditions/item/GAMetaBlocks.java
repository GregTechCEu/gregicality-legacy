package gregicadditions.item;

import gregicadditions.blocks.GAMetalCasing;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.ClientProxy;
import gregtech.common.blocks.BlockOre;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gregtech.common.pipelike.fluidpipe.FluidPipeProperties;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

import static gregicadditions.ClientProxy.METAL_CASING_BLOCK_COLOR;
import static gregicadditions.ClientProxy.METAL_CASING_ITEM_COLOR;
import static gregicadditions.GAMaterials.GENERATE_METAL_CASING;
import static gregtech.common.ClientProxy.ORE_ITEM_COLOR;

public class GAMetaBlocks {

    public static GAMultiblockCasing MUTLIBLOCK_CASING;

    public static GATransparentCasing TRANSPARENT_CASING;

    public static CellCasing CELL_CASING;

    public static Map<IngotMaterial, GAMetalCasing> METAL_CASING = new HashMap<>();

    public static Collection<BlockOre> GA_ORES = new HashSet<>();


    public static void init() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {
                createOreBlock((DustMaterial) mat);
            }
        }
        MUTLIBLOCK_CASING = new GAMultiblockCasing();
        MUTLIBLOCK_CASING.setRegistryName("ga_multiblock_casing");

        TRANSPARENT_CASING = new GATransparentCasing();
        TRANSPARENT_CASING.setRegistryName("ga_transparent_casing");

        CELL_CASING = new CellCasing();
        CELL_CASING.setRegistryName("ga_cell_casing");

        MetaBlocks.FLUID_PIPE.addPipeMaterial(Materials.Ultimet, new FluidPipeProperties(1500, 12000, true));
        //MetaBlocks.FLUID_PIPE.addPipeMaterial(GAMaterials.Plasma, new FluidPipeProperties(1000000, 30, true));


        createMachineCasing();
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "STEAM_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.STEAM_TURBINE_FUELS, GAMetaBlocks.getMetalCasingBlockState(Materials.Steel), GAMetaBlocks.METAL_CASING.get(Materials.Steel), true);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "GAS_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.GAS_TURBINE_FUELS, GAMetaBlocks.getMetalCasingBlockState(Materials.StainlessSteel), GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel), false);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "PLASMA_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.PLASMA_GENERATOR_FUELS, GAMetaBlocks.getMetalCasingBlockState(Materials.TungstenSteel), GAMetaBlocks.METAL_CASING.get(Materials.TungstenSteel), true);

    }

    private static void createOreBlock(DustMaterial material) {
        StoneType[] stoneTypeBuffer = new StoneType[16];
        int generationIndex = 0;
        for (StoneType stoneType : StoneType.STONE_TYPE_REGISTRY) {
            int id = StoneType.STONE_TYPE_REGISTRY.getIDForObject(stoneType), index = id / 16;
            if (index > generationIndex) {
                createOreBlock(material, copyNotNull(stoneTypeBuffer), generationIndex, OrePrefix.valueOf("oreDense"));
                Arrays.fill(stoneTypeBuffer, null);
            }
            stoneTypeBuffer[id % 16] = stoneType;
            generationIndex = index;
        }
        createOreBlock(material, copyNotNull(stoneTypeBuffer), generationIndex, OrePrefix.valueOf("oreDense"));
    }

    private static <T> T[] copyNotNull(T[] src) {
        int nullIndex = ArrayUtils.indexOf(src, null);
        return Arrays.copyOfRange(src, 0, nullIndex == -1 ? src.length : nullIndex);
    }

    private static void createOreBlock(DustMaterial material, StoneType[] stoneTypes, int index, OrePrefix orePrefix) {
        BlockOre block = new BlockOre(material, stoneTypes);

        block.setRegistryName("ore_dense_" + material + "_" + index);

//        for (StoneType stoneType : stoneTypes) {
//            GregTechAPI.oreBlockTable.computeIfAbsent(material, m -> new HashMap<>()).put(stoneType, block);
//        }
        GA_ORES.add(block);
    }


    public static IBlockState getCompressedFromMaterial(Material material) {
        if (MetaBlocks.COMPRESSED.get(material) == null) {
            return null;
        }
        int index = MetaBlocks.COMPRESSED.get(material).variantProperty.getAllowedValues().indexOf(material);
        return MetaBlocks.COMPRESSED.get(material).getStateFromMeta(index);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(MUTLIBLOCK_CASING);
        registerItemModel(TRANSPARENT_CASING);
        registerItemModel(CELL_CASING);
        METAL_CASING.values().stream().distinct().forEach(GAMetaBlocks::registerItemModel);
        GA_ORES.stream().distinct().forEach(GAMetaBlocks::registerItemModel);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        GAMetaBlocks.METAL_CASING.values().stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(METAL_CASING_BLOCK_COLOR, block);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(METAL_CASING_ITEM_COLOR, block);
        });

        GAMetaBlocks.GA_ORES.stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(ClientProxy.ORE_BLOCK_COLOR);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ClientProxy.ORE_ITEM_COLOR, block);
        });

    }

    public static void registerOreDict() {
        for (Map.Entry<IngotMaterial, GAMetalCasing> entry : METAL_CASING.entrySet()) {
            IngotMaterial material = entry.getKey();
            GAMetalCasing block = entry.getValue();
            ItemStack itemStack = block.getItem(material);
            OreDictUnifier.registerOre(itemStack, OrePrefix.valueOf("gtMetalCasing"), material);
        }

    }

    public static String statePropertiesToString(Map<IProperty<?>, Comparable<?>> properties) {
        StringBuilder stringbuilder = new StringBuilder();

        List<Map.Entry<IProperty<?>, Comparable<?>>> entries = properties.entrySet().stream().sorted(Comparator.comparing(c -> c.getKey().getName())).collect(Collectors.toList());

        for (Map.Entry<IProperty<?>, Comparable<?>> entry : entries) {
            if (stringbuilder.length() != 0) {
                stringbuilder.append(",");
            }

            IProperty<?> property = entry.getKey();
            stringbuilder.append(property.getName());
            stringbuilder.append("=");
            stringbuilder.append(getPropertyName(property, entry.getValue()));
        }

        if (stringbuilder.length() == 0) {
            stringbuilder.append("normal");
        }

        return stringbuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
        return property.getName((T) value);
    }


    private static void createMachineCasing() {
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material instanceof IngotMaterial && material.hasFlag(GENERATE_METAL_CASING)) {
                GAMetalCasing block = new GAMetalCasing(material);
                block.setRegistryName("gregtech:metal_casing_" + material.toString());
                METAL_CASING.put((IngotMaterial) material, block);
            }
        }
    }

    public static IBlockState getMetalCasingBlockState(Material material) {
        return METAL_CASING.get(material).getStateFromMaterial(material);
    }


}
