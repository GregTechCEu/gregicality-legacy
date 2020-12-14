package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class LithiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Spodumene)
                .outputs(RoastedSpodumene.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Lepidolite)
                .input(dust, Quicklime)
                .outputs(RoastedLepidolite.getItemStack())
                .outputs(CalciumFluoride.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(160).EUt(120)
                .inputs(CalciumFluoride.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Calcium))
                .fluidOutputs(Fluorine.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(120)
                .inputs(RoastedSpodumene.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SiliconDioxide, 6))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(120)
                .inputs(RoastedLepidolite.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(3000))
                .outputs(PotassiumSulfate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(DissolvedLithiumOre.getFluid(3000))
                .input(dust, SodaAsh, 3)
                .outputs(AluminiumSulfate.getItemStack(4))
                .fluidOutputs(LithiumCarbonateSolution.getFluid(3000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(PotassiumSulfate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Potassium))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(AluminiumSulfate.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Aluminium, 3))
                .outputs(OreDictUnifier.get(dust, Sulfur, 3))
                .fluidOutputs(Oxygen.getFluid(12000))
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(120)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(LithiumChlorideSolution.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(LithiumChlorideSolution.getFluid(1000))
                .outputs(LithiumChloride.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(90).EUt(120)
                .inputs(LithiumChloride.getItemStack())
                .input(dust, RockSalt)
                .outputs(LiKChlorideEutetic.getItemStack(2))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(150).EUt(120)
                .inputs(LiKChlorideEutetic.getItemStack())
                .fluidOutputs(MoltenLiKChlorideEutetic.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(125)
                .fluidInputs(MoltenLiKChlorideEutetic.getFluid(4000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .fluidOutputs(Lithium.getFluid(1000))
                .fluidOutputs(Potassium.getFluid(1000))
                .buildAndRegister();

    }
}
