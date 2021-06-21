package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class HNIWChain {

    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .inputs(PotassiumCarbonate.getItemStack())
                .outputs(PotassiumBisulfite.getItemStack())
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, Saltpeter)
                .input(dust, Lead)
                .outputs(PotassiumNitrite.getItemStack(1))
                .output(dust, Massicot)
                .blastFurnaceTemp(3000)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(PotassiumNitrite.getItemStack())
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(NitrousAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrousAcid.getFluid(1000))
                .inputs(PotassiumBisulfite.getItemStack())
                .outputs(PotassiumHydroxylaminedisulfonate.getItemStack())
                .fluidOutputs(Water.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(PotassiumHydroxylaminedisulfonate.getItemStack(2))
                .fluidInputs(Water.getFluid(4000))
                .outputs(HydroxylammoniumSulfate.getItemStack())
                .outputs(PotassiumSulfate.getItemStack(2))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(HydroxylammoniumSulfate.getItemStack())
                .inputs(BariumChloride.getItemStack())
                .fluidOutputs(HydroxylamineHydrochloride.getFluid(2000))
                .fluidOutputs(BariumSulfateSolution.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SuccinicAcid.getItemStack())
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .notConsumable(dust, CalciumChloride)
                .outputs(SuccinicAnhydride.getItemStack())
                .fluidOutputs(AceticAcid.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Acetylene.getFluid(3000))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidOutputs(Tetrahydrofuran.getFluid(2000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .inputs(SuccinicAnhydride.getItemStack(65))
                .fluidInputs(HydroxylamineHydrochloride.getFluid(64000))
                .fluidInputs(Toluene.getFluid(9000))
                .input(dust, Sodium, 64)
                .fluidInputs(Methanol.getFluid(67000))
                .output(dust, Salt, 64)
                .outputs(NHydroxysuccinimide.getItemStack(64))
                .EUt(30720)
                .duration(400)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(3000))
                .fluidOutputs(Triethylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Triethylamine.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(4000))
                .inputs(NHydroxysuccinimide.getItemStack(8))
                .notConsumable(Tetrahydrofuran.getFluid(1000))
                .outputs(SuccinimidylAcetate.getItemStack(9))
                .fluidOutputs(Hydrogen.getFluid(16000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SeleniumOxide.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SelenousAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Acetaldehyde.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .notConsumable(SelenousAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Glyoxal.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(AmmoniumCarbonate.getItemStack())
                .fluidInputs(AceticAcid.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(AmmoniumAcetate.getItemStack(2))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(AmmoniumAcetate.getItemStack())
                .outputs(Acetamide.getItemStack())
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(Acetamide.getItemStack())
                .outputs(Acetonitrile.getItemStack())
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .notConsumable(GAMetaItems.UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(BenzylChloride.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Formaldehyde.getFluid(4000))
                .fluidOutputs(Ammonia.getFluid(6000))
                .outputs(Hexamethylenetetramine.getItemStack())
                .fluidOutputs(Water.getFluid(6000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(BenzylChloride.getFluid(20000))
                .inputs(Hexamethylenetetramine.getItemStack(7))
                .input(dust, SodiumHydroxide, 18)
                .fluidOutputs(Benzylamine.getFluid(26000))
                .fluidOutputs(AmmoniumChloride.getFluid(2000))
                .output(dust, Salt, 18)
                .fluidOutputs(Oxygen.getFluid(18000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Glyoxal.getFluid(3000))
                .fluidInputs(Benzylamine.getFluid(6000))
                .notConsumable(Hydrogen.getFluid(1000))
                .inputs(Acetonitrile.getItemStack())
                .outputs(Hexabenzylhexaazaisowurtzitane.getItemStack())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(PalladiumChloride.getItemStack())
                .input(dust, Carbon)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .outputs(PdCCatalyst.getItemStack())
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(Hydrogen.getFluid(1000))
                .notConsumable(PdCCatalyst.getItemStack())
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .notConsumable(EthylBenzene.getFluid(1000))
                .notConsumable(HydrobromicAcid.getFluid(1000))
                .inputs(SuccinimidylAcetate.getItemStack())
                .inputs(Hexabenzylhexaazaisowurtzitane.getItemStack())
                .outputs(DibenzylTetraacetylhexaazaisowurtzitane.getItemStack())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

    }
}
