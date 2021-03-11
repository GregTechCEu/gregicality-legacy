package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SensorEmitter {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(420).EUt(720)
                .input(dust, Strontium)
                .input(dust, Barium)
                .fluidInputs(AceticAcid.getFluid(2000))
                .fluidOutputs(BariumStrontiumAcetateSolution.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1200)
                .input(dust, Titanium)
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidOutputs(TitaniumIsopropoxide.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(280).EUt(600)
                .fluidInputs(BariumStrontiumAcetateSolution.getFluid(2000))
                .fluidInputs(TitaniumIsopropoxide.getFluid(1000))
                .fluidOutputs(BariumStrontiumTitanatePreparation.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(1500).blastFurnaceTemp(1200)
                .fluidInputs(BariumStrontiumTitanatePreparation.getFluid(1000))
                .input(dust, Calcite, 2)
                .outputs(BariumStrontiumTitanate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Quicklime))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1300)
                .fluidInputs(Water.getFluid(7000))
                .inputs(PotasssiumFluoroTantalate.getItemStack())
                .outputs(TantalumOxide.getItemStack())
                .fluidOutputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(PotassiumHydroxide.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(8192)
                .input(dust, Tantalum, 2)
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(Water.getFluid(5000))
                .outputs(TantalumOxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(5000))
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(8192)
                .input(dust, Scandium, 2)
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(Water.getFluid(5000))
                .outputs(ScandiumOxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(5000))
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(8192)
                .input(dust, Lutetium, 2)
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(Water.getFluid(5000))
                .outputs(LutetiumOxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(5000))
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(8192)
                .input(dust, Thulium, 2)
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(Water.getFluid(5000))
                .outputs(ThuliumOxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(5000))
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(8192)
                .input(dust, Europium, 2)
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(Water.getFluid(5000))
                .outputs(EuropiumOxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(5000))
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(1200).blastFurnaceTemp(1350)
                .input(dust, LeadNitrate)
                .inputs(TantalumOxide.getItemStack())
                .inputs(ScandiumOxide.getItemStack())
                .outputs(LeadScandiumTantalate.getItemStack(2))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(940).EUt(30)
                .input(dust, Terbium, 4)
                .input(dust, Dysprosium, 7)
                .input(dust, Iron, 10)
                .input(dust, Cobalt, 5)
                .input(dust, Boron, 2)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .outputs(MagnetorestrictiveAlloy.getItemStack(30))
            .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(750)
                .input(dust, Lead)
                .input(dust, Selenium)
                .outputs(LeadSenenide.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(750)
                .input(dust, Zinc)
                .input(dust, Selenium)
                .outputs(ZincSelenide.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(6500).blastFurnaceTemp(2200)
                .input(dust, Francium)
                .input(dust, Caesium)
                .input(dust, Cadmium, 2)
                .fluidInputs(Bromine.getFluid(6000))
                .outputs(FranciumCaesiumCadmiumBromide.getItemStack(10))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(9000)
                .fluidInputs(Aniline.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .inputs(SodiumIodide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Iodobenzene.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(18000)
                .notConsumable(PalladiumAcetate.getItemStack())
                .fluidInputs(Iodobenzene.getFluid(1000))
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(200))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .outputs(Stibene.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(600000)
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Amino3phenol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(64500)
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Ethylamine.getFluid(2000))
                .notConsumable(SodiumAzanide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(490000)
                .fluidInputs(Ethylamine.getFluid(2000))
                .fluidInputs(Amino3phenol.getFluid(1000))
                .input(dust, PhthalicAnhydride)
                .notConsumable(TetraethylammoniumNonahydridides.getItemStack())
                .fluidOutputs(Ammonia.getFluid(2000))
                .outputs(RhodamineB.getItemStack(2))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(850000)
                .input(dust, Rhenium)
                .fluidInputs(Fluorine.getFluid(7000))
                .fluidInputs(Water.getFluid(4000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(7000))
                .fluidOutputs(AmmoniumPerrhenate.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(850000)
                .input(dust, Technetium)
                .fluidInputs(AquaRegia.getFluid(4000))
                .input(dust, SodiumHydroxide, 4)
                .outputs(OreDictUnifier.get(dust, Salt, 4))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .outputs(SodiumPertechnate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(750000).blastFurnaceTemp(6500)
                .input(dust, Potassium)
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .outputs(PotassiumPerrhenate.getItemStack())
                .fluidOutputs(Ammonia.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(750000).blastFurnaceTemp(6500)
                .input(dust, Potassium)
                .inputs(SodiumPertechnate.getItemStack())
                .outputs(PotassiumPertechnate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sodium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(840000)
                .inputs(PotassiumPerrhenate.getItemStack())
                .input(dust, Potassium, 18)
                .fluidInputs(Ethanol.getFluid(13000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .outputs(PotassiumNonahydridorhenate.getItemStack())
                .fluidOutputs(PotassiumEtoxide.getFluid(13000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(840000)
                .inputs(PotassiumPertechnate.getItemStack())
                .input(dust, Potassium, 18)
                .fluidInputs(Ethanol.getFluid(13000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .outputs(PotassiumNonahydridotechnate.getItemStack())
                .fluidOutputs(PotassiumEtoxide.getFluid(13000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(3450)
                .fluidInputs(PotassiumEtoxide.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(360).EUt(9400)
                .inputs(PotassiumNonahydridorhenate.getItemStack())
                .inputs(PotassiumNonahydridotechnate.getItemStack())
                .fluidInputs(TetraethylammoniumBromide.getFluid(4000))
                .outputs(PotassiumBromide.getItemStack(4))
                .outputs(TetraethylammoniumNonahydridides.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(870000)
                .input(dust, Iodine)
                .fluidInputs(Aminophenol.getFluid(1000))
                .fluidInputs(ButhylLithium.getFluid(1000))
                .fluidOutputs(Butylaniline.getFluid(1000))
                .outputs(LithiumIodide.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(650000)
                .inputs(SodiumHydride.getItemStack())
                .fluidInputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(Trimethylsilane.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(350000)
                .input(dust, Potassium)
                .fluidInputs(Bromine.getFluid(1000))
                .outputs(PotassiumBromide.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(340000)
                .inputs(PotassiumBromide.getItemStack())
                .fluidInputs(Ozone.getFluid(3000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .outputs(PotassiumBromate.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(980000)
                .input(dust, PhthalicAnhydride)
                .inputs(SodiumIodide.getItemStack())
                .inputs(SodiumNitrite.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(IodobenzoicAcid.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 2))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(345000)
                .inputs(PotassiumBromate.getItemStack(2))
                .fluidInputs(IodobenzoicAcid.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .outputs(PotassiumBromide.getItemStack(2))
                .outputs(IBX.getItemStack(3))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(LithiumIodide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Lithium))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(500)
                .inputs(PotassiumBromide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Potassium))
                .fluidOutputs(Bromine.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(845000)
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .fluidInputs(Fluorotoluene.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .inputs(IBX.getItemStack())
                .notConsumable(dust, CobaltOxide)
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(1000))
                .fluidOutputs(Metoxybenzaldehyde.getFluid(1000))
                .fluidOutputs(IodobenzoicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(240000)
                .fluidInputs(Metoxybenzaldehyde.getFluid(1000))
                .fluidInputs(Butylaniline.getFluid(1000))
                .notConsumable(TetraethylammoniumNonahydridides.getItemStack())
                .fluidOutputs(MBBA.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(680000)
                .input(dust, CarbonNanotubes)
                .fluidInputs(MBBA.getFluid(2000))
                .fluidOutputs(LiquidCrystalDetector.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(98000)
                .inputs(PalladiumChloride.getItemStack())
                .fluidInputs(AceticAcid.getFluid(2000))
                .outputs(PalladiumAcetate.getItemStack())
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(87500)
                .input(dust, Iodine)
                .input(dust, Copper)
                .outputs(CopperIodide.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(87500)
                .input(dust, Iodine)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(IodineChloride.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(260).EUt(84500)
                .input(dust, Rhenium)
                .input(dust, Rhodium)
                .input(dust, Naquadah)
                .outputs(RhReNqCatalyst.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(128)
                .input(dust, Magnesium, 2)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, MagnesiumChloride))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(350000)
                .fluidInputs(Acetylene.getFluid(2000))
                .input(dust, MagnesiumChloride)
                .fluidInputs(Trimethylchlorosilane.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(AcetylatingReagent.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(250000)
                .fluidInputs(Naphtalene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Dimethylnaphtalene.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(290).EUt(1200000)
                .fluidInputs(Dimethylnaphtalene.getFluid(1000))
                .fluidInputs(AcetylatingReagent.getFluid(2000))
                .fluidInputs(IodineChloride.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(Bromosuccinimide.getItemStack())
                .inputs(CopperIodide.getItemStack())
                .inputs(SodiumIodide.getItemStack())
                .notConsumable(RhReNqCatalyst.getItemStack())
                .outputs(CopperChloride.getItemStack())
                .outputs(MgClBrominide.getItemStack())
                .fluidOutputs(Dihydroiodotetracene.getFluid(1000))
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(135000)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(5000))
                .fluidInputs(HydrogenCyanide.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .fluidOutputs(Dichlorodicyanobenzoquinone.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(135000)
                .fluidInputs(Dichlorodicyanohydroquinone.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Dichlorodicyanobenzoquinone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .notConsumable(VanadiumOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(491520)
                .inputs(PalladiumAcetate.getItemStack())
                .input(wireFine, LithiumTitanate)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .outputs(LithiumChloride.getItemStack())
                .outputs(PalladiumLoadedRutileNanoparticles.getItemStack(2))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(850000)
                .fluidInputs(Dihydroiodotetracene.getFluid(1000))
                .fluidInputs(Dichlorodicyanobenzoquinone.getFluid(1000))
                .fluidOutputs(Dichlorodicyanohydroquinone.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidOutputs(Acetone.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .outputs(Tetracene.getItemStack())
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .notConsumable(PalladiumLoadedRutileNanoparticles.getItemStack())
                .buildAndRegister();
        
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(850000)
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm())
                .input(dustSmall, Vibranium, 2)
                .fluidInputs(HeavyLeptonMix.getFluid(500))
                .fluidInputs(FreeElectronGas.getFluid(500))
                .outputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm())
                .buildAndRegister();
    }
}
