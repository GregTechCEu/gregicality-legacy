package gregicadditions.recipes.chain;

import gregicadditions.GAMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Dimethylbenzene;
import static gregtech.api.unification.ore.OrePrefix.*;

public class PolymerChain {

    public static void init() {
        polyimideInit();
        fluorinatedEthylenePropyleneInit();
    }

    public static void polyimideInit() {

        // C6H4(CH3)2 + 2CH3Cl -> C6H2(CH3)4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylbenzene.getFluid(1000))
                .fluidInputs(Chloromethane.getFluid(2000))
                .output(dust, Durene, 24)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        // C6H2(CH3)4 + 12O -> C6H2(C2O3)2 + 6H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Durene, 24)
                .fluidInputs(Oxygen.getFluid(12000))
                .output(dust, PyromelliticDianhydride, 18)
                .fluidOutputs(Water.getFluid(6000))
                .EUt(120)
                .duration(150)
                .buildAndRegister();

        // 2C6H5NH2 + C2H5OH -> C12H12N2O + 2CH4
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Tin)
                .notConsumable(HydrochloricAcid.getFluid())
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(Phenol.getFluid(1000))
                .fluidOutputs(Oxydianiline.getFluid(1000))
                .fluidOutputs(Methane.getFluid(2000))
                .EUt(120)
                .duration(150)
                .buildAndRegister();

        // C6H2(C2O3)2 + C12H12N2O -> C22H14N2O7
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PyromelliticDianhydride, 18)
                .fluidInputs(Oxydianiline.getFluid(1000))
                .fluidOutputs(PolyamicAcid.getFluid(1000))
                .EUt(7680)
                .duration(400)
                .buildAndRegister();

        // Lose water
        // This should not gain water output ever, due to
        // the 1B -> 144mb recipe change with the above recipe.
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PolyamicAcid.getFluid(144))
                .fluidOutputs(Polyimide.getFluid(144))
                .EUt(30)
                .duration(270)
                .buildAndRegister();
    }

    public static void fluorinatedEthylenePropyleneInit() {

        // 3C2F4 -> 2C3F6
        PYROLYSE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .notConsumable(stick, Steel)
                .fluidInputs(Tetrafluoroethylene.getFluid(3000))
                .fluidOutputs(Hexafluoropropylene.getFluid(2000))
                .EUt(96)
                .duration(460)
                .buildAndRegister();

        // C2F4 + C3F6 -> C5F10
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Tetrafluoroethylene.getFluid(1000))
                .fluidInputs(Hexafluoropropylene.getFluid(1000))
                .fluidOutputs(FluorinatedEthylenePropylene.getFluid(1000))
                .EUt(1920)
                .duration(125)
                .buildAndRegister();
    }
}
