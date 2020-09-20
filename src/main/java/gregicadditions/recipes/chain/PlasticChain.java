package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAMaterials.Chlorobenzene;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class PlasticChain {

    public static void init() {
        polybenzimidazoleInit();
    }

    public static void polybenzimidazoleInit() {
        //platics Polybenzimidazole
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Diaminobenzidine.getFluid(1000))
                .fluidInputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(Phenol.getFluid(1000))
                .fluidOutputs(Polybenzimidazole.getFluid(1000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Phenol.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .fluidOutputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .EUt(7500)
                .duration(1000)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(2000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(Dimethylbenzene.getFluid(3000))
                .EUt(120)
                .duration(4000)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dustTiny, Potassiumdichromate)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidInputs(Dimethylbenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(PhthalicAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Zinc)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Dichlorobenzidine.getFluid(1000))
                .fluidOutputs(Diaminobenzidine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dustTiny, Copper)
                .fluidInputs(Nitrochlorobenzene.getFluid(1000))
                .fluidOutputs(Dichlorobenzidine.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, ZincSulfate, 6)
                .outputs(OreDictUnifier.get(dust, Zinc))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidInputs(Oxygen.getFluid(4000))
                .EUt(90)
                .duration(26)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Chrome)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, ChromiumTrioxide, 1))
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ChromiumTrioxide, 2)
                .input(dust, PotassiumNitrade, 2)
                .outputs(OreDictUnifier.get(dust, Potassiumdichromate))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium)
                .fluidInputs(NitricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PotassiumNitrade))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(Nitrochlorobenzene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(30)
                .duration(240)
                .buildAndRegister();

    }
}
