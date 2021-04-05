package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class AmmoniaChain {
    public static void init() {

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, Garnierite, 2)
                .inputs(Alumina.getItemStack(5))
                .outputs(NiAlOCatalyst.getItemStack(7))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, Iron)
                .input(dust, ChromiumTrioxide, 4)
                .outputs(FeCrOCatalyst.getItemStack(5))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(NiAlOCatalyst.getItemStack())
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidInputs(Air.getFluid(1000))
                .fluidOutputs(RichNitrogenMix.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(NiAlOCatalyst.getItemStack())
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(RichNitrogenMix.getFluid(5000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .notConsumable(FeCrOCatalyst.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(RichNitrogenMix.getFluid(1000))
                .fluidOutputs(OxidisedNitrogenMix.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(OxidisedNitrogenMix.getFluid(1000))
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(PurifiedNitrogenMix.getFluid(1000))
                .fluidOutputs(CarbonatedEthanolamine.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(CarbonatedEthanolamine.getFluid(2000))
                .fluidOutputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(500))
                .fluidOutputs(Water.getFluid(500))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(PurifiedNitrogenMix.getFluid(1000))
                .fluidOutputs(HotPurifiedNitrogenMix.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(HotPurifiedNitrogenMix.getFluid(1000))
                .notConsumable(dust, Magnetite)
                .fluidOutputs(AmmoniaRichMix.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(AmmoniaRichMix.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(PurifiedNitrogenMix.getFluid(1000))
                .buildAndRegister();
    }
}

