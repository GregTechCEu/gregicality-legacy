package gregicadditions.recipes;

import gregicadditions.GAEnums;
import gregicadditions.GAValues;
import gregicadditions.materials.IsotopeMaterial;
import gregicadditions.materials.RadioactiveMaterial;
import gregicadditions.recipes.map.NuclearReactorBuilder;
import gregtech.api.recipes.builders.IntCircuitRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;

import java.util.stream.IntStream;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.PYROLYTIC_CARBON;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BALL;

public class NuclearHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearMaterial);
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearFuel);
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearDepleatedFuel);
    }

    private static void processNuclearDepleatedFuel(OrePrefix ingot, IngotMaterial material) {
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (isotopeMaterial == null) {
            return;
        }
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, 1))
                .fluidInputs(Fluorine.getFluid(2000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(CarbonFluoride.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, 1))
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .outputs(ZirconiumTetrachloride.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(3000)
                .input(dust, Boron)
                .fluidInputs(NitricAcid.getFluid(1000))
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dustTiny, Boron, 7))
                .fluidOutputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .buildAndRegister();


        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(3000)
                .fluidInputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(RP1.getFluid(1000))
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .input(dust, FerriteMixture)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, 1))
                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm())
                .fluidOutputs(RedOil.getFluid(2000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().EUt(480).duration(2000)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, 1))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm())
                .buildAndRegister();


    }

    private static void processNuclearFuel(OrePrefix ingot, IngotMaterial material) {
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (isotopeMaterial == null) {
            return;
        }
        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(ingot, isotopeMaterial.getMaterial())
                .input(ingot, Zirconium)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .fluidInputs(Chlorine.getFluid(3000))
                .outputs(OreDictUnifier.get(ingot, isotopeMaterial.getMaterial()))
                .outputs(ZirconiumTetrachloride.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(ingot, isotopeMaterial.getMaterial())
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .input(dust, Carbon, 2)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(1000).EUt(120).duration(3000)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidInputs(Nitrogen.getFluid(3000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 1))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 1))
                .fluidInputs(Water.getFluid(3000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .fluidOutputs(Ammonia.getFluid(3000))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelCarbide, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelZirconiumAlloy, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 1))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelNitride, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelOxide, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .input(ingot, isotopeMaterial.getMaterial())
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelPure, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelCarbide, 1))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .inputs(PYROLYTIC_CARBON.getStackForm())
                .input(dust, Graphite)
                .inputs(SiliconCarbide.getItemStack())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelTRISO, 1))
                .buildAndRegister();
    }


    private static void processNuclearMaterial(OrePrefix ingot, IngotMaterial material) {
        RadioactiveMaterial radioactiveMaterial = RadioactiveMaterial.REGISTRY.get(material);
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (radioactiveMaterial != null && radioactiveMaterial.composition.size() > 0) {
            int complexity = radioactiveMaterial.complexity;


            CHEMICAL_RECIPES.recipeBuilder().duration(2000 * complexity / 100)
                    .input(dust, radioactiveMaterial.getMaterial())
                    .fluidInputs(NitricAcid.getFluid(2000))
                    .outputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.nitrite, 3))
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(100 * complexity / 100).EUt(120 * complexity / 100)
                    .inputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.nitrite, 1))
                    .fluidInputs(Water.getFluid(6000))
                    .outputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                    .fluidOutputs(NitrogenTetroxide.getFluid(1000))
                    .buildAndRegister();


            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .inputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                    .fluidInputs(Chlorine.getFluid(6000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexachloride(6000))
                    .fluidOutputs(Oxygen.getFluid(1000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .fluidInputs(radioactiveMaterial.getFluidHexachloride(2000))
                    .fluidInputs(HydrofluoricAcid.getFluid(10000))
                    .fluidOutputs(HydrochloricAcid.getFluid(10000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexafluoride(2000))
                    .buildAndRegister();

            IntCircuitRecipeBuilder builder1 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(0).EUt(GAValues.V[GAValues.ULV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder1.fluidOutputs(key.getFluidHexafluoride(value * 2)));
            builder1.buildAndRegister();

            IntCircuitRecipeBuilder builder2 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(1).EUt(GAValues.V[GAValues.LV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder2.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 110 / 100 : value * 2) * 2)));
            builder2.buildAndRegister();

            IntCircuitRecipeBuilder builder3 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(2).EUt(GAValues.V[GAValues.MV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder3.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 120 / 100 : value * 3) * 2)));
            builder3.buildAndRegister();

            IntCircuitRecipeBuilder builder4 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(3).EUt(GAValues.V[GAValues.HV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder4.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 130 / 100 : value * 4) * 2)));
            builder4.buildAndRegister();

            IntCircuitRecipeBuilder builder5 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(4).EUt(GAValues.V[GAValues.EV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder5.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 140 / 100 : value * 5) * 2)));
            builder5.buildAndRegister();

            IntCircuitRecipeBuilder builder6 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(5).EUt(GAValues.V[GAValues.IV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder6.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 150 / 100 : value * 6) * 2)));
            builder6.buildAndRegister();

            IntCircuitRecipeBuilder builder7 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(6).EUt(GAValues.V[GAValues.LuV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder7.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 160 / 100 : value * 7) * 2)));
            builder7.buildAndRegister();

            IntCircuitRecipeBuilder builder8 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(7).EUt(GAValues.V[GAValues.ZPM]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder8.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 170 / 100 : value * 8) * 2)));
            builder8.buildAndRegister();

            IntCircuitRecipeBuilder builder9 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(8).EUt(GAValues.V[GAValues.UV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder9.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 180 / 100 : value * 9) * 2)));
            builder9.buildAndRegister();

            IntCircuitRecipeBuilder builder10 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(9).EUt(GAValues.V[GAValues.UHV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder10.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 190 / 100 : value * 10) * 2)));
            builder10.buildAndRegister();

            IntCircuitRecipeBuilder builder11 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(10).EUt(GAValues.V[GAValues.UEV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder11.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 200 / 100 : value * 11) * 2)));
            builder11.buildAndRegister();

            IntCircuitRecipeBuilder builder12 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(11).EUt(GAValues.V[GAValues.UIV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder12.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 210 / 100 : value * 12) * 2)));
            builder12.buildAndRegister();

            IntCircuitRecipeBuilder builder13 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(12).EUt(GAValues.V[GAValues.UMV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder13.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 220 / 100 : value * 13) * 2)));
            builder13.buildAndRegister();

            IntCircuitRecipeBuilder builder14 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(10000 * complexity / 100).circuitMeta(13).EUt(GAValues.V[GAValues.UXV]).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder14.fluidOutputs(key.getFluidHexafluoride((value >= 5000 ? value : value >= 1000 ? value * 230 / 100 : value * 14) * 2)));
            builder14.buildAndRegister();


            radioactiveMaterial.composition.forEach((key, value) -> {
                CRACKING_RECIPES.recipeBuilder().duration(40 * complexity / 100).EUt(120 * complexity / 100)
                        .fluidInputs(Steam.getFluid(1000))
                        .fluidInputs(key.getFluidHexafluoride(6000))
                        .fluidOutputs(key.getFluidHexafluorideSteamCracked(7000))
                        .buildAndRegister();
                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(600 * complexity / 100).EUt(120 * complexity / 100)
                        .notConsumable(new IntCircuitIngredient(0))
                        .fluidInputs(key.getFluidHexafluorideSteamCracked(7000))
                        .outputs(key.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                        .fluidOutputs(Fluorine.getFluid(5000))
                        .buildAndRegister();

                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(1000 * complexity / 100).EUt(120 * complexity / 100)
                        .inputs(key.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                        .outputs(OreDictUnifier.get(ingot, key.getMaterial()))
                        .fluidOutputs(Oxygen.getFluid(2000))
                        .buildAndRegister();
            });
        } else if (isotopeMaterial != null && isotopeMaterial.fissile) {
            IntStream.range(1, 10).forEach(operand -> {
                NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(20000).EUt(((isotopeMaterial.baseHeat + operand) * operand * 2) * 80 / 100) //20000 => 80% eff
                        .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand * 2)
                        .notConsumable(new IntCircuitIngredient(operand + 10))
                        .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelTRISO, operand))
                        .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, operand))
                        .buildAndRegister();
                NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(8000).EUt(((isotopeMaterial.baseHeat + operand) * operand * 2) * 105 / 100) //8000 => 105% eff
                        .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand * 2)
                        .notConsumable(new IntCircuitIngredient(operand + 10))
                        .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelOxide, operand))
                        .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, operand))
                        .buildAndRegister();
                NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(10000).EUt(((isotopeMaterial.baseHeat + operand) * operand * 2) * 110 / 100) //10000 => 110% eff
                        .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand * 2)
                        .notConsumable(new IntCircuitIngredient(operand + 10))
                        .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelNitride, operand))
                        .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, operand))
                        .buildAndRegister();
                NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(15000).EUt(((isotopeMaterial.baseHeat + operand) * operand * 2) * 120 / 100) //15000 => 120% eff
                        .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand * 2)
                        .notConsumable(new IntCircuitIngredient(operand + 10))
                        .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelZirconiumAlloy, operand))
                        .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, operand))
                        .buildAndRegister();
            });

            IntStream.range(1, 10).forEach(operand ->
                    IsotopeMaterial.REGISTRY.entrySet().stream()
                            .filter(isotopeMaterialEntry -> isotopeMaterialEntry.getValue().fertile)
                            .forEach(isotopeMaterialEntry -> {
                                        NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(40000).EUt(((isotopeMaterial.baseHeat + operand) * operand / 2) * 80 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelTRISO, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, operand))
                                                .outputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9))
                                                .buildAndRegister();
                                        NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(16000).EUt(((isotopeMaterial.baseHeat + operand) * operand / 2) * 105 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelOxide, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, operand))
                                                .outputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9))
                                                .buildAndRegister();
                                        NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(20000).EUt(((isotopeMaterial.baseHeat + operand) * operand / 2) * 110 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelNitride, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, operand))
                                                .outputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9))
                                                .buildAndRegister();
                                        NUCLEAR_REACTOR_RECIPES.recipeBuilder().duration(30000).EUt(((isotopeMaterial.baseHeat + operand) * operand / 2) * 120 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelZirconiumAlloy, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, operand))
                                                .outputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9))
                                                .buildAndRegister();

                                        NuclearReactorBuilder builder1 = NUCLEAR_BREEDER_RECIPES.recipeBuilder().duration(10000).EUt((isotopeMaterial.baseHeat + operand) * operand * 80 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand / 5)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelTRISO, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder1.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9), value, 100));
                                        builder1.buildAndRegister();

                                        NuclearReactorBuilder builder2 = NUCLEAR_BREEDER_RECIPES.recipeBuilder().duration(4000).EUt((isotopeMaterial.baseHeat + operand) * operand * 105 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand / 5)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelOxide, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder2.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9), value, 100));
                                        builder2.buildAndRegister();

                                        NuclearReactorBuilder builder3 = NUCLEAR_BREEDER_RECIPES.recipeBuilder().duration(5000).EUt((isotopeMaterial.baseHeat + operand) * operand * 110 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand / 5)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelNitride, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder3.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9), value, 100));
                                        builder3.buildAndRegister();

                                        NuclearReactorBuilder builder4 = NUCLEAR_BREEDER_RECIPES.recipeBuilder().duration(7500).EUt((isotopeMaterial.baseHeat + operand) * operand * 120 / 100)
                                                .baseHeatProduction((isotopeMaterial.baseHeat + operand) * operand / 5)
                                                .notConsumable(new IntCircuitIngredient(operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder4.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9), value, 100));
                                        builder4.buildAndRegister();
                                    }
                            ));
        } else if (isotopeMaterial != null && !isotopeMaterial.fertile && isotopeMaterial.isotopeDecay.size() > 0) {
            isotopeMaterial.isotopeDecay.keySet().forEach(isotopeMaterialDecay -> {
                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(6000).EUt(32)
                        .input(GAEnums.GAOrePrefix.fuelOxide, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelOxide, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(6000).EUt(32)
                        .input(GAEnums.GAOrePrefix.fuelNitride, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelNitride, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(6000).EUt(32)
                        .input(GAEnums.GAOrePrefix.fuelTRISO, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelTRISO, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(6000).EUt(32)
                        .input(GAEnums.GAOrePrefix.fuelZirconiumAlloy, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelZirconiumAlloy, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

            });
        }

    }

}
