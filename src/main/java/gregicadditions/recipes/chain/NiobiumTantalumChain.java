package gregicadditions.recipes.chain;

import gregicadditions.GAMaterials;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class NiobiumTantalumChain {
    public static void init() {

        // MnO2 + sugar(tiny) -> H2SO4 + CO2 + MnSO4
        // weird, but probably fine
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(1500)
                .input(dust, Pyrolusite, 3)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(111))
                .output(dust, ManganeseSulfate, 6)
                .buildAndRegister();

        // MnCO3 + sugar(tiny) -> H2SO4 + CO2 + MnSO4
        // weird, but probably fine
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(1500)
                .input(dust, Rhodochrosite, 5)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1111))
                .output(dust, ManganeseSulfate, 6)
                .buildAndRegister();

        // SnO2 + C -> H2O? + CokePellets
        MIXER_RECIPES.recipeBuilder().duration(340).EUt(30)
                .input(dust, Cassiterite, 2)
                .input(dust, Coke)
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, CassiteriteCokePellets, 5)
                .buildAndRegister();

        // CokePellets -> CO2 + 2Sn + slag
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(1600)
                .input(dust, CassiteriteCokePellets, 5)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(ingot, Tin, 2)
                .output(dust, TinSlag)
                .buildAndRegister();

        // 6slag -> ash + ?
        CENTRIFUGE_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, TinSlag, 6)
                .output(dust, DarkAsh)
                .output(dust, NbTaContainingDust, 4)
                .buildAndRegister();

        // ? + C + SiO2 -> CO2 + FeSiO3 + (Nb2O5)(Ta2O5)
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(1600)
                .input(dust, NbTaContainingDust, 4)
                .input(dust, Carbon)
                .input(dust, SiliconDioxide, 3)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(ingot, Ferrosilite, 5)
                .output(dust, NiobiumTantalumOxide, 14)
                .buildAndRegister();

        // 19HF + (Nb2O5)(Ta2O5) -> AlF3 + SiF4 + ? + 7.5H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(1920)
                .fluidInputs(HydrofluoricAcid.getFluid(19000))
                .input(dust, NiobiumTantalumOxide, 14)
                .output(dust, AluminiumTrifluoride, 4)
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(NbTaFluorideMix.getFluid(1000))
                .fluidOutputs(Water.getFluid(7500))
                .buildAndRegister();

        // BaO + O -> BaO2
        BLAST_RECIPES.recipeBuilder().duration(230).EUt(120).blastFurnaceTemp(2300)
                .input(dust, BariumOxide, 2)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, BariumPeroxide, 3)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(3400)
                .input(dust, Columbite)
                .input(dust, BariumPeroxide, 3)
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(500))
                .output(dust, FusedColumbite, 9)
                .output(dust, ColumbiteMinorOxideResidue, 4)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(30)
                .input(dust, FusedColumbite, 9)
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .output(dust, LeachedColumbite, 17)
                .output(dust, IronSulfate, 12)
                .output(dust, SodiumSulfate, 14)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, ColumbiteMinorOxideResidue, 4)
                .output(dust, BariumOxide, 2)
                .chancedOutput(OreDictUnifier.get(dust, Cassiterite, 2), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, TungstenTrioxide, 4), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, Alumina, 5), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .input(dust, LeachedColumbite, 17)
                .fluidInputs(HydrofluoricAcid.getFluid(10400))
                .fluidOutputs(FluoroniobicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3200))
                .buildAndRegister();

        // MnTa2O6 + BaO2 + NaOH -> 0.5H2O + (Fe2O3)(NaO)Ta2O5 + (BaO)(ZrO2)(TiO2)(SiO2)
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(3400)
                .input(dust, Tantalite, 5)
                .input(dust, BariumPeroxide, 3)
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, FusedTantalite, 9)
                .output(dust, TantaliteMinorOxideResidue, 4)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, FusedTantalite, 9)
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .output(dust, LeachedTantalite, 17)
                .output(dust, SodiumSulfate, 14)
                .output(dust, ManganeseSulfate, 12)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(480)
                .input(dust, TantaliteMinorOxideResidue, 4)
                .output(dust, BariumOxide, 2)
                .chancedOutput(OreDictUnifier.get(dust, CubicZirconia, 3), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, Rutile, 3), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide, 3), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, LeachedTantalite, 17)
                .fluidInputs(HydrofluoricAcid.getFluid(13600))
                .fluidOutputs(FluorotantalicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(4800))
                .buildAndRegister();

        // C12H27O4P + C6H12O -> C18H39O5P
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(30)
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .fluidInputs(MethylIsobutylKetone.getFluid(1000))
                .fluidOutputs(NbTaSeparationMixture.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(480).blastFurnaceTemp(2700)
                .input(dust, Pyrochlore, 6)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust, AcidicLeachedPyrochlore, 6)
                .fluidOutputs(REEThUSulfateSolution.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .input(dust, AcidicLeachedPyrochlore, 3)
                .fluidInputs(HydrogenPeroxide.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(12000))
                .fluidOutputs(HydrofluoricAcid.getFluid(13000))
                .fluidOutputs(AlkalineEarthSulfateSolution.getFluid(8000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .output(dust, LeachedPyrochlore, 3)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, LeachedPyrochlore)
                .fluidInputs(HydrofluoricAcid.getFluid(10400))
                .fluidOutputs(FluoroniobicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3200))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .fluidInputs(REEThUSulfateSolution.getFluid(2000))
                .fluidInputs(NitricAcid.getFluid(7000))
                .output(dust, UranylThoriumNitrate, 26)
                .fluidOutputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(3000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(1920)
                .fluidInputs(AlkalineEarthSulfateSolution.getFluid(4000))
                .fluidOutputs(Water.getFluid(4000))
                .output(dust, Barite, 3)
                .output(dust, Gypsum, 8)
                .output(dust, Celestine, 6)
                .output(dustSmall, Radium, 2)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(7680)
                .fluidInputs(FluoroniobicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(9000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        // (H2NbOF5 + H2TaF7) -> H2NbOF5 + H2TaF7
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(7680)
                .fluidInputs(NbTaFluorideMix.getFluid(2000))
                .fluidInputs(NbTaSeparationMixture.getFluid(200))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        // 10HTaF7 -> H2NbOF5 + 9H2TaF7
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(7680)
                .fluidInputs(FluorotantalicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(9000))
                .buildAndRegister();

        // H2NbOF5 + 2KF -> K2NbF7 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(120)
                .fluidInputs(OxypentafluoroNiobate.getFluid(1000))
                .input(dust, PotassiumFluoride, 4)
                .output(dust, PotassiumFluoroNiobate, 10)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // H2TaF7 + 2KOH -> K2TaF7 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(120)
                .fluidInputs(HeptafluoroTantalate.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .output(dust, PotasssiumFluoroTantalate, 10)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // K2NbF7 + 5Na -> 5NaF + 2KF + Nb
        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, PotassiumFluoroNiobate, 10)
                .fluidInputs(Sodium.getFluid(5000))
                .output(dust, SodiumFluoride, 10)
                .output(dust, PotassiumFluoride, 4)
                .output(dust, Niobium)
                .buildAndRegister();

        // K2TaF7 + 5Na -> 5NaF + 2KF + Ta
        ELECTROLYZER_RECIPES.recipeBuilder().duration(170).EUt(120)
                .input(dust, PotasssiumFluoroTantalate, 10)
                .fluidInputs(Sodium.getFluid(5000))
                .output(dust, SodiumFluoride, 10)
                .output(dust, PotassiumFluoride, 4)
                .output(dust, Tantalum)
                .buildAndRegister();
    }
}
