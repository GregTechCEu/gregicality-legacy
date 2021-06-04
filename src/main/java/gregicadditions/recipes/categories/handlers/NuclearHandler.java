package gregicadditions.recipes.categories.handlers;

import gregicadditions.GAEnums;
import gregicadditions.GAValues;
import gregicadditions.materials.IsotopeMaterial;
import gregicadditions.materials.RadioactiveMaterial;
import gregicadditions.recipes.impl.NuclearReactorBuilder;
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
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BALL;

public class NuclearHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearMaterial);
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearFuel);
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, NuclearHandler::processNuclearDepletedFuel);
    }

    private static void processNuclearDepletedFuel(OrePrefix ingot, IngotMaterial material) {
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (isotopeMaterial == null) {
            return;
        }
        // 8F + C? + C?? + Fuel TRISO = Depleted Fuel + SiF4 + CF4
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, 1))
                .fluidInputs(Fluorine.getFluid(8000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(CarbonFluoride.getFluid(1000))
                .buildAndRegister();

        // FuelZr + 4Cl = ZrCl4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, 1))
                .fluidInputs(Chlorine.getFluid(4000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .outputs(ZirconiumTetrachloride.getItemStack(5))
                .buildAndRegister();

        // Fuel + O = [Fuel + O]
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .buildAndRegister();

        // HNO3 + [Fuel + O] + O = [Fuel + NO3 + H2O]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(3000)
                .notConsumable(dust, Boron)
                .fluidInputs(NitricAcid.getFluid(1000))
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .fluidOutputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .buildAndRegister();

        // [Fuel + NO3 + H2O] + N2H4 + RP1 + C12H27O4P = Fuel2N3 + Red Oil [N2H4 + RP1 + C12H27O4P]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(3000)
                .fluidInputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(RP1.getFluid(1000))
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .input(dust, FerriteMixture)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, 1))
                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm())
                .fluidOutputs(RedOil.getFluid(3000))
                .buildAndRegister();

        // Fuel2N3 = Waste + 3N
        ELECTROLYZER_RECIPES.recipeBuilder().EUt(480).duration(2000)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, 1))
                .fluidOutputs(Nitrogen.getFluid(3000))
                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm())
                .buildAndRegister();


    }

    private static void processNuclearFuel(OrePrefix ingot, IngotMaterial material) {
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (isotopeMaterial == null) {
            return;
        }
        // Fuel + Zr = [Fuel + Zr]
        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(ingot, isotopeMaterial.getMaterial())
                .input(ingot, Zirconium)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .buildAndRegister();

        // [Fuel + Zr] + 4Cl = Fuel + ZrCl4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .fluidInputs(Chlorine.getFluid(3000))
                .outputs(OreDictUnifier.get(ingot, isotopeMaterial.getMaterial()))
                .outputs(ZirconiumTetrachloride.getItemStack(5))
                .buildAndRegister();

        // Fuel + O = [Fuel + O]
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(ingot, isotopeMaterial.getMaterial())
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .buildAndRegister();

        // [Fuel + O] + 2C = [Fuel + C] + CO
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .input(dust, Carbon, 2)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // [Fuel + C] + 4O = CO2 + [Fuel + 2O]
        BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(1000).EUt(120).duration(2000)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidInputs(Oxygen.getFluid(4000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .buildAndRegister();

        // [Fuel + O] + [Fuel + C] + 3N = Fuel2N3 + CO
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.carbide, 1))
                .fluidInputs(Nitrogen.getFluid(3000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 2))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // Fuel2N3 + 4H2O + 3O = 2[Fuel + 2O] + H2O + NO2 + 2NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 1))
                .fluidInputs(Water.getFluid(3000))
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 2))
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
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

            // Mat + 2HNO3 = [Mat + 2NO3] + 2H
            CHEMICAL_RECIPES.recipeBuilder().duration(200 * complexity / 100)
                    .input(dust, radioactiveMaterial.getMaterial())
                    .fluidInputs(NitricAcid.getFluid(2000))
                    .outputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.nitrite, 3))
                    .fluidOutputs(Hydrogen.getFluid(2000))
                    .buildAndRegister();

            // [Mat + 2NO3] = [Mat + 2O] + 2NO
            BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(90 * complexity / 100).EUt(120)
                    .inputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.nitrite, 3))
                    .outputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                    .fluidOutputs(NitricOxide.getFluid(2000))
                    .buildAndRegister();

            // [Mat + 2O] + 6Cl = [Mat + 6Cl] + 2O
            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .inputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                    .fluidInputs(Chlorine.getFluid(6000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexachloride(1000))
                    .fluidOutputs(Oxygen.getFluid(2000))
                    .buildAndRegister();

            // [Mat + 6Cl] + 6HF = 6HCl + [Mat + 6F]
            CHEMICAL_RECIPES.recipeBuilder().duration(100 * complexity / 100)
                    .fluidInputs(radioactiveMaterial.getFluidHexachloride(1000))
                    .fluidInputs(HydrofluoricAcid.getFluid(6000))
                    .fluidOutputs(HydrochloricAcid.getFluid(6000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexafluoride(1000))
                    .buildAndRegister();

            IntCircuitRecipeBuilder builder1 = GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(500 * complexity / 100).EUt(960).fluidInputs(radioactiveMaterial.getFluidHexafluoride(20000));
            radioactiveMaterial.composition.forEach((key, value) -> builder1.fluidOutputs(key.getFluidHexafluoride(value * 2)));
            builder1.buildAndRegister();

            radioactiveMaterial.composition.forEach((key, value) -> {
                // [Mat + F6] + 3H2O -> [Mat + F6 + 3H2O]
                CRACKING_RECIPES.recipeBuilder().duration(40 * complexity / 100).EUt(120)
                        .fluidInputs(Steam.getFluid(3000))
                        .fluidInputs(key.getFluidHexafluoride(1000))
                        .fluidOutputs(key.getFluidHexafluorideSteamCracked(1000))
                        .buildAndRegister();

                // [Mat + F6 + 3H2O] -> [Mat + 2O] + 6HF + O (O lost)
                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(600 * complexity / 100).EUt(120)
                        .notConsumable(new IntCircuitIngredient(0))
                        .fluidInputs(key.getFluidHexafluorideSteamCracked(1000))
                        .outputs(key.getItemStack(GAEnums.GAOrePrefix.dioxide, 3))
                        .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                        .buildAndRegister();

                // [Mat + 2O] -> Mat + 2O
                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(1000 * complexity / 100).EUt(120)
                        .inputs(key.getItemStack(GAEnums.GAOrePrefix.dioxide, 3))
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
                                                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelZirconiumAlloy, operand))
                                                .inputs(isotopeMaterialEntry.getValue().getItemStack(GAEnums.GAOrePrefix.fuelPure, 9))
                                                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, operand));

                                        isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) ->
                                                builder4.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 9), value, 100));
                                        builder4.buildAndRegister();
                                    }
                            ));
        } else if (isotopeMaterial != null && !isotopeMaterial.fertile && isotopeMaterial.isotopeDecay.size() > 0) {
            isotopeMaterial.isotopeDecay.keySet().forEach(isotopeMaterialDecay -> {
                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(600).EUt(30)
                        .input(GAEnums.GAOrePrefix.fuelOxide, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelOxide, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(600).EUt(30)
                        .input(GAEnums.GAOrePrefix.fuelNitride, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelNitride, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(600).EUt(30)
                        .input(GAEnums.GAOrePrefix.fuelTRISO, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelTRISO, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

                DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(600).EUt(30)
                        .input(GAEnums.GAOrePrefix.fuelZirconiumAlloy, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(GAEnums.GAOrePrefix.fuelZirconiumAlloy, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();

            });
        }

    }

}
