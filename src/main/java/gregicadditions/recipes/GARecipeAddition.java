package gregicadditions.recipes;

import forestry.core.ModuleCore;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregicadditions.GAMaterials;
import gregicadditions.armor.PowerlessJetpack;
import gregicadditions.item.*;
import gregicadditions.item.components.*;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.recipes.chain.*;
import gregicadditions.recipes.chain.optical.Lasers;
import gregicadditions.recipes.chain.optical.OpticalCircuits;
import gregicadditions.recipes.chain.optical.OpticalComponents;
import gregicadditions.recipes.chain.optical.OpticalFiber;
import gregicadditions.recipes.chain.wetware.*;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Loader;
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
import static gregtech.common.blocks.BlockMachineCasing.MachineCasingType.LuV;
import static gregtech.common.items.MetaItems.*;

public class GARecipeAddition {

    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Rubber, 144),
            new MaterialStack(StyreneButadieneRubber, 108),
            new MaterialStack(SiliconeRubber, 72)
    };

    private static final MaterialStack[] firstMetal = {
            new MaterialStack(Iron, 1),
            new MaterialStack(Nickel, 1),
            new MaterialStack(Invar, 2),
            new MaterialStack(Steel, 2),
            new MaterialStack(StainlessSteel, 3),
            new MaterialStack(Titanium, 3),
            new MaterialStack(Tungsten, 4),
            new MaterialStack(TungstenSteel, 5)
    };

    private static final MaterialStack[] lastMetal = {
            new MaterialStack(Tin, 0),
            new MaterialStack(Zinc, 0),
            new MaterialStack(Aluminium, 1)
    };

    private static final MaterialStack[] lapisLike = {
            new MaterialStack(Lapis, 1),
            new MaterialStack(Lazurite, 1),
            new MaterialStack(Sodalite, 1)
    };


    public static void init() {


        OreDictUnifier.registerOre(new ItemStack(Items.SNOWBALL), dust, Snow);
        OreDictUnifier.registerOre(new ItemStack(Blocks.SNOW), block, Snow);

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

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(24)
                .inputs(LARGE_FLUID_CELL_STEEL.getStackForm())
                .inputs(ELECTRIC_MOTOR_LV.getStackForm())
                .input(stick, Steel)
                .input(ring, Rubber, 2)
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(HAND_PUMP.getStackForm())
                .buildAndRegister();
      ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(512)
                .inputs(ELECTRIC_MOTOR_HV.getStackForm())
                .input(circuit, Tier.Advanced, 2)
                .input(stick, StainlessSteel)
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(FREEDOM_WRENCH.getStackForm())
              .buildAndRegister();
        

        //GTNH Bricks
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.removeFurnaceSmelting(COMPRESSED_CLAY.getStackForm());
        ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2)
                .inputs(new ItemStack(Items.CLAY_BALL))
                .notConsumable(SHAPE_MOLD_INGOT)
                .outputs(new ItemStack(Items.BRICK))
                .buildAndRegister();

        OreDictionary.registerOre("formWood", WOODEN_FORM_BRICK.getStackForm());

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
      
        String plateB = new String("plate");
        if (GAConfig.GT6.addCurvedPlates) {
            plateB = "plateCurved";
        }

        OrePrefix roundOrScrew;
        if (GAConfig.GT6.addRounds)
            roundOrScrew = GAEnums.GAOrePrefix.round;
        else
            roundOrScrew = screw;

        //GT6 Bending
        if (GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET),
                    "ChC", " P ",
                    'C', plateB + "Iron",
                    'P', "plateIron");

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(valueOf(plateB), Iron, 2)
                    .input(plate, Iron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(valueOf(plateB), WroughtIron, 2)
                    .input(plate, WroughtIron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET),
                "PPP", "ChC",
                'P', "plateIron",
                'C', plateB + "Iron");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE),
                "PhP", "CPC", "CPC",
                'P', "plateIron",
                'C', plateB + "Iron");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS),
                "PCP", "ChC", "C C",
                'P', "plateIron",
                'C', plateB + "Iron");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS),
                "P P", "ChC",
                'P', "plateIron",
                'C', plateB + "Iron");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET),
                "PPP", "ChC",
                'P', "plateGold",
                'C', plateB + "Gold");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE),
                "PhP", "CPC", "CPC",
                'P', "plateGold",
                'C', plateB + "Gold");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS),
                "PCP", "ChC", "C C",
                'P', "plateGold",
                'C', plateB + "Gold");

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS),
                "P P", "ChC",
                'P', "plateGold",
                'C', plateB + "Gold");

            ModHandler.addShapedRecipe("chain_helmet", new ItemStack(Items.CHAINMAIL_HELMET),
                "RRR", "RhR",
                'R', "ringIron");

            ModHandler.addShapedRecipe("chain_chestplate", new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                "RhR", "RRR", "RRR",
                'R', "ringIron");

            ModHandler.addShapedRecipe("chain_leggings", new ItemStack(Items.CHAINMAIL_LEGGINGS),
                "RRR", "RhR", "R R",
                'R', "ringIron");

            ModHandler.addShapedRecipe("chain_boots", new ItemStack(Items.CHAINMAIL_BOOTS),
                "R R", "RhR",
                'R', "ringIron");
        }

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

        // Reinforced Glass
        int multiplier2;
        for (MaterialStack metal1 : firstMetal) {
            IngotMaterial material1 = (IngotMaterial) metal1.material;
            int multiplier1 = (int) metal1.amount;
            for (MaterialStack metal2 : lastMetal) {
                IngotMaterial material2 = (IngotMaterial) metal2.material;
                if ((int) metal1.amount == 1) multiplier2 = 0;
                else multiplier2 = (int) metal2.amount;
                ModHandler.addShapedRecipe("mixed_metal_1_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2),
                        "F", "M", "L",
                        'F', new UnificationEntry(plate, material1),
                        'M', "plateBronze",
                        'L', OreDictUnifier.get(plate, material2));

                ModHandler.addShapedRecipe("mixed_metal_2_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2),
                        "F", "M", "L",
                        'F', new UnificationEntry(plate, material1),
                        'M', "plateBrass",
                        'L', OreDictUnifier.get(plate, material2));

                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8)
                        .input(plate, material1)
                        .input(plank, Bronze)
                        .input(plate, material2)
                        .outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2))
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8)
                        .input(plate, material1)
                        .input(plate, Brass)
                        .input(plate, material2)
                        .outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2))
                        .buildAndRegister();
            }
        }

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4)
                .inputs(ADVANCED_ALLOY_PLATE.getStackForm())
                .input(dust, Glass, 3)
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4)
                .inputs(ADVANCED_ALLOY_PLATE.getStackForm())
                .inputs(new ItemStack(Blocks.GLASS, 3))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 1))
                .fluidInputs(BorosilicateGlass.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS, 1))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(64)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS, 1))
                .fluidInputs(Nickel.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS, 1))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(256)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS, 1))
                .fluidInputs(Chrome.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS, 1))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(1024)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS, 1))
                .fluidInputs(Tungsten.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS, 1))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(4096)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS, 1))
                .fluidInputs(Iridium.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS, 1))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16384)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS, 1))
                .fluidInputs(Osmiridium.getFluid(GTValues.L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS, 1))
                .buildAndRegister();

        //Machine Components

        // EMITTERS -------------------------------------------------------------------------
        ModHandler.addShapedRecipe("ga_lv_emitter", EMITTER_LV.getStackForm(),
                "RRS", "CGR", "SCR",
                'R', OreDictUnifier.get(stick, Brass),
                'S', "circuitBasic",
                'C', OreDictUnifier.get(cableGtSingle, Tin),
                'G', OreDictUnifier.get(gem, Quartzite));

        ModHandler.addShapedRecipe("ga_mv_emitter", EMITTER_MV.getStackForm(),
                "RRS", "CGR", "SCR",
                'R', OreDictUnifier.get(stick, Electrum),
                'S', "circuitGood",
                'C', OreDictUnifier.get(cableGtSingle, Copper),
                'G', OreDictUnifier.get(gem, NetherQuartz));

        ModHandler.addShapedRecipe("ga_hv_emitter", EMITTER_HV.getStackForm(),
                "RRS", "CGR", "SCR",
                'R', OreDictUnifier.get(stick, Chrome),
                'S', "circuitAdvanced",
                'C', OreDictUnifier.get(cableGtSingle, Gold),
                'G', OreDictUnifier.get(gem, Emerald));

        ModHandler.addShapedRecipe("ga_ev_emitter", EMITTER_EV.getStackForm(),
                "RRS", "CGR", "SCR",
                'R', OreDictUnifier.get(stick, Platinum),
                'S', "circuitExtreme",
                'C', OreDictUnifier.get(cableGtSingle, Aluminium),
                'G', OreDictUnifier.get(gem, EnderPearl));

        ModHandler.addShapedRecipe("ga_iv_emitter", EMITTER_IV.getStackForm(),
                "RRS", "CGR", "SCR",
                'R', OreDictUnifier.get(stick, Osmium),
                'S', "circuitElite",
                'C', OreDictUnifier.get(cableGtSingle, Tungsten),
                'G', OreDictUnifier.get(gem, EnderEye));

        // SENSORS -------------------------------------------------------------------------
        ModHandler.addShapedRecipe("ga_lv_sensor", SENSOR_LV.getStackForm(),
                "P G", "PR ", "SPP",
                'P', "plateSteel",
                'G', "gemQuartzite",
                'R', "stickBrass",
                'S', "circuitBasic");

        ModHandler.addShapedRecipe("ga_mv_sensor", SENSOR_MV.getStackForm(),
                "P G", "PR ", "SPP",
                'P', "plateAluminium",
                'G', "gemNetherQuartz",
                'R', "stickElectrum",
                'S', "circuitGood");

        ModHandler.addShapedRecipe("ga_hv_sensor", SENSOR_HV.getStackForm(),
                "P G", "PR ", "SPP",
                'P', "plateStainlessSteel",
                'G', "gemEmerald",
                'R', "stickChrome",
                'S', "circuitAdvanced");

        ModHandler.addShapedRecipe("ga_ev_sensor", SENSOR_EV.getStackForm(),
                "P G", "PR ", "SPP",
                'P', "plateTitanium",
                'G', "gemEnderPearl",
                'R', "stickPlatinum",
                'S', "circuitExtreme");

        ModHandler.addShapedRecipe("ga_iv_sensor", SENSOR_IV.getStackForm(),
                "P G", "PR ", "SPP",
                'P', "plateTungstenSteel",
                'G', "gemEnderEye",
                'R', "stickOsmium",
                'S', "circuitElite");

        // ROBOT ARMS -------------------------------------------------------------------------
        ModHandler.addShapedRecipe("ga_lv_robot_arm", ROBOT_ARM_LV.getStackForm(),
                "CCC", "MRM", "PSR",
                'C', OreDictUnifier.get(cableGtSingle, Tin),
                'M', ELECTRIC_MOTOR_LV.getStackForm(),
                'R', OreDictUnifier.get(stick, Steel),
                'P', ELECTRIC_PISTON_LV.getStackForm(),
                'S', "circuitBasic");

        ModHandler.addShapedRecipe("ga_mv_robot_arm", ROBOT_ARM_MV.getStackForm(),
                "CCC", "MRM", "PSR",
                'C', OreDictUnifier.get(cableGtSingle, Copper),
                'M', ELECTRIC_MOTOR_MV.getStackForm(),
                'R', OreDictUnifier.get(stick, Aluminium),
                'P', ELECTRIC_PISTON_MV.getStackForm(),
                'S', "circuitGood");

        ModHandler.addShapedRecipe("ga_hv_robot_arm", ROBOT_ARM_HV.getStackForm(),
                "CCC", "MRM", "PSR",
                'C', OreDictUnifier.get(cableGtSingle, Gold),
                'M', ELECTRIC_MOTOR_HV.getStackForm(),
                'R', OreDictUnifier.get(stick, StainlessSteel),
                'P', ELECTRIC_PISTON_HV.getStackForm(),
                'S', "circuitAdvanced");

        ModHandler.addShapedRecipe("ga_ev_robot_arm", ROBOT_ARM_EV.getStackForm(),
                "CCC", "MRM", "PSR",
                'C', OreDictUnifier.get(cableGtSingle, Aluminium),
                'M', ELECTRIC_MOTOR_EV.getStackForm(),
                'R', OreDictUnifier.get(stick, Titanium),
                'P', ELECTRIC_PISTON_EV.getStackForm(),
                'S', "circuitExtreme");

        ModHandler.addShapedRecipe("ga_iv_robot_arm", ROBOT_ARM_IV.getStackForm(),
                "CCC", "MRM", "PSR",
                'C', OreDictUnifier.get(cableGtSingle, Tungsten),
                'M', ELECTRIC_MOTOR_IV.getStackForm(),
                'R', OreDictUnifier.get(stick, TungstenSteel),
                'P', ELECTRIC_PISTON_IV.getStackForm(),
                'S', "circuitElite");

        // FIELD GENERATORS -------------------------------------------------------------------------
        ModHandler.addShapedRecipe("ga_lv_field_generator", FIELD_GENERATOR_LV.getStackForm(),
                "WSW", "SGS", "WSW",
                'W', OreDictUnifier.get(wireGtSingle, Osmium),
                'S', "circuitBasic",
                'G', OreDictUnifier.get(gem, EnderPearl));

        ModHandler.addShapedRecipe("ga_mv_field_generator", FIELD_GENERATOR_MV.getStackForm(),
                "WSW", "SGS", "WSW",
                'W', OreDictUnifier.get(wireGtDouble, Osmium),
                'S', "circuitGood",
                'G', OreDictUnifier.get(gem, EnderEye));

        ModHandler.addShapedRecipe("ga_hv_field_generator", FIELD_GENERATOR_HV.getStackForm(),
                "WSW", "SGS", "WSW",
                'W', OreDictUnifier.get(wireGtQuadruple, Osmium),
                'S', "circuitAdvanced",
                'G', QUANTUM_EYE.getStackForm());

        ModHandler.addShapedRecipe("ga_ev_field_generator", FIELD_GENERATOR_EV.getStackForm(),
                "WSW", "SGS", "WSW",
                'W', OreDictUnifier.get(wireGtOctal, Osmium),
                'S', "circuitExtreme",
                'G', OreDictUnifier.get(gem, NetherStar));

        ModHandler.addShapedRecipe("ga_v_field_generator", FIELD_GENERATOR_IV.getStackForm(),
                "WSW", "SGS", "WSW",
                'W', OreDictUnifier.get(wireGtHex, Osmium),
                'S', "circuitElite",
                'G', QUANTUM_STAR.getStackForm());

        // PUMPS -------------------------------------------------------------------------
        ModHandler.addShapedRecipe("lv_electric_pump_paper", ELECTRIC_PUMP_LV.getStackForm(),
                "SRH", "dPw", "HMC",
                'S', OreDictUnifier.get(screw, Tin),
                'R', OreDictUnifier.get(rotor, Tin),
                'H', OreDictUnifier.get(ring, Paper),
                'P', OreDictUnifier.get(pipeMedium, Bronze),
                'M', ELECTRIC_MOTOR_LV.getStackForm(),
                'C', OreDictUnifier.get(cableGtSingle, Tin));

        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;

            ModHandler.addShapedRecipe("lv_electric_pump_" + m.toString(), ELECTRIC_PUMP_LV.getStackForm(),
                    "SRH", "dPw", "HMC",
                    'S', OreDictUnifier.get(screw, Tin),
                    'R', OreDictUnifier.get(rotor, Tin),
                    'H', OreDictUnifier.get(ring, m),
                    'P', OreDictUnifier.get(pipeMedium, Bronze),
                    'M', ELECTRIC_MOTOR_LV.getStackForm(),
                    'C', OreDictUnifier.get(cableGtSingle, Tin));

            ModHandler.addShapedRecipe("mv_electric_pump_" + m.toString(), ELECTRIC_PUMP_MV.getStackForm(),
                    "SRH", "dPw", "HMC",
                    'S', OreDictUnifier.get(screw, Bronze),
                    'R', OreDictUnifier.get(rotor, Bronze),
                    'H', OreDictUnifier.get(ring, m),
                    'P', OreDictUnifier.get(pipeMedium, Steel),
                    'M', ELECTRIC_MOTOR_MV.getStackForm(),
                    'C', OreDictUnifier.get(cableGtSingle, Copper));

            ModHandler.addShapedRecipe("hv_electric_pump_" + m.toString(), ELECTRIC_PUMP_HV.getStackForm(),
                    "SRH", "dPw", "HMC",
                    'S', OreDictUnifier.get(screw, Steel),
                    'R', OreDictUnifier.get(rotor, Steel),
                    'H', OreDictUnifier.get(ring, m),
                    'P', OreDictUnifier.get(pipeMedium, StainlessSteel),
                    'M', ELECTRIC_MOTOR_HV.getStackForm(),
                    'C', OreDictUnifier.get(cableGtSingle, Gold));

            ModHandler.addShapedRecipe("ev_electric_pump_" + m.toString(), ELECTRIC_PUMP_EV.getStackForm(),
                    "SRH", "dPw", "HMC",
                    'S', OreDictUnifier.get(screw, StainlessSteel),
                    'R', OreDictUnifier.get(rotor, StainlessSteel),
                    'H', OreDictUnifier.get(ring, m),
                    'P', OreDictUnifier.get(pipeMedium, Titanium),
                    'M', ELECTRIC_MOTOR_EV.getStackForm(),
                    'C', OreDictUnifier.get(cableGtSingle, Aluminium));

            ModHandler.addShapedRecipe("iv_electric_pump_" + m.toString(), ELECTRIC_PUMP_IV.getStackForm(),
                    "SRH", "dPw", "HMC",
                    'S', OreDictUnifier.get(screw, TungstenSteel),
                    'R', OreDictUnifier.get(rotor, TungstenSteel),
                    'H', OreDictUnifier.get(ring, m),
                    'P', OreDictUnifier.get(pipeMedium, TungstenSteel),
                    'M', ELECTRIC_MOTOR_IV.getStackForm(),
                    'C', OreDictUnifier.get(cableGtSingle, Tungsten));
        }

        // Data Stick
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90)
                .input(circuit, Tier.Good)
                .inputs(MetaItems.PLASTIC_BOARD.getStackForm())
                .inputs(MetaItems.NAND_MEMORY_CHIP.getStackForm(32))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(wireFine, Materials.RedAlloy, 8)
                .input(plate, Materials.Plastic, 4)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(MetaItems.TOOL_DATA_STICK.getStackForm())
                .buildAndRegister();

        if (GAConfig.Misc.assemblerCanMakeComponents) {

            //Automatic Machine Component Recipes

            // MOTORS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .inputs(CountableIngredient.from(cableGtSingle, Tin))
                    .inputs(CountableIngredient.from(roundOrScrew, Steel, 8))
                    .inputs(CountableIngredient.from(stick, SteelMagnetic))
                    .fluidInputs(Copper.getFluid(72))
                    .outputs(ELECTRIC_MOTOR_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .inputs(CountableIngredient.from(cableGtSingle, Copper))
                    .inputs(CountableIngredient.from(roundOrScrew, Aluminium, 8))
                    .inputs(CountableIngredient.from(stick, SteelMagnetic))
                    .fluidInputs(Copper.getFluid(144))
                    .outputs(ELECTRIC_MOTOR_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .inputs(CountableIngredient.from(cableGtSingle, Gold))
                    .inputs(CountableIngredient.from(roundOrScrew, StainlessSteel, 8))
                    .inputs(CountableIngredient.from(stick, SteelMagnetic))
                    .fluidInputs(Copper.getFluid(288))
                    .outputs(ELECTRIC_MOTOR_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium))
                    .inputs(CountableIngredient.from(roundOrScrew, Titanium, 8))
                    .inputs(CountableIngredient.from(stick, NeodymiumMagnetic))
                    .fluidInputs(AnnealedCopper.getFluid(576))
                    .outputs(ELECTRIC_MOTOR_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten))
                    .inputs(CountableIngredient.from(roundOrScrew, TungstenSteel, 8))
                    .inputs(CountableIngredient.from(stick, NeodymiumMagnetic))
                    .fluidInputs(AnnealedCopper.getFluid(1152))
                    .outputs(ELECTRIC_MOTOR_IV.getStackForm())
                    .buildAndRegister();

            // PISTONS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .inputs(CountableIngredient.from(cableGtSingle, Tin))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Steel, 2))
                    .inputs(CountableIngredient.from(stickLong, Steel))
                    .inputs(CountableIngredient.from(gearSmall, Steel))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(72))
                    .outputs(ELECTRIC_PISTON_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .inputs(CountableIngredient.from(cableGtSingle, Copper))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Aluminium, 2))
                    .inputs(CountableIngredient.from(stickLong, Aluminium))
                    .inputs(CountableIngredient.from(gearSmall, Aluminium))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(144))
                    .outputs(ELECTRIC_PISTON_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .inputs(CountableIngredient.from(cableGtSingle, Gold))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), StainlessSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, StainlessSteel))
                    .inputs(CountableIngredient.from(gearSmall, StainlessSteel))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(ELECTRIC_PISTON_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Titanium, 2))
                    .inputs(CountableIngredient.from(stickLong, Titanium))
                    .inputs(CountableIngredient.from(gearSmall, Titanium))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(576))
                    .outputs(ELECTRIC_PISTON_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), TungstenSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, TungstenSteel))
                    .inputs(CountableIngredient.from(gearSmall, TungstenSteel))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(1152))
                    .outputs(ELECTRIC_PISTON_IV.getStackForm())
                    .buildAndRegister();

            // ROBOT ARMS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic))
                    .inputs(CountableIngredient.from(screw, Steel, 3))
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 2))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm()))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_LV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(72))
                    .outputs(ROBOT_ARM_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good))
                    .inputs(CountableIngredient.from(screw, Aluminium, 3))
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 2))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm()))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_MV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(144))
                    .outputs(ROBOT_ARM_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced))
                    .inputs(CountableIngredient.from(screw, StainlessSteel, 3))
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 2))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm()))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_HV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(244))
                    .outputs(ROBOT_ARM_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme))
                    .inputs(CountableIngredient.from(screw, Titanium, 3))
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 2))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm()))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_EV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(576))
                    .outputs(ROBOT_ARM_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite))
                    .inputs(CountableIngredient.from(screw, TungstenSteel, 3))
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 2))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm()))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_IV.getStackForm()))
                    .fluidInputs(SolderingAlloy.getFluid(1152))
                    .outputs(ROBOT_ARM_IV.getStackForm())
                    .buildAndRegister();


            // FIELD GENERATORS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(dust, EnderPearl))
                    .fluidInputs(Osmium.getFluid(72))
                    .outputs(FIELD_GENERATOR_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(dust, EnderEye))
                    .fluidInputs(Osmium.getFluid(144))
                    .outputs(FIELD_GENERATOR_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(QUANTUM_EYE.getStackForm()))
                    .fluidInputs(Osmium.getFluid(288))
                    .outputs(FIELD_GENERATOR_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(dust, NetherStar))
                    .fluidInputs(Osmium.getFluid(576))
                    .outputs(FIELD_GENERATOR_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(QUANTUM_STAR.getStackForm()))
                    .fluidInputs(Osmium.getFluid(1152))
                    .outputs(FIELD_GENERATOR_IV.getStackForm())
                    .buildAndRegister();

            // CONVEYORS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin))
                    .inputs(ELECTRIC_MOTOR_LV.getStackForm(1))
                    .fluidInputs(Rubber.getFluid(432))
                    .outputs(CONVEYOR_MODULE_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper))
                    .inputs( ELECTRIC_MOTOR_MV.getStackForm(1))
                    .fluidInputs(Rubber.getFluid(432))
                    .outputs(CONVEYOR_MODULE_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold))
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(1))
                    .fluidInputs(Rubber.getFluid(432))
                    .outputs(CONVEYOR_MODULE_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium))
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(1))
                    .fluidInputs(Rubber.getFluid(432))
                    .outputs(CONVEYOR_MODULE_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten))
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(1))
                    .fluidInputs(Rubber.getFluid(432))
                    .outputs(CONVEYOR_MODULE_IV.getStackForm())
                    .buildAndRegister();

            // SENSORS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .input(circuit, MarkerMaterials.Tier.Basic)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin))
                    .inputs(OreDictUnifier.get(foil, Steel, 8))
                    .inputs(OreDictUnifier.get(gemFlawless, Quartzite))
                    .fluidInputs(Brass.getFluid(72))
                    .outputs(SENSOR_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .input(circuit, MarkerMaterials.Tier.Good)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper))
                    .inputs(OreDictUnifier.get(foil, Aluminium, 8))
                    .inputs(OreDictUnifier.get(gemFlawless, NetherQuartz))
                    .fluidInputs(Electrum.getFluid(72))
                    .outputs(SENSOR_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .input(circuit, MarkerMaterials.Tier.Advanced)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold))
                    .inputs(OreDictUnifier.get(foil, StainlessSteel, 8))
                    .inputs(OreDictUnifier.get(gemFlawless, Emerald))
                    .fluidInputs(Chrome.getFluid(72))
                    .outputs(SENSOR_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .input(circuit, MarkerMaterials.Tier.Extreme)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium))
                    .inputs(OreDictUnifier.get(foil, Titanium, 8))
                    .inputs(OreDictUnifier.get(gem, EnderPearl))
                    .fluidInputs(Platinum.getFluid(72))
                    .outputs(SENSOR_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .input(circuit, MarkerMaterials.Tier.Elite)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten))
                    .inputs(OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(gem, EnderEye))
                    .fluidInputs(Osmium.getFluid(72))
                    .outputs(SENSOR_IV.getStackForm())
                    .buildAndRegister();

            // EMITTERS -------------------------------------------------------------------------
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .input(circuit, MarkerMaterials.Tier.Basic)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin))
                    .inputs(OreDictUnifier.get(gemFlawless, Quartzite))
                    .fluidInputs(Brass.getFluid(144))
                    .outputs(EMITTER_LV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                    .input(circuit, MarkerMaterials.Tier.Good)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper))
                    .inputs(OreDictUnifier.get(gemFlawless, NetherQuartz))
                    .fluidInputs(Electrum.getFluid(144))
                    .outputs(EMITTER_MV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                    .input(circuit, MarkerMaterials.Tier.Advanced)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold))
                    .inputs(OreDictUnifier.get(gemFlawless, Emerald))
                    .fluidInputs(Chrome.getFluid(144))
                    .outputs(EMITTER_HV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                    .input(circuit, MarkerMaterials.Tier.Extreme)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium))
                    .inputs(OreDictUnifier.get(gem, EnderPearl))
                    .fluidInputs(Platinum.getFluid(144))
                    .outputs(EMITTER_EV.getStackForm())
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .input(circuit, MarkerMaterials.Tier.Elite)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten))
                    .inputs(OreDictUnifier.get(gem, EnderEye))
                    .fluidInputs(Osmium.getFluid(144))
                    .outputs(EMITTER_IV.getStackForm())
                    .buildAndRegister();

            // PUMPS -------------------------------------------------------------------------
            for (MaterialStack stackFluid : cableFluids) {
                IngotMaterial m = (IngotMaterial) stackFluid.material;
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                        .inputs(ELECTRIC_MOTOR_LV.getStackForm())
                        .inputs(OreDictUnifier.get(cableGtSingle, Tin))
                        .inputs(OreDictUnifier.get(pipeMedium, Bronze))
                        .inputs(OreDictUnifier.get(screw, Tin))
                        .inputs(CountableIngredient.from(rotor, Tin))
                        .inputs(CountableIngredient.from(ring, m))
                        .fluidInputs(SolderingAlloy.getFluid(72))
                        .outputs(ELECTRIC_PUMP_LV.getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125)
                        .inputs(ELECTRIC_MOTOR_MV.getStackForm())
                        .inputs(OreDictUnifier.get(cableGtSingle, Copper))
                        .inputs(OreDictUnifier.get(pipeMedium, Steel))
                        .inputs(OreDictUnifier.get(screw, Bronze))
                        .inputs(CountableIngredient.from(rotor, Bronze))
                        .inputs(CountableIngredient.from(ring, m))
                        .fluidInputs(SolderingAlloy.getFluid(72))
                        .outputs(ELECTRIC_PUMP_MV.getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500)
                        .inputs(ELECTRIC_MOTOR_HV.getStackForm())
                        .inputs(OreDictUnifier.get(cableGtSingle, Gold))
                        .inputs(OreDictUnifier.get(pipeMedium, StainlessSteel))
                        .inputs(OreDictUnifier.get(screw, Steel))
                        .inputs(CountableIngredient.from(rotor, Steel))
                        .inputs(CountableIngredient.from(ring, m))
                        .fluidInputs(SolderingAlloy.getFluid(72))
                        .outputs(ELECTRIC_PUMP_HV.getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000)
                        .inputs(ELECTRIC_MOTOR_EV.getStackForm())
                        .inputs(OreDictUnifier.get(cableGtSingle, Aluminium))
                        .inputs(OreDictUnifier.get(pipeMedium, Titanium))
                        .inputs(OreDictUnifier.get(screw, StainlessSteel))
                        .inputs(CountableIngredient.from(rotor, StainlessSteel))
                        .inputs(CountableIngredient.from(ring, m))
                        .fluidInputs(SolderingAlloy.getFluid(72))
                        .outputs(ELECTRIC_PUMP_EV.getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000)
                        .inputs(ELECTRIC_MOTOR_IV.getStackForm())
                        .inputs(OreDictUnifier.get(cableGtSingle, Tungsten))
                        .inputs(OreDictUnifier.get(pipeMedium, TungstenSteel))
                        .inputs(OreDictUnifier.get(screw, TungstenSteel))
                        .inputs(CountableIngredient.from(rotor, TungstenSteel))
                        .inputs(CountableIngredient.from(ring, m))
                        .fluidInputs(SolderingAlloy.getFluid(72))
                        .outputs(ELECTRIC_PUMP_IV.getStackForm())
                        .buildAndRegister();

            }
        }

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            //Automatic Machine Component Recipes with assembly line

            // MOTORS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 4))
                    .inputs(CountableIngredient.from(stickLong, Steel, 4))
                    .inputs(CountableIngredient.from(roundOrScrew, Steel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Steel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Steel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Steel, 16))
                    .inputs(CountableIngredient.from(stickLong, SteelMagnetic, 4))
                    .fluidInputs(Copper.getFluid(72 * 4))
                    .outputs(ELECTRIC_MOTOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 4))
                    .inputs(CountableIngredient.from(stickLong, Aluminium, 4))
                    .inputs(CountableIngredient.from(roundOrScrew, Aluminium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Aluminium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Aluminium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Aluminium, 16))
                    .inputs(CountableIngredient.from(stickLong, SteelMagnetic, 4))
                    .fluidInputs(Copper.getFluid(144 * 4))
                    .outputs(ELECTRIC_MOTOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 4))
                    .inputs(CountableIngredient.from(stickLong, StainlessSteel, 4))
                    .inputs(CountableIngredient.from(roundOrScrew, StainlessSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, StainlessSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, StainlessSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, StainlessSteel, 16))
                    .inputs(CountableIngredient.from(stickLong, SteelMagnetic, 4))
                    .fluidInputs(Copper.getFluid(288 * 4))
                    .outputs(ELECTRIC_MOTOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 4))
                    .inputs( CountableIngredient.from(cableGtSingle, Aluminium, 4))
                    .inputs(CountableIngredient.from(stickLong, Titanium, 4))
                    .inputs(CountableIngredient.from(roundOrScrew, Titanium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Titanium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Titanium, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, Titanium, 16))
                    .inputs(CountableIngredient.from(stickLong, NeodymiumMagnetic, 4))
                    .fluidInputs(AnnealedCopper.getFluid(576 * 4))
                    .outputs(ELECTRIC_MOTOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 4))
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 4))
                    .inputs(CountableIngredient.from(stickLong, TungstenSteel, 4))
                    .inputs(CountableIngredient.from(roundOrScrew, TungstenSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, TungstenSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, TungstenSteel, 16))
                    .inputs(CountableIngredient.from(roundOrScrew, TungstenSteel, 16))
                    .inputs(CountableIngredient.from(stickLong, NeodymiumMagnetic, 4))
                    .fluidInputs(AnnealedCopper.getFluid(1152 * 4))
                    .outputs(ELECTRIC_MOTOR_IV.getStackForm(16))
                    .buildAndRegister();

            // PISTONS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 8))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Steel, 2))
                    .inputs(CountableIngredient.from(plate, Steel, 2))
                    .inputs(CountableIngredient.from(ingot, Steel, 2))
                    .inputs(CountableIngredient.from(stickLong, Steel, 2))
                    .inputs(CountableIngredient.from(stickLong, Steel, 2))
                    .inputs(CountableIngredient.from(gearSmall, Steel, 4))
                    .inputs(CountableIngredient.from(gearSmall, Steel, 4))
                    .inputs(CountableIngredient.from(gearSmall, Steel, 4))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                    .outputs(ELECTRIC_PISTON_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 8))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Aluminium, 2))
                    .inputs(CountableIngredient.from(plate, Aluminium, 2))
                    .inputs(CountableIngredient.from(ingot, Aluminium, 2))
                    .inputs(CountableIngredient.from(stickLong, Aluminium, 2))
                    .inputs(CountableIngredient.from(stickLong, Aluminium, 2))
                    .inputs(CountableIngredient.from(gearSmall, Aluminium, 4))
                    .inputs(CountableIngredient.from(gearSmall, Aluminium, 4))
                    .inputs(CountableIngredient.from(gearSmall, Aluminium, 4))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                    .outputs(ELECTRIC_PISTON_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 8))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), StainlessSteel, 2))
                    .inputs(CountableIngredient.from(plate, StainlessSteel, 2))
                    .inputs(CountableIngredient.from(ingot, StainlessSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, StainlessSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, StainlessSteel, 2))
                    .inputs(CountableIngredient.from(gearSmall, StainlessSteel, 4))
                    .inputs(CountableIngredient.from(gearSmall, StainlessSteel, 4))
                    .inputs(CountableIngredient.from(gearSmall, StainlessSteel, 4))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(288 * 4))
                    .outputs(ELECTRIC_PISTON_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 8))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), Titanium, 2))
                    .inputs(CountableIngredient.from(plate, Titanium, 2))
                    .inputs(CountableIngredient.from(ingot, Titanium, 2))
                    .inputs(CountableIngredient.from(stickLong, Titanium, 2))
                    .inputs(CountableIngredient.from(stickLong, Titanium, 2))
                    .inputs(CountableIngredient.from(gearSmall, Titanium, 4))
                    .inputs(CountableIngredient.from(gearSmall, Titanium, 4))
                    .inputs(CountableIngredient.from(gearSmall, Titanium, 4))
                    .inputs(CountableIngredient.from(gearSmall, Titanium, 4))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(576 * 4))
                    .outputs(ELECTRIC_PISTON_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 8))
                    .inputs(CountableIngredient.from(OrePrefix.getPrefix(plateB), TungstenSteel, 2))
                    .inputs(CountableIngredient.from(plate, TungstenSteel, 2))
                    .inputs(CountableIngredient.from(ingot, TungstenSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, TungstenSteel, 2))
                    .inputs(CountableIngredient.from(stickLong, TungstenSteel, 2))
                    .inputs(CountableIngredient.from(gearSmall, TungstenSteel, 4))
                    .inputs(CountableIngredient.from(gearSmall, TungstenSteel, 4))
                    .inputs(CountableIngredient.from(gearSmall, TungstenSteel, 4))
                    .inputs(CountableIngredient.from(gearSmall, TungstenSteel, 4))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(1152 * 4))
                    .outputs(ELECTRIC_PISTON_IV.getStackForm(16))
                    .buildAndRegister();

            // ROBOT ARMS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(screw, Steel, 8))
                    .inputs(CountableIngredient.from(screw, Steel, 8))
                    .inputs(CountableIngredient.from(cableGtSingle, Tin, 16))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm(4)))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_LV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                    .outputs(ROBOT_ARM_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(screw, Aluminium, 8))
                    .inputs(CountableIngredient.from(screw, Aluminium, 8))
                    .inputs(CountableIngredient.from(cableGtSingle, Copper, 16))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm(4)))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_MV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                    .outputs(ROBOT_ARM_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(screw, StainlessSteel, 8))
                    .inputs(CountableIngredient.from(screw, StainlessSteel, 8))
                    .inputs(CountableIngredient.from(cableGtSingle, Gold, 16))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm(4)))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_HV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(244 * 4))
                    .outputs(ROBOT_ARM_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(screw, Titanium, 8))
                    .inputs(CountableIngredient.from(screw, Titanium, 8))
                    .inputs(CountableIngredient.from(cableGtSingle, Aluminium, 16))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm(4)))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_EV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(576 * 4))
                    .outputs(ROBOT_ARM_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(screw, TungstenSteel, 8))
                    .inputs(CountableIngredient.from(screw, TungstenSteel, 8))
                    .inputs(CountableIngredient.from(cableGtSingle, Tungsten, 16))
                    .inputs(CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm(4)))
                    .inputs(CountableIngredient.from(ELECTRIC_PISTON_IV.getStackForm(4)))
                    .fluidInputs(SolderingAlloy.getFluid(1152 * 4))
                    .outputs(ROBOT_ARM_IV.getStackForm(16))
                    .buildAndRegister();

            // CONVEYORS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(432 * 4))
                    .outputs(CONVEYOR_MODULE_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(432 * 4))
                    .outputs(CONVEYOR_MODULE_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs( OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(432 * 4))
                    .outputs(CONVEYOR_MODULE_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(432 * 4))
                    .outputs(CONVEYOR_MODULE_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(432 * 4))
                    .outputs(CONVEYOR_MODULE_IV.getStackForm(16))
                    .buildAndRegister();

            // FIELD GENERATORS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Basic, 2))
                    .inputs(CountableIngredient.from(dust, EnderPearl))
                    .inputs(CountableIngredient.from(dust, EnderPearl))
                    .inputs(CountableIngredient.from(dust, EnderPearl))
                    .inputs(CountableIngredient.from(dust, EnderPearl))
                    .fluidInputs(Osmium.getFluid(72 * 4))
                    .outputs(FIELD_GENERATOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Good, 2))
                    .inputs(CountableIngredient.from(dust, EnderEye))
                    .inputs(CountableIngredient.from(dust, EnderEye))
                    .inputs(CountableIngredient.from(dust, EnderEye))
                    .inputs(CountableIngredient.from(dust, EnderEye))
                    .fluidInputs(Osmium.getFluid(144 * 4))
                    .outputs(FIELD_GENERATOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Advanced, 2))
                    .inputs(CountableIngredient.from(QUANTUM_EYE.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_EYE.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_EYE.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_EYE.getStackForm()))
                    .fluidInputs(Osmium.getFluid(288 * 4))
                    .outputs(FIELD_GENERATOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 2))
                    .inputs(CountableIngredient.from(dust, NetherStar))
                    .inputs(CountableIngredient.from(dust, NetherStar))
                    .inputs(CountableIngredient.from(dust, NetherStar))
                    .inputs(CountableIngredient.from(dust, NetherStar))
                    .fluidInputs(Osmium.getFluid(576 * 4))
                    .outputs(FIELD_GENERATOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Elite, 2))
                    .inputs(CountableIngredient.from(QUANTUM_STAR.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_STAR.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_STAR.getStackForm()))
                    .inputs(CountableIngredient.from(QUANTUM_STAR.getStackForm()))
                    .fluidInputs(Osmium.getFluid(1152 * 4))
                    .outputs(FIELD_GENERATOR_IV.getStackForm(16))
                    .buildAndRegister();

            // SENSORS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .input(circuit, MarkerMaterials.Tier.Basic, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(foil, Steel, 8))
                    .inputs(OreDictUnifier.get(foil, Steel, 8))
                    .inputs(OreDictUnifier.get(foil, Steel, 8))
                    .inputs(OreDictUnifier.get(foil, Steel, 8))
                    .inputs(OreDictUnifier.get(gemExquisite, Quartzite, 2))
                    .fluidInputs(Brass.getFluid(72 * 4))
                    .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                    .outputs(SENSOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(16000)
                    .input(circuit, MarkerMaterials.Tier.Good, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(foil, Aluminium, 8))
                    .inputs(OreDictUnifier.get(foil, Aluminium, 8))
                    .inputs(OreDictUnifier.get(foil, Aluminium, 8))
                    .inputs(OreDictUnifier.get(foil, Aluminium, 8))
                    .inputs(OreDictUnifier.get(gemExquisite, NetherQuartz, 2))
                    .fluidInputs(Electrum.getFluid(72 * 4))
                    .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                    .outputs(SENSOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(32000)
                    .input(circuit, MarkerMaterials.Tier.Advanced, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(foil, StainlessSteel, 8))
                    .inputs(OreDictUnifier.get(foil, StainlessSteel, 8))
                    .inputs(OreDictUnifier.get(foil, StainlessSteel, 8))
                    .inputs(OreDictUnifier.get(foil, StainlessSteel, 8))
                    .inputs(OreDictUnifier.get(gemExquisite, Emerald, 2))
                    .fluidInputs(Chrome.getFluid(72 * 4))
                    .fluidInputs(SolderingAlloy.getFluid(288 * 4))
                    .outputs(SENSOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(64000)
                    .input(circuit, MarkerMaterials.Tier.Extreme, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(foil, Titanium, 8))
                    .inputs(OreDictUnifier.get(foil, Titanium, 8))
                    .inputs(OreDictUnifier.get(foil, Titanium, 8))
                    .inputs(OreDictUnifier.get(foil, Titanium, 8))
                    .inputs(OreDictUnifier.get(gem, EnderPearl, 16))
                    .fluidInputs(Platinum.getFluid(72 * 4))
                    .fluidInputs(SolderingAlloy.getFluid(576 * 4))
                    .outputs(SENSOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(128000)
                    .input(circuit, MarkerMaterials.Tier.Elite, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(foil, TungstenSteel, 8))
                    .inputs(OreDictUnifier.get(foil, TungstenSteel, 8))
                    .inputs(OreDictUnifier.get(foil, TungstenSteel, 8))
                    .inputs(OreDictUnifier.get(foil, TungstenSteel, 8))
                    .inputs(OreDictUnifier.get(gem, EnderEye, 16))
                    .fluidInputs(Osmium.getFluid(72 * 4))
                    .fluidInputs(SolderingAlloy.getFluid(1152 * 4))
                    .outputs(SENSOR_IV.getStackForm(16))
                    .buildAndRegister();

            // EMITTERS -------------------------------------------------------------------------
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(circuit, MarkerMaterials.Tier.Basic, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                    .inputs(OreDictUnifier.get(gem, Quartzite, 4))
                    .fluidInputs(Brass.getFluid(72 * 4))
                    .outputs(EMITTER_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(circuit, MarkerMaterials.Tier.Good, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                    .inputs(OreDictUnifier.get(gem, NetherQuartz, 4))
                    .fluidInputs(Electrum.getFluid(72 * 4))
                    .outputs(EMITTER_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(circuit, MarkerMaterials.Tier.Advanced, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                    .inputs(OreDictUnifier.get(gem, Emerald, 4))
                    .fluidInputs(Chrome.getFluid(72 * 4))
                    .outputs(EMITTER_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(circuit, MarkerMaterials.Tier.Extreme, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                    .inputs(OreDictUnifier.get(gem, EnderPearl, 4))
                    .fluidInputs(Platinum.getFluid(72 * 4))
                    .outputs(EMITTER_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(circuit, MarkerMaterials.Tier.Elite, 4)
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                    .inputs(OreDictUnifier.get(gem, EnderEye, 4))
                    .fluidInputs(Osmium.getFluid(72 * 4))
                    .outputs(EMITTER_IV.getStackForm(16))
                    .buildAndRegister();

            // PUMPS -------------------------------------------------------------------------
            for (MaterialStack stackFluid : cableFluids) {
                IngotMaterial m = (IngotMaterial) stackFluid.material;
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                        .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tin, 4))
                        .inputs(OreDictUnifier.get(pipeSmall, Bronze, 16))
                        .inputs(OreDictUnifier.get(screw, Tin, 16))
                        .inputs(CountableIngredient.from(rotor, Tin, 4))
                        .inputs(CountableIngredient.from(ring, m, 8))
                        .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                        .outputs(ELECTRIC_PUMP_LV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                        .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Copper, 4))
                        .inputs(OreDictUnifier.get(pipeSmall, Steel, 16))
                        .inputs(OreDictUnifier.get(screw, Bronze, 16))
                        .inputs(CountableIngredient.from(rotor, Bronze, 4))
                        .inputs( CountableIngredient.from(ring, m, 8))
                        .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                        .outputs(ELECTRIC_PUMP_MV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                        .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Gold, 4))
                        .inputs(OreDictUnifier.get(pipeSmall, StainlessSteel, 16))
                        .inputs(OreDictUnifier.get(screw, Steel, 16))
                        .inputs(CountableIngredient.from(rotor, Steel, 4))
                        .inputs(CountableIngredient.from(ring, m, 8))
                        .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                        .outputs(ELECTRIC_PUMP_HV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                        .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4))
                        .inputs(OreDictUnifier.get(pipeSmall, Titanium, 16))
                        .inputs(OreDictUnifier.get(screw, StainlessSteel, 16))
                        .inputs(CountableIngredient.from(rotor, StainlessSteel, 4))
                        .inputs(CountableIngredient.from(ring, m, 8))
                        .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                        .outputs(ELECTRIC_PUMP_EV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                        .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                        .inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4))
                        .inputs(OreDictUnifier.get(pipeSmall, TungstenSteel, 16))
                        .inputs(OreDictUnifier.get(screw, TungstenSteel, 16))
                        .inputs(CountableIngredient.from(rotor, TungstenSteel, 4))
                        .inputs(CountableIngredient.from(ring, m, 8))
                        .fluidInputs(SolderingAlloy.getFluid(72 * 4))
                        .outputs(ELECTRIC_PUMP_IV.getStackForm(16))
                        .buildAndRegister();
            }
        }

        // Nightvision Goggles
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(128)
            .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
            .inputs(SENSOR_MV.getStackForm(2))
            .inputs(EMITTER_MV.getStackForm(2))
            .inputs(BATTERY_RE_MV_LITHIUM.getStackForm())
            .inputs(INSULATING_TAPE.getStackForm(2))
            .outputs(NIGHTVISION_GOGGLES.getStackForm())
            .notConsumable(new IntCircuitIngredient(3))
            .buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
            .input("circuitAdvanced", 1)
            .inputs(MetaItems.CARBON_PLATE.getStackForm(7))
            .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
            .notConsumable(new IntCircuitIngredient(0))
            .outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
            .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
            .input("circuitAdvanced", 1)
            .inputs(MetaItems.CARBON_PLATE.getStackForm(6))
            .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
            .notConsumable(new IntCircuitIngredient(1))
            .outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
            .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
            .input("circuitAdvanced", 1)
            .inputs(MetaItems.CARBON_PLATE.getStackForm(4))
            .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
            .notConsumable(new IntCircuitIngredient(2))
            .outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
            .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
            .input("circuitAdvanced", 2)
            .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
            .inputs(NIGHTVISION_GOGGLES.getStackForm())
            .inputs(MetaItems.CARBON_PLATE.getStackForm(5))
            .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
            .notConsumable(new IntCircuitIngredient(3))
            .outputs(NANO_MUSCLE_SUITE_HELMET.getStackForm())
            .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1500).EUt(1024)
            .input("circuitExtreme", 2)
            .inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
            .inputs(ADVANCED_IMPELLER_JETPACK.getStackForm())
            .inputs(INSULATING_TAPE.getStackForm(4))
            .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4))
            .outputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
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


        // Assembly Line Casings
        ModHandler.addShapedRecipe("assline_casing", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING, 2),
                "PhP", "AFA", "PwP",
                'P', "plateSteel", 'A', ROBOT_ARM_IV.getStackForm(),
                'F', OreDictUnifier.get(frameGt, TungstenSteel));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaItems.ROBOT_ARM_IV.getStackForm(2))
                .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Steel, 4))
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.TungstenSteel))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING, 2))
                .duration(100).EUt(8000).buildAndRegister();

        ModHandler.addShapedRecipe("ga_assembler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING, 3), "CCC", "CFC", "CMC",
                'C', "circuitElite",
                'F', "frameGtTungstenSteel",
                'M', ELECTRIC_MOTOR_IV.getStackForm());

        // Large Chemical Reactor Casing
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .input(valueOf("gtMetalCasing"), Steel, 1)
                .fluidInputs(Polytetrafluoroethylene.getFluid(216))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT, 1))
                .buildAndRegister();

        // Integral Frameworks
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(circuit, MarkerMaterials.Tier.Primitive, 2)
                .inputs(OreDictUnifier.get(gear, Potin, 8))
                .inputs(OreDictUnifier.get(plate, Potin, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Tin))
                .inputs(MetaTileEntities.HULL[GTValues.ULV].getStackForm())
                .fluidInputs(Steel.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ULV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(32)
                .input(circuit, MarkerMaterials.Tier.Basic, 2)
                .inputs(OreDictUnifier.get(gear, Magnalium, 8))
                .inputs(OreDictUnifier.get(plate, Magnalium, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Cobalt))
                .inputs(MetaTileEntities.HULL[GTValues.LV].getStackForm())
                .fluidInputs(Silicon.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(64)
                .input(circuit, MarkerMaterials.Tier.Good, 2)
                .inputs(OreDictUnifier.get(gear, EglinSteel, 8))
                .inputs(OreDictUnifier.get(plate, EglinSteel, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, AnnealedCopper))
                .inputs(MetaTileEntities.HULL[GTValues.MV].getStackForm())
                .fluidInputs(BabbittAlloy.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_MV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(128)
                .input(circuit, MarkerMaterials.Tier.Advanced, 2)
                .inputs(OreDictUnifier.get(gear, Inconel625, 8))
                .inputs(OreDictUnifier.get(plate, Inconel625, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Gold))
                .inputs(MetaTileEntities.HULL[GTValues.HV].getStackForm())
                .fluidInputs(Inconel625.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_HV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(256)
                .input(circuit, MarkerMaterials.Tier.Extreme, 2)
                .inputs(OreDictUnifier.get(gear, TungstenCarbide, 8))
                .inputs(OreDictUnifier.get(plate, TungstenCarbide, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Titanium))
                .inputs(MetaTileEntities.HULL[GTValues.EV].getStackForm())
                .fluidInputs(Stellite.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_EV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(512)
                .input(circuit, MarkerMaterials.Tier.Elite, 2)
                .inputs(OreDictUnifier.get(gear, Nitinol60, 8))
                .inputs(OreDictUnifier.get(plate, Nitinol60, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Nichrome))
                .inputs(MetaTileEntities.HULL[GTValues.IV].getStackForm())
                .fluidInputs(Thorium.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_IV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(1024)
                .input(circuit, MarkerMaterials.Tier.Master, 2)
                .inputs(OreDictUnifier.get(gear, IncoloyMA956, 8))
                .inputs(OreDictUnifier.get(plate, IncoloyMA956, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, Platinum))
                .inputs(MetaTileEntities.HULL[GTValues.LuV].getStackForm())
                .fluidInputs(Uranium235.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LUV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2048)
                .input(circuit, MarkerMaterials.Tier.Ultimate, 2)
                .inputs(OreDictUnifier.get(gear, BabbittAlloy, 8))
                .inputs(OreDictUnifier.get(plate, BabbittAlloy, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, NiobiumTitanium))
                .inputs(MetaTileEntities.HULL[GTValues.ZPM].getStackForm())
                .fluidInputs(Plutonium241.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ZPM))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4096)
                .input(circuit, MarkerMaterials.Tier.Superconductor, 2)
                .inputs(OreDictUnifier.get(gear, HG1223, 8))
                .inputs(OreDictUnifier.get(plate, HG1223, 8))
                .inputs(OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate))
                .inputs(MetaTileEntities.HULL[GTValues.UV].getStackForm())
                .fluidInputs(NaquadahEnriched.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_UV))
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

        // Reactor Casing
        FORMING_PRESS_RECIPES.recipeBuilder().duration(1500).EUt(500)
                .input(plateDense, Lead, 9)
                .input(plateDense, Lead, 9)
                .input(plateDense, ReactorSteel, 4)
                .input(plateDense, StainlessSteel, 2)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CLADDED_REACTOR_CASING, 4))
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

        // UHV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(2781).EUt(30)
                .inputs(TBCCODust.getItemStack(4))
                .inputs(StrontiumSuperconductorDust.getItemStack(4))
                .input(dust, Taranium)
                .outputs(OreDictUnifier.get(dust, UHVSuperconductorBase, 9))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3081).EUt(30)
                .input(dust, UHVSuperconductorBase, 9)
                .outputs(TBCCODust.getItemStack(4))
                .outputs(StrontiumSuperconductorDust.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Taranium))
                .buildAndRegister();

        // UEV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(11292).EUt(30)
                .inputs(ActiniumSuperhydride.getItemStack())
                .inputs(BETSPerrhenate.getItemStack())
                .input(dust, Vibranium, 2)
                .input(dust, Quantum)
                .input(dust, TriniumTitanium)
                .outputs(OreDictUnifier.get(dust, UEVSuperconductorBase, 6))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(11892).EUt(30)
                .input(dust, UEVSuperconductorBase, 6)
                .outputs(ActiniumSuperhydride.getItemStack())
                .outputs(StrontiumSuperconductorDust.getItemStack())
                .outputs(OreDictUnifier.get(dust, Vibranium, 2))
                .outputs(OreDictUnifier.get(dust, Quantum))
                .outputs(OreDictUnifier.get(dust, TriniumTitanium))
                .buildAndRegister();

        // UIV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(3395).EUt(30)
                .inputs(BorocarbideDust.getItemStack(2))
                .inputs(FullereneSuperconductiveDust.getItemStack())
                .input(dust, MetastableOganesson, 2)
                .input(dust, ProtoAdamantium, 2)
                .outputs(OreDictUnifier.get(dust, UIVSuperconductorBase, 7))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3995).EUt(30)
                .input(dust, UIVSuperconductorBase, 7)
                .outputs(BorocarbideDust.getItemStack(2))
                .outputs(FullereneSuperconductiveDust.getItemStack())
                .outputs(OreDictUnifier.get(dust, MetastableOganesson, 2))
                .outputs(OreDictUnifier.get(dust, ProtoAdamantium, 2))
                .buildAndRegister();

        // UMV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(720).EUt(8500000)
                .input(dust, BlackTitanium, 3)
                .input(dust, SuperheavyHAlloy, 2)
                .inputs(ChargedCesiumCeriumCobaltIndium.getItemStack(3))
                .inputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(6))
                .outputs(OreDictUnifier.get(dust, UMVSuperconductorBase, 14))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(8500000)
                .input(dust, UMVSuperconductorBase, 14)
                .outputs(OreDictUnifier.get(dust, BlackTitanium, 3))
                .outputs(OreDictUnifier.get(dust, SuperheavyHAlloy, 2))
                .outputs(ChargedCesiumCeriumCobaltIndium.getItemStack(3))
                .outputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(6))
                .buildAndRegister();

        // UXV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(720).EUt(33500000)
                .inputs(Legendarium.getItemStack(5))
                .input(dust, Neutronium, 4)
                .inputs(ActiniumSuperhydride.getItemStack(5))
                .inputs(LanthanumFullereneNanotubes.getItemStack(4))
                .inputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(12))
                .outputs(OreDictUnifier.get(dust, UXVSuperconductorBase, 30))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(33500000)
                .input(dust, UXVSuperconductorBase, 30)
                .outputs(Legendarium.getItemStack(5))
                .outputs(OreDictUnifier.get(dust, Neutronium, 4))
                .outputs(ActiniumSuperhydride.getItemStack(5))
                .outputs(LanthanumFullereneNanotubes.getItemStack(4))
                .outputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(12))
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

        // Large Assembler Casing
        ASSEMBLER_RECIPES.recipeBuilder().EUt(8000).duration(600)
                .fluidInputs(HastelloyN.getFluid(144 * 4))
                .input(valueOf("gtMetalCasing"), Staballoy, 2)
                .inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER, 2))
                .buildAndRegister();

        // Battery Tower Cells
        ASSEMBLER_RECIPES.recipeBuilder().EUt(240).duration(1200)
                .input(plateDense, Lead, 4)
                .fluidInputs(Oxygen.getFluid(16000))
                .input(valueOf("gtMetalCasing"), StainlessSteel)
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_HV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(960).duration(2400)
                .input(plateDense, Titanium, 4)
                .fluidInputs(Nitrogen.getFluid(16000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_HV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_EV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3840).duration(4800)
                .input(plateDense, TungstenSteel, 4)
                .fluidInputs(Helium.getFluid(8000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_EV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_IV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(15360).duration(9600)
                .input(plateDense, Iridium, 4)
                .fluidInputs(Argon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_IV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_LUV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(61440).duration(18200)
                .input(plateDense, NaquadahAlloy, 4)
                .fluidInputs(Radon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_LUV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_ZPM))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(245760).duration(36400)
                .input(plateDense, Tritanium, 4)
                .fluidInputs(Xenon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_ZPM))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_UV))
                .buildAndRegister();

        // GTCE Casings
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, Bronze, 6)
                .input(frameGt, Bronze, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, Invar, 6)
                .input(frameGt, Invar, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, Steel, 6)
                .input(frameGt, Steel, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, Aluminium, 6)
                .input(frameGt, Aluminium, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, TungstenSteel, 6)
                .input(frameGt, TungstenSteel, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, StainlessSteel, 6)
                .input(frameGt, StainlessSteel, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN, 3)).duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16)
                .notConsumable(new IntCircuitIngredient(30))
                .input(plate, Titanium, 6)
                .input(frameGt, Titanium, 1)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE, 3)).duration(50)
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


        // Armor
        ModHandler.addShapedRecipe("gtadditions:insulating_tape", INSULATING_TAPE.getStackForm(6),
                "RRR", "SSS",
                'R', new ItemStack(Items.PAPER),
                'S', MetaItems.RUBBER_DROP.getStackForm());

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100)
                .input(circuit, MarkerMaterials.Tier.Good, 6)
                .inputs(MetaTileEntities.STEEL_TANK.getStackForm())
                .inputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm(2))
                .inputs(OreDictUnifier.get(pipeSmall, Plastic, 2))
                .inputs(OreDictUnifier.get(pipeMedium, Steel, 2))
                .inputs(OreDictUnifier.get(plate, Aluminium))
                .inputs(OreDictUnifier.get(screw, Aluminium, 4))
                .inputs(OreDictUnifier.get(stick, Aluminium, 2))
                .outputs(SEMIFLUID_JETPACK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100)
                .input(circuit, MarkerMaterials.Tier.Good, 6)
                .inputs(MetaItems.BATTERY_RE_MV_CADMIUM.getStackForm(6))
                .inputs(IMPELLER_MV.getStackForm(4))
                .inputs(OreDictUnifier.get(plate, Aluminium))
                .inputs(OreDictUnifier.get(screw, Aluminium, 4))
                .inputs(OreDictUnifier.get(stick, Aluminium, 2))
                .outputs(IMPELLER_JETPACK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(OreDictUnifier.get(cableGtSingle, Copper))
                .inputs(MetaItems.ELECTRIC_MOTOR_MV.getStackForm())
                .inputs(OreDictUnifier.get(stick, Steel))
                .inputs(OreDictUnifier.get(rotor, Plastic, 2))
                .inputs(OreDictUnifier.get(pipeMedium, Plastic))
                .outputs(IMPELLER_MV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(OreDictUnifier.get(cableGtSingle, Gold))
                .inputs(MetaItems.ELECTRIC_MOTOR_HV.getStackForm())
                .inputs(OreDictUnifier.get(stick, StainlessSteel))
                .inputs(OreDictUnifier.get(rotor, Plastic, 2))
                .inputs(OreDictUnifier.get(pipeMedium, Plastic))
                .outputs(IMPELLER_HV.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("gtadditions:battery_pack.lv", BATPACK_LV.getStackForm(), "BPB", "BCB", "B B",
                'B', MetaItems.BATTERY_RE_LV_LITHIUM.getStackForm(),
                'C', "circuitBasic",
                'P', OreDictUnifier.get(plate, Steel));

        ModHandler.addShapedRecipe("gtadditions:battery_pack.mv", BATPACK_MV.getStackForm(), "BPB", "BCB", "B B",
                'B', MetaItems.BATTERY_RE_MV_LITHIUM.getStackForm(),
                'C', "circuitGood",
                'P', OreDictUnifier.get(plate, Aluminium));

        ModHandler.addShapedRecipe("gtadditions:battery_pack.hv", BATPACK_HV.getStackForm(), "BPB", "BCB", "B B",
                'B', MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm(),
                'C', "circuitAdvanced",
                'P', OreDictUnifier.get(plate, StainlessSteel));

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(400)
                .input(circuit, MarkerMaterials.Tier.Good, 4)
                .input(circuit, MarkerMaterials.Tier.Advanced, 1)
                .inputs(BATPACK_HV.getStackForm())
                .inputs(IMPELLER_HV.getStackForm(6))
                .inputs(MetaItems.BATTERY_RE_HV_CADMIUM.getStackForm())
                .inputs(OreDictUnifier.get(plate, Aluminium))
                .inputs(OreDictUnifier.get(screw, Aluminium, 4))
                .inputs(OreDictUnifier.get(stick, Aluminium, 2))
                .outputs(ADVANCED_IMPELLER_JETPACK.getStackForm())
                .buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input("circuitAdvanced", 1)
                .inputs(MetaItems.CARBON_PLATE.getStackForm(7))
                .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input("circuitAdvanced", 1)
                .inputs(MetaItems.CARBON_PLATE.getStackForm(6))
                .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input("circuitAdvanced", 1)
                .inputs(MetaItems.CARBON_PLATE.getStackForm(4))
                .inputs(MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(2))
                .outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input("circuitAdvanced", 2)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .inputs(MetaItems.SENSOR_HV.getStackForm(2), MetaItems.EMITTER_HV.getStackForm(2))
                .inputs(MetaItems.CARBON_PLATE.getStackForm(4), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(3))
                .outputs(NANO_MUSCLE_SUITE_HELMET.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1500).EUt(1024)
                .input("circuitExtreme", 2)
                .inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .inputs(ADVANCED_IMPELLER_JETPACK.getStackForm())
                .inputs(INSULATING_TAPE.getStackForm(4))
                .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .outputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        //QuarkTech Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input("circuitExtreme", 2)
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4))
                .inputs(MetaItems.ELECTRIC_PISTON_EV.getStackForm(2))
                .inputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
                .outputs(QUARK_TECH_SUITE_BOOTS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input("circuitExtreme", 4)
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(6))
                .inputs( MetaItems.CONVEYOR_MODULE_EV.getStackForm(2))
                .inputs( NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
                .outputs(QUARK_TECH_SUITE_LEGGINS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input("circuitExtreme", 4)
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(8))
                .inputs(MetaItems.FIELD_GENERATOR_EV.getStackForm(2))
                .inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .outputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input("circuitExtreme", 2)
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm())
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4))
                .inputs(MetaItems.SENSOR_EV.getStackForm())
                .inputs(MetaItems.EMITTER_EV.getStackForm())
                .inputs(NANO_MUSCLE_SUITE_HELMET.getStackForm())
                .outputs(QUARK_TECH_SUITE_HELMET.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(7100)
                .inputs(MetaItems.FIELD_GENERATOR_IV.getStackForm())
                .inputs(MetaItems.FIELD_GENERATOR_EV.getStackForm(2))
                .input("circuitMaster", 4)
                .input(wireGtSingle, IVSuperconductor, 4)
                .inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GRAVITATION_ENGINE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(12))
                .input("circuitElite", 4)
                .inputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(16))
                .input("circuitElite", 2)
                .inputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        // Jetpack cleaning recipes
        // could be better, but....
        for (FuelRecipe recipe : PowerlessJetpack.fuels) {
            if (PowerlessJetpack.forbiddenFuels.contains(recipe.getRecipeFluid().getFluid())) continue;
            ItemStack jetpack = SEMIFLUID_JETPACK.getStackForm();
            IFluidHandlerItem cont = jetpack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            if (cont == null) continue;
            FluidStack fluid = recipe.getRecipeFluid();
            IFluidTankProperties[] prop = cont.getTankProperties();
            if (prop.length < 1) continue;
            if (prop[0] == null) continue;
            fluid.amount = prop[0].getCapacity();
            cont.fill(fluid, true);
            ModHandler.addShapelessRecipe("gtadditions:clean_jetpack_" + fluid.getUnlocalizedName(), SEMIFLUID_JETPACK.getStackForm(), jetpack);
        }

        COMPRESSOR_RECIPES.recipeBuilder().EUt(120).duration(300)
                .input(ingot, Graphite)
                .outputs(PYROLYTIC_CARBON.getStackForm())
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


        // UHV+ Component Recipes
        // MOTORS -----------------------------------------------------------------------------
        OrePrefix roundOrScrew;
        if (GAConfig.GT6.addRounds)
            roundOrScrew = GAEnums.GAOrePrefix.round;
        else
            roundOrScrew = screw;

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10240)
                .outputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1))
                .inputs(OreDictUnifier.get(stickLong, HSSG, 2))
                .inputs(OreDictUnifier.get(ring, HSSG, 4))
                .inputs(OreDictUnifier.get(roundOrScrew, HSSG, 16))
                .inputs(OreDictUnifier.get(wireFine, AnnealedCopper, 64))
                .inputs(OreDictUnifier.get(wireFine, AnnealedCopper, 64))
                .inputs(OreDictUnifier.get(wireFine, AnnealedCopper, 64))
                .inputs(OreDictUnifier.get(wireFine, AnnealedCopper, 64))
                .inputs(OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(40960)
                .outputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 16))
                .inputs(OreDictUnifier.get(stickLong, HSSE, 2))
                .inputs(OreDictUnifier.get(ring, HSSE, 4))
                .inputs(OreDictUnifier.get(roundOrScrew, HSSE, 16))
                .inputs(OreDictUnifier.get(wireFine, Platinum, 64))
                .inputs(OreDictUnifier.get(wireFine, Platinum, 64))
                .inputs(OreDictUnifier.get(wireFine, Platinum, 64))
                .inputs(OreDictUnifier.get(wireFine, Platinum, 64))
                .inputs(OreDictUnifier.get(cableGtQuadruple, Naquadah, 2))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(163840)
                .outputs(ELECTRIC_MOTOR_UV.getStackForm())
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64))
                .inputs(OreDictUnifier.get(stickLong, Tritanium, 2))
                .inputs(OreDictUnifier.get(ring, Tritanium, 4))
                .inputs(OreDictUnifier.get(roundOrScrew, Tritanium, 16))
                .inputs(OreDictUnifier.get(wireFine, Duranium, 64))
                .inputs(OreDictUnifier.get(wireFine, Duranium, 64))
                .inputs(OreDictUnifier.get(wireFine, Duranium, 64))
                .inputs(OreDictUnifier.get(wireFine, Duranium, 64))
                .inputs(OreDictUnifier.get(cableGtQuadruple, Duranium, 2))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(655360)
                .outputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64))
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64))
                .inputs(OreDictUnifier.get(stickLong, HDCS, 2))
                .inputs(OreDictUnifier.get(ring, HDCS, 4))
                .inputs(OreDictUnifier.get(roundOrScrew, HDCS, 16))
                .inputs(OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64))
                .inputs(OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64))
                .inputs(OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64))
                .inputs(OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64))
                .input(cableGtQuadruple, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(2592))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2621440)
                .outputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64))
                .inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64))
                .inputs(OreDictUnifier.get(stickLong, HDCS, 2))
                .inputs(OreDictUnifier.get(ring, HDCS, 4))
                .inputs(OreDictUnifier.get(roundOrScrew, HDCS, 16))
                .inputs(OreDictUnifier.get(wireFine, Pikyonium, 64))
                .inputs(OreDictUnifier.get(wireFine, Pikyonium, 64))
                .inputs(OreDictUnifier.get(wireFine, Pikyonium, 64))
                .inputs(OreDictUnifier.get(wireFine, Pikyonium, 64))
                .input(cableGtQuadruple, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(2592))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();
        // PUMPS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(ELECTRIC_PUMP_LUV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(pipeSmall, Ultimet, 2))
                .inputs(OreDictUnifier.get(screw, HSSG, 8))
                .inputs(OreDictUnifier.get(ring, SiliconeRubber, 16))
                .inputs(OreDictUnifier.get(rotor, HSSG, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(pipeMedium, Ultimet, 2))
                .inputs(OreDictUnifier.get(screw, HSSE, 8))
                .inputs(OreDictUnifier.get(ring, SiliconeRubber, 16))
                .inputs(OreDictUnifier.get(rotor, HSSE, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Naquadah, 2))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(ELECTRIC_PUMP_UV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .inputs(OreDictUnifier.get(pipeLarge, Ultimet, 2))
                .inputs(OreDictUnifier.get(screw, Tritanium, 8))
                .inputs(OreDictUnifier.get(ring, SiliconeRubber, 16))
                .inputs(OreDictUnifier.get(rotor, Tritanium, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Duranium, 2))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .inputs(OreDictUnifier.get(pipeLarge, Zeron100, 32))
                .inputs(OreDictUnifier.get(screw, HDCS, 8))
                .inputs(OreDictUnifier.get(ring, SiliconeRubber, 16))
                .inputs(OreDictUnifier.get(rotor, HDCS, 2))
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .inputs(OreDictUnifier.get(pipeLarge, Lafium, 64))
                .inputs(OreDictUnifier.get(pipeLarge, Lafium, 64))
                .inputs(OreDictUnifier.get(screw, HDCS, 8))
                .inputs(OreDictUnifier.get(ring, SiliconeRubber, 16))
                .inputs(OreDictUnifier.get(rotor, HDCS, 2))
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        // CONVEYORS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(CONVEYOR_MODULE_LUV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm(2))
                .inputs(OreDictUnifier.get(plate, HSSG, 8))
                .inputs(OreDictUnifier.get(gear, HSSG, 4))
                .inputs(OreDictUnifier.get(stick, HSSG, 4))
                .inputs(OreDictUnifier.get(ingot, HSSG, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2))
                .fluidInputs(StyreneButadieneRubber.getFluid(1440))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(CONVEYOR_MODULE_ZPM.getStackForm())
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .inputs(OreDictUnifier.get(plate, HSSE, 8))
                .inputs(OreDictUnifier.get(gear, HSSE, 4))
                .inputs(OreDictUnifier.get(stick, HSSE, 4))
                .inputs(OreDictUnifier.get(ingot, HSSE, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Naquadah, 2))
                .fluidInputs(StyreneButadieneRubber.getFluid(2880))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(CONVEYOR_MODULE_UV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .inputs(OreDictUnifier.get(plate, Tritanium, 8))
                .inputs(OreDictUnifier.get(gear, Tritanium, 4))
                .inputs(OreDictUnifier.get(stick, Tritanium, 4))
                .inputs(OreDictUnifier.get(ingot, Tritanium, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Duranium, 2))
                .fluidInputs(StyreneButadieneRubber.getFluid(2880))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(CONVEYOR_MODULE_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(OreDictUnifier.get(plate, HDCS, 8))
                .inputs(OreDictUnifier.get(gear, HDCS, 4))
                .inputs(OreDictUnifier.get(stick, HDCS, 4))
                .inputs(OreDictUnifier.get(ingot, HDCS, 2))
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(2880))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(CONVEYOR_MODULE_UEV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(OreDictUnifier.get(plate, HDCS, 8))
                .inputs(OreDictUnifier.get(gear, HDCS, 4))
                .inputs(OreDictUnifier.get(stick, HDCS, 4))
                .inputs(OreDictUnifier.get(ingot, HDCS, 2))
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(2880))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();
        // PISTONS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(ELECTRIC_PISTON_LUV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(plate, HSSG, 8))
                .inputs(OreDictUnifier.get(gearSmall, HSSG, 8))
                .inputs(OreDictUnifier.get(stick, HSSG, 4))
                .inputs(OreDictUnifier.get(ingot, HSSG, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(plate, HSSE, 8))
                .inputs(OreDictUnifier.get(gearSmall, HSSE, 8))
                .inputs(OreDictUnifier.get(stick, HSSE, 4))
                .inputs(OreDictUnifier.get(ingot, HSSE, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Naquadah, 2))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(ELECTRIC_PISTON_UV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .inputs(OreDictUnifier.get(plate, Tritanium, 8))
                .inputs(OreDictUnifier.get(gearSmall, Tritanium, 8))
                .inputs(OreDictUnifier.get(stick, Tritanium, 4))
                .inputs(OreDictUnifier.get(ingot, Tritanium, 2))
                .inputs(OreDictUnifier.get(cableGtSingle, Duranium, 2))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(ELECTRIC_PISTON_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .inputs(OreDictUnifier.get(plate, HDCS, 8))
                .inputs(OreDictUnifier.get(gearSmall, HDCS, 8))
                .inputs(OreDictUnifier.get(stick, HDCS, 4))
                .inputs(OreDictUnifier.get(ingot, HDCS, 2))
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(ELECTRIC_PISTON_UEV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .inputs(OreDictUnifier.get(plate, HDCS, 8))
                .inputs(OreDictUnifier.get(gearSmall, HDCS, 8))
                .inputs(OreDictUnifier.get(stick, HDCS, 4))
                .inputs(OreDictUnifier.get(ingot, HDCS, 2))
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        // ROBOT ARMS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(20480)
                .outputs(ROBOT_ARM_LUV.getStackForm())
                .inputs(OreDictUnifier.get(cableGtDouble, YttriumBariumCuprate, 16))
                .inputs(OreDictUnifier.get(screw, HSSG, 16))
                .inputs(OreDictUnifier.get(stick, HSSG, 16))
                .inputs(OreDictUnifier.get(ingot, HSSG))
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_LUV.getStackForm())
                .input(circuit, Tier.Extreme, 8)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(81920)
                .outputs(ROBOT_ARM_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(cableGtDouble, Naquadah, 16))
                .inputs(OreDictUnifier.get(screw, HSSE, 16))
                .inputs(OreDictUnifier.get(stick, HSSE, 16))
                .inputs(OreDictUnifier.get(ingot, HSSE))
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .inputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .input(circuit, Tier.Elite, 8)
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(327680)
                .outputs(ROBOT_ARM_UV.getStackForm())
                .inputs(OreDictUnifier.get(cableGtDouble, Duranium, 16))
                .inputs(OreDictUnifier.get(screw, Tritanium, 16))
                .inputs(OreDictUnifier.get(stick, Tritanium, 16))
                .inputs(OreDictUnifier.get(ingot, Tritanium))
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UV.getStackForm())
                .input(circuit, Tier.Master, 8)
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1310720)
                .outputs(ROBOT_ARM_UHV.getStackForm())
                .inputs(OreDictUnifier.get(cableGtDouble, TungstenTitaniumCarbide, 16))
                .inputs(OreDictUnifier.get(screw, HDCS, 16))
                .inputs(OreDictUnifier.get(stick, HDCS, 16))
                .inputs(OreDictUnifier.get(ingot, HDCS))
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UHV.getStackForm())
                .input(circuit, Tier.Ultimate, 8)
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(5242880)
                .outputs(ROBOT_ARM_UEV.getStackForm())
                .inputs(OreDictUnifier.get(cableGtDouble, Pikyonium, 16))
                .inputs(OreDictUnifier.get(screw, HDCS, 16))
                .inputs(OreDictUnifier.get(stick, HDCS, 16))
                .inputs(OreDictUnifier.get(ingot, HDCS))
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UEV.getStackForm())
                .input(circuit, Tier.Superconductor, 8)
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        // EMITTERS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(EMITTER_LUV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSG, 1))
                .inputs(ZincSelenide.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Electrum, 64))
                .inputs(OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Ruby, 2))
                .input(circuit, MarkerMaterials.Tier.Extreme, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(EMITTER_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSE, 1))
                .inputs(Fluorescein.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Platinum, 64))
                .inputs(OreDictUnifier.get(wireGtDouble, Naquadah, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Emerald, 2))
                .input(circuit, Tier.Elite, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(EMITTER_UV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, Tritanium, 1))
                .inputs(Stilbene.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 32))
                .inputs(OreDictUnifier.get(wireGtDouble, Duranium, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .input(circuit, Tier.Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(EMITTER_UHV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HDCS, 1))
                .inputs(FranciumCaesiumCadmiumBromide.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .input(circuit, Tier.Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(EMITTER_UEV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HDCS, 1))
                .inputs(RhodamineB.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .input(cableGtSingle, Pikyonium, 8)
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .input(circuit, Tier.Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .buildAndRegister();
        // SENSORS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(SENSOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSG, 1))
                .inputs(OreDictUnifier.get(dust, Germanium, 16))
                .inputs(OreDictUnifier.get(foil, Electrum, 64))
                .inputs(OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Ruby, 2))
                .input(circuit, MarkerMaterials.Tier.Extreme, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(SENSOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSE, 1))
                .inputs(LeadSenenide.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Platinum, 64))
                .inputs(OreDictUnifier.get(wireGtDouble, Naquadah, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Emerald, 2))
                .input(circuit, Tier.Elite, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(SENSOR_UV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, Tritanium, 1))
                .inputs(BariumStrontiumTitanate.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 32))
                .inputs(OreDictUnifier.get(wireGtDouble, Duranium, 8))
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .input(circuit, Tier.Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(SENSOR_UHV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HDCS, 1))
                .inputs(LeadScandiumTantalate.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(circuit, Tier.Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(SENSOR_UEV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HDCS, 1))
                .inputs(MagnetorestrictiveAlloy.getItemStack(16))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .inputs(OreDictUnifier.get(foil, Osmiridium, 64))
                .input(cableGtSingle, Pikyonium, 8)
                .inputs(OreDictUnifier.get(gemExquisite, Diamond, 2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, Tier.Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .buildAndRegister();

        // FIELD GENERATORS -----------------------------------------------------------------------------
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(30720)
                .outputs(FIELD_GENERATOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSG, 1))
                .inputs(QUANTUM_STAR.getStackForm())
                .inputs(OreDictUnifier.get(wireFine, Osmium, 16))
                .inputs(OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4))
                .input(circuit, Tier.Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .outputs(FIELD_GENERATOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, HSSE, 1))
                .inputs(QUANTUM_STAR.getStackForm())
                .inputs(OreDictUnifier.get(wireFine, Osmium, 16))
                .inputs(OreDictUnifier.get(cableGtOctal, Naquadah, 4))
                .input(circuit, Tier.Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(491520)
                .outputs(FIELD_GENERATOR_UV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, Tritanium, 1))
                .inputs(GRAVI_STAR.getStackForm())
                .inputs(OreDictUnifier.get(wireFine, Osmium, 64))
                .inputs(OreDictUnifier.get(cableGtOctal, Duranium, 4))
                .input(circuit, Tier.Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1966080)
                .outputs(FIELD_GENERATOR_UHV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, Seaborgium, 1))
                .inputs(GRAVI_STAR.getStackForm())
                .inputs(OreDictUnifier.get(wireFine, Osmium, 64))
                .input(cableGtSingle, TungstenTitaniumCarbide, 4)
                .input(circuit, Tier.Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .outputs(FIELD_GENERATOR_UEV.getStackForm())
                .inputs(OreDictUnifier.get(frameGt, Bohrium, 1))
                .inputs(GRAVI_STAR.getStackForm())
                .inputs(OreDictUnifier.get(wireFine, Osmium, 64))
                .inputs(OreDictUnifier.get(wireFine, Osmium, 64))
                .input(cableGtSingle, Pikyonium, 4)
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(1152))
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


        // Fusion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING),
                "PhP", "PHP", "PwP",
                'P', "plateTungstenSteel",
                'H', MetaBlocks.MACHINE_CASING.getItemVariant(LuV));

        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2),
                "PhP", "PHP", "PwP",
                'P', "plateRutherfordium",
                'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING));

        ModHandler.addShapedRecipe("fusion_casing_3", GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_3),
                "PhP", "PHP", "PwP",
                'P', "plateDubnium",
                'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING))
                .input(plate, Rutherfordium, 6)
                .outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2))
                .input(plate, Dubnium, 6)
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_3))
                .buildAndRegister();

        // Fusion Coil Recipes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(2))
                .inputs(FIELD_GENERATOR_LUV.getStackForm())
                .inputs(OreDictUnifier.get(cableGtQuadruple, LuVSuperconductor, 4))
                .inputs(OreDictUnifier.get(plate, Osmiridium, 2))
                .input(circuit, Tier.Master, 1)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(122880).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(4))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .inputs(OreDictUnifier.get(cableGtQuadruple, ZPMSuperconductor, 4))
                .inputs(OreDictUnifier.get(plate, Rutherfordium, 2))
                .input(circuit, Tier.Ultimate, 1)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_2))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(491520).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(6))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm(2))
                .inputs(OreDictUnifier.get(cableGtQuadruple, UVSuperconductor, 4))
                .inputs(OreDictUnifier.get(plate, Tritanium, 2))
                .input(circuit, Tier.Superconductor, 1)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_3))
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

        // Dynamite
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.PAPER))
                .inputs(new ItemStack(Items.STRING))
                .fluidInputs(Glyceryl.getFluid(500))
                .outputs(DYNAMITE.getStackForm())
                .buildAndRegister();

        //Lapotron Crystal Recipes
        for (MaterialStack m : lapisLike) {
            GemMaterial gem = (GemMaterial) m.material;
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(),
                    LAPOTRON_CRYSTAL.getStackForm(),
                    OreDictUnifier.get(gemExquisite, Sapphire),
                    OreDictUnifier.get(stick, gem),
                    CAPACITOR.getStackForm());
        }

        //Add Missing Superconducter Wire Tiering Recipes
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


        ModHandler.addShapelessRecipe("superonducter_wire_gtsingle_doubling", OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_doubling", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_doubling", OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_doubling", OreDictUnifier.get(wireGtHex, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_splitting", OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 2), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_splitting", OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 2), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_splitting", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 2), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gthex_splitting", OreDictUnifier.get(wireGtOctal, Tier.Superconductor, 2), OreDictUnifier.get(wireGtHex, Tier.Superconductor));

        //Schematic Recipe
        ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4)
                .input(circuit, Tier.Good, 4)
                .input(plate, StainlessSteel, 2)
                .outputs(SCHEMATIC.getStackForm())
                .buildAndRegister();

        //MAX Machine Hull
        ModHandler.addShapedRecipe("ga_casing_max", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(plate, Neutronium));

        ModHandler.addShapedRecipe("ga_hull_max", MetaTileEntities.HULL[GTValues.MAX].getStackForm(),
                "PHP", "CMC",
                'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX),
                'C', new UnificationEntry(wireGtSingle, MarkerMaterials.Tier.Superconductor),
                'H', new UnificationEntry(plate, Neutronium),
                'P', new UnificationEntry(plate, Polytetrafluoroethylene));

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).duration(50)
                .input(plate, Neutronium, 8)
                .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX))
                .circuitMeta(8)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX))
                .input(wireGtSingle, MarkerMaterials.Tier.Superconductor, 2)
                .fluidInputs(Polytetrafluoroethylene.getFluid(288))
                .outputs(MetaTileEntities.HULL[9].getStackForm())
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

        ModHandler.addShapedRecipe("3x3_schematic", SCHEMATIC_3X3.getStackForm(), "  d", " S ", "   ", 'S', SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("2x2_schematic", SCHEMATIC_2X2.getStackForm(), " d ", " S ", "   ", 'S', SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("dust_schematic", SCHEMATIC_DUST.getStackForm(), "   ", " S ", "  d", 'S', SCHEMATIC.getStackForm());

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(wireGtSingle, MVSuperconductorBase, 3)
                .inputs(OreDictUnifier.get(pipeTiny, StainlessSteel, 2))
                .inputs(ELECTRIC_PUMP_MV.getStackForm(2))
                .fluidInputs(Nitrogen.getFluid(2000))
                .outputs(OreDictUnifier.get(wireGtSingle, MVSuperconductor, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256)
                .input(wireGtSingle, HVSuperconductorBase, 3)
                .inputs(OreDictUnifier.get(pipeTiny, Titanium, 2))
                .inputs(ELECTRIC_PUMP_HV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(2000))
                .outputs(OreDictUnifier.get(wireGtSingle, HVSuperconductor, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                .input(wireGtSingle, EVSuperconductorBase, 9)
                .inputs(OreDictUnifier.get(pipeTiny, TungstenSteel, 6))
                .inputs(ELECTRIC_PUMP_EV.getStackForm(2))
                .fluidInputs(Nitrogen.getFluid(6000))
                .outputs(OreDictUnifier.get(wireGtSingle, EVSuperconductor, 9))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .input(wireGtSingle, IVSuperconductorBase, 6)
                .inputs(OreDictUnifier.get(pipeTiny, NiobiumTitanium, 4))
                .inputs(ELECTRIC_PUMP_IV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(4000))
                .outputs(OreDictUnifier.get(wireGtSingle, IVSuperconductor, 6))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680)
                .input(wireGtSingle, LuVSuperconductorBase, 8)
                .inputs(OreDictUnifier.get(pipeTiny, Enderium, 5))
                .inputs(ELECTRIC_PUMP_LUV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(6000))
                .outputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720)
                .input(wireGtSingle, ZPMSuperconductorBase, 16)
                .inputs(OreDictUnifier.get(pipeTiny, Naquadah, 6))
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .fluidInputs(Nitrogen.getFluid(8000))
                .outputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductor, 16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880)
                .input(wireGtSingle, UVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Ultimet, 7))
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .fluidInputs(Nitrogen.getFluid(10000))
                .outputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(491520)
                .input(wireGtSingle, UHVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Zeron100, 7))
                .inputs(ELECTRIC_PUMP_UV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(12000))
                .outputs(OreDictUnifier.get(wireGtSingle, UHVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(1966080)
                .input(wireGtSingle, UEVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Lafium, 7))
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(14000))
                .outputs(OreDictUnifier.get(wireGtSingle, UEVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(7864320)
                .input(wireGtSingle, UIVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7))
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(16000))
                .outputs(OreDictUnifier.get(wireGtSingle, UIVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(31457280)
                .input(wireGtSingle, UMVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7))
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(18000))
                .outputs(OreDictUnifier.get(wireGtSingle, UMVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(125829120)
                .input(wireGtSingle, UXVSuperconductorBase, 32)
                .inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7))
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(20000))
                .outputs(OreDictUnifier.get(wireGtSingle, UXVSuperconductor, 32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(20).EUt(503316480)
                .input(wireGtSingle, UXVSuperconductorBase, 64)
                .inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7))
                .inputs(ELECTRIC_PUMP_UMV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(22000))
                .outputs(OreDictUnifier.get(wireGtSingle, MarkerMaterials.Tier.Superconductor, 64))
                .buildAndRegister();

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


        // COILS

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .inputs(OreDictUnifier.get(dust, AluminoSilicateWool, 12))
                .fluidInputs(Tin.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tin.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30)
                .input(wireGtDouble, Kanthal, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Copper.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120)
                .input(wireGtDouble, Nichrome, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Aluminium.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                .input(wireGtDouble, TungstenSteel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Nichrome.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TUNGSTENSTEEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(wireGtDouble, HSSG, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tungsten.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096)
                .input(wireGtDouble, Naquadah, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(HSSG.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680)
                .input(wireGtDouble, NaquadahAlloy, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Naquadah.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH_ALLOY))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(500000)
                .input(wireGtDouble, TungstenTitaniumCarbide, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tritanium.getFluid(144))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000000)
                .input(wireGtDouble, Pikyonium, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Adamantium.getFluid(144))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_2))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8000000)
                .input(wireGtDouble, Cinobite, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Vibranium.getFluid(144))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(32000000)
                .input(wireGtDouble, Neutronium, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Neutronium.getFluid(144))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_4))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, LuVSuperconductor, 64)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(64))
                .fluidInputs(NaquadahAlloy.getFluid(144 * 8))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, ZPMSuperconductor, 32)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(32))
                .fluidInputs(NaquadahAlloy.getFluid(144 * 4))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, UVSuperconductor, 16)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(NaquadahAlloy.getFluid(144 * 2))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, MarkerMaterials.Tier.Superconductor, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(NaquadahAlloy.getFluid(144))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                .buildAndRegister();


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


        for (EmitterCasing.CasingType emitter : EmitterCasing.CasingType.values()) {
            ItemStack emitterStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.EMITTER.getIngredient(emitter.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(emitter.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(emitter.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, emitter.getTier() - 1))).duration(200)
                    .inputs(emitterStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.EMITTER_CASING.getItemVariant(emitter))
                    .buildAndRegister();
        }
        for (MotorCasing.CasingType motor : MotorCasing.CasingType.values()) {
            ItemStack motorStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.MOTOR.getIngredient(motor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(motor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(motor.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, motor.getTier() - 1))).duration(200)
                    .inputs(motorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.MOTOR_CASING.getItemVariant(motor))
                    .buildAndRegister();
        }
        for (PistonCasing.CasingType piston : PistonCasing.CasingType.values()) {
            ItemStack pistonStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.PISTON.getIngredient(piston.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(piston.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(piston.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, piston.getTier() - 1))).duration(200)
                    .inputs(pistonStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.PISTON_CASING.getItemVariant(piston))
                    .buildAndRegister();
        }
        for (SensorCasing.CasingType sensor : SensorCasing.CasingType.values()) {
            ItemStack sensorStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.SENSOR.getIngredient(sensor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(sensor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(sensor.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, sensor.getTier() - 1))).duration(200)
                    .inputs(sensorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.SENSOR_CASING.getItemVariant(sensor))
                    .buildAndRegister();
        }
        for (FieldGenCasing.CasingType fieldgen : FieldGenCasing.CasingType.values()) {
            ItemStack fieldgenStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.FIELD_GENERATOR.getIngredient(fieldgen.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(fieldgen.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(fieldgen.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, fieldgen.getTier() - 1))).duration(200)
                    .inputs(fieldgenStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.FIELD_GEN_CASING.getItemVariant(fieldgen))
                    .buildAndRegister();
        }
        for (PumpCasing.CasingType pump : PumpCasing.CasingType.values()) {
            ItemStack pumpStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.PUMP.getIngredient(pump.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(pump.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(pump.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, pump.getTier() - 1))).duration(200)
                    .inputs(pumpStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.PUMP_CASING.getItemVariant(pump))
                    .buildAndRegister();
        }
        for (ConveyorCasing.CasingType conveyor : ConveyorCasing.CasingType.values()) {
            ItemStack conveyorStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.CONVEYOR.getIngredient(conveyor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(conveyor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(conveyor.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, conveyor.getTier() - 1))).duration(200)
                    .inputs(conveyorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.CONVEYOR_CASING.getItemVariant(conveyor))
                    .buildAndRegister();
        }
        for (RobotArmCasing.CasingType robotarm : RobotArmCasing.CasingType.values()) {
            ItemStack robotarmStack = ((MetaItem<?>.MetaValueItem) GACraftingComponents.ROBOT_ARM.getIngredient(robotarm.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(robotarm.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(robotarm.getTier());

            ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, robotarm.getTier() - 1))).duration(200)
                    .inputs(robotarmStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(GAMetaBlocks.ROBOT_ARM_CASING.getItemVariant(robotarm))
                    .buildAndRegister();
        }

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

        // Drilling Rig

        ASSEMBLER_RECIPES.recipeBuilder().duration(230).EUt(480)
                .input(pipeLarge, Steel)
                .input(ring, Steel)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(WELL_PIPE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(340).EUt(480)
                .inputs(ELECTRIC_MOTOR_EV.getStackForm())
                .inputs(ELECTRIC_PUMP_EV.getStackForm())
                .input(stickLong, Steel, 6)
                .input(plate, Steel, 2)
                .input(toolHeadDrill, Steel)
                .outputs(RIG_DRILL.getStackForm())
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

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, Tier.Infinite, 2)
                .inputs(OreDictUnifier.get(gear, AbyssalAlloy, 8))
                .inputs(OreDictUnifier.get(plate, AbyssalAlloy, 8))
                .inputs(OreDictUnifier.get(cableGtSingle, TungstenTitaniumCarbide, 16))
                .inputs(GATileEntities.GA_HULLS[0].getStackForm())
                .fluidInputs(Naquadria.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UHV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, UEV, 2)
                .inputs(OreDictUnifier.get(gear, TitanSteel, 8))
                .inputs(OreDictUnifier.get(plate, TitanSteel, 8))
                .inputs(OreDictUnifier.get(cableGtSingle, Pikyonium, 16))
                .inputs(GATileEntities.GA_HULLS[1].getStackForm())
                .fluidInputs(Naquadria.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UEV, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, UIV, 2)
                .inputs(OreDictUnifier.get(gear, BlackTitanium, 8))
                .inputs(OreDictUnifier.get(plate, BlackTitanium, 8))
                .inputs(OreDictUnifier.get(cableGtSingle, Cinobite, 16))
                .inputs(GATileEntities.GA_HULLS[2].getStackForm())
                .fluidInputs(Naquadria.getFluid(1440))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UIV, 1))
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

        // Prospectors
        ModHandler.addShapedRecipe("ga_prospect_tool_mv", PROSPECT_TOOL_MV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_MV.getStackForm(),
                'D', new UnificationEntry(toolHeadDrill, Aluminium),
                'S', SENSOR_MV.getStackForm(),
                'C', new UnificationEntry(circuit, Tier.Good),
                'T', COVER_MACHINE_CONTROLLER.getStackForm(),
                'P', new UnificationEntry(plate, Aluminium),
                'B', BATTERY_RE_MV_SODIUM.getStackForm());

        ModHandler.addShapedRecipe("ga_prospect_tool_hv", PROSPECT_TOOL_HV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_HV.getStackForm(),
                'D', new UnificationEntry(toolHeadDrill, StainlessSteel),
                'S', SENSOR_HV.getStackForm(),
                'C', new UnificationEntry(circuit, Tier.Advanced),
                'T', COVER_MACHINE_CONTROLLER.getStackForm(),
                'P', new UnificationEntry(plate, StainlessSteel),
                'B', BATTERY_RE_HV_SODIUM.getStackForm());

        ModHandler.addShapedRecipe("ga_prospect_tool_luv", PROSPECT_TOOL_LuV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_LUV.getStackForm(),
                'D', new UnificationEntry(toolHeadDrill, RhodiumPlatedPalladium),
                'S', SENSOR_LUV.getStackForm(),
                'C', new UnificationEntry(circuit, Tier.Master),
                'T', COVER_MACHINE_CONTROLLER.getStackForm(),
                'P', new UnificationEntry(plate, RhodiumPlatedPalladium),
                'B', ENERGY_LAPOTRONIC_ORB2.getStackForm());

        ModHandler.addShapedRecipe("ga_prospect_tool_zpm", PROSPECT_TOOL_ZPM.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_ZPM.getStackForm(),
                'D', new UnificationEntry(toolHeadDrill, HSSS),
                'S', SENSOR_ZPM.getStackForm(),
                'C', new UnificationEntry(circuit, Tier.Ultimate),
                'T', COVER_MACHINE_CONTROLLER.getStackForm(),
                'P', new UnificationEntry(plate, HSSS),
                'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE.getStackForm() : BATTERY_LARGE_LITHIUM_ION.getStackForm());

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

    public static void init3() {
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

    public static void forestrySupport() {
        //Making BioDiesel
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Fluids.BIO_ETHANOL.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        } else {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Ethanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        }


        if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_APATITE.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.APATITE, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Apatite, 2)
                    .input(bolt, Apatite)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Apatite, 4)
                    .input(bolt, Apatite, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BLAZE, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Blaze, 2)
                    .input(dustSmall, Blaze, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Blaze, 5)
                    .input(dust, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(4))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BRONZE, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Bronze, 2).input(bolt, Bronze)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Bronze, 4).input(bolt, Bronze, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_COPPER.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.COPPER, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Copper, 2)
                    .input(bolt, Copper).input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Copper, 4)
                    .input(bolt, Copper, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(2)).
                    buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.DIAMOND, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Diamond, 2)
                    .input(bolt, Diamond)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Diamond, 4)
                    .input(bolt, Diamond, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.EMERALD, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Emerald, 2)
                    .input(bolt, Emerald)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Emerald, 4)
                    .input(bolt, Emerald, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_ENDER.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ENDER, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Endstone, 2)
                    .input(dustSmall, Endstone, 2)
                    .input(dust, EnderEye)
                    .outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Endstone, 5)
                    .input(dust, EnderEye, 2)
                    .outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(4))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_GOLD.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.GOLD, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Gold, 2)
                    .input(bolt, Gold)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Gold, 4)
                    .input(bolt, Gold, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(2))
                    .buildAndRegister();

            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_IRON.getStackForm())
                        .inputs(OreDictUnifier.get(plate, Glass))
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.IRON, 1))
                        .buildAndRegister();

                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                        .input(stick, Iron, 2)
                        .input(bolt, Iron)
                        .input(dustSmall, Redstone, 2)
                        .outputs(GAMetaItems.ELECTRODE_IRON.getStackForm())
                        .buildAndRegister();

                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                        .input(stick, Iron, 4).input(bolt, Iron, 2)
                        .input(dust, Redstone)
                        .outputs(GAMetaItems.ELECTRODE_IRON.getStackForm(2))
                        .buildAndRegister();

            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.LAPIS, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Lapis, 2)
                    .input(bolt, Lapis)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Lapis, 4).input(bolt, Lapis, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.OBSIDIAN, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Obsidian, 2)
                    .input(dustSmall, Obsidian, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(2))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Obsidian, 5)
                    .input(dust, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(4))
                    .buildAndRegister();

            if (Loader.isModLoaded("extrautils2")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm())
                        .inputs(OreDictUnifier.get(plate, Glass))
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ORCHID, 1))
                        .buildAndRegister();

                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                        .inputs(new ItemStack(Blocks.REDSTONE_ORE, 5))
                        .inputs(OreDictUnifier.get(dust, Redstone))
                        .outputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(4))
                        .buildAndRegister();
            }
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm())
                        .inputs(OreDictUnifier.get(plate, Glass))
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.RUBBER, 1))
                        .buildAndRegister();

                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                        .input(stick, Rubber, 2)
                        .input(bolt, Rubber)
                        .input(dustSmall, Redstone, 2)
                        .outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm())
                        .buildAndRegister();

                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                        .input(stick, Rubber, 4)
                        .input(bolt, Rubber, 2)
                        .input(dust, Redstone)
                        .outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(2))
                        .buildAndRegister();
            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_TIN.getStackForm())
                    .inputs(OreDictUnifier.get(plate, Glass))
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.TIN, 1))
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Tin, 2)
                    .input(bolt, Tin)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_TIN.getStackForm())
                    .buildAndRegister();

            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Tin, 4)
                    .input(bolt, Tin, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_TIN.getStackForm(2))
                    .buildAndRegister();
        }
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
