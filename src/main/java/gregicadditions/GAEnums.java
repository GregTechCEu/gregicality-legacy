package gregicadditions;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.MetaFluids;
import gregtech.common.items.MetaItems;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.M;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE;
import static gregtech.api.unification.ore.OrePrefix.Conditions.hasIngotProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

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
    }

    public static class GAOrePrefix {

        //nuclear stuff
        public static final OrePrefix dioxide = new OrePrefix("dioxide", M / 3, null, GAMaterialIconType.dioxide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix hexafluoride = new OrePrefix("hexafluoride", M / 7, null, GAMaterialIconType.hexafluoride, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix nitrite = new OrePrefix("nitrite", M / 2, null, GAMaterialIconType.nitrite, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix nitride = new OrePrefix("nitride", M / 2, null, GAMaterialIconType.nitride, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix carbide = new OrePrefix("carbide", M / 2, null, GAMaterialIconType.carbide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix zirconiumAlloy = new OrePrefix("zirconiumAlloy", M / 2, null, GAMaterialIconType.zirconiumAlloy, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix oxide = new OrePrefix("oxide", M / 2, null, GAMaterialIconType.oxide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelNitride = new OrePrefix("fuelNitride", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelCarbide = new OrePrefix("fuelCarbide", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelZirconiumAlloy = new OrePrefix("fuelZirconiumAlloy", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelOxide = new OrePrefix("fuelOxide", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelPure = new OrePrefix("fuelPure", M, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix fuelTRISO = new OrePrefix("fuelTRISO", M / 4, null, GAMaterialIconType.fuelTRISO, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix depletedFuelNitride = new OrePrefix("depletedFuelNitride", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix depletedFuelZirconiumAlloy = new OrePrefix("depletedFuelZirconiumAlloy", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix depletedFuelOxide = new OrePrefix("depletedFuelOxide", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix depletedFuelTRISO = new OrePrefix("depletedFuelTRISO", M / 4, null, GAMaterialIconType.depletedFuelTRISO, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
        public static final OrePrefix depletedFuel = new OrePrefix("depletedFuel", M, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));

        public static final OrePrefix opticalFiberHex = new OrePrefix("opticalFiberHex", M * 8, null, null, ENABLE_UNIFICATION, null);
        public static final OrePrefix opticalFiberOctal = new OrePrefix("opticalFiberOctal", M * 4, null, null, ENABLE_UNIFICATION, null);
        public static final OrePrefix opticalFiberQuadruple = new OrePrefix("opticalFiberQuadruple", M * 2, null, null, ENABLE_UNIFICATION, null);
        public static final OrePrefix opticalFiberDouble = new OrePrefix("opticalFiberDouble", M, null, null, ENABLE_UNIFICATION, null);
        public static final OrePrefix opticalFiberSingle = new OrePrefix("opticalFiberSingle", M / 2, null, null, ENABLE_UNIFICATION, null);
    }

    public static void onConstruction() {

        if (GAConfig.GT6.addCurvedPlates) OrePrefix.plateCurved.setGenerationCondition(mat -> mat.hasFlag(GENERATE_PLATE));
        MetaItems.addOrePrefix(dioxide, hexafluoride, nitrite, nitride, carbide, zirconiumAlloy, oxide,
                fuelNitride, fuelCarbide, fuelZirconiumAlloy, fuelOxide, fuelPure, fuelTRISO,
                depletedFuelNitride, depletedFuelZirconiumAlloy, depletedFuelOxide, depletedFuelTRISO, depletedFuel);

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
                    "Rich " + stoneTypes[i] + " Ore", M * 2, null, MaterialIconType.valueOf("oreRich" + stoneTypes[i]), ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("oreRich" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            RICH_ORES.add(OrePrefix.valueOf("oreRich" + stoneTypes[i]));

            EnumHelper.addEnum(MaterialIconType.class, "orePoor" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePoor" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Poor " + stoneTypes[i] + " Ore", M / 2, null, MaterialIconType.valueOf("orePoor" + stoneTypes[i]), ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePoor" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            POOR_ORES.add(OrePrefix.valueOf("orePoor" + stoneTypes[i]));

            EnumHelper.addEnum(MaterialIconType.class, "orePure" + stoneTypes[i], new Class[0]);
            EnumHelper.addEnum(OrePrefix.class, "orePure" + stoneTypes[i],
                    new Class[]{String.class, long.class, Material.class, MaterialIconType.class, long.class, Predicate.class},
                    "Pure " + stoneTypes[i] + " Ore", M * 4, null, MaterialIconType.valueOf("orePure" + stoneTypes[i]), ENABLE_UNIFICATION, null);
            OrePrefix.valueOf("orePure" + stoneTypes[i]).addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            PURE_ORES.add(OrePrefix.valueOf("orePure" + stoneTypes[i]));
        }
    }

    public static final Predicate<Material> dust = mat -> mat.hasProperty(PropertyKey.DUST);
    public static final Predicate<Material> ingot = mat -> mat.hasProperty(PropertyKey.INGOT);
    public static final Predicate<Material> isNuclear = mat -> mat.hasFlag(GENERATE_NUCLEAR_COMPOUND);

    public static MaterialIconType createMaterialIconType(String name) {
        EnumHelper.addEnum(MaterialIconType.class, name, new Class[0]);
        return MaterialIconType.valueOf(name);
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
