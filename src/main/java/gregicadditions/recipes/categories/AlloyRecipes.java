package gregicadditions.recipes.categories;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.BLAST_ALLOY_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class AlloyRecipes {

    public static void init() {

        // Manual Potin Dust
        ModHandler.addShapelessRecipe("ga_potin_dust", OreDictUnifier.get(dust, Potin, 5),
                new UnificationEntry(dust, Lead),
                new UnificationEntry(dust, Lead),
                new UnificationEntry(dust, Bronze),
                new UnificationEntry(dust, Bronze),
                new UnificationEntry(dust, Tin));

        // Staballoy Dust
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .input(dust, UraniumRadioactive.getMaterial(), 9)
                .input(dust, Titanium, 1)
                .output(dust, Staballoy, 10)
                .buildAndRegister();

        // Reactor Steel
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(12000).EUt(120)
                .notConsumable(new IntCircuitIngredient(5))
                .input(dust, Iron, 15)
                .input(dust, Niobium, 1)
                .input(dust, Vanadium, 4)
                .input(dust, Carbon, 2)
                .fluidInputs(Argon.getFluid(1000))
                .fluidOutputs(ReactorSteel.getFluid(L * 22))
                .buildAndRegister();

        // Quantum Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(10500).EUt(30)
                .input(dust, Stellite, 15)
                .input(dust, Jasper, 5)
                .input(dust, Gallium, 5)
                .input(dust, Americium241.getMaterial(), 5)
                .input(dust, Palladium, 5)
                .input(dust, Bismuth, 5)
                .input(dust, Germanium, 5)
                .inputs(SiliconCarbide.getItemStack(5))
                .output(dust, Quantum, 50)
                .buildAndRegister();

        // Eglin Steel
        MIXER_RECIPES.recipeBuilder().EUt(32).duration(100)
                .input(dust, Iron, 4)
                .input(dust, Kanthal)
                .input(dust, Invar, 5)
                .output(dust, EglinSteelBase, 10)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().EUt(128).duration(100)
                .input(dust, Iron, 4)
                .input(dust, Kanthal)
                .input(dust, Invar, 5)
                .input(dust, Sulfur)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(6))
                .output(dust, EglinSteel, 13)
                .buildAndRegister();

        // Incoloy MA956
        LARGE_MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(4))
                .input(dust, Iron, 16)
                .input(dust, Aluminium, 3)
                .input(dust, Chrome, 5)
                .input(dust, Yttrium, 1)
                .outputs(OreDictUnifier.get(dust, IncoloyMA956, 25))
                .EUt(500)
                .duration(100)
                .buildAndRegister();

        // BLAST ALLOY RECIPES =========================================================================================

        // Soldering Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(775).EUt(1200)
                .input(dust, Tin, 9)
                .input(dust, Antimony)
                .fluidOutputs(SolderingAlloy.getFluid(L * 10))
                .buildAndRegister();

        // Red Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(473).EUt(240)
                .input(dust, Redstone, 3)
                .input(dust, Copper)
                .fluidOutputs(RedAlloy.getFluid(L * 4))
                .buildAndRegister();

        // Magnalium
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(320).EUt(360)
                .input(dust, Aluminium, 2)
                .input(dust, Magnesium)
                .fluidOutputs(Magnalium.getFluid(L * 3))
                .buildAndRegister();

        // Tin Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(556).EUt(174)
                .input(dust, Tin)
                .input(dust, Iron)
                .fluidOutputs(TinAlloy.getFluid(L * 2))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(556).EUt(174)
                .input(dust, Tin)
                .input(dust, WroughtIron)
                .fluidOutputs(TinAlloy.getFluid(L * 2))
                .buildAndRegister();

        // Battery Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(512).EUt(600)
                .input(dust, Lead, 4)
                .input(dust, Antimony)
                .fluidOutputs(BatteryAlloy.getFluid(L * 5))
                .buildAndRegister();
    }
}
