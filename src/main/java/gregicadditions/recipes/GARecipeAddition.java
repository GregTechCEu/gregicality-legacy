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
import gregicadditions.recipes.chain.wetware.*;
import gregicadditions.recipes.chain.optical.*;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
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
import gregtech.api.util.ValidationResult;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
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
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeAllRecipes;
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMachineCasing.MachineCasingType.LuV;
import static gregtech.common.items.MetaItems.*;

public class GARecipeAddition {

    private static final MaterialStack[] sawLubricants = {
            new MaterialStack(Lubricant, 1L),
            new MaterialStack(DistilledWater, 3L),
            new MaterialStack(Water, 4L)
    };

    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Rubber, 144),
            new MaterialStack(StyreneButadieneRubber, 108),
            new MaterialStack(SiliconeRubber, 72)
    };

    private static final MaterialStack[] cableDusts = {
            new MaterialStack(Polydimethylsiloxane, 1),
            new MaterialStack(PolyvinylChloride, 1)
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

    private static final MaterialStack[] ironOres = {
            new MaterialStack(Pyrite, 1),
            new MaterialStack(BrownLimonite, 1),
            new MaterialStack(YellowLimonite, 1),
            new MaterialStack(Magnetite, 1),
            new MaterialStack(Iron, 1)
    };

    private static final MaterialStack[] lapisLike = {
            new MaterialStack(Lapis, 1),
            new MaterialStack(Lazurite, 1),
            new MaterialStack(Sodalite, 1)
    };


    public static void init() {


        OreDictUnifier.registerOre(new ItemStack(Items.SNOWBALL), dust, Snow);
        OreDictUnifier.registerOre(new ItemStack(Blocks.SNOW), block, Snow);

        ModHandler.addSmeltingRecipe(new UnificationEntry(ingot, Iron), HOT_IRON_INGOT.getStackForm());
        ModHandler.addShapelessRecipe("ga_wrought", OreDictUnifier.get(ingot, WroughtIron), 'h', HOT_IRON_INGOT.getStackForm());
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(16).inputs(HOT_IRON_INGOT.getStackForm()).outputs(OreDictUnifier.get(ingot, WroughtIron)).buildAndRegister();


        //seed oil
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).input("listAllSeed", 1).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllmushroom", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllfruit", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllveggie", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllspice", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllgrain", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllnut", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllpepper", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllherb", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("listAllfiber", 8).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(BrownAlgae.getItemStack(8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(RedAlgae.getItemStack(8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GreenAlgae.getItemStack(8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();


        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().fluidInputs(Glass.getFluid(144)).notConsumable(SHAPE_MOLD_BALL.getStackForm()).outputs(GLASS_TUBE.getStackForm()).EUt(16).duration(80).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.GLOWSTONE_DUST, 4)).outputs(new ItemStack(Blocks.GLOWSTONE)).EUt(16).duration(40).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(24).inputs(LARGE_FLUID_CELL_STEEL.getStackForm(), ELECTRIC_MOTOR_LV.getStackForm()).input(stick, Steel).input(ring, Rubber, 2).fluidInputs(SolderingAlloy.getFluid(1440)).outputs(HAND_PUMP.getStackForm()).buildAndRegister();

        //GTNH Bricks
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.removeFurnaceSmelting(COMPRESSED_CLAY.getStackForm());
        ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2).inputs(new ItemStack(Items.CLAY_BALL)).notConsumable(SHAPE_MOLD_INGOT).outputs(new ItemStack(Items.BRICK)).buildAndRegister();
        OreDictionary.registerOre("formWood", WOODEN_FORM_BRICK.getStackForm());
        ModHandler.addShapelessRecipe("clay_brick", COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.CLAY_BALL), "formWood");
        ModHandler.addShapedRecipe("eight_clay_brick", COMPRESSED_CLAY.getStackForm(8), "BBB", "BFB", "BBB", 'B', new ItemStack(Items.CLAY_BALL), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_brick", COMPRESSED_COKE_CLAY.getStackForm(3), "BBB", "SFS", "SSS", 'B', new ItemStack(Items.CLAY_BALL), 'S', new ItemStack(Blocks.SAND), 'F', "formWood");
        ModHandler.addSmeltingRecipe(COMPRESSED_COKE_CLAY.getStackForm(), COKE_OVEN_BRICK.getStackForm());

        //GT5U Old Primitive Brick Processing

//        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());
//        COMPRESSOR_RECIPES.recipeBuilder().input(dust, Fireclay).outputs(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm()).duration(100).EUt(2).buildAndRegister();
        ModHandler.addShapedRecipe("quartz_sand", OreDictUnifier.get(dust, GAMaterials.QuartzSand), "S", "m", 'S', "sand");
        MACERATOR_RECIPES.recipeBuilder().duration(200).EUt(8).input("sand", 1).outputs(OreDictUnifier.get(dust, GAMaterials.QuartzSand)).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2500, 500).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2000, 500).buildAndRegister();
        ModHandler.addShapelessRecipe("glass_dust_ga", OreDictUnifier.get(dust, Glass), "dustSand", "dustFlint");
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(8).input(dust, Flint).input(dust, GAMaterials.QuartzSand, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(160).EUt(8).input(dust, Flint).input(dust, Quartzite, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(16).input(dust, Calcite, 2).input(dust, Stone).input(dust, Clay).input(dust, GAMaterials.QuartzSand).fluidInputs(Water.getFluid(2000)).fluidOutputs(Concrete.getFluid(2304)).buildAndRegister();


        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), RUBBER_DROP.getStackForm());
        FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 4, 15)).duration(16).EUt(10).buildAndRegister();



        //GT6 Bending
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET), "ChC", " P ", 'C', "plateCurvedIron", 'P', "plateIron");
            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(valueOf("plateCurved"), Iron, 2).input(plate, Iron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(valueOf("plateCurved"), WroughtIron, 2).input(plate, WroughtIron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET), "PPP", "ChC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE), "PhP", "CPC", "CPC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS), "PCP", "ChC", "C C", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS), "P P", "ChC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET), "PPP", "ChC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE), "PhP", "CPC", "CPC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS), "PCP", "ChC", "C C", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS), "P P", "ChC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.addShapedRecipe("chain_helmet", new ItemStack(Items.CHAINMAIL_HELMET), "RRR", "RhR", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_chestplate", new ItemStack(Items.CHAINMAIL_CHESTPLATE), "RhR", "RRR", "RRR", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_leggings", new ItemStack(Items.CHAINMAIL_LEGGINGS), "RRR", "RhR", "R R", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_boots", new ItemStack(Items.CHAINMAIL_BOOTS), "R R", "RhR", 'R', "ringIron");
        }

        //wood pipe
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipes(OreDictUnifier.get(pipeSmall, Wood, 4));
            ModHandler.removeRecipes(OreDictUnifier.get(pipeMedium, Wood, 2));
            ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(pipeMedium, Wood, 2), "PPP", "sCh", "PPP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
            ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood), "PhP", "PCP", "PsP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
            ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(pipeSmall, Wood, 4), "PsP", "PCP", "PhP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
        }

        //Reinforced Glass
        int multiplier2;
        for (MaterialStack metal1 : firstMetal) {
            IngotMaterial material1 = (IngotMaterial) metal1.material;
            int multiplier1 = (int) metal1.amount;
            for (MaterialStack metal2 : lastMetal) {
                IngotMaterial material2 = (IngotMaterial) metal2.material;
                if ((int) metal1.amount == 1) multiplier2 = 0;
                else multiplier2 = (int) metal2.amount;
                ModHandler.addShapedRecipe("mixed_metal_1_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(plate, material1), 'M', "plateBronze", 'L', OreDictUnifier.get(plate, material2));
                ModHandler.addShapedRecipe("mixed_metal_2_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(plate, material1), 'M', "plateBrass", 'L', OreDictUnifier.get(plate, material2));

                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(plate, material1).input(plank, Bronze).input(plate, material2).outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2)).buildAndRegister();
                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(plate, material1).input(plate, Brass).input(plate, material2).outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2)).buildAndRegister();
            }
        }

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(ADVANCED_ALLOY_PLATE.getStackForm()).input(dust, Glass, 3).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(ADVANCED_ALLOY_PLATE.getStackForm(), new ItemStack(Blocks.GLASS, 3)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 1)).fluidInputs(BorosilicateGlass.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS, 1)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(64).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS, 1)).fluidInputs(Nickel.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS, 1)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(256).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS, 1)).fluidInputs(Chrome.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS, 1)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(1024).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS, 1)).fluidInputs(Tungsten.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS, 1)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(4096).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS, 1)).fluidInputs(Iridium.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS, 1)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16384).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS, 1)).fluidInputs(Osmiridium.getFluid(GTValues.L)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS, 1)).buildAndRegister();


        //Machine Components


        ModHandler.addShapedRecipe("ga_lv_emitter", EMITTER_LV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Brass), 'S', "circuitBasic", 'C', OreDictUnifier.get(cableGtSingle, Tin), 'G', OreDictUnifier.get(gem, Quartzite));
        ModHandler.addShapedRecipe("ga_mv_emitter", EMITTER_MV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Electrum), 'S', "circuitGood", 'C', OreDictUnifier.get(cableGtSingle, Copper), 'G', OreDictUnifier.get(gem, NetherQuartz));
        ModHandler.addShapedRecipe("ga_hv_emitter", EMITTER_HV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Chrome), 'S', "circuitAdvanced", 'C', OreDictUnifier.get(cableGtSingle, Gold), 'G', OreDictUnifier.get(gem, Emerald));
        ModHandler.addShapedRecipe("ga_ev_emitter", EMITTER_EV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Platinum), 'S', "circuitExtreme", 'C', OreDictUnifier.get(cableGtSingle, Aluminium), 'G', OreDictUnifier.get(gem, EnderPearl));
        ModHandler.addShapedRecipe("ga_iv_emitter", EMITTER_IV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Osmium), 'S', "circuitElite", 'C', OreDictUnifier.get(cableGtSingle, Tungsten), 'G', OreDictUnifier.get(gem, EnderEye));

        ModHandler.addShapedRecipe("ga_lv_sensor", SENSOR_LV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateSteel", 'G', "gemQuartzite", 'R', "stickBrass", 'S', "circuitBasic");
        ModHandler.addShapedRecipe("ga_mv_sensor", SENSOR_MV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateAluminium", 'G', "gemNetherQuartz", 'R', "stickElectrum", 'S', "circuitGood");
        ModHandler.addShapedRecipe("ga_hv_sensor", SENSOR_HV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateStainlessSteel", 'G', "gemEmerald", 'R', "stickChrome", 'S', "circuitAdvanced");
        ModHandler.addShapedRecipe("ga_ev_sensor", SENSOR_EV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTitanium", 'G', "gemEnderPearl", 'R', "stickPlatinum", 'S', "circuitExtreme");
        ModHandler.addShapedRecipe("ga_iv_sensor", SENSOR_IV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTungstenSteel", 'G', "gemEnderEye", 'R', "stickOsmium", 'S', "circuitElite");

        ModHandler.addShapedRecipe("ga_lv_robot_arm", ROBOT_ARM_LV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Tin), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'R', OreDictUnifier.get(stick, Steel), 'P', ELECTRIC_PISTON_LV.getStackForm(), 'S', "circuitBasic");
        ModHandler.addShapedRecipe("ga_mv_robot_arm", ROBOT_ARM_MV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Copper), 'M', ELECTRIC_MOTOR_MV.getStackForm(), 'R', OreDictUnifier.get(stick, Aluminium), 'P', ELECTRIC_PISTON_MV.getStackForm(), 'S', "circuitGood");
        ModHandler.addShapedRecipe("ga_hv_robot_arm", ROBOT_ARM_HV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Gold), 'M', ELECTRIC_MOTOR_HV.getStackForm(), 'R', OreDictUnifier.get(stick, StainlessSteel), 'P', ELECTRIC_PISTON_HV.getStackForm(), 'S', "circuitAdvanced");
        ModHandler.addShapedRecipe("ga_ev_robot_arm", ROBOT_ARM_EV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Aluminium), 'M', ELECTRIC_MOTOR_EV.getStackForm(), 'R', OreDictUnifier.get(stick, Titanium), 'P', ELECTRIC_PISTON_EV.getStackForm(), 'S', "circuitExtreme");
        ModHandler.addShapedRecipe("ga_iv_robot_arm", ROBOT_ARM_IV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Tungsten), 'M', ELECTRIC_MOTOR_IV.getStackForm(), 'R', OreDictUnifier.get(stick, TungstenSteel), 'P', ELECTRIC_PISTON_IV.getStackForm(), 'S', "circuitElite");

        ModHandler.addShapedRecipe("ga_lv_field_generator", FIELD_GENERATOR_LV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtSingle, Osmium), 'S', "circuitBasic", 'G', OreDictUnifier.get(gem, EnderPearl));
        ModHandler.addShapedRecipe("ga_mv_field_generator", FIELD_GENERATOR_MV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtDouble, Osmium), 'S', "circuitGood", 'G', OreDictUnifier.get(gem, EnderEye));
        ModHandler.addShapedRecipe("ga_hv_field_generator", FIELD_GENERATOR_HV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtQuadruple, Osmium), 'S', "circuitAdvanced", 'G', QUANTUM_EYE.getStackForm());
        ModHandler.addShapedRecipe("ga_ev_field_generator", FIELD_GENERATOR_EV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtOctal, Osmium), 'S', "circuitExtreme", 'G', OreDictUnifier.get(gem, NetherStar));
        ModHandler.addShapedRecipe("ga_v_field_generator", FIELD_GENERATOR_IV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtHex, Osmium), 'S', "circuitElite", 'G', QUANTUM_STAR.getStackForm());

        ModHandler.addShapedRecipe("lv_electric_pump_paper", ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, Paper), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            ModHandler.addShapedRecipe("lv_electric_pump_" + m.toString(), ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
            ModHandler.addShapedRecipe("mv_electric_pump_" + m.toString(), ELECTRIC_PUMP_MV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Bronze), 'R', OreDictUnifier.get(rotor, Bronze), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Steel), 'M', ELECTRIC_MOTOR_MV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Copper));
            ModHandler.addShapedRecipe("hv_electric_pump_" + m.toString(), ELECTRIC_PUMP_HV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Steel), 'R', OreDictUnifier.get(rotor, Steel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, StainlessSteel), 'M', ELECTRIC_MOTOR_HV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Gold));
            ModHandler.addShapedRecipe("ev_electric_pump_" + m.toString(), ELECTRIC_PUMP_EV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, StainlessSteel), 'R', OreDictUnifier.get(rotor, StainlessSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Titanium), 'M', ELECTRIC_MOTOR_EV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Aluminium));
            ModHandler.addShapedRecipe("iv_electric_pump_" + m.toString(), ELECTRIC_PUMP_IV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, TungstenSteel), 'R', OreDictUnifier.get(rotor, TungstenSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, TungstenSteel), 'M', ELECTRIC_MOTOR_IV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tungsten));
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).input(circuit, Tier.Good).inputs(MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.NAND_MEMORY_CHIP.getStackForm(32), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4)).input(OrePrefix.wireFine, Materials.RedAlloy, 8).input(OrePrefix.plate, Materials.Plastic, 4).fluidInputs(SolderingAlloy.getFluid(144)).outputs(MetaItems.TOOL_DATA_STICK.getStackForm()).buildAndRegister();

        if (GAConfig.Misc.assemblerCanMakeComponents) {
            //Automatic Machine Component Recipes
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(cableGtSingle, Tin), CountableIngredient.from(OrePrefix.getPrefix("round"), Steel, 8), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(72)).outputs(ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(CountableIngredient.from(cableGtSingle, Copper), CountableIngredient.from(OrePrefix.getPrefix("round"), Aluminium, 8), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(144)).outputs(ELECTRIC_MOTOR_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(CountableIngredient.from(cableGtSingle, Gold), CountableIngredient.from(OrePrefix.getPrefix("round"), StainlessSteel, 8), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(288)).outputs(ELECTRIC_MOTOR_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(CountableIngredient.from(cableGtSingle, Aluminium), CountableIngredient.from(OrePrefix.getPrefix("round"), Titanium, 8), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(576)).outputs(ELECTRIC_MOTOR_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(CountableIngredient.from(cableGtSingle, Tungsten), CountableIngredient.from(OrePrefix.getPrefix("round"), TungstenSteel, 8), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(1152)).outputs(ELECTRIC_MOTOR_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(cableGtSingle, Tin), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Steel, 2), CountableIngredient.from(stickLong, Steel), CountableIngredient.from(gearSmall, Steel), CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PISTON_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(CountableIngredient.from(cableGtSingle, Copper), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Aluminium, 2), CountableIngredient.from(stickLong, Aluminium), CountableIngredient.from(gearSmall, Aluminium), CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(144)).outputs(ELECTRIC_PISTON_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(CountableIngredient.from(cableGtSingle, Gold), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), StainlessSteel, 2), CountableIngredient.from(stickLong, StainlessSteel), CountableIngredient.from(gearSmall, StainlessSteel), CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PISTON_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(CountableIngredient.from(cableGtSingle, Aluminium), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Titanium, 2), CountableIngredient.from(stickLong, Titanium), CountableIngredient.from(gearSmall, Titanium), CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(576)).outputs(ELECTRIC_PISTON_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(CountableIngredient.from(cableGtSingle, Tungsten), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), TungstenSteel, 2), CountableIngredient.from(stickLong, TungstenSteel), CountableIngredient.from(gearSmall, TungstenSteel), CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ELECTRIC_PISTON_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(circuit, Tier.Basic), CountableIngredient.from(screw, Steel, 3), CountableIngredient.from(cableGtSingle, Tin, 2), CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm()), CountableIngredient.from(ELECTRIC_PISTON_LV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ROBOT_ARM_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(CountableIngredient.from(circuit, Tier.Good), CountableIngredient.from(screw, Aluminium, 3), CountableIngredient.from(cableGtSingle, Copper, 2), CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm()), CountableIngredient.from(ELECTRIC_PISTON_MV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(144)).outputs(ROBOT_ARM_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(CountableIngredient.from(circuit, Tier.Advanced), CountableIngredient.from(screw, StainlessSteel, 3), CountableIngredient.from(cableGtSingle, Gold, 2), CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm()), CountableIngredient.from(ELECTRIC_PISTON_HV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(244)).outputs(ROBOT_ARM_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(CountableIngredient.from(circuit, Tier.Extreme), CountableIngredient.from(screw, Titanium, 3), CountableIngredient.from(cableGtSingle, Aluminium, 2), CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm()), CountableIngredient.from(ELECTRIC_PISTON_EV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(576)).outputs(ROBOT_ARM_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(CountableIngredient.from(circuit, Tier.Elite), CountableIngredient.from(screw, TungstenSteel, 3), CountableIngredient.from(cableGtSingle, Tungsten, 2), CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm()), CountableIngredient.from(ELECTRIC_PISTON_IV.getStackForm())).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ROBOT_ARM_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(dust, EnderPearl)).fluidInputs(Osmium.getFluid(72)).outputs(FIELD_GENERATOR_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(dust, EnderEye)).fluidInputs(Osmium.getFluid(144)).outputs(FIELD_GENERATOR_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(QUANTUM_EYE.getStackForm())).fluidInputs(Osmium.getFluid(288)).outputs(FIELD_GENERATOR_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(dust, NetherStar)).fluidInputs(Osmium.getFluid(576)).outputs(FIELD_GENERATOR_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(QUANTUM_STAR.getStackForm())).fluidInputs(Osmium.getFluid(1152)).outputs(FIELD_GENERATOR_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(OreDictUnifier.get(cableGtSingle, Tin), ELECTRIC_MOTOR_LV.getStackForm(1)).fluidInputs(Rubber.getFluid(432)).outputs(CONVEYOR_MODULE_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(OreDictUnifier.get(cableGtSingle, Copper), ELECTRIC_MOTOR_MV.getStackForm(1)).fluidInputs(Rubber.getFluid(432)).outputs(CONVEYOR_MODULE_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(OreDictUnifier.get(cableGtSingle, Gold), ELECTRIC_MOTOR_HV.getStackForm(1)).fluidInputs(Rubber.getFluid(432)).outputs(CONVEYOR_MODULE_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(OreDictUnifier.get(cableGtSingle, Aluminium), ELECTRIC_MOTOR_EV.getStackForm(1)).fluidInputs(Rubber.getFluid(432)).outputs(CONVEYOR_MODULE_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(OreDictUnifier.get(cableGtSingle, Tungsten), ELECTRIC_MOTOR_IV.getStackForm(1)).fluidInputs(Rubber.getFluid(432)).outputs(CONVEYOR_MODULE_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(circuit, Tier.Basic).inputs(OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(foil, Steel, 8), OreDictUnifier.get(gem, Quartzite)).fluidInputs(Brass.getFluid(72)).outputs(SENSOR_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).input(circuit, Tier.Good).inputs(OreDictUnifier.get(cableGtSingle, Copper), OreDictUnifier.get(foil, Aluminium, 8), OreDictUnifier.get(gem, NetherQuartz)).fluidInputs(Electrum.getFluid(72)).outputs(SENSOR_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).input(circuit, Tier.Advanced).inputs(OreDictUnifier.get(cableGtSingle, Gold), OreDictUnifier.get(foil, StainlessSteel, 8), OreDictUnifier.get(gem, Emerald)).fluidInputs(Chrome.getFluid(72)).outputs(SENSOR_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).input(circuit, Tier.Extreme).inputs(OreDictUnifier.get(cableGtSingle, Aluminium), OreDictUnifier.get(foil, Titanium, 8), OreDictUnifier.get(dust, EnderPearl)).fluidInputs(Platinum.getFluid(72)).outputs(SENSOR_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).input(circuit, Tier.Elite).inputs(OreDictUnifier.get(cableGtSingle, Tungsten), OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(dust, EnderEye)).fluidInputs(Osmium.getFluid(72)).outputs(SENSOR_IV.getStackForm()).buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(circuit, Tier.Basic).inputs(OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(gemFlawless, Quartzite)).fluidInputs(Brass.getFluid(144)).outputs(EMITTER_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).input(circuit, Tier.Good).inputs(OreDictUnifier.get(cableGtSingle, Copper), OreDictUnifier.get(gemFlawless, NetherQuartz)).fluidInputs(Electrum.getFluid(144)).outputs(EMITTER_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).input(circuit, Tier.Advanced).inputs(OreDictUnifier.get(cableGtSingle, Gold), OreDictUnifier.get(gemFlawless, Emerald)).fluidInputs(Chrome.getFluid(144)).outputs(EMITTER_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).input(circuit, Tier.Extreme).inputs(OreDictUnifier.get(cableGtSingle, Aluminium), OreDictUnifier.get(gem, EnderPearl)).fluidInputs(Platinum.getFluid(144)).outputs(EMITTER_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).input(circuit, Tier.Elite).inputs(OreDictUnifier.get(cableGtSingle, Tungsten), OreDictUnifier.get(gem, EnderEye)).fluidInputs(Osmium.getFluid(144)).outputs(EMITTER_IV.getStackForm()).buildAndRegister();

            for (MaterialStack stackFluid : cableFluids) {
                IngotMaterial m = (IngotMaterial) stackFluid.material;
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(ELECTRIC_MOTOR_LV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(pipeMedium, Bronze), OreDictUnifier.get(screw, Tin)).inputs(CountableIngredient.from(rotor, Tin), CountableIngredient.from(ring, m)).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(125).inputs(ELECTRIC_MOTOR_MV.getStackForm(), OreDictUnifier.get(cableGtSingle, Copper), OreDictUnifier.get(pipeMedium, Steel), OreDictUnifier.get(screw, Bronze)).inputs(CountableIngredient.from(rotor, Bronze), CountableIngredient.from(ring, m)).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PUMP_MV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(500).inputs(ELECTRIC_MOTOR_HV.getStackForm(), OreDictUnifier.get(cableGtSingle, Gold), OreDictUnifier.get(pipeMedium, StainlessSteel), OreDictUnifier.get(screw, Steel)).inputs(CountableIngredient.from(rotor, Steel), CountableIngredient.from(ring, m)).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PUMP_HV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2000).inputs(ELECTRIC_MOTOR_EV.getStackForm(), OreDictUnifier.get(cableGtSingle, Aluminium), OreDictUnifier.get(pipeMedium, Titanium), OreDictUnifier.get(screw, StainlessSteel)).inputs(CountableIngredient.from(rotor, StainlessSteel), CountableIngredient.from(ring, m)).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PUMP_EV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(8000).inputs(ELECTRIC_MOTOR_IV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tungsten), OreDictUnifier.get(pipeMedium, TungstenSteel), OreDictUnifier.get(screw, TungstenSteel)).inputs(CountableIngredient.from(rotor, TungstenSteel), CountableIngredient.from(ring, m)).fluidInputs(SolderingAlloy.getFluid(72)).outputs(ELECTRIC_PUMP_IV.getStackForm()).buildAndRegister();
            }
        }

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            //Automatic Machine Component Recipes with assembly line

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(CountableIngredient.from(cableGtSingle, Tin, 4), CountableIngredient.from(cableGtSingle, Tin, 4), CountableIngredient.from(cableGtSingle, Tin, 4), CountableIngredient.from(cableGtSingle, Tin, 4), CountableIngredient.from(stickLong, Steel, 4), CountableIngredient.from(OrePrefix.getPrefix("round"), Steel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Steel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Steel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Steel, 16), CountableIngredient.from(stickLong, SteelMagnetic, 4)).fluidInputs(Copper.getFluid(72 * 4)).outputs(ELECTRIC_MOTOR_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(CountableIngredient.from(cableGtSingle, Copper, 4), CountableIngredient.from(cableGtSingle, Copper, 4), CountableIngredient.from(cableGtSingle, Copper, 4), CountableIngredient.from(cableGtSingle, Copper, 4), CountableIngredient.from(stickLong, Aluminium, 4), CountableIngredient.from(OrePrefix.getPrefix("round"), Aluminium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Aluminium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Aluminium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Aluminium, 16), CountableIngredient.from(stickLong, SteelMagnetic, 4)).fluidInputs(Copper.getFluid(144 * 4)).outputs(ELECTRIC_MOTOR_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(CountableIngredient.from(cableGtSingle, Gold, 4), CountableIngredient.from(cableGtSingle, Gold, 4), CountableIngredient.from(cableGtSingle, Gold, 4), CountableIngredient.from(cableGtSingle, Gold, 4), CountableIngredient.from(stickLong, StainlessSteel, 4), CountableIngredient.from(OrePrefix.getPrefix("round"), StainlessSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), StainlessSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), StainlessSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), StainlessSteel, 16), CountableIngredient.from(stickLong, SteelMagnetic, 4)).fluidInputs(Copper.getFluid(288 * 4)).outputs(ELECTRIC_MOTOR_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(CountableIngredient.from(cableGtSingle, Aluminium, 4), CountableIngredient.from(cableGtSingle, Aluminium, 4), CountableIngredient.from(cableGtSingle, Aluminium, 4), CountableIngredient.from(cableGtSingle, Aluminium, 4), CountableIngredient.from(stickLong, Titanium, 4), CountableIngredient.from(OrePrefix.getPrefix("round"), Titanium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Titanium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Titanium, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), Titanium, 16), CountableIngredient.from(stickLong, NeodymiumMagnetic, 4)).fluidInputs(AnnealedCopper.getFluid(576 * 4)).outputs(ELECTRIC_MOTOR_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(CountableIngredient.from(cableGtSingle, Tungsten, 4), CountableIngredient.from(cableGtSingle, Tungsten, 4), CountableIngredient.from(cableGtSingle, Tungsten, 4), CountableIngredient.from(cableGtSingle, Tungsten, 4), CountableIngredient.from(stickLong, TungstenSteel, 4), CountableIngredient.from(OrePrefix.getPrefix("round"), TungstenSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), TungstenSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), TungstenSteel, 16), CountableIngredient.from(OrePrefix.getPrefix("round"), TungstenSteel, 16), CountableIngredient.from(stickLong, NeodymiumMagnetic, 4)).fluidInputs(AnnealedCopper.getFluid(1152 * 4)).outputs(ELECTRIC_MOTOR_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(CountableIngredient.from(cableGtSingle, Tin, 8), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Steel, 2), CountableIngredient.from(plate, Steel, 2), CountableIngredient.from(ingot, Steel, 2), CountableIngredient.from(stickLong, Steel, 2), CountableIngredient.from(stickLong, Steel, 2), CountableIngredient.from(gearSmall, Steel, 4), CountableIngredient.from(gearSmall, Steel, 4), CountableIngredient.from(gearSmall, Steel, 4), CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PISTON_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(CountableIngredient.from(cableGtSingle, Copper, 8), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Aluminium, 2), CountableIngredient.from(plate, Aluminium, 2), CountableIngredient.from(ingot, Aluminium, 2), CountableIngredient.from(stickLong, Aluminium, 2), CountableIngredient.from(stickLong, Aluminium, 2), CountableIngredient.from(gearSmall, Aluminium, 4), CountableIngredient.from(gearSmall, Aluminium, 4), CountableIngredient.from(gearSmall, Aluminium, 4), CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(144 * 4)).outputs(ELECTRIC_PISTON_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(CountableIngredient.from(cableGtSingle, Gold, 8), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), StainlessSteel, 2), CountableIngredient.from(plate, StainlessSteel, 2), CountableIngredient.from(ingot, StainlessSteel, 2), CountableIngredient.from(stickLong, StainlessSteel, 2), CountableIngredient.from(stickLong, StainlessSteel, 2), CountableIngredient.from(gearSmall, StainlessSteel, 4), CountableIngredient.from(gearSmall, StainlessSteel, 4), CountableIngredient.from(gearSmall, StainlessSteel, 4), CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(288 * 4)).outputs(ELECTRIC_PISTON_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(CountableIngredient.from(cableGtSingle, Aluminium, 8), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), Titanium, 2), CountableIngredient.from(plate, Titanium, 2), CountableIngredient.from(ingot, Titanium, 2), CountableIngredient.from(stickLong, Titanium, 2), CountableIngredient.from(stickLong, Titanium, 2), CountableIngredient.from(gearSmall, Titanium, 4), CountableIngredient.from(gearSmall, Titanium, 4), CountableIngredient.from(gearSmall, Titanium, 4), CountableIngredient.from(gearSmall, Titanium, 4), CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(576 * 4)).outputs(ELECTRIC_PISTON_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(CountableIngredient.from(cableGtSingle, Tungsten, 8), CountableIngredient.from(OrePrefix.getPrefix("plateCurved"), TungstenSteel, 2), CountableIngredient.from(plate, TungstenSteel, 2), CountableIngredient.from(ingot, TungstenSteel, 2), CountableIngredient.from(stickLong, TungstenSteel, 2), CountableIngredient.from(stickLong, TungstenSteel, 2), CountableIngredient.from(gearSmall, TungstenSteel, 4), CountableIngredient.from(gearSmall, TungstenSteel, 4), CountableIngredient.from(gearSmall, TungstenSteel, 4), CountableIngredient.from(gearSmall, TungstenSteel, 4), CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(1152 * 4)).outputs(ELECTRIC_PISTON_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(screw, Steel, 8), CountableIngredient.from(screw, Steel, 8), CountableIngredient.from(cableGtSingle, Tin, 16), CountableIngredient.from(ELECTRIC_MOTOR_LV.getStackForm(4)), CountableIngredient.from(ELECTRIC_PISTON_LV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ROBOT_ARM_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(screw, Aluminium, 8), CountableIngredient.from(screw, Aluminium, 8), CountableIngredient.from(cableGtSingle, Copper, 16), CountableIngredient.from(ELECTRIC_MOTOR_MV.getStackForm(4)), CountableIngredient.from(ELECTRIC_PISTON_MV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(144 * 4)).outputs(ROBOT_ARM_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(screw, StainlessSteel, 8), CountableIngredient.from(screw, StainlessSteel, 8), CountableIngredient.from(cableGtSingle, Gold, 16), CountableIngredient.from(ELECTRIC_MOTOR_HV.getStackForm(4)), CountableIngredient.from(ELECTRIC_PISTON_HV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(244 * 4)).outputs(ROBOT_ARM_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(screw, Titanium, 8), CountableIngredient.from(screw, Titanium, 8), CountableIngredient.from(cableGtSingle, Aluminium, 16), CountableIngredient.from(ELECTRIC_MOTOR_EV.getStackForm(4)), CountableIngredient.from(ELECTRIC_PISTON_EV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(576 * 4)).outputs(ROBOT_ARM_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(screw, TungstenSteel, 8), CountableIngredient.from(screw, TungstenSteel, 8), CountableIngredient.from(cableGtSingle, Tungsten, 16), CountableIngredient.from(ELECTRIC_MOTOR_IV.getStackForm(4)), CountableIngredient.from(ELECTRIC_PISTON_IV.getStackForm(4))).fluidInputs(SolderingAlloy.getFluid(1152 * 4)).outputs(ROBOT_ARM_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), ELECTRIC_MOTOR_LV.getStackForm(4)).fluidInputs(Rubber.getFluid(432 * 4)).outputs(CONVEYOR_MODULE_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), ELECTRIC_MOTOR_MV.getStackForm(4)).fluidInputs(Rubber.getFluid(432 * 4)).outputs(CONVEYOR_MODULE_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), ELECTRIC_MOTOR_HV.getStackForm(4)).fluidInputs(Rubber.getFluid(432 * 4)).outputs(CONVEYOR_MODULE_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), ELECTRIC_MOTOR_EV.getStackForm(4)).fluidInputs(Rubber.getFluid(432 * 4)).outputs(CONVEYOR_MODULE_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), ELECTRIC_MOTOR_IV.getStackForm(4)).fluidInputs(Rubber.getFluid(432 * 4)).outputs(CONVEYOR_MODULE_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(circuit, Tier.Basic, 2), CountableIngredient.from(dust, EnderPearl), CountableIngredient.from(dust, EnderPearl), CountableIngredient.from(dust, EnderPearl), CountableIngredient.from(dust, EnderPearl)).fluidInputs(Osmium.getFluid(72 * 4)).outputs(FIELD_GENERATOR_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(circuit, Tier.Good, 2), CountableIngredient.from(dust, EnderEye), CountableIngredient.from(dust, EnderEye), CountableIngredient.from(dust, EnderEye), CountableIngredient.from(dust, EnderEye)).fluidInputs(Osmium.getFluid(144 * 4)).outputs(FIELD_GENERATOR_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(circuit, Tier.Advanced, 2), CountableIngredient.from(QUANTUM_EYE.getStackForm()), CountableIngredient.from(QUANTUM_EYE.getStackForm()), CountableIngredient.from(QUANTUM_EYE.getStackForm()), CountableIngredient.from(QUANTUM_EYE.getStackForm())).fluidInputs(Osmium.getFluid(288 * 4)).outputs(FIELD_GENERATOR_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(circuit, Tier.Extreme, 2), CountableIngredient.from(dust, NetherStar), CountableIngredient.from(dust, NetherStar), CountableIngredient.from(dust, NetherStar), CountableIngredient.from(dust, NetherStar)).fluidInputs(Osmium.getFluid(576 * 4)).outputs(FIELD_GENERATOR_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(circuit, Tier.Elite, 2), CountableIngredient.from(QUANTUM_STAR.getStackForm()), CountableIngredient.from(QUANTUM_STAR.getStackForm()), CountableIngredient.from(QUANTUM_STAR.getStackForm()), CountableIngredient.from(QUANTUM_STAR.getStackForm())).fluidInputs(Osmium.getFluid(1152 * 4)).outputs(FIELD_GENERATOR_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(8000).input(circuit, Tier.Basic, 4).inputs(OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(foil, Steel, 8), OreDictUnifier.get(foil, Steel, 8), OreDictUnifier.get(foil, Steel, 8), OreDictUnifier.get(foil, Steel, 8), OreDictUnifier.get(gemExquisite, Quartzite, 2)).fluidInputs(Brass.getFluid(72 * 4), SolderingAlloy.getFluid(72 * 4)).outputs(SENSOR_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(16000).input(circuit, Tier.Good, 4).inputs(OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(foil, Aluminium, 8), OreDictUnifier.get(foil, Aluminium, 8), OreDictUnifier.get(foil, Aluminium, 8), OreDictUnifier.get(foil, Aluminium, 8), OreDictUnifier.get(gemExquisite, NetherQuartz, 2)).fluidInputs(Electrum.getFluid(72 * 4), SolderingAlloy.getFluid(144 * 4)).outputs(SENSOR_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(32000).input(circuit, Tier.Advanced, 4).inputs(OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(foil, StainlessSteel, 8), OreDictUnifier.get(foil, StainlessSteel, 8), OreDictUnifier.get(foil, StainlessSteel, 8), OreDictUnifier.get(foil, StainlessSteel, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).fluidInputs(Chrome.getFluid(72 * 4), SolderingAlloy.getFluid(288 * 4)).outputs(SENSOR_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(64000).input(circuit, Tier.Extreme, 4).inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(foil, Titanium, 8), OreDictUnifier.get(foil, Titanium, 8), OreDictUnifier.get(foil, Titanium, 8), OreDictUnifier.get(foil, Titanium, 8), OreDictUnifier.get(gem, EnderPearl, 16)).fluidInputs(Platinum.getFluid(72 * 4), SolderingAlloy.getFluid(576 * 4)).outputs(SENSOR_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(128000).input(circuit, Tier.Elite, 4).inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(foil, TungstenSteel, 8), OreDictUnifier.get(gem, EnderEye, 16)).fluidInputs(Osmium.getFluid(72 * 4), SolderingAlloy.getFluid(1152 * 4)).outputs(SENSOR_IV.getStackForm(16)).buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).input(circuit, Tier.Basic, 4).inputs(OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(gem, Quartzite, 4)).fluidInputs(Brass.getFluid(72 * 4)).outputs(EMITTER_LV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).input(circuit, Tier.Good, 4).inputs(OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(gem, NetherQuartz, 4)).fluidInputs(Electrum.getFluid(72 * 4)).outputs(EMITTER_MV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).input(circuit, Tier.Advanced, 4).inputs(OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(gem, Emerald, 4)).fluidInputs(Chrome.getFluid(72 * 4)).outputs(EMITTER_HV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).input(circuit, Tier.Extreme, 4).inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(gem, EnderPearl, 4)).fluidInputs(Platinum.getFluid(72 * 4)).outputs(EMITTER_EV.getStackForm(16)).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).input(circuit, Tier.Elite, 4).inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(gem, EnderEye, 4)).fluidInputs(Osmium.getFluid(72 * 4)).outputs(EMITTER_IV.getStackForm(16)).buildAndRegister();

            for (MaterialStack stackFluid : cableFluids) {
                IngotMaterial m = (IngotMaterial) stackFluid.material;
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000).inputs(ELECTRIC_MOTOR_LV.getStackForm(4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(cableGtSingle, Tin, 4), OreDictUnifier.get(pipeSmall, Bronze, 16), OreDictUnifier.get(screw, Tin, 16)).inputs(CountableIngredient.from(rotor, Tin, 4), CountableIngredient.from(ring, m, 8)).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PUMP_LV.getStackForm(16)).buildAndRegister();
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000).inputs(ELECTRIC_MOTOR_MV.getStackForm(4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(cableGtSingle, Copper, 4), OreDictUnifier.get(pipeSmall, Steel, 16), OreDictUnifier.get(screw, Bronze, 16)).inputs(CountableIngredient.from(rotor, Bronze, 4), CountableIngredient.from(ring, m, 8)).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PUMP_MV.getStackForm(16)).buildAndRegister();
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000).inputs(ELECTRIC_MOTOR_HV.getStackForm(4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(cableGtSingle, Gold, 4), OreDictUnifier.get(pipeSmall, StainlessSteel, 16), OreDictUnifier.get(screw, Steel, 16)).inputs(CountableIngredient.from(rotor, Steel, 4), CountableIngredient.from(ring, m, 8)).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PUMP_HV.getStackForm(16)).buildAndRegister();
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000).inputs(ELECTRIC_MOTOR_EV.getStackForm(4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(cableGtSingle, Aluminium, 4), OreDictUnifier.get(pipeSmall, Titanium, 16), OreDictUnifier.get(screw, StainlessSteel, 16)).inputs(CountableIngredient.from(rotor, StainlessSteel, 4), CountableIngredient.from(ring, m, 8)).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PUMP_EV.getStackForm(16)).buildAndRegister();
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000).inputs(ELECTRIC_MOTOR_IV.getStackForm(4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(cableGtSingle, Tungsten, 4), OreDictUnifier.get(pipeSmall, TungstenSteel, 16), OreDictUnifier.get(screw, TungstenSteel, 16)).inputs(CountableIngredient.from(rotor, TungstenSteel, 4), CountableIngredient.from(ring, m, 8)).fluidInputs(SolderingAlloy.getFluid(72 * 4)).outputs(ELECTRIC_PUMP_IV.getStackForm(16)).buildAndRegister();
            }
        }


        //Pyrolise Oven Recipes

        PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(1).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(640).EUt(64).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(2).fluidInputs(Nitrogen.getFluid(400)).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(320).EUt(96).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().inputs(PLANT_BALL.getStackForm()).circuitMeta(2).fluidInputs(Water.getFluid(1500)).chancedOutput(PLANT_BALL.getStackForm(), 1000, 100).fluidOutputs(FermentedBiomass.getFluid(1500)).duration(200).EUt(10).buildAndRegister();


        //Distillation Recipes
        DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(FishOil.getFluid(24)).fluidOutputs(Lubricant.getFluid(12)).buildAndRegister();
        ValidationResult<Recipe> result = DISTILLATION_RECIPES.recipeBuilder().duration(7500).EUt(1920).fluidInputs(LiquidAir.getFluid(100000)).fluidOutputs(Nitrogen.getFluid(78000), Oxygen.getFluid(20000), Argon.getFluid(1000), CarbonDioxide.getFluid(500), Neon.getFluid(100), Helium.getFluid(50), Methane.getFluid(20), Krypton.getFluid(10), Hydrogen.getFluid(5), Xenon.getFluid(1)).build();
        DISTILLATION_RECIPES.addRecipe(result);

        //Fluid Heater Recipes

        //Fermenter Recipe
        FERMENTING_RECIPES.recipeBuilder().duration(150).EUt(2).fluidInputs(Biomass.getFluid(100)).fluidOutputs(FermentedBiomass.getFluid(100)).buildAndRegister();

        //Oil Extractor Recipes
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH)).fluidOutputs(FishOil.getFluid(40)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 1)).fluidOutputs(FishOil.getFluid(60)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 2)).fluidOutputs(FishOil.getFluid(70)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 3)).fluidOutputs(FishOil.getFluid(30)).buildAndRegister();

        //Misc Blast Furnace Recipes
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Pentlandite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Garnierite), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Pyrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, BandedIron), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Galena).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Massicot), OreDictUnifier.get(nugget, Lead, 6)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Stibnite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, AntimonyTrioxide), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Sphalerite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Zincite), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Cobaltite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CobaltOxide), OreDictUnifier.get(dust, ArsenicTrioxide)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Tetrahedrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CupricOxide), OreDictUnifier.get(dustTiny, AntimonyTrioxide, 3)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Chalcopyrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CupricOxide), OreDictUnifier.get(dust, Ferrosilite)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, AntimonyTrioxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Antimony, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(3000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CobaltOxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Cobalt, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, ArsenicTrioxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Arsenic, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CupricOxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Copper, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Garnierite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Nickel, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BandedIron, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, SiliconDioxide).input(dust, Carbon, 2).outputs(OreDictUnifier.get(ingot, Silicon), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(CarbonMonoxde.getFluid(2000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Malachite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Copper, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(3000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Magnetite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, GraniticMineralSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BrownLimonite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, YellowLimonite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BasalticMineralSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Cassiterite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Tin, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CassiteriteSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Tin, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, SiliconDioxide).input(dust, Carbon, 2).outputs(OreDictUnifier.get(ingot, Silicon), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(CarbonMonoxde.getFluid(2000)).buildAndRegister();

        for (MaterialStack ore : ironOres) {
            Material materials = ore.material;
            BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(dust, Calcite).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustSmall, DarkAsh)).buildAndRegister();
            BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(dustTiny, Quicklime, 3).outputs(OreDictUnifier.get(ingot, Iron, 2), OreDictUnifier.get(dustSmall, DarkAsh)).buildAndRegister();
        }


        //Mince Meat Recipes
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.PORKCHOP)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.BEEF)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.RABBIT)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.CHICKEN)).outputs(OreDictUnifier.get(dust, Meat)).buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.MUTTON)).outputs(OreDictUnifier.get(dust, Meat)).buildAndRegister();

        //Ash-Related Recipes
        CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6).input(dust, DarkAsh).outputs(OreDictUnifier.get(dust, Ash), OreDictUnifier.get(dust, Carbon)).buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30).input(dust, Ash).chancedOutput(OreDictUnifier.get(dustSmall, Quicklime, 2), 9900, 0).chancedOutput(OreDictUnifier.get(dustSmall, Potash), 6400, 0).chancedOutput(OreDictUnifier.get(dustSmall, Magnesia), 6000, 0).chancedOutput(OreDictUnifier.get(dustSmall, PhosphorousPentoxide), 500, 0).chancedOutput(OreDictUnifier.get(dustSmall, SodaAsh), 5000, 0).buildAndRegister();


        //GAMetaBlocks.MUTLIBLOCK_CASING recipe
        ModHandler.addShapedRecipe("assline_casing", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING, 2), "PhP", "AFA", "PwP", 'P', "plateSteel", 'A', ROBOT_ARM_IV.getStackForm(), 'F', OreDictUnifier.get(frameGt, TungstenSteel));
        ModHandler.addShapedRecipe("ga_assembler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING, 3), "CCC", "CFC", "CMC", 'C', "circuitElite", 'F', "frameGtTungstenSteel", 'M', ELECTRIC_MOTOR_IV.getStackForm());
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).input(valueOf("gtMetalCasing"), Steel, 1).fluidInputs(Polytetrafluoroethylene.getFluid(216)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16).input(circuit, Tier.Primitive, 2).inputs(OreDictUnifier.get(gear, Potin, 8), OreDictUnifier.get(plate, Potin, 8), OreDictUnifier.get(cableGtOctal, Tin), MetaTileEntities.HULL[GTValues.ULV].getStackForm()).fluidInputs(Steel.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ULV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(32).input(circuit, Tier.Basic, 2).inputs(OreDictUnifier.get(gear, Tumbaga, 8), OreDictUnifier.get(plate, Tumbaga, 8), OreDictUnifier.get(cableGtOctal, Cobalt), MetaTileEntities.HULL[GTValues.LV].getStackForm()).fluidInputs(Silicon.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(64).input(circuit, Tier.Good, 2).inputs(OreDictUnifier.get(gear, EglinSteel, 8), OreDictUnifier.get(plate, EglinSteel, 8), OreDictUnifier.get(cableGtOctal, AnnealedCopper), MetaTileEntities.HULL[GTValues.MV].getStackForm()).fluidInputs(BabbittAlloy.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_MV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(128).input(circuit, Tier.Advanced, 2).inputs(OreDictUnifier.get(gear, Inconel625, 8), OreDictUnifier.get(plate, Inconel625, 8), OreDictUnifier.get(cableGtOctal, Gold), MetaTileEntities.HULL[GTValues.HV].getStackForm()).fluidInputs(Inconel625.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_HV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(256).input(circuit, Tier.Extreme, 2).inputs(OreDictUnifier.get(gear, TungstenCarbide, 8), OreDictUnifier.get(plate, TungstenCarbide, 8), OreDictUnifier.get(cableGtOctal, Titanium), MetaTileEntities.HULL[GTValues.EV].getStackForm()).fluidInputs(Stellite.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_EV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(512).input(circuit, Tier.Elite, 2).inputs(OreDictUnifier.get(gear, Nitinol60, 8), OreDictUnifier.get(plate, Nitinol60, 8), OreDictUnifier.get(cableGtOctal, Nichrome), MetaTileEntities.HULL[GTValues.IV].getStackForm()).fluidInputs(Thorium.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_IV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(1024).input(circuit, Tier.Master, 2).inputs(OreDictUnifier.get(gear, IncoloyMA956, 8), OreDictUnifier.get(plate, IncoloyMA956, 8), OreDictUnifier.get(cableGtOctal, Platinum), MetaTileEntities.HULL[GTValues.LuV].getStackForm()).fluidInputs(Uranium235.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LUV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2048).input(circuit, Tier.Ultimate, 2).inputs(OreDictUnifier.get(gear, BabbittAlloy, 8), OreDictUnifier.get(plate, BabbittAlloy, 8), OreDictUnifier.get(cableGtOctal, NiobiumTitanium), MetaTileEntities.HULL[GTValues.ZPM].getStackForm()).fluidInputs(Plutonium241.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4096).input(circuit, Tier.Superconductor, 2).inputs(OreDictUnifier.get(gear, HG1223, 8), OreDictUnifier.get(plate, HG1223, 8), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate), MetaTileEntities.HULL[GTValues.UV].getStackForm()).fluidInputs(NaquadahEnriched.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_UV)).buildAndRegister();

        ModHandler.addShapelessRecipe("ga_potin_dust", OreDictUnifier.get(dust, Potin, 5), new UnificationEntry(dust, Lead), new UnificationEntry(dust, Lead), new UnificationEntry(dust, Bronze), new UnificationEntry(dust, Bronze), new UnificationEntry(dust, Tin));

        MIXER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .input(dust, UraniumRadioactive.getMaterial(), 9)
                .input(dust, Titanium, 1)
                .outputs(OreDictUnifier.get(dust, Staballoy, 10)).buildAndRegister();
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(12000).EUt(120)
                .notConsumable(new IntCircuitIngredient(5))
                .input(dust, Iron, 15)
                .input(dust, Niobium, 1)
                .input(dust, Vanadium, 4)
                .input(dust, Carbon, 2)
                .fluidInputs(Argon.getFluid(1000))
                .fluidOutputs(ReactorSteel.getFluid(GTValues.L * 22))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().duration(1500).EUt(500)
                .input(plateDense, Lead, 9)
                .input(plateDense, Lead, 9)
                .input(plateDense, ReactorSteel, 4)
                .input(plateDense, StainlessSteel, 2)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CLADDED_REACTOR_CASING, 4))
                .buildAndRegister();

        //Pyrotheum
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Coal)
                .input(dust, Sulfur)
                .fluidInputs(Lava.getFluid(100))
                .outputs(OreDictUnifier.get(dust, Blaze, 2)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Charcoal)
                .input(dust, Sulfur)
                .fluidInputs(Lava.getFluid(100))
                .outputs(OreDictUnifier.get(dust, Blaze, 2)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .input(dust, Blaze, 2)
                .outputs(OreDictUnifier.get(dust, Pyrotheum, 2)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Pyrotheum)
                .fluidOutputs(Pyrotheum.getFluid(250)).buildAndRegister();

        //Cryotheum
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Snow)
                .fluidInputs(Redstone.getFluid(144))
                .outputs(OreDictUnifier.get(dust, Blizz, 2)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Snow)
                .input(dust, Blizz, 2)
                .outputs(OreDictUnifier.get(dust, Cryotheum, 2)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Cryotheum)
                .fluidOutputs(Cryotheum.getFluid(250)).buildAndRegister();

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


        ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(HastelloyN.getFluid(144 * 4)).input(valueOf("gtMetalCasing"), Staballoy, 2).inputs(CountableIngredient.from(circuit, Tier.Extreme)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER, 2)).duration(600).EUt(8000).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(240).duration(1200).input(plateDense, Lead, 4).fluidInputs(Oxygen.getFluid(16000)).input(OrePrefix.valueOf("gtMetalCasing"), StainlessSteel).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_HV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(960).duration(2400).input(plateDense, Titanium, 4).fluidInputs(Nitrogen.getFluid(16000)).inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_HV)).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_EV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(3840).duration(4800).input(plateDense, TungstenSteel, 4).fluidInputs(Helium.getFluid(8000)).inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_EV)).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_IV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(15360).duration(9600).input(plateDense, Iridium, 4).fluidInputs(Argon.getFluid(4000)).inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_IV)).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_LUV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(61440).duration(18200).input(plateDense, NaquadahAlloy, 4).fluidInputs(Radon.getFluid(4000)).inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_LUV)).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(245760).duration(36400).input(plateDense, Tritanium, 4).fluidInputs(Xenon.getFluid(4000)).inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_ZPM)).outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CellCasing.CellType.CELL_UV)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Bronze, 6).input(frameGt, Bronze, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Invar, 6).input(frameGt, Invar, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Steel, 6).input(frameGt, Steel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Aluminium, 6).input(frameGt, Aluminium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, TungstenSteel, 6).input(frameGt, TungstenSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, StainlessSteel, 6).input(frameGt, StainlessSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Titanium, 6).input(frameGt, Titanium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE, 3)).duration(50).buildAndRegister();

        removeAllRecipes(BREWING_RECIPES);
        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Honey.getFluid(180)).fluidOutputs(Biomass.getFluid(270)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(600).EUt(3).input("treeSapling", 1).fluidInputs(Honey.getFluid(100)).fluidOutputs(Biomass.getFluid(150)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Honey.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Juice.getFluid(180)).fluidOutputs(Biomass.getFluid(270)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(600).EUt(3).input("treeSapling", 1).fluidInputs(Juice.getFluid(100)).fluidOutputs(Biomass.getFluid(150)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(240).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Juice.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();

        BREWING_RECIPES.recipeBuilder().duration(800).EUt(3).input("treeSapling", 1).fluidInputs(Water.getFluid(100)).fluidOutputs(Biomass.getFluid(100)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();


        // Armor
        ModHandler.addShapedRecipe("gtadditions:insulating_tape", INSULATING_TAPE.getStackForm(6), "RRR", "SSS", 'R', new ItemStack(Items.PAPER), 'S', MetaItems.RUBBER_DROP.getStackForm());
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100).input(circuit, Tier.Good, 6).inputs(MetaTileEntities.STEEL_TANK.getStackForm(), MetaItems.ELECTRIC_PUMP_MV.getStackForm(2), OreDictUnifier.get(pipeSmall, Plastic, 2), OreDictUnifier.get(pipeMedium, Steel, 2), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(SEMIFLUID_JETPACK.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100).input(circuit, Tier.Good, 6).inputs(MetaItems.BATTERY_RE_MV_CADMIUM.getStackForm(6), IMPELLER_MV.getStackForm(4), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(IMPELLER_JETPACK.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Copper), MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), OreDictUnifier.get(stick, Steel), OreDictUnifier.get(rotor, Plastic, 2), OreDictUnifier.get(pipeMedium, Plastic)).outputs(IMPELLER_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Gold), MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), OreDictUnifier.get(stick, StainlessSteel), OreDictUnifier.get(rotor, Plastic, 2), OreDictUnifier.get(pipeMedium, Plastic)).outputs(IMPELLER_HV.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("gtadditions:battery_pack.lv", BATPACK_LV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_LV_LITHIUM.getStackForm(), 'C', "circuitBasic", 'P', OreDictUnifier.get(plate, Steel));
        ModHandler.addShapedRecipe("gtadditions:battery_pack.mv", BATPACK_MV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_MV_LITHIUM.getStackForm(), 'C', "circuitGood", 'P', OreDictUnifier.get(plate, Aluminium));
        ModHandler.addShapedRecipe("gtadditions:battery_pack.hv", BATPACK_HV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm(), 'C', "circuitAdvanced", 'P', OreDictUnifier.get(plate, StainlessSteel));
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(400).input(circuit, Tier.Good, 4).input(circuit, Tier.Advanced, 1).inputs(BATPACK_HV.getStackForm(), IMPELLER_HV.getStackForm(6), MetaItems.BATTERY_RE_HV_CADMIUM.getStackForm(), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(ADVANCED_IMPELLER_JETPACK.getStackForm()).buildAndRegister();

        // Nightvision Goggles
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(128).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS), SENSOR_MV.getStackForm(2), EMITTER_MV.getStackForm(2), BATTERY_RE_MV_LITHIUM.getStackForm(), INSULATING_TAPE.getStackForm(2)).outputs(NIGHTVISION_GOGGLES.getStackForm()).notConsumable(new IntCircuitIngredient(3)).buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(7), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(0)).outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(6), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(1)).outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(4), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(2)).outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 2).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS), NIGHTVISION_GOGGLES.getStackForm(), MetaItems.CARBON_PLATE.getStackForm(5), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(3)).outputs(NANO_MUSCLE_SUITE_HELMET.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1500).EUt(1024).input("circuitExtreme", 2).inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm(), ADVANCED_IMPELLER_JETPACK.getStackForm(), INSULATING_TAPE.getStackForm(4), MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4)).outputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm()).buildAndRegister();

        //QuarkTech Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 2).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4), MetaItems.ELECTRIC_PISTON_EV.getStackForm(2), NANO_MUSCLE_SUITE_BOOTS.getStackForm()).outputs(QUARK_TECH_SUITE_BOOTS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 4).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(6), MetaItems.CONVEYOR_MODULE_EV.getStackForm(2), NANO_MUSCLE_SUITE_LEGGINS.getStackForm()).outputs(QUARK_TECH_SUITE_LEGGINS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 4).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(8), MetaItems.FIELD_GENERATOR_EV.getStackForm(2), NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm()).outputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 2).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4), MetaItems.SENSOR_EV.getStackForm(), MetaItems.EMITTER_EV.getStackForm(), NANO_MUSCLE_SUITE_HELMET.getStackForm()).outputs(QUARK_TECH_SUITE_HELMET.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(7100).inputs(MetaItems.FIELD_GENERATOR_IV.getStackForm()).inputs(MetaItems.FIELD_GENERATOR_EV.getStackForm(2)).input("circuitMaster", 4).input(wireGtSingle, IVSuperconductor, 4).inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4)).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(GRAVITATION_ENGINE.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192).inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16)).input(wireGtSingle, IVSuperconductor, 8).inputs(GRAVITATION_ENGINE.getStackForm(2)).inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(12)).input("circuitElite", 4).inputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm()).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192).inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8)).input(wireGtSingle, IVSuperconductor, 8).inputs(GRAVITATION_ENGINE.getStackForm(2)).inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(16)).input("circuitElite", 2).inputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm()).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
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

        COMPRESSOR_RECIPES.recipeBuilder().EUt(120).duration(300).input(ingot, Graphite).outputs(PYROLYTIC_CARBON.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().EUt(480).duration(3000).input(dust, Silicon).input(dust, Carbon).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Argon.getFluid(1000)).outputs(SiliconCarbide.getItemStack(2)).blastFurnaceTemp(2500).buildAndRegister();
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Aluminium));
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(884).input(dust, Aluminium).notConsumable(new IntCircuitIngredient(1)).outputs(OreDictUnifier.get(ingot, Aluminium)).blastFurnaceTemp(1700).buildAndRegister();

        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)});
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(dust, SodiumHydroxide, 3).fluidInputs(HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)).notConsumable(new IntCircuitIngredient(2)).fluidOutputs(Epichlorhydrin.getFluid(1000), SaltWater.getFluid(1000)).buildAndRegister();
    }


    public static void init2() {
        //Fuel Rabbit Hole - Layer 1
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(120).fluidInputs(LightFuel.getFluid(5000), HeavyFuel.getFluid(1000)).fluidOutputs(Fuel.getFluid(6000)).buildAndRegister();

        //Fuel high Octane
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(1920).fluidInputs(NitricOxide.getFluid(6000), Gasoline.getFluid(20000), Toluene.getFluid(1000), Octane.getFluid(2000), EthylTertButylEther.getFluid(3000)).fluidOutputs(HighOctaneGasoline.getFluid(32000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(10).EUt(480).fluidInputs(RawGasoline.getFluid(10000), Toluene.getFluid(1000)).fluidOutputs(Gasoline.getFluid(11000)).buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480).fluidInputs(Naphtha.getFluid(16000), Gas.getFluid(2000), Methanol.getFluid(1000), Acetone.getFluid(1000)).fluidOutputs(RawGasoline.getFluid(20000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(480).fluidInputs(Ethanol.getFluid(1000), Butane.getFluid(1000)).fluidOutputs(EthylTertButylEther.getFluid(2000)).buildAndRegister();


        //remove old hydrocracked light fuel to add a better one
        removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, HydroCrackedLightFuel.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{HydroCrackedLightFuel.getFluid(1000)});


        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(HydroCrackedLightFuel.getFluid(1000))
                .fluidOutputs(Naphtha.getFluid(750), Propane.getFluid(200), Butane.getFluid(150), Ethane.getFluid(125), Methane.getFluid(125), Octane.getFluid(50))
                .buildAndRegister();

        //Rocket Fuel tier 4
        MIXER_RECIPES.recipeBuilder().duration(19).EUt(480).fluidInputs(Dimethylhydrazine.getFluid(40), NitrogenTetroxide.getFluid(40)).fluidOutputs(RocketFuelH8N4C2O4.getFluid(35)).buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500).input(dust, Copper).fluidInputs(NitricAcid.getFluid(2000)).fluidOutputs(NitrogenTetroxide.getFluid(450)).chancedOutput(OreDictUnifier.get(dustSmall, Ash, 3), 10, 0).buildAndRegister();
        //Rocket fuel tier 3
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(NitricAcid.getFluid(25), MonoMethylHydrazine.getFluid(25)).fluidOutputs(RocketFuelCN3H7O3.getFluid(50)).buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(480).EUt(240).input(dust, Carbon).fluidInputs(Hydrogen.getFluid(1000), Hydrazine.getFluid(1000)).fluidOutputs(MonoMethylHydrazine.getFluid(1500)).buildAndRegister();
        //Rocket fuel tier 2
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(Hydrazine.getFluid(40), Methanol.getFluid(60)).fluidOutputs(DenseHydrazineFuelMixture.getFluid(100)).buildAndRegister();
        //rocket fuel tier 1
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(LiquidOxygen.getFluid(100), RP1.getFluid(25)).fluidOutputs(RP1RocketFuel.getFluid(25)).buildAndRegister();

        //Rocket fuel chemicalcha
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).fluidInputs(Ammonia.getFluid(1000), HydrogenPeroxide.getFluid(1000)).fluidOutputs(Hydrazine.getFluid(1000), Water.getFluid(100)).buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(600).EUt(240).fluidInputs(Air.getFluid(15000), EthylAnthraHydroQuinone.getFluid(5000), Anthracene.getFluid(50)).fluidOutputs(HydrogenPeroxide.getFluid(2000), EthylAnthraQuinone.getFluid(4000)).buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(800).EUt(120).fluidInputs(Hydrogen.getFluid(2000), EthylAnthraQuinone.getFluid(4000)).fluidOutputs(EthylAnthraHydroQuinone.getFluid(5000)).buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(300).EUt(120).input(dust, PhthalicAnhydride, 4).fluidInputs(EthylBenzene.getFluid(2000)).fluidOutputs(EthylAnthraQuinone.getFluid(4000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Ethylene.getFluid(2000), Benzene.getFluid(2000)).fluidOutputs(EthylBenzene.getFluid(4000)).buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(PhthalicAcid.getFluid(144)).outputs(OreDictUnifier.get(dust, PhthalicAnhydride)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).input(dust, Lithium, 5).fluidInputs(Naphtalene.getFluid(2000)).fluidOutputs(PhthalicAcid.getFluid(2500)).buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(Oxygen.getFluid(1000)).fluidOutputs(LiquidOxygen.getFluid(1000)).buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().duration(16).EUt(540).fluidInputs(Hydrogen.getFluid(60)).fluidOutputs(LiquidHydrogen.getFluid(60)).buildAndRegister();

        //Coal tar byproduct
        PYROLYSE_RECIPES.recipeBuilder().duration(1080).EUt(60).notConsumable(new IntCircuitIngredient(20)).input(gem, Lignite, 16).outputs(OreDictUnifier.get(dust, Ash, 4)).fluidOutputs(CoalTar.getFluid(800)).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().duration(360).EUt(120).notConsumable(new IntCircuitIngredient(20)).input(gem, Charcoal, 32).outputs(OreDictUnifier.get(dust, Ash, 4)).fluidOutputs(CoalTar.getFluid(800)).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().duration(720).EUt(120).notConsumable(new IntCircuitIngredient(20)).input(gem, Coal, 12).outputs(OreDictUnifier.get(dust, Ash, 4)).fluidOutputs(CoalTar.getFluid(2200)).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().duration(360).EUt(240).notConsumable(new IntCircuitIngredient(20)).input(gem, Coke, 8).outputs(OreDictUnifier.get(dust, Ash, 4)).fluidOutputs(CoalTar.getFluid(3400)).buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(900).EUt(60).fluidInputs(CoalTar.getFluid(1000)).fluidOutputs(CoalTarOil.getFluid(500), Kerosene.getFluid(200), EthylBenzene.getFluid(150), Naphtha.getFluid(100), Anthracene.getFluid(50)).buildAndRegister();

        DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(120).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Kerosene.getFluid(50)).fluidOutputs(RP1.getFluid(25)).buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().duration(25).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(SulfuricCoalTarOil.getFluid(20)).fluidOutputs(Naphtalene.getFluid(20)).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).fluidInputs(SulfuricAcid.getFluid(8000), CoalTarOil.getFluid(8000)).fluidOutputs(SulfuricCoalTarOil.getFluid(16000)).buildAndRegister();


        removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, FermentedBiomass.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(2).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(3).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(4).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(5).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(6).getMatchingStacks(), new FluidStack[]{FermentedBiomass.getFluid(1000)});
        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(FermentedBiomass.getFluid(2000))
                .fluidOutputs(AceticAcid.getFluid(25),
                        Water.getFluid(375),
                        Ethanol.getFluid(250),
                        Methanol.getFluid(150),
                        Ammonia.getFluid(100),
                        CarbonDioxide.getFluid(400),
                        Methane.getFluid(600),
                        Butanol.getFluid(100))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(FermentationBase.getFluid(2000))
                .fluidOutputs(AceticAcid.getFluid(50),
                        Ethanol.getFluid(600),
                        Methanol.getFluid(150),
                        Ammonia.getFluid(100),
                        CarbonDioxide.getFluid(400),
                        Methane.getFluid(600),
                        Butanol.getFluid(100))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(RedOil.getFluid(4000))
                .outputs(OreDictUnifier.get(dust, FerriteMixture))
                .fluidOutputs(Hydrazine.getFluid(1000),
                        RP1.getFluid(1000),
                        TributylPhosphate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(20)
                .fluidInputs(Chlorine.getFluid(3000))
                .input(dust, Phosphorus)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(PhosphorusTrichloride.getFluid(4000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .fluidInputs(PhosphorusTrichloride.getFluid(4000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(PhosphorylChloride.getFluid(5000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .fluidInputs(PhosphorylChloride.getFluid(5000))
                .fluidInputs(Butanol.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(1000))
                .fluidOutputs(TributylPhosphate.getFluid(5000))
                .buildAndRegister();


        //Assline Recipes
        //motor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10240).outputs(ELECTRIC_MOTOR_LUV.getStackForm()).inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, HSSG, 2), OreDictUnifier.get(ring, HSSG, 4), OreDictUnifier.get(GAEnums.GAOrePrefix.round, HSSG, 16), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(40960).outputs(ELECTRIC_MOTOR_ZPM.getStackForm()).inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 16), OreDictUnifier.get(stickLong, HSSE, 2), OreDictUnifier.get(ring, HSSE, 4), OreDictUnifier.get(GAEnums.GAOrePrefix.round, HSSE, 16), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(cableGtQuadruple, Naquadah, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(163840).outputs(ELECTRIC_MOTOR_UV.getStackForm()).inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64), OreDictUnifier.get(stickLong, Tritanium, 2), OreDictUnifier.get(ring, Tritanium, 4), OreDictUnifier.get(GAEnums.GAOrePrefix.round, Tritanium, 16), OreDictUnifier.get(wireFine, Duranium, 64), OreDictUnifier.get(wireFine, Duranium, 64), OreDictUnifier.get(wireFine, Duranium, 64), OreDictUnifier.get(wireFine, Duranium, 64), OreDictUnifier.get(cableGtQuadruple, Duranium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(655360).outputs(ELECTRIC_MOTOR_UHV.getStackForm()).inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64), OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64), OreDictUnifier.get(stickLong, HDCS, 2), OreDictUnifier.get(ring, HDCS, 4), OreDictUnifier.get(GAEnums.GAOrePrefix.round, HDCS, 16), OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64), OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64), OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64), OreDictUnifier.get(wireFine, TungstenTitaniumCarbide, 64)).input(cableGtQuadruple, TungstenTitaniumCarbide, 2).fluidInputs(SolderingAlloy.getFluid(2592), Lubricant.getFluid(3000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2621440).outputs(ELECTRIC_MOTOR_UEV.getStackForm()).inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64), OreDictUnifier.get(stickLong, NeodymiumMagnetic, 64), OreDictUnifier.get(stickLong, HDCS, 2), OreDictUnifier.get(ring, HDCS, 4), OreDictUnifier.get(GAEnums.GAOrePrefix.round, HDCS, 16), OreDictUnifier.get(wireFine, Pikyonium, 64), OreDictUnifier.get(wireFine, Pikyonium, 64), OreDictUnifier.get(wireFine, Pikyonium, 64), OreDictUnifier.get(wireFine, Pikyonium, 64)).input(cableGtQuadruple, Pikyonium, 2).fluidInputs(SolderingAlloy.getFluid(2592), Lubricant.getFluid(3000)).buildAndRegister();
        //pump
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360).outputs(ELECTRIC_PUMP_LUV.getStackForm()).inputs(ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(pipeSmall, Ultimet, 2), OreDictUnifier.get(screw, HSSG, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440).outputs(ELECTRIC_PUMP_ZPM.getStackForm()).inputs(ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(pipeMedium, Ultimet, 2), OreDictUnifier.get(screw, HSSE, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSE, 2), OreDictUnifier.get(cableGtSingle, Naquadah, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760).outputs(ELECTRIC_PUMP_UV.getStackForm()).inputs(ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(pipeLarge, Ultimet, 2), OreDictUnifier.get(screw, Tritanium, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, Tritanium, 2), OreDictUnifier.get(cableGtSingle, Duranium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040).outputs(ELECTRIC_PUMP_UHV.getStackForm()).inputs(ELECTRIC_MOTOR_UHV.getStackForm(), OreDictUnifier.get(pipeLarge, Zeron100, 32), OreDictUnifier.get(screw, HDCS, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HDCS, 2)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160).outputs(ELECTRIC_PUMP_UEV.getStackForm()).inputs(ELECTRIC_MOTOR_UEV.getStackForm(), OreDictUnifier.get(pipeLarge, Lafium, 64), OreDictUnifier.get(pipeLarge, Lafium, 64), OreDictUnifier.get(screw, HDCS, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HDCS, 2)).input(cableGtSingle, Pikyonium, 2).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        //conveyor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360).outputs(CONVEYOR_MODULE_LUV.getStackForm()).inputs(ELECTRIC_MOTOR_LUV.getStackForm(2), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gear, HSSG, 4), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(StyreneButadieneRubber.getFluid(1440), Lubricant.getFluid(250)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440).outputs(CONVEYOR_MODULE_ZPM.getStackForm()).inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gear, HSSE, 4), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, Naquadah, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(750)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760).outputs(CONVEYOR_MODULE_UV.getStackForm()).inputs(ELECTRIC_MOTOR_UV.getStackForm(2), OreDictUnifier.get(plate, Tritanium, 8), OreDictUnifier.get(gear, Tritanium, 4), OreDictUnifier.get(stick, Tritanium, 4), OreDictUnifier.get(ingot, Tritanium, 2), OreDictUnifier.get(cableGtSingle, Duranium, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040).outputs(CONVEYOR_MODULE_UHV.getStackForm()).inputs(ELECTRIC_MOTOR_UHV.getStackForm(2), OreDictUnifier.get(plate, HDCS, 8), OreDictUnifier.get(gear, HDCS, 4), OreDictUnifier.get(stick, HDCS, 4), OreDictUnifier.get(ingot, HDCS, 2)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160).outputs(CONVEYOR_MODULE_UEV.getStackForm()).inputs(ELECTRIC_MOTOR_UEV.getStackForm(2), OreDictUnifier.get(plate, HDCS, 8), OreDictUnifier.get(gear, HDCS, 4), OreDictUnifier.get(stick, HDCS, 4), OreDictUnifier.get(ingot, HDCS, 2)).input(cableGtSingle, Pikyonium, 2).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(2000)).buildAndRegister();
        //piston
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360).outputs(ELECTRIC_PISTON_LUV.getStackForm()).inputs(ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gearSmall, HSSG, 8), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440).outputs(ELECTRIC_PISTON_ZPM.getStackForm()).inputs(ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gearSmall, HSSE, 8), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, Naquadah, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760).outputs(ELECTRIC_PISTON_UV.getStackForm()).inputs(ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(plate, Tritanium, 8), OreDictUnifier.get(gearSmall, Tritanium, 8), OreDictUnifier.get(stick, Tritanium, 4), OreDictUnifier.get(ingot, Tritanium, 2), OreDictUnifier.get(cableGtSingle, Duranium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040).outputs(ELECTRIC_PISTON_UHV.getStackForm()).inputs(ELECTRIC_MOTOR_UHV.getStackForm(), OreDictUnifier.get(plate, HDCS, 8), OreDictUnifier.get(gearSmall, HDCS, 8), OreDictUnifier.get(stick, HDCS, 4), OreDictUnifier.get(ingot, HDCS, 2)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160).outputs(ELECTRIC_PISTON_UEV.getStackForm()).inputs(ELECTRIC_MOTOR_UEV.getStackForm(), OreDictUnifier.get(plate, HDCS, 8), OreDictUnifier.get(gearSmall, HDCS, 8), OreDictUnifier.get(stick, HDCS, 4), OreDictUnifier.get(ingot, HDCS, 2)).input(cableGtSingle, Pikyonium, 2).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).buildAndRegister();
        //robot arm
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(20480).outputs(ROBOT_ARM_LUV.getStackForm()).inputs(OreDictUnifier.get(cableGtDouble, YttriumBariumCuprate, 16), OreDictUnifier.get(screw, HSSG, 16), OreDictUnifier.get(stick, HSSG, 16), OreDictUnifier.get(ingot, HSSG), ELECTRIC_MOTOR_LUV.getStackForm(2), ELECTRIC_PISTON_LUV.getStackForm()).input(circuit, Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576), Lubricant.getFluid(250)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(81920).outputs(ROBOT_ARM_ZPM.getStackForm()).inputs(OreDictUnifier.get(cableGtDouble, Naquadah, 16), OreDictUnifier.get(screw, HSSE, 16), OreDictUnifier.get(stick, HSSE, 16), OreDictUnifier.get(ingot, HSSE), ELECTRIC_MOTOR_ZPM.getStackForm(2), ELECTRIC_PISTON_ZPM.getStackForm()).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(1152), Lubricant.getFluid(750)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(327680).outputs(ROBOT_ARM_UV.getStackForm()).inputs(OreDictUnifier.get(cableGtDouble, Duranium, 16), OreDictUnifier.get(screw, Tritanium, 16), OreDictUnifier.get(stick, Tritanium, 16), OreDictUnifier.get(ingot, Tritanium), ELECTRIC_MOTOR_UV.getStackForm(2), ELECTRIC_PISTON_UV.getStackForm()).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(2304), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1310720).outputs(ROBOT_ARM_UHV.getStackForm()).inputs(OreDictUnifier.get(cableGtDouble, TungstenTitaniumCarbide, 16), OreDictUnifier.get(screw, HDCS, 16), OreDictUnifier.get(stick, HDCS, 16), OreDictUnifier.get(ingot, HDCS), ELECTRIC_MOTOR_UHV.getStackForm(2), ELECTRIC_PISTON_UHV.getStackForm()).input(circuit, Tier.Ultimate, 8).fluidInputs(SolderingAlloy.getFluid(2304), Lubricant.getFluid(2000)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(5242880).outputs(ROBOT_ARM_UEV.getStackForm()).inputs(OreDictUnifier.get(cableGtDouble, Pikyonium, 16), OreDictUnifier.get(screw, HDCS, 16), OreDictUnifier.get(stick, HDCS, 16), OreDictUnifier.get(ingot, HDCS), ELECTRIC_MOTOR_UEV.getStackForm(2), ELECTRIC_PISTON_UEV.getStackForm()).input(circuit, Tier.Superconductor, 8).fluidInputs(SolderingAlloy.getFluid(2304), Lubricant.getFluid(2000)).buildAndRegister();
        //emitter
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360).outputs(EMITTER_LUV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSG, 1), ZincSelenide.getItemStack(16), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440).outputs(EMITTER_ZPM.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSE, 1), Fluorescein.getItemStack(16), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, Naquadah, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760).outputs(EMITTER_UV.getStackForm()).inputs(OreDictUnifier.get(frameGt, Tritanium, 1), Stibene.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 32), OreDictUnifier.get(wireGtDouble, Duranium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040).outputs(EMITTER_UHV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HDCS, 1), FranciumCaesiumCadmiumBromide.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 64)).input(cableGtSingle, TungstenTitaniumCarbide, 8).inputs(OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Ultimate, 2).fluidInputs(SolderingAlloy.getFluid(288)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160).outputs(EMITTER_UEV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HDCS, 1), RhodamineB.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64)).input(cableGtSingle, Pikyonium, 8).inputs(OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Superconductor, 2).fluidInputs(SolderingAlloy.getFluid(576)).buildAndRegister();
        //sensor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360).outputs(SENSOR_LUV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSG, 1), OreDictUnifier.get(dust, Germanium, 16), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440).outputs(SENSOR_ZPM.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSE, 1), LeadSenenide.getItemStack(16), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, Naquadah, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760).outputs(SENSOR_UV.getStackForm()).inputs(OreDictUnifier.get(frameGt, Tritanium, 1), BariumStrontiumTitanate.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 32), OreDictUnifier.get(wireGtDouble, Duranium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040).outputs(SENSOR_UHV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HDCS, 1), LeadScandiumTantalate.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 64)).input(cableGtSingle, TungstenTitaniumCarbide, 8).inputs(OreDictUnifier.get(gemExquisite, Diamond, 2)).inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4)).input(circuit, Tier.Ultimate, 2).fluidInputs(SolderingAlloy.getFluid(288)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160).outputs(SENSOR_UEV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HDCS, 1), MagnetorestrictiveAlloy.getItemStack(16), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64)).input(cableGtSingle, Pikyonium, 8).inputs(OreDictUnifier.get(gemExquisite, Diamond, 2)).inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8)).input(circuit, Tier.Superconductor, 2).fluidInputs(SolderingAlloy.getFluid(576)).buildAndRegister();
        //field generator
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(30720).outputs(FIELD_GENERATOR_LUV.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSG, 1), QUANTUM_STAR.getStackForm(), OreDictUnifier.get(wireFine, Osmium, 16), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Master, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880).outputs(FIELD_GENERATOR_ZPM.getStackForm()).inputs(OreDictUnifier.get(frameGt, HSSE, 1), QUANTUM_STAR.getStackForm(), OreDictUnifier.get(wireFine, Osmium, 16), OreDictUnifier.get(cableGtOctal, Naquadah, 4)).input(circuit, Tier.Ultimate, 2).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(491520).outputs(FIELD_GENERATOR_UV.getStackForm()).inputs(OreDictUnifier.get(frameGt, Tritanium, 1), GRAVI_STAR.getStackForm(), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, Duranium, 4)).input(circuit, Tier.Superconductor, 2).fluidInputs(SolderingAlloy.getFluid(288)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1966080).outputs(FIELD_GENERATOR_UHV.getStackForm()).inputs(OreDictUnifier.get(frameGt, Seaborgium, 1), GRAVI_STAR.getStackForm(), OreDictUnifier.get(wireFine, Osmium, 64)).input(cableGtSingle, TungstenTitaniumCarbide, 4).input(circuit, Tier.Infinite, 2).fluidInputs(SolderingAlloy.getFluid(576)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320).outputs(FIELD_GENERATOR_UEV.getStackForm()).inputs(OreDictUnifier.get(frameGt, Bohrium, 1), GRAVI_STAR.getStackForm(), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64)).input(cableGtSingle, Pikyonium, 4).input(circuit, UEV, 2).fluidInputs(SolderingAlloy.getFluid(1152)).buildAndRegister();

        //Star Recipes
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680).inputs(new ItemStack(Items.NETHER_STAR)).fluidInputs(Dubnium.getFluid(288)).outputs(GRAVI_STAR.getStackForm()).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(122880).inputs(GRAVI_STAR.getStackForm()).fluidInputs(Adamantium.getFluid(288)).outputs(UNSTABLE_STAR.getStackForm()).buildAndRegister();

        //Fusion Recipes
        FUSION_RECIPES.recipeBuilder().fluidInputs(Deuterium.getFluid(125), Tritium.getFluid(125)).fluidOutputs(Helium.getPlasma(125)).duration(16).EUt(4096).EUToStart(40000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Deuterium.getFluid(125), Helium3.getFluid(125)).fluidOutputs(Helium.getPlasma(125)).duration(16).EUt(2048).EUToStart(60000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Fluorine.getFluid(125), Helium3.getFluid(125)).fluidOutputs(Potassium.getPlasma(125)).duration(16).EUt(2048).EUToStart(60000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Carbon.getFluid(125), Helium3.getFluid(125)).fluidOutputs(Oxygen.getPlasma(125)).duration(32).EUt(4096).EUToStart(80000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Beryllium.getFluid(16), Deuterium.getFluid(375)).fluidOutputs(Nitrogen.getPlasma(175)).duration(16).EUt(16384).EUToStart(180000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Silicon.getFluid(16), Magnesium.getFluid(16)).fluidOutputs(Iron.getPlasma(125)).duration(32).EUt(8192).EUToStart(360000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Potassium.getFluid(16), Fluorine.getFluid(125)).fluidOutputs(Nickel.getPlasma(125)).duration(16).EUt(32768).EUToStart(480000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Beryllium.getFluid(16), Tungsten.getFluid(16)).fluidOutputs(Platinum.getFluid(16)).duration(32).EUt(32768).EUToStart(150000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Neodymium.getFluid(16), Hydrogen.getFluid(48)).fluidOutputs(Europium.getFluid(16)).duration(64).EUt(24576).EUToStart(150000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Lutetium.getFluid(16), Chrome.getFluid(16)).fluidOutputs(Americium.getFluid(16)).duration(96).EUt(49152).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Plutonium.getFluid(16), Thorium.getFluid(16)).fluidOutputs(Naquadah.getFluid(16)).duration(64).EUt(32768).EUToStart(300000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Tungsten.getFluid(16), Helium.getFluid(16)).fluidOutputs(Osmium.getFluid(16)).duration(64).EUt(24578).EUToStart(150000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Manganese.getFluid(16), Hydrogen.getFluid(16)).fluidOutputs(Iron.getFluid(16)).duration(64).EUt(8192).EUToStart(120000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Mercury.getFluid(16), Magnesium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Aluminium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Uranium.getFluid(16), Helium.getFluid(16)).fluidOutputs(Plutonium.getFluid(16)).duration(128).EUt(49152).EUToStart(480000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Nickel.getFluid(16), Polonium.getFluid(16)).fluidOutputs(Copernicium.getFluid(16)).duration(128).EUt(49152).EUToStart(480000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Vanadium.getFluid(16), Hydrogen.getFluid(125)).fluidOutputs(Chrome.getFluid(16)).duration(64).EUt(24576).EUToStart(140000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gallium.getFluid(16), Radon.getFluid(125)).fluidOutputs(Duranium.getFluid(16)).duration(64).EUt(16384).EUToStart(180000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Titanium.getFluid(48), Duranium.getFluid(32)).fluidOutputs(Tritanium.getFluid(16)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Mercury.getFluid(16)).fluidOutputs(Radon.getPlasma(125)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Tantalum.getFluid(16), Tritium.getFluid(16)).fluidOutputs(Tungsten.getFluid(16)).duration(16).EUt(24576).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Silver.getFluid(16), Lithium.getFluid(16)).fluidOutputs(Indium.getFluid(16)).duration(32).EUt(24576).EUToStart(380000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(NaquadahEnriched.getFluid(15), Radon.getFluid(125)).fluidOutputs(Naquadria.getFluid(3)).duration(64).EUt(49152).EUToStart(400000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Lanthanum.getFluid(16), Silicon.getFluid(16)).fluidOutputs(Lutetium.getFluid(16)).duration(16).EUt(8192).EUToStart(80000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Plutonium244Isotope.getMaterial().getFluid(16), Neon.getFluid(16)).fluidOutputs(Rutherfordium.getFluid(16)).duration(64).EUt(24576).EUToStart(150000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Americium241.getMaterial().getFluid(16), Neon.getFluid(16)).fluidOutputs(Dubnium.getFluid(16)).duration(96).EUt(49152).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Plutonium.getFluid(144), Calcium.getFluid(144)).fluidOutputs(Seaborgium.getFluid(288)).duration(100).EUt(75000).EUToStart(400000000).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Curium247.getMaterial().getFluid(144), Sodium.getFluid(144)).fluidOutputs(Bohrium.getFluid(288)).duration(50).EUt(1000000).euStart(1000000000).coilTier(1).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Trinium.getFluid(144), Tritanium.getFluid(144)).fluidOutputs(Adamantium.getFluid(288)).duration(100).EUt(2000000).coilTier(1).euStart(1000000000).euReturn(40).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Adamantium.getFluid(144), Seaborgium.getFluid(144)).fluidOutputs(Vibranium.getFluid(288)).duration(100).EUt(8000000).coilTier(2).euStart(2500000000L).euReturn(40).buildAndRegister();
        //Fusion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING), "PhP", "PHP", "PwP", 'P', "plateTungstenSteel", 'H', MetaBlocks.MACHINE_CASING.getItemVariant(LuV));
        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2), "PhP", "PHP", "PwP", 'P', "plateRutherfordium", 'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING));
        ModHandler.addShapedRecipe("fusion_casing_3", GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_3), "PhP", "PHP", "PwP", 'P', "plateDubnium", 'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).input(plate, Rutherfordium, 6).outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2)).input(plate, Dubnium, 6).outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_3)).duration(50).buildAndRegister();

        // Fusion Coil Recipes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).inputs(NEUTRON_REFLECTOR.getStackForm(2), FIELD_GENERATOR_LUV.getStackForm(), OreDictUnifier.get(cableGtQuadruple, LuVSuperconductor, 4), OreDictUnifier.get(plate, Osmiridium, 2)).input(circuit, Tier.Master, 1).fluidInputs(Helium.getFluid(4000)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL)).duration(400).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(122880).inputs(NEUTRON_REFLECTOR.getStackForm(4), FIELD_GENERATOR_ZPM.getStackForm(), OreDictUnifier.get(cableGtQuadruple, ZPMSuperconductor, 4), OreDictUnifier.get(plate, Rutherfordium, 2)).input(circuit, Tier.Ultimate, 1).fluidInputs(Helium.getFluid(4000)).outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_2)).duration(400).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(491520).inputs(NEUTRON_REFLECTOR.getStackForm(6), FIELD_GENERATOR_ZPM.getStackForm(2), OreDictUnifier.get(cableGtQuadruple, UVSuperconductor, 4), OreDictUnifier.get(plate, Tritanium, 2)).input(circuit, Tier.Superconductor, 1).fluidInputs(Helium.getFluid(4000)).outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_3)).duration(400).buildAndRegister();


        MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 4)
                .input(dust, Kanthal)
                .input(dust, Invar, 5)
                .outputs(OreDictUnifier.get(dust, EglinSteelBase, 10))
                .EUt(32)
                .duration(100)
                .buildAndRegister();
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
        //Explosive Recipes
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.PAPER), new ItemStack(Items.STRING)).fluidInputs(Glyceryl.getFluid(500)).outputs(DYNAMITE.getStackForm()).buildAndRegister();

        //Lapotron Crystal Recipes
        for (MaterialStack m : lapisLike) {
            GemMaterial gem = (GemMaterial) m.material;
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(), LAPOTRON_CRYSTAL.getStackForm(), OreDictUnifier.get(gemExquisite, Sapphire), OreDictUnifier.get(stick, gem), CAPACITOR.getStackForm());
        }

        //Add Missing Superconducter Wire Tiering Recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGtSingle, Tier.Superconductor, 1).input(foil, PolyphenyleneSulfide, 1).outputs(OreDictUnifier.get(cableGtSingle, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(25).input(wireGtSingle, Tier.Superconductor, 2).input(foil, PolyphenyleneSulfide, 2).outputs(OreDictUnifier.get(cableGtDouble, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(26).input(wireGtSingle, Tier.Superconductor, 4).input(foil, PolyphenyleneSulfide, 4).outputs(OreDictUnifier.get(cableGtQuadruple, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(27).input(wireGtSingle, Tier.Superconductor, 8).input(foil, PolyphenyleneSulfide, 8).outputs(OreDictUnifier.get(cableGtOctal, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(28).input(wireGtSingle, Tier.Superconductor, 16).input(foil, PolyphenyleneSulfide, 16).outputs(OreDictUnifier.get(cableGtHex, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGtDouble, Tier.Superconductor).input(foil, PolyphenyleneSulfide, 2).outputs(OreDictUnifier.get(cableGtDouble, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGtQuadruple, Tier.Superconductor).input(foil, PolyphenyleneSulfide, 4).outputs(OreDictUnifier.get(cableGtQuadruple, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGtOctal, Tier.Superconductor).input(foil, PolyphenyleneSulfide, 8).outputs(OreDictUnifier.get(cableGtOctal, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGtHex, Tier.Superconductor).input(foil, PolyphenyleneSulfide, 16).outputs(OreDictUnifier.get(cableGtHex, Tier.Superconductor)).duration(150).EUt(8).buildAndRegister();


        ModHandler.addShapelessRecipe("superonducter_wire_gtsingle_doubling", OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_doubling", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_doubling", OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_doubling", OreDictUnifier.get(wireGtHex, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_splitting", OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 2), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_splitting", OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 2), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_splitting", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 2), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gthex_splitting", OreDictUnifier.get(wireGtOctal, Tier.Superconductor, 2), OreDictUnifier.get(wireGtHex, Tier.Superconductor));

        //Schematic Recipes
        ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4).input(circuit, Tier.Good, 4).input(plate, StainlessSteel, 2).outputs(SCHEMATIC.getStackForm()).buildAndRegister();

        //Configuration Circuit
        ModHandler.addShapelessRecipe("basic_to_configurable_circuit", INTEGRATED_CIRCUIT.getStackForm(), "circuitBasic");

        //MAX Machine Hull
        ModHandler.addShapedRecipe("ga_casing_max", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Neutronium));
        ModHandler.addShapedRecipe("ga_hull_max", MetaTileEntities.HULL[GTValues.MAX].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), 'C', new UnificationEntry(wireGtSingle, Tier.Superconductor), 'H', new UnificationEntry(plate, Neutronium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).input(plate, Neutronium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).circuitMeta(8).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).input(wireGtSingle, Tier.Superconductor, 2).fluidInputs(Polytetrafluoroethylene.getFluid(288)).outputs(MetaTileEntities.HULL[9].getStackForm()).buildAndRegister();

        OreDictionary.getOres("treeLeaves").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

        //Redstone and glowstone melting
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(dust, Redstone).fluidOutputs(Redstone.getFluid(144)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(dust, Glowstone).fluidOutputs(Glowstone.getFluid(144)).buildAndRegister();

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
        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(dust, NetherQuartz).outputs(OreDictUnifier.get(plate, NetherQuartz)).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(dust, CertusQuartz).outputs(OreDictUnifier.get(plate, CertusQuartz)).buildAndRegister();

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

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120).input(wireGtSingle, MVSuperconductorBase, 3).inputs(OreDictUnifier.get(pipeTiny, StainlessSteel, 2), ELECTRIC_PUMP_MV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, MVSuperconductor, 3)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256).input(wireGtSingle, HVSuperconductorBase, 3).inputs(OreDictUnifier.get(pipeTiny, Titanium, 2), ELECTRIC_PUMP_HV.getStackForm()).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, HVSuperconductor, 3)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).input(wireGtSingle, EVSuperconductorBase, 9).inputs(OreDictUnifier.get(pipeTiny, TungstenSteel, 6), ELECTRIC_PUMP_EV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, EVSuperconductor, 9)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920).input(wireGtSingle, IVSuperconductorBase, 6).inputs(OreDictUnifier.get(pipeTiny, NiobiumTitanium, 4), ELECTRIC_PUMP_IV.getStackForm()).fluidInputs(Nitrogen.getFluid(4000)).outputs(OreDictUnifier.get(wireGtSingle, IVSuperconductor, 6)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680).input(wireGtSingle, LuVSuperconductorBase, 8).inputs(OreDictUnifier.get(pipeTiny, Enderium, 5), ELECTRIC_PUMP_LUV.getStackForm()).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720).input(wireGtSingle, ZPMSuperconductorBase, 16).inputs(OreDictUnifier.get(pipeTiny, Naquadah, 6), ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(8000)).outputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductor, 16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880).input(wireGtSingle, UVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Ultimet, 7), ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(10000)).outputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(491520).input(wireGtSingle, UHVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Zeron100, 7), ELECTRIC_PUMP_UV.getStackForm()).fluidInputs(Nitrogen.getFluid(12000)).outputs(OreDictUnifier.get(wireGtSingle, UHVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(1966080).input(wireGtSingle, UEVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Lafium, 7), ELECTRIC_PUMP_UHV.getStackForm()).fluidInputs(Nitrogen.getFluid(14000)).outputs(OreDictUnifier.get(wireGtSingle, UEVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(7864320).input(wireGtSingle, UIVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7), ELECTRIC_PUMP_UHV.getStackForm()).fluidInputs(Nitrogen.getFluid(16000)).outputs(OreDictUnifier.get(wireGtSingle, UIVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(31457280).input(wireGtSingle, UMVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7), ELECTRIC_PUMP_UEV.getStackForm()).fluidInputs(Nitrogen.getFluid(18000)).outputs(OreDictUnifier.get(wireGtSingle, UMVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(125829120).input(wireGtSingle, UXVSuperconductorBase, 32).inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7), ELECTRIC_PUMP_UIV.getStackForm()).fluidInputs(Nitrogen.getFluid(20000)).outputs(OreDictUnifier.get(wireGtSingle, UXVSuperconductor, 32)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(20).EUt(503316480).input(wireGtSingle, UXVSuperconductorBase, 64).inputs(OreDictUnifier.get(pipeTiny, Neutronium, 7), ELECTRIC_PUMP_UMV.getStackForm()).fluidInputs(Nitrogen.getFluid(22000)).outputs(OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64)).buildAndRegister();

        //GTNH Coils
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Mica, 3).input(dust, RawRubber, 2).outputs(OreDictUnifier.get(dust, MicaPulp, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Mica, 3).inputs(RUBBER_DROP.getStackForm()).outputs(OreDictUnifier.get(dust, MicaPulp, 4)).buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, Sapphire).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, GreenSapphire).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, Ruby).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(28).input(dust, MicaPulp, 4).input(dust, Asbestos).outputs(MICA_SHEET.getStackForm(4)).buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(30).inputs(MICA_SHEET.getStackForm(4)).input(dust, SiliconDioxide).outputs(MICA_INSULATOR_SHEET.getStackForm(4)).buildAndRegister();
        if (GAConfig.GT6.BendingFoilsAutomatic && GAConfig.GT6.BendingCylinders)
            CLUSTER_MILL_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(MICA_INSULATOR_SHEET.getStackForm()).outputs(MICA_INSULATOR_FOIL.getStackForm(4)).buildAndRegister();
        else BENDER_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(MICA_INSULATOR_SHEET.getStackForm()).circuitMeta(1).outputs(MICA_INSULATOR_FOIL.getStackForm(4)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8).input(wireGtDouble, Cupronickel, 8).inputs(OreDictUnifier.get(dust, AluminoSilicateWool, 12)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).input(wireGtDouble, Cupronickel, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30).input(wireGtDouble, Kanthal, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Copper.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120).input(wireGtDouble, Nichrome, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Aluminium.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).input(wireGtDouble, TungstenSteel, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Nichrome.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TUNGSTENSTEEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).input(wireGtDouble, HSSG, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tungsten.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096).input(wireGtDouble, Naquadah, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(HSSG.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680).input(wireGtDouble, NaquadahAlloy, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Naquadah.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH_ALLOY)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(500000).input(wireGtDouble, TungstenTitaniumCarbide, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tritanium.getFluid(144)).outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000000).input(wireGtDouble, Pikyonium, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Adamantium.getFluid(144)).outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8000000).input(wireGtDouble, Cinobite, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Vibranium.getFluid(144)).outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_3)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(32000000).input(wireGtDouble, Neutronium, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Neutronium.getFluid(144)).outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(GAHeatingCoil.CoilType.HEATING_COIL_4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).input(wireGtDouble, LuVSuperconductor, 64).inputs(MICA_INSULATOR_FOIL.getStackForm(64)).fluidInputs(NaquadahAlloy.getFluid(144 * 8)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).input(wireGtDouble, ZPMSuperconductor, 32).inputs(MICA_INSULATOR_FOIL.getStackForm(32)).fluidInputs(NaquadahAlloy.getFluid(144 * 4)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).input(wireGtDouble, UVSuperconductor, 16).inputs(MICA_INSULATOR_FOIL.getStackForm(16)).fluidInputs(NaquadahAlloy.getFluid(144 * 2)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).input(wireGtDouble, Tier.Superconductor, 8).inputs(MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(NaquadahAlloy.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();


        //Formic acid
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .fluidOutputs(SodiumFormate.getFluid(1000))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumFormate.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FormicAcid.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 7))
                .EUt(30)
                .duration(15)
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Calcium, 5)
                .input(dust, Phosphate, 3)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, OrganicFertilizer, 10))
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120).fluidInputs(HydrochloricAcid.getFluid(2000)).input(dust, Calcite).fluidOutputs(Water.getFluid(1000)).fluidOutputs(CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(dust, CalciumChloride)).buildAndRegister();


        for (EmitterCasing.CasingType emitter : EmitterCasing.CasingType.values()) {
            ItemStack emitterStack = ((MetaItem.MetaValueItem) GACraftingComponents.EMITTER.getIngredient(emitter.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(emitter.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(emitter.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(emitterStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, emitter.getTier() - 1)))
                    .outputs(GAMetaBlocks.EMITTER_CASING.getItemVariant(emitter))
                    .duration(200)
                    .buildAndRegister();
        }
        for (MotorCasing.CasingType motor : MotorCasing.CasingType.values()) {
            ItemStack motorStack = ((MetaItem.MetaValueItem) GACraftingComponents.MOTOR.getIngredient(motor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(motor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(motor.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(motorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, motor.getTier() - 1)))
                    .outputs(GAMetaBlocks.MOTOR_CASING.getItemVariant(motor))
                    .duration(200)
                    .buildAndRegister();
        }
        for (PistonCasing.CasingType piston : PistonCasing.CasingType.values()) {
            ItemStack pistonStack = ((MetaItem.MetaValueItem) GACraftingComponents.PISTON.getIngredient(piston.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(piston.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(piston.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(pistonStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, piston.getTier() - 1)))
                    .outputs(GAMetaBlocks.PISTON_CASING.getItemVariant(piston))
                    .duration(200)
                    .buildAndRegister();
        }
        for (SensorCasing.CasingType sensor : SensorCasing.CasingType.values()) {
            ItemStack sensorStack = ((MetaItem.MetaValueItem) GACraftingComponents.SENSOR.getIngredient(sensor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(sensor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(sensor.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(sensorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, sensor.getTier() - 1)))
                    .outputs(GAMetaBlocks.SENSOR_CASING.getItemVariant(sensor))
                    .duration(200)
                    .buildAndRegister();
        }
        for (FieldGenCasing.CasingType fieldgen : FieldGenCasing.CasingType.values()) {
            ItemStack fieldgenStack = ((MetaItem.MetaValueItem) GACraftingComponents.FIELD_GENERATOR.getIngredient(fieldgen.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(fieldgen.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(fieldgen.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(fieldgenStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, fieldgen.getTier() - 1)))
                    .outputs(GAMetaBlocks.FIELD_GEN_CASING.getItemVariant(fieldgen))
                    .duration(200)
                    .buildAndRegister();
        }
        for (PumpCasing.CasingType pump : PumpCasing.CasingType.values()) {
            ItemStack pumpStack = ((MetaItem.MetaValueItem) GACraftingComponents.PUMP.getIngredient(pump.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(pump.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(pump.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(pumpStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, pump.getTier() - 1)))
                    .outputs(GAMetaBlocks.PUMP_CASING.getItemVariant(pump))
                    .duration(200)
                    .buildAndRegister();
        }
        for (ConveyorCasing.CasingType conveyor : ConveyorCasing.CasingType.values()) {
            ItemStack conveyorStack = ((MetaItem.MetaValueItem) GACraftingComponents.CONVEYOR.getIngredient(conveyor.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(conveyor.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(conveyor.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(conveyorStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, conveyor.getTier() - 1)))
                    .outputs(GAMetaBlocks.CONVEYOR_CASING.getItemVariant(conveyor))
                    .duration(200)
                    .buildAndRegister();
        }
        for (RobotArmCasing.CasingType robotarm : RobotArmCasing.CasingType.values()) {
            ItemStack robotarmStack = ((MetaItem.MetaValueItem) GACraftingComponents.ROBOT_ARM.getIngredient(robotarm.getTier())).getStackForm(2);
            ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(robotarm.getTier());
            UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(robotarm.getTier());
            ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(robotarmStack)
                    .inputs(hull)
                    .input(cableGtSingle, cable.material, 8)
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .EUt((int) (30 * Math.pow(4, robotarm.getTier() - 1)))
                    .outputs(GAMetaBlocks.ROBOT_ARM_CASING.getItemVariant(robotarm))
                    .duration(200)
                    .buildAndRegister();
        }

        //ZirconiumTetrachloride should be remove later
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120).inputs(ZirconiumTetrachloride.getItemStack(5)).outputs(OreDictUnifier.get(dust, Zirconium)).fluidOutputs(Chlorine.getFluid(4000)).buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120).fluidInputs(SiliconFluoride.getFluid(2000)).outputs(OreDictUnifier.get(dust, Silicon)).fluidOutputs(Fluorine.getFluid(1000)).buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120).fluidInputs(SiliconFluoride.getFluid(2000)).outputs(OreDictUnifier.get(dust, Silicon)).fluidOutputs(Fluorine.getFluid(1000)).buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120).fluidInputs(CarbonFluoride.getFluid(2000)).outputs(OreDictUnifier.get(dust, Carbon)).fluidOutputs(Fluorine.getFluid(1000)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(230).EUt(480).input(pipeLarge, Steel).input(ring, Steel).fluidInputs(SolderingAlloy.getFluid(288)).outputs(WELL_PIPE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(340).EUt(480).inputs(ELECTRIC_MOTOR_EV.getStackForm()).inputs(ELECTRIC_PUMP_EV.getStackForm()).input(stickLong, Steel, 6).input(plate, Steel, 2).input(toolHeadDrill, Steel).outputs(RIG_DRILL.getStackForm()).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64).input(dust, Barite).fluidInputs(Water.getFluid(1000)).fluidOutputs(BariumSulfateSolution.getFluid(1000)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64).input(dust, Calcite).fluidInputs(Water.getFluid(1000)).fluidOutputs(CalciumCarbonateSolution.getFluid(1000)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(64).input(dust, Bentonite).input(dust, Clay).fluidInputs(Water.getFluid(2000)).fluidOutputs(BentoniteClaySlurry.getFluid(2000)).buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder().duration(240).EUt(480).fluidInputs(BariumSulfateSolution.getFluid(1000)).fluidInputs(CalciumCarbonateSolution.getFluid(1000)).fluidInputs(BentoniteClaySlurry.getFluid(1000)).fluidInputs(Lubricant.getFluid(1000)).fluidInputs(ATL.getFluid(1000)).fluidInputs(EthyleneGlycol.getFluid(1000)).fluidOutputs(DrillingMud.getFluid(6000)).buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(480).fluidInputs(UsedDrillingMud.getFluid(1000)).fluidOutputs(DrillingMud.getFluid(950)).outputs(new ItemStack(Blocks.GRAVEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192).input(circuit, Tier.Infinite, 2).inputs(OreDictUnifier.get(gear, AbyssalAlloy, 8), OreDictUnifier.get(plate, AbyssalAlloy, 8), OreDictUnifier.get(cableGtSingle, TungstenTitaniumCarbide, 16), GATileEntities.GA_HULLS[0].getStackForm()).fluidInputs(Naquadria.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UHV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192).input(circuit, UEV, 2).inputs(OreDictUnifier.get(gear, TitanSteel, 8), OreDictUnifier.get(plate, TitanSteel, 8), OreDictUnifier.get(cableGtSingle, Pikyonium, 16), GATileEntities.GA_HULLS[1].getStackForm()).fluidInputs(Naquadria.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UEV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192).input(circuit, UIV, 2).inputs(OreDictUnifier.get(gear, BlackTitanium, 8), OreDictUnifier.get(plate, BlackTitanium, 8), OreDictUnifier.get(cableGtSingle, Cinobite, 16)).inputs(GATileEntities.GA_HULLS[2].getStackForm()).fluidInputs(Naquadria.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.TIERED_HULL_UIV, 1)).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Ethylene.getFluid(1000), Benzene.getFluid(1000)).fluidOutputs(Hydrogen.getFluid(2000), Styrene.getFluid(1000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Ammonia.getFluid(1000), Materials.Methanol.getFluid(2000)).fluidOutputs(Materials.Water.getFluid(2000), Materials.Dimethylamine.getFluid(1000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.HypochlorousAcid.getFluid(1000), Materials.Ammonia.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), Materials.Chloramine.getFluid(1000)).buildAndRegister();

        ModHandler.addShapedRecipe("ga_prospect_tool_mv", PROSPECT_TOOL_MV.getStackForm(), "EDS", "CTC", "PBP", 'E', EMITTER_MV.getStackForm(), 'D', new UnificationEntry(toolHeadDrill, Aluminium), 'S', SENSOR_MV.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Good), 'T', COVER_MACHINE_CONTROLLER.getStackForm(), 'P', new UnificationEntry(plate, Aluminium), 'B', BATTERY_RE_MV_SODIUM.getStackForm());
        ModHandler.addShapedRecipe("ga_prospect_tool_hv", PROSPECT_TOOL_HV.getStackForm(), "EDS", "CTC", "PBP", 'E', EMITTER_HV.getStackForm(), 'D', new UnificationEntry(toolHeadDrill, StainlessSteel), 'S', SENSOR_HV.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Advanced), 'T', COVER_MACHINE_CONTROLLER.getStackForm(), 'P', new UnificationEntry(plate, StainlessSteel), 'B', BATTERY_RE_HV_SODIUM.getStackForm());
        ModHandler.addShapedRecipe("ga_prospect_tool_luv", PROSPECT_TOOL_LuV.getStackForm(), "EDS", "CTC", "PBP", 'E', EMITTER_LUV.getStackForm(), 'D', new UnificationEntry(toolHeadDrill, RhodiumPlatedPalladium), 'S', SENSOR_LUV.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Master), 'T', COVER_MACHINE_CONTROLLER.getStackForm(), 'P', new UnificationEntry(plate, RhodiumPlatedPalladium), 'B', ENERGY_LAPOTRONIC_ORB2.getStackForm());
        ModHandler.addShapedRecipe("ga_prospect_tool_zpm", PROSPECT_TOOL_ZPM.getStackForm(), "EDS", "CTC", "PBP", 'E', EMITTER_ZPM.getStackForm(), 'D', new UnificationEntry(toolHeadDrill, HSSS), 'S', SENSOR_ZPM.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Ultimate), 'T', COVER_MACHINE_CONTROLLER.getStackForm(), 'P', new UnificationEntry(plate, HSSS), 'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE.getStackForm() : BATTERY_MEDIUM_NAQUADRIA.getStackForm());

        removeRecipesByInputs(CHEMICAL_RECIPES, Glycerol.getFluid(1000), HydrochloricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(480)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Epichlorhydrin.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
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
    }

    public static void forestrySupport() {
        //Making BioDiesel
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
        } else {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Ethanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
        }


        if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.APATITE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Apatite, 2).input(bolt, Apatite).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Apatite, 4).input(bolt, Apatite, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BLAZE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(dust, Blaze, 2).input(dustSmall, Blaze, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(dust, Blaze, 5).input(dust, Redstone, 2).outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(4)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BRONZE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Bronze, 2).input(bolt, Bronze).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Bronze, 4).input(bolt, Bronze, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.COPPER, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Copper, 2).input(bolt, Copper).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Copper, 4).input(bolt, Copper, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.DIAMOND, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Diamond, 2).input(bolt, Diamond).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Diamond, 4).input(bolt, Diamond, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.EMERALD, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Emerald, 2).input(bolt, Emerald).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Emerald, 4).input(bolt, Emerald, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ENDER, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(dust, Endstone, 2).input(dustSmall, Endstone, 2).input(dust, EnderEye).outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(dust, Endstone, 5).input(dust, EnderEye, 2).outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(4)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.GOLD, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Gold, 2).input(bolt, Gold).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Gold, 4).input(bolt, Gold, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(2)).buildAndRegister();
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_IRON.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.IRON, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Iron, 2).input(bolt, Iron).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_IRON.getStackForm()).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Iron, 4).input(bolt, Iron, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_IRON.getStackForm(2)).buildAndRegister();
            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.LAPIS, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Lapis, 2).input(bolt, Lapis).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Lapis, 4).input(bolt, Lapis, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.OBSIDIAN, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(dust, Obsidian, 2).input(dustSmall, Obsidian, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(dust, Obsidian, 5).input(dust, Redstone, 2).outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(4)).buildAndRegister();
            if (Loader.isModLoaded("extrautils2")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ORCHID, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).inputs(new ItemStack(Blocks.REDSTONE_ORE, 5), OreDictUnifier.get(dust, Redstone)).outputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(4)).buildAndRegister();
            }
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.RUBBER, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Rubber, 2).input(bolt, Rubber).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm()).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Rubber, 4).input(bolt, Rubber, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(2)).buildAndRegister();
            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_TIN.getStackForm(), OreDictUnifier.get(plate, Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.TIN, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(stick, Tin, 2).input(bolt, Tin).input(dustSmall, Redstone, 2).outputs(GAMetaItems.ELECTRODE_TIN.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(stick, Tin, 4).input(bolt, Tin, 2).input(dust, Redstone).outputs(GAMetaItems.ELECTRODE_TIN.getStackForm(2)).buildAndRegister();
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
                            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).outputs(recipe.getRecipeOutput()).buildAndRegister();
                        } else {
                            PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(SCHEMATIC_3X3.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(SCHEMATIC_3X3.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(SCHEMATIC_2X2.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                    FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9 && !hasPrefix(recipe.getIngredients().get(0).getMatchingStacks()[0], "ingot")) {
                if (!recipesToRemove.contains(recipe.getRegistryName()) && GAConfig.Misc.Unpackager3x3Recipes) {
                    UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).notConsumable(SCHEMATIC_3X3.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
            FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(block, NetherQuartz)).outputs(OreDictUnifier.get(gem, NetherQuartz, 4)).buildAndRegister();
            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(gem, Materials.NetherQuartz, 4).outputs(new ItemStack(Blocks.QUARTZ_BLOCK)).buildAndRegister();
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
