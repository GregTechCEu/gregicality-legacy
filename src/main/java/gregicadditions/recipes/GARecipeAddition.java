package gregicadditions.recipes;

import forestry.core.ModuleCore;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.armor.PowerlessJetpack;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.ToolDictNames;
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
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.GATransparentCasing.CasingType.REINFORCED_GLASS;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

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

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(32).fluidInputs(Redstone.getFluid(144 * 3), Copper.getFluid(144)).fluidOutputs(RedAlloy.getFluid(144)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Redstone.getFluid(144 * 2)).inputs(CountableIngredient.from(ingot, Copper)).outputs(OreDictUnifier.get(ingot, RedAlloy)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Redstone.getFluid(144)).inputs(CountableIngredient.from(ingot, AnnealedCopper)).outputs(OreDictUnifier.get(ingot, RedAlloy)).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().fluidInputs(Glass.getFluid(144)).notConsumable(SHAPE_MOLD_BALL.getStackForm()).outputs(GLASS_TUBE.getStackForm()).EUt(16).duration(80).buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.GLOWSTONE_DUST, 4)).outputs(new ItemStack(Blocks.GLOWSTONE)).EUt(16).duration(40).buildAndRegister();

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
        ModHandler.removeFurnaceSmelting(MetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_block_to_dust"));
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());
        COMPRESSOR_RECIPES.recipeBuilder().input(dust, Fireclay).outputs(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm()).duration(100).EUt(2).buildAndRegister();
        ModHandler.addShapedRecipe("quartz_sand", OreDictUnifier.get(dust, GAMaterials.QuartzSand), "S", "m", 'S', "sand");
        MACERATOR_RECIPES.recipeBuilder().duration(200).EUt(8).input("sand", 1).outputs(OreDictUnifier.get(dust, GAMaterials.QuartzSand)).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2500, 500).chancedOutput(OreDictUnifier.get(dust, GAMaterials.QuartzSand), 2000, 500).buildAndRegister();
        ModHandler.addShapelessRecipe("glass_dust_ga", OreDictUnifier.get(dust, Glass), "dustSand", "dustFlint");
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(8).input(dust, Flint).input(dust, GAMaterials.QuartzSand, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(160).EUt(8).input(dust, Flint).input(dust, Quartzite, 4).outputs(OreDictUnifier.get(dust, Glass, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(16).input(dust, Calcite, 2).input(dust, Stone).input(dust, Clay).input(dust, GAMaterials.QuartzSand).fluidInputs(Water.getFluid(2000)).fluidOutputs(Concrete.getFluid(2304)).buildAndRegister();


        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), RUBBER_DROP.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));
        ModHandler.addShapelessRecipe("harder_bone_meal", new ItemStack(Items.DYE, 3, 15), new ItemStack(Items.BONE), ToolDictNames.craftingToolMortar);
        FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(16).EUt(10).buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(300).EUt(2).buildAndRegister();

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
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(ring, m).isEmpty() && !OreDictUnifier.get(stick, m).isEmpty() && m != Rubber && m != StyreneButadieneRubber && m != SiliconeRubber && GAConfig.GT6.BendingRings && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(ring, m));
                ModHandler.addShapedRecipe("tod_to_ring_" + m.toString(), OreDictUnifier.get(ring, m), "hS", " C", 'S', OreDictUnifier.get(stick, m), 'C', "craftingToolBendingCylinderSmall");
            }
            if (!OreDictUnifier.get(valueOf("plateCurved"), m).isEmpty() && GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
                ModHandler.addShapedRecipe("curved_plate_" + m.toString(), OreDictUnifier.get(valueOf("plateCurved"), m), "h", "P", "C", 'P', new UnificationEntry(plate, m), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("flatten_plate_" + m.toString(), OreDictUnifier.get(plate, m), "h", "C", 'C', new UnificationEntry(valueOf("plateCurved"), m));
                BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(plate, m).circuitMeta(0).outputs(OreDictUnifier.get(valueOf("plateCurved"), m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(rotor, m).isEmpty() && GAConfig.GT6.BendingRotors && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(rotor, m));
                ModHandler.addShapedRecipe("ga_rotor_" + m.toString(), OreDictUnifier.get(rotor, m), "ChC", "SRf", "CdC", 'C', OreDictUnifier.get(valueOf("plateCurved"), m), 'S', OreDictUnifier.get(screw, m), 'R', OreDictUnifier.get(ring, m));
                ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24).inputs(OreDictUnifier.get(valueOf("plateCurved"), m, 4), OreDictUnifier.get(ring, m)).fluidInputs(SolderingAlloy.getFluid(32)).outputs(OreDictUnifier.get(rotor, m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(foil, m).isEmpty()) {
                if (GAConfig.GT6.BendingFoils && GAConfig.GT6.BendingCylinders) {
                    ModHandler.addShapedRecipe("foil_" + m.toString(), OreDictUnifier.get(foil, m, 2), "hPC", 'P', new UnificationEntry(plate, m), 'C', "craftingToolBendingCylinder");
                }
                if (GAConfig.GT6.BendingFoilsAutomatic && GAConfig.GT6.BendingCylinders) {
                    CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(plate, m).outputs(OreDictUnifier.get(foil, m, 4)).buildAndRegister();
                } else if (GAConfig.GT6.BendingFoilsAutomatic == false || GAConfig.GT6.BendingCylinders == false) {
                    BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).circuitMeta(4).input(plate, m).outputs(OreDictUnifier.get(foil, m, 4)).buildAndRegister();
                }
            }
            if (!OreDictUnifier.get(wireGtSingle, m).isEmpty() && !OreDictUnifier.get(wireFine, m).isEmpty() && GAConfig.GT5U.OldFineWireRecipes && GAConfig.GT6.BendingCylinders) {
                WIREMILL_RECIPES.recipeBuilder().EUt(9).duration(200).inputs(OreDictUnifier.get(wireGtSingle, m)).outputs(OreDictUnifier.get(wireFine, m, 4)).buildAndRegister();
            }

            if (!OreDictUnifier.get(valueOf("round"), m).isEmpty()) {
                ModHandler.addShapedRecipe("round" + m.toString(), OreDictUnifier.get(valueOf("round"), m), "fN", "N ", 'N', OreDictUnifier.get(nugget, m));
                LATHE_RECIPES.recipeBuilder().EUt(8).duration(100).inputs(OreDictUnifier.get(nugget, m)).outputs(OreDictUnifier.get(valueOf("round"), m)).buildAndRegister();
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
                        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                        for (MaterialStack stackDust : cableDusts) {
                            Material dust = stackDust.material;
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                        }
                    } else
                        // EV+ tiered superconductors cable recipe
                        if (tieredSuperconductors.contains(m)) {
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                            for (MaterialStack stackDust : cableDusts) {
                                Material dust = stackDust.material;
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                            }
                        } else
                            // EV+ tiered cables recipe
                            if (tieredCables.contains(m)) {
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                for (MaterialStack stackDust : cableDusts) {
                                    Material dust = stackDust.material;
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(foil, PolyphenyleneSulfide), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 2), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 4), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 8), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(foil, PolyphenyleneSulfide, 16), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                }
                            } else
                            // Low-tier cable recipes
                            {
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
                                for (MaterialStack stackDust : cableDusts) {
                                    Material dust = stackDust.material;
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtSingle, m), OreDictUnifier.get(dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(cableGtSingle, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, m), OreDictUnifier.get(dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(cableGtDouble, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtQuadruple, m), OreDictUnifier.get(dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(cableGtQuadruple, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtOctal, m), OreDictUnifier.get(dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(cableGtOctal, m)).buildAndRegister();
                                    ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(wireGtHex, m), OreDictUnifier.get(dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(cableGtHex, m)).buildAndRegister();
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

        //Ultimate Pipes
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(96).inputs(OreDictUnifier.get(pipeSmall, TungstenSteel), ELECTRIC_PUMP_EV.getStackForm()).outputs(OreDictUnifier.get(pipeSmall, Ultimet)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(148).inputs(OreDictUnifier.get(pipeMedium, TungstenSteel), ELECTRIC_PUMP_IV.getStackForm()).outputs(OreDictUnifier.get(pipeMedium, Ultimet)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(256).inputs(OreDictUnifier.get(pipeLarge, TungstenSteel), ELECTRIC_PUMP_IV.getStackForm(2)).outputs(OreDictUnifier.get(pipeLarge, Ultimet)).buildAndRegister();

        //Reinforced Glass
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:ingot_mixed_metal"));
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

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(ADVANCED_ALLOY_PLATE.getStackForm()).input(dust, Glass, 3).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS, 4)).buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(ADVANCED_ALLOY_PLATE.getStackForm(), new ItemStack(Blocks.GLASS, 3)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS, 4)).buildAndRegister();

        //Machine Components
        ModHandler.removeRecipes(EMITTER_LV.getStackForm());
        ModHandler.removeRecipes(EMITTER_MV.getStackForm());
        ModHandler.removeRecipes(EMITTER_HV.getStackForm());
        ModHandler.removeRecipes(EMITTER_EV.getStackForm());
        ModHandler.removeRecipes(EMITTER_IV.getStackForm());

        ModHandler.removeRecipes(SENSOR_LV.getStackForm());
        ModHandler.removeRecipes(SENSOR_MV.getStackForm());
        ModHandler.removeRecipes(SENSOR_HV.getStackForm());
        ModHandler.removeRecipes(SENSOR_EV.getStackForm());
        ModHandler.removeRecipes(SENSOR_IV.getStackForm());

        ModHandler.removeRecipes(ROBOT_ARM_LV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_MV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_HV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_EV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_IV.getStackForm());

        ModHandler.removeRecipes(FIELD_GENERATOR_LV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_MV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_HV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_EV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_IV.getStackForm());

        ModHandler.removeRecipes(ELECTRIC_PUMP_LV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_MV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_HV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_EV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_IV.getStackForm());

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
        ModHandler.addShapedRecipe("iga_v_field_generator", FIELD_GENERATOR_IV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(wireGtHex, Osmium), 'S', "circuitElite", 'G', QUANTUM_STAR.getStackForm());

        ModHandler.addShapedRecipe("lv_electric_pump_paper", ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, Paper), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            ModHandler.addShapedRecipe("lv_electric_pump_" + m.toString(), ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Tin), 'R', OreDictUnifier.get(rotor, Tin), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tin));
            ModHandler.addShapedRecipe("mv_electric_pump_" + m.toString(), ELECTRIC_PUMP_MV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Bronze), 'R', OreDictUnifier.get(rotor, Bronze), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Steel), 'M', ELECTRIC_MOTOR_MV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Copper));
            ModHandler.addShapedRecipe("hv_electric_pump_" + m.toString(), ELECTRIC_PUMP_HV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, Steel), 'R', OreDictUnifier.get(rotor, Steel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, StainlessSteel), 'M', ELECTRIC_MOTOR_HV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Gold));
            ModHandler.addShapedRecipe("ev_electric_pump_" + m.toString(), ELECTRIC_PUMP_EV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, StainlessSteel), 'R', OreDictUnifier.get(rotor, StainlessSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, Titanium), 'M', ELECTRIC_MOTOR_EV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Aluminium));
            ModHandler.addShapedRecipe("iv_electric_pump_" + m.toString(), ELECTRIC_PUMP_IV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(screw, TungstenSteel), 'R', OreDictUnifier.get(rotor, TungstenSteel), 'H', OreDictUnifier.get(ring, m), 'P', OreDictUnifier.get(pipeMedium, TungstenSteel), 'M', ELECTRIC_MOTOR_IV.getStackForm(), 'C', OreDictUnifier.get(cableGtSingle, Tungsten));
        }

        //Automatic Machine Component Recipes
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(circuit, Tier.Basic, 4), CountableIngredient.from(dust, EnderPearl)).fluidInputs(Osmium.getFluid(288)).outputs(FIELD_GENERATOR_LV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).inputs(CountableIngredient.from(circuit, Tier.Good, 4), CountableIngredient.from(dust, EnderEye)).fluidInputs(Osmium.getFluid(576)).outputs(FIELD_GENERATOR_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).inputs(CountableIngredient.from(circuit, Tier.Advanced, 4), CountableIngredient.from(QUANTUM_EYE.getStackForm())).fluidInputs(Osmium.getFluid(1152)).outputs(FIELD_GENERATOR_HV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(CountableIngredient.from(circuit, MarkerMaterials.Tier.Extreme, 4), CountableIngredient.from(dust, NetherStar)).fluidInputs(Osmium.getFluid(2304)).outputs(FIELD_GENERATOR_EV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(7680).inputs(CountableIngredient.from(circuit, Tier.Elite, 4), CountableIngredient.from(QUANTUM_STAR.getStackForm())).fluidInputs(Osmium.getFluid(4608)).outputs(FIELD_GENERATOR_IV.getStackForm()).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(cableGtSingle, Tin, 2), CountableIngredient.from(stick, Iron, 2), CountableIngredient.from(stick, IronMagnetic)).fluidInputs(Copper.getFluid(288)).outputs(ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(cableGtSingle, Tin, 2), CountableIngredient.from(stick, Steel, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(288)).outputs(ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(40).inputs(CountableIngredient.from(cableGtSingle, Copper, 2), CountableIngredient.from(stick, Aluminium, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(576)).outputs(ELECTRIC_MOTOR_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(160).inputs(CountableIngredient.from(cableGtSingle, Gold, 2), CountableIngredient.from(stick, StainlessSteel, 2), CountableIngredient.from(stick, SteelMagnetic)).fluidInputs(Copper.getFluid(1152)).outputs(ELECTRIC_MOTOR_HV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(640).inputs(CountableIngredient.from(cableGtSingle, Aluminium, 2), CountableIngredient.from(stick, Titanium, 2), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(2304)).outputs(ELECTRIC_MOTOR_EV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2560).inputs(CountableIngredient.from(cableGtSingle, Tungsten, 2), CountableIngredient.from(stick, TungstenSteel, 2), CountableIngredient.from(stick, NeodymiumMagnetic)).fluidInputs(AnnealedCopper.getFluid(4608)).outputs(ELECTRIC_MOTOR_IV.getStackForm()).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).inputs(OreDictUnifier.get(cableGtSingle, Tin), ELECTRIC_MOTOR_LV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(CONVEYOR_MODULE_LV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Copper), ELECTRIC_MOTOR_MV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(CONVEYOR_MODULE_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).inputs(OreDictUnifier.get(cableGtSingle, Gold), ELECTRIC_MOTOR_HV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(CONVEYOR_MODULE_HV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).inputs(OreDictUnifier.get(cableGtSingle, Aluminium), ELECTRIC_MOTOR_EV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(CONVEYOR_MODULE_EV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).inputs(OreDictUnifier.get(cableGtSingle, Tungsten), ELECTRIC_MOTOR_IV.getStackForm(2)).fluidInputs(Rubber.getFluid(864)).outputs(CONVEYOR_MODULE_IV.getStackForm()).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).input(circuit, Tier.Basic, 2).inputs(OreDictUnifier.get(cableGtSingle, Tin, 2), OreDictUnifier.get(gem, Quartzite)).fluidInputs(Brass.getFluid(576)).outputs(EMITTER_LV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).input(circuit, Tier.Good, 2).inputs(OreDictUnifier.get(cableGtSingle, Copper, 2), OreDictUnifier.get(gem, NetherQuartz)).fluidInputs(Electrum.getFluid(576)).outputs(EMITTER_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).input(circuit, Tier.Advanced, 2).inputs(OreDictUnifier.get(cableGtSingle, Gold, 2), OreDictUnifier.get(gem, Emerald)).fluidInputs(Chrome.getFluid(576)).outputs(EMITTER_HV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).input(circuit, MarkerMaterials.Tier.Extreme, 2).inputs(OreDictUnifier.get(cableGtSingle, Aluminium, 2), OreDictUnifier.get(gem, EnderPearl)).fluidInputs(Platinum.getFluid(576)).outputs(EMITTER_EV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).input(circuit, Tier.Elite, 2).inputs(OreDictUnifier.get(cableGtSingle, Tungsten, 2), OreDictUnifier.get(gem, EnderEye)).fluidInputs(Osmium.getFluid(576)).outputs(EMITTER_IV.getStackForm()).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(ELECTRIC_MOTOR_LV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(pipeMedium, Bronze), OreDictUnifier.get(screw, Tin)).inputs(CountableIngredient.from(rotor, Tin, 1), CountableIngredient.from(ring, Paper, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(ELECTRIC_MOTOR_LV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tin), OreDictUnifier.get(pipeMedium, Bronze), OreDictUnifier.get(screw, Tin)).inputs(CountableIngredient.from(rotor, Tin, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(40).inputs(ELECTRIC_MOTOR_MV.getStackForm(), OreDictUnifier.get(cableGtSingle, Copper), OreDictUnifier.get(pipeMedium, Steel), OreDictUnifier.get(screw, Bronze)).inputs(CountableIngredient.from(rotor, Bronze, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_MV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(160).inputs(ELECTRIC_MOTOR_HV.getStackForm(), OreDictUnifier.get(cableGtSingle, Gold), OreDictUnifier.get(pipeMedium, StainlessSteel), OreDictUnifier.get(screw, Steel)).inputs(CountableIngredient.from(rotor, Steel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_HV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(640).inputs(ELECTRIC_MOTOR_EV.getStackForm(), OreDictUnifier.get(cableGtSingle, Aluminium), OreDictUnifier.get(pipeMedium, Titanium), OreDictUnifier.get(screw, StainlessSteel)).inputs(CountableIngredient.from(rotor, StainlessSteel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_EV.getStackForm()).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2560).inputs(ELECTRIC_MOTOR_IV.getStackForm(), OreDictUnifier.get(cableGtSingle, Tungsten), OreDictUnifier.get(pipeMedium, TungstenSteel), OreDictUnifier.get(screw, TungstenSteel)).inputs(CountableIngredient.from(rotor, TungstenSteel, 1), CountableIngredient.from(ring, m, 2)).fluidInputs(SolderingAlloy.getFluid(288)).outputs(ELECTRIC_PUMP_IV.getStackForm()).buildAndRegister();
        }


        //Pyrolise Oven Recipes
        PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(1).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(640).EUt(64).buildAndRegister();
        PYROLYSE_RECIPES.recipeBuilder().inputs(new ItemStack(Items.SUGAR, 23)).circuitMeta(2).fluidInputs(Nitrogen.getFluid(400)).outputs(OreDictUnifier.get(dust, Charcoal, 12)).fluidOutputs(Water.getFluid(1500)).duration(320).EUt(96).buildAndRegister();

        //Distillation Recipes
        DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(FishOil.getFluid(24)).fluidOutputs(Lubricant.getFluid(12)).buildAndRegister();
        ValidationResult<Recipe> result = DISTILLATION_RECIPES.recipeBuilder().duration(7500).EUt(1920).fluidInputs(LiquidAir.getFluid(100000)).fluidOutputs(Nitrogen.getFluid(78000), Oxygen.getFluid(20000), Argon.getFluid(1000), CarbonDioxide.getFluid(500), Neon.getFluid(100), Helium.getFluid(50), Methane.getFluid(20), Krypton.getFluid(10), Hydrogen.getFluid(5), Xenon.getFluid(1)).build();
        DISTILLATION_RECIPES.addRecipe(result);

        //Fluid Heater Recipes
        FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(Acetone.getFluid(100)).fluidOutputs(Ethenone.getFluid(100)).buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(CalciumAcetate.getFluid(200)).fluidOutputs(Acetone.getFluid(200)).buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(24).circuitMeta(1).fluidInputs(RawGrowthMedium.getFluid(500)).fluidOutputs(SterileGrowthMedium.getFluid(500)).buildAndRegister();

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
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_assembler_casing"));
        ModHandler.addShapedRecipe("ga_assmbler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING, 3), "CCC", "CFC", "CMC", 'C', "circuitElite", 'F', "frameGtTungstenSteel", 'M', ELECTRIC_MOTOR_IV.getStackForm());
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(valueOf("gtMetalCasing"), Steel, 1).fluidInputs(Polytetrafluoroethylene.getFluid(216)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Primitive, 2).inputs(OreDictUnifier.get(gear, Potin), OreDictUnifier.get(plate, Potin), OreDictUnifier.get(cableGtOctal, Tin), MetaTileEntities.HULL[GTValues.ULV].getStackForm()).fluidInputs(Steel.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ULV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Basic, 2).inputs(OreDictUnifier.get(gear, Tumbaga), OreDictUnifier.get(plate, Tumbaga), OreDictUnifier.get(cableGtOctal, Cobalt), MetaTileEntities.HULL[GTValues.LV].getStackForm()).fluidInputs(Silicon.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Good, 2).inputs(OreDictUnifier.get(gear, EglinSteel), OreDictUnifier.get(plate, EglinSteel), OreDictUnifier.get(cableGtOctal, AnnealedCopper), MetaTileEntities.HULL[GTValues.MV].getStackForm()).fluidInputs(BabbittAlloy.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_MV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Advanced, 2).inputs(OreDictUnifier.get(gear, Inconel625), OreDictUnifier.get(plate, Inconel625), OreDictUnifier.get(cableGtOctal, Gold), MetaTileEntities.HULL[GTValues.HV].getStackForm()).fluidInputs(Inconel625.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_HV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Extreme, 2).inputs(OreDictUnifier.get(gear, TungstenCarbide), OreDictUnifier.get(plate, TungstenCarbide), OreDictUnifier.get(cableGtOctal, Titanium), MetaTileEntities.HULL[GTValues.EV].getStackForm()).fluidInputs(Stellite.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_EV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Elite, 2).inputs(OreDictUnifier.get(gear, Nitinol60), OreDictUnifier.get(plate, Nitinol60), OreDictUnifier.get(cableGtOctal, Nichrome), MetaTileEntities.HULL[GTValues.IV].getStackForm()).fluidInputs(Thorium.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_IV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Master, 2).inputs(OreDictUnifier.get(gear, IncoloyMA956), OreDictUnifier.get(plate, IncoloyMA956), OreDictUnifier.get(cableGtOctal, Platinum), MetaTileEntities.HULL[GTValues.LuV].getStackForm()).fluidInputs(Uranium235.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LUV, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Ultimate, 2).inputs(OreDictUnifier.get(gear, BabbittAlloy), OreDictUnifier.get(plate, BabbittAlloy), OreDictUnifier.get(cableGtOctal, NiobiumTitanium), MetaTileEntities.HULL[GTValues.ZPM].getStackForm()).fluidInputs(Plutonium241.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_ZPM, 1)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000).input(circuit, Tier.Superconductor, 2).inputs(OreDictUnifier.get(gear, HG1223), OreDictUnifier.get(plate, HG1223), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate), MetaTileEntities.HULL[GTValues.UV].getStackForm()).fluidInputs(NaquadahEnriched.getFluid(1440)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_UV, 1)).buildAndRegister();


        MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR, 4), OreDictUnifier.get(dust, Meat), OreDictUnifier.get(dustTiny, Salt)).fluidInputs(DistilledWater.getFluid(4000)).fluidOutputs(RawGrowthMedium.getFluid(4000)).buildAndRegister();


        //add missing casing and component
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .outputs(OreDictUnifier.get(dust, EglinSteelBase, 10)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, EglinSteelBase, 10)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .outputs(OreDictUnifier.get(dust, EglinSteel, 13)).buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Titanium, 9)
                .input(dust, Carbon, 9)
                .input(dust, Lithium, 9)
                .input(dust, Sulfur, 9)
                .input(dust, Potassium, 9)
                .fluidInputs(Hydrogen.getFluid(500))
                .outputs(OreDictUnifier.get(dust, Grisium, 48)).buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .input(dust, Nickel, 3)
                .outputs(OreDictUnifier.get(dust, Inconel625, 43)).buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Steel, 16)
                .input(dust, Molybdenum, 1)
                .input(dust, Titanium, 1)
                .input(dust, Cobalt, 2)
                .input(dust, Nickel, 4)
                .outputs(OreDictUnifier.get(dust, MaragingSteel250, 24)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Lead, 2)
                .input(dust, Bronze, 2)
                .input(dust, Tin, 1)
                .outputs(OreDictUnifier.get(dust, Potin, 5)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Uranium, 9)
                .input(dust, Titanium, 1)
                .outputs(OreDictUnifier.get(dust, Staballoy, 10)).buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Yttrium, 2)
                .input(dust, Molybdenum, 4)
                .input(dust, Chrome, 2)
                .input(dust, Titanium, 2)
                .input(dust, Nickel, 15)
                .outputs(OreDictUnifier.get(dust, HastelloyN, 25)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Gold, 7)
                .input(dust, Bronze, 3)
                .outputs(OreDictUnifier.get(dust, Tumbaga, 10)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Cobalt, 9)
                .input(dust, Chrome, 9)
                .input(dust, Manganese, 5)
                .input(dust, Titanium, 2)
                .outputs(OreDictUnifier.get(dust, Stellite, 25)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Phosphorus, 2)
                .input(dust, Molybdenum, 1)
                .outputs(OreDictUnifier.get(dust, Talonite, 10)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Titanium, 3)
                .input(dust, Nickel, 2)
                .outputs(OreDictUnifier.get(dust, Nitinol60, 5)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Tin, 5)
                .input(dust, Lead, 36)
                .input(dust, Antimony, 8)
                .input(dust, Arsenic, 1)
                .outputs(OreDictUnifier.get(dust, BabbittAlloy, 50)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(1000).EUt(1920)
                .input(dust, Iron, 16)
                .input(dust, Aluminium, 3)
                .input(dust, Chrome, 5)
                .input(dust, Yttrium, 1)
                .outputs(OreDictUnifier.get(dust, IncoloyMA956, 25)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(30720)
                .fluidInputs(Mercury.getFluid(1000), Oxygen.getFluid(8000))
                .input(dust, Barium, 2)
                .input(dust, Calcium, 2)
                .input(dust, Copper, 3)
                .outputs(OreDictUnifier.get(dust, HG1223, 16)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Zirconium)
                .input(dust, Carbon)
                .outputs(OreDictUnifier.get(dust, ZirconiumCarbide, 2)).buildAndRegister();

        //Pyrotheum
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
                .fluidInputs(Redstone.getFluid(250))
                .outputs(OreDictUnifier.get(dust, Blizz, 2)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Snow)
                .input(dust, Blizz, 2)
                .outputs(OreDictUnifier.get(dust, Cryotheum, 2)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, Cryotheum)
                .fluidOutputs(Cryotheum.getFluid(250)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(HastelloyN.getFluid(144 * 4)).input(valueOf("gtMetalCasing"), Staballoy, 2).inputs(CountableIngredient.from(circuit, Tier.Extreme)).outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER, 2)).duration(600).EUt(8000).buildAndRegister();


        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Invar, 6).input(frameGt, Invar, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Steel, 6).input(frameGt, Steel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Aluminium, 6).input(frameGt, Aluminium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, TungstenSteel, 6).input(frameGt, TungstenSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, StainlessSteel, 6).input(frameGt, StainlessSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN, 3)).duration(50).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).notConsumable(new IntCircuitIngredient(30)).input(plate, Titanium, 6).input(frameGt, Titanium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE, 3)).duration(50).buildAndRegister();


        //Circuit Rabbit Hole - Layer 1
        ModHandler.removeRecipes(BASIC_CIRCUIT_LV.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:good_circuit"));
        ModHandler.addShapedRecipe("primitive_processor", BASIC_CIRCUIT_LV.getStackForm(), "RPR", "TBT", "CCC", 'R', RESISTOR, 'P', GACraftingComponents.CIRCUIT_PLATE.getIngredient(0), 'T', VACUUM_TUBE, 'B', BASIC_BOARD, 'C', new UnificationEntry(cableGtSingle, RedAlloy));
        ModHandler.addShapedRecipe("primitive_assembly", PRIMITIVE_ASSEMBLY.getStackForm(), "PCT", "CDC", "TCP", 'C', BASIC_CIRCUIT_LV, 'P', GACraftingComponents.CIRCUIT_PLATE.getIngredient(0), 'D', DIODE, 'T', new UnificationEntry(cableGtSingle, RedAlloy));

        for (String fluid : GAConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = Integer.parseInt(fluidSplit[1]);
            if (amount > 64000) {
                amount = 64000;
            }
            if (amount < 1) {
                amount = 1;
            }
            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);

            //ELECTRONICS   //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16).outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).inputs(RESISTOR.getStackForm(4), CAPACITOR.getStackForm(4), GOOD_PHENOLIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, Copper, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16).outputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm()).inputs(SMD_RESISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), GOOD_PHENOLIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, Copper, 4).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_ASSEMBLY.getStackForm()).inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), TRANSISTOR.getStackForm(1), RESISTOR.getStackForm(4)).input(plate, Electrum, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_ASSEMBLY.getStackForm()).inputs(BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), SMD_TRANSISTOR.getStackForm(1), SMD_RESISTOR.getStackForm(4)).input(plate, Electrum, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_COMPUTER.getStackForm()).inputs(ELECTRONIC_ASSEMBLY.getStackForm(4), CAPACITOR.getStackForm(2), RESISTOR.getStackForm(2), INTEGRATED_LOGIC_CIRCUIT.getStackForm(2)).input(plate, Aluminium, 2).input(wireGtSingle, AnnealedCopper, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16).outputs(ELECTRONIC_COMPUTER.getStackForm()).inputs(ELECTRONIC_ASSEMBLY.getStackForm(4), SMD_CAPACITOR.getStackForm(2), SMD_RESISTOR.getStackForm(2), INTEGRATED_LOGIC_CIRCUIT.getStackForm(2)).input(plate, Aluminium, 2).input(wireGtSingle, AnnealedCopper, 4).fluidInputs(fluidStack).buildAndRegister();


            //REFINED       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(RESISTOR.getStackForm(4), TRANSISTOR.getStackForm(4), CAPACITOR.getStackForm(4), GOOD_PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), GOOD_PLASTIC_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600).outputs(REFINED_PROCESSOR.getStackForm(4)).inputs(GOOD_PLASTIC_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, TinAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60).outputs(REFINED_ASSEMBLY.getStackForm()).inputs(REFINED_PROCESSOR.getStackForm(3), RESISTOR.getStackForm(2), TRANSISTOR.getStackForm(2), CAPACITOR.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(plate, StainlessSteel, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60).outputs(REFINED_ASSEMBLY.getStackForm()).inputs(REFINED_PROCESSOR.getStackForm(3), SMD_RESISTOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), SMD_CAPACITOR.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(plate, StainlessSteel, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90).outputs(REFINED_COMPUTER.getStackForm()).inputs(REFINED_ASSEMBLY.getStackForm(4), RESISTOR.getStackForm(2), TRANSISTOR.getStackForm(2), RANDOM_ACCESS_MEMORY.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(wireGtSingle, MVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90).outputs(REFINED_COMPUTER.getStackForm()).inputs(REFINED_ASSEMBLY.getStackForm(4), SMD_RESISTOR.getStackForm(2), SMD_TRANSISTOR.getStackForm(2), RANDOM_ACCESS_MEMORY.getStackForm(2), GOOD_PLASTIC_BOARD.getStackForm()).input(wireGtSingle, MVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110).outputs(REFINED_MAINFRAME.getStackForm()).inputs(REFINED_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(16), SMD_TRANSISTOR.getStackForm(8), SMD_DIODE.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(4)).input(frameGt, StainlessSteel, 4).fluidInputs(fluidStack).buildAndRegister();


            //MICRO       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(RESISTOR.getStackForm(4), TRANSISTOR.getStackForm(4), CAPACITOR.getStackForm(4), ADVANCED_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(2)).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), ADVANCED_BOARD.getStackForm(), CENTRAL_PROCESSING_UNIT.getStackForm(2)).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400).outputs(MICRO_PROCESSOR.getStackForm(4)).inputs(ADVANCED_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, RedAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350).outputs(PROCESSOR_ASSEMBLY_HV.getStackForm()).inputs(MICRO_PROCESSOR.getStackForm(3), CAPACITOR.getStackForm(2), SMALL_COIL.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(2), ADVANCED_BOARD.getStackForm()).input(plate, Titanium, 4).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350).outputs(PROCESSOR_ASSEMBLY_HV.getStackForm()).inputs(MICRO_PROCESSOR.getStackForm(3), SMD_CAPACITOR.getStackForm(2), SMALL_COIL.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(2), ADVANCED_BOARD.getStackForm()).input(plate, Titanium, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425).outputs(MICRO_COMPUTER.getStackForm()).inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4), SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(8), ADVANCED_BOARD.getStackForm()).input(wireGtSingle, HVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500).outputs(MICRO_MAINFRAME.getStackForm()).inputs(MICRO_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(20), SMD_TRANSISTOR.getStackForm(10), SMD_DIODE.getStackForm(5), RANDOM_ACCESS_MEMORY.getStackForm(8)).input(frameGt, Titanium, 4).fluidInputs(fluidStack).buildAndRegister();


            //NANO       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_HV.getStackForm(4)).inputs(SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), EXTREME_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12)).input(wireFine, Aluminium, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600).outputs(NANO_PROCESSOR_HV.getStackForm(4)).inputs(EXTREME_BOARD.getStackForm(), SYSTEM_ON_CHIP.getStackForm()).input(wireFine, Aluminium, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000).outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm()).inputs(NANO_PROCESSOR_HV.getStackForm(3), SMD_CAPACITOR.getStackForm(4), SMALL_COIL.getStackForm(4), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2), EXTREME_BOARD.getStackForm()).input(plate, TungstenSteel, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000).outputs(NANO_COMPUTER.getStackForm()).inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4), SMD_RESISTOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4), RANDOM_ACCESS_MEMORY.getStackForm(8), EXTREME_BOARD.getStackForm()).input(wireGtSingle, EVSuperconductor, 1).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000).outputs(NANO_MAINFRAME.getStackForm()).inputs(NANO_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(24), SMD_TRANSISTOR.getStackForm(12), SMD_DIODE.getStackForm(6), RANDOM_ACCESS_MEMORY.getStackForm(12)).input(frameGt, TungstenSteel, 4).fluidInputs(fluidStack).buildAndRegister();

            //QUANTUM       //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000).outputs(QUANTUM_PROCESSOR_EV.getStackForm(4)).inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR.getStackForm(4), SMD_CAPACITOR.getStackForm(4), ELITE_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, Platinum, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(36000).outputs(QUANTUM_PROCESSOR_EV.getStackForm(4)).inputs(ELITE_BOARD.getStackForm(), ADVANCED_SYSTEM_ON_CHIP.getStackForm()).input(wireFine, Platinum, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000).outputs(QUANTUM_ASSEMBLY.getStackForm()).inputs(QUANTUM_PROCESSOR_EV.getStackForm(3), SMD_CAPACITOR.getStackForm(4), SMALL_COIL.getStackForm(4), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2), ELITE_BOARD.getStackForm()).input(plate, Chrome, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000).outputs(QUANTUM_COMPUTER.getStackForm()).inputs(QUANTUM_ASSEMBLY.getStackForm(4), SMD_DIODE.getStackForm(8), QUANTUM_EYE.getStackForm(1), POWER_INTEGRATED_CIRCUIT.getStackForm(4), ELITE_BOARD.getStackForm()).input(wireGtSingle, IVSuperconductor, 2).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000).outputs(QUANTUM_MAINFRAME.getStackForm()).inputs(QUANTUM_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(32), SMD_TRANSISTOR.getStackForm(28), SMD_CAPACITOR.getStackForm(28), SMD_DIODE.getStackForm(16), POWER_INTEGRATED_CIRCUIT.getStackForm(8), QUANTUM_STAR.getStackForm()).input(frameGt, TungstenSteel, 4).input(wireGtSingle, IVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();

            //CRYSTAL      //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000).outputs(CRYSTAL_PROCESSOR.getStackForm(4)).inputs(ENGRAVED_CRYSTAL_CHIP.getStackForm(1), SMD_TRANSISTOR.getStackForm(8), SMD_CAPACITOR.getStackForm(4), MASTER_BOARD.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, NiobiumTitanium, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(86000).outputs(CRYSTAL_PROCESSOR.getStackForm(4)).inputs(MASTER_BOARD.getStackForm(), CRYSTAL_SYSTEM_ON_CHIP.getStackForm()).input(wireFine, NiobiumTitanium, 2).fluidInputs(fluidStack).buildAndRegister();
            //ASSEMBLY
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000).outputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm()).inputs(CRYSTAL_PROCESSOR.getStackForm(3), CENTRAL_PROCESSING_UNIT.getStackForm(1), SMALL_COIL.getStackForm(4), QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(1), MASTER_BOARD.getStackForm()).input(wireGtSingle, LuVSuperconductor, 4).fluidInputs(fluidStack).buildAndRegister();
            //COMPUTER
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000).outputs(CRYSTAL_COMPUTER.getStackForm()).inputs(ENERGY_FLOW_CIRCUIT_LUV.getStackForm(4), SMD_DIODE.getStackForm(8), SMD_RESISTOR.getStackForm(8), QUANTUM_EYE.getStackForm(1), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(1), MASTER_BOARD.getStackForm()).input(plate, HSSE, 2).input(wireGtSingle, LuVSuperconductor, 16).fluidInputs(fluidStack).buildAndRegister();
            //MAINFRAME
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(30000).outputs(CRYSTAL_MAINFRAME.getStackForm()).inputs(CRYSTAL_COMPUTER.getStackForm(2), SMD_RESISTOR.getStackForm(48), SMD_TRANSISTOR.getStackForm(36), SMD_CAPACITOR.getStackForm(32), SMD_DIODE.getStackForm(24), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(4), QUANTUM_STAR.getStackForm(4)).input(frameGt, HSSE, 4).input(wireGtSingle, LuVSuperconductor, 32).fluidInputs(fluidStack).buildAndRegister();

            //WETWARE      //PROCESSOR
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(28000).outputs(WETWARE_PROCESSOR_LUV.getStackForm(1)).inputs(CENTRAL_PROCESSING_UNIT.getStackForm(1), SMD_TRANSISTOR.getStackForm(8), SMD_CAPACITOR.getStackForm(4), NEURO_PROCESSOR.getStackForm(), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(1)).input(wireFine, YttriumBariumCuprate, 2).fluidInputs(fluidStack).buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(120000).outputs(WETWARE_PROCESSOR_LUV.getStackForm(4)).inputs(NEURO_PROCESSOR.getStackForm(), ADVANCED_SYSTEM_ON_CHIP.getStackForm(4)).input(wireFine, NaquadahAlloy, 2).fluidInputs(fluidStack).buildAndRegister();
        }
        //ASSEMBLY
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000).outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm()).inputs(WETWARE_PROCESSOR_LUV.getStackForm(3), SMD_RESISTOR.getStackForm(16), SMD_TRANSISTOR.getStackForm(16), SMD_CAPACITOR.getStackForm(16), SMD_DIODE.getStackForm(16), RANDOM_ACCESS_MEMORY.getStackForm(1), QUANTUM_EYE.getStackForm(4), SMALL_COIL.getStackForm(4), NEURO_PROCESSOR.getStackForm()).input(wireGtSingle, ZPMSuperconductor, 4).input(foil, SiliconeRubber, 16).fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();
        //COMPUTER
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880).outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm()).inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4), SMD_RESISTOR.getStackForm(32), SMD_TRANSISTOR.getStackForm(32), SMD_CAPACITOR.getStackForm(32), SMD_DIODE.getStackForm(32), QUANTUM_STAR.getStackForm(4), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8), NEURO_PROCESSOR.getStackForm()).input(plate, Europium, 2).input(wireGtSingle, ZPMSuperconductor, 16).input(foil, SiliconeRubber, 32).fluidInputs(SterileGrowthMedium.getFluid(2000)).buildAndRegister();
        //MAINFRAME
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000).outputs(WETWARE_MAINFRAME_MAX.getStackForm()).inputs(WETWARE_SUPER_COMPUTER_UV.getStackForm(2), SMD_RESISTOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_DIODE.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8), GRAVI_STAR.getStackForm(4)).input(frameGt, Tritanium, 4).input(plate, Duranium, 32).input(wireGtSingle, Tier.Superconductor, 64).input(foil, Polytetrafluoroethylene, 64).fluidInputs(SterileGrowthMedium.getFluid(2000), UUMatter.getFluid(1000)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 2
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480).inputs(ELITE_BOARD.getStackForm(), PETRI_DISH.getStackForm(), ELECTRIC_PUMP_LV.getStackForm(), SENSOR_LV.getStackForm()).input(circuit, Tier.Good).fluidInputs(SterileGrowthMedium.getFluid(250)).outputs(WETWARE_BOARD.getStackForm()).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(PositiveMatter.getFluid(10), NeutralMatter.getFluid(10)).fluidOutputs(UUMatter.getFluid(20)).buildAndRegister();

        ModHandler.removeRecipes(COATED_BOARD.getStackForm(3));
        ModHandler.addShapedRecipe("coated_board_shaped", COATED_BOARD.getStackForm(3), "RRR", "BBB", "RRR", 'R', RUBBER_DROP, 'B', "plateWood");
        ModHandler.addShapelessRecipe("coated_board_shapeless", COATED_BOARD.getStackForm(), RUBBER_DROP, RUBBER_DROP, "plateWood");
        ModHandler.addShapedRecipe("basic_board", BASIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Copper), 'B', COATED_BOARD);
        ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(20).input(plate, Wood).input(foil, Copper, 4).fluidInputs(Glue.getFluid(72)).outputs(BASIC_BOARD.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("good_board", GOOD_PHENOLIC_BOARD.getStackForm(), "WWW", "WBW", "WWW", 'W', new UnificationEntry(wireGtSingle, Gold), 'B', PHENOLIC_BOARD);
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(PHENOLIC_BOARD.getStackForm()).input(foil, Gold, 4).fluidInputs(SodiumPersulfate.getFluid(200)).outputs(GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(PHENOLIC_BOARD.getStackForm()).input(foil, Gold, 4).fluidInputs(IronChloride.getFluid(100)).outputs(GOOD_PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(SodiumPersulfate.getFluid(500)).outputs(GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).inputs(PLASTIC_BOARD.getStackForm()).input(foil, Copper, 6).fluidInputs(IronChloride.getFluid(250)).outputs(GOOD_PLASTIC_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(SodiumPersulfate.getFluid(1000)).outputs(ADVANCED_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30).inputs(EPOXY_BOARD.getStackForm()).input(foil, Electrum, 8).fluidInputs(IronChloride.getFluid(500)).outputs(ADVANCED_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(SodiumPersulfate.getFluid(2000)).outputs(EXTREME_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30).inputs(FIBER_BOARD.getStackForm()).input(foil, AnnealedCopper, 12).fluidInputs(IronChloride.getFluid(1000)).outputs(EXTREME_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(SodiumPersulfate.getFluid(4000)).outputs(ELITE_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120).inputs(MULTILAYER_FIBER_BOARD.getStackForm()).input(foil, Platinum, 16).fluidInputs(IronChloride.getFluid(2000)).outputs(ELITE_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(SodiumPersulfate.getFluid(10000)).outputs(MASTER_BOARD.getStackForm()).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480).inputs(WETWARE_BOARD.getStackForm()).input(foil, NiobiumTitanium, 32).fluidInputs(IronChloride.getFluid(5000)).outputs(MASTER_BOARD.getStackForm()).buildAndRegister();

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diode"));
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(288)).outputs(DIODE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(144)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).input(dust, GalliumArsenide).fluidInputs(Plastic.getFluid(288)).outputs(SMD_DIODE.getStackForm(32)).buildAndRegister();

        ModHandler.removeRecipes(RESISTOR.getStackForm(3));
        for (Material m : new Material[]{Coal, Charcoal, Carbon}) {
            ModHandler.addShapedRecipe(String.format("gautils:resistor_%s", m.toString()), RESISTOR.getStackForm(), "RWR", "CMC", " W ", 'M', new UnificationEntry(dust, m), 'R', RUBBER_DROP, 'W', "wireFineCopper", 'C', "wireGtSingleCopper");
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, Copper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(RESISTOR.getStackForm(8)).buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16).input(dust, m).input(wireFine, AnnealedCopper, 4).input(wireGtSingle, Copper, 4).fluidInputs(Glue.getFluid(200)).outputs(RESISTOR.getStackForm(8)).buildAndRegister();
        }

        //Cutting Machine Recipes
        for (MaterialStack stack : sawLubricants) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;
            CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(420).inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).fluidInputs(material.getFluid(2 * multiplier)).outputs(RAW_CRYSTAL_CHIP.getStackForm(2)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polystyrene.getFluid(36)).notConsumable(SHAPE_MOLD_CYLINDER.getStackForm()).outputs(PETRI_DISH.getStackForm()).buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Polytetrafluoroethylene.getFluid(36)).notConsumable(SHAPE_MOLD_CYLINDER.getStackForm()).outputs(PETRI_DISH.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Emerald).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Olivine).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(1024).inputs(new ItemStack(Items.EGG)).chancedOutput(STEM_CELLS.getStackForm(), 500, 750).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(30).input(dust, Iron).fluidInputs(HydrochloricAcid.getFluid(2000)).fluidOutputs(IronChloride.getFluid(3000), Hydrogen.getFluid(3000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(), CARBON_FIBERS.getStackForm(16)).fluidInputs(Glowstone.getFluid(576)).outputs(NANO_CENTRAL_PROCESSING_UNIT_WAFER.getStackForm()).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Europium.getFluid(16)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 1000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Europium.getFluid(16)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 1000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(dust, Carbon).fluidInputs(Lutetium.getFluid(1)).chancedOutput(CARBON_FIBERS.getStackForm(2), 3333, 750).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000).inputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).notConsumable(craftingLens, MarkerMaterials.Color.Lime).outputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR, 4), OreDictUnifier.get(dust, Meat), OreDictUnifier.get(dustTiny, Salt)).fluidInputs(DistilledWater.getFluid(4000)).fluidOutputs(RawGrowthMedium.getFluid(4000)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).blastFurnaceTemp(1784).input(dust, Silicon, 32).input(dustSmall, GalliumArsenide).outputs(SILICON_BOULE.getStackForm()).buildAndRegister();

        //Circuit resonatic Magneto
        CHEMICAL_RECIPES.recipeBuilder().duration(4000).EUt(30).input(dust, Yttrium, 2).fluidInputs(Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(dust, YttriumOxide, 5)).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(56000).EUt(480).blastFurnaceTemp(2953).input(dust, Zirconium, 10).input(dust, YttriumOxide, 1).fluidInputs(Oxygen.getFluid(10000)).outputs(OreDictUnifier.get(gemFlawed, CubicZirconia, 40)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(1080).EUt(20).input(dust, Prasiolite, 3).input(dust, BismuthTellurite, 4).input(dust, CubicZirconia, 1).input(dust, SteelMagnetic).outputs(OreDictUnifier.get(dust, MagnetoResonatic, 9)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(570).EUt(90).input(dust, Bismuth, 2).input(dust, Boron).fluidInputs(Hydrogen.getFluid(1000)).outputs(OreDictUnifier.get(dust, Dibismusthydroborat, 4)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(161).EUt(60).input(dust, Bismuth, 2).input(dust, Tellurium, 3).outputs(OreDictUnifier.get(dust, BismuthTellurite, 5)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(982).EUt(15).input(dust, IndiumGalliumPhosphide).input(dust, Dibismusthydroborat, 3).input(dust, BismuthTellurite, 2).outputs(OreDictUnifier.get(dust, CircuitCompoundMK3, 6)).buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(300).EUt(480).input(dust, CircuitCompoundMK3, 4).input(dust, MagnetoResonatic).outputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm()).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(300).EUt(1920).inputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm()).fluidInputs(SolderingAlloy.getFluid(GTValues.L * 3)).outputs(IMPRINT_SUPPORTED_BOARD.getStackForm()).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Neon.getFluid(100)).outputs(OreDictUnifier.get(gemChipped, MagnetoResonatic, 9)).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Krypton.getFluid(100)).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(30).input(gemChipped, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawed, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(120).input(gemFlawed, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(1200).EUt(480).input(gem, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawless, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(1920).input(gemFlawless, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemExquisite, MagnetoResonatic)).buildAndRegister();


        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(75).EUt(30).outputs(CIRCUIT_MAGNETIC_ULV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), IMPRINT_SUPPORTED_BOARD.getStackForm(), SMD_DIODE.getStackForm(4), SMD_CAPACITOR.getStackForm(4), SMD_TRANSISTOR.getStackForm(4)).fluidInputs(SolderingAlloy.getFluid(36)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(120).outputs(CIRCUIT_MAGNETIC_LV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_ULV.getStackForm(), SMD_DIODE.getStackForm(8), SMD_CAPACITOR.getStackForm(8), SMD_TRANSISTOR.getStackForm(8)).fluidInputs(SolderingAlloy.getFluid(72)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(225).EUt(480).outputs(CIRCUIT_MAGNETIC_MV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_LV.getStackForm(), SMD_DIODE.getStackForm(12), SMD_CAPACITOR.getStackForm(12), SMD_TRANSISTOR.getStackForm(12)).fluidInputs(SolderingAlloy.getFluid(108)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920).outputs(CIRCUIT_MAGNETIC_HV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_MV.getStackForm(), SMD_DIODE.getStackForm(16), SMD_CAPACITOR.getStackForm(16), SMD_TRANSISTOR.getStackForm(16)).fluidInputs(SolderingAlloy.getFluid(144)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(375).EUt(7680).outputs(CIRCUIT_MAGNETIC_EV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(), OreDictUnifier.get(gem, MagnetoResonatic), CIRCUIT_MAGNETIC_HV.getStackForm(), SMD_DIODE.getStackForm(20), SMD_CAPACITOR.getStackForm(20), SMD_TRANSISTOR.getStackForm(20)).fluidInputs(SolderingAlloy.getFluid(180)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(450).EUt(30720).outputs(CIRCUIT_MAGNETIC_IV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gem, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_EV.getStackForm(), SMD_DIODE.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64)).fluidInputs(SolderingAlloy.getFluid(864)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(525).EUt(122880).outputs(CIRCUIT_MAGNETIC_LUV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gem, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_IV.getStackForm(), SMD_DIODE.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64)).fluidInputs(SolderingAlloy.getFluid(1008)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(491520).outputs(CIRCUIT_MAGNETIC_ZPM.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gemExquisite, MagnetoResonatic), CIRCUIT_MAGNETIC_LUV.getStackForm(), SMD_DIODE.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64)).fluidInputs(SolderingAlloy.getFluid(4608)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(675).EUt(491520).outputs(CIRCUIT_MAGNETIC_UV.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gemExquisite, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_ZPM.getStackForm(), SMD_DIODE.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64)).fluidInputs(SolderingAlloy.getFluid(5184)).buildAndRegister();
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(750).EUt(491520).outputs(CIRCUIT_MAGNETIC_MAX.getStackForm()).inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6), OreDictUnifier.get(gemExquisite, MagnetoResonatic, 6), CIRCUIT_MAGNETIC_UV.getStackForm(), SMD_DIODE.getStackForm(64), SMD_CAPACITOR.getStackForm(64), SMD_TRANSISTOR.getStackForm(64)).fluidInputs(SolderingAlloy.getFluid(5760)).buildAndRegister();

        //distillery and new brewing
        DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(60).circuitMeta(0).fluidInputs(FermentationBase.getFluid(40)).fluidOutputs(Ethanol.getFluid(4)).buildAndRegister();

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
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100).input("circuitAdvanced", 6).inputs(MetaTileEntities.STEEL_TANK.getStackForm(), MetaItems.ELECTRIC_PUMP_MV.getStackForm(2), OreDictUnifier.get(pipeSmall, Plastic, 2), OreDictUnifier.get(pipeMedium, Steel, 2), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(SEMIFLUID_JETPACK.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100).input("circuitAdvanced", 6).inputs(MetaItems.BATTERY_RE_MV_CADMIUM.getStackForm(6), IMPELLER_MV.getStackForm(4), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(IMPELLER_JETPACK.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Copper), MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), OreDictUnifier.get(stick, Steel), OreDictUnifier.get(rotor, Plastic, 2), OreDictUnifier.get(pipeMedium, Plastic)).outputs(IMPELLER_MV.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(OreDictUnifier.get(cableGtSingle, Gold), MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), OreDictUnifier.get(stick, StainlessSteel), OreDictUnifier.get(rotor, Plastic, 2), OreDictUnifier.get(pipeMedium, Plastic)).outputs(IMPELLER_HV.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("gtadditions:battery_pack.lv", BATPACK_LV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_LV_LITHIUM.getStackForm(), 'C', "circuitBasic", 'P', OreDictUnifier.get(plate, Steel));
        ModHandler.addShapedRecipe("gtadditions:battery_pack.mv", BATPACK_MV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_MV_LITHIUM.getStackForm(), 'C', "circuitGood", 'P', OreDictUnifier.get(plate, Aluminium));
        ModHandler.addShapedRecipe("gtadditions:battery_pack.hv", BATPACK_HV.getStackForm(), "BPB", "BCB", "B B", 'B', MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm(), 'C', "circuitAdvanced", 'P', OreDictUnifier.get(plate, StainlessSteel));
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(400).input("circuitAdvanced", 4).input("circuitExtreme", 1).inputs(BATPACK_HV.getStackForm(), IMPELLER_HV.getStackForm(6), MetaItems.BATTERY_RE_HV_CADMIUM.getStackForm(), OreDictUnifier.get(plate, Aluminium), OreDictUnifier.get(screw, Aluminium, 4), OreDictUnifier.get(stick, Aluminium, 2)).outputs(ADVANCED_IMPELLER_JETPACK.getStackForm()).buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(7), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(0)).outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(6), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(1)).outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 1).inputs(MetaItems.CARBON_PLATE.getStackForm(4), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(2)).outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512).input("circuitAdvanced", 2).inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS), MetaItems.SENSOR_HV.getStackForm(2), MetaItems.EMITTER_HV.getStackForm(2), MetaItems.CARBON_PLATE.getStackForm(4), MetaItems.BATTERY_RE_HV_LITHIUM.getStackForm()).notConsumable(new IntCircuitIngredient(3)).outputs(NANO_MUSCLE_SUITE_HELMET.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1500).EUt(1024).input("circuitExtreme", 2).inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm(), ADVANCED_IMPELLER_JETPACK.getStackForm(), INSULATING_TAPE.getStackForm(4), MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4)).outputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm()).buildAndRegister();

        //QuarkTech Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 2).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4), MetaItems.ELECTRIC_PISTON_EV.getStackForm(2), NANO_MUSCLE_SUITE_BOOTS.getStackForm()).outputs(QUARK_TECH_SUITE_BOOTS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 4).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(6), MetaItems.CONVEYOR_MODULE_EV.getStackForm(2), NANO_MUSCLE_SUITE_LEGGINS.getStackForm()).outputs(QUARK_TECH_SUITE_LEGGINS.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 4).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(8), MetaItems.FIELD_GENERATOR_EV.getStackForm(2), NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm()).outputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600).input("circuitExtreme", 2).inputs(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.LAPOTRON_CRYSTAL.getStackForm(), MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(4), MetaItems.SENSOR_EV.getStackForm(), MetaItems.EMITTER_EV.getStackForm(), NANO_MUSCLE_SUITE_HELMET.getStackForm()).outputs(QUARK_TECH_SUITE_HELMET.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(7100).inputs(MetaItems.FIELD_GENERATOR_IV.getStackForm()).inputs(MetaItems.FIELD_GENERATOR_EV.getStackForm(2)).input("circuitMaster", 4).input(cableGtSingle, IVSuperconductor, 4).inputs(MetaItems.POWER_INTEGRATED_CIRCUIT.getStackForm(4)).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(GRAVITATION_ENGINE.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192).inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16)).input(cableGtSingle, IVSuperconductor, 8).inputs(GRAVITATION_ENGINE.getStackForm(2)).inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(12)).input("circuitElite", 4).inputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm()).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192).inputs(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8)).input(cableGtSingle, IVSuperconductor, 8).inputs(GRAVITATION_ENGINE.getStackForm(2)).inputs(MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(16)).input("circuitElite", 2).inputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm()).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm()).buildAndRegister();

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
    }


    public static void init2() {
        //Fuel Rabbit Hole - Layer 1
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(120).fluidInputs(LightFuel.getFluid(5000), HeavyFuel.getFluid(1000)).fluidOutputs(Fuel.getFluid(6000)).buildAndRegister();

        //Fuel high Octane
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(1920).fluidInputs(NitricOxide.getFluid(6000), Gasoline.getFluid(20000), Toluene.getFluid(1000), Octane.getFluid(2000), EthylTertButylEther.getFluid(3000)).fluidOutputs(HighOctaneGasoline.getFluid(32000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(10).EUt(480).fluidInputs(RawGasoline.getFluid(10000), Toluene.getFluid(1000)).fluidOutputs(Gasoline.getFluid(11000)).buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480).fluidInputs(Naphtha.getFluid(16000), Gas.getFluid(2000), Methanol.getFluid(1000), Acetone.getFluid(1000)).fluidOutputs(RawGasoline.getFluid(20000)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(480).fluidInputs(Ethanol.getFluid(1000), Butane.getFluid(1000)).fluidOutputs(EthylTertButylEther.getFluid(2000)).buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(HydroCrackedLightFuel.getFluid(1000)).fluidOutputs(Naphtha.getFluid(750), Propane.getFluid(200), Butane.getFluid(150), Ethane.getFluid(125), Methane.getFluid(125), Octane.getFluid(50)).buildAndRegister();

        //Rocket Fuel tier 4
        MIXER_RECIPES.recipeBuilder().duration(19).EUt(480).fluidInputs(Dimethylhydrazine.getFluid(40), NitrogenTetroxide.getFluid(40)).fluidOutputs(RocketFuelH8N4C2O4.getFluid(35)).buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500).input(dust, Copper).fluidInputs(NitricAcid.getFluid(2000)).fluidOutputs(NitrogenTetroxide.getFluid(450)).chancedOutput(OreDictUnifier.get(dustSmall, Ash, 3), 10, 0).buildAndRegister();
        //Rocket fuel tier 3
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(NitricAcid.getFluid(25), MonoMethylHydrazine.getFluid(25)).fluidOutputs(RocketFuelCN3H7O3.getFluid(50)).buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(480).EUt(240).input(dust, Carbon).fluidInputs(Hydrogen.getFluid(1000), Hydrazine.getFluid(1000)).fluidOutputs(MonoMethylHydrazine.getFluid(1500)).buildAndRegister();
        //Rocket fuel tier 2
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(Hydrazine.getFluid(40), Methanol.getFluid(60)).fluidOutputs(DenseHydrazineFuelMixture.getFluid(100)).buildAndRegister();
        //rocket fuel tier 1
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240).fluidInputs(LiquidOxygen.getFluid(1000), RP1.getFluid(5)).fluidOutputs(RP1RocketFuel.getFluid(5)).buildAndRegister();

        //Rocket fuel chemical
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).fluidInputs(Ammonia.getFluid(1000), HydrogenPeroxide.getFluid(1000)).fluidOutputs(Hydrazine.getFluid(1000), Water.getFluid(100)).buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(600).EUt(240).fluidInputs(Air.getFluid(15000), EthylAnthraHydroQuinone.getFluid(5000), Anthracene.getFluid(1000)).fluidOutputs(HydrogenPeroxide.getFluid(2000), EthylAnthraQuinone.getFluid(4000)).buildAndRegister();
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

        DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(120).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Kerosene.getFluid(4)).fluidOutputs(RP1.getFluid(2)).buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().duration(25).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(SulfuricCoalTarOil.getFluid(20)).fluidOutputs(Naphtalene.getFluid(20)).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).fluidInputs(SulfuricAcid.getFluid(8000), CoalTarOil.getFluid(8000)).fluidOutputs(SulfuricCoalTarOil.getFluid(16000)).buildAndRegister();


        //Assline Recipes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, HSSG, 2), OreDictUnifier.get(ring, HSSG, 4), OreDictUnifier.get(valueOf("round"), HSSG, 16), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(wireFine, AnnealedCopper, 64), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(ELECTRIC_MOTOR_LUV.getStackForm()).duration(600).EUt(10240).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(stickLong, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, HSSE, 2), OreDictUnifier.get(ring, HSSE, 4), OreDictUnifier.get(valueOf("round"), HSSE, 16), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(wireFine, Platinum, 64), OreDictUnifier.get(cableGtQuadruple, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(ELECTRIC_MOTOR_ZPM.getStackForm()).duration(600).EUt(40960).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(block, NeodymiumMagnetic, 1), OreDictUnifier.get(stickLong, Neutronium, 2), OreDictUnifier.get(ring, Neutronium, 4), OreDictUnifier.get(valueOf("round"), Neutronium, 16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 64), OreDictUnifier.get(cableGtQuadruple, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(ELECTRIC_MOTOR_UV.getStackForm()).duration(600).EUt(163840).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(pipeSmall, Ultimet, 2), OreDictUnifier.get(screw, HSSG, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(ELECTRIC_PUMP_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(pipeMedium, Ultimet, 2), OreDictUnifier.get(screw, HSSE, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(ELECTRIC_PUMP_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(pipeLarge, Ultimet, 2), OreDictUnifier.get(screw, Neutronium, 8), OreDictUnifier.get(ring, SiliconeRubber, 16), OreDictUnifier.get(rotor, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(ELECTRIC_PUMP_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_LUV.getStackForm(2), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gear, HSSG, 4), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(StyreneButadieneRubber.getFluid(1440), Lubricant.getFluid(250)).outputs(CONVEYOR_MODULE_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gear, HSSE, 4), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(750)).outputs(CONVEYOR_MODULE_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_UV.getStackForm(2), OreDictUnifier.get(plate, Neutronium, 8), OreDictUnifier.get(gear, Neutronium, 4), OreDictUnifier.get(stick, Neutronium, 4), OreDictUnifier.get(ingot, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(StyreneButadieneRubber.getFluid(2880), Lubricant.getFluid(2000)).outputs(CONVEYOR_MODULE_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_LUV.getStackForm(), OreDictUnifier.get(plate, HSSG, 8), OreDictUnifier.get(gearSmall, HSSG, 8), OreDictUnifier.get(stick, HSSG, 4), OreDictUnifier.get(ingot, HSSG, 2), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)).fluidInputs(SolderingAlloy.getFluid(144), Lubricant.getFluid(250)).outputs(ELECTRIC_PISTON_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(plate, HSSE, 8), OreDictUnifier.get(gearSmall, HSSE, 8), OreDictUnifier.get(stick, HSSE, 4), OreDictUnifier.get(ingot, HSSE, 2), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)).fluidInputs(SolderingAlloy.getFluid(288), Lubricant.getFluid(750)).outputs(ELECTRIC_PISTON_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(plate, Neutronium, 8), OreDictUnifier.get(gearSmall, Neutronium, 8), OreDictUnifier.get(stick, Neutronium, 4), OreDictUnifier.get(ingot, Neutronium, 2), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)).fluidInputs(SolderingAlloy.getFluid(1296), Lubricant.getFluid(2000)).outputs(ELECTRIC_PISTON_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, YttriumBariumCuprate, 16), OreDictUnifier.get(screw, HSSG, 16), OreDictUnifier.get(stick, HSSG, 16), OreDictUnifier.get(ingot, HSSG), ELECTRIC_MOTOR_LUV.getStackForm(2), ELECTRIC_PISTON_LUV.getStackForm()).input(circuit, Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576), Lubricant.getFluid(250)).outputs(ROBOT_ARM_LUV.getStackForm()).duration(600).EUt(20480).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, VanadiumGallium, 16), OreDictUnifier.get(screw, HSSE, 16), OreDictUnifier.get(stick, HSSE, 16), OreDictUnifier.get(ingot, HSSE), ELECTRIC_MOTOR_ZPM.getStackForm(2), ELECTRIC_PISTON_ZPM.getStackForm()).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(1152), Lubricant.getFluid(750)).outputs(ROBOT_ARM_ZPM.getStackForm()).duration(600).EUt(81920).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(cableGtDouble, NiobiumTitanium, 16), OreDictUnifier.get(screw, Neutronium, 16), OreDictUnifier.get(stick, Neutronium, 16), OreDictUnifier.get(ingot, Neutronium), ELECTRIC_MOTOR_UV.getStackForm(2), ELECTRIC_PISTON_UV.getStackForm()).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(2304), Lubricant.getFluid(2000)).outputs(ROBOT_ARM_UV.getStackForm()).duration(600).EUt(327680).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), EMITTER_IV.getStackForm(2), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(EMITTER_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), EMITTER_LUV.getStackForm(2), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, VanadiumGallium, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(EMITTER_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), EMITTER_ZPM.getStackForm(2), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(wireGtDouble, NiobiumTitanium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(EMITTER_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), SENSOR_IV.getStackForm(2), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(foil, Electrum, 64), OreDictUnifier.get(wireGtDouble, YttriumBariumCuprate, 8), OreDictUnifier.get(gemExquisite, Ruby, 2)).input(circuit, MarkerMaterials.Tier.Extreme, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(SENSOR_LUV.getStackForm()).duration(600).EUt(15360).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), SENSOR_LUV.getStackForm(2), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(foil, Platinum, 64), OreDictUnifier.get(wireGtDouble, VanadiumGallium, 8), OreDictUnifier.get(gemExquisite, Emerald, 2)).input(circuit, Tier.Elite, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(SENSOR_ZPM.getStackForm()).duration(600).EUt(61440).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), SENSOR_ZPM.getStackForm(2), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(foil, Osmiridium, 64), OreDictUnifier.get(wireGtDouble, NiobiumTitanium, 8), OreDictUnifier.get(gemExquisite, Diamond, 2)).input(circuit, Tier.Master, 8).fluidInputs(SolderingAlloy.getFluid(576)).outputs(SENSOR_UV.getStackForm()).duration(600).EUt(245760).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSG, 1), QUANTUM_STAR.getStackForm(), EMITTER_LUV.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Master, 16).fluidInputs(SolderingAlloy.getFluid(576)).outputs(FIELD_GENERATOR_LUV.getStackForm()).duration(600).EUt(30720).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, HSSE, 1), QUANTUM_STAR.getStackForm(), EMITTER_ZPM.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Ultimate, 16).fluidInputs(SolderingAlloy.getFluid(1152)).outputs(FIELD_GENERATOR_ZPM.getStackForm()).duration(600).EUt(245760).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(frameGt, Neutronium, 1), GRAVI_STAR.getStackForm(), EMITTER_UV.getStackForm(4), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(wireFine, Osmium, 64), OreDictUnifier.get(cableGtOctal, YttriumBariumCuprate, 4)).input(circuit, Tier.Superconductor, 16).fluidInputs(SolderingAlloy.getFluid(2304)).outputs(FIELD_GENERATOR_UV.getStackForm()).duration(600).EUt(491520).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MASTER_BOARD.getStackForm(), STEM_CELLS.getStackForm(8), GLASS_TUBE.getStackForm(8), OreDictUnifier.get(foil, SiliconeRubber, 64)).input(plate, Gold, 8).input(plate, StainlessSteel, 4).fluidInputs(SterileGrowthMedium.getFluid(100), UUMatter.getFluid(20), DistilledWater.getFluid(4000)).outputs(NEURO_PROCESSOR.getStackForm(8)).duration(200).EUt(20000).buildAndRegister();

        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe recipe : ASSEMBLER_RECIPES.getRecipeList()) {
            if (recipe.getOutputs().get(0).isItemEqual(WETWARE_PROCESSOR_LUV.getStackForm()) || recipe.getOutputs().get(0).isItemEqual(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())) {
                recipes.add(recipe);
            }
        }
        recipes.forEach(recipe -> ASSEMBLER_RECIPES.removeRecipe(recipe));


        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? MAX_BATTERY : ZPM2).getStackForm();

        if (GAConfig.GT5U.enableZPMandUVBats) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Europium, 16), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), ENERGY_LAPOTRONIC_ORB2.getStackForm(8), FIELD_GENERATOR_LUV.getStackForm(2), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), SMD_DIODE.getStackForm(8), OreDictUnifier.get(cableGtSingle, Naquadah, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(8000)).outputs(ENERGY_MODULE.getStackForm()).duration(2000).EUt(100000).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Americium, 16), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), WETWARE_SUPER_COMPUTER_UV.getStackForm(), ENERGY_MODULE.getStackForm(8), FIELD_GENERATOR_ZPM.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(cableGtSingle, NaquadahAlloy, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(ENERGY_CLUSTER.getStackForm()).duration(2000).EUt(200000).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), ENERGY_CLUSTER.getStackForm(8), FIELD_GENERATOR_UV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000), Naquadria.getFluid(1152)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();
        } else
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), ENERGY_LAPOTRONIC_ORB2.getStackForm(8), FIELD_GENERATOR_UV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), OreDictUnifier.get(plate, Plutonium241), OreDictUnifier.get(plate, NetherStar), FIELD_GENERATOR_IV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32), OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).input(circuit, Tier.Ultimate).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[0].getStackForm()).duration(1000).EUt(30000).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), OreDictUnifier.get(plate, Europium, 4), FIELD_GENERATOR_LUV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(48), OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 32)).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).input(circuit, Tier.Superconductor).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[1].getStackForm()).duration(1000).EUt(60000).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), WETWARE_MAINFRAME_MAX.getStackForm(), OreDictUnifier.get(plate, Americium, 4), FIELD_GENERATOR_ZPM.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), OreDictUnifier.get(wireGtQuadruple, Tier.Superconductor, 32)).fluidInputs(SolderingAlloy.getFluid(2880)).outputs(GATileEntities.FUSION_REACTOR[2].getStackForm()).duration(1000).EUt(90000).buildAndRegister();

        //Star Recipes
        CHEMICAL_RECIPES.recipeBuilder().duration(60000).EUt(8).input(ingot, Plutonium, 3).outputs(OreDictUnifier.get(dust, Plutonium, 3)).fluidOutputs(Radon.getFluid(50)).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680).inputs(new ItemStack(Items.NETHER_STAR)).fluidInputs(Neutronium.getFluid(288)).outputs(GRAVI_STAR.getStackForm()).buildAndRegister();

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
        FUSION_RECIPES.recipeBuilder().fluidInputs(PositiveMatter.getFluid(1), NeutralMatter.getFluid(1)).fluidOutputs(Neutronium.getFluid(2)).duration(200).EUt(98304).EUToStart(600000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Tungsten.getFluid(16), Helium.getFluid(16)).fluidOutputs(Osmium.getFluid(16)).duration(64).EUt(24578).EUToStart(150000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Manganese.getFluid(16), Hydrogen.getFluid(16)).fluidOutputs(Iron.getFluid(16)).duration(64).EUt(8192).EUToStart(120000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Mercury.getFluid(16), Magnesium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Aluminium.getFluid(16)).fluidOutputs(Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(240000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Uranium.getFluid(16), Helium.getFluid(16)).fluidOutputs(Plutonium.getFluid(16)).duration(128).EUt(49152).EUToStart(480000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Vanadium.getFluid(16), Hydrogen.getFluid(125)).fluidOutputs(Chrome.getFluid(16)).duration(64).EUt(24576).EUToStart(140000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gallium.getFluid(16), Radon.getFluid(125)).fluidOutputs(Duranium.getFluid(16)).duration(64).EUt(16384).EUToStart(140000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Titanium.getFluid(48), Duranium.getFluid(32)).fluidOutputs(Tritanium.getFluid(16)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Gold.getFluid(16), Mercury.getFluid(16)).fluidOutputs(Radon.getPlasma(125)).duration(64).EUt(32768).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Tantalum.getFluid(16), Tritium.getFluid(16)).fluidOutputs(Tungsten.getFluid(16)).duration(16).EUt(24576).EUToStart(200000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Silver.getFluid(16), Lithium.getFluid(16)).fluidOutputs(Indium.getFluid(16)).duration(32).EUt(24576).EUToStart(380000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(NaquadahEnriched.getFluid(15), Radon.getFluid(125)).fluidOutputs(Naquadria.getFluid(3)).duration(64).EUt(49152).EUToStart(400000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Lithium.getFluid(16), Tungsten.getFluid(16)).fluidOutputs(Iridium.getFluid(16)).duration(32).EUt(32768).EUToStart(300000000).buildAndRegister();
        FUSION_RECIPES.recipeBuilder().fluidInputs(Lanthanum.getFluid(16), Silicon.getFluid(16)).fluidOutputs(Lutetium.getFluid(16)).duration(16).EUt(8192).EUToStart(80000000).buildAndRegister();

        //FUsion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING), "PhP", "PHP", "PwP", 'P', "plateTungstenSteel", 'H', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV));
        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2), "PhP", "PHP", "PwP", 'P', "plateAmericium", 'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).input(plate, Americium, 6).outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING_MK2)).duration(50).buildAndRegister();

        ModHandler.addShapedRecipe("fusion_coil", MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), "CRC", "FSF", "CRC", 'C', "circuitMaster", 'R', NEUTRON_REFLECTOR.getStackForm(), 'F', FIELD_GENERATOR_MV.getStackForm(), 'S', MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR));

        //Explosive Recipes
        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));
        ModHandler.removeRecipes(DYNAMITE.getStackForm());
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.PAPER), new ItemStack(Items.STRING)).fluidInputs(Glyceryl.getFluid(500)).outputs(DYNAMITE.getStackForm()).buildAndRegister();

        //Lapotron Crystal Recipes
        for (MaterialStack m : lapisLike) {
            GemMaterial gem = (GemMaterial) m.material;
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(), LAPOTRON_CRYSTAL.getStackForm(), OreDictUnifier.get(gemExquisite, Sapphire), OreDictUnifier.get(stick, gem), CAPACITOR.getStackForm());
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
                PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustSmall, m, 4).notConsumable(SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, m)).buildAndRegister();
                PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustTiny, m, 9).notConsumable(SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, m)).buildAndRegister();
            }
        }

        //Schematic Recipes
        ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4).input(circuit, Tier.Good, 4).input(plate, StainlessSteel, 2).outputs(SCHEMATIC.getStackForm()).buildAndRegister();
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_1"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_c"));

        //Configuration Circuit
        //ModHandler.removeRecipes(MetaItems.BASIC_CIRCUIT_LV.getStackForm());
        ModHandler.removeRecipes(INTEGRATED_CIRCUIT.getStackForm());
        ModHandler.addShapelessRecipe("basic_to_configurable_circuit", INTEGRATED_CIRCUIT.getStackForm(), "circuitBasic");

        //MAX Machine Hull
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_max"));
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

        //Improved Superconductor recipes
        MIXER_RECIPES.recipeBuilder().duration(1200).EUt(120).input(dust, Cadmium, 5).input(dust, Magnesium).fluidInputs(Oxygen.getFluid(6000)).outputs(OreDictUnifier.get(dust, MVSuperconductorBase, 12)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(2400).EUt(120).input(dust, Titanium).input(dust, Barium, 9).input(dust, Copper, 10).fluidInputs(Oxygen.getFluid(20000)).outputs(OreDictUnifier.get(dust, HVSuperconductorBase, 40)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(480).input(dust, Uranium).input(dust, Platinum, 3).outputs(OreDictUnifier.get(dust, EVSuperconductorBase, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(480).input(dust, Vanadium).input(dust, Indium, 3).outputs(OreDictUnifier.get(dust, IVSuperconductorBase, 4)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(2400).EUt(1920).input(dust, Indium, 4).input(dust, Bronze, 8).input(dust, Barium, 2).input(dust, Titanium).fluidInputs(Oxygen.getFluid(14000)).outputs(OreDictUnifier.get(dust, LuVSuperconductorBase, 29)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(1200).EUt(1920).input(dust, Naquadah, 4).input(dust, Indium, 2).input(dust, Palladium, 6).input(dust, Osmium).outputs(OreDictUnifier.get(dust, ZPMSuperconductorBase, 13)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8).input(dust, Lead, 3).input(dust, Platinum).input(dust, EnderPearl, 4).outputs(OreDictUnifier.get(dust, Enderium, 4)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120).inputs(OreDictUnifier.get(wireGtSingle, MVSuperconductorBase, 3), OreDictUnifier.get(pipeTiny, StainlessSteel, 2), ELECTRIC_PUMP_MV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, MVSuperconductor, 3)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256).inputs(OreDictUnifier.get(wireGtSingle, HVSuperconductorBase, 3), OreDictUnifier.get(pipeTiny, Titanium, 2), ELECTRIC_PUMP_HV.getStackForm()).fluidInputs(Nitrogen.getFluid(2000)).outputs(OreDictUnifier.get(wireGtSingle, HVSuperconductor, 3)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(OreDictUnifier.get(wireGtSingle, EVSuperconductorBase, 9), OreDictUnifier.get(pipeTiny, TungstenSteel, 6), ELECTRIC_PUMP_EV.getStackForm(2)).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, EVSuperconductor, 9)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920).inputs(OreDictUnifier.get(wireGtSingle, IVSuperconductorBase, 6), OreDictUnifier.get(pipeTiny, NiobiumTitanium, 4), ELECTRIC_PUMP_IV.getStackForm()).fluidInputs(Nitrogen.getFluid(4000)).outputs(OreDictUnifier.get(wireGtSingle, IVSuperconductor, 6)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680).inputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductorBase, 8), OreDictUnifier.get(pipeTiny, Enderium, 5), ELECTRIC_PUMP_LUV.getStackForm()).fluidInputs(Nitrogen.getFluid(6000)).outputs(OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720).inputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductorBase, 16), OreDictUnifier.get(pipeTiny, Naquadah, 6), ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(8000)).outputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductor, 16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880).inputs(OreDictUnifier.get(wireGtSingle, ZPMSuperconductorBase, 32), OreDictUnifier.get(pipeTiny, Ultimet, 7), ELECTRIC_PUMP_ZPM.getStackForm()).fluidInputs(Nitrogen.getFluid(10000)).outputs(OreDictUnifier.get(wireGtSingle, Tier.Superconductor, 32)).buildAndRegister();

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
        else if (!GAConfig.GT6.BendingFoilsAutomatic || !GAConfig.GT6.BendingCylinders)
            BENDER_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(MICA_INSULATOR_SHEET.getStackForm()).circuitMeta(1).outputs(MICA_INSULATOR_FOIL.getStackForm(4)).buildAndRegister();

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

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, Cupronickel, 8), OreDictUnifier.get(dust, AluminoSilicateWool, 12)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(OreDictUnifier.get(wireGtDouble, Cupronickel, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tin.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30).inputs(OreDictUnifier.get(wireGtDouble, Kanthal, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Copper.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120).inputs(OreDictUnifier.get(wireGtDouble, Nichrome, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Aluminium.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(OreDictUnifier.get(wireGtDouble, TungstenSteel, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Nichrome.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TUNGSTENSTEEL)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(OreDictUnifier.get(wireGtDouble, HSSG, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Tungsten.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096).inputs(OreDictUnifier.get(wireGtDouble, Naquadah, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(HSSG.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680).inputs(OreDictUnifier.get(wireGtDouble, NaquadahAlloy, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(Naquadah.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH_ALLOY)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).inputs(OreDictUnifier.get(wireGtDouble, Tier.Superconductor, 8), MICA_INSULATOR_FOIL.getStackForm(8)).fluidInputs(NaquadahAlloy.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001).inputs(OreDictUnifier.get(wireGtDouble, LuVSuperconductor, 32), MICA_INSULATOR_FOIL.getStackForm(16)).fluidInputs(NaquadahAlloy.getFluid(144)).outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR)).buildAndRegister();


        //Nuclear react recipe
        BOILING_THORIUM_REACTOR_RECIPES.recipeBuilder().duration(2000).EUt(480).input(stick, Thorium, 1).outputs(OreDictUnifier.get(dustTiny, Thorium, 3), OreDictUnifier.get(dustTiny, Uranium, 2), OreDictUnifier.get(dustTiny, Uranium235, 1)).buildAndRegister();
        BOILING_URANIUM_REACTOR_RECIPES.recipeBuilder().duration(4000).EUt(480).input(stick, Uranium235, 1).outputs(OreDictUnifier.get(dustTiny, Plutonium, 3), OreDictUnifier.get(dustTiny, Plutonium241, 1)).buildAndRegister();
        BOILING_PLUTONIUM_REACTOR_RECIPES.recipeBuilder().duration(6000).EUt(480).input(stick, Plutonium241, 1).outputs(OreDictUnifier.get(dustTiny, Naquadah, 3), OreDictUnifier.get(dustTiny, Americium, 1)).buildAndRegister();

        //Platinum Process

        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(775)
                .input(dust, IrLeachResidue)
                .outputs(OreDictUnifier.get(dust, IridiumDioxide))
                .outputs(OreDictUnifier.get(dust, PGSDResidue))
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumDioxide)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AcidicIridiumSolution.getFluid(1000))
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcidicIridiumSolution.getFluid(1000))
                .fluidInputs(AmmoniumChloride.getFluid(3000))
                .fluidOutputs(Ammonia.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, IridiumChloride))
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumChloride)
                .input(dust, Calcium, 3)
                .fluidOutputs(CalciumChloride.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, PGSDResidue2))
                .outputs(OreDictUnifier.get(dust, Iridium))
                .duration(300)
                .EUt(1920)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumMetallicPowder)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(PlatinumConcentrate.getFluid(1000))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumResidue))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(DilutedSulfuricAcid.getFluid(1000))
                .fluidOutputs(AquaRegia.getFluid(1000))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium, 2)
                .input(dust, Sulfur, 2)
                .fluidInputs(Oxygen.getFluid(7000))
                .outputs(OreDictUnifier.get(dust, PotassiumDisulfate, 11))
                .EUt(90)
                .duration(42)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumResidue)
                .fluidInputs(PotassiumDisulfate.getFluid(360))
                .outputs(OreDictUnifier.get(dust, LeachResidue))
                .fluidOutputs(RhodiumSulfate.getFluid(360))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, Saltpeter, 10)
                .input(dust, LeachResidue, 10)
                .fluidInputs(SaltWater.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, IrOsLeachResidue))
                .outputs(OreDictUnifier.get(dust, SodiumRuthenate, 3))
                .fluidOutputs(Steam.getFluid(1000))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, IrOsLeachResidue)
                .fluidOutputs(AcidicOsmiumSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, IrLeachResidue))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumConcentrate.getFluid(2000))
                .fluidInputs(AmmoniumChloride.getFluid(200))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumSaltCrude, 16))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumRawPowder, 2))
                .fluidOutputs(PalladiumAmmonia.getFluid(200))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .EUt(30)
                .duration(1200)
                .buildAndRegister();

        //PTSalt

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltCrude)
                .chancedOutput(OreDictUnifier.get(dust, PlatinumSaltRefined), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltRefined)
                .outputs(OreDictUnifier.get(dust, PlatinumMetallicPowder))
                .fluidOutputs(Chlorine.getFluid(87))
                .EUt(120)
                .blastFurnaceTemp(775)
                .duration(200)
                .buildAndRegister();

        //Platinum

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumRawPowder, 2)
                .input(dust, Calcium)
                .outputs(OreDictUnifier.get(dust, Platinum, 2))
                .outputs(OreDictUnifier.get(dust, CalciumChloride))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        //Palldium

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder)
                .fluidOutputs(PalladiumAmmonia.getFluid(1000))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PalladiumSalt))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PalladiumSalt)
                .chancedOutput(OreDictUnifier.get(dust, PalladiumMetallicPowder), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder)
                .outputs(OreDictUnifier.get(dustTiny, PalladiumSalt, 16))
                .outputs(OreDictUnifier.get(dustTiny, PalladiumRawPowder, 2))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumRawPowder, 2)
                .fluidInputs(FormicAcid.getFluid(4000))
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Palladium, 2))
                .EUt(1920)
                .duration(300)
                .buildAndRegister();

        //Formic acid
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .fluidOutputs(Sodiumformate.getFluid(1000))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Sodiumformate.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FormicAcid.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Sodiumsulfate, 7))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        //Rhodium
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfate.getFluid(11000))
                .fluidInputs(Water.getFluid(10000))
                .fluidOutputs(Potassium.getFluid(2000))
                .fluidOutputs(RhodiumSulfateSolution.getFluid(11000))
                .outputs(OreDictUnifier.get(dustTiny, LeachResidue, 10))
                .EUt(30)
                .duration(1200)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfateSolution.getFluid(1000))
                .input(dust, Zinc)
                .outputs(OreDictUnifier.get(dust, ZincSulfate, 6))
                .outputs(OreDictUnifier.get(dust, CrudeRhodiumMetall))
                .EUt(30)
                .duration(300)
                .buildAndRegister();


        BLAST_RECIPES.recipeBuilder()
                .input(dust, CrudeRhodiumMetall)
                .input(dust, Salt)
                .fluidInputs(Chlorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, RhodiumSalt))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumSalt)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(RhodiumSaltSolution.getFluid(200))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium)
                .fluidInputs(NitricAcid.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, SodiumNitrate, 2))
                .EUt(60)
                .duration(8)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSaltSolution.getFluid(1000))
                .input(dust, SodiumNitrate)
                .outputs(OreDictUnifier.get(dust, Salt))
                .outputs(OreDictUnifier.get(dust, RhodiumNitrate))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, RhodiumNitrate)
                .chancedOutput(OreDictUnifier.get(dust, RhodiumFilterCake), 9500, 0)
                .EUt(30)
                .duration(600)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumFilterCake)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(RhodiumFilterCakeSolution.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumFilterCakeSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, ReRhodium))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ReRhodium)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Rhodium))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        //Ruthenium
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumRuthenate, 2)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(RutheniumTetroxideSolution.getFluid(3000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();
        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(Steam.getFluid(1000))
                .fluidInputs(RutheniumTetroxideSolution.getFluid(1000))
                .fluidOutputs(HotRutheniumTetroxideSolution.getFluid(2000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HotRutheniumTetroxideSolution.getFluid(9000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .fluidOutputs(RutheniumTetroxide.getFluid(7200))
                .fluidOutputs(Water.getFluid(1800))
                .duration(1500)
                .EUt(480)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(RutheniumTetroxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, RutheniumTetroxide))
                .EUt(8)
                .duration(16)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RutheniumTetroxide)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(6000))
                .outputs(OreDictUnifier.get(dust, Ruthenium))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        //Osmium
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(AcidicOsmiumSolution.getFluid(1000))
                .fluidOutputs(OsmiumSolution.getFluid(100))
                .fluidOutputs(Water.getFluid(900))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(OsmiumSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .outputs(OreDictUnifier.get(dust, Osmium))
                .fluidOutputs(Chlorine.getFluid(7000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, PGSDResidue, 5)
                .outputs(OreDictUnifier.get(dust, SiliconDioxide, 3))
                .outputs(OreDictUnifier.get(dust, Gold, 2))
                .EUt(10)
                .duration(226)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, PGSDResidue2, 2)
                .outputs(OreDictUnifier.get(dust, Nickel))
                .outputs(OreDictUnifier.get(dust, Copper))
                .EUt(10)
                .duration(60)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, Rhodium, 1)
                .input(dust, Palladium, 3)
                .outputs(OreDictUnifier.get(dust, RhodiumPlatedPalladium, 4))
                .EUt(10)
                .duration(100)
                .buildAndRegister();

        //platics
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Diaminobenzidine.getFluid(1000))
                .fluidInputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(Phenol.getFluid(1000))
                .fluidOutputs(Polybenzimidazole.getFluid(1000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Phenol.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .fluidOutputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .EUt(7500)
                .duration(1000)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dustTiny, Potassiumdichromate)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidInputs(Dimethylbenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(PhthalicAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Zinc)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Dichlorobenzidine.getFluid(1000))
                .fluidOutputs(Diaminobenzidine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dustTiny, Copper)
                .fluidInputs(Nitrochlorobenzene.getFluid(1000))
                .fluidOutputs(Dichlorobenzidine.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, ZincSulfate, 6)
                .outputs(OreDictUnifier.get(dust, Zinc))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidInputs(Oxygen.getFluid(4000))
                .EUt(90)
                .duration(26)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Chrome)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, ChromiumTrioxide, 1))
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ChromiumTrioxide, 2)
                .input(dust, PotassiumNitrade, 2)
                .outputs(OreDictUnifier.get(dust, Potassiumdichromate))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium)
                .fluidInputs(NitricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PotassiumNitrade))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(Nitrochlorobenzene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(30)
                .duration(240)
                .buildAndRegister();


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
                            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !hasPrefix(recipe.getRecipeOutput(), "dust", "dustTiny") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager3x3Recipes) {
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
                    FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9) {
                if (!recipesToRemove.contains(recipe.getRegistryName()) && GAConfig.Misc.Unpackager3x3Recipes) {
                    UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).inputs(new CountableIngredient(new IntCircuitIngredient(1), 0)).outputs(recipe.getRecipeOutput()).buildAndRegister();
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
            FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(block, NetherQuartz)).outputs(OreDictUnifier.get(gem, NetherQuartz, 4)).buildAndRegister();
            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(OrePrefix.gem, Materials.NetherQuartz, 4).outputs(new ItemStack(Blocks.QUARTZ_BLOCK)).buildAndRegister();

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
                    CUTTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).fluidInputs(Lubricant.getFluid(1)).outputs(GTUtility.copyAmount(6, recipe.getRecipeOutput()), OreDictUnifier.get(dust, Wood, 2)).buildAndRegister();
                }
                if (OreDictionary.getOreName(i).equals("slabWood") && recipe.getRecipeOutput().getCount() == 6) {
                    CUTTER_RECIPES.recipeBuilder().duration(50).EUt(4).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(GTUtility.copyAmount(2, recipe.getRecipeOutput())).buildAndRegister();
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
