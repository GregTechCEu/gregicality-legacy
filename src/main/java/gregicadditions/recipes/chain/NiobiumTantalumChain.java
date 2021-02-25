package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class NiobiumTantalumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(1500)
                .input(dust, Pyrolusite)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(111))
                .outputs(ManganeseSulfate.getItemStack(6))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(1500)
                .input(dust, Rhodocrosite)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1111))
                .outputs(ManganeseSulfate.getItemStack(6))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(ManganeseSulfate.getItemStack(6))
                .outputs(OreDictUnifier.get(dust, Manganese))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(340).EUt(420)
                .input(dust, Cassiterite, 2)
                .input(dust, Coke)
                .fluidInputs(DistilledWater.getFluid(1000))
                .outputs(CassiteriteCokePellets.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(1300).blastFurnaceTemp(1600)
                .inputs(CassiteriteCokePellets.getItemStack())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Tin, 2))
                .outputs(TinSlag.getItemStack())
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(180).EUt(500)
                .inputs(TinSlag.getItemStack(6))
                .outputs(OreDictUnifier.get(dust, DarkAsh))
                .outputs(NbTaContainingDust.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(1300).blastFurnaceTemp(1600)
                .inputs(NbTaContainingDust.getItemStack())
                .input(dust, Carbon)
                .input(dust, SiliconDioxide, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Ferrosilite, 2))
                .outputs(NiobiumTantalumOxide.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2400)
                .fluidInputs(HydrofluoricAcid.getFluid(19000))
                .inputs(NiobiumTantalumOxide.getItemStack())
                .outputs(AluminiumTrifluoride.getItemStack())
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .fluidOutputs(NbTaFluorideMix.getFluid(1000))
                .fluidOutputs(Water.getFluid(7500))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(230).EUt(1300).blastFurnaceTemp(2300)
                .inputs(BariumOxide.getItemStack())
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(BariumPeroxide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Columbite)
                .inputs(BariumPeroxide.getItemStack())
                .input(dust, SodiumHydroxide)
                .fluidOutputs(Water.getFluid(500))
                .outputs(FusedColumbite.getItemStack())
                .outputs(ColumbiteMinorOxideResidue.getItemStack())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedColumbite.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .outputs(LeachedColumbite.getItemStack())
                .outputs(IronSulfateDust.getItemStack(12))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 14))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(IronSulfateDust.getItemStack(6))
                .outputs(OreDictUnifier.get(dust, Iron))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(ColumbiteMinorOxideResidue.getItemStack())
                .outputs(BariumOxide.getItemStack())
                .chancedOutput(OreDictUnifier.get(dust, Cassiterite), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, TungstenTrioxide), 1000, 75)
                .chancedOutput(Alumina.getItemStack(), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedColumbite.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(10400))
                .fluidOutputs(FluoroniobicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3200))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Tantalite)
                .inputs(BariumPeroxide.getItemStack())
                .input(dust, SodiumHydroxide)
                .fluidOutputs(Water.getFluid(500))
                .outputs(FusedTantalite.getItemStack())
                .outputs(TantaliteMinorOxideResidue.getItemStack())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedTantalite.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(4000))
                .outputs(LeachedTantalite.getItemStack())
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 14))
                .outputs(ManganeseSulfate.getItemStack(12))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(TantaliteMinorOxideResidue.getItemStack())
                .outputs(BariumOxide.getItemStack())
                .chancedOutput(OreDictUnifier.get(dust, CubicZirconia), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, Rutile), 1000, 75)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide), 1000, 75)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedTantalite.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(13600))
                .fluidOutputs(FluorotantalicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(4800))
                .buildAndRegister();


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
                .outputs(UranylThoriumNitrate.getItemStack())
                .fluidOutputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(750)
                .fluidInputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(3000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(380).EUt(1600)
                .fluidInputs(AlkalineEarthSulfateSolution.getFluid(8000))
                .fluidOutputs(Water.getFluid(8000))
                .outputs(OreDictUnifier.get(dust, Barite, 3))
                .outputs(OreDictUnifier.get(dust, Gypsum, 6))
                .outputs(StrontiumSulfate.getItemStack(3))
                .outputs(OreDictUnifier.get(dustTiny, Radium, 3))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluoroniobicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(9000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(NbTaFluorideMix.getFluid(2000))
                .fluidInputs(NbTaSeparationMixture.getFluid(200))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluorotantalicAcid.getFluid(10000))
                .fluidInputs(NbTaSeparationMixture.getFluid(1000))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1000))
                .fluidOutputs(HeptafluoroTantalate.getFluid(9000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(OxypentafluoroNiobate.getFluid(1000))
                .input(dust, PotassiumFluoride, 2)
                .outputs(PotasssiumFluoroNiobate.getItemStack(10))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(HeptafluoroTantalate.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .outputs(PotasssiumFluoroTantalate.getItemStack(10))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroNiobate.getItemStack(10))
                .fluidInputs(Sodium.getFluid(720))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 5))
                .outputs(OreDictUnifier.get(dust, PotassiumFluoride, 2))
                .outputs(OreDictUnifier.get(dust, Niobium))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroTantalate.getItemStack(10))
                .fluidInputs(Sodium.getFluid(720))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 5))
                .outputs(OreDictUnifier.get(dust, PotassiumFluoride, 2))
                .outputs(OreDictUnifier.get(dust, Tantalum))
                .buildAndRegister();
    }
}
