package gregicadditions.materials;

public class GAUnknownCompositionMaterials {

    public static void register() {
        Kerosene = new Material.Builder(9500, "kerosene")
                .fluid()
                .color(0xD570D5)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        RP1RocketFuel = new Material.Builder(9501, "rocket_fuel_c")
                .fluid()
                .color(0xFF503C)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Oxygen, 1)
                .build();
        RP1 = new Material.Builder(9502, "rp")
                .fluid()
                .color(0xFF6E5D)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        IodizedOil = new Material.Builder(9503, "iodized_oil")
                .fluid()
                .color(0x666666)
                .build();
        NdYAG = new Material.Builder(9504, "nd_yag")
                .dust(6).fluid()
                .color(0xcf8acf).iconSet(SHINY)
                .build();
        PrHoYLF = new Material.Builder(9505, "prho_ylf")
                .dust(6).fluid()
                .color(0x6f20af).iconSet(SHINY)
                .build();
        LuTmYVO = new Material.Builder(9506, "lutm_yvo")
                .dust(6).fluid()
                .color(0x206faf).iconSet(SHINY)
                .build();
        HeavyQuarkDegenerateMatter = new Material.Builder(9507, "heavy_quark_degenerate_matter")
                .ingot(6)
                .color(0x5dbd3a).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .blastTemp(13000)
                .build();
        QCDMatter = new Material.Builder(9508, "qcd_confined_matter")
                .ingot(7)
                .color(0xeb9e3f).iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_REPLICATION, NO_WORKING, NO_SMELTING, NO_SMASHING, GENERATE_FRAME, GENERATE_ROD)
                .blastTemp(13100)
                .build();
        CosmicNeutronium = new Material.Builder(9508, "cosmic_neutronium")
                .ingot(7)
                .color(0x323232).iconSet(SHINY)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Neutronium, 1)
                .blastTemp(14100)
                .build();
        Polyimide = new Material.Builder(9509, "polyimide")
                .ingot(1).fluid()
                .color(0xFF7F50).iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 22, Hydrogen, 12, Nitrogen, 2, Oxygen, 6)
                .build();
        FluorinatedEthylenePropylene = new Material.Builder(9510, "fluorinated_ethylene_propylene")
                .ingot(1).fluid
                .color(0xC8C8C8).iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 5, Fluorine, 10)
                .build();
        public static IngotMaterial FluorinatedEthylenePropylene = new IngotMaterial(988, "fluorinated_ethylene_propylene", 0xC8C8C8, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 5), new MaterialStack(Fluorine, 10)), GENERATE_PLATE | FLAMMABLE | NO_SMASHING | SMELT_INTO_FLUID | DISABLE_DECOMPOSITION);
        public static SimpleFluidMaterial Catalase = new SimpleFluidMaterial("catalase", 0xdb6596, "?");
        public static SimpleFluidMaterial Blood = new SimpleFluidMaterial("blood", 0x5c0606, "Blood");
        public static SimpleFluidMaterial BloodCells = new SimpleFluidMaterial("blood_cells", 0xad2424, "???");
        public static SimpleFluidMaterial BloodPlasma = new SimpleFluidMaterial("blood_plasma", 0xe37171, "???");
        public static SimpleFluidMaterial BFGF = new SimpleFluidMaterial("bfgf", 0xb365e0, "bFGF");
        public static SimpleFluidMaterial BacterialGrowthMedium = new SimpleFluidMaterial("bacterial_growth_medium", 0x0b2e12, "For Bacteria");
        public static SimpleFluidMaterial DepletedGrowthMedium = new SimpleFluidMaterial("depleted_growth_medium", 0x071209, "Depleted");
        public static SimpleFluidMaterial AnimalCells = new SimpleFluidMaterial("animal_cells", 0xc94996, "???");
        public static SimpleFluidMaterial RapidlyReplicatingAnimalCells = new SimpleFluidMaterial("rapidly_replicating_animal_cells", 0x7a335e, TextFormatting.OBFUSCATED + "????");
        public static SimpleFluidMaterial MycGene = new SimpleFluidMaterial("myc_gene", 0x445724, "?");
        public static SimpleFluidMaterial Oct4Gene = new SimpleFluidMaterial("oct_4_gene", 0x374f0d, "?");
        public static SimpleFluidMaterial SOX2Gene = new SimpleFluidMaterial("sox_2_gene", 0x5d8714, "?");
        public static SimpleFluidMaterial KFL4Gene = new SimpleFluidMaterial("kfl_4_gene", 0x759143, "?");
        public static SimpleFluidMaterial Cas9 = new SimpleFluidMaterial("cas_9", 0x5f6e46, "?");
        public static SimpleFluidMaterial GenePlasmids = new SimpleFluidMaterial("pluripotency_induction_gene_plasmids", 0xabe053, "?");
        public static SimpleFluidMaterial Chitin = new SimpleFluidMaterial("chitin", 0xcbd479, "?");
        public static SimpleFluidMaterial Chitosan = new SimpleFluidMaterial("chitosan", 0xb1bd42, "?");
        public static SimpleFluidMaterial GeneTherapyFluid = new SimpleFluidMaterial("pluripotency_induction_gene_therapy_fluid", 0x6b2f66, "?");
        public static SimpleFluidMaterial Resin = new SimpleFluidMaterial("resin", 0x3d2f11, "?");
        public static SimpleFluidMaterial Brine = new SimpleFluidMaterial("brine", 0xfcfc8a, "?");
        public static SimpleFluidMaterial ZrHfSeparationMix = new SimpleFluidMaterial("zrhf_separation_mix", 0xfcfc95, "?");
        public static SimpleFluidMaterial MetalRichSlagSlurry = new SimpleFluidMaterial("metal_slag_slurry", (Zinc.materialRGB-10), "?");
        public static SimpleFluidMaterial PolyphenolMix = new SimpleFluidMaterial("polyphenol_mix", (Phenol.materialRGB+10), "?");
        public static SimpleFluidMaterial AcidifiedPolyphenolMix = new SimpleFluidMaterial("acidified_polyphenol_mix", (PolyphenolMix.rgb+SulfuricAcid.materialRGB)/2, "?");
        public static SimpleFluidMaterial Amidoxime = new SimpleFluidMaterial("amidoxime", 0x66ff33, "?");
        public static SimpleFluidMaterial DebrominatedWater = new SimpleFluidMaterial("debrominated_brine", 0x0000ff, WATER.formula());
        public static SimpleFluidMaterial SeaWater = new SimpleFluidMaterial("sea_water", 0x0000FF, WATER.formula()+"?");
        public static SimpleFluidMaterial ConcentratedBrine = new SimpleFluidMaterial("concentrated_brine", 0xfcfc95, "?");
        public static SimpleFluidMaterial CalciumFreeBrine = new SimpleFluidMaterial("calcium_free_brine", 0xfcfca6, "?");
        public static SimpleFluidMaterial SodiumFreeBrine = new SimpleFluidMaterial("sodium_free_brine", 0xfcfcb1, "?");
        public static SimpleFluidMaterial PotassiumFreeBrine = new SimpleFluidMaterial("potassium_free_brine", 0xfcfcbc, "?");
        public static SimpleFluidMaterial BoronFreeSolution = new SimpleFluidMaterial("boron_free_solution", 0xfcfccd, "?");
        public static SimpleFluidMaterial ChilledBrine = new SimpleFluidMaterial("chilled_brine", 0xfcfc95, "?");
        public static SimpleFluidMaterial Cycloparaphenylene = new SimpleFluidMaterial("cycloparaphenylene", 0x333333, "CPP");
        public static SimpleFluidMaterial NeutronPlasma = new SimpleFluidMaterial("neutron_plasma", 0xf0e9e9, "n");
        public static SimpleFluidMaterial QuassifissioningPlasma = new SimpleFluidMaterial("quasifissioning_plasma", 0xD5CB54, TextFormatting.OBFUSCATED + "???");
        public static SimpleFluidMaterial RubySlurry = new SimpleFluidMaterial("ruby_slurry", Ruby.materialRGB, "?");
        public static SimpleFluidMaterial SapphireSlurry = new SimpleFluidMaterial("sapphire_slurry", Sapphire.materialRGB, "?");
        public static SimpleFluidMaterial GreenSapphireSlurry = new SimpleFluidMaterial("green_sapphire_slurry", GreenSapphire.materialRGB, "?");
        public static SimpleFluidMaterial ATL = new SimpleFluidMaterial("atl", 0x709c4a, "ATL");
        public static SimpleFluidMaterial DrillingMud = new SimpleFluidMaterial("drilling_mud", 0x996600, "For the Void Miner");
        public static SimpleFluidMaterial UsedDrillingMud = new SimpleFluidMaterial("used_drilling_mud", 0x998833, "Used Mud");
        public static SimpleFluidMaterial UnprocessedNdYAGSolution = new SimpleFluidMaterial("unprocessed_ndyag_solution",0x6f20af, "Nd:YAG");
        public static SimpleFluidMaterial LiquidCrystalDetector = new SimpleFluidMaterial("liquid_crystal_detector",0xda20da);
        public static SimpleFluidMaterial QuarkGluonPlasma = new SimpleFluidMaterial("quark_gluon_plasma",0x8f00ff, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial HeavyQuarks = new SimpleFluidMaterial("heavy_quarks",0x008800, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)ds" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial LightQuarks = new SimpleFluidMaterial("light_quarks",0x0000ff, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(c2)(t2)b" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial Gluons = new SimpleFluidMaterial("gluons",0xfcfcfa, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "g" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial HeavyLeptonMix = new SimpleFluidMaterial("heavy_lepton_mix",0x5adf52, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(t2)u" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial CosmicComputingMix = new SimpleFluidMaterial("cosmic_computing_mix",0xafad2f, TextFormatting.OBFUSCATED + "aaaaa", false, true);
        public static SimpleFluidMaterial HeavyQuarkEnrichedMix = new SimpleFluidMaterial("heavy_quark_enriched_mix",0xefefef, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)b" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial DenseNeutronPlasma = new SimpleFluidMaterial("dense_neutron_plasma",0xacecac,1000000, false, TextFormatting.OBFUSCATED.toString() + "a" + TextFormatting.RESET + TextFormatting.GRAY + "n" + TextFormatting.OBFUSCATED.toString() + "a");
        public static SimpleFluidMaterial CosmicMeshPlasma = new SimpleFluidMaterial("cosmic_mesh_plasma",0x1c1c8c,1000000, false, TextFormatting.OBFUSCATED.toString() + "nn");
        public static SimpleFluidMaterial FreeAlphaGas = new SimpleFluidMaterial("free_alpha_gas", 0xe0d407, "a");
        public static SimpleFluidMaterial FreeElectronGas = new SimpleFluidMaterial("free_electron_gas", 0x044c4c, "e-");
        public static SimpleFluidMaterial HighEnergyQGP = new SimpleFluidMaterial("high_energy_qgp", 0x8f00ff, TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a", false, true);
        public static SimpleFluidMaterial FermionicUUMatter = new SimpleFluidMaterial("fermionic_uu_matter", UUMatter.materialRGB / 3, TextFormatting.OBFUSCATED + "???");
        public static SimpleFluidMaterial BosonicUUMatter = new SimpleFluidMaterial("bosonic_uu_matter", UUMatter.materialRGB - FermionicUUMatter.rgb, TextFormatting.OBFUSCATED + "???");
        public static SimpleFluidMaterial Soap = new SimpleFluidMaterial("soap", 0xFFAE42, "?");
        public static SimpleFluidMaterial DeglyceratedSoap = new SimpleFluidMaterial("deglyceratedsoap", 0xFFAE41);
        public static SimpleDustMaterial Yeast = new SimpleDustMaterial("yeast", 0xf0e660, (short) 26, MaterialIconSet.ROUGH, "???");
        public static SimpleDustMaterial GreenAlgae = new SimpleDustMaterial("green_algae", 0x228b22, (short) 32, MaterialIconSet.METALLIC, "An Algae");
        public static SimpleDustMaterial BrownAlgae = new SimpleDustMaterial("brown_algae", 0xa52a2a, (short) 33, MaterialIconSet.METALLIC, "An Algae");
        public static SimpleDustMaterial RedAlgae = new SimpleDustMaterial("red_algae", 0xf08080, (short) 34, MaterialIconSet.METALLIC, "An Algae");
        public static SimpleDustMaterial DryRedAlgae = new SimpleDustMaterial("dry_red_algae", 0xff7f50, (short) 35, MaterialIconSet.ROUGH, "A Dry Algae");
        public static SimpleDustMaterial RedAlgaePowder = new SimpleDustMaterial("red_algae_powder", 0xcc2f2f, (short) 36, MaterialIconSet.ROUGH, "A Powdered Algae");
        public static SimpleDustMaterial PreFreezeAgar = new SimpleDustMaterial("pre_freeze_agar", 0x132b0d, (short) 37, MaterialIconSet.ROUGH, "Warm Agar");
        public static SimpleDustMaterial FrozenAgarCrystals = new SimpleDustMaterial("frozen_agar_crystals", 0x68db4b, (short) 38, MaterialIconSet.SHINY, "Cold Agar");
        public static SimpleDustMaterial BrevibacteriumFlavium = new SimpleDustMaterial("brevibacterium_flavium", 0x2c4d24, (short) 40, MaterialIconSet.ROUGH, "Bacteria");
        public static SimpleDustMaterial StreptococcusPyogenes = new SimpleDustMaterial("streptococcus_pyogenes", 0x1c3b15, (short) 41, MaterialIconSet.ROUGH, "Bacteria");
        public static SimpleDustMaterial EschericiaColi = new SimpleDustMaterial("eschericia_coli", 0x2d4228, (short) 42, MaterialIconSet.ROUGH, "Bacteria");
        public static SimpleDustMaterial BifidobacteriumBreve = new SimpleDustMaterial("bifidobacterium_breve", 0x377528, (short) 43, MaterialIconSet.ROUGH, "Bacteria");
        public static SimpleDustMaterial Alumina = new SimpleDustMaterial("alumina", 0x0b585c, (short) 44, MaterialIconSet.ROUGH, "Al2O3");
        public static SimpleDustMaterial CupriavidusNecator = new SimpleDustMaterial("cupriavidus_necator", 0x22704f, (short) 46, MaterialIconSet.ROUGH, "Bacteria");
        public static SimpleDustMaterial Shewanella = new SimpleDustMaterial("shewanella", 0x8752ab, (short) 47, MaterialIconSet.METALLIC, "Bacteria");
        public static SimpleDustMaterial ZincResidualSlag = new SimpleDustMaterial("zinc_residual_slag", (Zinc.materialRGB-20), (short) 122, MaterialIconSet.DULL, "?");
        public static SimpleDustMaterial ZincFlueDust = new SimpleDustMaterial("zinc_flue_dust", 0xfcfca, (short) 123, MaterialIconSet.ROUGH, "?");
        public static SimpleDustMaterial FineZincSlagDust = new SimpleDustMaterial("fine_zinc_slag_dust", (Zinc.materialRGB-10), (short) 125, MaterialIconSet.FINE, "?");
        public static SimpleDustMaterial DehydrogenationCatalyst = new SimpleDustMaterial("dehydrogenation_catalyst", 0x6464f5, (short) 148, MaterialIconSet.SHINY, "?");
        public static SimpleDustMaterial NeodymiumDopedYttrium = new SimpleDustMaterial("neodymium_doped_yttrium",YttriumOxide.materialRGB,(short) 268,MaterialIconSet.DULL, "Nd:Y?");
        public static SimpleDustMaterial NdYAGNanoparticles = new SimpleDustMaterial("nd_yag_nanoparticles",0x6f20af,(short) 269,MaterialIconSet.SHINY, "Nd:YAG");
        public static SimpleDustMaterial PotassiumPermanganate = new SimpleDustMaterial("potassium_permanganate",PotassiumManganate.rgb-15,(short) 270,MaterialIconSet.ROUGH, "KMnO4");
        public static SimpleDustMaterial PrHoYLFNanoparticles = new SimpleDustMaterial("prho_ylf_nanoparticles",0xcf8acf,(short) 278,MaterialIconSet.SHINY, "Pr/Ho:YLF");
        public static SimpleDustMaterial LuTmYVONanoparticles = new SimpleDustMaterial("lutm_yvo_nanoparticles",0x206faf,(short) 279,MaterialIconSet.SHINY, "Lu/Tm:YVO");
        public static SimpleDustMaterial UnprocessedNdYAGDust = new SimpleDustMaterial("unprocessed_ndyag_dust",0x6f20af,(short) 283,MaterialIconSet.DULL, "Nd:YAG?");
        public static SimpleDustMaterial LuTmYVOPrecipitate = new SimpleDustMaterial("lutm_yvo_precipitate",0xcf8acf,(short) 318,MaterialIconSet.DULL, "Lu/Tm:YVO?");
        public static SimpleDustMaterial SelectivelyMutatedCupriavidiusNecator = new SimpleDustMaterial("selectively_mutated_cupriavidius_necator", CupriavidusNecator.rgb * 5 / 4, (short) 373, MaterialIconSet.SHINY, "Bacteria");
        public static SimpleDustMaterial CoAcABCatalyst = new SimpleDustMaterial("coacab_catalyst", 0x755f30, (short) 900, MaterialIconSet.FINE, "Co/AC-AB");
        public static Material DrillingMudMixture = new Material.Builder(18504, "drilling_mud_mixture").fluid().color(CaCBaSMixture.rgb + LubricantClaySlurry.rgb).build();
    }
}
