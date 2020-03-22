package gregicadditions.item;

import gregicadditions.GAMaterials;
import gregicadditions.blocks.GAMetalCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gregtech.common.pipelike.cable.WireProperties;
import gregtech.common.pipelike.fluidpipe.FluidPipeProperties;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.GENERATE_METAL_CASING;
import static gregicadditions.GregicAdditions.METAL_CASING_BLOCK_COLOR;
import static gregicadditions.GregicAdditions.METAL_CASING_ITEM_COLOR;

public class GAMetaBlocks {

    public static GAMultiblockCasing MUTLIBLOCK_CASING;

    public static GATransparentCasing TRANSPARENT_CASING;

    public static Map<IngotMaterial, GAMetalCasing> METAL_CASING = new HashMap<>();

    public static void init() {
        MUTLIBLOCK_CASING = new GAMultiblockCasing();
        MUTLIBLOCK_CASING.setRegistryName("ga_multiblock_casing");

        TRANSPARENT_CASING = new GATransparentCasing();
        TRANSPARENT_CASING.setRegistryName("ga_transparent_casing");

        MetaBlocks.FLUID_PIPE.addPipeMaterial(Materials.Ultimet, new FluidPipeProperties(1500, 12000, true));
        //MetaBlocks.FLUID_PIPE.addPipeMaterial(GAMaterials.Plasma, new FluidPipeProperties(1000000, 30, true));

        MetaBlocks.CABLE.addCableMaterial(GAMaterials.MVSuperconductor, new WireProperties(128, 2, 0));
        MetaBlocks.CABLE.addCableMaterial(GAMaterials.HVSuperconductor, new WireProperties(512, 2, 0));
        MetaBlocks.CABLE.addCableMaterial(GAMaterials.EVSuperconductor, new WireProperties(2048, 4, 0));
        MetaBlocks.CABLE.addCableMaterial(GAMaterials.IVSuperconductor, new WireProperties(8192, 4, 0));
        MetaBlocks.CABLE.addCableMaterial(GAMaterials.LuVSuperconductor, new WireProperties(32768, 8, 0));
        MetaBlocks.CABLE.addCableMaterial(GAMaterials.ZPMSuperconductor, new WireProperties(131072, 8, 0));

        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material instanceof IngotMaterial && material.hasFlag(GENERATE_METAL_CASING)) {
                GAMetalCasing blockMetalCasing = new GAMetalCasing((IngotMaterial) material);
                blockMetalCasing.setRegistryName(GTValues.MODID, "metal_casing_" + material.toString());
                METAL_CASING.put((IngotMaterial) material, blockMetalCasing);
            }
        }

        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "STEAM_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.STEAM_TURBINE_FUELS, GAMetaBlocks.METAL_CASING.get(Materials.Steel).getDefaultState(), GAMetaBlocks.METAL_CASING.get(Materials.Steel), true);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "HIGH_PRESSURE_STEAM_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                GARecipeMaps.HIGH_PRESSURE_STEAM_TURBINE_FUELS, GAMetaBlocks.METAL_CASING.get(GAMaterials.Stellite).getDefaultState(), GAMetaBlocks.METAL_CASING.get(GAMaterials.Stellite), true);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "GAS_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.GAS_TURBINE_FUELS, GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel).getDefaultState(), GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel), false);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "PLASMA_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.PLASMA_GENERATOR_FUELS, GAMetaBlocks.METAL_CASING.get(Materials.TungstenSteel).getDefaultState(), GAMetaBlocks.METAL_CASING.get(Materials.TungstenSteel), true);

    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(MUTLIBLOCK_CASING);
        registerItemModel(TRANSPARENT_CASING);
        METAL_CASING.values().stream().distinct().forEach(GAMetaBlocks::registerItemModel);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), block.getMetaFromState(state), new ModelResourceLocation(block.getRegistryName(), statePropertiesToString(state.getProperties())));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerStateMappers() {
        IStateMapper normalStateMapper = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()), "normal");
            }
        };
        METAL_CASING.values().stream().distinct().forEach(it -> ModelLoader.setCustomStateMapper(it, normalStateMapper));
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        GAMetaBlocks.METAL_CASING.values().stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(METAL_CASING_BLOCK_COLOR, block);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(METAL_CASING_ITEM_COLOR, block);
        });

    }

    public static void registerOreDict() {
        for (Map.Entry<IngotMaterial, GAMetalCasing> entry : METAL_CASING.entrySet()) {
            SolidMaterial material = entry.getKey();
            GAMetalCasing block = entry.getValue();
            for (int i = 0; i < 16; i++) {
                ItemStack itemStack = new ItemStack(block, 1, i);
                OreDictUnifier.registerOre(itemStack, OrePrefix.valueOf("gtMetalCasing"), material);
            }
        }
    }

    private static String statePropertiesToString(Map<IProperty<?>, Comparable<?>> properties) {
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
}
