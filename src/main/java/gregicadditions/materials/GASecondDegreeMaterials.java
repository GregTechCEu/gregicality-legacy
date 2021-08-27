package gregicadditions.materials;

import gregicadditions.GAMaterials;
import gregtech.api.unification.material.Material;

import static gregicadditions.materials.GAMaterialFlags.*;
import static gregtech.api.unification.material.Materials.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
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

        NaquadricSolution = new Material.Builder(11529, "naquadric_solution")
                .fluid()
                .color(0x232225)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadah, 1, NitrogenDioxide, 1)
                .build();

        EnrichedNaquadricSolution = new Material.Builder(11530, "enriched_naquadric_solution")
                .fluid()
                .color(0x312735)
                .flags(DISABLE_DECOMPOSITION)
                .components(NaquadahEnriched, 1, NitrogenDioxide, 1)
                .build();

        NaquadriaticSolution = new Material.Builder(11531, "naquadriatic_solution")
                .fluid()
                .color(0x312735)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, NitrogenDioxide, 1)
                .build();

        CaesiumXenontrioxideFluoride = new Material.Builder(11532, "caesium_xenontrioxide_fluoride")
                .fluid()
                .color(0x3333CC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Caesium, 1, XenonTrioxide, 1, Fluorine, 1)
                .build();

        NitrylFluoride = new Material.Builder(11533, "nitryl_fluoride")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(NitrogenDioxide, 1, Fluorine, 1)
                .build();

        NitrosoniumOctafluoroxenate = new Material.Builder(11534, "nitrosonium_octafluoroxenate")
                .fluid()
                .color(0x3F3F83)
                .flags(DISABLE_DECOMPOSITION)
                .components(NitrogenDioxide, 2, Xenon, 1, Fluorine, 8)
                .build();

        NaquadriaCesiumfluoride = new Material.Builder(11535, "naquadria_caesiumfluoride")
                .fluid()
                .color(0x6363790)
                .flags(DISABLE_DECOMPOSITION)
                .components(Naquadria, 1, Fluorine, 2, CaesiumFluoride, 1)
                .build();

        SodiumHydroxideSolution = new Material.Builder(11536, "sodium_hydroxide_solution")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Water, 1, SodiumHydroxide, 1)
                .build();

        AmmoniumNitrate = new Material.Builder(11537, "ammonium_nitrate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Ammonia, 1, NitricAcid, 1)
                .build()
                .setFormula("NH4NO3", true);

        SodiumNitrateSolution = new Material.Builder(11538, "sodium_nitrate_solution")
                .fluid()
                .color(0xA09ED7)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(SodiumNitrate, 1, Water, 1)
                .build();

        IodizedBrine = new Material.Builder(11539, "iodized_brine")
                .fluid()
                .color(0x525242)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iodine, 1, RareEarth, 1)
                .build();

        IodineSlurry = new Material.Builder(11540, "iodine_slurry")
                .fluid()
                .color(0x08081C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iodine, 1, RareEarth, 1)
                .build();

        Hydroxylamine = new Material.Builder(11541, "hydroxylamine")
                .fluid()
                .color(0x99CC99)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 3, Nitrogen, 1, Oxygen, 1)
                .build();

        HydroxylamineDisulfate = new Material.Builder(11542, "hydroxylamine_disulfate")
                .fluid()
                .color(0x99ADD6)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ammonia, 2, Oxygen, 6, Hydrogen, 2, Sulfur, 1)
                .build()
                .setFormula("(NH3OH)2SO4", true);

        // Free ID 11543

        AcidicSaltWater = new Material.Builder(11544, "acidic_salt_water")
                .fluid()
                .color(0x006960)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Water, 1, SulfuricAcid, 1, Chlorine, 1)
                .build();

        SulfuricBromineSolution = new Material.Builder(11545, "sulfuric_bromine_solution")
                .fluid()
                .color(0xFF5100)
                .flags(DISABLE_DECOMPOSITION)
                .components(Bromine, 1, SulfuricAcid, 1, RareEarth, 1)
                .build();

        HotVapourMixture = new Material.Builder(11546, "hot_vapor_mixture")
                .fluid()
                .color(0xFF5100)
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricBromineSolution, 1, Water, 1)
                .build();

        DampBromine = new Material.Builder(11547, "damp_bromine")
                .fluid()
                .color(0xE17594)
                .flags(DISABLE_DECOMPOSITION)
                .components(Bromine, 1, Water, 1)
                .build();

        RareEarthHydroxidesSolution = new Material.Builder(11548, "rare_earth_hydroxides_solution")
                .fluid()
                .color(0xCFB37D)
                .flags(DISABLE_DECOMPOSITION)
                .components(SodiumHydroxideSolution, 1, RareEarth, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("NaOH(H2O)?(OH)3", true);

        RareEarthChloridesSolution = new Material.Builder(11549, "rare_earth_chlorides_solution")
                .fluid()
                .color(0x164B45)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarthHydroxidesSolution, 1, RareEarth, 1, Chlorine, 3)
                .build()
                .setFormula("(?Cl3)H2O", true);

        LaNdOxidesSolution = new Material.Builder(11550, "la_nd_oxides_solution")
                .fluid()
                .color(0x9CE3DB)
                .flags(DISABLE_DECOMPOSITION)
                .components(LanthanumOxide, 1, PraseodymiumOxide, 1, NeodymiumOxide, 1, CeriumOxide, 1)
                .build();

        SmGdOxidesSolution = new Material.Builder(11551, "sm_gd_oxides_solution")
                .fluid()
                .color(0xFFFF99)
                .flags(DISABLE_DECOMPOSITION)
                .components(ScandiumOxide, 1, EuropiumOxide, 1, GadoliniumOxide, 1, SamariumOxide, 1)
                .build();

        TbHoOxidesSolution = new Material.Builder(11552, "tb_ho_oxides_solution")
                .fluid()
                .color(0x99FF99)
                .flags(DISABLE_DECOMPOSITION)
                .components(YttriumOxide, 1, TerbiumOxide, 1, DysprosiumOxide, 1, HolmiumOxide, 1)
                .build();

        ErLuOxidesSolution = new Material.Builder(11553, "er_lu_oxides_solution")
                .fluid()
                .color(0xFFB3FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(ErbiumOxide, 1, ThuliumOxide, 1, YtterbiumOxide, 1, LutetiumOxide, 1)
                .build();

        CleanAmmoniaSolution = new Material.Builder(11554, "clear_ammonia_solution")
                .fluid()
                .color(0x53C9A0)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Ammonia, 1, Water, 1)
                .build();

        SilicaGelBase = new Material.Builder(11555, "silica_gel_base")
                .fluid()
                .color(0x27A176)
                .flags(DISABLE_DECOMPOSITION)
                .components(SiliconDioxide, 1, HydrochloricAcid, 1, SodiumHydroxideSolution, 1)
                .build();

        PiranhaSolution = new Material.Builder(11556, "piranha_solution")
                .fluid()
                .color(0x4820AB)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(SulfuricAcid, 1, HydrogenPeroxide, 1)
                .build();

        WaterAgarMix = new Material.Builder(11557, "water_agar_mix")
                .fluid()
                .color(0x48DBBE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, RareEarth, 1)
                .build();

        RedOil = new Material.Builder(11558, "red_oil")
                .fluid()
                .color(0x7C1500)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrazine, 1, RP1, 1, FerriteMixture, 1)
                .build();

        ChlorideLeachedSolution = new Material.Builder(11559, "chloride_leached_solution")
                .fluid()
                .color(0x41472E)
                .flags(DISABLE_DECOMPOSITION)
                .components(CalciumChloride, 1, CopperChloride, 1, LeadChloride, 1, BismuthChloride, 1, Iron2Chloride, 1)
                .build();

        MolybdenumFlue = new Material.Builder(11560, "molybdenum_flue_gas")
                .fluid(Material.FluidType.GAS)
                .color(0x333338)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Rhenium, 1, Sulfur, 1, RareEarth, 1)
                .build();

        CalciumTungstate = new Material.Builder(11561, "calcium_tungstate")
                .dust()
                .color(0x6E6867).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tungsten, 1, CalciumCarbide, 1, Oxygen, 4)
                .build();

        UranylChlorideSolution = new Material.Builder(11562, "uranyl_chloride_solution")
                .fluid()
                .color(0xDFE018)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Chlorine, 2, Water, 1, RareEarth, 1)
                .build();

        UraniumSulfateWasteSolution = new Material.Builder(11563, "uranium_sulfate_waste_solution")
                .fluid()
                .color(0xDFE018)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lead, 1, Radon, 1, Strontium, 1, SulfuricAcid, 1)
                .build();

        PurifiedUranylNitrate = new Material.Builder(11564, "purified_uranyl_nitrate_solution")
                .fluid()
                .color(0xEFF028)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Nitrogen, 2, Oxygen, 6, Water, 1)
                .build()
                .setFormula("UO2(NO3)2(H2O)", true);

        UranylNitrateSolution = new Material.Builder(11565, "uranyl_nitrate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(PurifiedUranylNitrate, 1, RareEarth, 1)
                .build();

        UraniumDiuranate = new Material.Builder(11566, "uranium_diuranate")
                .fluid()
                .color(0xEFF028)
                .flags(DISABLE_DECOMPOSITION)
                .components(NitrogenDioxide, 2, Hydrogen, 8, Uranium238, 2, Oxygen, 7)
                .build()
                .setFormula("(NH4)2U207", true);

        UraniumRefinementWasteSolution = new Material.Builder(11567, "uranium_refinement_waste_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricAcid, 1, Carbon, 1, RareEarth, 1)
                .build();

        ThoriumNitrateSolution = new Material.Builder(11568, "thorium_nitrate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Nitrogen, 4, Oxygen, 12, Water, 1)
                .build()
                .setFormula("Th(NO3)4(H2O)", true);

        SodiumHexafluoroaluminate = new Material.Builder(11569, "sodium_hexafluoroaluminate")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .build();

        SodiumCarbonateSolution = new Material.Builder(11570, "sodium_carbonate_solution")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(SodaAsh, 1, Water, 1)
                .build();

        // Free ID 11571

        // Free ID 11572

        // Free ID 11573

        // Free ID 11574

        LithiumCarbonateSolution = new Material.Builder(11575, "lithium_carbonate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 2, Carbon, 1, Oxygen, 3, Water, 1)
                .build();

        LithiumChlorideSolution = new Material.Builder(11576, "lithium_chloride_solution")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(LithiumChloride, 1, Water, 1)
                .build();

        CalicheIodateBrine = new Material.Builder(11577, "caliche_iodate_brine")
                .fluid()
                .color(0xFFE666)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, SodiumNitrate, 1, Saltpeter, 1, RockSalt, 1, SodiumIodate, 1)
                .build();

        IodideSolution = new Material.Builder(11578, "iodide_solution")
                .fluid()
                .color(0x08081C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, SodiumNitrate, 1, Saltpeter, 1, RockSalt, 1, SodiumIodide, 1)
                .build();

        CalicheNitrateSolution = new Material.Builder(11579, "caliche_nitrate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, SodiumNitrate, 1, Saltpeter, 1, RockSalt, 1, SodiumHydroxide, 1)
                .build();

        CalicheIodineBrine = new Material.Builder(11580, "caliche_iodine_brine")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(CalicheNitrateSolution, 1, Iodine, 1)
                .build();

        ZirconChlorinatingResidue = new Material.Builder(11581, "zircon_chlorinating_residue")
                .fluid()
                .color(0x51D351)
                .flags(DISABLE_DECOMPOSITION)
                .components(SiliconChloride, 1, Cobalt, 1, RareEarth, 1)
                .build();

        ZincExhaustMixture = new Material.Builder(11582, "zinc_exhaust_mixture")
                .fluid(Material.FluidType.GAS)
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfurDioxide, 1, CarbonDioxide, 1, RareEarth, 1)
                .build();

        ZincSlagSlurry = new Material.Builder(11583, "zinc_slag_slurry")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, RareEarth, 1)
                .build();

        AcidicMetalSlurry = new Material.Builder(11584, "acidic_metal_slurry")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(PhosphoricAcid, 1, RareEarth, 1)
                .build();

        SeparatedMetalSlurry = new Material.Builder(11585, "separated_metal_slurry")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(AcidicMetalSlurry, 1, Zinc, 1)
                .build();

        CadmiumThalliumLiquor = new Material.Builder(11586, "cdtl_liquor")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricAcid, 1, Cadmium, 1, Thallium, 1)
                .build();

        CadmiumSulfateSolution = new Material.Builder(11587, "cadmium_sulfate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Cadmium, 1, Sulfur, 1, Oxygen, 4, RareEarth, 1)
                .build();

        ThalliumSulfateSolution = new Material.Builder(11588, "thallium_sulfate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Thallium, 2, Sulfur, 1, Oxygen, 4, RareEarth, 1)
                .build();

        GermanicAcidSolution = new Material.Builder(11589, "germanic_acid_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 4, Germanium, 1, Oxygen, 4)
                .build();

        // Free ID 11590

        LithiumHydroxideSolution = new Material.Builder(11591, "lithium_hydroxide_solution")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Water, 1, LithiumHydroxide, 1)
                .build();

        LithiumPeroxideSolution = new Material.Builder(11592, "lithium_peroxide_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Lithium, 2, Oxygen, 2)
                .build();

        PureUranylNitrateSolution = new Material.Builder(11593, "pure_uranyl_nitrate")
                .fluid()
                .color(0x33BD45)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Uraninite, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("(H2O)UO2(NO3)2", true);

        SodiumLithiumSolution = new Material.Builder(11594, "sodium_lithium_solution")
                .fluid()
                .color(0xFCFCCD)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Lithium, 1, RareEarth, 1)
                .build();

        MagnesiumContainingBrine = new Material.Builder(11595, "magnesium_containing_brine")
                .fluid()
                .color(0xFCFCBC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Magnesium, 1, RareEarth, 1)
                .build();

        BrominatedBrine = new Material.Builder(11596, "brominated_brine")
                .fluid()
                .color(0xFDD48D)
                .flags(DISABLE_DECOMPOSITION)
                .components(Bromine, 1, RareEarth, 1)
                .build();

        AcidicBrominatedBrine = new Material.Builder(11597, "acidic_brominated_brine")
                .fluid()
                .color(0xFDD48D)
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricAcid, 1, Chlorine, 1, RareEarth, 1)
                .build();

        SelenateTellurateMix = new Material.Builder(11598, "selenate_tellurate_mixture")
                .fluid()
                .color(0x765A30)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tellurium, 1, Selenium, 1, Hydrogen, 4, Sodium, 4, Carbon, 2, Oxygen, 6)
                .build()
                .setFormula("TeSe(H2Na2CO3)2", true);

        CopperRefiningSolution = new Material.Builder(11599, "copper_refining_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Copper, 1, SulfuricAcid, 1)
                .build();

        // Free ID 11600

        // Free ID 11601

        // Free ID 11602

        // Free ID 11603

        // Free ID 11604

        // Free ID 11605

        DiluteNitricAcid = new Material.Builder(11606, "dilute_nitric_acid")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Water, 1, NitricAcid, 1)
                .build();

        NbTaFluorideMix = new Material.Builder(11607, "nbta_fluoride_mix")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(FluoroniobicAcid, 1, FluorotantalicAcid, 1)
                .build();

        REEThUSulfateSolution = new Material.Builder(11608, "reethu_sulfate_soltion")
                .fluid()
                .color(0x89BE5C)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Sulfur, 1, Oxygen, 4)
                .build();

        RareEarthNitrateSolution = new Material.Builder(11609, "rare_earth_nitrate_solution")
                .fluid()
                .color(0xCFB37D)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Nitrogen, 1, Oxygen, 3)
                .build();

        AlkalineEarthSulfateSolution = new Material.Builder(11610, "alkalineearth_sulfate")
                .fluid()
                .color(0xE6EBFF)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Sulfur, 1, Oxygen, 4)
                .build();

        CalciumCarbonateSolution = new Material.Builder(11611, "calcium_carbonate_solution")
                .fluid()
                .colorAverage()
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Water, 1, Calcite, 1)
                .build();

        BariumSulfateSolution = new Material.Builder(11612, "barium_sulfate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Barite, 1)
                .build();

        BentoniteClaySlurry = new Material.Builder(11613, "bentontie_clay_solution")
                .fluid()
                .color(0xDBC9C5)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, RareEarth, 1)
                .build();

        AstatideSolution = new Material.Builder(11614, "astadide_solution")
                .fluid()
                .color(0x6DF63F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Astatine, 1, Water, 1, SulfurTrioxide, 1)
                .build();

        BariumChlorideSolution = new Material.Builder(11615, "barium_chloride_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, BariumChloride, 1)
                .build();

        IronCarbonyl = new Material.Builder(11616, "iron_carbonyl")
                .fluid()
                .color(0xFF8000)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 1, RareEarth, 1)
                .build();

        PurifiedIronCarbonyl = new Material.Builder(11617, "purified_iron_carbonyl")
                .fluid()
                .color(0xFFA000)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 1)
                .build();

        BismuthNitrateSoluton = new Material.Builder(11618, "bismuth_nitrate_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Bismuth, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("(H2O)Bi(NO3)3", true);

        PrYHoNitrateSolution = new Material.Builder(11619, "pryho_nitrate_solution")
                .fluid()
                .color(0x00F2B2)
                .flags(DISABLE_DECOMPOSITION)
                .components(YttriumNitrate, 6, Praseodymium, 2, Nitrogen, 12, Oxygen, 36, Neodymium, 2, Water, 15)
                .build()
                .setFormula("(Y(NO3)3)6(Pr(NO3)3)2(Nd(NO3)3)2(H2O)15", true);

        AmmoniumBifluorideSolution = new Material.Builder(11620, "ammonium_bifluoride_solution")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Ammonia, 1, HydrofluoricAcid, 2)
                .build()
                .setFormula("(H2O)NH4FHF", true);

        LuTmYChlorideSolution = new Material.Builder(11621, "lutmy_chloride_solution")
                .fluid()
                .color(0x00F2B2)
                .flags(DISABLE_DECOMPOSITION)
                .components(YttriumNitrate, 6, Lutetium, 2, Chlorine, 12, Thulium, 2, Water, 15)
                .build()
                .setFormula("(YCl3)6(LuCl3)2(TmCl3)2(H2O)15", true);

        // Free ID 11622

        SeaborgiumDopedNanotubes = new Material.Builder(11623, "seaborgium_doped_nanotubes")
                .fluid()
                .color(0x2C2C8C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Seaborgium, 1, CarbonNanotubes, 1)
                .build()
                .setFormula("SgCNT", true);

        FullereneDopedNanotubes = new Material.Builder(11624, "fullerene_doped_nanotubes")
                .fluid()
                .color(0x6C2C6C)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fullerene, 1, CarbonNanotubes, 1)
                .build()
                .setFormula("C60CNT", true);

        GrapheneOxidationSolution = new Material.Builder(11625, "graphene_oxidation_solution")
                .fluid()
                .color(0x96821A)
                .flags(DISABLE_DECOMPOSITION)
                .components(PotassiumManganate, 1, Saltpeter, 1, SulfuricAcid, 1)
                .build();

        SupercriticalCO2 = new Material.Builder(11626, "supercritical_co2")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(CarbonDioxide, 1)
                .build();

        LiquidEnrichedHelium = new Material.Builder(11627, "liquid_enriched_helium")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Helium, 1, Helium3, 1)
                .build();

        LiquidNitrogen = new Material.Builder(11628, "liquid_nitrogen")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 1)
                .build();

        RheniumScrubbedSolution = new Material.Builder(11629, "rhenium_scrubbed_solution")
                .fluid()
                .color(0xED2C3A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhenium, 1, RareEarth, 1)
                .build();

        NeutroniumDopedNanotubes = new Material.Builder(11630, "neutronium_doped_nanotubes")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Neutronium, 1, CarbonNanotubes, 1)
                .build()
                .setFormula("NtCNT", true);

        SupercriticalSteam = new Material.Builder(11631, "supercritical_steam")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Steam, 1)
                .build();

        SupercriticalSodiumPotassiumAlloy = new Material.Builder(11632, "supercritical_sodium_potassium_alloy")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(SodiumPotassiumAlloy, 1)
                .build();

        SupercriticalFLiNaK = new Material.Builder(11633, "supercritical_flinak")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(FLiNaK, 1)
                .build();

        SupercriticalFLiBe = new Material.Builder(11634, "supercritical_flibe")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(FLiBe, 1)
                .build();

        SupercriticalLeadBismuthEutectic = new Material.Builder(11635, "supercritical_lead_bismuth_eutectic")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(LeadBismuthEutectic, 1)
                .build();

        // Free ID 11636

        // Free ID 11637

        SilicaAluminaGel = new Material.Builder(11638, "silica_alumina_gel")
                .dust()
                .color(0x558D9E)
                .flags(DISABLE_DECOMPOSITION)
                .components(Alumina, 1, SiliconDioxide, 1)
                .build();

        ZeoliteSievingPellets = new Material.Builder(11639, "zeolite_sieving_pellets")
                .dust()
                .color(0xA17BD1).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Alumina, 1, SiliconDioxide, 1)
                .build();

        WetZeoliteSievingPellets = new Material.Builder(11640, "wet_zeolite_sieving_pellets")
                .dust()
                .color(0x392F45).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(ZeoliteSievingPellets, 1, RareEarth, 1)
                .build();

        ZBLAN = new Material.Builder(11641, "zblan")
                .ingot().fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION, GENERATE_FINE_WIRE, NO_UNIFICATION)
                .components(ZirconiumTetrafluoride, 18, BariumDifluoride, 7, LanthanumTrifluoride, 2, AluminiumTrifluoride, 1, SodiumFluoride, 7)
                .blastTemp(2200)
                .build();

        // Free ID 11642

        PotassiumUranylTricarbonate = new Material.Builder(11643, "potassium_uranyl_carbonate")
                .dust()
                .color(0xEFF028)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Carbon, 1, Oxygen, 3)
                .build()
                .setFormula("(UO2)CO3", true);

        UraniumPeroxideThoriumOxide = new Material.Builder(11644, "uranium_peroixide_thorium_oxide")
                .dust()
                .color(0x202020).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uranium238, 1, Oxygen, 5, HydrogenPeroxide, 1, Thorium, 1)
                .build()
                .setFormula("(UO3)(H2O2)ThO2", true);

        UraniumThoriumOxide = new Material.Builder(11645, "uranium_thorium_oxide")
                .dust()
                .color(0x202020).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Thorium, 1, Oxygen, 2)
                .build()
                .setFormula("UO2ThO2", true);

        UranylThoriumSulfate = new Material.Builder(11646, "uranyl_thorium_sulfate")
                .dust()
                .color(0xE7E848).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Sulfur, 1, Oxygen, 6, Thorium, 1)
                .build()
                .setFormula("(UO2)SO4ThO2", true);

        UranylThoriumNitrate = new Material.Builder(11647, "uranium_thorium_nitrate")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Nitrogen, 6, Oxygen, 18, Thorium, 1)
                .build()
                .setFormula("UO2(NO3)2Th(NO3)4", true);

        UraniumOxideThoriumNitrate = new Material.Builder(11648, "uranium_oxide_thorium_nitrate")
                .dust()
                .color(0x33BD45).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Thorium, 1, Nitrogen, 4, Oxygen, 12)
                .build()
                .setFormula("UO2Th(NO3)4", true);

        GrapheneOxidationResidue = new Material.Builder(11649, "graphene_oxidation_residue")
                .dust()
                .color(0x96821A).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(PotassiumManganate, 1, Saltpeter, 1, SulfuricAcid, 1)
                .build();

        ZincCokePellets = new Material.Builder(11650, "zinc_coke_pellets")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, Sphalerite, 1, Coke, 1)
                .build();

        CadmiumZinc = new Material.Builder(11651, "cadmium_zinc")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricAcid, 1, Cadmium, 1, Zinc, 1, RareEarth, 1)
                .build();

        UranylNitrate = new Material.Builder(11652, "uranyl_nitrate")
                .dust()
                .color(0x33BD45).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uraninite, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("UO2(NO3)2");

        SodiumSalts = new Material.Builder(11653, "sodium_salts")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Salt, 1, RareEarth, 1)
                .build();

        PotassiumMagnesiumSalts = new Material.Builder(11654, "kmg_salts")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(RockSalt, 1, MagnesiumSulfate, 1, PotassiumSulfate, 1 , PotassiumFluoride, 1)
                .build();

        CalciumMagnesiumSalts = new Material.Builder(11655, "camg_salts")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Calcite, 1, StrontiumCarbonate, 1, CarbonDioxide, 1, Magnesium, 1, Oxygen, 1)
                .build();

        SodiumAluminiumHydride = new Material.Builder(11656, "sodium_aluminium_hydride")
                .dust()
                .color(0x98CAFC).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Aluminium, 1, Hydrogen, 4)
                .build();

        LithiumAluminiumHydride = new Material.Builder(11657, "lithium_aluminium_hydride")
                .dust()
                .color(0xC0DEFC).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Aluminium, 1, Hydrogen, 4)
                .build();

        SodiumPhosphomolybdate = new Material.Builder(11658, "sodium_phosphomolybdate")
                .dust()
                .color(0xFCFC00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(MolybdenumTrioxide, 12, Sodium, 3, Phosphate, 1)
                .build();

        SodiumPhosphotungstate = new Material.Builder(11659, "sodium_phosphotungstate")
                .dust()
                .color(0x7a7777).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(TungstenTrioxide, 12, Sodium, 3, Phosphate, 1)
                .build();

        LeadNitrateCalciumMixture = new Material.Builder(11660, "lead_nitrate_calcium_mixture")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(LeadNitrate, 1, Calcium, 9)
                .build();

        TinSlag = new Material.Builder(11661, "tin_slag")
                .dust()
                .color(0xC8B9A9)
                .flags(DISABLE_DECOMPOSITION)
                .components(Niobium, 1, Tantalum, 1, RareEarth, 1)
                .build();

        NiobiumTantalumOxide = new Material.Builder(11662, "niobium_tantalum_oxide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Niobium, 2, Oxygen, 10, Tantalum, 2)
                .build()
                .setFormula("(Nb2O5)(Ta2O5)", true);

        FusedColumbite = new Material.Builder(11663, "fused_columbite")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 2, Oxygen, 8, SodiumOxide, 1, Niobium, 2)
                .build()
                .setFormula("(Fe2O3)(NaO)Nb2O5", true);

        LeachedColumbite = new Material.Builder(11664, "leached_columbite")
                .dust()
                .color(0xCCCC00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Niobium, 18, Oxygen, 45, Tantalum, 2, Oxygen, 5, RareEarth, 1)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?");

        FusedTantalite = new Material.Builder(11665, "fused_tantalite")
                .dust()
                .colorAverage().iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 2, Oxygen, 8, SodiumOxide, 1, Tantalum, 2)
                .build()
                .setFormula("(Fe2O3)(NaO)Ta2O5", true);

        LeachedTantalite = new Material.Builder(11666, "leached_tantalite")
                .dust()
                .color(0x00CCCC).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tantalum, 18, Oxygen, 45, Niobium, 2, Oxygen, 5, RareEarth, 1)
                .build()
                .setFormula("(Ta2O5)9Nb2O5?");

        ColumbiteMinorOxideResidue = new Material.Builder(11667, "columbite_minor_oxide_residue")
                .dust()
                .color(0x915028).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(BariumOxide, 1, Cassiterite, 1, TungstenTrioxide, 1, Alumina, 1)
                .build();

        TantaliteMinorOxideResidue = new Material.Builder(11668, "tantalite_minor_oxide_residue")
                .dust()
                .color(0xCC00CC).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(BariumOxide, 1, Zirconium, 1, Oxygen, 2, Rutile, 1, SiliconDioxide, 1)
                .build();

        LeachedPyrochlore = new Material.Builder(11669, "leached_pyrochlore")
                .dust()
                .color(0x996633).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Niobium, 18, Oxygen, 50, Tantalum, 2, RareEarth, 1)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?");

        AcidicLeachedPyrochlore = new Material.Builder(11670, "acidic_leached_pyrochlore")
                .dust()
                .color(0x336699).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(SulfuricAcid, 1, Calcium, 12, Strontium, 6, Barium, 6, RareEarth, 1, Thorium, 1, Uranium238, 1, Niobium, 26, Oxygen, 78, Fluorine, 26)
                .build()
                .setFormula("(H2SO4)Ca12Sr6Ba6?ThUNb26O78F26");

        PotassiumFluoroNiobate = new Material.Builder(11671, "potassium_fluoroniobate")
                .dust()
                .color(0x73ff00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Potassium, 2, Niobium, 1, Fluorine, 7)
                .build();

        PotasssiumFluoroTantalate = new Material.Builder(11672, "potassium_fluoroniobate")
                .dust()
                .color(0x00ff73).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Potassium, 2, Tantalum, 1, Fluorine, 7)
                .build();

        BariumPeroxide = new Material.Builder(11673, "barium_peroxide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Barium, 1, Oxygen, 2)
                .build();

        CopperGalliumIndiumSelenide = new Material.Builder(11674, "copper_gallium_indium_selenide")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Copper, 1, Gallium, 1, Indium, 1, Selenium, 2)
                .build();

        UVAHalideMix = new Material.Builder(11675, "uva_halide_mix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, GalliumIodide, 1, PotassiumIodide, 1)
                .build();

        WhiteHalideMix = new Material.Builder(11676, "white_halide_mix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, ScandiumIodide, 1, PotassiumIodide, 1)
                .build();

        BlueHalideMix = new Material.Builder(11677, "blue_halide_mix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, IndiumIodide, 1, PotassiumIodide, 1)
                .build();

        GreenHalideMix = new Material.Builder(11678, "green_halide_mix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, ThalliumIodide, 1, PotassiumIodide, 1)
                .build();

        RedHalideMix = new Material.Builder(11679, "red_halide_mix")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Mercury, 1, RubidiumIodide, 1, PotassiumIodide, 1)
                .build();

        LeadScandiumTantalate = new Material.Builder(11680, "lead_scandium_tantalate")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lead, 1, Scandium, 1, Tantalum, 1, Oxygen, 3)
                .build()
                .setFormula("PB(ScTa)O3", true);

        MagnetorestrictiveAlloy = new Material.Builder(11681, "magnetorestrictive_alloy")
                .dust()
                .color(0xAFEFEF).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Terbium, 4, Dysprosium, 7, Iron, 10, Cobalt, 5, Boron, 2, SiliconCarbide, 1)
                .build();

        // Free ID 11682

        // Free ID 11683

        // Free ID 11684

        // Free ID 11685

        PotassiumNonahydridotechnetate = new Material.Builder(11686, "potassium_nonahydridotechnetate")
                .dust()
                .color(0xEDE2A4).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 9, Potassium, 2, Technetium, 1, Oxygen, 4)
                .build();

        PotassiumNonahydridorhenate = new Material.Builder(11687, "potassium_nonahydridorhenate")
                .dust()
                .color(0xEDE2A4).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 9, Potassium, 2, Rhenium, 1, Oxygen, 4)
                .build();

        PalladiumLoadedRutileNanoparticles = new Material.Builder(11688, "palladium_loaded_rutile_nanoparticles")
                .dust()
                .colorAverage().iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Rutile, 1)
                .build();

        PurifiedColumbite = new Material.Builder(11689, "purified_columbite")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tantalum, 2, Oxygen, 50, Niobium, 18)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        PurifiedPyrochlore = new Material.Builder(11690, "purified_pyrochlore")
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tantalum, 2, Oxygen, 50, Niobium, 18)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        ChargedCesiumCeriumCobaltIndium = new Material.Builder(11691, "charged_cesium_cerium_cobalt_indium")
                .dust()
                .color(0x52AD25).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Caesium, 1, Cerium, 1, Cobalt, 2, Indium, 10)
                .build();

        SulfurCoatedHalloysite = new Material.Builder(11692, "sulfur_coated_halloysite")
                .dust()
                .color(0x23963A).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sulfur, 2, CarbonDioxide, 2, Alumina, 9, SiliconDioxide, 10, Oxygen, 50, Gallium, 1)
                .build()
                .setFormula("S2C2(Al9Si10O50Ga)", true);

        // Free ID 11693

        TBCCO = new Material.Builder(11694, "tbcco")
                .dust()
                .color(0x669900).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thallium, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 10)
                .build();

        StrontiumSuperconductor = new Material.Builder(11695, "strontium_superconductor_dust")
                .dust()
                .color(0x45ABF4).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Seaborgium, 1, GAMaterials.Celestine, 4)
                .build();

        FullereneSuperconductor = new Material.Builder(11696, "fullerene_superconductor")
                .dust()
                .color(0x99CC00).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lanthanum, 1, Caesium, 1, Rubidium, 1, Fullerene, 2)
                .build();

        // Free ID 11697

        IridiumTrichlorideSolution = new Material.Builder(11698, "iridiumtrichloridesolution")
                .fluid()
                .color(0x96821A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iridium, 1, Chlorine, 3)
                .build();

        Quantum = new Material.Builder(11699, "quantum")
                .ingot(7).fluid()
                .color(0x0F0F0F).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION, DISABLE_REPLICATION, DISABLE_AUTOGENERATED_MIXER_RECIPE)
                .components(Stellite, 15, Jasper, 5, Gallium, 5, Americium241, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
                .blastTemp(11400)
                .build();

        BoronFranciumCarbide = new Material.Builder(11700, "boron_francium_carbide")
                .dust()
                .color(0x808080).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Fluorine, 4, Boron, 4, Carbon, 5)
                .build();

        TlTmCesiumIodide = new Material.Builder(11701, "tl_tm_caesium_iodide")
                .dust()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Caesium, 1, Iodine, 1, Thallium, 1, Thulium, 1)
                .build();

        RocketFuelH8N4C2O4 = new Material.Builder(11702, "rocket_fuel_a")
                .fluid()
                .color(0x5ECB22)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 8, Nitrogen, 4, Carbon, 2, Oxygen, 4)
                .build();

        Phosgene = new Material.Builder(11703, "phosgene")
                .fluid()
                .colorAverage()
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 1, Oxygen, 1, Chlorine, 2)
                .build();

        FinelyPowderedRutile = new Material.Builder(11704, "finely_powdered_rutile")
                .fluid()
                .color(0xFFFFFF).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rutile, 1)
                .build();
    }
}
