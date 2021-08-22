package gregicadditions.recipes.categories.circuits;

import gregicadditions.GAMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitRecipes {

    // Organized by "Group" rather than by voltage
    public static void init() {
        biowareCircuits();
        opticalCircuits();
        exoticCircuits();
        cosmicCircuits();
        supracausalCircuits();

        CircuitComponentRecipes.init();
        WaferRecipes.init();
    }

    private static void biowareCircuits() {

        // Bioware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(240000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(8))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(4))
                .inputs(NEURO_PROCESSOR.getStackForm())
                .inputs(HASOC.getStackForm())
                .input(wireFine, NaquadahAlloy, 4)
                .outputs(BIOWARE_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Bioware Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(480000)
                .inputs(BIOWARE_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(16))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(16))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(16))
                .inputs(NEURO_PROCESSOR.getStackForm())
                .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 4)
                .inputs(ARAM.getStackForm(32))
                .input(plate, Duranium, 2)
                .input(foil, Polybenzimidazole, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .fluidInputs(Polyethylene.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Bioware Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(480000)
                .inputs(BIOWARE_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(32))
                .inputs(NEURO_PROCESSOR.getStackForm())
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 2)
                .inputs(UHPIC.getStackForm(16))
                .inputs(ARAM.getStackForm(64))
                .input(plate, Tritanium, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(2))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(L * 2))
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_COMPUTER.getStackForm())
                .buildAndRegister();

        // Bioware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(1920000)
                .inputs(BIOWARE_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(64))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(64))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(64))
                .inputs(NEURO_PROCESSOR.getStackForm(2))
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 6)
                .inputs(UHPIC.getStackForm(32))
                .input(plate, Adamantium, 2)
                .input(frameGt, Adamantium)
                .input(plate, Naquadria, 8)
                .input(foil, Polybenzimidazole, 64)
                .inputs(UNSTABLE_STAR.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polybenzimidazole.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(BIOWARE_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void opticalCircuits() {

        // Optical Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(960000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .inputs(HASOC.getStackForm())
                .input(wireFine, Pikyonium, 4)
                .outputs(OPTICAL_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Optical Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(960000)//.qubit(4)
                .inputs(OPTICAL_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(2))
                .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 4)
                .inputs(ARAM.getStackForm(32))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                .input(plate, HDCS, 2)
                .input(foil, Polyetheretherketone, 16)
                .fluidInputs(Duranium.getFluid(L * 9))
                .fluidInputs(Polytetrafluoroethylene.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(OPTICAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Optical Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1920000)//.qubit(4)
                .inputs(OPTICAL_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .inputs(OPTICAL_SOC.getStackForm())
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 2)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(16))
                .inputs(UHPIC.getStackForm(32))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .input(plate, Quantum, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(L * 2))
                .fluidInputs(Polyetheretherketone.getFluid(L * 9))
                .fluidInputs(Adamantium.getFluid(L * 9))
                .outputs(OPTICAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Optical Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(6000000)//.qubit(8)
                .inputs(OPTICAL_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .input(wireGtSingle, ActiniumVibraniumBETSSuperhydride, 6)
                .inputs(UHPIC.getStackForm(64))
                .inputs(UHASOC.getStackForm(64))
                .input(plate, EnrichedNaquadahAlloy, 4)
                .input(frameGt, Bohrium)
                .input(foil, Zylon, 64)
                .inputs(UNSTABLE_STAR.getStackForm(8))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(48))
                .fluidInputs(Cinobite.getFluid(L * 2))
                .fluidInputs(Vibranium.getFluid(L * 9))
                .fluidInputs(Polyetheretherketone.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(OPTICAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void exoticCircuits() {

            //Exotic Processor
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt((int)2E+6)//.qubit(8)
                    .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(8))
                    .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(8))
                    .inputs(EXOTIC_PROCESSING_CORE.getStackForm())
                    .input(wireFine, Cinobite, 4)
                    .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                    .inputs(HASOC.getStackForm(1))
                    .solderMultiplier(4)
                    .outputs(EXOTIC_PROCESSOR.getStackForm())
                    .buildAndRegister();

        //Exotic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt((int)2E+6)//.qubit(8)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(16))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(16))
                .inputs(EXOTIC_PROCESSOR.getStackForm(3))
                .input(wireFine, Cinobite, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .input(plate, EnrichedNaquadahAlloy, 2)
                .inputs(ARAM.getStackForm(2))
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 2)
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144))
                .fluidInputs(TriniumTitanium.getFluid(144))
                .fluidInputs(Quantum.getFluid(144))
                .fluidInputs(QuantumDots.getFluid(10))
                .outputs(EXOTIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        //Exotic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt((int)2E+6)//.qubit(8)
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(EXOTIC_ASSEMBLY.getStackForm(4))
                .input(wireFine, Quantum, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHPIC.getStackForm(4))
                .inputs(ARAM.getStackForm(8))
                .input(wireGtSingle, ActiniumVibraniumBETSSuperhydride, 4)
                .fluidInputs(Polyetheretherketone.getFluid(1000))
                .fluidInputs(Vibranium.getFluid(144))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144))
                .fluidInputs(TriniumTitanium.getFluid(144))
                .outputs(EXOTIC_COMPUTER.getStackForm())
                .buildAndRegister();

        //Exotic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt((int)1E+7)//.qubit(8)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(64))
                .inputs(EXOTIC_COMPUTER.getStackForm(2))
                .input(wireFine, Vibranium, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHASOC.getStackForm(4))
                .input(frameGt, TriniumTitanium)
                .inputs(UHPIC.getStackForm(2))
                .inputs(ARAM.getStackForm(16))
                .input(wireGtSingle, ProtoFullereneBorocarbide, 8)
                .fluidInputs(LiquidEnrichedHelium.getFluid(100))
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .fluidInputs(Quantum.getFluid(144))
                .fluidInputs(Naquadria.getFluid(144))
                .outputs(EXOTIC_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void cosmicCircuits() {

        // Cosmic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(1920000)//.qubit(16)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(16))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm())
                .inputs(UHASOC.getStackForm())
                .input(wireFine, Cinobite, 4)
                .outputs(COSMIC_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Cosmic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(3840000)//.qubit(16)
                .inputs(COSMIC_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(NEURO_PROCESSOR.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(2))
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 4)
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .input(plate, Quantum, 2)
                .input(foil, Zylon, 16)
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polyetheretherketone.getFluid(L * 15))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(COSMIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Cosmic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7680000)//.qubit(16)
                .inputs(COSMIC_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(10))
                .input(wireGtDouble, ActiniumVibraniumBETSSuperhydride, 2)
                .inputs(UHPIC.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(OPTICAL_SOC.getStackForm(32))
                .input(plate, Quantum, 16)
                .input(foil, FullerenePolymerMatrix, 12)
                .inputs(GRAVI_STAR.getStackForm(32))
                .fluidInputs(SterileGrowthMedium.getFluid(16000))
                .fluidInputs(Tritanium.getFluid(L * 2))
                .fluidInputs(Zylon.getFluid(L * 10))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(COSMIC_COMPUTER.getStackForm())
                .buildAndRegister();

        // Cosmic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(24000000)//.qubit(32)
                .inputs(COSMIC_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(4))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm(2))
                .input(wireGtQuadruple, ProtoFullereneBorocarbide, 6)
                .inputs(UHASOC.getStackForm(64))
                .inputs(UHPIC.getStackForm(64))
                .input(frameGt, Quantum)
                .input(plate, Quantum, 8)
                .input(foil, FullerenePolymerMatrix, 64)
                .inputs(UNSTABLE_STAR.getStackForm(4))
                .fluidInputs(Taranium.getFluid(L * 6))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(Zylon.getFluid(L * 18))
                .fluidInputs(Vibranium.getFluid(L * 9))
                .outputs(COSMIC_MAINFRAME.getStackForm())
                .buildAndRegister();
    }


    private static void supracausalCircuits() {

        // Supracausal Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2097152)//.qubit(32)
                .inputs(UHASOC.getStackForm(16))
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .input(plate, SuperheavyHAlloy, 4)
                .input(wireGtSingle, GAMaterials.StrontiumTaraniumTBCCO, 8)
                .outputs(SUPRACAUSAL_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Supracausal Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(8388608)//.qubit(8)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(UHASOC.getStackForm(4))
                .inputs(SUPRACAUSAL_PROCESSOR.getStackForm(3))
                .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .input(plate, TriniumTitanium, 16)
                .input(foil, FullerenePolymerMatrix, 24)
                .input(wireGtSingle, ActiniumVibraniumBETSSuperhydride, 8)
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Supracausal Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(33554432)//.qubit(16)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SUPRACAUSAL_ASSEMBLY.getStackForm(4))
                .inputs(ARAM.getStackForm(12))
                .inputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .input(plate, TriniumTitanium, 32)
                .input(plate, MetastableFlerovium, 16)
                .input(plate, Neutronium, 4)
                .input(wireGtSingle, ProtoFullereneBorocarbide, 64)
                .inputs(UHPIC.getStackForm(2))
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Supracausal Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(134217728)//.qubit(32)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SUPRACAUSAL_COMPUTER.getStackForm(2))
                .inputs(UHPIC.getStackForm(8))
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm(2))
                .inputs(ARAM.getStackForm(16))
                .inputs(CTC_COMPUTATIONAL_UNIT.getStackForm())
                .input(foil, FullerenePolymerMatrix, 48)
                .input(frameGt, Neutronium, 1)
                .input(wireGtSingle, SuperheavyChargedBlackTitanium, 16)
                .input(plate, MetastableOganesson, 4)
                .input(plate, QCDMatter, 8)
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }
}
