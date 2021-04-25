package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static gregicadditions.GAEnums.GAOrePrefix.oxide;
import static gregicadditions.GAEnums.GAOrePrefix.dioxide;

public class UraniumChain {
    public static void init() {

        // Uraninite and Pitchblende MUST NOT be creatable from each's respective components

        // (UO2)3ThPb + BaCO3 -> [(UO2)3ThPb + BaCO3]
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(500)
                .input(dust, Pitchblende, 5)
                .inputs(BariumCarbonate.getItemStack(5))
                .outputs(PitchblendeBaCOmix.getItemStack(10))
                .buildAndRegister();

        // [(UO2)3ThPb + BaCO3] + 3HCl + 3HClO -> 3[UO2Cl2 + H2O + ?] + CO2 + BaO
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(PitchblendeBaCOmix.getItemStack(10))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidInputs(HypochlorousAcid.getFluid(3000))
                .outputs(BariumOxide.getItemStack(2))
                .fluidOutputs(UranylChlorideSolution.getFluid(3000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // BaO -> Ba + O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(220).EUt(120)
                .inputs(BariumOxide.getItemStack(2))
                .output(dust, Barium)
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // UO2 + HCl + HClO -> [UO2Cl2 + H2O + ?]
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
                .input(dust, Uraninite, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidOutputs(UranylChlorideSolution.getFluid(1000))
                .buildAndRegister();

        // [UO2Cl2 + H2O + ?] + 2HNO3 -> [UO2(NO3)2 + H2O + ?] + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(500)
                .fluidInputs(UranylChlorideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(UranylNitrateSolution.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // [UO2(NO3)2 + H2O + ?] + H2SO4 -> [UO2(NO3)2 + H2O] + [? + SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(UranylNitrateSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(PurifiedUranylNitrate.getFluid(1000))
                .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000))
                .buildAndRegister();

        // [? + SO4] -> Pb + 0.111Ra + 0.111Sr + H2SO4
        // Lead is okay here because of uranium decay
        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(UraniumSulfateWasteSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .output(dust, Lead)
                .output(dustTiny, Radium)
                .output(dustTiny, Strontium)
                .output(dustTiny, Barium)
                .buildAndRegister();

        // 2[UO2(NO3)2 + H2O] + 4H2O + 8C -> (NH4)2U2O7 + 2HNO3 + 8CO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(500)
                .input(dust, Carbon, 8)
                .fluidInputs(PurifiedUranylNitrate.getFluid(2000))
                .fluidInputs(Water.getFluid(4000))
                .fluidOutputs(UraniumDiuranate.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(CarbonMonoxde.getFluid(8000))
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(UraniumDiuranate.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(HotUraniumDiuranate.getFluid(1000))
                .buildAndRegister();

        // CO2 + 2KOH -> K2CO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .outputs(PotassiumCarbonate.getItemStack(6))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // (NH4)2U2O7 + 2K2CO3 -> 2UO2(CO3) + 2K2O + 2NH3 + H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(HotUraniumDiuranate.getFluid(1000))
                .inputs(PotassiumCarbonate.getItemStack(12))
                .fluidOutputs(HotPotassiumUranylTricarbonate.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, Potash, 6)
                .buildAndRegister();

        // UO2(CO3) (hot) -> UO2(CO3)
        VACUUM_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HotPotassiumUranylTricarbonate.getFluid(1000))
                .outputs(PotassiumUranylTricarbonate.getItemStack(5))
                .buildAndRegister();

        // UO2(CO3) + [H2O2 + H2SO4] -> [UO3•H2O2 + ThO2] + [? + C + H2SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1000)
                .inputs(PotassiumUranylTricarbonate.getItemStack(5))
                .fluidInputs(PiranhaSolution.getFluid(2000))
                .fluidOutputs(UraniumRefinementWasteSolution.getFluid(1000))
                .outputs(UraniumPeroxideThoriumOxide.getItemStack(8))
                .buildAndRegister();

        // [? + C + H2SO4] -> H2SO4 + CsOH + MoO3 + V2O5 (carbon voided, multiplied by 20 for game balance)
        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(UraniumRefinementWasteSolution.getFluid(20000))
                .fluidOutputs(SulfuricAcid.getFluid(20000))
                .outputs(CaesiumHydroxide.getItemStack(3))
                .outputs(MolybdenumTrioxide.getItemStack(4))
                .outputs(VanadiumOxide.getItemStack(7))
                .buildAndRegister();

        // KOH -> K + O + H
        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .output(dust, Potassium)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // CsOH -> Cs + O + H
        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .inputs(CaesiumHydroxide.getItemStack(3))
                .output(dust, Caesium)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // [UO3•H2O2 + ThO2] -> [UO2 + ThO2] + H2O + 2O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(500).EUt(500)
                .inputs(UraniumPeroxideThoriumOxide.getItemStack(8))
                .outputs(UraniumThoriumOxide.getItemStack(6))
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // [UO2 + ThO2] + H2SO4 + S -> [UO2SO4 + ThO2] + H2S
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(UraniumThoriumOxide.getItemStack(6))
                .input(dust, Sulfur)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(UranylThoriumSulfate.getItemStack(11))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .buildAndRegister();

        // [UO2SO4 + ThO2] + 6HNO3 -> [UO2(NO3)2 + Th(NO3)4] + 2H2O + H2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(500)
                .inputs(UranylThoriumSulfate.getItemStack(11))
                .fluidInputs(NitricAcid.getFluid(6000))
                .outputs(UranylThoriumNitrate.getItemStack(26))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // [UO2(NO3)2 + Th(NO3)4] + 2H -> [UO2 + Th(NO3)4] + 2HNO3
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).blastFurnaceTemp(500)
                .inputs(UranylThoriumNitrate.getItemStack(26))
                .fluidInputs(Hydrogen.getFluid(2000))
                .outputs(UraniumOxideThoriumNitrate.getItemStack(18))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .buildAndRegister();

        // [UO2 + Th(NO3)4] + H2O -> UO2 + [H2O + Th(NO3)4]
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(500)
                .inputs(UraniumOxideThoriumNitrate.getItemStack(18))
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dioxide, UraniumRadioactive.getMaterial(), 3)
                .fluidOutputs(ThoriumNitrateSolution.getFluid(1000))
                .buildAndRegister();

        // [H2O + Th(NO3)4] + 2Na -> ThO + 2NaNO3 + 2HNO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .input(dust, Sodium, 2)
                .fluidInputs(ThoriumNitrateSolution.getFluid(1000))
                .output(oxide, Thorium, 2)
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
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(500).blastFurnaceTemp(1000)
                .input(dioxide, UraniumRadioactive.getMaterial())
                .input(dust, Carbon)
                .output(dust, UraniumRadioactive.getMaterial())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // ThO + CaCl2 -> Th + CaO + 2Cl
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(500).blastFurnaceTemp(1000)
                .input(oxide, Thorium, 2)
                .input(dust, CalciumChloride, 3)
                .output(dust, ThoriumRadioactive.getMaterial())
                .output(dust, Quicklime, 2)
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();
    }
}
