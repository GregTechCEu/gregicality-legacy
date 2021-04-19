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
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(1000)
                .inputs(AluminiumHydroxide.getItemStack(14))
                .outputs(Alumina.getItemStack(5))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 6NaOH + Al2O3 + 12HF -> 2Na3AlF6 + 9H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumHydroxide, 18)
                .inputs(Alumina.getItemStack(5))
                .fluidInputs(HydrofluoricAcid.getFluid(12000))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(2000))
                .fluidOutputs(Water.getFluid(9000))
                .buildAndRegister();

        // 2Al2O3 + Na3AlF6 -> 4Al + 3NaF + AlF3 + 6O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(125)
                .inputs(Alumina.getItemStack(10))
                .fluidInputs(SodiumHexafluoroaluminate.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .output(dust, Aluminium, 4)
                .output(dust, SodiumFluoride, 6)
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // 3NaF + AlF3 -> Na3AlF6
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumFluoride, 6)
                .inputs(AluminiumTrifluoride.getItemStack(4))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .buildAndRegister();

        // 24[H2O + NaOH] + (TiO2)2Al16H10O11 -> [24H2O + 24NaOH + (TiO2)2Al16H10O11 + ?]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(32)
                .fluidInputs(SodiumHydroxideSolution.getFluid(24000))
                .input(dust, Bauxite, 39)
                .fluidOutputs(SodiumHydroxideBauxite.getFluid(24000))
                .buildAndRegister();

        // [24H2O + 24NaOH + (TiO2)2Al16H10O11 + ?] = [4TiO2 + 16Al(OH)3 + 12H + 11H2O + 24Na] - Increase to 4 TiO2 to make process worth doing
        FLUID_HEATER_RECIPES.recipeBuilder().duration(230).EUt(125)
                .circuitMeta(0)
                .fluidInputs(SodiumHydroxideBauxite.getFluid(1000))
                .fluidOutputs(ImpureAluminiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // [4TiO2 + 16Al(OH)3 + 12H + 11H2O + 24Na] + 5H2O = 8 Red Mud [Contains Total: 4TiO2 + 24Na + 12H] + 16[Al(OH)3 + H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .fluidInputs(Water.getFluid(5000))
                .fluidInputs(ImpureAluminiumHydroxideSolution.getFluid(24000))
                .fluidOutputs(RedMud.getFluid(8000))
                .fluidOutputs(PureAluminiumHydroxideSolution.getFluid(16000))
                .buildAndRegister();

        // Done only once
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(AluminiumHydroxide.getItemStack(7))
                .buildAndRegister();

        // [Al(OH)3 + H2O] = Al(OH)3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(8000))
                .notConsumable(AluminiumHydroxide.getItemStack())
                .outputs(AluminiumHydroxide.getItemStack(56))
                .buildAndRegister();

        // 8 Red Mud [Contains Total: 4TiO2 + 24Na + 12H] + 8HCl = 8 Neutralized Mud [Contains Total: 4TiO2 + 24Na + 12H + 8HCl] --- This is supposed to be 1B output
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(1000)
                .fluidInputs(RedMud.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(NeutralisedRedMud.getFluid(1000))
                .buildAndRegister();

        // 8 Neutralized Mud [Contains Total: 4TiO2 + 24Na + 12H + 8HCl] = 4 Red Slurry [Contains Total: 4TiO2] + 4 Ferric REE Chloride [Contains Total: 12H + 8HCl] + 24 Na
        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(840)
                .fluidInputs(NeutralisedRedMud.getFluid(2000))
                .fluidOutputs(RedSlurry.getFluid(1000))
                .fluidOutputs(FerricREEChloride.getFluid(1000))
                .output(dust, Sodium, 6)
                .buildAndRegister();

        // 4 Ferric REE Chloride [Contains Total: 12H + 8HCl] = 2 Rare Earth Chlorides [Contains Total: 2Cl] + 2 Iron III Chloride [Contains Total: 6Cl] + 20H (total, increased to 22 total)
        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(950)
                .fluidInputs(FerricREEChloride.getFluid(2000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .fluidOutputs(IronChloride.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(11000))
                .buildAndRegister();

        // 4 Red Slurry [Contains Total: 4TiO2] + 8H2SO4 = 4Ti(SO4)2 + 8H2O (decreased to 5H2O total for in/out balancing)
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(1250)
                .fluidInputs(RedSlurry.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .fluidOutputs(TitaniumDisulfate.getFluid(2000))
                .fluidOutputs(Water.getFluid(2500))
                .buildAndRegister();

        // Ti(SO4)2 + 4HCl = TiCl4 + 2H2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(950)
                .fluidInputs(TitaniumDisulfate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(TitaniumTetrachloride.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(2000))
                .buildAndRegister();
    }
}
