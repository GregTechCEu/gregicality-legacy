package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class AluminiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(1000)
                .inputs(AluminiumHydroxide.getItemStack(2))
                .outputs(Alumina.getItemStack())
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumHydroxide, 3)
                .inputs(Alumina.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .fluidOutputs(Water.getFluid(4500))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(125)
                .inputs(Alumina.getItemStack())
                .fluidInputs(SodiumHexafluoroaluminate.getFluid(1000))
                .fluidOutputs(Aluminium.getFluid(288))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 3))
                .outputs(AluminiumTrifluoride.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumFluoride, 3)
                .inputs(AluminiumTrifluoride.getItemStack())
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(32)
                .fluidInputs(Water.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .input(dust, Bauxite, 2)
                .fluidOutputs(SodiumHydroxideBauxite.getFluid(4000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(230).EUt(125)
                .circuitMeta(0)
                .fluidInputs(SodiumHydroxideBauxite.getFluid(1000))
                .fluidOutputs(ImpureAluminiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(230).EUt(125)
                .fluidInputs(ImpureAluminiumHydroxideSolution.getFluid(1500))
                .fluidOutputs(RedMud.getFluid(500))
                .fluidOutputs(PureAluminiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(AluminiumHydroxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(PureAluminiumHydroxideSolution.getFluid(9000))
                .inputs(AluminiumHydroxide.getItemStack())
                .outputs(AluminiumHydroxide.getItemStack(10))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(1000)
                .fluidInputs(RedMud.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(NeutralisedRedMud.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(840)
                .fluidInputs(NeutralisedRedMud.getFluid(2000))
                .fluidOutputs(RedSlurry.getFluid(1000))
                .fluidOutputs(FerricREEChloride.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(950)
                .fluidInputs(FerricREEChloride.getFluid(2000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .fluidOutputs(IronChloride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(1250)
                .fluidInputs(RedSlurry.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(TitaniumSulfate.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SiliconDioxide))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(950)
                .fluidInputs(TitaniumSulfate.getFluid(1000))
                .fluidOutputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .buildAndRegister();
    }
}
