package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CosmicComponents {
    public static void init() {

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(140).EUt(14000000)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.LEPTONIC_CHARGE))
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .fluidOutputs(QuarkGluonPlasma.getFluid(2000))
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(1200000)
                .fluidInputs(QuarkGluonPlasma.getFluid(1000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(HeavyQuarks.getFluid(200))
                .fluidOutputs(Gluons.getFluid(200))
                .fluidOutputs(LightQuarks.getFluid(600))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(320).EUt(2400000)
                .fluidInputs(HeavyLeptonMix.getFluid(1000))
                .fluidInputs(HeavyQuarks.getFluid(1000))
                .fluidInputs(Gluons.getFluid(1000))
                .fluidOutputs(CosmicComputingMix.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(260).EUt(3250000)
                .fluidInputs(HeavyQuarks.getFluid(750))
                .fluidInputs(LightQuarks.getFluid(250))
                .fluidOutputs(HeavyQuarkEnrichedMix.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(450000)
                .fluidInputs(Titanium50.getFluid(144))
                .fluidInputs(Scandium.getFluid(144))
                .fluidOutputs(ScandiumTitanium50Mix.getFluid(288))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(450000)
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Radium.getFluid(144))
                .fluidOutputs(RadonRadiumMix.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(900000).coilTier(3).euStart(10000000000L).euReturn(50)
                .fluidInputs(ScandiumTitanium50Mix.getFluid(36))
                .fluidInputs(RadonRadiumMix.getFluid(144))
                .fluidOutputs(MetastableHassium.getFluid(144))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Americium.getFluid(144))
                .fluidInputs(Titanium.getFluid(144))
                .fluidOutputs(Tennessine.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Plutonium.getFluid(144))
                .fluidInputs(Titanium.getFluid(144))
                .fluidOutputs(Livermorium.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Neptunium.getMaterial().getFluid(144))
                .fluidInputs(Titanium.getFluid(144))
                .fluidOutputs(Moscovium.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Astatine.getFluid(144))
                .fluidInputs(Nickel.getFluid(144))
                .fluidOutputs(Nihonium.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Radium.getFluid(144))
                .fluidInputs(Vanadium.getFluid(144))
                .fluidOutputs(Roentgenium.getFluid(288))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40)
                .fluidInputs(Polonium.getFluid(144))
                .fluidInputs(Chrome.getFluid(144))
                .fluidOutputs(Meitnerium.getFluid(288))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(340).EUt(4500000)
                .fluidInputs(Deuterium.getFluid(2000))
                .fluidInputs(MetastableHassium.getFluid(144))
                .fluidInputs(MetastableFlerovium.getFluid(144))
                .fluidInputs(MetastableOganesson.getFluid(144))
                .fluidOutputs(DeuteriumSuperheavyMix.getFluid(2592))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(1100000).coilTier(3).euStart(18000000000L).euReturn(50)
                .fluidInputs(DeuteriumSuperheavyMix.getFluid(144))
                .fluidInputs(HeavyQuarkEnrichedMix.getFluid(144))
                .fluidOutputs(HeavyQuarkDegenerateMatter.getFluid(144))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7500000)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(3))
                .input(wireFine, HeavyQuarkDegenerateMatter, 6)
                .input(plate, HeavyQuarkDegenerateMatter, 2)
                .fluidInputs(CosmicComputingMix.getFluid(1000))
                .outputs(COSMIC_PROCESSING_UNIT_CORE.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(850000).blastFurnaceTemp(50000*3/4)
                .input(dustTiny, MetastableHassium)
                .fluidInputs(Nitrogen.getFluid(1000))
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(17000000).blastFurnaceTemp(50000)
                .input(dust, MetastableHassium, 2)
                .inputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(HASSIUM_BOULE.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(290).EUt(175000)
                .inputs(HASSIUM_BOULE.getStackForm())
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .outputs(HASSIUM_WAFER.getStackForm(8))
                .buildAndRegister();

        // 3HCl + Fl -> FlCl3 + 3H
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(75000)
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .input(dust, MetastableFlerovium)
                .fluidOutputs(Trichloroferane.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(345000)
                .inputs(HASSIUM_WAFER.getStackForm())
                .fluidInputs(Trichloroferane.getFluid(250))
                .outputs(COATED_HASSIUM_WAFER.getStackForm())
                .buildAndRegister();

        // C6H5F + 2Na -> C6H5Na + NaF
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(845000)
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .input(dust, Sodium, 2)
                .fluidOutputs(Phenylsodium.getFluid(1000))
                .output(dust, SodiumFluoride, 2)
                .buildAndRegister();

        // LiAlH4 + C4H6O4 -> C4H6O2 + Li + 2H2O + Al
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(750000)
                .inputs(LithiumAluminiumHydride.getItemStack(4))
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidOutputs(Succinaldehyde.getFluid(1000))
                .output(dust, Lithium)
                .output(dust, Aluminium)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2KF + H + C6H4Cl2 + N -> 2KCl + C6H5F2N
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(840000)
                .input(dust, PotassiumFluoride, 4)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Dichlorobenzene.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .notConsumable(AluminiumChloride.getItemStack())
                .output(dust, RockSalt, 4)
                .fluidOutputs(Difluoroaniline.getFluid(1000))
                .buildAndRegister();

        // C6H5F2N + C4H6O2 -> C10H7F2N + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(780000)
                .fluidInputs(Difluoroaniline.getFluid(1000))
                .fluidInputs(Succinaldehyde.getFluid(1000))
                .notConsumable(dust, PhosphorousPentoxide)
                .fluidOutputs(NDifluorophenylpyrrole.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2H2O + AgBF4 + 8C6H5Na + 2C8H20NBr + AgClO4 + C5H5Cl3Ti + 8C10H7F2N -> 2AgCl + 2NaBr + 6H2ONaCl + ?
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(3400000)
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(Silvertetrafluoroborate.getFluid(2000))
                .fluidInputs(Phenylsodium.getFluid(8000))
                .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
                .inputs(SilverPerchlorate.getItemStack(6))
                .inputs(TitaniumCyclopentadienyl.getItemStack(14))
                .fluidInputs(NDifluorophenylpyrrole.getFluid(8000))
                .output(dust, SilverChloride, 4)
                .outputs(SodiumBromide.getItemStack(4))
                .fluidOutputs(SaltWater.getFluid(6000))
                .fluidOutputs(PhotopolymerSolution.getFluid(6000))
                .buildAndRegister();

        // 3NaClO -> 2NaCl + NaClO3
        BLAST_RECIPES.recipeBuilder().duration(210).EUt(4000).blastFurnaceTemp(1100)
                .inputs(SodiumHypochlorite.getItemStack(9))
                .output(dust, Salt, 4)
                .outputs(SodiumChlorate.getItemStack(5))
                .buildAndRegister();

        // NaClO3 + H2O2 -> NaClO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(8750)
                .inputs(SodiumChlorate.getItemStack(5))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .outputs(SodiumPerchlorate.getItemStack(6))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // Ag2O + 2NaClO4 + HCl -> 2AgClO4 + Na2O + dil.HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(8750)
                .input(dust, SilverOxide, 3)
                .inputs(SodiumPerchlorate.getItemStack(12))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(SilverPerchlorate.getItemStack(12))
                .outputs(SodiumOxide.getItemStack(3))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2AgCl + H2O -> Ag2O + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(800)
                .input(dust, SilverChloride, 4)
                .notConsumable(dust, SodiumHydroxide)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Ag2O + C -> 2Ag + CO
        BLAST_RECIPES.recipeBuilder().duration(80).EUt(120).blastFurnaceTemp(1200)
                .input(dust, SilverOxide, 3)
                .input(dust, Carbon)
                .output(ingot, Silver, 2)
                .output(dustTiny, Ash, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(695000)
                .inputs(COATED_HASSIUM_WAFER.getStackForm())
                .fluidInputs(PhotopolymerSolution.getFluid(500))
                .outputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(840000)
                .input(craftingLens, MarkerMaterials.Color.White)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .outputs(GRATING_LITHOGRAPHY_MASK.getStackForm())
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(320).EUt(3200000)
                .inputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm())
                .notConsumable(GRATING_LITHOGRAPHY_MASK.getStackForm())
                .outputs(DIFFRACTOR_GRATING_MIRROR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(270).EUt(4500000)
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .inputs(DIFFRACTOR_GRATING_MIRROR.getStackForm(4))
                .inputs(NDYAG_ROD.getStackForm())
                .inputs(LUTMYVO_ROD.getStackForm())
                .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(2))
                .fluidInputs(SolderingAlloy.getFluid(384))
                .outputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .buildAndRegister();

        // Ge + 2S -> GeS2
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(9850)
                .input(dust, Germanium)
                .input(dust, Sulfur, 2)
                .outputs(GermaniumSulfide.getItemStack(3))
                .buildAndRegister();

        // GeS2 + 6O -> GeO2 + 2SO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(9750).blastFurnaceTemp(2500)
                .inputs(GermaniumSulfide.getItemStack(3))
                .fluidInputs(Oxygen.getFluid(6000))
                .outputs(GermaniumOxide.getItemStack(3))
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(380).EUt(500000)
                .fluidInputs(BismuthNitrateSoluton.getFluid(4000))
                .inputs(GermaniumOxide.getItemStack(3))
                .notConsumable(LOW_FREQUENCY_LASER.getStackForm())
                .outputs(BismuthGermanate.getItemStack(33))
                .output(dust, Potash, 18)
                .fluidOutputs(NitrogenDioxide.getFluid(12000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // WC + 4O -> WO3 + CO
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(8192)
                .input(dust, TungstenCarbide, 2)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, TungstenTrioxide, 4)
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // CdS + WO3 + 3O -> CdWO4 + SO2
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(845000).blastFurnaceTemp(2800)
                .inputs(CadmiumSulfide.getItemStack(2))
                .input(dust, TungstenTrioxide, 4)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(CadmiumTungstate.getItemStack(6))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(740000)
                .fluidInputs(Anthracene.getFluid(1000))
                .inputs(Tetracene.getItemStack(2))
                .outputs(PolycyclicAromaticMix.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(98500)
                .input(dust, Caesium)
                .input(dust, Iodine)
                .outputs(CesiumIodide.getItemStack(2))
                .buildAndRegister();

        // CsI + Tm + Tl -> CsITmTl
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(845000).blastFurnaceTemp(2853)
                .inputs(CesiumIodide.getItemStack())
                .input(dustSmall, Thulium, 2)
                .input(dustSmall, Thallium, 2)
                .outputs(TlTmCesiumIodide.getItemStack(2))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(280).EUt(1500000)
                .input(plate, Vibranium, 2)
                .inputs(TlTmCesiumIodide.getItemStack())
                .inputs(PolycyclicAromaticMix.getItemStack())
                .inputs(CadmiumTungstate.getItemStack())
                .inputs(BismuthGermanate.getItemStack())
                .outputs(SCINTILLATOR_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(3450000).blastFurnaceTemp(15000)
                .input(dust, MetastableHassium)
                .input(dust, Molybdenum)
                .input(dust, Rhenium)
                .fluidInputs(NaquadahAlloy.getFluid(144))
                .outputs(LEPTON_TRAP_CRYSTAL.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(380).EUt(3500000)
                .inputs(SCINTILLATOR_CRYSTAL.getStackForm())
                .input(screw, HDCS, 12)
                .input(plate, Cinobite, 4)
                .input(plate, Quantum, 4)
                .inputs(SEPARATION_ELECTROMAGNET.getStackForm())
                .input(foil, Zylon, 6)
                .input(wireFine, Cinobite, 12)
                .fluidInputs(TriniumTitanium.getFluid(432))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(SCINTILLATOR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, SuperheavyHAlloy, 4)
                .input(plate, Vibranium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_DIODE_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, MetastableHassium, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(foil, Quantum, 4)
                .input(foil, FullerenePolymerMatrix, 4)
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_CAPACITOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, SuperheavyLAlloy, 4)
                .input(plate, TriniumTitanium, 4)
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_RESISTOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(11796480).qubit(32)
                .inputs(COSMIC_PROCESSING_UNIT_CORE.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(2))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(2))
                .input(foil, FullerenePolymerMatrix, 2)
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .input(wireGtSingle, Cinobite, 8)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(plate,BlackTitanium,4)
                .fluidInputs(Zylon.getFluid(864))
                .fluidInputs(Quantum.getFluid(432))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(432))
                .outputs(COSMIC_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
    }
}
