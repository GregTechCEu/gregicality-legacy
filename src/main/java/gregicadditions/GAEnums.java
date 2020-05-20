package gregicadditions;

import gregtech.api.unification.Element;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.MetaFluids;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.function.Function;

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


        if (GAConfig.GT6.addCurvedPlates) {
            EnumHelper.addEnum(MaterialIconType.class, "plateCurved", new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "plateCurved",
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Curved Plate", M, null, MaterialIconType.valueOf("plateCurved"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_PLATE)));
        }

        if (GAConfig.GT6.addDoubleIngots) {
            EnumHelper.addEnum(MaterialIconType.class, "ingotDouble", new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "ingotDouble",
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Double Ingot", M * 2, null, MaterialIconType.valueOf("ingotDouble"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_PLATE)));
        }

        if (GAConfig.GT6.addRounds) {
            EnumHelper.addEnum(MaterialIconType.class, "round", new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "round",
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Round", M / 9, null, MaterialIconType.valueOf("round"), OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(IngotMaterial.MatFlags.GENERATE_SMALL_GEAR)));
        }

        EnumHelper.addEnum(MetaFluids.FluidState.class, "HOT", new Class[]{String.class}, "gregtech.fluid.hot");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXAFLUORIDE", new Class[]{String.class}, "gregtech.fluid.hexafluoride");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXACHLORIDE", new Class[]{String.class}, "gregtech.fluid.hexachloride");
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HOT", new Class[]{String.class, Function.class}, "hot.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HOT"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXAFLUORIDE", new Class[]{String.class, Function.class}, "hexafluoride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXAFLUORIDE"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXACHLORIDE", new Class[]{String.class, Function.class}, "hexachloride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXACHLORIDE"));
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
