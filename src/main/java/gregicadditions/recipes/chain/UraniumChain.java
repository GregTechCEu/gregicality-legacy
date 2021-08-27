package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class UraniumChain {
    public static void init() {

        // Pitchblende Processing was removed to save 2 steps
        // and was simplified to just electrolysis into Uraninite.

        // UO2 + HCl + HClO -> [UO2Cl2 + H2O + ?]
        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Uraninite, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidOutputs(UranylChlorideSolution.getFluid(1000))
                .buildAndRegister();

        // [UO2Cl2 + H2O + ?] + 2HNO3 -> [UO2(NO3)2 + H2O + ?] + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(UranylChlorideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(UranylNitrateSolution.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // [UO2(NO3)2 + H2O + ?] + H2SO4 -> [UO2(NO3)2 + H2O] + [? + SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(120)
                .fluidInputs(UranylNitrateSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(PurifiedUranylNitrate.getFluid(1000))
                .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000))
                .buildAndRegister();

        // [? + SO4] -> Pb + 0.111Ra + 0.111Sr + H2SO4
        // Lead is okay here because of uranium decay
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(UraniumSulfateWasteSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .output(dust, Lead)
                .output(dustTiny, Radium)
                .output(dustTiny, Strontium)
                .output(dustTiny, Barium)
                .buildAndRegister();

        // 2[UO2(NO3)2 + H2O] + 4H2O + 8C -> (NH4)2U2O7 + 2HNO3 + 8CO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .input(dust, Carbon, 8)
                .fluidInputs(PurifiedUranylNitrate.getFluid(2000))
                .fluidInputs(Water.getFluid(4000))
                .fluidOutputs(UraniumDiuranate.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(CarbonMonoxide.getFluid(8000))
                .buildAndRegister();

        // CO2 + 2KOH -> K2CO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(30)
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, PotassiumCarbonate, 6)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // (NH4)2U2O7 + 2K2CO3 -> 2UO2(CO3) + 2K2O + 2NH3 + H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120)
                .fluidInputs(UraniumDiuranate.getFluid(1000))
                .input(dust, PotassiumCarbonate, 12)
                .output(dust, PotassiumUranylTricarbonate, 5)
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, Potash, 6)
                .buildAndRegister();

        // UO2(CO3) + [H2O2 + H2SO4] -> [UO3•H2O2 + ThO2] + [? + C + H2SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .input(dust, PotassiumUranylTricarbonate, 5)
                .fluidInputs(PiranhaSolution.getFluid(2000))
                .fluidOutputs(UraniumRefinementWasteSolution.getFluid(1000))
                .output(dust, UraniumPeroxideThoriumOxide, 8)
                .buildAndRegister();

        // [? + C + H2SO4] -> H2SO4 + CsOH + MoO3 + V2O5 (carbon voided, multiplied by 20 for game balance)
        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(1920)
                .fluidInputs(UraniumRefinementWasteSolution.getFluid(20000))
                .fluidOutputs(SulfuricAcid.getFluid(20000))
                .output(dust, CaesiumHydroxide, 3)
                .output(dust, MolybdenumTrioxide, 4)
                .output(dust, VanadiumOxide, 7)
                .buildAndRegister();

        // [UO3•H2O2 + ThO2] -> [UO2 + ThO2] + H2O + 2O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, UraniumPeroxideThoriumOxide, 8)
                .output(dust, UraniumThoriumOxide, 6)
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // [UO2 + ThO2] + H2SO4 + S -> [UO2SO4 + ThO2] + H2S
        CHEMICAL_RECIPES.recipeBuilder().duration(110).EUt(120)
                .input(dust, UraniumThoriumOxide, 6)
                .input(dust, Sulfur)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, UranylThoriumSulfate, 11)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .buildAndRegister();

        // [UO2SO4 + ThO2] + 6HNO3 -> [UO2(NO3)2 + Th(NO3)4] + 2H2O + H2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, UranylThoriumSulfate, 11)
                .fluidInputs(NitricAcid.getFluid(6000))
                .output(dust, UranylThoriumNitrate, 26)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // [UO2(NO3)2 + Th(NO3)4] + 2H -> [UO2 + Th(NO3)4] + 2HNO3
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(500)
                .input(dust, UranylThoriumNitrate, 26)
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, UraniumOxideThoriumNitrate, 18)
                .fluidOutputs(NitricAcid.getFluid(2000))
                .buildAndRegister();

        // [UO2 + Th(NO3)4] + H2O -> UO2 + [H2O + Th(NO3)4]
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, UraniumOxideThoriumNitrate, 18)
                .fluidInputs(DistilledWater.getFluid(1000))
//                .output(dioxide, Uranium238, 3) //todo nuclear rework
                .fluidOutputs(ThoriumNitrateSolution.getFluid(1000))
                .buildAndRegister();

        // [H2O + Th(NO3)4] + 2Na -> ThO + 2NaNO3 + 2HNO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Sodium, 2)
                .fluidInputs(ThoriumNitrateSolution.getFluid(1000))
//                .output(oxide, Thorium, 2)
                .output(dust, SodiumNitrate, 10)
                .fluidOutputs(NitricAcid.getFluid(2000))
                .buildAndRegister();

        // NaNO3 + H2O -> NaOH + HNO3
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, SodiumNitrate, 5)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .fluidOutputs(NitricAcid.getFluid(1000))
                .buildAndRegister();

        // UO2 + C -> U + CO2
        BLAST_RECIPES.recipeBuilder().duration(150).EUt(120).blastFurnaceTemp(1000)
//                .input(dioxide, Uranium238) //todo nuclear rework
                .input(dust, Carbon)
                .output(dust, Uranium238)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // ThO + CaCl2 -> Th + CaO + 2Cl
        BLAST_RECIPES.recipeBuilder().duration(150).EUt(120).blastFurnaceTemp(1000)
//                .input(oxide, Thorium, 2) //todo nuclear rework
                .input(dust, CalciumChloride, 3)
                .output(dust, Thorium)
                .output(dust, Quicklime, 2)
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();
    }
}
