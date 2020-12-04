package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class UraniumChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(500)
            .input(dust, Pitchbende)
            .input(BariumCarbonate.getItemStack())
            .output(PitchblendeBaCOmix.getItemStack())
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
            .input(PitchblendeBaCOmix.getItemStack())
            .fluidInputs(HydrochloricAcid.getFluid(3000))
            .fluidOutputs(UranylChlorideSolution.getFluid(3000))
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
            .input(dust, Uraninite)
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidOutputs(UranylChlorideSolution.getFluid(1000))
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(500)
            .fluidInputs(UranylChlorideSolution.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidOutputs(UranylNitrateSolution.getFluid(1000)
            .fluidOutputs(NitrogenDioxide.getFluid(2000))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
            .fluidInputs(UranylNitrateSolution.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidOutputs(PurifiedUranylNitrate.getFluid(1000))
            .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000))
            .buildAndRegister();
            
        ELECTROLYSER_RECIPES.recipeBuilder().duration(600).EUt(500)
            .fluidInputs(UraniumSulfateWasteSolution.getFluid(5000)
            .fluidOutputs(DiluteSulfuricAcid.getFluid(8000))
            .output(OreDictUnifier(dust, Lead))
            .output(OreDictUnifier(tiny_dust, Radium))
            .output(OreDictUnifier(tiny_dust, Strontium))
            .output(OreDictUnifier(tiny_dust, Barium))
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(500)
            .fluidInputs(PurifiedUranylNitrate.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(UraniumDiuranate.getFluid(1000))
            .buildAndRegister();
            
        CRACKER_UNIT_RECIPES.recipeBuilder().duration(240).EUt(500)
            .fluidInputs(UraniumDiuranate.getFluid(1000))
            .fluidInputs(Steam.getFluid(1000))
            .fluidOutputs(HotUraniumDiuranate.getFluid(1000))
            .buildAndRegister();
        
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
            .fluidInputs(PotassiumHydroxide.getFluid(2000))
            .fluidInputs(CarbonDioxide.getFluid(1000)
            .input(PotassiumCarbonate.getItemStack())
        
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
            .fluidInputs(HotUraniumDiuranate.getFluid(1000))
            .inputs(PotassiumCarbonate.getItemStack())
            .fluidOutput(HotPotassiumUranylTricarbonate.getFluid(1000))
            .output(dust, Potash)
            .output(OreDictUnifier(dust, Iron))
            .fluidOutput(Ammonia.getFluid(1000))
            .buildAndRegister();
            
        VACCUM_RECIPES.recipeBuilder().duration(320).EUt(500)
            .fluidInputs(HotPotassiumUranylTricarbonate.getFluid(1000))
            .output(PotassiumUranylTricarbonate.getItemStack())
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1000)
            .input(PotassiumUranylTricarbonate.getItemStack())
            .fluidInput(HydrogenPeroxide.getFluid(1000))
            .fluidInput(SulfuricAcid.getFluid(1000)))
            .fluidOutput(UraniumRefinementWasteSolution.getFluid(1000))
            .output(UraniumPeroxideThoriumOxide.getItemStack())
            .buildAndRegister();
            
        ELECTROLYSER_RECIPES.recipeBuilder().duration(600).EUt(500)
            .fluidInputs(UraniumRefinementWasteSolution.getFluid(20000)
            .fluidOutputs(DiluteSulfuricAcid.getFluid(32000))
            .fluidOutputs(Ammonia.getFluid(4000))
            .fluidOutputs(PotassiumHydroxide.getFluid(4000))
            .output(CesiumHydroxide.getItemStack())
            .output(MolybdenumTrioxide.getItemStack())
            .output(VandiumOxide.getItemStak())
            .buildAndRegister();
            
        ELECTROLYSER_RECIPES.recipeBuider().duration(600).EUt(500)
            .fluidInputs(PotassiumHydroxide.getFluid(1000))
            .output(OreDictUnifier(dust, Potassium))
            .fluidOutputs(Hydrogen.getFluid(1000))
            .fluidOutputs(Oxygem.getFluid(1000))
            .buildAndRegister();
            
        ELECTROLYSER_RECIPES.recipeBuider().duration(600).EUt(500)
            .fluidInputs(CesiumHydroxide.getFluid(1000))
            .output(OreDictUnifier(dust, Caesium))
            .fluidOutputs(Hydrogen.getFluid(1000))
            .fluidOutputs(Oxygem.getFluid(1000))
            .buildAndRegister();
          
        PYROLYSE_RECIPES.recipeBuiler().duration(500).EUt(500))
            .input(UraniumPeroxideThoriumOxide.getItemStack())
            .output(UraniumThoriumOxide.getItemStack())
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
            .input(UraniumThoriumOxide.getItemStack())
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(UranylThoriumSulfate.getItemStack())
            .buildAndRegister();
            
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(500)
            .input(UranylThoriumSulfate.getItemStack())
            .fluidInputs(NitricAcid.getFluid(1000))
            .output(UranylThoriumNitrate.getItemStack())
            .fluidOutputs(DiluteSulfuricAcid(1800)
            .buildAndRegister();
            
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).blastFurnaceTemp(500)
            .input(UranylThoriumNitrate.getItemStack())
            .output(UraniumOxideThoriumNitrate.getItemStack())
            .fluidOutputs(NitrogenDioxide.getFluid(700))
            .buildAndRegister();
            
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(500)
            .input(UraniumOxideThoriumNitrate.getItemStack())
            .fluidInputs(DistilledWater.getFluid(1000))
            .output(dust, UraniumDioxide)
            .fluidOutputs(ThoriumNitrateSoluiton.getFluid(500))
            .buildAndRegister();
            
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(500).blastFurnaceTemp(800)
            .fluidInputs(ThoriumNitrateSoluiton.getFluid(1000))
            .output(dust, ThoriumDioxide)
            .fluidOutputs(NitrogenDioxide.getFluid(1000)
            .buildAndRegister();
   
    }
}
