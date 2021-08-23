package gregicadditions.recipes.categories.handlers;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregicadditions.recipes.helper.HelperMethods;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.circuit;
import static gregtech.loaders.recipe.FuelRecipes.*;

/**
 * Class for Fuel Recipe additions/overrides.
 *
 * Fuel recipes do not have to be removed to be overridden, simply register
 * a new Fuel recipe here and it will automatically overwrite the original.
 */
public class FuelHandler {

    /**
     * This effectively overrides GT Materials to use Forestry ones if the mod is loaded. By not specifying
     * "Materials." before the material name, it will default to this one instead. For example:
     *
     *     registerDieselGeneratorFuel(Ethanol.getFluid(2), 12, LV);
     *
     * This looks like the standard material, but it will use the Forestry fluid if Forestry is loaded,
     * and if Forestry Integration is enabled.
     */
    private static HelperMethods.GenericFluid Ethanol = new HelperMethods.GenericFluid(Materials.Ethanol);
    private static HelperMethods.GenericFluid SeedOil = new HelperMethods.GenericFluid(Materials.SeedOil);

    public static void init() {

        if (Loader.isModLoaded(MODID_FR) && GAConfig.Misc.ForestryIntegration) {
            Ethanol = new HelperMethods.GenericFluid(Fluids.BIO_ETHANOL);
            SeedOil = new HelperMethods.GenericFluid(Fluids.SEED_OIL);
        }

        // Steam Turbine
        registerSteamGeneratorFuel(Steam.getFluid(640), 10, LV);

        // Nuclear Reactor Turbine
        //registerHotCoolantTurbineFuel(getHotFluid(Steam,                570), Steam,                1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(Deuterium,            240), Deuterium,            1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(SodiumPotassiumAlloy, 120), SodiumPotassiumAlloy, 1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(Sodium,               100), Sodium,               1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(FLiNaK,                50), FLiNaK,               1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(FLiBe,                 55), FLiBe,                1, EV);
        //registerHotCoolantTurbineFuel(getHotFluid(LeadBismuthEutectic,   60), LeadBismuthEutectic,  1, EV);

        registerHotCoolantTurbineFuel(SupercriticalSteam               .getFluid(570), Steam,                10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalDeuterium           .getFluid(240), Deuterium,            10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalSodiumPotassiumAlloy.getFluid(120), SodiumPotassiumAlloy, 10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalSodium              .getFluid(100), Sodium,               10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalFLiNaK              .getFluid( 50), FLiNaK,               10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalFLiBe               .getFluid( 55), FLiBe,                10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalLeadBismuthEutectic .getFluid( 60), LeadBismuthEutectic,  10000, EV);

        // Gas Turbine Fuels
        registerGasGeneratorFuel(NaquadahGas       .getFluid( 1),  8000, LV);
        registerGasGeneratorFuel(LightNaquadahFuel .getFluid( 1),  4000, LV);
        registerGasGeneratorFuel(LightENaquadahFuel.getFluid( 1), 10000, LV);


        // Plasma Generator
        registerPlasmaFuel(Radon     .getPlasma(1), 6144, LV);
        registerPlasmaFuel(Carbon    .getPlasma(1), 6144, LV);
        registerPlasmaFuel(Neon      .getPlasma(1), 3072, LV);
        registerPlasmaFuel(Magnesium .getPlasma(1), 3072, LV);
        registerPlasmaFuel(Silicon   .getPlasma(1), 2731, LV);
        registerPlasmaFuel(Sulfur    .getPlasma(1), 2731, LV);
        registerPlasmaFuel(Argon     .getPlasma(1), 2731, LV);
        registerPlasmaFuel(Calcium   .getPlasma(1), 2560, LV);
        registerPlasmaFuel(Titanium44.getPlasma(1), 2560, LV);
        registerPlasmaFuel(Chromium48.getPlasma(1), 2560, LV);
        registerPlasmaFuel(Titanium  .getPlasma(1), 2560, LV);
        registerPlasmaFuel(Iron52    .getPlasma(1), 2560, LV);
        registerPlasmaFuel(Nickel56  .getPlasma(1), 2560, LV);
        registerPlasmaFuel(HeliumCNO .getPlasma(1), 3840, LV);

        // Semi-Fluid Fuels
        registerSemiFluidGeneratorFuel(CoalTar.getFluid(320),  10, LV);

        // Rocket Fuel
        registerRocketFuel(RocketFuelH8N4C2O4       .getFluid( 3), 160, EV);
        registerRocketFuel(RocketFuelCN3H7O3        .getFluid( 6), 120, EV);
        registerRocketFuel(DenseHydrazineFuelMixture.getFluid( 9),  80, EV);
        registerRocketFuel(RP1RocketFuel            .getFluid(12),  40, EV);
        registerRocketFuel(RocketFuel               .getFluid(15),  20, EV);

        // Naquadah Reactor
        registerNaquadahReactorFuel(HeavyENaquadahFuel .getFluid(1), 25000, LV);
        registerNaquadahReactorFuel(MediumENaquadahFuel.getFluid(1), 17500, LV);
        registerNaquadahReactorFuel(HeavyNaquadahFuel  .getFluid(1), 17500, LV);
        registerNaquadahReactorFuel(MediumNaquadahFuel .getFluid(1),  7500, LV);

        // Hyper Reactor Fuels
        registerHyperReactorFuel(HyperFuelI  .getFluid(1),  300, IV);
        registerHyperReactorFuel(HyperFuelII .getFluid(1),  500, IV);
        registerHyperReactorFuel(HyperFuelIII.getFluid(1),  700, IV);
        registerHyperReactorFuel(HyperFuelIV .getFluid(1), 6000, UV);

        // Qubit Generator
        registerQubitGeneratorFuel(circuit, Elite,           1, UV);
        registerQubitGeneratorFuel(circuit, Master,          4, UV);
        registerQubitGeneratorFuel(circuit, Ultimate,       15, UV);
        registerQubitGeneratorFuel(circuit, Superconductor, 50, UV);
        registerQubitGeneratorFuel(circuit, Infinite,      200, UV);
        registerQubitGeneratorFuel(circuit, Ultra,         800, UV);
        registerQubitGeneratorFuel(circuit, Insane,       3200, UV);
        registerQubitGeneratorFuel(circuit, UMVCircuit,  12800, UV);
        registerQubitGeneratorFuel(circuit, UXVCircuit,  51200, UV);
        registerQubitGeneratorFuel(circuit, Maximum,    102400, UV);
    }
}
