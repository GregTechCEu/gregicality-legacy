package gregicadditions.recipes.categories.handlers;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.fluid.GAMetaFluids.getHotFluid;
import static gregtech.api.GTValues.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.circuit;

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
    private static GenericFluid Ethanol = new GenericFluid(Materials.Ethanol);
    private static GenericFluid SeedOil = new GenericFluid(Materials.SeedOil);

    public static void init() {

        if (Loader.isModLoaded(MODID_FR) && GAConfig.Misc.ForestryIntegration) {
            Ethanol = new GenericFluid(Fluids.BIO_ETHANOL);
            SeedOil = new GenericFluid(Fluids.SEED_OIL);
        }

        // Steam Turbine
        registerSteamGeneratorFuel(Steam.getFluid(640), 10, LV);

        // Nuclear Reactor Turbine
        registerHotCoolantTurbineFuel(getHotFluid(Steam,                570), Steam,                1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(Deuterium,            240), Deuterium,            1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(SodiumPotassiumAlloy, 120), SodiumPotassiumAlloy, 1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(Sodium,               100), Sodium,               1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(FLiNaK,                50), FLiNaK,               1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(FLiBe,                 55), FLiBe,                1, EV);
        registerHotCoolantTurbineFuel(getHotFluid(LeadBismuthEutectic,   60), LeadBismuthEutectic,  1, EV);

        registerHotCoolantTurbineFuel(SupercriticalSteam               .getFluid(570), Steam,                10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalDeuterium           .getFluid(240), Deuterium,            10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalSodiumPotassiumAlloy.getFluid(120), SodiumPotassiumAlloy, 10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalSodium              .getFluid(100), Sodium,               10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalFLiNaK              .getFluid( 50), FLiNaK,               10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalFLiBe               .getFluid( 55), FLiBe,                10000, EV);
        registerHotCoolantTurbineFuel(SupercriticalLeadBismuthEutectic .getFluid( 60), LeadBismuthEutectic,  10000, EV);

        // Gas Turbine Fuels
        registerGasGeneratorFuel(NaturalGas        .getFluid(20),    13, LV);
        registerGasGeneratorFuel(Hydrogen          .getFluid(20),    1, LV);
        registerGasGeneratorFuel(CarbonMonoxde     .getFluid(20),    15, LV);
        registerGasGeneratorFuel(WoodGas           .getFluid(20),    15, LV);
        registerGasGeneratorFuel(SulfuricNaphtha   .getFluid( 8),    10, LV);
        registerGasGeneratorFuel(Ethylene          .getFluid( 5),    20, LV);
        registerGasGeneratorFuel(Gas               .getFluid( 2),    10, LV);
        registerGasGeneratorFuel(Propene           .getFluid(10),    60, LV);
        registerGasGeneratorFuel(Butene            .getFluid(10),    80, LV);
        registerGasGeneratorFuel(Phenol            .getFluid(10),    90, LV);
        registerGasGeneratorFuel(Benzene           .getFluid(10),    90, LV);
        registerGasGeneratorFuel(NaquadahGas       .getFluid( 1),  8000, LV);
        registerGasGeneratorFuel(LightNaquadahFuel .getFluid( 1),  4000, LV);
        registerGasGeneratorFuel(LightENaquadahFuel.getFluid( 1), 10000, LV);

        // Diesel Generator Fluids
        registerDieselGeneratorFuel(Oil               .getFluid(20),   10, LV);
        registerDieselGeneratorFuel(SulfuricLightFuel .getFluid( 8),   10, LV);
        registerDieselGeneratorFuel(BioDiesel         .getFluid( 2),   14, LV);
        registerDieselGeneratorFuel(LightFuel         .getFluid(32),  305, LV);
        registerDieselGeneratorFuel(Gasoline          .getFluid( 8),  135, LV);
        registerDieselGeneratorFuel(HighOctaneGasoline.getFluid(10),  562, LV);
        registerDieselGeneratorFuel(Octane            .getFluid(20),   45, LV);
        registerDieselGeneratorFuel(Ethanol           .getFluid( 2),   12, LV);


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
        registerSemiFluidGeneratorFuel(FishOil           .getFluid(640),  10, LV);
        registerSemiFluidGeneratorFuel(SeedOil           .getFluid(640),  10, LV);
        registerSemiFluidGeneratorFuel(Creosote          .getFluid(160),  10, LV);
        registerSemiFluidGeneratorFuel(Biomass           .getFluid(160),  10, LV);
        registerSemiFluidGeneratorFuel(OilLight          .getFluid(320),  50, LV);
        registerSemiFluidGeneratorFuel(OilMedium         .getFluid(640), 150, LV);
        registerSemiFluidGeneratorFuel(OilHeavy          .getFluid(160),  50, LV);
        registerSemiFluidGeneratorFuel(SulfuricHeavyFuel .getFluid(160),  50, LV);
        registerSemiFluidGeneratorFuel(HeavyFuel         .getFluid( 80), 150, LV);
        registerSemiFluidGeneratorFuel(CoalTar           .getFluid(320),  10, LV);
        registerSemiFluidGeneratorFuel(CoalTarOil        .getFluid(160),  10, LV);
        registerSemiFluidGeneratorFuel(SulfuricCoalTarOil.getFluid(120),  10, LV);

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
        registerQubitGeneratorFuel(circuit, UEV,           800, UV);
        registerQubitGeneratorFuel(circuit, UIV,          3200, UV);
        registerQubitGeneratorFuel(circuit, UMV,         12800, UV);
        registerQubitGeneratorFuel(circuit, UXV,         51200, UV);
    }
}
