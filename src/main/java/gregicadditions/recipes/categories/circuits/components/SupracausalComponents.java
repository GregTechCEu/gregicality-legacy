package gregicadditions.recipes.categories.circuits.components;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAExplosive.ExplosiveType.LEPTONIC_CHARGE;
import static gregicadditions.item.GAExplosive.ExplosiveType.QCD_CHARGE;
import static gregicadditions.item.GAMetaBlocks.EXPLOSIVE;
import static gregicadditions.item.GAMetaBlocks.FUSION_CASING;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.fusion.GAFusionCasing.CasingType.ADV_FUSION_COIL_1;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.NEUTRON_REFLECTOR;
import static gregtech.common.items.MetaItems.TOOL_DATA_ORB;

public class SupracausalComponents {

    public static void init(){

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .input(plate, ProtoAdamantium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .input(plate, Vibranium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_DIODE_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .input(plate, Neutronium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .input(foil, FullerenePolymerMatrix)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        // Supracausal Processing Core
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .inputs(NUCLEAR_CLOCK.getStackForm())
                .inputs(TOPOLOGICAL_MANIPULATOR_UNIT.getStackForm(2))
                .inputs(RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM.getStackForm(2))
                .inputs(GRAVITON_TRANSDUCER.getStackForm(4))
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(3))
                .input(plate, Neutronium)
                .input(wireGtSingle, UMVSuperconductor, 2)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 9))
                .outputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm(8))
                .buildAndRegister();

        // Topological Manipulator Unit
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(33550000)
                .inputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(FIELD_GENERATOR_UHV.getStackForm())
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(2))
                .fluidInputs(Neutronium.getFluid(L))
                .outputs(TOPOLOGICAL_MANIPULATOR_UNIT.getStackForm())
                .buildAndRegister();

        // Graviton Transducer
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(33550000)
                .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(2))
                .fluidInputs(Neutronium.getFluid(L))
                .outputs(GRAVITON_TRANSDUCER.getStackForm())
                .buildAndRegister();

        // Relativistic Spinorial Memory System
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .inputs(FIELD_GENERATOR_UHV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(NEUTRON_REFLECTOR.getStackForm(2))
                .inputs(BATTERY_MEDIUM_LIS.getStackForm())
                .input(wireGtSingle, UMVSuperconductor, 2)
                .inputs(FUSION_CASING.getItemVariant(ADV_FUSION_COIL_1))
                .fluidInputs(Neutronium.getFluid(L * 9))
                .outputs(RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM.getStackForm())
                .buildAndRegister();

        // CTC Guidance Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(134217728).qubit(8)
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .inputs(TOOL_DATA_ORB.getStackForm())
                .inputs(NUCLEAR_CLOCK.getStackForm())
                .inputs(BATTERY_LARGE_LIS.getStackForm())
                .inputs(ARAM.getStackForm(16))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(Vibranium.getFluid(L * 9))
                .outputs(CTC_GUIDANCE_UNIT.getStackForm())
                .buildAndRegister();

        // Nuclear Clock
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1600).EUt(33550000)
                .inputs(SENSOR_UIV.getStackForm(2))
                .inputs(SCINTILLATOR.getStackForm())
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm(2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .fluidInputs(Thorium.getFluid(L))
                .fluidInputs(BlackTitanium.getFluid(L * 9))
                .outputs(NUCLEAR_CLOCK.getStackForm())
                .buildAndRegister();

        // Manifold Oscillatory Power Cell
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(33550000)
                .input(wireGtSingle, Pikyonium, 32)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(2))
                .input(wireFine, Quantum, 8)
                .input(stick, NeodymiumMagnetic, 32)
                .input(plate, Vibranium, 16)
                .fluidInputs(Taranium.getFluid(L / 2))
                .fluidInputs(Naquadria.getFluid(L))
                .fluidInputs(Trinium.getFluid(L * 2))
                .outputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .buildAndRegister();

        // QCD Charge
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(33550000)
                .input(plate, ProtoAdamantium, 3)
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .inputs(EXPLOSIVE.getItemVariant(LEPTONIC_CHARGE))
                .input(plate, HeavyQuarkDegenerateMatter, 2)
                .input(plate, SuperheavyHAlloy)
                .input(plate, SuperheavyLAlloy)
                .input(plate, Taranium, 2)
                .fluidInputs(Gluons.getFluid(L * 9))
                .outputs(EXPLOSIVE.getItemVariant(QCD_CHARGE))
                .buildAndRegister();

        // CTC Computational Unit Container
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(33550000)
                .inputs(CTC_GUIDANCE_UNIT.getStackForm())
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .input(plate, EnrichedNaquadahAlloy, 36)
                .input(plate, FullerenePolymerMatrix, 36)
                .inputs(FIELD_GENERATOR_UHV.getStackForm(3))
                .input(plateDense, Neutronium)
                .input(plate, Taranium, 2)
                .input(frameGt, Neutronium)
                .input(plate, HeavyQuarkDegenerateMatter, 6)
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .fluidInputs(SuperheavyHAlloy.getFluid(L * 9))
                .fluidInputs(SuperheavyLAlloy.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 9))
                .outputs(CTC_COMPUTATIONAL_UNIT_CONTAINER.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .input(ingot, HeavyQuarkDegenerateMatter)
                .inputs(EXPLOSIVE.getItemVariant(QCD_CHARGE))
                .fluidOutputs(HighEnergyQGP.getFluid(L))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(2000).EUt(134217728)
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(4))
                .inputs(EXPLOSIVE.getItemVariant((QCD_CHARGE)))
                .fluidOutputs(QCDMatter.getFluid(L * 9))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .inputs(EXPLOSIVE.getItemVariant((QCD_CHARGE)))
                .inputs(MACROWORMHOLE_GENERATOR.getStackForm(2))
                .outputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .inputs(EXPLOSIVE.getItemVariant((QCD_CHARGE)))
                .outputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(800).EUt(134217728)
                .inputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .inputs(CTC_COMPUTATIONAL_UNIT_CONTAINER.getStackForm())
                .inputs(EXPLOSIVE.getItemVariant((QCD_CHARGE)))
                .outputs(CTC_COMPUTATIONAL_UNIT.getStackForm())
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .fluidInputs(HighEnergyQGP.getFluid(L))
                .fluidInputs(LiquidHelium.getFluid(10000))
                .fluidOutputs(Helium.getFluid(10000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(QCD_PROTECTIVE_PLATING.getStackForm())
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(QCDMatter.getFluid(L))
                .fluidInputs(LiquidHelium.getFluid(20000))
                .fluidOutputs(Helium.getFluid(20000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .output(plate, QCDMatter)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(200).EUt(134217728)
                .fluidInputs(QCDMatter.getFluid(L / 2))
                .fluidInputs(LiquidHelium.getFluid(10000))
                .fluidOutputs(Helium.getFluid(10000))
                .notConsumable(ROD_FIELD_SHAPE.getStackForm())
                .output(stick, QCDMatter)
                .buildAndRegister();
    }
}
