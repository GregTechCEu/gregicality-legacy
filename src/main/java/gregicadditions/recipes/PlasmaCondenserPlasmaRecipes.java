package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregtech.api.GTValues;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;

import static gregicadditions.recipes.GARecipeMaps.PLASMA_CONDENSER_RECIPES;

public class PlasmaCondenserPlasmaRecipes {

    public static void init() {
        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Argon.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Argon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Argon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Helium.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Helium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Nitrogen.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Nitrogen.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Nitrogen.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Oxygen.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Oxygen.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Oxygen.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Radon.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Radon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Radon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Krypton.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Krypton.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) GAMaterials.Krypton.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Neon.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Neon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) GAMaterials.Neon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.HeliumCNO.getPlasma(100))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.HeliumCNO.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Helium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Iron52.getPlasma(GTValues.L))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Iron52.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Iron.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Chromium48.getPlasma(GTValues.L))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Chromium48.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Chrome.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Titanium44.getPlasma(GTValues.L))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Titanium44.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Titanium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.Nickel56.getPlasma(GTValues.L))
                .fluidInputs(GAMaterials.LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.Nickel56.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Titanium.getAverageMass()).EUt(960).buildAndRegister();
    }
}
