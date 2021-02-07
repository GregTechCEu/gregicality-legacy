package gregicadditions.recipes.chain;

import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregicadditions.item.fusion.GAFusionCasing;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import net.minecraft.item.ItemStack;

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

        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Americium.getFluid(144), Titanium.getFluid(144)).fluidOutputs(Tennessine.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Plutonium.getFluid(144), Titanium.getFluid(144)).fluidOutputs(Livermorium.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Neptunium.getMaterial().getFluid(144), Titanium.getFluid(144)).fluidOutputs(Moscovium.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Astatine.getFluid(144), Nickel.getFluid(144)).fluidOutputs(Nihonium.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Radium.getFluid(144), Vanadium.getFluid(144)).fluidOutputs(Roentgenium.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Polonium.getFluid(144), Chrome.getFluid(144)).fluidOutputs(Meitnerium.getFluid(288)).duration(100).EUt(75000).coilTier(2).euStart(2080000000).euReturn(40).buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(340).EUt(4500000)
                .fluidInputs(Deuterium.getFluid(2000))
                .fluidInputs(MetastableHassium.getFluid(144))
                .fluidInputs(MetastableFlerovium.getFluid(144))
                .fluidInputs(MetastableOganesson.getFluid(144))
                .fluidOutputs(DeuteriumSuperheavyMix.getFluid(576))
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

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(845000)
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .input(dust, Sodium, 2)
                .fluidOutputs(Phenylsodium.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 2))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(750000)
                .inputs(LithiumAluminiumHydride.getItemStack(4))
                .inputs(SuccinicAcid.getItemStack())
                .fluidOutputs(Succinaldehyde.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Lithium, 4))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(840000)
                .input(dust, PotassiumFluoride, 4)
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidInputs(Dichlorobenzene.getFluid(1000))
                .notConsumable(AluminiumChloride.getItemStack())
                .outputs(OreDictUnifier.get(dust, RockSalt, 4))
                .fluidOutputs(Difluoroaniline.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(780000)
                .fluidInputs(Difluoroaniline.getFluid(1000))
                .fluidInputs(Succinaldehyde.getFluid(1000))
                .notConsumable(dust, PhosphorousPentoxide)
                .fluidOutputs(NDifluorophenylpyrrole.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(3400000)
                .fluidInputs(Silvertetrafluoroborate.getFluid(2000))
                .fluidInputs(Phenylsodium.getFluid(8000))
                .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
                .inputs(TitaniumCyclopentanyl.getItemStack(3))
                .fluidInputs(NDifluorophenylpyrrole.getFluid(6000))
                .outputs(SilverFluoride.getItemStack(2))
                .outputs(SodiumBromide.getItemStack(2))
                .fluidOutputs(SodiumChlorideSolution.getFluid(6000))
                .fluidOutputs(Fluorine.getFluid(6000))
                .fluidOutputs(PhotopolymerSolution.getFluid(5000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(8750).blastFurnaceTemp(3200)
                .inputs(SilverFluoride.getItemStack())
                .input(dust, SodiumHydroxide, 2)
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 2))
                .outputs(OreDictUnifier.get(dust, SilverOxide))
                .fluidOutputs(Hydrogen.getFluid(2000))
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

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(9850)
                .input(dust, Germanium)
                .input(dust, Sulfur, 2)
                .outputs(GermaniumSulfide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(9750).blastFurnaceTemp(2500)
                .inputs(GermaniumSulfide.getItemStack())
                .fluidInputs(Oxygen.getFluid(4000))
                .outputs(GermaniumOxide.getItemStack())
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(380).EUt(500000)
                .fluidInputs(BismuthNitrateSoluton.getFluid(4000))
                .inputs(GermaniumOxide.getItemStack(3))
                .notConsumable(LOW_FREQUENCY_LASER.getStackForm())
                .outputs(BismuthGermanate.getItemStack(3))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(845000).blastFurnaceTemp(2800)
                .inputs(CadmiumSulfide.getItemStack())
                .input(dust, TungstenTrioxide)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(CadmiumTungstate.getItemStack())
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
                .outputs(CesiumIodide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(845000)
                .inputs(CesiumIodide.getItemStack(5))
                .input(dustSmall, Thulium, 2)
                .input(dustSmall, Thallium, 2)
                .outputs(TlTmCesiumIodide.getItemStack(6))
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
                .outputs(SMD_RESISTOR_COSMIC.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(11796480).qubit(32)
                .inputs(COSMIC_PROCESSING_UNIT_CORE.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(2))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(2))
                .input(foil, FullerenePolymerMatrix, 2)
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .inputs(NEURO_PROCESSOR.getStackForm())
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
