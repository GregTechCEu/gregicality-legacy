package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;

import static gregicadditions.recipes.helper.AdditionMethods.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES;
import static gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.UUMatter;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.*;

public class OreRecipeHandler { // TODO

    public static void register() {
        //ore tripling
        if (GAConfig.Misc.uuMatterOreProcessing) {
            OrePrefix.ore.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreBasalt.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreBlackgranite.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreEndstone.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreGravel.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreNetherrack.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreMarble.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreRedgranite.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
            OrePrefix.oreSand.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre);
        }
        OrePrefix.valueOf("oreRich").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichBlackgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichRedgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichMarble").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichBasalt").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichSand").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichGravel").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichNetherrack").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("oreRichEndstone").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre);
        OrePrefix.valueOf("orePoor").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorBlackgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorRedgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorMarble").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorBasalt").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorSand").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorGravel").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorNetherrack").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePoorEndstone").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre);
        OrePrefix.valueOf("orePure").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureBlackgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureRedgranite").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureMarble").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureBasalt").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureSand").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureGravel").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureNetherrack").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);
        OrePrefix.valueOf("orePureEndstone").addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre);

        //ore quad and sextupling
        if (GAConfig.Misc.thermalCentrifugeOreProcessing) {
            OrePrefix.crushedPurified.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processCrushedPurified);
        }

        OrePrefix.dustImpure.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processDirtyDust);
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

    private static boolean doesMaterialUseNormalFurnace(Material material) {
        return !(material instanceof IngotMaterial) ||
                ((IngotMaterial) material).blastFurnaceTemperature <= 0;
    }

    public static void processPoorOre(OrePrefix orePrefix, DustMaterial material) {
        DustMaterial byproductMaterial = GTUtility.selectItemInList(0, material, material.oreByProducts, DustMaterial.class);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial, 1);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material.crushedInto);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);
        ItemStack ingotStack;

        if (smeltingMaterial instanceof IngotMaterial) {
            ingotStack = OreDictUnifier.get(OrePrefix.nugget, smeltingMaterial, 5);
        } else if (smeltingMaterial instanceof GemMaterial) {
            ingotStack = OreDictUnifier.get(dustSmall, smeltingMaterial, 2);
        } else {
            ingotStack = OreDictUnifier.get(dustSmall, smeltingMaterial, 2);
        }
        ingotStack.setCount(ingotStack.getCount() * material.oreMultiplier);
        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
        }

        if (!crushedStack.isEmpty()) {
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .duration(100).EUt(6)
                    .buildAndRegister();

            RecipeBuilder<?> builder = RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre), crushedStack))
                    .chancedOutput(byproductStack, 700, 425)
                    .duration(400).EUt(12);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material instanceof DustMaterial) {
                    ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                    builder.chancedOutput(dustStack, 3350, 400);
                }
            }
            builder.buildAndRegister();

            if (GAConfig.Misc.uuMatterOreProcessing) {
                byproductStack.setCount(byproductStack.getCount() * 2);
                builder = CHEMICAL_BATH_RECIPES.recipeBuilder()
                        .fluidInputs(UUMatter.getFluid((int) material.getAverageMass() / 2))
                        .input(orePrefix, material)
                        .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 2, crushedStack))
                        .chancedOutput(byproductStack, 1400, 850)
                        .duration(800).EUt(24);
                for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                    if (secondaryMaterial.material instanceof DustMaterial) {
                        ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                        dustStack.setCount(dustStack.getCount() * 2);
                        builder.chancedOutput(dustStack, 6700, 800);
                    }
                }
                builder.buildAndRegister();
            }
        }
    }

    public static void processRichOre(OrePrefix orePrefix, DustMaterial material) {
        DustMaterial byproductMaterial = GTUtility.selectItemInList(0, material, material.oreByProducts, DustMaterial.class);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial, 2);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material.crushedInto);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = 2 * material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);
        ItemStack ingotStack;

        if (smeltingMaterial instanceof IngotMaterial) {
            ingotStack = OreDictUnifier.get(ingot, smeltingMaterial, 2);
        } else if (smeltingMaterial instanceof GemMaterial) {
            ingotStack = OreDictUnifier.get(gem, smeltingMaterial, 2);
        } else {
            ingotStack = OreDictUnifier.get(dust, smeltingMaterial, 2);
        }
        ingotStack.setCount(ingotStack.getCount() * material.oreMultiplier);
        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
        }

        if (!crushedStack.isEmpty()) {
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .duration(100).EUt(6)
                    .buildAndRegister();

            RecipeBuilder<?> builder = RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 2, crushedStack))
                    .chancedOutput(byproductStack, 1400, 850)
                    .duration(400).EUt(12);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material instanceof DustMaterial) {
                    ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                    dustStack.setCount(dustStack.getCount() * 2);
                    builder.chancedOutput(dustStack, 6700, 800);
                }
            }
            builder.buildAndRegister();

            if (GAConfig.Misc.uuMatterOreProcessing) {
                byproductStack.setCount(byproductStack.getCount() * 2);
                builder = CHEMICAL_BATH_RECIPES.recipeBuilder()
                        .fluidInputs(UUMatter.getFluid((int) material.getAverageMass() * 2))
                        .input(orePrefix, material)
                        .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 3, crushedStack))
                        .chancedOutput(byproductStack, 1400, 850)
                        .duration(800).EUt(24);
                for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                    if (secondaryMaterial.material instanceof DustMaterial) {
                        ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                        dustStack.setCount(dustStack.getCount() * 2);
                        builder.chancedOutput(dustStack, 6700, 800);
                    }
                }
                builder.buildAndRegister();
            }
        }
    }

    public static void processPureOre(OrePrefix orePrefix, DustMaterial material) {
        DustMaterial byproductMaterial = GTUtility.selectItemInList(0, material, material.oreByProducts, DustMaterial.class);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial, 4);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material.crushedInto);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = 4 * material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);
        ItemStack ingotStack;

        if (smeltingMaterial instanceof IngotMaterial) {
            ingotStack = OreDictUnifier.get(ingot, smeltingMaterial, 4);
        } else if (smeltingMaterial instanceof GemMaterial) {
            ingotStack = OreDictUnifier.get(gem, smeltingMaterial, 4);
        } else {
            ingotStack = OreDictUnifier.get(dust, smeltingMaterial, 4);
        }
        ingotStack.setCount(ingotStack.getCount() * material.oreMultiplier);
        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
        }

        if (!crushedStack.isEmpty()) {
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .duration(100).EUt(6)
                    .buildAndRegister();

            RecipeBuilder<?> builder = RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 2, crushedStack))
                    .chancedOutput(byproductStack, 1400, 850)
                    .duration(400).EUt(12);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material instanceof DustMaterial) {
                    ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                    dustStack.setCount(dustStack.getCount() * 4);
                    builder.chancedOutput(dustStack, 6700, 800);
                }
            }
            builder.buildAndRegister();

            if (GAConfig.Misc.uuMatterOreProcessing) {
                byproductStack.setCount(byproductStack.getCount() * 2);
                builder = CHEMICAL_BATH_RECIPES.recipeBuilder()
                        .fluidInputs(UUMatter.getFluid((int) material.getAverageMass() * 3))
                        .input(orePrefix, material)
                        .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre) * 3, crushedStack))
                        .chancedOutput(byproductStack, 1400, 850)
                        .duration(800).EUt(24);
                for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                    if (secondaryMaterial.material instanceof DustMaterial) {
                        ItemStack dustStack = OreDictUnifier.getDust(secondaryMaterial);
                        dustStack.setCount(dustStack.getCount() * 4);
                        builder.chancedOutput(dustStack, 6700, 800);
                    }
                }
                builder.buildAndRegister();
            }
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

    public static void processDirtyDust(OrePrefix dustImpurePrefix, DustMaterial dustMaterial) {
        GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES.recipeBuilder().input(dustImpurePrefix, dustMaterial)
                .fluidInputs(Water.getFluid(100))
                .outputs(OreDictUnifier.get(OrePrefix.dust, dustMaterial)).buildAndRegister();
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
