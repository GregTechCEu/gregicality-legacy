package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaItems;
import gregicadditions.materials.IsotopeMaterial;
import gregicadditions.materials.RadioactiveMaterial;
import gregicadditions.recipes.map.LargeRecipeBuilder;
import gregicadditions.recipes.map.NuclearReactorBuilder;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.SCHEMATIC_3X3;
import static gregicadditions.item.GAMetaItems.SCHEMATIC_DUST;
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.M;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.NO_SMASHING;
import static gregtech.api.unification.ore.OrePrefix.*;

public class RecipeHandler {

    private static final List<FluidMaterial> OLD_INSULATION_MATERIAL = Arrays.asList(Rubber, StyreneButadieneRubber, SiliconeRubber);


    private static final OrePrefix[] WIRE_DOUBLING_ORDER = new OrePrefix[]{
            OrePrefix.wireGtSingle, OrePrefix.wireGtDouble, OrePrefix.wireGtQuadruple, OrePrefix.wireGtOctal, OrePrefix.wireGtHex
    };

    public static void register() {
        //ore tripling
        if (GAConfig.Misc.uuMatterOreProcessing) {
            OrePrefix.ore.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreBasalt.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreBlackgranite.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreEndstone.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreGravel.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreNetherrack.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreMarble.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreRedgranite.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
            OrePrefix.oreSand.addProcessingHandler(DustMaterial.class, RecipeHandler::processOre);
        }
        //ore quad and sextupling
        if (GAConfig.Misc.thermalCentrifugeOreProcessing) {
            OrePrefix.crushedPurified.addProcessingHandler(DustMaterial.class, RecipeHandler::processCrushedPurified);
        }

        OrePrefix.valueOf("gtMetalCasing").addProcessingHandler(IngotMaterial.class, RecipeHandler::processMetalCasing);
        OrePrefix.turbineBlade.addProcessingHandler(IngotMaterial.class, RecipeHandler::processTurbine);
        OrePrefix.dustImpure.addProcessingHandler(DustMaterial.class, RecipeHandler::processDirtyDust);
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders)
            OrePrefix.valueOf("plateCurved").addProcessingHandler(IngotMaterial.class, RecipeHandler::processPlateCurved);
        if (GAConfig.GT6.PlateDoubleIngot) {
            plate.addProcessingHandler(IngotMaterial.class, RecipeHandler::processDoubleIngot);
        }
        valueOf("round").addProcessingHandler(IngotMaterial.class, RecipeHandler::processRound);
        if (GAConfig.GT6.BendingRings && GAConfig.GT6.BendingCylinders) {
            ring.addProcessingHandler(IngotMaterial.class, RecipeHandler::processRing);
        }
        if (GAConfig.GT5U.CablesGT5U) {
            for (OrePrefix wirePrefix : WIRE_DOUBLING_ORDER) {
                wirePrefix.addProcessingHandler(IngotMaterial.class, RecipeHandler::processWireGt);
            }
        }
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, RecipeHandler::processNuclearMaterial);
        OrePrefix.dust.addProcessingHandler(DustMaterial.class, RecipeHandler::processReplication);

        if (GAConfig.Misc.PackagerDustRecipes) {
            OrePrefix.dustTiny.addProcessingHandler(DustMaterial.class, RecipeHandler::processTinyDust);
            OrePrefix.dustSmall.addProcessingHandler(DustMaterial.class, RecipeHandler::processSmallDust);
            OrePrefix.nugget.addProcessingHandler(IngotMaterial.class, RecipeHandler::processNugget);
        }

    }

    public static void processCrushedPurified(OrePrefix purifiedPrefix, DustMaterial material) {
        ItemStack crushedCentrifugedStack = OreDictUnifier.get(OrePrefix.crushedCentrifuged, material);
        Material byproductMaterial = GTUtility.selectItemInList(1, material, material.oreByProducts, DustMaterial.class);
        if (!crushedCentrifugedStack.isEmpty()) {
            removeRecipesByInputs(THERMAL_CENTRIFUGE_RECIPES, OreDictUnifier.get(purifiedPrefix, material));
            RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                    .input(purifiedPrefix, material)
                    .outputs(GTUtility.copyAmount(2, crushedCentrifugedStack))
                    .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, byproductMaterial, 6), 5000, 500)
                    .duration((int) (material.getAverageMass() * 24))
                    .EUt(60)
                    .buildAndRegister();
        }
    }

    public static void processOre(OrePrefix orePrefix, DustMaterial material) {
        DustMaterial byproductMaterial = GTUtility.selectItemInList(0, material, material.oreByProducts, DustMaterial.class);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial, 2);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material.crushedInto);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);
        crushedStack.setCount(crushedStack.getCount() * material.oreMultiplier);
        if (!crushedStack.isEmpty()) {
            RecipeBuilder<?> builder = CHEMICAL_BATH_RECIPES.recipeBuilder()
                    .fluidInputs(UUMatter.getFluid((int) material.getAverageMass()))
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 3, crushedStack))
                    .chancedOutput(byproductStack, 1400, 850)
                    .duration(800).EUt(24);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material instanceof DustMaterial) {
                    ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                    builder.chancedOutput(dustStack, 6700, 800);
                }
            }
            builder.buildAndRegister();
        }
    }


    private static void processTinyDust(OrePrefix dustTiny, DustMaterial material) {
        removeRecipesByInputs(RecipeMaps.PACKER_RECIPES, OreDictUnifier.get(dustTiny, material, 9), IntCircuitIngredient.getIntegratedCircuit(1));
        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustTiny, material, 9).notConsumable(SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, material)).buildAndRegister();
    }

    private static void processSmallDust(OrePrefix dustSmall, DustMaterial material) {
        removeRecipesByInputs(RecipeMaps.PACKER_RECIPES, OreDictUnifier.get(dustSmall, material, 4), IntCircuitIngredient.getIntegratedCircuit(2));
        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(dustSmall, material, 4).notConsumable(SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(dust, material)).buildAndRegister();
    }

    private static void processNugget(OrePrefix nugget, IngotMaterial material) {
        removeRecipesByInputs(RecipeMaps.PACKER_RECIPES, OreDictUnifier.get(nugget, material, 9), IntCircuitIngredient.getIntegratedCircuit(1));
        PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(nugget, material, 9).notConsumable(SCHEMATIC_3X3.getStackForm()).outputs(OreDictUnifier.get(ingot, material)).buildAndRegister();

        removeRecipesByInputs(RecipeMaps.UNPACKER_RECIPES, OreDictUnifier.get(ingot, material, 1), IntCircuitIngredient.getIntegratedCircuit(1));
        UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(ingot, material, 1).notConsumable(SCHEMATIC_3X3.getStackForm()).outputs(OreDictUnifier.get(nugget, material, 9)).buildAndRegister();

    }


    private static void processNuclearMaterial(OrePrefix ingot, IngotMaterial material) {
        RadioactiveMaterial radioactiveMaterial = RadioactiveMaterial.REGISTRY.get(material);
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (radioactiveMaterial != null && radioactiveMaterial.composition.size() > 0) {
            int complexity = radioactiveMaterial.complexity;


            CHEMICAL_RECIPES.recipeBuilder().duration(2000 * complexity / 100)
                    .input(dust, radioactiveMaterial.getMaterial())
                    .fluidInputs(NitricAcid.getFluid(2000))
                    .outputs(radioactiveMaterial.getDustNitrate(3))
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(100 * complexity / 100).EUt(120 * complexity / 100)
                    .inputs(radioactiveMaterial.getDustNitrate(1))
                    .fluidInputs(Water.getFluid(6000))
                    .outputs(radioactiveMaterial.getDustDioxide(1))
                    .fluidOutputs(NitrogenTetroxide.getFluid(1000))
                    .buildAndRegister();


            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .inputs(radioactiveMaterial.getDustDioxide(1))
                    .fluidInputs(Chlorine.getFluid(6000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexachloride(6000))
                    .fluidOutputs(Oxygen.getFluid(2000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .fluidInputs(radioactiveMaterial.getFluidHexachloride(2000))
                    .fluidInputs(HydrogenFluoride.getFluid(10000))
                    .fluidOutputs(HydrochloricAcid.getFluid(10000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexafluoride(2000))
                    .buildAndRegister();


            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(100 * complexity / 100).EUt(120)
                    .fluidInputs(radioactiveMaterial.getFluidHexafluoride(1000))
                    .outputs(radioactiveMaterial.getDustHexafluoride(1))
                    .buildAndRegister();


            SimpleRecipeBuilder builder = THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000 * complexity / 100).EUt(60 * complexity / 100)
                    .inputs(radioactiveMaterial.getDustHexafluoride(1));
            radioactiveMaterial.composition.forEach((key, value) -> builder.chancedOutput(key.getDustHexafluoride(1), value, 100));
            builder.buildAndRegister();


            radioactiveMaterial.composition.forEach((key, value) -> {
                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(600 * complexity / 100).EUt(120 * complexity / 100)
                        .fluidInputs(Steam.getFluid(6000))
                        .inputs(key.getDustHexafluoride(1))
                        .outputs(key.getDustDioxide(1))
                        .fluidOutputs(Fluorine.getFluid(6000))
                        .buildAndRegister();

                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(1000 * complexity / 100).EUt(120 * complexity / 100)
                        .inputs(key.getDustDioxide(1))
                        .outputs(OreDictUnifier.get(ingot, key.getMaterial()))
                        .fluidOutputs(Oxygen.getFluid(2000))
                        .buildAndRegister();
            });
        } else if (isotopeMaterial != null && isotopeMaterial.fissile) {
            IntStream.range(1, 10).forEach(operand ->
                    NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(10000).EUt((isotopeMaterial.baseHeat + operand) * operand * 2)
                            .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand * 2)
                            .notConsumable(new IntCircuitIngredient(operand + 10))
                            .input(stickLong, isotopeMaterial.getMaterial(), operand)
                            .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm(operand))
                            .buildAndRegister());
            IntStream.range(1, 10).forEach(operand ->
                    IsotopeMaterial.REGISTRY.entrySet().stream()
                            .filter(isotopeMaterialEntry -> isotopeMaterialEntry.getValue().fertile)
                            .forEach(isotopeMaterialEntry -> {
                                        NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(20000).EUt((isotopeMaterial.baseHeat + operand) * operand / 2)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .input(stickLong, isotopeMaterial.getMaterial(), operand)
                                                .input(stickLong, isotopeMaterialEntry.getKey(), 9)
                                                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm(operand))
                                                .outputs(isotopeMaterialEntry.getValue().getRadioactiveMaterial().waste.getStackForm(9))
                                                .buildAndRegister();

                                        NuclearReactorBuilder builder = NUCLEAR_BREEDER_RECIPES.recipeBuilder().duration(10000).EUt((isotopeMaterial.baseHeat + operand) * operand)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand / 5)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .input(stickLong, isotopeMaterial.getMaterial(), operand)
                                                .input(stickLong, isotopeMaterialEntry.getKey(), 9)
                                                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm(operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder.chancedOutput(OreDictUnifier.get(stickLong, key.getMaterial(), 9), value, 100));
                                        builder.buildAndRegister();
                                    }
                            ));
        } else if (isotopeMaterial != null && !isotopeMaterial.fertile && isotopeMaterial.isotopeDecay.size() > 0) {
            isotopeMaterial.isotopeDecay.keySet().forEach(isotopeMaterialDecay -> {
                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(6000).EUt(32)
                        .input(stickLong, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(stickLong, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();
            });
        }

    }


    private static void processWireGt(OrePrefix wireGt, IngotMaterial material) {
        if (material.cableProperties == null) return;
        int cableAmount = (int) (wireGt.materialAmount * 2 / M);
        OrePrefix cablePrefix = OrePrefix.valueOf("cable" + wireGt.name().substring(4));
        ItemStack cableStack = OreDictUnifier.get(cablePrefix, material);


        for (FluidMaterial fluid : OLD_INSULATION_MATERIAL) {
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material), IntCircuitIngredient.getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(144)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material, 2), IntCircuitIngredient.getIntegratedCircuit(25)}, new FluidStack[]{fluid.getFluid(288)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material, 4), IntCircuitIngredient.getIntegratedCircuit(26)}, new FluidStack[]{fluid.getFluid(576)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material, 8), IntCircuitIngredient.getIntegratedCircuit(27)}, new FluidStack[]{fluid.getFluid(1152)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material, 16), IntCircuitIngredient.getIntegratedCircuit(28)}, new FluidStack[]{fluid.getFluid(2304)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, material), IntCircuitIngredient.getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(288)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtQuadruple, material), IntCircuitIngredient.getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(576)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtOctal, material), IntCircuitIngredient.getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(1152)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtHex, material), IntCircuitIngredient.getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(2304)});
        }

        int tier = GTUtility.getTierByVoltage(material.cableProperties.voltage);
        int cableSize = ArrayUtils.indexOf(WIRE_DOUBLING_ORDER, wireGt);
        if (wireGt != OrePrefix.wireGtSingle) {
            switch (tier) {
                case 0:
                case 1:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, Rubber, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
                case 2:
                case 3:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, Polycaprolactam, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
                case 4:
                case 5:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, Plastic, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
                case 6:
                case 7:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, PolyvinylChloride, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
                case 8:
                case 9:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, PolyphenyleneSulfide, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
                default:
                    RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24 + cableSize).input(OrePrefix.wireGtSingle, material, cableAmount).input(foil, PolyphenyleneSulfide, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();

            }
        }
        switch (tier) {
            case 0:
            case 1:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, Rubber, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
            case 2:
            case 3:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, Polycaprolactam, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
            case 4:
            case 5:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, Plastic, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
            case 6:
            case 7:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, PolyvinylChloride, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
            case 8:
            case 9:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, PolyphenyleneSulfide, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();
            default:
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().circuitMeta(24).input(wireGt, material).input(foil, PolyphenyleneSulfide, cableAmount).outputs(cableStack).duration(150).EUt(8).buildAndRegister();

        }

    }


    private static void processRing(OrePrefix ring, IngotMaterial material) {
        if (!material.hasFlag(NO_SMASHING)) {
            ModHandler.removeRecipes(OreDictUnifier.get(ring, material));
            ModHandler.addShapedRecipe("tod_to_ring_" + material.toString(), OreDictUnifier.get(ring, material), "hS", " C", 'S', OreDictUnifier.get(stick, material), 'C', "craftingToolBendingCylinderSmall");
        }
    }

    private static void processRound(OrePrefix round, IngotMaterial material) {
        ModHandler.addShapedRecipe("round" + material.toString(), OreDictUnifier.get(round, material), "fN", "N ", 'N', OreDictUnifier.get(nugget, material));
        LATHE_RECIPES.recipeBuilder().EUt(8).duration(100).inputs(OreDictUnifier.get(nugget, material)).outputs(OreDictUnifier.get(round, material)).buildAndRegister();
    }

    private static void processDoubleIngot(OrePrefix plate, IngotMaterial material) {
        ModHandler.removeRecipes(OreDictUnifier.get(plate, material));
        ModHandler.addShapedRecipe("ingot_double_" + material.toString(), OreDictUnifier.get(valueOf("ingotDouble"), material), "h", "I", "I", 'I', new UnificationEntry(ingot, material));
        ModHandler.addShapedRecipe("double_ingot_to_plate_" + material.toString(), OreDictUnifier.get(plate, material), "h", "I", 'I', OreDictUnifier.get(valueOf("ingotDouble"), material));
    }

    private static void processPlateCurved(OrePrefix plateCurved, IngotMaterial material) {

        ModHandler.addShapedRecipe("curved_plate_" + material.toString(), OreDictUnifier.get(plateCurved, material), "h", "P", "C", 'P', new UnificationEntry(plate, material), 'C', "craftingToolBendingCylinder");
        ModHandler.addShapedRecipe("flatten_plate_" + material.toString(), OreDictUnifier.get(plate, material), "h", "C", 'C', new UnificationEntry(plateCurved, material));
        BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) material.getMass()).input(plate, material).circuitMeta(1).outputs(OreDictUnifier.get(plateCurved, material)).buildAndRegister();
        BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) material.getMass()).input(plateCurved, material).circuitMeta(0).outputs(OreDictUnifier.get(plate, material)).buildAndRegister();

        if (!OreDictUnifier.get(rotor, material).isEmpty() && GAConfig.GT6.BendingRotors) {
            ModHandler.removeRecipes(OreDictUnifier.get(rotor, material));
            ModHandler.addShapedRecipe("ga_rotor_" + material.toString(), OreDictUnifier.get(rotor, material), "ChC", "SRf", "CdC", 'C', OreDictUnifier.get(plateCurved, material), 'S', OreDictUnifier.get(screw, material), 'R', OreDictUnifier.get(ring, material));
            ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24).inputs(OreDictUnifier.get(plateCurved, material, 4), OreDictUnifier.get(ring, material)).fluidInputs(SolderingAlloy.getFluid(32)).outputs(OreDictUnifier.get(rotor, material)).buildAndRegister();
        }
        if (!OreDictUnifier.get(foil, material).isEmpty()) {
            if (GAConfig.GT6.BendingFoils) {
                ModHandler.addShapedRecipe("foil_" + material.toString(), OreDictUnifier.get(foil, material, 2), "hPC", 'P', new UnificationEntry(plate, material), 'C', "craftingToolBendingCylinder");
            }
            if (GAConfig.GT6.BendingFoilsAutomatic) {
                //Foil recipes
                removeRecipesByInputs(RecipeMaps.BENDER_RECIPES, OreDictUnifier.get(plate, material), IntCircuitIngredient.getIntegratedCircuit(0));
                CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int) material.getMass()).input(plate, material).outputs(OreDictUnifier.get(foil, material, 4)).buildAndRegister();
            }
        }

        if (!OreDictUnifier.get(pipeMedium, material).isEmpty() && GAConfig.GT6.BendingPipes) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:small_" + material.toString() + "_pipe"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:medium_" + material.toString() + "_pipe"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_" + material.toString() + "_pipe"));
            if (!OreDictUnifier.get(plateCurved, material).isEmpty()) {
                ModHandler.addShapedRecipe("pipe_ga_" + material.toString(), OreDictUnifier.get(pipeMedium, material, 2), "PPP", "wCh", "PPP", 'P', OreDictUnifier.get(plateCurved, material), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_large_" + material.toString(), OreDictUnifier.get(pipeLarge, material), "PhP", "PCP", "PwP", 'P', OreDictUnifier.get(plateCurved, material), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_small_" + material.toString(), OreDictUnifier.get(pipeSmall, material, 4), "PwP", "PCP", "PhP", 'P', OreDictUnifier.get(plateCurved, material), 'C', "craftingToolBendingCylinder");
            }
        }
    }

    private static void processMetalCasing(OrePrefix prefix, IngotMaterial material) {
        if (material.hasFlag(GENERATE_METAL_CASING)) {
            ItemStack metalCasingStack = OreDictUnifier.get(prefix, material, 3);
            ModHandler.addShapedRecipe(String.format("metal_casing_%s", material), metalCasingStack,
                    "PhP", "PBP", "PwP",
                    'P', new UnificationEntry(OrePrefix.plate, material),
                    'B', new UnificationEntry(OrePrefix.frameGt, material));


            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(OrePrefix.plate, material, 6)
                    .input(OrePrefix.frameGt, material, 1)
                    .outputs(metalCasingStack)
                    .EUt(8).duration(200)
                    .buildAndRegister();
        }
    }

    public static void processDirtyDust(OrePrefix dustImpurePrefix, DustMaterial dustMaterial) {
        GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES.recipeBuilder().input(dustImpurePrefix, dustMaterial)
                .fluidInputs(Water.getFluid(100))
                .outputs(OreDictUnifier.get(OrePrefix.dust, dustMaterial)).buildAndRegister();
    }

    public static void processTurbine(OrePrefix toolPrefix, IngotMaterial material) {
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, material, 5), OreDictUnifier.get(screw, material, 2), IntCircuitIngredient.getIntegratedCircuit(10));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(stickLong, Titanium), OreDictUnifier.get(turbineBlade, material, 8));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:" + String.format("turbine_blade_%s", material)));

        ItemStack hugeTurbineRotorStackForm = GAMetaItems.HUGE_TURBINE_ROTOR.getStackForm();
        ItemStack largeTurbineRotorStackForm = GAMetaItems.LARGE_TURBINE_ROTOR.getStackForm();
        ItemStack mediumTurbineRotorStackForm = GAMetaItems.MEDIUM_TURBINE_ROTOR.getStackForm();
        ItemStack smallTurbineRotorStackForm = GAMetaItems.SMALL_TURBINE_ROTOR.getStackForm();

        TurbineRotorBehavior.getInstanceFor(smallTurbineRotorStackForm).setPartMaterial(smallTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(mediumTurbineRotorStackForm).setPartMaterial(mediumTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(largeTurbineRotorStackForm).setPartMaterial(largeTurbineRotorStackForm, material);
        TurbineRotorBehavior.getInstanceFor(hugeTurbineRotorStackForm).setPartMaterial(hugeTurbineRotorStackForm, material);

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .input(OrePrefix.turbineBlade, material, 4)
                .input(OrePrefix.stickLong, Titanium)
                .outputs(smallTurbineRotorStackForm)
                .duration(200).EUt(400)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(OrePrefix.turbineBlade, material, 8)
                .input(OrePrefix.stickLong, Tungsten)
                .outputs(mediumTurbineRotorStackForm)
                .duration(400).EUt(800)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(OrePrefix.turbineBlade, material, 16)
                .input(OrePrefix.stickLong, Osmium)
                .outputs(largeTurbineRotorStackForm)
                .duration(800).EUt(1600)
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(OrePrefix.turbineBlade, material, 32)
                .input(OrePrefix.stickLong, Europium)
                .outputs(hugeTurbineRotorStackForm)
                .duration(1600).EUt(3200)
                .buildAndRegister();

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .input(OrePrefix.plateDense, material, 5)
                .input(OrePrefix.screw, material, 2)
                .outputs(OreDictUnifier.get(toolPrefix, material))
                .duration(20).EUt(256)
                .buildAndRegister();

    }

    public static void processReplication(OrePrefix dustPrefix, DustMaterial material) {
        if (material.hasFlag(DISABLE_REPLICATION)) {
            return;
        }

        GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (material.getAverageMass() * 200)).EUt(32)
                .notConsumable(OreDictUnifier.get(dustPrefix, material))
                .fluidInputs(GAMaterials.PositiveMatter.getFluid((int) (material.getAverageProtons())), GAMaterials.NeutralMatter.getFluid((int) (material.getAverageNeutrons())))
                .outputs(OreDictUnifier.get(dustPrefix, material))
                .buildAndRegister();

        GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (material.getAverageMass() * 100)).EUt(32)
                .inputs(OreDictUnifier.get(dustPrefix, material))
                .fluidOutputs(GAMaterials.PositiveMatter.getFluid((int) (material.getAverageProtons())), GAMaterials.NeutralMatter.getFluid((int) (material.getAverageNeutrons())))
                .buildAndRegister();
    }

    public static void registerLargeChemicalRecipes() {
        RecipeMaps.CHEMICAL_RECIPES.getRecipeList().forEach(recipe -> {
            LargeRecipeBuilder largeRecipeMap = GARecipeMaps.LARGE_CHEMICAL_RECIPES.recipeBuilder()
                    .EUt(recipe.getEUt())
                    .duration(recipe.getDuration())
                    .fluidInputs(recipe.getFluidInputs())
                    .inputsIngredients(recipe.getInputs())
                    .outputs(recipe.getOutputs())
                    .fluidOutputs(recipe.getFluidOutputs());

            recipe.getChancedOutputs().forEach(chanceEntry -> {
                largeRecipeMap.chancedOutput(chanceEntry.getItemStack(), chanceEntry.getChance(), chanceEntry.getBoostPerTier());
            });
            largeRecipeMap.buildAndRegister();
        });
    }

    public static void registerLargeMixerRecipes() {
        RecipeMaps.MIXER_RECIPES.getRecipeList().forEach(recipe ->
                GARecipeMaps.LARGE_MIXER_RECIPES.recipeBuilder()
                        .notConsumable(new IntCircuitIngredient(recipe.getInputs().size() + recipe.getFluidInputs().size()))
                        .EUt(recipe.getEUt())
                        .duration(recipe.getDuration())
                        .fluidInputs(recipe.getFluidInputs())
                        .inputsIngredients(recipe.getInputs())
                        .outputs(recipe.getOutputs())
                        .fluidOutputs(recipe.getFluidOutputs())
                        .buildAndRegister());
    }

    public static void registerLargeForgeHammerRecipes() {
        RecipeMaps.FORGE_HAMMER_RECIPES.getRecipeList().forEach(recipe ->
                GARecipeMaps.LARGE_FORGE_HAMMER_RECIPES.recipeBuilder()
                        .EUt(recipe.getEUt())
                        .duration(recipe.getDuration())
                        .fluidInputs(Lubricant.getFluid(2))
                        .inputsIngredients(recipe.getInputs())
                        .outputs(recipe.getOutputs())
                        .fluidOutputs(recipe.getFluidOutputs())
                        .buildAndRegister());
    }


    public static void registerAlloyBlastRecipes() {
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (!(material instanceof IngotMaterial))
                continue;
            IngotMaterial ingotMaterial = (IngotMaterial) material;
            if (ingotMaterial.blastFurnaceTemperature == 0)
                continue;

            GARecipeMaps.LARGE_MIXER_RECIPES.getRecipeList().stream()
                    .filter(recipe -> recipe.getOutputs().size() == 1)
                    .filter(recipe -> recipe.getOutputs().get(0).isItemEqualIgnoreDurability(OreDictUnifier.get(OrePrefix.dust, ingotMaterial)))
                    .findFirst()
                    .ifPresent(recipe -> {
                        ItemStack itemStack = recipe.getOutputs().get(0);
                        IngotMaterial ingot = ((IngotMaterial) (OreDictUnifier.getUnificationEntry(itemStack).material));
                        int duration = Math.max(1, (int) (ingot.getAverageMass() * ingot.blastFurnaceTemperature / 50L));
                        GARecipeMaps.BLAST_ALLOY_RECIPES.recipeBuilder()
                                .duration(duration * 80 / 100)
                                .EUt(120 * itemStack.getCount())
                                .fluidInputs(recipe.getFluidInputs())
                                .inputsIngredients(recipe.getInputs())
                                .fluidOutputs(ingot.getFluid(itemStack.getCount() * GTValues.L)).buildAndRegister();

                    });
        }
    }

    public static void registerChemicalPlantRecipes() {
        RecipeMaps.BREWING_RECIPES.getRecipeList().forEach(recipe -> {
            FluidStack fluidInput = recipe.getFluidInputs().get(0).copy();
            fluidInput.amount = (fluidInput.amount * 10 * 125 / 100);
            CountableIngredient itemInput = new CountableIngredient(recipe.getInputs().get(0).getIngredient(), recipe.getInputs().get(0).getCount() * 10);
            FluidStack fluidOutput = FermentationBase.getFluid(recipe.getFluidOutputs().get(0).amount * 10);

            GARecipeMaps.CHEMICAL_PLANT_RECIPES.recipeBuilder()
                    .EUt(recipe.getEUt() * 10)
                    .duration(recipe.getDuration() * 10)
                    .fluidInputs(fluidInput)
                    .inputsIngredients(Collections.singleton(itemInput))
                    .fluidOutputs(fluidOutput)
                    .buildAndRegister();
        });
    }

    public static void registerGreenHouseRecipes() {
        Arrays.stream(OreDictionary.getOreNames()).filter(name -> name.startsWith("seed")).forEach(name -> {
            String oreName = name.substring(4);
            String seedName = "seed" + titleCase(oreName);
            String cropName = "essence" + titleCase(oreName);

            List<ItemStack> registeredSeeds = OreDictionary.getOres(seedName, false);
            List<ItemStack> registeredCrops = OreDictionary.getOres(cropName, false);

            if (registeredSeeds.isEmpty() || registeredCrops.isEmpty()) {
                return;
            }

            ItemStack seed = registeredSeeds.get(0).copy();
            ItemStack essence = registeredCrops.get(0).copy();


            GREEN_HOUSE_RECIPES.recipeBuilder().duration(4000).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(essence).chancedOutput(seed, 100, 50).buildAndRegister();

            essence = registeredCrops.get(0).copy();
            essence.setCount(3);
            GREEN_HOUSE_RECIPES.recipeBuilder().duration(3000).fluidInputs(Water.getFluid(2000)).notConsumable(seed).input(dust, OrganicFertilizer).outputs(essence).chancedOutput(seed, 100, 50).buildAndRegister();

        });

    }

    public static String titleCase(String input) {
        return input.substring(0, 1).toUpperCase(Locale.US) + input.substring(1);
    }


    private static double getPercentOfComponentInMaterial(Material material, Material materialToFind) {
        if (material == materialToFind) {
            return 1.0;
        }
        double amountOfComponents = material.materialComponents.stream()
                .mapToLong(it -> it.amount).sum();
        return material.materialComponents.stream()
                .mapToDouble(it -> getPercentOfComponentInMaterial(it.material, materialToFind) *
                        (it.amount / amountOfComponents)).sum();
    }
}
