package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregicadditions.item.fusion.GAFusionCasing;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SupraCausalComponents {
    public static void init(){
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(33550000)
                .inputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(FIELD_GENERATOR_UMV.getStackForm(2))
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(6))
                .fluidInputs(Neutronium.getFluid(1296))
                .outputs(TOPOLOGICAL_MANIPULATOR_UNIT.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(33550000)
                .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SENSOR_UMV.getStackForm(4))
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(6))
                .fluidInputs(Neutronium.getFluid(1296))
                .outputs(GRAVITON_TRANSDUCER.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .inputs(FIELD_GENERATOR_UMV.getStackForm(2))
                .inputs(SENSOR_UMV.getStackForm(4))
                .inputs(NEUTRON_REFLECTOR.getStackForm(8))
                .inputs(BATTERY_SMALL_NEUTRONIUM.getStackForm())
                .input(wireGtSingle, UMVSuperconductor, 16)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_3))
                .fluidInputs(Neutronium.getFluid(1296))
                .outputs(RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, ProtoAdamantium)
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, Vibranium)
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_DIODE_SUPRACAUSAL.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, Neutronium)
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(foil, FullerenePolymerMatrix)
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(134217728).qubit(32)
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .inputs(TOOL_DATA_ORB.getStackForm())
                .inputs(NUCLEAR_CLOCK.getStackForm())
                .inputs(BATTERY_MEDIUM_NEUTRONIUM.getStackForm())
                .inputs(ARAM.getStackForm(16))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(Vibranium.getFluid(1296))
                .outputs(CTC_GUIDANCE_UNIT.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1600).EUt(33550000)
                .inputs(SENSOR_UMV.getStackForm(8))
                .inputs(SCINTILLATOR.getStackForm())
                .fluidInputs(Thorium.getFluid(144))
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm(2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .fluidInputs(BlackTitanium.getFluid(1296))
                .outputs(NUCLEAR_CLOCK.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(33550000)
                .fluidInputs(Taranium.getFluid(1296))
                .fluidInputs(Naquadria.getFluid(1296))
                .fluidInputs(Trinium.getFluid(1296))
                .input(wireGtSingle, Pikyonium, 64)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(16))
                .input(wireFine, Quantum, 8)
                .input(stick, NeodymiumMagnetic, 64)
                .input(plate, Vibranium, 48)
                .outputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .inputs(COSMIC_PROCESSING_CORE.getStackForm(16))
                .inputs(NUCLEAR_CLOCK.getStackForm())
                .inputs(TOPOLOGICAL_MANIPULATOR_UNIT.getStackForm(8))
                .inputs(RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM.getStackForm(8))
                .inputs(GRAVITON_TRANSDUCER.getStackForm(4))
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(12))
                .input(plate, Neutronium, 32)
                .input(wireGtSingle, UMVSuperconductor, 32)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .fluidInputs(FullerenePolymerMatrix.getFluid(1296))
                .outputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .fluidInputs(Taranium.getFluid(1296))
                .fluidInputs(Gluons.getFluid(1296))
                .fluidInputs(SuperheavyHAlloy.getFluid(1296))
                .fluidInputs(SuperheavyLAlloy.getFluid(1296))
                .input(plate, ProtoAdamantium, 36)
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.LEPTONIC_CHARGE))
                .input(plate, HeavyQuarkDegenerateMatter, 36)
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(33550000)
                .fluidInputs(SuperheavyHAlloy.getFluid(1296))
                .fluidInputs(SuperheavyLAlloy.getFluid(1296))
                .fluidInputs(TriniumTitanium.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(1296))
                .inputs(CTC_GUIDANCE_UNIT.getStackForm())
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .input(plate, EnrichedNaquadahAlloy, 36)
                .input(plate, FullerenePolymerMatrix, 36)
                .inputs(FIELD_GENERATOR_UMV.getStackForm(12))
                .input(plateDense, Neutronium, 36)
                .input(plate, Taranium, 64)
                .input(frameGt, Neutronium, 16)
                .input(plate, HeavyQuarkDegenerateMatter, 6)
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .outputs(CTC_COMPUTATIONAL_UNIT_CONTAINER.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .input(block, HeavyQuarkDegenerateMatter, 16)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .fluidOutputs(HighEnergyQGP.getFluid(144))
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(2000).EUt(134217728)
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(16))
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant((GASimpleBlock.CasingType.QCD_CHARGE)))
                .fluidOutputs(QCDMatter.getFluid(1296))
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant((GASimpleBlock.CasingType.QCD_CHARGE)))
                .inputs(MACROWORMHOLE_GENERATOR.getStackForm(2))
                .outputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant((GASimpleBlock.CasingType.QCD_CHARGE)))
                .outputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(800).EUt(134217728)
                .inputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .inputs(CTC_COMPUTATIONAL_UNIT_CONTAINER.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant((GASimpleBlock.CasingType.QCD_CHARGE)))
                .outputs(CTC_COMPUTATIONAL_UNIT.getStackForm())
                .buildAndRegister();
        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .fluidInputs(HighEnergyQGP.getFluid(144))
                .fluidInputs(LiquidHelium.getFluid(10000))
                .fluidOutputs(Helium.getFluid(10000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(QCD_PROTECTIVE_PLATING.getStackForm())
                .buildAndRegister();
        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(QCDMatter.getFluid(144))
                .fluidInputs(LiquidHelium.getFluid(20000))
                .fluidOutputs(Helium.getFluid(20000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(OreDictUnifier.get(plate, QCDMatter))
                .buildAndRegister();
    }
}