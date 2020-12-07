package gregicadditions.recipes;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.recipes.nuclear.HotCoolantRecipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
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
        FuelRecipes.registerSteamGeneratorFuel(Steam.getFluid(640), 10, GAValues.LV);

        //reactor turbine
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(Steam, 570), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(Deuterium, 240), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(SodiumPotassiumAlloy, 120), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(Sodium, 100), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(FLiNaK, 50), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(FLiBe, 55), 1, GAValues.V[GAValues.EV]));
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(GAMetaFluids.getHotFluid(LeadBismuthEutectic, 60), 1, GAValues.V[GAValues.EV]));

        //Gas Turbine Fuels
        FuelRecipes.registerGasGeneratorFuel(NaturalGas.getFluid(20), 13, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Hydrogen.getFluid(20), 13, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(CarbonMonoxde.getFluid(20), 15, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(WoodGas.getFluid(20), 15, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(SulfuricGas.getFluid(32), 25, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(SulfuricNaphtha.getFluid(8), 10, GAValues.LV);
        //FuelRecipes.registerGasGeneratorFuel(Materials.BioGas.getFluid(4), 45, GTValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Methane.getFluid(4), 14, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Ethylene.getFluid(5), 20, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Gas.getFluid(2), 10, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Ethane.getFluid(4), 21, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Propene.getFluid(10), 60, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butadiene.getFluid(16), 103, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Propane.getFluid(4), 29, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(RocketFuel.getFluid(1), 5, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butene.getFluid(10), 80, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Phenol.getFluid(10), 90, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Benzene.getFluid(10), 90, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Butane.getFluid(4), 37, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(LPG.getFluid(1), 10, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Naphtha.getFluid(1), 10, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(Toluene.getFluid(4), 41, GAValues.LV);

        //Diesel Generator Fluids
        //FuelRecipes.registerDieselGeneratorFuel(Materials.BioFuel.getFluid(16), 3, GTValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Oil.getFluid(20), 10, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(SulfuricLightFuel.getFluid(8), 10, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Methanol.getFluid(8), 21, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(RocketFuel.getFluid(10), 35, GAValues.LV);
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            FuelRecipes.registerDieselGeneratorFuel(Fluids.BIO_ETHANOL.getFluid(2), 12, GAValues.LV);
        else FuelRecipes.registerDieselGeneratorFuel(Ethanol.getFluid(2), 12, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(BioDiesel.getFluid(2), 14, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(LightFuel.getFluid(32), 305, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Fuel.getFluid(1), 15, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(NitroFuel.getFluid(2), 45, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Gasoline.getFluid(8), 135, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(HighOctaneGasoline.getFluid(10), 562, GAValues.LV);
        FuelRecipes.registerDieselGeneratorFuel(Octane.getFluid(20), 45, GAValues.LV);


        //Plasma Generator
        registerPlasmaFuel(Helium.getPlasma(1), 2560, GAValues.LV);
        registerPlasmaFuel(Nitrogen.getPlasma(1), 4032, GAValues.LV);
        registerPlasmaFuel(Radon.getPlasma(1), 6144, GAValues.LV);
        registerPlasmaFuel(Oxygen.getPlasma(1), 4096, GAValues.LV);
        registerPlasmaFuel(Iron.getPlasma(16), 103219, GAValues.LV);
        registerPlasmaFuel(Nickel.getPlasma(16), 106905, GAValues.LV);

        //Smefuels
        FuelRecipes.registerSemiFluidGeneratorFuel(GAMaterials.FishOil.getFluid(640), 10, GAValues.LV);
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            FuelRecipes.registerSemiFluidGeneratorFuel(Fluids.SEED_OIL.getFluid(640), 10, GAValues.LV);
        else FuelRecipes.registerSemiFluidGeneratorFuel(SeedOil.getFluid(640), 10, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(Creosote.getFluid(160), 10, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(Biomass.getFluid(160), 10, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilLight.getFluid(320), 50, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilMedium.getFluid(640), 150, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(OilHeavy.getFluid(160), 50, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(SulfuricHeavyFuel.getFluid(160), 50, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(HeavyFuel.getFluid(80), 150, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(CoalTar.getFluid(320), 10, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(CoalTarOil.getFluid(160), 10, GAValues.LV);
        FuelRecipes.registerSemiFluidGeneratorFuel(SulfuricCoalTarOil.getFluid(120), 10, GAValues.LV);

        //rocket fuel
        registerRocketFuel(RocketFuelH8N4C2O4.getFluid(3), 160, GAValues.EV);
        registerRocketFuel(RocketFuelCN3H7O3.getFluid(6), 120, GAValues.EV);
        registerRocketFuel(DenseHydrazineFuelMixture.getFluid(9), 80, GAValues.EV);
        registerRocketFuel(RP1RocketFuel.getFluid(12), 40, GAValues.EV);

        FuelRecipes.registerGasGeneratorFuel(NaquadahGas.getFluid(1), 24000, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(LightNaquadahFuel.getFluid(1), 10000, GAValues.LV);
        FuelRecipes.registerGasGeneratorFuel(LightENaquadahFuel.getFluid(1), 25000, GAValues.LV);
        registerNaquadahReactorFuel(HeavyNaquadahFuel.getFluid(1), 40000, GAValues.LV);
        registerNaquadahReactorFuel(MediumNaquadahFuel.getFluid(1), 20000, GAValues.LV);
        registerNaquadahReactorFuel(HeavyENaquadahFuel.getFluid(1), 60000, GAValues.LV);
        registerNaquadahReactorFuel(MediumENaquadahFuel.getFluid(1), 40000, GAValues.LV);
        registerHyperReactorFuel(HyperFuelI.getFluid(1), 400, GAValues.IV);
        registerHyperReactorFuel(HyperFuelII.getFluid(1), 600, GAValues.IV);
        registerHyperReactorFuel(HyperFuelIII.getFluid(1), 800, GAValues.IV);

        //Qubit generator
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(1).qubit(1).input(OrePrefix.circuit, MarkerMaterials.Tier.Elite).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(4).qubit(1).input(OrePrefix.circuit, MarkerMaterials.Tier.Master).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(15).qubit(1).input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(50).qubit(1).input(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(200).qubit(1).input(OrePrefix.circuit, MarkerMaterials.Tier.Infinite).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(800).qubit(1).input(OrePrefix.circuit, UEV).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(3200).qubit(1).input(OrePrefix.circuit, UIV).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(12800).qubit(1).input(OrePrefix.circuit, UMV).buildAndRegister();
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder().EUt(GAValues.V[GAValues.UV]).duration(51200).qubit(1).input(OrePrefix.circuit, UXV).buildAndRegister();
    }

    //Register Methods
    public static void registerPlasmaFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.PLASMA_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    //Register Methods
    public static void registerNaquadahReactorFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.NAQUADAH_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerHyperReactorFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.HYPER_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerRocketFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.ROCKET_FUEL_RECIPES.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    private static void removeFuelRecipe(FuelRecipeMap map, FluidStack fluidStack) {
        map.removeRecipe(map.findRecipe(Integer.MAX_VALUE, fluidStack));
    }
}
