package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class RheniumChain {
    public static void init() {
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(8000))
                .fluidInputs(Ammonia.getFluid(8000))
                .fluidInputs(Oxygen.getFluid(24000))
                .fluidOutputs(HydrogenCyanide.getFluid(8000))
                .fluidOutputs(Water.getFluid(24000))
                .EUt(7680)
                .duration(400)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(8000))
                .input(dust, Sodium, 8)
                .fluidOutputs(SodiumCyanide.getFluid(16000))
                .EUt(491520)
                .duration(50)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Molybdenite)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidOutputs(SodiumHydroxide.getFluid(250))
                .fluidOutputs(GoldCyanide.getFluid(250))
                .outputs(GoldDepleteMolybdenite.getItemStack())
                .EUt(122880)
                .duration(100)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(GoldCyanide.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Gold))
                .fluidOutputs(SodiumCyanide.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(GoldDepleteMolybdenite.getItemStack())
                .fluidInputs(IronChloride.getFluid(1000))
                .outputs(MolybdenumConcentrate.getItemStack())
                .fluidOutputs(ChlorideLeachedSolution.getFluid(1000))
                .EUt(491520)
                .duration(10)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ChlorideLeachedSolution.getFluid(5000))
                .outputs(OreDictUnifier.get(dust, CalciumChloride))
                .outputs(CopperChloride.getItemStack())
                .outputs(LeadChloride.getItemStack())
                .outputs(BismuthChloride.getItemStack())
                .fluidOutputs(IronChloride.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(CopperChloride.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Copper))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(LeadChloride.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Lead))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(BismuthChloride.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Bismuth))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(MolybdenumConcentrate.getItemStack(2))
                .input(dust, Carbon)
                .blastFurnaceTemp(9000)
                .EUt(491520)
                .duration(50)
                .outputs(OreDictUnifier.get(dust, Ash))
                .fluidOutputs(MolybdenumFlue.getFluid(1000))
                .outputs(MolybdenumTrioxide.getItemStack())
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(1000))
                .inputs(MolybdenumTrioxide.getItemStack(1))
                .outputs(OreDictUnifier.get(dust, Molybdenum))
                .fluidOutputs(Water.getFluid(1000))
                .blastFurnaceTemp(1000)
                .EUt(480)
                .duration(200)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(MolybdenumFlue.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(RheniumSulfuricSolution.getFluid(2000))
                .EUt(491520)
                .duration(30)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RheniumSulfuricSolution.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumSulfate.getFluid(1000))
                .fluidOutputs(AmmoniumPerrhenate.getFluid(1000))
                .EUt(491520)
                .duration(25)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Rhenium))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(491520)
                .duration(20)
                .buildAndRegister();

    }
}
