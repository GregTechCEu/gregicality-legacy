package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class TungstenChain {

    public static void init() {
        //Tungsten process
        if (GAConfig.Misc.tungstenProcess) {
            MIXER_RECIPES.recipeBuilder()
                    .input(dust, Scheelite, 7)
                    .input(dust, SodiumHydroxide, 4)
                    .fluidInputs(Water.getFluid(4000))
                    .outputs(OreDictUnifier.get(dust, Calcium, 2))
                    .fluidOutputs(SodiumTungstate.getFluid(13000))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            MIXER_RECIPES.recipeBuilder()
                    .input(dust, Tungstate, 7)
                    .input(dust, SodiumHydroxide, 4)
                    .fluidInputs(Water.getFluid(4000))
                    .outputs(OreDictUnifier.get(dust, Lithium, 2))
                    .fluidOutputs(SodiumTungstate.getFluid(13000))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, CalciumChloride, 3)
                    .fluidInputs(SodiumTungstate.getFluid(4000))
                    .outputs(OreDictUnifier.get(dust, CalciumTungstate, 3))
                    .fluidOutputs(SaltWater.getFluid(4000))
                    .EUt(480)
                    .duration(100)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, CalciumTungstate, 6)
                    .fluidInputs(Water.getFluid(2000))
                    .fluidInputs(HydrochloricAcid.getFluid(1000))
                    .outputs(OreDictUnifier.get(dust, TungsticAcid, 7))
                    .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                    .EUt(960)
                    .duration(150)
                    .buildAndRegister();

            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                    .input(dust, TungsticAcid, 7)
                    .outputs(OreDictUnifier.get(dust, TungstenTrioxide, 4))
                    .fluidOutputs(Water.getFluid(2000))
                    .EUt(120)
                    .duration(150)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, TungstenTrioxide, 4)
                    .fluidInputs(Hydrogen.getFluid(6000))
                    .outputs(OreDictUnifier.get(dust, Tungsten))
                    .fluidOutputs(Water.getFluid(9000))
                    .EUt(1920)
                    .duration(75)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, Tungsten)
                    .fluidInputs(Chlorine.getFluid(6000))
                    .outputs(OreDictUnifier.get(dust, TungstenHexachloride, 7))
                    .duration(150)
                    .EUt(480)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, TungstenHexachloride, 7)
                    .fluidInputs(Methane.getFluid(1000))
                    .fluidInputs(Hydrogen.getFluid(6000))
                    .outputs(OreDictUnifier.get(dust, TungstenCarbide))
                    .fluidOutputs(DilutedHydrochloricAcid.getFluid(13000))
                    .EUt(480)
                    .duration(150)
                    .buildAndRegister();
        }

    }
}
