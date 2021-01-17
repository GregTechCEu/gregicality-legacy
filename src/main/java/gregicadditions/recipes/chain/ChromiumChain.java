package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class ChromiumChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, SodaAsh)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(500)
                .input(dust, Calcite)
                .input(dust, Salt)
                .fluidInputs(Ammonia.getFluid(5))
                .outputs(OreDictUnifier.get(dust, SodaAsh))
                .outputs(OreDictUnifier.get(dust, CalciumChloride))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(125)
                .input(dust, Chromite)
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, BandedIron))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SodiumChromateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(125)
                .fluidInputs(SodiumChromateSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SodiumDichromateSolution.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(125).blastFurnaceTemp(1200)
                .input(dust, Carbon)
                .fluidInputs(SodiumDichromateSolution.getFluid(1000))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodaAsh))
                .outputs(ChromiumOxide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(125).blastFurnaceTemp(1200)
                .inputs(ChromiumOxide.getItemStack())
                .input(dust, Aluminium)
                .outputs(OreDictUnifier.get(dust, Chrome))
                .outputs(Alumina.getItemStack())
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(125)
                .fluidInputs(SodiumSulfateSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate))
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, Ruby)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(RubySlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(RubySlurry.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Aluminium))
                .outputs(OreDictUnifier.get(dust, Chrome))
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, Sapphire)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(SapphireSlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(SapphireSlurry.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Aluminium, 2))
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2000)
                .input(dust, GreenSapphire)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(GreenSapphireSlurry.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .fluidInputs(GreenSapphireSlurry.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Aluminium, 2))
                .chancedOutput(OreDictUnifier.get(dustTiny, Beryllium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

    }
}
