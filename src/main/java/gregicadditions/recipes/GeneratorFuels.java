package gregicadditions.recipes;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.loaders.recipe.FuelRecipes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;

public class GeneratorFuels {
    public static void init() {
        //Removal
        //removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, Materials.BioFuel.getFluid(2));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, LightFuel.getFluid(1));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, LightFuel.getFluid(1));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, Fuel.getFluid(1));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, Ethanol.getFluid(2));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, NitroFuel.getFluid(1));
        removeFuelRecipe(RecipeMaps.DIESEL_GENERATOR_FUELS, Steam.getFluid(32));
        removeFuelRecipe(RecipeMaps.GAS_TURBINE_FUELS, Hydrogen.getFluid(1));
        removeFuelRecipe(RecipeMaps.GAS_TURBINE_FUELS, Methane.getFluid(1));
        removeFuelRecipe(RecipeMaps.GAS_TURBINE_FUELS, LPG.getFluid(1));
        removeFuelRecipe(RecipeMaps.GAS_TURBINE_FUELS, NaturalGas.getFluid(1));
        removeFuelRecipe(RecipeMaps.SEMI_FLUID_GENERATOR_FUELS, Creosote.getFluid(2));
        removeFuelRecipe(RecipeMaps.STEAM_TURBINE_FUELS, Steam.getFluid(32));

        //Steam Turbine
        FuelRecipes.registerSteamGeneratorFuel(Steam.getFluid(640), 10, GTValues.LV);
        GARecipeMaps.HIGH_PRESSURE_STEAM_TURBINE_FUELS.addRecipe(new FuelRecipe(HighPressureSteam.getFluid(320), 10, GTValues.V[GTValues.LV]));

        //Gas Turbine Fuels
        FuelRecipes.registerGasGeneratorFuel(NaturalGas.getFluid(20), 13, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Hydrogen.getFluid(20), 13, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(CarbonMonoxde.getFluid(20), 15, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(WoodGas.getFluid(20), 15, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(SulfuricGas.getFluid(32), 25, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(SulfuricNaphtha.getFluid(8), 10, GTValues.LV);
        //FuelRecipes.registerGasGeneratorFuel(Materials.BioGas.getFluid(4), 45, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Methane.getFluid(4), 14, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Ethylene.getFluid(5), 20, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Gas.getFluid(2), 10, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Ethane.getFluid(4), 21, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Propene.getFluid(10), 60, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butadiene.getFluid(16), 103, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Propane.getFluid(4), 29, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(RocketFuel.getFluid(1), 5, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butene.getFluid(10), 80, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Phenol.getFluid(10), 90, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Benzene.getFluid(10), 90, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butane.getFluid(4), 37, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(LPG.getFluid(1), 10, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Naphtha.getFluid(1), 10, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Toluene.getFluid(4), 41, GTValues.LV);

        //Diesel Generator Fluids
        //FuelRecipes.registerDieselGeneratorFuel(Materials.BioFuel.getFluid(16), 3, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Oil.getFluid(20), 10, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(SulfuricLightFuel.getFluid(8), 10, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Methanol.getFluid(8), 21, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(RocketFuel.getFluid(10), 35, GTValues.LV);
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            FuelRecipes.registerDieselGeneratorFuel(Fluids.BIO_ETHANOL.getFluid(2), 12, GTValues.LV);
        else FuelRecipes.registerDieselGeneratorFuel(Ethanol.getFluid(2), 12, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(BioDiesel.getFluid(2), 14, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(LightFuel.getFluid(32), 305, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Fuel.getFluid(1), 15, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(NitroFuel.getFluid(2), 45, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Gasoline.getFluid(8), 135, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(HighOctaneGasoline.getFluid(10), 562, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Octane.getFluid(20), 45, GTValues.LV);

        //Naquadah Reactor
        registerNaquadahReactorFuel(NaquadahEnriched.getFluid(1), 750, GTValues.LV);
        registerNaquadahReactorFuel(Naquadria.getFluid(1), 4500, GTValues.LV);

        //Plasma Generator
        registerPlasmaFuel(Helium.getPlasma(1), 2560, GTValues.LV);
        registerPlasmaFuel(Nitrogen.getPlasma(1), 4032, GTValues.LV);
        registerPlasmaFuel(Radon.getPlasma(1), 6144, GTValues.LV);
        registerPlasmaFuel(Oxygen.getPlasma(1), 4096, GTValues.LV);
        registerPlasmaFuel(Iron.getPlasma(16), 103219, GTValues.LV);
        registerPlasmaFuel(Nickel.getPlasma(16), 106905, GTValues.LV);

        //Smefuels
        FuelRecipes.registerSemiFluidGeneratorFuel(GAMaterials.FishOil.getFluid(640), 10, GTValues.LV);
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            FuelRecipes.registerSemiFluidGeneratorFuel(Fluids.SEED_OIL.getFluid(640), 10, GTValues.LV);
        else FuelRecipes.registerSemiFluidGeneratorFuel(SeedOil.getFluid(640), 10, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(Creosote.getFluid(160), 10, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(Biomass.getFluid(160), 10, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilLight.getFluid(320), 50, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilMedium.getFluid(640), 150, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilHeavy.getFluid(160), 50, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(SulfuricHeavyFuel.getFluid(160), 50, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(HeavyFuel.getFluid(80), 150, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(CoalTar.getFluid(320), 10, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(CoalTarOil.getFluid(160), 10, GTValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(SulfuricCoalTarOil.getFluid(120), 10, GTValues.LV);

        //rocket fuel
        registerRocketFuel(RocketFuelH8N4C2O4.getFluid(6), 10, GTValues.EV);
        registerRocketFuel(RocketFuelCN3H7O3.getFluid(12), 10, GTValues.EV);
        registerRocketFuel(DenseHydrazineFuelMixture.getFluid(18), 10, GTValues.EV);
        registerRocketFuel(RP1RocketFuel.getFluid(24), 10, GTValues.EV);

    }

    //Register Methods
    public static void registerPlasmaFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.PLASMA_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    //Register Methods
    public static void registerNaquadahReactorFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.NAQUADAH_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerRocketFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.ROCKET_FUEL_RECIPES.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    private static void removeFuelRecipe(FuelRecipeMap map, FluidStack fluidStack) {
        map.removeRecipe(map.findRecipe(Integer.MAX_VALUE, fluidStack));
    }
}
