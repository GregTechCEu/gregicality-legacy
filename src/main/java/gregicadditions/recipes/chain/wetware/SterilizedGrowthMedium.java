package gregicadditions.recipes.chain.wetware;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class SterilizedGrowthMedium {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SiliconDioxide)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(SilicaGelBase.getFluid(3000))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SilicaGelBase.getFluid(1000))
                .outputs(SilicaGel.getItemStack())
                .EUt(30720)
                .duration(250)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .inputs(SilicaGel.getItemStack())
                .inputs(Alumina.getItemStack())
                .outputs(SilicaAluminaGel.getItemStack())
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(SilicaAluminaGel.getItemStack())
                .outputs(ZeoliteSievingPellets.getItemStack())
                .blastFurnaceTemp(4500)
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(WetZeoliteSievingPellets.getItemStack())
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .outputs(ZeoliteSievingPellets.getItemStack())
                .blastFurnaceTemp(4500)
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
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(NitroBenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(NitroBenzene.getFluid(1000))
                .notConsumable(dust, Platinum)
                .fluidOutputs(Aniline.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(ChlorosulfuricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(ChlorosulfuricAcid.getFluid(1000))
                .fluidOutputs(AcetylsulfanilideChloride.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SodiumBicarbonate.getItemStack())
                .fluidInputs(AcetylsulfanilideChloride.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
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
