package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class FusionElementsChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(500)
                .input(dust, Lithium)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1800)
                .fluidInputs(LithiumHydroxideSolution.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(LithiumPeroxideSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(LithiumPeroxideSolution.getFluid(1000))
                .fluidOutputs(LithiumCarbonatePureSolution.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(2000)
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Ozone.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(NitrogenPentoxide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .fluidInputs(NitrogenPentoxide.getFluid(2000))
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .outputs(TitaniumNitrate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1950).blastFurnaceTemp(3100)
                .inputs(TitaniumNitrate.getItemStack())
                .fluidInputs(LithiumCarbonatePureSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, LithiumTitanate))
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

//        FUSION_RECIPES.recipeBuilder().duration(128).EUt(2048).EUToStart(80000000)
//                .fluidInputs(Hydrogen.getFluid(128))
//                .fluidInputs(CNOcatalyst.getFluid(32))
//                .fluidOutputs(HeliumCNOPlasma.getFluid(160))
//                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(150).EUt(25000)
                .fluidInputs(HeliumCNO.getFluid(800))
                .fluidOutputs(Helium.getFluid(640))
                .fluidOutputs(CNOcatalyst.getFluid(160))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Helium.getFluid(375), Helium.getFluid(375)).fluidOutputs(Carbon.getPlasma(250)).duration(384).EUt(7000).euStart(160000000).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Carbon.getFluid(125), Helium.getFluid(125)).fluidOutputs(Oxygen.getPlasma(125)).duration(128).EUt(7000).euStart(180000000).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Oxygen.getFluid(125), Helium.getFluid(125)).fluidOutputs(Neon.getPlasma(125)).duration(128).EUt(7000).euStart(200000000).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Neon.getFluid(125), Helium.getFluid(125)).fluidOutputs(Magnesium.getPlasma(125)).duration(128).EUt(7000).euStart(220000000).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Magnesium.getFluid(125), Helium.getFluid(125)).fluidOutputs(Silicon.getPlasma(125)).duration(128).EUt(7000).euStart(240000000L).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Silicon.getFluid(125), Helium.getFluid(125)).fluidOutputs(Sulfur.getPlasma(125)).duration(128).EUt(7000).euStart(260000000L).coilTier(1).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Sulfur.getFluid(125), Helium.getFluid(125)).fluidOutputs(Argon.getPlasma(125)).duration(128).EUt(7000).euStart(280000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Argon.getFluid(125), Helium.getFluid(125)).fluidOutputs(Calcium.getPlasma(125)).duration(128).EUt(7000).euStart(70000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Calcium.getFluid(125), Helium.getFluid(125)).fluidOutputs(PlasmaTitanium44.getFluid(125)).duration(128).EUt(7000).euStart(320000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Titanium44.getFluid(125), Helium.getFluid(125)).fluidOutputs(PlasmaChromium48.getFluid(125)).duration(128).EUt(7000).euStart(340000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Calcium44.getFluid(125), Helium.getFluid(125)).fluidOutputs(Titanium.getPlasma(125)).duration(128).EUt(7000).euStart(360000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Chromium48.getFluid(125), Helium.getFluid(125)).fluidOutputs(PlasmaIron52.getFluid(125)).duration(128).EUt(7000).euStart(380000000L).coilTier(2).buildAndRegister();
        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Iron52.getFluid(125), Helium.getFluid(125)).fluidOutputs(PlasmaNickel56.getFluid(125)).duration(128).EUt(7000).euStart(400000000L).coilTier(2).buildAndRegister();


        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(10000).fluidInputs(PlasmaTitanium44.getFluid(144)).fluidOutputs(Calcium44.getFluid(144)).buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(10000).fluidInputs(PlasmaChromium48.getFluid(144)).fluidOutputs(Titanium.getFluid(144)).buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(10000).fluidInputs(PlasmaIron52.getFluid(144)).fluidOutputs(Chrome.getFluid(144)).buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(10000).fluidInputs(PlasmaNickel56.getFluid(144)).fluidOutputs(Iron.getFluid(144)).buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Uranium.getFluid(125), Uranium.getFluid(125)).fluidOutputs(QuassifissioningPlasma.getFluid(125)).duration(100).EUt(600000).coilTier(2).euStart(4000000000L).buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(320).EUt(750000).fluidInputs(QuassifissioningPlasma.getFluid(1000)).fluidOutputs(FlYbPlasma.getFluid(1000)).buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(290).EUt(840000).fluidInputs(FlYbPlasma.getFluid(1000)).fluidOutputs(MetastableFlerovium.getFluid(500)).fluidOutputs(Ytterbium178.getFluid(500)).buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(320).EUt(450000).fluidInputs(Ytterbium178.getFluid(1000)).fluidOutputs(Hafnium.getFluid(1000)).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(6000)
                .input(dust, Titanium)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .outputs(TitaniumTetrafluoride.getItemStack())
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(400).EUt(5400)
                .inputs(TitaniumTetrafluoride.getItemStack())
                .fluidOutputs(MoltenTitaniumTetrafluoride.getFluid(1000))
                .buildAndRegister();

        GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(420).EUt(59000)
                .circuitMeta(0)
                .fluidInputs(MoltenTitaniumTetrafluoride.getFluid(10000))
                .fluidOutputs(MoltenTitanium50Tetrafluoride.getFluid(518))
                .fluidOutputs(Fluorine.getFluid(9482))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(7200).EUt(10000)
                .fluidInputs(MoltenTitanium50Tetrafluoride.getFluid(1000))
                .input(dust, Sodium, 4)
                .outputs(OreDictUnifier.get(ingotHot, Titanium50))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 4))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(125000)
                .fluidInputs(Titanium50.getFluid(1000))
                .fluidInputs(Californium252.getMaterial().getFluid(1000))
                .fluidOutputs(OgannesonBreedingBase.getFluid(2000))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().fluidInputs(Curium250.getMaterial().getFluid(125), OgannesonBreedingBase.getFluid(125)).fluidOutputs(HotMetastableOganesson.getFluid(125)).duration(100).EUt(600000).coilTier(2).euStart(4000000000L).buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(380).EUt(1200000)
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(HotMetastableOganesson.getFluid(1000))
                .outputs(OreDictUnifier.get(ingotHot, MetastableOganesson))
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
        
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(345000)
                .fluidInputs(HeptafluoroTantalate.getFluid(6000))
                .input(dust, Graphite,6)
                .input(dust, SiliconDioxide, 7)
                .fluidInputs(Hydrogen.getFluid(8000))
                .fluidOutputs(Water.getFluid(14000))
                .outputs(TantalumCarbide.getItemStack(6))
                .fluidOutputs(FluorosilicicAcid.getFluid(7000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(135000).blastFurnaceTemp(3000)
                .inputs(SodiumSeaborgate.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .input(dust, Carbon)
                .outputs(SeaborgiumCarbide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(118000).blastFurnaceTemp(6200)
                .inputs(TantalumCarbide.getItemStack(12))
                .inputs(HafniumCarbide.getItemStack(3))
                .inputs(SeaborgiumCarbide.getItemStack())
                .outputs(OreDictUnifier.get(ingotHot, TantalumHafniumSeaborgiumCarbide, 16))
                .buildAndRegister();
    }
}
