package gregicadditions.recipes;

import gregicadditions.recipes.categories.*;
import gregicadditions.recipes.categories.RecipeOverride;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class GARecipeAddition {

    public static void init() {

        RecipeOverride.init();
        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MaterialIteration.init();
        AlloyRecipes.init();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(new ItemStack(Items.GLOWSTONE_DUST, 4))
                .outputs(new ItemStack(Blocks.GLOWSTONE))
                .buildAndRegister();

        //Pyrolyse Oven Recipes
        PYROLYSE_RECIPES.recipeBuilder().duration(640).EUt(64)
                .inputs(new ItemStack(Items.SUGAR, 23))
                .circuitMeta(1)
                .outputs(OreDictUnifier.get(dust, Charcoal, 12))
                .fluidOutputs(Water.getFluid(1500))
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder().duration(200).EUt(10)
                .inputs(PLANT_BALL.getStackForm())
                .circuitMeta(2)
                .fluidInputs(Water.getFluid(1500))
                .chancedOutput(PLANT_BALL.getStackForm(), 1000, 100)
                .fluidOutputs(FermentedBiomass.getFluid(1500))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(300).duration(880)
                .input(dust, Beryllium)
                .input(dust, Potassium, 4)
                .fluidInputs(Nitrogen.getFluid(5000))
                .outputs(OreDictUnifier.get(dust, EnderPearl, 10))
                .buildAndRegister();

        // Snow
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .input(block, Snow)
                .outputs(OreDictUnifier.get(dust, Snow, 4))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(2).duration(200)
                .input(dust, Snow, 4)
                .outputs(OreDictUnifier.get(block, Snow, 4))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(3000).blastFurnaceTemp(2500)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(2))
                .notConsumable(Argon.getFluid(0))
                .outputs(SiliconCarbide.getItemStack(2))
                .buildAndRegister();

        IMPLOSION_RECIPES.recipeBuilder().EUt(30).duration(20).explosivesAmount(48)
                .input(dust, Diamond, 4)
                .output(gem, Diamond, 3)
                .output(dustTiny, DarkAsh, 2)
                .buildAndRegister();

        // Redstone and glowstone Fluid Extraction
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32)
                .input(dust, Redstone)
                .fluidOutputs(Redstone.getFluid(144)).
                buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32)
                .input(dust, Glowstone)
                .fluidOutputs(Glowstone.getFluid(144))
                .buildAndRegister();
    }
}
