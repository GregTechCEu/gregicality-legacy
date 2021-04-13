package gregicadditions.recipes.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GoldChain {

    public static void init() {

        /* Gold Chain Attempt #4
         *
         * This chain has undergone a very careful re-balance to make it more rewarding and less intimidating.
         *
         * The main stages of the chain:
         *
         * - Precious Metal Ingot: Smelts to 1 nugget of gold
         * - Gold Alloy: Smelts to 2 nuggets of gold, 8 nuggets total (almost 1 ingot) per Precious Metal
         * - Gold Leach: Smelts to 4 nuggets of gold, 16 total (almost 2 ingots) per Precious Metal
         * - Chloroauric Acid: The step creating this outputs the byproducts, and returns the Copper from Gold Alloy
         * - Chloroauric Acid: Processes into 3 ingots
         *
         * In the end, each step has a major compensation, but each step is reasonable return.
         * STEP 1: +7 nuggets of gold
         * STEP 2: +8 nuggets of gold
         * STEP 3: +byproducts and Copper no longer voided
         * STEP 4: +10 nuggets of gold
         *
         * Everything else in this chain is a closed loop. However, 1 Oxygen and 1 Hydrogen in addition to
         * the Copper will be lost if the player chooses to cut short at Gold Leach (step 2).
         */

        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, PreciousMetal), OreDictUnifier.get(nugget, Gold));
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, GoldAlloy), OreDictUnifier.get(nugget, Gold, 2));
        ModHandler.addSmeltingRecipe(GoldLeach.getItemStack(), OreDictUnifier.get(nugget, Gold, 4));

        // STEP 1
        // Au? + 3Cu -> Cu3Au?
        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, PreciousMetal)
                .input(dust, Copper, 3)
                .output(ingot, GoldAlloy, 4)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(ingot, PreciousMetal)
                .input(dust, Copper, 3)
                .output(ingot, GoldAlloy, 4)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, PreciousMetal)
                .input(ingot, Copper, 3)
                .output(ingot, GoldAlloy, 4)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(ingot, PreciousMetal)
                .input(ingot, Copper, 3)
                .output(ingot, GoldAlloy, 4)
                .buildAndRegister();

        // STEP 2
        // Cu3Au? + HNO3 -> Cu3Au?(OH) + NO2
        CHEMICAL_RECIPES.recipeBuilder().duration(80)
                .input(ingot, GoldAlloy, 4)
                .fluidInputs(NitricAcid.getFluid(1000))
                .outputs(GoldLeach.getItemStack(4))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        // STEP 3
        // Cu3Au?(OH) + HCl -> HAuCl(OH) + Cu3?
        CHEMICAL_RECIPES.recipeBuilder().duration(80)
                .inputs(GoldLeach.getItemStack(4))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(CopperLeach.getItemStack(4))
                .fluidOutputs(ChloroauricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // STEP 3.5
        // This step regains the Copper, and also has many other things as chanced outputs from
        // older iterations of this Gold Process
        // Cu3? -> 3Cu + Fe + Ni + Ag + Pb
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(80)
                .inputs(CopperLeach.getItemStack(4))
                .output(dust, Copper, 3)
                .chancedOutput(OreDictUnifier.get(dust, Lead), 1500, 500)
                .chancedOutput(OreDictUnifier.get(dust, Iron), 1200, 500)
                .chancedOutput(OreDictUnifier.get(dust, Nickel), 1000, 500)
                .chancedOutput(OreDictUnifier.get(dust, Silver), 800, 500)
                .buildAndRegister();

        // NOT CONSUMED INGREDIENT
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .notConsumable(new IntCircuitIngredient(1))
                .input(dust, Potassium, 2)
                .input(dust, Sulfur, 2)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust, PotassiumMetabisulfite, 9)
                .buildAndRegister();

        // STEP 4
        // HAuCl(OH) -> Au + H2O + Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .fluidInputs(ChloroauricAcid.getFluid(1000))
                .notConsumable(dust, PotassiumMetabisulfite)
                .output(dust, Gold, 3) // 3 per ore, maybe change
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .buildAndRegister();
    }
}
