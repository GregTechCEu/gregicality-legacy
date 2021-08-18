package gregicadditions.materials;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;

import static gregicadditions.materials.GAMaterialFlags.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public class GAFirstDegreeMaterials {

    // 3500-????
    public static void register() {
        Hydrazine = new Material.Builder(3500, "hydrazine")
                .fluid()
                .flags(DISABLE_DECOMPOSITION, FLAMMABLE)
                .components(Nitrogen, 2, Hydrogen, 4)
                .build();

        HydrogenPeroxide = new Material.Builder(3501, "hydrogen_peroxide")
                .fluid()
                .color(0xD1FFFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Oxygen, 2)
                .build();

        PlatinumConcentrate = new Material.Builder(3502, "platinum_concentrate")
                .fluid()
                .color(0xFFFFC5)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, RareEarth, 1)
                .build();

        AmmoniumChloride = new Material.Builder(3503, "ammonium_chloride")
                .fluid()
                .color(0x1FEF40)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 4, Chlorine, 1)
                .build();

        RhodiumSulfate = new Material.Builder(3504, "rhodium_sulfate")
                .fluid()
                .color(0xEEAA55)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Sulfur, 1, Oxygen, 4, RareEarth, 1)
                .build();

        OsmiumTetroxideSolution = new Material.Builder(3505, "osmium_tetroxide_solution")
                .fluid()
                .color(0x19197F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Osmium, 1, Oxygen, 4, Water, 1)
                .build();

        IridiumDioxide = new Material.Builder(3506, "iridium_dioxide")
                .dust().fluid()
                .color(0x9EDA7A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 1, Oxygen, 2)
                .build();

        SodiumTungstate = new Material.Builder(3507, "sodium_tungstate")
                .fluid()
                .color(0x7a7777)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 2, Tungsten, 1, Oxygen, 4)
                .build();

        AluminoSilicateWool = new Material.Builder(3508, "alumino_silicate_wool")
                .dust()
                .color(0xbbbbbb).iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .components(Aluminium, 2, Oxygen, 3, Silicon, 1, Oxygen, 2)
                .build();

        Dibismusthydroborate = new Material.Builder(3509, "dibismuthhydroborate")
                .dust()
                .color(0x00B749).iconSet(SAND)
                .components(Bismuth, 2, Hydrogen, 1, Boron, 1, Oxygen, 4)
                .build();

        BismuthTelluride = new Material.Builder(3510, "bismuth_telluride")
                .dust()
                .color(0x006B38).iconSet(SAND)
                .components(Bismuth, 2, Tellurium, 1, Oxygen, 2)
                .build();

        AmmoniumPersulfate = new Material.Builder(3511, "ammonium_persulfate")
                .fluid()
                .color(0x6464F5)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 2, Oxygen, 8)
                .build()
                .setFormula("(NH4)2S2O8", true);

        PlatinumSalt = new Material.Builder(3512, "platinum_salt")
                .dust()
                .color(0xFFFBC5)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, RareEarth, 1)
                .build();

        PlatinumSaltRefined = new Material.Builder(3513, "refined_platinum_salt")
                .dust()
                .color(0xFBFFC5).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, RareEarth, 1, Chlorine, 1)
                .build();

        PlatinumMetallicPowder = new Material.Builder(3514, "platinum_metallic_powder")
                .dust().ore()
                .color(0xFFFFC8).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, RareEarth, 1)
                .build();

        PlatinumResidue = new Material.Builder(3515, "platinum_residue")
                .dust()
                .color(0x64632E).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 2, RareEarth, 1, RareEarth, 1)
                .build();

        ReprecipitatedPlatinum = new Material.Builder(3516, "reprecipitated_platinum")
                .dust()
                .color(0xFBFBC5).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, Chlorine, 2)
                .build();

        PalladiumMetallicPowder = new Material.Builder(3517, "palladium_metallic_powder")
                .dust().ore()
                .color(0x808080).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, RareEarth, 1)
                .build();

        PalladiumSalt = new Material.Builder(3518, "palladium_salt")
                .dust()
                .color(0x808080).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, RareEarth, 1)
                .build();

        SodiumSulfate = new Material.Builder(3519, "sodium_sulfate")
                .dust()
                .iconSet(ROUGH)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        PotassiumDisulfate = new Material.Builder(3520, "potassium_disulfate")
                .dust()
                .color(0xFBBB66)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 7)
                .build();

        LeachResidue = new Material.Builder(3521, "leach_residue")
                .dust()
                .color(0x644629).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 2, RareEarth, 1, RareEarth, 1)
                .build();

        CalciumChloride = new Material.Builder(3522, "calcium_chloride")
                .dust()
                .components(Calcium, 1, Chlorine, 2)
                .build();

        SodiumRuthenate = new Material.Builder(3523, "sodium_ruthenate")
                .dust()
                .color(0x3A40CB).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Ruthenium, 1, Oxygen, 4)
                .build();

        RutheniumTetroxide = new Material.Builder(3524, "ruthenium_tetroxide")
                .dust().fluid()
                .color(0xC7C7C7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 4)
                .build();

        IridiumChloride = new Material.Builder(3525, "iridium_chloride")
                .gem()
                .color(0x78787A).iconSet(LAPIS)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 1, Chlorine, 3)
                .build();

        MetallicSludgeResidue = new Material.Builder(3526, "metallic_sludge_residue")
                .dust()
                .color(0x7FB200)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Copper, 1, Nickel, 1)
                .build();

        CrudeRhodiumMetal = new Material.Builder(3527, "crude_rhodium_metal")
                .dust()
                .color(0x666666)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, RareEarth, 1)
                .build();

        SodiumNitrate = new Material.Builder(3528, "sodium_nitrate")
                .dust()
                .color(0x846684).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .build();

        ZincSulfate = new Material.Builder(3529, "zinc_sulfate")
                .gem()
                .color(0xE1DC78).iconSet(QUARTZ)
                .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
                .build();

        RhodiumFilterCake = new Material.Builder(3530, "rhodium_filter_cake")
                .gem()
                .color(0xCE9A35).iconSet(QUARTZ)
                .components(Rhodium, 1, RareEarth, 1)
                .build();

        SilverOxide = new Material.Builder(3531, "silver_oxide")
                .dust()
                .color(0x4D4D4D)
                .components(Silver, 2, Oxygen, 1)
                .build();

        SilverChloride = new Material.Builder(3532, "silver_chloride")
                .dust()
                .color(0xFEFEFE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Silver, 1, Chlorine, 1)
                .build();

        PotassiumMetabisulfite = new Material.Builder(3533, "potassium_metabisulfite")
                .dust()
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .build();

        LeadNitrate = new Material.Builder(3534, "lead_nitrate")
                .dust()
                .color(0xFEFEFE)
                .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Pb(NO3)2", true);

        SodiumPotassiumAlloy = new Material.Builder(3535, "sodium_potassium_alloy")
                .dust().fluid()
                .color(0x252525).iconSet(SHINY)
                .components(Sodium, 7, Potassium, 3)
                .build();

        SodiumFluoride = new Material.Builder(3536, "sodium_fluoride")
                .dust()
                .colorAverage()
                .components(Sodium, 1, Fluorine, 2)
                .build();

        PotassiumFluoride = new Material.Builder(3537, "potassium_fluoride")
                .dust()
                .color(0xFDFDFD)
                .components(Potassium, 1, Fluorine, 1)
                .build();

        FLiNaK = new Material.Builder(3538, "flinak")
                .dust().fluid()
                .colorAverage().iconSet(SHINY)
                .components(Fluorine, 3, Lithium, 1, Sodium, 1, Potassium, 1)
                .build();

        FLiBe = new Material.Builder(3539, "flibe")
                .dust().fluid()
                .color(0x252525)
                .components(Fluorine, Lithium, 1, Beryllium, 1)
                .build();

        CalciumTungstate = new Material.Builder(3540, "calcium_tungstate")
                .dust()
                .color(0x6E6867).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tungsten, 1, CalciumCarbide, 1, Oxygen, 4)
                .build();

        TungsticAcid = new Material.Builder(3541, "tungstic_acid")
                .dust()
                .color(0xFFE700).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Tungsten, 1, Oxygen, 4)
                .build();

        TungstenTrioxide = new Material.Builder(3542, "tungsten_trioxide")
                .dust()
                .color(0x99FF97).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tungsten, 1, Oxygen, 3)
                .build();

        TungstenHexachloride = new Material.Builder(3543, "tungsten_hexachloride")
                .dust()
                .color(0x533F75).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tungsten, 1, Chlorine, 6)
                .build();

        Rhodochrosite = new Material.Builder(3544, "rhodochrosite")
                .dust().ore()
                .color(0xFF6699).iconSet(SHINY)
                .components(Manganese, 1, Carbon, 1, Oxygen, 3)
                .build();

        Fluorite = new Material.Builder(3445, "fluorite")
                .dust().ore()
                .color(0x009933).iconSet(SHINY)
                .components(Calcium, 1, Fluorine, 2)
                .build();

        Columbite = new Material.Builder(3446, "columbite")
                .dust().ore()
                .color(0xCCCC00).iconSet(SHINY)
                .components(Iron, 1, Niobium, 2, Oxygen, 6)
                .build();

        Pyrochlore = new Material.Builder(3447, "pyrochlore")
                .dust().ore()
                .color(0x996633).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 12, Strontium, 6, Barium, 6, RareEarth, 1, Thorium, 1, Uranium238, 1, Niobium, 26, Oxygen, 78, Fluorine, 26)
                .build()
                .setFormula("Ca12Sr6Ba6?ThUNb26O78F26", true);

        IndiumPhospide = new Material.Builder(3448, "indium_phosphide")
                .dust()
                .color(0x5C9C9C).iconSet(DULL)
                .components(Indium, 1, Phosphorus, 1)
                .build();

        Barytocalcite = new Material.Builder(3449, "barytocalcite")
                .dust().ore()
                .color(0xBF9C7C).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Calcium, 1, Carbon, 2, Oxygen, 6)
                .build()
                .setFormula("BaCa(CO3)2", true);

        Witherite = new Material.Builder(3450, "witherite")
                .dust().ore()
                .color(0xC6C29D).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .build();

        Arsenopyrite = new Material.Builder(3451, "arsenopyrite")
                .dust().ore()
                .color(0xAA9663).iconSet(METALLIC)
                .components(Iron, 1, Arsenic, 1, Sulfur, 1)
                .build();

        Gallite = new Material.Builder(3452, "gallite")
                .dust().ore()
                .color(0x7F7B9E).iconSet(SHINY)
                .components(Copper, 1, Gallium, 1, Sulfur, 2)
                .build();

        Bowieite = new Material.Builder(3453, "bowieite")
                .dust().ore()
                .color(0x8B8995).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Iridium, 1, Platinum, 1, Sulfur, 3)
                .build();

        Celestine = new Material.Builder(3454, "celestine")
                .dust().ore()
                .color(0x9DB1B8).iconSet(SHINY)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        CubicZirconia = new Material.Builder(3455, "cubic_zirconia")
                .gem(4)
                .color(0xFFDFE2).iconSet(DIAMOND)
                .flags(NO_SMELTING, GENERATE_LENS)
                .components(Zirconium, 1, Oxygen, 2)
                .build();

        Prasiolite = new Material.Builder(3456, "prasiolite")
                .gem().ore()
                .color(0x9EB749).iconSet(QUARTZ)
                .flags(GENERATE_LENS)
                .components(Silicon, 5, Oxygen, 10, Iron)
                .build()
                .setFormula("(SiO2)5Fe", true);

        Zircon = new Material.Builder(3457, "zircon")
                .gem().ore()
                .color(0xEB9E3F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Silicon, 1, Oxygen, 4)
                .build();

        LeadZirconateTitanate = new Material.Builder(3458, "lead_zirconate_titanate")
                .gem(3)
                .color(0x359ADE).iconSet(OPAL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Lead, 1, Zirconium, 1, Titanium, 1, Oxygen, 3)
                .build();

        Grisium = new Material.Builder(3459, "grisium")
                .ingot(3)
                .color(0x355D6A).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Titanium, 9, Carbon, 9, Potassium, 9, Lithium, 9, Sulfur, 9, Hydrogen, 5)
                .blastTemp(3850)
                .build();

        Staballoy = new Material.Builder(3460, "staballoy")
                .ingot(3)
                .color(0x444B42).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Uranium238, 9, Titanium, 1)
                .blastTemp(3450)
                .build()
                .setFormula("U9Ti", true);

        HastelloyN = new Material.Builder(3461, "hastelloy_n")
                .ingot(3)
                .color(0xDDDDDD).iconSet(METALLIC)
                .flags(GA_EXT2_METAL, GENERATE_DENSE)
                .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
                .blastTemp(4350)
                .build();

        Stellite = new Material.Builder(3462, "stellite")
                .ingot(3)
                .color(0x9991A5).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Cobalt, 8, Chrome, 9, Manganese, 5, Titanium, 2)
                .blastTemp(4310)
                .build();

        Talonite = new Material.Builder(3463, "talonite")
                .ingot()
                .color(0x9991A5).iconSet(SHINY)
                .flags(GA_EXT2_METAL)
                .components(Cobalt, 4, Chrome, 3, Phosphorus, 2, Molybdenum, 1)
                .blastTemp(3452)
                .build();

        Nitinol60 = new Material.Builder(3464, "nitinol_60")
                .ingot()
                .color(0xCCB0EC).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Nickel, 2, Titanium, 3)
                .blastTemp(2041)
                .build();

        BabbittAlloy = new Material.Builder(3465, "babbitt_alloy")
                .ingot()
                .color(0xA19CA4).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Tin, 5, Lead, 36, Antimony, 8, Arsenic, 1)
                .blastTemp(737)
                .build();

        HG1223 = new Material.Builder(3466, "hg_1223")
                .ingot()
                .color(0x245397).iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_DENSE)
                .components(Mercury, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 8)
                .blastTemp(5325)
                .cableProperties(GTValues.V[GTValues.ZPM], 2, 2)
                .build();

        IncoloyMA956 = new Material.Builder(3467, "incoloy_ma")
                .ingot()
                .color(0xAABEBB).iconSet(METALLIC)
                .flags(GA_EXT2_METAL, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Iron, 16, Aluminium, 3, Chrome, 5, Yttrium, 1)
                .blastTemp(5150)
                .build();

        ZirconiumCarbide = new Material.Builder(3468, "zirconium_carbide")
                .ingot()
                .color(0xFFDACD).iconSet(SHINY)
                .flags(GA_EXT2_METAL)
                .components(Zirconium, 1, Carbon, 1)
                .blastTemp(1200)
                .build();

        RhodiumPlatedPalladium = new Material.Builder(3469, "rhodium_plated_palladium")
                .ingot()
                .colorAverage().iconSet(METALLIC)
                .flags(GA_EXT2_METAL, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Palladium, 3, Rhodium, 1)
                .toolStats(14.0f, 6.5f, 2900, 48)
                .blastTemp(4500)
                .build();

        Ruridit = new Material.Builder(3470, "ruridit")
                .ingot()
                .color(0xA4A4A4).iconSet(METALLIC)
                .flags(GA_CORE_METAL)
                .components(Ruthenium, 2, Iridium, 1)
                .blastTemp(9950)
                .build();

        GoldAlloy = new Material.Builder(3471, "gold_alloy")
                .ingot()
                .color(0xBBA52B).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Copper, 3, Gold, 1, RareEarth, 1)
                .build();

        PreciousMetal = new Material.Builder(3472, "precious_metal")
                .ingot().ore()
                .color(0xB99023).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Gold, 1, RareEarth, 1)
                .build();

        LithiumFluoride = new Material.Builder(3473, "lithium_fluoride")
                .ingot()
                .color(0x757575).iconSet(SHINY)
                .components(Lithium, 1, Fluorine, 1)
                .build();

        BerylliumFluoride = new Material.Builder(3474, "beryllium_fluoride")
                .ingot()
                .color(0x757575).iconSet(SHINY)
                .components(Beryllium, 1, Fluorine, 2)
                .build();

        LeadBismuthEutectic = new Material.Builder(3475, "lead_bismuth_eutatic")
                .dust().fluid()
                .colorAverage().iconSet(SHINY)
                .components(Lead, 3, Bismuth, 7)
                .build();

        ReactorSteel = new Material.Builder(3476, "reactor_steel")
                .ingot().fluid()
                .color(0xB4B3B0).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION, GENERATE_DENSE)
                .components(Iron, 15, Niobium, 1, Vanadium, 4, Carbon, 2)
                .build();

        EnrichedNaquadahAlloy = new Material.Builder(3477, "enriched_naquadah_alloy")
                .ingot(5)
                .color(0x403F3D).iconSet(SHINY)
                .flags(GA_EXT2_METAL, DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Dubnium, 1, Rubidium, 2, Einsteinium255, 1)
                .blastTemp(10000)
                .build();

        BlackTitanium = new Material.Builder(3478, "black_titanium")
                .ingot(7)
                .color(0x6C003B).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Titanium, 26, Lanthanum, 6, Tungsten, 4, Cobalt, 3, Manganese, 2, Phosphorus, 2, Palladium, 2, Niobium, 1, Argon, 5)
                .blastTemp(11500)
                .build();

        ProtoAdamantium = new Material.Builder(3479, "proto_adamantium")
                .ingot(7)
                .color(0x4662D4).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Adamantium, 3, Promethium, 2)
                .blastTemp(11244)
                .build();

        TriniumTitanium = new Material.Builder(3480, "trinium_titanium")
                .ingot(7)
                .color(0x9986A3).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Trinium, 2, Titanium, 1)
                .build();

        LithiumTitanate = new Material.Builder(3481, "lithium_titanate")
                .ingot(5)
                .color(0xFE71A9).iconSet(METALLIC)
                .flags(GA_CORE_METAL, GENERATE_PLATE, DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Lithium, 2, Titanium, 1, Oxygen, 3)
                .blastTemp(2500)
                .build();

        ElectricallyImpureCopper = new Material.Builder(3482, "electrically_impure_copper")
                .ingot()
                .color(0x765A30).iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Copper, 1, RareEarth, 1)
                .build();

        ThoriaDopedTungsten = new Material.Builder(3483, "thoria_doped_tungsten")
                .ingot()
                .colorAverage().iconSet(SHINY)
                .flags(GENERATE_ROD, GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Tungsten, 9)
                .build();

        BariumTitanate = new Material.Builder(3484, "barium_titanate")
                .ingot()
                .color(0x99FF99).iconSet(METALLIC)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .build();

        TantalumHafniumSeaborgiumCarbide = new Material.Builder(3485, "tantalum_hafnium_seaborgium_carbide")
                .ingot()
                .color(0x2C2C2C).iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Tantalum, 12, Hafnium, 3, Seaborgium, 1, Carbon, 16)
                .blastTemp(5200)
                .build();

        BismuthRuthenate = new Material.Builder(3486, "bismuth_ruthenate")
                .ingot()
                .color(0x94CF5C)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Bismuth, 2, Ruthenium, 2, Oxygen, 7)
                .build();

        BismuthIridiate = new Material.Builder(3487, "bismuth_iridiate")
                .ingot()
                .color(0x478A6B)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Bismuth, 2, Iridium, 2, Oxygen, 7)
                .build();

        RutheniumDioxide = new Material.Builder(3488, "ruthenium_dioxide")
                .ingot()
                .colorAverage()
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 2)
                .build();

        GermaniumTungstenNitride = new Material.Builder(3489, "germanium_tungsten_nitride")
                .ingot()
                .color(0x8F8FCF)
                .flags(GENERATE_PLATE)
                .components(Germanium, 3, Tungsten, 3, Nitrogen, 10)
                .blastTemp(5400)
                .build();

        LithiumNiobate = new Material.Builder(3490, "lithium_niobate")
                .ingot()
                .color(0xCFCF3A).iconSet(METALLIC)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .blastTemp(6700)
                .build();

        SuperheavyHAlloy = new Material.Builder(3491, "superheavy_h_alloy")
                .ingot(6)
                .color(0xE84B36).iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
                .blastTemp(10600)
                .build();

        SuperheavyLAlloy = new Material.Builder(3491, "superheavy_l_alloy")
                .ingot(6)
                .color(0x2B45DF).iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1, Meitnerium, 1, Roentgenium, 1)
                .blastTemp(10600)
                .build();

        Periodicium = new Material.Builder(3492, "periodicium")
                .ingot(6)
                .color(0x3D4BF6).iconSet(SHINY)
                .flags(DISABLE_AUTOGENERATED_MIXER_RECIPE, DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Helium, 1, Lithium, 1, Beryllium, 1, Boron, 1, Carbon, 1, Nitrogen, 1, Oxygen, 1, Fluorine, 1, Neon, 1, Sodium, 1, Magnesium, 1, Aluminium, 1, Silicon, 1, Phosphorus, 1, Sulfur, 1, Chlorine, 1, Argon, 1, Potassium, 1, Calcium, 1, Scandium, 1, Titanium, 1, Vanadium, 1, Chrome, 1, Manganese, 1, Iron, 1, Cobalt, 1, Nickel, 1, Copper, 1, Zinc, 1, Gallium, 1, Germanium, 1, Arsenic, 1, Selenium, 1, Bromine, 1, Krypton, 1, Rubidium, 1, Strontium, 1, Yttrium, 1, Zirconium, 1, Niobium, 1, Molybdenum, 1, Technetium, 1, Ruthenium, 1, Rhodium, 1, Palladium, 1, Silver, 1, Cadmium, 1, Indium, 1, Tin, 1, Antimony, 1, Tellurium, 1, Iodine, 1, Xenon, 1, Caesium, 1, Barium, 1, Lanthanum, 1, Cerium, 1, Praseodymium, 1, Neodymium, 1, Promethium, 1, Samarium, 1, Europium, 1, Gadolinium, 1, Terbium, 1, Dysprosium, 1, Holmium, 1, Erbium, 1, Thulium, 1, Ytterbium, 1, Lutetium, 1, Hafnium, 1, Tantalum, 1, Tungsten, 1, Rhenium, 1, Osmium, 1, Iridium, 1, Platinum, 1, Gold, 1, Mercury, 1, Thallium, 1, Lead, 1, Bismuth, 1, Polonium, 1, Astatine, 1, Radon, 1, Francium, 1, Radium, 1, Actinium, 1, Thorium, 1, Materials.Protactinium, 1, Uranium238, 1, Materials.Neptunium, 1, Americium, 1, Materials.Curium, 1, Materials.Berkelium, 1, Materials.Californium, 1, Materials.Einsteinium, 1, Materials.Fermium, 1, Materials.Mendelevium, 1, Nobelium, 1, Lawrencium, 1, Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1, Meitnerium, 1, Roentgenium, 1, Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
                .blastTemp(13500)
                .build()
                .setFormula("HHeLiBeBCNOFNeNaMgAlSiPSClArKCaScTiVCrMnFeCoNiCuZnGaGeAsSeBrKrRbSrYZrNbMoTcRuRhPdAgCdInSnSbTeIXeCsBaLaCePrNdPmSmEuGdTbDyHoErTnLuYbHfTaWReOsIrPtAuHgTlPbBiPoAtRdFrRaAcThPaUNpAmCmBkCfEsFmMdNoLrRfDbSgBhHsMtRgCnNhFlMcLvTsOg");

        NaquadriaticTaranium = new Material.Builder(3493, "naquadriatic_taranium")
                .ingot()
                .colorAverage().iconSet(DULL)
                .flags(GA_STD_METAL, GENERATE_LONG_ROD)
                .components(Naquadria, 1, Taranium, 1)
                .blastTemp(11200)
                .build();

        AntimonyPentafluoride = new Material.Builder(3494, "antimony_pentafluoride")
                .fluid()
                .colorAverage()
                .components(Antimony, 1, Fluorine, 5)
                .build();

        FluoroantimonicAcid = new Material.Builder(3495, "fluoroantimonic_acid")
                .fluid()
                .color(0x8DA2A5)
                .components(Hydrogen, 2, Antimony, 1, Fluorine, 7)
                .build();

        FluoronaquadricAcid = new Material.Builder(3496, "fluoronaquadric_acid")
                .fluid()
                .color(0x485D60)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Naquadah, 1, Fluorine, 4)
                .build();

        EnrichedFluoronaquadricAcid = new Material.Builder(3497, "fluoronaquadric_acid")
                .fluid()
                .color(0x485D60)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, NaquadahEnriched, 1, Fluorine, 4)
                .build();

        FluoronaquadriaticAcid = new Material.Builder(3498, "fluoronaquadric_acid")
                .fluid()
                .color(0x485D60)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Naquadria, 1, Fluorine, 4)
                .build();

        NaquadahDifluoride = new Material.Builder(3499, "naquadah_difluoride")
                .fluid()
                .color(0x324649)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, Fluorine, 2)
                .build();

        EnrichedNaquadahDifluoride = new Material.Builder(3500, "enriched_naquadah_difluoride")
                .fluid()
                .color(0x141E1F)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, Fluorine, 2)
                .build();

        EnrichedNaquadahDifluoride = new Material.Builder(3501, "naquadria_difluoride")
                .fluid()
                .color(0x141E1F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, Fluorine, 2)
                .build();

        NaquadriaHexafluoride = new Material.Builder(3502, "naquadria_hexafluoride")
                .fluid()
                .color(0x111C27)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, Fluorine, 6)
                .build();

        RadonDifluoride = new Material.Builder(3503, "radon_difluoride")
                .fluid()
                .color(0x9966FF)
                .components(Radon, 1, Fluorine, 2)
                .build();

        RadonNaquadriaoctafluoride = new Material.Builder(3504, "radon_naquadriaoctafluoride")
                .fluid()
                .color(0x111C27)
                .flags(DISABLE_DECOMPOSITION)
                .components(Radon, 1, Naquadria, 1, Fluorine, 8)
                .build();

        XenonTrioxide = new Material.Builder(3505, "xenon_trioxide")
                .fluid()
                .color(0x432791)
                .components(Xenon, 1, Oxygen, 3)
                .build();

        CaesiumFluoride = new Material.Builder(3506, "caesium_fluoride")
                .fluid()
                .color(0xABAB69)
                .components(Caesium, 1, Fluorine, 1)
                .build();

        RadonTrioxide = new Material.Builder(3507, "radon_trioxide")
                .fluid()
                .color(0x9966FF)
                .components(Radon, 1, Oxygen, 3)
                .build();

        NaquadriaCaesiumXenonNonfluoride = new Material.Builder(3508, "naquadria_caesium_xenon_nonfluoride")
                .fluid()
                .color(0x1C1C5E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, Caesium, 1, Xenon, 1, Fluorine, 9)
                .build();

        EnrichedNaquadahhexafluoride = new Material.Builder(3509, "enriched_naquadahhexafluoride")
                .fluid()
                .color(0x030330)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, Fluorine, 6)
                .build();

        EnrichedXenonHexafluoronaquadate = new Material.Builder(3510, "enriched_xenon_hexafluoronaquadate")
                .fluid()
                .color(0x1E1EC2)
                .flags(DISABLE_DECOMPOSITION)
                .components(Xenon, 1, NaquadahEnriched, 1, Fluorine, 6)
                .build();

        AuricChloride = new Material.Builder(3511, "auric_chloride")
                .fluid()
                .color(0xdFFB50)
                .flags(DISABLE_DECOMPOSITION)
                .components(Gold, 2, Chlorine, 6)
                .build()
                .setFormula("(AuCl3)2", true);

        BromineTrifluoride = new Material.Builder(3512, "bromine_trifluoride")
                .fluid()
                .color(0xFCDE1D)
                .components(Bromine, 1, Fluorine, 3)
                .build();

        XenoauricFluoroantimonicAcid = new Material.Builder(3513, "xenoauric_fluoroantimonic_acid")
                .fluid()
                .color(0x685B08)
                .flags(DISABLE_DECOMPOSITION)
                .components(Xenon, 1, Gold, 1, Antimony, 1, Fluorine, 6)
                .build();

        NaquadahSulfate = new Material.Builder(3514, "naquadah_sulfate")
                .fluid()
                .color(0x38330F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, Sulfur, 1, Oxygen, 4)
                .build();

        ClearNaquadahLiquid = new Material.Builder(3515, "clear_naquadah_liquid")
                .fluid()
                .color(0xa89F9E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1)
                .build();

        ComplicatedNaquadahGas = new Material.Builder(3516, "complicated_naquadah_gas")
                .fluid(Material.FluidType.GAS)
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1, RareEarth, 1)
                .build();

        ComplicatedHeavyNaquadah = new Material.Builder(3517, "complicated_heavy_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1, RareEarth, 1)
                .build();

        ComplicatedMediumNaquadah = new Material.Builder(3518, "complicated_medium_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1, RareEarth, 1)
                .build();

        ComplicatedLightNaquadah = new Material.Builder(3519, "complicated_light_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1, RareEarth, 1)
                .build();

        NaquadahGas = new Material.Builder(3520, "naquadah_gas")
                .fluid(Material.FluidType.GAS)
                .color(0x575757)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        HeavyNaquadah = new Material.Builder(3521, "heavy_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        MediumNaquadah = new Material.Builder(3522, "medium_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        LightNaquadah = new Material.Builder(3523, "light_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        FCrackedHeavyNaquadah = new Material.Builder(3524, "f_cracked_heavy_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, Fluorine, 1)
                .build();

        FCrackedMediumNaquadah = new Material.Builder(3525, "f_cracked_medium_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, Fluorine, 1)
                .build();

        FCrackedLightNaquadah = new Material.Builder(3526, "f_cracked_light_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, Fluorine, 1)
                .build();

        HeavyNaquadahFuel = new Material.Builder(3527, "heavy_naquadah_fuel")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        MediumNaquadahFuel = new Material.Builder(3528, "medium_naquadah_fuel")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        LightNaquadahFuel = new Material.Builder(3529, "light_naquadah_fuel")
                .fluid(Material.FluidType.GAS)
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        ENaquadahSolution = new Material.Builder(3530, "e_naquadah_solution")
                .fluid()
                .color(0x523B3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1)
                .build();

        ClearENaquadahLiquid = new Material.Builder(3531, "clear_e_naquadah_liquid")
                .fluid()
                .color(0xA89F9E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1)
                .build();

        ComplicatedHeavyENaquadah = new Material.Builder(3532, "complicated_heavy_e_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1, RareEarth, 1)
                .build();

        ComplicatedMediumENaquadah = new Material.Builder(3533, "complicated_medium_e_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1, RareEarth, 1)
                .build();

        ComplicatedLightENaquadah = new Material.Builder(3534, "complicated_light_e_naquadah")
                .fluid()
                .color(0x403D3D)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1, RareEarth, 1)
                .build();

        HeavyENaquadah = new Material.Builder(3535, "heavy_e_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        MediumENaquadah = new Material.Builder(3536, "medium_e_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        LightENaquadah = new Material.Builder(3537, "light_e_naquadah")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        RnCrackedHeavyENaquadah = new Material.Builder(3538, "rn_cracked_heavy_e_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Radon, 1, NaquadahEnriched, 1)
                .build();

        RnCrackedMediumENaquadah = new Material.Builder(3539, "rn_cracked_medium_e_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Radon, 1, NaquadahEnriched, 1)
                .build();

        RnCrackedLightNaquadah = new Material.Builder(3560, "rn_cracked_light_e_naquadah")
                .fluid()
                .color(0x505E5B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Radon, 1, NaquadahEnriched, 1)
                .build();

        HeavyENaquadahFuel = new Material.Builder(3561, "heavy_e_naquadah_fuel")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        MediumENaquadahFuel = new Material.Builder(3562, "medium_e_naquadah_fuel")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        LightENaquadahFuel = new Material.Builder(3563, "light_e_naquadah_fuel")
                .fluid()
                .color(0x2E2E2E)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        NaquadriaSolution = new Material.Builder(3564, "naquadria_solution")
                .fluid()
                .color(0x523B3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1)
                .build();

        HyperFuelI = new Material.Builder(3565, "hyper_fluid_i")
                .fluid()
                .color(0xFAFF5E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, NaquadahEnriched, 1, Naquadria, 1, Rutherfordium, 1, Plutonium239)
                .build();

        HyperFuelII = new Material.Builder(3566, "hyper_fluid_ii")
                .fluid()
                .color(0xD8DB67)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, NaquadahEnriched, 1, Naquadria, 1, Dubnium, 1, Materials.Curium)
                .build();

        HyperFuelIII = new Material.Builder(3567, "hyper_fluid_iii")
                .fluid()
                .color(0x8F9146)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, NaquadahEnriched, 1, Naquadria, 1, Adamantium, 1, Materials.Californium)
                .build();

        HyperFuelIV = new Material.Builder(3568, "hyper_fluid_iv")
                .fluid()
                .color(0x4D4E31)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, NaquadahEnriched, 1, Naquadria, 1, Adamantium, 1, Materials.Californium, Neutronium, 1, Taranium, 1)
                .build();

        ChlorosulfonicAcid = new Material.Builder(3569, "chlorosulfonic_acid")
                .fluid()
                .color(0x916C1d)
                .components(Hydrogen, 1, Sulfur, 1, Oxygen, 3, Chlorine, 1)
                .build();

        SiliconFluoride = new Material.Builder(3570, "silicon_fluoride")
                .fluid()
                .color(0xB2B4B4)
                .components(Silicon, 1, Fluorine, 4)
                .build();

        PhosphorusTrichloride = new Material.Builder(3571, "phosphorus_trichloride")
                .fluid()
                .colorAverage()
                .components(Phosphorus, 1, Chlorine, 3)
                .build();

        PhosphorylChloride = new Material.Builder(3572, "phosphoryl_chloride")
                .fluid()
                .color(0xE6E6E6)
                .components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
                .build();

        RheniumSulfuricSolution = new Material.Builder(3573, "rhenium_sulfuric_solution")
                .fluid()
                .color(0xBABAFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhenium, 1, Sulfur, 1, RareEarth, 1)
                .build();

        AmmoniumSulfate = new Material.Builder(3574, "ammonium_sulfate")
                .fluid()
                .color(0x6464F5)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 1, Oxygen, 4)
                .build()
                .setFormula("(NH4)2SO4", true);

        AmmoniumPerrhenate = new Material.Builder(3575, "ammonium_perrhenate")
                .fluid()
                .color(0x1C1C45)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ammonia, 1, Rhenium, 1, Oxygen, 4)
                .build();

        ElectronDegenerateRheniumPlasma = new Material.Builder(3576, "degenerate_rhenium_plasma") //todo fancy
                .plasma()
                .color(0x6666FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhenium, 1)
                .build();

        BoricAcid = new Material.Builder(3577, "boric_acid")
                .fluid()
                .color(0xD5D2D7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 3, Boron, 1, Oxygen, 3)
                .build();

        FluoroBoricAcid = new Material.Builder(3578, "fluoroboric_acid")
                .fluid()
                .color(0xD5D2D7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Boron, 1, Fluorine, 4)
                .build();

        BoronFluoride = new Material.Builder(3578, "boron_fluoride")
                .fluid()
                .colorAverage()
                .components(Boron, 1, Fluorine, 3)
                .build();

        HydrobromicAcid = new Material.Builder(3579, "hydrobromic_acid")
                .fluid()
                .color(0xBC6C53)
                .components(Hydrogen, 1, Bromine, 1)
                .build();

        ThionylChloride = new Material.Builder(3580, "thionyl_chloride")
                .fluid()
                .color(0xF9F7E5)
                .components(Sulfur, 1, Oxygen, 1, Chlorine, 2)
                .build();

        Silvertetrafluoroborate = new Material.Builder(3581, "silvertetrafluoroborate")
                .fluid()
                .colorAverage()
                .components(Silver, 1, Boron, 1, Fluorine, 1)
                .build();

        PotassiumHydroxide = new Material.Builder(3581, "potassium_hydroxide")
                .fluid()
                .colorAverage()
                .components(Potassium, 1, Oxygen, 1, Hydrogen, 1)
                .build();

        ZrHfChloride = new Material.Builder(3582, "zrhf_chloride")
                .fluid()
                .color(0x51D351)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Hafnium, 1, Chlorine, 4)
                .build();

        ZrHfOxyChloride = new Material.Builder(3583, "zrhf_oxychloride")
                .fluid()
                .color(0x51A351)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Hafnium, 1, Oxygen, 1, Chlorine, 2)
                .build();

        ZincPoorMix = new Material.Builder(3584, "zinc_poor_mix")
                .fluid()
                .color(0xB8B8B8)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 1, RareEarth, 1)
                .build();

        IronPoorMix = new Material.Builder(3585, "iron_poor_mix")
                .fluid()
                .color(0xFF7410)
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, RareEarth, 1)
                .build();

        IndiumHydroxideConcentrate = new Material.Builder(3586, "indium_hydroxide_concentrate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("In(OH)3", true);

        ZincAmalgam = new Material.Builder(3587, "zinc_amalgam")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Zinc, 1, Mercury, 1)
                .build();

        GermaniumChloride = new Material.Builder(3587, "germanium_chloride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Germanium, 1, Chlorine, 4)
                .build();

        Ozone = new Material.Builder(3588, "ozone")
                .fluid()
                .color(0x0099FF)
                .components(Oxygen, 3)
                .build();

        NitrogenPentoxide = new Material.Builder(3589, "nitrogen_pentoxide")
                .fluid()
                .color(0x0033C0)
                .components(Nitrogen, 2, Oxygen, 5)
                .build();

        CarbonSulfide = new Material.Builder(3590, "carbon_sulfide")
                .fluid()
                .color(0x40FFBF)
                .components(Carbon, 1, Sulfur, 2)
                .build();

        ChloroPlatinicAcid = new Material.Builder(3591, "chloroplatinic_acid")
                .fluid()
                .color(0xFFBA54)
                .components(Hydrogen, 2, Platinum, 1, Chlorine, 6)
                .build();

        SuperheavyMix = new Material.Builder(3592, "superheavy_mix")
                .fluid()
                .color(0x403737)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Seaborgium, 1, Bohrium, 1, Rutherfordium, 1, Dubnium, 1)
                .build();

        HotMetastableOganesson = new Material.Builder(3592, "hot_metastable_oganesson")
                .fluid()
                .color(0x521973)
                .flags(DISABLE_DECOMPOSITION)
                .components(MetastableOganesson, 1)
                .fluidTemp(25000)
                .build();

        TitaniumTetrafluoride = new Material.Builder(3593, "titanium_tetrafluoride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Titanium, 1, Fluorine, 4)
                .build();

        Titanium50Tetrafluoride = new Material.Builder(3594, "titanium50_tetrafluoride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Titanium50, Fluorine, 4)
                .build();

        CNOcatalyst = new Material.Builder(3595, "cno_catalyst")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon12, 1, Carbon13, 1, Nitrogen14, 1, Nitrogen15, 1)
                .build();

        OgannesonBreedingBase = new Material.Builder(3596, "og_breeding_base")
                .fluid()
                .colorAverage()
                .components(Titanium50, 1, Californium252, 1)
                .build();

        FlYbPlasma = new Material.Builder(3597, "flyb_plasma")
                .plasma()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Flerovium, 1, Ytterbium, 1)
                .build();

        HeliumCNO = new Material.Builder(3598, "helium_rich_cno")
                .fluid(Material.FluidType.GAS).plasma()
                .color(0x59FFA6)
                .flags(DISABLE_DECOMPOSITION)
                .components(Helium, 1, RareEarth, 1)
                .build();

        SelenateSolution = new Material.Builder(3599, "selenate_solution")
                .fluid()
                .color(0xC1C46A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Selenium, 1, Carbon, 1, Oxygen, 3)
                .build();

        TitaniumDisulfate = new Material.Builder(3600, "titanium_disulfate")
                .fluid()
                .color(0xDC3D7C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Titanium, 1, Sulfur, 1, Oxygen, 5)
                .build()
                .setFormula("TiO(SO4)", true);

        FluoroniobicAcid = new Material.Builder(3601, "fluroniobic_acid")
                .fluid()
                .color(0x73FF00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Niobium, 1, Fluorine, 7)
                .build();

        FluorotantalicAcid = new Material.Builder(3602, "flurotantalic_acid")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Tantalum, 1, Fluorine, 7)
                .build();

        OxypentafluoroNiobate = new Material.Builder(3603, "oxypentafluoroniobate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Niobium, 1, Oxygen, 1, Fluorine, 5)
                .build();

        HeptafluoroTantalate = new Material.Builder(3604, "heptafluorotantalate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Tantalum, 1, Fluorine, 7)
                .build();

        HydroselenicAcid = new Material.Builder(3605, "hydroselenic_acid")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Selenium, 1)
                .build();

        BariumTitanatePreparation = new Material.Builder(3606, "barium_titanate_preparation")
                .fluid()
                .color(0x99FF99)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .build();

        CarbonTetrachloride = new Material.Builder(3607, "carbon_tetrachloride")
                .fluid()
                .color(0x2D8020)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Carbon, 1, Chlorine, 4)
                .build();

        ActiniumSuperhydride = new Material.Builder(3608, "actinium_superhydride_plasma")
                .fluid().plasma()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Actinium, 1, Hydrogen, 12)
                .build();

        Diborane = new Material.Builder(3609, "diborane")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Boron, 2, Hydrogen, 6)
                .build()
                .setFormula("(BH3)2");

        FluorosilicicAcid = new Material.Builder(3610, "fluorosilicic_acid")
                .fluid()
                .color(0x2CCF2A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Silicon, 1, Fluorine, 6)
                .build();

        AmmoniumFluoride = new Material.Builder(3611, "ammonium_fluoride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 5, Fluorine, 1)
                .build()
                .setFormula("NH4HF");

        MercuryNitrate = new Material.Builder(3612, "mercury_nitrate")
                .fluid()
                .color(0xD6B8AD)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Mercury, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Hg(NO3)2", true);

        KryptonDifluoride = new Material.Builder(3613, "krypton_difluoride")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Krypton, 1, Fluorine, 2)
                .build();

        DeuteriumSuperheavyMix = new Material.Builder(3614, "deuterium_superheavy_mix")
                .fluid()
                .color(0xA2D2A4)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Deuterium, 1, MetastableFlerovium, 1, MetastableHassium, 1, MetastableOganesson, 1)
                .build();

        ScandiumTitanium50Mix = new Material.Builder(3615, "scandium_titanium50_mix")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Scandium, 1, Titanium50, 1)
                .build();

        RadonRadiumMix = new Material.Builder(3616, "radon_radium_mix")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Radon, 1, Radium, 1)
                .build();

        Trichloroflerane = new Material.Builder(3617, "trichloroferane")
                .fluid()
                .color(0x521973)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(MetastableFlerovium, 1, Chlorine, 3)
                .build();

        NobleGasesMixture = new Material.Builder(3618, "noble_gases_mixture")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Helium, 1, Neon, 1, Argon, 1, Krypton, 1, Xenon, 1, Radon, 1)
                .build();

        NonMetals = new Material.Builder(3619, "non_metals")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Boron, 1, Carbon, 1, Phosphorus, 1, Sulfur, 1, Arsenic, 1, Selenium, 1, Tellurium, 1, Iodine, 1, Astatine, 1, Oxygen, 1, Nitrogen, 1, Hydrogen, 1, Fluorine, 1, Chlorine, 1, Bromine, 1)
                .build();

        Iron2Chloride = new Material.Builder(3620, "iron_ii_chloride")
                .fluid()
                .colorAverage()
                .components(Iron, 1, Chlorine, 2)
                .build();

        FluorophosphoricAcid = new Material.Builder(3621, "fluorophosphoric_acid")
                .fluid()
                .colorAverage()
                .components(Fluorine, 1, Hydrogen, 2, Oxygen, 3, Phosphorus, 1)
                .build();

        ChloroauricAcid = new Material.Builder(3622, "chloroauric_acid")
                .fluid()
                .color(0xDFD11F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, Gold, 1, Chlorine, 1, RareEarth, 1)
                .build();

        LiquidHelium = new Material.Builder(3623, "liquid_helium")
                .fluid()
                .color(0xDDDD00)
                .flags(DISABLE_DECOMPOSITION)
                .components(Helium, 1)
                .build();

        SuperfluidHelium = new Material.Builder(3624, "superfluid_helium")
                .fluid()
                .color(0xDDDD22)
                .flags(DISABLE_DECOMPOSITION)
                .components(Helium, 1)
                .build();

        LiquidHelium3 = new Material.Builder(3625, "liquid_helium_3")
                .fluid()
                .color(0xDDDD44)
                .flags(DISABLE_DECOMPOSITION)
                .components(Helium3, 1)
                .build();

        HotNitrogen = new Material.Builder(3626, "hot_nitrogen")
                .fluid(Material.FluidType.GAS)
                .color(0x22BFC1)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1)
                .build();

        SupercriticalDeuterium = new Material.Builder(3627, "supercritical_deuterium")
                .fluid()
                .color(0xDDDD66)
                .flags(DISABLE_DECOMPOSITION)
                .components(Deuterium, 1)
                .build();

        SupercriticalSodium = new Material.Builder(3628, "supercritical_sodium")
                .fluid()
                .color(0x0000AB)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1)
                .build();

        NaquadricCompound = new Material.Builder(3629, "naquadric_compound")
                .dust(4).ore()
                .color(0x323232).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1)
                .build();

        EnrichedNaquadricCompound = new Material.Builder(3630, "enriched_naquadric_compound")
                .dust(4).ore()
                .color(0x3C3C3C).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1)
                .build();

        NaquadriaticCompound = new Material.Builder(3631, "naquadriatic_compound")
                .dust(3).ore()
                .color(0x1E1E1E).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1)
                .build();

        QuantumDots = new Material.Builder(3632, "quantumdots") //todo fancy
                .fluid()
                .color(0xFF0000)
                .flags(DISABLE_DECOMPOSITION)
                .components(Cadmium, 1, Selenium, 1)
                .build();

        SemisolidHydrogen = new Material.Builder(3633, "semisolidhydrogen")
                .fluid()
                .color(0x044C4B)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1)
                .build();

        MicrocrystallizingHydrogen = new Material.Builder(3634, "microcrystallizinghydrogen")
                .fluid()
                .color(0x155D5C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1)
                .build();

        NitrousAcid = new Material.Builder(3635, "nitrous_acid")
                .fluid()
                .color(0x1E73B0)
                .components(Hydrogen, 1, Nitrogen, 1, Oxygen, 2)
                .build();

        SelenousAcid = new Material.Builder(3636, "selenous_acid")
                .fluid()
                .colorAverage()
                .components(Hydrogen, 2, Selenium, 1, Oxygen, 3)
                .build();

        TetrafluoroboricAcid = new Material.Builder(3637, "tetrafluoroboric_acid")
                .fluid()
                .colorAverage()
                .components(Hydrogen, 1, Boron, 1, Fluorine, 4)
                .build();

        AntimonyTrichloride = new Material.Builder(3638, "antimony_trifluoride")
                .dust()
                .color(0xC7C7C7).iconSet(ROUGH)
                .components(Antimony, 1, Fluorine, 3)
                .build();

        IndiumTrifluoride = new Material.Builder(3639, "indium_trifluoride")
                .dust()
                .color(0x2B0F48)
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, Fluorine, 3)
                .build();

        IndiumTrioxide = new Material.Builder(3640, "indium_trioxide")
                .dust()
                .color(0x2B0F48).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 2, Oxygen, 3)
                .build();

        NaquadahConcentrate = new Material.Builder(3641, "naquadah_concentrate")
                .dust()
                .color(0x323232).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, RareEarth, 1)
                .build();

        EnrichedNaquadahConcentrate = new Material.Builder(3642, "enriched_naquadah_concentrate")
                .dust()
                .color(0x282828).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, RareEarth, 1)
                .build();

        NaquadriaConcentrate = new Material.Builder(3643, "naquadria_concentrate")
                .dust()
                .color(0x1E1E1E).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, RareEarth, 1)
                .build();

        AuricFluoride = new Material.Builder(3644, "auric_fluoride")
                .dust()
                .color(0xDFFB50).iconSet(SHINY)
                .components(Gold, 1, Fluorine, 3)
                .build();

        ThUSludge = new Material.Builder(3645, "thorium_uranium_sludge")
                .dust()
                .color(0x002908)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Uranium238, 1)
                .build()
                .setFormula("ThU", false);

        LanthanumOxide = new Material.Builder(3646, "lanthanum_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Lanthanum, 2, Oxygen, 3)
                .build();

        PraseodymiumOxide = new Material.Builder(3647, "praseodymium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Praseodymium, 2, Oxygen, 3)
                .build();

        NeodymiumOxide = new Material.Builder(3648, "neodymium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Neodymium, 2, Oxygen, 3)
                .build();

        CeriumOxide = new Material.Builder(3649, "cerium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Cerium, 2, Oxygen, 3)
                .build();

        EuropiumOxide = new Material.Builder(3650, "europium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Europium, 2, Oxygen, 3)
                .build();

        GadoliniumOxide = new Material.Builder(3651, "gadolinium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Gadolinium, 2, Oxygen, 3)
                .build();

        SamariumOxide = new Material.Builder(3652, "samarium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Samarium, 2, Oxygen, 3)
                .build();

        TerbiumOxide = new Material.Builder(3653, "terbium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Terbium, 2, Oxygen, 3)
                .build();

        DysprosiumOxide = new Material.Builder(3654, "dysprosium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Dysprosium, 2, Oxygen, 3)
                .build();

        HolmiumOxide = new Material.Builder(3655, "holmium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Holmium, 2, Oxygen, 3)
                .build();

        ErbiumOxide = new Material.Builder(3656, "erbium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Erbium, 2, Oxygen, 3)
                .build();

        ThuliumOxide = new Material.Builder(3657, "thulium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thulium, 2, Oxygen, 3)
                .build();

        YtterbiumOxide = new Material.Builder(3658, "ytterbium_oxide")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ytterbium, 2, Oxygen, 3)
                .build();

        YttriumOxide = new Material.Builder(3659, "yttrium_oxide")
                .dust()
                .colorAverage().iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .components(Yttrium, 2, Oxygen, 3)
                .build();

        LutetiumOxide = new Material.Builder(3660, "lutetium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Lutetium, 2, Oxygen, 3)
                .build();

        ScandiumOxide = new Material.Builder(3661, "scandium_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Scandium, 2, Oxygen, 3)
                .build();

        CalciumCarbide = new Material.Builder(3662, "calcium_carbide")
                .dust()
                .color(0x807B70).iconSet(METALLIC)
                .components(Calcium, 1, Carbon, 2)
                .build();

        CalciumHydroxide = new Material.Builder(3663, "calcium_hydroxide")
                .dust()
                .color(0x5F8764).iconSet(DULL)
                .components(Calcium, 1, Oxygen, 2, Hydrogen, 2)
                .build()
                .setFormula("Ca(OH)2", true);

        SilicaGel = new Material.Builder(3664, "silica_gel")
                .dust()
                .color(0x61DAFF).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Silicon, 1, Oxygen, 2)
                .build();

        ZirconiumTetrachloride = new Material.Builder(3665, "zirconium_tetrachloride")
                .dust()
                .color(0xF0F0F0).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Fluorine, 4)
                .build();

        SiliconCarbide = new Material.Builder(3666, "silicon_carbide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Silicon, 1, Carbon, 1)
                .build();

        GoldDepleteMolybdenite = new Material.Builder(3667, "gold_depleted_molybdenite")
                .dust()
                .color(0x7C7C8F).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Molybdenum, 1, Sulfur, 2, RareEarth, 1)
                .build();

        MolybdenumConcentrate = new Material.Builder(3668, "molybdenum_concentrate")
                .dust()
                .color(0x565666).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Molybdenum, 1, Sulfur, 2, Rhenium, 1)
                .build();

        MolybdenumTrioxide = new Material.Builder(3669, "molybdenum_trioxide")
                .dust()
                .color(0x666685).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Molybdenum, 1, Oxygen, 3)
                .build();

        CopperChloride = new Material.Builder(3670, "copper_chloride")
                .dust()
                .color(0xF5B35D).iconSet(SHINY)
                .components(Copper, 1, Chlorine, 2)
                .build();

        BismuthChloride = new Material.Builder(3671, "bismuth_chloride")
                .dust()
                .color(0x95F5D7).iconSet(SHINY)
                .components(Bismuth, 1, Chlorine, 3)
                .build();

        LeadChloride = new Material.Builder(3672, "lead_chloride")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Lead, 1, Chlorine, 2)
                .build();

        ZirconiumTetrafluoride = new Material.Builder(3673, "zirconium_tetrafluoride")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Zirconium, 1, Fluorine, 4)
                .build();

        BariumDifluoride = new Material.Builder(3674, "barium_difluoride")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Barium, 1, Fluorine, 2)
                .build();

        LanthanumTrifluoride = new Material.Builder(3675, "lanthanum_trifluoride")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Lanthanum, 1, Fluorine, 3)
                .build();

        AluminiumTrifluoride = new Material.Builder(3676, "aluminium_trifluoride")
                .dust()
                .colorAverage()
                .components(Aluminium, 1, Fluorine, 3)
                .build();

        ErbiumTrifluoride = new Material.Builder(3677, "erbium_trifluoride")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Erbium, 1, Fluorine, 3)
                .build();

        PdIrReOCeOS = new Material.Builder(3678, "pdirreoceos")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Palladium, 1, Iridium, 1, Rhenium, 1, Cerium, 1, Osmium, 1, Silicon, 1, Oxygen, 4)
                .build();

        MgClBromide = new Material.Builder(3679, "mgcl_bromide")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Magnesium, 1, Chlorine, 1, Bromine, 1)
                .build();

        SodiumNitrate = new Material.Builder(3680, "sodium_nitrite")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .build();

        ZnFeAlClCatalyst = new Material.Builder(3681, "znfealcl_catalyst")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Zinc, 1, Iron, 1, Aluminium, 1, Chlorine, 1)
                .build();

        AluminiumChloride = new Material.Builder(3682, "aluminium_chloride")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Aluminium, 1, Chlorine, 3)
                .build();

        AuPdCCatalyst = new Material.Builder(3683, "aupdc_catalyst")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Gold, 1, Palladium, 1, Carbon, 1)
                .build();

        TinChloride = new Material.Builder(3684, "tin_chloride")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Tin, 1, Chlorine, 2)
                .build();

        VanadiumSlagDust = new Material.Builder(3685, "vanadium_slag_dust")
                .dust()
                .color(0xF2EF1B).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Vanadium, 1, Oxygen, 1)
                .build();

        SodiumVanadate = new Material.Builder(3686, "sodium_vanadate")
                .dust()
                .color(0xF2DF1D).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 3, Vanadium, 1, Oxygen, 4)
                .build();

        AmmoniumVanadate = new Material.Builder(3687, "ammonium_vanadate")
                .dust()
                .color(0xF2FF1C).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 4, Vanadium, 1, Oxygen, 3)
                .build();

        VanadiumOxide = new Material.Builder(3688, "vanadium_oxide")
                .dust()
                .color(0xF2EF1B).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Vanadium, 2, Oxygen, 5)
                .build();

        BariumSulfide = new Material.Builder(3689, "barium_sulfide")
                .dust()
                .color(0xC2C2BE).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Sulfur, 1)
                .build();

        BariumCarbonate = new Material.Builder(3690, "barium_carbonate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .build();

        BariumOxide = new Material.Builder(3691, "barium_oxide")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Oxygen, 1)
                .build();

        BariumAluminate = new Material.Builder(3692, "barium_aluminate")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Aluminium, 2, Oxygen, 4)
                .build();

        CaesiumHydroxide = new Material.Builder(3693, "caesium_hydroxide")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Caesium, 1, Oxygen, 1, Hydrogen, 1)
                .build();

        AluminiumHydroxide = new Material.Builder(3694, "aluminium_hydroxide")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("Al(OH)3", true);

        PotassiumCarbonate = new Material.Builder(3695, "potassium_carbonate")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Potassium, 2, Carbon, 1, Oxygen, 3)
                .build();

        NiAlOCatalyst = new Material.Builder(3696, "nialo_catalyst")
                .dust()
                .color(0x0AF0AF).iconSet(SHINY)
                .components(Nickel, 1, Aluminium, 2, Oxygen, 4)
                .build();

        FeCrOCatalyst = new Material.Builder(3697, "fecro_catalyst")
                .dust()
                .color(0x8C4517).iconSet(SHINY)
                .components(Iron, 1, Chrome, 1, Oxygen, 3)
                .build();

        LithiumChloride = new Material.Builder(3698, "lithium_chloride")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Lithium, 1, Chlorine, 1)
                .build();

        NickelChloride = new Material.Builder(3699, "nickel_chloride")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Nickel, 1, Chlorine, 2)
                .build();

        PotassiumSulfate = new Material.Builder(3700, "potassium_sulfate")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Potassium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        AluminiumSulfate = new Material.Builder(3701, "aluminium_sulfate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Aluminium, 2, Sulfur, 3, Oxygen, 12)
                .build()
                .setFormula("Al2(SO4)3", true);

        BariumHydroxide = new Material.Builder(3702, "barium_hydroxide")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Barium, 1, Oxygen, 2, Hydrogen, 2)
                .build()
                .setFormula("Ba(OH)2", true);

        HafniumOxide = new Material.Builder(3703, "hafnium_oxide")
                .dust()
                .color(0x404040).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Oxygen, 2)
                .build();

        SiliconChloride = new Material.Builder(3704, "silicon_chloride")
                .dust()
                .colorAverage()
                .components(Silicon, 1, Chlorine, 4)
                .build();

        HafniumChloride = new Material.Builder(3705, "hafnium_chloride")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Chlorine, 4)
                .build();

        ZincLeachingResidue = new Material.Builder(3706, "zinc_leaching_residue")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Germanium, 1, RareEarth, 1)
                .build();

        IndiumHydroxide = new Material.Builder(3707, "indium_hydroxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("In(OH)3", true);

        ThalliumResidue = new Material.Builder(3708, "thallium_residue")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thallium, 1, RareEarth, 1)
                .build();

        ThalliumChloride = new Material.Builder(3709, "thallium_chloride")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thallium, 1, Chlorine, 1)
                .build();

        ZincChloride = new Material.Builder(3710, "zinc_chloride")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Zinc, 1, Chlorine, 2)
                .build();

        SodiumSulfite = new Material.Builder(3711, "sodium_sulfite")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 3)
                .build();

        GermaniumOxide = new Material.Builder(3712, "germanium_oxide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Germanium, 1, Oxygen, 2)
                .build();

        DisodiumPhosphate = new Material.Builder(3713, "disodium_phosphate")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Sodium, 2, Hydrogen, 1, Phosphorus, 1, Oxygen, 4)
                .build();

        SodiumAzanide = new Material.Builder(3714, "sodium_azanide")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Nitrogen, 1, Hydrogen, 2)
                .build();

        SodiumAzide = new Material.Builder(3715, "sodium_azide")
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Sodium, 1, Nitrogen, 3)
                .build();

        AluminiumHydride = new Material.Builder(3716, "aluminium_hydride")
                .dust()
                .color(0x0B585C).iconSet(ROUGH)
                .components(Aluminium, 1, Hydrogen, 3)
                .build();

        SodiumHydride = new Material.Builder(3717, "sodium_hydride")
                .dust()
                .color(0xCACAC8).iconSet(ROUGH)
                .components(Sodium, 1, Hydrogen, 1)
                .build();

        MagnesiumSulfate = new Material.Builder(3718, "magnesium_sulfate")
                .dust()
                .colorAverage()
                .components(Magnesium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        SodiumMolybdate = new Material.Builder(3719, "sodium_molybdate")
                .dust()
                .color(0xFCFC00).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 2, Molybdenum, 1, Oxygen, 4)
                .build();

        StrontiumSulfate = new Material.Builder(3720, "strontium_sulfate")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        StrontiumOxide = new Material.Builder(3721, "strontium_oxide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Strontium, 1, Oxygen, 1)
                .build();

        PalladiumChloride = new Material.Builder(3722, "palladium_chloride")
                .dust()
                .color(0xB9C0C7).iconSet(SHINY)
                .components(Palladium, 1, Chlorine, 2)
                .build();

        PotassiumTetrachloroplatinate = new Material.Builder(3723, "potassium_tetrachloroplatinate")
                .dust()
                .color(0xFFBA54).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Potassium, 2, Platinum, 1, Chlorine, 4)
                .build();

        NiAlCatalyst = new Material.Builder(3724, "potassium_tetrachloroplatinate")
                .dust()
                .color(0x6EA2FF).iconSet(SHINY)
                .components(Nickel, 1, Aluminium, 1)
                .build();

        TitaniumNitrate = new Material.Builder(3725, "titanium_nitrate")
                .dust()
                .color(0xFF0066).iconSet(FINE)
                .components(Titanium, Nitrogen, 4, Oxygen, 12)
                .build()
                .setFormula("Ti(NO3)4", true);

        AnodicSlime = new Material.Builder(3726, "anodic_slime")
                .dust()
                .color(0x765A30)
                .flags(DISABLE_DECOMPOSITION)
                .components(Selenium, 1, Tellurium, 1, RareEarth, 2)
                .build()
                .setFormula("SeTe??");

        TelluriumOxide = new Material.Builder(3727, "tellurium_oxide")
                .dust()
                .color(0xFFFF66).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tellurium, 1, Oxygen, 2)
                .build();

        SeleniumOxide = new Material.Builder(3728, "selenium_oxide")
                .dust()
                .color(0xFFFF66).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Selenium, 1, Oxygen, 2)
                .build();

        ManganeseSulfate = new Material.Builder(3729, "manganese_sulfate")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Manganese, 1, Sulfur, 1, Oxygen, 4)
                .build();

        NbTaContainingDust = new Material.Builder(3730, "nbta_containing_dust")
                .dust()
                .color(0xC8B9A9).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Niobium, 1, Tantalum, 1)
                .build();

        IronSulfate = new Material.Builder(3731, "iron_sulfate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Iron, 1, Sulfur, 1, Oxygen, 4)
                .build();

        StrontiumCarbonate = new Material.Builder(3732, "strontium_carbonate")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Strontium, 1, Carbon, 1, Oxygen, 3)
                .build();

        SodiumHypochlorite = new Material.Builder(3733, "sodium_hypochlorite")
                .dust()
                .color(0x6CFF50).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Chlorine, 1, Oxygen, 1)
                .build();

        CopperGalliumIndiumMix = new Material.Builder(3734, "copper_gallium_indium_mix")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Copper, 1, Gallium, 1, Indium)
                .build();

        LanthanumCalciumManganate = new Material.Builder(3635, "lanthanum_gallium_manganate")
                .dust()
                .color(0x8AA07B).iconSet(SHINY)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Lanthanum, 1, Calcium, 1, Manganese, 1, Oxygen, 3)
                .build();

        IronPlatinumCatalyst = new Material.Builder(3736, "iron_platinum_catalyst")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Iron, 1, Platinum, 1)
                .build();

        YttriumNitrate = new Material.Builder(3737, "yttrium_nitrate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Yttrium, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Y(NO3)3", true);

        CopperNitrate = new Material.Builder(3738, "copper_nitrate")
                .dust()
                .color(0xCAECEC).iconSet(SHINY)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Copper, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Cu(NO3)2", true);

        BariumNitrate = new Material.Builder(3739, "barium_nitrate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Barium, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Ba(NO3)2", true);

        WellMixedYBCOxides = new Material.Builder(3740, "well_mixed_ybc_oxides")
                .dust()
                .color(0x2C3429).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 6)
                .build();

        PiledTBCC = new Material.Builder(3741, "piled_tbcc")
                .dust()
                .color(0x669900).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thallium, 2, Barium, 2, Copper, 3, Calcium, 2)
                .build();

        ActiniumHydride = new Material.Builder(3742, "actinium_hydride")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Actinium, 1, Hydrogen, 3)
                .build();

        IronIodide = new Material.Builder(3743, "iron_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Iron, 1, Iodine, 2)
                .build();

        PotassiumIodide = new Material.Builder(3744, "potassium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Potassium, 1, Iodine, 1)
                .build();

        ThalliumIodide = new Material.Builder(3745, "thallium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Thallium, 1, Iodine, 1)
                .build();

        ScandiumIodide = new Material.Builder(3746, "scandium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Scandium, 1, Iodine, 3)
                .build();

        RubidiumIodide = new Material.Builder(3747, "rubidium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Rubidium, 1, Iodine, 1)
                .build();

        IndiumIodide = new Material.Builder(3748, "indium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Indium, 1, Iodine, 3)
                .build();

        GalliumIodide = new Material.Builder(3749, "gallium_iodide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Gallium, 1, Iodine, 3)
                .build();

        CarbonylPurifiedIron = new Material.Builder(3750, "carbonyl_purified_iron")
                .dust()
                .color(0xC8C8C8).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 1)
                .build();

        SodiumThiosulfate = new Material.Builder(3751, "sodium_thiosulfate")
                .dust()
                .color(0x2090FC).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 2, Sulfur, 2, Oxygen, 3)
                .build();

        SodiumBromide = new Material.Builder(3752, "sodium_bromide")
                .dust()
                .color(0xFEAFFC).iconSet(ROUGH)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Sodium, 1, Bromine, 1)
                .build();

        FranciumCarbide = new Material.Builder(3753, "francium_carbide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Francium, 2, Carbon, 2)
                .build();

        BoronCarbide = new Material.Builder(3754, "boron_carbide")
                .dust()
                .color(0x303030).iconSet(FINE)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Boron, 4, Carbon, 1)
                .build();

        MixedAstatideSalts = new Material.Builder(3755, "mixed_astatide_salts")
                .dust()
                .color(0x6DF63F).iconSet(SHINY)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Astatine, 3, Holmium, 1, Thulium, 1, Copernicium, 1, Flerovium, 1)
                .build();

        SodiumIodide = new Material.Builder(3756, "sodium_iodide")
                .dust()
                .color(0x555588).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Iodine, 1)
                .build();

        SodiumIodate = new Material.Builder(3757, "sodium_iodate")
                .dust()
                .color(0x11116D).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Iodine, 1, Oxygen, 3)
                .build();

        SodiumPeriodate = new Material.Builder(3758, "sodium_periodate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Iodine, 1, Oxygen, 4)
                .build();

        SodiumSeaborgate = new Material.Builder(3759, "sodium_seaborgate")
                .dust()
                .color(0x55BBD4).iconSet(SHINY)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Sodium, 1, Seaborgium, 1, Oxygen, 3)
                .build();

        OsmiumTetroxide = new Material.Builder(3760, "osmium_tetroxide")
                .dust()
                .color(0x82CBD6).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Osmium, 1, Oxygen, 4)
                .build();

        StrontiumChloride = new Material.Builder(3761, "strontium_chloride")
                .dust()
                .color(0x3A9ABA).iconSet(SHINY)
                .components(Strontium, 1, Chlorine, 2)
                .build();

        YttriumEuropiumVanadate = new Material.Builder(3762, "yttrium_europium_vanadate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Yttrium, 1, Europium, 1, Vanadium, 1, Oxygen, 4)
                .build();

        StrontiumEuropiumAluminate = new Material.Builder(3763, "strontium_europium_aluminate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Strontium, 1, Europium, 1, Aluminium, 2, Oxygen, 4)
                .build();

        BariumStrontiumTitanate = new Material.Builder(3764, "barium_strontium_titanate")
                .dust()
                .color(0xFF0066).iconSet(SHINY)
                .components(Barium, 1, Oxygen, 4, Strontium, 1, Titanium, 1)
                .build();

        PotassiumManganate = new Material.Builder(3765, "potassium_manganate")
                .dust()
                .color(0xAF20AF)
                .components(Potassium, 2, Manganese, 1, Oxygen, 4)
                .build();

        BariumChloride = new Material.Builder(3766, "barium_chloride")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Chlorine, 2)
                .build();

        TantalumOxide = new Material.Builder(3767, "tantalum_oxide")
                .dust()
                .colorAverage()
                .components(Tantalum, 2, Oxygen, 5)
                .build();

        ZirconylChloride = new Material.Builder(3768, "zirconyl_chloride")
                .dust()
                .colorAverage()
                .components(Zirconium, 1, Oxygen, 1, Chlorine, 2)
                .build();

        LeadSelenide = new Material.Builder(3769, "lead_selenide")
                .dust()
                .colorAverage()
                .components(Lead, 1, Selenium, 1)
                .build();

        BoronOxide = new Material.Builder(3770, "boron_oxide")
                .dust()
                .colorAverage()
                .components(Boron, 2, Oxygen, 3)
                .build();

        LithiumAluminiumFluoride = new Material.Builder(3771, "lithium_aluminium_fluoride")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Aluminium, 1, Fluorine, 4, Lithium, 1)
                .build();

        HafniumCarbide = new Material.Builder(3772, "hafnium_carbide")
                .dust()
                .color(0x2C2C2C).iconSet(SHINY)
                .components(Hafnium, 1, Carbon, 1)
                .build();

        TantalumCarbide = new Material.Builder(3773, "tantalum_carbide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Tantalum, 1, Carbon, 1)
                .build();

        SeaborgiumCarbide = new Material.Builder(3774, "seaborgium_carbide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Seaborgium, 1, Carbon, 1)
                .build();

        AluminiumNitrate = new Material.Builder(3775, "aluminium_nitrate")
                .dust()
                .colorAverage()
                .components(Aluminium, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Al(NO3)3", true);

        CaesiumBromide = new Material.Builder(3776, "cesium_bromide")
                .dust()
                .colorAverage()
                .components(Caesium, 1, Bromine, 1)
                .build();

        FluoroapatiteSolidResidue = new Material.Builder(3777, "fluoroapatite_solid_residue")
                .dust()
                .color(0x3CB290).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 6, Phosphorus, 1, Oxygen, 4, Silicon, 1, Oxygen, 3, Fluorine, 1)
                .build();

        ApatiteSolidResidue = new Material.Builder(3778, "apatite_solid_residue")
                .dust()
                .color(0x3CB290).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 6, Phosphorus, 1, Oxygen, 4, Silicon, 1, Oxygen, 3)
                .build();

        AmmoniumBifluoride = new Material.Builder(3779, "ammonium_bifluoride")
                .dust()
                .color(0x20CFCF).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Hydrogen, 5, Fluorine, 2)
                .build()
                .setFormula("NH4HF2", true);

        SodiumArsenate = new Material.Builder(3780, "sodium_arsenate")
                .dust()
                .color(0xBFFABF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 3, Arsenic, 1, Oxygen, 4)
                .build();

        PureSodiumVanadate = new Material.Builder(3781, "pure_sodium_vanadate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 3, Vanadium, 1, Oxygen, 4)
                .build();

        AmmoniumCarbonate = new Material.Builder(3782, "ammonium_carbonate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 2, Hydrogen, 8, Carbon, 1, Oxygen, 3)
                .build()
                .setFormula("(NH4)2CO2", true);

        CadmiumSulfide = new Material.Builder(3783, "cadmium_sulfide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Cadmium, 1, Sulfur, 1)
                .build();

        BismuthVanadate = new Material.Builder(3784, "bismuth_vanadate")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Bismuth, 1, Vanadium, 1, Oxygen, 4)
                .build();

        CopperArsenite = new Material.Builder(3785, "copper_arsenite")
                .dust()
                .color(0x66FF66).iconSet(ROUGH)
                .components(Copper, 3, Arsenic, 2, Oxygen, 8)
                .build()
                .setFormula("Cu3(AsO4)2", true);

        MercuryIodide = new Material.Builder(3786, "mercury_iodide")
                .dust()
                .color(0xFF0000)
                .components(Mercury, 1, Iodine, 2)
                .build();

        CobaltZincOxide = new Material.Builder(3787, "cobalt_zinc_oxide")
                .dust()
                .color(0x00FFFF)
                .components(Cobalt, 1, Zinc, 4, Oxygen, 5)
                .build();

        ScheelesGreen = new Material.Builder(3788, "scheeles_green")
                .dust()
                .color(0x00FF00)
                .components(Arsenic, 1, Copper, 1, Hydrogen, 1, Oxygen, 3)
                .build();

        CobaltAluminate = new Material.Builder(3789, "cobalt_aluminate")
                .dust()
                .color(0x0000FF).iconSet(FINE)
                .components(Aluminium, 2, Cobalt, 2, Oxygen, 5)
                .build();

        HanPurple = new Material.Builder(3790, "han_purple")
                .dust()
                .color(0x660066)
                .components(Barium, 1, Copper, 1, Silicon, 2, Oxygen, 6)
                .build();

        ChromeYellow = new Material.Builder(3791, "chrome_yellow")
                .dust()
                .color(0xFFFF00)
                .components(Lead, 1, Chrome, 1, Oxygen, 4)
                .build();

        ChromeOrange = new Material.Builder(3792, "chrome_orange")
                .dust()
                .color(0xFF6600)
                .components(Lead, 2, Chrome, 1, Oxygen, 5)
                .build();

        ManganeseIIIOxide = new Material.Builder(3793, "manganese_iii_oxide")
                .dust()
                .colorAverage()
                .components(Manganese, 2, Oxygen, 3)
                .build();

        MercuryChloride = new Material.Builder(3794, "mercury_chloride")
                .dust()
                .color(0xD6B8AD)
                .components(Mercury, 1, Chlorine, 2)
                .build();

        LithiumHydride = new Material.Builder(3795, "lithium_hydride")
                .dust()
                .colorAverage()
                .components(Lithium, 1, Hydrogen, 1)
                .build();

        NiobiumChloride = new Material.Builder(3796, "niobium_chloride")
                .dust()
                .colorAverage()
                .components(Niobium, 1, Chlorine, 5)
                .build();

        NiobiumHydroxide = new Material.Builder(3797, "niobium_hydroxide")
                .dust()
                .color(0x7C7C7C)
                .components(Niobium, Oxygen, 5, Hydrogen, 5)
                .build()
                .setFormula("Nb(OH)5", true);

        MagnesiumFluoride = new Material.Builder(3798, "magnesium_fluoride")
                .dust()
                .color(0xCFCFCF)
                .components(Magnesium, 1, Fluorine, 2)
                .build();

        ZincSulfide = new Material.Builder(3799, "zinc_sulfide")
                .dust()
                .color(0x3C3C3C)
                .components(Zinc, 1, Sulfur, 1)
                .build();

        LithiumNiobateNanoparticles = new Material.Builder(3800, "lithium_niobate_nanoparticles")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .build();

        LithiumHydroxide = new Material.Builder(3801, "lithium_hydroxide")
                .dust()
                .colorAverage()
                .components(Lithium, 1, Oxygen, 1, Hydrogen, 1)
                .build();

        RhReNqCatalyst = new Material.Builder(3802, "rhrenq_catalyst")
                .dust()
                .colorAverage()
                .components(Rhenium, 1, Rhodium, 1, Naquadah, 1)
                .build();

        FranciumCaesiumCadmiumBromide = new Material.Builder(3803, "francium_caesium_cadmium_bromide")
                .dust()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Francium, 1, Caesium, 1, Cadmium, 2, Bromine, 6)
                .build();

        ZincSelenide = new Material.Builder(3804, "zinc_selenide")
                .dust()
                .color(0xFCFC00)
                .components(Zinc, 1, Selenium, 1)
                .build();

        PotassiumBromide = new Material.Builder(3805, "potassium_bromide")
                .dust()
                .color(0xE066A3)
                .components(Potassium, 1, Bromine, 1)
                .build();

        PotassiumBromate = new Material.Builder(3806, "potassium_bromate")
                .dust()
                .color(0x8A4CD1)
                .components(Potassium, 1, Bromine, 1, Oxygen, 3)
                .build();

        SodiumPertechnetate = new Material.Builder(3807, "sodium_pertechnetate")
                .dust()
                .color(0x6162C4)
                .components(Sodium, 1, Technetium, 1, Oxygen, 4)
                .build();

        PotassiumPertechnate = new Material.Builder(3808, "potassium_pertechnate")
                .dust()
                .color(0xDEC451)
                .components(Potassium, 1, Technetium, 1, Oxygen, 4)
                .build();

        PotassiumPerrhenate = new Material.Builder(3809, "potassium_perrhenate")
                .dust()
                .colorAverage()
                .components(Potassium, 1, Rhenium, 1, Oxygen, 4)
                .build();

        LithiumIodide = new Material.Builder(3810, "lithium_iodide")
                .dust()
                .colorAverage()
                .components(Lithium, 1, Iodine, 1)
                .build();

        ManganeseFluoride = new Material.Builder(3811, "manganese_fluoride")
                .dust()
                .colorAverage()
                .components(Manganese, 1, Fluorine, 2)
                .build();

        GermaniumSulfide = new Material.Builder(3812, "germanium_sulfide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Germanium, 1, Sulfur, 2)
                .build();

        BismuthGermanate = new Material.Builder(3813, "bismuth_germanate")
                .dust()
                .color(0x94CF5C)
                .components(Bismuth, 12, Germanium, 1, Oxygen, 20)
                .build();

        CaesiumIodide = new Material.Builder(3814, "caesium_iodide")
                .dust()
                .colorAverage()
                .components(Caesium, 1, Iodine, 1)
                .build();

        CadmiumTungstate = new Material.Builder(3815, "cadmium_tungstate")
                .dust()
                .colorAverage()
                .components(Cadmium, 1, Tungsten, 1, Oxygen, 4)
                .build();

        SodiumOxide = new Material.Builder(3816, "sodium_oxide")
                .dust()
                .color(0x0373FC)
                .components(Sodium, 2, Oxygen, 1)
                .build();

        SodiumPerchlorate = new Material.Builder(3817, "sodium_perchlorate")
                .dust()
                .colorAverage()
                .components(Sodium, 1, Chlorine, 1, Oxygen, 4)
                .build();

        Lanthanoids = new Material.Builder(3818, "lanthanoids")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Lanthanum, 1, Cerium, 1, Praseodymium, 1, Neodymium, 1, Promethium, 1, Samarium, 1, Europium, 1, Gadolinium, 1, Terbium, 1, Dysprosium, 1, Holmium, 1, Erbium, 1, Thulium, 1, Ytterbium, 1, Lutetium, 1)
                .build();

        Actinoids = new Material.Builder(3819, "actinoids")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Actinium, 1, Thorium, 1, Materials.Protactinium, 1, Uranium238, 1, Materials.Neptunium, 1, Plutonium239, 1, Americium, 1, Materials.Curium, 1, Materials.Berkelium, 1, Materials.Californium, 1, Materials.Einsteinium, 1, Materials.Fermium, 1, Materials.Mendelevium, 1)
                .build()
                .setFormula("AcThpaUNpPuAmCmBkCfEsFmMd");

        Alkalis = new Material.Builder(3820, "alkalis")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Lithium, 1, Beryllium, 1, Sodium, 1, Magnesium, 1, Potassium, 1, Calcium, 1, Scandium, 1, Rubidium, 1, Strontium, 1, Yttrium, 1, Caesium, 1, Barium, 1, Francium, 1, Radium, 1)
                .build();

        PreciousMetals = new Material.Builder(3821, "precious_metals")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Ruthenium, 1, Rhodium, 1, Palladium, 1, Silver, 1, Rhenium, 1, Osmium, 1, Iridium, 1, Platinum, 1, Gold, 1)
                .build();

        LightTranstionMetals = new Material.Builder(3822, "light_transition_metals")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Titanium, 1, Vanadium, 1, Chrome, 1, Manganese, 1, Iron, 1, Cobalt, 1, Nickel, 1, Copper, 1)
                .build();

        RefractoryMetals = new Material.Builder(3823, "refractory_metals")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Zirconium, 1, Niobium, 1, Molybdenum, 1, Technetium, 1, Hafnium, 1, Tantalum, 1, Tungsten, 1)
                .build();

        PostTransitionMetals = new Material.Builder(3824, "post_transition_metals")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Aluminium, 1, Silicon, 1, Zinc, 1, Gallium, 1, Germanium, 1, Cadmium, 1, Indium, 1, Tin, 1, Antimony, 1, Mercury, 1, Thallium, 1, Lead, 1, Bismuth, 1, Polonium, 1)
                .build();

        RheniumChloride = new Material.Builder(3825, "rhenium_chloride")
                .dust()
                .color(0x3C2A5C).iconSet(SHINY)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .components(Rhenium, 1, Chlorine, 5)
                .build();

        AntimonyTrichloride = new Material.Builder(3826, "antimony_trichloride")
                .dust()
                .colorAverage()
                .components(Antimony, 1, Chlorine, 3)
                .build();

        LithiumCobaltOxide = new Material.Builder(3827, "lithium_cobalt_oxide")
                .dust()
                .color(0xD2A4F3).iconSet(SHINY)
                .components(Lithium, 1, Cobalt, 1, Oxygen, 1)
                .build();

        LithiumTriflate = new Material.Builder(3828, "lithium_triflate")
                .dust()
                .color(0xE2DAE3)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Carbon, 1, Sulfur, 1, Oxygen, 3, Fluorine, 3)
                .build();

        SiliconNanoparticles = new Material.Builder(3829, "silicon_nanoparticles")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Silicon, 1, RareEarth, 1)
                .build();

        Halloysite = new Material.Builder(3830, "halloysite")
                .dust()
                .color(0x23423A)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Aluminium, 9, Silicon, 10, Oxygen, 50, Gallium, 1)
                .build();

        GalliumChloride = new Material.Builder(3831, "gallium_chloride")
                .dust()
                .color(0x92867A).iconSet(ROUGH)
                .components(Gallium, 1, Chlorine, 3)
                .build();

        LanthanumNickelOxide = new Material.Builder(3832, "lanthanum_nickel_oxide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Lanthanum, 2, Nickel, 1, Oxygen, 4)
                .build();

        BorocarbideDust = new Material.Builder(3833, "borocarbide_dust")
                .dust()
                .color(0x9A9A2A).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Boron, 4, Carbon, 7, Astatine, 6, Holmium, 2, Thulium, 2, Flerovium, 2, Copernicium, 2)
                .build();

        HassiumChloride = new Material.Builder(3834, "hassium_chloride")
                .dust()
                .color(0x5D2ABC).iconSet(SHINY)
                .components(MetastableHassium, 1, Chlorine, 4)
                .build();

        SodiumMetavanadate = new Material.Builder(3835, "sodium_metavanadate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Vanadium, 1, Oxygen, 3)
                .build();

        PotassiumPeroxymonosulfate = new Material.Builder(3836, "potassium_peroxymonosulfate")
                .dust()
                .colorAverage()
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 5)
                .build();

        SilverPerchlorate = new Material.Builder(3837, "silver_perchlorate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(SilverChloride, 1, Chlorine, 1, Oxygen, 4)
                .build();

        SodiumChlorate = new Material.Builder(3838, "sodium_chlorate")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Sodium, 1, Chlorine, 1, Oxygen, 3)
                .build();

        CopperLeach = new Material.Builder(3839, "copper_leach")
                .dust()
                .color(0x765A30).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Copper, 3, RareEarth, 1)
                .build();

        CaliforniumTrioxide = new Material.Builder(3840, "californiumtrioxide")
                .dust()
                .color(0x7CC922)
                .components(Materials.Californium, 2, Oxygen, 3)
                .build();

        CaliforniumTrichloride = new Material.Builder(3841, "californiumtrichloride")
                .dust()
                .color(0x3E9837)
                .components(Materials.Californium, 1, Chlorine, 3)
                .build();

        IridiumTrioxide = new Material.Builder(3842, "iridiumtrioxide")
                .dust()
                .color(0x9A9A2B)
                .components(Iridium, 2, Oxygen, 3)
                .build();

        PotassiumHydroxylaminedisulfonate = new Material.Builder(3843, "potassium_hydroxylaminedisulfonate")
                .dust()
                .colorAverage()
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 3)
                .build();

        NitroniumTetrafluoroborate = new Material.Builder(3844, "nitronium_tetrafluoroborate")
                .dust()
                .color(0x686C6E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Oxygen, 2, Boron, 1, Fluorine, 4)
                .build();

        NitrosoniumTetrafluoroborate = new Material.Builder(3845, "nitrosonium_tetrafluoroborate")
                .dust()
                .color(0x7E8D94)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1, Oxygen, 1, Boron, 1, Fluorine, 4)
                .build();

        PdCCatalyst = new Material.Builder(3846, "pdc_catalyst")
                .dust()
                .colorAverage()
                .components(Palladium, 1, Carbon, 1)
                .build();

        PotassiumBisulfite = new Material.Builder(3847, "potassium_bisulfite")
                .dust()
                .color(0xF0EAD6)
                .components(Potassium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 3)
                .build();

        PotassiumNitrite = new Material.Builder(3848, "potassium_nitrite")
                .dust()
                .color(0xF0EAD6)
                .components(Potassium, 1, Nitrogen, 1, Oxygen, 2)
                .build();

        HydroxylammoniumSulfate = new Material.Builder(3849, "hydroxylammonium_sulfate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 2, Hydrogen, 8, Oxygen, 6, Sulfur, 2)
                .build()
                .setFormula("(NH3OH)2SO4", true);

        Legendarium = new Material.Builder(3850, "legendarium")
                .dust()
                .flags(DISABLE_DECOMPOSITION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Naquadah, 1, NaquadahEnriched, 1, Naquadria, 1, Duranium, 1, Tritanium, 1, Vibranium, 1, Adamantium, 1, Trinium, 1, Taranium, 1)
                .build();

        Polyimide = new Material.Builder(3851, "polyimide")
                .ingot(1).fluid()
                .color(0xff7f50).iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 22, Hydrogen, 12, Nitrogen, 2, Oxygen, 6)
                .build();

        FluorinatedEthylenePropylene = new Material.Builder(3852, "fluorinated_ethylene_propylene")
                .ingot(1).fluid()
                .color(0xc8c8c8).iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Fluorine, 10)
                .build();

        MetastableOganesson = new Material.Builder(3853, "metastable_oganesson")
                .ingot(7)
                .color(0xE61C24).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Oganesson, 1)
                .blastTemp(10380)
                .build();

        MetastableFlerovium = new Material.Builder(3854, "metastable_flerovium")
                .ingot(7)
                .color(0x521973).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Flerovium, 1)
                .blastTemp(10990)
                .build();

        MetastableHassium = new Material.Builder(3855, "metastable_hassium")
                .ingot(6)
                .color(0x2D3A9D).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Hassium, 1)
                .blastTemp(11240)
                .build();

        Alumina = new Material.Builder(3856, "alumina")
                .dust()
                .color(0x0b585c).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Aluminium, 2, Oxygen, 3)
                .build();
    }
}
