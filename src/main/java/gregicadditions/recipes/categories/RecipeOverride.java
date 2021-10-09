package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAEnums.GAOrePrefix.plateDouble;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.CLUSTER_MILL_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.W;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class RecipeOverride {

    public static void init() {
        chemistryOverride();
        gregtechOverride();
        vanillaOverride();
        brewingOverride();
        configCircuitOverride();
        recipeRemoval();
    }

    private static void configCircuitOverride() {

        // Aluminium EBF
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Aluminium));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(884).blastFurnaceTemp(1700)
                .input(dust, Aluminium)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingot, Aluminium)
                .buildAndRegister();

        // Epichlorohydrin from Allyl Chloride
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)});
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(AllylChloride.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(Epichlorhydrin.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();

        // Epichlorohydrin from Glycerol
        removeRecipesByInputs(CHEMICAL_RECIPES, Glycerol.getFluid(1000), HydrochloricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(480)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Epichlorhydrin.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        // Hot Tungsten
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Tungsten));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(10980).blastFurnaceTemp(3000)
                .input(dust, Tungsten)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingotHot, Tungsten)
                .buildAndRegister();

        // Hot Lithium Titanate
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, LithiumTitanate));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(900).blastFurnaceTemp(2500)
                .input(dust, LithiumTitanate)
                .notConsumable(new IntCircuitIngredient(0))
                .output(ingotHot, LithiumTitanate)
                .buildAndRegister();

        // Dichlorobenzene
        removeRecipesByInputs(CHEMICAL_RECIPES, Benzene.getFluid(1000), Chlorine.getFluid(4000));
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Dichlorobenzene.getFluid(1000))
                .buildAndRegister();

        // Concrete
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Clay), OreDictUnifier.get(dust, Stone, 3)}, new FluidStack[]{Water.getFluid(500)});
        MIXER_RECIPES.recipeBuilder().duration(20).EUt(16)
                .input(dust, Clay)
                .input(dust, Stone, 3)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidInputs(Water.getFluid(500))
                .fluidOutputs(Concrete.getFluid(L * 4))
                .buildAndRegister();

        // Osmiridium Mixer Recipe
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dust, Iridium, 3), OreDictUnifier.get(dust, Osmium));
        MIXER_RECIPES.recipeBuilder().EUt(30).duration(764)
                .input(dust, Iridium, 3)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(2))
                .output(dust, Osmiridium, 4)
                .buildAndRegister();

        // HSS-G Mixer Recipe
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dust, HSSG, 6), OreDictUnifier.get(dust, Iridium, 2), OreDictUnifier.get(dust, Osmium));
        MIXER_RECIPES.recipeBuilder().EUt(30).duration(1160)
                .input(dust, HSSG, 6)
                .input(dust, Iridium, 2)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(3))
                .output(dust, HSSS, 9)
                .buildAndRegister();

        // Ethylene from Ethanol
        removeRecipesByInputs(CHEMICAL_RECIPES, Ethanol.getFluid(1000), SulfuricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // Chloramine from Hypochlorous Acid
        removeRecipesByInputs(CHEMICAL_RECIPES, HypochlorousAcid.getFluid(1000), Ammonia.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Chloramine.getFluid(1000))
                .buildAndRegister();

        // Dimethylamine
        removeRecipesByInputs(CHEMICAL_RECIPES, Ammonia.getFluid(1000), Methanol.getFluid(2000));
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Dimethylamine.getFluid(1000))
                .buildAndRegister();

        // Styrene
        removeRecipesByInputs(CHEMICAL_RECIPES, Ethylene.getFluid(1000), Benzene.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Styrene.getFluid(1000))
                .buildAndRegister();
    }

    private static void brewingOverride() {

        removeAllRecipes(BREWING_RECIPES);

        // Honey -> Biomass
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3)
                .inputs(PLANT_BALL.getStackForm())
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

        // Juice -> Biomass
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3)
                .inputs(PLANT_BALL.getStackForm())
                .fluidInputs(Juice.getFluid(180))
                .fluidOutputs(Biomass.getFluid(270))
                .buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(600).EUt(3)
                .input("treeSapling", 1)
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

        // Water -> Biomass
        BREWING_RECIPES.recipeBuilder().duration(800).EUt(3)
                .input("treeSapling", 1)
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
    }

    private static void chemistryOverride() {

        // Add Cyclopentadiene to Steam-Cracked Naphtha Distillation
        removeRecipesByInputs(DISTILLATION_RECIPES, SteamCrackedNaphtha.getFluid(1000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(7).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(8).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(9).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(10).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});

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

        // Add Octane to Hydro-Cracked Light Fuel Distillation
        removeRecipesByInputs(DISTILLATION_RECIPES, HydroCrackedLightFuel.getFluid(1000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});

        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(HydroCrackedLightFuel.getFluid(1000))
                .fluidOutputs(Naphtha.getFluid(750))
                .fluidOutputs(Propane.getFluid(200))
                .fluidOutputs(Butane.getFluid(150))
                .fluidOutputs(Ethane.getFluid(125))
                .fluidOutputs(Methane.getFluid(125))
                .fluidOutputs(Octane.getFluid(50))
                .buildAndRegister();

        // Add Butanol to Fermented Biomass
        removeRecipesByInputs(DISTILLATION_RECIPES, FermentedBiomass.getFluid(1000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});

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

        // Putting Fermentation Base here as well to keep them together
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

        // Fix GTCE Hypochlorous Acid Recipe
        removeRecipesByInputs(CHEMICAL_RECIPES, Chlorine.getFluid(10000), Water.getFluid(10000), Mercury.getFluid(1000));

        // 10Cl + 10H2O -> 10HClO + 10H
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(8)
                .fluidInputs(Chlorine.getFluid(10000))
                .fluidInputs(Water.getFluid(10000))
                .notConsumable(Mercury.getFluid(0))
                .fluidOutputs(HypochlorousAcid.getFluid(10000))
                .fluidOutputs(Hydrogen.getFluid(10000))
                .buildAndRegister();

        // Fix GTCE Cumene Recipe
        removeRecipesByInputs(CHEMICAL_RECIPES, Propene.getFluid(8000), Benzene.getFluid(8000), PhosphoricAcid.getFluid(1000));

        // 8C3H6 + 8C6H6 -> 8C9H12
        CHEMICAL_RECIPES.recipeBuilder().duration(1920).EUt(30)
                .fluidInputs(Propene.getFluid(8000))
                .fluidInputs(Benzene.getFluid(8000))
                .notConsumable(PhosphoricAcid.getFluid(0))
                .fluidOutputs(Cumene.getFluid(8000))
                .buildAndRegister();
    }

    private static void gregtechOverride() {

        // GTNH Bricks
        removeFurnaceRecipe(new ItemStack(Items.CLAY_BALL, 1, W));
        removeFurnaceRecipe(COMPRESSED_CLAY.getStackForm());
        removeRecipeByName("gregtech:brick_to_dust");
        removeRecipeByName("gregtech:brick_block_to_dust");
        removeRecipeByName("gregtech:compressed_clay");
        removeRecipeByName("gtadditions:block_compress_clay");
        removeRecipeByName("gtadditions:block_decompress_clay");

        ModHandler.addShapelessRecipe("clay_brick", COMPRESSED_CLAY.getStackForm(),
                new ItemStack(Items.CLAY_BALL),
                "formWood");

        ModHandler.addShapedRecipe("eight_clay_brick", COMPRESSED_CLAY.getStackForm(8),
                "BBB", "BFB", "BBB",
                'B', new ItemStack(Items.CLAY_BALL),
                'F', "formWood");

        ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2)
                .inputs(new ItemStack(Items.CLAY_BALL))
                .notConsumable(SHAPE_MOLD_INGOT)
                .outputs(new ItemStack(Items.BRICK))
                .buildAndRegister();

        ModHandler.addShapedRecipe("coke_brick", COMPRESSED_COKE_CLAY.getStackForm(3),
                "BBB", "SFS", "SSS",
                'B', new ItemStack(Items.CLAY_BALL),
                'S', new ItemStack(Blocks.SAND),
                'F', "formWood");

        ModHandler.addSmeltingRecipe(COMPRESSED_COKE_CLAY.getStackForm(), COKE_OVEN_BRICK.getStackForm());

        // GT5U Glass Recipes
        ModHandler.addShapedRecipe("quartz_sand", OreDictUnifier.get(dust, GAMaterials.QuartzSand),
                "S", "m",
                'S', "sand");

        MACERATOR_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input("sand", 1)
                .output(dust, GAMaterials.QuartzSand)
                .chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2500, 500)
                .chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2000, 500)
                .buildAndRegister();

        ModHandler.addShapelessRecipe("glass_dust_ga", OreDictUnifier.get(dust, Glass), "dustSand", "dustFlint");

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input(dust, Flint)
                .input(dust, GAMaterials.QuartzSand, 4)
                .output(dust, Glass, 4)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(160).EUt(8)
                .input(dust, Flint)
                .input(dust, Quartzite, 4)
                .output(dust, Glass, 4)
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().duration(20).EUt(30)
                .inputs(new ItemStack(Blocks.SAND, 1))
                .outputs(new ItemStack(Blocks.GLASS, 2))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, Glass)
                .notConsumable(SHAPE_MOLD_BLOCK.getStackForm())
                .outputs(new ItemStack(Blocks.GLASS, 1))
                .buildAndRegister();

        // Concrete
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(dust, Calcite, 2)
                .input(dust, Stone)
                .input(dust, Clay)
                .input(dust, GAMaterials.QuartzSand)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Concrete.getFluid(2304))
                .buildAndRegister();

        // GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), RUBBER_DROP.getStackForm());

        // Bone Meal
        removeRecipeByName("minecraft:bone_meal_from_bone");
        FORGE_HAMMER_RECIPES.recipeBuilder().duration(16).EUt(10)
                .inputs(new ItemStack(Items.BONE))
                .outputs(new ItemStack(Items.DYE, 4, 15))
                .buildAndRegister();

        // Wood Pipes
        if (GAConfig.GT6.BendingCylinders) {
            removeCraftingRecipes(OreDictUnifier.get(pipeSmall, Wood, 4));
            removeCraftingRecipes(OreDictUnifier.get(pipeMedium, Wood, 2));

            ModHandler.addShapedRecipe("pipe_ga_tiny_wood", OreDictUnifier.get(pipeTiny, Wood, 8),
                    "PPP", "hCw", "PPP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(pipeSmall, Wood, 4),
                    "PsP", "PCP", "PhP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(pipeMedium, Wood, 2),
                    "PPP", "sCh", "PPP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood),
                    "PhP", "PCP", "PsP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

        } else {

            // Only add Tiny and Large Wood Pipes if Bending Cylinders are not enabled
            ModHandler.addShapedRecipe("pipe_ga_tiny_wood", OreDictUnifier.get(pipeTiny, Wood, 8),
                    "PPP", "h w", "PPP",
                    'P', "plankWood");

            ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood),
                    "PhP", "P P", "PsP",
                    'P', "plankWood");
        }

        // GTNH Coils
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .input(dust, RawRubber, 2)
                .output(dust, MicaPulp, 5)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .inputs(RUBBER_DROP.getStackForm())
                .output(dust, MicaPulp, 4)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Sapphire)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, GreenSapphire)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Ruby)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
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

        // Ash Recipes
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, DarkAsh, 2));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6)
                .input(dust, DarkAsh)
                .output(dust, Ash)
                .output(dust, Carbon)
                .buildAndRegister();

        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Ash));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30)
                .input(dust, Ash)
                .chancedOutput(OreDictUnifier.get(dustSmall, Quicklime, 2), 9900, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, Potash), 6400, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, Magnesia), 6000, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, PhosphorousPentoxide), 500, 0)
                .chancedOutput(OreDictUnifier.get(dustSmall, SodaAsh), 5000, 0)
                .buildAndRegister();

        // Gold Dust Centrifuging
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Netherrack));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(20)
                .input(dust, Netherrack)
                .chancedOutput(OreDictUnifier.get(dustTiny, Redstone), 5625, 850)
                .chancedOutput(OreDictUnifier.get(dustTiny, PreciousMetal, 2), 625, 500)
                .chancedOutput(OreDictUnifier.get(dustSmall, Sulfur), 9900, 100)
                .chancedOutput(OreDictUnifier.get(dustTiny, Coal), 5625, 850)
                .buildAndRegister();

        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Glowstone));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(488).EUt(80)
                .input(dust, Glowstone)
                .output(dustSmall, Netherrack, 2)
                .output(dustSmall, PreciousMetal, 2)
                .buildAndRegister();

        // Precious Metal Dust from Netherrack
        removeRecipesByInputs(MACERATOR_RECIPES, new ItemStack(Blocks.NETHERRACK));
        MACERATOR_RECIPES.recipeBuilder().duration(150).EUt(8)
                .inputs(new ItemStack(Blocks.NETHERRACK))
                .output(dust, Netherrack)
                .chancedOutput(OreDictUnifier.get(dustTiny, PreciousMetal), 500, 120)
                .buildAndRegister();

        // GTNH Lava
        removeRecipesByInputs(CENTRIFUGE_RECIPES, Lava.getFluid(100));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(80).EUt(80)
                .fluidInputs(Lava.getFluid(100))
                .chancedOutput(OreDictUnifier.get(dustSmall, SiliconDioxide),5000, 500)
                .chancedOutput(OreDictUnifier.get(dustSmall, Magnesia),1000, 100)
                .chancedOutput(OreDictUnifier.get(dustSmall, Quicklime),1000, 100)
                .chancedOutput(OreDictUnifier.get(dustSmall, PreciousMetal),250, 90)
                .chancedOutput(OreDictUnifier.get(dustSmall, Sapphire),1250, 150)
                .chancedOutput(OreDictUnifier.get(dustSmall, Tantalite),500, 90)
                .buildAndRegister();

        // Quartz Plates
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Lubricant.getFluid(18)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Lubricant.getFluid(18)});

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, NetherQuartz)
                .output(plate, NetherQuartz)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, CertusQuartz)
                .output(plate, CertusQuartz)
                .buildAndRegister();

        // Seed Oil TODO This duplicates
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input("listAllSeed", 1)
                .fluidOutputs(SeedOil.getFluid(10))
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

        // Diesel
        removeRecipesByInputs(MIXER_RECIPES, LightFuel.getFluid(5000), HeavyFuel.getFluid(1000));
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(120)
                .fluidInputs(LightFuel.getFluid(5000))
                .fluidInputs(HeavyFuel.getFluid(1000))
                .fluidOutputs(Fuel.getFluid(6000))
                .buildAndRegister();

        // Dynamite
        removeCraftingRecipes(DYNAMITE.getStackForm());
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.PAPER))
                .inputs(new ItemStack(Items.STRING))
                .fluidInputs(Glyceryl.getFluid(500))
                .outputs(DYNAMITE.getStackForm())
                .buildAndRegister();

        // Silicon Boule
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Silicon, 32), OreDictUnifier.get(dustTiny, Gallium), getIntegratedCircuit(1));
        BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).blastFurnaceTemp(1784)
                .input(dust, Silicon, 32)
                .input(dustSmall, GalliumArsenide)
                .outputs(SILICON_BOULE.getStackForm())
                .buildAndRegister();

        // BartWorks Platinum Group Sludge
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, PlatinumGroupSludge));
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(900)
                .input(dust, PlatinumGroupSludge)
                .output(dust, SiliconDioxide)
                .output(dust, PreciousMetal)
                .output(dust, PlatinumMetallicPowder, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, PalladiumMetallicPowder, 6), 9500, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, IrMetalResidue, 3), 9000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, RarestMetalResidue, 3), 8500, 0)
                .buildAndRegister();

        // Sheldonite Smelting Recipe
        removeFurnaceRecipe(OreDictUnifier.get(dust, Cooperite));
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(dust, Cooperite), OreDictUnifier.get(dust, PlatinumMetallicPowder, 2));

        // Sheldonite Electrolysis
        removeRecipesByInputs(ELECTROLYZER_RECIPES, OreDictUnifier.get(dust, Cooperite, 6));
        ELECTROLYZER_RECIPES.recipeBuilder().EUt(60).duration(2592)
                .input(dust, Cooperite, 6)
                .output(dust, PlatinumMetallicPowder, 6)
                .output(dust, Nickel)
                .output(dust, Sulfur)
                .output(dust, PalladiumMetallicPowder, 2)
                .buildAndRegister();

        // Endstone
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Endstone));
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(20).duration(320)
                .input(dust, Endstone)
                .chancedOutput(OreDictUnifier.get(dustSmall, Tungstate), 1250, 450)
                .chancedOutput(OreDictUnifier.get(dustTiny, PlatinumMetallicPowder, 2), 625, 150)
                .chancedOutput(new ItemStack(Blocks.SAND), 9000, 300)
                .fluidOutputs(Helium.getFluid(120))
                .buildAndRegister();

        // Cells from Dense Plates to Double Plates
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(ring, Steel, 8), OreDictUnifier.get(plateDense, Steel, 2), getIntegratedCircuit(1));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(100)
                .input(ring, Steel, 8)
                .input(plateDouble, Steel, 2)
                .outputs(LARGE_FLUID_CELL_STEEL.getStackForm())
                .buildAndRegister();

        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(ring, TungstenSteel, 8), OreDictUnifier.get(plateDense, TungstenSteel, 2), getIntegratedCircuit(1));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(100)
                .input(ring, TungstenSteel, 8)
                .input(plateDouble, TungstenSteel, 2)
                .outputs(LARGE_FLUID_CELL_TUNGSTEN_STEEL.getStackForm())
                .buildAndRegister();

        // Oil Desulfurization Catalysis
        removeRecipesByInputs(CHEMICAL_RECIPES, Hydrogen.getFluid(2000), SulfuricGas.getFluid(16000));
        removeRecipesByInputs(CHEMICAL_RECIPES, Hydrogen.getFluid(2000), SulfuricNaphtha.getFluid(12000));
        removeRecipesByInputs(CHEMICAL_RECIPES, Hydrogen.getFluid(2000), SulfuricLightFuel.getFluid(12000));
        removeRecipesByInputs(CHEMICAL_RECIPES, Hydrogen.getFluid(2000), SulfuricHeavyFuel.getFluid(8000));

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(SulfuricGas.getFluid(16000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Gas.getFluid(16000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .notConsumable(dust, Molybdenite)
                .notConsumable(CobaltAluminate.getItemStack())
                .fluidInputs(SulfuricGas.getFluid(8000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Gas.getFluid(8000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(SulfuricNaphtha.getFluid(12000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Naphtha.getFluid(12000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .notConsumable(dust, Molybdenite)
                .notConsumable(CobaltAluminate.getItemStack())
                .fluidInputs(SulfuricNaphtha.getFluid(6000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Naphtha.getFluid(6000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(SulfuricLightFuel.getFluid(12000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(LightFuel.getFluid(12000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .notConsumable(dust, Molybdenite)
                .notConsumable(CobaltAluminate.getItemStack())
                .fluidInputs(SulfuricLightFuel.getFluid(6000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(LightFuel.getFluid(6000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(SulfuricHeavyFuel.getFluid(8000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HeavyFuel.getFluid(8000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .notConsumable(dust, Molybdenite)
                .notConsumable(CobaltAluminate.getItemStack())
                .fluidInputs(SulfuricHeavyFuel.getFluid(4000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HeavyFuel.getFluid(4000))
                .buildAndRegister();

        // Trona Electrolysis
        ELECTROLYZER_RECIPES.recipeBuilder().EUt(60).duration(784)
                .input(dust, Trona, 14)
                .output(dust, SodaAsh, 6)
                .outputs(SodiumBicarbonate.getItemStack(6))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // Ilmenite Processing
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Ilmenite), OreDictUnifier.get(dust, Carbon));
        BLAST_RECIPES.recipeBuilder().EUt(500).duration(600).blastFurnaceTemp(1700)
                .input(dust, Ilmenite, 5)
                .output(ingot, WroughtIron)
                .output(dust, Rutile, 3)
                .buildAndRegister();
    }

    private static void vanillaOverride() {

        if (GAConfig.GT6.addCurvedPlates) {

            // Buckets
            removeRecipeByName("gregtech:iron_bucket");
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET),
                    "ChC", " P ",
                    'C', new UnificationEntry(plateCurved, Iron),
                    'P', new UnificationEntry(plate, Iron));

            removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(plate, Iron, 12), getIntegratedCircuit(1));
            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(plateCurved, Iron, 2)
                    .input(plate, Iron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(plate, WroughtIron, 12), getIntegratedCircuit(1));
            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(plateCurved, WroughtIron, 2)
                    .input(plate, WroughtIron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            // Iron Armor
            removeRecipeByName("minecraft:iron_helmet");
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            removeRecipeByName("minecraft:iron_chestplate");
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            removeRecipeByName("minecraft:iron_leggings");
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            removeRecipeByName("minecraft:iron_boots");
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            // Gold Armor
            removeRecipeByName("minecraft:golden_helmet");
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            removeRecipeByName("minecraft:golden_chestplate");
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            removeRecipeByName("minecraft:golden_leggings");
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            removeRecipeByName("minecraft:golden_boots");
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));
        }

        // Chain Armor
        ModHandler.addShapedRecipe("chain_helmet", new ItemStack(Items.CHAINMAIL_HELMET),
                "RRR", "RhR",
                'R', new UnificationEntry(ring, Iron));

        ModHandler.addShapedRecipe("chain_chestplate", new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                "RhR", "RRR", "RRR",
                'R', new UnificationEntry(ring, Iron));

        ModHandler.addShapedRecipe("chain_leggings", new ItemStack(Items.CHAINMAIL_LEGGINGS),
                "RRR", "RhR", "R R",
                'R', new UnificationEntry(ring, Iron));

        ModHandler.addShapedRecipe("chain_boots", new ItemStack(Items.CHAINMAIL_BOOTS),
                "R R", "RhR",
                'R', new UnificationEntry(ring, Iron));

        // Glowstone / Nether Quartz Block Recipes
        if (GAConfig.GT5U.Remove3x3BlockRecipes) {
            removeRecipeByName("minecraft:glowstone");
            removeRecipeByName("minecraft:quartz_block");
            removeRecipeByName("gregtech:nether_quartz_block_to_nether_quartz");
        }

        FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(block, NetherQuartz)
                .output(gem, NetherQuartz, 4)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(block, Glowstone)
                .output(dust, Glowstone, 4)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(gem, NetherQuartz, 4)
                .outputs(new ItemStack(Blocks.QUARTZ_BLOCK))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(new ItemStack(Items.GLOWSTONE_DUST, 4))
                .outputs(new ItemStack(Blocks.GLOWSTONE))
                .buildAndRegister();


        // Glowstone Recipes
        MIXER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Redstone)
                .input(dust, PreciousMetal)
                .output(dust, Glowstone)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Redstone)
                .input(dust, Gold)
                .output(dust, Glowstone, 2)
                .buildAndRegister();

        // Snow Block Recipes
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .input(block, Snow)
                .outputs(OreDictUnifier.get(dust, Snow, 4))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(2).duration(200)
                .input(dust, Snow, 4)
                .outputs(OreDictUnifier.get(block, Snow, 4))
                .buildAndRegister();

        // Log -> Charcoal Recipes
        // TODO Ignores Forestry wood
        if (GAConfig.GT5U.DisableLogToCharcoalSmelting) {
            List<ItemStack> allWoodLogs = OreDictionary.getOres("logWood")
                                                       .stream()
                                                       .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                                                       .collect(Collectors.toList());

            for (ItemStack stack : allWoodLogs) {
                ItemStack smeltingOutput = ModHandler.getSmeltingOutput(stack);
                if (!smeltingOutput.isEmpty() && smeltingOutput.getItem() == Items.COAL && smeltingOutput.getMetadata() == 1) {
                    ItemStack woodStack = stack.copy();
                    woodStack.setItemDamage(W);
                    removeFurnaceRecipe(woodStack);
                }
            }
        }
    }

    private static void recipeRemoval() {

        // Remove Conflicting Redstone Plate Recipe
        removeRecipesByInputs(COMPRESSOR_RECIPES, OreDictUnifier.get(dust, Redstone));

        // Remove MAX-Superconductor Wire Recipes
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, YttriumBariumCuprate, 3), OreDictUnifier.get(plate, TungstenSteel, 3), ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, NiobiumTitanium, 3),      OreDictUnifier.get(plate, TungstenSteel, 3), ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, VanadiumGallium, 3),      OreDictUnifier.get(plate, TungstenSteel, 3), ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});

        // Remove Nuclear Processing
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Uranium));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Plutonium));
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite, 3), OreDictUnifier.get(dust, Aluminium));
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite, 3), OreDictUnifier.get(dust, Magnesium));

        // Remove Rare Earth Centrifuging
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, RareEarth));

        // Remove GTCE NZF Recipes
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, FerriteMixture, 6)}, new FluidStack[]{Oxygen.getFluid(8000)});
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Iron, 4), OreDictUnifier.get(dust, Nickel), OreDictUnifier.get(dust, Zinc)}, new FluidStack[]{Oxygen.getFluid(8000)});

        // Remove GTCE Dinitrogen Tetroxide Recipes
        removeRecipesByInputs(CHEMICAL_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{NitrogenDioxide.getFluid(2000)});
        removeRecipesByInputs(MIXER_RECIPES, DinitrogenTetroxide.getFluid(1000), Dimethylhydrazine.getFluid(1000));

        // QCD Matter progression skipping
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_INGOT.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_NUGGET.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(L)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_PLATE.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L * 9)});

        // Remove Yttrium Barium Cuprate Recipes
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Copper, 3), OreDictUnifier.get(dust, Barium, 2), OreDictUnifier.get(dust, Yttrium)}, new FluidStack[]{Oxygen.getFluid(7000)});

        // Remove Vanilla TNT Recipe
        removeCraftingRecipes(new ItemStack(Blocks.TNT));

        // Ultimate Battery
        removeRecipesByInputs(ASSEMBLER_RECIPES, ENERGY_LAPOTRONIC_ORB2.getStackForm(8), OreDictUnifier.get(plate, Darmstadtium, 16));

        // Reinforced Epoxy Resin Handcrafting Recipes
        removeRecipeByName("gregtech:reinforcing_epoxy_resin_by_glass");
        removeRecipeByName("gregtech:reinforcing_epoxy_resin_by_carbon");

        // Remove Bad Sulfuric Acid Recipe
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Sulfur)}, new FluidStack[]{Water.getFluid(4000)});

        // Remove Small and Tiny Dust Mixer Recipes ====================================================================

        ///////////////////////////////////////////////////
        //                  Tiny Dusts                   //
        ///////////////////////////////////////////////////

        // Borosilicate Glass Tiny Dust Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustTiny, Boron), OreDictUnifier.get(dustTiny, Glass, 7));
        // Brass Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustTiny, Copper, 3), OreDictUnifier.get(dustTiny, Zinc));
        // Bronze Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustTiny, Copper, 3), OreDictUnifier.get(dustTiny, Tin));
        // Red Steel Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustTiny, SterlingSilver), OreDictUnifier.get(dustTiny, BismuthBronze), OreDictUnifier.get(dustTiny, BlackSteel, 4), OreDictUnifier.get(dustTiny, Steel, 2));
        // Blue Steel Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustTiny, RoseGold), OreDictUnifier.get(dustTiny, Brass), OreDictUnifier.get(dustTiny, BlackSteel, 4), OreDictUnifier.get(dustTiny, Steel, 2));
        // Ultimet Hand
        removeRecipeByName("gregtech:dust_tiny_ultimet");
        // Cobalt Brass Hand
        removeRecipeByName("gregtech:dust_tiny_cobalt_brass");
        // Stainless Steel Hand
        removeRecipeByName("gregtech:dust_tiny_stainless_steel");
        // Kanthal Hand
        removeRecipeByName("gregtech:dust_tiny_kanthal");

        ///////////////////////////////////////////////////
        //                  Small Dusts                  //
        ///////////////////////////////////////////////////

        // Stainless Steel Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustSmall, Iron, 4), OreDictUnifier.get(dustSmall, Invar, 3), OreDictUnifier.get(dustSmall, Manganese), OreDictUnifier.get(dustSmall, Chrome));
        // Ultimet Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustSmall, Cobalt, 5), OreDictUnifier.get(dustSmall, Chrome, 2), OreDictUnifier.get(dustSmall, Nickel), OreDictUnifier.get(dustSmall, Molybdenum));
        // Cobalt Brass Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustSmall, Brass, 7), OreDictUnifier.get(dustSmall, Aluminium), OreDictUnifier.get(dustSmall, Cobalt));
        // Gunpowder from Coal Mixer
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dustSmall, Saltpeter, 2), OreDictUnifier.get(dustSmall, Sulfur), OreDictUnifier.get(dustSmall, Coal));

        // Some of them have both Small and Tiny Dust Mixer Recipes
        for (OrePrefix prefix : Arrays.asList(dustSmall, dustTiny)) {
            // Indium Gallium Phosphide
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Indium), OreDictUnifier.get(prefix, Gallium), OreDictUnifier.get(prefix, Phosphorus));
            // Electrum
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Gold), OreDictUnifier.get(prefix, Silver));
            // Invar
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Iron, 2), OreDictUnifier.get(prefix, Nickel), IntCircuitIngredient.getIntegratedCircuit(1));
            // Kanthal
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Iron), OreDictUnifier.get(prefix, Aluminium), OreDictUnifier.get(prefix, Chrome));
            // Cupronickel
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Copper), OreDictUnifier.get(prefix, Nickel));
            // Rose Gold
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Copper), OreDictUnifier.get(prefix, Gold));
            // Sterling Silver
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Copper), OreDictUnifier.get(prefix, Silver, 4));
            // Black Bronze
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Copper, 3), OreDictUnifier.get(prefix, Electrum, 2));
            // Bismuth Bronze
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Bismuth), OreDictUnifier.get(prefix, Brass, 4));
            // Black Steel
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, BlackBronze), OreDictUnifier.get(prefix, Nickel), OreDictUnifier.get(prefix, Steel, 3));
            // Gunpowder from Charcoal
            removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(prefix, Saltpeter, 2), OreDictUnifier.get(prefix, Sulfur), OreDictUnifier.get(prefix, Charcoal));
        }
    }
}
