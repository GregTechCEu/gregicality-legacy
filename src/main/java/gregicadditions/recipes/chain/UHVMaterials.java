package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GASimpleBlock;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.GELLED_TOLUENE;


public class UHVMaterials {
    public static void init() {
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(block, Naquadria)
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .input(plateDense, Uranium238Isotope.getMaterial(), 4)
                .input(plateDense, Osmium, 4)
                .input(plateDense, Titanium, 4)
                .fluidInputs(Glyceryl.getFluid(1000))
                .fluidInputs(Titanium.getFluid(1296))
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.NAQUADRIA_CHARGE))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(block, Rhenium)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.NAQUADRIA_CHARGE))
                .fluidOutputs(ElectronDegenerateRheniumPlasma.getFluid(1000))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();
        FLUID_CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(ElectronDegenerateRheniumPlasma.getFluid(1000))
                .outputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(500000)
                .duration(20)
                .buildAndRegister();
        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(LiquidHelium.getFluid(16000))
                .fluidOutputs(Helium.getFluid(16000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(500000)
                .duration(250)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(Helium.getFluid(5000))
                .fluidOutputs(LiquidHelium.getFluid(5000))
                .EUt(122880)
                .duration(20)
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic, 2)
                .input(wireGtSingle, UVSuperconductor, 16)
                .input(pipeLarge, Ultimet, 4)
                .input(plateCurved, NaquadahAlloy, 8)
                .fluidInputs(Titanium.getFluid(2592))
                .fluidInputs(NaquadahEnriched.getFluid(1584))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(1500000)
                .duration(50)
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(plateDense, Mendelevium.getMaterial())
                .inputs(GELLED_TOLUENE.getStackForm(16))
                .input(block, Naquadria, 8)
                .input(block, Taranium)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(1296))
                .fluidInputs(Glyceryl.getFluid(5000))
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.TARANIUM_CHARGE))
                .EUt(125000000)
                .duration(5)
                .buildAndRegister();
        BLAST_ALLOY_RECIPES.recipeBuilder()
                .input(dust, Seaborgium)
                .input(dust, Bohrium)
                .input(dust, Rutherfordium)
                .input(dust, Dubnium)
                .fluidOutputs(SuperheavyMix.getFluid(4000))
                .EUt(100000000)
                .duration(40)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .fluidInputs(SuperheavyMix.getFluid(1000))
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.SUPERHEAVY_BLOCK))
                .EUt(100000000)
                .duration(40)
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder()
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.SUPERHEAVY_BLOCK))
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.TARANIUM_CHARGE))
                .fluidOutputs(NeutronPlasma.getFluid(1000))
                .EUt(100000000)
                .duration(10)
                .buildAndRegister();
        FLUID_CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(NeutronPlasma.getFluid(1000))
                .outputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(100000000)
                .duration(20)
                .buildAndRegister();
        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(LiquidHelium.getFluid(32000))
                .fluidOutputs(Helium.getFluid(32000))
                .notConsumable(INGOT_FIELD_SHAPE.getStackForm())
                .outputs(OreDictUnifier.get(ingot, Neutronium))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(10000000)
                .duration(500)
                .buildAndRegister();
    }
}
