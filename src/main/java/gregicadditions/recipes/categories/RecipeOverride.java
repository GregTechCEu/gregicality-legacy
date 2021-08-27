package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.W;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class RecipeOverride {

    public static void init() {
        chemistryOverride();
        gregtechOverride();
        vanillaOverride();
        configCircuitOverride();
        recipeRemoval();
    }

    private static void configCircuitOverride() {

        // Aluminium EBF
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Aluminium));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(884).blastFurnaceTemp(1700)
                .input(dust, Aluminium)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingot, Aluminium)
                .buildAndRegister();

        // Epichlorohydrin from Glycerol
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, Glycerol.getFluid(1000), HydrochloricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(480)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Epichlorohydrin.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        // Hot Tungsten
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Tungsten));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(10980).blastFurnaceTemp(3000)
                .input(dust, Tungsten)
                .notConsumable(new IntCircuitIngredient(1))
                .output(ingotHot, Tungsten)
                .buildAndRegister();

        // Hot Lithium Titanate
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, LithiumTitanate));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(900).blastFurnaceTemp(2500)
                .input(dust, LithiumTitanate)
                .notConsumable(new IntCircuitIngredient(0))
                .output(ingotHot, LithiumTitanate)
                .buildAndRegister();

        // Concrete
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Clay), OreDictUnifier.get(dust, Stone, 3)}, new FluidStack[]{Water.getFluid(500)});
        MIXER_RECIPES.recipeBuilder().duration(20).EUt(16)
                .input(dust, Clay)
                .input(dust, Stone, 3)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidInputs(Water.getFluid(500))
                .fluidOutputs(Concrete.getFluid(L * 4))
                .buildAndRegister();

        // Ethylene from Ethanol todo stop removing this if zinc chain is removed
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, Ethanol.getFluid(1000), SulfuricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // Dimethylamine todo stop removing this if brine chain is removed
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, Ammonia.getFluid(1000), Methanol.getFluid(2000));
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Dimethylamine.getFluid(1000))
                .buildAndRegister();
    }

    private static void chemistryOverride() {

        // Add Cyclopentadiene to Steam-Cracked Naphtha Distillation
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES, SteamCrackedNaphtha.getFluid(1000));
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(7).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(8).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(9).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(10).getMatchingStacks(), new FluidStack[]{SteamCrackedNaphtha.getFluid(1000)});

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

        // Add Butanol to Fermented Biomass
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES, FermentedBiomass.getFluid(1000));
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});

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
    }

    private static void gregtechOverride() {

        // GTNH Bricks
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, W));
        ModHandler.removeFurnaceSmelting(COMPRESSED_CLAY.getStackForm());
        ModHandler.removeRecipeByName("gregtech:brick_to_dust");
        ModHandler.removeRecipeByName("gregtech:brick_block_to_dust");
        ModHandler.removeRecipeByName("gregtech:compressed_clay");
        ModHandler.removeRecipeByName("gtadditions:block_compress_clay");
        ModHandler.removeRecipeByName("gtadditions:block_decompress_clay");

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

        // GTNH Lava todo GTNH lava in CEu?
        removeRecipesByInputs(CENTRIFUGE_RECIPES, Lava.getFluid(100));
        CENTRIFUGE_RECIPES.recipeBuilder().duration(80).EUt(80)
                .fluidInputs(Lava.getFluid(100))
                .chancedOutput(OreDictUnifier.get(dustSmall, SiliconDioxide),5000, 500)
                .chancedOutput(OreDictUnifier.get(dustSmall, Magnesia),1000, 100)
                .chancedOutput(OreDictUnifier.get(dustSmall, Quicklime),1000, 100)
                .chancedOutput(OreDictUnifier.get(dustSmall, Gold),250, 90)
                .chancedOutput(OreDictUnifier.get(dustSmall, Sapphire),1250, 150)
                .chancedOutput(OreDictUnifier.get(dustSmall, Tantalite),500, 90)
                .buildAndRegister();

        // Seed Oil TODO This duplicates
        EXTRACTOR_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input("listAllSeed", 1)
                .fluidOutputs(SeedOil.getFluid(10))
                .buildAndRegister();

        // Platinum Group Sludge todo consistency with CEu PGS
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, PlatinumGroupSludge));
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(900)
                .input(dust, PlatinumGroupSludge)
                .output(dust, SiliconDioxide)
                .output(dust, Gold)
                .output(dust, PlatinumMetallicPowder, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, PalladiumMetallicPowder, 6), 9500, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, IrMetalResidue, 3), 9000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, RarestMetalResidue, 3), 8500, 0)
                .buildAndRegister();

        // Sheldonite Smelting Recipe todo ore processing rework
        ModHandler.removeFurnaceSmelting(OreDictUnifier.get(dust, Cooperite));
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(dust, Cooperite), OreDictUnifier.get(dust, PlatinumMetallicPowder, 2));

        // Sheldonite Electrolysistodo todo ore processing rework
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
    }

    private static void vanillaOverride() {

        // Glowstone Recipes
        MIXER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Redstone)
                .input(dust, Gold)
                .output(dust, Glowstone, 2)
                .buildAndRegister();

        // Log -> Charcoal Recipes
        // TODO Ignores Forestry wood, still needed?
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
                    ModHandler.removeFurnaceSmelting(woodStack);
                }
            }
        }
    }

    private static void recipeRemoval() {
        // Remove Nuclear Processing TODO nuclear rework
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Uranium238));
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Plutonium239));

        // Remove Rare Earth Centrifuging todo ore processing
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, RareEarth));

        // QCD Matter progression skipping
        GTRecipeHandler.removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_INGOT.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L)});
        GTRecipeHandler.removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_NUGGET.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(L)});
        GTRecipeHandler.removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_PLATE.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L)});
        GTRecipeHandler.removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()},  new FluidStack[]{QCDMatter.getFluid(L * 9)});

        // Remove Yttrium Barium Cuprate Recipes
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Copper, 3), OreDictUnifier.get(dust, Barium, 2), OreDictUnifier.get(dust, Yttrium)}, new FluidStack[]{Oxygen.getFluid(7000)});
    }
}
