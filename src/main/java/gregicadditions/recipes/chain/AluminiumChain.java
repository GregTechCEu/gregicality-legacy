package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class AluminiumChain {
    public static void init() {

        // 2Al(OH)3 -> Al2O3 + 3H2O
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(1100)
                .inputs(AluminiumHydroxide.getItemStack(14))
                .outputs(Alumina.getItemStack(5))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 6NaOH + Al2O3 + 12HF -> 2Na3AlF6 + 9H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(120)
                .input(dust, SodiumHydroxide, 18)
                .inputs(Alumina.getItemStack(5))
                .fluidInputs(HydrofluoricAcid.getFluid(12000))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(2000))
                .fluidOutputs(Water.getFluid(9000))
                .buildAndRegister();

        // 2Al2O3 + Na3AlF6 -> 4Al + 3NaF + AlF3 + 6O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(Alumina.getItemStack(10))
                .fluidInputs(SodiumHexafluoroaluminate.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .output(dust, Aluminium, 4)
                .output(dust, SodiumFluoride, 6)
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // 3NaF + AlF3 -> Na3AlF6
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, SodiumFluoride, 6)
                .inputs(AluminiumTrifluoride.getItemStack(4))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .buildAndRegister();

        // 24[H2O + NaOH] + (TiO2)2Al16H10O29 -> [24H2O + 24NaOH + (TiO2)2Al16H10O29 + ?]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(30)
                .fluidInputs(SodiumHydroxideSolution.getFluid(24000))
                .input(dust, Bauxite, 39)
                .fluidOutputs(SodiumHydroxideBauxite.getFluid(24000))
                .buildAndRegister();

        // [24H2O + 24NaOH + (TiO2)2Al16H10O29 + ?] = [4TiO2 + 16Al(OH)3 + 24NaOH + 5 H2O] - Increase to 4 TiO2 to make process worth doing
        FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(30)
                .circuitMeta(0)
                .fluidInputs(SodiumHydroxideBauxite.getFluid(1000))
                .fluidOutputs(ImpureAluminiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // [4TiO2 + 16Al(OH)3 + 24NaOH + 5 H2O] + 9 H2O = 8 Red Mud [Contains Total: 4TiO2 + 24NaOH + 6 H2O] + 8 [2 Al(OH)3 + H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(120)
                .fluidInputs(Water.getFluid(9000))
                .fluidInputs(ImpureAluminiumHydroxideSolution.getFluid(24000))
                .fluidOutputs(RedMud.getFluid(8000))
                .fluidOutputs(PureAluminiumHydroxideSolution.getFluid(16000))
                .buildAndRegister();

        // [2 Al(OH)3 + H2O] = 2 Al(OH)3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(AluminiumHydroxide.getItemStack(14))
                .buildAndRegister();

        // [2 Al(OH)3 + H2O] = 2 Al(OH)3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(240)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(4000))
                .notConsumable(AluminiumHydroxide.getItemStack())
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(AluminiumHydroxide.getItemStack(56))
                .buildAndRegister();

        // 8 Red Mud [Contains Total: 4TiO2 + 24 NaOH + 6 H2O] + 36 HCl = 8 Neutralized Mud [Contains Total: 4TiO2 + 24NaCl + 30 H2O + 12HCl]
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(RedMud.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4500))
                .fluidOutputs(NeutralisedRedMud.getFluid(2000))
                .buildAndRegister();

        // 8 Neutralized Mud [Contains Total: 4TiO2 + 24NaCl + 30 H2O + 12 HCl] = 4 Red Slurry [Contains Total: 4TiO2] + 4 Ferric REE Chloride [Contains Total: 12 HCl + 6 H2O] + 24 [NaCl + H2O]
        CENTRIFUGE_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(NeutralisedRedMud.getFluid(2000))
                .fluidOutputs(RedSlurry.getFluid(1000))
                .fluidOutputs(FerricREEChloride.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(6000))
                .buildAndRegister();

        // 4 Ferric REE Chloride [Contains Total: 12 HCl + 6 H2O] = 2 Rare Earth Chlorides [Contains Total: REECl3 + 3 H2O] + 2 Iron III Chloride [Contains Total: FeCl3] + 6 H2O
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(FerricREEChloride.getFluid(2000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .fluidOutputs(IronChloride.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 4 Red Slurry [Contains Total: 4TiO2] + 4 H2SO4 = 4 TiO(SO4) + 4H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120)
                .fluidInputs(RedSlurry.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidOutputs(TitanylSulfate.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // TiO(SO4) + 4HCl = TiCl4 + H2SO4 + H2O (water voided)
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(960)
                .fluidInputs(TitanylSulfate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(TitaniumTetrachloride.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(2100))
                .buildAndRegister();
    }
}
