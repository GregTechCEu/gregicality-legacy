package gregicadditions.machines;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.energy.GAMetaTileEntityDiode;
import gregicadditions.machines.energy.GAMetaTileEntityTransformer;
import gregicadditions.machines.energy.TileEntityLargeTransformer;
import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.ConverterType;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregicadditions.machines.multi.*;
import gregicadditions.machines.multi.advance.*;
import gregicadditions.machines.multi.advance.hyper.*;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityCentralMonitor;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregicadditions.machines.multi.drill.MetaTileEntityFluidDrillingPlant;
import gregicadditions.machines.multi.impl.MetaTileEntityRotorHolderForNuclearCoolant;
import gregicadditions.machines.multi.mega.MetaTileEntityMegaBlastFurnace;
import gregicadditions.machines.multi.mega.MetaTileEntityMegaDistillationTower;
import gregicadditions.machines.multi.mega.MetaTileEntityMegaVacuumFreezer;
import gregicadditions.machines.multi.miner.*;
import gregicadditions.machines.multi.multiblockpart.GAMetaTileEntityEnergyHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityOutputFilteredHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityQubitHatch;
import gregicadditions.machines.multi.miner.MetaTileEntityChunkMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityLargeMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.machines.multi.multiblockpart.*;
import gregicadditions.machines.multi.nuclear.*;
import gregicadditions.machines.multi.override.*;
import gregicadditions.machines.multi.qubit.*;
import gregicadditions.machines.multi.simple.*;
import gregicadditions.machines.multi.uumatter.*;
import gregicadditions.machines.overrides.*;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.electric.MetaTileEntityAirCollector;
import gregtech.common.metatileentities.electric.MetaTileEntityPump;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static gregicadditions.client.ClientHandler.*;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.*;

public class GATileEntities {
    public static MTE<?>[] DEHYDRATOR = new MTE[14];
    public static MTE<?>[] DECAY_CHAMBER = new MTE[14];
    public static MTE<?>[] GREEN_HOUSE = new MTE[14];
    public static SimpleGeneratorMetaTileEntity[] NAQUADAH_REACTOR = new SimpleGeneratorMetaTileEntity[8];
    public static SimpleGeneratorMetaTileEntity[] ROCKET_GENERATOR = new SimpleGeneratorMetaTileEntity[8];
    public static MetaTileEntityRockBreaker[] ROCK_BREAKER = new MetaTileEntityRockBreaker[8];
    public static ListMultimap<EnergyConverterType, MetaTileEntityEnergyConverter> ENERGY_CONVERTER = ArrayListMultimap.create();
    public static MetaTileEntityRotorHolderForNuclearCoolant[] ROTOR_HOLDER = new MetaTileEntityRotorHolderForNuclearCoolant[4];
    public static TileEntityBuffer[] BUFFER = new TileEntityBuffer[3];
    //multiblock
    public static MetaTileEntityMonitorScreen MONITOR_SCREEN;
    public static MetaTileEntityCentralMonitor CENTRAL_MONITOR;
    public static TileEntityAssemblyLine ASSEMBLY_LINE;
    public static TileEntityLargeThermalCentrifuge LARGE_THERMAL_CENTRIFUGE;
    public static TileEntityLargeElectrolyzer LARGE_ELECTROLYZER;
    public static TileEntityLargeCentrifuge LARGE_CENTRIFUGE;
    public static TileEntityLargeCutting LARGE_CUTTING;
    public static TileEntityLargeMacerator LARGE_MACERATOR;
    public static TileEntityLargeMixer LARGE_MIXER;
    public static TileEntityLargeBenderAndForming LARGE_BENDER_AND_FORMING;
    public static TileEntityLargeSifter LARGE_SIFTER;
    public static TileEntityLargeWashingPlant LARGE_WASHING_PLANT;
    public static TileEntityLargeWiremill LARGE_WIREMILL;
    public static TileEntityLargeExtruder LARGE_EXTRUDER;
    public static TileEntityLargeAssembler LARGE_ASSEMBLER;
    public static TileEntityLargeCircuitAssemblyLine LARGE_CIRCUIT_ASSEMBLY_LINE;
    public static MetaTileEntityLargeMiner[] LARGE_MINER = new MetaTileEntityLargeMiner[3];
    public static MetaTileEntityVoidMiner[] VOID_MINER = new MetaTileEntityVoidMiner[3];
    public static TileEntityLargeTransformer LARGE_TRANSFORMER;
    public static MetaTileEntityAdvancedDistillationTower ADVANCED_DISTILLATION_TOWER;
    public static MetaTileEntityLargeRocketEngine LARGE_ROCKET_ENGINE;
    public static TileEntityAlloyBlastFurnace ALLOY_BLAST_FURNACE;
    public static TileEntityLargeForgeHammer LARGE_FORGE_HAMMER;
    public static MetaTileEntityLargeNaquadahReactor LARGE_NAQUADAH_REACTOR;
    public static MetaTileEntityHyperReactorI HYPER_REACTOR_I;
    public static MetaTileEntityHyperReactorII HYPER_REACTOR_II;
    public static MetaTileEntityHyperReactorIII HYPER_REACTOR_III;
    public static MetaTileEntityBatteryTower BATTERY_TOWER;
    public static MetaTileEntityAdvFusionReactor ADVANCED_FUSION_REACTOR;
    public static GAMetaTileEntityHull[] GA_HULLS = new GAMetaTileEntityHull[5]; // todo remove, carefully
    public static MetaTileEntityStellarForge STELLAR_FORGE;
    public static MetaTileEntityQubitComputer QUBIT_COMPUTER;
    public static MetaTileEntitySolarSampler SOLAR_FLUID_SAMPLER;
    public static MetaTileEntityFluidDrillingPlant[] FLUID_DRILLING_PLANT = new MetaTileEntityFluidDrillingPlant[3];

    public static MetaTileEntityBioReactor BIO_REACTOR;
    public static TileEntityLargePackager LARGE_PACKAGER;
    public static MetaTileEntityCosmicRayDetector COSMIC_RAY_DETECTOR;
    //Nuclear
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;
    public static MetaTileEntityNuclearReactor NUCLEAR_BREEDER;
    public static MetaTileEntityGasCentrifuge GAS_CENTRIFUGE;
    public static TileEntityLargeLaserEngraver LARGE_LASER_ENGRAVER;

    //multiblock
    public static List<MetaTileEntityOutputFilteredHatch> OUTPUT_HATCH_FILTERED = new ArrayList<>();
    public static List<MetaTileEntityMultiFluidHatch> INPUT_HATCH_MULTI = new ArrayList<>();
    public static List<MetaTileEntityMultiFluidHatch> OUTPUT_HATCH_MULTI = new ArrayList<>();

    //override from GTCE
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_INPUT_HATCH_4_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_INPUT_HATCH_16_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_INPUT_HATCH_64_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_INPUT_HATCH_128_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_OUTPUT_HATCH_16_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_OUTPUT_HATCH_32_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_OUTPUT_HATCH_64_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityEnergyHatch> ENERGY_OUTPUT_HATCH_128_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityTransformer> TRANSFORMER_1_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityTransformer> TRANSFORMER_4_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityTransformer> TRANSFORMER_8_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityTransformer> TRANSFORMER_12_AMPS = new ArrayList<>();
    public static List<GAMetaTileEntityTransformer> TRANSFORMER_16_AMPS = new ArrayList<>();
    public static GAMetaTileEntityBatteryBuffer[][] BATTERY_BUFFERS = new GAMetaTileEntityBatteryBuffer[6][4];
    public static GAMetaTileEntityCharger[] CHARGER = new GAMetaTileEntityCharger[6];

    //optical fiber
    public static MetaTileEntityQubitHatch[] QBIT_INPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];
    public static MetaTileEntityQubitHatch[] QBIT_OUTPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];

    public static MetaTileEntityLargeTurbine LARGE_STEAM_TURBINE;
    public static MetaTileEntityHotCoolantTurbine HOT_COOLANT_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_GAS_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_PLASMA_TURBINE;

    public static SteamPump STEAM_PUMP;
    public static TileEntitySteamMixer STEAM_MIXER;
    public static SimpleMachineMetaTileEntity SIMPLE_ORE_WASHER;

    public static MetaTileEntityPump[] PUMP = new MetaTileEntityPump[8];
    public static MetaTileEntityAirCollector[] AIR_COLLECTOR = new MetaTileEntityAirCollector[8];
    public static TileEntityWorldAccelerator[] WORLD_ACCELERATOR = new TileEntityWorldAccelerator[8];
    public static MetaTileEntityChunkMiner[] MINER = new MetaTileEntityChunkMiner[3];

    public static GAMetaTileEntityEnergyHatch[] ENERGY_INPUT = new GAMetaTileEntityEnergyHatch[5];
    public static GAMetaTileEntityEnergyHatch[] ENERGY_OUTPUT = new GAMetaTileEntityEnergyHatch[5];

    public static List<GAMetaTileEntityDiode> DIODES = new ArrayList<>();

    public static MetaTileEntityPlasmaCondenser PLASMA_CONDENSER;

    public static MTE<?>[] DISASSEMBLER = new MTE[14];
    public static MetaTileEntityElectricImplosion ELECTRIC_IMPLOSION;
    public static MetaTileEntityMufflerHatch[] MUFFLER_HATCH = new MetaTileEntityMufflerHatch[8];

    public static TileEntitySteamMiner STEAM_MINER;
    public static TileEntityAdvancedChemicalReactor ADVANCED_CHEMICAL_REACTOR;
    public static TileEntityLargeBrewery LARGE_BREWERY;
    public static TileEntityLargeElectromagnet LARGE_ELECTROMAGNET;
    public static TileEntityLargeExtractor LARGE_EXTRACTOR;
    public static TileEntityLargeArcFurnace LARGE_ARC_FURNACE;
    public static TileEntityLargeCanningMachine LARGE_CANNING_MACHINE;
    public static TileEntityLargeMassFabricator LARGE_MASS_FABRICATOR;
    public static TileEntityLargeReplicator LARGE_REPLICATOR;

    public static MetaTileEntityMegaDistillationTower MEGA_DISTILLATION_TOWER;
    public static MetaTileEntityMegaBlastFurnace MEGA_BLAST_FURNACE;
    public static MetaTileEntityMegaVacuumFreezer MEGA_VACUUM_FREEZER;

    public static MetaTileEntityMaintenanceHatch[] MAINTENANCE_HATCH = new MetaTileEntityMaintenanceHatch[3];

    public static void init() {

        // todo need to send a message to CEu about our configs for high-tier machines

        MONITOR_SCREEN = GregTechAPI.registerMetaTileEntity(1999, new MetaTileEntityMonitorScreen(location("monitor_screen")));

        LARGE_CIRCUIT_ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2004, new TileEntityLargeCircuitAssemblyLine(location("large_circuit_assembly")));

        if (GAConfig.Misc.naqEfficieny) {
            NAQUADAH_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2173, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk2"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 5, 100));
            NAQUADAH_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2174, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk3"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6, 80));
            NAQUADAH_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2191, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk4"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7, 50));
        } else {
            NAQUADAH_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2173, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk2"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 5));
            NAQUADAH_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2174, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk3"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6));
            NAQUADAH_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2191, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk4"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7));

        }

        CENTRAL_MONITOR = GregTechAPI.registerMetaTileEntity(2499, new MetaTileEntityCentralMonitor(location("central_monitor")));

        ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2502, new TileEntityAssemblyLine(location("assembly_line")));

        LARGE_THERMAL_CENTRIFUGE = GregTechAPI.registerMetaTileEntity(2508, new TileEntityLargeThermalCentrifuge(location("large_thermal_centrifuge")));
        LARGE_ELECTROLYZER = GregTechAPI.registerMetaTileEntity(2509, new TileEntityLargeElectrolyzer(location("large_electrolyzer")));
        LARGE_BENDER_AND_FORMING = GregTechAPI.registerMetaTileEntity(2510, new TileEntityLargeBenderAndForming(location("large_bender_and_forming"), RecipeMaps.BENDER_RECIPES));
        LARGE_CENTRIFUGE = GregTechAPI.registerMetaTileEntity(2512, new TileEntityLargeCentrifuge(location("large_centrifuge")));
        LARGE_CUTTING = GregTechAPI.registerMetaTileEntity(2513, new TileEntityLargeCutting(location("large_cutting")));
        LARGE_MACERATOR = GregTechAPI.registerMetaTileEntity(2514, new TileEntityLargeMacerator(location("large_macerator")));
        LARGE_MIXER = GregTechAPI.registerMetaTileEntity(2515, new TileEntityLargeMixer(location("large_mixer")));
        LARGE_SIFTER = GregTechAPI.registerMetaTileEntity(2526, new TileEntityLargeSifter(location("large_sifter")));
        LARGE_WASHING_PLANT = GregTechAPI.registerMetaTileEntity(2527, new TileEntityLargeWashingPlant(location("large_washing_plant"), RecipeMaps.ORE_WASHER_RECIPES));
        LARGE_WIREMILL = GregTechAPI.registerMetaTileEntity(2529, new TileEntityLargeWiremill(location("large_wiremill")));
        LARGE_EXTRUDER = GregTechAPI.registerMetaTileEntity(2531, new TileEntityLargeExtruder(location("large_extruder")));
        LARGE_ASSEMBLER = GregTechAPI.registerMetaTileEntity(2533, new TileEntityLargeAssembler(location("large_assembler")));

        LARGE_STEAM_TURBINE = GregTechAPI.registerMetaTileEntity(2541, new MetaTileEntityLargeTurbine(location("large_turbine.steam"), MetaTileEntityLargeTurbine.TurbineType.valueOf("STEAM_OVERRIDE")));
        LARGE_GAS_TURBINE = GregTechAPI.registerMetaTileEntity(2542, new MetaTileEntityLargeTurbine(location("large_turbine.gas"), MetaTileEntityLargeTurbine.TurbineType.valueOf("GAS_OVERRIDE")));
        LARGE_PLASMA_TURBINE = GregTechAPI.registerMetaTileEntity(2543, new MetaTileEntityLargeTurbine(location("large_turbine.plasma"), MetaTileEntityLargeTurbine.TurbineType.valueOf("PLASMA_OVERRIDE")));
        HOT_COOLANT_TURBINE = GregTechAPI.registerMetaTileEntity(2544, new MetaTileEntityHotCoolantTurbine(location("large_turbine.hot_coolant"), MetaTileEntityHotCoolantTurbine.TurbineType.HOT_COOLANT));

        NUCLEAR_REACTOR = GregTechAPI.registerMetaTileEntity(2545, new MetaTileEntityNuclearReactor(location("nuclear_reactor"), GARecipeMaps.NUCLEAR_REACTOR_RECIPES));
        NUCLEAR_BREEDER = GregTechAPI.registerMetaTileEntity(2546, new MetaTileEntityNuclearReactor(location("nuclear_breeder"), GARecipeMaps.NUCLEAR_BREEDER_RECIPES));

        LARGE_MINER[0] = GregTechAPI.registerMetaTileEntity(2548, new MetaTileEntityLargeMiner(location("miner.basic"), Miner.Type.BASIC));

        LARGE_MINER[1] = GregTechAPI.registerMetaTileEntity(2549, new MetaTileEntityLargeMiner(location("miner.large"), Miner.Type.LARGE));

        LARGE_MINER[2] = GregTechAPI.registerMetaTileEntity(2550, new MetaTileEntityLargeMiner(location("miner.advance"), Miner.Type.ADVANCE));

        VOID_MINER[0] = GregTechAPI.registerMetaTileEntity(2551, new MetaTileEntityVoidMiner(location("void_miner"), GAValues.UV, GAConfig.multis.voidMiner.maxTemp));
        LARGE_TRANSFORMER = GregTechAPI.registerMetaTileEntity(2552, new TileEntityLargeTransformer(location("large_transformer")));
        ADVANCED_DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(2554, new MetaTileEntityAdvancedDistillationTower(location("advanced_distillation_tower"), RecipeMaps.DISTILLERY_RECIPES));
        GregTechAPI.registerMetaTileEntity(2555, new MetaTileEntityAdvancedDistillationTower(location("advanced_distillation_tower"), RecipeMaps.DISTILLATION_RECIPES));
        LARGE_ROCKET_ENGINE = GregTechAPI.registerMetaTileEntity(2558, new MetaTileEntityLargeRocketEngine(location("large_rocket_engine")));
        ALLOY_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(2559, new TileEntityAlloyBlastFurnace(location("alloy_blast_furnace")));
        LARGE_FORGE_HAMMER = GregTechAPI.registerMetaTileEntity(2560, new TileEntityLargeForgeHammer(location("large_forge_hammer")));
        LARGE_NAQUADAH_REACTOR = GregTechAPI.registerMetaTileEntity(2561, new MetaTileEntityLargeNaquadahReactor(location("large_naquadah_reactor")));
        BATTERY_TOWER = GregTechAPI.registerMetaTileEntity(2562, new MetaTileEntityBatteryTower(location("battery_tower")));

        if (GAConfig.GT5U.highTierPumps) {
            PUMP[4] = GregTechAPI.registerMetaTileEntity(2201, new MetaTileEntityPump(location("pump.iv"), 5));
            PUMP[5] = GregTechAPI.registerMetaTileEntity(2202, new MetaTileEntityPump(location("pump.luv"), 6));
            PUMP[6] = GregTechAPI.registerMetaTileEntity(2203, new MetaTileEntityPump(location("pump.zpm"), 7));
            PUMP[7] = GregTechAPI.registerMetaTileEntity(2204, new MetaTileEntityPump(location("pump.uv"), 8));
        }

        if (GAConfig.Misc.highTierCollector) {
            AIR_COLLECTOR[4] = GregTechAPI.registerMetaTileEntity(2205, new MetaTileEntityAirCollector(location("air_collector.iv"), 5));
            AIR_COLLECTOR[5] = GregTechAPI.registerMetaTileEntity(2206, new MetaTileEntityAirCollector(location("air_collector.luv"), 6));
        }

        WORLD_ACCELERATOR[0] = GregTechAPI.registerMetaTileEntity(2213, new TileEntityWorldAccelerator(location("world_accelerator.lv"), 1));
        WORLD_ACCELERATOR[1] = GregTechAPI.registerMetaTileEntity(2214, new TileEntityWorldAccelerator(location("world_accelerator.mv"), 2));
        WORLD_ACCELERATOR[2] = GregTechAPI.registerMetaTileEntity(2215, new TileEntityWorldAccelerator(location("world_accelerator.hv"), 3));
        WORLD_ACCELERATOR[3] = GregTechAPI.registerMetaTileEntity(2216, new TileEntityWorldAccelerator(location("world_accelerator.ev"), 4));
        if (GAConfig.GT5U.highTierWorldAccelerator) {
            WORLD_ACCELERATOR[4] = GregTechAPI.registerMetaTileEntity(2217, new TileEntityWorldAccelerator(location("world_accelerator.iv"), 5));
            WORLD_ACCELERATOR[5] = GregTechAPI.registerMetaTileEntity(2218, new TileEntityWorldAccelerator(location("world_accelerator.luv"), 6));
            WORLD_ACCELERATOR[6] = GregTechAPI.registerMetaTileEntity(2219, new TileEntityWorldAccelerator(location("world_accelerator.zpm"), 7));
            WORLD_ACCELERATOR[7] = GregTechAPI.registerMetaTileEntity(2220, new TileEntityWorldAccelerator(location("world_accelerator.uv"), 8));
        }
        MINER[0] = GregTechAPI.registerMetaTileEntity(2221, new MetaTileEntityChunkMiner(location("miner.lv"), Miner.Type.LV, 1));
        MINER[1] = GregTechAPI.registerMetaTileEntity(2222, new MetaTileEntityChunkMiner(location("miner.mv"), Miner.Type.MV, 2));
        MINER[2] = GregTechAPI.registerMetaTileEntity(2223, new MetaTileEntityChunkMiner(location("miner.hv"), Miner.Type.HV, 3));
        STEAM_PUMP = GregTechAPI.registerMetaTileEntity(2232, new SteamPump(location("pump.steam")));
        STEAM_MIXER = GregTechAPI.registerMetaTileEntity(2235, new TileEntitySteamMixer(location("steam_mixer")));

        if (GAConfig.Misc.rocketEfficiency) {
            ROCKET_GENERATOR[3] = GregTechAPI.registerMetaTileEntity(2236, new SimpleGeneratorWithLossMetaTileEntity(location("rocket_generator.mk1"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 4, 80));
            ROCKET_GENERATOR[4] = GregTechAPI.registerMetaTileEntity(2237, new SimpleGeneratorWithLossMetaTileEntity(location("rocket_generator.mk2"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 5, 70));
            ROCKET_GENERATOR[5] = GregTechAPI.registerMetaTileEntity(2238, new SimpleGeneratorWithLossMetaTileEntity(location("rocket_generator.mk3"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 6, 60));
        } else {
            ROCKET_GENERATOR[3] = GregTechAPI.registerMetaTileEntity(2236, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk1"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 4));
            ROCKET_GENERATOR[4] = GregTechAPI.registerMetaTileEntity(2237, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk2"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 5));
            ROCKET_GENERATOR[5] = GregTechAPI.registerMetaTileEntity(2238, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk3"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 6));
        }
        if (GAConfig.Misc.dieselEfficiency) {
            MetaTileEntities.DIESEL_GENERATOR[0] = GregTechAPI.registerMetaTileEntity(2239, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("diesel_generator.lv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, 1, 100));
            MetaTileEntities.DIESEL_GENERATOR[1] = GregTechAPI.registerMetaTileEntity(2240, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("diesel_generator.mv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, 2, 90));
            MetaTileEntities.DIESEL_GENERATOR[2] = GregTechAPI.registerMetaTileEntity(2241, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("diesel_generator.hv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, 3, 80));
        }

        if (GAConfig.Misc.steamEfficiency) {
            MetaTileEntities.STEAM_TURBINE[0] = GregTechAPI.registerMetaTileEntity(2242, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("steam_turbine.lv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 1, 100));
            MetaTileEntities.STEAM_TURBINE[1] = GregTechAPI.registerMetaTileEntity(2243, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("steam_turbine.mv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 2, 90));
            MetaTileEntities.STEAM_TURBINE[2] = GregTechAPI.registerMetaTileEntity(2244, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("steam_turbine.hv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, 3, 80));
        }

        if (GAConfig.Misc.gasEfficiency) {
            MetaTileEntities.GAS_TURBINE[0] = GregTechAPI.registerMetaTileEntity(2245, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("gas_turbine.lv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 1, 100));
            MetaTileEntities.GAS_TURBINE[1] = GregTechAPI.registerMetaTileEntity(2246, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("gas_turbine.mv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 2, 90));
            MetaTileEntities.GAS_TURBINE[2] = GregTechAPI.registerMetaTileEntity(2247, new SimpleGeneratorWithLossMetaTileEntity(gregtechId("gas_turbine.hv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, 3, 80));
        }

        DEHYDRATOR[0] = create(2248, new SimpleMachineMetaTileEntity(location("dehydrator.lv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 1));
        DEHYDRATOR[1] = create(2249, new SimpleMachineMetaTileEntity(location("dehydrator.mv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 2));
        DEHYDRATOR[2] = create(2250, new SimpleMachineMetaTileEntity(location("dehydrator.hv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 3));
        DEHYDRATOR[3] = create(2251, new SimpleMachineMetaTileEntity(location("dehydrator.ev"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 4));
        if (GAConfig.GT5U.highTierChemicalDehydrator) {
            DEHYDRATOR[4] = create(2252, new SimpleMachineMetaTileEntity(location("dehydrator.iv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 5));
            DEHYDRATOR[5] = create(2253, new SimpleMachineMetaTileEntity(location("dehydrator.luv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 6));
            DEHYDRATOR[6] = create(2254, new SimpleMachineMetaTileEntity(location("dehydrator.zpm"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 7));
            DEHYDRATOR[7] = create(2255, new SimpleMachineMetaTileEntity(location("dehydrator.uv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 8));
            DEHYDRATOR[8] = create(3439, new GASimpleMachineMetaTileEntity(location("dehydrator.uhv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 9));
            DEHYDRATOR[9] = create(3440, new GASimpleMachineMetaTileEntity(location("dehydrator.uev"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 10));
            DEHYDRATOR[10] = create(3441, new GASimpleMachineMetaTileEntity(location("dehydrator.uiv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 11));
            DEHYDRATOR[11] = create(3442, new GASimpleMachineMetaTileEntity(location("dehydrator.umv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 12));
            DEHYDRATOR[12] = create(3443, new GASimpleMachineMetaTileEntity(location("dehydrator.uxv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 13));

        }

        SIMPLE_ORE_WASHER = GregTechAPI.registerMetaTileEntity(2256, new SimpleMachineMetaTileEntity(location("simple_ore_washer"), GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 1));

        int id = 2900;
        for (final ConverterType t : ConverterType.values()) {
            for (int tier = t.getMinTier(); tier < t.getMaxTier(); ++tier) {
                for (int value : GAConfig.EnergyConversion.values) {
                    final String vn = GTValues.VN[tier].toLowerCase();
                    ENERGY_CONVERTER.put(t.getGTEUToForgeType(), GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyConverter(location(t.getGTEUToForgeType() + "." + vn + "." + value), tier, t.getGTEUToForgeType(), value)));
                    ENERGY_CONVERTER.put(t.getForgeToGTEUType(), GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyConverter(location(t.getForgeToGTEUType() + "." + vn + "." + value), tier, t.getForgeToGTEUType(), value)));
                }
            }
        }

        for (int i = 0; i < GTValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier
            if (i > 0) {
                TRANSFORMER_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".4"), i, 4, 16)));
                TRANSFORMER_8_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".8"), i, 8, 32)));
                TRANSFORMER_12_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".12"), i, 12, 48)));
                TRANSFORMER_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".16"), i, 16, 64)));
            }
        }
        for (int i = 0; i < GTValues.V.length - 1; i++) {
            ENERGY_INPUT_HATCH_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".4"), i, 4, false)));
            ENERGY_INPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".16"), i, 16, false)));
            ENERGY_INPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".64"), i, 64, false)));
            ENERGY_INPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".128"), i, 128, false)));

            ENERGY_OUTPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".16"), i, 16, true)));
            ENERGY_OUTPUT_HATCH_32_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".32"), i, 32, true)));
            ENERGY_OUTPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".64"), i, 64, true)));
            ENERGY_OUTPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".128"), i, 128, true)));
        }

        //        DECAY_CHAMBER[0] = GregTechAPI.registerMetaTileEntity(3200, new SimpleMachineMetaTileEntity(location("decay_chamber.lv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 1));
        DECAY_CHAMBER[1] = create(3201, new SimpleMachineMetaTileEntity(location("decay_chamber.mv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 2));
        DECAY_CHAMBER[2] = create(3202, new SimpleMachineMetaTileEntity(location("decay_chamber.hv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 3));
        DECAY_CHAMBER[3] = create(3203, new SimpleMachineMetaTileEntity(location("decay_chamber.ev"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 4));
        if (GAConfig.GT5U.highTierDecayChamber) {
            DECAY_CHAMBER[4] = create(3204, new SimpleMachineMetaTileEntity(location("decay_chamber.iv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 5));
            DECAY_CHAMBER[5] = create(3205, new SimpleMachineMetaTileEntity(location("decay_chamber.luv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 6));
            DECAY_CHAMBER[6] = create(3206, new SimpleMachineMetaTileEntity(location("decay_chamber.zpm"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 7));
            DECAY_CHAMBER[7] = create(3207, new SimpleMachineMetaTileEntity(location("decay_chamber.uv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 8));
            DECAY_CHAMBER[8] = create(3454, new GASimpleMachineMetaTileEntity(location("decay_chamber.uhv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 9));
            DECAY_CHAMBER[9] = create(3455, new GASimpleMachineMetaTileEntity(location("decay_chamber.uev"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 10));
            DECAY_CHAMBER[10] = create(3456, new GASimpleMachineMetaTileEntity(location("decay_chamber.uiv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 11));
            DECAY_CHAMBER[11] = create(3457, new GASimpleMachineMetaTileEntity(location("decay_chamber.umv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 12));
            DECAY_CHAMBER[12] = create(3458, new GASimpleMachineMetaTileEntity(location("decay_chamber.uxv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 13));
        }

        ROTOR_HOLDER[0] = GregTechAPI.registerMetaTileEntity(3208, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.hv"), GTValues.HV, 1.1f));
        ROTOR_HOLDER[1] = GregTechAPI.registerMetaTileEntity(3209, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.luv"), GTValues.LuV, 1.35f));
        ROTOR_HOLDER[2] = GregTechAPI.registerMetaTileEntity(3210, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.uhv"), GAValues.UHV, 1.7f));

        GREEN_HOUSE[0] = create(3211, new SimpleMachineMetaTileEntity(location("green_house.lv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 1));
        GREEN_HOUSE[1] = create(3212, new SimpleMachineMetaTileEntity(location("green_house.mv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 2));
        GREEN_HOUSE[2] = create(3213, new SimpleMachineMetaTileEntity(location("green_house.hv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 3));
        GREEN_HOUSE[3] = create(3214, new SimpleMachineMetaTileEntity(location("green_house.ev"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 4));
        if (GAConfig.GT5U.highTierGreenHouse) {
            GREEN_HOUSE[4] = create(3215, new SimpleMachineMetaTileEntity(location("green_house.iv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 5));
            GREEN_HOUSE[5] = create(3216, new SimpleMachineMetaTileEntity(location("green_house.luv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 6));
            GREEN_HOUSE[6] = create(3217, new SimpleMachineMetaTileEntity(location("green_house.zpm"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 7));
            GREEN_HOUSE[7] = create(3218, new SimpleMachineMetaTileEntity(location("green_house.uv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 8));
            GREEN_HOUSE[8] = create(3459, new GASimpleMachineMetaTileEntity(location("green_house.uhv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 9));
            GREEN_HOUSE[9] = create(3460, new GASimpleMachineMetaTileEntity(location("green_house.uev"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 10));
            GREEN_HOUSE[10] = create(3461, new GASimpleMachineMetaTileEntity(location("green_house.uiv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 11));
            GREEN_HOUSE[11] = create(3462, new GASimpleMachineMetaTileEntity(location("green_house.umv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 12));
            GREEN_HOUSE[12] = create(3463, new GASimpleMachineMetaTileEntity(location("green_house.uxv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 13));

        }

        id = 3220;
        for (int i = 0; i < GTValues.V.length; i++) {
            OUTPUT_HATCH_FILTERED.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityOutputFilteredHatch(location("fluid_hatch.export_filtered." + GTValues.VN[i].toLowerCase()), i == 9 ? GAValues.MAX : i)));
        }

        BUFFER[0] = GregTechAPI.registerMetaTileEntity(3230, new TileEntityBuffer(location("buffer.lv"), 3));
        BUFFER[1] = GregTechAPI.registerMetaTileEntity(3231, new TileEntityBuffer(location("buffer.mv"), 4));
        BUFFER[2] = GregTechAPI.registerMetaTileEntity(3232, new TileEntityBuffer(location("buffer.hv"), 5));

        HYPER_REACTOR_I = GregTechAPI.registerMetaTileEntity(3234, new MetaTileEntityHyperReactorI(location("hyper_reactor.i"), GAConfig.multis.hyperReactors.euGeneration[0]));
        HYPER_REACTOR_II = GregTechAPI.registerMetaTileEntity(3235, new MetaTileEntityHyperReactorII(location("hyper_reactor.ii"), GAConfig.multis.hyperReactors.euGeneration[1]));
        HYPER_REACTOR_III = GregTechAPI.registerMetaTileEntity(3236, new MetaTileEntityHyperReactorIII(location("hyper_reactor.iii"), GAConfig.multis.hyperReactors.euGeneration[2]));
        ADVANCED_FUSION_REACTOR = GregTechAPI.registerMetaTileEntity(3237, new MetaTileEntityAdvFusionReactor(location("advanced_fusion_reactor")));
        VOID_MINER[1] = GregTechAPI.registerMetaTileEntity(4018, new MetaTileEntityVoidMiner(location("void_miner.uhv"), GAValues.UHV, GAConfig.multis.voidMiner.maxTempUHV));
        VOID_MINER[2] = GregTechAPI.registerMetaTileEntity(4019, new MetaTileEntityVoidMiner(location("void_miner.uev"), GAValues.UEV, GAConfig.multis.voidMiner.maxTempUEV));
        STELLAR_FORGE = GregTechAPI.registerMetaTileEntity(4021, new MetaTileEntityStellarForge(location("stellar_forge")));
        if (GAConfig.Misc.enableRockBreaker) {
            ROCK_BREAKER[0] = GregTechAPI.registerMetaTileEntity(4000, new MetaTileEntityRockBreaker(location("rock_breaker.lv"), 1));
            ROCK_BREAKER[1] = GregTechAPI.registerMetaTileEntity(4001, new MetaTileEntityRockBreaker(location("rock_breaker.mv"), 2));
            ROCK_BREAKER[2] = GregTechAPI.registerMetaTileEntity(4002, new MetaTileEntityRockBreaker(location("rock_breaker.hv"), 3));
            ROCK_BREAKER[3] = GregTechAPI.registerMetaTileEntity(4003, new MetaTileEntityRockBreaker(location("rock_breaker.ev"), 4));
        }
        if (GAConfig.Misc.enableRockBreakerHighTier) {
            ROCK_BREAKER[4] = GregTechAPI.registerMetaTileEntity(4004, new MetaTileEntityRockBreaker(location("rock_breaker.iv"), 5));
            ROCK_BREAKER[5] = GregTechAPI.registerMetaTileEntity(4005, new MetaTileEntityRockBreaker(location("rock_breaker.luv"), 6));
            ROCK_BREAKER[6] = GregTechAPI.registerMetaTileEntity(4006, new MetaTileEntityRockBreaker(location("rock_breaker.zpm"), 7));
            ROCK_BREAKER[7] = GregTechAPI.registerMetaTileEntity(4007, new MetaTileEntityRockBreaker(location("rock_breaker.uv"), 8));
        }

        QBIT_INPUT_HATCH[0] = GregTechAPI.registerMetaTileEntity(4016, new MetaTileEntityQubitHatch(location("qubit_hatch.input.16"), 0, 16, false));
        QBIT_OUTPUT_HATCH[0] = GregTechAPI.registerMetaTileEntity(4017, new MetaTileEntityQubitHatch(location("qubit_hatch.output.1"), 0, 1, true));
        GAS_CENTRIFUGE = GregTechAPI.registerMetaTileEntity(4020, new MetaTileEntityGasCentrifuge(location("gas_centrifuge")));
        QUBIT_COMPUTER = GregTechAPI.registerMetaTileEntity(4022, new MetaTileEntityQubitComputer(location("qubit_computer")));


        SOLAR_FLUID_SAMPLER = GregTechAPI.registerMetaTileEntity(4024, new MetaTileEntitySolarSampler(location("solar_fluid_sampler")));

        ENERGY_INPUT[0] = GregTechAPI.registerMetaTileEntity(4025, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input.uhv"), GAValues.UHV, 2, false));
        ENERGY_INPUT[1] = GregTechAPI.registerMetaTileEntity(4026, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input.uev"), GAValues.UEV, 2, false));
        ENERGY_INPUT[2] = GregTechAPI.registerMetaTileEntity(4027, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input.uiv"), GAValues.UIV, 2, false));
        ENERGY_INPUT[3] = GregTechAPI.registerMetaTileEntity(4028, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input.umv"), GAValues.UMV, 2, false));
        ENERGY_INPUT[4] = GregTechAPI.registerMetaTileEntity(4029, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input.uxv"), GAValues.UXV, 2, false));

        ENERGY_OUTPUT[0] = GregTechAPI.registerMetaTileEntity(4030, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output.uhv"), GAValues.UHV, 4, true));
        ENERGY_OUTPUT[1] = GregTechAPI.registerMetaTileEntity(4031, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output.uev"), GAValues.UEV, 4, true));
        ENERGY_OUTPUT[2] = GregTechAPI.registerMetaTileEntity(4032, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output.uiv"), GAValues.UIV, 4, true));
        ENERGY_OUTPUT[3] = GregTechAPI.registerMetaTileEntity(4033, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output.umv"), GAValues.UMV, 4, true));
        ENERGY_OUTPUT[4] = GregTechAPI.registerMetaTileEntity(4034, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output.uxv"), GAValues.UXV, 4, true));

        id = 4035;
        for (int i = 9; i < GAValues.V.length - 1; i++) {
            ENERGY_INPUT_HATCH_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GAValues.VN[i].toLowerCase() + ".4"), i, 4, false)));
            ENERGY_INPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GAValues.VN[i].toLowerCase() + ".16"), i, 16, false)));
            ENERGY_INPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GAValues.VN[i].toLowerCase() + ".64"), i, 64, false)));
            ENERGY_INPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.input." + GAValues.VN[i].toLowerCase() + ".128"), i, 128, false)));

            ENERGY_OUTPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GAValues.VN[i].toLowerCase() + ".16"), i, 16, true)));
            ENERGY_OUTPUT_HATCH_32_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GAValues.VN[i].toLowerCase() + ".32"), i, 32, true)));
            ENERGY_OUTPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GAValues.VN[i].toLowerCase() + ".64"), i, 64, true)));
            ENERGY_OUTPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityEnergyHatch(location("energy_hatch.output." + GAValues.VN[i].toLowerCase() + ".128"), i, 128, true)));
        }
        for (final ConverterType t : ConverterType.values()) {
            for (int tier = t.getMaxTier(); tier < GAValues.V.length - 1; ++tier) {
                for (int value : GAConfig.EnergyConversion.values) {
                    final String vn = GAValues.VN[tier].toLowerCase();
                    Long voltage = (long) (GAValues.V[tier] * value * GAConfig.EnergyConversion.RATIO);
                    if (voltage.compareTo((long) Integer.MAX_VALUE) > 0) continue;
                    ENERGY_CONVERTER.put(t.getGTEUToForgeType(), GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyConverter(location(t.getGTEUToForgeType() + "." + vn + "." + value), tier, t.getGTEUToForgeType(), value)));
                    ENERGY_CONVERTER.put(t.getForgeToGTEUType(), GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyConverter(location(t.getForgeToGTEUType() + "." + vn + "." + value), tier, t.getForgeToGTEUType(), value)));
                }
            }
        }
        for (int i = 9; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier
            TRANSFORMER_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase() + ".4"), i, 4, 16)));
            TRANSFORMER_8_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase() + ".8"), i, 8, 32)));
            TRANSFORMER_12_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase() + ".12"), i, 12, 48)));
            TRANSFORMER_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase() + ".16"), i, 16, 64)));
        }
        id = 4127;

        LARGE_LASER_ENGRAVER = GregTechAPI.registerMetaTileEntity(4137, new TileEntityLargeLaserEngraver(location("large_laser_engraver")));
        BATTERY_BUFFERS[5][0] = GregTechAPI.registerMetaTileEntity(4138, new GAMetaTileEntityBatteryBuffer(gregtechId("battery_buffer.max.1"), GAValues.MAX, 1));
        BATTERY_BUFFERS[5][1] = GregTechAPI.registerMetaTileEntity(4139, new GAMetaTileEntityBatteryBuffer(gregtechId("battery_buffer.max.4"), GAValues.MAX, 4));
        BATTERY_BUFFERS[5][2] = GregTechAPI.registerMetaTileEntity(4140, new GAMetaTileEntityBatteryBuffer(gregtechId("battery_buffer.max.9"), GAValues.MAX, 9));
        BATTERY_BUFFERS[5][3] = GregTechAPI.registerMetaTileEntity(4141, new GAMetaTileEntityBatteryBuffer(gregtechId("battery_buffer.max.16"), GAValues.MAX, 16));
        CHARGER[5] = GregTechAPI.registerMetaTileEntity(4142, new GAMetaTileEntityCharger(gregtechId("charger.max"), GAValues.MAX, 4));
        id = 4145;
        for (int i = 0; i < BATTERY_BUFFERS.length - 1; i++) {
            for (int j = 0; j < BATTERY_BUFFERS[i].length; j++) {
                BATTERY_BUFFERS[i][j] = GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityBatteryBuffer(location("battery_buffer." + GAValues.VN[i + 9].toLowerCase() + "." + (int) Math.pow(j + 1, 2)), i + 9, (int) Math.pow(j + 1, 2)));
            }
            CHARGER[i] = GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityCharger(location("charger." + GAValues.VN[i + 9].toLowerCase()), i + 9, 4));
        }
        BIO_REACTOR = GregTechAPI.registerMetaTileEntity(4170, new MetaTileEntityBioReactor(location("bio_reactor")));
        PLASMA_CONDENSER = GregTechAPI.registerMetaTileEntity(4171, new MetaTileEntityPlasmaCondenser(location("plasma_condenser")));
        LARGE_PACKAGER = GregTechAPI.registerMetaTileEntity(4172, new TileEntityLargePackager(location("large_packager"), RecipeMaps.PACKER_RECIPES));
        COSMIC_RAY_DETECTOR = GregTechAPI.registerMetaTileEntity(4174, new MetaTileEntityCosmicRayDetector(location("cosmic_ray_detector")));

        id = 4179;
        for (int i = 9; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier
            TRANSFORMER_1_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase()), i, 1, 4)));
        }
        id = 4184;
        for (int i = 1; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier, plus one because we dont want ULV
            DIODES.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityDiode(location("diode." + GAValues.VN[i].toLowerCase()), i)));
        }

        if (GAConfig.Misc.enableDisassembly) {
            id = 4198;
            for (int i = 1; i < GAValues.V.length - 1; i++) {
                final int tier = i; // used for inner class
                DISASSEMBLER[i] = create(id++, new GASimpleMachineMetaTileEntity(location("disassembler." + GAValues.VN[i].toLowerCase()), GARecipeMaps.DISASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, i) {
                    @Override
                    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
                        tooltip.add(I18n.format("gtadditions.machine.disassembler.tooltip", GAValues.VOLTAGE_NAMES[tier]));
                        super.addInformation(stack, player, tooltip, advanced);
                    }
                });
            }
        }
        ELECTRIC_IMPLOSION = GregTechAPI.registerMetaTileEntity(4211, new MetaTileEntityElectricImplosion(location("electric_implosion")));
        STEAM_MINER = GregTechAPI.registerMetaTileEntity(4212, new TileEntitySteamMiner(location("steam_miner")));

        MAINTENANCE_HATCH[0] = GregTechAPI.registerMetaTileEntity(4213, new MetaTileEntityMaintenanceHatch(location("maintenance_hatch"), 1));
        MAINTENANCE_HATCH[1] = GregTechAPI.registerMetaTileEntity(4214, new MetaTileEntityMaintenanceHatch(location("auto_maintenance_hatch"), 5));
        MAINTENANCE_HATCH[2] = GregTechAPI.registerMetaTileEntity(4215, new MetaTileEntityMaintenanceHatch(location("fullauto_maintenance_hatch"), 9));

        id = 4215;
        for (int i = 1; i <= MUFFLER_HATCH.length; i++)
            MUFFLER_HATCH[i - 1] = GregTechAPI.registerMetaTileEntity(id + i, new MetaTileEntityMufflerHatch(location("muffler_hatch." + GAValues.VN[i].toLowerCase()), i));

        ADVANCED_CHEMICAL_REACTOR = GregTechAPI.registerMetaTileEntity(4224, new TileEntityAdvancedChemicalReactor(location("advanced_chemical_reactor")));
        LARGE_BREWERY = GregTechAPI.registerMetaTileEntity(4225, new TileEntityLargeBrewery(location("large_brewery"), RecipeMaps.BREWING_RECIPES));
        LARGE_ELECTROMAGNET = GregTechAPI.registerMetaTileEntity(4226, new TileEntityLargeElectromagnet(location("large_electromagnet"), RecipeMaps.POLARIZER_RECIPES));
        LARGE_EXTRACTOR = GregTechAPI.registerMetaTileEntity(4227, new TileEntityLargeExtractor(location("large_extractor"), RecipeMaps.FLUID_EXTRACTION_RECIPES));
        LARGE_ARC_FURNACE = GregTechAPI.registerMetaTileEntity(4228, new TileEntityLargeArcFurnace(location("large_arc_furnace"), RecipeMaps.ARC_FURNACE_RECIPES));
        LARGE_CANNING_MACHINE = GregTechAPI.registerMetaTileEntity(4229, new TileEntityLargeCanningMachine(location("large_canning_machine"), RecipeMaps.CANNER_RECIPES));
        LARGE_MASS_FABRICATOR = GregTechAPI.registerMetaTileEntity(4230, new TileEntityLargeMassFabricator(location("large_mass_fabricator"), GARecipeMaps.MASS_FAB_RECIPES));
        LARGE_REPLICATOR = GregTechAPI.registerMetaTileEntity(4231, new TileEntityLargeReplicator(location("large_replicator"), GARecipeMaps.REPLICATOR_RECIPES));
        MEGA_DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(4232, new MetaTileEntityMegaDistillationTower(location("mega_distillation_tower")));
        MEGA_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(4233, new MetaTileEntityMegaBlastFurnace(location("mega_blast_furnace")));
        MEGA_VACUUM_FREEZER = GregTechAPI.registerMetaTileEntity(4234, new MetaTileEntityMegaVacuumFreezer(location("mega_vacuum_freezer")));

        INPUT_HATCH_MULTI.add(GregTechAPI.registerMetaTileEntity(4235, new MetaTileEntityMultiFluidHatch(location("multi_fluid_input_4x"), 2, false)));
        INPUT_HATCH_MULTI.add(GregTechAPI.registerMetaTileEntity(4236, new MetaTileEntityMultiFluidHatch(location("multi_fluid_input_9x"), 3, false)));

        OUTPUT_HATCH_MULTI.add(GregTechAPI.registerMetaTileEntity(4237, new MetaTileEntityMultiFluidHatch(location("multi_fluid_output_4x"), 2, true)));
        OUTPUT_HATCH_MULTI.add(GregTechAPI.registerMetaTileEntity(4238, new MetaTileEntityMultiFluidHatch(location("multi_fluid_output_9x"), 3, true)));

        FLUID_DRILLING_PLANT[0] = GregTechAPI.registerMetaTileEntity(4239, new MetaTileEntityFluidDrillingPlant(location("fluid_drilling_plant_mv"), 2));
        FLUID_DRILLING_PLANT[1] = GregTechAPI.registerMetaTileEntity(4240, new MetaTileEntityFluidDrillingPlant(location("fluid_drilling_plant_hv"), 3));
        FLUID_DRILLING_PLANT[2] = GregTechAPI.registerMetaTileEntity(4241, new MetaTileEntityFluidDrillingPlant(location("fluid_drilling_plant_ev"), 4));
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> MTE<T> create(int id, T sampleMetaTileEntity) {
        return new MTE<>(GregTechAPI.registerMetaTileEntity(id, sampleMetaTileEntity));
    }

    public static class MTE<T extends MetaTileEntity & ITieredMetaTileEntity> {

        private final T t;

        MTE(T t) {
            this.t = t;
        }

        public MetaTileEntity getMetaTileEntity() {
            return t;
        }

        public ITieredMetaTileEntity getITieredMetaTileEntity() {
            return t;
        }
    }


    public static ResourceLocation location(String name) {
        return new ResourceLocation(Gregicality.MODID, name);
    }

    private static ResourceLocation gregtechId(String name) {
        return new ResourceLocation(GTValues.MODID, name);
    }
}
