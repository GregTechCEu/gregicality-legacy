package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;


public class TaraniumChain {
    public static void init(){
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Stone, 24)
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(DirtyHexafluorosilicicAcid.getFluid(3000))
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DirtyHexafluorosilicicAcid.getFluid(3000))
                .fluidOutputs(DiluteHexafluorosilicicAcid.getFluid(3000))
                .outputs(StoneResidueDust.getItemStack(12))
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(DiluteHexafluorosilicicAcid.getFluid(3000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .duration(160)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(StoneResidueDust.getItemStack(32))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(UncommonResidues.getItemStack())
                .fluidOutputs(SodiumHydroxideSolution.getFluid(900))
                .fluidOutputs(RedMud.getFluid(100))
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(LiquidOxygen.getFluid(2000))
                .fluidInputs(LiquidFluorine.getFluid(2000))
                .notConsumable(MICROFOCUS_X_RAY_TUBE.getStackForm())
                .fluidOutputs(Dioxygendifluoride.getFluid(1000))
                .duration(80)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(UncommonResidues.getItemStack())
                .fluidInputs(Dioxygendifluoride.getFluid(1000))
                .outputs(PartiallyOxidizedResidues.getItemStack())
                .duration(80)
                .EUt(100)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DistilledWater.getFluid(20000))
                .inputs(PartiallyOxidizedResidues.getItemStack(20))
                .fluidOutputs(OxidizedResidualSolution.getFluid(20000))
                .output(dustTiny, InertResidues)
                .duration(320)
                .EUt(100)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(OxidizedResidualSolution.getFluid(2000))
                .outputs(OxidizedResidues.getItemStack())
                .outputs(HeavyOxidizedResidues.getItemStack())
                .duration(80)
                .EUt(3000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .inputs(OxidizedResidues.getItemStack(10))
                .fluidInputs(Hydrogen.getFluid(6000))
                .outputs(MetallicResidues.getItemStack())
                .fluidOutputs(DiluteHydrofluoricAcid.getFluid(4000))
                .duration(1600)
                .EUt(2000)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .inputs(HeavyOxidizedResidues.getItemStack(10))
                .fluidInputs(Hydrogen.getFluid(6000))
                .outputs(HeavyMetallicResidues.getItemStack())
                .fluidOutputs(DiluteHydrofluoricAcid.getFluid(4000))
                .duration(1600)
                .EUt(2000)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(DiluteHydrofluoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(1000))
                .duration(80)
                .EUt(200)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(MetallicResidues.getItemStack(10))
                .notConsumable(SEPARATION_ELECTROMAGNET)
                .outputs(DiamagneticResidues.getItemStack(3))
                .outputs(ParamagneticResidues.getItemStack(3))
                .outputs(FerromagneticResidues.getItemStack(3))
                .outputs(UncommonResidues.getItemStack())
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(HeavyMetallicResidues.getItemStack(10))
                .notConsumable(SEPARATION_ELECTROMAGNET)
                .outputs(HeavyDiamagneticResidues.getItemStack(3))
                .outputs(HeavyParamagneticResidues.getItemStack(3))
                .outputs(HeavyFerromagneticResidues.getItemStack(3))
                .outputs(ExoticHeavyResidues.getItemStack())
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(FerromagneticResidues.getItemStack(30))
                .output(dustSmall, Iron)
                .output(dustSmall, Nickel)
                .output(dustSmall, Cobalt)
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(DiamagneticResidues.getItemStack(30))
                .output(dustSmall, Calcium)
                .output(dustSmall, Zinc)
                .output(dustSmall, Copper)
                .output(dustSmall, Gallium)
                .output(dustSmall, Beryllium)
                .output(dustSmall, Tin)
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(ParamagneticResidues.getItemStack(30))
                .output(dustSmall, Sodium)
                .output(dustSmall, Potassium)
                .output(dustSmall, Magnesium)
                .output(dustSmall, Titanium)
                .output(dustSmall, Vanadium)
                .output(dustSmall, Manganese)
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(HeavyParamagneticResidues.getItemStack(30))
                .output(dustSmall, ThoriumRadioactive.getMaterial())
                .output(dustSmall, UraniumRadioactive.getMaterial())
                .output(dustSmall, Tungsten)
                .output(dustSmall, Hafnium)
                .output(dustSmall, Tantalum)
                .output(dustSmall, Thallium)
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(HeavyDiamagneticResidues.getItemStack(30))
                .output(dustSmall, Lead)
                .output(dustSmall, Cadmium)
                .output(dustSmall, Indium)
                .output(dustSmall, Gold)
                .output(dustSmall, Bismuth)
                .fluidOutputs(Mercury.getFluid(36))
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(HeavyFerromagneticResidues.getItemStack(30))
                .output(dustSmall, Dysprosium)
                .duration(200)
                .EUt(3000)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(DistilledWater.getFluid(2000))
                .inputs(ExoticHeavyResidues.getItemStack(64))
                .input(dust, SodiumHydroxide, 3)
                .inputs(PROTONATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .duration(80)
                .EUt(500000)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, InertResidues, 10)
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .outputs(CleanInertResidues.getItemStack(10))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .fluidOutputs(FluoronaquadricAcid.getFluid(1000))
                .duration(320)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Tritium.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(TritiumHydride.getFluid(1000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(TritiumHydride.getFluid(10000))
                .fluidOutputs(Helium3Hydride.getFluid(100))
                .fluidOutputs(TritiumHydride.getFluid(9900))
                .duration(800)
                .EUt(200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(CleanInertResidues.getItemStack())
                .fluidInputs(Helium3Hydride.getFluid(1000))
                .fluidOutputs(UltraacidicResidueSolution.getFluid(1000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(UltraacidicResidueSolution.getFluid(2000))
                .fluidInputs(LiquidOxygen.getFluid(4000))
                .fluidInputs(LiquidXenon.getFluid(1000))
                .fluidOutputs(XenicAcid.getFluid(1000))
                .fluidOutputs(DustyLiquidHelium3.getFluid(2000))
                .duration(120)
                .EUt(2000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DustyLiquidHelium3.getFluid(1000))
                .fluidOutputs(TaraniumEnrichedLHelium3.getFluid(100))
                .fluidOutputs(TaraniumSemidepletedLHelium3.getFluid(300))
                .fluidOutputs(TaraniumDepletedLHelium3.getFluid(600))
                .duration(400)
                .EUt(3000)
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumEnrichedLHelium3.getFluid(1000))
                .fluidInputs(Helium3.getFluid(1000))
                .fluidOutputs(TaraniumRichDustyHeliumPlasma.getFluid(3000))
                .duration(160)
                .EUt(32768)
                .euStart(100000000)
                .euReturn(5000)
                .coilTier(1)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumRichDustyHeliumPlasma.getFluid(3000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(TaraniumRichHelium4.getPlasma(500))
                .fluidOutputs(Hydrogen.getPlasma(2000))
                .fluidOutputs(TaraniumDepletedHeliumPlasma.getFluid(500))
                .duration(80)
                .EUt(2000)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getPlasma(1000))
                .fluidInputs(TaraniumDepletedLHelium3.getFluid(1000))
                .fluidOutputs(TaraniumDepletedHeliumPlasma.getFluid(2000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumDepletedHeliumPlasma.getFluid(10000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(Helium3.getPlasma(10000))
                .outputs(CleanInertResidues.getItemStack())
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumSemidepletedLHelium3.getFluid(1000))
                .fluidOutputs(TaraniumEnrichedLHelium3.getFluid(100))
                .fluidOutputs(TaraniumDepletedLHelium3.getFluid(900))
                .duration(400)
                .EUt(3000)
                .buildAndRegister();


        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumRichHelium4.getFluid(1000))
                .output(dustTiny, Taranium)
                .fluidOutputs(TaraniumPoorLiquidHelium.getFluid(1000))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumPoorLiquidHelium.getFluid(1000))
                .fluidInputs(LiquidHelium3.getFluid(100))
                .fluidOutputs(TaraniumPoorLiquidHeliumMix.getFluid(1100))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumPoorLiquidHeliumMix.getFluid(1100))
                .fluidOutputs(LiquidHelium.getFluid(1000))
                .fluidOutputs(DustyLiquidHelium3.getFluid(100))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumRichHelium4.getPlasma(1000))
                .fluidOutputs(TaraniumRichHelium4.getFluid(1000))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidOutputs(LiquidFluorine.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Xenon.getFluid(1000))
                .fluidOutputs(LiquidXenon.getFluid(1000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getFluid(1000))
                .fluidOutputs(Helium3.getPlasma(1000))
                .circuitMeta(0)
                .duration(60)
                .EUt(8000)
                .buildAndRegister();

    }
}
