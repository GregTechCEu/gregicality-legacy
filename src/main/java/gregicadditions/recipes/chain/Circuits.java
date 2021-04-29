package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregicadditions.Gregicality;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class Circuits {
    public static void init() {

        MaterialStack[] sawLubricants = {
                new MaterialStack(Lubricant, 1L),
                new MaterialStack(DistilledWater, 3L),
                new MaterialStack(Water, 4L)
        };

        //Circuit Rabbit Hole - Layer 1
        ModHandler.removeRecipes(BASIC_CIRCUIT_LV.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:good_circuit"));
        ModHandler.addShapedRecipe("primitive_processor", BASIC_CIRCUIT_LV.getStackForm(), "RPR", "TBT", "CCC", 'R', RESISTOR, 'P', new UnificationEntry(plate, WroughtIron), 'T', VACUUM_TUBE, 'B', BASIC_BOARD, 'C', new UnificationEntry(cableGtSingle, RedAlloy));
        ModHandler.addShapedRecipe("primitive_assembly", PRIMITIVE_ASSEMBLY.getStackForm(), "PCT", "CDC", "TCP", 'C', BASIC_CIRCUIT_LV, 'P', new UnificationEntry(plate, WroughtIron), 'D', DIODE, 'T', new UnificationEntry(cableGtSingle, RedAlloy));

        for (String fluid : GAConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = Integer.parseInt(fluidSplit[1]);
            if (amount > 64000) {
                amount = 64000;
            }
            if (amount < 1) {
                amount = 1;
            }
            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);
            if (fluidStack == null) continue;

            //ELECTRONICS   //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16).outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).inputs(RESISTOR.getStackForm(8), CAPACITOR.getStackForm(8), GOOD_PHENOLIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, Copper, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16).outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).inputs(SMD_RESISTOR_REFINED.getStackForm(4), SMD_CAPACITOR_REFINED.getStackForm(4), GOOD_PHENOLIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, Copper, 4).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_ASSEMBLY.getStackForm()).inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), TRANSISTOR.getStackForm(2), RESISTOR.getStackForm(8)).input(plate, Electrum, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_ASSEMBLY.getStackForm()).inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), SMD_TRANSISTOR_REFINED.getStackForm(1), SMD_RESISTOR_REFINED.getStackForm(4)).input(plate, Electrum, 1).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_COMPUTER.getStackForm()).inputs(ELECTRONIC_ASSEMBLY.getStackForm(4), CAPACITOR.getStackForm(4), RESISTOR.getStackForm(4), INTEGRATED_LOGIC_CIRCUIT.getStackForm(2)).input(plate, Aluminium, 2).input(wireGtSingle, AnnealedCopper, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_COMPUTER.getStackForm()).inputs(ELECTRONIC_ASSEMBLY.getStackForm(4), SMD_CAPACITOR_REFINED.getStackForm(2), SMD_RESISTOR_REFINED.getStackForm(2), INTEGRATED_LOGIC_CIRCUIT.getStackForm(2)).input(plate, Aluminium, 2).input(wireGtSingle, AnnealedCopper, 4).fluidInputs(fluidStack).buildAndRegister();


            //REFINED       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(RESISTOR.getStackForm(8), TRANSISTOR.getStackForm(8), CAPACITOR.getStackForm(8), GOOD_PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(SMD_RESISTOR_REFINED.getStackForm(4), SMD_TRANSISTOR_REFINED.getStackForm(4), SMD_CAPACITOR_REFINED.getStackForm(4), GOOD_PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(GOOD_PLASTIC_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60).outputs(REFINED_ASSEMBLY.getStackForm()).inputs(REFINED_PROCESSOR.getStackForm(3), RESISTOR.getStackForm(8), TRANSISTOR.getStackForm(8), CAPACITOR.getStackForm(8), GOOD_PLASTIC_BOARD.getStackForm()).input(plate, StainlessSteel, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60).outputs(REFINED_ASSEMBLY.getStackForm()).inputs(REFINED_PROCESSOR.getStackForm(3), SMD_RESISTOR_REFINED.getStackForm(2), SMD_TRANSISTOR_REFINED.getStackForm(2), SMD_CAPACITOR_REFINED.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(plate, StainlessSteel, 1).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90).outputs(REFINED_COMPUTER.getStackForm()).inputs(REFINED_ASSEMBLY.getStackForm(4), RESISTOR.getStackForm(8), TRANSISTOR.getStackForm(8), RANDOM_ACCESS_MEMORY.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(wireGtSingle, MVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90).outputs(REFINED_COMPUTER.getStackForm()).inputs(REFINED_ASSEMBLY.getStackForm(4), SMD_RESISTOR_REFINED.getStackForm(2), SMD_TRANSISTOR_REFINED.getStackForm(2), RANDOM_ACCESS_MEMORY.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(wireGtSingle, MVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110).outputs(REFINED_MAINFRAME.getStackForm()).inputs(REFINED_COMPUTER.getStackForm(2), RESISTOR.getStackForm(32), TRANSISTOR.getStackForm(16), DIODE.getStackForm(8), RANDOM_ACCESS_MEMORY.getStackForm(4)).input(frameGt, StainlessSteel, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110).outputs(REFINED_MAINFRAME.getStackForm()).inputs(REFINED_COMPUTER.getStackForm(2), SMD_RESISTOR_REFINED.getStackForm(16), SMD_TRANSISTOR_REFINED.getStackForm(8), SMD_DIODE_REFINED.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4)).input(frameGt, StainlessSteel, 4).fluidInputs(fluidStack).buildAndRegister();


            //MICRO       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(SMD_RESISTOR_REFINED.getStackForm(8), SMD_TRANSISTOR_REFINED.getStackForm(8), SMD_CAPACITOR_REFINED.getStackForm(8), ADVANCED_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(2)).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), ADVANCED_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(2)).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(ADVANCED_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350).outputs(PROCESSOR_ASSEMBLY_HV.getStackForm()).inputs(MICRO_PROCESSOR.getStackForm(3), SMD_CAPACITOR_REFINED.getStackForm(4), SMD_RESISTOR_REFINED.getStackForm(8), RANDOM_ACCESS_MEMORY.getStackForm(2), ADVANCED_BOARD.getStackForm()).input(plate, Titanium, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350).outputs(PROCESSOR_ASSEMBLY_HV.getStackForm()).inputs(MICRO_PROCESSOR.getStackForm(3), SMD_CAPACITOR.getStackForm(2), SMD_RESISTOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(2), ADVANCED_BOARD.getStackForm()).input(plate, Titanium, 1).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425).outputs(MICRO_COMPUTER.getStackForm()).inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4), SMD_RESISTOR_REFINED.getStackForm(8), SMD_TRANSISTOR_REFINED.getStackForm(8), RANDOM_ACCESS_MEMORY.getStackForm(8), ADVANCED_BOARD.getStackForm()).input(wireGtSingle, HVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425).outputs(MICRO_COMPUTER.getStackForm()).inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4), SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(8), ADVANCED_BOARD.getStackForm()).input(wireGtSingle, HVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500).outputs(MICRO_MAINFRAME.getStackForm()).inputs(MICRO_COMPUTER.getStackForm(2), SMD_RESISTOR_REFINED.getStackForm(40), SMD_TRANSISTOR_REFINED.getStackForm(20), SMD_DIODE_REFINED.getStackForm(10), RANDOM_ACCESS_MEMORY.getStackForm(8)).input(frameGt, Titanium, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500).outputs(MICRO_MAINFRAME.getStackForm()).inputs(MICRO_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(20), SMD_TRANSISTOR.getStackForm(10), SMD_DIODE.getStackForm(5), RANDOM_ACCESS_MEMORY.getStackForm(8)).input(frameGt, Titanium, 4).fluidInputs(fluidStack).buildAndRegister();


            //NANO       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_HV.getStackForm(4)).inputs(SMD_RESISTOR.getStackForm(8), SMD_TRANSISTOR.getStackForm(8), SMD_CAPACITOR.getStackForm(8), EXTREME_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12)).input(wireFine, Aluminium, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_HV.getStackForm(4)).inputs(SMD_RESISTOR_NANO.getStackForm(4), SMD_TRANSISTOR_NANO.getStackForm(4), SMD_CAPACITOR_NANO.getStackForm(4), EXTREME_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12)).input(wireFine, Aluminium, 2).fluidInputs(fluidStack).buildAndRegister();
            //CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600).outputs(NANO_PROCESSOR_HV.getStackForm(4)).inputs(EXTREME_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, Aluminium, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm()).inputs(NANO_PROCESSOR_HV.getStackForm(3), SMD_CAPACITOR.getStackForm(8), SMD_RESISTOR.getStackForm(8), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2), EXTREME_BOARD.getStackForm()).input(plate, TungstenSteel, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm()).inputs(NANO_PROCESSOR_HV.getStackForm(3), SMD_CAPACITOR_NANO.getStackForm(4), SMD_RESISTOR_NANO.getStackForm(4), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2), EXTREME_BOARD.getStackForm()).input(plate, TungstenSteel, 1).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000).outputs(NANO_COMPUTER.getStackForm()).inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4), SMD_RESISTOR.getStackForm(8), SMD_TRANSISTOR.getStackForm(8), RANDOM_ACCESS_MEMORY.getStackForm(8), EXTREME_BOARD.getStackForm()).input(wireGtSingle, EVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000).outputs(NANO_COMPUTER.getStackForm()).inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4), SMD_RESISTOR_NANO.getStackForm(4), SMD_TRANSISTOR_NANO.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(8), EXTREME_BOARD.getStackForm()).input(wireGtSingle, EVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000).outputs(NANO_MAINFRAME.getStackForm()).inputs(NANO_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(48), SMD_TRANSISTOR.getStackForm(24), SMD_DIODE.getStackForm(12), RANDOM_ACCESS_MEMORY.getStackForm(12)).input(frameGt, TungstenSteel, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000).outputs(NANO_MAINFRAME.getStackForm()).inputs(NANO_COMPUTER.getStackForm(2), SMD_RESISTOR_NANO.getStackForm(24), SMD_TRANSISTOR_NANO.getStackForm(12), SMD_DIODE_NANO.getStackForm(6), RANDOM_ACCESS_MEMORY.getStackForm(12)).input(frameGt, TungstenSteel, 4).fluidInputs(fluidStack).buildAndRegister();

            //QUANTUM       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000).outputs(QUANTUM_PROCESSOR_EV.getStackForm(4)).inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR_NANO.getStackForm(8), SMD_CAPACITOR_NANO.getStackForm(8), ELITE_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, Platinum, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000).outputs(QUANTUM_PROCESSOR_EV.getStackForm(4)).inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR_QUANTUM.getStackForm(4), SMD_CAPACITOR_QUANTUM.getStackForm(4), ELITE_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, Platinum, 2).fluidInputs(fluidStack).buildAndRegister();
            //CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(36000).outputs(QUANTUM_PROCESSOR_EV.getStackForm(4)).inputs(ELITE_BOARD.getStackForm(), ADVANCED_SYSTEM_ON_CHIP.getStackForm()).input(wireFine, Platinum, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000).outputs(QUANTUM_ASSEMBLY.getStackForm()).inputs(QUANTUM_PROCESSOR_EV.getStackForm(3), SMD_CAPACITOR_NANO.getStackForm(8), SMD_RESISTOR_NANO.getStackForm(8), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2), ELITE_BOARD.getStackForm()).input(plate, Osmium, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000).outputs(QUANTUM_ASSEMBLY.getStackForm()).inputs(QUANTUM_PROCESSOR_EV.getStackForm(3), SMD_CAPACITOR_QUANTUM.getStackForm(4), SMD_RESISTOR_QUANTUM.getStackForm(4), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2), ELITE_BOARD.getStackForm()).input(plate, Osmium, 1).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000).outputs(QUANTUM_COMPUTER.getStackForm()).inputs(QUANTUM_ASSEMBLY.getStackForm(4), SMD_DIODE_NANO.getStackForm(16), QUANTUM_EYE.getStackForm(1), POWER_INTEGRATED_CIRCUIT.getStackForm(4), ELITE_BOARD.getStackForm()).input(wireGtSingle, IVSuperconductor, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000).outputs(QUANTUM_COMPUTER.getStackForm()).inputs(QUANTUM_ASSEMBLY.getStackForm(4), SMD_DIODE_QUANTUM.getStackForm(8), QUANTUM_EYE.getStackForm(1), POWER_INTEGRATED_CIRCUIT.getStackForm(4), ELITE_BOARD.getStackForm()).input(wireGtSingle, IVSuperconductor, 2).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000).outputs(QUANTUM_MAINFRAME.getStackForm()).inputs(QUANTUM_COMPUTER.getStackForm(2), SMD_RESISTOR_NANO.getStackForm(64), SMD_TRANSISTOR_NANO.getStackForm(56), SMD_CAPACITOR_NANO.getStackForm(56), SMD_DIODE_NANO.getStackForm(32), POWER_INTEGRATED_CIRCUIT.getStackForm(8), QUANTUM_STAR.getStackForm()).input(frameGt, TungstenSteel, 4).input(wireGtSingle, IVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000).outputs(QUANTUM_MAINFRAME.getStackForm()).inputs(QUANTUM_COMPUTER.getStackForm(2), SMD_RESISTOR_QUANTUM.getStackForm(32), SMD_TRANSISTOR_QUANTUM.getStackForm(28), SMD_CAPACITOR_QUANTUM.getStackForm(28), SMD_DIODE_QUANTUM.getStackForm(16), POWER_INTEGRATED_CIRCUIT.getStackForm(8), QUANTUM_STAR.getStackForm()).input(frameGt, TungstenSteel, 4).input(wireGtSingle, IVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();

            //CRYSTAL      //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000).outputs(CRYSTAL_PROCESSOR.getStackForm(4)).inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR_QUANTUM.getStackForm(16), SMD_CAPACITOR_QUANTUM.getStackForm(8), KAPTON_CIRCUIT_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, NiobiumTitanium, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000).outputs(CRYSTAL_PROCESSOR.getStackForm(4)).inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR_CRYSTAL.getStackForm(8), SMD_CAPACITOR_CRYSTAL.getStackForm(4), KAPTON_CIRCUIT_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, NiobiumTitanium, 2).fluidInputs(fluidStack).buildAndRegister();
            // CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(86000).outputs(CRYSTAL_PROCESSOR.getStackForm(4)).inputs(MASTER_BOARD.getStackForm(), CRYSTAL_SYSTEM_ON_CHIP.getStackForm()).input(wireFine, NiobiumTitanium, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000).outputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm()).inputs(CRYSTAL_PROCESSOR.getStackForm(3), CENTRAL_PROCESSING_UNIT.getStackForm(64), SMD_RESISTOR_QUANTUM.getStackForm(8), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), KAPTON_CIRCUIT_BOARD.getStackForm()).input(wireGtSingle, LuVSuperconductor, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000).outputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm()).inputs(CRYSTAL_PROCESSOR.getStackForm(3), CENTRAL_PROCESSING_UNIT.getStackForm(64), SMD_RESISTOR_CRYSTAL.getStackForm(4), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), KAPTON_CIRCUIT_BOARD.getStackForm()).input(wireGtSingle, LuVSuperconductor, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000).outputs(CRYSTAL_COMPUTER.getStackForm()).inputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm(4), SMD_DIODE_QUANTUM.getStackForm(16), SMD_RESISTOR_QUANTUM.getStackForm(16), QUANTUM_EYE.getStackForm(1), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(1), KAPTON_CIRCUIT_BOARD.getStackForm()).input(plate, RhodiumPlatedPalladium, 2).input(wireGtSingle, LuVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000).outputs(CRYSTAL_COMPUTER.getStackForm()).inputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm(4), SMD_DIODE_CRYSTAL.getStackForm(8), SMD_RESISTOR_CRYSTAL.getStackForm(8), QUANTUM_EYE.getStackForm(1), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(1), KAPTON_CIRCUIT_BOARD.getStackForm()).input(plate, RhodiumPlatedPalladium, 2).input(wireGtSingle, LuVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(30000).outputs(CRYSTAL_MAINFRAME.getStackForm()).inputs(CRYSTAL_COMPUTER.getStackForm(2), SMD_RESISTOR_QUANTUM.getStackForm(64), SMD_RESISTOR_QUANTUM.getStackForm(64), SMD_TRANSISTOR_QUANTUM.getStackForm(64), SMD_CAPACITOR_QUANTUM.getStackForm(64), SMD_DIODE_QUANTUM.getStackForm(48), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4), QUANTUM_STAR.getStackForm(4)).input(frameGt, HSSE, 4).input(wireGtSingle, LuVSuperconductor, 32).fluidInputs(fluidStack).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(30000).outputs(CRYSTAL_MAINFRAME.getStackForm()).inputs(CRYSTAL_COMPUTER.getStackForm(2), SMD_RESISTOR_CRYSTAL.getStackForm(48), SMD_TRANSISTOR_CRYSTAL.getStackForm(36), SMD_CAPACITOR_CRYSTAL.getStackForm(32), SMD_DIODE_CRYSTAL.getStackForm(24), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4), QUANTUM_STAR.getStackForm(4)).input(frameGt, HSSE, 4).input(wireGtSingle, LuVSuperconductor, 32).fluidInputs(fluidStack).buildAndRegister();

            //WETWARE      //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(56000).outputs(WETWARE_PROCESSOR_LUV.getStackForm(1)).inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm(1), SMD_TRANSISTOR_CRYSTAL.getStackForm(16), SMD_CAPACITOR_CRYSTAL.getStackForm(8), CYBER_PROCESSING_UNIT.getStackForm(), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, YttriumBariumCuprate, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(56000).outputs(WETWARE_PROCESSOR_LUV.getStackForm(1)).inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm(1), SMD_TRANSISTOR_WETWARE.getStackForm(8), SMD_CAPACITOR_WETWARE.getStackForm(4), CYBER_PROCESSING_UNIT.getStackForm(), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, YttriumBariumCuprate, 2).fluidInputs(fluidStack).buildAndRegister();
            //CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(120000).outputs(WETWARE_PROCESSOR_LUV.getStackForm(4)).inputs(CYBER_PROCESSING_UNIT.getStackForm(), ADVANCED_SYSTEM_ON_CHIP.getStackForm(4)).input(wireFine, NaquadahAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            fluidStack.amount = Math.min(64000, fluidStack.amount * 4);

            //BIOWARE     //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(240000)
                    .outputs(BIOWARE_PROCESSOR.getStackForm(1))
                    .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4),
                            SMD_TRANSISTOR_BIOWARE.getStackForm(8),
                            SMD_CAPACITOR_BIOWARE.getStackForm(4),
                            NEURO_PROCESSOR.getStackForm(),
                            HASOC.getStackForm(1))
                    .input(wireFine, NaquadahAlloy, 4)
                    .fluidInputs(fluidStack).buildAndRegister();

            //OPTICAL    //PROCESSOR

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(480000)
                    .outputs(OPTICAL_PROCESSOR.getStackForm(1))
                    .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4),
                            SMD_TRANSISTOR_OPTICAL.getStackForm(8),
                            SMD_CAPACITOR_OPTICAL.getStackForm(4),
                            OPTICAL_PROCESSING_CORE.getStackForm(),
                            HASOC.getStackForm(1))
                    .input(wireFine, Pikyonium, 4)
                    .fluidInputs(fluidStack).buildAndRegister();

            
            //COSMIC     //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(1920000)
                    .outputs(COSMIC_PROCESSOR.getStackForm(1))
                    .qubit(16)
                    .inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4),
                            SMD_TRANSISTOR_COSMIC.getStackForm(32),
                            SMD_CAPACITOR_COSMIC.getStackForm(16),
                            COSMIC_PROCESSING_CORE.getStackForm(),
                            UHASOC.getStackForm(1))
                    .input(wireFine, Cinobite, 4)
                    .fluidInputs(fluidStack).buildAndRegister();


            //SUPRACAUSAL     //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2097152).qubit(32)
                    .outputs(SUPRACAUSAL_PROCESSOR.getStackForm())
                    .inputs(UHASOC.getStackForm(16),
                            MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm(1),
                            MICROWORMHOLE_GENERATOR.getStackForm(),
                            SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                    .input(plate, SuperheavyHAlloy, 4)
                    .input(wireGtSingle, UHVSuperconductor, 8)
                    .fluidInputs(fluidStack).buildAndRegister();
        }

        //ASSEMBLY
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000)
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .inputs(WETWARE_PROCESSOR_LUV.getStackForm(3), SMD_RESISTOR_CRYSTAL.getStackForm(32),
                        SMD_TRANSISTOR_CRYSTAL.getStackForm(32), SMD_CAPACITOR_CRYSTAL.getStackForm(32),
                        SMD_DIODE_CRYSTAL.getStackForm(32), ARAM.getStackForm(4),
                        QUANTUM_EYE.getStackForm(4), CYBER_PROCESSING_UNIT.getStackForm())
                .input(wireGtSingle, ZPMSuperconductor, 4).input(foil, SiliconeRubber, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000)
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .inputs(WETWARE_PROCESSOR_LUV.getStackForm(3), SMD_RESISTOR_WETWARE.getStackForm(16),
                        SMD_TRANSISTOR_WETWARE.getStackForm(16), SMD_CAPACITOR_WETWARE.getStackForm(16),
                        SMD_DIODE_WETWARE.getStackForm(16), ARAM.getStackForm(4),
                        QUANTUM_EYE.getStackForm(4), CYBER_PROCESSING_UNIT.getStackForm())
                .input(wireGtSingle, ZPMSuperconductor, 4).input(foil, SiliconeRubber, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();

        //COMPUTER
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880).outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm()).inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4), SMD_RESISTOR_CRYSTAL.getStackForm(64), SMD_TRANSISTOR_CRYSTAL.getStackForm(64), SMD_CAPACITOR_CRYSTAL.getStackForm(64), SMD_DIODE_CRYSTAL.getStackForm(64), QUANTUM_STAR.getStackForm(4), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8), CYBER_PROCESSING_UNIT.getStackForm()).input(plate, Rutherfordium, 2).input(wireGtSingle, ZPMSuperconductor, 16).input(foil, SiliconeRubber, 32).fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880).outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm()).inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4), SMD_RESISTOR_WETWARE.getStackForm(32), SMD_TRANSISTOR_WETWARE.getStackForm(32), SMD_CAPACITOR_WETWARE.getStackForm(32), SMD_DIODE_WETWARE.getStackForm(32), QUANTUM_STAR.getStackForm(4), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8), CYBER_PROCESSING_UNIT.getStackForm()).input(plate, Rutherfordium, 2).input(wireGtSingle, ZPMSuperconductor, 16).input(foil, SiliconeRubber, 32).fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();
        //MAINFRAME
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000).outputs(WETWARE_MAINFRAME_MAX.getStackForm()).inputs(WETWARE_SUPER_COMPUTER_UV.getStackForm(2), SMD_RESISTOR_WETWARE.getStackForm(64), SMD_TRANSISTOR_WETWARE.getStackForm(64), SMD_CAPACITOR_WETWARE.getStackForm(64), SMD_DIODE_WETWARE.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8), GRAVI_STAR.getStackForm(4)).input(frameGt, Tritanium, 4).input(plate, Duranium, 32).input(wireGtSingle, UVSuperconductor, 64).input(foil, Polytetrafluoroethylene, 64).fluidInputs(SterileGrowthMedium.getFluid(2000), UUMatter.getFluid(1000)).buildAndRegister();


        //BIOWARE
        //PROCESSOR
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(BIOWARE_ASSEMBLY.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(1296))
                .fluidInputs(Plastic.getFluid(2592))
                .fluidInputs(NaquadahEnriched.getFluid(1296))
                .EUt(480000)
                .duration(400)
                .buildAndRegister();

        //COMPUTER
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(BIOWARE_COMPUTER.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(288))
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .fluidInputs(NaquadahEnriched.getFluid(1296))
                .EUt(960000)
                .duration(600)
                .buildAndRegister();

        //MAINFRAME
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(BIOWARE_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(64))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(64))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(64))
                .inputs(NEURO_PROCESSOR.getStackForm(2))
                .input(wireGtSingle, UHVSuperconductor, 6)
                .inputs(UHPIC.getStackForm(32))
                .input(plate, Adamantium, 2)
                .input(frameGt, Adamantium, 1)
                .input(plate, Naquadria, 8)
                .input(foil, Polybenzimidazole, 64)
                .inputs(UNSTABLE_STAR.getStackForm())
                .outputs(BIOWARE_MAINFRAME.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(1296))
                .fluidInputs(Polybenzimidazole.getFluid(2592))
                .fluidInputs(Naquadria.getFluid(1296))
                .EUt(1920000)
                .duration(800)
                .buildAndRegister();

        
        //OPTICAL ASSEMBLY
        //PROCESSOR

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(OPTICAL_ASSEMBLY.getStackForm())
                .fluidInputs(Duranium.getFluid(1296))
                .fluidInputs(Polytetrafluoroethylene.getFluid(2592))
                .fluidInputs(NaquadahEnriched.getFluid(1296))
                .qubit(4)
                .EUt(960000)
                .duration(400)
                .buildAndRegister();

        //COMPUTER

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(OPTICAL_COMPUTER.getStackForm())
                .fluidInputs(Tritanium.getFluid(288))
                .fluidInputs(Polyetheretherketone.getFluid(1296))
                .fluidInputs(Adamantium.getFluid(1296))
                .qubit(4)
                .EUt(1920000)
                .duration(600)
                .buildAndRegister();

        //MAINFRAME

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .input(frameGt, Bohrium, 1)
                .input(foil, Zylon, 64)
                .inputs(UNSTABLE_STAR.getStackForm(8))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(48))
                .outputs(OPTICAL_MAINFRAME.getStackForm())
                .fluidInputs(Cinobite.getFluid(384))
                .fluidInputs(Vibranium.getFluid(1296))
                .fluidInputs(Polyetheretherketone.getFluid(2592))
                .fluidInputs(Naquadria.getFluid(1296))
                .qubit(8)
                .EUt(6000000)
                .duration(800)
                .buildAndRegister();

        
        //COSMIC ASSEMBLY
        //PROCESSOR
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(COSMIC_ASSEMBLY.getStackForm())
                .fluidInputs(Tritanium.getFluid(1296))
                .fluidInputs(Polyetheretherketone.getFluid(2160))
                .fluidInputs(NaquadahEnriched.getFluid(1296))
                .qubit(16)
                .EUt(3840000)
                .duration(400)
                .buildAndRegister();

        //COMPUTER
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .outputs(COSMIC_COMPUTER.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(16000))
                .fluidInputs(Tritanium.getFluid(288))
                .fluidInputs(Zylon.getFluid(1440))
                .fluidInputs(Naquadria.getFluid(1296))
                .qubit(16)
                .EUt(7680000)
                .duration(600)
                .buildAndRegister();

        //MAINFRAME
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
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
                .input(frameGt, Quantum, 1)
                .input(plate, Quantum, 8)
                .input(foil, FullerenePolymerMatrix, 64)
                .inputs(UNSTABLE_STAR.getStackForm(4))
                .outputs(COSMIC_MAINFRAME.getStackForm())
                .fluidInputs(Taranium.getFluid(864))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(Zylon.getFluid(2592))
                .fluidInputs(Vibranium.getFluid(1296))
                .qubit(32)
                .EUt(24000000)
                .duration(800)
                .buildAndRegister();

        //SUPRACAUSAL
        //ASSEMBLY
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(8388608).qubit(8)
                .outputs(SUPRACAUSAL_ASSEMBLY.getStackForm())
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
                .fluidInputs(Taranium.getFluid(1296))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(1296))
                .fluidInputs(FullerenePolymerMatrix.getFluid(1296))
                .buildAndRegister();

        //COMPUTER
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(33554432).qubit(16)
                .outputs(SUPRACAUSAL_COMPUTER.getStackForm())
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
                .fluidInputs(Taranium.getFluid(1296))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(1296))
                .fluidInputs(FullerenePolymerMatrix.getFluid(1296))
                .buildAndRegister();

        //MAINFRAME
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(134217728).qubit(32)
                .outputs(SUPRACAUSAL_MAINFRAME.getStackForm())
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
                .fluidInputs(Taranium.getFluid(1296))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(1296))
                .fluidInputs(FullerenePolymerMatrix.getFluid(1296))
                .buildAndRegister();

        // Kapton Circuit Board
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(240)
                .input(plate, Polyimide)
                .fluidInputs(FluorinatedEthylenePropylene.getFluid(72))
                .outputs(KAPTON_BOARD.getStackForm())
                .buildAndRegister();

        //Circuit Rabbit Hole - Layer 2
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480).inputs(ELITE_BOARD.getStackForm(), PETRI_DISH.getStackForm(), ELECTRIC_PUMP_LV.getStackForm(), SENSOR_LV.getStackForm()).input(circuit, MarkerMaterials.Tier.Good).fluidInputs(SterileGrowthMedium.getFluid(250)).outputs(WETWARE_BOARD.getStackForm()).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(PositiveMatter.getFluid(10), NeutralMatter.getFluid(10)).fluidOutputs(UUMatter.getFluid(20)).buildAndRegister();

        ModHandler.removeRecipes(COATED_BOARD.getStackForm(3));
        ModHandler.addShapedRecipe("coated_board_shaped", COATED_BOARD.getStackForm(3), "RRR", "BBB", "RRR", 'R', RUBBER_DROP, 'B', "plateWood");
        ModHandler.addShapelessRecipe("coated_board_shapeless", COATED_BOARD.getStackForm(), RUBBER_DROP, RUBBER_DROP, "plateWood");
        ModHandler.addShapedRecipe("basic_board", BASIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Copper), 'B', COATED_BOARD);
        ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(20).input(plate, Wood).input(foil, Copper, 4).fluidInputs(Glue.getFluid(72)).outputs(BASIC_BOARD.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("good_board", GOOD_PHENOLIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Copper), 'B', PHENOLIC_BOARD);
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(PHENOLIC_BOARD.getStackForm()).input(foil, Copper, 4).fluidInputs(SodiumPersulfate.getFluid(200)).outputs(GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(PHENOLIC_BOARD.getStackForm()).input(foil, Copper, 4).fluidInputs(IronChloride.getFluid(100)).outputs(GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(SodiumPersulfate.getFluid(500)).outputs(GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(IronChloride.getFluid(250)).outputs(GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(SodiumPersulfate.getFluid(1000)).outputs(ADVANCED_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(IronChloride.getFluid(500)).outputs(ADVANCED_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(SodiumPersulfate.getFluid(2000)).outputs(EXTREME_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(IronChloride.getFluid(1000)).outputs(EXTREME_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(SodiumPersulfate.getFluid(4000)).outputs(ELITE_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(IronChloride.getFluid(2000)).outputs(ELITE_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2600).EUt(240).inputs(KAPTON_BOARD.getStackForm()).input(foil, VanadiumGallium, 24).fluidInputs(SodiumPersulfate.getFluid(6000)).outputs(KAPTON_CIRCUIT_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2600).EUt(240).inputs(KAPTON_BOARD.getStackForm()).input(foil, VanadiumGallium, 24).fluidInputs(IronChloride.getFluid(3000)).outputs(KAPTON_CIRCUIT_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(SodiumPersulfate.getFluid(10000)).outputs(MASTER_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(IronChloride.getFluid(5000)).outputs(MASTER_BOARD.getStackForm()).buildAndRegister();

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diode"));
        ModHandler.addShapedRecipe("ga_diode", DIODE.getStackForm(), " P ", "CGC", " P ", 'P', "paneGlassColorless", 'C', new UnificationEntry(wireFine, Copper), 'G', new UnificationEntry(dustSmall, Gallium));
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).input(dust, GalliumArsenide).fluidInputs(Plastic.getFluid(288)).outputs(SMD_DIODE.getStackForm(32)).buildAndRegister();

        ModHandler.removeRecipes(RESISTOR.getStackForm(3));
        for (Material m : new Material[]{Coal, Charcoal, Carbon}) {
            if (GAConfig.GT6.BendingFoils) {
                ModHandler.addShapedRecipe(String.format(Gregicality.MODID + ":resistor_%s", m.toString()), RESISTOR.getStackForm(), "RWR", "CMC", " W ", 'M', new UnificationEntry(dust, m), 'R', RUBBER_DROP, 'W', "wireFineCopper", 'C', "wireGtSingleCopper");
            } else {
                ModHandler.addShapedRecipe(String.format(Gregicality.MODID + ":resistor_%s", m.toString()), RESISTOR.getStackForm(), "RCR", "CMC", " C ", 'M', new UnificationEntry(dust, m), 'R', RUBBER_DROP, 'C', "wireGtSingleCopper");
            }
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, Copper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(RESISTOR.getStackForm(8)).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, AnnealedCopper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(RESISTOR.getStackForm(8)).buildAndRegister();
        }

        //Cutting Machine Recipes
        for (MaterialStack stack : sawLubricants) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;
            CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(60).inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).fluidInputs(material.getFluid(2 * multiplier)).outputs(RAW_CRYSTAL_CHIP.getStackForm(2)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polystyrene.getFluid(36)).notConsumable(SHAPE_MOLD_CYLINDER.getStackForm()).outputs(PETRI_DISH.getStackForm()).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polytetrafluoroethylene.getFluid(36)).notConsumable(SHAPE_MOLD_CYLINDER.getStackForm()).outputs(PETRI_DISH.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Emerald).fluidInputs(Rutherfordium.getFluid(72)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Olivine).fluidInputs(Rutherfordium.getFluid(72)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Emerald).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Olivine).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(30).input(dust, Iron).fluidInputs(HydrochloricAcid.getFluid(2000)).fluidOutputs(IronChloride.getFluid(3000), Hydrogen.getFluid(3000)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        ModHandler.removeRecipes(OreDictUnifier.get(dust, Materials.ReinforcedEpoxyResin));

        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Olivine)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 500).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Emerald)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 500).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 500, 75).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 500, 75).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Olivine)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 250, 50).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Emerald)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 250, 50).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(dust, Carbon).fluidInputs(Cerium.getFluid(1)).chancedOutput(CARBON_FIBERS.getStackForm(2), 1250, 250).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000).inputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).notConsumable(craftingLens, MarkerMaterials.Color.Lime).outputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).blastFurnaceTemp(1784).input(dust, Silicon, 32).input(dustSmall, GalliumArsenide).outputs(SILICON_BOULE.getStackForm()).buildAndRegister();

        //SMD REFINED
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 6).inputs(TRANSISTOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_REFINED.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 6).input(plate, Silver).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_REFINED.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 4).inputs(RESISTOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_REFINED.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 4).input(dust, Carbon).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_REFINED.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96).input(foil, Rubber, 4).inputs(CAPACITOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_REFINED.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120).input(foil, Rubber, 4).input(foil, Steel).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_REFINED.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Gold, 8).inputs(DIODE.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_REFINED.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Gold, 8).input(dust, Lithium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_REFINED.getStackForm(32)).buildAndRegister();

        //SMD MICRO
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, AnnealedCopper, 6).inputs(SMD_TRANSISTOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Electrum, 4).inputs(SMD_RESISTOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR.getStackForm(12)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96).input(foil, PolyvinylChloride, 4).inputs(SMD_CAPACITOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR.getStackForm(8)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).inputs(SMD_DIODE_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE.getStackForm(16)).buildAndRegister();

        //SMD NANO
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Palladium, 12).inputs(SMD_TRANSISTOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_NANO.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Palladium, 12).input(plate, Magnalium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_NANO.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Cerium, 8).inputs(SMD_RESISTOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_NANO.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Cerium, 8).input(dust, Graphite).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_NANO.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480).input(foil, Silicon, 4).inputs(SMD_CAPACITOR.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_NANO.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480).input(foil, Silicon, 4).input(foil, Titanium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_NANO.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).input(wireFine, ReinforcedEpoxyResin, 8).inputs(SMD_DIODE.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_NANO.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).input(wireFine, ReinforcedEpoxyResin, 8).input(dust, Caesium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_NANO.getStackForm(32)).buildAndRegister();

        //SMD QUANTUM
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Plutonium, 12).inputs(SMD_TRANSISTOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Plutonium, 12).input(plate, Americium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Ruthenium, 8).inputs(SMD_RESISTOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_QUANTUM.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Ruthenium, 8).input(plate, Graphene).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_QUANTUM.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920).input(foil, SiliconeRubber, 4).inputs(SMD_CAPACITOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_QUANTUM.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920).input(foil, SiliconeRubber, 4).input(foil, Tungsten).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_QUANTUM.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).input(wireFine, HSSG, 8).inputs(SMD_DIODE_NANO.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_QUANTUM.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).input(wireFine, HSSG, 8).input(dust, Polonium).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_QUANTUM.getStackForm(32)).buildAndRegister();

        //SMD CRYSTAL
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, Rutherfordium, 12).inputs(SMD_TRANSISTOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, Rutherfordium, 12).input(plate, NetherStar).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, NaquadahAlloy, 8).inputs(SMD_RESISTOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_CRYSTAL.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, NaquadahAlloy, 8).input(plate, Graphene).input(plate, RutheniumDioxide).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_RESISTOR_CRYSTAL.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680).input(foil, Polybenzimidazole, 4).inputs(SMD_CAPACITOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680).input(foil, Polybenzimidazole, 4).input(foil, NaquadahAlloy).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).input(wireFine, HSSS, 8).inputs(SMD_DIODE_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_CRYSTAL.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).input(wireFine, HSSS, 8).inputs(LanthanumCalciumManganate.getItemStack()).fluidInputs(Plastic.getFluid(GTValues.L)).outputs(SMD_DIODE_CRYSTAL.getStackForm(32)).buildAndRegister();

        //SMD WETWARE
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616).input(wireFine, PEDOT, 8).input(foil, Polybenzimidazole, 4).input(foil, BariumTitanate, 4).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_CAPACITOR_WETWARE.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616).input(wireFine, NaquadahAlloy, 6).input(plate, BismuthRuthenate).input(plate, BismuthIridiate).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_RESISTOR_WETWARE.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720).input(wireFine, Dubnium, 8).input(plate, GermaniumTungstenNitride, 4).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_TRANSISTOR_WETWARE.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7680).input(wireFine, Osmiridium, 8).inputs(AluminiumComplex.getItemStack()).inputs(CopperGalliumIndiumSelenide.getItemStack()).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_DIODE_WETWARE.getStackForm(32)).buildAndRegister();

        //Circuit resonatic Magneto
        //CHEMICAL_RECIPES.recipeBuilder().duration(4000).EUt(30).input(dust, Yttrium, 2).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, YttriumOxide, 5)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(56000).EUt(480).blastFurnaceTemp(2953).input(dust, Zirconium, 10).input(dust, YttriumOxide, 1).fluidInputs(Oxygen.getFluid(10000)).outputs(OreDictUnifier.get(gemFlawed, CubicZirconia, 40)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(1080).EUt(20).input(dust, Prasiolite, 3).input(dust, BismuthTellurite, 4).input(dust, CubicZirconia, 1).input(dust, SteelMagnetic).outputs(OreDictUnifier.get(dust, MagnetoResonatic, 9)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(570).EUt(90).input(dust, Bismuth, 2).input(dust, Boron).fluidInputs(Hydrogen.getFluid(1000)).outputs(OreDictUnifier.get(dust, Dibismusthydroborat, 4)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(161).EUt(60).input(dust, Bismuth, 2).input(dust, Tellurium, 3).outputs(OreDictUnifier.get(dust, BismuthTellurite, 5)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(982).EUt(15).input(dust, IndiumGalliumPhosphide).input(dust, Dibismusthydroborat, 3).input(dust, BismuthTellurite, 2).outputs(OreDictUnifier.get(dust, CircuitCompoundMK3, 6)).buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(300).EUt(480).input(dust, CircuitCompoundMK3, 4).input(dust, MagnetoResonatic).outputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm()).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(300).EUt(1920).inputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm()).fluidInputs(SolderingAlloy.getFluid(GTValues.L * 3)).outputs(IMPRINT_SUPPORTED_BOARD.getStackForm()).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Neon.getFluid(100)).outputs(OreDictUnifier.get(gemChipped, MagnetoResonatic, 9)).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Krypton.getFluid(100)).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(30).input(gemChipped, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawed, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(120).input(gemFlawed, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(1200).EUt(480).input(gem, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawless, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(1920).input(gemFlawless, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemExquisite, MagnetoResonatic)).buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(75).EUt(30).outputs(CIRCUIT_MAGNETIC_ULV.getStackForm(4)).inputs(VACUUM_TUBE.getStackForm()).inputs(OreDictUnifier.get(gem, MagnetoResonatic), IMPRINT_SUPPORTED_BOARD.getStackForm(), DIODE.getStackForm(4), CAPACITOR.getStackForm(4), TRANSISTOR.getStackForm(4)).fluidInputs(SolderingAlloy.getFluid(36)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(120).outputs(CIRCUIT_MAGNETIC_LV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_ULV.getStackForm(), SMD_DIODE_REFINED.getStackForm(8), SMD_CAPACITOR_REFINED.getStackForm(8), SMD_TRANSISTOR_REFINED.getStackForm(8)).fluidInputs(SolderingAlloy.getFluid(72)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(225).EUt(480).outputs(CIRCUIT_MAGNETIC_MV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_LV.getStackForm(), SMD_DIODE.getStackForm(12), SMD_CAPACITOR.getStackForm(12), SMD_TRANSISTOR.getStackForm(12)).fluidInputs(SolderingAlloy.getFluid(108)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920).outputs(CIRCUIT_MAGNETIC_HV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_MV.getStackForm(), SMD_DIODE_NANO.getStackForm(16), SMD_CAPACITOR_NANO.getStackForm(16), SMD_TRANSISTOR_NANO.getStackForm(16)).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(375).EUt(7680).outputs(CIRCUIT_MAGNETIC_EV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_HV.getStackForm(), SMD_DIODE_QUANTUM.getStackForm(20), SMD_CAPACITOR_QUANTUM.getStackForm(20), SMD_TRANSISTOR_QUANTUM.getStackForm(20)).fluidInputs(SolderingAlloy.getFluid(180)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(450).EUt(30720).outputs(CIRCUIT_MAGNETIC_IV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gem, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_EV.getStackForm(), SMD_DIODE_CRYSTAL.getStackForm(24), SMD_CAPACITOR_CRYSTAL.getStackForm(24), SMD_TRANSISTOR_CRYSTAL.getStackForm(24)).fluidInputs(SolderingAlloy.getFluid(864)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(525).EUt(122880).outputs(CIRCUIT_MAGNETIC_LUV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gem, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_IV.getStackForm(), SMD_DIODE_WETWARE.getStackForm(28), SMD_CAPACITOR_WETWARE.getStackForm(28), SMD_TRANSISTOR_WETWARE.getStackForm(28)).fluidInputs(SolderingAlloy.getFluid(1008)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(491520).outputs(CIRCUIT_MAGNETIC_ZPM.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gemExquisite, MagnetoResonatic), CIRCUIT_MAGNETIC_LUV.getStackForm(), SMD_DIODE_BIOWARE.getStackForm(32), SMD_CAPACITOR_BIOWARE.getStackForm(32), SMD_TRANSISTOR_BIOWARE.getStackForm(32)).fluidInputs(SolderingAlloy.getFluid(4608)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(675).EUt(1966080).outputs(CIRCUIT_MAGNETIC_UV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gemExquisite, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_ZPM.getStackForm(), SMD_DIODE_BIOWARE.getStackForm(36), SMD_CAPACITOR_BIOWARE.getStackForm(36), SMD_TRANSISTOR_BIOWARE.getStackForm(36)).fluidInputs(SolderingAlloy.getFluid(5184)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(750).EUt(7864320).outputs(CIRCUIT_MAGNETIC_UHV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(12), OreDictUnifier.get(gemExquisite, MagnetoResonatic, 12), CIRCUIT_MAGNETIC_UV.getStackForm(), SMD_DIODE_BIOWARE.getStackForm(40), SMD_CAPACITOR_BIOWARE.getStackForm(40), SMD_TRANSISTOR_BIOWARE.getStackForm(40)).fluidInputs(SolderingAlloy.getFluid(5760)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(825).EUt(31457280).outputs(CIRCUIT_MAGNETIC_UEV.getStackForm(4)).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(12), OreDictUnifier.get(gemExquisite, MagnetoResonatic, 12), CIRCUIT_MAGNETIC_UHV.getStackForm(), SMD_DIODE_BIOWARE.getStackForm(44), SMD_CAPACITOR_BIOWARE.getStackForm(44), SMD_TRANSISTOR_BIOWARE.getStackForm(44)).fluidInputs(SolderingAlloy.getFluid(6336)).buildAndRegister();


    }
}
