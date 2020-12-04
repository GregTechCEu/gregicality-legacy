package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust

public chain UraniumChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(1000)
            .input(dust, Pitchbende)
            .input(BariumCarbonate.getItemStack())
            .output(PitchblendeBaCOmix.getItemStack());
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1000)
            .input(PitchblendeBaCOmix.getItemStack())
            .fluidInputs(HydrochloricAcid.getFluid(3000))
            .fluidOutputs(UranylChlorideSolution.getFluid(3000))
            .fluidOutputs(CarbonDioxide.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1000)
            .input(dust, Uraninite)
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidOutputs(UranylChlorideSolution.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(1000)
            .fluidInputs(UranylChlorideSolution.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidOutputs(UranylNitrateSolution.getFluid(1000)
            .fluidOutputs(NitrogenDioxide.getFluid(2000))
            .fluidOutputs(HydrochloricAcid.getFluid(1000));
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1000)
            .fluidInputs(UranylNitrateSolution.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidOutputs(PurifiedUranylNitrate.getFluid(1000))
            .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000));
        ELECTROLYSER_RECIPES.recipeBuilder().duration(600).EUt(1000)
            
            
       
    }
}
