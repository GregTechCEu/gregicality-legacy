package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.CLUSTER_MILL_RECIPES;
import static gregicadditions.recipes.helper.AdditionMethods.removeAllRecipes;
import static gregicadditions.recipes.helper.AdditionMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
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
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Water.getFluid(500))
                .fluidOutputs(Concrete.getFluid(L * 4))
                .buildAndRegister();

        // Osmiridium Mixer Recipe
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dust, Iridium, 3), OreDictUnifier.get(dust, Osmium));
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Iridium, 3)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(0))
                .output(dust, Osmiridium, 4)
                .EUt(30)
                .duration(764)
                .buildAndRegister();

        // HSS-G Mixer Recipe
        removeRecipesByInputs(MIXER_RECIPES, OreDictUnifier.get(dust, HSSG, 6), OreDictUnifier.get(dust, Iridium, 2), OreDictUnifier.get(dust, Osmium));
        MIXER_RECIPES.recipeBuilder()
                .input(dust, HSSG, 6)
                .input(dust, Iridium, 2)
                .input(dust, Osmium)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, HSSS, 9)
                .EUt(30)
                .duration(1160)
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

        // Juice -> Biomass
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
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
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(7).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(8).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(9).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(10).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});

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
        removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, HydroCrackedLightFuel.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});

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
        removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, FermentedBiomass.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});

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
    }

    private static void gregtechOverride() {

        // GTNH Bricks
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.removeFurnaceSmelting(COMPRESSED_CLAY.getStackForm());
        ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2)
                .inputs(new ItemStack(Items.CLAY_BALL))
                .notConsumable(SHAPE_MOLD_INGOT)
                .outputs(new ItemStack(Items.BRICK))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("clay_brick", COMPRESSED_CLAY.getStackForm(),
                new ItemStack(Items.CLAY_BALL),
                "formWood");

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
                .output(dust, GAMaterials.QuartzSand)
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

        // GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), RUBBER_DROP.getStackForm());

        FORGE_HAMMER_RECIPES.recipeBuilder().duration(16).EUt(10)
                .inputs(new ItemStack(Items.BONE))
                .outputs(new ItemStack(Items.DYE, 4, 15))
                .buildAndRegister();

        //wood pipe
        if (GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipes(OreDictUnifier.get(pipeSmall, Wood, 4));
            ModHandler.removeRecipes(OreDictUnifier.get(pipeMedium, Wood, 2));

            ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(pipeMedium, Wood, 2),
                    "PPP", "sCh", "PPP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood),
                    "PhP", "PCP", "PsP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");

            ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(pipeSmall, Wood, 4),
                    "PsP", "PCP", "PhP",
                    'P', "plankWood",
                    'C', "craftingToolBendingCylinder");
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
        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, DarkAsh, 2));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6)
                .input(dust, DarkAsh)
                .output(dust, Ash)
                .output(dust, Carbon)
                .buildAndRegister();

        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Ash));
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

        // Quartz Plates
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Lubricant.getFluid(18)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Lubricant.getFluid(18)});

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, NetherQuartz)
                .output(plate, NetherQuartz)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                .input(dust, CertusQuartz)
                .output(plate, CertusQuartz)
                .buildAndRegister();

        // Seed Oil
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
    }

    private static void vanillaOverride() {

        if (GAConfig.GT6.BendingCylinders && GAConfig.GT6.addCurvedPlates) {

            // Buckets
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
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
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            // Gold Armor
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

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
        }
    }

    private static void recipeRemoval() {

        // Remove GTCE Platinum and Palladium Recipes
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(crushedPurified, Chalcopyrite)}, new FluidStack[]{NitricAcid.getFluid(1000)});
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(crushedPurified, Pentlandite)}, new FluidStack[]{NitricAcid.getFluid(1000)});
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, PlatinumGroupSludge));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Endstone));

        // Remove Conflicting Redstone Plate Recipe
        removeRecipesByInputs(COMPRESSOR_RECIPES, OreDictUnifier.get(dust, Redstone));

        // Remove MAX-Superconductor Wire Recipes
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, YttriumBariumCuprate, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, NiobiumTitanium, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, VanadiumGallium, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});

        // Remove Nuclear Processing
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Uranium));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Plutonium));

        // Remove Rare Earth Centrifuging
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, RareEarth));

        // Remove GTCE Uraninite Recipes
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite, 3), OreDictUnifier.get(dust, Aluminium));
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite, 3), OreDictUnifier.get(dust, Magnesium));

        // Remove GTCE NZF Recipes
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, FerriteMixture, 6)}, new FluidStack[]{Oxygen.getFluid(8000)});
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Iron, 4), OreDictUnifier.get(dust, Nickel), OreDictUnifier.get(dust, Zinc)}, new FluidStack[]{Oxygen.getFluid(8000)});

        // Remove GTCE Dinitrogen Tetroxide Recipes
        removeRecipesByInputs(CHEMICAL_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{NitrogenDioxide.getFluid(2000)});
        removeRecipesByInputs(MIXER_RECIPES, DinitrogenTetroxide.getFluid(1000), Dimethylhydrazine.getFluid(1000));

        // QCD Matter progression skipping
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_INGOT.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(144)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_NUGGET.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(144)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_PLATE.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(144)});
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(1296)});

        // Remove Yttrium Barium Cuprate Mixer Recipe
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Copper, 3), OreDictUnifier.get(dust, Barium, 2), OreDictUnifier.get(dust, Yttrium)}, new FluidStack[]{Oxygen.getFluid(7000)});
    }
}
