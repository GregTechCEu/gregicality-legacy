package gregicadditions.materials;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.stack.MaterialStack;

import gregtech.api.unification.material.Material;

import static gregicadditions.materials.GAMaterialFlags.*;
import static gregtech.api.unification.material.Materials.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import static com.google.common.collect.ImmutableList.of;
import static gregicadditions.GAEnums.EnumIonsGroups.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GASecondDegreeMaterials {

    /*
     * IDs 11500-14499
     */

    public static void register() {
        PalladiumAmmonia = new Material.Builder(11500, "palladium_enriched_ammonia")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Ammonia, 1, RareEarth, 1)
                .build();

        RhodiumSulfateSolution = new Material.Builder(11501, "rhodium_sulfate_solution")
                .fluid()
                .color(0xFFBB66)
                .flags(DISABLE_DECOMPOSITION)
                .components(RhodiumSulfate, 1, Water, 1)
                .build();

        RutheniumTetroxideSolution = new Material.Builder(11502, "ruthenium_tetroxide_solution")
                .fluid()
                .color(0xC7C7C7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Salt, 2, Ruthenium, 1, Oxygen, 4)
                .build();

        RhodiumSaltSolution = new Material.Builder(11503, "rhodium_salt_solution")
                .fluid()
                .color(0x667788)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Salt, 2, Chlorine, 1)
                .build();

        RhodiumFilterCakeSolution = new Material.Builder(11504, "rhodium_filter_cake_solution")
                .fluid()
                .color(0x667788)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Water, 2, RareEarth, 1)
                .build();

        EglinSteelBase = new Material.Builder(11505, "eglin_steel_base")
                .dust(6)
                .color(0x8B4513).iconSet(SAND)
                .components(Iron, 4, Kanthal, 1, Invar, 5)
                .build();

        MicaPulp = new Material.Builder(11506, "mica_based")
                .dust(1)
                .color(0x917445).iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mica, 1, RareEarth, 1)
                .build();

        // Free ID 11507

        Zirkelite = new Material.Builder(11508, "zirkelite")
                .dust(4).ore()
                .color(0x6B5E6A)
                .components(Calcium, 2, Thorium, 2, Cerium, 1, Zirconium, 7, Rutile, 6, Niobium, 4, Oxygen, 10)
                .build();

        PalladiumRawPowder = new Material.Builder(11509, "reprecipitated_palladium")
                .dust()
                .colorAverage().iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Ammonia, 1)
                .build();

        RarestMetalResidue = new Material.Builder(11510, "rarest_metal_residue")
                .dust().ore()
                .color(0x644629).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 2, Oxygen, 2, SiliconDioxide, 2, Gold, 3)
                .build();

        IrMetalResidue = new Material.Builder(11511, "iridium_metal_residue")
                .dust().ore()
                .color(0x846649).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 2, Oxygen, 4, SiliconDioxide, 2, Gold, 3)
                .build();

        PGSDResidue = new Material.Builder(11512, "sludge_dust_residue")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SiliconDioxide, 2, Gold, 3)
                .build();

        RhodiumNitrate = new Material.Builder(11513, "rhodium_nitrate")
                .dust()
                .colorAverage().iconSet(QUARTZ)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Ammonia, 3)
                .build();

        OrganicFertilizer = new Material.Builder(11514, "organic_fertilizer")
                .dust()
                .color(0xDDDDDD).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcium, 5, Phosphate, 3, Hydrogen, 1, Oxygen, 1)
                .build();

        Caliche = new Material.Builder(11515, "caliche")
                .dust(7).ore()
                .color(0xEB9E3F)
                .flags(DISABLE_DECOMPOSITION)
                .components(SodiumNitrate, 1, Potassium, 1, Nitrogen, 1, Oxygen, 6, RockSalt, 1, Sodium, 1, Iodine, 1)
                .build()
                .setFormula("(NaNO3)KNO3(KCl)NaIO3", true);

        FluoroApatite = new Material.Builder(11516, "fluoroapatite")
                .dust().ore()
                .colorAverage()
                .components(Calcium, 5, Phosphate, 3, Fluorine, 1)
                .build();

        RhodiumSalt = new Material.Builder(11517, "rhodium_salt")
                .gem()
                .color(0x848484).iconSet(GEM_VERTICAL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhodium, 1, Salt, 2)
                .build();

        Inconel625 = new Material.Builder(11518, "inconel_a")
                .ingot(6)
                .color(0x80C880).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Nickel, 3, Chrome, 7, Molybdenum, 10, Invar, 10, Nichrome, 13)
                .blastTemp(2425)
                .build();

        MaragingSteel250 = new Material.Builder(11519, "maraging_steel_a")
                .ingot(6)
                .color(0x92918D).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Steel, 16, Molybdenum, 1, Titanium, 1, Nickel, 4, Cobalt, 2)
                .blastTemp(2413)
                .build();

        Tumbaga = new Material.Builder(11520, "tumbaga")
                .ingot(6)
                .color(0xFFB20F).iconSet(METALLIC)
                .flags(GA_EXT2_METAL)
                .components(Gold, 7, Bronze, 3)
                .blastTemp(1200)
                .build();

        Enderium = new Material.Builder(11521, "enderium")
                .ingot(3)
                .color(0x23524A).iconSet(METALLIC)
                .flags(GA_EXT2_METAL, DISABLE_DECOMPOSITION)
                .components(Lead, 3, Platinum, 1, EnderPearl, 1)
                .toolStats(8.0F, 3.0F, 1280, 52)
                .fluidPipeProperties(1500, 650, true)
                .blastTemp(4500)
                .build();

        AbyssalAlloy = new Material.Builder(11522, "abyssal_alloy")
                .ingot(6)
                .color(0x9E706A).iconSet(METALLIC)
                .flags(GA_EXT2_METAL, DISABLE_DECOMPOSITION)
                .components(StainlessSteel, 5, TungstenCarbide, 5, Nichrome, 5, Bronze, 5, IncoloyMA956, 5, Iodine, 1, Germanium, 1, Radon, 1)
                .blastTemp(9625)
                .build();

        Incoloy813 = new Material.Builder(11523, "incoloy813")
                .ingot()
                .color(0x37BF7E).iconSet(SHINY)
                .flags(GA_EXT2_METAL, DISABLE_DECOMPOSITION)
                .components(VanadiumSteel, 4, Osmiridium, 2, Technetium, 3, Germanium, 4, Iridium, 7, Duranium, 5, Californium252, 1)
                .blastTemp(10000)
                .build();

        TungstenTitaniumCarbide = new Material.Builder(11524, "tungsten_titanium_carbide")
                .ingot(7)
                .color(0x800D0D).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenCarbide, 7, Titanium, 3)
                .blastTemp(4422)
                .build();

        Inconel792 = new Material.Builder(11525, "inconel_b")
                .ingot(5)
                .color(0x6CF076).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Nickel, 2, Niobium, 1, Aluminium, 2, Nichrome, 1)
                .blastTemp(6200)
                .build();

        Lafium = new Material.Builder(11526, "lafium")
                .ingot(7)
                .color(0x0D0D60).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(HastelloyN, 8, Naquadah, 4, Samarium, 2, Tungsten, 4, Argon, 2, Aluminium, 6, Nickel, 8, Carbon, 2)
                .fluidPipeProperties(23000, 2000, true)
                .blastTemp(9865)
                .build();

        Zeron100 = new Material.Builder(11527, "zeron")
                .ingot(5)
                .color(0xB4B414).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Chrome, 13, Nickel, 3, Molybdenum, 2, Copper, 10, Tungsten, 2, Steel, 20)
                .fluidPipeProperties(15000, 1750, true)
                .blastTemp(6100)
                .build();

        WoodsGlass = new Material.Builder(11528, "woods_glass")
                .ingot()
                .color(0x730099).iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(SiliconDioxide, 1, Barium, 1, Oxygen, 1, Garnierite, 1, SodaAsh, 1)
                .build();

        NaquadricSolution = new SimpleFluidMaterial("naquadric_solution", 0x232225, "NqNO2");
        EnrichedNaquadricSolution = new SimpleFluidMaterial("enriched_naquadric_solution", 0x312735, "Nq+NO2");
        NaquadriaticSolution = new SimpleFluidMaterial("naquadriatic_solution", 0x312735, "*Nq*NO2");
        CesiumXenontrioxideFluoride = new SimpleFluidMaterial("cesium_xenontrioxide_fluoride", 0x3333cc, "CsXeO3F");
        NitrylFluoride = new SimpleFluidMaterial("nitryl_fluoride", NitricOxide.materialRGB, "NO2F");
        NitrosoniumOctafluoroxenate = new SimpleFluidMaterial("nitrosonium_octafluoroxenate", 0x3f3f83, "(NO2)2XeF8");
        NaquadriaCesiumfluoride = new SimpleFluidMaterial("naquadria_cesiumfluoride", 0x636379, "*Nq*F2CsF");
        NaquadahSolution = new SimpleFluidMaterial("naquadah_solution", 0x523b3a, "NqNH4NO3");
        AmmoniumNitrate = new SimpleFluidMaterial("ammonium_nitrate", Ammonia.materialRGB, "NH4NO3");
        SodiumNitrateSolution = new SimpleFluidMaterial("sodium_nitrate_solution", 0xA09ED7, WATER.formulaGroup()+"NaNO3");
        IodizedBrine = new SimpleFluidMaterial("iodized_brine", 0x525242, "I?");
        IodineSlurry = new SimpleFluidMaterial("iodine_slurry", 0x08081c, "I?");
        Hydroxylamine = new SimpleFluidMaterial("hydroxylamine", 0x99cc99, "H3NO");
        HydroxylamineDisulfate = new SimpleFluidMaterial("hydroxylamine_disulfate", 0x99add6, "(NH3OH)2SO4");
        SodiumHydroxideBauxite = new SimpleFluidMaterial("sodium_hydroxide_bauxite", 0xbf731a, "Al2H2O4");
        AcidicSaltWater = new SimpleFluidMaterial("acidic_salt_water", 0x006960, "Na(H2O)(H2SO4)Cl");
        SulfuricBromineSolution = new SimpleFluidMaterial("sulfuric_bromine_solution", 0xff5100, "Br(H2SO4)?");
        HotVapourMixture = new SimpleFluidMaterial("hot_vapour_mixture", 0xff5100, "Br(H2SO4)(H2O)?");
        DampBromine = new SimpleFluidMaterial("damp_bromine", 0xe17594, "Br(H2O)");
        RareEarthHydroxidesSolution = new SimpleFluidMaterial("rare_earth_hydroxides_solution", 0xcfb37d, "NaOH(H2O)?");
        RareEarthChloridesSolution = new SimpleFluidMaterial("rare_earth_chlorides_solution", 0x164b45, "NaOH(H2O)(HCl)?");
        LaNdOxidesSolution = new SimpleFluidMaterial("la_nd_oxides_solution", 0x9ce3db, "(La2O3)(Pr2O3)(Nd2O3)(Ce2O3)");
        SmGdOxidesSolution = new SimpleFluidMaterial("sm_gd_oxides_solution", 0xffff99, "(Sc2O3)(Eu2O3)(Gd2O3)(Sm2O3)");
        TbHoOxidesSolution = new SimpleFluidMaterial("tb_ho_oxides_solution", 0x99ff99, "(Y2O3)(Tb2O3)(Dy2O3)(Ho2O3)");
        ErLuOxidesSolution = new SimpleFluidMaterial("er_lu_oxides_solution", 0xffb3ff, "(Er2O3)(Tm2O3)(Yb2O3)(Lu2O3)");
        CleanAmmoniaSolution = new SimpleFluidMaterial("clear_ammonia_solution", 0x53c9a0, "NH3(H2O)");
        SilicaGelBase = new SimpleFluidMaterial("silica_gel_base", 0x27a176, "SiO2(HCl)(NaOH)(H2O)");
        PiranhaSolution = new SimpleFluidMaterial("piranha_solution", 0x4820ab, "(H2SO4)H2O2");
        WaterAgarMix = new SimpleFluidMaterial("water_agar_mix", 0x48dbbe, "H2O?");
        RedOil = new SimpleFluidMaterial("red_oil", 0x7C1500, "H2N4(RP-1)NiZnFe4");
        ChlorideLeachedSolution = new SimpleFluidMaterial("chloride_leached_solution", 0x41472e, "CaCl2(CuCl2)(PbCl2)(BiCl3)(FeCl2)");
        MolybdenumFlue = new SimpleFluidMaterial("molybdenum_flue_gas", 0x333338, "H2OReS?");
        VanadiumWasteSolution = new SimpleFluidMaterial("vanadium_waste_solution", 0xbf95f5, "NaCl(Na2SO4)(SiO2)(Al(OH)3)");
        UranylChlorideSolution = new SimpleFluidMaterial("uranyl_chloride_solution", 0xdfe018, "UO2Cl2(H2O)?");
        UranylNitrateSolution = new SimpleFluidMaterial("uranyl_nitrate_solution", 0xdfe018, "UO2(NO3)2(H2O)?]");
        UraniumSulfateWasteSolution = new SimpleFluidMaterial("uranium_sulfate_waste_solution", 0xdfe018, "PbRaSr(H2SO4)");
        PurifiedUranylNitrate = new SimpleFluidMaterial("purified_uranyl_nitrate_solution", 0xeff028, "UO2(NO3)2(H2O)");
        UraniumDiuranate = new SimpleFluidMaterial("uranium_diuranate", 0xeff028, "(NH4)2U2O7");
        UraniumRefinementWasteSolution = new SimpleFluidMaterial("uranium_refinement_waste_solution", 0xeff028, "H2SO4C?");
        ThoriumNitrateSolution = new SimpleFluidMaterial("thorium_nitrate_solution", 0x33bd45, "Th(NO3)4(H2O)");
        SodiumHexafluoroaluminate = new SimpleFluidMaterial("sodium_hexafluoroaluminate", (Sodium.materialRGB+Aluminium.materialRGB+Fluorine.materialRGB)/3, "Na3AlF6");
        SodiumCarbonateSolution = new SimpleFluidMaterial("sodium_carbonate_solution", (SodaAsh.materialRGB+30), "Na2CO3(H2O)");
        SodiumSulfateSolution = new SimpleFluidMaterial("sodium_sulfate_solution", (SodiumSulfate.materialRGB+30), "Na2SO4(H2O)");
        SodiumChromateSolution = new SimpleFluidMaterial("sodium_chromate_solution", 0xf2e70f, "Na2CrO4(H2O)");
        SodiumDichromateSolution = new SimpleFluidMaterial("sodium_dichromate_solution", 0xf2750f, "Na2Cr2O7");
        DissolvedLithiumOre = new SimpleFluidMaterial("dissolved_lithium_ores", 0x664850, "LiAlO2(H2SO4)");
        LithiumCarbonateSolution = new SimpleFluidMaterial("lithium_carbonate_solution", (Lithium.materialRGB+Carbon.materialRGB+Oxygen.materialRGB)/3, "Li2CO3(H2O)");
        LithiumChlorideSolution = new SimpleFluidMaterial("lithium_chloride_solution", (Lithium.materialRGB+Chlorine.materialRGB), "LiCl(H2O)");
        CalicheIodateBrine = new SimpleFluidMaterial("caliche_iodate_brine", 0xffe6660, "(H2O)NaNO3KNO3KClNaIO3");
        IodideSolution = new SimpleFluidMaterial("iodide_solution", 0x08081c, "(H2O)NaNO3KNO3KClNaI");
        CalicheNitrateSolution = new SimpleFluidMaterial("caliche_nitrate_solution", 0xffe6660, "(H2O)NaNO3KNO3KClNaOH");
        CalicheIodineBrine = new SimpleFluidMaterial("caliche_iodine_brine", 0xffe6660, "(H2O)NaNO3KNO3KClNaOHI");
        ZirconChlorinatingResidue = new SimpleFluidMaterial("zircon_chlorinating_residue", 0x51d351, "(SiCl4)Co?");
        ZincExhaustMixture = new SimpleFluidMaterial("zinc_exhaust_mixture", (CarbonDioxide.materialRGB+SulfurDioxide.materialRGB)/2, "(SO2)(CO2)?");
        ZincSlagSlurry = new SimpleFluidMaterial("zinc_slag_slurry", (Zinc.materialRGB-20), "H2O?");
        AcidicMetalSlurry = new SimpleFluidMaterial("acidic_metal_slurry", (Zinc.materialRGB-10+PhosphoricAcid.materialRGB)/2, "H3PO4?");
        SeparatedMetalSlurry = new SimpleFluidMaterial("separated_metal_slurry", (Zinc.materialRGB-20), "H3PO4?");
        CadmiumThalliumLiquor = new SimpleFluidMaterial("cdtl_liquor", (Cadmium.materialRGB+Thallium.materialRGB+RareEarth.materialRGB)/3, "(H2SO4)CdTl");
        CadmiumSulfateSolution = new SimpleFluidMaterial("cadmium_sulfate", (Cadmium.materialRGB+SulfuricAcid.materialRGB)/2, "CdSO4?");
        ThalliumSulfateSolution = new SimpleFluidMaterial("thallium_sulfate", (Thallium.materialRGB+SulfuricAcid.materialRGB)/2, "Tl2SO4?");
        GermanicAcidSolution = new SimpleFluidMaterial("germanic_acid_solution", (Germanium.materialRGB-10), "H4GeO4");
        SodiumHydroxideSolution = new SimpleFluidMaterial("sodium_hydroxide_solution", SodiumHydroxide.materialRGB+50, "(H2O)NaOH");
        LithiumHydroxideSolution = new SimpleFluidMaterial("lithium_hydroxide_solution", (Lithium.materialRGB+Oxygen.materialRGB+Hydrogen.materialRGB)/3, "(H2O)LiOH");
        LithiumPeroxideSolution = new SimpleFluidMaterial("lithium_peroxide", (Lithium.materialRGB+Oxygen.materialRGB)/2, "(H2O)Li2O2");
        PureUranylNitrateSolution = new SimpleFluidMaterial("pure_uranyl_nitrate", 0x33bd45, "(H2O)UO2(NO3)2");
        SodiumLithiumSolution = new SimpleFluidMaterial("sodium_lithium_solution", 0xfcfccd, "NaLi?");
        MagnesiumContainingBrine = new SimpleFluidMaterial("magnesium_containing_brine", 0xfcfcbc, "Mg?");
        BrominatedBrine = new SimpleFluidMaterial("brominated_brine", 0xfdd48d, "Br?");
        AcidicBrominatedBrine = new SimpleFluidMaterial("acidic_brominated_brine", 0xfdd48d, "(H2SO4)Cl?");
        SelenateTellurateMix = new SimpleFluidMaterial("selenate_tellurate_mixture", 0x765A30, "TeSe(H2Na2CO3)2");
        CopperRefiningSolution = new SimpleFluidMaterial("copper_refining_solution", 0x765A30, "CuH2SO4");
        ImpureAluminiumHydroxideSolution = new SimpleFluidMaterial("impure_aloh3_soution", 0xd8653e, "(H2O)Al(OH)3?");
        PureAluminiumHydroxideSolution = new SimpleFluidMaterial("pure_aloh3_soution", (Aluminium.materialRGB+Oxygen.materialRGB+ Hydrogen.materialRGB+40)/2, "(H2O)Al(OH)3");
        RedMud = new SimpleFluidMaterial("red_mud", 0xcc3300, "HCl?");
        NeutralisedRedMud = new SimpleFluidMaterial("neutralised_red_mud", 0xcc3300, "Fe??");
        FerricREEChloride = new SimpleFluidMaterial("ferric_ree_chloride", 0x30301a, "Fe?");
        RedSlurry = new SimpleFluidMaterial("red_slurry", 0xcc3300, "TiO2?");
        DiluteNitricAcid = new SimpleFluidMaterial("dilute_nitric_acid", (NitricAcid.materialRGB + Water.materialRGB) / 2, "(H2O)HNO3");
        NbTaFluorideMix = new SimpleFluidMaterial("nbta_fluoride_mix", 0xbcac93, "(H2NbOF5)(H2TaF7)");
        REEThUSulfateSolution = new SimpleFluidMaterial("reethu_sulfate_solution", 0x89be5c, "?SO4");
        RareEarthNitrateSolution = new SimpleFluidMaterial("rare_earth_nitrate_solution", 0xcfb37d, "?NO3");
        AlkalineEarthSulfateSolution = new SimpleFluidMaterial("alkalineearth_sulfate", 0xe6ebff, "?SO4");
        CalciumCarbonateSolution = new SimpleFluidMaterial("calcium_carbonate_solution", Calcite.materialRGB, "(H2O)CaCO3");
        BariumSulfateSolution = new SimpleFluidMaterial("barium_sulfate_solution", Barite.materialRGB, "(H2O)BaSO4");
        BentoniteClaySlurry = new SimpleFluidMaterial("bentonite_clay_solution", 0xdbc9c5, "H2O?");
        AstatideSolution = new SimpleFluidMaterial("astatide_solution", 0x6df63f, "At(H2O)(SO3)");
        BariumChlorideSolution = new SimpleFluidMaterial("barium_chloride_solution", (Barium.materialRGB+Chlorine.materialRGB)/2, "(H2O)BaCl3");
        IronCarbonyl = new SimpleFluidMaterial("iron_carbonyl", 0xff8000, "Fe?");
        PurifiedIronCarbonyl = new SimpleFluidMaterial("purified_iron_carbonyl", 0xff8000, "Fe");
        BismuthNitrateSoluton = new SimpleFluidMaterial("bismuth_nitrate_solution", (Bismuth.materialRGB+Chlorine.materialRGB)/2, "(H2O)Bi(NO3)3");
        PrYHoNitrateSolution = new SimpleFluidMaterial("pryho_nitrate_solution",0x00f2b2, "(Y(NO3)3)6(Pr(NO3)3)2(Nd(NO3)3)2(H2O)15");
        PhosphorousArsenicSolution = new SimpleFluidMaterial("phosphorous_arsenic_solution", PhosphoricAcid.materialRGB, "AsCd(HPO4)10");
        AmmoniumBifluorideSolution = new SimpleFluidMaterial("ammonium_bifluoride_solution", (Ammonia.materialRGB+Fluorine.materialRGB)/2, "(H2O)NH4FHF");
        LuTmYChlorideSolution = new SimpleFluidMaterial("lutmy_chloride_solution",0x00f2b2, "(YCl3)6(LuCl3)2(TmCl3)2(H2O)15");
        BismuthVanadateSolution = new SimpleFluidMaterial("bismuth_vanadate_solution",0xffff00, "(H2O)BiVO4");
        SeaborgiumDopedNanotubes = new SimpleFluidMaterial("seaborgium_doped_nanotubes",0x2c2c8c, "SgCNT");
        FullereneDopedNanotubes = new SimpleFluidMaterial("fullerene_doped_nanotubes",0x6c2c6c, "C60CNT");
        LiquidZBLAN = new SimpleFluidMaterial("molten_zblan",(Zirconium.materialRGB+Barium.materialRGB+Lanthanum.materialRGB+Aluminium.materialRGB+Fluorine.materialRGB)/5, "(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7");
        GrapheneOxidationSolution = new SimpleFluidMaterial("graphene_oxidation_solution",0x96821a, "(KMnO4)(NaNO3)(H2SO4)");
        SupercriticalCO2 = new SimpleFluidMaterial("supercritcal_co2",CarbonDioxide.materialRGB, "CO2");
        LiquidEnrichedHelium = new SimpleFluidMaterial("liquid_enriched_helium", Helium.materialRGB, 4, false, "HeHe_3");
        LiquidNitrogen = new SimpleFluidMaterial("liquid_nitrogen",Nitrogen.materialRGB, 70, false, "N");
        RheniumScrubbedSolution = new SimpleFluidMaterial("rhenium_scrubbed_solution",0xed2c3a, "Re?");
        NeutroniumDopedNanotubes = new SimpleFluidMaterial("neutronium_doped_nanotubes",(Neutronium.materialRGB+CarbonNanotubes.materialRGB)/2, "Nt?");
        SupercriticalSteam = new SimpleFluidMaterial("supercritical_steam", Steam.materialRGB, "H2O");
        SupercriticalSodiumPotassiumAlloy = new SimpleFluidMaterial("supercritical_sodium_potassium_alloy", SodiumPotassiumAlloy.materialRGB, "Na7K3");
        SupercriticalFLiNaK = new SimpleFluidMaterial("supercritical_flinak", FLiNaK.materialRGB, "FLiNaK");
        SupercriticalFLiBe = new SimpleFluidMaterial("supercritical_flibe", FLiBe.materialRGB, "FLiBe");
        SupercriticalLeadBismuthEutectic = new SimpleFluidMaterial("supercritical_lead_bismuth_eutectic", LeadBismuthEutectic.materialRGB, "Pb3Bi7");
        ApatiteAcidicLeach = new SimpleFluidMaterial("apatite_acidic_leach", PhosphoricAcid.materialRGB, "H10P3O12Cl??");
        FluoroapatiteAcidicLeach = new SimpleFluidMaterial("fluoroapatite_acidic_leach", PhosphoricAcid.materialRGB,"H10P3O12F??");
        SilicaAluminaGel = new SimpleDustMaterial("silica_alumina_gel", 0x558d9e, (short) 29, MaterialIconSet.ROUGH, "Al2O3SiO2");
        ZeoliteSievingPellets = new SimpleDustMaterial("zeolite_sieving_pellets", 0xa17bd1, (short) 30, MaterialIconSet.ROUGH, "Al2O3SiO2");
        WetZeoliteSievingPellets = new SimpleDustMaterial("wet_zeolite_sieving_pellets", 0x392f45, (short) 31, MaterialIconSet.METALLIC, "Al2O3SiO2?");
        ZBLANDust = new SimpleDustMaterial("zblan_dust", (ZirconiumTetrafluoride.rgb+BariumDifluoride.rgb+LanthanumTrifluoride.rgb+AluminiumTrifluoride.rgb)/4, (short) 61, MaterialIconSet.SHINY, "(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7");
        VanadiumSlag = new SimpleDustMaterial("vanadium_slag", (Vanadium.materialRGB+Titanium.materialRGB)/2, (short) 88, MaterialIconSet.DULL, "(VO)C(TiO2)");
        PotassiumUranylTricarbonate = new SimpleDustMaterial("potassium_uranyl_carbonate", 0xeff028, (short) 98, MaterialIconSet.METALLIC, "(UO2)CO3");
        UraniumPeroxideThoriumOxide = new SimpleDustMaterial("uranium_peroxide_thorium_oxide", 0x202020, (short) 99, MaterialIconSet.SHINY, "(UO3)(H2O2)ThO2");
        UraniumThoriumOxide = new SimpleDustMaterial("uranium_thorium_oxide", 0x202020, (short) 100, MaterialIconSet.SHINY, "UO2ThO2");
        UranylThoriumSulfate = new SimpleDustMaterial("uranium_thorium_sulfate", 0xe7e848, (short) 101, MaterialIconSet.SHINY, "(UO2)SO4ThO2");
        UranylThoriumNitrate = new SimpleDustMaterial("uranium_thorium_nitrate", 0xe7e848, (short) 102, MaterialIconSet.SHINY, "UO2(NO3)2Th(NO3)4");
        UraniumOxideThoriumNitrate = new SimpleDustMaterial("uranium_oxide_thorium_nitrate", 0x33bd45, (short) 103, MaterialIconSet.SHINY, "UO2Th(NO3)4");
        GrapheneOxidationResidue = new SimpleDustMaterial("graphene_oxidation_residue", 0x96821a, (short) 107, MaterialIconSet.FINE, "(KMnO4)(NaNO3)(H2SO4)");
        ZincCokePellets = new SimpleDustMaterial("zinc_coke_pellets", (Zinc.materialRGB+Coke.materialRGB)/2, (short) 121, MaterialIconSet.ROUGH, "(H2O)(ZnS)C");
        CadmiumZincDust = new SimpleDustMaterial("cadmium_zinc_dust", (Cadmium.materialRGB+Zinc.materialRGB)/2, (short) 127, MaterialIconSet.SHINY, "(H2SO4)CdZn?");
        UranylNitrate = new SimpleDustMaterial("uranyl_nitrate", 0x33bd45, (short) 136, MaterialIconSet.SHINY, "U" + OXIDE2.formula() + NITRATE.formulaGroup(2));
        SodiumSalts = new SimpleDustMaterial("sodium_salts", Sodium.materialRGB-5, (short) 138, MaterialIconSet.ROUGH, "NaCl?");
        PotassiumMagnesiumSalts = new SimpleDustMaterial("kmg_salts", 0xcacac8, (short) 139, MaterialIconSet.ROUGH, "KClMg" + SULFATE.formula() + "K2" + SULFATE.formula() + "KF");
        CalciumMagnesiumSalts = new SimpleDustMaterial("camg_salts", 0xcacac8, (short) 140, MaterialIconSet.ROUGH, "Ca" + CARBONATE.formula() + "(Sr" + CARBONATE.formula() + ")(CO2)MgO");
        SodiumAluminiumHydride = new SimpleDustMaterial("sodium_aluminium_hydride", 0x98cafc, (short) 141, MaterialIconSet.ROUGH, "NaAlH4");
        LithiumAluminiumHydride = new SimpleDustMaterial("lithium_aluminium_hydride", 0xc0defc, (short) 142, MaterialIconSet.ROUGH, "LiAlH4");
        SodiumPhosphomolybdate = new SimpleDustMaterial("sodium_phosphomolybdate", 0xfcfc00, (short) 152, MaterialIconSet.SHINY, "(MoO3)12Na3PO4");
        SodiumPhosphotungstate = new SimpleDustMaterial("sodium_phosphotungstate", 0x7a7777, (short) 153, MaterialIconSet.SHINY, "(WO3)12Na3PO4");
        LeadNitrateCalciumMixture = new SimpleDustMaterial("lead_nitrate_calcium_mixture", (LeadNitrate.materialRGB+Calcium.materialRGB)/2, (short) 167, MaterialIconSet.SHINY, "(Pb(NO3)2)Ca9");
        TinSlag = new SimpleDustMaterial("tin_slag", 0xc8b9a9, (short) 177, MaterialIconSet.DULL, "NbTa?");
        NiobiumTantalumOxide = new SimpleDustMaterial("niobium_tantalum_oxide", (Niobium.materialRGB+Tantalum.materialRGB)/2, (short) 179, MaterialIconSet.SHINY, "(Nb2O5)(Ta2O5)");
        FusedColumbite = new SimpleDustMaterial("fused_columbite", 0xCCCC00, (short) 180, MaterialIconSet.ROUGH, "(Fe2O3)(NaO)Nb2O5");
        LeachedColumbite = new SimpleDustMaterial("leached_columbite", 0xCCCC00, (short) 181, MaterialIconSet.SHINY, "(Nb2O5)9Ta2O5?");
        FusedTantalite = new SimpleDustMaterial("fused_tantalite", 0x915028, (short) 182, MaterialIconSet.ROUGH, "(Fe2O3)(NaO)Ta2O5");
        LeachedTantalite = new SimpleDustMaterial("leached_tantalite", 0x915028, (short) 183, MaterialIconSet.SHINY, "(Ta2O5)9Nb2O5?");
        ColumbiteMinorOxideResidue = new SimpleDustMaterial("columbite_minor_oxide_residue", 0x915028, (short) 184, MaterialIconSet.ROUGH, "(BaO)(SnO2)(WO3)(Al2O3)");
        TantaliteMinorOxideResidue = new SimpleDustMaterial("tantalite_minor_oxide_residue", 0xCCCC00, (short) 185, MaterialIconSet.ROUGH, "(BaO)(ZrO2)(TiO2)(SiO2)");
        LeachedPyrochlore = new SimpleDustMaterial("leached_pyrochlore", 0x996633, (short) 186, MaterialIconSet.SHINY, "(Nb2O5)9Ta2O5?");
        AcidicLeachedPyrochlore = new SimpleDustMaterial("acidic_leached_pyrochlore", 0x996633, (short) 187, MaterialIconSet.SHINY, "(H2SO4)Ca12Sr6Ba6?ThUNb26O78F26");
        PotasssiumFluoroNiobate = new SimpleDustMaterial("potassium_fluoroniobate", 0x73ff00, (short) 188, MaterialIconSet.SHINY, "K2NbF7");
        PotasssiumFluoroTantalate = new SimpleDustMaterial("potassium_fluorotantalate", 0x73ff00, (short) 189, MaterialIconSet.SHINY, "K2TaF7");
        BariumPeroxide = new SimpleDustMaterial("barium_peroxide", (Barium.materialRGB+Oxygen.materialRGB-30)/2, (short) 190, MaterialIconSet.SHINY, "BaO2");
        CopperGalliumIndiumSelenide = new SimpleDustMaterial("copper_gallium_indium_selenide", (CopperGalliumIndiumMix.rgb + Selenium.materialRGB) / 2, (short) 199, MaterialIconSet.SHINY, "CuGaInSe2");
        UVAHalideMix = new SimpleDustMaterial("uva_halide_mix", (GalliumIodide.rgb+PotassiumIodide.rgb+Mercury.materialRGB)/3, (short) 221, MaterialIconSet.SHINY, "Hg(GaI3)KI");
        WhiteHalideMix = new SimpleDustMaterial("white_halide_mix", (ScandiumIodide.rgb+PotassiumIodide.rgb+Mercury.materialRGB)/3, (short) 222, MaterialIconSet.SHINY, "Hg(ScI3)KI");
        BlueHalideMix = new SimpleDustMaterial("blue_halide_mix", (IndiumIodide.rgb+PotassiumIodide.rgb+Mercury.materialRGB)/3, (short) 223, MaterialIconSet.SHINY, "Hg(InI3)KI");
        GreenHalideMix = new SimpleDustMaterial("green_halide_mix", (ThalliumIodide.rgb+PotassiumIodide.rgb+Mercury.materialRGB)/3, (short) 224, MaterialIconSet.SHINY, "Hg(TlI)KI");
        RedHalideMix = new SimpleDustMaterial("red_halide_mix", (RubidiumIodide.rgb+PotassiumIodide.rgb+Mercury.materialRGB)/3, (short) 225, MaterialIconSet.SHINY, "Hg(RbI)KI");
        LeadScandiumTantalate = new SimpleDustMaterial("lead_scandium_tantalate", (Lead.materialRGB+Scandium.materialRGB+Tantalum.materialRGB)/3, (short) 251, MaterialIconSet.SHINY, "Pb(ScTa)O3");
        MagnetorestrictiveAlloy = new SimpleDustMaterial("magnetorestrictive_alloy", 0xafefef, (short) 252, MaterialIconSet.ROUGH, "Tb4Dy7Fe10Co5B2SiC");
        RawSienna = new SimpleDustMaterial("raw_siena",0x663300,(short) 284,MaterialIconSet.ROUGH, "SiO2(MnO2)(FeO2)");
        BurnedSienna = new SimpleDustMaterial("burned_siena",0xff0000,(short) 285,MaterialIconSet.DULL, "SiO2(MnO2)(FeO2)");
        TitaniumYellow = new SimpleDustMaterial("titanium_yellow",0xffff00,(short) 289,MaterialIconSet.SHINY, "NiO(Sb2O3)(TiO2)20");
        AmmoniumManganesePhosphate = new SimpleDustMaterial("ammonium_manganese_phosphate",0x660066,(short) 295,MaterialIconSet.SHINY, "NH4MnPO4");
        PotassiumNonahydridotechnetate = new SimpleDustMaterial("potassium_nonahydridotechnetate",0xede2a4,(short) 342,MaterialIconSet.SHINY, "H9K2TcO4");
        PotassiumNonahydridorhenate = new SimpleDustMaterial("potassium_nonahydridorhenate",0xeae2a8,(short) 343,MaterialIconSet.SHINY, "H9K2ReO4");
        PalladiumLoadedRutileNanoparticles = new SimpleDustMaterial("palladium_loaded_rutile_nanoparticles",(Palladium.materialRGB+Rutile.materialRGB),(short) 345,MaterialIconSet.FINE, "PdTiO2");
        PurifiedColumbite = new SimpleDustMaterial("purified_columbite", LeachedColumbite.rgb, (short) 371, MaterialIconSet.SHINY, "Ta2O5Nb18O45");
        PurifiedPyrochlore = new SimpleDustMaterial("purified_pyrochlore", LeachedPyrochlore.rgb, (short) 372, MaterialIconSet.SHINY, "Ta2O5Nb18O45");
        ChargedCesiumCeriumCobaltIndium = new SimpleDustMaterial("charged_cesium_cerium_cobalt_indium", 0x52ad25, (short) 380, MaterialIconSet.SHINY, "CsCeCo2In10");
        SulfurCoatedHalloysite = new SimpleDustMaterial("sulfur_coated_halloysite", 0x23973a, (short) 393, MaterialIconSet.ROUGH, "S2C2(Al9Si10O50Ga)");
        NickelOxideHydroxide = new SimpleDustMaterial("nickel_oxide_hydroxide",0xa2f2a2,(short) 398, MaterialIconSet.METALLIC, "NiO(OH)");
        TBCCODust = new SimpleDustMaterial("tbcco_dust", 0x669900, (short) 257, MaterialIconSet.SHINY, "TlBa2Ca2Cu3O10");
        StrontiumSuperconductorDust = new SimpleDustMaterial("strontium_superconductor_dust", 0x45abf4, (short) 260, MaterialIconSet.SHINY, "RuSgSr2O8");
        FullereneSuperconductiveDust = new SimpleDustMaterial("fullerene_superconductor_dust", 0x99cc00, (short) 261, MaterialIconSet.SHINY, "LaCsRb(C60)2");
        GoldLeach = new SimpleDustMaterial("gold_leach", 0xBBA52B, (short) 904, MaterialIconSet.ROUGH, "Cu3Au?");
        IridiumTrichlorideSolution = new SimpleFluidMaterial("iridiumtrichloridesolution", 0x96821a, "IrCl3");
        Quantum = new IngotMaterial(857, "quantum", 0x0f0f0f, MaterialIconSet.SHINY, 7, of(new MaterialStack(Stellite, 15), new MaterialStack(Jasper, 5), new MaterialStack(Gallium, 5), new MaterialStack(Americium241.getMaterial(), 5), new MaterialStack(Palladium, 5), new MaterialStack(Bismuth, 5), new MaterialStack(Germanium, 5), new SimpleDustMaterialStack(SiliconCarbide, 5)), GA_CORE_METAL | DISABLE_DECOMPOSITION | DISABLE_REPLICATION | GENERATE_METAL_CASING | DISABLE_AUTOGENERATED_MIXER_RECIPE, null, 11400);
        BoronFranciumCarbide = new SimpleDustMaterial("boron_francium_carbide", 0x808080, (short) 234, MaterialIconSet.SHINY, "F4B4C5");
        TlTmCesiumIodide = new SimpleDustMaterial("tl_tm_cesium_iodide",CesiumBromide.rgb*9/10+Thallium.materialRGB/10,(short) 353,MaterialIconSet.SHINY, "CsITlTm");
        RocketFuelH8N4C2O4 = new FluidMaterial(939, "rocket_fuel_a", 0x5ECB22, MaterialIconSet.FLUID, of(new MaterialStack(Hydrogen, 8), new MaterialStack(Nitrogen, 4), new MaterialStack(Carbon, 2), new MaterialStack(Oxygen, 4)), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
        Phosgene = new SimpleFluidMaterial("phosgene", (Chlorine.materialRGB+CarbonMonoxde.materialRGB)/2, "COCl2");
    }
}
