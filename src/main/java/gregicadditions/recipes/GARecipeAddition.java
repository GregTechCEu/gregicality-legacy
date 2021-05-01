package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.recipes.chain.*;
import gregicadditions.recipes.chain.optical.Lasers;
import gregicadditions.recipes.chain.optical.OpticalCircuits;
import gregicadditions.recipes.chain.optical.OpticalComponents;
import gregicadditions.recipes.chain.optical.OpticalFiber;
import gregicadditions.recipes.chain.wetware.*;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class GARecipeAddition {

    public static void init() {

        // TODO This should probably not be done here
        OreDictUnifier.registerOre(new ItemStack(Items.SNOWBALL), dust, Snow);
        OreDictUnifier.registerOre(new ItemStack(Blocks.SNOW), block, Snow);
        OreDictionary.registerOre("formWood", WOODEN_FORM_BRICK.getStackForm());

        ComponentRecipes.init();
        MetaItemRecipes.init();
        VanillaOverride.init();
        CasingRecipes.init();

        // Wrought Iron
        ModHandler.addSmeltingRecipe(new UnificationEntry(ingot, Iron), HOT_IRON_INGOT.getStackForm());
        ModHandler.addShapelessRecipe("ga_wrought", OreDictUnifier.get(ingot, WroughtIron), 'h', HOT_IRON_INGOT.getStackForm());
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(16)
                .inputs(HOT_IRON_INGOT.getStackForm())
                .outputs(OreDictUnifier.get(ingot, WroughtIron))
                .buildAndRegister();

        // Seed Oil
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input("listAllSeed", 1)
                .fluidOutputs(Materials.SeedOil.getFluid(10))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllmushroom", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllfruit", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllveggie", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllspice", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllgrain", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllnut", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllpepper", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllherb", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllfiber", 8)
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(BrownAlgae.getItemStack(8))
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(RedAlgae.getItemStack(8))
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(GreenAlgae.getItemStack(8))
                .outputs(MetaItems.PLANT_BALL.getStackForm())
                .buildAndRegister();


        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(80)
                .fluidInputs(Glass.getFluid(144))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(GLASS_TUBE.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(new ItemStack(Items.GLOWSTONE_DUST, 4))
                .outputs(new ItemStack(Blocks.GLOWSTONE))
                .buildAndRegister();

        //GTNH Bricks TODO OVERRIDE
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.removeFurnaceSmelting(COMPRESSED_CLAY.getStackForm());
        ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2)
                .inputs(new ItemStack(Items.CLAY_BALL))
                .notConsumable(SHAPE_MOLD_INGOT)
                .outputs(new ItemStack(Items.BRICK))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("clay_brick", COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.CLAY_BALL), "formWood");

        ModHandler.addShapedRecipe("eight_clay_brick", COMPRESSED_CLAY.getStackForm(8),
                "BBB", "BFB", "BBB",
                'B', new ItemStack(Items.CLAY_BALL),
                'F', "formWood");

        ModHandler.addShapedRecipe("coke_brick", COMPRESSED_COKE_CLAY.getStackForm(3),
                "BBB", "SFS", "SSS",
                'B', new ItemStack(Items.CLAY_BALL),
                'S', new ItemStack(Blocks.SAND),
                'F', "formWood");

        ModHandler.addSmeltingRecipe(COMPRESSED_COKE_CLAY.getStackForm(), COKE_OVEN_BRICK.getStackForm());

        //GT5U Old Primitive Brick Processing
        ModHandler.addShapedRecipe("quartz_sand", OreDictUnifier.get(dust, GAMaterials.QuartzSand),
                "S", "m",
                'S', "sand");

        MACERATOR_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input("sand", 1)
                .outputs(OreDictUnifier.get(dust, GAMaterials.QuartzSand))
                .chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2500, 500)
                .chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2000, 500)
                .buildAndRegister();

        ModHandler.addShapelessRecipe("glass_dust_ga", OreDictUnifier.get(dust, Glass), "dustSand", "dustFlint");

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input(dust, Flint)
                .input(dust, GAMaterials.QuartzSand, 4)
                .outputs(OreDictUnifier.get(dust, Glass, 4))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(160).EUt(8)
                .input(dust, Flint)
                .input(dust, Quartzite, 4)
                .outputs(OreDictUnifier.get(dust, Glass, 4))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(dust, Calcite, 2)
                .input(dust, Stone)
                .input(dust, Clay)
                .input(dust, GAMaterials.QuartzSand)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Concrete.getFluid(2304))
                .buildAndRegister();

        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), RUBBER_DROP.getStackForm());

        FORGE_HAMMER_RECIPES.recipeBuilder().duration(16).EUt(10)
                .inputs(new ItemStack(Items.BONE))
                .outputs(new ItemStack(Items.DYE, 4, 15))
                .buildAndRegister();

        //wood pipe
        if (GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipes(OreDictUnifier.get(pipeSmall, Wood, 4));
            ModHandler.removeRecipes(OreDictUnifier.get(pipeMedium, Wood, 2));

            ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(pipeMedium, Wood, 2), "PPP", "sCh", "PPP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood), "PhP", "PCP", "PsP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(pipeSmall, Wood, 4), "PsP", "PCP", "PhP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");
        }

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


        // Liquid Air Distillation
        DISTILLATION_RECIPES.recipeBuilder().duration(7500).EUt(1920)
                .fluidInputs(LiquidAir.getFluid(100000))
                .fluidOutputs(Nitrogen.getFluid(78000))
                .fluidOutputs(Oxygen.getFluid(20000))
                .fluidOutputs(Argon.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(500))
                .fluidOutputs(Neon.getFluid(100))
                .fluidOutputs(Helium.getFluid(50))
                .fluidOutputs(Methane.getFluid(20))
                .fluidOutputs(Krypton.getFluid(10))
                .fluidOutputs(Hydrogen.getFluid(5))
                .fluidOutputs(Xenon.getFluid(1))
                .buildAndRegister();

        // Fish Oil
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.FISH))
                .fluidOutputs(FishOil.getFluid(40))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.FISH, 1, 1))
                .fluidOutputs(FishOil.getFluid(60))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.FISH, 1, 2))
                .fluidOutputs(FishOil.getFluid(70))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.FISH, 1, 3))
                .fluidOutputs(FishOil.getFluid(30))
                .buildAndRegister();

        //Mince Meat Recipes
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.PORKCHOP))
                .outputs(OreDictUnifier.get(dustSmall, Meat, 6))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.BEEF))
                .outputs(OreDictUnifier.get(dustSmall, Meat, 6))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.RABBIT))
                .outputs(OreDictUnifier.get(dustSmall, Meat, 6))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.CHICKEN))
                .outputs(OreDictUnifier.get(dust, Meat))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.MUTTON))
                .outputs(OreDictUnifier.get(dust, Meat))
                .buildAndRegister();

        //Ash-Related Recipes
        CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6)
                .input(dust, DarkAsh)
                .outputs(OreDictUnifier.get(dust, Ash))
                .outputs(OreDictUnifier.get(dust, Carbon))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30)
                .input(dust, Ash)
                .chancedOutput(OreDictUnifier.get(dustSmall, Quicklime, 2), 9900, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, Potash), 6400, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, Magnesia), 6000, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, PhosphorousPentoxide), 500, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, SodaAsh), 5000, 0)
                .buildAndRegister();

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
                .outputs(OreDictUnifier.get(dust, Staballoy, 10))
                .buildAndRegister();

        // Reactor Steel
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(12000).EUt(120)
                .notConsumable(new IntCircuitIngredient(5))
                .input(dust, Iron, 15)
                .input(dust, Niobium, 1)
                .input(dust, Vanadium, 4)
                .input(dust, Carbon, 2)
                .fluidInputs(Argon.getFluid(1000))
                .fluidOutputs(ReactorSteel.getFluid(GTValues.L * 22))
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
                .outputs(OreDictUnifier.get(dust, Quantum, 50))
                .buildAndRegister();

        //Pyrotheum
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Coal)
                .input(dust, Sulfur)
                .fluidInputs(Lava.getFluid(100))
                .outputs(OreDictUnifier.get(dust, Blaze, 2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Charcoal)
                .input(dust, Sulfur)
                .fluidInputs(Lava.getFluid(100))
                .outputs(OreDictUnifier.get(dust, Blaze, 2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .input(dust, Blaze, 2)
                .outputs(OreDictUnifier.get(dust, Pyrotheum, 2))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Pyrotheum)
                .fluidOutputs(Pyrotheum.getFluid(250))
                .buildAndRegister();

        //Cryotheum
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Snow)
                .fluidInputs(Redstone.getFluid(144))
                .outputs(OreDictUnifier.get(dust, Blizz, 2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Snow)
                .input(dust, Blizz, 2)
                .outputs(OreDictUnifier.get(dust, Cryotheum, 2))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Cryotheum)
                .fluidOutputs(Cryotheum.getFluid(250))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .duration(200)
                .EUt(491520)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Cryotheum.getFluid(10000))
                .fluidOutputs(SupercooledCryotheum.getFluid(10000))
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

        GAMachineRecipeRemoval.removeAllRecipes(BREWING_RECIPES);

        // Biomass
        // Honey
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
                .fluidInputs(Honey.getFluid(180))
                .fluidOutputs(Biomass.getFluid(270))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(600).EUt(3)
                .input("treeSapling", 1)
                .fluidInputs(Honey.getFluid(100))
                .fluidOutputs(Biomass.getFluid(150))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.POTATO))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.CARROT))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.CACTUS))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.REEDS))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.RED_MUSHROOM))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.BEETROOT))
                .fluidInputs(Honey.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        // Juice
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
                .fluidInputs(Juice.getFluid(180))
                .fluidOutputs(Biomass.getFluid(270))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(600).EUt(3).input("treeSapling", 1)
                .fluidInputs(Juice.getFluid(100))
                .fluidOutputs(Biomass.getFluid(150))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.POTATO))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.CARROT))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.CACTUS))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.REEDS))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Blocks.RED_MUSHROOM))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3)
                .inputs(new ItemStack(Items.BEETROOT))
                .fluidInputs(Juice.getFluid(20))
                .fluidOutputs(Biomass.getFluid(30))
                .buildAndRegister();

        // Water
        BREWING_RECIPES.recipeBuilder().duration(800).EUt(3).input("treeSapling", 1)
                .fluidInputs(Water.getFluid(100))
                .fluidOutputs(Biomass.getFluid(100))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Items.POTATO))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Items.CARROT))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Blocks.CACTUS))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Items.REEDS))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Blocks.RED_MUSHROOM))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3)
                .inputs(new ItemStack(Items.BEETROOT))
                .fluidInputs(Water.getFluid(20))
                .fluidOutputs(Biomass.getFluid(20))
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

        // Recipes Needing Configuration Circuits
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Aluminium));

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(884).blastFurnaceTemp(1700)
                .input(dust, Aluminium)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingot, Aluminium)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(10980).blastFurnaceTemp(3000)
                .input(dust, Tungsten)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingotHot, Tungsten)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(900).blastFurnaceTemp(2500)
                .input(dust, LithiumTitanate)
                .notConsumable(new IntCircuitIngredient(0))
                .output(ingotHot, LithiumTitanate)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Dichlorobenzene.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(20).EUt(16)
                .input(OrePrefix.dust, Materials.Clay, 1)
                .input(OrePrefix.dust, Materials.Stone, 3)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Materials.Water.getFluid(500))
                .fluidOutputs(Materials.Concrete.getFluid(576))
                .buildAndRegister();

        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)});

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(AllylChloride.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(Epichlorhydrin.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();
    }

    public static void init2() {
        // Diesel
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(120)
                .fluidInputs(LightFuel.getFluid(5000))
                .fluidInputs(HeavyFuel.getFluid(1000))
                .fluidOutputs(Fuel.getFluid(6000))
                .buildAndRegister();

        // High Octane Gasoline
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(1920)
                .fluidInputs(NitricOxide.getFluid(6000))
                .fluidInputs(Gasoline.getFluid(20000))
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Octane.getFluid(2000))
                .fluidInputs(EthylTertButylEther.getFluid(3000))
                .fluidOutputs(HighOctaneGasoline.getFluid(32000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(10).EUt(480)
                .fluidInputs(RawGasoline.getFluid(10000))
                .fluidInputs(Toluene.getFluid(1000))
                .fluidOutputs(Gasoline.getFluid(11000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .fluidInputs(Naphtha.getFluid(16000))
                .fluidInputs(Gas.getFluid(2000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(RawGasoline.getFluid(20000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(480)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(Butane.getFluid(1000))
                .fluidOutputs(EthylTertButylEther.getFluid(2000))
                .buildAndRegister();


         // Octane Distillation
        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(HydroCrackedLightFuel.getFluid(1000))
                .fluidOutputs(Naphtha.getFluid(750))
                .fluidOutputs(Propane.getFluid(200))
                .fluidOutputs(Butane.getFluid(150))
                .fluidOutputs(Ethane.getFluid(125))
                .fluidOutputs(Methane.getFluid(125))
                .fluidOutputs(Octane.getFluid(50))
                .buildAndRegister();

        // Cyclopentadiene Distillation
        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(SteamCrackedNaphtha.getFluid(1000))
                .fluidOutputs(HeavyFuel.getFluid(25))
                .fluidOutputs(LightFuel.getFluid(50))
                .fluidOutputs(Toluene.getFluid(20))
                .fluidOutputs(Benzene.getFluid(100))
                .fluidOutputs(Butene.getFluid(50))
                .fluidOutputs(Butadiene.getFluid(50))
                .fluidOutputs(Propane.getFluid(15))
                .fluidOutputs(Propene.getFluid(300))
                .fluidOutputs(Ethane.getFluid(65))
                .fluidOutputs(Ethylene.getFluid(500))
                .fluidOutputs(Methane.getFluid(500))
                .fluidOutputs(Cyclopentadiene.getFluid(75))
                .buildAndRegister();

        // Rocket Fuel Tier T4
        // C2H8N2 + N2O4 = H8N4C2O4 (treat like chem reactor recipes)
        // TODO CONFLICT WITH GTCE
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(Dimethylhydrazine.getFluid(1000))
                .fluidInputs(DinitrogenTetroxide.getFluid(1000))
                .fluidOutputs(RocketFuelH8N4C2O4.getFluid(1000))
                .buildAndRegister();

        // TODO Remove this recipe one release after Chem Fixes 2 PR is merged
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(Dimethylhydrazine.getFluid(1000))
                .fluidInputs(NitrogenTetroxide.getFluid(1000))
                .fluidOutputs(RocketFuelH8N4C2O4.getFluid(1000))
                .buildAndRegister();

        // 2NO2 = N2O4
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .notConsumable(dust, Copper)
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(DinitrogenTetroxide.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 3
        // HNO3 + CH3(NH)NH2 = CN3H7O3 (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(240)
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(MonoMethylHydrazine.getFluid(1000))
                .fluidOutputs(RocketFuelCN3H7O3.getFluid(1000))
                .buildAndRegister();

        // C + 2H + N2H4 = CH3(NH)NH2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(480).EUt(240)
                .input(dust, Carbon)
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidOutputs(MonoMethylHydrazine.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 2
        // N2H4 + CH3OH = [N2H4 + CH3OH] (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(120).EUt(240)
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(DenseHydrazineFuelMixture.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 1
        // O + RP-1 = [O + RP-1] (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240)
                .fluidInputs(LiquidOxygen.getFluid(1000))
                .fluidInputs(RP1.getFluid(1000))
                .fluidOutputs(RP1RocketFuel.getFluid(1000))
                .buildAndRegister();

        //Rocket fuel chemicals
        // 2NH3 + H2O2 = N2H4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C16H12O2H2 + 2O + C14H10 (catalyst) = H2O2 + C16H12O2
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(240)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidInputs(EthylAnthraHydroQuinone.getFluid(1000))
                .notConsumable(Anthracene)
                .fluidOutputs(HydrogenPeroxide.getFluid(2000))
                .fluidOutputs(EthylAnthraQuinone.getFluid(1000))
                .buildAndRegister();

        // 2H + C16H12O2 = C16H12O2H2
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(120)
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidInputs(EthylAnthraQuinone.getFluid(1000))
                .fluidOutputs(EthylAnthraHydroQuinone.getFluid(1000))
                .buildAndRegister();

        // C8H4O3 + C8H10 = C16H12O2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(EthylBenzene.getFluid(1000))
                .fluidOutputs(EthylAnthraQuinone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C2H4 + C6H6 = C8H10
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30)
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(EthylBenzene.getFluid(1000))
                .buildAndRegister();

        // C8H6O4 = C8H4O3 + H2O (H2O voided - Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(1200).EUt(120)
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PhthalicAnhydride, 15))
                .buildAndRegister();

        // 21O + 4 C10H8 = 5C8H6O4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30)
                .notConsumable(dust, Lithium)
                .fluidInputs(Oxygen.getFluid(21000))
                .fluidInputs(Naphtalene.getFluid(4000))
                .fluidOutputs(PhthalicAcid.getFluid(5000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(LiquidOxygen.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(16).EUt(540)
                .fluidInputs(Hydrogen.getFluid(500))
                .fluidOutputs(LiquidHydrogen.getFluid(500))
                .buildAndRegister();

        //Coal Tar Byproducts
        PYROLYSE_RECIPES.recipeBuilder().duration(1080).EUt(60)
                .notConsumable(new IntCircuitIngredient(20))
                .input(gem, Lignite, 16)
                .outputs(OreDictUnifier.get(dust, Ash, 4))
                .fluidOutputs(CoalTar.getFluid(800))
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder().duration(360).EUt(120)
                .notConsumable(new IntCircuitIngredient(20))
                .input(gem, Charcoal, 32)
                .outputs(OreDictUnifier.get(dust, Ash, 4))
                .fluidOutputs(CoalTar.getFluid(800))
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder().duration(720).EUt(120)
                .notConsumable(new IntCircuitIngredient(20))
                .input(gem, Coal, 12)
                .outputs(OreDictUnifier.get(dust, Ash, 4))
                .fluidOutputs(CoalTar.getFluid(2200))
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder().duration(360).EUt(240)
                .notConsumable(new IntCircuitIngredient(20))
                .input(gem, Coke, 8)
                .outputs(OreDictUnifier.get(dust, Ash, 4))
                .fluidOutputs(CoalTar.getFluid(3400))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(900).EUt(60)
                .fluidInputs(CoalTar.getFluid(1000))
                .fluidOutputs(CoalTarOil.getFluid(500))
                .fluidOutputs(Kerosene.getFluid(200))
                .fluidOutputs(EthylBenzene.getFluid(150))
                .fluidOutputs(Naphtha.getFluid(100))
                .fluidOutputs(Anthracene.getFluid(50))
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Kerosene.getFluid(50))
                .fluidOutputs(RP1.getFluid(25))
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder().duration(80).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(SulfuricCoalTarOil.getFluid(50))
                .fluidOutputs(Naphtalene.getFluid(50))
                .buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder().duration(5).EUt(480)
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(SulfuricCoalTarOil.getFluid(800))
                .fluidOutputs(Naphtalene.getFluid(800))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(30)
                .fluidInputs(SulfuricAcid.getFluid(8000))
                .fluidInputs(CoalTarOil.getFluid(8000))
                .fluidOutputs(SulfuricCoalTarOil.getFluid(16000))
                .buildAndRegister();


        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180)
                .fluidInputs(FermentedBiomass.getFluid(2000))
                .fluidOutputs(AceticAcid.getFluid(25))
                .fluidOutputs(Water.getFluid(375))
                .fluidOutputs(Ethanol.getFluid(250))
                .fluidOutputs(Methanol.getFluid(150))
                .fluidOutputs(Ammonia.getFluid(100))
                .fluidOutputs(CarbonDioxide.getFluid(400))
                .fluidOutputs(Methane.getFluid(600))
                .fluidOutputs(Butanol.getFluid(100))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180)
                .fluidInputs(FermentationBase.getFluid(2000))
                .fluidOutputs(AceticAcid.getFluid(50))
                .fluidOutputs(Ethanol.getFluid(600))
                .fluidOutputs(Methanol.getFluid(150))
                .fluidOutputs(Ammonia.getFluid(100))
                .fluidOutputs(CarbonDioxide.getFluid(400))
                .fluidOutputs(Methane.getFluid(600))
                .fluidOutputs(Butanol.getFluid(100))
                .buildAndRegister();

        // Nuclear Process Distillation
        // TODO: NUCLEAR REWORK: CHANGE FERRITE MIXTURE OUTPUT
        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180)
                .fluidInputs(RedOil.getFluid(4000))
                .outputs(OreDictUnifier.get(dust, FerriteMixture))
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(RP1.getFluid(1000))
                .fluidOutputs(TributylPhosphate.getFluid(1000))
                .buildAndRegister();

        //  3Cl + P = PCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(20)
                .fluidInputs(Chlorine.getFluid(3000))
                .input(dust, Phosphorus)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(PhosphorusTrichloride.getFluid(1000))
                .buildAndRegister();

        // PCl3 + O = POCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(PhosphorylChloride.getFluid(1000))
                .buildAndRegister();

        // POCl3 + 3C4H10O = C12H27O4P + 3HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .fluidInputs(PhosphorylChloride.getFluid(1000))
                .fluidInputs(Butanol.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(TributylPhosphate.getFluid(1000))
                .buildAndRegister();

        //Star Recipes
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680)
                .inputs(new ItemStack(Items.NETHER_STAR))
                .fluidInputs(Dubnium.getFluid(288))
                .outputs(GRAVI_STAR.getStackForm())
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(122880)
                .inputs(GRAVI_STAR.getStackForm())
                .fluidInputs(Adamantium.getFluid(288))
                .outputs(UNSTABLE_STAR.getStackForm())
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

        // Misc Blast Alloy Recipes
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(775).EUt(1200)
                .input(dust, Tin, 9)
                .input(dust, Antimony)
                .fluidOutputs(SolderingAlloy.getFluid(1440))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(473).EUt(240)
                .input(dust, Redstone)
                .input(dust, Copper)
                .fluidOutputs(RedAlloy.getFluid(288))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(320).EUt(360)
                .input(dust, Aluminium, 2)
                .input(dust, Magnesium)
                .fluidOutputs(Magnalium.getFluid(432))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(556).EUt(174)
                .input(dust, Tin)
                .input(dust, Iron)
                .fluidOutputs(TinAlloy.getFluid(288))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(556).EUt(174)
                .input(dust, Tin)
                .input(dust, WroughtIron)
                .fluidOutputs(TinAlloy.getFluid(288))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(512).EUt(600)
                .input(dust, Lead, 4)
                .input(dust, Antimony)
                .fluidOutputs(BatteryAlloy.getFluid(720))
                .buildAndRegister();

        //Add Missing Superconducter Wire Tiering Recipes TODO Are these duplicate?
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(24)
                .input(wireGtSingle, Tier.Superconductor, 1)
                .input(foil, PolyphenyleneSulfide, 1)
                .outputs(OreDictUnifier.get(cableGtSingle, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(25)
                .input(wireGtSingle, Tier.Superconductor, 2).duration(150).EUt(8)
                .input(foil, PolyphenyleneSulfide, 2)
                .outputs(OreDictUnifier.get(cableGtDouble, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(26)
                .input(wireGtSingle, Tier.Superconductor, 4).duration(150).EUt(8)
                .input(foil, PolyphenyleneSulfide, 4)
                .outputs(OreDictUnifier.get(cableGtQuadruple, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(27)
                .input(wireGtSingle, Tier.Superconductor, 8).duration(150).EUt(8)
                .input(foil, PolyphenyleneSulfide, 8)
                .outputs(OreDictUnifier.get(cableGtOctal, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(28)
                .input(wireGtSingle, Tier.Superconductor, 16)
                .input(foil, PolyphenyleneSulfide, 16)
                .outputs(OreDictUnifier.get(cableGtHex, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(24)
                .input(wireGtDouble, Tier.Superconductor)
                .input(foil, PolyphenyleneSulfide, 2)
                .outputs(OreDictUnifier.get(cableGtDouble, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(24)
                .input(wireGtQuadruple, Tier.Superconductor)
                .input(foil, PolyphenyleneSulfide, 4)
                .outputs(OreDictUnifier.get(cableGtQuadruple, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(24)
                .input(wireGtOctal, Tier.Superconductor)
                .input(foil, PolyphenyleneSulfide, 8)
                .outputs(OreDictUnifier.get(cableGtOctal, Tier.Superconductor))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                .circuitMeta(24)
                .input(wireGtHex, Tier.Superconductor)
                .input(foil, PolyphenyleneSulfide, 16)
                .outputs(OreDictUnifier.get(cableGtHex, Tier.Superconductor))
                .buildAndRegister();

        // TODO Do these have duplicates?
        ModHandler.addShapelessRecipe("superonducter_wire_gtsingle_doubling", OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_doubling", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_doubling", OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_doubling", OreDictUnifier.get(wireGtHex, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_splitting", OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 2), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_splitting", OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 2), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_splitting", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 2), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gthex_splitting", OreDictUnifier.get(wireGtOctal, Tier.Superconductor, 2), OreDictUnifier.get(wireGtHex, Tier.Superconductor));

        // Redstone and glowstone Fluid Extraction
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32)
                .input(dust, Redstone)
                .fluidOutputs(Redstone.getFluid(144)).
                buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32)
                .input(dust, Glowstone)
                .fluidOutputs(Glowstone.getFluid(144))
                .buildAndRegister();

        //Gem Tool Part Fixes
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(gem, material).isEmpty() && !OreDictUnifier.get(toolHeadHammer, material).isEmpty() && material != Flint) {
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadAxe, material));
                ModHandler.addShapedRecipe("axe_head_" + material.toString(), OreDictUnifier.get(toolHeadAxe, material), "GG", "Gf", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadFile, material));
                ModHandler.addShapedRecipe("file_head_" + material.toString(), OreDictUnifier.get(toolHeadFile, material), "G", "G", "f", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadHammer, material));
                ModHandler.addShapedRecipe("hammer_head_" + material.toString(), OreDictUnifier.get(toolHeadHammer, material), "GG ", "GGf", "GG ", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadHoe, material));
                ModHandler.addShapedRecipe("hoe_head_" + material.toString(), OreDictUnifier.get(toolHeadHoe, material), "GGf", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadPickaxe, material));
                ModHandler.addShapedRecipe("pickaxe_head_" + material.toString(), OreDictUnifier.get(toolHeadPickaxe, material), "GGG", "f  ", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadSaw, material));
                ModHandler.addShapedRecipe("saw_head_" + material.toString(), OreDictUnifier.get(toolHeadSaw, material), "GG", "f ", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadSense, material));
                ModHandler.addShapedRecipe("sense_head_" + material.toString(), OreDictUnifier.get(toolHeadSense, material), "GGG", " f ", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadShovel, material));
                ModHandler.addShapedRecipe("shovel_head_" + material.toString(), OreDictUnifier.get(toolHeadShovel, material), "fG", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadSword, material));
                ModHandler.addShapedRecipe("sword_head_" + material.toString(), OreDictUnifier.get(toolHeadSword, material), " G", "fG", 'G', new UnificationEntry(gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(toolHeadUniversalSpade, material));
                ModHandler.addShapedRecipe("universal_spade_head_" + material.toString(), OreDictUnifier.get(toolHeadUniversalSpade, material), "GGG", "GfG", " G ", 'G', new UnificationEntry(gem, material));
            }
        }

        //Misc Recipe Patches
        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, NetherQuartz)
                .outputs(OreDictUnifier.get(plate, NetherQuartz))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, CertusQuartz)
                .outputs(OreDictUnifier.get(plate, CertusQuartz))
                .buildAndRegister();

        //Dust Uncrafting Fixes
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(dustSmall, m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(dustSmall, m));
                ModHandler.addShapedRecipe("dust_small_" + m.toString(), OreDictUnifier.get(dustSmall, m, 4), " D", "  ", 'D', new UnificationEntry(dust, m));
            }
        }

        //GTNH Coils
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .input(dust, RawRubber, 2)
                .outputs(OreDictUnifier.get(dust, MicaPulp, 5))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .inputs(RUBBER_DROP.getStackForm())
                .outputs(OreDictUnifier.get(dust, MicaPulp, 4))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Sapphire)
                .input(dust, SiliconDioxide)
                .outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, GreenSapphire)
                .input(dust, SiliconDioxide)
                .outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Ruby)
                .input(dust, SiliconDioxide)
                .outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(28)
                .input(dust, MicaPulp, 4)
                .input(dust, Asbestos)
                .outputs(MICA_SHEET.getStackForm(5))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(30)
                .inputs(MICA_SHEET.getStackForm(5))
                .input(dust, SiliconDioxide, 3)
                .outputs(MICA_INSULATOR_SHEET.getStackForm(8))
                .buildAndRegister();

        if (GAConfig.GT6.BendingFoilsAutomatic) {
            CLUSTER_MILL_RECIPES.recipeBuilder().duration(100).EUt(30)
                    .inputs(MICA_INSULATOR_SHEET.getStackForm())
                    .outputs(MICA_INSULATOR_FOIL.getStackForm(4))
                    .buildAndRegister();
        } else {
            BENDER_RECIPES.recipeBuilder().duration(100).EUt(30)
                    .inputs(MICA_INSULATOR_SHEET.getStackForm())
                    .circuitMeta(0)
                    .outputs(MICA_INSULATOR_FOIL.getStackForm(4))
                    .buildAndRegister();
        }

        // Formic acid
        // CO + NaOH = HCOONa
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(15)
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(SodiumFormate.getFluid(1000))
                .buildAndRegister();

        // HCOONa + H2SO4 = CH2O2 + NaHSO4
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(15)
                .fluidInputs(SodiumFormate.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FormicAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumBisulfate, 7))
                .buildAndRegister();

        // 3Ca + 3PO4 + H + O = [3Ca + 3PO4 + H + O]
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(dust, Calcium, 3)
                .input(dust, Phosphate, 3) // this is probably wrong
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, OrganicFertilizer, 10))
                .buildAndRegister();

        // 2HCl + CaCO3 = H2O + CO2 + CaCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .input(dust, Calcite, 5)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, CalciumChloride, 3))
                .buildAndRegister();

        // ZrCl4 = Zr + 4Cl
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(ZirconiumTetrachloride.getItemStack(5))
                .outputs(OreDictUnifier.get(dust, Zirconium))
                .fluidOutputs(Chlorine.getFluid(4000))
                .buildAndRegister();

        // SiF4 = Si + 4F
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120)
                .fluidInputs(SiliconFluoride.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Silicon))
                .fluidOutputs(Fluorine.getFluid(4000))
                .buildAndRegister();

        // CF4 = C + 4F
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120)
                .fluidInputs(CarbonFluoride.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Carbon))
                .fluidOutputs(Fluorine.getFluid(4000))
                .buildAndRegister();

        // BaSO4 + H2O = [BaSO4 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64)
                .input(dust, Barite)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(BariumSulfateSolution.getFluid(1000))
                .buildAndRegister();

        // CaCO3 + H2O = [CaCO3 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64)
                .input(dust, Calcite)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CalciumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // Bentonite + Clay + 2H2O = 2[H2O + 0.5 Bentonite + 0.5 Clay]
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64)
                .input(dust, Bentonite)
                .input(dust, Clay)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(BentoniteClaySlurry.getFluid(2000))
                .buildAndRegister();

        // C2H4 + C6H6 -> 2H + C8H8
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Styrene.getFluid(1000))
                .buildAndRegister();

        // NH3 + 2CH3OH -> 2H2O + (CH3)2NH
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Dimethylamine.getFluid(1000))
                .buildAndRegister();

        // HClO + NH3 -> H2O + NH2Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Chloramine.getFluid(1000))
                .buildAndRegister();

        // Recipe Conflicts
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(480)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Epichlorhydrin.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, Iridium, 3)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(0))
                .output(dust, Osmiridium, 4)
                .EUt(30)
                .duration(764)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .input(dust, HSSG, 6)
                .input(dust, Iridium, 2)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, HSSS, 9)
                .EUt(30)
                .duration(1160)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // Chain Fixing
        // Gold Dust Centrifuging
        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(20)
                .input(dust, Netherrack)
                .chancedOutput(OreDictUnifier.get(dustTiny, Materials.Redstone, 1), 5625, 850)
                .chancedOutput(OreDictUnifier.get(dustTiny, PreciousMetal, 2), 625, 500)
                .chancedOutput(OreDictUnifier.get(dustSmall, Materials.Sulfur, 1), 9900, 100)
                .chancedOutput(OreDictUnifier.get(dustTiny, Materials.Coal, 1), 5625, 850)
                .buildAndRegister();
    }

    public static void initChains() {
        GoldChain.init();
        NaquadahChain.init();
        OpticalFiber.init();
        NuclearChain.init();
        PlasticChain.init();
        PlatinumSludgeGroupChain.init();
        TungstenChain.init();
        REEChain.init();
        BacteriaCultures.init();
        GrowthMedium.init();
        StemCells.init();
        SterilizedGrowthMedium.init();
        ProcessingUnits.init();
        Circuits.init();
        Batteries.init();
        RheniumChain.init();
        UHVMaterials.init();
        PEEKChain.init();
        ZylonChain.init();
        FullereneChain.init();
        BariumChain.init();
        UraniumChain.init();
        VanadiumChain.init();
        IodineChain.init();
        ZirconChain.init();
        ZincChain.init();
        AluminiumChain.init();
        AmmoniaChain.init();
        ChromiumChain.init();
        LithiumChain.init();
        WaferChain.init();
        BrineChain.init();
        FusionElementsChain.init();
        NanotubeChain.init();
        VariousChains.init();
        SuperconductorsSMDChain.init();
        FusionComponents.init();
        NiobiumTantalumChain.init();
        Lasers.init();
        Dyes.init();
        SensorEmitter.init();
        SeleniumChain.init();
        OpticalComponents.init();
        OpticalCircuits.init();
        WormholeGeneratorChain.init();
        CosmicComponents.init();
        SupraCausalComponents.init();
        UltimateMaterials.init();
        DigitalInterfaces.init();
        InsulationWireAssemblyChain.init();
        ArcFurnaceOxidation.init();
    }

    public static void generatedRecipes() {
        List<ResourceLocation> recipesToRemove = new ArrayList<>();

        for (IRecipe recipe : CraftingManager.REGISTRY) {
            if (recipe.getRecipeOutput().isEmpty()) {
                //dont know how it can be possible but its appear
                continue;
            }
            if (recipe.getIngredients().size() == 9) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) != Blocks.AIR) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        if (GAConfig.GT5U.Remove3x3BlockRecipes) recipesToRemove.add(recipe.getRegistryName());
                        if (GAConfig.GT5U.GenerateCompressorRecipes && !recipe.getIngredients().get(0).test(new ItemStack(Items.WHEAT))) {
                            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                                    .inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size()))
                                    .outputs(recipe.getRecipeOutput())
                                    .buildAndRegister();
                        } else {
                            PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                                    .inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size()))
                                    .notConsumable(SCHEMATIC_3X3.getStackForm())
                                    .outputs(recipe.getRecipeOutput())
                                    .buildAndRegister();
                        }
                    }
                }
            }
            if (recipe.getIngredients().size() == 9) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) == Blocks.AIR) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !hasPrefix(recipe.getRecipeOutput(), "dust", "dustTiny") && !hasPrefix(recipe.getRecipeOutput(), "ingot") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager3x3Recipes) {
                        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                                .inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size()))
                                .notConsumable(SCHEMATIC_3X3.getStackForm())
                                .outputs(recipe.getRecipeOutput())
                                .buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 4) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) != Blocks.QUARTZ_BLOCK) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !hasPrefix(recipe.getRecipeOutput(), "dust", "dustSmall") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager2x2Recipes) {
                        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                                .inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size()))
                                .notConsumable(SCHEMATIC_2X2.getStackForm())
                                .outputs(recipe.getRecipeOutput())
                                .buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9 && !hasPrefix(recipe.getIngredients().get(0).getMatchingStacks()[0], "ingot") && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.AIR && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.SLIME_BLOCK) {
                boolean isIngot = false;
                for (int i : OreDictionary.getOreIDs(recipe.getRecipeOutput())) {
                    if (OreDictionary.getOreName(i).startsWith("ingot")) {
                        isIngot = true;
                        break;
                    }
                }
                if (GAConfig.GT5U.RemoveBlockUncraftingRecipes) recipesToRemove.add(recipe.getRegistryName());
                if (!isIngot) {
                    FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24)
                            .inputs(recipe.getIngredients().get(0).getMatchingStacks()[0])
                            .outputs(recipe.getRecipeOutput())
                            .buildAndRegister();
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9 && !hasPrefix(recipe.getIngredients().get(0).getMatchingStacks()[0], "ingot")) {
                if (!recipesToRemove.contains(recipe.getRegistryName()) && GAConfig.Misc.Unpackager3x3Recipes) {
                    UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8)
                            .inputs(recipe.getIngredients().get(0).getMatchingStacks()[0])
                            .notConsumable(SCHEMATIC_3X3.getStackForm())
                            .outputs(recipe.getRecipeOutput())
                            .buildAndRegister();
                }
            }

        }

        recipesToRemove.add(new ResourceLocation("gtadditions:block_compress_clay"));
        recipesToRemove.add(new ResourceLocation("gtadditions:block_decompress_clay"));

        for (ResourceLocation r : recipesToRemove)
            ModHandler.removeRecipeByName(r);
        recipesToRemove.clear();

        if (GAConfig.GT5U.GenerateCompressorRecipes) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:quartz_block"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_nether_quartz"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_nether_quartz"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:nether_quartz_block_to_nether_quartz"));
            FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .inputs(OreDictUnifier.get(block, NetherQuartz))
                    .outputs(OreDictUnifier.get(gem, NetherQuartz, 4))
                    .buildAndRegister();

            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                    .input(gem, Materials.NetherQuartz, 4)
                    .outputs(new ItemStack(Blocks.QUARTZ_BLOCK)).
                    buildAndRegister();
        }


        if (GAConfig.GT5U.DisableLogToCharcoalSmeltg) {
            List<ItemStack> allWoodLogs = OreDictionary.getOres("logWood").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

            for (ItemStack stack : allWoodLogs) {
                ItemStack smeltingOutput = ModHandler.getSmeltingOutput(stack);
                if (!smeltingOutput.isEmpty() && smeltingOutput.getItem() == Items.COAL && smeltingOutput.getMetadata() == 1) {
                    ItemStack woodStack = stack.copy();
                    woodStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
                    ModHandler.removeFurnaceSmelting(woodStack);
                }
            }
        }
    }
}
