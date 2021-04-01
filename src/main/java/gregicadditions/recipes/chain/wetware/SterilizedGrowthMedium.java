package gregicadditions.recipes.chain.wetware;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.STERILIZED_PETRI_DISH;
import static gregicadditions.item.GAMetaItems.PETRI_DISH;
import static gregicadditions.item.GAMetaItems.CONTAMINATED_PETRI_DISH;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class SterilizedGrowthMedium {
    public static void init() {

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(SilicaGelBase.getFluid(3000))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SilicaGelBase.getFluid(1000))
                .outputs(SilicaGel.getItemStack(3))
                .output(dust, Salt, 2)
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(SilicaGel.getItemStack(3))
                .inputs(Alumina.getItemStack(5))
                .outputs(SilicaAluminaGel.getItemStack(8))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .inputs(SilicaAluminaGel.getItemStack())
                .outputs(ZeoliteSievingPellets.getItemStack())
                .blastFurnaceTemp(4500)
                .EUt(30720)
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(WetZeoliteSievingPellets.getItemStack())
                .outputs(ZeoliteSievingPellets.getItemStack())
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(ZeoliteSievingPellets.getItemStack())
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Ethanol100.getFluid(1000))
                .outputs(WetZeoliteSievingPellets.getItemStack())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(PETRI_DISH.getStackForm())
                .fluidInputs(Ethanol100.getFluid(100))
                .outputs(STERILIZED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(25)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(PiranhaSolution.getFluid(2000))
                .EUt(480)
                .duration(50)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PiranhaSolution.getFluid(100))
                .inputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .outputs(PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(25)
                .buildAndRegister();

        // [H2SO4 + HNO3] + C6H6 -> C6H5NO2 + H2O + H2SO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(NitroBenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // 6H + C6H5NO2 -> C6H5NH2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(NitroBenzene.getFluid(1000))
                .notConsumable(dust, Platinum)
                .fluidOutputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // HCl + 2H2SO4 + O -> HSO3Cl + 2H2O + SO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(ChlorosulfonicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // C6H5NH2 + (CH3CO)2O + HSO3Cl -> C8H8ClNO3S + H2O + CH3COOH
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(ChlorosulfonicAcid.getFluid(1000))
                .fluidOutputs(AcetylsulfanilylChloride.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // H2O + Na2CO3 -> NaHCO3 + NaOH
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(Water.getFluid(1000))
                .input(dust, SodaAsh, 6)
                .outputs(SodiumBicarbonate.getItemStack(6))
                .output(dust, SodiumHydroxide, 3)
                .buildAndRegister();

        // NaHCO3 + C8H8ClNO3S + NH3 -> NaCl + C6H8N2O2S + CO2 + CH3COOH
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SodiumBicarbonate.getItemStack(6))
                .fluidInputs(AcetylsulfanilylChloride.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(Sulfanilamide.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(RawGrowthMedium.getFluid(1000))
                .fluidInputs(Sulfanilamide.getFluid(250))
                .fluidOutputs(SterileGrowthMedium.getFluid(1250))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();
    }
}
