package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.item.GAExplosive;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.materials.SimpleDustMaterialStack;
import gregicadditions.recipes.categories.*;
import gregicadditions.recipes.categories.circuits.CircuitRecipes;
import gregicadditions.recipes.categories.machines.MachineCraftingRecipes;
import gregicadditions.recipes.chain.*;
import gregicadditions.recipes.impl.LargeRecipeBuilder;
import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.recipes.*;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Color.White;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.NO_SMASHING;
import static gregtech.api.unification.material.type.GemMaterial.MatFlags.CRYSTALLISABLE;
import static gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_SMALL_GEAR;
import static gregtech.api.unification.material.type.Material.MatFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;

/**
 * Primary Recipe Registration Class
 */
public class RecipeHandler {

    /**
     * Used for Gem Implosion
     */
    private static final ItemStack[] EXPLOSIVES = new ItemStack[]{
            GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.POWDER_BARREL, 48),
            new ItemStack(Blocks.TNT, 24),
            MetaItems.DYNAMITE.getStackForm(12),
            GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.ITNT, 6)};

    /**
     * GT Material Handler registration.
     * This probably isn't the method you are looking for.
     */
    public static void register() {

        gtMetalCasing.addProcessingHandler(IngotMaterial.class, RecipeHandler::processMetalCasing);
        turbineBlade.addProcessingHandler(IngotMaterial.class, RecipeHandler::processTurbine);
        ingot.addProcessingHandler(IngotMaterial.class, RecipeHandler::processIngot);
        plateDense.addProcessingHandler(IngotMaterial.class, RecipeHandler::processDensePlate);

        if (GAConfig.GT6.addCurvedPlates)
            plateCurved.addProcessingHandler(IngotMaterial.class, RecipeHandler::processPlateCurved);

        if (GAConfig.GT6.addRounds)
            round.addProcessingHandler(IngotMaterial.class, RecipeHandler::processRound);

        plateDouble.addProcessingHandler(IngotMaterial.class, RecipeHandler::processDoublePlate);

        if (GAConfig.GT6.BendingCylinders)
            ring.addProcessingHandler(IngotMaterial.class, RecipeHandler::processRing);

        dustSmall.addProcessingHandler(DustMaterial.class, RecipeHandler::processSmallDust);
        dustTiny.addProcessingHandler(DustMaterial.class, RecipeHandler::processTinyDust);
        nugget.addProcessingHandler(IngotMaterial.class, RecipeHandler::processNugget);

        if (GAConfig.GT5U.stickGT5U)
            stick.addProcessingHandler(DustMaterial.class, RecipeHandler::processStick);

        dust.addProcessingHandler(GemMaterial.class, RecipeHandler::processGem);
        foil.addProcessingHandler(IngotMaterial.class, RecipeHandler::processFoil);
        rotor.addProcessingHandler(IngotMaterial.class, RecipeHandler::processRotor);
        lens.addProcessingHandler(GemMaterial.class, RecipeHandler::processLens);
        spring.addProcessingHandler(IngotMaterial.class, RecipeHandler::processSpring);
        springSmall.addProcessingHandler(IngotMaterial.class, RecipeHandler::processSpringSmall);
        gearSmall.addProcessingHandler(IngotMaterial.class, RecipeHandler::processGearSmall);
        gear.addProcessingHandler(IngotMaterial.class, RecipeHandler::processGear);
        ingotHot.addProcessingHandler(IngotMaterial.class, RecipeHandler::processIngotHot);

        pipeTiny.addProcessingHandler(IngotMaterial.class, RecipeHandler::processTinyPipe);
        pipeSmall.addProcessingHandler(IngotMaterial.class, RecipeHandler::processSmallPipe);
        pipeMedium.addProcessingHandler(IngotMaterial.class, RecipeHandler::processMediumPipe);
        pipeLarge.addProcessingHandler(IngotMaterial.class, RecipeHandler::processLargePipe);

        dust.addProcessingHandler(FluidMaterial.class, RecipeHandler::processPlasma);
    }


    /**
     * Main Method for initializing recipe chains,
     * located in the "recipes/chain" directory.
     */
    public static void initChains() {
        GoldChain.init();
        NaquadahChain.init();
        NuclearChain.init();
        PolymerChain.init();
        PlatinumSludgeGroupChain.init();
        TungstenChain.init();
        REEChain.init();
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
        BrineChain.init();
        FusionElementsChain.init();
        NanotubeChain.init();
        VariousChains.init();
        SuperconductorsSMDChain.init();
        FusionComponents.init();
        NiobiumTantalumChain.init();
        Dyes.init();
        SensorEmitter.init();
        SeleniumChain.init();
        WormholeGeneratorChain.init();
        CosmicChain.init();
        UltimateMaterials.init();
        DigitalInterfaces.init();
        InsulationWireAssemblyChain.init();
        ArcFurnaceOxidation.init();
        WetwareChain.init();
        OpticalChain.init();
        CombinedChains.init();
        OrganometallicChains.init();
        QuantumDotsChain.init();
        HNIWChain.init();
        CasingConvesion.init();
    }

    /**
     * Main Recipe Addition Method for recipes outside of autogenerated
     * recipes and chemistry chains.
     */
    public static void initRecipes() {
        RecipeOverride.init();
        CircuitRecipes.init();
        MachineCraftingRecipes.init();
        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MiscRecipes.init();
    }

    /**
     * Large Machine Recipes.
     * These are run after other RecipeMaps are finalized
     * since they often take recipes from other maps.
     */
    public static void registerLargeMachineRecipes() {

        registerLargeMachineRecipes(CHEMICAL_RECIPES, LARGE_CHEMICAL_RECIPES);
        registerLargeMachineRecipes(LASER_ENGRAVER_RECIPES, LARGE_ENGRAVER_RECIPES);
        registerLargeMachineRecipes(CENTRIFUGE_RECIPES, LARGE_CENTRIFUGE_RECIPES);

        registerLargeMixerRecipes();
        registerAlloyBlastRecipes();
        registerChemicalPlantRecipes();
        registerGreenHouseRecipes();
    }

    /**
     * Ingot Material Handler. Generates:
     *
     * + Mixer Recipes for GTCE Materials we add
     * + Bending Cylinder Recipes
     * + GT6 Wrench Recipes (plates over ingots)
     * + Ingot -> Nugget Alloy Smelter recipes
     * + Block -> Ingot Alloy Smelter recipes
     */
    private static void processIngot(OrePrefix ingot, IngotMaterial material) {

        // Ingot Composition
        processIngotComposition(material);

        // Tools
        if (!material.hasFlag(NO_SMASHING) && material.toolDurability > 0) {

            // GT6 Expensive Wrenches (Plates over Ingots)
            if (GAConfig.GT6.ExpensiveWrenches) {

                removeRecipeByName(String.format("gtadditions:wrench_%s", material.toString()));

                ModHandler.addShapedRecipe(String.format("ga_wrench_%s", material.toString()), MetaItems.WRENCH.getStackForm(material),
                        "XhX", "XXX", " X ",
                        'X', new UnificationEntry(plate, material));
            }

            // Bending Cylinders
            if (GAConfig.GT6.BendingCylinders) {

                ModHandler.addShapedRecipe(String.format("cylinder_%s", material.toString()), ((ToolMetaItem<?>.MetaToolValueItem) BENDING_CYLINDER).getStackForm(material),
                        "sfh", "XXX", "XXX",
                        'X', new UnificationEntry(ingot, material));

                ModHandler.addShapedRecipe(String.format("small_cylinder_%s", material.toString()), ((ToolMetaItem<?>.MetaToolValueItem) SMALL_BENDING_CYLINDER).getStackForm(material),
                        "sfh", "XXX",
                        'X', new UnificationEntry(ingot, material));
            }
        }

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(8).duration((int) material.getAverageMass())
                .input(ingot, material)
                .notConsumable(MetaItems.SHAPE_MOLD_NUGGET.getStackForm())
                .output(nugget, material, 9)
                .buildAndRegister();

        if (!OreDictUnifier.get(block, material).isEmpty())
            ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(8).duration((int) material.getAverageMass() * 9)
                    .input(block, material)
                    .notConsumable(MetaItems.SHAPE_MOLD_INGOT.getStackForm())
                    .output(ingot, material, 9)
                    .buildAndRegister();
    }

    /**
     * Generates Mixer (or Large Mixer) recipes for GTCE Materials we add.
     */
    private static void processIngotComposition(IngotMaterial material) {
        if (material.materialComponents.size() <= 1 || material.blastFurnaceTemperature == 0 || material.hasFlag(DISABLE_AUTOGENERATED_MIXER_RECIPE))
            return;

        int fluidCount = (int) material.materialComponents.stream().filter(mat -> mat.material instanceof FluidMaterial && !(mat.material instanceof DustMaterial)).count();
        int itemCount = material.materialComponents.size() - fluidCount;

        // Try to fit in Mixer
        RecipeBuilder<?> builder;
        if (itemCount <= MIXER_RECIPES.getMaxInputs()
                && fluidCount <= MIXER_RECIPES.getMaxFluidInputs())

            builder = MIXER_RECIPES.recipeBuilder();

        // Try to fit in Large Mixer
        else if (itemCount <= LARGE_MIXER_RECIPES.getMaxInputs()
                && fluidCount <= LARGE_MIXER_RECIPES.getMaxFluidInputs())

            builder = LARGE_MIXER_RECIPES.recipeBuilder()
                    .notConsumable(new IntCircuitIngredient(material.materialComponents.size()));

        // Cannot fit in either
        else {
            GALog.logger.warn("Material {} has too many material components to generate a recipe in either normal or large mixer.", material.getUnlocalizedName());
            return;
        }

        int totalMaterial = 0;
        for (MaterialStack stack : material.materialComponents) {
            int amount = (int) stack.amount;

            if (stack.material instanceof DustMaterial)
                builder.input(dust, stack.material, amount);

            else if (stack.material instanceof FluidMaterial)
                builder.fluidInputs(((FluidMaterial) stack.material).getFluid(1000 * amount));

            else if (stack instanceof SimpleDustMaterialStack)
                builder.inputs(((SimpleDustMaterialStack) stack).simpleDustMaterial.getItemStack(amount));

            totalMaterial += amount;
        }

        builder.output(dust, material, totalMaterial)
               .duration((int) (material.getAverageMass() * totalMaterial))
               .EUt(30)
               .buildAndRegister();
    }

    /**
     * Gem Material Handler. Generates:
     *
     * + Laser Engraver Gem Recipes
     * + Implosion Compressor Gem Recipes
     * + Gem Hammer Recipes
     *
     * - Removes GTCE Gem Implosion Recipes
     */
    private static void processGem(OrePrefix dustPrefix, GemMaterial material) {

        // Gem Implosion Recipes
        if (!material.hasFlag(CRYSTALLISABLE) && !material.hasFlag(EXPLOSIVE) && !material.hasFlag(FLAMMABLE)) {

            RecipeBuilder<?> builder;

            removeRecipesByInputs(IMPLOSION_RECIPES, OreDictUnifier.get(dustPrefix, material, 4), new ItemStack(Blocks.TNT, 2));

            // It isn't uncommon for some GemMaterials to disable one or more of these prefixes.
            // Lets just check for both to be safe
            boolean hasFlawless = !OreDictUnifier.get(gemFlawless, material).isEmpty();
            boolean hasExquisite = !OreDictUnifier.get(gemExquisite, material).isEmpty();

            // Laser Engraver Recipes
            if (hasFlawless)

                // Gem -> Flawless
                LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(2000)
                        .input(gem, material, 4)
                        .notConsumable(craftingLens, White)
                        .output(gemFlawless, material)
                        .buildAndRegister();

            if (hasExquisite)

                // Flawless -> Exquisite
                LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(2000)
                        .input(gemFlawless, material, 4)
                        .notConsumable(craftingLens, White)
                        .output(gemExquisite, material)
                        .buildAndRegister();

            // Implosion Compressor Recipes
            for (ItemStack explosive : EXPLOSIVES) {

                // Dust -> Gem
                builder = IMPLOSION_RECIPES.recipeBuilder()
                        .input(dust, material, 4)
                        .output(gem, material, 3)
                        .output(dustTiny, DarkAsh, 2);
                builder .applyProperty("explosives", explosive);
                builder .buildAndRegister();

                if (hasFlawless) {

                    // Gem -> Flawless
                    builder = IMPLOSION_RECIPES.recipeBuilder()
                            .input(gem, material, 3)
                            .output(gemFlawless, material)
                            .output(dustTiny, DarkAsh, 2);
                    builder .applyProperty("explosives", explosive);
                    builder .buildAndRegister();
                }

                if (hasExquisite) {

                    // Flawless -> Exquisite
                    builder = IMPLOSION_RECIPES.recipeBuilder()
                            .input(gemFlawless, material, 3)
                            .output(gemExquisite, material)
                            .output(dustTiny, DarkAsh, 2);
                    builder .applyProperty("explosives", explosive);
                    builder .buildAndRegister();
                }
            }
        }

        // Gem Hammer Recipes
        if (!OreDictUnifier.get(toolHeadHammer, material).isEmpty()) {

            ModHandler.addMirroredShapedRecipe(String.format("gem_hammer_%s", material.toString()), MetaItems.HARD_HAMMER.getStackForm(material),
                    "GG ", "GGS", "GG ",
                    'G', new UnificationEntry(gem, material),
                    'S', new UnificationEntry(stick, Wood));
        }
    }

    /**
     * Rod Material Handler. Generates:
     *
     * + GT5U Lathe Recipes if enabled (1 rod + 2 small dust)
     *
     * - Removes old Lathe Rod Recipes if enabled
     */
    private static void processStick(OrePrefix stickPrefix, DustMaterial material) {

        // GT5U Lathe recipes
        if (material instanceof GemMaterial || material instanceof IngotMaterial) {
            OrePrefix prefix = material instanceof IngotMaterial ? ingot : gem;

            removeRecipesByInputs(LATHE_RECIPES, OreDictUnifier.get(prefix, material));

            LATHE_RECIPES.recipeBuilder().EUt(16).duration((int) Math.max(material.getAverageMass() * 2, 1))
                    .input(prefix, material)
                    .output(stickPrefix, material)
                    .output(dustSmall, material, 2)
                    .buildAndRegister();
        }
    }

    /**
     * Tiny Dust Material Handler. Generates:
     *
     * + Schematic Recipes in favor of Integrated Circuit Packager Recipes
     */
    private static void processTinyDust(OrePrefix dustTiny, DustMaterial material) {

        removeRecipesByInputs(PACKER_RECIPES, OreDictUnifier.get(dustTiny, material, 9), getIntegratedCircuit(1));

        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                .input(dustTiny, material, 9)
                .notConsumable(SCHEMATIC_DUST.getStackForm())
                .output(dust, material)
                .buildAndRegister();
    }

    /**
     * Small Dust Material Handler. Generates:
     *
     * + Schematic Recipes in favor of Integrated Circuit Packager Recipes
     */
    private static void processSmallDust(OrePrefix dustSmall, DustMaterial material) {

        removeRecipesByInputs(PACKER_RECIPES, OreDictUnifier.get(dustSmall, material, 4), getIntegratedCircuit(2));

        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                .input(dustSmall, material, 4)
                .notConsumable(SCHEMATIC_DUST.getStackForm())
                .output(dust, material)
                .buildAndRegister();
    }

    /**
     * Nugget Material Handler. Generates:
     *
     * + Ingot -> Nugget Alloy Smelter Recipes
     *
     * - GTCE Packer / Unpacker recipes, to be registered elsewhere if configured.
     */
    private static void processNugget(OrePrefix nugget, IngotMaterial material) {

        // Packer / Unpacker removal, to be readded elsewhere depending on Config settings
        removeRecipesByInputs(PACKER_RECIPES, OreDictUnifier.get(nugget, material, 9), getIntegratedCircuit(1));
        removeRecipesByInputs(UNPACKER_RECIPES, OreDictUnifier.get(ingot, material, 1), getIntegratedCircuit(1));

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(8).duration((int) material.getAverageMass())
                .input(nugget, material, 9)
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT.getStackForm())
                .output(ingot, material)
                .buildAndRegister();
    }

    /**
     * Ring Material Handler. Generates:
     *
     * + Bending Cylinder Ring Recipes if enabled
     *
     * - Removes old Handcrafting Ring Recipes if enabled
     */
    private static void processRing(OrePrefix ring, IngotMaterial material) {
        if (!material.hasFlag(NO_SMASHING)) {

            removeCraftingRecipes(OreDictUnifier.get(ring, material));

            ModHandler.addShapedRecipe(String.format("rod_to_ring_%s", material.toString()), OreDictUnifier.get(ring, material),
                    "hS", " C",
                    'S', new UnificationEntry(stick, material),
                    'C', "craftingToolBendingCylinderSmall");
        }
    }

    /**
     * Foil Material Handler. Generates:
     *
     * + Bending Cylinder Foil Recipes if enabled
     * + Cluster Mill Foil Recipes if enabled
     *
     * - Removes Bender Foils if Cluster Mill is enabled
     */
    private static void processFoil(OrePrefix foil, IngotMaterial material) {
        if (!OreDictUnifier.get(foil, material).isEmpty()) {

            // Handcrafting foils
            if (!material.hasFlag(NO_SMASHING)) {

                if (GAConfig.GT6.BendingCylinders) {

                    ModHandler.addShapedRecipe(String.format("foil_%s", material.toString()), OreDictUnifier.get(foil, material, 2),
                            "hPC",
                            'P', new UnificationEntry(plate, material),
                            'C', "craftingToolBendingCylinder");

                } else {

                    ModHandler.addShapedRecipe(String.format("foil_%s", material.toString()), OreDictUnifier.get(foil, material, 2),
                            "hP ",
                            'P', new UnificationEntry(plate, material));
                }
            }

            // Cluster Mill foils
            if (GAConfig.GT6.BendingFoilsAutomatic) {

                removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(plate, material), getIntegratedCircuit(0));

                CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int) material.getAverageMass())
                        .input(plate, material)
                        .output(foil, material, 4)
                        .buildAndRegister();
            }
        }
    }

    /**
     * Round Material Handler. Generates:
     *
     * + Round Handcrafting Recipes
     * + Round Lathe Recipes
     * + Round Unification Recipes
     */
    private static void processRound(OrePrefix round, IngotMaterial material) {
        if (!material.hasFlag(NO_SMASHING)) {

            ModHandler.addShapedRecipe(String.format("round_%s", material.toString()), OreDictUnifier.get(round, material),
                    "fN", "Nh",
                    'N', new UnificationEntry(nugget, material));

            ModHandler.addShapedRecipe(String.format("round_from_ingot_%s", material.toString()), OreDictUnifier.get(round, material, 4),
                    "fIh",
                    'I', new UnificationEntry(ingot, material));
        }

        LATHE_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(nugget, material)
                .output(round, material)
                .buildAndRegister();

        int voltageMultiplier = material.blastFurnaceTemperature == 0 ? 1 : material.blastFurnaceTemperature > 2000 ? 16 : 4;

        // Unification Recipes
        MACERATOR_RECIPES.recipeBuilder().EUt(8 * voltageMultiplier).duration(16)
                .input(round, material)
                .output(dustTiny, material)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32 * voltageMultiplier).duration(2)
                .input(round, material)
                .fluidOutputs(material.getFluid(L / 9))
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30 * voltageMultiplier).duration(16)
                .input(round, material)
                .output(nugget, material.arcSmeltInto == null ? material : material.arcSmeltInto)
                .buildAndRegister();
    }

    /**
     * Double PLate Material Handler. Generates:
     *
     * + Plate to Double Plate Hand Recipes
     * + Double Plate Forge Hammer Recipes
     * + Double Plate Unification Recipes
     */
    private static void processDoublePlate(OrePrefix doublePlate, IngotMaterial material) {
        if (!material.hasFlag(NO_SMASHING)) {
            ModHandler.addShapedRecipe(String.format("plate_double_%s", material.toString()), OreDictUnifier.get(doublePlate, material),
                    "h", "P", "P",
                    'P', new UnificationEntry(plate, material));
        }

        BENDER_RECIPES.recipeBuilder().EUt(30).duration((int) material.getAverageMass())
                .input(plate, material, 2)
                .output(doublePlate, material)
                .circuitMeta(2)
                .buildAndRegister();

        int voltageMultiplier = material.blastFurnaceTemperature == 0 ? 1 : material.blastFurnaceTemperature > 2000 ? 16 : 4;

        // Unification Recipes
        MACERATOR_RECIPES.recipeBuilder().EUt(8 * voltageMultiplier).duration(60)
                .input(doublePlate, material)
                .output(dust, material, 2)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32 * voltageMultiplier).duration(160)
                .input(doublePlate, material)
                .fluidOutputs(material.getFluid(L * 2))
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30 * voltageMultiplier).duration(120)
                .input(doublePlate, material)
                .output(ingot, material.arcSmeltInto == null ? material : material.arcSmeltInto, 2)
                .buildAndRegister();
    }

    /**
     * Dense PLate Material Handler. Generates:
     *
     * + Plate/Ingot to Dense Plate Bender Recipes with circuit 9
     * - Plate/Ingot to Dense Plate Bender Recipes with circuit 2/5
     */
    private static void processDensePlate(OrePrefix densePlate, IngotMaterial material) {
        removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(ingot, material, 9), getIntegratedCircuit(5));
        removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(plate, material, 9), getIntegratedCircuit(2));

        BENDER_RECIPES.recipeBuilder().duration((int) material.getAverageMass() * 4).EUt(96)
                .input(plate, material, 9)
                .output(densePlate, material, 1)
                .circuitMeta(9)
                .buildAndRegister();
        BENDER_RECIPES.recipeBuilder().duration((int) material.getAverageMass() * 9).EUt(96)
                .input(ingot, material, 9)
                .output(densePlate, material, 1)
                .circuitMeta(9)
                .buildAndRegister();
    }


    /**
     * Curved Plate Material Handler. Generates:
     *
     * + Curved Plate Recipes if enabled, Handcrafting and Machine
     * + Curved Plate Rotor Recipes if enabled
     * + Curved Plate Pipe Recipes if enabled
     * + Curved Plate Unification Recipes
     */
    private static void processPlateCurved(OrePrefix plateCurved, IngotMaterial material) {

        //if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders)

        // Register Curved Plate recipes
        if (!material.hasFlag(NO_SMASHING)) {
            if (GAConfig.GT6.BendingCylinders) {

                ModHandler.addShapedRecipe(String.format("curved_plate_%s", material.toString()), OreDictUnifier.get(plateCurved, material),
                        "h", "P", "C",
                        'P', new UnificationEntry(plate, material),
                        'C', "craftingToolBendingCylinder");
            } else {

                ModHandler.addShapedRecipe(String.format("curved_plate_%s", material.toString()), OreDictUnifier.get(plateCurved, material),
                        "h", "P", "f",
                        'P', new UnificationEntry(plate, material));
            }

            ModHandler.addShapedRecipe(String.format("flatten_plate_%s", material.toString()), OreDictUnifier.get(plate, material),
                    "h", "C",
                    'C', new UnificationEntry(plateCurved, material));
        }

        BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) material.getAverageMass())
                .input(plate, material)
                .circuitMeta(1)
                .output(plateCurved, material)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) material.getAverageMass())
                .input(plateCurved, material)
                .circuitMeta(0)
                .output(plate, material)
                .buildAndRegister();

        // Unification Recipes
        int voltageMultiplier = material.blastFurnaceTemperature == 0 ? 1 : material.blastFurnaceTemperature > 2000 ? 16 : 4;

        // Uncrafting Recipes
        MACERATOR_RECIPES.recipeBuilder().EUt(8 * voltageMultiplier).duration(30)
                .input(plateCurved, material)
                .output(dust, material)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32 * voltageMultiplier).duration(80)
                .input(plateCurved, material)
                .fluidOutputs(material.getFluid(L))
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30 * voltageMultiplier).duration(85)
                .input(plateCurved, material)
                .output(ingot, material.arcSmeltInto == null ? material : material.arcSmeltInto)
                .buildAndRegister();
    }

    /**
     * Rotor Material Handler. Generates:
     *
     * + Curved Plate Rotor Recipe
     * + Assembler Rotor Recipe that GTCE removed
     * + Extruder Rotor Recipe
     */
    private static void processRotor(OrePrefix rotor, IngotMaterial material) {

        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

        removeCraftingRecipes(OreDictUnifier.get(rotor, material));

        ModHandler.addShapedRecipe(String.format("ga_rotor_%s", material.toString()), OreDictUnifier.get(rotor, material),
                "ChC", "SRf", "CdC",
                'C', new UnificationEntry(plateOrCurved, material),
                'S', new UnificationEntry(screw, material),
                'R', new UnificationEntry(ring, material));

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24)
                .input(plateOrCurved, material, 4)
                .input(ring, material)
                .fluidInputs(SolderingAlloy.getFluid(32))
                .output(rotor, material)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder().duration((int) material.getAverageMass()).EUt(material.blastFurnaceTemperature >= 2800 ? 256 : 64)
                    .input(ingot, material, 5)
                    .notConsumable(SHAPE_EXTRUDER_ROTOR)
                    .output(rotor, material)
                    .buildAndRegister();
    }

    /**
     * Tiny Pipe Material Handler. Generates:
     *
     * + Tiny Pipe Handcrafting Recipes
     */
    private static void processTinyPipe(OrePrefix prefix, IngotMaterial material) {

        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

        if (GAConfig.GT6.BendingCylinders)

            ModHandler.addShapedRecipe(String.format("pipe_ga_tiny_%s", material.toString()), OreDictUnifier.get(pipeTiny, material, 8),
                    "PPP", "hCw", "PPP",
                    'P', new UnificationEntry(plateOrCurved, material),
                    'C', "craftingToolBendingCylinder");
        else
            ModHandler.addShapedRecipe(String.format("pipe_ga_tiny_%s", material.toString()), OreDictUnifier.get(pipeTiny, material, 8),
                    "PPP", "h w", "PPP",
                    'P', new UnificationEntry(plateOrCurved, material));
    }

    /**
     * Small Pipe Material Handler. Generates:
     *
     * + Small Pipe Handcrafting Recipes
     */
    private static void processSmallPipe(OrePrefix prefix, IngotMaterial material) {

        removeCraftingRecipes(OreDictUnifier.get(pipeSmall, material, 4));
        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

        if (GAConfig.GT6.BendingCylinders)

            ModHandler.addShapedRecipe(String.format("pipe_ga_small_%s", material.toString()), OreDictUnifier.get(pipeSmall, material, 4),
                    "PwP", "PCP", "PhP",
                    'P', new UnificationEntry(plateOrCurved, material),
                    'C', "craftingToolBendingCylinder");
        else
            ModHandler.addShapedRecipe(String.format("pipe_ga_small_%s", material.toString()), OreDictUnifier.get(pipeSmall, material, 4),
                    "PwP", "P P", "PhP",
                    'P', new UnificationEntry(plateOrCurved, material));
    }

    /**
     * Medium Pipe Material Handler. Generates:
     *
     * + Medium Pipe Handcrafting Recipes
     */
    private static void processMediumPipe(OrePrefix prefix, IngotMaterial material) {

        removeCraftingRecipes(OreDictUnifier.get(pipeMedium, material, 2));
        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

        if (GAConfig.GT6.BendingCylinders)

            ModHandler.addShapedRecipe(String.format("pipe_ga_%s", material.toString()), OreDictUnifier.get(pipeMedium, material, 2),
                    "PPP", "wCh", "PPP",
                    'P', new UnificationEntry(plateOrCurved, material),
                    'C', "craftingToolBendingCylinder");
        else
            ModHandler.addShapedRecipe(String.format("pipe_ga_%s", material.toString()), OreDictUnifier.get(pipeMedium, material, 2),
                    "PPP", "w h", "PPP",
                    'P', new UnificationEntry(plateOrCurved, material));
    }

    /**
     * Large Pipe Material Handler. Generates:
     *
     * + Large Pipe Handcrafting Recipes
     */
    private static void processLargePipe(OrePrefix prefix, IngotMaterial material) {

        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

        if (GAConfig.GT6.BendingCylinders)

            ModHandler.addShapedRecipe(String.format("pipe_ga_large_%s", material.toString()), OreDictUnifier.get(pipeLarge, material),
                    "PhP", "PCP", "PwP",
                    'P', new UnificationEntry(plateOrCurved, material),
                    'C', "craftingToolBendingCylinder");
        else
            ModHandler.addShapedRecipe(String.format("pipe_ga_large_%s", material.toString()), OreDictUnifier.get(pipeLarge, material),
                    "PhP", "P P", "PwP",
                    'P', new UnificationEntry(plateOrCurved, material));
    }

    /**
     * Metal Casing Material Handler. Generates:
     *
     * + Autogenerated Metal Casing Handcrafting and Assembler Recipes
     * + Casing Unification Recipes
     */
    private static void processMetalCasing(OrePrefix prefix, IngotMaterial material) {

        if (material.hasFlag(GENERATE_METAL_CASING)) {
            ItemStack metalCasingStack = OreDictUnifier.get(prefix, material, 3);
            ModHandler.addShapedRecipe(String.format("autogen_metal_casing_%s", material), metalCasingStack,
                    "PhP", "PBP", "PwP",
                    'P', new UnificationEntry(plate, material),
                    'B', new UnificationEntry(frameGt, material));

            ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                    .input(plate, material, 6)
                    .input(frameGt, material)
                    .circuitMeta(0)
                    .outputs(metalCasingStack)
                    .buildAndRegister();

            int voltageMultiplier = material.blastFurnaceTemperature == 0 ? 1 : material.blastFurnaceTemperature > 2000 ? 16 : 4;

            ARC_FURNACE_RECIPES.recipeBuilder().EUt(30 * voltageMultiplier).duration(382)
                    .input(gtMetalCasing, material)
                    .output(ingot, material, 6)
                    .output(nugget, material, 3)
                    .buildAndRegister();

            FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(32 * voltageMultiplier).duration(510)
                    .input(gtMetalCasing, material)
                    .fluidOutputs(material.getFluid(918))
                    .buildAndRegister();

            MACERATOR_RECIPES.recipeBuilder().EUt(8 * voltageMultiplier).duration(191)
                    .input(gtMetalCasing, material)
                    .output(dust, material, 6)
                    .output(dustTiny, material, 3)
                    .buildAndRegister();
        }
    }

    /**
     * Turbine Material Handler. Generates:
     *
     * + Small, Medium, Large, and Huge Turbine Assembler Recipes
     * + Turbine Blade Forming Press Recipes
     *
     * - Removes GTCE Recipes for Turbines and Blades, as well as the Handcrafting Turbine Blades Recipes
     */
    private static void processTurbine(OrePrefix toolPrefix, IngotMaterial material) {

        // Remove GTCE Turbine/Blade recipes
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, material, 5), OreDictUnifier.get(screw, material, 2), getIntegratedCircuit(10));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(stickLong, Titanium), OreDictUnifier.get(turbineBlade, material, 8));
        removeRecipeByName(String.format("gtadditions:turbine_blade_%s", material));

        // Generate the Turbine item with proper stats
        ItemStack hugeTurbineRotorStackForm = HUGE_TURBINE_ROTOR.getStackForm();
        ItemStack largeTurbineRotorStackForm = LARGE_TURBINE_ROTOR.getStackForm();
        ItemStack mediumTurbineRotorStackForm = MEDIUM_TURBINE_ROTOR.getStackForm();
        ItemStack smallTurbineRotorStackForm = SMALL_TURBINE_ROTOR.getStackForm();

        TurbineRotorBehavior.getInstanceFor(smallTurbineRotorStackForm).setPartMaterial(smallTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(mediumTurbineRotorStackForm).setPartMaterial(mediumTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(largeTurbineRotorStackForm).setPartMaterial(largeTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(hugeTurbineRotorStackForm).setPartMaterial(hugeTurbineRotorStackForm, material);

        // Turbine Recipes
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(400)
                .circuitMeta(0)
                .input(turbineBlade, material, 4)
                .input(stickLong, Titanium)
                .outputs(smallTurbineRotorStackForm)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(800)
                .circuitMeta(1)
                .input(turbineBlade, material, 8)
                .input(stickLong, Tungsten)
                .outputs(mediumTurbineRotorStackForm)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(1600)
                .circuitMeta(2)
                .input(turbineBlade, material, 16)
                .input(stickLong, Osmium)
                .outputs(largeTurbineRotorStackForm)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(3200)
                .circuitMeta(3)
                .input(turbineBlade, material, 32)
                .input(stickLong, Rutherfordium)
                .outputs(hugeTurbineRotorStackForm)
                .buildAndRegister();

        // Turbine Blade recipe
        FORMING_PRESS_RECIPES.recipeBuilder().duration(20).EUt(256)
                .input(plateDouble, material, 5)
                .input(screw, material, 2)
                .output(toolPrefix, material)
                .buildAndRegister();
    }

    /**
     * Lens Material Handler. Generates:
     *
     * + Exquisite Gem -> Lens Recipes
     *
     * - Plate -> Lens Recipes
     */
    private static void processLens(OrePrefix gem, GemMaterial material) {

        removeRecipesByInputs(LATHE_RECIPES, OreDictUnifier.get(plate, material));

        if (!OreDictUnifier.get(gemExquisite, material).isEmpty()) {
            LATHE_RECIPES.recipeBuilder().duration(2400).EUt(30)
                    .input(gemExquisite, material)
                    .output(lens, material)
                    .output(dust, material, 2)
                    .buildAndRegister();
        } else {
            LATHE_RECIPES.recipeBuilder().duration(2400).EUt(30)
                    .input(plate, material)
                    .output(lens, material)
                    .output(dustTiny, material, 2)
                    .buildAndRegister();
        }
    }

    /**
     * Spring Material Handler. Generates:
     *
     * + Crafting Table Recipe for Spring
     */
    private static void processSpring(OrePrefix prefix, IngotMaterial material) {

        ModHandler.addShapedRecipe(String.format("spring_%s", material.toString()), OreDictUnifier.get(spring, material),
                " s ", "fRx", " R ",
                'R', new UnificationEntry(stickLong, material));
    }

    /**
     * Small Spring Material Handler. Generates:
     *
     * + Crafting Table Recipe for Small Spring
     * + Bender Recipe for Small Spring
     *
     * - GTCE Fine Wire to Small Spring Recipe
     */
    private static void processSpringSmall(OrePrefix prefix, IngotMaterial material) {

        if (material.cableProperties != null)
            removeRecipesByInputs(BENDER_RECIPES, OreDictUnifier.get(wireGtSingle, material));

        ModHandler.addShapedRecipe(String.format("spring_small_%s", material.toString()), OreDictUnifier.get(springSmall, material),
                " s ", "fRx",
                'R', new UnificationEntry(stick, material));

        BENDER_RECIPES.recipeBuilder().duration((int) (material.getAverageMass() / 2)).EUt(8)
                .input(stick, material)
                .output(springSmall, material, 2)
                .circuitMeta(1)
                .buildAndRegister();
    }

    /**
     * Small Gear Material Handler. Generates:
     *
     * + Harder Small Gear Crafting Table Recipe
     * + Extruder Recipe for Small Gears
     * + Lossy Small Gear recipe in Alloy Smelter (similar to normal Gears)
     *
     * - Removes Forge Hammer Recipe for Small Gears
     */
    private static void processGearSmall(OrePrefix prefix, IngotMaterial material) {

        if (material.hasFlag(GENERATE_SMALL_GEAR)) {

            removeRecipeByName(String.format("gtadditions:small_gear_%s", material.toString()));
            ModHandler.addShapedRecipe(String.format("small_gear_%s", material.toString()), OreDictUnifier.get(gearSmall, material),
                    " R ", "hPx", " R ",
                    'R', new UnificationEntry(stick, material),
                    'P', new UnificationEntry(plate, material));

            removeRecipesByInputs(FORGE_HAMMER_RECIPES, OreDictUnifier.get(plate, material, 2));
            EXTRUDER_RECIPES.recipeBuilder().duration((int) material.getAverageMass()).EUt(material.blastFurnaceTemperature >= 2800 ? 256 : 64)
                    .input(ingot, material)
                    .notConsumable(SHAPE_EXTRUDER_SMALL_GEAR.getStackForm())
                    .output(gearSmall, material)
                    .buildAndRegister();

            ALLOY_SMELTER_RECIPES.recipeBuilder().duration((int) material.getAverageMass()).EUt(30)
                    .input(ingot, material, 2)
                    .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm())
                    .output(gearSmall, material)
                    .buildAndRegister();
        }
    }

    /**
     * Gear Material Handler. Generates:
     *
     * + Replace GTCE Gear recipe to use proper tool
     */
    private static void processGear(OrePrefix prefix, IngotMaterial material) {

        removeRecipeByName(String.format("gtadditions:gear_%s", material.toString()));
        ModHandler.addShapedRecipe(String.format("gear_%s", material.toString()), OreDictUnifier.get(gear, material),
                "RPR", "PwP", "RPR",
                'R', new UnificationEntry(stick, material),
                'P', new UnificationEntry(plate, material));
    }

    /**
     * Hot Ingot Material Handler. Generates:
     *
     * + Increased duration Hot Ingot Recipes, to make Cryogenic Freezer viable
     */
    private static void processIngotHot(OrePrefix prefix, IngotMaterial material) {

        // Temperature at which a Hot Ingot is generated
        if (material.blastFurnaceTemperature > 1750) {

            removeRecipesByInputs(VACUUM_RECIPES, OreDictUnifier.get(ingotHot, material));
            VACUUM_RECIPES.recipeBuilder().duration((int) (material.getAverageMass() * 3))
                    .input(ingotHot, material)
                    .output(ingot, material)
                    .buildAndRegister();
        }
    }

    private static void registerLargeMachineRecipes(RecipeMap<?> mapToCopy, RecipeMap<LargeRecipeBuilder> mapToForm) {

        for (Recipe recipe : mapToCopy.getRecipeList()) {

            LargeRecipeBuilder largeRecipeBuilder = mapToForm.recipeBuilder()
                    .EUt(recipe.getEUt())
                    .duration(recipe.getDuration())
                    .inputsIngredients(recipe.getInputs())
                    .outputs(recipe.getOutputs())
                    .fluidInputs(recipe.getFluidInputs())
                    .fluidOutputs(recipe.getFluidOutputs());

            // TODO Giving better way to do this in GTCE
            for (Recipe.ChanceEntry entry : recipe.getChancedOutputs())
                largeRecipeBuilder.chancedOutput(entry.getItemStack(), entry.getChance(), entry.getBoostPerTier());

            largeRecipeBuilder.buildAndRegister();
        }
    }

    /**
     * Large Mixer Recipe Creation.
     * Copies the Mixer RecipeMap.
     *
     * This RecipeMap also applies a circuit to every recipe to avoid conflicts.
     */
    private static void registerLargeMixerRecipes() {
        MIXER_RECIPES.getRecipeList().forEach(recipe -> {
            List<CountableIngredient> inputList = new ArrayList<>();
            IntCircuitIngredient circuitIngredient = null;

            // Make sure 2 circuits do not end up in the recipe
            for (CountableIngredient input : recipe.getInputs()) {
                if (!(input.getIngredient() instanceof IntCircuitIngredient))
                    inputList.add(input);
                else
                    circuitIngredient = (IntCircuitIngredient) input.getIngredient();
            }

            if (circuitIngredient == null)
                circuitIngredient = new IntCircuitIngredient(inputList.size() + recipe.getFluidInputs().size());

            LARGE_MIXER_RECIPES.recipeBuilder()
                    .notConsumable(circuitIngredient)
                    .EUt(recipe.getEUt())
                    .duration(recipe.getDuration())
                    .fluidInputs(recipe.getFluidInputs())
                    .inputsIngredients(inputList)
                    .outputs(recipe.getOutputs())
                    .fluidOutputs(recipe.getFluidOutputs())
                    .buildAndRegister();
        });
    }

    /**
     * Alloy Blast Furnace Recipe creation.
     * Uses the Large Mixer RecipeMap to look up compositions of alloys.
     *
     * This Recipe Registration MUST be run after the Large Mixer recipe addition.
     */
    private static void registerAlloyBlastRecipes() {
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (!(material instanceof IngotMaterial))
                continue;
            IngotMaterial ingotMaterial = (IngotMaterial) material;
            if (ingotMaterial.blastFurnaceTemperature == 0)
                continue;

            // This could use some code cleanup
            LARGE_MIXER_RECIPES.getRecipeList().stream()
                    .filter(recipe -> recipe.getOutputs().size() == 1)
                    .filter(recipe -> recipe.getFluidOutputs().size() == 0)
                    .filter(recipe -> recipe.getOutputs().get(0).isItemEqualIgnoreDurability(OreDictUnifier.get(dust, ingotMaterial)))
                    .findFirst()
                    .ifPresent(recipe -> {
                        ItemStack itemStack = recipe.getOutputs().get(0);
                        IngotMaterial ingot = ((IngotMaterial) (OreDictUnifier.getUnificationEntry(itemStack).material));
                        int duration = Math.max(1, (int) (ingot.getAverageMass() * ingot.blastFurnaceTemperature / 50L));
                        BLAST_ALLOY_RECIPES.recipeBuilder()
                                .duration(duration * 80 / 100)
                                .EUt(120 * itemStack.getCount())
                                .fluidInputs(recipe.getFluidInputs())
                                .inputsIngredients(recipe.getInputs())
                                .fluidOutputs(ingot.getFluid(itemStack.getCount() * GTValues.L)).buildAndRegister();
                    });
        }

        // Soldering Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(775).EUt(1200)
                .input(dust, Tin, 9)
                .input(dust, Antimony)
                .fluidOutputs(SolderingAlloy.getFluid(L * 10))
                .notConsumable(new IntCircuitIngredient(10))
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
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(556).EUt(174)
                .input(dust, Tin)
                .input(dust, WroughtIron)
                .fluidOutputs(TinAlloy.getFluid(L * 2))
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();

        // Battery Alloy
        BLAST_ALLOY_RECIPES.recipeBuilder().duration(512).EUt(600)
                .input(dust, Lead, 4)
                .input(dust, Antimony)
                .fluidOutputs(BatteryAlloy.getFluid(L * 5))
                .notConsumable(new IntCircuitIngredient(5))
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
    }

    /**
     * Chemical Plant Recipe creation.
     * Copies the Brewer RecipeMap loosely.
     */
    private static void registerChemicalPlantRecipes() {
        BREWING_RECIPES.getRecipeList().forEach(recipe -> {
            FluidStack fluidInput = recipe.getFluidInputs().get(0).copy();
            fluidInput.amount = (fluidInput.amount * 10 * 125 / 100);
            CountableIngredient itemInput = new CountableIngredient(recipe.getInputs().get(0).getIngredient(), recipe.getInputs().get(0).getCount() * 10);
            FluidStack fluidOutput = FermentationBase.getFluid(recipe.getFluidOutputs().get(0).amount * 10);

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                    .EUt(recipe.getEUt() * 10)
                    .duration(recipe.getDuration() * 10)
                    .fluidInputs(fluidInput)
                    .inputsIngredients(Collections.singleton(itemInput))
                    .fluidOutputs(fluidOutput)
                    .buildAndRegister();
        });
    }

    /**
     * Green House Recipe creation.
     */
    private static void registerGreenHouseRecipes() {

        final List<Object> fertilizers = Arrays.asList(
            null, 1000,
            new ItemStack(Items.DYE, 1, 15), 900,
            OreDictUnifier.get(dust, OrganicFertilizer), 600
        );

        final List<Item> inputs = Arrays.asList(
                Items.CARROT,
                Item.getItemFromBlock(Blocks.CACTUS),
                Items.REEDS,
                Item.getItemFromBlock(Blocks.BROWN_MUSHROOM),
                Item.getItemFromBlock(Blocks.RED_MUSHROOM),
                Items.BEETROOT);

        // Create Recipes for Vanilla crops
        for (int i = 0; i < fertilizers.size() / 2; i++) {

            List<RecipeBuilder<?>> recipes = new ArrayList<>();

            ItemStack fertilizer = (ItemStack) fertilizers.get(i * 2);
            int duration = (int) fertilizers.get(i * 2 + 1);

            // Potato
            recipes.add(GREEN_HOUSE_RECIPES.recipeBuilder().duration(duration)
                    .circuitMeta(i)
                    .fluidInputs(Water.getFluid(2000))
                    .notConsumable(new ItemStack(Items.POTATO))
                    .outputs(new ItemStack(Items.POTATO, i))
                    .chancedOutput(new ItemStack(Items.POISONOUS_POTATO, 1), 100, 50));

            // Melon
            GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000)
                    .circuitMeta(i)
                    .fluidInputs(Water.getFluid(2000))
                    .notConsumable(new ItemStack(Items.MELON_SEEDS))
                    .outputs(new ItemStack(Items.MELON, i))
                    .chancedOutput(new ItemStack(Items.MELON_SEEDS), 100, 50)
                    .buildAndRegister();

            // Pumpkin
            GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000)
                    .circuitMeta(i)
                    .fluidInputs(Water.getFluid(2000))
                    .notConsumable(new ItemStack(Items.PUMPKIN_SEEDS))
                    .outputs(new ItemStack(Blocks.PUMPKIN, i))
                    .chancedOutput(new ItemStack(Items.PUMPKIN_SEEDS), 100, 50)
                    .buildAndRegister();

            for (Item input : inputs) {

                recipes.add(GREEN_HOUSE_RECIPES.recipeBuilder().duration(duration)
                        .circuitMeta(i)
                        .fluidInputs(Water.getFluid(2000))
                        .notConsumable(new ItemStack(input))
                        .outputs(new ItemStack(input, i)));
            }

            if (fertilizer != null)
                recipes.stream()
                       .map(r -> r.inputs(fertilizer))
                       .forEach(RecipeBuilder::buildAndRegister);
        }

        // Search for seeds in the OreDictionary to find other recipes to add
        Arrays.stream(OreDictionary.getOreNames()).filter(name -> name.startsWith("seed")).forEach(name -> {

            String oreName = name.substring(4);

            if (oreName.length() <= 0)
                return;

            String seedName = "seed" + titleCase(oreName);
            String cropName = "essence" + titleCase(oreName);

            List<ItemStack> registeredSeeds = OreDictionary.getOres(seedName, false);
            List<ItemStack> registeredCrops = OreDictionary.getOres(cropName, false);

            if (registeredSeeds.isEmpty() || registeredCrops.isEmpty())
                return;

            ItemStack seed = registeredSeeds.get(0).copy();
            ItemStack essence = registeredCrops.get(0).copy();

            GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000)
                    .fluidInputs(Water.getFluid(2000))
                    .circuitMeta(0)
                    .notConsumable(seed)
                    .outputs(essence)
                    .chancedOutput(seed, 100, 50)
                    .buildAndRegister();

            essence = registeredCrops.get(0).copy();
            essence.setCount(3);
            GREEN_HOUSE_RECIPES.recipeBuilder().duration(600)
                    .fluidInputs(Water.getFluid(2000))
                    .circuitMeta(2)
                    .notConsumable(seed)
                    .input(dust, OrganicFertilizer)
                    .outputs(essence)
                    .chancedOutput(seed, 100, 50)
                    .buildAndRegister();
        });
    }

    /**
     * Recipe Registration method for any recipes that need to iterate over
     * ALL registered materials. Generates:
     *
     * - Large Centrifuge autogenerated recipes
     * - Matter Replication Recipes
     */
    public static void runRecipeGeneration() {
        for (Material material : Material.MATERIAL_REGISTRY) {

            // Decomposition Recipes
            if (material instanceof FluidMaterial) {
                OrePrefix prefix = material instanceof DustMaterial ? dust : null;
                processDecomposition(prefix, (FluidMaterial) material);
            }

            // Matter Replication Recipes
            if (material.element != null) {
                matterReplication(material);
            }
        }
    }

    // TODO This method needs work
    private static void processDecomposition(OrePrefix decomposePrefix, FluidMaterial material) {
        if (material.materialComponents.isEmpty() || !material.hasFlag(DECOMPOSITION_BY_CENTRIFUGING) ||
                //disable decomposition if explicitly disabled for this material or for one of it's components
                material.hasFlag(DISABLE_DECOMPOSITION)) return;

        ArrayList<ItemStack> outputs = new ArrayList<>();
        ArrayList<FluidStack> fluidOutputs = new ArrayList<>();
        int totalInputAmount = 0;

        //compute outputs
        for (MaterialStack component : material.materialComponents) {
            totalInputAmount += component.amount;
            if (component.material instanceof DustMaterial) {
                outputs.add(OreDictUnifier.get(dust, component.material, (int) component.amount));
            } else if (component.material instanceof FluidMaterial) {
                FluidMaterial componentMaterial = (FluidMaterial) component.material;
                fluidOutputs.add(componentMaterial.getFluid((int) (1000 * component.amount)));
            }
        }

        //generate builder
        RecipeBuilder<?> builder;
        if (!material.hasFlag(DECOMPOSITION_BY_ELECTROLYZING)) {
            builder = LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                    .duration((int) Math.ceil(material.getAverageMass() * totalInputAmount * 1.5))
                    .EUt(30);
        } else {
            return;
        }
        builder.outputs(outputs);
        builder.fluidOutputs(fluidOutputs);

        //finish builder
        if (decomposePrefix != null) {
            builder.input(decomposePrefix, material, totalInputAmount);
        } else {
            builder.fluidInputs(material.getFluid(1000 * totalInputAmount));
        }
        if (material.hasFlag(DECOMPOSITION_REQUIRES_HYDROGEN)) {
            builder.fluidInputs(Hydrogen.getFluid(1000 * totalInputAmount));
        }

        //register recipe
        builder.buildAndRegister();
    }

    /**
     * Matter Replication Recipe Generation. Formerly was its own file.
     */
    private static void matterReplication(Material material) {

        int mass = (int) material.getMass();
        FluidStack uuFluid = mass % 2 == 0 ?
                BosonicUUMatter.getFluid(mass) :
                FermionicUUMatter.getFluid(mass);

        if (((FluidMaterial) material).getMaterialFluid() != null) {

            int amount = material instanceof IngotMaterial ? GTValues.L : 1000;

            MASS_FAB_RECIPES.recipeBuilder()
                    .fluidInputs(((FluidMaterial) material).getFluid(amount))
                    .fluidOutputs(uuFluid)
                    .fluidOutputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            if (!material.hasFlag(DISABLE_REPLICATION)) {

                REPLICATOR_RECIPES.recipeBuilder()
                        .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                        .notConsumable(((FluidMaterial) material).getFluid(amount))
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectronGas.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();

                REPLICATOR_RECIPES.recipeBuilder()
                        .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                        .notConsumable(((FluidMaterial) material).getFluid(amount))
                        .fluidInputs(UUMatter.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
            }
        } else {

            MASS_FAB_RECIPES.recipeBuilder()
                    .input(dust, material)
                    .fluidOutputs(uuFluid)
                    .fluidOutputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            if (!material.hasFlag(DISABLE_REPLICATION)) {

                REPLICATOR_RECIPES.recipeBuilder()
                        .output(dust, material)
                        .notConsumable(dust, material)
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectronGas.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();

                REPLICATOR_RECIPES.recipeBuilder()
                        .output(dust, material)
                        .notConsumable(dust, material)
                        .fluidInputs(UUMatter.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
            }
        }
    }

    /**
     * Recipe Generation for any recipes that need to iterate over the entire Crafting Table recipe registry.
     *
     * Details on recipe registration in each individual method. It works only on recipes that
     * have exactly one unique Item input.
     */
    public static void generatedRecipes() {

        for (IRecipe recipe : ForgeRegistries.RECIPES) {

            switch(getSingleInputCount(recipe)) {
                case -1: continue;
                case 1: generate1to9Recipes(recipe); break;
                case 4: generate2x2Recipes(recipe); break;
                case 9: generate3x3Recipes(recipe); break;
            }
        }
    }

    /**
     * 3x3 Single Input Recipe Generation. Generates:
     *
     * + Compressor Recipes for 3x3 Crafting Recipes
     * + Packer Recipes for 3x3 Crafting Recipes
     *
     * - Removes handcrafting 3x3 Recipes for:
     *     - Blocks
     *     - Nuggets
     *     - All others
     *   depending on config values.
     */
    private static void generate3x3Recipes(IRecipe recipe) {

        ItemStack input = getTopLeft(recipe);
        ItemStack output = recipe.getRecipeOutput();

        // Exclude tinyDust->dust recipes, handled elsewhere
        if (output.getCount() != 1
         || hasOrePrefix(input, "dustTiny"))
            return;

        if (GAConfig.GT5U.Remove3x3BlockRecipes && hasOrePrefix(output, "block"))
            removeRecipeByName(recipe.getRegistryName());

        else if (GAConfig.GT5U.Remove3x3NuggetRecipes && hasOrePrefix(input, "nugget"))
            removeRecipeByName(recipe.getRegistryName());

        else if (GAConfig.GT5U.Remove3x3MiscRecipes && !hasOrePrefix(output, "block") && !hasOrePrefix(input, "nugget"))
            removeRecipeByName(recipe.getRegistryName());

        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                .inputs(CountableIngredient.from(input, 9))
                .notConsumable(SCHEMATIC_3X3.getStackForm())
                .outputs(output)
                .buildAndRegister();

        // Exclude Wheat, since it compresses to Plant Balls
        if (!ItemStack.areItemsEqual(input, new ItemStack(Items.WHEAT)))
            COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2)
                    .inputs(CountableIngredient.from(input, 9))
                    .outputs(output)
                    .buildAndRegister();
    }

    /**
     * 2x2 Single Input Recipe Generation. Generates:
     *
     * + Packer Recipes for 2x2 Crafting Recipes
     */
    private static void generate2x2Recipes(IRecipe recipe) {

        ItemStack input = getTopLeft(recipe);
        ItemStack output = recipe.getRecipeOutput();

        // Exclude smallDust->dust recipes and wire/cable compacting, handled elsewhere
        if (output.getCount() != 1
         || hasOrePrefix(input, "dustSmall")
         || hasOrePrefix(input, "wireGt")
         || hasOrePrefix(input, "cableGt"))
            return;

        // Add Packager 2x2 Recipes
        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4)
                .inputs(CountableIngredient.from(input, 4))
                .notConsumable(SCHEMATIC_2X2.getStackForm())
                .outputs(output)
                .buildAndRegister();
    }

    /**
     * 1 to 9 Single Input Recipe Generation. Generates:
     *
     * + Unpacker Recipes for 1 to 9 Crafting Recipes
     *
     * - Removes handcrafting 1 to 9 Recipes for:
     *     - Blocks
     *     - Nuggets
     *     - All others
     *   depending on config values.
     */
    private static void generate1to9Recipes(IRecipe recipe) {

        ItemStack input = getTopLeft(recipe);
        ItemStack output = recipe.getRecipeOutput();

        // Exclude dust->tinyDust recipes, handled elsewhere
        if (output.getCount() != 9
         || hasOrePrefix(output, "dustTiny"))
            return;

        if (GAConfig.GT5U.Remove1to9BlockRecipes && hasOrePrefix(input, "block"))
            removeRecipeByName(recipe.getRegistryName());

        else if (GAConfig.GT5U.Remove1to9NuggetRecipes && hasOrePrefix(output, "nugget"))
            removeRecipeByName(recipe.getRegistryName());

        else if (GAConfig.GT5U.Remove1to9MiscRecipes && !hasOrePrefix(input, "block") && !hasOrePrefix(output, "nugget"))
            removeRecipeByName(recipe.getRegistryName());

        UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8)
                .inputs(input)
                .notConsumable(SCHEMATIC_3X3.getStackForm())
                .outputs(output)
                .buildAndRegister();
    }

    /**
     * Plasma to Fluid plasma condenser recipes
     *
     * + Plasma Condenser recipes
     */
    private static void processPlasma(OrePrefix prefix, FluidMaterial material) {
        if (material.shouldGeneratePlasma()) {
            int fluidAmount = material instanceof DustMaterial ? GTValues.L : 100;
            //todo this doesn't work on fluidmaterials because of gtce limitations

            PLASMA_CONDENSER_RECIPES.recipeBuilder().duration((int) material.getAverageMass()).EUt(960)
                    .fluidInputs(LiquidHelium.getFluid(100))
                    .fluidInputs(material.getPlasma(fluidAmount))
                    .notConsumable(new IntCircuitIngredient(1))
                    .fluidOutputs(Helium.getFluid(100))
                    .fluidOutputs(material.getFluid(fluidAmount))
                    .buildAndRegister();
        }
    }
}
