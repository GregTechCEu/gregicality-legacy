package gregicadditions.materials;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
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
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, NO_SMASHING, GENERATE_FOIL, GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
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

        Fullerene = new Material.Builder(6558, "fullerene")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 60)
                .build();

        TiAlChloride = new Material.Builder(6559, "tial_chloride")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Titanium, 1, Aluminium, 1, Chlorine, 7)
                .build();

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

        Mercaptophenol = new Material.Builder(6586, "mercaptophenol")
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
                .components(Carbon, 18, Hydrogen, 39, Oxygen, 5, Phosphorus, 1)
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

        Chloroethane = new Material.Builder(6621, "chloroethane")
                .fluid()
                .color(0x33AA33)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Chlorine, 1)
                .build()
                .setFormula("CH3CH2Cl", true);

        IsopropylAcetate = new Material.Builder(6622, "isopropyl_acetate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 10, Oxygen, 2)
                .build()
                .setFormula("(CH3)2CHCOOCH3", true);

        ChlorinatedSolvents = new Material.Builder(6623, "chlorinated_solvents")
                .fluid()
                .color(0x40804C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Methane, 2, Chlorine, 5)
                .build();

        Dichloromethane = new Material.Builder(6623, "dichloromethane")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 2, Chlorine, 2)
                .build();

        ButanolGas = new Material.Builder(6624, "butanol_gas")
                .fluid(Material.FluidType.GAS)
                .colorAverage()
                .components(Butanol, 1)
                .build();

        Tributylamine = new Material.Builder(6625, "tributylamine")
                .fluid()
                .color(0x801A80)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 27, Nitrogen, 1)
                .build()
                .setFormula("(C4H9)3N", true);

        CrudeAluminaSolution = new Material.Builder(6626, "crude_alumina_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(AluminiumNitrate, 2, Dichloromethane, 1, Tributylamine, 1)
                .build();

        AluminaSolution = new Material.Builder(6627, "alumina_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(AluminiumNitrate, 1, Dichloromethane, 1, Tributylamine, 2)
                .build();

        AmmoniumCyanate = new Material.Builder(6628, "ammonium_cyanate")
                .fluid()
                .color(0x3A5DCF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 2, Hydrogen, 4, Carbon, 1, Oxygen, 1)
                .build()
                .setFormula("NH4CNO", true);

        Ethylenediamine = new Material.Builder(6629, "ethylenediamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Ethylene, 1, Nitrogen, 2, Hydrogen, 4)
                .build()
                .setFormula("C2H4(NH2)2", true);

        EDTA = new Material.Builder(6630, "edta")
                .fluid()
                .color(0x0026D9)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 8)
                .build();

        EDTASolution = new Material.Builder(6631, "edta_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(EDTA, 3, Carbon, 2, Hydrogen, 8, Nitrogen, 2, Oxygen, 2)
                .build();

        Glycine = new Material.Builder(6632, "glycine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 5, Carbon, 2, Oxygen, 2)
                .build()
                .setFormula("NH2CH2COOH", true);

        Cellulose = new Material.Builder(6633, "cellulose")
                .dust()
                .color(0xFEFEFC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 10, Oxygen, 5)
                .build();

        // Free ID 6634

        // Free ID 6635

        // Free ID 6636

        Toluenesulfonate = new Material.Builder(6637, "toluenesulfonate")
                .fluid()
                .color(0x8F8F00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 7, Sulfur, 1, Oxygen, 3, Sodium, 1)
                .build();

        // Free ID 6638

        // Free ID 6639

        // Free ID 6640

        AmmoniumNiobiumOxalateSolution = new Material.Builder(6641, "ammonium_niobium_oxalate_solution")
                .fluid()
                .color(0x6C6CAC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 4, Carbon, 10, Niobium, 2, Oxygen, 20)
                .build()
                .setFormula("(NH4)C10Nb2O20", true);

        DielectricMirrorFormationMix = new Material.Builder(6642, "dielectric_mirror_formation_mix")
                .fluid()
                .color(0xFF992C)
                .flags(DISABLE_DECOMPOSITION)
                .components(MagnesiumFluoride, 1, Sphalerite, 1, Tantalum, 2, Titanium, 1, Carbon, 2, Hydrogen, 6, Oxygen, 8)
                .build()
                .setFormula("MgF2ZnSTa2Ti(C2H6O8)", true);

        Iodobenzene = new Material.Builder(6643, "iodobenzene")
                .fluid()
                .color(0x2C2C6C0)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 5, Iodine, 1)
                .build();

        Amino3phenol = new Material.Builder(6644, "3_aminophenol")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build();

        Dimethylnaphthalene = new Material.Builder(6645, "dimethylnaphthalene")
                .fluid()
                .color(0xE34FB0)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 12)
                .build();

        IodineMonochloride = new Material.Builder(6646, "iodine_monochloride")
                .fluid()
                .color(0x004C4C)
                .components(Iodine, 1, Chlorine, 1)
                .build();

        AcetylatingReagent = new Material.Builder(6647, "acetylating_reagent")
                .fluid()
                .color(0x8D5E63)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 9, Hydrogen, 12, Silicon, 1, Magnesium, 2, Bromine, 2)
                .build()
                .setFormula("C9H12Si(MgBr)2", true);

        Dihydroiodotetracene = new Material.Builder(6648, "dihydroiodotetracene")
                .fluid()
                .color(0x5C4D38)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 13, Carbon, 8, Iodine, 1)
                .build()
                .setFormula("H2C18H11I", true);

        Dichlorodicyanobenzoquinone = new Material.Builder(6649, "dichlorodicyanobenzoquinone")
                .fluid()
                .color(0x3A2ABA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Chlorine, 2, Nitrogen, 2, Oxygen, 2)
                .build();

        Dichlorodicyanohydroquinone = new Material.Builder(6650, "dichlorodicyanohidroquinone")
                .fluid()
                .color(0x3A2ABA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Chlorine, 2, Nitrogen, 2, Oxygen, 2, Hydrogen, 2)
                .build()
                .setFormula("C8Cl2N2(OH)2", true);

        IodobenzoicAcid = new Material.Builder(6651, "iodobenzoic_acid")
                .fluid()
                .color(0x2CAC6C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 5, Iodine, 1, Oxygen, 2)
                .build();

        Methoxybenzaldehyde = new Material.Builder(6652, "methoxybenzaldehyde")
                .fluid()
                .color(0x3C3A7A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8, Oxygen, 2)
                .build();

        Butylaniline = new Material.Builder(6653, "butylaniline")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 15, Nitrogen, 1)
                .build();

        MBBA = new Material.Builder(6654, "mbba")
                .fluid()
                .color(0xFA30FA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 21, Nitrogen, 1, Oxygen, 1)
                .build();

        PotassiumEthoxide = new Material.Builder(6655, "potassium_ethoxide")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Potassium, 1, Oxygen, 1)
                .build();

        TetraethylammoniumBromide = new Material.Builder(6656, "tetraethylammonium_bromide")
                .fluid()
                .color(0xCC33FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 20, Nitrogen, 1, Bromine, 1)
                .build();

        Hexanediol = new Material.Builder(6657, "hexanediol")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 14, Oxygen, 2)
                .build();

        Hexamethylenediamine = new Material.Builder(6658, "hexamethylenediamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 16, Nitrogen, 2)
                .build();

        Tertbutanol = new Material.Builder(6659, "tertbutanol")
                .fluid()
                .color(0xCCCC2C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
                .build();

        Triaminoethaneamine = new Material.Builder(6660, "triaminoethaneamine")
                .fluid()
                .color(0x6F7D87)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 4, Hydrogen, 18, Carbon, 6)
                .build()
                .setFormula("(NH2CH2CH2)3N", true);

        TertButylAzidoformate = new Material.Builder(6661, "tertbuthylcarbonylazide")
                .fluid()
                .color(0x888818)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 9, Nitrogen, 3, Oxygen, 2)
                .build();

        AminatedFullerene = new Material.Builder(6662, "aminated_fullerene")
                .fluid()
                .color(0x2C2CAA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Nitrogen, 12, Hydrogen, 12)
                .build();

        Azafullerene = new Material.Builder(6663, "azafullerene")
                .fluid()
                .color(0x8A7A1A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Nitrogen, 12, Hydrogen, 12)
                .build();

        Ethylamine = new Material.Builder(6664, "ethylamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 7, Nitrogen, 1)
                .build()
                .setFormula("C2H6NH2", true);

        Trimethylsilane = new Material.Builder(6665, "trimethylsilane")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 10, Silicon, 1)
                .build();

        Phenylsodium = new Material.Builder(6666, "phenylsodium")
                .fluid()
                .color(0x2C2CC8)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 5, Sodium, 1)
                .build();

        Difluoroaniline = new Material.Builder(6667, "difluoroaniline")
                .fluid()
                .color(0x3FAC4A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 5, Fluorine, 2, Nitrogen, 1)
                .build();

        Succinaldehyde = new Material.Builder(6668, "succinaldehyde")
                .fluid()
                .color(0x7C6D9A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
                .build();

        NDifluorophenylpyrrole = new Material.Builder(6669, "n_difluorophenylpyrrole")
                .fluid()
                .color(0x3A99AA9)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 7, Fluorine, 2, Nitrogen, 1)
                .build();

        PhotopolymerSolution = new Material.Builder(6670, "photopolymer_solution")
                .fluid()
                .color(0x8A526D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 149, Hydrogen, 97, Nitrogen, 10, Oxygen, 2, Titanium, 1, Boron, 1, Fluorine, 20)
                .build()
                .setFormula("C149H97N10O2(TiBF20)");

        Glucose = new Material.Builder(6671, "glucose")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
                .build();

        Methylethanolamine = new Material.Builder(6672, "methylethanolamine")
                .fluid()
                .color(0x6A3BAA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
                .build();

        Methylguanidine = new Material.Builder(6673, "methylguanidine")
                .fluid()
                .color(0x5A9A3C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 7, Nitrogen, 3)
                .build();

        Methylnitronitrosoguanidine = new Material.Builder(6674, "methylnitronitrosoguanidine")
                .fluid()
                .color(0x68B15D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Nitrogen, 5, Oxygen, 3)
                .build();

        IsoamylAlcohol = new Material.Builder(6675, "isoamyl_alcohol")
                .fluid()
                .color(0xCABA77)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 12, Oxygen, 1)
                .build();

        Octanol = new Material.Builder(6676, "octanol")
                .fluid()
                .color(0xA2B8C2)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 18, Oxygen, 1)
                .build();

        Trioctylamine = new Material.Builder(6677, "trioctylamine")
                .fluid()
                .color(0x87A2Bc)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 24, Hydrogen, 51, Nitrogen, 1)
                .build();

        RheniumSeparationMixture = new Material.Builder(6678, "rhenium_separation_mixture")
                .fluid()
                .color(0xED2C3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 11, Hydrogen, 24)
                .build();

        AcetylsulfanilylChloride = new Material.Builder(6679, "acetylsulfanilyl_chloride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8, Chlorine, 1, Nitrogen, 1, Oxygen, 3, Sulfur, 1)
                .build();

        BenzoylPeroxide = new Material.Builder(6680, "benzoyl_peroxide")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 10, Oxygen, 4)
                .build();

        Propadiene = new Material.Builder(6681, "propadiene")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 4)
                .build();

        PhenylenedioxydiaceticAcid = new Material.Builder(6682, "phenylenedioxydiacetic_acid")
                .fluid()
                .color(0x99546A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 10, Oxygen, 6)
                .build();

        Diethylthiourea = new Material.Builder(6683, "diethylthiourea")
                .fluid()
                .color(0x2ACAA4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 12, Nitrogen, 2, Sulfur, 1)
                .build()
                .setFormula("(C2H5NH)2CS", true);

        Isophthaloylbisdiethylthiourea = new Material.Builder(6685, "isophthaloylbisdiethylthiourea")
                .fluid()
                .color(0x8A6B9C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 26, Nitrogen, 4, Oxygen, 2, Sulfur, 2)
                .build();

        SodiumAlginateSolution = new Material.Builder(6686, "sodium_alginate_solution")
                .fluid()
                .color(0xCA8642)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Carbon, 6, Hydrogen, 7, Oxygen, 6)
                .build();

        AscorbicAcid = new Material.Builder(6687, "ascorbic_acid")
                .fluid()
                .color(0xE6CD00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 8, Oxygen, 6)
                .build();

        DehydroascorbicAcid = new Material.Builder(6688, "dehydroascorbic_acid")
                .fluid()
                .color(0xE6CD11)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 6)
                .build();

        Cyclopentadiene = new Material.Builder(6689, "cyclopentadiene")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 6)
                .build();

        Oxydianiline = new Material.Builder(6690, "oxydianiline")
                .fluid()
                .color(0xF0E130)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
                .build();

        PolyamicAcid = new Material.Builder(6691, "polyamic_acid")
                .fluid()
                .color(0xFFAE42)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 22, Hydrogen, 14, Nitrogen, 2, Oxygen, 7)
                .build();

        Hexafluoropropylene = new Material.Builder(6692, "hexafluoropropylene")
                .fluid()
                .color(0x111111)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Fluorine, 6)
                .build();

        Dimethylether = new Material.Builder(6693, "dimethylether")
                .fluid()
                .color(0xE6CD11)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 6, Oxygen, 1)
                .build();

        Dimethoxyethane = new Material.Builder(6694, "dimethyoxyethane")
                .fluid()
                .color(0x2ACBB4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
                .build();

        LithiumCyclopentadienide = new Material.Builder(6695, "lithiumcyclopentadienide")
                .fluid()
                .color(0x95556A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Carbon, 5, Hydrogen, 5)
                .build();

        CaliforniumCyclopentadienide = new Material.Builder(6696, "californiumcyclopentadienide")
                .fluid()
                .color(0x94445B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 15, Hydrogen, 15, Californium, 1)
                .build();

        StearicAcid = new Material.Builder(6697, "stearicacid")
                .fluid()
                .color(0x2BBBB4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 36, Oxygen, 2)
                .build();

        Trioctylphosphine = new Material.Builder(6698, "trioctylphosphine")
                .fluid()
                .color(0xF1E130)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 24, Hydrogen, 51, Phosphorus, 1)
                .build();

        // Free ID 6699

        HydroxylamineHydrochloride = new Material.Builder(6700, "hydroxylamine_hydrochloride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 4, Oxygen, 1, Nitrogen, 1, Chlorine, 1)
                .build()
                .setFormula("HONH2HCl", true);

        Glyoxal = new Material.Builder(6701, "glyoxal")
                .fluid()
                .color(0xF2F068)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
                .build();

        BenzylChloride = new Material.Builder(6702, "benzyl_chloride")
                .fluid()
                .color(0xAEF7FC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 7, Chlorine, 1)
                .build();

        Benzylamine = new Material.Builder(6703, "benzylamine")
                .fluid()
                .color(0xAEF7FC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 9, Nitrogen, 1)
                .build();

        Tetrahydrofuran = new Material.Builder(6704, "tetrahydrofuran")
                .fluid()
                .color(0xB7EBCD)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 8, Oxygen, 1)
                .build()
                .setFormula("(CH2)4O", true);

        Triethylamine = new Material.Builder(6705, "triethylamine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Carbon, 6, Hydrogen, 15)
                .build()
                .setFormula("N(CH2CH3)3", true);

        BetaPinene = new Material.Builder(6706, "beta_pinene")
                .fluid()
                .color(0x61AB6B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 16)
                .build();

        Glutamine = new Material.Builder(6707, "glutamine")
                .fluid()
                .color(0xEDE9B4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 10, Nitrogen, 2, Oxygen, 3)
                .build();

        PotassiumCyanide = new Material.Builder(6708, "potassium_cyanide")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Potassium, 1, Carbon, 1, Nitrogen, 1)
                .build();

        SuccinicAcid = new Material.Builder(6709, "succinic_acid")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
                .build();
        Succinimide = new Material.Builder(6710, "succinimide")
                .fluid()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 2)
                .build();

        Bromosuccinimide = new Material.Builder(6711, "bromo_succinimide")
                .fluid()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
                .build();

        Benzophenanthrenylacetonitrile = new Material.Builder(6712, "benzophenanthrenylacetonitrile")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 20, Hydrogen, 13, Nitrogen, 1)
                .build();

        UnfoldedFullerene = new Material.Builder(6713, "unfolded_fullerene")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 60, Hydrogen, 30)
                .build();

        PCBA = new Material.Builder(6714, "pcba")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Carbon, 12, Hydrogen, 14, Oxygen, 2)
                .build()
                .setFormula("C72H14O2", true);

        PCBS = new Material.Builder(6715, "pcbs")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, Carbon, 20, Hydrogen, 21, Oxygen, 2)
                .build()
                .setFormula("C80H21O2", true);

        Dimethylaminopyridine = new Material.Builder(6716, "dimethylaminopyridine")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 11, Nitrogen, 2)
                .build()
                .setFormula("(CH3)2NC5H4N", true);

        SodiumEthoxide = new Material.Builder(6717, "sodium_ethoxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Oxygen, 1, Sodium, 1)
                .build();

        Sarcosine = new Material.Builder(6717, "sarcosine")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
                .build();

        Difluorobenzophenone = new Material.Builder(6718, "difluorobenzophenone")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fluorine, 2, Carbon, 13, Hydrogen, 8, Oxygen, 1)
                .build()
                .setFormula("(FC6H4)2CO", true);

        PdFullereneMatrix = new Material.Builder(6719, "pd_fullerene_matrix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Fullerene, 1)
                .build();

        Terephthalaldehyde = new Material.Builder(6720, "terephthalaldehyde")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 6, Oxygen, 2)
                .build();

        PreZylon = new Material.Builder(6721, "pre_zylon")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 20, Hydrogen, 22, Nitrogen, 2, Oxygen, 2)
                .build();

        Cyanonaphthalene = new Material.Builder(6722, "cyanonaphthalene")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 11, Hydrogen, 7, Nitrogen, 1)
                .build();

        Triphenylphosphine = new Material.Builder(6723, "triphenylphosphine")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 15, Phosphorus, 1)
                .build()
                .setFormula("(C6H5)3P", true);

        Methylbenzophenanthrene = new Material.Builder(6724, "methylbenzophenanthrene")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 19, Hydrogen, 14)
                .build();

        AcrylicFibers = new Material.Builder(6725, "acrylic_fibers")
                .dust()
                .color(0xFDFDFB).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Oxygen, 2, Hydrogen, 8)
                .build();

        Glucosamine = new Material.Builder(6726, "glucosamine")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 13, Nitrogen, 1, Oxygen, 5)
                .build();

        PolystyreneNanoParticles = new Material.Builder(6727, "polystyrene_nanoparticles")
                .dust()
                .color(0x888079).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8)
                .build();

        Fructose = new Material.Builder(6728, "fructose")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
                .build();

        GlucoseIronSolution = new Material.Builder(6729, "glucose_iron_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Glucose, 1, Iron3Chloride, 1)
                .build();

        Biphenyl = new Material.Builder(6730, "biphenyl")
                .dust()
                .color(0x003366).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 10)
                .build();

        Diiodobiphenyl = new Material.Builder(6731, "diiodobiphenyl")
                .dust()
                .color(0x000F66).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 12, Hydrogen, 8, Iodine, 2)
                .build();

        Bipyridine = new Material.Builder(6732, "bipyridine")
                .dust()
                .color(0x978662).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 8, Nitrogen, 2)
                .build();

        PalladiumBisDibenzylidieneacetone = new Material.Builder(6733, "palladium_bisdibenzylidieneacetone")
                .dust()
                .color(0xBE81A0).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 51, Hydrogen, 42, Oxygen, 3, Palladium, 2)
                .build();

        NickelTriphenylPhosphite = new Material.Builder(6734, "nickel_triphenyl_phosphite")
                .dust()
                .color(0xD9D973).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 36, Hydrogen, 30, Chlorine, 2, Nickel, 1, Phosphorus, 2)
                .build();

        Dichlorocycloctadieneplatinum = new Material.Builder(6735, "dichlorocyclooctadieneplatinum")
                .dust()
                .color(0xE0F78A).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 12, Chlorine, 2, Platinum, 1)
                .build();

        GrapheneNanotubeMix = new Material.Builder(6736, "graphene_nanotube_mix")
                .dust()
                .color(0x2C2C2C).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, RareEarth, 1)
                .build()
                .setFormula("(C)C?", true);

        GrapheneAlignedCNT = new Material.Builder(6737, "graphene_aligned_cnt")
                .dust()
                .color(0x2C2C2C).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 31, Hydrogen, 20)
                .build()
                .setFormula("(C)C30H20", true);

        DehydratedLignite = new Material.Builder(6738, "dehydrated_lignite")
                .dust()
                .color(0x5C4020)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Water, 4)
                .build()
                .setFormula("C2(H2O)4C?", true);

        BCEPellet = new Material.Builder(6739, "bce_pellet")
                .dust(2, 1600)
                .color(0x3C3020)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 3, Water, 4)
                .build()
                .setFormula("C2(H2O)4C", true);

        ActiniumOxalate = new Material.Builder(6740, "actinium_oxalate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Actinium, 1, CarbonDioxide, 4)
                .build();

        LanthanumFullereneMix = new Material.Builder(6741, "lanthanum_fullerene_mix")
                .dust()
                .color(0xDFCAFA).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 2, Lanthanum, 2, RareEarth, 1)
                .build();

        LanthanumEmbeddedFullerene = new Material.Builder(6742, "lanthanum_embedded_fullerene")
                .dust()
                .color(0x99CC00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 2, Lanthanum, 2)
                .build();

        BariumTriflate = new Material.Builder(6743, "barium_triflate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Oxygen, 6, Sulfur, 2, Carbon, 2, Fluorine, 6)
                .build()
                .setFormula("Ba(OSO2CF3)2", true);

        ScandiumTriflate = new Material.Builder(6744, "scandium_triflate")
                .dust()
                .color(0xDFCFCF).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Scandium, 1, Oxygen, 9, Sulfur, 3, Carbon, 3, Fluorine, 9)
                .build()
                .setFormula("Sc(OSO2CF3)3", true);

        TitaniumCyclopentadienyl = new Material.Builder(6745, "titanium_cyclopentadienyl")
                .dust()
                .color(0xBC30BC).iconSet(SHINY)
                .components(Carbon, 5, Hydrogen, 5, Chlorine, 3, Titanium, 1)
                .build();

        BETS = new Material.Builder(6746, "bets")
                .dust()
                .color(0x7ADA00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4)
                .build();

        Urea = new Material.Builder(6747, "urea")
                .dust()
                .color(0x30CF20).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Hydrogen, 4, Nitrogen, 2, Oxygen, 1)
                .build();

        // Free ID 6748

        // Free ID 6749

        // Free ID 6750

        // Free ID 6751

        // Free ID 6752

        // Free ID 6753

        // Free ID 6754

        // Free ID 6755

        // Free ID 6756

        // Free ID 6757

        // Free ID 6758

        // Free ID 6759

        // Free ID 6760

        // Free ID 6761

        // Free ID 6762

        // Free ID 6763

        Fluorescein = new Material.Builder(6734, "fluorescein")
                .dust()
                .color(0x990000)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 5)
                .build();

        // Free ID 6735

        // Free ID 6736

        // Free ID 6737

        PalladiumAcetate = new Material.Builder(6738, "palladium_acetate")
                .dust()
                .color(0xCC3300).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 4, Palladium, 1)
                .build();

        RhodamineB = new Material.Builder(6739, "rhodamine_b")
                .dust()
                .color(0xFC2020).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 28, Hydrogen, 31, Chlorine, 1, Nitrogen, 2, Oxygen, 3)
                .build();

        Stilbene = new Material.Builder(6740, "stilbene")
                .dust()
                .color(0x3C9C3C).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 12)
                .build();

        Tetracene = new Material.Builder(6741, "tetracene")
                .dust()
                .color(0x99801A).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 12)
                .build();

        DitertbutylDicarbonate = new Material.Builder(6742, "ditertbutyl_dicarbonate")
                .dust()
                .color(0xCCCCF6).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 18, Oxygen, 5)
                .build();

        IBX = new Material.Builder(6743, "ibx")
                .dust()
                .color(0x20208C).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 7, Hydrogen, 5, Iodine, 1, Oxygen, 4)
                .build();

        SaccharicAcid = new Material.Builder(6744, "saccharic_acid")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 10, Oxygen, 8)
                .build();

        AdipicAcid = new Material.Builder(6745, "adipic_acid")
                .dust()
                .color(0xDA9288).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 10, Oxygen, 4)
                .build();

        TetraethylammoniumNonahydridides = new Material.Builder(6746, "tetraethylammonium_nonahydrides")
                .dust()
                .color(0xBEE8B9).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 20, Nitrogen, 1, Rhenium, 1, Hydrogen, 18, Technetium, 1)
                .build()
                .setFormula("(C8H20N)(ReH9)(TcH9)", true);

        PolycyclicAromaticMix = new Material.Builder(6747, "polycyclic_aromatic_mix")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 18, Hydrogen, 12)
                .build();

        GrapheneOxide = new Material.Builder(6748, "graphene_oxide")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Oxygen, 2)
                .build()
                .setFormula("C(O2)", true);

        GraphiteOxide = new Material.Builder(6749, "graphite_oxide")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Oxygen, 2)
                .build()
                .setFormula("C(O2)", true);

        GrapheneGelSuspension = new Material.Builder(6750, "graphene_gel_suspension")
                .dust()
                .color(0xADADAD).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1)
                .build();

        DryGrapheneGel = new Material.Builder(6751, "dry_graphene_gel")
                .dust()
                .color(0x3A3ADA)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1)
                .build();

        MercuryAcetate = new Material.Builder(6752, "mercury_acetate")
                .dust()
                .color(0xCC8562).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, Carbon, 4, Hydrogen, 6, Oxygen, 4)
                .build()
                .setFormula("Hg(CH3COO)2", true);

        CalciumCyanamide = new Material.Builder(6753, "calcium_cyanamide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 1, Carbon, 1, Nitrogen, 2)
                .build();

        Xylose = new Material.Builder(6754, "xylose")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Hydrogen, 10, Oxygen, 5)
                .build();

        FluorideBatteryElectrolyte = new Material.Builder(6755, "fluoride_battery_electrolyte")
                .dust()
                .color(0x9A628A).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lanthanum, 9, Barium, 1, Fluorine, 30, Carbon, 8, Hydrogen, 7)
                .build()
                .setFormula("La9BaF29(C8H7F)", true);

        Sorbose = new Material.Builder(6756, "sorbose")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
                .build();

        CalciumAlginate = new Material.Builder(6757, "calcium_alginate")
                .dust()
                .color(0x654321).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 1, Carbon, 12, Hydrogen, 14, Oxygen, 12)
                .build();

        BETSPerrhenate = new Material.Builder(6758, "bets_perrhenate")
                .dust()
                .color(0x7ADA00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhenium, 1, Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4, Oxygen, 4)
                .build();

        Durene = new Material.Builder(6758, "durene")
                .dust()
                .color(0xA39C95).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 10, Hydrogen, 14)
                .build()
                .setFormula("C6H2(CH3)4", true);

        PyromelliticDianhydride = new Material.Builder(6759, "pyromellitic_dianhydride")
                .dust()
                .color(0xF0EAD6).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 2, Oxygen, 6)
                .build()
                .setFormula("C6H2(C2O3)2");

        SuccinicAnhydride = new Material.Builder(6760, "succinic_anhydride")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon,4, Hydrogen, 4, Oxygen, 2)
                .build()
                .setFormula("(CH2CO)2O", true);

        AmmoniumAcetate = new Material.Builder(6761, "ammonium_acetate")
                .dust()
                .color(0xB6DEE0)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 7, Carbon, 2, Oxygen, 2)
                .build()
                .setFormula("NH4CH3CO2", true);

        Acetamide = new Material.Builder(6762, "acetamine")
                .dust()
                .color(0xA6BEBF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 2, Hydrogen, 5, Oxygen, 1, Nitrogen, 1)
                .build()
                .setFormula("CH3CONH2", true);

        Acetonitrile = new Material.Builder(6763, "acetonitrile")
                .dust()
                .color(0xA2AFB0)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 3, Nitrogen, 1)
                .build()
                .setFormula("CH3CN", true);

        NHydroxysuccinimide = new Material.Builder(6764, "n-hydroxysuccinimide")
                .dust()
                .color(0xDBCAE3)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
                .build()
                .setFormula("(CH2CO)2NOH", true);

        Hexabenzylhexaazaisowurtzitane = new Material.Builder(6765, "hexabenzylhexaazaisowurtzitane")
                .dust()
                .color(0x624573)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 48, Nitrogen, 6, Hydrogen, 48)
                .build();

        SuccinimidylAcetate = new Material.Builder(6766, "succinimidyl_acetate")
                .dust()
                .color(0xBD93A6)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 4)
                .build();

        DibenzylTetraacetylhexaazaisowurtzitane = new Material.Builder(6767, "dibenzyltetraacetylhexaazaisowurtzitane")
                .dust()
                .color(0xB3C98B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 28, Nitrogen, 6, Hydrogen, 32, Oxygen, 4)
                .build();

        HexanitroHexaaxaisowurtzitane = new Material.Builder(6768, "hexanitrohexaaxaisowurtzitane")
                .dust()
                .color(0x414A4F).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION, EXPLOSIVE)
                .components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
                .build();

        Hexamethylenediamine = new Material.Builder(6769, "v")
                .dust()
                .color(0x7E8D94)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 12, Nitrogen, 4)
                .build()
                .setFormula("(CH2)6N4", true);

        Tetraacetyldinitrosohexaazaisowurtzitane = new Material.Builder(6770, "tetraacetyldinitrosohexaazaisowurtzitane")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Nitrogen, 8, Hydrogen, 18, Oxygen, 6)
                .build();

        CrudeHexanitroHexaaxaisowurtzitane = new Material.Builder(6771, "crude_hexanitrohexaaxaisowurtzitane")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
                .build();

        ChlorodiisopropylPhosphine = new Material.Builder(6771, "chlorodiisopropyl_phosphine")
                .fluid()
                .color(0xA2C122)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 14, Chlorine, 1, Phosphorus, 1)
                .build();

        IridiumCyclooctadienylChlorideDimer = new Material.Builder(6772, "iridium_cyclooctadienyl_chloride_dimer")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 2, Carbon, 17, Hydrogen, 24, Chlorine, 2)
                .build()
                .setFormula("Ir2(C8H12)2Cl2");

        AluminiumComplex = new Material.Builder(6773, "aluminium_complex")
                .dust()
                .color(0x3F5A9F).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Aluminium, 1, Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build();
    }
}
