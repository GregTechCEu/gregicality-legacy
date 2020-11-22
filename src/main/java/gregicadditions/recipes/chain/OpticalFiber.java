package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class OpticalFiber {

    public static void init() {

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Zirconium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(ZirconiumTetrafluoride.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Barium)
                .fluidInputs(Fluorine.getFluid(2000))
                .outputs(BariumDifluoride.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Lanthanum)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(LanthanumTrifluoride.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Aluminium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Erbium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(ErbiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZirconiumTetrafluoride.getItemStack(18))
                .inputs(BariumDifluoride.getItemStack(7))
                .inputs(LanthanumTrifluoride.getItemStack(2))
                .inputs(AluminiumTrifluoride.getItemStack(1))
                .input(dust, SodiumFluoride, 7)
                .outputs(ZBLANDust.getItemStack(35))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZBLANDust.getItemStack(1))
                .inputs(ErbiumTrifluoride.getItemStack(1))
                .outputs(ErbiumDopedZBLANDust.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(262000).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ZBLANDust.getItemStack(1))
                .outputs(ZBLAN.getStackForm(1))
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(262000).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ErbiumDopedZBLANDust.getItemStack(1))
                .outputs(ERBIUM_DOPED_ZBLAN.getStackForm(1))
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .notConsumable(SHAPE_MOLD_INGOT)
                .inputs(ZBLAN.getStackForm(1))
                .outputs(ZBLAN_INGOT.getStackForm(1))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(10000)
                .fluidInputs(Oxygen.getFluid(1000))
                .inputs(ZBLAN_INGOT.getStackForm(1))
                .outputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm(1))
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .notConsumable(SHAPE_EXTRUDER_WIRE)
                .inputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm(1))
                .outputs(ZBLAN_FIBER.getStackForm(2))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .inputs(ZBLAN_FIBER.getStackForm(1))
                .inputs(ERBIUM_DOPED_ZBLAN.getStackForm(1))
                .outputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(1))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(8).duration(150)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(1))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("ga_optical_double", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()));
        ModHandler.addShapelessRecipe("ga_optical_quad", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()));
        ModHandler.addShapelessRecipe("ga_optical_octal", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()));
        ModHandler.addShapelessRecipe("ga_optical_hex", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberHex.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()));
    }
}
