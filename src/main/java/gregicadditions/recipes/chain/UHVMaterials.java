package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregicadditions.item.GAExplosive;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregtech.api.unification.ore.OrePrefix;
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

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, Naquadria)
                .inputs(GELLED_TOLUENE.getStackForm(2))
                .input(plate, Uranium238Isotope.getMaterial(), 1)
                .input(bolt, Osmium, 1)
                .input(bolt, Titanium, 4)
                .inputs(HexanitroHexaaxaisowurtzitane.getItemStack())
                .fluidInputs(Glyceryl.getFluid(1000))
                .outputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .EUt(1966080)
                .duration(100)
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(ingot, Rhenium)
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .fluidOutputs(ElectronDegenerateRheniumPlasma.getFluid(1000))
                .EUt(1966080)
                .duration(20)
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(ElectronDegenerateRheniumPlasma.getFluid(1000))
                .outputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(30720)
                .duration(20)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(LiquidHelium.getFluid(16000))
                .fluidOutputs(Helium.getFluid(16000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(122880)
                .duration(250)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(Helium.getFluid(5000))
                .fluidOutputs(LiquidHelium.getFluid(5000))
                .EUt(7680)
                .duration(20)
                .buildAndRegister();
      
        OrePrefix plateB = plate;
        if (GAConfig.GT6.addCurvedPlates) {
            plateB = plateCurved;
        }
      
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic, 2)
                .input(wireGtSingle, UVSuperconductor, 16)
                .input(pipeLarge, Ultimet, 4)
                .input(plateB, NaquadahAlloy, 8)
                .fluidInputs(Titanium.getFluid(2592))
                .fluidInputs(NaquadahEnriched.getFluid(1584))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(983040)
                .duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Mendelevium.getMaterial())
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .input(stickLong, NaquadriaticTaranium)
                .input(dust, Taranium)
                .input(plate, Tritanium)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .fluidInputs(Glyceryl.getFluid(2500))
                .outputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.TARANIUM_CHARGE))
                .EUt(7_864_320)
                .duration(20)
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder()
                .input(dust, Seaborgium)
                .input(dust, Bohrium)
                .input(dust, Rutherfordium)
                .input(dust, Dubnium)
                .fluidOutputs(SuperheavyMix.getFluid(4000))
                .EUt(25_000_000)
                .duration(40)
                .blastFurnaceTemp(12800)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .fluidInputs(SuperheavyMix.getFluid(1000))
                .outputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.BlockType.SUPERHEAVY_BLOCK))
                .EUt(100000000)
                .duration(40)
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder()
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.BlockType.SUPERHEAVY_BLOCK))
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.TARANIUM_CHARGE))
                .fluidOutputs(NeutronPlasma.getFluid(1000))
                .EUt(100000000)
                .duration(10)
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(NeutronPlasma.getFluid(1000))
                .outputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(25000000)
                .duration(80)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(LiquidHelium.getFluid(32000))
                .fluidOutputs(Helium.getFluid(32000))
                .notConsumable(INGOT_FIELD_SHAPE.getStackForm())
                .output(ingot, Neutronium)
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(10000000)
                .duration(500)
                .buildAndRegister();
    }
}
