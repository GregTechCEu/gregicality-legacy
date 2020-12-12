package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class UraniumChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(500)
                .input(dust, Pitchblende)
                .inputs(BariumCarbonate.getItemStack())
                .outputs(PitchblendeBaCOmix.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(PitchblendeBaCOmix.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(UranylChlorideSolution.getFluid(3000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
                .input(dust, Uraninite)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(UranylChlorideSolution.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(500)
                .fluidInputs(UranylChlorideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(UranylNitrateSolution.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(UranylNitrateSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(PurifiedUranylNitrate.getFluid(1000))
                .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(UraniumSulfateWasteSolution.getFluid(5000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(8000))
                .outputs(OreDictUnifier.get(dust, Lead))
                .outputs(OreDictUnifier.get(dustTiny, Radium))
                .outputs(OreDictUnifier.get(dustTiny, Strontium))
                .outputs(OreDictUnifier.get(dustTiny, Barium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(500)
                .fluidInputs(PurifiedUranylNitrate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(UraniumDiuranate.getFluid(1000))
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(UraniumDiuranate.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(HotUraniumDiuranate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .outputs(PotassiumCarbonate.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(HotUraniumDiuranate.getFluid(1000))
                .inputs(PotassiumCarbonate.getItemStack())
                .fluidOutputs(HotPotassiumUranylTricarbonate.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Potash))
                .outputs(OreDictUnifier.get(dust, Iron))
                .fluidOutputs(Ammonia.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HotPotassiumUranylTricarbonate.getFluid(1000))
                .outputs(PotassiumUranylTricarbonate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1000)
                .inputs(PotassiumUranylTricarbonate.getItemStack())
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(UraniumRefinementWasteSolution.getFluid(1000))
                .outputs(UraniumPeroxideThoriumOxide.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(UraniumRefinementWasteSolution.getFluid(20000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(32000))
                .fluidOutputs(Ammonia.getFluid(4000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .outputs(CesiumHydroxide.getItemStack())
                .outputs(MolybdenumTrioxide.getItemStack())
                .outputs(VanadiumOxide.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Potassium))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(500)
                .inputs(CesiumHydroxide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Caesium))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(500).EUt(500)
                .inputs(UraniumPeroxideThoriumOxide.getItemStack())
                .outputs(UraniumThoriumOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(UraniumThoriumOxide.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(UranylThoriumSulfate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(500)
                .inputs(UranylThoriumSulfate.getItemStack())
                .fluidInputs(NitricAcid.getFluid(1000))
                .outputs(UranylThoriumNitrate.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1800))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).blastFurnaceTemp(500)
                .inputs(UranylThoriumNitrate.getItemStack())
                .outputs(UraniumOxideThoriumNitrate.getItemStack())
                .fluidOutputs(NitrogenDioxide.getFluid(700))
                .buildAndRegister();


        MIXER_RECIPES.recipeBuilder().duration(100).EUt(500)
                .inputs(UraniumOxideThoriumNitrate.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .outputs(OreDictUnifier.get(GAEnums.GAOrePrefix.dioxide, UraniumRadioactive.getMaterial()))
                .fluidOutputs(ThoriumNitrateSolution.getFluid(300))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .fluidInputs(ThoriumNitrateSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(GAEnums.GAOrePrefix.oxide, Thorium))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(500).blastFurnaceTemp(1000)
                .input(GAEnums.GAOrePrefix.dioxide, UraniumRadioactive.getMaterial())
                .input(dust, Carbon)
                .outputs(OreDictUnifier.get(dust, UraniumRadioactive.getMaterial()))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(500).blastFurnaceTemp(1000)
                .input(GAEnums.GAOrePrefix.oxide, Thorium)
                .input(dust, Calcium)
                .input(dust, CalciumChloride)
                .outputs(OreDictUnifier.get(dust, ThoriumRadioactive.getMaterial()))
                .outputs(OreDictUnifier.get(dust, Quicklime, 2))
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();
    }
}
