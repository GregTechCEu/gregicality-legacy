package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAMaterials.ErbiumDopedZBLANDust;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.GAMetaItems.CLADDED_OPTICAL_FIBER_CORE;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Polytetrafluoroethylene;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class OpticalChain {

    public static void init() { // TODO

        // FIBER CHEMISTRY =============================================================================================

        // Zr + 4F -> ZrF4
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, Zirconium)
                .fluidInputs(Fluorine.getFluid(4000))
                .outputs(ZirconiumTetrafluoride.getItemStack(5))
                .buildAndRegister();

        // Ba + 2F -> BaF2
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, Barium)
                .fluidInputs(Fluorine.getFluid(2000))
                .outputs(BariumDifluoride.getItemStack(3))
                .buildAndRegister();

        // La + 3F -> LaF3
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, Lanthanum)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(LanthanumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // Al + 3F -> AlF3
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, Aluminium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // Er + 3F -> ErF3
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, Erbium)
                .fluidInputs(Fluorine.getFluid(3000))
                .outputs(ErbiumTrifluoride.getItemStack(4))
                .buildAndRegister();

        // Si + 4F -> SiF4
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(480)
                .input(dust, Silicon)
                .fluidInputs(Fluorine.getFluid(4000))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .buildAndRegister();

        // LASER CHEMISTRY =============================================================================================

        // SiF4 + 2HF -> H2SiF6
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .fluidInputs(SiliconFluoride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .buildAndRegister();

        // H2SiF6 + 6NH3 + 2H2O -> SiO2 + 6NH4F
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(FluorosilicicAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(6000))
                .fluidInputs(Water.getFluid(2000))
                .output(dust, SiliconDioxide, 3)
                .fluidOutputs(AmmoniumFluoride.getFluid(6000))
                .buildAndRegister();

        // 2NH4F -> NH3 + NH4HF2
        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(120)
                .fluidInputs(AmmoniumFluoride.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .outputs(AmmoniumBifluoride.getItemStack(8))
                .buildAndRegister();

        // H2O + NH4HF2 -> [NH4HF2 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(140).EUt(30)
                .fluidInputs(DistilledWater.getFluid(1000))
                .inputs(AmmoniumBifluoride.getItemStack(8))
                .fluidOutputs(AmmoniumBifluorideSolution.getFluid(1000))
                .buildAndRegister();

        // [NH4HF2 + H2O] -> NH3 + 2HF + H2O
        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(120)
                .fluidInputs(AmmoniumBifluorideSolution.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // NH4VO3 + NaCl + Na2O -> Na3VO4 + NH4Cl
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(120).blastFurnaceTemp(700)
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
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(15360)
                .input(dust, YttriumOxide,15)
                .inputs(LutetiumOxide.getItemStack(5))
                .inputs(ThuliumOxide.getItemStack(5))
                .fluidInputs(HydrochloricAcid.getFluid(30000))
                .fluidOutputs(LuTmYChlorideSolution.getFluid(30000))
                .buildAndRegister();

        // NaVO3 + NH4Cl + H2O -> NH4VO3 + NaCl(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(120)
                .inputs(SodiumMetavanadate.getItemStack(5))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .outputs(AmmoniumVanadate.getItemStack(9))
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();

        // [6YCl3 + 2LuCl3 + 2TmCl3 + 15H2O] + Na3VO4 + 2CH4N2O -> LuTmYVO Precipitate + 0.9Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(LuTmYChlorideSolution.getFluid(1000))
                .inputs(PureSodiumVanadate.getItemStack(8))
                .inputs(Urea.getItemStack(16))
                .fluidOutputs(Chlorine.getFluid(900))
                .outputs(LuTmYVOPrecipitate.getItemStack())
                .buildAndRegister();

        // LuTmYVO Precipitate + C2H6O -> LuTmYVO Nanoparticles + 3NaCl + (NH4)2CO3 + C3H6
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(7680)
                .inputs(LuTmYVOPrecipitate.getItemStack())
                .fluidInputs(Ethanol100.getFluid(1000))
                .outputs(LuTmYVONanoparticles.getItemStack())
                .output(dust, Salt, 6)
                .outputs(AmmoniumCarbonate.getItemStack(14))
                .fluidOutputs(Propene.getFluid(1000))
                .buildAndRegister();

        // (NH4)2CO3 + Ca + NaH + 2O -> 2NH3 + Ca(OH)2 + NaHCO3
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(120).blastFurnaceTemp(700)
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

        // MgF2 + ZnS + Ta2O5 + TiO2 + C2H5OH -> Dielectric Mirror Formation Mix
        MIXER_RECIPES.recipeBuilder().duration(270).EUt(983040)
                .inputs(MagnesiumFluoride.getItemStack(3))
                .inputs(ZincSulfide.getItemStack(2))
                .inputs(TantalumOxide.getItemStack(7))
                .input(dust, Rutile, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(DielectricMirrorFormationMix.getFluid(1000))
                .buildAndRegister();

        // COMPONENTS CHEMISTRY ========================================================================================

        // Nb + 5Cl -> NbCl5
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(120)
                .input(dust, Niobium)
                .fluidInputs(Chlorine.getFluid(5000))
                .outputs(NiobiumChloride.getItemStack(6))
                .buildAndRegister();

        // LiAlH4 -> LiH + AlH3
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(1600)
                .inputs(LithiumAluminiumHydride.getItemStack(6))
                .outputs(LithiumHydride.getItemStack(2))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        // NbCl5 + LiH + 2H2O2 -> LiNbO4 + 5HCl
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(4500)
                .inputs(NiobiumChloride.getItemStack(6))
                .inputs(LithiumHydride.getItemStack(2))
                .notConsumable(dust, Hafnium)
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .output(ingotHot, LithiumNiobate, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .buildAndRegister();

        // 5NaOH + NbCl5 -> 5NaCl + H5NbO5
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, SodiumHydroxide, 15)
                .inputs(NiobiumChloride.getItemStack(6))
                .output(dust, Salt, 10)
                .outputs(NiobiumHydroxide.getItemStack(11))
                .buildAndRegister();

        // 2H5NbO5 + 5C2H2O4 + NH3 + Na -> 9H2O + [C10Nb2O20 + NH4] + NaOH
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(480)
                .inputs(NiobiumHydroxide.getItemStack(22))
                .input(dust, Sodium)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(OxalicAcid.getFluid(5000))
                .output(dust ,SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(9000))
                .fluidOutputs(AmmoniumNiobiumOxalateSolution.getFluid(1000))
                .buildAndRegister();

        // [C10Nb2O20 + NH4] + 2LiNbO4 -> Nanoparticles
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(491520)
                .input(dust, LithiumNiobate, 12)
                .fluidInputs(AmmoniumNiobiumOxalateSolution.getFluid(1000))
                .outputs(LithiumNiobateNanoparticles.getItemStack(3))
                .buildAndRegister();

        // MgO + NH4HF2 -> MgF2 + NH3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Magnesia, 2)
                .inputs(AmmoniumBifluoride.getItemStack(8))
                .outputs(MagnesiumFluoride.getItemStack(3))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // P + I -> InP
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(120).blastFurnaceTemp(4600)
                .input(dust, Phosphorus)
                .input(dust, Indium)
                .output(dust, IndiumPhospide, 2)
                .buildAndRegister();

        // Zn + S -> ZnS
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(120).blastFurnaceTemp(4600)
                .input(dust, Zinc)
                .input(dust, Sulfur)
                .outputs(ZincSulfide.getItemStack(2))
                .buildAndRegister();

        // NH4NO3 -> NH3 + HNO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(220).EUt(120)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .buildAndRegister();

        // C2H7NO + NH3 -> C2H8N2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Ethylenediamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .notConsumable(dust, Palladium)
                .buildAndRegister();

        // 2C2H8N2 + 24CH2O + 8HCl + 8NaCN + 6O -> [3[C10H16N2O8 + C2H8N2 + 2O] + 8NaCl]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(7680)
                .fluidInputs(Ethylenediamine.getFluid(2000))
                .fluidInputs(Formaldehyde.getFluid(24000))
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .fluidInputs(SodiumCyanide.getFluid(8000))
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidOutputs(EDTASolution.getFluid(15000))
                .buildAndRegister();

        // [3[C10H16N2O8 + C2H8N2 + 2O] + 8NaCl] -> 8NaCl + 3C10H16N2O8 + 3[C2H8N2 + 2O]
        // 3[C2H8N2 + 2O] -> 3C2H5NO2 + 3NH3
        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .fluidInputs(EDTASolution.getFluid(15000))
                .output(dust, Salt, 16)
                .fluidOutputs(Ammonia.getFluid(3000))
                .fluidOutputs(EDTA.getFluid(3000))
                .fluidOutputs(Glycine.getFluid(3000))
                .buildAndRegister();

        // O + 3C2H5NO2 + 2HBr + 2CsOH -> 2CsBr + 2H2O + 2C3H7NO2 + HNO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
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
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, Caesium, 2)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .outputs(CaesiumHydroxide.getItemStack(6))
                .buildAndRegister();

        // CsBr -> Cs + Br
        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(120)
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
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(15360)
                .input(dust, YttriumOxide,15)
                .inputs(PraseodymiumOxide.getItemStack(5))
                .inputs(HolmiumOxide.getItemStack(5))
                .fluidInputs(NitricAcid.getFluid(30000))
                .fluidOutputs(PrYHoNitrateSolution.getFluid(30000))
                .buildAndRegister();

        // Be + LiF + 2NH4HF2 + CO + [6Y(NO3)3 + 2Pr(NO3)3 + 2Nd(NO3)3 + 15H2O] -> 2PrHoYLF Nanoparticles + 2NH4NO3 + 2HF + BeF2 + CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(7680)
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

        // 2CH4 + 5Cl -> [2CH4 + 5Cl]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .fluidInputs(Methane.getFluid(2000))
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidOutputs(ChlorinatedSolvents.getFluid(7000))
                .buildAndRegister();

        // 2[2CH4 + 5Cl] -> 1.33CH3Cl + 2.17CH2Cl2 + 2.17CHCl3 + 1.33CCl4
        // Not chemically balanced, but is a convenient, not too OP recipe
        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .fluidInputs(ChlorinatedSolvents.getFluid(14000))
                .fluidOutputs(Chloromethane.getFluid(1330))
                .fluidOutputs(Dichloromethane.getFluid(2170))
                .fluidOutputs(Chloroform.getFluid(2170))
                .fluidOutputs(CarbonTetrachloride.getFluid(1330))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(130).EUt(480)
                .fluidInputs(Butanol.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(ButanolGas.getFluid(1000))
                .buildAndRegister();

        // 3C4H10O + NH3 -> C12H27N + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(480)
                .fluidInputs(ButanolGas.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .notConsumable(dust, Zeolite)
                .fluidOutputs(Tributylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // Al2O3 + 6HNO3 -> 2Al(NO3)3 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(30)
                .inputs(Alumina.getItemStack(5))
                .fluidInputs(NitricAcid.getFluid(6000))
                .outputs(AluminiumNitrate.getItemStack(26))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 2Al(NO3)3 + CH2Cl2 + C12H27N -> [2Al(NO3)3 + CH2Cl2 + C12H27N]
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(120)
                .inputs(AluminiumNitrate.getItemStack(26))
                .fluidInputs(Dichloromethane.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidOutputs(CrudeAluminaSolution.getFluid(1000))
                .buildAndRegister();

        // [2Al(NO3)3 + CH2Cl2 + C12H27N] + C12H27N + H2O2 -> [Al2O3 + CH2Cl2 + 2C12H27N] + 2HNO3 + NO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .fluidInputs(CrudeAluminaSolution.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(AluminaSolution.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        // 5NH3 + 5HCN + 3H2SO4 + 2KMnO4 -> 3H2O + 2MnSO4 + K2SO4 + 5NH4CNO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(480)
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
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(AmmoniumCyanate.getFluid(1000))
                .outputs(Urea.getItemStack(8))
                .buildAndRegister();

        // 3K2MnO4 + 2H2O -> MnO2 + 2KMnO4 + 4KOH
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(120).blastFurnaceTemp(720)
                .inputs(PotassiumManganate.getItemStack(21))
                .fluidInputs(Water.getFluid(2000))
                .output(dust, Pyrolusite, 3)
                .outputs(PotassiumPermanganate.getItemStack(12))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .buildAndRegister();
    }
}
