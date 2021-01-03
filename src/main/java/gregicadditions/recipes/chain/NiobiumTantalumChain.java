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
                .outputs(ManganeseSulfate.getItemStack(2))
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(1500)
                .input(dust, Rhodocrosite)
                .input(dustTiny, Sugar)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1111))
                .outputs(ManganeseSulfate.getItemStack(2))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(ManganeseSulfate.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Manganese))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(340).EUt(420)
                .input(dust, Cassiterite)
                .input(dust, Coke)
                .outputs(CassiteriteCokePellets.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(1300).blastFurnaceTemp(1600)
                .inputs(CassiteriteCokePellets.getItemStack(2))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Tin))
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
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Ferrosilite, 2))
                .outputs(NiobiumTantalumOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(2400)
                .fluidInputs(HydrofluoricAcid.getFluid(12000))
                .inputs(NiobiumTantalumOxide.getItemStack())
                .fluidOutputs(SiliconFluoride.getFluid(1000))
                .outputs(AluminiumTrifluoride.getItemStack())
                .fluidOutputs(NbTaFluorideMix.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(230).EUt(1300).blastFurnaceTemp(2300)
                .inputs(BariumOxide.getItemStack())
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(BariumPeroxide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Columbite, 2)
                .inputs(BariumPeroxide.getItemStack())
                .input(dust, SodiumHydroxide)
                .fluidInputs(Water.getFluid(1000))
                .outputs(FusedColumbite.getItemStack(2))
                .outputs(ColumbiteMinorOxideResidue.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedColumbite.getItemStack(3))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(LeachedColumbite.getItemStack(2))
                .outputs(IronSulfateDust.getItemStack(2))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(500)
                .inputs(IronSulfateDust.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Iron))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(ColumbiteMinorOxideResidue.getItemStack(3))
                .outputs(BariumOxide.getItemStack(1))
                .chancedOutput(OreDictUnifier.get(dust, Cassiterite), 3500, 200)
                .chancedOutput(OreDictUnifier.get(dust, TungstenTrioxide), 3500, 200)
                .chancedOutput(Alumina.getItemStack(), 3500, 200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedColumbite.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(FluoroniobicAcid.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1000).blastFurnaceTemp(3400)
                .input(dust, Tantalite, 2)
                .inputs(BariumPeroxide.getItemStack())
                .input(dust, SodiumHydroxide)
                .fluidInputs(Water.getFluid(1000))
                .outputs(FusedTantalite.getItemStack(2))
                .outputs(TantaliteMinorOxideResidue.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(FusedTantalite.getItemStack(3))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(LeachedTantalite.getItemStack(2))
                .outputs(ManganeseSulfate.getItemStack(2))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(260).EUt(850)
                .inputs(TantaliteMinorOxideResidue.getItemStack(3))
                .outputs(BariumOxide.getItemStack(1))
                .chancedOutput(OreDictUnifier.get(dust, CubicZirconia), 3500, 200)
                .chancedOutput(OreDictUnifier.get(dust, Rutile), 3500, 200)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide), 3500, 200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedTantalite.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(FluorotantalicAcid.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(220).EUt(1500)
                .fluidInputs(TributylPhosphate.getFluid(1000))
                .fluidInputs(MethylIsobutylKetone.getFluid(1000))
                .fluidOutputs(NbTaSeparationMixture.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(1850).blastFurnaceTemp(2700)
                .input(dust, Pyrochlore, 3)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .outputs(AcidicLeachedPyrochlore.getItemStack(2))
                .fluidOutputs(REEThUSulfateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .inputs(AcidicLeachedPyrochlore.getItemStack(2))
                .fluidInputs(Water.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(3000))
                .fluidOutputs(AlkalineEarthSulfateSolution.getFluid(2000))
                .outputs(LeachedPyrochlore.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1230)
                .inputs(LeachedPyrochlore.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(FluoroniobicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(680)
                .fluidInputs(REEThUSulfateSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(1000))
                .outputs(UranylThoriumNitrate.getItemStack())
                .fluidOutputs(RareEarthNitrateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(750)
                .fluidInputs(RareEarthNitrateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(380).EUt(1600)
                .fluidInputs(AlkalineEarthSulfateSolution.getFluid(6000))
                .fluidOutputs(SulfuricAcid.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, Barite))
                .outputs(OreDictUnifier.get(dust, Gypsum))
                .outputs(StrontiumSulfate.getItemStack())
                .outputs(OreDictUnifier.get(dustTiny, Radium))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluoroniobicAcid.getFluid(3000))
                .fluidInputs(NbTaSeparationMixture.getFluid(400))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(2700))
                .fluidOutputs(HeptafluoroTantalate.getFluid(300))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(NbTaFluorideMix.getFluid(3000))
                .fluidInputs(NbTaSeparationMixture.getFluid(400))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(1500))
                .fluidOutputs(HeptafluoroTantalate.getFluid(1500))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1900)
                .fluidInputs(FluorotantalicAcid.getFluid(3000))
                .fluidInputs(NbTaSeparationMixture.getFluid(400))
                .fluidOutputs(OxypentafluoroNiobate.getFluid(300))
                .fluidOutputs(HeptafluoroTantalate.getFluid(2700))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(OxypentafluoroNiobate.getFluid(1000))
                .input(dust, PotassiumFluoride, 2)
                .outputs(PotasssiumFluoroNiobate.getItemStack(1))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1400)
                .fluidInputs(HeptafluoroTantalate.getFluid(1000))
                .input(dust, PotassiumFluoride, 2)
                .outputs(PotasssiumFluoroTantalate.getItemStack(1))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroNiobate.getItemStack())
                .fluidInputs(Sodium.getFluid(720))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 5))
                .outputs(OreDictUnifier.get(dust, PotassiumFluoride, 2))
                .outputs(OreDictUnifier.get(dust, Niobium))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(360).EUt(1900)
                .inputs(PotasssiumFluoroTantalate.getItemStack())
                .fluidInputs(Sodium.getFluid(720))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 5))
                .outputs(OreDictUnifier.get(dust, PotassiumFluoride, 2))
                .outputs(OreDictUnifier.get(dust, Tantalum))
                .buildAndRegister();
    }
}
