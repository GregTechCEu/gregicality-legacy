package gregicadditions.materials;

import gregicadditions.GAMaterials;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.stack.MaterialStack;

import static com.google.common.collect.ImmutableList.of;
import static gregicadditions.GAMaterials.GA_CORE_METAL;
import static gregicadditions.GAMaterials.MetastableHassium;
import static gregtech.api.unification.Elements.Hs;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

// Organic Chemistry Materials
// IDs: 6500-9499

public class GAOrganicChemistryMaterials {

    public static void register() {
        Anthracene = new Material.Builder(6500, "anthracene")
                .fluid()
                .color(0xA2ACA2)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 10)
                .build();

        MonoMethylHydrazine = new Material.Builder(6501, "monomethylhydrazine")
                .fluid()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
                .build();

        EthylAnthraQuinone = new Material.Builder(6502, "ethylanthraquinone")
                .fluid()
                .color(0xFFFF00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
                .build();

        EthylAnthraHydroQuinone = new Material.Builder(6503, "ethylanthrahydroquinone")
                .fluid()
                .color(0xFFFF47)
                .flags(DISABLE_DECOMPOSITION)
                .components(EthylAnthraQuinone, 1, Hydrogen, 2)
                .build();

        DenseHydrazineFuelMixture = new Material.Builder(6504, "dense_hydrazine_fuel_mixture")
                .fluid()
                .color(0x5E2B4A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrazine, 1, Methanol, 2)
                .build();

        RocketFuelCN3H7O3 = new Material.Builder(6505, "rocket_fuel_b")
                .fluid()
                .color(0xBE46C5)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Nitrogen, 3, Hydrogen, 7, Oxygen, 3)
                .build();

        SodiumFormate = new Material.Builder(6506, "sodium_formate")
                .fluid()
                .color(0xFFAAAA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Carbon, 1, Oxygen, 2, Sodium, 1)
                .build()
                .setFormula("HCOONa", true);

        FormicAcid = new Material.Builder(6507, "formic_acid")
                .fluid()
                .color(0xFFAA77)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 2, Oxygen, 2)
                .build();

        PhthalicAnhydride = new Material.Builder(6508, "phthalicanhydride")
                .dust()
                .color(0xFFAA77).iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
                .build();

        Polyetheretherketone = new Material.Builder(6509, "polyetheretherketone")
                .ingot()
                .color(0x403E37).iconSet(DULL)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, DISABLE_DECOMPOSITION, GENERATE_FINE_WIRE)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
                .build();

        Zylon = new Material.Builder(6510, "zylon")
                .ingot()
                .color(0xFFE000).iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build();

        FullerenePolymerMatrix = new Material.Builder(6511, "fullerene_polymer_matrix")
                .ingot()
                .color(0x403E37).iconSet(DULL)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Carbon, 140, Hydrogen, 21, Oxygen, 2)
                .build();

        CarbonNanotubes = new Material.Builder(6512, "carbon_nanotubes")
                .ingot()
                .color(0x2c2c2c).iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 1)
                .build();

        Polyurethane = new Material.Builder(6513, "polyurethane")
                .ingot()
                .color(0x2c2c2c).iconSet(DULL)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 17, Hydrogen, 16, Nitrogen, 2, Oxygen, 4)
                .build();

        PEDOT = new Material.Builder(6514, "pedot")
                .ingot()
                .color(0x5cef20).iconSet(DULL)
                .flags(GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .cableProperties(GTValues.V[GTValues.ZPM], 6, 4)
                .build();

        Butyraldehyde = new Material.Builder(6515, "butyraldehyde")
                .fluid()
                .color(0xe7cf6e)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 18, Oxygen, 1)
                .build()
                .setFormula("CH3(CH2)3CH(CH2CH3)CH2OH", true);

        Ethylhexanol = new Material.Builder(6516,"ethylhexanol")
                .fluid()
                .color(0xfeea9a)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
                .build()
                .setFormula("C8H10O",true);

        DiethylhexylPhosphoricAcid = new Material.Builder(6517, "di_ethylhexyl_phosphoric_acid")
                .fluid()
                .color(0xffff99)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
                .build()
                .setFormula("C16H35O4P", true);

        Turpentine = new Material.Builder(6518, "turpentine")
                .fluid()
                .color(0x93bd46)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 16)
                .build()
                .setFormula("C10H16", true);

        Acetylene = new Material.Builder(6519, "acetylene")
                .fluid()
                .color(0x959c60)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 2)
                .build()
                .setFormula("C2H2", true);

        Formaldehyde = new Material.Builder(6520, "formaldehyde")
                .fluid()
                .color(0x95a13a)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 2, Oxygen, 1)
                .build()
                .setFormula("CH2O", true);

        PropargylAlcohol = new Material.Builder(6521, "propargyl_alcohol")
                .fluid()
                .color(0xbfb32a)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 4, Oxygen, 1)
                .build()
                .setFormula("HCCCH2OH", true);

        PropargylChloride = new Material.Builder(6522, "propargyl_chloride")
                .fluid()
                .color(0x918924)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 3, Chlorine, 1)
                .build()
                .setFormula("HCCCH2Cl", true);

        Citral = new Material.Builder(6523, "citral")
                .fluid()
                .color(0xf2e541)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 16, Oxygen, 1)
                .build()
                .setFormula("C10H16O", true);

        BetaIonone = new Material.Builder(6524, "beta_ionone")
                .fluid()
                .color(0xdc5ce6)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 13, Hydrogen, 20, Oxygen, 1)
                .build()
                .setFormula("C13H20O", true);

        VitaminA = new Material.Builder(6525, "vitamin_a")
                .fluid()
                .color(0x8d5c91)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 20, Hydrogen, 30, Oxygen, 1)
                .build()
                .setFormula("C20H30O", true);

        EthyleneOxide = new Material.Builder(6526, "ethylene_oxide")
                .fluid()
                .color(0xa0c3de)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .build()
                .setFormula("C2H4O", true);

        Ethanolamine = new Material.Builder(6527, "ethanolamine")
                .fluid()
                .color(0x6f7d87)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 7, Oxygen, 1, Nitrogen, 1)
                .build()
                .setFormula("HOCH2CH2NH2", true);

        Biotin = new Material.Builder(6528, "biotin")
                .fluid()
                .color(0x68cc6a)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 16, Oxygen, 3, Nitrogen, 2, Sulfur, 1)
                .build()
                .setFormula("C10H16N2O3S", true);

        B27Supplement = new Material.Builder(6529, "biotin")
                .fluid()
                .color(0x386939)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 142, Hydrogen, 230, Oxygen, 44, Nitrogen, 36, Sulfur, 1)
                .build()
                .setFormula("C142H230N36O44S", true);

        EGF = new Material.Builder(6530, "egf")
                .fluid()
                .color(0x815799)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 257, Hydrogen, 381, Oxygen, 83, Nitrogen, 73, Sulfur, 7)
                .build()
                .setFormula("C257H381N73O83S7", true);

        Aniline = new Material.Builder(6531, "aniline")
                .fluid()
                .color(0x4c911d)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
                .build()
                .setFormula("C6H5NH2", true);

        Sulfanilamide = new Material.Builder(6532, "sulfanilamide")
                .fluid()
                .color(0x523b0a)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 8, Oxygen, 2, Nitrogen, 2, Sulfur, 1)
                .build()
                .setFormula("C6H8N2O2S", true);

        Ethanol100 = new Material.Builder(6533, "ethanol_100")
                .fluid()
                .color(Ethanol.getMaterialRGB())
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 6, Oxygen, 1)
                .build()
                .setFormula("C2H5OH", true);

        LinoleicAcid = new Material.Builder(6534, "linoleic_acid")
                .fluid()
                .color(0xD5D257)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 32, Oxygen, 2)
                .build()
                .setFormula("C18H32O2", true);

        CarbonTetrafluoride = new Material.Builder(6535, "carbon_tetrafluoride")
                .fluid()
                .color(0xE6E6E6)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Fluorine, 4)
                .build()
                .setFormula("CF4", true);

        TributylPhosphate = new Material.Builder(6536, "tributyl_phosphate")
                .fluid()
                .color(0x7C5B2C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 27, Oxygen, 4, Phosphorus, 1)
                .build()
                .setFormula("(C4H9)3PO4", true);

        Butanol = new Material.Builder(6537, "butanol")
                .fluid()
                .color(FermentedBiomass.getMaterialRGB()+20)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .build()
                .setFormula("C4H9OH", true);

        HydrogenCyanide = new Material.Builder(6538, "hydrogen_cyanide")
                .fluid()
                .color(0xb6d1ae)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 1, Nitrogen, 1)
                .build()
                .setFormula("HCN", true);

        SodiumCyanide = new Material.Builder(6539, "sodium_cyanide")
                .dust()
                .color(0x5f7c8c)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Carbon, 1, Nitrogen, 1)
                .build()
                .setFormula("NaCN", true);

        GoldCyanide = new Material.Builder(6540, "gold_cyanide")
                .fluid()
                .color(0x8c8761)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Gold, 1, Nitrogen, 1)
                .build()
                .setFormula("AuCN", true);

        BenzenediazoniumTetrafluoroborate = new Material.Builder(6541, "benzenediazonium_tetrafluoroborate")
                .fluid()
                .color(0xD5D2D7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 5, Nitrogen, 2, Boron, 1, Fluorine, 4)
                .build()
                .setFormula("C6H5BF4N2", true);

        FluoroBenzene = new Material.Builder(6542, "fluoro_benzene")
                .fluid()
                .color(0xD5D2D7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 5, Fluorine, 1)
                .build()
                .setFormula("C6H5F", true);

        Fluorotoluene = new Material.Builder(6543, "fluorotoluene")
                .fluid()
                .color(0xE0DA99)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 7, Fluorine, 1)
                .build()
                .setFormula("C7H7F", true);

        OrthoXylene = new Material.Builder(6544, "orthoxylene")
                .fluid()
                .color(0xB9575E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 10)
                .build()
                .setFormula("C6H4(CH3)2", true);

        OrthoXyleneZeoliteMixture = new Material.Builder(6545, "ortho_xylene_zeolite")
                .fluid()
                .color(0xB9575E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zeolite, 1, Carbon, 8, Hydrogen, 10)
                .build()
                .setFormula("(NaC4Si27Al9(H2O)28O72)C6H4(CH3)2", true);

        ParaXylene = new Material.Builder(6546, "para_xylene")
                .fluid()
                .color(0xB9575E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 10)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Dibromomethylbenzene = new Material.Builder(6547, "dibromomethylbenzene")
                .fluid()
                .color(0x0A1D2C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 6, Bromine, 2)
                .build()
                .setFormula("C7H6Br2", true);

        AceticAnhydride = new Material.Builder(6548, "acetic_anhydride")
                .fluid()
                .color(0xD5DDDF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 6, Oxygen, 3)
                .build()
                .setFormula("(CH3CO)2O", true);

        Isochloropropane = new Material.Builder(6549, "isochloropropane")
                .fluid()
                .color(0xD5DD95)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
                .build()
                .setFormula("CH3CClCH3", true);

        Resorcinol = new Material.Builder(6550, "resorcinol")
                .fluid()
                .color(0xD5DDBE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
                .build()
                .setFormula("C6H6O2", true);

        Dinitrodipropanyloxybenzene = new Material.Builder(6551, "dinitrodipropanyloxybenzene")
                .fluid()
                .color(0x83945F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 16, Oxygen, 2, NitrogenDioxide, 2)
                .build()
                .setFormula("C12H16O2(NO2)2", true);

        Naphthaldehyde = new Material.Builder(6552, "napthaldehyde")
                .fluid()
                .color(0xBCA853)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 11, Hydrogen, 7, Oxygen, 1)
                .build()
                .setFormula("C10H7CHO", true);

        Diisopropylcarbodiimide = new Material.Builder(6553, "diisopropylcarbodiimide")
                .fluid()
                .color(0xA0CFFE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 14, Nitrogen, 2)
                .build()
                .setFormula("C7H14N2", true);

        Pyridine = new Material.Builder(6554, "pyridine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 5, Nitrogen, 1)
                .build()
                .setFormula("C5H5N", true);

        Phenylpentanoicacid = new Material.Builder(6555, "phenylpentanoicacid")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 11, Hydrogen, 14, Oxygen, 2)
                .build()
                .setFormula("C11H14O2", true);

        Dimethylsulfide = new Material.Builder(6556, "dimethylsulfide")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 6, Sulfur, 1)
                .build()
                .setFormula("(CH3)2S", true);

        BenzoylChloride = new Material.Builder(6557, "benzoyl_chloride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 5, Chlorine, 1, Oxygen, 1)
                .build()
                .setFormula("C7H5ClO", true);

        PCBA = new Material.Builder(6558, "pcba")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Carbon, 12, Hydrogen, 14, Oxygen, 2)
                .build()
                .setFormula("C72H14O2", true);

        PCBS = new Material.Builder(6559, "pcbs")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Carbon, 20, Hydrogen, 21, Oxygen, 2)
                .build()
                .setFormula("C80H21O2", true);

        Ferrocene = new Material.Builder(6560, "ferrocene")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 10, Iron, 1)
                .build()
                .setFormula("C10H10Fe", true);

        Ferrocenylfulleropyrrolidine = new Material.Builder(6561, "ferrocenylfulleropyrddolidine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Carbon, 14, Hydrogen, 19, Iron, 1, Nitrogen, 1)
                .build()
                .setFormula("C74H19FeN", true);

        Hydroquinone = new Material.Builder(6562, "hydroquinone")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
                .build()
                .setFormula("C6H4(OH)2", true);

        SodiumAcetate = new Material.Builder(6563, "sodium_acetate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 3, Sodium, 1, Oxygen, 2)
                .build()
                .setFormula("C2H3NaO2", true);

        Methylamine = new Material.Builder(6564, "methylamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 5, Nitrogen, 1)
                .build()
                .setFormula("CH3NH2", true);

        IsopropylAlcohol = new Material.Builder(6565, "isopropyl_alcohol")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 8, Oxygen, 1)
                .build()
                .setFormula("C3H8O", true);

        RichNitrogenMix = new Material.Builder(6566, "rich_nitrogen_mix")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Methane, 1, RareEarth, 1)
                .build()
                .setFormula("H2O(CH4)?", true);

        OxidisedNitrogenMix = new Material.Builder(6567, "oxidised_nitrogen_mix")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 2, Methane, 1, RareEarth, 2)
                .build()
                .setFormula("(H2O)2(CH4)??", true);

        PurifiedNitrogenMix = new Material.Builder(6568, "purified_nitrogen_mix")
                .fluid()
                .color(0x6891D8)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 2, Methane, 1, RareEarth, 1)
                .build()
                .setFormula("(H2O)2(CH4)?", true);

        CarbonatedEthanolamine = new Material.Builder(6569, "carbonated_ethanolamine")
                .fluid()
                .color(0x6F7D87)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 7, Nitrogen, 1, Carbon, 3)
                .build()
                .setFormula("H2NCH2CH2OHC", true);

        AmmoniaRichMix = new Material.Builder(6570, "ammonia_rich_mix")
                .fluid()
                .color(0x2F5D99)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ammonia, 1, PurifiedNitrogenMix, 1)
                .build()
                .setFormula("NH3((H2O)2(CH4)?)", true);

        KeroseneIodineSolution = new Material.Builder(6571, "kerosene_iodine_solution")
                .fluid()
                .color(0x08081C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Kerosene, 1, Iodine, 1)
                .build()
                .setFormula("C12H26I", true);

        MesitylOxide = new Material.Builder(6572, "mesityl_oxide")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 10, Oxygen, 1)
                .build();

        MethylIsobutylKetone = new Material.Builder(6573, "methyl_isobutyl_ketone")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 1)
                .build();

        ThiocyanicAcid = new Material.Builder(6574, "thiocyanic_acid")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Sulfur, 1, Carbon, 1, Nitrogen, 1)
                .build();

        Diethylether = new Material.Builder(6575, "diethylether")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .build()
                .setFormula("(C2H5)2O", true);

        TannicAcid = new Material.Builder(6576, "tannic_acid")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 76, Hydrogen, 52, Oxygen, 46)
                .build();

        AcryloNitrile = new Material.Builder(6577, "acrylonitrile")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 3, Nitrogen, 1)
                .build()
                .setFormula("CH2CHCN", true);

        SodiumThiocyanate = new Material.Builder(6578, "sodium_thiocyanate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Sulfur, 1, Carbon, 1, Nitrogen, 1)
                .build();

        PolyacrylonitrileSolution = new Material.Builder(6579, "polyacrylonitrile_solution")
                .fluid()
                .color(0x9999FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(AcryloNitrile, 1, SodiumThiocyanate, 1)
                .build()
                .setFormula("(C3H3N)n(NaSCN)", true);

        MethylFormate = new Material.Builder(6580, "methyl_formate")
                .fluid()
                .color(0xFF9999)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 4, Carbon, 2, Oxygen, 2)
                .build()
                .setFormula("HCOOCH3", true);

        Formamide = new Material.Builder(6581, "formamide")
                .fluid()
                .color(0x33CCFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 3, Nitrogen, 1, Oxygen, 1)
                .build()
                .setFormula("CH3NO", true);

        WetFormamide = new Material.Builder(6582, "wet_formamide")
                .fluid()
                .color(0x33BBFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Formamide, 1)
                .build()
                .setFormula("(H2O)CH3NO", true);

        AmineMixture = new Material.Builder(6583, "amine_mixture")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Ammonia, 1, Methane, 1)
                .build()
                .setFormula("(NH3)CH4", true);

        DimethylthiocarbamoilChloride = new Material.Builder(6584, "dimethylthiocarbamoil_chloride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 6, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
                .build()
                .setFormula("(CH3)2NC(S)Cl", true);

        Trimethylamine = new Material.Builder(6585, "trimetylamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 9, Nitrogen, 1)
                .build()
                .setFormula("(CH3)3N", true);

        Mercaphenol = new Material.Builder(6586, "mercaphenol")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 1, Sulfur, 1)
                .build()
                .setFormula("C6H6OS", true);

        Dimethylformamide = new Material.Builder(6587, "dimethylformamide")
                .fluid()
                .color(0x42BDFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 7, Nitrogen, 1)
                .build()
                .setFormula("(CH3)2NCH", true);

        HydrogenCrackedDMF = new Material.Builder(6588, "hydrogen_cracked_dmf")
                .fluid()
                .color(0x62BDFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 7, Oxygen, 1, Sulfur, 1)
                .build()
                .setFormula("(C6H6OS)H", true);

        Oct1ene = new Material.Builder(6589, "1_octene")
                .fluid()
                .color(0x62BDFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 16)
                .build();

        CetaneTrimethylAmmoniumBromide = new Material.Builder(6590, "cetane_trimethyl_ammonium_bromide")
                .fluid()
                .color(0xB9C1C9)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 19, Hydrogen, 32, Bromine, 1, Nitrogen, 1)
                .build()
                .setFormula("C19H42BrN", true);

        Acetaldehyde = new Material.Builder(6591, "acetaldehyde")
                .fluid()
                .color(0xFF9933)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .build();

        Benzaldehyde = new Material.Builder(6592, "benzaldehyde")
                .fluid()
                .color(0xB26F22)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 6, Oxygen, 1)
                .build();

        Dibenzylideneacetone = new Material.Builder(6593, "dibenzylideneacetone")
                .fluid()
                .color(0xCC6699)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 17, Hydrogen, 14, Oxygen, 1)
                .build();

        TrimethyltinChloride = new Material.Builder(6594, "trimethyltin_chloride")
                .fluid()
                .color(0x8C8075)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 9, Tin, 1, Chlorine, 1)
                .build()
                .setFormula("(CH3)3SnCl", true);

        Cyclooctadiene = new Material.Builder(6595, "cyclooctadiene")
                .fluid()
                .color(0x33CC33)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 12)
                .build();

        NbTaSeparationMixture = new Material.Builder(6596, "nbta_separation_mixture")
                .fluid()
                .color(0xBCAC93)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 39, Oxygen, 5, Phosphorus)
                .build();

        WetEthyleneOxide = new Material.Builder(6597, "wet_etylene_oxide")
                .fluid()
                .color(0x90B3FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .build();

        EthyleneGlycol = new Material.Builder(6598, "ethylene_glycol")
                .fluid()
                .color(0x8080FA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 6, Oxygen, 2)
                .build();

        Chloroethanol = new Material.Builder(6599, "chloroethanol")
                .fluid()
                .color(0xCFB050)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Chlorine, 1, Oxygen, 1)
                .build();

        Choline = new Material.Builder(6600, "choline")
                .fluid()
                .color(0x63E45F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 14, Nitrogen, 1, Oxygen, 1)
                .build();

        ViscoelasticPolyurethane = new Material.Builder(6601, "viscoelastic_polyurethane")
                .fluid()
                .color(0xEFFCEF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 17, Hydrogen, 16, Nitrogen, 2, Oxygen, 4)
                .build();

        ViscoelasticPolyurethaneFoam = new Material.Builder(6602, "viscoelastic_polyurethane_foam")
                .fluid()
                .color(0xEFFEEF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 17, Hydrogen, 16, Nitrogen, 2, Oxygen, 4, RareEarth, 1)
                .build();

        TolueneDiisocyanate = new Material.Builder(6603, "toluene_diisocyanate")
                .fluid()
                .color(0xBAF6CA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 9, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build();

        Aminophenol = new Material.Builder(6604, "aminophenol")
                .fluid()
                .color(0xAFCA3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build()
                .setFormula("C6H4(OH)(NH2)", true);

        Hydroxyquinoline = new Material.Builder(6605, "hydroxyquinoline")
                .fluid()
                .color(0x3A9A71)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build();

        Perbromothiophene = new Material.Builder(6606, "perbromothiophene")
                .fluid()
                .color(0x87CC17)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Bromine, 4, Sulfur, 1)
                .build();

        Dimethylthiophene = new Material.Builder(6607, "dimethylthiophene")
                .fluid()
                .color(0x90FF43)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 8, Sulfur, 1)
                .build()
                .setFormula("C4H2(CH3)2S", true);

        EDOT = new Material.Builder(6608, "ethylenedioxythiophene")
                .fluid()
                .color(0x7A9996)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .build()
                .setFormula("C2H4O2C4H2S", true);

        CitricAcid = new Material.Builder(6609, "citric_acid")
                .fluid()
                .color(0xFFCC00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 8, Oxygen, 7)
                .build()
                .setFormula("C2H4O2C4H2S", true);

        OxalicAcid = new Material.Builder(6610, "oxalic_acid")
                .fluid()
                .color(0x4AAAE2)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 4)
                .build()
                .setFormula("HOOCCOOH", true);

        Trimethylchlorosilane = new Material.Builder(6611, "trimethylchlorosilane")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 9, Silicon, 1, Chlorine, 1)
                .build()
                .setFormula("(CH3)3SiCl", true);

        Dibromoacrolein = new Material.Builder(6612, "dibromoacrolein")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 2, Bromine, 2, Oxygen, 2)
                .build();

        Bromohydrothiine = new Material.Builder(6613, "bromodihydrothiine")
                .fluid()
                .color(0x40FF3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Bromine, 2)
                .build();

        Lithiumthiinediselenide = new Material.Builder(6614, "lithiumthiinediselenide")
                .fluid()
                .color(0x7ADA00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Lithium, 2, Selenium, 2)
                .build();

        Bromobutane = new Material.Builder(6615, "bromobutane")
                .fluid()
                .color(0xFF3333)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 9, Bromine, 1)
                .build()
                .setFormula("CH3(CH2)3Br", true);

        Biperfluoromethanedisulfide = new Material.Builder(6616, "biperfluoromethanedisulfide")
                .fluid()
                .color(0x3ADA40)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Fluorine, 6, Sulfur, 2)
                .build();

        BariumTriflateSolution = new Material.Builder(6617, "barium_triflate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 3, Mercury, 1, Carbon, 2, Barium, 1, Fluorine, 6, Oxygen, 6, Sulfur, 2)
                .build()
                .setFormula("(H2O)3(Hg)C2BaF6O6S2", true);

        BariumStrontiumAcetateSolution = new Material.Builder(6618, "basr_acetate_solution")
                .fluid()
                .color(0x9A9B98)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 3, Barium, 1, Oxygen, 2, Strontium, 1)
                .build();

        TitaniumIsopropoxide = new Material.Builder(6619, "titanium_isopropoxide")
                .fluid()
                .color(0xFF0066)
                .flags(DISABLE_DECOMPOSITION)
                .components(Titanium, 1, Carbon, 12, Hydrogen, 28, Oxygen, 4)
                .build()
                .setFormula("Ti(OCH(CH3)2)4", true);

        BariumStrontiumTitanatePreparation = new Material.Builder(6620, "basr_titanate_preparation")
                .fluid()
                .color(0xFF2266)
                .flags(DISABLE_DECOMPOSITION)
                .components(BariumTitanate, 1, Carbon, 2, Hydrogen, 3, Barium, 1, Oxygen, 2, Strontium, 1)
                .build()
                .setFormula("(BaTiO3)C2H3BaO2Sr", true);


        public static SimpleFluidMaterial Chloroethane = new SimpleFluidMaterial("chloroethane", 0x33aa33, "CH3CH2Cl");
        public static SimpleFluidMaterial IsopropylAcetate = new SimpleFluidMaterial("isopropyl_acetate", (Strontium.materialRGB+IsopropylAlcohol.rgb+Water.materialRGB)/3, "(CH3)2CHCOOCH3");
        public static SimpleFluidMaterial ChlorinatedSolvents = new SimpleFluidMaterial("chlorinated_solvents",0x40804c, "(CH4)2Cl5");
        public static SimpleFluidMaterial Dichloromethane = new SimpleFluidMaterial("dichloromethane", Chloromethane.materialRGB-10, "CH2Cl2");
        public static SimpleFluidMaterial ButanolGas = new SimpleFluidMaterial("butanol_gas",Butanol.rgb+20, "C4H9OH");
        public static SimpleFluidMaterial Tributylamine = new SimpleFluidMaterial("tributylamine",0x801a80, "(C4H9)3N");
        public static SimpleFluidMaterial CrudeAluminaSolution = new SimpleFluidMaterial("crude_alumina_solution", (Aluminium.materialRGB-30), "(Al(NO3)3)2(CH2Cl2)(C12H27N)");
        public static SimpleFluidMaterial AluminaSolution = new SimpleFluidMaterial("alumina_solution", (Aluminium.materialRGB-15), "(Al2O3)(CH2Cl2)(C12H27N)2");
        public static SimpleFluidMaterial AmmoniumCyanate = new SimpleFluidMaterial("ammonium_cyanate",0x3a5dcf, "NH4CNO");
        public static SimpleFluidMaterial Ethylenediamine = new SimpleFluidMaterial("ethylenediamine", Ethanolamine.rgb, "C2H4(NH2)2");
        public static SimpleFluidMaterial EDTASolution = new SimpleFluidMaterial("edta_solution",0x0026d9, "(C10H16N2O8)3(C2H8N2)O2");
        public static SimpleFluidMaterial EDTA = new SimpleFluidMaterial("edta",0x0026d9, "C10H16N2O8");
        public static SimpleFluidMaterial Glycine = new SimpleFluidMaterial("glycine", (Ethylenediamine.rgb+Formaldehyde.rgb)/2, "NH2CH2COOH");
        public static SimpleFluidMaterial Nitrotoluene = new SimpleFluidMaterial("nitrotoluene",0xfcca00, "C7H7NO2");
        public static SimpleFluidMaterial Naphthylamine = new SimpleFluidMaterial("naphthylamine",0xe3e81c, "C10H9N");
        public static SimpleFluidMaterial Acetoacetanilide = new SimpleFluidMaterial("acetoacetanilide",0xffffc2, "C10H11NO2");
        public static SimpleFluidMaterial Quinizarin = new SimpleFluidMaterial("quinizarin",0x3c5a2c0, "C14H8O4");
        public static SimpleFluidMaterial Toluenesulfonate = new SimpleFluidMaterial("toluenesulfonate",0x8f8f00, "C7H7SO3Na");
        public static SimpleFluidMaterial Isopropylsuccinate = new SimpleFluidMaterial("isopropylsuccinate",0xb26680, "C7H12O4");
        public static SimpleFluidMaterial MaleicAnhydride = new SimpleFluidMaterial("maleic_anhydride",0x3c20ad, "C4H2O3");
        public static SimpleFluidMaterial Benzonitrile = new SimpleFluidMaterial("benzonitrile",0x2c2c9c, "C7H5N");
        public static SimpleFluidMaterial AmmoniumNiobiumOxalateSolution = new SimpleFluidMaterial("ammonium_niobium_oxalate_solution",0x6c6cac, "(NH4)C10Nb2O20");
        public static SimpleFluidMaterial DielectricMirrorFormationMix = new SimpleFluidMaterial("dielectric_mirror_formation_mix",0xff992c, "MgF2ZnSTa2Ti(C2H6O8)");
        public static SimpleFluidMaterial Iodobenzene = new SimpleFluidMaterial("iodobenzene",0x2c2c6c0, "C6H5I");
        public static SimpleFluidMaterial Amino3phenol = new SimpleFluidMaterial("3_aminophenol",Aminophenol.rgb, "C6H7NO");
        public static SimpleFluidMaterial Dimethylnaphthalene = new SimpleFluidMaterial("dimethylnaphthalene",0xe34fb0, "C12H12");
        public static SimpleFluidMaterial IodineMonochloride = new SimpleFluidMaterial("iodine_monochloride",0x004c4c, "ICl");
        public static SimpleFluidMaterial AcetylatingReagent = new SimpleFluidMaterial("acetylating_reagent",0x8d5e63, "C9H12Si(MgBr)2");
        public static SimpleFluidMaterial Dihydroiodotetracene = new SimpleFluidMaterial("dihydroiodotetracene",0x5c4d38, "H2C18H11I");
        public static SimpleFluidMaterial Dichlorodicyanobenzoquinone = new SimpleFluidMaterial("dichlorodicyanobenzoquinone",0x3a2aba, "C8Cl2N2O2");
        public static SimpleFluidMaterial Dichlorodicyanohydroquinone = new SimpleFluidMaterial("dichlorodicyanohidroquinone",0x3a2aba, "C8Cl2N2(OH)2");
        public static SimpleFluidMaterial IodobenzoicAcid = new SimpleFluidMaterial("iodobenzoic_acid",0x2cac6c0, "C7H5IO2");
        public static SimpleFluidMaterial Methoxybenzaldehyde = new SimpleFluidMaterial("methoxybenzaldehyde",0x3c3a7a, "C8H8O2");
        public static SimpleFluidMaterial Butylaniline = new SimpleFluidMaterial("butylaniline", Aniline.rgb, "C10H15N");
        public static SimpleFluidMaterial MBBA = new SimpleFluidMaterial("mbba",0xfa30fa, "C18H21NO");
        public static SimpleFluidMaterial PotassiumEthoxide = new SimpleFluidMaterial("potassium_ethoxide",Ethanol.materialRGB, "C2H5KO");
        public static SimpleFluidMaterial TetraethylammoniumBromide = new SimpleFluidMaterial("tetraethylammonium_bromide",0xcc33ff, "C8H20NBr");
        public static SimpleFluidMaterial Hexanediol = new SimpleFluidMaterial("hexanediol", EthyleneGlycol.rgb, "C6H14O2");
        public static SimpleFluidMaterial Hexamethylenediamine = new SimpleFluidMaterial("hexamethylenediamine",Ethylenediamine.rgb, "C6H16N2");
        public static SimpleFluidMaterial Tertbutanol = new SimpleFluidMaterial("tertbutanol",0xcccc2c, "C4H10O");
        public static SimpleFluidMaterial Triaminoethaneamine = new SimpleFluidMaterial("triaminoethaneamine",0x6f7d87, "(NH2CH2CH2)3N");
        public static SimpleFluidMaterial TertButylAzidoformate = new SimpleFluidMaterial("tertbuthylcarbonylazide",0x888818, "C5H9N3O2");
        public static SimpleFluidMaterial AminatedFullerene = new SimpleFluidMaterial("aminated_fullerene",0x2c2caa, "C60N12H12");
        public static SimpleFluidMaterial Azafullerene = new SimpleFluidMaterial("azafullerene",0x8a7a1a, "C60N12H12");
        public static SimpleFluidMaterial Ethylamine = new SimpleFluidMaterial("ethylamine",Ethylenediamine.rgb, "C2H5NH2");
        public static SimpleFluidMaterial Trimethylsilane = new SimpleFluidMaterial("trimethylsilane",Trimethylchlorosilane.rgb, "C3H10Si");
        public static SimpleFluidMaterial Phenylsodium = new SimpleFluidMaterial("phenylsodium",0x2c2cc8, "C6H5Na");
        public static SimpleFluidMaterial Difluoroaniline = new SimpleFluidMaterial("difluoroaniline",0x3fac4a, "C6H5F2N");
        public static SimpleFluidMaterial Succinaldehyde = new SimpleFluidMaterial("succinaldehyde",0x7c6d9a, "C4H6O2");
        public static SimpleFluidMaterial NDifluorophenylpyrrole = new SimpleFluidMaterial("n_difluorophenylpyrrole",0x3a9aa9, "C10H7F2N");
        public static SimpleFluidMaterial PhotopolymerSolution = new SimpleFluidMaterial("photopolymer_solution",0x8a526d, "C149H97N10O2(TiBF20)");
        public static SimpleFluidMaterial GlucoseIronSolution = new SimpleFluidMaterial("glucose_iron_solution", (Sugar.materialRGB+Iron.materialRGB)/2, "(C6H12O6)FeCl3");
        public static SimpleFluidMaterial Methylethanolamine = new SimpleFluidMaterial("methylethanolamine",0x6a3baa, "C3H9NO");
        public static SimpleFluidMaterial Methylguanidine = new SimpleFluidMaterial("methylguanidine",0x5a9a3c, "C2H7N3");
        public static SimpleFluidMaterial Methylnitronitrosoguanidine = new SimpleFluidMaterial("methylnitronitrosoguanidine",0x68b15d, "C2H5N5O3");
        public static SimpleFluidMaterial IsoamylAlcohol = new SimpleFluidMaterial("isoamyl_alcohol",0xcaba77, "C5H12O");
        public static SimpleFluidMaterial Octanol = new SimpleFluidMaterial("octanol",0xa2b8c2, "C8H18O");
        public static SimpleFluidMaterial Trioctylamine = new SimpleFluidMaterial("trioctylamine",0x87a2bc, "C24H51N");
        public static SimpleFluidMaterial RheniumSeparationMixture = new SimpleFluidMaterial("rhenium_separation_mixture",0xed2c3a, "C11H24");
        public static SimpleFluidMaterial AcetylsulfanilylChloride = new SimpleFluidMaterial("acetylsulfanilyl_chloride", (Aniline.rgb + AceticAnhydride.rgb + ChlorosulfonicAcid.rgb)/3, "C8H8ClNO3S");
        public static SimpleFluidMaterial BenzoylPeroxide = new SimpleFluidMaterial("benzoyl_peroxide", (Barium.materialRGB + BenzoylChloride.rgb)/2, "C14H10O4");
        public static SimpleFluidMaterial Propadiene = new SimpleFluidMaterial("propadiene", (Butanol.rgb-20), "C3H4");
        public static SimpleFluidMaterial PhenylenedioxydiaceticAcid = new SimpleFluidMaterial("phenylenedioxydiacetic_acid", 0x99546a, "C10H10O6");
        public static SimpleFluidMaterial Diethylthiourea = new SimpleFluidMaterial("diethylthiourea", 0x2acaa4, "(C2H5NH)2CS");
        public static SimpleFluidMaterial Isophthaloylbisdiethylthiourea = new SimpleFluidMaterial("isophthaloylbisdiethylthiourea", 0x8a7b9c, "C18H26N4O2S2");
        public static SimpleFluidMaterial SodiumAlginateSolution = new SimpleFluidMaterial("sodium_alginate_solution",0xca8642, "NaC6H7O6");
        public static SimpleFluidMaterial AscorbicAcid = new SimpleFluidMaterial("ascorbic_acid",0xe6cd00, "C6H8O6");
        public static SimpleFluidMaterial DehydroascorbicAcid = new SimpleFluidMaterial("dehydroascorbic_acid",0xe6cd00, "C6H6O6");
        public static SimpleFluidMaterial Cyclopentadiene = new SimpleFluidMaterial("cyclopentadiene", Cyclooctadiene.rgb, "C5H6");
        public static SimpleFluidMaterial Oxydianiline = new SimpleFluidMaterial("oxydianiline", 0xF0E130, "C12H12N2O");
        public static SimpleFluidMaterial PolyamicAcid = new SimpleFluidMaterial("polyamic_acid", 0xFFAE42, "C22H14N2O7");
        public static SimpleFluidMaterial Hexafluoropropylene = new SimpleFluidMaterial("hexafluoropropylene", 0x111111, "C3F6");
        public static SimpleFluidMaterial Dimethylether = new SimpleFluidMaterial("dimethylether", 0xe6cd11, "C2H6O");
        public static SimpleFluidMaterial Dimethoxyethane = new SimpleFluidMaterial("dimethoxyethane", 0x2acbb4, "C4H10O2");
        public static SimpleFluidMaterial LithiumCyclopentadienide = new SimpleFluidMaterial("lithiumcyclopentadienide", 0x95556a, "LiC5H5");
        public static SimpleFluidMaterial CaliforniumCyclopentadienide = new SimpleFluidMaterial("californiumcyclopentadienide", 0x94445b, "C15H15Cf");
        public static SimpleFluidMaterial StearicAcid = new SimpleFluidMaterial("stearicacid", 0x2bbbb4, "C18H36O2");
        public static SimpleFluidMaterial Trioctylphosphine = new SimpleFluidMaterial("trioctylphosphine", 0xF1E130, "C24H51P");
        public static SimpleFluidMaterial Toluidine = new SimpleFluidMaterial("toluidine",(Toluene.materialRGB+ Aniline.rgb)/2,"C7H9N");
        public static SimpleFluidMaterial HydroxylamineHydrochloride = new SimpleFluidMaterial("hydroxylamine_hydrochloride", ((Barium.materialRGB+Chlorine.materialRGB)/2 + 0xF0EAD6)/2, "HONH2HCl");
        public static SimpleFluidMaterial Glyoxal = new SimpleFluidMaterial("glyoxal", 0xf2f068, "C2H2O2");
        public static SimpleFluidMaterial BenzylChloride = new SimpleFluidMaterial("benzyl_chloride", 0xaef7fc, "C7H7Cl");
        public static SimpleFluidMaterial Benzylamine = new SimpleFluidMaterial("benzylamine", 0x5c8082, "C7H9N");
        public static SimpleFluidMaterial Tetrahydrofuran = new SimpleFluidMaterial("tetrahydrofuran", 0xb7ebcd, "(CH2)4O");
        public static SimpleFluidMaterial Triethylamine = new SimpleFluidMaterial("triethylamine", Ethylenediamine.rgb, "N(CH2CH3)3");
        public static SimpleDustMaterial BetaPinene = new SimpleDustMaterial("beta_pinene", 0x61ad6b, (short) 25, MaterialIconSet.DULL, "C10H16");
        public static SimpleDustMaterial Glutamine = new SimpleDustMaterial("glutamine", 0xede9b4, (short) 27, MaterialIconSet.DULL,"C5H10N2O3");
        public static SimpleDustMaterial PotassiumCyanide = new SimpleDustMaterial("potassium_cyanide", (Potassium.materialRGB+Nitrogen.materialRGB)/2, (short) 63, MaterialIconSet.ROUGH, "KCN");
        public static SimpleDustMaterial SuccinicAcid = new SimpleDustMaterial("succinic_acid", (MaleicAnhydride.rgb+Water.materialRGB+Hydrogen.materialRGB)/3, (short) 64, MaterialIconSet.ROUGH, "C4H6O4");
        public static SimpleDustMaterial Succinimide = new SimpleDustMaterial("succinimide", (SuccinicAcid.rgb+Ammonia.materialRGB)/2, (short) 65, MaterialIconSet.METALLIC, "C4H5NO2");
        public static SimpleDustMaterial Bromosuccinimide = new SimpleDustMaterial("bromo_succinimide", (Succinimide.rgb+Bromine.materialRGB)/2, (short) 66, MaterialIconSet.METALLIC, "C4H4BrNO2");
        public static SimpleDustMaterial Benzophenanthrenylacetonitrile = new SimpleDustMaterial("benzophenanthrenylacetonitrile", (Naphthaldehyde.rgb+Ethylene.materialRGB-20)/2, (short) 67, MaterialIconSet.ROUGH, "C20H13N");
        public static SimpleDustMaterial UnfoldedFullerene = new SimpleDustMaterial("unfolded_fullerene", (Benzophenanthrenylacetonitrile.rgb+Oxygen.materialRGB)/2, (short) 68, MaterialIconSet.DULL, "C60H30");
        public static SimpleDustMaterial Fullerene = new SimpleDustMaterial("fullerene", (UnfoldedFullerene.rgb-20), (short) 69, MaterialIconSet.METALLIC, "C60");
        public static SimpleDustMaterial TiAlChloride = new SimpleDustMaterial("tial_chloride", (Titanium.materialRGB+Aluminium.materialRGB+Chlorine.materialRGB)/3, (short) 70, MaterialIconSet.DULL, "TiAlCl7");
        public static SimpleDustMaterial Dimethylaminopyridine = new SimpleDustMaterial("dimethylaminopyridine", (Dimethylamine.materialRGB+Pyridine.rgb)/2, (short) 71, MaterialIconSet.ROUGH, "(CH3)2NC5H4N");
        public static SimpleDustMaterial SodiumEthoxide = new SimpleDustMaterial("sodium_ethoxide", (Ethanol.materialRGB+ SodiumHydroxide.materialRGB)/2, (short) 73, MaterialIconSet.METALLIC, "C2H5ONa");
        public static SimpleDustMaterial Sarcosine = new SimpleDustMaterial("sarcosine", (Glycine.rgb+Oxygen.materialRGB)/2, (short) 75, MaterialIconSet.SHINY, "C3H7NO2");
        public static SimpleDustMaterial Difluorobenzophenone = new SimpleDustMaterial("difluorobenzophenone", (FluoroBenzene.rgb+Fluorotoluene.rgb)/2, (short) 78, MaterialIconSet.SHINY, "(FC6H4)2CO");
        public static SimpleDustMaterial PdFullereneMatrix = new SimpleDustMaterial("pd_fullerene_matrix", (Palladium.materialRGB+Fullerene.rgb)/2, (short) 80, MaterialIconSet.SHINY, "PdC60");
        public static SimpleDustMaterial Terephthalaldehyde = new SimpleDustMaterial("terephthalaldehyde", (Dibromomethylbenzene.rgb+SulfuricAcid.materialRGB)/2, (short) 81, MaterialIconSet.FINE, "C8H6O2");
        public static SimpleDustMaterial PreZylon = new SimpleDustMaterial("pre_zylon", (Terephthalaldehyde.rgb+Dinitrodipropanyloxybenzene.rgb)/2, (short) 82, MaterialIconSet.FINE, "C20H22N2O2");
        public static SimpleDustMaterial Cyanonaphthalene = new SimpleDustMaterial("cyanonaphthalene", (SodiumCyanide.rgb+ Naphthalene.materialRGB)/2, (short) 84, MaterialIconSet.FINE, "C11H7N");
        public static SimpleDustMaterial Triphenylphosphine = new SimpleDustMaterial("triphenylphosphine", (Chlorobenzene.materialRGB+PhosphorusTrichloride.rgb)/2, (short) 86, MaterialIconSet.ROUGH, "(C6H5)3P");
        public static SimpleDustMaterial Methylbenzophenanthrene = new SimpleDustMaterial("methylbenzophenanthrene", (Naphthaldehyde.rgb+EthylBenzene.materialRGB)/2, (short) 87, MaterialIconSet.FINE, "C19H14");
        public static SimpleDustMaterial AcrylicFibers = new SimpleDustMaterial("acrylic_fibers", 0xfdfdfb, (short) 135, MaterialIconSet.FINE, "(C5O2H8)n");
        public static SimpleDustMaterial Glucosamine = new SimpleDustMaterial("glucosamine", (Cellulose.rgb+Water.materialRGB)/2, (short) 145, MaterialIconSet.FINE, "C6H13NO5");
        public static SimpleDustMaterial PolystyreneNanoParticles = new SimpleDustMaterial("polystryrene_nanoparticles", 0x888079, (short) 149, MaterialIconSet.FINE, "(C8H8)n");
        public static SimpleDustMaterial Fructose = new SimpleDustMaterial("fructose", (Cellulose.rgb+Sugar.materialRGB)/2, (short) 165, MaterialIconSet.ROUGH, "C6H12O6");
        public static SimpleDustMaterial Glucose = new SimpleDustMaterial("glucose", (Sugar.materialRGB+5), (short) 166, MaterialIconSet.ROUGH, "C6H12O6");
        public static SimpleDustMaterial Biphenyl = new SimpleDustMaterial("biphenyl", 0x003366, (short) 155, MaterialIconSet.SHINY, "C12H10");
        public static SimpleDustMaterial Diiodobiphenyl = new SimpleDustMaterial("diiodobiphenyl", 0x000f66, (short) 156, MaterialIconSet.ROUGH, "C12H8I2");
        public static SimpleDustMaterial Bipyridine = new SimpleDustMaterial("bipyridine", 0X978662, (short) 170, MaterialIconSet.FINE, "C10H8N2");
        public static SimpleDustMaterial PalladiumBisDibenzylidieneacetone = new SimpleDustMaterial("palladium_bisdibenzylidieneacetone", 0Xbe81a0, (short) 158, MaterialIconSet.ROUGH, "C51H42O3Pd2");
        public static SimpleDustMaterial NickelTriphenylPhosphite = new SimpleDustMaterial("nickel_triphenyl_phosphite", 0xd9d973, (short) 160, MaterialIconSet.SHINY, "C36H30Cl2NiP2");
        public static SimpleDustMaterial Dichlorocycloctadieneplatinium = new SimpleDustMaterial("dichlorocyclooctadieneplatinium", 0xe0f78a, (short) 161, MaterialIconSet.SHINY, "C8H12Cl2Pt");
        public static SimpleDustMaterial GrapheneNanotubeMix = new SimpleDustMaterial("graphene_nanotube_mix", 0x2c2c2c, (short) 162, MaterialIconSet.ROUGH, "(C)C?");
        public static SimpleDustMaterial GrapheneAlignedCNT = new SimpleDustMaterial("graphene_aligned_cnt", 0x2c2c2c, (short) 163, MaterialIconSet.SHINY, "(C)C30H20");
        public static SimpleDustMaterial DehydratedLignite = new SimpleDustMaterial("dehydrated_lignite", 0x5c4020, (short) 196, MaterialIconSet.DULL, "C2(H2O)4C?");
        public static SimpleDustMaterial BCEPellet = new SimpleDustMaterial("bce_pellet", 0x3c3020, (short) 197, MaterialIconSet.LIGNITE, "C2(H2O)4C"); //todo coal burn time
        public static SimpleDustMaterial ActiniumOxalate = new SimpleDustMaterial("actinium_oxalate", Actinium.materialRGB, (short) 210, MaterialIconSet.SHINY, "Ac(CO2)4");
        public static SimpleDustMaterial LanthanumFullereneMix = new SimpleDustMaterial("lanthanum_fullerene_mix", 0xdfcafa, (short) 212, MaterialIconSet.SHINY, "(C60)2La2?");
        public static SimpleDustMaterial LanthanumEmbeddedFullerene = new SimpleDustMaterial("lanthanum_embedded_fullerene", 0x99cc00, (short) 213, MaterialIconSet.SHINY, "(C60)2La2");
        public static SimpleDustMaterial BariumTriflate = new SimpleDustMaterial("barium_triflate", (Barium.materialRGB+Fluorine.materialRGB)/2, (short) 227, MaterialIconSet.SHINY, "Ba(OSO2CF3)2");
        public static SimpleDustMaterial ScandiumTriflate = new SimpleDustMaterial("scandium_triflate", 0xdfcfcf, (short) 228, MaterialIconSet.SHINY, "Sc(OSO2CF3)3");
        public static SimpleDustMaterial TitaniumCyclopentadienyl = new SimpleDustMaterial("titanium_cyclopentadienyl", 0xbc30bc, (short) 230, MaterialIconSet.SHINY, "C5H5Cl3Ti");
        public static SimpleDustMaterial BETS = new SimpleDustMaterial("bets", 0x7ada00, (short) 253, MaterialIconSet.SHINY, "C10H8S4Se4");
        public static SimpleDustMaterial Urea = new SimpleDustMaterial("urea",0x30cf20,(short) 271,MaterialIconSet.ROUGH, "CH4N2O");
        public static SimpleDustMaterial PotassiumFerrocyanide = new SimpleDustMaterial("potassium_ferrocyanide",0x0000ff,(short) 293,MaterialIconSet.ROUGH, "K4Fe(CN)6(H2O)3");
        public static SimpleDustMaterial PrussianBlue = new SimpleDustMaterial("prussian_blue",0x0000ff,(short) 294,MaterialIconSet.DULL, "Fe4[Fe(CN)6]3");
        public static SimpleDustMaterial DiaminostilbenedisulfonicAcid = new SimpleDustMaterial("diaminostilbenedisulfonic_acid",0xffffff,(short) 299,MaterialIconSet.DULL, "C14H14N2O6S2");
        public static SimpleDustMaterial Nigrosin = new SimpleDustMaterial("nigrosin",0x000000,(short) 300,MaterialIconSet.DULL, "C36H26N5ClNa2S2O6");
        public static SimpleDustMaterial DirectBrown = new SimpleDustMaterial("direct_brown",0x663300,(short) 301,MaterialIconSet.DULL, "C26H19N6NaO3S");
        public static SimpleDustMaterial DianilineterephthalicAcid = new SimpleDustMaterial("dianilineterephthalic_acid",0xff0000,(short) 302,MaterialIconSet.DULL, "C20H16N2O4");
        public static SimpleDustMaterial Quinacridone = new SimpleDustMaterial("quinacridone",0xff0000,(short) 303,MaterialIconSet.DULL, "C20H12N2O2");
        public static SimpleDustMaterial DiarylideYellow = new SimpleDustMaterial("diarylide_yellow",0xffff00,(short) 304,MaterialIconSet.DULL, "C32H26Cl2N6O4");
        public static SimpleDustMaterial AlizarineCyanineGreen = new SimpleDustMaterial("alizarine_cyanine_green",0x00ff00,(short) 305,MaterialIconSet.DULL, "C28H20N2Na2O8S2");
        public static SimpleDustMaterial Aminoanthraquinone = new SimpleDustMaterial("aminoanthraquinone",0x0000ff,(short) 306,MaterialIconSet.DULL, "C14H9NO2");
        public static SimpleDustMaterial IndanthroneBlue = new SimpleDustMaterial("indanthrone_blue",0x0000ff,(short) 307,MaterialIconSet.DULL, "C28H14N2O2");
        public static SimpleDustMaterial Diketopyrrolopyrrole = new SimpleDustMaterial("diketopyrrolopyrrole",0xff6600,(short) 308,MaterialIconSet.DULL, "C18H12N2O2");
        public static SimpleDustMaterial Mauveine = new SimpleDustMaterial("mauveine",0x660066,(short) 309,MaterialIconSet.DULL, "C26H23N4");
        public static SimpleDustMaterial Indigo = new SimpleDustMaterial("indigo",0x0000ff,(short) 310,MaterialIconSet.DULL, "C16H10N2O2");
        public static SimpleDustMaterial Tetrabromoindigo = new SimpleDustMaterial("tetrabromoindigo",0x00ff00,(short) 311,MaterialIconSet.DULL, "C16H6Br2N2O2");
        public static SimpleDustMaterial CyanIndigoDye = new SimpleDustMaterial("cyan_indigo_dye",0x009999,(short) 312,MaterialIconSet.DULL, "(C16H10N2O2)2Br2");
        public static SimpleDustMaterial Fluorescein = new SimpleDustMaterial("fluorescein",0x990000,(short) 313,MaterialIconSet.DULL, "C20H12O5");
        public static SimpleDustMaterial Erythrosine = new SimpleDustMaterial("erythrosine",0xff00ff,(short) 314,MaterialIconSet.DULL, "C20H6I4Na2O5");
        public static SimpleDustMaterial SodiumSulfanilate = new SimpleDustMaterial("sodium_sulfanilate",0xe49879,(short) 317,MaterialIconSet.SHINY, "C6H6NNaO3S");
        public static SimpleDustMaterial Anthraquinone = new SimpleDustMaterial("anthraquinone",0xfff782,(short) 320,MaterialIconSet.ROUGH, "C14H8O2");
        public static SimpleDustMaterial PalladiumAcetate = new SimpleDustMaterial("palladium_acetate",0xcc3300,(short) 329,MaterialIconSet.SHINY, "C4H6O4Pd");
        public static SimpleDustMaterial RhodamineB = new SimpleDustMaterial("rhodamine_b",0xfc2020,(short) 332,MaterialIconSet.SHINY, "C28H31ClN2O3");
        public static SimpleDustMaterial Stilbene = new SimpleDustMaterial("stilbene",0x3c9c3c,(short) 333,MaterialIconSet.SHINY, "C14H12");
        public static SimpleDustMaterial Tetracene = new SimpleDustMaterial("tetracene",0x99801a,(short) 334,MaterialIconSet.SHINY, "C18H12");
        public static SimpleDustMaterial DitertbutylDicarbonate = new SimpleDustMaterial("ditertbutyl_dicarbonate",0xccccf6,(short) 335, MaterialIconSet.ROUGH, "C10H18O5");
        public static SimpleDustMaterial IBX = new SimpleDustMaterial("ibx",0x20208c,(short) 338,MaterialIconSet.SHINY, "C7H5IO4");
        public static SimpleDustMaterial SaccharicAcid = new SimpleDustMaterial("saccharic_acid",Glucose.rgb,(short) 346,MaterialIconSet.METALLIC, "C6H10O8");
        public static SimpleDustMaterial AdipicAcid = new SimpleDustMaterial("adipic_acid",0xda9288,(short) 347,MaterialIconSet.ROUGH, "C6H10O4");
        public static SimpleDustMaterial TetraethylammoniumNonahydridides = new SimpleDustMaterial("tetraethylammonium_nonahydrides",0xbee8b9,(short) 348,MaterialIconSet.SHINY, "(C8H20N)(ReH9)(TcH9)");
        public static SimpleDustMaterial PolycyclicAromaticMix = new SimpleDustMaterial("polycyclic_aromatic_mix",Tetracene.rgb,(short) 355,MaterialIconSet.ROUGH, "C18H12");
        public static SimpleDustMaterial GrapheneOxide = new SimpleDustMaterial("graphene_oxide",Graphene.materialRGB,(short) 357,MaterialIconSet.ROUGH, "C(O2)");
        public static SimpleDustMaterial GraphiteOxide = new SimpleDustMaterial("graphite_oxide", Graphite.materialRGB, (short) 358,MaterialIconSet.FINE, "C(O2)");
        public static SimpleDustMaterial GrapheneGelSuspension = new SimpleDustMaterial("graphene_gel_suspension",0xadadad,(short) 360,MaterialIconSet.ROUGH, "C");
        public static SimpleDustMaterial DryGrapheneGel = new SimpleDustMaterial("dry_graphene_gel",0x3a3ada,(short) 361,MaterialIconSet.DULL, "C");
        public static SimpleDustMaterial MercuryAcetate = new SimpleDustMaterial("mercury_acetate",0xcc8562, (short) 369, MaterialIconSet.ROUGH, "Hg(CH3COO)2");
        public static SimpleDustMaterial CalciumCyanamide = new SimpleDustMaterial("calcium_cyanamide", CalciumCarbide.rgb, (short) 370, MaterialIconSet.SHINY, "CaCN2");
        public static SimpleDustMaterial Xylose = new SimpleDustMaterial("xylose", Glucose.rgb,(short) 389, MaterialIconSet.ROUGH, "C5H10O5");
        public static SimpleDustMaterial FluorideBatteryElectrolyte = new SimpleDustMaterial("fluoride_battery_electrolyte", 0x9a628a, (short) 394, MaterialIconSet.SHINY, "La9BaF29(C8H7F)");
        public static SimpleDustMaterial Sorbose = new SimpleDustMaterial("sorbose", Glucose.rgb, (short) 396, MaterialIconSet.DULL, "C6H12O6");
        public static SimpleDustMaterial CalciumAlginate = new SimpleDustMaterial("calcium_alginate",0x654321, (short) 397, MaterialIconSet.ROUGH, "CaC12H14O12");
        public static SimpleDustMaterial BETSPerrhenate = new SimpleDustMaterial("bets_perrhenate", 0x7ada00, (short) 255, MaterialIconSet.SHINY, "ReC10H8S4Se4O4");
        public static SimpleDustMaterial Durene = new SimpleDustMaterial("durene", 0xA39C95, (short) 905, MaterialIconSet.ROUGH, "C6H2(CH3)4");
        public static SimpleDustMaterial PyromelliticDianhydride = new SimpleDustMaterial("pyromellitic_dianhydride", 0xF0EAD6, (short) 906, MaterialIconSet.SHINY, "C6H2(C2O3)2");
        public static SimpleDustMaterial SuccinicAnhydride = new SimpleDustMaterial("succinic_anhydride", (SuccinicAcid.rgb + AceticAnhydride.rgb)/2, (short) 911, MaterialIconSet.DULL, "(CH2CO)2O");
        public static SimpleDustMaterial AmmoniumAcetate = new SimpleDustMaterial("ammonium_acetate", 0xb6dee0, (short) 912, MaterialIconSet.DULL, "NH4CH3CO2");
        public static SimpleDustMaterial Acetamide = new SimpleDustMaterial("acetamide", 0xa6bebf, (short) 913, MaterialIconSet.DULL, "CH3CONH2");
        public static SimpleDustMaterial Acetonitrile = new SimpleDustMaterial("acetonitrile", 0xa2afb0, (short) 914, MaterialIconSet.DULL, "CH3CN");
        public static SimpleDustMaterial NHydroxysuccinimide = new SimpleDustMaterial("n-hydroxysuccinimide", 0xdbcae3, (short) 915, MaterialIconSet.DULL, "(CH2CO)2NOH");
        public static SimpleDustMaterial Hexabenzylhexaazaisowurtzitane = new SimpleDustMaterial("hexabenzylhexaazaisowurtzitane", 0x624573, (short) 916, MaterialIconSet.DULL, "C48N6H48");
        public static SimpleDustMaterial SuccinimidylAcetate = new SimpleDustMaterial("succinimidyl_acetate", 0xbd93a6, (short) 917, MaterialIconSet.DULL, "C6H7NO4");
        public static SimpleDustMaterial DibenzylTetraacetylhexaazaisowurtzitane = new SimpleDustMaterial("dibenzyltetraacetylhexaazaisowurtzitane", 0xb3c98b, (short) 918, MaterialIconSet.DULL, "C28N6H32O4");
        public static SimpleDustMaterial HexanitroHexaaxaisowurtzitane = new SimpleDustMaterial("hexanitrohexaaxaisowurtzitane", 0x414a4f, (short) 919, MaterialIconSet.SHINY, "C6H6N12O12");
        public static SimpleDustMaterial Hexamethylenetetramine = new SimpleDustMaterial("hexamethylenetetramine", 0x7e8d94, (short) 922, MaterialIconSet.DULL, "(CH2)6N4");
        public static SimpleDustMaterial Tetraacetyldinitrosohexaazaisowurtzitane = new SimpleDustMaterial("tetraacetyldinitrosohexaazaisowurtzitane",(DibenzylTetraacetylhexaazaisowurtzitane.rgb+Hexabenzylhexaazaisowurtzitane.rgb)/2,(short) 924, MaterialIconSet.DULL, "C14N8H18O6");
        public static SimpleDustMaterial CrudeHexanitroHexaaxaisowurtzitane = new SimpleDustMaterial("crude_hexanitrohexaaxaisowurtzitane", HexanitroHexaaxaisowurtzitane.rgb*5/7, (short) 925, MaterialIconSet.DULL, "C6H6N12O12");

    }
}
