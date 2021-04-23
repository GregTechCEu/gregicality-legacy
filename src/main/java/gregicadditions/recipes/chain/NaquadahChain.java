package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class NaquadahChain {

    public static void init() {

        // Nq + 2[HNO3 + HCl] -> Naquadric Solution [Nq + NO2] + [H2O + HNO3] + HCl + Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, NaquadricCompound)
                .fluidInputs(AquaRegia.getFluid(4000))
                .fluidOutputs(NaquadricSolution.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // Nq + 2[HNO3 + HCl] = Enriched Naquadric Solution [Nq + NO2] + [H2O + HNO3] + HCl + Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, EnrichedNaquadricCompound)
                .fluidInputs(AquaRegia.getFluid(4000))
                .fluidOutputs(EnrichedNaquadricSolution.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // Nq + 2[HNO3 + HCl] = Naquadriatic Solution [Nq + NO2] + [H2O + HNO3] + HCl + Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, NaquadriaticCompound)
                .fluidInputs(AquaRegia.getFluid(4000))
                .fluidOutputs(NaquadriaticSolution.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // Sb2O3 + 6HF = 2SbF3 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AntimonyTrioxide, 5)
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .outputs(AntimonyTrifluoride.getItemStack(8))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // SbF3 + 2F = SbF5
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(AntimonyTrifluoride.getItemStack(4))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(AntimonyPentafluoride.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // SbF5 + 2HF = H2SbF7
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(AntimonyPentafluoride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(FluoroantimonicAcid.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Naquadric Solution [Nq + NO2] + H2SbF7 = SbF3 + Fluoronaquadric Acid [H2NqF4] + NO2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NaquadricSolution.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .fluidOutputs(FluoronaquadricAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Enriched Naquadric Solution [Nq + NO2] + H2SbF7 = SbF3 + Enriched Fluoronaquadric Acid [H2NqF4] + NO2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadricSolution.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .fluidOutputs(EnrichedFluoronaquadricAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Naquadriatic Solution [Nq + NO2] + H2SbF7 = SbF3 + Fluoronaquadriatic Acid [H2NqF4] + NO2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaticSolution.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .fluidOutputs(FluoronaquadriaticAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Fluoronaquadric Acid [H2NqF4] = 2HF + Naquadah Difluoride [NqF2]
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(FluoronaquadricAcid.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(NaquadahDifluoride.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        // Enriched Fluoronaquadric Acid [H2NqF4] = 2HF + Enriched Naquadah Difluoride [NqF2]
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedFluoronaquadricAcid.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(EnrichedNaquadahDifluoride.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        // Fluoronaquadriatic Acid [H2NqF4] = 2HF + Naquadria Difluoride [NqF2]
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(FluoronaquadriaticAcid.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(NaquadriaDifluoride.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        // 3 Naquadah Difluoride [NqF2] + 2In = 2InF3 + 3 Naquadah Concentrate [Nq]
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(NaquadahDifluoride.getFluid(3000))
                .input(dust, Indium, 2)
                .outputs(IndiumTrifluoride.getItemStack(8))
                .outputs(NaquadahConcentrate.getItemStack(3))
                .EUt(30720)
                .duration(100)
                .blastFurnaceTemp(4500)
                .buildAndRegister();

        // 3 Enriched Naquadah Difluoride [NqF2] + 2In = 2InF3 + 3 Enriched Naquadah Concentrate [Nq]
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadahDifluoride.getFluid(3000))
                .input(dust, Indium, 2)
                .outputs(IndiumTrifluoride.getItemStack(8))
                .outputs(EnrichedNaquadahConcentrate.getItemStack(3))
                .EUt(30720)
                .duration(100)
                .blastFurnaceTemp(4500)
                .buildAndRegister();

        // 3 Naquadria Difluoride [NqF2] + 2In = 2InF3 + 3 Naquadria Concentrate [Nq]
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaDifluoride.getFluid(3000))
                .input(dust, Indium, 2)
                .outputs(IndiumTrifluoride.getItemStack(8))
                .outputs(NaquadriaConcentrate.getItemStack(3))
                .EUt(30720)
                .duration(100)
                .blastFurnaceTemp(4500)
                .buildAndRegister();

        // 6F + Naquadria Concentrate [Nq] = Naquadria Hexafluoride [NqF6]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Fluorine.getFluid(6000))
                .inputs(NaquadriaConcentrate.getItemStack())
                .fluidOutputs(NaquadriaHexafluoride.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // Rn + 2F = RnF2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(RadonDifluoride.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // RnF2 + Naquadria Hexafluoride [NqF6] = Radon Naquadriaoctafluoride [RnNqF8]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RadonDifluoride.getFluid(1000))
                .fluidInputs(NaquadriaHexafluoride.getFluid(1000))
                .fluidOutputs(RadonNaquadriaoctafluoride.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // Xe + 3O = XeO3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(XenonTrioxide.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // Cs + F = CsF
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Caesium)
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidOutputs(CesiumFluoride.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // CsF + XeO3 = CsXeO3F
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CesiumFluoride.getFluid(1000))
                .fluidInputs(XenonTrioxide.getFluid(1000))
                .fluidOutputs(CesiumXenontrioxideFluoride.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // CsXeO3F + Radon Naquadriaoctafluoride [RnNqF8] = RnO3 + NqCsXeF9
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CesiumXenontrioxideFluoride.getFluid(1000))
                .fluidInputs(RadonNaquadriaoctafluoride.getFluid(1000))
                .fluidOutputs(RadonTrioxide.getFluid(1000))
                .fluidOutputs(NaquadriaCesiumXenonNonfluoride.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // NO2 + F = NO2F
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrogenDioxide.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidOutputs(NitrylFluoride.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // 2NO2F + NqCsXeF9 = (NO2)2XeF8 + NqF2CsF
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrylFluoride.getFluid(2000))
                .fluidInputs(NaquadriaCesiumXenonNonfluoride.getFluid(1000))
                .fluidOutputs(NitrosoniumOctafluoroxenate.getFluid(1000))
                .fluidOutputs(NaquadriaCesiumfluoride.getFluid(1000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        // NqF2CsF = Nq + 3F + Cs
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaCesiumfluoride.getFluid(1000))
                .output(dust, Naquadria)
                .fluidOutputs(Fluorine.getFluid(3000))
                .output(dust, Caesium)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // RnO3 = Rn + O3
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(RadonTrioxide.getFluid(1000))
                .fluidOutputs(Radon.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // (NO2)2XeF8 = 8F + Xe + 2NO2
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(NitrosoniumOctafluoroxenate.getFluid(1000))
                .fluidOutputs(Fluorine.getFluid(8000))
                .fluidOutputs(Xenon.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(2000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // Enriched Naquadah Concentrate [Nq] + 6F = NqF6
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(EnrichedNaquadahConcentrate.getItemStack())
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidOutputs(EnrichedNaquadahhexafluoride.getFluid(1000))
                .EUt(7680)
                .duration(175)
                .buildAndRegister();

        // NqF6 + Xe = XeNqF6
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadahhexafluoride.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidOutputs(EnrichedXenonHexafluoronaquadate.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Au + HNO3 + 4HCl = HAuCl4 + NO + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .EUt(8000)
                .duration(50)
                .input(dust, Gold)
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(ChloroauricAcid.getFluid(1000))
                .fluidOutputs(NitricOxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2Au + 3Cl2 -> Au2Cl6
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Gold, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidOutputs(AuricChloride.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // Br + 3F = BrF3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Bromine.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(3000))
                .fluidOutputs(BromineTrifluoride.getFluid(1000))
                .EUt(1920)
                .duration(250)
                .buildAndRegister();

        // 2BrF3 + Au2Cl6 = 2AuF3 + 2Br + 6Cl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BromineTrifluoride.getFluid(2000))
                .fluidInputs(AuricChloride.getFluid(1000))
                .outputs(AuricFluoride.getItemStack(8))
                .fluidOutputs(Bromine.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(6000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // H2SbF7 + AuF3 + XeNqF6 + 9H = Nq + 9HF + [AuXe + H2SbF7]
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(AuricFluoride.getItemStack(4))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidInputs(EnrichedXenonHexafluoronaquadate.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(9000))
                .output(dust, NaquadahEnriched)
                .fluidOutputs(XenoauricFluoroantimonicAcid.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(9000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        // [AuXe + H2SbF7] = Au + Xe + 2HF + SbF5
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(XenoauricFluoroantimonicAcid.getFluid(1000))
                .output(dust, Gold)
                .fluidOutputs(Xenon.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(AntimonyPentafluoride.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // 2H2SO4 + 3H2O2 + 2K = 2KHSO5 + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(3000))
                .input(dust, Potassium, 2)
                .outputs(PotassiumPeroxymonosulfate.getItemStack(16))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // KOH + O + H2SO4 -> KHSO5 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(PotassiumPeroxymonosulfate.getItemStack(8))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // HF + KHSO5 + Nq = H2O + KF + NqSO4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .inputs(PotassiumPeroxymonosulfate.getItemStack(8))
                .inputs(NaquadahConcentrate.getItemStack())
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, PotassiumFluoride, 2)
                .fluidOutputs(NaquadahSulfate.getFluid(1000))
                .EUt(7980)
                .duration(200)
                .buildAndRegister();

        // NqSO4 + 2H = H2SO4 + Nq
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NaquadahSulfate.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .output(dust, Naquadah)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // 2InF3 + 3H2O = In2O3 + 6HF
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(IndiumTrifluoride.getItemStack(8))
                .fluidInputs(Water.getFluid(3000))
                .outputs(IndiumTrioxide.getItemStack(5))
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // In2O3 + 3C = 2In + 3CO
        BLAST_RECIPES.recipeBuilder()
                .inputs(IndiumTrioxide.getItemStack(5))
                .input(dust, Carbon, 3)
                .output(dust, Indium, 2)
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .EUt(480)
                .duration(200)
                .blastFurnaceTemp(4500)
                .buildAndRegister();

        // NH3 + HNO3 = NH4NO3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidOutputs(AmmoniumNitrate.getFluid(1000))
                .EUt(480)
                .duration(250)
                .buildAndRegister();

        // Nq + NH4NO3 = Naquadah Solution [Nq + NH4NO3]
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Naquadah)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .fluidOutputs(NaquadahSolution.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // 2 Naquadah Solution [Nq + NH4NO3] = Clear Naquadah Liquid + NH3 + HNO3
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(NaquadahSolution.getFluid(2000))
                .fluidOutputs(ClearNaquadahLiquid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .output(dustTiny, PlatinumRawPowder, 15)
                .output(dustTiny, IridiumChloride, 12)
                .output(dustTiny, Naquadah)
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(ClearNaquadahLiquid.getFluid(1000))
                .fluidOutputs(ComplicatedNaquadahGas.getFluid(60))
                .fluidOutputs(ComplicatedLightNaquadah.getFluid(180))
                .fluidOutputs(ComplicatedMediumNaquadah.getFluid(320))
                .fluidOutputs(ComplicatedHeavyNaquadah.getFluid(440))
                .EUt(7680)
                .duration(500)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedNaquadahGas.getFluid(2000))
                .fluidOutputs(NaquadahGas.getFluid(1000))
                .output(dustTiny, IridiumChloride, 4)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedHeavyNaquadah.getFluid(2000))
                .fluidOutputs(HeavyNaquadah.getFluid(1000))
                .output(dustTiny, IridiumChloride, 4)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedLightNaquadah.getFluid(2000))
                .fluidOutputs(LightNaquadah.getFluid(1000))
                .output(dustTiny, IridiumChloride, 4)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedMediumNaquadah.getFluid(2000))
                .fluidOutputs(MediumNaquadah.getFluid(1000))
                .output(dustTiny, IridiumChloride, 4)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(MediumNaquadah.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(400))
                .fluidOutputs(FCrackedMediumNaquadah.getFluid(1400))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(LightNaquadah.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(200))
                .fluidOutputs(FCrackedLightNaquadah.getFluid(1200))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(HeavyNaquadah.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(600))
                .fluidOutputs(FCrackedHeavyNaquadah.getFluid(1600))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(FCrackedLightNaquadah.getFluid(1200))
                .fluidOutputs(NaquadahGas.getFluid(350))
                .fluidOutputs(LightNaquadahFuel.getFluid(400))
                .fluidOutputs(MediumNaquadahFuel.getFluid(150))
                .fluidOutputs(HeavyNaquadahFuel.getFluid(50))
                .fluidOutputs(Fluorine.getFluid(150))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(FCrackedMediumNaquadah.getFluid(1400))
                .fluidOutputs(NaquadahGas.getFluid(150))
                .fluidOutputs(LightNaquadahFuel.getFluid(200))
                .fluidOutputs(MediumNaquadahFuel.getFluid(400))
                .fluidOutputs(HeavyNaquadahFuel.getFluid(100))
                .fluidOutputs(Fluorine.getFluid(350))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(FCrackedHeavyNaquadah.getFluid(1600))
                .fluidOutputs(NaquadahGas.getFluid(50))
                .fluidOutputs(LightNaquadahFuel.getFluid(100))
                .fluidOutputs(MediumNaquadahFuel.getFluid(200))
                .fluidOutputs(HeavyNaquadahFuel.getFluid(400))
                .fluidOutputs(Fluorine.getFluid(550))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // Cu + O + H2SO4 = [CuSO4 + H2O]
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Copper)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CopperSulfateSolution.getFluid(1000))
                .EUt(480)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CopperSulfateSolution.getFluid(900))
                .fluidInputs(NaquadahGas.getFluid(100))
                .input(dust, NaquadahEnriched)
                .fluidOutputs(ENaquadahSolution.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ENaquadahSolution.getFluid(2000))
                .output(dustTiny, PlatinumRawPowder, 3)
                .fluidOutputs(OsmiumSolution.getFluid(350))
                .output(dustTiny, NaquadahEnriched)
                .fluidOutputs(ClearENaquadahLiquid.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(ClearENaquadahLiquid.getFluid(1000))
                .fluidOutputs(ComplicatedNaquadahGas.getFluid(60))
                .fluidOutputs(ComplicatedLightENaquadah.getFluid(180))
                .fluidOutputs(ComplicatedMediumENaquadah.getFluid(320))
                .fluidOutputs(ComplicatedHeavyENaquadah.getFluid(440))
                .EUt(7680)
                .duration(500)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedHeavyENaquadah.getFluid(2000))
                .fluidOutputs(HeavyENaquadah.getFluid(1000))
                .output(dustTiny, Naquadria)
                .output(dustTiny, IridiumChloride, 8)
                .fluidOutputs(OsmiumSolution.getFluid(350))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedLightENaquadah.getFluid(2000))
                .fluidOutputs(LightENaquadah.getFluid(1000))
                .output(dustTiny, Naquadria)
                .output(dustTiny, IridiumChloride, 8)
                .fluidOutputs(OsmiumSolution.getFluid(350))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ComplicatedMediumENaquadah.getFluid(2000))
                .fluidOutputs(MediumENaquadah.getFluid(1000))
                .output(dustTiny, Naquadria)
                .output(dustTiny, IridiumChloride, 8)
                .fluidOutputs(OsmiumSolution.getFluid(350))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(MediumENaquadah.getFluid(1000))
                .fluidInputs(Radon.getFluid(400))
                .fluidOutputs(RnCrackedMediumENaquadah.getFluid(1400))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(LightENaquadah.getFluid(1000))
                .fluidInputs(Radon.getFluid(200))
                .fluidOutputs(RnCrackedLightNaquadah.getFluid(1200))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(HeavyENaquadah.getFluid(1000))
                .fluidInputs(Radon.getFluid(600))
                .fluidOutputs(RnCrackedHeavyENaquadah.getFluid(1600))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(RnCrackedLightNaquadah.getFluid(1200))
                .fluidOutputs(NaquadahGas.getFluid(350))
                .fluidOutputs(LightENaquadahFuel.getFluid(400))
                .fluidOutputs(MediumENaquadahFuel.getFluid(150))
                .fluidOutputs(HeavyENaquadahFuel.getFluid(50))
                .fluidOutputs(Radon.getFluid(180))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(RnCrackedMediumENaquadah.getFluid(1400))
                .fluidOutputs(NaquadahGas.getFluid(150))
                .fluidOutputs(LightENaquadahFuel.getFluid(200))
                .fluidOutputs(MediumENaquadahFuel.getFluid(400))
                .fluidOutputs(HeavyENaquadahFuel.getFluid(100))
                .fluidOutputs(Radon.getFluid(350))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(RnCrackedHeavyENaquadah.getFluid(1600))
                .fluidOutputs(NaquadahGas.getFluid(50))
                .fluidOutputs(LightENaquadahFuel.getFluid(100))
                .fluidOutputs(MediumENaquadahFuel.getFluid(200))
                .fluidOutputs(HeavyENaquadahFuel.getFluid(400))
                .fluidOutputs(Radon.getFluid(570))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Naquadria)
                .fluidInputs(NitrogenDioxide.getFluid(500))
                .fluidInputs(SulfuricAcid.getFluid(500))
                .fluidOutputs(NaquadriaSolution.getFluid(1000))
                .output(dustTiny, Lutetium)
                .output(dustTiny, Uranium)
                .output(dustTiny, NaquadahEnriched)
                .output(dustTiny, Plutonium)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LightNaquadahFuel.getFluid(500))
                .fluidInputs(LightENaquadahFuel.getFluid(300))
                .fluidInputs(NaquadriaSolution.getFluid(200))
                .input(dust, Rutherfordium)
                .fluidInputs(Plutonium244Isotope.getMaterial().getFluid(144))
                .fluidOutputs(HyperFuelI.getFluid(2000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(MediumNaquadahFuel.getFluid(400))
                .fluidInputs(MediumENaquadahFuel.getFluid(350))
                .fluidInputs(NaquadriaSolution.getFluid(250))
                .input(dust, Dubnium)
                .fluidInputs(Curium250.getMaterial().getFluid(144))
                .fluidOutputs(HyperFuelII.getFluid(2000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HeavyNaquadahFuel.getFluid(300))
                .fluidInputs(HeavyENaquadahFuel.getFluid(400))
                .fluidInputs(NaquadriaSolution.getFluid(300))
                .input(dust, Adamantium)
                .fluidInputs(Californium256.getMaterial().getFluid(144))
                .fluidOutputs(HyperFuelIII.getFluid(2000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HyperFuelIII.getFluid(1000))
                .input(dust, Neutronium)
                .input(dust, Taranium)
                .fluidOutputs(HyperFuelIV.getFluid(3000))
                .EUt(8000000)
                .duration(1)
                .buildAndRegister();
    }
}
