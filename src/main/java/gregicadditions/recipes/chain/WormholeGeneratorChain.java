package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.EMITTER_UV;
import static gregtech.common.items.MetaItems.FIELD_GENERATOR_UV;

public class WormholeGeneratorChain {
    public static void init(){
        POLARIZER_RECIPES.recipeBuilder().duration(200).EUt(500000)
                .inputs(NEUTRONIUM_SPHERE.getStackForm())
                .outputs(TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .buildAndRegister();
        LARGE_CENTRIFUGE_RECIPES.recipeBuilder().duration(40).EUt(100000)
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidInputs(Helium.getPlasma(1000))
                .fluidOutputs(FreeAlphaGas.getFluid(1000))
                .fluidOutputs(FreeElectronGas.getFluid(1000))
                .buildAndRegister();
        FLUID_CANNER_RECIPES.recipeBuilder().duration(200).EUt(50000)
                .inputs(TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .fluidInputs(FreeAlphaGas.getFluid(1000))
                .outputs(CHARGED_TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(100000)
                .fluidInputs(Tritanium.getFluid(288))
                .input(stick, NaquadahAlloy, 16)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(plateDense, Neutronium, 8)
                .inputs(EMITTER_UV.getStackForm(32))
                .inputs(FIELD_GENERATOR_UV.getStackForm(32))
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(288566)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.TARANIUM_CHARGE))
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(64))
                .inputs(CHARGED_TRIPLET_NEUTRONIUM_SPHERE.getStackForm(64))
                .outputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm(64))
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(4000).EUt(500000)
                .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm(64))
                .outputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(63))
                .buildAndRegister();
        FLUID_CANNER_RECIPES.recipeBuilder().duration(40).EUt(100000)
                .fluidInputs(FreeElectronGas.getFluid(1000))
                .inputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .outputs(CONTAINED_KERR_SINGULARITY.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(100000)
                .fluidInputs(Tritanium.getFluid(576))
                .fluidInputs(FreeElectronGas.getFluid(1000))
                .input(plate, MetastableOganesson, 6)
                .input(plate, Uranium, 6)
                .input(plate, Mendelevium.getMaterial(), 6)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.TARANIUM_CHARGE))
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.LEPTONIC_CHARGE))
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(100000)
                .fluidInputs(Tritanium.getFluid(576))
                .inputs(EMITTER_UV.getStackForm(32))
                .inputs(FIELD_GENERATOR_UV.getStackForm(32))
                .inputs(CONTAINED_KERR_SINGULARITY.getStackForm())
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(plate, Neutronium, 64)
                .outputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(400).EUt(1000000)
                .inputs(NEUTRONIUM_SPHERE.getStackForm())
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .outputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(1000000)
                .fluidInputs(NaquadahAlloy.getFluid(576))
                .inputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .input(circuit, MarkerMaterials.Tier.Infinite, 16)
                .inputs(EMITTER_UHV.getStackForm(32))
                .inputs(FIELD_GENERATOR_UHV.getStackForm(32))
                .inputs(SENSOR_UHV.getStackForm(32))
                .inputs(BATTERY_LARGE_NAQUADRIA.getStackForm())
                .outputs(MACROWORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(600).EUt(5000000)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(64))
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.LEPTONIC_CHARGE))
                .inputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .outputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(6000000)
                .fluidInputs(Naquadria.getFluid(1000))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(1000))
                .fluidInputs(Neutronium.getFluid(1000))
                .fluidInputs(Taranium.getFluid(1000))
                .input(circuit, UEV, 16)
                .inputs(EMITTER_UEV.getStackForm(32))
                .inputs(FIELD_GENERATOR_UEV.getStackForm(32))
                .inputs(SENSOR_UEV.getStackForm(32))
                .inputs(BATTERY_LARGE_NEUTRONIUM.getStackForm())
                .inputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                .inputs(MACROWORMHOLE_GENERATOR.getStackForm())
                .outputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();
    }
}
