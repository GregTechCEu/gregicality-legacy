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
        Catalase = new Material.Builder(9511, "catalase")
                .fluid()
                .color(0xdb6596)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Blood = new Material.Builder(9512, "blood")
                .fluid()
                .color(0x5c0606)
                .build()
                .setFormula("Blood");
        BloodCells = new Material.Builder(9513, "blood_cells")
                .fluid()
                .color(0xad2424)
                .build()
                .setFormula("???");
        BloodPlasma = new Material.Builder(9514, "blood_plasma")
                .fluid()
                .color(0xe37171)
                .build()
                .setFormula("???");
        BFGF = new Material.Builder(9515, "bfgf")
                .fluid()
                .color(0xb365e0)
                .build()
                .setFormula("bFGF");
        BacterialGrowthMedium = new Material.Builder(9516, "bacterial_growth_medium")
                .fluid()
                .color(0x0b2e12)
                .build()
                .setFormula("For Bacteria");
        DepletedGrowthMedium = new Material.Builder(9517, "depleted_growth_medium")
                .fluid()
                .color(0x071209)
                .build()
                .setFormula("Depleted");
        AnimalCells = new Material.Builder(9518, "animal_cells")
                .fluid()
                .color(0xc94996)
                .build()
                .setFormula("???");
        RapidlyReplicatingAnimalCells = new Material.Builder(9519, "rapidly_replicating_animal_cells")
                .fluid()
                .color(0x7a335e)
                // TextFormatting.OBFUSCATED + "????"
                .build();
        MycGene = new Material.Builder(9520, "myc_gene")
                .fluid()
                .color(0x445724)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Oct4Gene = new Material.Builder(9521, "oct_4_gene")
                .fluid()
                .color(0x374f0d)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        SOX2Gene = new Material.Builder(9522, "sox_2_gene")
                .fluid()
                .color(0x5d8714)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        KFL4Gene = new Material.Builder(9523, "kfl_4_gene")
                .fluid()
                .color(0x759143)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Cas9 = new Material.Builder(9524, "cas_9")
                .fluid()
                .color(0x5f6e46)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        GenePlasmids = new Material.Builder(9525, "pluripotency_induction_gene_plasmids")
                .fluid()
                .color(0xabe053)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Chitin = new Material.Builder(9526, "chitin")
                .fluid()
                .color(0xcbd479)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Chitosan = new Material.Builder(9527, "chitosan")
                .fluid()
                .color(0xb1bd42)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        GeneTherapyFluid = new Material.Builder(9528, "pluripotency_induction_gene_therapy_fluid")
                .fluid()
                .color(0x6b2f66)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Resin = new Material.Builder(9529, "resin")
                .fluid()
                .color(0x3d2f11)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Brine = new Material.Builder(9530, "brine")
                .fluid()
                .color(0xfcfc8a)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        ZrHfSeparationMix = new Material.Builder(9531, "zrhf_separation_mix")
                .fluid()
                .color(0xfcfc95)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        MetalRichSlagSlurry = new Material.Builder(9532, "metal_slag_slurry")
                .fluid()
                .color(0xFAF0E6)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        PolyphenolMix = new Material.Builder(9533, "polyphenol_mix")
                .fluid()
                .color(0x78442B)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        AcidifiedPolyphenolMix = new Material.Builder(9534, "acidified_polyphenol_mix")
                .fluid()
                .color(0xBBE215)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Amidoxime = new Material.Builder(9535, "amidoxime")
                .fluid()
                .color(0x66ff33)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        DebrominatedWater = new Material.Builder(9536, "debrominated_brine")
                .fluid()
                .color(0x0000ff)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1)
                .build();
        SeaWater = new Material.Builder(9537, "sea_water")
                .fluid()
                .color(0x0000FF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Water, 1, RareEarth, 1)
                .build();
        ConcentratedBrine = new Material.Builder(9538, "concentrated_brine")
                .fluid()
                .color(0xfcfc95)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        CalciumFreeBrine = new Material.Builder(9539, "calcium_free_brine")
                .fluid()
                .color(0xfcfca6)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        SodiumFreeBrine = new Material.Builder(9540, "sodium_free_brine")
                .fluid()
                .color(0xfcfcb1)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        PotassiumFreeBrine = new Material.Builder(9541, "potassium_free_brine")
                .fluid()
                .color(0xfcfcbc)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        BoronFreeSolution = new Material.Builder(9542, "boron_free_solution")
                .fluid()
                .color(0xfcfccd)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        ChilledBrine = new Material.Builder(9543, "chilled_brine")
                .fluid()
                .color(0xfcfc95)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        Cycloparaphenylene = new Material.Builder(9544, "cycloparaphenylene")
                .fluid()
                .color(0x333333)
                .build()
                .setFormula("CPP");
        NeutronPlasma = new Material.Builder(9545, "neutron_plasma")
                .fluid()
                .color(0xf0e9e9)
                .build()
                .setFormula("n");
        QuassifissioningPlasma = new Material.Builder(9546, "quasifissioning_plasma")
                .fluid()
                .color(0xD5CB54)
                // TextFormatting.OBFUSCATED + "???"
                .build();
        RubySlurry = new Material.Builder(9547, "ruby_slurry")
                .fluid()
                .color(0xFF6464)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        SapphireSlurry = new Material.Builder(9548, "sapphire_slurry")
                .fluid()
                .color(0x6464C8)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        GreenSapphireSlurry = new Material.Builder(9549, "green_sapphire_slurry")
                .fluid()
                .color(0x64C882)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        ATL = new Material.Builder(9550, "atl")
                .fluid()
                .color(0x709c4a)
                .build()
                .setFormula("ATL");
        DrillingMud = new Material.Builder(9551, "drilling_mud")
                .fluid()
                .color(0x996600)
                .build()
                .setFormula("For the Void Miner");
        UsedDrillingMud = new Material.Builder(9552, "used_drilling_mud")
                .fluid()
                .color(0x998833)
                .build()
                .setFormula("Used Mud");
        UnprocessedNdYAGSolution = new Material.Builder(9553, "unprocessed_ndyag_solution")
                .fluid()
                .color(0x6f20af)
                .build()
                .setFormula("Nd:YAG");
        LiquidCrystalDetector = new Material.Builder(9554, "liquid_crystal_detector")
                .fluid()
                .color(0xda20da)
                .build();
        QuarkGluonPlasma = new Material.Builder(9555, "quark_gluon_plasma")
                .fluid()
                .color(0x8f00ff)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        HeavyQuarks = new Material.Builder(9556, "heavy_quarks")
                .fluid()
                .color(0x008800)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)ds" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        LightQuarks = new Material.Builder(9557, "light_quarks")
                .fluid()
                .color(0x0000ff)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(c2)(t2)b" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        Gluons = new Material.Builder(9558, "gluons")
                .fluid()
                .color(0xfcfcfa)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "g" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        HeavyLeptonMix = new Material.Builder(9559, "heavy_lepton_mix")
                .fluid()
                .color(0x5adf52)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(t2)u" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        CosmicComputingMix = new Material.Builder(9560, "cosmic_computing_mix")
                .fluid()
                .color(0xafad2f)
                // TextFormatting.OBFUSCATED + "aaaaa"
                // fancy tooltip
                .build();
        HeavyQuarkEnrichedMix = new Material.Builder(9561, "heavy_quark_enriched_mix")
                .fluid()
                .color(0xefefef)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)b" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        DenseNeutronPlasma = new Material.Builder(9562, "dense_neutron_plasma")
                .fluid()
                .color(0xacecac)
                .fluidTemp(1000000)
                // TextFormatting.OBFUSCATED.toString() + "a" + TextFormatting.RESET + TextFormatting.GRAY + "n" + TextFormatting.OBFUSCATED.toString() + "a"
                .build();
        CosmicMeshPlasma = new Material.Builder(9563, "cosmic_mesh_plasma")
                .fluid()
                .color(0x1c1c8c)
                .fluidTemp(1000000)
                // TextFormatting.OBFUSCATED.toString() + "nn"
                .build();
        FreeAlphaGas = new Material.Builder(9564, "free_alpha_gas")
                .fluid()
                .color(0xe0d407)
                .build()
                .setFormula("a");
        FreeElectronGas = new Material.Builder(9565, "free_electron_gas")
                .fluid()
                .color(0x044c4c)
                .build()
                .setFormula("e-");
        HighEnergyQGP = new Material.Builder(9566, "high_energy_qgp")
                .fluid()
                .color(0x8f00ff)
                // TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a"
                // fancy tooltip
                .build();
        FermionicUUMatter = new Material.Builder(9567, "fermionic_uu_matter")
                .fluid()
                .color(0x27AAD2)
                // TextFormatting.OBFUSCATED + "???"
                .build();
        BosonicUUMatter = new Material.Builder(9568, "bosonic_uu_matter")
                .fluid()
                .color(0x4F55A5)
                // TextFormatting.OBFUSCATED + "???"
                .build();
        Soap = new Material.Builder(9569, "soap")
                .fluid()
                .color(0xFFAE42)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();
        DeglyceratedSoap = new Material.Builder(9570, "deglyceratedsoap")
                .fluid()
                .color(0xFFAE41)
                .build();
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
