package gregicadditions.recipes.chain.optical;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class Lasers {
    public static void init() {

        // 2CH4 + 5Cl -> [2CH4 + 5Cl]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(Methane.getFluid(2000))
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidOutputs(ChlorinatedSolvents.getFluid(7000))
                .buildAndRegister();

        // 2[2CH4 + 5Cl] -> 1.33CH3Cl + 2.17CH2Cl2 + 2.17CHCl3 + 1.33CCl4
        // Not chemically balanced, but is a convenient, not too OP recipe
        DISTILLATION_RECIPES.recipeBuilder().duration(280).EUt(1100)
                .fluidInputs(ChlorinatedSolvents.getFluid(14000))
                .fluidOutputs(Chloromethane.getFluid(1330))
                .fluidOutputs(Dichloromethane.getFluid(2170))
                .fluidOutputs(Chloroform.getFluid(2170))
                .fluidOutputs(CarbonTetrachloride.getFluid(1330))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(130).EUt(1700)
                .fluidInputs(Butanol.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(ButanolGas.getFluid(1000))
                .buildAndRegister();

        // 3C4H10O + NH3 -> C12H27N + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(7400)
                .fluidInputs(ButanolGas.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .notConsumable(dust, Zeolite)
                .fluidOutputs(Tributylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // Al2O3 + 6HNO3 -> 2Al(NO3)3 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(6400)
                .inputs(Alumina.getItemStack(5))
                .fluidInputs(NitricAcid.getFluid(6000))
                .outputs(AluminiumNitrate.getItemStack(26))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 2Al(NO3)3 + CH2Cl2 + C12H27N -> [2Al(NO3)3 + CH2Cl2 + C12H27N]
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(12000)
                .inputs(AluminiumNitrate.getItemStack(26))
                .fluidInputs(Dichloromethane.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidOutputs(CrudeAluminaSolution.getFluid(1000))
                .buildAndRegister();

        // [2Al(NO3)3 + CH2Cl2 + C12H27N] + C12H27N + H2O2 -> [Al2O3 + CH2Cl2 + 2C12H27N] + 2HNO3 + NO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(7500)
                .fluidInputs(CrudeAluminaSolution.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(AluminaSolution.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        // 9Y2O3 + Nd2O3 -> 10 Neodymium Doped Yttrium
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(250).EUt(6400)
                .input(dust, YttriumOxide,45)
                .inputs(NeodymiumOxide.getItemStack(5))
                .outputs(NeodymiumDopedYttrium.getItemStack(10))
                .buildAndRegister();

        // 5NH3 + 5HCN + 3H2SO4 + 2KMnO4 -> 3H2O + 2MnSO4 + K2SO4 + 5NH4CNO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(3400)
                .fluidInputs(Ammonia.getFluid(5000))
                .fluidInputs(HydrogenCyanide.getFluid(5000))
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .inputs(PotassiumPermanganate.getItemStack(12))
                .fluidOutputs(Water.getFluid(3000))
                .outputs(ManganeseSulfate.getItemStack(12))
                .outputs(PotassiumSulfate.getItemStack(7))
                .fluidOutputs(AmmoniumCyanate.getFluid(5000))
                .buildAndRegister();

        // NH4CNO -> CH4N2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(1600)
                .fluidInputs(AmmoniumCyanate.getFluid(1000))
                .outputs(Urea.getItemStack(8))
                .buildAndRegister();

        // 3K2MnO4 + 2H2O -> MnO2 + 2KMnO4 + 4KOH
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(240).blastFurnaceTemp(720)
                .inputs(PotassiumManganate.getItemStack(21))
                .fluidInputs(Water.getFluid(2000))
                .output(dust, Pyrolusite, 3)
                .outputs(PotassiumPermanganate.getItemStack(12))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .buildAndRegister();

        // [Al2O3  + CH2Cl2 + 2C12H27N] + Neodymium Doped Yttrium + (NH2)CO(NH2) -> 2Unprocessed Nd:YAG + 2C12H27N
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(32000)
                .fluidInputs(AluminaSolution.getFluid(1000))
                .inputs(NeodymiumDopedYttrium.getItemStack())
                .inputs(Urea.getItemStack(8))
                .fluidOutputs(UnprocessedNdYAGSolution.getFluid(2000))
                .fluidOutputs(Tributylamine.getFluid(2000))
                .buildAndRegister();

        // Unprocessed Nd:YAG -> Unprocessed Nd:YAG Dust + CH2Cl2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(16000)
                .fluidInputs(UnprocessedNdYAGSolution.getFluid(1000))
                .fluidOutputs(Dichloromethane.getFluid(1000))
                .outputs(UnprocessedNdYAGDust.getItemStack())
                .buildAndRegister();

        // Unprocessed Nd:YAG Dust -> Nd:YAG Nanoparticles
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(24500).blastFurnaceTemp(300)
                .inputs(UnprocessedNdYAGDust.getItemStack())
                .outputs(NdYAGNanoparticles.getItemStack())
                .buildAndRegister();

        // Nd:YAG Nanoparticles + NdYAG -> Nd:YAG Boule
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidInputs(NdYAG.getFluid(18))
                .outputs(NDYAG_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(NDYAG_BOULE.getStackForm())
                .outputs(NDYAG_ROD.getStackForm(2))
                .output(dustTiny, NdYAG)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidOutputs(NdYAG.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, NdYAG)
                .fluidOutputs(NdYAG.getFluid(18))
                .buildAndRegister();

        // C2H7NO + NH3 -> C2H8N2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(21000)
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Ethylenediamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .notConsumable(dust, Palladium)
                .buildAndRegister();

        // 2C2H8N2 + 24CH2O + 8HCl + 8NaCN + 6O -> [3[C10H16N2O8 + C2H8N2 + 2O] + 8NaCl]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(24000)
                .fluidInputs(Ethylenediamine.getFluid(2000))
                .fluidInputs(Formaldehyde.getFluid(24000))
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .fluidInputs(SodiumCyanide.getFluid(8000))
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidOutputs(EDTASolution.getFluid(15000))
                .buildAndRegister();

        // [3[C10H16N2O8 + C2H8N2 + 2O] + 8NaCl] -> 8NaCl + 3C10H16N2O8 + 3[C2H8N2 + 2O]
        // 3[C2H8N2 + 2O] -> 3C2H5NO2 + 3NH3
        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(17500)
                .fluidInputs(EDTASolution.getFluid(15000))
                .output(dust, Salt, 16)
                .fluidOutputs(Ammonia.getFluid(3000))
                .fluidOutputs(EDTA.getFluid(3000))
                .fluidOutputs(Glycine.getFluid(3000))
                .buildAndRegister();

        // O + 3C2H5NO2 + 2HBr + 2CsOH -> 2CsBr + 2H2O + 2C3H7NO2 + HNO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(8000)
                .notConsumable(WHITE_HALIDE_LAMP.getStackForm())
                .inputs(CaesiumHydroxide.getItemStack(6))
                .fluidInputs(Glycine.getFluid(3000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .outputs(CesiumBromide.getItemStack(4))
                .outputs(Sarcosine.getItemStack(26))
                .buildAndRegister();

        // 2Cs + H2O2 -> 2CsOH
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2000)
                .input(dust, Caesium, 2)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .outputs(CaesiumHydroxide.getItemStack(6))
                .buildAndRegister();

        // CsBr -> Cs + Br
        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(CesiumBromide.getItemStack(2))
                .fluidOutputs(Bromine.getFluid(1000))
                .output(dust, Caesium)
                .buildAndRegister();

        // 2Pr + H2SO4 -> Pr2O3 + H2S + O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Praseodymium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(PraseodymiumOxide.getItemStack(5))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // 2Ho + H2SO4 -> Ho2O3 + H2S + O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Holmium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(HolmiumOxide.getItemStack(5))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // 2Nd + H2SO4 -> Nd2O3 + H2S + O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Neodymium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(NeodymiumOxide.getItemStack(5))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // 2Y + H2SO4 -> Y2O3 + H2S + O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Yttrium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, YttriumOxide, 5)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // 3Y2O3 + Pr2O3 + Ho2O3 + 30HNO3 -> [6Y(NO3)3 + 2Pr(NO3)3 + 2Ho(NO3)3 + 15H2O]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(18000)
                .input(dust, YttriumOxide,15)
                .inputs(PraseodymiumOxide.getItemStack(5))
                .inputs(HolmiumOxide.getItemStack(5))
                .fluidInputs(NitricAcid.getFluid(30000))
                .fluidOutputs(PrYHoNitrateSolution.getFluid(30000))
                .buildAndRegister();

        // Be + LiF + 2NH4HF2 + CO + [6Y(NO3)3 + 2Pr(NO3)3 + 2Nd(NO3)3 + 15H2O] -> 2PrHoYLF Nanoparticles + 2NH4NO3 + 2HF + BeF2 + CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .input(dust, LithiumFluoride, 2)
                .input(dust, Beryllium)
                .inputs(AmmoniumBifluoride.getItemStack(16))
                .fluidInputs(PrYHoNitrateSolution.getFluid(2000))
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .notConsumable(CetaneTrimethylAmmoniumBromide.getFluid(0))
                .notConsumable(EDTA.getFluid(0))
                .outputs(PrHoYLFNanoparticles.getItemStack(2))
                .output(dust, BerylliumFluoride, 3)
                .fluidOutputs(AmmoniumNitrate.getFluid(2000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // NH4NO3 -> NH3 + HNO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(120)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .inputs(PrHoYLFNanoparticles.getItemStack())
                .fluidInputs(PrHoYLF.getFluid(18))
                .outputs(PRHOYLF_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(PRHOYLF_BOULE.getStackForm())
                .outputs(PRHOYLF_ROD.getStackForm(2))
                .output(dustTiny, PrHoYLF)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(PrHoYLFNanoparticles.getItemStack())
                .fluidOutputs(PrHoYLF.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, PrHoYLF)
                .fluidOutputs(PrHoYLF.getFluid(18))
                .buildAndRegister();

        // NH4VO3 + NaCl + Na2O -> Na3VO4 + NH4Cl
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(4000).blastFurnaceTemp(700)
                .inputs(AmmoniumVanadate.getItemStack(9))
                .input(dust, Salt, 2)
                .inputs(SodiumOxide.getItemStack(3))
                .outputs(PureSodiumVanadate.getItemStack(8))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .buildAndRegister();

        // 2Na + O -> Na2O
        CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30)
                .notConsumable(new IntCircuitIngredient(1))
                .input(dust, Sodium, 2)
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(SodiumOxide.getItemStack(3))
                .buildAndRegister();

        // 3Y2O3 + Lu2O3 + Tm2O3 + 30HCl -> [6YCl3 + 2LuCl3 + 2TmCl3 + 15H2O]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(18000)
                .input(dust, YttriumOxide,15)
                .inputs(LutetiumOxide.getItemStack(5))
                .inputs(ThuliumOxide.getItemStack(5))
                .fluidInputs(HydrochloricAcid.getFluid(30000))
                .fluidOutputs(LuTmYChlorideSolution.getFluid(30000))
                .buildAndRegister();

        // NaVO3 + NH4Cl + H2O -> NH4VO3 + NaCl(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(1920)
                .inputs(SodiumMetavanadate.getItemStack(5))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .outputs(AmmoniumVanadate.getItemStack(9))
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();

        // [6YCl3 + 2LuCl3 + 2TmCl3 + 15H2O] + Na3VO4 + 2CH4N2O -> LuTmYVO Precipitate + 0.9Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(32000)
                .fluidInputs(LuTmYChlorideSolution.getFluid(1000))
                .inputs(PureSodiumVanadate.getItemStack(8))
                .inputs(Urea.getItemStack(16))
                .fluidOutputs(Chlorine.getFluid(900))
                .outputs(LuTmYVOPrecipitate.getItemStack())
                .buildAndRegister();

        // LuTmYVO Precipitate + C2H6O -> LuTmYVO Nanoparticles + 3NaCl + (NH4)2CO3 + C3H6
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(13500)
                .inputs(LuTmYVOPrecipitate.getItemStack())
                .fluidInputs(Ethanol100.getFluid(1000))
                .outputs(LuTmYVONanoparticles.getItemStack())
                .output(dust, Salt, 6)
                .outputs(AmmoniumCarbonate.getItemStack(14))
                .fluidOutputs(Propene.getFluid(1000))
                .buildAndRegister();

        // (NH4)2CO3 + Ca + NaH + 2O -> 2NH3 + Ca(OH)2 + NaHCO3
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(750).blastFurnaceTemp(700)
                .inputs(AmmoniumCarbonate.getItemStack(14))
                .inputs(SodiumHydride.getItemStack(2))
                .input(dust, Calcium)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(2000))
                .outputs(SodiumBicarbonate.getItemStack(6))
                .outputs(CalciumHydroxide.getItemStack(5))
                .buildAndRegister();

        // NaH -> Na + H
        ELECTROLYZER_RECIPES.recipeBuilder().duration(240).EUt(30)
                .inputs(SodiumHydride.getItemStack(2))
                .output(dust, Sodium)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .inputs(LuTmYVONanoparticles.getItemStack())
                .fluidInputs(LuTmYVO.getFluid(72))
                .outputs(LUTMYVO_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(LUTMYVO_BOULE.getStackForm())
                .outputs(LUTMYVO_ROD.getStackForm(2))
                .output(dustTiny, LuTmYVO)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(LuTmYVONanoparticles.getItemStack())
                .fluidOutputs(LuTmYVO.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, LuTmYVO)
                .fluidOutputs(LuTmYVO.getFluid(18))
                .buildAndRegister();

        // Si + 4F -> SiF4
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(2000)
                .input(dust, Silicon)
                .fluidInputs(Fluorine.getFluid(4000))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .buildAndRegister();

        // SiF4 + 2HF -> H2SiF6
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1300)
                .fluidInputs(SiliconFluoride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .buildAndRegister();

        // H2SiF6 + 6NH3 + 2H2O -> SiO2 + 6NH4F
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(940)
                .fluidInputs(FluorosilicicAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(6000))
                .fluidInputs(Water.getFluid(2000))
                .output(dust, SiliconDioxide, 3)
                .fluidOutputs(AmmoniumFluoride.getFluid(6000))
                .buildAndRegister();

        // 2NH4F -> NH3 + NH4HF2
        DISTILLATION_RECIPES.recipeBuilder().duration(340).EUt(1600)
                .fluidInputs(AmmoniumFluoride.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .outputs(AmmoniumBifluoride.getItemStack(8))
                .buildAndRegister();

        // H2O + NH4HF2 -> [NH4HF2 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(DistilledWater.getFluid(1000))
                .inputs(AmmoniumBifluoride.getItemStack(8))
                .fluidOutputs(AmmoniumBifluorideSolution.getFluid(1000))
                .buildAndRegister();

        // [NH4HF2 + H2O] -> NH3 + 2HF + H2O
        DISTILLATION_RECIPES.recipeBuilder().duration(260).EUt(1300)
                .fluidInputs(AmmoniumBifluorideSolution.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
    }
}
