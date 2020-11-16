package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing2;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregtech.common.items.MetaItems.*;


public class UHVMaterials {
    public static void init() {
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(block, Naquadria)
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .input(plateDense, Uranium238Isotope.getMaterial(), 4)
                .input(plateDense, Osmium, 4)
                .input(plateDense, Titanium, 4)
                .fluidInputs(Glyceryl.getFluid(1000))
                .fluidInputs(Titanium.getFluid(1000))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.NAQUADRIA_CHARGE))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(block, Rhenium)
                .inputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.NAQUADRIA_CHARGE))
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
                .fluidInputs(Titanium.getFluid(2500))
                .fluidInputs(NaquadahEnriched.getFluid(1500))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(1500000)
                .duration(50)
                .buildAndRegister();
    }
}
