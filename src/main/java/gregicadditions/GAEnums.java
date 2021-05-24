package gregicadditions;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.Element;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.MetaFluids;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.M;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.GENERATE_PLATE;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.NO_SMASHING;
import static gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_FRAME;

public class GAEnums {

    public static final List<OrePrefix> RICH_ORES = new ArrayList<>();
    public static final List<OrePrefix> POOR_ORES = new ArrayList<>();
    public static final List<OrePrefix> PURE_ORES = new ArrayList<>();

    public static class GAMaterialIconType {

        //nuclear stuff
        public final static MaterialIconType dioxide = createMaterialIconType("dioxide");
        public final static MaterialIconType nitride = createMaterialIconType("nitride");
        public final static MaterialIconType nitrite = createMaterialIconType("nitrite");
        public final static MaterialIconType hexafluoride = createMaterialIconType("hexafluoride");
        public final static MaterialIconType carbide = createMaterialIconType("carbide");
        public final static MaterialIconType zirconiumAlloy = createMaterialIconType("zirconiumAlloy");
        public final static MaterialIconType oxide = createMaterialIconType("oxide");
        public final static MaterialIconType nuclearFuel = createMaterialIconType("nuclearFuel");
        public final static MaterialIconType depletedFuel = createMaterialIconType("depletedFuel");
        public final static MaterialIconType fuelTRISO = createMaterialIconType("fuelTriso");
        public final static MaterialIconType depletedFuelTRISO = createMaterialIconType("depletedFuelTriso");

        /////////////////////////////////////


        public final static MaterialIconType gtMetalCasing = createMaterialIconType("gtMetalCasing");
        public final static MaterialIconType plateCurved = createMaterialIconType("plateCurved");
        public final static MaterialIconType ingotDouble = createMaterialIconType("ingotDouble");
        public final static MaterialIconType round = createMaterialIconType("round");
        public final static MaterialIconType plateDouble = createMaterialIconType("plateDouble");
        public final static MaterialIconType coke = createMaterialIconType("coke");
    }

    public static class GAOrePrefix {

        //nuclear stuff
        public final static OrePrefix dioxide = createOrePrefix("dioxide", "Dioxide", M / 3, null, GAMaterialIconType.dioxide, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix hexafluoride = createOrePrefix("hexafluoride", "Hexafluoride", M / 7, null, GAMaterialIconType.hexafluoride, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix nitrite = createOrePrefix("nitrite", "Nitrite", M / 2, null, GAMaterialIconType.nitrite, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix nitride = createOrePrefix("nitride", "Nitride", M / 2, null, GAMaterialIconType.nitride, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix carbide = createOrePrefix("carbide", "Carbide", M / 2, null, GAMaterialIconType.carbide, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix zirconiumAlloy = createOrePrefix("zirconiumAlloy", "Zirconium Alloy", M / 2, null, GAMaterialIconType.zirconiumAlloy, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix oxide = createOrePrefix("oxide", "Oxide", M / 2, null, GAMaterialIconType.oxide, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelNitride = createOrePrefix("fuelNitride", "Fuel Nitride", M / 2, null, GAMaterialIconType.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelCarbide = createOrePrefix("fuelCarbide", "Fuel Carbide", M / 2, null, GAMaterialIconType.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelZirconiumAlloy = createOrePrefix("fuelZirconiumAlloy", "Fuel Zirconium Alloy", M / 2, null, GAMaterialIconType.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelOxide = createOrePrefix("fuelOxide", "Fuel Oxide", M / 2, null, GAMaterialIconType.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelPure = createOrePrefix("fuelPure", "Pure Isotope", M, null, GAMaterialIconType.nuclearFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix fuelTRISO = createOrePrefix("fuelTRISO", "Fuel TRISO", M / 4, null, GAMaterialIconType.fuelTRISO, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix depletedFuelNitride = createOrePrefix("depletedFuelNitride", "Depleted Fuel Nitride", M / 2, null, GAMaterialIconType.depletedFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix depletedFuelZirconiumAlloy = createOrePrefix("depletedFuelZirconiumAlloy", "Depleted Fuel Zirconium Alloy", M / 2, null, GAMaterialIconType.depletedFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix depletedFuelOxide = createOrePrefix("depletedFuelOxide", "Depleted Fuel Oxide", M / 2, null, GAMaterialIconType.depletedFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix depletedFuelTRISO = createOrePrefix("depletedFuelTRISO", "Depleted Fuel TRISO", M / 4, null, GAMaterialIconType.depletedFuelTRISO, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        public final static OrePrefix depletedFuel = createOrePrefix("depletedFuel", "Depleted Fuel", M, null, GAMaterialIconType.depletedFuel, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_NUCLEAR_COMPOUND)));
        /////////////////////////////////////

        public final static OrePrefix gtMetalCasing = createOrePrefix("gtMetalCasing", "Metal Casing", Math.round(M * 6.375), null, GAMaterialIconType.gtMetalCasing, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_FRAME | GENERATE_PLATE)));
        public final static OrePrefix plateCurved = createOrePrefix("plateCurved", "Curved Plate", M, null, GAMaterialIconType.plateCurved, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_PLATE)));
        public final static OrePrefix round = createOrePrefix("round", "Round", M / 9, null, GAMaterialIconType.round, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && mat.hasFlag(GENERATE_ROUND)));
        public final static OrePrefix plateDouble = createOrePrefix("plateDouble", "Double Plate", M * 2, null, GAMaterialIconType.plateDouble, OrePrefix.Flags.ENABLE_UNIFICATION, pred(mat -> ingot.test(mat) && (mat.hasFlag(GENERATE_DOUBLE_PLATE) || ((IngotMaterial) mat).toolDurability != 0)));
        public final static OrePrefix opticalFiberHex = createOrePrefix("opticalFiberHex", "Hex optical fiber", M * 8, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);
        public final static OrePrefix opticalFiberOctal = createOrePrefix("opticalFiberOctal", "Octal optical fiber", M * 4, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);
        public final static OrePrefix opticalFiberQuadruple = createOrePrefix("opticalFiberQuadruple", "Quadruple optical fiber", M * 2, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);
        public final static OrePrefix opticalFiberDouble = createOrePrefix("opticalFiberDouble", "Double optical fiber", M, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);
        public final static OrePrefix opticalFiberSingle = createOrePrefix("opticalFiberSingle", "Single optical fiber", M / 2, null, null, OrePrefix.Flags.ENABLE_UNIFICATION, null);


    }

    public static void onConstruction() {
        EnumHelper.addEnum(Element.class, "Nt",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                0L, 5000L, -1L, null, "NEUTRONIUM", false);
        EnumHelper.addEnum(Element.class, "Ke",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                1000L, 1000L, -1L, null, "TRINIUM", false);//TODO calculate correct protons, electrons and neutrons
        EnumHelper.addEnum(Element.class, "Ad",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                1000L, 1000L, -1L, null, "ADAMANTIUM", false);
        EnumHelper.addEnum(Element.class, "Vb",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                1000L, 1000L, -1L, null, "VIBRANIUM", false);
        EnumHelper.addEnum(Element.class, "Tn",
                new Class[]{long.class, long.class, long.class, String.class, String.class, boolean.class},
                1000L, 1000L, -1L, null, "TARANIUM", false);

        EnumHelper.addEnum(MetaFluids.FluidState.class, "HOT", new Class[]{String.class}, "gregtech.fluid.hot");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXAFLUORIDE", new Class[]{String.class}, "gregtech.fluid.hexafluoride");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXACHLORIDE", new Class[]{String.class}, "gregtech.fluid.hexachloride");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "DEPLETED_FUEL_NITRATE_SOLUTION", new Class[]{String.class}, "gregtech.fluid.depleted_fuel_nitrate_solution");
        EnumHelper.addEnum(MetaFluids.FluidState.class, "HEXAFLUORIDE_STEAM_CRACKED", new Class[]{String.class}, "gregtech.fluid.hexafluoride_steam_cracked");
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HOT", new Class[]{String.class, Function.class}, "hot.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HOT"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXAFLUORIDE", new Class[]{String.class, Function.class}, "hexafluoride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXAFLUORIDE"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXACHLORIDE", new Class[]{String.class, Function.class}, "hexachloride.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXACHLORIDE"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "DEPLETED_FUEL_NITRATE_SOLUTION", new Class[]{String.class, Function.class}, "depleted_fuel_nitrate_solution.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("DEPLETED_FUEL_NITRATE_SOLUTION"));
        EnumHelper.addEnum(MetaFluids.FluidType.class, "HEXAFLUORIDE_STEAM_CRACKED", new Class[]{String.class, Function.class}, "hexafluoride_steam_cracked.", (Function<FluidMaterial, MetaFluids.FluidState>) material -> MetaFluids.FluidState.valueOf("HEXAFLUORIDE_STEAM_CRACKED"));

        String[] stoneTypes = {"", "Blackgranite", "Redgranite", "Marble", "Basalt", "Sand", "Gravel", "Netherrack", "Endstone"};
        Material[] secondaryMaterials = {Materials.Stone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Marble,
                Materials.Basalt, Materials.SiliconDioxide, Materials.Flint, Materials.Netherrack, Materials.Endstone};

        for (int i = 0; i < stoneTypes.length; i++) {
            EnumHelper.addEnum(MaterialIconType.class, "oreRich" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "oreRich" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Rich " + stoneTypes[i] + " Ore", M * 2, null, MaterialIconType.valueOf("oreRich" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("oreRich" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            RICH_ORES.add(OrePrefix.valueOf("oreRich" + stoneTypes[i]));

            EnumHelper.addEnum(MaterialIconType.class, "orePoor" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePoor" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Poor " + stoneTypes[i] + " Ore", M / 2, null, MaterialIconType.valueOf("orePoor" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePoor" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            POOR_ORES.add(OrePrefix.valueOf("orePoor" + stoneTypes[i]));

            EnumHelper.addEnum(MaterialIconType.class, "orePure" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePure" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Pure " + stoneTypes[i] + " Ore", M * 4, null, MaterialIconType.valueOf("orePure" + stoneTypes[i]), OrePrefix.Flags.ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePure" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            PURE_ORES.add(OrePrefix.valueOf("orePure" + stoneTypes[i]));
        }
    }

    public static void preInit() {
        // Turbine Overrides
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "STEAM_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.STEAM_TURBINE_FUELS, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING, true);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "GAS_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.GAS_TURBINE_FUELS, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN), Textures.CLEAN_STAINLESS_STEEL_CASING, false);
        EnumHelper.addEnum(MetaTileEntityLargeTurbine.TurbineType.class, "PLASMA_OVERRIDE",
                new Class[]{FuelRecipeMap.class, IBlockState.class, ICubeRenderer.class, boolean.class},
                RecipeMaps.PLASMA_GENERATOR_FUELS, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST), Textures.ROBUST_TUNGSTENSTEEL_CASING, true);
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

    public static MaterialIconType createMaterialIconType(String name) {
        EnumHelper.addEnum(MaterialIconType.class, name, new Class[0]);
        return MaterialIconType.valueOf(name);
    }

    public static OrePrefix createOrePrefix(String orePrefix, String categoryName, long materialAmount, Material material, MaterialIconType materialIconType, long flags, Predicate<Material> condition) {
        EnumHelper.addEnum(OrePrefix.class, orePrefix,
                new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                categoryName, materialAmount, material, materialIconType, flags, condition);

        return OrePrefix.valueOf(orePrefix);

    }

    public enum EnumIonsGroups {

        // +1
        AMMONIUM("NH4"),
        HYDRONIUM("H3O"),
        MERCURY1("Hg2"),

        // -1
        DHPHOSPHITE("H2PO3"),
        DHPHOSPHATE("H2PO4"),
        HCARBONATE("HCO3"),
        HSULFITE("HSO3"),
        HSULFATE("HSO4"),
        NITRITE("NO2"),
        NITRATE("NO3"),
        HYDROXIDE("OH"),
        ACETATE("CH3COO"),
        CHROMITE("CrO2"),
        CYANIDE("CN"),
        CYANATE("CNO"),
        THIOCYANATE("CNS"),
        PERMANGANATE("MnO4"),
        HYPOCHLORITE("ClO"),
        CHLORITE("ClO2"),
        CHLORATE("ClO3"),
        PERCHLORATE("ClO4"),
        HYPOBROMITE("BrO"),
        BROMITE("BrO2"),
        BROMATE("BrO3"),
        PERBROMATE("BrO4"),
        HYPOIODITE("IO"),
        IODITE("IO2"),
        IODATE("IO3"),
        PERIODATE("IO4"),
        AZIDE("N3"),

        // -2
        HPHOSPHITE("HPO3"),
        HPHOSPHATE("HPO4"),
        CARBONATE("CO3"),
        SULFITE("SO3"),
        SULFATE("SO4"),
        THIOSULFATE("S2O3"),
        OXALATE("C2O4"),
        CHROMATE("CrO4"),
        DICHROMATE("Cr2O7"),
        DISULFIDE("S2"),
        SULFIDE("S"),

        // -3
        PHOSPHITE("PO3"),
        ARSENITE("AsO3"),
        ARSENATE("AsO4"),
        NITRIDE("N3"),

        // Oxidation states
        OXIDE("O"),
        OXIDE2("O2"),
        OXIDE3("O3"),
        OXIDE4("O4"),
        OXIDE5("O5"),
        OXIDE6("O6"),
        OXIDE7("O7"),
        OXIDE8("O8"),

        // Misc
        WATER("H2O"),
        PHOSPHATE("PO4"),
        AMMONIA("NH3"),

        // Simple OChem functional groups
        ALCOHOL("OH"),
        THIOL("SH"),
        ALDEHYDE("COH"),
        KETONE("COCH3"),
        CARBOXYLIC("COOH"),
        NITRILE("CN"),
        NITRO("NO2");

        private final String FORMULA;

        EnumIonsGroups(String formula) {
            this.FORMULA = formula;
        }

        public String formula() {
            return this.FORMULA;
        }

        public String formulaGroup() {
            return "(" + this.FORMULA + ")";
        }

        public String formulaGroup(int count) {
            return "(" + this.FORMULA + ")" + count;
        }
    }
}
