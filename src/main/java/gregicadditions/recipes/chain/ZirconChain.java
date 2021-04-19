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

        // Ba + H2O2 = Ba(OH)2
        CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(500)
                .input(dust, Barium)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .outputs(BariumHydroxide.getItemStack(5))
                .buildAndRegister();

        // 2C3H6O = C6H10O + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(600)
                .notConsumable(BariumHydroxide.getItemStack())
                .fluidInputs(Acetone.getFluid(2000))
                .fluidOutputs(MesitylOxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C6H10O + C + H2O = C6H12O + CO
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(650)
                .notConsumable(dust, Palladium)
                .input(dust, Carbon)
                .fluidInputs(MesitylOxide.getFluid(1000))
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .fluidOutputs(MethylIsobutylKetone.getFluid(1000))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // S + HCN = HSCN
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(500)
                .input(dust, Sulfur)
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidOutputs(ThiocyanicAcid.getFluid(1000))
                .buildAndRegister();

        // C6H12O + HSCN = [C6H12O + HSCN]
        MIXER_RECIPES.recipeBuilder().duration(80).EUt(120)
                .fluidInputs(MethylIsobutylKetone.getFluid(1000))
                .fluidInputs(ThiocyanicAcid.getFluid(1000))
                .fluidOutputs(ZrHfSeparationMix.getFluid(2000))
                .buildAndRegister();

        // ZrSiO4 + 8Cl = ZrHfCl4 + SiCl4?
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, Zircon, 6)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(ZrHfChloride.getFluid(1000))
                .fluidOutputs(ZirconChlorinatingResidue.getFluid(1000))
                .buildAndRegister();

        // SiCl4? = SiCl4 + Co + RareEarth
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(ZirconChlorinatingResidue.getFluid(1000))
                .outputs(SiliconChloride.getItemStack(5))
                .chancedOutput(OreDictUnifier.get(dust, Cobalt), 7500, 450)
                .chancedOutput(OreDictUnifier.get(dust, RareEarth), 200, 20)
                .buildAndRegister();

        // SiCl4 = Si + 4Cl
        ELECTROLYZER_RECIPES.recipeBuilder().duration(240).EUt(125)
                .inputs(SiliconChloride.getItemStack(5))
                .output(dust, Silicon)
                .fluidOutputs(Chlorine.getFluid(4000))
                .buildAndRegister();

        // ZrHfCl4 + H2O = Cl2HfOZr + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(700)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(ZrHfChloride.getFluid(1000))
                .fluidOutputs(ZrHfOxyChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // 3Cl2HfOZr + 3HClO + 3H2SO4 + 15NH3 + 3H2O2 = 9NH4Cl + 3(NH4)2SO4 + 3ZrO2 + 3HfO2 (THIS IS TOO BIG)
        // 3Cl2HfOZr + 3H2SO4 + 6NH4Cl + 9O = 3(NH4)2SO4 + 6HCl + 6Cl + 3ZrO2 + 3HfO2 (THIS FITS)
        // 3Cl2HfOZr + 3SO3 + 6NH4Cl + 6H2O2 = 3(NH4)2SO4 + 12HCl + 3ZrO2 + 3HfO2 (CURRENTLY USING)
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(650)
                .fluidInputs(ZrHfOxyChloride.getFluid(3000))
                .fluidInputs(SulfurTrioxide.getFluid(3000))
                .fluidInputs(AmmoniumChloride.getFluid(6000))
                .fluidInputs(HydrogenPeroxide.getFluid(6000))
                .notConsumable(ZrHfSeparationMix.getFluid(0))
                .fluidOutputs(AmmoniumSulfate.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(12000))
                .output(dust, CubicZirconia, 3)
                .chancedOutput(HafniumOxide.getItemStack(3), 1000, 0)
                .buildAndRegister();

        // C + 4Cl + ZrO2 = CO2 + ZrCl4
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(550)
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .input(dust, CubicZirconia, 3)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(ZirconiumTetrachloride.getItemStack(5))
                .buildAndRegister();

        // ZrCl4 + 2Mg = Zr + 2MgCl2
        BLAST_RECIPES.recipeBuilder().duration(300).EUt(600).blastFurnaceTemp(2500)
                .inputs(ZirconiumTetrachloride.getItemStack(5))
                .input(dust, Magnesium, 2)
                .output(dust, Zirconium)
                .output(dust, MagnesiumChloride, 6)
                .buildAndRegister();

        // C + 4Cl + HfO2 = CO2 + HfCl4
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(550)
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .inputs(HafniumOxide.getItemStack(3))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(HafniumChloride.getItemStack(5))
                .buildAndRegister();

        // HfCl4 + 2Mg = Hf + 2MgCl2
        BLAST_RECIPES.recipeBuilder().duration(300).EUt(600).blastFurnaceTemp(2500)
                .inputs(HafniumChloride.getItemStack(5))
                .input(dust, Magnesium, 2)
                .output(dust, Hafnium)
                .output(dust, MagnesiumChloride, 6)
                .buildAndRegister();
    }
}
