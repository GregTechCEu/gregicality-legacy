package gregicadditions.integrations.exnihilocreatio.items;

import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.common.util.EnumHelper;

import java.util.function.Predicate;

public class ExNihiloEnums {
    public static void preInit() {
        EnumHelper.addEnum(MaterialIconType.class, "oreChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreEnderChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreNetherChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "oreSandyChunk", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "plateDouble", new Class[0]);

        EnumHelper.addEnum(OrePrefix.class, "oreChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Ore Chunk", -1L, null, MaterialIconType.valueOf("oreChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreEnderChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Ender Ore Chunk", -1L, null, MaterialIconType.valueOf("oreEnderChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreNetherChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Nether Ore Chunk", -1L, null, MaterialIconType.valueOf("oreNetherChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "oreSandyChunk",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Sandy Ore Chunk", -1L, null, MaterialIconType.valueOf("oreSandyChunk"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

        EnumHelper.addEnum(OrePrefix.class, "plateDouble",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Double Plate", -1L, null, MaterialIconType.valueOf("plateDouble"), OrePrefix.Flags.ENABLE_UNIFICATION | OrePrefix.Flags.DISALLOW_RECYCLING,
                pred((mat) ->
                        mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)));

    }

    private static Predicate<Material> pred(Predicate<Material> in) {
        return (mat) -> in.test(mat);
    }
}