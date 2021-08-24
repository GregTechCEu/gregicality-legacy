package gregicadditions.materials;

import gregtech.api.unification.material.Material;

import static gregicadditions.materials.GAMaterialFlags.*;
import static gregtech.api.unification.material.Materials.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GAUnknownCompositionMaterials {

    public static void register() {
        Kerosene = new Material.Builder(9500, "kerosene")
                .fluid()
                .color(0xd570d5)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        RP1RocketFuel = new Material.Builder(9501, "rocket_fuel_c")
                .fluid()
                .color(0xff503c)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1, Oxygen, 1)
                .build();

        RP1 = new Material.Builder(9502, "rp")
                .fluid()
                .color(0xff6e5d)
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

        // TODO: fill 2 empty ids
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
                .color(0xfaf0e6)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        PolyphenolMix = new Material.Builder(9533, "polyphenol_mix")
                .fluid()
                .color(0x78442b)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        AcidifiedPolyphenolMix = new Material.Builder(9534, "acidified_polyphenol_mix")
                .fluid()
                .color(0xbbe215)
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
                .color(0x0000ff)
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
                .color(0xd5cb54)
                // TextFormatting.OBFUSCATED + "???"
                .build();

        // Free ID 9547

        // Free ID 9548

        // Free ID 9549

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
                .color(0x27aad2)
                // TextFormatting.OBFUSCATED + "???"
                .build();

        BosonicUUMatter = new Material.Builder(9568, "bosonic_uu_matter")
                .fluid()
                .color(0x4f55a5)
                // TextFormatting.OBFUSCATED + "???"
                .build();

        Soap = new Material.Builder(9569, "soap")
                .fluid()
                .color(0xffae42)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        DeglyceratedSoap = new Material.Builder(9570, "deglyceratedsoap")
                .fluid()
                .color(0xffae41)
                .build();

        Yeast = new Material.Builder(9571, "yeast")
                .dust()
                .color(0xf0e660).iconSet(ROUGH)
                .build()
                .setFormula("???");

        GreenAlgae = new Material.Builder(9572, "green_algae")
                .dust()
                .color(0x228b22).iconSet(METALLIC)
                .build()
                .setFormula("An Algae");

        BrownAlgae = new Material.Builder(9573, "brown_algae")
                .dust()
                .color(0xa52a2a).iconSet(METALLIC)
                .build()
                .setFormula("An Algae");

        RedAlgae = new Material.Builder(9574, "red_algae")
                .dust()
                .color(0xf08080).iconSet(METALLIC)
                .build()
                .setFormula("An Algae");

        DryRedAlgae = new Material.Builder(9575, "dry_red_algae")
                .dust()
                .color(0xff7f50).iconSet(ROUGH)
                .build()
                .setFormula("A Dry Algae");

        RedAlgaePowder = new Material.Builder(9576, "red_algae_powder")
                .dust()
                .color(0xcc2f2f).iconSet(ROUGH)
                .build()
                .setFormula("A Powdered Algae");

        PreFreezeAgar = new Material.Builder(9577, "pre_freeze_agar")
                .dust()
                .color(0x132b0d).iconSet(ROUGH)
                .build()
                .setFormula("Warm Agar");

        FrozenAgarCrystals = new Material.Builder(9578, "frozen_agar_crystals")
                .dust()
                .color(0x68db4b).iconSet(SHINY)
                .build()
                .setFormula("Warm Agar");

        BrevibacteriumFlavium = new Material.Builder(9579, "brevibacterium_flavium")
                .dust()
                .color(0x2c4d24).iconSet(ROUGH)
                .build()
                .setFormula("Bacteria");

        StreptococcusPyogenes = new Material.Builder(9580, "streptococcus_pyogenes")
                .dust()
                .color(0x1c3b15).iconSet(ROUGH)
                .build()
                .setFormula("Bacteria");

        EschericiaColi = new Material.Builder(9581, "eschericia_coli")
                .dust()
                .color(0x2d4228).iconSet(ROUGH)
                .build()
                .setFormula("Bacteria");

        BifidobacteriumBreve = new Material.Builder(9582, "bifidobacterium_breve")
                .dust()
                .color(0x377528).iconSet(ROUGH)
                .build()
                .setFormula("Bacteria");

        CupriavidusNecator = new Material.Builder(9584, "cupriavidus_necator")
                .dust()
                .color(0x22704f).iconSet(ROUGH)
                .build()
                .setFormula("Bacteria");

        Shewanella = new Material.Builder(9585, "shewanella")
                .dust()
                .color(0x8752ab).iconSet(METALLIC)
                .build()
                .setFormula("Bacteria");

        ZincResidualSlag = new Material.Builder(9586, "zinc_residual_slag")
                .dust()
                .color(0xfaf0dc).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        ZincFlue = new Material.Builder(9587, "zinc_flue")
                .dust()
                .color(0xfcfca).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        FineZincSlag = new Material.Builder(9588, "fine_zinc_slag")
                .dust()
                .color(0xfaf0e6).iconSet(FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        DehydrogenationCatalyst = new Material.Builder(9589, "dehydrogenation_catalyst")
                .dust()
                .color(0x6464f5).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(RareEarth, 1)
                .build();

        NeodymiumDopedYttrium = new Material.Builder(9590, "neodymium_doped_yttrium")
                .dust()
                .color(0xc6ebb3).iconSet(DULL)
                .build()
                .setFormula("Nd:Y?");

        NdYAGNanoparticles = new Material.Builder(9591, "nd_yag_nanoparticles")
                .dust()
                .color(0x6f20af).iconSet(SHINY)
                .build()
                .setFormula("Nd:YAG");

        PotassiumPermanganate = new Material.Builder(9592, "potassium_permanganate")
                .dust()
                .color(0xaf20a0).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Potassium, 1, Manganese, 1, Oxygen, 4)
                .build();

        PrHoYLFNanoparticles = new Material.Builder(9593, "prho_ylf_nanoparticles")
                .dust()
                .color(0xcf8acf).iconSet(SHINY)
                .build()
                .setFormula("Pr/Ho:YLF");

        LuTmYVONanoparticles = new Material.Builder(9594, "lutm_yvo_nanoparticles")
                .dust()
                .color(0x206faf).iconSet(SHINY)
                .build()
                .setFormula("Lu/Tm:YVO");

        UnprocessedNdYAG = new Material.Builder(9595, "unprocessed_ndyag")
                .dust()
                .color(0x6f20af).iconSet(DULL)
                .build()
                .setFormula("Nd:YAG?");

        LuTmYVOPrecipitate = new Material.Builder(9596, "lutm_yvo_precipitate")
                .dust()
                .color(0xcf8acf).iconSet(DULL)
                .build()
                .setFormula("Lu/Tm:YVO?");

        LuTmYVOPrecipitate = new Material.Builder(9597, "lutm_yvo_precipitate")
                .dust()
                .color(0xcf8acf).iconSet(DULL)
                .build()
                .setFormula("Lu/Tm:YVO?");

        SelectivelyMutatedCupriavidiusNecator = new Material.Builder(9598, "selectively_mutated_cupriavidius_necator")
                .dust()
                .color(0x2b0c62).iconSet(SHINY)
                .build()
                .setFormula("Bacteria");

        CoAcABCatalyst = new Material.Builder(9599, "coacab_catalyst")
                .dust()
                .color(0x755f30).iconSet(FINE)
                .build()
                .setFormula("Co/AC-AB");

        DrillingMudMixture = new Material.Builder(9600, "drilling_mud_mixture")
                .fluid()
                .colorAverage()
                .components(CaCBaSMixture, 1, LubricantClaySlurry, 1)
                .build();

    }
}
