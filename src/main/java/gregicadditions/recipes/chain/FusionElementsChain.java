package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class FusionElementsChain {
    public static void init() {

        advFusionRecipes();

        // Li + 2H2O -> H + LiOH(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(500)
                .input(dust, Lithium)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // 2LiOH(H2O) + H2O2 -> Li2O2(H2O) + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1800)
                .fluidInputs(LithiumHydroxideSolution.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(LithiumPeroxideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // CO2 + Li2O2(H2O) -> Li2CO3(H2O) + O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(LithiumPeroxideSolution.getFluid(1000))
                .fluidOutputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // 6O -> 2O3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(2000)
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidOutputs(Ozone.getFluid(2000))
                .buildAndRegister();

        // 6NO2 + O3 -> 3N2O5
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(NitrogenDioxide.getFluid(6000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(NitrogenPentoxide.getFluid(3000))
                .buildAndRegister();

        // 2N2O5 + TiCl4 + 2O -> 4Cl + Ti(NO3)4
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .fluidInputs(NitrogenPentoxide.getFluid(2000))
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(4000))
                .outputs(TitaniumNitrate.getItemStack(17))
                .buildAndRegister();

        // Ti(NO3)4 + 2NaOH + Li2CO3(H2O) -> 4HNO3 + Li2TiO3 + Na2CO3
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1950).blastFurnaceTemp(3100)
                .inputs(TitaniumNitrate.getItemStack(17))
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(4000))
                .output(ingot, LithiumTitanate, 6)
                .output(dust, SodaAsh, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(180000)
                .fluidInputs(Carbon.getFluid(10000))
                .fluidOutputs(Carbon12.getFluid(9893))
                .fluidOutputs(Carbon13.getFluid(107))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3400).EUt(180000)
                .fluidInputs(Nitrogen.getFluid(100000))
                .fluidOutputs(Nitrogen14.getFluid(99636))
                .fluidOutputs(NItrogen15.getFluid(364))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(250).EUt(72000)
                .fluidInputs(Carbon12.getFluid(1000))
                .fluidInputs(Carbon13.getFluid(1000))
                .fluidInputs(Nitrogen14.getFluid(1000))
                .fluidInputs(NItrogen15.getFluid(1000))
                .fluidOutputs(CNOcatalyst.getFluid(4000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(150).EUt(25000)
                .fluidInputs(HeliumCNO.getFluid(800))
                .fluidOutputs(Helium.getFluid(640))
                .fluidOutputs(CNOcatalyst.getFluid(160))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(180).EUt(10000)
                .fluidInputs(Titanium44.getPlasma(144))
                .fluidOutputs(Calcium44.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(180).EUt(10000)
                .fluidInputs(Chromium48.getPlasma(144))
                .fluidOutputs(Titanium.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(180).EUt(10000)
                .fluidInputs(Iron52.getPlasma(144))
                .fluidOutputs(Chrome.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(180).EUt(10000)
                .fluidInputs(Nickel56.getPlasma(144))
                .fluidOutputs(Iron.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(320).EUt(750000)
                .fluidInputs(QuassifissioningPlasma.getFluid(1000))
                .fluidOutputs(FlYbPlasma.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .duration(290).EUt(840000)
                .fluidInputs(FlYbPlasma.getFluid(1000))
                .fluidOutputs(MetastableFlerovium.getFluid(288))
                .fluidOutputs(Ytterbium178.getFluid(288))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .duration(180).EUt(150000)
                .fluidInputs(Ytterbium178.getFluid(144))
                .fluidOutputs(Hafnium.getFluid(144))
                .buildAndRegister();

        // Ti + 4HF -> TiF4 + 4H
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(6000)
                .input(dust, Titanium)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .outputs(TitaniumTetrafluoride.getItemStack(5))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(400).EUt(5400)
                .inputs(TitaniumTetrafluoride.getItemStack(5))
                .fluidOutputs(MoltenTitaniumTetrafluoride.getFluid(1000))
                .buildAndRegister();

        GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(420).EUt(59000)
                .circuitMeta(0)
                .fluidInputs(MoltenTitaniumTetrafluoride.getFluid(10000))
                .fluidOutputs(MoltenTitanium50Tetrafluoride.getFluid(518))
                .fluidOutputs(Fluorine.getFluid(9482))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(7200).EUt(10000)
                .blastFurnaceTemp(Titanium.blastFurnaceTemperature)
                .fluidInputs(MoltenTitanium50Tetrafluoride.getFluid(1000))
                .input(dust, Sodium, 4)
                .output(ingotHot, Titanium50)
                .output(dust, SodiumFluoride, 8)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(125000)
                .fluidInputs(Titanium50.getFluid(288))
                .fluidInputs(Californium252.getMaterial().getFluid(288))
                .fluidOutputs(OgannesonBreedingBase.getFluid(2000))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Curium250.getMaterial().getFluid(36))
                .fluidInputs(OgannesonBreedingBase.getFluid(125))
                .fluidOutputs(HotMetastableOganesson.getFluid(125))
                .duration(100).EUt(600000)
                .coilTier(2).euStart(4000000000L).euReturn(50)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(380).EUt(1200000)
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(HotMetastableOganesson.getFluid(1000))
                .output(ingotHot, MetastableOganesson)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(320000).blastFurnaceTemp(3000)
                .input(dust, Hafnium)
                .input(dust, Graphite)
                .outputs(HafniumCarbide.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(470000).blastFurnaceTemp(2500)
                .input(dust, Tantalum)
                .input(dust, Graphite)
                .outputs(TantalumCarbide.getItemStack(2))
                .buildAndRegister();

        // 6H2TaF7 + 6C + 7SiO2 + 30H -> 14H2O + 6TaC + 7H2SiF6
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(345000)
                .fluidInputs(HeptafluoroTantalate.getFluid(6000))
                .input(dust, Graphite,6)
                .input(dust, SiliconDioxide, 21)
                .fluidInputs(Hydrogen.getFluid(30000))
                .fluidOutputs(Water.getFluid(14000))
                .outputs(TantalumCarbide.getItemStack(12))
                .fluidOutputs(FluorosilicicAcid.getFluid(7000))
                .buildAndRegister();

        // NaSgO3 + Cl + 4C -> SgC + NaCl + 3CO
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(135000).blastFurnaceTemp(3000)
                .inputs(SodiumSeaborgate.getItemStack(5))
                .fluidInputs(Chlorine.getFluid(1000))
                .input(dust, Carbon, 4)
                .outputs(SeaborgiumCarbide.getItemStack(2))
                .output(dust, Salt, 2)
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .buildAndRegister();

        // 12TaC + 3HfC + SgC -> Ta12Hf3SgC16
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(118000).blastFurnaceTemp(6200)
                .inputs(TantalumCarbide.getItemStack(24))
                .inputs(HafniumCarbide.getItemStack(6))
                .inputs(SeaborgiumCarbide.getItemStack(2))
                .output(ingotHot, TantalumHafniumSeaborgiumCarbide, 32)
                .buildAndRegister();
    }

    private static void advFusionRecipes() {

        ADV_FUSION_RECIPES.recipeBuilder().duration(384).EUt(7000).euStart(160000000).coilTier(1).euReturn(100)
                .fluidInputs(Helium.getFluid(375))
                .fluidInputs(Helium.getFluid(375))
                .fluidOutputs(Carbon.getPlasma(250))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(180000000).coilTier(1).euReturn(100)
                .fluidInputs(Carbon.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Oxygen.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(200000000).coilTier(1).euReturn(100)
                .fluidInputs(Oxygen.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Neon.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(220000000).coilTier(1).euReturn(100)
                .fluidInputs(Neon.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Magnesium.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(240000000L).coilTier(1).euReturn(95)
                .fluidInputs(Magnesium.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Silicon.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(260000000L).coilTier(1).euReturn(95)
                .fluidInputs(Silicon.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Sulfur.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(280000000L).coilTier(2).euReturn(95)
                .fluidInputs(Sulfur.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Argon.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(70000000L).coilTier(2).euReturn(90)
                .fluidInputs(Argon.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Calcium.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(320000000L).coilTier(2).euReturn(90)
                .fluidInputs(Calcium.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Titanium44.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(340000000L).coilTier(2).euReturn(90)
                .fluidInputs(Titanium44.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Chromium48.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(360000000L).coilTier(2).euReturn(85)
                .fluidInputs(Calcium44.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Titanium.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(380000000L).coilTier(2).euReturn(85)
                .fluidInputs(Chromium48.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Iron52.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).euStart(400000000L).coilTier(2).euReturn(80)
                .fluidInputs(Iron52.getFluid(125))
                .fluidInputs(Helium.getFluid(125))
                .fluidOutputs(Nickel56.getPlasma(125))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(8192).euStart(100000000).coilTier(1)
                .fluidInputs(CNOcatalyst.getFluid(160))
                .fluidInputs(Hydrogen.getFluid(640))
                .fluidOutputs(HeliumCNO.getPlasma(800))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(600000).coilTier(2).euStart(4000000000L).euReturn(50)
                .fluidInputs(Uranium.getFluid(125))
                .fluidInputs(Uranium.getFluid(125))
                .fluidOutputs(QuassifissioningPlasma.getFluid(125))
                .buildAndRegister();
    }
}
