package gregicadditions.recipes.chain.optical;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class Lasers {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2500))
                .notConsumable(new IntCircuitIngredient(4))
                .fluidOutputs(ChlorinatedSolvents.getFluid(3500))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(280).EUt(1100)
                .fluidInputs(ChlorinatedSolvents.getFluid(1000))
                .fluidOutputs(Chloromethane.getFluid(190))
                .fluidOutputs(Dichloromethane.getFluid(310))
                .fluidOutputs(Chloroform.getFluid(310))
                .fluidOutputs(CarbonTetrachloride.getFluid(190))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(130).EUt(1700)
                .fluidInputs(Butanol.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(ButanolGas.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(7400)
                .fluidInputs(ButanolGas.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .notConsumable(dust,Zeolite)
                .fluidOutputs(Tributylamine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(6400)
                .input(dust,Aluminium)
                .fluidInputs(NitricAcid.getFluid(1500))
                .outputs(AluminiumNitrate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(12000)
                .inputs(AluminiumNitrate.getItemStack())
                .fluidInputs(Dichloromethane.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidOutputs(CrudeAluminaSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(7500)
                .fluidInputs(CrudeAluminaSolution.getFluid(2000))
                .fluidInputs(Tributylamine.getFluid(1000))
                .fluidOutputs(AluminaSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(6400)
                .input(dust,YttriumOxide,9)
                .inputs(NeodymiumOxide.getItemStack())
                .outputs(NeodymiumDopedYttrium.getItemStack(10))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(3400)
                .fluidInputs(Ammonia.getFluid(2500))
                .fluidInputs(HydrogenCyanide.getFluid(2500))
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .inputs(PotassiumPermanganate.getItemStack(2))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(ManganeseSulfate.getItemStack())
                .outputs(PotassiumSulfate.getItemStack(2))
                .fluidOutputs(AmmoniumCyanate.getFluid(5000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(1600)
                .fluidInputs(AmmoniumCyanate.getFluid(1000))
                .outputs(Urea.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(240).blastFurnaceTemp(720)
                .inputs(PotassiumManganate.getItemStack(3))
                .fluidInputs(Water.getFluid(4000))
                .outputs(OreDictUnifier.get(dust, Pyrolusite, 1))
                .outputs(PotassiumPermanganate.getItemStack(2))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(32000)
                .fluidInputs(AluminaSolution.getFluid(1000))
                .inputs(NeodymiumDopedYttrium.getItemStack())
                .inputs(Urea.getItemStack())
                .fluidOutputs(UnprocessedNdYAGSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(16000)
                .fluidInputs(UnprocessedNdYAGSolution.getFluid(1000))
                .outputs(UnprocessedNdYAGDust.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(24500).blastFurnaceTemp(300)
                .inputs(UnprocessedNdYAGDust.getItemStack())
                .outputs(NdYAGNanoparticles.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidInputs(NdYAG.getFluid(18))
                .outputs(NDYAG_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(NDYAG_BOULE.getStackForm())
                .outputs(NDYAG_ROD.getStackForm(2))
                .outputs(OreDictUnifier.get(dustTiny,NdYAG))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidOutputs(NdYAG.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, NdYAG)
                .fluidOutputs(NdYAG.getFluid(18))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(21000)
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Ethylenediamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .notConsumable(dust,Palladium)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(24000)
                .fluidInputs(Ethylenediamine.getFluid(1000))
                .fluidInputs(Formaldehyde.getFluid(4000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .fluidInputs(SodiumCyanide.getFluid(4000))
                .fluidOutputs(EDTASolution.getFluid(13000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(17500)
                .fluidInputs(EDTASolution.getFluid(13000))
                .outputs(OreDictUnifier.get(dust,Salt,4))
                .fluidOutputs(Ammonia.getFluid(4100))
                .fluidOutputs(EDTA.getFluid(4100))
                .fluidOutputs(Glycine.getFluid(800))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(8000)
                .notConsumable(WHITE_HALIDE_LAMP.getStackForm())
                .fluidInputs(Glycine.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .inputs(CesiumHydroxide.getItemStack())
                .outputs(CesiumBromide.getItemStack())
                .outputs(NMethylglicine.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2000)
                .input(dust,Caesium)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(CesiumHydroxide.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(CesiumBromide.getItemStack())
                .fluidOutputs(Bromine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust,Caesium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Praseodymium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(PraseodymiumOxide.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Holmium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(HolmiumOxide.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(280).EUt(18000)
                .input(dust,YttriumOxide,14)
                .inputs(PraseodymiumOxide.getItemStack())
                .inputs(HolmiumOxide.getItemStack())
                .fluidInputs(NitricAcid.getFluid(24000))
                .fluidOutputs(PrYHoNitrateSolution.getFluid(16000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30000)
                .input(dust,LithiumFluoride)
                .inputs(AmmoniumBifluoride.getItemStack(2))
                .fluidInputs(PrYHoNitrateSolution.getFluid(1000))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(20))
                .fluidInputs(EDTA.getFluid(60))
                .outputs(PrHoYLFNanoparticles.getItemStack())
                .fluidOutputs(AmmoniaNitrate.getFluid(1000))
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
                .outputs(OreDictUnifier.get(dustTiny,PrHoYLF))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(PrHoYLFNanoparticles.getItemStack())
                .fluidOutputs(PrHoYLF.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, PrHoYLF)
                .fluidOutputs(PrHoYLF.getFluid(18))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(4000)
                .inputs(AmmoniumVanadate.getItemStack())
                .input(dust,Salt)
                .outputs(PureSodiumVanadate.getItemStack())
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(280).EUt(18000)
                .input(dust,YttriumOxide,14)
                .inputs(LutetiumOxide.getItemStack())
                .inputs(ThuliumOxide.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(24000))
                .fluidOutputs(LuTmYChlorideSolution.getFluid(16000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(32000)
                .fluidInputs(LuTmYChlorideSolution.getFluid(1000))
                .inputs(PureSodiumVanadate.getItemStack())
                .inputs(Urea.getItemStack())
                .outputs(LuTmYVONanoparticles.getItemStack())
                .buildAndRegister();

        ORE_WASHER_RECIPES.recipeBuilder().duration(200).EUt(13500)
                .inputs(LuTmYVOPrecipitate.getItemStack())
                .fluidInputs(Ethanol100.getFluid(1000))
                .outputs(LuTmYVONanoparticles.getItemStack())
                .outputs(OreDictUnifier.get(dust,Salt))
                .outputs(AmmoniumCarbonate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(750).blastFurnaceTemp(700)
                .inputs(AmmoniumCarbonate.getItemStack())
                .input(dust,Gypsum)
                .fluidOutputs(AmmoniumSulfate.getFluid(2000))
                .outputs(OreDictUnifier.get(dust,Calcite))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(260).EUt(2000)
                .fluidInputs(AmmoniumSulfate.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(500))
                .fluidOutputs(SulfuricAcid.getFluid(500))
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
                .outputs(OreDictUnifier.get(dustTiny,LuTmYVO))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .inputs(LuTmYVONanoparticles.getItemStack())
                .fluidOutputs(LuTmYVO.getFluid(144))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(3200)
                .input(dustTiny, LuTmYVO)
                .fluidOutputs(LuTmYVO.getFluid(18))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(2000)
                .input(dust,Silicon)
                .fluidInputs(Fluorine.getFluid(4000))
                .fluidOutputs(SiliconFluoride.getFluid(5000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1300)
                .fluidInputs(SiliconFluoride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(940)
                .fluidInputs(FluorosilicicAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(6000))
                .fluidInputs(Water.getFluid(2000))
                .outputs(OreDictUnifier.get(dust,SiliconDioxide))
                .fluidOutputs(AmmoniumFluoride.getFluid(6000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(340).EUt(1600)
                .fluidInputs(AmmoniumFluoride.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .outputs(AmmoniumBifluoride.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(125)
                .fluidInputs(DistilledWater.getFluid(1000))
                .inputs(AmmoniumBifluoride.getItemStack())
                .fluidOutputs(AmmoniumBifluorideSolution.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(260).EUt(1300)
                .fluidInputs(AmmoniumBifluorideSolution.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(DistilledWater.getFluid(1000))
                .buildAndRegister();
    }
}
