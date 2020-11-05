package gregicadditions.recipes;

import gregicadditions.GAEnums;
import gregicadditions.materials.IsotopeMaterial;
import gregicadditions.materials.RadioactiveMaterial;
import gregicadditions.recipes.map.NuclearReactorBuilder;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;

import java.util.stream.IntStream;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.PYROLITIC_CARBON;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

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
        GALog.logger.info(isotopeMaterial.toString());
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelTRISO, 1))
                .fluidInputs(Fluorine.getFluid(2000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(CarbonFluoride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, 1))
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(CarbonFluoride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuel, 1))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(1900).duration(3000)
                .input(stick, Boron)
                .fluidInputs(NitricAcid.getFluid(1000))
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelOxide, 1))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Boron))
                .fluidOutputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .buildAndRegister();


        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(1900).duration(3000)
                .fluidInputs(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000))
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(RP1.getFluid(1000))
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .input(dust, FerriteMixture)
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.depletedFuelNitride, 1))
                .outputs(isotopeMaterial.getRadioactiveMaterial().waste.getStackForm())
                .fluidOutputs(RedOil.getFluid(2000))
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
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelCarbide, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.zirconiumAlloy, 1))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelZirconiumAlloy, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.nitride, 1))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelNitride, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.oxide, 1))
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelOxide, 1))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .input(ingot, isotopeMaterial.getMaterial())
                .outputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelPure, 1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(isotopeMaterial.getItemStack(GAEnums.GAOrePrefix.fuelCarbide, 1))
                .inputs(PYROLITIC_CARBON.getStackForm())
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
                    .fluidOutputs(Oxygen.getFluid(2000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(1000 * complexity / 100)
                    .fluidInputs(radioactiveMaterial.getFluidHexachloride(2000))
                    .fluidInputs(HydrofluoricAcid.getFluid(10000))
                    .fluidOutputs(HydrochloricAcid.getFluid(10000))
                    .fluidOutputs(radioactiveMaterial.getFluidHexafluoride(2000))
                    .buildAndRegister();


            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(100 * complexity / 100).EUt(120)
                    .fluidInputs(radioactiveMaterial.getFluidHexafluoride(1000))
                    .outputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.hexafluoride, 1))
                    .buildAndRegister();


            SimpleRecipeBuilder builder = THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000 * complexity / 100).EUt(60 * complexity / 100)
                    .inputs(radioactiveMaterial.getItemStack(GAEnums.GAOrePrefix.hexafluoride, 1));
            radioactiveMaterial.composition.forEach((key, value) -> builder.chancedOutput(key.getItemStack(GAEnums.GAOrePrefix.hexafluoride, 1), value, 100));
            builder.buildAndRegister();


            radioactiveMaterial.composition.forEach((key, value) -> {
                BLAST_RECIPES.recipeBuilder().blastFurnaceTemp(600).duration(600 * complexity / 100).EUt(120 * complexity / 100)
                        .fluidInputs(Steam.getFluid(6000))
                        .inputs(key.getItemStack(GAEnums.GAOrePrefix.hexafluoride, 1))
                        .outputs(key.getItemStack(GAEnums.GAOrePrefix.dioxide, 1))
                        .fluidOutputs(Fluorine.getFluid(6000))
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
                        .input(stickLong, isotopeMaterial.getMaterial())
                        .chancedOutput(OreDictUnifier.get(stickLong, isotopeMaterialDecay.getMaterial()), 9000, 100)
                        .buildAndRegister();
            });
        }

    }

}
