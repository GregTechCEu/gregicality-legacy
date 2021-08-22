package gregicadditions.recipes.categories;

import gregtech.api.GTValues;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.PLASMA_CONDENSER_RECIPES;
import static gregtech.api.unification.material.Materials.*;

public class PlasmaCondenserPlasmaRecipes {

    public static void init() {
        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Argon.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Argon.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Argon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Helium.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Helium.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Helium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Nitrogen.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Nitrogen.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Nitrogen.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Oxygen.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Oxygen.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Oxygen.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Radon.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Radon.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Radon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Krypton.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Krypton.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Krypton.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Neon.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Neon.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Neon.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(HeliumCNO.getPlasma(100))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(HeliumCNO.getFluid(100))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Helium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Iron52.getPlasma(GTValues.L))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Iron52.getFluid(GTValues.L))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Iron.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Chromium48.getPlasma(GTValues.L))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Chromium48.getFluid(GTValues.L))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Chrome.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Titanium44.getPlasma(GTValues.L))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Titanium44.getFluid(GTValues.L))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Titanium.getAverageMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Nickel56.getPlasma(GTValues.L))
                .fluidInputs(LiquidHelium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Nickel56.getFluid(GTValues.L))
                .fluidOutputs(Helium.getFluid(100))
                .duration((int) Titanium.getAverageMass()).EUt(960).buildAndRegister();
    }
}
