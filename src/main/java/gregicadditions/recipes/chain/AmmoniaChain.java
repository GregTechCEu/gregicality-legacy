package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.stickLong;

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

        // CH4 + H2O + Air(N) -> NH4C(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(NiAlOCatalyst.getItemStack())
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Steam.getFluid(960))
                .fluidInputs(Air.getFluid(1000))
                .fluidOutputs(RichNitrogenMix.getFluid(3000))
                .buildAndRegister();

        // CH4 + H2O + N -> NH4C(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(NiAlOCatalyst.getItemStack())
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Steam.getFluid(960))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(RichNitrogenMix.getFluid(5000))
                .buildAndRegister();

        // NH4C(H2O) + H2O -> NH4C(H2O)2
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(120)
                .notConsumable(FeCrOCatalyst.getItemStack())
                .notConsumable(stickLong, Titanium)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(RichNitrogenMix.getFluid(1000))
                .fluidOutputs(OxidisedNitrogenMix.getFluid(2000))
                .buildAndRegister();

        // NH4C(H2O)2 + H2O + C2H7NO -> [C2H7NO + 0.5CO2 + 0.5H2O] + [0.5H2O + NH4 + H4]
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(OxidisedNitrogenMix.getFluid(1000))
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(PurifiedNitrogenMix.getFluid(1000))
                .fluidOutputs(CarbonatedEthanolamine.getFluid(2000))
                .buildAndRegister();

        // [C2H7NO + 0.5CO2 + 0.5H2O] -> C2H7NO + 0.5CO2 + 0.5H2O
        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(CarbonatedEthanolamine.getFluid(2000))
                .fluidOutputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(500))
                .fluidOutputs(Water.getFluid(500))
                .buildAndRegister();

        // [0.5H2O + NH4 + H4] -> [NH4 + H4]
        FLUID_HEATER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(PurifiedNitrogenMix.getFluid(1000))
                .fluidOutputs(HotPurifiedNitrogenMix.getFluid(1000))
                .buildAndRegister();

        // [NH4 + H4] -> [NH4]
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(HotPurifiedNitrogenMix.getFluid(1000))
                .notConsumable(dust, Magnetite)
                .fluidOutputs(AmmoniaRichMix.getFluid(1000))
                .buildAndRegister();

        // 2[NH4] -> NH4 + [0.5H2O + NH4 + H4]
        CENTRIFUGE_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(AmmoniaRichMix.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(PurifiedNitrogenMix.getFluid(1000))
                .buildAndRegister();
    }
}

