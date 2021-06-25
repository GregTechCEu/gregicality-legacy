package gregicadditions.recipes.categories.circuits;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipeByName;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitRecipes {

    // Organized by "Group" rather than by voltage
    public static void init() {

        removeGTCECircuitRecipes();

        primitiveCircuits();
        electronicCircuits();
        refinedCircuits();
        microCircuits();
        nanoCircuits();
        quantumCircuits();
        crystalCircuits();
        wetwareCircuits();
        biowareCircuits();
        opticalCircuits();
        exoticCircuits(); // TBD
        cosmicCircuits();
        supracausalCircuits();

        MagnetoRecipes.init();

        CircuitComponentRecipes.init();
        WaferRecipes.init();
    }

    private static void primitiveCircuits() {

        // Primitive Circuit (Integrated Logic Circuit in game)
        removeRecipeByName("gregtech:basic_circuit");
        ModHandler.addShapedRecipe("primitive_processor", BASIC_CIRCUIT_LV.getStackForm(),
                "RPR", "TBT", "CCC",
                'R', RESISTOR,
                'P', new UnificationEntry(plate, WroughtIron),
                'T', VACUUM_TUBE,
                'B', BASIC_BOARD,
                'C', new UnificationEntry(cableGtSingle, RedAlloy));

        // Primitive Assembly
        removeRecipeByName("gregtech:good_circuit");
        ModHandler.addShapedRecipe("primitive_assembly", PRIMITIVE_ASSEMBLY.getStackForm(),
                "PCT", "CDC", "TCP",
                'C', BASIC_CIRCUIT_LV,
                'P', new UnificationEntry(plate, WroughtIron),
                'D', DIODE,
                'T', new UnificationEntry(cableGtSingle, RedAlloy));
    }

    private static void electronicCircuits() {

        // Basic Electronic Circuit
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .inputs(RESISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, Copper, 4)
                .outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, Copper, 4)
                .outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm())
                .buildAndRegister();

        // Electronic Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3))
                .inputs(TRANSISTOR.getStackForm(2))
                .inputs(RESISTOR.getStackForm(8))
                .input(plate, Electrum)
                .outputs(ELECTRONIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm())
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .input(plate, Electrum)
                .outputs(ELECTRONIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Electronic Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(ELECTRONIC_ASSEMBLY.getStackForm(4))
                .inputs(CAPACITOR.getStackForm(4))
                .inputs(RESISTOR.getStackForm(4))
                .inputs(INTEGRATED_LOGIC_CIRCUIT.getStackForm(2))
                .input(plate, Aluminium, 2)
                .input(wireGtSingle, AnnealedCopper, 4)
                .outputs(ELECTRONIC_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(ELECTRONIC_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(2))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(INTEGRATED_LOGIC_CIRCUIT.getStackForm(2))
                .input(plate, Aluminium, 2)
                .input(wireGtSingle, AnnealedCopper, 4)
                .outputs(ELECTRONIC_COMPUTER.getStackForm())
                .buildAndRegister();
    }

    private static void refinedCircuits() {

        // Refined Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, TinAlloy, 2)
                .outputs(REFINED_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, TinAlloy, 2)
                .outputs(REFINED_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // SoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600)
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, TinAlloy, 8)
                .outputs(REFINED_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // Refined Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60)
                .inputs(REFINED_PROCESSOR.getStackForm(3))
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .input(plate, StainlessSteel)
                .outputs(REFINED_ASSEMBLY.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60)
                .inputs(REFINED_PROCESSOR.getStackForm(3))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(2))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(2))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .input(plate, StainlessSteel)
                .outputs(REFINED_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Refined Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90)
                .inputs(REFINED_ASSEMBLY.getStackForm(4))
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .input(wireGtSingle, MVSuperconductor)
                .outputs(REFINED_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90)
                .inputs(REFINED_ASSEMBLY.getStackForm(4))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(2))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(GOOD_PLASTIC_BOARD.getStackForm())
                .input(wireGtSingle, MVSuperconductor)
                .outputs(REFINED_COMPUTER.getStackForm())
                .buildAndRegister();

        // Refined Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110)
                .inputs(REFINED_COMPUTER.getStackForm(2))
                .inputs(RESISTOR.getStackForm(32))
                .inputs(TRANSISTOR.getStackForm(16))
                .inputs(DIODE.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(frameGt, StainlessSteel, 4)
                .outputs(REFINED_MAINFRAME.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110)
                .inputs(REFINED_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(16))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(8))
                .inputs(SMD_DIODE_REFINED.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(frameGt, StainlessSteel, 4)
                .outputs(REFINED_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void microCircuits() {

        // Micro Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400)
                .inputs(SMD_RESISTOR_REFINED.getStackForm(8))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(8))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(8))
                .inputs(ADVANCED_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .input(wireFine, RedAlloy, 2)
                .outputs(MICRO_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400)
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(SMD_TRANSISTOR.getStackForm(4))
                .inputs(SMD_CAPACITOR.getStackForm(4))
                .inputs(ADVANCED_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .input(wireFine, RedAlloy, 2)
                .outputs(MICRO_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // SoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400)
                .inputs(ADVANCED_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, RedAlloy, 8)
                .outputs(MICRO_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // Micro Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350)
                .inputs(MICRO_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(ADVANCED_BOARD.getStackForm())
                .input(plate, Titanium)
                .outputs(PROCESSOR_ASSEMBLY_HV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350)
                .inputs(MICRO_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR.getStackForm(2))
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(ADVANCED_BOARD.getStackForm())
                .input(plate, Titanium)
                .outputs(PROCESSOR_ASSEMBLY_HV.getStackForm())
                .buildAndRegister();

        // Micro Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425)
                .inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(8))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(ADVANCED_BOARD.getStackForm())
                .input(wireGtSingle, HVSuperconductor)
                .outputs(MICRO_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425)
                .inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4))
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(SMD_TRANSISTOR.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(ADVANCED_BOARD.getStackForm())
                .input(wireGtSingle, HVSuperconductor)
                .outputs(MICRO_COMPUTER.getStackForm())
                .buildAndRegister();

        // Micro Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500)
                .inputs(MICRO_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(40))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(20))
                .inputs(SMD_DIODE_REFINED.getStackForm(10))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .input(frameGt, Titanium, 4)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500)
                .inputs(MICRO_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR.getStackForm(20))
                .inputs(SMD_TRANSISTOR.getStackForm(10))
                .inputs(SMD_DIODE.getStackForm(5))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .input(frameGt, Titanium, 4)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void nanoCircuits() {

        // Nano Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .inputs(SMD_RESISTOR.getStackForm(8))
                .inputs(SMD_TRANSISTOR.getStackForm(8))
                .inputs(SMD_CAPACITOR.getStackForm(8))
                .inputs(EXTREME_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12))
                .input(wireFine, Aluminium, 2)
                .outputs(NANO_PROCESSOR_HV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .outputs(NANO_PROCESSOR_HV.getStackForm(4))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(4))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(4))
                .inputs(EXTREME_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12))
                .input(wireFine, Aluminium, 2)
                .buildAndRegister();

        // SoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600)
                .inputs(EXTREME_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, Aluminium, 8)
                .outputs(NANO_PROCESSOR_HV.getStackForm(4))
                .buildAndRegister();

        // Nano Processor Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm())
                .inputs(NANO_PROCESSOR_HV.getStackForm(3))
                .inputs(SMD_CAPACITOR.getStackForm(8))
                .inputs(SMD_RESISTOR.getStackForm(8))
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(EXTREME_BOARD.getStackForm()).input(plate, TungstenSteel)
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .inputs(NANO_PROCESSOR_HV.getStackForm(3))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(4))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(EXTREME_BOARD.getStackForm()).input(plate, TungstenSteel)
                .outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm())
                .buildAndRegister();

        // Nano Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000)
                .inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4))
                .inputs(SMD_RESISTOR.getStackForm(8))
                .inputs(SMD_TRANSISTOR.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(EXTREME_BOARD.getStackForm())
                .input(wireGtSingle, EVSuperconductor)
                .outputs(NANO_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000)
                .outputs(NANO_COMPUTER.getStackForm())
                .inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(EXTREME_BOARD.getStackForm())
                .input(wireGtSingle, EVSuperconductor)
                .buildAndRegister();

        // Nano Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000)
                .inputs(NANO_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR.getStackForm(48))
                .inputs(SMD_TRANSISTOR.getStackForm(24))
                .inputs(SMD_DIODE.getStackForm(12))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(12))
                .input(frameGt, TungstenSteel, 4)
                .outputs(NANO_MAINFRAME.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000)
                .inputs(NANO_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_NANO.getStackForm(24))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(12))
                .inputs(SMD_DIODE_NANO.getStackForm(6))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(12))
                .input(frameGt, TungstenSteel, 4)
                .outputs(NANO_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void quantumCircuits() {

        // Quantum Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(8))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(8))
                .inputs(ELITE_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, Platinum, 2)
                .outputs(QUANTUM_PROCESSOR_EV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(4))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(4))
                .inputs(ELITE_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, Platinum, 2)
                .outputs(QUANTUM_PROCESSOR_EV.getStackForm(4))
                .buildAndRegister();

        // ASoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(36000)
                .inputs(ELITE_BOARD.getStackForm())
                .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, Platinum, 8)
                .outputs(QUANTUM_PROCESSOR_EV.getStackForm(4))
                .buildAndRegister();


        // Quantum Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000)
                .inputs(QUANTUM_PROCESSOR_EV.getStackForm(3))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(8))
                .inputs(SMD_RESISTOR_NANO.getStackForm(8))
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(ELITE_BOARD.getStackForm())
                .input(plate, Osmium)
                .outputs(QUANTUM_ASSEMBLY.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000)
                .inputs(QUANTUM_PROCESSOR_EV.getStackForm(3))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(4))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(4))
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(ELITE_BOARD.getStackForm())
                .input(plate, Osmium)
                .outputs(QUANTUM_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Quantum Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000)
                .inputs(QUANTUM_ASSEMBLY.getStackForm(4))
                .inputs(SMD_DIODE_NANO.getStackForm(16))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .inputs(ELITE_BOARD.getStackForm())
                .input(wireGtSingle, IVSuperconductor, 2)
                .outputs(QUANTUM_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000)
                .inputs(QUANTUM_ASSEMBLY.getStackForm(4))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(8))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .inputs(ELITE_BOARD.getStackForm())
                .input(wireGtSingle, IVSuperconductor, 2)
                .outputs(QUANTUM_COMPUTER.getStackForm())
                .buildAndRegister();

        // Quantum Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000)
                .inputs(QUANTUM_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_NANO.getStackForm(64))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(56))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(56))
                .inputs(SMD_DIODE_NANO.getStackForm(32))
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(QUANTUM_STAR.getStackForm())
                .input(frameGt, TungstenSteel, 4)
                .input(wireGtSingle, IVSuperconductor, 16)
                .outputs(QUANTUM_MAINFRAME.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000)
                .inputs(QUANTUM_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(32))
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(28))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(28))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(16))
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(QUANTUM_STAR.getStackForm())
                .input(frameGt, TungstenSteel, 4)
                .input(wireGtSingle, IVSuperconductor, 16)
                .outputs(QUANTUM_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void crystalCircuits() {

        // Crystal Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000)
                .inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(16))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(8))
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, NiobiumTitanium, 2)
                .outputs(CRYSTAL_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000)
                .inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(4))
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, NiobiumTitanium, 2)
                .outputs(CRYSTAL_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // Crystal SoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(86000)
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, NiobiumTitanium, 8)
                .outputs(CRYSTAL_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        // Crystal Processor Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000)
                .inputs(CRYSTAL_PROCESSOR.getStackForm(3))
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(64))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(8))
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, LuVSuperconductor, 4)
                .outputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000)
                .inputs(CRYSTAL_PROCESSOR.getStackForm(3))
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(64))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(4))
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, LuVSuperconductor, 4)
                .outputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm())
                .buildAndRegister();

        // Crystal Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000)
                .inputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm(4))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(16))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(16))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm())
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(plate, RhodiumPlatedPalladium, 2)
                .input(wireGtSingle, LuVSuperconductor, 16)
                .outputs(CRYSTAL_COMPUTER.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000)
                .inputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm(4))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(8))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(8))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm())
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(plate, RhodiumPlatedPalladium, 2)
                .input(wireGtSingle, LuVSuperconductor, 16)
                .outputs(CRYSTAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Crystal Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(30000)
                .inputs(CRYSTAL_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(64))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(64))
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(64))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(64))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(48))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .input(frameGt, HSSE, 4)
                .input(wireGtSingle, LuVSuperconductor, 32)
                .outputs(CRYSTAL_MAINFRAME.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(30000)
                .inputs(CRYSTAL_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(48))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(36))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(24))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .input(frameGt, HSSE, 4)
                .input(wireGtSingle, LuVSuperconductor, 32)
                .outputs(CRYSTAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void wetwareCircuits() {

        // Wetware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(56000)
                .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(16))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(8))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, YttriumBariumCuprate, 2)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(56000)
                .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(8))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, YttriumBariumCuprate, 2)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm())
                .buildAndRegister();

        // ASoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(120000)
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm(4))
                .input(wireFine, NaquadahAlloy, 8)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm(4))
                .buildAndRegister();

        // Wetware Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000)
                .inputs(WETWARE_PROCESSOR_LUV.getStackForm(3))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(32))
                .inputs(ARAM.getStackForm(4))
                .inputs(QUANTUM_EYE.getStackForm(4))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .input(wireGtSingle, ZPMSuperconductor, 4)
                .input(foil, SiliconeRubber, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000)
                .inputs(WETWARE_PROCESSOR_LUV.getStackForm(3))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(16))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(16))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(16))
                .inputs(SMD_DIODE_WETWARE.getStackForm(16))
                .inputs(ARAM.getStackForm(4))
                .inputs(QUANTUM_EYE.getStackForm(4))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .input(wireGtSingle, ZPMSuperconductor, 4)
                .input(foil, SiliconeRubber, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .buildAndRegister();

        // Wetware Supercomputer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(64))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .input(plate, Rutherfordium, 2)
                .input(wireGtSingle, ZPMSuperconductor, 16)
                .input(foil, SiliconeRubber, 32)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(32))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(32))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(32))
                .inputs(SMD_DIODE_WETWARE.getStackForm(32))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .input(plate, Rutherfordium, 2)
                .input(wireGtSingle, ZPMSuperconductor, 16)
                .input(foil, SiliconeRubber, 32)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm())
                .buildAndRegister();

        // Wetware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000)
                .inputs(WETWARE_SUPER_COMPUTER_UV.getStackForm(2))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(64))
                .inputs(SMD_DIODE_WETWARE.getStackForm(64))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(GRAVI_STAR.getStackForm(4))
                .input(frameGt, Tritanium, 4)
                .input(plate, Duranium, 32)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(foil, Polytetrafluoroethylene, 64)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .fluidInputs(UUMatter.getFluid(1000))
                .outputs(WETWARE_MAINFRAME_MAX.getStackForm())
                .buildAndRegister();
    }

    private static void biowareCircuits() {

        // Bioware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(240000)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
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
                .input(wireGtSingle, UVSuperconductor, 4)
                .inputs(ARAM.getStackForm(32))
                .input(plate, Duranium, 2)
                .input(foil, Polybenzimidazole, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .fluidInputs(Plastic.getFluid(L * 18))
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
                .input(wireGtSingle, UHVSuperconductor, 2)
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
                .input(wireGtSingle, UHVSuperconductor, 6)
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
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .inputs(HASOC.getStackForm())
                .input(wireFine, Pikyonium, 4)
                .outputs(OPTICAL_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Optical Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(960000).qubit(4)
                .inputs(OPTICAL_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(2))
                .input(wireGtSingle, UVSuperconductor, 4)
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
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1920000).qubit(4)
                .inputs(OPTICAL_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .inputs(OPTICAL_SOC.getStackForm())
                .input(wireGtSingle, UHVSuperconductor, 2)
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
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(6000000).qubit(8)
                .inputs(OPTICAL_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .input(wireGtSingle, UEVSuperconductor, 6)
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
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt((int)2E+6).qubit(8)
                    .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(8))
                    .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(8))
                    .inputs(EXOTIC_PROCESSING_CORE.getStackForm())
                    .input(wireFine, Cinobite, 4)
                    .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                    .inputs(HASOC.getStackForm(1))
                    .solderMultiplier(4)
                    .outputs(EXOTIC_PROCESSOR.getStackForm())
                    .buildAndRegister();

        //Exotic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt((int)2E+6).qubit(8)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(16))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(16))
                .inputs(EXOTIC_PROCESSOR.getStackForm(3))
                .input(wireFine, Cinobite, 4)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .input(plate, EnrichedNaquadahAlloy, 2)
                .inputs(ARAM.getStackForm(2))
                .input(wireGtSingle, UHVSuperconductor, 2)
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144))
                .fluidInputs(TriniumTitanium.getFluid(144))
                .fluidInputs(Quantum.getFluid(144))
                .fluidInputs(QuantumDots.getFluid(10))
                .outputs(EXOTIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        //Exotic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt((int)2E+6).qubit(8)
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(EXOTIC_ASSEMBLY.getStackForm(4))
                .input(wireFine, Quantum, 4)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHPIC.getStackForm(4))
                .inputs(ARAM.getStackForm(8))
                .input(wireGtSingle, UEVSuperconductor, 4)
                .fluidInputs(Polyetheretherketone.getFluid(1000))
                .fluidInputs(Vibranium.getFluid(144))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144))
                .fluidInputs(TriniumTitanium.getFluid(144))
                .outputs(EXOTIC_COMPUTER.getStackForm())
                .buildAndRegister();

        //Exotic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt((int)1E+7).qubit(8)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(64))
                .inputs(EXOTIC_COMPUTER.getStackForm(2))
                .input(wireFine, Vibranium, 4)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHASOC.getStackForm(4))
                .input(frameGt, TriniumTitanium)
                .inputs(UHPIC.getStackForm(2))
                .inputs(ARAM.getStackForm(16))
                .input(wireGtSingle, UIVSuperconductor, 8)
                .fluidInputs(LiquidEnrichedHelium.getFluid(100))
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .fluidInputs(Quantum.getFluid(144))
                .fluidInputs(Naquadria.getFluid(144))
                .outputs(EXOTIC_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void cosmicCircuits() {

        // Cosmic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(1920000).qubit(16)
                .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(16))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm())
                .inputs(UHASOC.getStackForm())
                .input(wireFine, Cinobite, 4)
                .outputs(COSMIC_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Cosmic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(3840000).qubit(16)
                .inputs(COSMIC_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(NEURO_PROCESSOR.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(2))
                .input(wireGtSingle, UHVSuperconductor, 4)
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
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7680000).qubit(16)
                .inputs(COSMIC_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(10))
                .input(wireGtDouble, UEVSuperconductor, 2)
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
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(24000000).qubit(32)
                .inputs(COSMIC_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm(4))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm(2))
                .input(wireGtQuadruple, UIVSuperconductor, 6)
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
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2097152).qubit(32)
                .inputs(UHASOC.getStackForm(16))
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .input(plate, SuperheavyHAlloy, 4)
                .input(wireGtSingle, UHVSuperconductor, 8)
                .outputs(SUPRACAUSAL_PROCESSOR.getStackForm())
                .solderMultiplier(4)
                .buildAndRegister();

        // Supracausal Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(8388608).qubit(8)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(UHASOC.getStackForm(4))
                .inputs(SUPRACAUSAL_PROCESSOR.getStackForm(3))
                .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .input(plate, TriniumTitanium, 16)
                .input(foil, FullerenePolymerMatrix, 24)
                .input(wireGtSingle, UEVSuperconductor, 8)
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Supracausal Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(33554432).qubit(16)
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
                .input(wireGtSingle, UIVSuperconductor, 64)
                .inputs(UHPIC.getStackForm(2))
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Supracausal Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(134217728).qubit(32)
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
                .input(wireGtSingle, UMVSuperconductor, 16)
                .input(plate, MetastableOganesson, 4)
                .input(plate, QCDMatter, 8)
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void removeGTCECircuitRecipes() {

        //Remove ALL GTCE Circuit recipes
        for (MaterialStack stack : new MaterialStack[]{new MaterialStack(Tin, 2L), new MaterialStack(SolderingAlloy, 1L)}) {

            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;

            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PHENOLIC_BOARD.getStackForm(), INTEGRATED_LOGIC_CIRCUIT.getStackForm(), RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PHENOLIC_BOARD.getStackForm(), INTEGRATED_LOGIC_CIRCUIT.getStackForm(), SMD_RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(4), RESISTOR.getStackForm(4), CAPACITOR.getStackForm(4), TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(4), SMD_RESISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PHENOLIC_BOARD.getStackForm(), BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PHENOLIC_BOARD.getStackForm(), BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), SMD_RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(), RESISTOR.getStackForm(2), CAPACITOR.getStackForm(2), TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(), SMD_RESISTOR.getStackForm(2), SMD_CAPACITOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), ADVANCED_CIRCUIT_MV.getStackForm(2), SMALL_COIL.getStackForm(4), CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), ADVANCED_CIRCUIT_MV.getStackForm(2), SMALL_COIL.getStackForm(4), SMD_CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{PLASTIC_BOARD.getStackForm(), ADVANCED_CIRCUIT_MV.getStackForm(), NAND_MEMORY_CHIP.getStackForm(32), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 8), OreDictUnifier.get(plate, Plastic, 4)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{EPOXY_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), SMD_RESISTOR.getStackForm(2), SMD_CAPACITOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Electrum, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{EPOXY_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Electrum, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{EPOXY_BOARD.getStackForm(), NANO_PROCESSOR_HV.getStackForm(2), SMALL_COIL.getStackForm(4), SMD_CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{FIBER_BOARD.getStackForm(), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), SMD_CAPACITOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Platinum, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{FIBER_BOARD.getStackForm(), ADVANCED_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Platinum, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{FIBER_BOARD.getStackForm(), QUANTUM_PROCESSOR_EV.getStackForm(2), SMALL_COIL.getStackForm(4), SMD_CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Platinum, 6)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MULTILAYER_FIBER_BOARD.getStackForm(), CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), SMD_CAPACITOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MULTILAYER_FIBER_BOARD.getStackForm(), CRYSTAL_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MULTILAYER_FIBER_BOARD.getStackForm(), CRYSTAL_PROCESSOR_IV.getStackForm(2), SMALL_COIL.getStackForm(4), SMD_CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, NiobiumTitanium, 6)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{WETWARE_BOARD.getStackForm(), CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), SMD_CAPACITOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 2)}, new FluidStack[]{material.getFluid(L / 2 * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{WETWARE_BOARD.getStackForm(), WETWARE_PROCESSOR_LUV.getStackForm(2), SMALL_COIL.getStackForm(4), SMD_CAPACITOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)}, new FluidStack[]{material.getFluid(L * multiplier)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{WETWARE_BOARD.getStackForm(2), WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(3), SMD_DIODE.getStackForm(4), NOR_MEMORY_CHIP.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)}, new FluidStack[]{material.getFluid(L * multiplier)});
        }
    }
}
