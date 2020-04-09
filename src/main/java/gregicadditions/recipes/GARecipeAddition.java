package gregicadditions.recipes;

import forestry.core.ModuleCore;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GARecipeAddition {

    private static final MaterialStack[] sawLubricants = {
            new MaterialStack(Lubricant, 1L),
            new MaterialStack(DistilledWater, 3L),
            new MaterialStack(Water, 4L)
    };
    private static final MaterialStack[] solderingList = {
            new MaterialStack(Tin, 2L),
            new MaterialStack(SolderingAlloy, 1L),
            new MaterialStack(Lead, 4L)
    };

    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Rubber, 144),
            new MaterialStack(StyreneButadieneRubber, 108),
            new MaterialStack(SiliconeRubber, 72)};

    private static final MaterialStack[] cableDusts = {
            new MaterialStack(Polydimethylsiloxane, 1),
            new MaterialStack(PolyvinylChloride, 1)};

    private static final MaterialStack[] firstMetal = {
            new MaterialStack(Iron, 1),
            new MaterialStack(Nickel, 1),
            new MaterialStack(Invar, 2),
            new MaterialStack(Steel, 2),
            new MaterialStack(StainlessSteel, 3),
            new MaterialStack(Titanium, 3),
            new MaterialStack(Tungsten, 4),
            new MaterialStack(TungstenSteel, 5)};

    private static final MaterialStack[] lastMetal = {
            new MaterialStack(Tin, 0),
            new MaterialStack(Zinc, 0),
            new MaterialStack(Aluminium, 1)};

    private static final MaterialStack[] ironOres = {
            new MaterialStack(Pyrite, 1),
            new MaterialStack(BrownLimonite, 1),
            new MaterialStack(YellowLimonite, 1),
            new MaterialStack(Magnetite, 1),
            new MaterialStack(Iron, 1)};

    private static final MaterialStack[] lubeDusts = {
            new MaterialStack(Talc, 1),
            new MaterialStack(Soapstone, 1),
            new MaterialStack(Redstone, 1)};

    private static final MaterialStack[] lapisLike = {
            new MaterialStack(Lapis, 1),
            new MaterialStack(Lazurite, 1),
            new MaterialStack(Sodalite, 1)};

    private static final List<Material> tieredCables = Arrays.asList(new Material[]{
            Tungsten, Osmium, Platinum, TungstenSteel, Graphene,
            VanadiumGallium, HSSG, YttriumBariumCuprate, NiobiumTitanium,
            Naquadah, NiobiumTitanium, NaquadahEnriched, Duranium,
            NaquadahAlloy
    });
    private static final List<Material> superconductors = Arrays.asList(new Material[]{
            MVSuperconductor, HVSuperconductor, EVSuperconductor,
            IVSuperconductor, LuVSuperconductor, ZPMSuperconductor
    });

    private static final List<Material> tieredSuperconductors = Arrays.asList(new Material[]{
            EVSuperconductor, IVSuperconductor,
            LuVSuperconductor, ZPMSuperconductor
    });

    public static void init() {

        OreDictUnifier.registerOre(new ItemStack(Items.SNOWBALL), dust, Snow);
        OreDictUnifier.registerOre(new ItemStack(Blocks.SNOW), block, Snow);

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(32).fluidInputs(Redstone.getFluid(144 * 3), Copper.getFluid(144)).fluidOutputs(RedAlloy.getFluid(144)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Redstone.getFluid(144 * 2)).inputs(CountableIngredient.from(ingot, Copper)).outputs(OreDictUnifier.get(ingot, RedAlloy)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Redstone.getFluid(144)).inputs(CountableIngredient.from(ingot, AnnealedCopper)).outputs(OreDictUnifier.get(ingot, RedAlloy)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().fluidInputs(Glass.getFluid(144)).notConsumable(MetaItems.SHAPE_MOLD_BALL.getStackForm()).outputs(MetaItems.GLASS_TUBE.getStackForm()).EUt(16).duration(80).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.GLOWSTONE_DUST, 4)).outputs(new ItemStack(Blocks.GLOWSTONE)).EUt(16).duration(40).buildAndRegister();

        //GTNH Bricks
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.removeFurnaceSmelting(MetaItems.COMPRESSED_CLAY.getStackForm());
        ModHandler.addSmeltingRecipe(MetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2).inputs(new ItemStack(Items.CLAY_BALL)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(new ItemStack(Items.BRICK)).buildAndRegister();
        OreDictionary.registerOre("formWood", MetaItems.WOODEN_FORM_BRICK.getStackForm());
        ModHandler.addShapelessRecipe("clay_brick", MetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.CLAY_BALL), "formWood");
        ModHandler.addShapedRecipe("eight_clay_brick", MetaItems.COMPRESSED_CLAY.getStackForm(8), "BBB", "BFB", "BBB", 'B', new ItemStack(Items.CLAY_BALL), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_brick", GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(3), "BBB", "SFS", "SSS", 'B', new ItemStack(Items.CLAY_BALL), 'S', new ItemStack(Blocks.SAND), 'F', "formWood");
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(), MetaItems.COKE_OVEN_BRICK.getStackForm());

        //GT5U Old Primitive Brick Processing
        ModHandler.removeFurnaceSmelting(MetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_block_to_dust"));
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().input(dust, Fireclay).outputs(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm()).duration(100).EUt(2).buildAndRegister();
        ModHandler.addShapedRecipe("quartz_sand", OreDictUnifier.get(dust, GAMaterials.QuartzSand), "S", "m", 'S', "sand");
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(200).EUt(8).input("sand", 1).outputs(OreDictUnifier.get(dust, GAMaterials.QuartzSand)).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2500, 500).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2000, 500).buildAndRegister();
        ModHandler.addShapelessRecipe("glass_dust_ga", OreDictUnifier.get(dust, Glass), "dustSand", "dustFlint");
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(200).EUt(8).input(dust, Flint).input(dust, GAMaterials.QuartzSand, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(160).EUt(8).input(dust, Flint).input(dust, Quartzite, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(100).EUt(16).input(dust, Calcite, 2).input(dust, Stone).input(dust, Clay).input(dust, GAMaterials.QuartzSand).fluidInputs(Water.getFluid(2000)).fluidOutputs(Concrete.getFluid(2304)).buildAndRegister();


        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), MetaItems.RUBBER_DROP.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));
        ModHandler.addShapelessRecipe("harder_bone_meal", new ItemStack(Items.DYE, 3, 15), new ItemStack(Items.BONE), ToolDictNames.craftingToolMortar);
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(16).EUt(10).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(300).EUt(2).buildAndRegister();

        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST, 3));


        //GT6 Bending
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET), "ChC", " P ", 'C', "plateCurvedIron", 'P', "plateIron");
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(valueOf("plateCurved"), Iron, 2).input(plate, Iron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(valueOf("plateCurved"), WroughtIron, 2).input(plate, WroughtIron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
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
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(ring, m).isEmpty() && !OreDictUnifier.get(stick, m).isEmpty() && m != Rubber && m != StyreneButadieneRubber && m != SiliconeRubber && GAConfig.GT6.BendingRings && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(ring, m));
                ModHandler.addShapedRecipe("tod_to_ring_" + m.toString(), OreDictUnifier.get(ring, m), "hS", " C", 'S', OreDictUnifier.get(stick, m), 'C', "craftingToolBendingCylinderSmall");
            }
            if (!OreDictUnifier.get(valueOf("plateCurved"), m).isEmpty() && GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
                ModHandler.addShapedRecipe("curved_plate_" + m.toString(), OreDictUnifier.get(valueOf("plateCurved"), m), "h", "P", "C", 'P', new UnificationEntry(plate, m), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("flatten_plate_" + m.toString(), OreDictUnifier.get(plate, m), "h", "C", 'C', new UnificationEntry(valueOf("plateCurved"), m));
                RecipeMaps.BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(plate, m).circuitMeta(0).outputs(OreDictUnifier.get(valueOf("plateCurved"), m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(rotor, m).isEmpty() && GAConfig.GT6.BendingRotors && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(rotor, m));
                ModHandler.addShapedRecipe("ga_rotor_" + m.toString(), OreDictUnifier.get(rotor, m), "ChC", "SRf", "CdC", 'C', OreDictUnifier.get(valueOf("plateCurved"), m), 'S', OreDictUnifier.get(screw, m), 'R', OreDictUnifier.get(ring, m));
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24).inputs(OreDictUnifier.get(valueOf("plateCurved"), m, 4), OreDictUnifier.get(ring, m)).fluidInputs(SolderingAlloy.getFluid(32)).outputs(OreDictUnifier.get(rotor, m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(foil, m).isEmpty()) {
                if (GAConfig.GT6.BendingFoils && GAConfig.GT6.BendingCylinders) {
                    ModHandler.addShapedRecipe("foil_" + m.toString(), OreDictUnifier.get(foil, m, 2), "hPC", 'P', new UnificationEntry(plate, m), 'C', "craftingToolBendingCylinder");
                }
                if (GAConfig.GT6.BendingFoilsAutomatic && GAConfig.GT6.BendingCylinders) {
                    GARecipeMaps.CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(plate, m).outputs(OreDictUnifier.get(foil, m, 4)).buildAndRegister();
                } else if (GAConfig.GT6.BendingFoilsAutomatic == false || GAConfig.GT6.BendingCylinders == false) {
                    RecipeMaps.BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).circuitMeta(4).input(plate, m).outputs(OreDictUnifier.get(foil, m, 4)).buildAndRegister();
                }
            }
            if (!OreDictUnifier.get(wireGtSingle, m).isEmpty() && !OreDictUnifier.get(wireFine, m).isEmpty() && GAConfig.GT5U.OldFineWireRecipes && GAConfig.GT6.BendingCylinders) {
                RecipeMaps.WIREMILL_RECIPES.recipeBuilder().EUt(9).duration(200).inputs(OreDictUnifier.get(wireGtSingle, m)).outputs(OreDictUnifier.get(wireFine, m, 4)).buildAndRegister();
            }

            if (!OreDictUnifier.get(valueOf("round"), m).isEmpty()) {
                ModHandler.addShapedRecipe("round" + m.toString(), OreDictUnifier.get(valueOf("round"), m), "fN", "N ", 'N', OreDictUnifier.get(nugget, m));
                RecipeMaps.LATHE_RECIPES.recipeBuilder().EUt(8).duration(100).inputs(OreDictUnifier.get(nugget, m)).outputs(OreDictUnifier.get(valueOf("round"), m)).buildAndRegister();
            }

            //ModHandler.addShapedRecipe("plasma_pipe", OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Plasma), "ESE", "NTN", "ESE", 'E', "platePlastic", 'S', OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor), 'N', "plateNeodymiumMagnetic", 'T', OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Titanium));

            if (GAConfig.GT6.BendingPipes && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(pipeSmall, Wood));
                ModHandler.removeRecipes(OreDictUnifier.get(pipeMedium, Wood));
                ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(pipeMedium, Wood, 2), "PPP", "sCh", "PPP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(pipeLarge, Wood), "PhP", "PCP", "PsP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(pipeSmall, Wood, 6), "PsP", "PCP", "PhP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
            }

            //Cables
            if ((m instanceof IngotMaterial || superconductors.contains(m)) && !OreDictUnifier.get(cableGtSingle, m).isEmpty() && m != RedAlloy && m != Cobalt && m != Zinc && m != SolderingAlloy && m != Tin && m != Lead && GAConfig.GT5U.CablesGT5U) {
                for (MaterialStack stackFluid : cableFluids) {
                    IngotMaterial fluid = (IngotMaterial) stackFluid.material;
                    int multiplier = (int) stackFluid.amount;
                    // Low-tiered superconductors recipe
                    if (superconductors.contains(m) && !tieredSuperconductors.contains(m)) {
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                        for (MaterialStack stackDust : cableDusts) {
                            Material dust = stackDust.material;
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                        }
                    } else
                        // EV+ tiered superconductors cable recipe
                        if (tieredSuperconductors.contains(m)) {
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                            for (MaterialStack stackDust : cableDusts) {
                                Material dust = stackDust.material;
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                            }
                        } else
                            // EV+ tiered cables recipe
                            if (tieredCables.contains(m)) {
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                for (MaterialStack stackDust : cableDusts) {
                                    Material dust = stackDust.material;
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                }
                            } else
                            // Low-tier cable recipes
                            {
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                for (MaterialStack stackDust : cableDusts) {
                                    Material dust = stackDust.material;
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                }
                            }
                }
            }

            //GT6 Plate Recipe
            if (m instanceof IngotMaterial && !OreDictUnifier.get(plate, m).isEmpty() && !OreDictUnifier.get(valueOf("ingotDouble"), m).isEmpty() && GAConfig.GT6.PlateDoubleIngot) {
                ModHandler.removeRecipes(OreDictUnifier.get(plate, m));
                ModHandler.addShapedRecipe("ingot_double_" + m.toString(), OreDictUnifier.get(valueOf("ingotDouble"), m), "h", "I", "I", 'I', new UnificationEntry(ingot, m));
                ModHandler.addShapedRecipe("double_ingot_to_plate_" + m.toString(), OreDictUnifier.get(plate, m), "h", "I", 'I', OreDictUnifier.get(valueOf("ingotDouble"), m));
            }
        }

        //Pipes
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(pipeMedium, m).isEmpty() && GAConfig.GT6.BendingPipes) {
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:small_" + m.toString() + "_pipe"));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:medium_" + m.toString() + "_pipe"));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_" + m.toString() + "_pipe"));
                if (!OreDictUnifier.get(valueOf("plateCurved"), m).isEmpty()) {
                    ModHandler.addShapedRecipe("pipe_ga_" + m.toString(), OreDictUnifier.get(pipeMedium, m, 2), "PPP", "wCh", "PPP", 'P', OreDictUnifier.get(valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                    ModHandler.addShapedRecipe("pipe_ga_large_" + m.toString(), OreDictUnifier.get(pipeLarge, m), "PhP", "PCP", "PwP", 'P', OreDictUnifier.get(valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                    ModHandler.addShapedRecipe("pipe_ga_small_" + m.toString(), OreDictUnifier.get(pipeSmall, m, 4), "PwP", "PCP", "PhP", 'P', OreDictUnifier.get(valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                }
            }
        }

        //Rubbers
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Isoprene.getFluid(144), Air.getFluid(2000)).outputs(OreDictUnifier.get(dust, RawRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Isoprene.getFluid(144), Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(dust, RawRubber, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Butadiene.getFluid(108), Styrene.getFluid(36), Air.getFluid(2000)).outputs(OreDictUnifier.get(dust, RawStyreneButadieneRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Butadiene.getFluid(108), Styrene.getFluid(36), Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(dust, RawStyreneButadieneRubber, 3)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Propene.getFluid(2000)).fluidOutputs(Methane.getFluid(1000), Isoprene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(3500).EUt(30).input(dust, Carbon).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Hydrogen.getFluid(4000)).fluidOutputs(Methane.getFluid(5000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Ethylene.getFluid(1000), Propene.getFluid(1000)).fluidOutputs(Hydrogen.getFluid(2000), Isoprene.getFluid(1000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dust, RawStyreneButadieneRubber, 9).input(dust, Sulfur).fluidOutputs(StyreneButadieneRubber.getFluid(1296)).buildAndRegister();

        //Polyphenylene Process
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(30).input(dust, Sodium, 2).input(dust, Sulfur).outputs(OreDictUnifier.get(dust, SodiumSulfide)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(360).input(dust, SodiumSulfide).fluidInputs(Dichlorobenzene.getFluid(1000), Air.getFluid(16000)).outputs(OreDictUnifier.get(dust, Salt, 2)).fluidOutputs(PolyphenyleneSulfide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(360).input(dust, SodiumSulfide).fluidInputs(Dichlorobenzene.getFluid(1000), Oxygen.getFluid(8000)).outputs(OreDictUnifier.get(dust, Salt, 2)).fluidOutputs(PolyphenyleneSulfide.getFluid(1500)).buildAndRegister();

        //Platinum Sludge
        //RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).inputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Chalcopyrite)).fluidInputs(Materials.NitricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.PlatinumGroupSludge)).fluidOutputs(Materials.BlueVitriolSolution.getFluid(9000)).buildAndRegister();
//        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).inputs(OreDictUnifier.get(crushedPurified, Pentlandite)).fluidInputs(NitricAcid.getFluid(1000)).outputs(OreDictUnifier.get(dustTiny, PlatinumGroupSludge)).fluidOutputs(NickelSulfateSolution.getFluid(9000)).buildAndRegister();
//        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(900).EUt(30).input(dust, PlatinumGroupSludge).outputs(OreDictUnifier.get(dust, SiliconDioxide), OreDictUnifier.get(dustTiny, Gold), OreDictUnifier.get(dustTiny, Platinum)).chancedOutput(OreDictUnifier.get(dustTiny, Palladium), 8000, 0).chancedOutput(OreDictUnifier.get(dustTiny, Iridium), 6000, 0).chancedOutput(OreDictUnifier.get(dustTiny, Osmium), 6000, 0).buildAndRegister();

        //Ultimate Pipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(96).inputs(OreDictUnifier.get(pipeSmall, TungstenSteel), MetaItems.ELECTRIC_PUMP_EV.getStackForm()).outputs(OreDictUnifier.get(pipeSmall, Ultimet)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(148).inputs(OreDictUnifier.get(pipeMedium, TungstenSteel), MetaItems.ELECTRIC_PUMP_IV.getStackForm()).outputs(OreDictUnifier.get(pipeMedium, Ultimet)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(256).inputs(OreDictUnifier.get(pipeLarge, TungstenSteel), MetaItems.ELECTRIC_PUMP_IV.getStackForm(2)).outputs(OreDictUnifier.get(pipeLarge, Ultimet)).buildAndRegister();

        //Reinforced Glass
        int multiplier2;
        for (MaterialStack metal1 : firstMetal) {
            IngotMaterial material1 = (IngotMaterial) metal1.material;
            int multiplier1 = (int) metal1.amount;
            for (MaterialStack metal2 : lastMetal) {
                IngotMaterial material2 = (IngotMaterial) metal2.material;
                if ((int) metal1.amount == 1) multiplier2 = 0;
                else multiplier2 = (int) metal2.amount;
                ModHandler.addShapedRecipe("mixed_metal_1_" + material1.toString() + "_" + material2.toString(), MetaItems.INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(plate, material1), 'M', "plateBronze", 'L', OreDictUnifier.get(plate, material2));
                ModHandler.addShapedRecipe("mixed_metal_2_" + material1.toString() + "_" + material2.toString(), MetaItems.INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(plate, material1), 'M', "plateBrass", 'L', OreDictUnifier.get(plate, material2));

                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(plate, material1).input(plank, Bronze).input(plate, material2).outputs(MetaItems.INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2)).buildAndRegister();
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(plate, material1).input(plate, Brass).input(plate, material2).outputs(MetaItems.INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2)).buildAndRegister();
            }
        }

        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(MetaItems.INGOT_MIXED_METAL.getStackForm()).outputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).input(dust, Glass, 3).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(MetaItems.ADVANCED_ALLOY_PLATE.getStackForm(), new ItemStack(Blocks.GLASS, 3)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();

        //Machine Components
        ModHandler.removeRecipes(MetaItems.EMITTER_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.SENSOR_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_IV.getStackForm());

        ModHandler.addShapedRecipe("ga_lv_emitter", MetaItems.EMITTER_LV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Brass), 'S', "circuitBasic", 'C', OreDictUnifier.get(cableGtSingle, Tin), 'G', OreDictUnifier.get(gem, Quartzite));
        ModHandler.addShapedRecipe("ga_mv_emitter", MetaItems.EMITTER_MV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Electrum), 'S', "circuitGood", 'C', OreDictUnifier.get(cableGtSingle, Copper), 'G', OreDictUnifier.get(gem, NetherQuartz));
        ModHandler.addShapedRecipe("ga_hv_emitter", MetaItems.EMITTER_HV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Chrome), 'S', "circuitAdvanced", 'C', OreDictUnifier.get(cableGtSingle, Gold), 'G', OreDictUnifier.get(gem, Emerald));
        ModHandler.addShapedRecipe("ga_ev_emitter", MetaItems.EMITTER_EV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Platinum), 'S', "circuitExtreme", 'C', OreDictUnifier.get(cableGtSingle, Aluminium), 'G', OreDictUnifier.get(gem, EnderPearl));
        ModHandler.addShapedRecipe("ga_iv_emitter", MetaItems.EMITTER_IV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(stick, Osmium), 'S', "circuitElite", 'C', OreDictUnifier.get(cableGtSingle, Tungsten), 'G', OreDictUnifier.get(gem, EnderEye));

        ModHandler.addShapedRecipe("ga_lv_sensor", MetaItems.SENSOR_LV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateSteel", 'G', "gemQuartzite", 'R', "stickBrass", 'S', "circuitBasic");
        ModHandler.addShapedRecipe("ga_mv_sensor", MetaItems.SENSOR_MV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateAluminium", 'G', "gemNetherQuartz", 'R', "stickElectrum", 'S', "circuitGood");
        ModHandler.addShapedRecipe("ga_hv_sensor", MetaItems.SENSOR_HV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateStainlessSteel", 'G', "gemEmerald", 'R', "stickChrome", 'S', "circuitAdvanced");
        ModHandler.addShapedRecipe("ga_ev_sensor", MetaItems.SENSOR_EV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTitanium", 'G', "gemEnderPearl", 'R', "stickPlatinum", 'S', "circuitExtreme");
        ModHandler.addShapedRecipe("ga_iv_sensor", MetaItems.SENSOR_IV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTungstenSteel", 'G', "gemEnderEye", 'R', "stickOsmium", 'S', "circuitElite");

        ModHandler.addShapedRecipe("ga_lv_robot_arm", MetaItems.ROBOT_ARM_LV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Tin), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'R', OreDictUnifier.get(stick, Steel), 'P', MetaItems.ELECTRIC_PISTON_LV.getStackForm(), 'S', "circuitBasic");
        ModHandler.addShapedRecipe("ga_mv_robot_arm", MetaItems.ROBOT_ARM_MV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Copper), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), 'R', OreDictUnifier.get(stick, Aluminium), 'P', MetaItems.ELECTRIC_PISTON_MV.getStackForm(), 'S', "circuitGood");
        ModHandler.addShapedRecipe("ga_hv_robot_arm", MetaItems.ROBOT_ARM_HV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Gold), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'R', OreDictUnifier.get(stick, StainlessSteel), 'P', MetaItems.ELECTRIC_PISTON_HV.getStackForm(), 'S', "circuitAdvanced");
        ModHandler.addShapedRecipe("ga_ev_robot_arm", MetaItems.ROBOT_ARM_EV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), 'R', OreDictUnifier.get(stick, Titanium), 'P', MetaItems.ELECTRIC_PISTON_EV.getStackForm(), 'S', "circuitExtreme");
        ModHandler.addShapedRecipe("ga_iv_robot_arm", MetaItems.ROBOT_ARM_IV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(cableGtSingle, Tungsten), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), 'R', OreDictUnifier.get(stick, TungstenSteel), 'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(), 'S', "circuitElite");

        ModHandler.addShapedRecipe("ga_lv_field_generator", MetaItems.FIELD_GENERATOR_LV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtSingle, Osmium), 'S', "circuitBasic", 'G', OreDictUnifier.get(gem, EnderPearl));
        ModHandler.addShapedRecipe("ga_mv_field_generator", MetaItems.FIELD_GENERATOR_MV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtDouble, Osmium), 'S', "circuitGood", 'G', OreDictUnifier.get(gem, EnderEye));
        ModHandler.addShapedRecipe("ga_hv_field_generator", MetaItems.FIELD_GENERATOR_HV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtQuadruple, Osmium), 'S', "circuitAdvanced", 'G', MetaItems.QUANTUM_EYE.getStackForm());
        ModHandler.addShapedRecipe("ga_ev_field_generator", MetaItems.FIELD_GENERATOR_EV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtOctal, Osmium), 'S', "circuitExtreme", 'G', OreDictUnifier.get(gem, NetherStar));
        ModHandler.addShapedRecipe("iga_v_field_generator", MetaItems.FIELD_GENERATOR_IV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtHex, Osmium), 'S', "circuitElite", 'G', MetaItems.QUANTUM_STAR.getStackForm());

        ModHandler.addShapedRecipe("lv_electric_pump_paper", MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, Paper), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            ModHandler.addShapedRecipe("lv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
            ModHandler.addShapedRecipe("mv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_MV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Bronze), 'R', OreDictUnifier.get(rotor, Bronze), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Steel), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Copper));
            ModHandler.addShapedRecipe("hv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_HV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Steel), 'R', OreDictUnifier.get(rotor, Steel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, StainlessSteel), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Gold));
            ModHandler.addShapedRecipe("ev_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_EV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, StainlessSteel), 'R', OreDictUnifier.get(rotor, StainlessSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Titanium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Aluminium));
            ModHandler.addShapedRecipe("iv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_IV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, TungstenSteel), 'R', OreDictUnifier.get(rotor, TungstenSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, TungstenSteel), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tungsten));
        }

        //Automatic Machine Component Recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(circuit, Tier.Basic, 4), CountableIngredient.from(dust, EnderPearl)).fluidInputs(Osmium.getFluid(288)).outputs(MetaItems.FIELD_GENERATOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).inputs(CountableIngredient.from(circuit, Tier.Good, 4), CountableIngredient.from(dust, EnderEye)).fluidInputs(Osmium.getFluid(576)).outputs(MetaItems.FIELD_GENERATOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).inputs(CountableIngredient.from(circuit, Tier.Advanced, 4), CountableIngredient.from(MetaItems.QUANTUM_EYE.getStackForm())).fluidInputs(Osmium.getFluid(1152)).outputs(MetaItems.FIELD_GENERATOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 4), CountableIngredient.from(dust, NetherStar)).fluidInputs(Osmium.getFluid(2304)).outputs(MetaItems.FIELD_GENERATOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(7680).inputs(CountableIngredient.from(circuit, Tier.Elite, 4), CountableIngredient.from(MetaItems.QUANTUM_STAR.getStackForm())).fluidInputs(Osmium.getFluid(4608)).outputs(MetaItems.FIELD_GENERATOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(cableGtSingle, Tin, 2), CountableIngredient.from(stick, Iron, 2), CountableIngredient.from(stick, IronMagnetic)).fluidInputs(Copper.getFluid(288)).outputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(cableGtSingle, Tin, 2), CountableIngredient.from(stick, Steel, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(288)).outputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(40).inputs(CountableIngredient.from(cableGtSingle, Copper, 2), CountableIngredient.from(stick, Aluminium, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(576)).outputs(MetaItems.ELECTRIC_MOTOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(160).inputs(CountableIngredient.from(cableGtSingle, Gold, 2), CountableIngredient.from(stick, StainlessSteel, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(1152)).outputs(MetaItems.ELECTRIC_MOTOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(640).inputs(CountableIngredient.from(cableGtSingle, Aluminium, 2), CountableIngredient.from(stick, Titanium, 2), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(2304)).outputs(MetaItems.ELECTRIC_MOTOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2560).inputs(CountableIngredient.from(cableGtSingle, Tungsten, 2), CountableIngredient.from(stick, TungstenSteel, 2), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(4608)).outputs(MetaItems.ELECTRIC_MOTOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).inputs(OreDictUnifier.get(cableGtSingle, Tin), MetaItems.ELECTRIC_MOTOR_LV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Copper), MetaItems.ELECTRIC_MOTOR_MV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).inputs(OreDictUnifier.get(cableGtSingle, Gold), MetaItems.ELECTRIC_MOTOR_HV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).inputs(OreDictUnifier.get(cableGtSingle, Aluminium), MetaItems.ELECTRIC_MOTOR_EV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).inputs(OreDictUnifier.get(cableGtSingle, Tungsten), MetaItems.ELECTRIC_MOTOR_IV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).input(circuit, Tier.Basic, 2).inputs(OreDictUnifier.get(cableGtSingle, Tin, 2), OreDictUnifier.get(gem, Quartzite)).fluidInputs(Brass.getFluid(576)).outputs(MetaItems.EMITTER_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).input(circuit, Tier.Good, 2).inputs(OreDictUnifier.get(cableGtSingle, Copper, 2), OreDictUnifier.get(gem, NetherQuartz)).fluidInputs(Electrum.getFluid(576)).outputs(MetaItems.EMITTER_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).input(circuit, Tier.Advanced, 2).inputs(OreDictUnifier.get(cableGtSingle, Gold, 2), OreDictUnifier.get(gem, Emerald)).fluidInputs(Chrome.getFluid(576)).outputs(MetaItems.EMITTER_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).input(circuit, MarkerMaterials.Tier.Extreme, 2).inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 2), OreDictUnifier.get(gem, EnderPearl)).fluidInputs(Platinum.getFluid(576)).outputs(MetaItems.EMITTER_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).input(circuit, Tier.Elite, 2).inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 2), OreDictUnifier.get(gem, EnderEye)).fluidInputs(Osmium.getFluid(576)).outputs(MetaItems.EMITTER_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(pipeMedium, Bronze), OreDictUnifier.get(screw, Tin)).inputs(CountableIngredient.from(rotor, Tin, 1), CountableIngredient.from(ring, Paper, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(pipeMedium, Bronze), OreDictUnifier.get(screw, Tin)).inputs(CountableIngredient.from(rotor, Tin, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(40).inputs(MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), OreDictUnifier.get(cableGtSingle, Copper), OreDictUnifier.get(pipeMedium, Steel), OreDictUnifier.get(screw, Bronze)).inputs(CountableIngredient.from(rotor, Bronze, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_MV.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(160).inputs(MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), OreDictUnifier.get(cableGtSingle, Gold), OreDictUnifier.get(pipeMedium, StainlessSteel), OreDictUnifier.get(screw, Steel)).inputs(CountableIngredient.from(rotor, Steel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_HV.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(640).inputs(MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), OreDictUnifier.get(cableGtSingle, Aluminium), OreDictUnifier.get(pipeMedium, Titanium), OreDictUnifier.get(screw, StainlessSteel)).inputs(CountableIngredient.from(rotor, StainlessSteel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_EV.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2560).inputs(MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tungsten), OreDictUnifier.get(pipeMedium, TungstenSteel), OreDictUnifier.get(screw, TungstenSteel)).inputs(CountableIngredient.from(rotor, TungstenSteel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(MetaItems.ELECTRIC_PUMP_IV.getStackForm()).buildAndRegister();
        }



        //Pyrolise Oven Recipes
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(0).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(Creosote.getFluid(4000)).duration(440).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(1).fluidInputs(Nitrogen.getFluid(400)).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(Creosote.getFluid(4000)).duration(200).EUt(96).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(2).outputs(OreDictUnifier.get(dust, Ash, 4)).fluidOutputs(OilHeavy.getFluid(200)).duration(280).EUt(192).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(3).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodVinegar.getFluid(3000)).duration(640).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(4).fluidInputs(Nitrogen.getFluid(400)).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodVinegar.getFluid(3000)).duration(320).EUt(96).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(5).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodGas.getFluid(1500)).duration(640).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(6).fluidInputs(Nitrogen.getFluid(400)).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodGas.getFluid(1500)).duration(320).EUt(96).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(7).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodTar.getFluid(1500)).duration(640).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(8).fluidInputs(Nitrogen.getFluid(400)).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(WoodTar.getFluid(1500)).duration(320).EUt(96).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().input(log, Wood, 16).circuitMeta(9).fluidInputs(Nitrogen.getFluid(400)).outputs(new ItemStack(Items.COAL, 20, 1)).fluidOutputs(CharcoalByproducts.getFluid(4000)).duration(320).EUt(96).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(1).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(640).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(2).fluidInputs(Nitrogen.getFluid(400)).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(320).EUt(96).buildAndRegister();

        //Chemical Reactor Cracking
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Ethane.getFluid(1000)).fluidOutputs(HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Ethylene.getFluid(1000)).fluidOutputs(HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Propene.getFluid(1000)).fluidOutputs(HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Propane.getFluid(1000)).fluidOutputs(HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), LightFuel.getFluid(1000)).fluidOutputs(HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Butane.getFluid(1000)).fluidOutputs(HydroCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Naphtha.getFluid(1000)).fluidOutputs(HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), HeavyFuel.getFluid(1000)).fluidOutputs(HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Gas.getFluid(1000)).fluidOutputs(HydroCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Butene.getFluid(1000)).fluidOutputs(HydroCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Hydrogen.getFluid(2000), Butadiene.getFluid(1000)).fluidOutputs(HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Ethane.getFluid(1000)).fluidOutputs(SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Ethylene.getFluid(1000)).fluidOutputs(SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Propene.getFluid(1000)).fluidOutputs(SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Propane.getFluid(1000)).fluidOutputs(SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), LightFuel.getFluid(1000)).fluidOutputs(CrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Butane.getFluid(1000)).fluidOutputs(SteamCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Naphtha.getFluid(1000)).fluidOutputs(SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), HeavyFuel.getFluid(1000)).fluidOutputs(CrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Gas.getFluid(1000)).fluidOutputs(SteamCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Butene.getFluid(1000)).fluidOutputs(SteamCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Steam.getFluid(2000), Butadiene.getFluid(1000)).fluidOutputs(SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Distillation Recipes
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(FishOil.getFluid(24)).fluidOutputs(Lubricant.getFluid(12)).buildAndRegister();

        //Fluid Heater Recipes
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(Acetone.getFluid(100)).fluidOutputs(Ethenone.getFluid(100)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(CalciumAcetate.getFluid(200)).fluidOutputs(Acetone.getFluid(200)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(24).circuitMeta(1).fluidInputs(RawGrowthMedium.getFluid(500)).fluidOutputs(SterileGrowthMedium.getFluid(500)).buildAndRegister();

        //Fermenter Recipe
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(150).EUt(2).fluidInputs(Biomass.getFluid(100)).fluidOutputs(FermentedBiomass.getFluid(100)).buildAndRegister();

        //Oil Extractor Recipes
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH)).fluidOutputs(FishOil.getFluid(40)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 1)).fluidOutputs(FishOil.getFluid(60)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 2)).fluidOutputs(FishOil.getFluid(70)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 3)).fluidOutputs(FishOil.getFluid(30)).buildAndRegister();

        //Misc Blast Furnace Recipes
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Pentlandite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Garnierite), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Pyrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, BandedIron), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Galena).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Massicot), OreDictUnifier.get(nugget, Lead, 6)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Stibnite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, AntimonyTrioxide), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Sphalerite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, Zincite), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Cobaltite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CobaltOxide), OreDictUnifier.get(dust, ArsenicTrioxide)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Tetrahedrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CupricOxide), OreDictUnifier.get(dustTiny, AntimonyTrioxide, 3)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(dust, Chalcopyrite).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, CupricOxide), OreDictUnifier.get(dust, Ferrosilite)).fluidOutputs(SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, AntimonyTrioxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Antimony, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(3000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CobaltOxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Cobalt, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, ArsenicTrioxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Arsenic, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CupricOxide, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Copper, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Garnierite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Nickel, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BandedIron, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Massicot, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Lead, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, SiliconDioxide).input(dust, Carbon, 2).outputs(OreDictUnifier.get(ingot, Silicon), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(CarbonMonoxde.getFluid(2000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Malachite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Copper, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(3000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Magnetite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, GraniticMineralSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BrownLimonite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, YellowLimonite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, BasalticMineralSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, Cassiterite, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Tin, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, CassiteriteSand, 2).input(dust, Carbon).outputs(OreDictUnifier.get(ingot, Tin, 3), OreDictUnifier.get(dustTiny, Ash, 2)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(dust, SiliconDioxide).input(dust, Carbon, 2).outputs(OreDictUnifier.get(ingot, Silicon), OreDictUnifier.get(dustTiny, Ash)).fluidOutputs(CarbonMonoxde.getFluid(2000)).buildAndRegister();

        for (MaterialStack ore : ironOres) {
            Material materials = ore.material;
            RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(dust, Calcite).outputs(OreDictUnifier.get(ingot, Iron, 3), OreDictUnifier.get(dustSmall, DarkAsh)).buildAndRegister();
            RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(dustTiny, Quicklime, 3).outputs(OreDictUnifier.get(ingot, Iron, 2), OreDictUnifier.get(dustSmall, DarkAsh)).buildAndRegister();
        }

        //Misc Centrifuging
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(192).fluidInputs(LeadZincSolution.getFluid(8000)).outputs(OreDictUnifier.get(dust, Lead), OreDictUnifier.get(dust, Silver), OreDictUnifier.get(dust, Zinc), OreDictUnifier.get(dust, Sulfur, 3)).fluidOutputs(Water.getFluid(2000)).buildAndRegister();

        //Mince Meat Recipes
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.PORKCHOP)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.BEEF)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.RABBIT)).outputs(OreDictUnifier.get(dustSmall, Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.CHICKEN)).outputs(OreDictUnifier.get(dust, Meat)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.MUTTON)).outputs(OreDictUnifier.get(dust, Meat)).buildAndRegister();

        //Ash-Related Recipes
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6).input(dust, DarkAsh).outputs(OreDictUnifier.get(dust, Ash), OreDictUnifier.get(dust, Carbon)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30).input(dust, Ash).chancedOutput(OreDictUnifier.get(dustSmall, Quicklime, 2), 9900, 0).chancedOutput(OreDictUnifier.get(dustSmall, Potash), 6400, 0).chancedOutput(OreDictUnifier.get(dustSmall, Magnesia), 6000, 0).chancedOutput(OreDictUnifier.get(dustSmall, PhosphorousPentoxide), 500, 0).chancedOutput(OreDictUnifier.get(dustSmall, SodaAsh), 5000, 0).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(dust, Quicklime).fluidInputs(CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(dust, Calcite)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(dust, Magnesia).fluidInputs(CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(dust, Magnesite)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(dust, Calcite).notConsumable(new IntCircuitIngredient(1)).outputs(OreDictUnifier.get(dust, Quicklime)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(dust, Magnesite).outputs(OreDictUnifier.get(dust, Magnesia)).fluidOutputs(CarbonDioxide.getFluid(1000)).buildAndRegister();

        ModHandler.addShapedRecipe("assline_casing", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING, 2), "PhP", "AFA", "PwP", 'P', "plateSteel", 'A', MetaItems.ROBOT_ARM_IV.getStackForm(), 'F', OreDictUnifier.get(frameGt, TungstenSteel));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_assembler_casing"));
        ModHandler.addShapedRecipe("ga_assmbler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING, 3), "CCC", "CFC", "CMC", 'C', "circuitElite", 'F', "frameGtTungstenSteel", 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());

        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR, 4), OreDictUnifier.get(dust, Meat), OreDictUnifier.get(dustTiny, Salt)).fluidInputs(DistilledWater.getFluid(4000)).fluidOutputs(RawGrowthMedium.getFluid(4000)).buildAndRegister();


        //add missing casing and component
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .outputs(OreDictUnifier.get(dust, EglinSteelBase, 10)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, EglinSteelBase, 10)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .outputs(OreDictUnifier.get(dust, EglinSteel, 13)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Titanium, 9)
                .input(dust, Carbon, 9)
                .input(dust, Lithium, 9)
                .input(dust, Sulfur, 9)
                .fluidInputs(Hydrogen.getFluid(500), Potassium.getFluid(144 * 9))
                .outputs(OreDictUnifier.get(dust, Grisium, 48)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .fluidInputs(Nickel.getFluid(144 * 3))
                .outputs(OreDictUnifier.get(dust, Inconel625, 43)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Steel, 16)
                .input(dust, Molybdenum, 1)
                .input(dust, Titanium, 1)
                .input(dust, Cobalt, 2)
                .fluidInputs(Nickel.getFluid(144 * 4))
                .outputs(OreDictUnifier.get(dust, MaragingSteel250, 24)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Lead, 2)
                .input(dust, Bronze, 2)
                .input(dust, Tin, 1)
                .outputs(OreDictUnifier.get(dust, Potin, 5)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Uranium, 9)
                .input(dust, Titanium, 1)
                .outputs(OreDictUnifier.get(dust, Staballoy, 10)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Yttrium, 2)
                .input(dust, Molybdenum, 4)
                .input(dust, Chrome, 2)
                .input(dust, Titanium, 2)
                .fluidInputs(Nickel.getFluid(144 * 15))
                .outputs(OreDictUnifier.get(dust, HastelloyN, 25)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Gold, 7)
                .input(dust, Copper, 3)
                .outputs(OreDictUnifier.get(dust, Tumbaga, 10)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Cobalt, 9)
                .input(dust, Chrome, 9)
                .input(dust, Manganese, 5)
                .input(dust, Titanium, 2)
                .outputs(OreDictUnifier.get(dust, Stellite, 25)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Phosphorus, 2)
                .input(dust, Molybdenum, 1)
                .outputs(OreDictUnifier.get(dust, Talonite, 10)).buildAndRegister();

        //Pyrotheum
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .input(dust, Blaze, 2)
                .outputs(OreDictUnifier.get(dust, Pyrotheum, 2)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Pyrotheum)
                .fluidOutputs(Pyrotheum.getFluid(250)).buildAndRegister();

        //Cryotheum
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Snow)
                .fluidInputs(Redstone.getFluid(250))
                .outputs(OreDictUnifier.get(dust, Blize, 2)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Snow)
                .input(dust, Blize, 2)
                .outputs(OreDictUnifier.get(dust, Cryotheum, 2)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Cryotheum)
                .fluidOutputs(Cryotheum.getFluid(250)).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(HastelloyN.getFluid(144 * 4)).input(valueOf("gtMetalCasing"), Staballoy, 2).inputs(CountableIngredient.from(circuit, Tier.Extreme)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER, 2)).duration(600).EUt(8000).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().input(valueOf("gtMetalCasing"), Steel, 1).fluidInputs(Polytetrafluoroethylene.getFluid(216)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT, 1)).duration(100).EUt(8000).buildAndRegister();

        //Circuit Rabbit Hole - Layer 1
        ModHandler.removeRecipes(MetaItems.BASIC_CIRCUIT_LV.getStackForm());
        ModHandler.addShapedRecipe("gautils:basic_circuit", MetaItems.BASIC_CIRCUIT_LV.getStackForm(), "RPR", "TBT", "CCC", 'R', MetaItems.RESISTOR, 'P', "plateSteel", 'T', MetaItems.VACUUM_TUBE, 'B', GAMetaItems.BASIC_BOARD, 'C', new UnificationEntry(cableGtSingle, RedAlloy));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:good_circuit"));
        ModHandler.addShapedRecipe("gautils:good_circuit", GAMetaItems.GOOD_CIRCUIT.getStackForm(), "WPW", "CBC", "DCD", 'P', "plateSteel", 'C', MetaItems.BASIC_CIRCUIT_LV.getStackForm(), 'W', OreDictUnifier.get(wireGtSingle, Copper), 'D', MetaItems.DIODE.getStackForm(), 'B', GAMetaItems.GOOD_PHENOLIC_BOARD);

        for (MaterialStack stack : solderingList) {
            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).inputs(GAMetaItems.BASIC_BOARD.getStackForm(), MetaItems.RESISTOR.getStackForm(2), OreDictUnifier.get(wireGtSingle, RedAlloy, 2), MetaItems.VACUUM_TUBE.getStackForm(2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.BASIC_CIRCUIT_LV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).inputs(GAMetaItems.BASIC_BOARD.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), OreDictUnifier.get(wireGtSingle, RedAlloy, 2), MetaItems.VACUUM_TUBE.getStackForm(2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.BASIC_CIRCUIT_LV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30).inputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_CIRCUIT_LV.getStackForm(2), MetaItems.DIODE.getStackForm(2)).input(wireGtSingle, Copper, 2).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30).inputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_CIRCUIT_LV.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(2)).input(wireGtSingle, Copper, 2).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.BASIC_BOARD.getStackForm(), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(), MetaItems.RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.BASIC_BOARD.getStackForm(), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(4), MetaItems.RESISTOR.getStackForm(4), MetaItems.CAPACITOR.getStackForm(4), MetaItems.TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(4), MetaItems.SMD_RESISTOR.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(2), MetaItems.RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(2), MetaItems.SMD_RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.RESISTOR.getStackForm(2), MetaItems.CAPACITOR.getStackForm(2), MetaItems.TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_MV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_MV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.ADVANCED_CIRCUIT_MV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm(2), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(3), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(), MetaItems.TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm(2), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(3), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(), MetaItems.SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(80).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(600).inputs(GAMetaItems.ADVANCED_BOARD.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Electrum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.NANO_PROCESSOR_HV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600).inputs(GAMetaItems.ADVANCED_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Electrum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.NANO_PROCESSOR_HV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(2), MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm(2), MetaItems.DIODE.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(2), MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.ADVANCED_BOARD.getStackForm(), MetaItems.NANO_PROCESSOR_HV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.NANO_PROCESSOR_ASSEMBLY_EV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2400).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(), MetaItems.QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Platinum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.QUANTUM_PROCESSOR_EV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(38400).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(), MetaItems.ADVANCED_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Platinum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.QUANTUM_PROCESSOR_EV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(frameGt, Aluminium), GAMetaItems.INTEGRATED_COMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.CAPACITOR.getStackForm(24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtSingle, AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(frameGt, Aluminium), GAMetaItems.INTEGRATED_COMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtSingle, AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.ADVANCED_BOARD.getStackForm(2), MetaItems.NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.NANO_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(), MetaItems.QUANTUM_PROCESSOR_EV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Platinum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(9600).inputs(GAMetaItems.ELITE_BOARD.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(153600).inputs(GAMetaItems.ELITE_BOARD.getStackForm(), MetaItems.CRYSTAL_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(1920).inputs(OreDictUnifier.get(frameGt, Aluminium), GAMetaItems.NANO_COMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtSingle, AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.NANO_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(2), MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Platinum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.QUANTUM_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.ELITE_BOARD.getStackForm(), MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, NiobiumTitanium, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_FLOW_CIRCUIT_LUV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(38400).inputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.WETWARE_PROCESSOR_LUV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(7680).inputs(OreDictUnifier.get(frameGt, Aluminium), GAMetaItems.QUANTUM_COMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtSingle, AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.QUANTUM_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.ELITE_BOARD.getStackForm(2), MetaItems.ENERGY_FLOW_CIRCUIT_LUV.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, NiobiumTitanium, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.CRYSTAL_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.MASTER_BOARD.getStackForm(), MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(30720).inputs(OreDictUnifier.get(frameGt, Aluminium), GAMetaItems.CRYSTAL_COMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.CRYSTAL_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.MASTER_BOARD.getStackForm(2), MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(512).EUt(1024).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(), MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4), MetaItems.ENGRAVED_LAPOTRON_CHIP.getStackForm(18), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), OreDictUnifier.get(wireFine, Platinum, 16)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1024).EUt(4096).inputs(GAMetaItems.EXTREME_BOARD.getStackForm(), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4), MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm(8), MetaItems.QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(), OreDictUnifier.get(wireFine, Platinum, 16)).input(plate, Europium, 4).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(), MetaItems.NAND_MEMORY_CHIP.getStackForm(32), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 8)).input(plate, Plastic, 4).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_STICK.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(1200).inputs(GAMetaItems.ADVANCED_BOARD.getStackForm(), MetaItems.NANO_PROCESSOR_HV.getStackForm(), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(32), MetaItems.NAND_MEMORY_CHIP.getStackForm(64), OreDictUnifier.get(wireFine, Platinum, 32)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_ORB.getStackForm()).buildAndRegister();
        }
        //Circuit Rabbit Hole - Layer 2
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480).inputs(GAMetaItems.ELITE_BOARD.getStackForm(), GAMetaItems.PETRI_DISH.getStackForm(), MetaItems.ELECTRIC_PUMP_LV.getStackForm(), MetaItems.SENSOR_LV.getStackForm()).input(circuit, Tier.Good).fluidInputs(SterileGrowthMedium.getFluid(250)).outputs(MetaItems.WETWARE_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(PositiveMatter.getFluid(10), NeutralMatter.getFluid(10)).fluidOutputs(UUMatter.getFluid(20)).buildAndRegister();

        ModHandler.removeRecipes(MetaItems.COATED_BOARD.getStackForm(3));
        ModHandler.addShapedRecipe("gautils:coated_board_shaped", MetaItems.COATED_BOARD.getStackForm(3), "RRR", "BBB", "RRR", 'R', MetaItems.RUBBER_DROP, 'B', "plateWood");
        ModHandler.addShapelessRecipe("gautils:coated_board_shapeless", MetaItems.COATED_BOARD.getStackForm(), MetaItems.RUBBER_DROP, MetaItems.RUBBER_DROP, "plateWood");
        ModHandler.addShapedRecipe("gautils:basic_board", GAMetaItems.BASIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Copper), 'B', MetaItems.COATED_BOARD);
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(20).input(plate, Wood).input(foil, Copper, 4).fluidInputs(Glue.getFluid(72)).outputs(GAMetaItems.BASIC_BOARD.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("gautils:good_board", GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Gold), 'B', MetaItems.PHENOLIC_BOARD);
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(MetaItems.PHENOLIC_BOARD.getStackForm()).input(foil, Gold, 4).fluidInputs(SodiumPersulfate.getFluid(200)).outputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(MetaItems.PHENOLIC_BOARD.getStackForm()).input(foil, Gold, 4).fluidInputs(IronChloride.getFluid(100)).outputs(GAMetaItems.GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(MetaItems.PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(SodiumPersulfate.getFluid(500)).outputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(MetaItems.PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(IronChloride.getFluid(250)).outputs(GAMetaItems.GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(MetaItems.EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(SodiumPersulfate.getFluid(1000)).outputs(GAMetaItems.ADVANCED_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(MetaItems.EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(IronChloride.getFluid(500)).outputs(GAMetaItems.ADVANCED_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(MetaItems.FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(SodiumPersulfate.getFluid(2000)).outputs(GAMetaItems.EXTREME_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(MetaItems.FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(IronChloride.getFluid(1000)).outputs(GAMetaItems.EXTREME_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MetaItems.MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(SodiumPersulfate.getFluid(4000)).outputs(GAMetaItems.ELITE_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MetaItems.MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(IronChloride.getFluid(2000)).outputs(GAMetaItems.ELITE_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(MetaItems.WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(SodiumPersulfate.getFluid(10000)).outputs(GAMetaItems.MASTER_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(MetaItems.WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(IronChloride.getFluid(5000)).outputs(GAMetaItems.MASTER_BOARD.getStackForm()).buildAndRegister();

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diode"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(MetaItems.DIODE.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(MetaItems.DIODE.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(MetaItems.DIODE.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(MetaItems.DIODE.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(MetaItems.SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(MetaItems.DIODE.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(MetaItems.SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(MetaItems.DIODE.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(MetaItems.SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(MetaItems.DIODE.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(MetaItems.SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(MetaItems.DIODE.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).input(dust, GalliumArsenide).fluidInputs(Plastic.getFluid(288)).outputs(MetaItems.SMD_DIODE.getStackForm(32)).buildAndRegister();

        ModHandler.removeRecipes(MetaItems.RESISTOR.getStackForm(3));
        for (Material m : new Material[]{Coal, Charcoal, Carbon}) {
            ModHandler.addShapedRecipe(String.format("gautils:resistor_%s", m.toString()), MetaItems.RESISTOR.getStackForm(), "RWR", "CMC", " W ", 'M', new UnificationEntry(dust, m), 'R', MetaItems.RUBBER_DROP, 'W', "wireFineCopper", 'C', "wireGtSingleCopper");
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, Copper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(MetaItems.RESISTOR.getStackForm(8)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, AnnealedCopper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(MetaItems.RESISTOR.getStackForm(8)).buildAndRegister();
        }

        //Cutting Machine Recipes
        for (MaterialStack stack : sawLubricants) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(420).inputs(MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).fluidInputs(material.getFluid(2 * multiplier)).outputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(2)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polystyrene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polytetrafluoroethylene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Emerald).fluidInputs(Helium.getFluid(1000)).outputs(MetaItems.ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Olivine).fluidInputs(Helium.getFluid(1000)).outputs(MetaItems.ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(1024).inputs(new ItemStack(Items.EGG)).chancedOutput(GAMetaItems.STEM_CELLS.getStackForm(), 500, 750).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(30).input(dust, Iron).fluidInputs(HydrochloricAcid.getFluid(2000)).fluidOutputs(IronChloride.getFluid(3000), Hydrogen.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(), MetaItems.CARBON_FIBERS.getStackForm(16)).fluidInputs(Glowstone.getFluid(576)).outputs(MetaItems.NANO_CENTRAL_PROCESSING_UNIT_WAFER.getStackForm()).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Europium.getFluid(16)).chancedOutput(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(), 1000, 750).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Europium.getFluid(16)).chancedOutput(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(), 1000, 750).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(dust, Carbon).fluidInputs(Lutetium.getFluid(1)).chancedOutput(MetaItems.CARBON_FIBERS.getStackForm(2), 3333, 750).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000).inputs(MetaItems.ENGRAVED_CRYSTAL_CHIP.getStackForm()).notConsumable(craftingLens, MarkerMaterials.Color.Lime).outputs(MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR, 4), OreDictUnifier.get(dust, Meat), OreDictUnifier.get(dustTiny, Salt)).fluidInputs(DistilledWater.getFluid(4000)).fluidOutputs(RawGrowthMedium.getFluid(4000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).blastFurnaceTemp(1784).input(dust, Silicon, 32).input(dustSmall, GalliumArsenide).outputs(MetaItems.SILICON_BOULE.getStackForm()).buildAndRegister();


    }


    public static void init2() {
        //Fuel Rabbit Hole - Layer 1
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(BioDiesel.getFluid(1000), Tetranitromethane.getFluid(40)).fluidOutputs(NitroFuel.getFluid(750)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(Fuel.getFluid(1000), Tetranitromethane.getFluid(20)).fluidOutputs(NitroFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1000).EUt(388).notConsumable(new IntCircuitIngredient(1)).fluidInputs(NitrogenDioxide.getFluid(1000), Hydrogen.getFluid(3000), Oxygen.getFluid(500)).fluidOutputs(Water.getFluid(4000), RocketFuel.getFluid(3000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(16).fluidInputs(Oxygen.getFluid(1000), Dimethylhydrazine.getFluid(1000)).fluidOutputs(RocketFuel.getFluid(3000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(16).fluidInputs(DinitrogenTetroxide.getFluid(1000), Dimethylhydrazine.getFluid(1000)).fluidOutputs(RocketFuel.getFluid(6000)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(Butane.getFluid(320)).fluidOutputs(LPG.getFluid(370)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(Propane.getFluid(320)).fluidOutputs(LPG.getFluid(290)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(16).EUt(120).fluidInputs(LightFuel.getFluid(5000), HeavyFuel.getFluid(1000)).fluidOutputs(Fuel.getFluid(6000)).buildAndRegister();

        //Fuel Rabbit Hole - Layer 2
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120).fluidInputs(NitricAcid.getFluid(8000), Ethenone.getFluid(1000)).fluidOutputs(Tetranitromethane.getFluid(2000), Water.getFluid(9000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Oxygen.getFluid(7000), Ammonia.getFluid(2000)).fluidOutputs(DinitrogenTetroxide.getFluid(1000), Water.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(NitrogenDioxide.getFluid(2000)).fluidOutputs(DinitrogenTetroxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1100).EUt(480).notConsumable(new IntCircuitIngredient(23)).fluidInputs(Oxygen.getFluid(7000), Nitrogen.getFluid(2000), Hydrogen.getFluid(6000)).fluidOutputs(DinitrogenTetroxide.getFluid(1000), Water.getFluid(3000)).buildAndRegister();

        //Fuel Rabbit Hole - Layer 3
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Oxygen.getFluid(4000), Ammonia.getFluid(1000)).fluidOutputs(NitricAcid.getFluid(1000), Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).notConsumable(new IntCircuitIngredient(4)).fluidInputs(Water.getFluid(1000), Oxygen.getFluid(1000), NitrogenDioxide.getFluid(2000)).fluidOutputs(NitricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480).notConsumable(new IntCircuitIngredient(24)).fluidInputs(Oxygen.getFluid(4000), Nitrogen.getFluid(1000), Hydrogen.getFluid(3000)).fluidOutputs(NitricAcid.getFluid(1000), Water.getFluid(1000)).buildAndRegister();

        //Assline Recipes
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, HSSG, 2), OreDictUnifier.get(ring, HSSG, 4), OreDictUnifier.get(valueOf("round"), HSSG, 16), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm()).duration(600).EUt(10240).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, HSSE, 2), OreDictUnifier.get(ring, HSSE, 4), OreDictUnifier.get(valueOf("round"), HSSE, 16), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(cableGtQuadruple, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm()).duration(600).EUt(40960).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(block, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, Neutronium, 2), OreDictUnifier.get(ring, Neutronium, 4), OreDictUnifier.get(valueOf("round"), Neutronium, 16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(cableGtQuadruple, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm()).duration(600).EUt(163840).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(pipeSmall, Ultimet, 2), OreDictUnifier.get(screw, HSSG, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(pipeMedium, Ultimet, 2), OreDictUnifier.get(screw, HSSE, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(pipeLarge, Ultimet, 2), OreDictUnifier.get(screw, Neutronium, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gear, HSSG, 4), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(StyreneButadieneRubber.getFluid(1440), Lubricant.getFluid(250)).outputs(MetaItems.CONVEYOR_MODULE_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gear, HSSE, 4), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(750)).outputs(MetaItems.CONVEYOR_MODULE_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2), OreDictUnifier.get(plate, Neutronium, 8), OreDictUnifier.get(gear, Neutronium, 4), OreDictUnifier.get(stick, Neutronium, 4), OreDictUnifier.get(ingot, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(2000)).outputs(MetaItems.CONVEYOR_MODULE_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gearSmall, HSSG, 8), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(MetaItems.ELECTRIC_PISTON_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gearSmall, HSSE, 8), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(MetaItems.ELECTRIC_PISTON_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(plate, Neutronium, 8), OreDictUnifier.get(gearSmall, Neutronium, 8), OreDictUnifier.get(stick, Neutronium, 4), OreDictUnifier.get(ingot, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(MetaItems.ELECTRIC_PISTON_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, YttriumBariumCuprate, 16), OreDictUnifier.get(screw, HSSG, 16), OreDictUnifier.get(stick, HSSG, 16), OreDictUnifier.get(ingot, HSSG), MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2), MetaItems.ELECTRIC_PISTON_LUV.getStackForm()).input(circuit, Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576), Lubricant.getFluid(250)).outputs(MetaItems.ROBOT_ARM_LUV.getStackForm()).duration(600).EUt(20480).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, VanadiumGallium, 16), OreDictUnifier.get(screw, HSSE, 16), OreDictUnifier.get(stick, HSSE, 16), OreDictUnifier.get(ingot, HSSE), MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2), MetaItems.ELECTRIC_PISTON_ZPM.getStackForm()).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(1152), Lubricant.getFluid(750)).outputs(MetaItems.ROBOT_ARM_ZPM.getStackForm()).duration(600).EUt(81920).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, NiobiumTitanium, 16), OreDictUnifier.get(screw, Neutronium, 16), OreDictUnifier.get(stick, Neutronium, 16), OreDictUnifier.get(ingot, Neutronium), MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2), MetaItems.ELECTRIC_PISTON_UV.getStackForm()).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(2304), Lubricant.getFluid(2000)).outputs(MetaItems.ROBOT_ARM_UV.getStackForm()).duration(600).EUt(327680).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), MetaItems.EMITTER_IV.getStackForm(2), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.EMITTER_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), MetaItems.EMITTER_LUV.getStackForm(2), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, VanadiumGallium, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.EMITTER_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), MetaItems.EMITTER_ZPM.getStackForm(2), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(wireGtDouble, NiobiumTitanium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.EMITTER_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), MetaItems.SENSOR_IV.getStackForm(2), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.SENSOR_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), MetaItems.SENSOR_LUV.getStackForm(2), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, VanadiumGallium, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.SENSOR_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), MetaItems.SENSOR_ZPM.getStackForm(2), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(wireGtDouble, NiobiumTitanium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.SENSOR_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(PositiveMatter.getFluid(10), NeutralMatter.getFluid(10)).fluidOutputs(UUMatter.getFluid(20)).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), MetaItems.QUANTUM_STAR.getStackForm(), MetaItems.EMITTER_LUV.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Master, 16).fluidInputs(SolderingAlloy.getFluid(576)).outputs(MetaItems.FIELD_GENERATOR_LUV.getStackForm()).duration(600).EUt(30720).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), MetaItems.QUANTUM_STAR.getStackForm(), MetaItems.EMITTER_ZPM.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Ultimate, 16).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).duration(600).EUt(245760).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), MetaItems.GRAVI_STAR.getStackForm(), MetaItems.EMITTER_UV.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Superconductor, 16).fluidInputs(SolderingAlloy.getFluid(2304)).outputs(MetaItems.FIELD_GENERATOR_UV.getStackForm()).duration(600).EUt(491520).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Tritanium, 4), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(8), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(32), MetaItems.SMD_RESISTOR.getStackForm(32), MetaItems.SMD_TRANSISTOR.getStackForm(32), MetaItems.SMD_DIODE.getStackForm(32), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 16), OreDictUnifier.get(foil, SiliconeRubber, 64)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(10000)).outputs(MetaItems.WETWARE_MAINFRAME_MAX.getStackForm()).duration(2000).EUt(300000).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaItems.WETWARE_BOARD.getStackForm(), GAMetaItems.STEM_CELLS.getStackForm(8), MetaItems.GLASS_TUBE.getStackForm(8), OreDictUnifier.get(foil, SiliconeRubber, 64)).input(plate, Gold, 8).input(plate, StainlessSteel, 4).fluidInputs(SterileGrowthMedium.getFluid(100), UUMatter.getFluid(20), DistilledWater.getFluid(4000)).outputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(8)).duration(200).EUt(20000).buildAndRegister();

        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe recipe : RecipeMaps.ASSEMBLER_RECIPES.getRecipeList()) {
            if (recipe.getOutputs().get(0).isItemEqual(MetaItems.WETWARE_PROCESSOR_LUV.getStackForm()) || recipe.getOutputs().get(0).isItemEqual(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())) {
                recipes.add(recipe);
            }
        }
        recipes.forEach(recipe -> RecipeMaps.ASSEMBLER_RECIPES.removeRecipe(recipe));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(28000).inputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2)).input(wireFine, YttriumBariumCuprate, 2).fluidInputs(SolderingAlloy.getFluid(72)).outputs(MetaItems.WETWARE_PROCESSOR_LUV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(28000).inputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2)).input(wireFine, YttriumBariumCuprate, 2).fluidInputs(Tin.getFluid(144)).outputs(MetaItems.WETWARE_PROCESSOR_LUV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30000).inputs(MetaItems.WETWARE_BOARD.getStackForm(), MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4)).input(wireFine, YttriumBariumCuprate, 6).fluidInputs(SolderingAlloy.getFluid(72)).outputs(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30000).inputs(MetaItems.WETWARE_BOARD.getStackForm(), MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4)).input(wireFine, YttriumBariumCuprate, 6).fluidInputs(Tin.getFluid(144)).outputs(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm()).buildAndRegister();

        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();

        if (GAConfig.GT5U.enableZPMandUVBats) {
            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Europium, 16), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8), MetaItems.FIELD_GENERATOR_LUV.getStackForm(2), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), MetaItems.SMD_DIODE.getStackForm(8), OreDictUnifier.get(cableGtSingle, Naquadah, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(8000)).outputs(GAMetaItems.ENERGY_MODULE.getStackForm()).duration(2000).EUt(100000).buildAndRegister();

            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Americium, 16), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(), GAMetaItems.ENERGY_MODULE.getStackForm(8), MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.SMD_DIODE.getStackForm(16), OreDictUnifier.get(cableGtSingle, NaquadahAlloy, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(GAMetaItems.ENERGY_CLUSTER.getStackForm()).duration(2000).EUt(200000).buildAndRegister();

            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), GAMetaItems.ENERGY_CLUSTER.getStackForm(8), MetaItems.FIELD_GENERATOR_UV.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000), Naquadria.getFluid(1152)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();
        } else
            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8), MetaItems.FIELD_GENERATOR_UV.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), MetaItems.SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), OreDictUnifier.get(plate, Plutonium241), OreDictUnifier.get(plate, NetherStar), MetaItems.FIELD_GENERATOR_IV.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[0].getStackForm()).duration(1000).EUt(30000).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), OreDictUnifier.get(plate, Europium, 4), MetaItems.FIELD_GENERATOR_LUV.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(48), OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 32)).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[1].getStackForm()).duration(1000).EUt(60000).buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(), OreDictUnifier.get(plate, Americium, 4), MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2), MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[2].getStackForm()).duration(1000).EUt(90000).buildAndRegister();

        //Star Recipes
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60000).EUt(8).input(ingot, Plutonium, 3).outputs(OreDictUnifier.get(dust, Plutonium, 3)).fluidOutputs(Radon.getFluid(50)).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680).inputs(new ItemStack(Items.NETHER_STAR)).fluidInputs(Neutronium.getFluid(288)).outputs(MetaItems.GRAVI_STAR.getStackForm()).buildAndRegister();

        //Fusion Recipes
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Deuterium.getFluid(125), Tritium.getFluid(125)).fluidOutputs(Helium.getPlasma(125)).duration(16).EUt(4096).EUToStart(40000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Deuterium.getFluid(125), Helium3.getFluid(125)).fluidOutputs(Helium.getPlasma(125)).duration(16).EUt(2048).EUToStart(60000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Carbon.getFluid(125), Helium3.getFluid(125)).fluidOutputs(Oxygen.getPlasma(125)).duration(32).EUt(4096).EUToStart(80000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Beryllium.getFluid(16), Deuterium.getFluid(375)).fluidOutputs(Nitrogen.getPlasma(175)).duration(16).EUt(16384).EUToStart(180000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Silicon.getFluid(16), Magnesium.getFluid(16)).fluidOutputs(Iron.getPlasma(125)).duration(32).EUt(8192).EUToStart(360000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Potassium.getFluid(16), Fluorine.getFluid(125)).fluidOutputs(Nickel.getPlasma(125)).duration(16).EUt(32768).EUToStart(480000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Beryllium.getFluid(16), Tungsten.getFluid(16)).fluidOutputs(Platinum.getFluid(16)).duration(32).EUt(32768).EUToStart(150000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Neodymium.getFluid(16), Hydrogen.getFluid(48)).fluidOutputs(Europium.getFluid(16)).duration(64).EUt(24576).EUToStart(150000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Lutetium.getFluid(16), Chrome.getFluid(16)).fluidOutputs(Americium.getFluid(16)).duration(96).EUt(49152).EUToStart(200000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Plutonium.getFluid(16), Thorium.getFluid(16)).fluidOutputs(Naquadah.getFluid(16)).duration(64).EUt(32768).EUToStart(300000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(PositiveMatter.getFluid(1), NeutralMatter.getFluid(1)).fluidOutputs(Neutronium.getFluid(2)).duration(200).EUt(98304).EUToStart(600000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Tungsten.getFluid(16), Helium.getFluid(16)).fluidOutputs(Osmium.getFluid(16)).duration(64).EUt(24578).EUToStart(150000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Manganese.getFluid(16), Hydrogen.getFluid(16)).fluidOutputs(Iron.getFluid(16)).duration(64).EUt(8192).EUToStart(120000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Mercury.getFluid(16), Magnesium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Aluminium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Uranium.getFluid(16), Helium.getFluid(16)).fluidOutputs(Plutonium.getFluid(16)).duration(128).EUt(49152).EUToStart(480000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Vanadium.getFluid(16), Hydrogen.getFluid(125)).fluidOutputs(Chrome.getFluid(16)).duration(64).EUt(24576).EUToStart(140000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Gallium.getFluid(16), Radon.getFluid(125)).fluidOutputs(Duranium.getFluid(16)).duration(64).EUt(16384).EUToStart(140000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Titanium.getFluid(48), Duranium.getFluid(32)).fluidOutputs(Tritanium.getFluid(16)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Mercury.getFluid(16)).fluidOutputs(Radon.getFluid(125)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Tantalum.getFluid(16), Tritium.getFluid(16)).fluidOutputs(Tungsten.getFluid(16)).duration(16).EUt(24576).EUToStart(200000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Silver.getFluid(16), Lithium.getFluid(16)).fluidOutputs(Indium.getFluid(16)).duration(32).EUt(24576).EUToStart(380000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(NaquadahEnriched.getFluid(15), Radon.getFluid(125)).fluidOutputs(Naquadria.getFluid(3)).duration(64).EUt(49152).EUToStart(400000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Lithium.getFluid(16), Tungsten.getFluid(16)).fluidOutputs(Iridium.getFluid(16)).duration(32).EUt(32768).EUToStart(300000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Lanthanum.getFluid(16), Silicon.getFluid(16)).fluidOutputs(Lutetium.getFluid(16)).duration(16).EUt(8192).EUToStart(80000000).buildAndRegister();

        //FUsion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING), "PhP", "PHP", "PwP", 'P', "plateTungstenSteel", 'H', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV));
        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2), "PhP", "PHP", "PwP", 'P', "plateAmericium", 'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).input(plate, Americium, 6).outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2)).duration(50).buildAndRegister();

        ModHandler.addShapedRecipe("fusion_coil", MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), "CRC", "FSF", "CRC", 'C', "circuitMaster", 'R', MetaItems.NEUTRON_REFLECTOR.getStackForm(), 'F', MetaItems.FIELD_GENERATOR_MV.getStackForm(), 'S', MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(4000).EUt(120).inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm()).input(plate, Beryllium, 30).input(plate, TungstenCarbide, 3).fluidInputs(TinAlloy.getFluid(13824)).outputs(MetaItems.NEUTRON_REFLECTOR.getStackForm()).buildAndRegister();

        //Explosive Recipes
        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));
        ModHandler.removeRecipes(MetaItems.DYNAMITE.getStackForm());
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.PAPER), new ItemStack(Items.STRING)).fluidInputs(Glyceryl.getFluid(500)).outputs(MetaItems.DYNAMITE.getStackForm()).buildAndRegister();

        //Electromagnetic Separator Recipes
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, BrownLimonite).outputs(OreDictUnifier.get(dust, BrownLimonite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, YellowLimonite).outputs(OreDictUnifier.get(dust, YellowLimonite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Nickel).outputs(OreDictUnifier.get(dust, Nickel)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Pentlandite).outputs(OreDictUnifier.get(dust, Pentlandite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, BandedIron).outputs(OreDictUnifier.get(dust, BandedIron)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Ilmenite).outputs(OreDictUnifier.get(dust, Ilmenite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Pyrite).outputs(OreDictUnifier.get(dust, Pyrite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Tin).outputs(OreDictUnifier.get(dust, Tin)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Chromite).outputs(OreDictUnifier.get(dust, Chromite)).chancedOutput(OreDictUnifier.get(dustSmall, Iron), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Iron), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Monazite).outputs(OreDictUnifier.get(dust, Monazite)).chancedOutput(OreDictUnifier.get(dustSmall, Neodymium), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Neodymium), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Bastnasite).outputs(OreDictUnifier.get(dust, Bastnasite)).chancedOutput(OreDictUnifier.get(dustSmall, Neodymium), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Neodymium), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, VanadiumMagnetite).outputs(OreDictUnifier.get(dust, VanadiumMagnetite)).chancedOutput(OreDictUnifier.get(dustSmall, Gold), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Gold), 2000, 0).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(dustPure, Magnetite).outputs(OreDictUnifier.get(dust, Magnetite)).chancedOutput(OreDictUnifier.get(dustSmall, Gold), 4000, 0).chancedOutput(OreDictUnifier.get(nugget, Gold), 2000, 0).buildAndRegister();

        //Lapotron Crystal Recipes
        for (MaterialStack m : lapisLike) {
            GemMaterial gem = (GemMaterial) m.material;
            ModHandler.addShapedRecipe("lapotron_crystal_shaped" + gem.toString(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), "PCP", "RFR", "PCP", 'P', new UnificationEntry(plate, gem), 'C', "circuitAdvanced", 'R', OreDictUnifier.get(stick, gem), 'F', OreDictUnifier.get(gemFlawless, Sapphire));
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), OreDictUnifier.get(gemExquisite, Sapphire), OreDictUnifier.get(stick, gem), MetaItems.CAPACITOR.getStackForm());
        }

        //Add Missing Superconducter Wire Tiering Recipes
        ModHandler.addShapelessRecipe("superonducter_wire_gtsingle_doubling", OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor), OreDictUnifier.get(wireGtSingle, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_doubling", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_doubling", OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_doubling", OreDictUnifier.get(wireGtHex, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_splitting", OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 2), OreDictUnifier.get(wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_splitting", OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 2), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_splitting", OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 2), OreDictUnifier.get(wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gthex_splitting", OreDictUnifier.get(wireGtOctal, Tier.Superconductor, 2), OreDictUnifier.get(wireGtHex, Tier.Superconductor));

        //Dust Packing
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(dust, m).isEmpty() && GAConfig.Misc.PackagerDustRecipes) {
                RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustSmall, m, 4).notConsumable(GAMetaItems.SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, m)).buildAndRegister();
                RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustTiny, m, 9).notConsumable(GAMetaItems.SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, m)).buildAndRegister();
            }
        }

        //Schematic Recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4).input(circuit, Tier.Good, 4).input(plate, StainlessSteel, 2).outputs(GAMetaItems.SCHEMATIC.getStackForm()).buildAndRegister();
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_1"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_c"));

        //Configuration Circuit
        //ModHandler.removeRecipes(MetaItems.BASIC_CIRCUIT_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.INTEGRATED_CIRCUIT.getStackForm());
        ModHandler.addShapelessRecipe("basic_to_configurable_circuit", MetaItems.INTEGRATED_CIRCUIT.getStackForm(), "circuitBasic");

        //MAX Machine Hull
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_max"));
        ModHandler.addShapedRecipe("ga_casing_max", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Neutronium));
        ModHandler.addShapedRecipe("ga_hull_max", MetaTileEntities.HULL[GTValues.MAX].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), 'C', new UnificationEntry(wireGtSingle, Tier.Superconductor), 'H', new UnificationEntry(plate, Neutronium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).input(plate, Neutronium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).input(wireGtSingle, Tier.Superconductor, 2).fluidInputs(Polytetrafluoroethylene.getFluid(288)).outputs(MetaTileEntities.HULL[9].getStackForm()).buildAndRegister();

        OreDictionary.getOres("treeLeaves").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

        List<ItemStack> allSaplings = OreDictionary.getOres("treeSapling").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

        //Biomass Process
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Water.getFluid(180)).fluidOutputs(Biomass.getFluid(180)).buildAndRegister();
        for (ItemStack stack : allSaplings)
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(800).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Water.getFluid(100)).fluidOutputs(Biomass.getFluid(100)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Water.getFluid(20)).fluidOutputs(Biomass.getFluid(20)).buildAndRegister();

        for (ItemStack stack : allSaplings)
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GTUtility.copyAmount(8, stack)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.POTATO, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.CARROT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.CACTUS, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.REEDS, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.RED_MUSHROOM, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.BEETROOT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();

        //Redstone and glowstone melting
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(dust, Redstone).fluidOutputs(Redstone.getFluid(144)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(dust, Glowstone).fluidOutputs(Glowstone.getFluid(144)).buildAndRegister();

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
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(dust, NetherQuartz).outputs(OreDictUnifier.get(plate, NetherQuartz)).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(dust, CertusQuartz).outputs(OreDictUnifier.get(plate, CertusQuartz)).buildAndRegister();

        //Dust Uncrafting Fixes
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(dustSmall, m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(dustSmall, m));
                ModHandler.addShapedRecipe("dust_small_" + m.toString(), OreDictUnifier.get(dustSmall, m, 4), " D", "  ", 'D', new UnificationEntry(dust, m));
            }
        }

        ModHandler.addShapedRecipe("3x3_schematic", GAMetaItems.SCHEMATIC_3X3.getStackForm(), "  d", " S ", "   ", 'S', GAMetaItems.SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("2x2_schematic", GAMetaItems.SCHEMATIC_2X2.getStackForm(), " d ", " S ", "   ", 'S', GAMetaItems.SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("dust_schematic", GAMetaItems.SCHEMATIC_DUST.getStackForm(), "   ", " S ", "  d", 'S', GAMetaItems.SCHEMATIC.getStackForm());

        //Improved Superconductor recipes
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(120).input(dust, Cadmium, 5).input(dust, Magnesium).fluidInputs(Oxygen.getFluid(6000)).outputs(OreDictUnifier.get(dust, MVSuperconductorBase, 12)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(2400).EUt(120).input(dust, Titanium).input(dust, Barium, 9).input(dust, Copper, 10).fluidInputs(Oxygen.getFluid(20000)).outputs(OreDictUnifier.get(dust, HVSuperconductorBase, 40)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(400).EUt(480).input(dust, Uranium).input(dust, Platinum, 3).outputs(OreDictUnifier.get(dust, EVSuperconductorBase, 4)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(400).EUt(480).input(dust, Vanadium).input(dust, Indium, 3).outputs(OreDictUnifier.get(dust, IVSuperconductorBase, 4)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(2400).EUt(1920).input(dust, Indium, 4).input(dust, Bronze, 8).input(dust, Barium, 2).input(dust, Titanium).fluidInputs(Oxygen.getFluid(14000)).outputs(OreDictUnifier.get(dust, LuVSuperconductorBase, 29)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(1920).input(dust, Naquadah, 4).input(dust, Indium, 2).input(dust, Palladium, 6).input(dust, Osmium).outputs(OreDictUnifier.get(dust, ZPMSuperconductorBase, 13)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Lead, 3).input(dust, Platinum).input(dust, EnderPearl, 4).outputs(OreDictUnifier.get(dust, Enderium, 4)).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120).inputs(OreDictUnifier.get(wireGtSingle, MVSuperconductorBase, 3), OreDictUnifier.get(pipeTiny, StainlessSteel, 2), MetaItems.ELECTRIC_PUMP_MV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, MVSuperconductor, 3)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256).inputs(OreDictUnifier.get(wireGtSingle, HVSuperconductorBase, 3), OreDictUnifier.get(pipeTiny, Titanium, 2), MetaItems.ELECTRIC_PUMP_HV.getStackForm()).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, HVSuperconductor, 3)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(OreDictUnifier.get(wireGtSingle, EVSuperconductorBase, 9), OreDictUnifier.get(pipeTiny, TungstenSteel, 6), MetaItems.ELECTRIC_PUMP_EV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, EVSuperconductor, 9)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920).inputs(OreDictUnifier.get(wireGtSingle, IVSuperconductorBase, 6), OreDictUnifier.get(pipeTiny, NiobiumTitanium, 4), MetaItems.ELECTRIC_PUMP_IV.getStackForm()).fluidInputs(Nitrogen.getFluid(4000)).outputs(OreDictUnifier.get(wireGtSingle, IVSuperconductor, 6)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680).inputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductorBase, 8), OreDictUnifier.get(pipeTiny, Enderium, 5), MetaItems.ELECTRIC_PUMP_LUV.getStackForm()).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 8)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720).inputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductorBase, 16), OreDictUnifier.get(pipeTiny, Naquadah, 6), MetaItems.ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(8000)).outputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductor, 16)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880).inputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductorBase, 32), OreDictUnifier.get(pipeTiny, Neutronium, 7), MetaItems.ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(10000)).outputs(OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).buildAndRegister();

        //GTNH Coils
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Mica, 3).input(dust, RawRubber, 2).outputs(OreDictUnifier.get(dust, MicaPulp, 4)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Mica, 3).inputs(MetaItems.RUBBER_DROP.getStackForm()).outputs(OreDictUnifier.get(dust, MicaPulp, 4)).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, Sapphire).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, GreenSapphire).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30).input(dust, Ruby).input(dust, SiliconDioxide).outputs(OreDictUnifier.get(dust, AluminoSilicateWool, 2)).buildAndRegister();

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(28).input(dust, MicaPulp, 4).input(dust, Asbestos).outputs(GAMetaItems.MICA_SHEET.getStackForm(4)).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(30).inputs(GAMetaItems.MICA_SHEET.getStackForm(4)).input(dust, SiliconDioxide).outputs(GAMetaItems.MICA_INSULATOR_SHEET.getStackForm(4)).buildAndRegister();
        if (GAConfig.GT6.BendingFoilsAutomatic && GAConfig.GT6.BendingCylinders)
            GARecipeMaps.CLUSTER_MILL_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(GAMetaItems.MICA_INSULATOR_SHEET.getStackForm()).outputs(GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(4)).buildAndRegister();
        else if (!GAConfig.GT6.BendingFoilsAutomatic || !GAConfig.GT6.BendingCylinders)
            RecipeMaps.BENDER_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(GAMetaItems.MICA_INSULATOR_SHEET.getStackForm()).circuitMeta(1).outputs(GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(4)).buildAndRegister();

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_cupronickel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_kanthal"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_nichrome"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_tungstensteel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_hss_g"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_naquadah"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_naquadah_alloy"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_cupronickel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_kanthal"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_nichrome"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_tungstensteel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_hss_g"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_naquadah"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_naquadah_alloy"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_superconductor"));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, Cupronickel, 8), OreDictUnifier.get(dust, AluminoSilicateWool, 12)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, Cupronickel, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30).inputs(OreDictUnifier.get(wireGtDouble, Kanthal, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Copper.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120).inputs(OreDictUnifier.get(wireGtDouble, Nichrome, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Aluminium.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(OreDictUnifier.get(wireGtDouble, TungstenSteel, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Nichrome.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TUNGSTENSTEEL)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(OreDictUnifier.get(wireGtDouble, HSSG, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tungsten.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096).inputs(OreDictUnifier.get(wireGtDouble, Naquadah, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(HSSG.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680).inputs(OreDictUnifier.get(wireGtDouble, NaquadahAlloy, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Naquadah.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH_ALLOY)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).inputs(OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 8), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(NaquadahAlloy.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).inputs(OreDictUnifier.get(wireGtDouble, LuVSuperconductor, 32), GAMetaItems.MICA_INSULATOR_FOIL.getStackForm(16)).fluidInputs(NaquadahAlloy.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();


        //Nuclear react recipe
        GARecipeMaps.BOILING_THORIUM_REACTOR_RECIPES.recipeBuilder().duration(2000).EUt(480).input(stick, Thorium, 1).outputs(OreDictUnifier.get(dustTiny, Thorium, 3), OreDictUnifier.get(dustTiny, Uranium, 2), OreDictUnifier.get(dustTiny, Uranium235, 1)).buildAndRegister();
        GARecipeMaps.BOILING_URANIUM_REACTOR_RECIPES.recipeBuilder().duration(4000).EUt(480).input(stick, Uranium235, 1).outputs(OreDictUnifier.get(dustTiny, Plutonium, 3), OreDictUnifier.get(dustTiny, Plutonium241, 1)).buildAndRegister();
        GARecipeMaps.BOILING_PLUTONIUM_REACTOR_RECIPES.recipeBuilder().duration(6000).EUt(480).input(stick, Plutonium241, 1).outputs(OreDictUnifier.get(dustTiny, Naquadah, 3), OreDictUnifier.get(dustTiny, Americium, 1)).buildAndRegister();

    }

    public static void forestrySupport() {
        //Distillation Support
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Fluids.SEED_OIL.getFluid(24)).fluidOutputs(Lubricant.getFluid(12)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(WoodVinegar.getFluid(1000)).fluidOutputs(AceticAcid.getFluid(100), Water.getFluid(500), Fluids.BIO_ETHANOL.getFluid(10), Methanol.getFluid(300), Acetone.getFluid(50), MethylAcetate.getFluid(10)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(FermentedBiomass.getFluid(1000)).fluidOutputs(AceticAcid.getFluid(25), Water.getFluid(375), Fluids.BIO_ETHANOL.getFluid(150), Methanol.getFluid(150), Ammonia.getFluid(100), CarbonDioxide.getFluid(400), Methane.getFluid(600)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(32).EUt(400).fluidInputs(Biomass.getFluid(1000)).outputs(OreDictUnifier.get(dustSmall, Wood, 2)).fluidOutputs(Fluids.BIO_ETHANOL.getFluid(600), Water.getFluid(300)).buildAndRegister();
        } else {
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(SeedOil.getFluid(24)).fluidOutputs(Lubricant.getFluid(12)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(WoodVinegar.getFluid(1000)).fluidOutputs(AceticAcid.getFluid(100), Water.getFluid(500), Ethanol.getFluid(10), Methanol.getFluid(300), Acetone.getFluid(50), MethylAcetate.getFluid(10)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(FermentedBiomass.getFluid(1000)).fluidOutputs(AceticAcid.getFluid(25), Water.getFluid(375), Ethanol.getFluid(150), Methanol.getFluid(150), Ammonia.getFluid(100), CarbonDioxide.getFluid(400), Methane.getFluid(600)).buildAndRegister();
            RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(32).EUt(400).fluidInputs(Biomass.getFluid(1000)).outputs(OreDictUnifier.get(dustSmall, Wood, 2)).fluidOutputs(Ethanol.getFluid(600), Water.getFluid(300)).buildAndRegister();
        }

        //Extracting Seed Oil
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.WHEAT_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.MELON_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.PUMPKIN_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
        } else {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.WHEAT_SEEDS)).fluidOutputs(SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.MELON_SEEDS)).fluidOutputs(SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.PUMPKIN_SEEDS)).fluidOutputs(SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.BEETROOT_SEEDS)).fluidOutputs(SeedOil.getFluid(10)).buildAndRegister();
        }

        //Making BioDiesel
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(Fluids.SEED_OIL.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(Fluids.SEED_OIL.getFluid(6000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
        } else {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(SeedOil.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(SeedOil.getFluid(6000), Ethanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Methanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(dustTiny, SodiumHydroxide).fluidInputs(FishOil.getFluid(6000), Ethanol.getFluid(1000)).fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000)).buildAndRegister();
        }

        //Lube Mixer Recipes
        for (MaterialStack lubeDust : lubeDusts) {
            DustMaterial dust = (DustMaterial) lubeDust.material;
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Oil.getFluid(750)).fluidOutputs(Lubricant.getFluid(750)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Creosote.getFluid(750)).fluidOutputs(Lubricant.getFluid(750)).buildAndRegister();
            if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
                RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Fluids.SEED_OIL.getFluid(750)).fluidOutputs(Lubricant.getFluid(750)).buildAndRegister();
            else
                RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(SeedOil.getFluid(750)).fluidOutputs(Lubricant.getFluid(750)).buildAndRegister();
        }

        List<ItemStack> allSaplings = OreDictionary.getOres("treeSapling").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

        //Biomass
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(2880).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Fluids.FOR_HONEY.getFluid(180)).fluidOutputs(Biomass.getFluid(270)).buildAndRegister();
            for (ItemStack stack : allSaplings)
                RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1200).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.FOR_HONEY.getFluid(100)).fluidOutputs(Biomass.getFluid(150)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        }

        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(2880).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Fluids.JUICE.getFluid(180)).fluidOutputs(Biomass.getFluid(270)).buildAndRegister();
            for (ItemStack stack : allSaplings)
                RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1200).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.JUICE.getFluid(100)).fluidOutputs(Biomass.getFluid(150)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Biomass.getFluid(30)).buildAndRegister();
        }

        //Making Ethylene
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(SulfuricAcid.getFluid(1000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(Ethylene.getFluid(1000), DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();

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
                        if (GAConfig.GT5U.GenerateCompressorRecipes)
                            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !GAMetaItems.hasPrefix(recipe.getRecipeOutput(), "dust", "dustTiny") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager3x3Recipes) {
                        RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(GAMetaItems.SCHEMATIC_3X3.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !GAMetaItems.hasPrefix(recipe.getRecipeOutput(), "dust", "dustSmall") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager2x2Recipes) {
                        RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(GAMetaItems.SCHEMATIC_2X2.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9 && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.AIR && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.SLIME_BLOCK) {
                boolean isIngot = false;
                for (int i : OreDictionary.getOreIDs(recipe.getRecipeOutput())) {
                    if (OreDictionary.getOreName(i).startsWith("ingot")) {
                        isIngot = true;
                        break;
                    }
                }
                if (GAConfig.GT5U.RemoveBlockUncraftingRecipes) recipesToRemove.add(recipe.getRegistryName());
                if (!isIngot) {
                    RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9) {
                if (!recipesToRemove.contains(recipe.getRegistryName()) && GAConfig.Misc.Unpackager3x3Recipes) {
                    RecipeMaps.UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).inputs(new CountableIngredient(new IntCircuitIngredient(1), 0)).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
        }

        for (ResourceLocation r : recipesToRemove)
            ModHandler.removeRecipeByName(r);
        recipesToRemove.clear();

        if (GAConfig.GT5U.GenerateCompressorRecipes) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:quartz_block"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_nether_quartz"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_nether_quartz"));
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(block, NetherQuartz)).outputs(OreDictUnifier.get(gem, NetherQuartz, 4)).buildAndRegister();
        }

        //Generate Plank Recipes
        for (IRecipe recipe : CraftingManager.REGISTRY) {
            if (recipe.getRecipeOutput().isEmpty()) continue;
            for (int i : OreDictionary.getOreIDs(recipe.getRecipeOutput())) {
                if (OreDictionary.getOreName(i).equals("plankWood") && recipe.getIngredients().size() == 1 && recipe.getRecipeOutput().getCount() == 4) {
                    if (GAConfig.GT5U.GeneratedSawingRecipes) {
                        ModHandler.removeRecipeByName(recipe.getRegistryName());
                        ModHandler.addShapelessRecipe("log_to_4_" + recipe.getRecipeOutput().toString(), GTUtility.copyAmount(4, recipe.getRecipeOutput()), recipe.getIngredients().get(0).getMatchingStacks()[0], ToolDictNames.craftingToolSaw);
                        ModHandler.addShapelessRecipe("log_to_2_" + recipe.getRecipeOutput().toString(), GTUtility.copyAmount(2, recipe.getRecipeOutput()), recipe.getIngredients().get(0).getMatchingStacks()[0]);
                    }
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).fluidInputs(Lubricant.getFluid(1)).outputs(GTUtility.copyAmount(6, recipe.getRecipeOutput()), OreDictUnifier.get(dust, Wood, 2)).buildAndRegister();
                }
                if (OreDictionary.getOreName(i).equals("slabWood") && recipe.getRecipeOutput().getCount() == 6) {
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(50).EUt(4).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(GTUtility.copyAmount(2, recipe.getRecipeOutput())).buildAndRegister();
                }
            }
        }

        //Disable Wood To Charcoal Recipes
        List<ItemStack> allWoodLogs = OreDictionary.getOres("logWood").stream().flatMap(stack -> ModHandler.getAllSubItems(stack).stream()).collect(Collectors.toList());

        for (ItemStack stack : allWoodLogs) {
            ItemStack smeltingOutput = ModHandler.getSmeltingOutput(stack);
            if (!smeltingOutput.isEmpty() && smeltingOutput.getItem() == Items.COAL && smeltingOutput.getMetadata() == 1 && GAConfig.GT5U.DisableLogToCharcoalSmeltg) {
                ItemStack woodStack = stack.copy();
                woodStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
                ModHandler.removeFurnaceSmelting(woodStack);
            }
        }


    }

}
