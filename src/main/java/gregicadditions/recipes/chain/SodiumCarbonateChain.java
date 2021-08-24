package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.CalciumChloride;
import static gregicadditions.GAMaterials.SodiumCarbonateSolution;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class SodiumCarbonateChain {

    public static void init() {

        // CaCO3 + 2NaCl -> Na2CO3 + CaCl2
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(500)
                .input(dust, Calcite, 5)
                .input(dust, Salt, 4)
                .output(dust, SodaAsh, 6)
                .output(dust, CalciumChloride, 3)
                .buildAndRegister();

        // Na2CO3 + H2O -> Na2CO3(H2O)
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, SodaAsh, 6)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // 2NaOH + CO2 -> Na2CO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, SodaAsh, 6)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
    }
}
