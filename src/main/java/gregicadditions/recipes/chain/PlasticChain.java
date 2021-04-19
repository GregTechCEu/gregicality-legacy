package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class PlasticChain {

    public static void init() {
        polybenzimidazoleInit();
    }

    public static void polybenzimidazoleInit() {

        // C12H14N4 + C20H14O4 -> 2C6H5OH + C20H12N4 + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Diaminobenzidine.getFluid(1000))
                .fluidInputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(Phenol.getFluid(2000))
                .fluidOutputs(Polybenzimidazole.getFluid(1008))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        // 2C6H5OH + C8H6O4 -> C20H14O4 + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Phenol.getFluid(2000))
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .fluidOutputs(Diphenylisophtalate.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(7500)
                .duration(1000)
                .buildAndRegister();

        // C7H8 + CH3OH -> C8H10 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(OrthoXylene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(120)
                .duration(4000)
                .buildAndRegister();

        // 6O + C8H10 -> 2H2O + C8H6O4
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dustTiny, Potassiumdichromate)
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidInputs(OrthoXylene.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(PhthalicAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // 2NH3 + C12H10Cl2N2 -> C12H14N4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Zinc)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Dichlorobenzidine.getFluid(1000))
                .fluidOutputs(Diaminobenzidine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(7500)
                .duration(100)
                .buildAndRegister();

        // 2C6H4ClNO2 + 2H -> C12H10Cl2N2 + 4O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dustTiny, Copper)
                .fluidInputs(Nitrochlorobenzene.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Dichlorobenzidine.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // Cr + 3O -> CrO3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Chrome)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, ChromiumTrioxide, 4)
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        // 2CrO3 + 2KNO3 -> K2Cr2O7 + 2NO2 + O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ChromiumTrioxide, 8)
                .input(dust, Saltpeter, 10)
                .output(dust, Potassiumdichromate, 11)
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // H2SO4 + HNO3 + C6H5Cl -> C6H4ClNO2 + dil.sulfuric
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(Nitrochlorobenzene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // C6H6 + 2Cl -> C6H5Cl + HCl
        CHEMICAL_RECIPES.recipeBuilder()
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
