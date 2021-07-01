package gregicadditions.recipes.categories.handlers;

import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

import static gregicadditions.recipes.GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.UUMatter;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.*;

public class OreRecipeHandler {

    private static final List<OrePrefix> ORE_TYPES = Arrays.asList(
            ore, oreBasalt, oreBlackgranite, oreEndstone, oreGravel, oreNetherrack, oreMarble, oreRedgranite, oreSand);

    public static void register() {

        // Ore Tripling
        if (GAConfig.Misc.uuMatterOreProcessing)
            ORE_TYPES.forEach(p -> p.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processOre));

        GAEnums.RICH_ORES.forEach(p -> p.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processRichOre));
        GAEnums.POOR_ORES.forEach(p -> p.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPoorOre));
        GAEnums.PURE_ORES.forEach(p -> p.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processPureOre));

        // Ore Quadrupling and Sextupling
        if (GAConfig.Misc.thermalCentrifugeOreProcessing) {
            crushedPurified.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processCrushedPurified);
        }

        dustImpure.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processDirtyDust);
        crushed.addProcessingHandler(DustMaterial.class, OreRecipeHandler::processCrushed);
    }


    public static void processOre(OrePrefix orePrefix, DustMaterial material) {

        ItemStack crushedStack = OreDictUnifier.get(crushed, material.crushedInto);
        crushedStack.setCount(crushedStack.getCount() * material.oreMultiplier);

        DustMaterial byproductMaterial = GTUtility.selectItemInList(0, material, material.oreByProducts, DustMaterial.class);
        ItemStack byproductStack = OreDictUnifier.get(dust, byproductMaterial, 2);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);

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
        ItemStack byproductStack = OreDictUnifier.get(dust, byproductMaterial, 1);
        ItemStack crushedStack = OreDictUnifier.get(crushed, material.crushedInto);
        DustMaterial smeltingMaterial = material.directSmelting == null ? material : material.directSmelting;
        double amountOfCrushedOre = material.oreMultiplier / getPercentOfComponentInMaterial(material, smeltingMaterial);

        ItemStack ingotStack = smeltingMaterial instanceof IngotMaterial ?
                OreDictUnifier.get(nugget, smeltingMaterial, 5) :
                OreDictUnifier.get(dustSmall, smeltingMaterial, 2);

        ingotStack.setCount(ingotStack.getCount() * material.oreMultiplier);

        if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
        }

        if (!crushedStack.isEmpty()) {

            FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(6)
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .buildAndRegister();

            RecipeBuilder<?> builder = MACERATOR_RECIPES.recipeBuilder().duration(400).EUt(12)
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.round(amountOfCrushedOre), crushedStack))
                    .chancedOutput(byproductStack, 700, 425);

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
        ItemStack byproductStack = OreDictUnifier.get(dust, byproductMaterial, 2);
        ItemStack crushedStack = OreDictUnifier.get(crushed, material.crushedInto);
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
            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .duration(100).EUt(6)
                    .buildAndRegister();

            RecipeBuilder<?> builder = MACERATOR_RECIPES.recipeBuilder()
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
        ItemStack byproductStack = OreDictUnifier.get(dust, byproductMaterial, 4);

        ItemStack crushedStack = OreDictUnifier.get(crushed, material.crushedInto);
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
            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(orePrefix, material)
                    .outputs(GTUtility.copyAmount((int) Math.ceil(amountOfCrushedOre), crushedStack))
                    .duration(100).EUt(6)
                    .buildAndRegister();

            RecipeBuilder<?> builder = MACERATOR_RECIPES.recipeBuilder()
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

        ItemStack crushedCentrifugedStack = OreDictUnifier.get(crushedCentrifuged, material);
        Material byproductMaterial = GTUtility.selectItemInList(1, material, material.oreByProducts, DustMaterial.class);

        if (!crushedCentrifugedStack.isEmpty()) {

            removeRecipesByInputs(THERMAL_CENTRIFUGE_RECIPES, OreDictUnifier.get(purifiedPrefix, material));

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                    .input(purifiedPrefix, material)
                    .outputs(GTUtility.copyAmount(2, crushedCentrifugedStack))
                    .chancedOutput(OreDictUnifier.get(dustTiny, byproductMaterial, 6), 5000, 500)
                    .duration((int) (material.getAverageMass() * 24))
                    .EUt(60)
                    .buildAndRegister();
        }
    }

    public static void processDirtyDust(OrePrefix dustImpurePrefix, DustMaterial dustMaterial) {

        SIMPLE_ORE_WASHER_RECIPES.recipeBuilder()
                .input(dustImpurePrefix, dustMaterial)
                .fluidInputs(Water.getFluid(100))
                .output(dust, dustMaterial)
                .buildAndRegister();
    }

    public static void processCrushed(OrePrefix dustCrushedPrefix, DustMaterial material) {
        if (OreDictUnifier.get(crushedPurified, material).isEmpty()) {
            return;
        }
        SIMPLE_ORE_WASHER_RECIPES.recipeBuilder()
                .input(dustCrushedPrefix, material)
                .fluidInputs(Water.getFluid(100))
                .output(crushedPurified, material)
                .buildAndRegister();
    }

    private static double getPercentOfComponentInMaterial(Material material, Material materialToFind) {

        if (material == materialToFind)
            return 1.0;

        double amountOfComponents = material.materialComponents.stream()
                                                               .mapToLong(it -> it.amount)
                                                               .sum();

        return material.materialComponents.stream()
                                          .mapToDouble(it -> getPercentOfComponentInMaterial(it.material, materialToFind)
                                                           * (it.amount / amountOfComponents)).sum();
    }
}
