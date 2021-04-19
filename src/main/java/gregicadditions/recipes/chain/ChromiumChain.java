package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class ChromiumChain {
    public static void init() {

        // Na2CO3 + H2O -> Na2CO3(H2O)
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, SodaAsh, 6)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // CaCO3 + 2NaCl -> Na2CO3 + CaCl2
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(500)
                .input(dust, Calcite, 5)
                .input(dust, Salt, 4)
                .output(dust, SodaAsh, 6)
                .output(dust, CalciumChloride, 3)
                .buildAndRegister();

        // 2Cr2FeO4 + 4Na2CO3(H2O) + 7O -> Fe2O3 + 4CO2 + 4Na2CrO4(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(125)
                .input(dust, Chromite, 14)
                .fluidInputs(SodiumCarbonateSolution.getFluid(4000))
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust, BandedIron, 5)
                .fluidOutputs(CarbonDioxide.getFluid(4000))
                .fluidOutputs(SodiumChromateSolution.getFluid(4000))
                .buildAndRegister();

        // 2Na2CrO4(H2O) + H2SO4 -> Na2Cr2O7 + Na2SO4(H2O) + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(125)
                .fluidInputs(SodiumChromateSolution.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SodiumDichromateSolution.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // CO2 + Na2Cr2O7 -> Na2CO3 + 2CrO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(125)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(SodiumDichromateSolution.getFluid(1000))
                .output(dust, SodaAsh, 6)
                .output(dust, ChromiumTrioxide, 8)
                .buildAndRegister();

        // CrO3 + 2Al -> Cr + Al2O3
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(125).blastFurnaceTemp(1200)
                .input(dust, ChromiumTrioxide, 4)
                .input(dust, Aluminium, 2)
                .output(dust, Chrome)
                .outputs(Alumina.getItemStack(5))
                .buildAndRegister();

        // Na2SO4(H2O) -> Na2SO4
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(125)
                .fluidInputs(SodiumSulfateSolution.getFluid(1000))
                .output(dust, SodiumSulfate, 7)
                .buildAndRegister();

        // Al2O3Cr + (HNO3 + HCl) -> Al2O3CrCl? + (H2NO3)
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, Ruby, 6)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(RubySlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        // Al2O3CrCl? -> 2Al + Cr + Cl + 3O + ? + ? + ?
        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(RubySlurry.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .output(dust, Aluminium, 2)
                .output(dust, Chrome)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        // Al2O3 + (HNO3 + HCl) -> Al2O3Cl? + (H2NO3)
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, Sapphire, 5)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(SapphireSlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        // Al2O3Cl? -> 2Al + + Cl + 3O + ? + ? + ?
        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(SapphireSlurry.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .output(dust, Aluminium, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        // Al2O3 + (HNO3 + HCl) -> Al2O3Cl? + (H2NO3)
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, GreenSapphire, 5)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(GreenSapphireSlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        // Al2O3Cl? -> 2Al + + Cl + 3O + ? + ? + ?
        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(GreenSapphireSlurry.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .output(dust, Aluminium, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, Beryllium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();
    }
}
