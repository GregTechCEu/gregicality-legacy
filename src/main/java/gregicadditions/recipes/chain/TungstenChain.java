package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class TungstenChain {

    public static void init() {

        //Tungsten process
        if (GAConfig.Misc.tungstenProcess) {

            // These first two should output 1B of Na2WO4, but I am outputting 4
            // to make the process have a similar balance to pre-chem rework.

            // WCa2O4 + 2NaOH + H2O2 -> 2Ca(OH)2 + Na2WO4
            MIXER_RECIPES.recipeBuilder()
                    .input(dust, Scheelite, 7)
                    .input(dust, SodiumHydroxide, 6)
                    .fluidInputs(HydrogenPeroxide.getFluid(1000))
                    .outputs(CalciumHydroxide.getItemStack(10))
                    .fluidOutputs(SodiumTungstate.getFluid(4000))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            // WLi2O4 + 2NaOH -> 2LiOH + Na2WO4
            MIXER_RECIPES.recipeBuilder()
                    .input(dust, Tungstate, 7)
                    .input(dust, SodiumHydroxide, 6)
                    .outputs(LithiumHydroxide.getItemStack(6))
                    .fluidOutputs(SodiumTungstate.getFluid(4000))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            // LiOH(H2O) -> LiOH (water lost)
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                    .fluidInputs(LithiumHydroxideSolution.getFluid(1000))
                    .outputs(LithiumHydroxide.getItemStack(3))
                    .EUt(30)
                    .duration(60)
                    .buildAndRegister();

            // LiOH + H2O -> LiOH(H2O)
            MIXER_RECIPES.recipeBuilder()
                    .fluidInputs(Water.getFluid(1000))
                    .inputs(LithiumHydroxide.getItemStack(3))
                    .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                    .EUt(30)
                    .duration(60)
                    .buildAndRegister();

            // CaCl2 + Na2WO4 + 2H2O -> WCaO4 + 2NaCl(H2O)
            // CaCl2 + Na0.5WO4 + 0.5H2O -> WCaO4 + 2Cl + (0.5Na + 0.5H2O)
            // This recipe is weird, since we are trying to keep a good balance with the chain.
            // The Na2WO4 is treated more like Na0.5WO
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, CalciumChloride, 3)
                    .fluidInputs(SodiumTungstate.getFluid(1000))
                    .fluidInputs(Water.getFluid(500))
                    .output(dust, CalciumTungstate, 6)
                    .fluidOutputs(SaltWater.getFluid(500))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            // WCaO4 + 2H2O -> H2WO4 + Ca(OH)2
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, CalciumTungstate, 6)
                    .fluidInputs(Water.getFluid(2000))
                    .output(dust, TungsticAcid, 7)
                    .outputs(CalciumHydroxide.getItemStack(5))
                    .EUt(960)
                    .duration(150)
                    .buildAndRegister();

            // H2WO4 -> WO3 + H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                    .input(dust, TungsticAcid, 7)
                    .output(dust, TungstenTrioxide, 4)
                    .fluidOutputs(Water.getFluid(1000))
                    .EUt(120)
                    .duration(150)
                    .buildAndRegister();

            // WO3 + 6H -> W + 3H2O
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, TungstenTrioxide, 4)
                    .fluidInputs(Hydrogen.getFluid(6000))
                    .output(dust, Tungsten)
                    .fluidOutputs(Water.getFluid(3000))
                    .EUt(1920)
                    .duration(75)
                    .buildAndRegister();

            // W + 6Cl -> WCl6
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, Tungsten)
                    .fluidInputs(Chlorine.getFluid(6000))
                    .output(dust, TungstenHexachloride, 7)
                    .duration(150)
                    .EUt(480)
                    .buildAndRegister();

            // WCl6 + CH4 + 2H -> WC + 6HCl
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, TungstenHexachloride, 7)
                    .fluidInputs(Methane.getFluid(1000))
                    .fluidInputs(Hydrogen.getFluid(2000))
                    .output(dust, TungstenCarbide, 2)
                    .fluidOutputs(HydrochloricAcid.getFluid(6000))
                    .EUt(480)
                    .duration(150)
                    .buildAndRegister();
        }
    }
}
