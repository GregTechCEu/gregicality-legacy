package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class NiobiumTantalumChain {
    public static void init() {

        // MnO2 + sugar(tiny) -> H2SO4 + CO2 + MnSO4
        // weird, but probably fine
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(1500)
                .input(dust, Pyrolusite, 3)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(111))
                .outputs(ManganeseSulfate.getItemStack(6))
                .buildAndRegister();

        // MnCO3 + sugar(tiny) -> H2SO4 + CO2 + MnSO4
        // weird, but probably fine
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(1500)
                .input(dust, Rhodocrosite, 5)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1111))
                .outputs(ManganeseSulfate.getItemStack(6))
                .buildAndRegister();

        // MnSO4 -> Mn + S + 4O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(ManganeseSulfate.getItemStack(6))
                .output(dust, Manganese)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        // SnO2 + C -> H2O? + CokePellets
        MIXER_RECIPES.recipeBuilder().duration(340).EUt(420)
                .input(dust, Cassiterite, 2)
                .input(dust, Coke)
                .fluidInputs(DistilledWater.getFluid(1000))
                .outputs(CassiteriteCokePellets.getItemStack(5))
                .buildAndRegister();

        // CokePellets -> CO2 + 2Sn + slag
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(1300).blastFurnaceTemp(1600)
                .inputs(CassiteriteCokePellets.getItemStack(5))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(ingot, Tin, 2)
                .outputs(TinSlag.getItemStack())
                .buildAndRegister();

        // 6slag -> ash + ?
        CENTRIFUGE_RECIPES.recipeBuilder().duration(180).EUt(500)
                .inputs(TinSlag.getItemStack(6))
                .output(dust, DarkAsh)
                .outputs(NbTaContainingDust.getItemStack(4))
                .buildAndRegister();

        // ? + C + SiO2 -> CO2 + FeSiO3 + (Nb2O5)(Ta2O5)
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(1300).blastFurnaceTemp(1600)
                .inputs(NbTaContainingDust.getItemStack(4))
                .input(dust, Carbon)
                .input(dust, SiliconDioxide, 3)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(ingot, Ferrosilite, 5)
                .outputs(NiobiumTantalumOxide.getItemStack(14))
                .buildAndRegister();

        // 19HF + (Nb2O5)(Ta2O5) -> AlF3 + SiF4 + ? + 7.5H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2400)
                .fluidInputs(HydrofluoricAcid.getFluid(19000))
                .inputs(NiobiumTantalumOxide.getItemStack(14))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(NbTaFluorideMix.getFluid(1000))
                .fluidOutputs(Water.getFluid(7500))
                .buildAndRegister();

        // BaO + O -> BaO2
        BLAST_RECIPES.recipeBuilder().duration(230).EUt(1300).blastFurnaceTemp(2300)
                .inputs(BariumOxide.getItemStack(2))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(BariumPeroxide.getItemStack(3))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Columbite)
                .inputs(BariumPeroxide.getItemStack(3))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(500))
                .outputs(FusedColumbite.getItemStack(9))
                .outputs(ColumbiteMinorOxideResidue.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedColumbite.getItemStack(9))
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .outputs(LeachedColumbite.getItemStack(17))
                .outputs(IronSulfateDust.getItemStack(12))
                .output(dust, SodiumSulfate, 14)
                .buildAndRegister();

        // FeSO4 -> Fe + S + 4O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(IronSulfateDust.getItemStack(6))
                .output(dust, Iron)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(ColumbiteMinorOxideResidue.getItemStack(4))
                .outputs(BariumOxide.getItemStack(2))
                .chancedOutput(OreDictUnifier.get(dust, Cassiterite, 2), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, TungstenTrioxide, 4), 1000, 75)
                .chancedOutput(Alumina.getItemStack(5), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedColumbite.getItemStack(17))
                .fluidInputs(HydrofluoricAcid.getFluid(10400))
                .fluidOutputs(FluoroniobicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3200))
                .buildAndRegister();

        // MnTa2O6 + BaO2 + NaOH -> 0.5H2O + (Fe2O3)(NaO)Ta2O5 + (BaO)(ZrO2)(TiO2)(SiO2)
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Tantalite, 5)
                .inputs(BariumPeroxide.getItemStack(3))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(1000))
                .outputs(FusedTantalite.getItemStack(9))
                .outputs(TantaliteMinorOxideResidue.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedTantalite.getItemStack(9))
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .outputs(LeachedTantalite.getItemStack(17))
                .output(dust, SodiumSulfate, 14)
                .outputs(ManganeseSulfate.getItemStack(12))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(TantaliteMinorOxideResidue.getItemStack(4))
                .outputs(BariumOxide.getItemStack(2))
                .chancedOutput(OreDictUnifier.get(dust, CubicZirconia, 3), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, Rutile, 3), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide, 3), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedTantalite.getItemStack(17))
                .fluidInputs(HydrofluoricAcid.getFluid(13600))
                .fluidOutputs(FluorotantalicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(4800))
                .buildAndRegister();

        // C12H27O4P + C6H12O -> C18H39O5P
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(1500)
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .fluidInputs(MethylIsobutylKetone.getFluid(1000))
                .fluidOutputs(NbTaSeparationMixture.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(1850).blastFurnaceTemp(2700)
                .input(dust, Pyrochlore, 6)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .outputs(AcidicLeachedPyrochlore.getItemStack(6))
                .fluidOutputs(REEThUSulfateSolution.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(AcidicLeachedPyrochlore.getItemStack(3))
                .fluidInputs(HydrogenPeroxide.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(12000))
                .fluidOutputs(HydrofluoricAcid.getFluid(13000))
                .fluidOutputs(AlkalineEarthSulfateSolution.getFluid(8000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .outputs(LeachedPyrochlore.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedPyrochlore.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(10400))
                .fluidOutputs(FluoroniobicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3200))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(680)
                .fluidInputs(REEThUSulfateSolution.getFluid(2000))
                .fluidInputs(NitricAcid.getFluid(7000))
                .outputs(UranylThoriumNitrate.getItemStack(26))
                .fluidOutputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(750)
                .fluidInputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(3000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .buildAndRegister();

        // TODO This recipe may be really mean
        CENTRIFUGE_RECIPES.recipeBuilder().duration(380).EUt(1600)
                .fluidInputs(AlkalineEarthSulfateSolution.getFluid(4000))
                .fluidOutputs(Water.getFluid(4000))
                .output(dust, Barite, 3)
                .output(dust, Gypsum, 8)
                .outputs(StrontiumSulfate.getItemStack(6))
                .output(dustTiny, Radium, 3)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluoroniobicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(9000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        // (H2NbOF5 + H2TaF7) -> H2NbOF5 + H2TaF7
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(NbTaFluorideMix.getFluid(2000))
                .fluidInputs(NbTaSeparationMixture.getFluid(200))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        // 10HTaF7 -> H2NbOF5 + 9H2TaF7
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluorotantalicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(9000))
                .buildAndRegister();

        // H2NbOF5 + 2KF -> K2NbF7 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(OxypentafluoroNiobate.getFluid(1000))
                .input(dust, PotassiumFluoride, 4)
                .outputs(PotasssiumFluoroNiobate.getItemStack(10))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // H2TaF7 + 2KOH -> K2TaF7 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(HeptafluoroTantalate.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .outputs(PotasssiumFluoroTantalate.getItemStack(10))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // K2NbF7 + 5Na -> 5NaF + 2KF + Nb
        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroNiobate.getItemStack(10))
                .fluidInputs(Sodium.getFluid(5000))
                .output(dust, SodiumFluoride, 10)
                .output(dust, PotassiumFluoride, 4)
                .output(dust, Niobium)
                .buildAndRegister();

        // K2TaF7 + 5Na -> 5NaF + 2KF + Ta
        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroTantalate.getItemStack(10))
                .fluidInputs(Sodium.getFluid(5000))
                .output(dust, SodiumFluoride, 10)
                .output(dust, PotassiumFluoride, 4)
                .output(dust, Tantalum)
                .buildAndRegister();
    }
}
