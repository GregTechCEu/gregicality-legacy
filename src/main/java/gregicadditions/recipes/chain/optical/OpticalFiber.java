package gregicadditions.recipes.chain.optical;

import gregicadditions.GAEnums;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class OpticalFiber {

    public static void init() {

        // Zr + 4F -> ZrF4
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Zirconium)
                .fluidInputs(Fluorine.getFluid(4000))
                .outputs(ZirconiumTetrafluoride.getItemStack(5))
                .buildAndRegister();

        // Ba + 2F -> BaF2
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Barium)
                .fluidInputs(Fluorine.getFluid(2000))
                .outputs(BariumDifluoride.getItemStack(3))
                .buildAndRegister();

        // La + 3F -> LaF3
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Lanthanum)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(LanthanumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // Al + 3F -> AlF3
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Aluminium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // Er + 3F -> ErF3
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Erbium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(ErbiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // 18ZrF4 + 7BaF2 + 2LaF3 + AlF3 + 7NaF -> ZBLAN
        LARGE_MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZirconiumTetrafluoride.getItemStack(90))
                .inputs(BariumDifluoride.getItemStack(21))
                .inputs(LanthanumTrifluoride.getItemStack(8))
                .inputs(AluminiumTrifluoride.getItemStack(4))
                .input(dust, SodiumFluoride, 14)
                .outputs(ZBLANDust.getItemStack(35))
                .buildAndRegister();

        // ZBLAN + ErF3 = [ZBLAN + ErF3]
        MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZBLANDust.getItemStack())
                .inputs(ErbiumTrifluoride.getItemStack(4))
                .outputs(ErbiumDopedZBLANDust.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(262000).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ZBLANDust.getItemStack())
                .outputs(ZBLAN.getStackForm())
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(262000).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ErbiumDopedZBLANDust.getItemStack())
                .outputs(ERBIUM_DOPED_ZBLAN.getStackForm())
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .notConsumable(SHAPE_MOLD_INGOT)
                .inputs(ZBLAN.getStackForm())
                .outputs(ZBLAN_INGOT.getStackForm())
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().EUt(120).duration(10000)
                .fluidInputs(Oxygen.getFluid(1000))
                .inputs(ZBLAN_INGOT.getStackForm())
                .outputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm())
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .notConsumable(SHAPE_EXTRUDER_WIRE)
                .inputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm())
                .outputs(ZBLAN_FIBER.getStackForm(2))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .inputs(ZBLAN_FIBER.getStackForm())
                .inputs(ERBIUM_DOPED_ZBLAN.getStackForm())
                .outputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(8).duration(150)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm())
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("ga_optical_double", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberSingle.toString()));
        ModHandler.addShapelessRecipe("ga_optical_quad", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberDouble.toString()));
        ModHandler.addShapelessRecipe("ga_optical_octal", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberQuadruple.toString()));
        ModHandler.addShapelessRecipe("ga_optical_hex", OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberHex.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()), OreDictUnifier.get(GAEnums.GAOrePrefix.opticalFiberOctal.toString()));
    }
}
