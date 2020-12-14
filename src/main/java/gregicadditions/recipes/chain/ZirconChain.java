package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class ZirconChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(500)
                .input(dust, Barium)
                .fluidInputs(Water.getFluid(2000))
                .outputs(BariumHydroxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(600)
                .notConsumable(BariumHydroxide.getItemStack())
                .fluidInputs(Acetone.getFluid(2000))
                .fluidOutputs(MesitylOxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(650)
                .notConsumable(dust, Palladium)
                .fluidInputs(MesitylOxide.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(MethylIsobutylKetone.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(500)
                .input(dust, Sulfur)
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidOutputs(ThiocyanicAcid.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(80).EUt(120)
                .fluidInputs(MethylIsobutylKetone.getFluid(1000))
                .fluidInputs(ThiocyanicAcid.getFluid(1000))
                .fluidOutputs(ZrHfSeparationMix.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, Zircon)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(ZrHfChloride.getFluid(1000))
                .fluidOutputs(ZirconChlorinatingResidue.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(ZirconChlorinatingResidue.getFluid(2000))
                .outputs(SiliconChloride.getItemStack())
                .chancedOutput(OreDictUnifier.get(dust, Cobalt), 7500, 450)
                .chancedOutput(OreDictUnifier.get(dust, RareEarth), 200, 20)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(240).EUt(125)
                .inputs(SiliconChloride.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Silicon))
                .fluidOutputs(Chlorine.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(700)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(ZrHfChloride.getFluid(1000))
                .fluidOutputs(ZrHfOxyChloride.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(650)
                .fluidInputs(ZrHfOxyChloride.getFluid(3000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(9000))
                .fluidInputs(ZrHfSeparationMix.getFluid(200))
                .fluidOutputs(AmmoniumChloride.getFluid(6000))
                .fluidOutputs(AmmoniumSulfate.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, CubicZirconia, 3))
                .chancedOutput(HafniumOxide.getItemStack(3), 1000, 0)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(550)
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .input(dust, CubicZirconia)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(ZirconiumTetrachloride.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(300).EUt(600).blastFurnaceTemp(2500)
                .inputs(ZirconiumTetrachloride.getItemStack())
                .input(dust, Magnesium, 2)
                .outputs(OreDictUnifier.get(dust, Zirconium))
                .outputs(OreDictUnifier.get(dust, MagnesiumChloride, 2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(550)
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .inputs(HafniumOxide.getItemStack())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(HafniumChloride.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(300).EUt(600).blastFurnaceTemp(2500)
                .inputs(HafniumChloride.getItemStack())
                .input(dust, Magnesium, 2)
                .outputs(OreDictUnifier.get(dust, Hafnium))
                .outputs(OreDictUnifier.get(dust, MagnesiumChloride, 2))
                .buildAndRegister();


    }

}
