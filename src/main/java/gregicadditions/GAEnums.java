package gregicadditions;

import gregtech.api.unification.Element;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.MetaFluids;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Function;

import static gregicadditions.GAMaterials.GENERATE_NUCLEAR_COMPOUND;
import static gregtech.api.GTValues.M;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.GENERATE_PLATE;
import static gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_FRAME;

public class GAEnums {

    public static void preInit() {
        EnumHelper.addEnum(Element.class, "Nt",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                0L, 5000L, -1L, null, "NEUTRONIUM", false);
        EnumHelper.addEnum(MaterialIconSet.class, "COKE", new Class[0]);
        EnumHelper.addEnum(MaterialIconType.class, "gtMetalCasing", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "gtMetalCasing",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Metal Casing", Math.round(M * 6.375), null, MaterialIconType.valueOf("gtMetalCasing"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_FRAME | GENERATE_PLATE)));

        EnumHelper.addEnum(MaterialIconType.class, "dioxide", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "dioxide",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Dioxide", M, null, MaterialIconType.valueOf("dioxide"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        EnumHelper.addEnum(MaterialIconType.class, "nitrate", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "nitrate",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Nitrate", M, null, MaterialIconType.valueOf("nitrate"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        EnumHelper.addEnum(MaterialIconType.class, "hexafluoride", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "hexafluoride",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Hexafluoride", M, null, MaterialIconType.valueOf("hexafluoride"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));


        EnumHelper.addEnum(MaterialIconType.class, "plateCurved", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "plateCurved",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Curved Plate", M, null, MaterialIconType.valueOf("plateCurved"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_PLATE)));

        EnumHelper.addEnum(MaterialIconType.class, "ingotDouble", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "ingotDouble",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Double Ingot", M * 2, null, MaterialIconType.valueOf("ingotDouble"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_PLATE)));

        EnumHelper.addEnum(MaterialIconType.class, "round", new Class[0]);
        EnumHelper.addEnum(OrePrefix.class, "round",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Round", M / 9, null, MaterialIconType.valueOf("round"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(IngotMaterial.MatFlags.GENERATE_SMALL_GEAR)));

        EnumHelper.addEnum(MetaFluids.FluidState.class, "HOT", new Class[]{String.class}, "gregtech.fluid.hot");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXAFLUORIDE", new Class[]{String.class}, "gregtech.fluid.hexafluoride");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXACHLORIDE", new Class[]{String.class}, "gregtech.fluid.hexachloride");
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HOT", new Class[]{String.class, Function.class}, "hot.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HOT"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXAFLUORIDE", new Class[]{String.class, Function.class}, "hexafluoride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXAFLUORIDE"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXACHLORIDE", new Class[]{String.class, Function.class}, "hexachloride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXACHLORIDE"));

        String[] stoneTypes = {"", "Blackgranite", "Redgranite", "Marble", "Basalt", "Sand", "Gravel", "Netherrack", "Endstone"};
        Material[] secondaryMaterials = {Materials.Stone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Marble,
                Materials.Basalt, Materials.SiliconDioxide, Materials.Flint, Materials.Netherrack, Materials.Endstone};

        for (int i = 0; i < stoneTypes.length; i++) {
            EnumHelper.addEnum(MaterialIconType.class, "oreRich" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "oreRich" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Rich " + stoneTypes[i] + " Ore", M * 2, null, MaterialIconType.valueOf("oreRich" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("oreRich" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));

            EnumHelper.addEnum(MaterialIconType.class, "orePoor" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePoor" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Poor " + stoneTypes[i] + " Ore", M / 2, null, MaterialIconType.valueOf("orePoor" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePoor" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));


            EnumHelper.addEnum(MaterialIconType.class, "orePure" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePure" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Pure " + stoneTypes[i] + " Ore", M * 4, null, MaterialIconType.valueOf("orePure" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePure" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));

        }


        EnumHelper.addEnum(OrePrefix.class, "opticalFiberHex",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Hex optical fiber", 29030400L, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);

        EnumHelper.addEnum(OrePrefix.class, "opticalFiberOctal",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Octal optical fiber", 29030400L, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);

        EnumHelper.addEnum(OrePrefix.class, "opticalFiberQuadruple",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Quadruple optical fiber", 29030400L, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);

        EnumHelper.addEnum(OrePrefix.class, "opticalFiberDouble",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Double optical fiber", 29030400L, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);

        EnumHelper.addEnum(OrePrefix.class, "opticalFiberSingle",
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                "Single optical fiber", 29030400L, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);


    }

    public static final Predicate<Material> dust = mat -> mat instanceof DustMaterial;
    public static final Predicate<Material> ingot = mat -> mat instanceof IngotMaterial;
    public static final HashMap<String, Integer> voltageMap = new HashMap<String, Integer>() {
        {
            put("lv", 32);
            put("mv", 128);
            put("hv", 512);
            put("ev", 2048);
            put("iv", 8192);
            put("luv", 32768);
            put("zpm", 131072);
            put("uv", 524288);
            put("max", Integer.MAX_VALUE);

        }
    };

    private static Predicate<Material> pred(Predicate<Material> in) {
        return mat -> in.test(mat);
    }
}
