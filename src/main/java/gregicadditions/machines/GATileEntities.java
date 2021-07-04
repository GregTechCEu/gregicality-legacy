package gregicadditions.machines;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregicadditions.client.ClientHandler;
import gregicadditions.machines.energy.GAMetaTileEntityDiode;
import gregicadditions.machines.energy.GAMetaTileEntityTransformer;
import gregicadditions.machines.energy.TileEntityLargeTransformer;
import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.ConverterType;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregicadditions.machines.multi.*;
import gregicadditions.machines.multi.advance.*;
import gregicadditions.machines.multi.advance.hyper.HyperReactor;
import gregicadditions.machines.multi.advance.hyper.HyperReactorUEV;
import gregicadditions.machines.multi.advance.hyper.HyperReactorUHV;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityCentralMonitor;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityMonitorScreen;
import gregicadditions.machines.multi.impl.MetaTileEntityRotorHolderForNuclearCoolant;
import gregicadditions.machines.multi.miner.MetaTileEntityChunkMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityLargeMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.machines.multi.multiblockpart.GAMetaTileEntityEnergyHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityOutputFilteredHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityQubitHatch;
import gregicadditions.machines.multi.nuclear.MetaTileEntityGasCentrifuge;
import gregicadditions.machines.multi.nuclear.MetaTileEntityHotCoolantTurbine;
import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
import gregicadditions.machines.multi.override.*;
import gregicadditions.machines.multi.qubit.MetaTileEntityQubitComputer;
import gregicadditions.machines.multi.simple.*;
import gregicadditions.machines.multi.steam.MetaTileEntitySteamGrinder;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntitySteamHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntitySteamItemBus;
import gregicadditions.machines.multi.steam.MetaTileEntitySteamOven;
import gregicadditions.machines.overrides.*;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTLog;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.electric.MetaTileEntityAirCollector;
import gregtech.common.metatileentities.electric.MetaTileEntityPump;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GATileEntities {

    public static SimpleMachineMetaTileEntity[] CIRCUITASSEMBLER = new SimpleMachineMetaTileEntity[4];
    public static MTE<?>[] CLUSTERMILL = new MTE[14];
    public static MTE<?>[] ELECTRIC_FURNACE = new MTE[14];
    public static MTE<?>[] MACERATOR = new MTE[14];
    public static MTE<?>[] ALLOY_SMELTER = new MTE[14];
    public static MTE<?>[] ARC_FURNACE = new MTE[14];
    public static MTE<?>[] ASSEMBLER = new MTE[14];
    public static MTE<?>[] AUTOCLAVE = new MTE[14];
    public static MTE<?>[] BENDER = new MTE[14];
    public static MTE<?>[] BREWERY = new MTE[14];
    public static MTE<?>[] CANNER = new MTE[14];
    public static MTE<?>[] CENTRIFUGE = new MTE[14];
    public static MTE<?>[] CHEMICAL_BATH = new MTE[14];
    public static MTE<?>[] CHEMICAL_REACTOR = new MTE[14];
    public static MTE<?>[] COMPRESSOR = new MTE[14];
    public static MTE<?>[] CUTTER = new MTE[14];
    public static MTE<?>[] DISTILLERY = new MTE[14];
    public static MTE<?>[] ELECTROLYZER = new MTE[14];
    public static MTE<?>[] ELECTROMAGNETIC_SEPARATOR = new MTE[14];
    public static MTE<?>[] EXTRACTOR = new MTE[14];
    public static MTE<?>[] EXTRUDER = new MTE[14];
    public static MTE<?>[] FERMENTER = new MTE[14];
    public static MTE<?>[] FLUID_CANNER = new MTE[14];
    public static MTE<?>[] FLUID_EXTRACTOR = new MTE[14];
    public static MTE<?>[] FLUID_HEATER = new MTE[14];
    public static MTE<?>[] FLUID_SOLIDIFIER = new MTE[14];
    public static MTE<?>[] FORGE_HAMMER = new MTE[14];
    public static MTE<?>[] FORMING_PRESS = new MTE[14];
    public static MTE<?>[] LATHE = new MTE[14];
    public static MTE<?>[] MICROWAVE = new MTE[14];
    public static MTE<?>[] MIXER = new MTE[14];
    public static MTE<?>[] ORE_WASHER = new MTE[14];
    public static MTE<?>[] PACKER = new MTE[14];
    public static MTE<?>[] UNPACKER = new MTE[14];
    public static MTE<?>[] PLASMA_ARC_FURNACE = new MTE[14];
    public static MTE<?>[] POLARIZER = new MTE[14];
    public static MTE<?>[] LASER_ENGRAVER = new MTE[14];
    public static MTE<?>[] SIFTER = new MTE[14];
    public static MTE<?>[] THERMAL_CENTRIFUGE = new MTE[14];
    public static MTE<?>[] WIREMILL = new MTE[14];
    public static MTE<?>[] DEHYDRATOR = new MTE[14];
    public static MTE<?>[] REPLICATOR = new MTE[14];
    public static MTE<?>[] MASS_FAB = new MTE[14];
    public static MTE<?>[] DECAY_CHAMBER = new MTE[14];
    public static MTE<?>[] GREEN_HOUSE = new MTE[14];
    public static SimpleGeneratorMetaTileEntity[] NAQUADAH_REACTOR = new SimpleGeneratorMetaTileEntity[8];
    public static SimpleGeneratorMetaTileEntity[] ROCKET_GENERATOR = new SimpleGeneratorMetaTileEntity[8];
    public static MetaTileEntityRockBreaker[] ROCK_BREAKER = new MetaTileEntityRockBreaker[8];
    public static TileEntityFusionReactor[] FUSION_REACTOR = new TileEntityFusionReactor[3];
    public static ListMultimap<EnergyConverterType, MetaTileEntityEnergyConverter> ENERGY_CONVERTER = ArrayListMultimap.create();
    public static MetaTileEntityRotorHolderForNuclearCoolant[] ROTOR_HOLDER = new MetaTileEntityRotorHolderForNuclearCoolant[4];
    public static TileEntityBuffer[] BUFFER = new TileEntityBuffer[3];
    //multiblock
    public static MetaTileEntityMonitorScreen MONITOR_SCREEN;
    public static MetaTileEntityCentralMonitor CENTRAL_MONITOR;
    public static TileEntityAssemblyLine ASSEMBLY_LINE;
    public static TileEntityProcessingArray PROCESSING_ARRAY;
    public static TileEntityLargeThermalCentrifuge LARGE_THERMAL_CENTRIFUGE;
    public static TileEntityLargeElectrolyzer LARGE_ELECTROLYZER;
    public static TileEntityLargeCentrifuge LARGE_CENTRIFUGE;
    public static TileEntityLargeCutting LARGE_CUTTING;
    public static TileEntityLargeMacerator LARGE_MACERATOR;
    public static TileEntityLargeMixer LARGE_MIXER;
    public static TileEntityLargeMultiUse LARGE_MULTI_USE;
    public static TileEntityLargeBenderAndForming LARGE_BENDER_AND_FORMING;
    public static TileEntityLargeSifter LARGE_SIFTER;
    public static TileEntityLargeWashingPlant LARGE_WASHING_PLANT;
    public static TileEntityLargeWiremill LARGE_WIREMILL;
    public static TileEntityLargeChemicalReactor LARGE_CHEMICAL_REACTOR;
    public static TileEntityLargeExtruder LARGE_EXTRUDER;
    public static TileEntityVolcanus VOLCANUS;
    public static TileEntityLargeAssembler LARGE_ASSEMBLER;
    public static TileEntityLargeCircuitAssemblyLine LARGE_CIRCUIT_ASSEMBLY_LINE;
    public static MetaTileEntityLargeMiner[] LARGE_MINER = new MetaTileEntityLargeMiner[3];
    public static MetaTileEntityVoidMiner[] VOID_MINER = new MetaTileEntityVoidMiner[3];
    public static TileEntityLargeTransformer LARGE_TRANSFORMER;
    public static MetaTileEntityIndustrialPrimitiveBlastFurnace INDUSTRIAL_PRIMITIVE_BLAST_FURNACE;
    public static TileEntityAdvancedDistillationTower ADVANCED_DISTILLATION_TOWER;
    public static TileEntityCryogenicFreezer CRYOGENIC_FREEZER;
    public static MetaTileEntityChemicalPlant CHEMICAL_PLANT;
    public static MetaTileEntityLargeRocketEngine LARGE_ROCKET_ENGINE;
    public static TileEntityAlloyBlastFurnace ALLOY_BLAST_FURNACE;
    public static TileEntityLargeForgeHammer LARGE_FORGE_HAMMER;
    public static MetaTileEntityLargeNaquadahReactor LARGE_NAQUADAH_REACTOR;
    public static HyperReactor HYPER_REACTOR;
    public static HyperReactorUHV HYPER_REACTOR_UHV;
    public static HyperReactorUEV HYPER_REACTOR_UEV;
    public static MetaTileEntityBatteryTower BATTERY_TOWER;
    public static TileEntityAdvFusionReactor ADVANCED_FUSION_REACTOR;
    public static GAMetaTileEntityHull[] GA_HULLS = new GAMetaTileEntityHull[5];
    public static MetaTileEntityStellarForge STELLAR_FORGE;
    public static MetaTileEntityQubitComputer QUBIT_COMPUTER;
    public static MetaTileEntityDrillingRig DRILLING_RIG;
    public static MetaTileEntitySolarSampler SOLAR_FLUID_SAMPLER;
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

    // Steam Multis
    public static MetaTileEntitySteamHatch STEAM_HATCH;
    public static MetaTileEntitySteamItemBus STEAM_INPUT_BUS;
    public static MetaTileEntitySteamItemBus STEAM_OUTPUT_BUS;
    public static MetaTileEntitySteamGrinder STEAM_GRINDER;
    public static MetaTileEntitySteamOven STEAM_OVEN;

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
    public static MetaTileEntityElectricBlastFurnace ELECTRIC_BLAST_FURNACE;
    public static MetaTileEntityVacuumFreezer VACUUM_FREEZER;
    public static MetaTileEntityImplosionCompressor IMPLOSION_COMPRESSOR;
    public static MetaTileEntityDistillationTower DISTILLATION_TOWER;
    public static MetaTileEntityCrackingUnit CRACKER;
    public static MetaTileEntityMultiFurnace MULTI_FURNACE;
    public static MetaTileEntityDieselEngine DIESEL_ENGINE;
    public static MetaTileEntityPyrolyseOven PYROLYSE_OVEN;
    public static GAMetaTileEntityBatteryBuffer[][] BATTERY_BUFFERS = new GAMetaTileEntityBatteryBuffer[6][4];
    public static GAMetaTileEntityCharger[] CHARGER = new GAMetaTileEntityCharger[6];

    //optical fiber
    public static MetaTileEntityQubitHatch[] QBIT_INPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];
    public static MetaTileEntityQubitHatch[] QBIT_OUTPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];

    public static MetaTileEntityLargeTurbine LARGE_STEAM_TURBINE;
    public static MetaTileEntityHotCoolantTurbine HOT_COOLANT_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_GAS_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_PLASMA_TURBINE;

    public static MetaTileEntityDrum WOODEN_DRUM;
    public static MetaTileEntityDrum BRONZE_DRUM;
    public static MetaTileEntityDrum STEEL_DRUM;
    public static MetaTileEntityDrum STAINLESS_STEEL_DRUM;
    public static MetaTileEntityDrum TITANIUM_DRUM;
    public static MetaTileEntityDrum TUNGSTENSTEEL_DRUM;

    public static TileEntityCrate WOODEN_CRATE;
    public static TileEntityCrate BRONZE_CRATE;
    public static TileEntityCrate STEEL_CRATE;
    public static TileEntityCrate STAINLESS_STEEL_CRATE;
    public static TileEntityCrate TITANIUM_CRATE;
    public static TileEntityCrate TUNGSTENSTEEL_CRATE;

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

    public static List<GASimpleMachineMetaTileEntity> DISASSEMBLER = new ArrayList<>();

    public static MetaTileEntityCVDUnit CVD_UNIT;

    public static void init() {

        MONITOR_SCREEN = GregTechAPI.registerMetaTileEntity(1999, new MetaTileEntityMonitorScreen(location("monitor_screen")));

        CIRCUITASSEMBLER[0] = GregTechAPI.registerMetaTileEntity(2000, new SimpleMachineMetaTileEntity(location("circuit_assembler.lv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 1));
        CIRCUITASSEMBLER[1] = GregTechAPI.registerMetaTileEntity(2001, new SimpleMachineMetaTileEntity(location("circuit_assembler.mv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 2));
        CIRCUITASSEMBLER[2] = GregTechAPI.registerMetaTileEntity(2002, new SimpleMachineMetaTileEntity(location("circuit_assembler.hv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 3));
        CIRCUITASSEMBLER[3] = GregTechAPI.registerMetaTileEntity(2003, new SimpleMachineMetaTileEntity(location("circuit_assembler.ev"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 4));
        LARGE_CIRCUIT_ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2004, new TileEntityLargeCircuitAssemblyLine(location("large_circuit_assembly")));

        CLUSTERMILL[0] = create(2008, new SimpleMachineMetaTileEntity(location("cluster_mill.lv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 1));
        CLUSTERMILL[1] = create(2009, new SimpleMachineMetaTileEntity(location("cluster_mill.mv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 2));
        CLUSTERMILL[2] = create(2010, new SimpleMachineMetaTileEntity(location("cluster_mill.hv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 3));
        CLUSTERMILL[3] = create(2011, new SimpleMachineMetaTileEntity(location("cluster_mill.ev"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 4));
        if (GAConfig.GT5U.highTierClusterMills) {
            CLUSTERMILL[4] = create(2012, new SimpleMachineMetaTileEntity(location("cluster_mill.iv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 5));
            CLUSTERMILL[5] = create(2013, new SimpleMachineMetaTileEntity(location("cluster_mill.luv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 6));
            CLUSTERMILL[6] = create(2014, new SimpleMachineMetaTileEntity(location("cluster_mill.zpm"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 7));
            CLUSTERMILL[7] = create(2015, new SimpleMachineMetaTileEntity(location("cluster_mill.uv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 8));
            CLUSTERMILL[8] = create(3244, new GASimpleMachineMetaTileEntity(location("cluster_mill.uhv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 9));
            CLUSTERMILL[9] = create(3245, new GASimpleMachineMetaTileEntity(location("cluster_mill.uev"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 10));
            CLUSTERMILL[10] = create(3246, new GASimpleMachineMetaTileEntity(location("cluster_mill.uiv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 11));
            CLUSTERMILL[11] = create(3247, new GASimpleMachineMetaTileEntity(location("cluster_mill.umv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 12));
            CLUSTERMILL[12] = create(3248, new GASimpleMachineMetaTileEntity(location("cluster_mill.uxv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierElectricFurnace) {
            ELECTRIC_FURNACE[4] = create(2016, new SimpleMachineMetaTileEntity(location("electric_furnace.iv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 5));
            ELECTRIC_FURNACE[5] = create(2017, new SimpleMachineMetaTileEntity(location("electric_furnace.luv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 6));
            ELECTRIC_FURNACE[6] = create(2018, new SimpleMachineMetaTileEntity(location("electric_furnace.zpm"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 7));
            ELECTRIC_FURNACE[7] = create(2019, new SimpleMachineMetaTileEntity(location("electric_furnace.uv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 8));
            ELECTRIC_FURNACE[8] = create(3249, new GASimpleMachineMetaTileEntity(location("electric_furnace.uhv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 9));
            ELECTRIC_FURNACE[9] = create(3250, new GASimpleMachineMetaTileEntity(location("electric_furnace.uev"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 10));
            ELECTRIC_FURNACE[10] = create(3251, new GASimpleMachineMetaTileEntity(location("electric_furnace.uiv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 11));
            ELECTRIC_FURNACE[11] = create(3252, new GASimpleMachineMetaTileEntity(location("electric_furnace.umv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 12));
            ELECTRIC_FURNACE[12] = create(3253, new GASimpleMachineMetaTileEntity(location("electric_furnace.uxv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierMacerators) {
            MACERATOR[4] = create(2020, new SimpleMachineMetaTileEntity(location("macerator.iv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 5));
            MACERATOR[5] = create(2021, new SimpleMachineMetaTileEntity(location("macerator.luv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 6));
            MACERATOR[6] = create(2022, new SimpleMachineMetaTileEntity(location("macerator.zpm"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 7));
            MACERATOR[7] = create(2023, new SimpleMachineMetaTileEntity(location("macerator.uv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 8));
            MACERATOR[8] = create(3254, new GASimpleMachineMetaTileEntity(location("macerator.uhv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 9));
            MACERATOR[9] = create(3255, new GASimpleMachineMetaTileEntity(location("macerator.uev"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 10));
            MACERATOR[10] = create(3256, new GASimpleMachineMetaTileEntity(location("macerator.uiv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 11));
            MACERATOR[11] = create(3257, new GASimpleMachineMetaTileEntity(location("macerator.umv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 12));
            MACERATOR[12] = create(3258, new GASimpleMachineMetaTileEntity(location("macerator.uxv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierAlloySmelter) {
            ALLOY_SMELTER[4] = create(2024, new SimpleMachineMetaTileEntity(location("alloy_smelter.iv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 5));
            ALLOY_SMELTER[5] = create(2025, new SimpleMachineMetaTileEntity(location("alloy_smelter.luv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 6));
            ALLOY_SMELTER[6] = create(2026, new SimpleMachineMetaTileEntity(location("alloy_smelter.zpm"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 7));
            ALLOY_SMELTER[7] = create(2027, new SimpleMachineMetaTileEntity(location("alloy_smelter.uv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 8));
            ALLOY_SMELTER[8] = create(3259, new GASimpleMachineMetaTileEntity(location("alloy_smelter.uhv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 9));
            ALLOY_SMELTER[9] = create(3260, new GASimpleMachineMetaTileEntity(location("alloy_smelter.uev"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 10));
            ALLOY_SMELTER[10] = create(3261, new GASimpleMachineMetaTileEntity(location("alloy_smelter.uiv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 11));
            ALLOY_SMELTER[11] = create(3262, new GASimpleMachineMetaTileEntity(location("alloy_smelter.umv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 12));
            ALLOY_SMELTER[12] = create(3263, new GASimpleMachineMetaTileEntity(location("alloy_smelter.uxv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierArcFurnaces) {
            ARC_FURNACE[4] = create(2032, new SimpleMachineMetaTileEntity(location("arc_furnace.iv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 5));
            ARC_FURNACE[5] = create(2033, new SimpleMachineMetaTileEntity(location("arc_furnace.luv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 6));
            ARC_FURNACE[6] = create(2034, new SimpleMachineMetaTileEntity(location("arc_furnace.zpm"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 7));
            ARC_FURNACE[7] = create(2035, new SimpleMachineMetaTileEntity(location("arc_furnace.uv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 8));
            ARC_FURNACE[8] = create(3264, new GASimpleMachineMetaTileEntity(location("arc_furnace.uhv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 9));
            ARC_FURNACE[9] = create(3265, new GASimpleMachineMetaTileEntity(location("arc_furnace.uev"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 10));
            ARC_FURNACE[10] = create(3266, new GASimpleMachineMetaTileEntity(location("arc_furnace.uiv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 11));
            ARC_FURNACE[11] = create(3267, new GASimpleMachineMetaTileEntity(location("arc_furnace.umv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 12));
            ARC_FURNACE[12] = create(3268, new GASimpleMachineMetaTileEntity(location("arc_furnace.uxv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 13));
        }

        if (GAConfig.GT5U.highTierAssemblers) {
            ASSEMBLER[5] = create(2037, new SimpleMachineMetaTileEntity(location("assembler.luv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 6));
            ASSEMBLER[6] = create(2038, new SimpleMachineMetaTileEntity(location("assembler.zpm"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 7));
            ASSEMBLER[7] = create(2039, new SimpleMachineMetaTileEntity(location("assembler.uv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 8));
            ASSEMBLER[8] = create(3269, new GASimpleMachineMetaTileEntity(location("assembler.uhv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 9));
            ASSEMBLER[9] = create(3270, new GASimpleMachineMetaTileEntity(location("assembler.uev"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 10));
            ASSEMBLER[10] = create(3271, new GASimpleMachineMetaTileEntity(location("assembler.uiv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 11));
            ASSEMBLER[11] = create(3272, new GASimpleMachineMetaTileEntity(location("assembler.umv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 12));
            ASSEMBLER[12] = create(3273, new GASimpleMachineMetaTileEntity(location("assembler.uxv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierAutoclaves) {
            AUTOCLAVE[5] = create(2041, new SimpleMachineMetaTileEntity(location("autoclave.luv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 6));
            AUTOCLAVE[6] = create(2042, new SimpleMachineMetaTileEntity(location("autoclave.zpm"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 7));
            AUTOCLAVE[7] = create(2043, new SimpleMachineMetaTileEntity(location("autoclave.uv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 8));
            AUTOCLAVE[8] = create(3274, new GASimpleMachineMetaTileEntity(location("autoclave.uhv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 9));
            AUTOCLAVE[9] = create(3275, new GASimpleMachineMetaTileEntity(location("autoclave.uev"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 10));
            AUTOCLAVE[10] = create(3276, new GASimpleMachineMetaTileEntity(location("autoclave.uiv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 11));
            AUTOCLAVE[11] = create(3277, new GASimpleMachineMetaTileEntity(location("autoclave.umv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 12));
            AUTOCLAVE[12] = create(3278, new GASimpleMachineMetaTileEntity(location("autoclave.uxv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierBenders) {
            BENDER[4] = create(2044, new SimpleMachineMetaTileEntity(location("bender.iv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 5));
            BENDER[5] = create(2045, new SimpleMachineMetaTileEntity(location("bender.luv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 6));
            BENDER[6] = create(2046, new SimpleMachineMetaTileEntity(location("bender.zpm"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 7));
            BENDER[7] = create(2047, new SimpleMachineMetaTileEntity(location("bender.uv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 8));
            BENDER[8] = create(3279, new GASimpleMachineMetaTileEntity(location("bender.uhv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 9));
            BENDER[9] = create(3280, new GASimpleMachineMetaTileEntity(location("bender.uev"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 10));
            BENDER[10] = create(3281, new GASimpleMachineMetaTileEntity(location("bender.uiv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 11));
            BENDER[11] = create(3282, new GASimpleMachineMetaTileEntity(location("bender.umv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 12));
            BENDER[12] = create(3283, new GASimpleMachineMetaTileEntity(location("bender.uxv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierBreweries) {
            BREWERY[4] = create(2048, new SimpleMachineMetaTileEntity(location("brewery.iv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 5));
            BREWERY[5] = create(2049, new SimpleMachineMetaTileEntity(location("brewery.luv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 6));
            BREWERY[6] = create(2050, new SimpleMachineMetaTileEntity(location("brewery.zpm"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 7));
            BREWERY[7] = create(2051, new SimpleMachineMetaTileEntity(location("brewery.uv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 8));
            BREWERY[8] = create(3284, new GASimpleMachineMetaTileEntity(location("brewery.uhv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 9));
            BREWERY[9] = create(3285, new GASimpleMachineMetaTileEntity(location("brewery.uev"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 10));
            BREWERY[10] = create(3286, new GASimpleMachineMetaTileEntity(location("brewery.uiv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 11));
            BREWERY[11] = create(3287, new GASimpleMachineMetaTileEntity(location("brewery.umv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 12));
            BREWERY[12] = create(3288, new GASimpleMachineMetaTileEntity(location("brewery.uxv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierCanners) {
            CANNER[4] = create(2052, new SimpleMachineMetaTileEntity(location("canner.iv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 5));
            CANNER[5] = create(2053, new SimpleMachineMetaTileEntity(location("canner.luv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 6));
            CANNER[6] = create(2054, new SimpleMachineMetaTileEntity(location("canner.zpm"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 7));
            CANNER[7] = create(2055, new SimpleMachineMetaTileEntity(location("canner.uv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 8));
            CANNER[8] = create(3289, new GASimpleMachineMetaTileEntity(location("canner.uhv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 9));
            CANNER[9] = create(3290, new GASimpleMachineMetaTileEntity(location("canner.uev"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 10));
            CANNER[10] = create(3291, new GASimpleMachineMetaTileEntity(location("canner.uiv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 11));
            CANNER[11] = create(3292, new GASimpleMachineMetaTileEntity(location("canner.umv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 12));
            CANNER[12] = create(3293, new GASimpleMachineMetaTileEntity(location("canner.uxv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierCentrifuges) {
            CENTRIFUGE[4] = create(2056, new SimpleMachineMetaTileEntity(location("centrifuge.iv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 5));
            CENTRIFUGE[5] = create(2057, new SimpleMachineMetaTileEntity(location("centrifuge.luv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 6));
            CENTRIFUGE[6] = create(2058, new SimpleMachineMetaTileEntity(location("centrifuge.zpm"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 7));
            CENTRIFUGE[7] = create(2059, new SimpleMachineMetaTileEntity(location("centrifuge.uv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 8));
            CENTRIFUGE[8] = create(3294, new GASimpleMachineMetaTileEntity(location("centrifuge.uhv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 9));
            CENTRIFUGE[9] = create(3295, new GASimpleMachineMetaTileEntity(location("centrifuge.uev"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 10));
            CENTRIFUGE[10] = create(3296, new GASimpleMachineMetaTileEntity(location("centrifuge.uiv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 11));
            CENTRIFUGE[11] = create(3297, new GASimpleMachineMetaTileEntity(location("centrifuge.umv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 12));
            CENTRIFUGE[12] = create(3298, new GASimpleMachineMetaTileEntity(location("centrifuge.uxv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierChemicalBaths) {
            CHEMICAL_BATH[4] = create(2060, new SimpleMachineMetaTileEntity(location("chemical_bath.iv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 5));
            CHEMICAL_BATH[5] = create(2061, new SimpleMachineMetaTileEntity(location("chemical_bath.luv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 6));
            CHEMICAL_BATH[6] = create(2062, new SimpleMachineMetaTileEntity(location("chemical_bath.zpm"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 7));
            CHEMICAL_BATH[7] = create(2063, new SimpleMachineMetaTileEntity(location("chemical_bath.uv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 8));
            CHEMICAL_BATH[8] = create(3299, new GASimpleMachineMetaTileEntity(location("chemical_bath.uhv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 9));
            CHEMICAL_BATH[9] = create(3300, new GASimpleMachineMetaTileEntity(location("chemical_bath.uev"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 10));
            CHEMICAL_BATH[10] = create(3301, new GASimpleMachineMetaTileEntity(location("chemical_bath.uiv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 11));
            CHEMICAL_BATH[11] = create(3302, new GASimpleMachineMetaTileEntity(location("chemical_bath.umv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 12));
            CHEMICAL_BATH[12] = create(3303, new GASimpleMachineMetaTileEntity(location("chemical_bath.uxv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierChemicalReactors) {
            CHEMICAL_REACTOR[4] = create(2064, new SimpleMachineMetaTileEntity(location("chemical_reactor.iv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 5));
            CHEMICAL_REACTOR[5] = create(2065, new SimpleMachineMetaTileEntity(location("chemical_reactor.luv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 6));
            CHEMICAL_REACTOR[6] = create(2066, new SimpleMachineMetaTileEntity(location("chemical_reactor.zpm"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 7));
            CHEMICAL_REACTOR[7] = create(2067, new SimpleMachineMetaTileEntity(location("chemical_reactor.uv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 8));
            CHEMICAL_REACTOR[8] = create(3304, new GASimpleMachineMetaTileEntity(location("chemical_reactor.uhv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 9));
            CHEMICAL_REACTOR[9] = create(3305, new GASimpleMachineMetaTileEntity(location("chemical_reactor.uev"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 10));
            CHEMICAL_REACTOR[10] = create(3306, new GASimpleMachineMetaTileEntity(location("chemical_reactor.uiv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 11));
            CHEMICAL_REACTOR[11] = create(3307, new GASimpleMachineMetaTileEntity(location("chemical_reactor.umv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 12));
            CHEMICAL_REACTOR[12] = create(3308, new GASimpleMachineMetaTileEntity(location("chemical_reactor.uxv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierCompressors) {
            COMPRESSOR[4] = create(2068, new SimpleMachineMetaTileEntity(location("compressor.iv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 5));
            COMPRESSOR[5] = create(2069, new SimpleMachineMetaTileEntity(location("compressor.luv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 6));
            COMPRESSOR[6] = create(2070, new SimpleMachineMetaTileEntity(location("compressor.zpm"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 7));
            COMPRESSOR[7] = create(2071, new SimpleMachineMetaTileEntity(location("compressor.uv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 8));
            COMPRESSOR[8] = create(3309, new GASimpleMachineMetaTileEntity(location("compressor.uhv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 9));
            COMPRESSOR[9] = create(3310, new GASimpleMachineMetaTileEntity(location("compressor.uev"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 10));
            COMPRESSOR[10] = create(3311, new GASimpleMachineMetaTileEntity(location("compressor.uiv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 11));
            COMPRESSOR[11] = create(3312, new GASimpleMachineMetaTileEntity(location("compressor.umv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 12));
            COMPRESSOR[12] = create(3313, new GASimpleMachineMetaTileEntity(location("compressor.uxv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierCutters) {
            CUTTER[4] = create(2072, new SimpleMachineMetaTileEntity(location("cutter.iv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 5));
            CUTTER[5] = create(2073, new SimpleMachineMetaTileEntity(location("cutter.luv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 6));
            CUTTER[6] = create(2074, new SimpleMachineMetaTileEntity(location("cutter.zpm"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 7));
            CUTTER[7] = create(2075, new SimpleMachineMetaTileEntity(location("cutter.uv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 8));
            CUTTER[8] = create(3314, new GASimpleMachineMetaTileEntity(location("cutter.uhv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 9));
            CUTTER[9] = create(3315, new GASimpleMachineMetaTileEntity(location("cutter.uev"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 10));
            CUTTER[10] = create(3316, new GASimpleMachineMetaTileEntity(location("cutter.uiv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 11));
            CUTTER[11] = create(3317, new GASimpleMachineMetaTileEntity(location("cutter.umv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 12));
            CUTTER[12] = create(3318, new GASimpleMachineMetaTileEntity(location("cutter.uxv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierDistilleries) {
            DISTILLERY[4] = create(2076, new SimpleMachineMetaTileEntity(location("distillery.iv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 5));
            DISTILLERY[5] = create(2077, new SimpleMachineMetaTileEntity(location("distillery.luv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 6));
            DISTILLERY[6] = create(2078, new SimpleMachineMetaTileEntity(location("distillery.zpm"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 7));
            DISTILLERY[7] = create(2079, new SimpleMachineMetaTileEntity(location("distillery.uv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 8));
            DISTILLERY[8] = create(3319, new GASimpleMachineMetaTileEntity(location("distillery.uhv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 9));
            DISTILLERY[9] = create(3320, new GASimpleMachineMetaTileEntity(location("distillery.uev"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 10));
            DISTILLERY[10] = create(3321, new GASimpleMachineMetaTileEntity(location("distillery.uiv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 11));
            DISTILLERY[11] = create(3322, new GASimpleMachineMetaTileEntity(location("distillery.umv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 12));
            DISTILLERY[12] = create(3323, new GASimpleMachineMetaTileEntity(location("distillery.uxv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierElectrolyzers) {
            ELECTROLYZER[4] = create(2080, new SimpleMachineMetaTileEntity(location("electrolyzer.iv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 5));
            ELECTROLYZER[5] = create(2081, new SimpleMachineMetaTileEntity(location("electrolyzer.luv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 6));
            ELECTROLYZER[6] = create(2082, new SimpleMachineMetaTileEntity(location("electrolyzer.zpm"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 7));
            ELECTROLYZER[7] = create(2083, new SimpleMachineMetaTileEntity(location("electrolyzer.uv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 8));
            ELECTROLYZER[8] = create(3324, new GASimpleMachineMetaTileEntity(location("electrolyzer.uhv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 9));
            ELECTROLYZER[9] = create(3325, new GASimpleMachineMetaTileEntity(location("electrolyzer.uev"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 10));
            ELECTROLYZER[10] = create(3326, new GASimpleMachineMetaTileEntity(location("electrolyzer.uiv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 11));
            ELECTROLYZER[11] = create(3327, new GASimpleMachineMetaTileEntity(location("electrolyzer.umv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 12));
            ELECTROLYZER[12] = create(3328, new GASimpleMachineMetaTileEntity(location("electrolyzer.uxv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierElectromagneticSeparators) {
            ELECTROMAGNETIC_SEPARATOR[4] = create(2084, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.iv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 5));
            ELECTROMAGNETIC_SEPARATOR[5] = create(2085, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.luv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 6));
            ELECTROMAGNETIC_SEPARATOR[6] = create(2086, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.zpm"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 7));
            ELECTROMAGNETIC_SEPARATOR[7] = create(2087, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.uv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 8));
            ELECTROMAGNETIC_SEPARATOR[8] = create(3329, new GASimpleMachineMetaTileEntity(location("electromagnetic_separator.uhv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 9));
            ELECTROMAGNETIC_SEPARATOR[9] = create(3330, new GASimpleMachineMetaTileEntity(location("electromagnetic_separator.uev"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 10));
            ELECTROMAGNETIC_SEPARATOR[10] = create(3331, new GASimpleMachineMetaTileEntity(location("electromagnetic_separator.uiv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 11));
            ELECTROMAGNETIC_SEPARATOR[11] = create(3332, new GASimpleMachineMetaTileEntity(location("electromagnetic_separator.umv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 12));
            ELECTROMAGNETIC_SEPARATOR[12] = create(3333, new GASimpleMachineMetaTileEntity(location("electromagnetic_separator.uxv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierExtractors) {
            EXTRACTOR[4] = create(2088, new SimpleMachineMetaTileEntity(location("extractor.iv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 5));
            EXTRACTOR[5] = create(2089, new SimpleMachineMetaTileEntity(location("extractor.luv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 6));
            EXTRACTOR[6] = create(2090, new SimpleMachineMetaTileEntity(location("extractor.zpm"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 7));
            EXTRACTOR[7] = create(2091, new SimpleMachineMetaTileEntity(location("extractor.uv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 8));
            EXTRACTOR[8] = create(3334, new GASimpleMachineMetaTileEntity(location("extractor.uhv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 9));
            EXTRACTOR[9] = create(3335, new GASimpleMachineMetaTileEntity(location("extractor.uev"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 10));
            EXTRACTOR[10] = create(3336, new GASimpleMachineMetaTileEntity(location("extractor.uiv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 11));
            EXTRACTOR[11] = create(3337, new GASimpleMachineMetaTileEntity(location("extractor.umv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 12));
            EXTRACTOR[12] = create(3338, new GASimpleMachineMetaTileEntity(location("extractor.uxv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierExtruders) {
            EXTRUDER[4] = create(2092, new SimpleMachineMetaTileEntity(location("extruder.iv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 5));
            EXTRUDER[5] = create(2093, new SimpleMachineMetaTileEntity(location("extruder.luv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 6));
            EXTRUDER[6] = create(2094, new SimpleMachineMetaTileEntity(location("extruder.zpm"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 7));
            EXTRUDER[7] = create(2095, new SimpleMachineMetaTileEntity(location("extruder.uv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 8));
            EXTRUDER[8] = create(3339, new GASimpleMachineMetaTileEntity(location("extruder.uhv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 9));
            EXTRUDER[9] = create(3340, new GASimpleMachineMetaTileEntity(location("extruder.uev"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 10));
            EXTRUDER[10] = create(3341, new GASimpleMachineMetaTileEntity(location("extruder.uiv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 11));
            EXTRUDER[11] = create(3342, new GASimpleMachineMetaTileEntity(location("extruder.umv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 12));
            EXTRUDER[12] = create(3343, new GASimpleMachineMetaTileEntity(location("extruder.uxv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFermenters) {
            FERMENTER[4] = create(2096, new SimpleMachineMetaTileEntity(location("fermenter.iv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 5));
            FERMENTER[5] = create(2097, new SimpleMachineMetaTileEntity(location("fermenter.luv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 6));
            FERMENTER[6] = create(2098, new SimpleMachineMetaTileEntity(location("fermenter.zpm"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 7));
            FERMENTER[7] = create(2099, new SimpleMachineMetaTileEntity(location("fermenter.uv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 8));
            FERMENTER[8] = create(3344, new GASimpleMachineMetaTileEntity(location("fermenter.uhv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 9));
            FERMENTER[9] = create(3345, new GASimpleMachineMetaTileEntity(location("fermenter.uev"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 10));
            FERMENTER[10] = create(3346, new GASimpleMachineMetaTileEntity(location("fermenter.uiv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 11));
            FERMENTER[11] = create(3347, new GASimpleMachineMetaTileEntity(location("fermenter.umv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 12));
            FERMENTER[12] = create(3348, new GASimpleMachineMetaTileEntity(location("fermenter.uxv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFluidCanners) {
            FLUID_CANNER[4] = create(2100, new SimpleMachineMetaTileEntity(location("fluid_canner.iv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 5));
            FLUID_CANNER[5] = create(2101, new SimpleMachineMetaTileEntity(location("fluid_canner.luv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 6));
            FLUID_CANNER[6] = create(2102, new SimpleMachineMetaTileEntity(location("fluid_canner.zpm"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 7));
            FLUID_CANNER[7] = create(2103, new SimpleMachineMetaTileEntity(location("fluid_canner.uv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 8));
            FLUID_CANNER[8] = create(3349, new GASimpleMachineMetaTileEntity(location("fluid_canner.uhv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 9));
            FLUID_CANNER[9] = create(3350, new GASimpleMachineMetaTileEntity(location("fluid_canner.uev"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 10));
            FLUID_CANNER[10] = create(3351, new GASimpleMachineMetaTileEntity(location("fluid_canner.uiv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 11));
            FLUID_CANNER[11] = create(3352, new GASimpleMachineMetaTileEntity(location("fluid_canner.umv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 12));
            FLUID_CANNER[12] = create(3353, new GASimpleMachineMetaTileEntity(location("fluid_canner.uxv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFluidExtractors) {
            FLUID_EXTRACTOR[4] = create(2104, new SimpleMachineMetaTileEntity(location("fluid_extractor.iv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 5));
            FLUID_EXTRACTOR[5] = create(2105, new SimpleMachineMetaTileEntity(location("fluid_extractor.luv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 6));
            FLUID_EXTRACTOR[6] = create(2106, new SimpleMachineMetaTileEntity(location("fluid_extractor.zpm"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 7));
            FLUID_EXTRACTOR[7] = create(2107, new SimpleMachineMetaTileEntity(location("fluid_extractor.uv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 8));
            FLUID_EXTRACTOR[8] = create(3354, new GASimpleMachineMetaTileEntity(location("fluid_extractor.uhv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 9));
            FLUID_EXTRACTOR[9] = create(3355, new GASimpleMachineMetaTileEntity(location("fluid_extractor.uev"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 10));
            FLUID_EXTRACTOR[10] = create(3356, new GASimpleMachineMetaTileEntity(location("fluid_extractor.uiv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 11));
            FLUID_EXTRACTOR[11] = create(3357, new GASimpleMachineMetaTileEntity(location("fluid_extractor.umv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 12));
            FLUID_EXTRACTOR[12] = create(3358, new GASimpleMachineMetaTileEntity(location("fluid_extractor.uxv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFluidHeaters) {
            FLUID_HEATER[4] = create(2108, new SimpleMachineMetaTileEntity(location("fluid_heater.iv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 5));
            FLUID_HEATER[5] = create(2109, new SimpleMachineMetaTileEntity(location("fluid_heater.luv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 6));
            FLUID_HEATER[6] = create(2110, new SimpleMachineMetaTileEntity(location("fluid_heater.zpm"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 7));
            FLUID_HEATER[7] = create(2111, new SimpleMachineMetaTileEntity(location("fluid_heater.uv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 8));
            FLUID_HEATER[8] = create(3359, new GASimpleMachineMetaTileEntity(location("fluid_heater.uhv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 9));
            FLUID_HEATER[9] = create(3360, new GASimpleMachineMetaTileEntity(location("fluid_heater.uev"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 10));
            FLUID_HEATER[10] = create(3361, new GASimpleMachineMetaTileEntity(location("fluid_heater.uiv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 11));
            FLUID_HEATER[11] = create(3362, new GASimpleMachineMetaTileEntity(location("fluid_heater.umv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 12));
            FLUID_HEATER[12] = create(3363, new GASimpleMachineMetaTileEntity(location("fluid_heater.uxv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFluidSolidifiers) {
            FLUID_SOLIDIFIER[4] = create(2112, new SimpleMachineMetaTileEntity(location("fluid_solidifier.iv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 5));
            FLUID_SOLIDIFIER[5] = create(2113, new SimpleMachineMetaTileEntity(location("fluid_solidifier.luv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 6));
            FLUID_SOLIDIFIER[6] = create(2114, new SimpleMachineMetaTileEntity(location("fluid_solidifier.zpm"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 7));
            FLUID_SOLIDIFIER[7] = create(2115, new SimpleMachineMetaTileEntity(location("fluid_solidifier.uv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 8));
            FLUID_SOLIDIFIER[8] = create(3364, new GASimpleMachineMetaTileEntity(location("fluid_solidifier.uhv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 9));
            FLUID_SOLIDIFIER[9] = create(3365, new GASimpleMachineMetaTileEntity(location("fluid_solidifier.uev"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 10));
            FLUID_SOLIDIFIER[10] = create(3366, new GASimpleMachineMetaTileEntity(location("fluid_solidifier.uiv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 11));
            FLUID_SOLIDIFIER[11] = create(3367, new GASimpleMachineMetaTileEntity(location("fluid_solidifier.umv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 12));
            FLUID_SOLIDIFIER[12] = create(3368, new GASimpleMachineMetaTileEntity(location("fluid_solidifier.uxv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierForgeHammers) {
            FORGE_HAMMER[4] = create(2116, new SimpleMachineMetaTileEntity(location("forge_hammer.iv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 5));
            FORGE_HAMMER[5] = create(2117, new SimpleMachineMetaTileEntity(location("forge_hammer.luv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 6));
            FORGE_HAMMER[6] = create(2118, new SimpleMachineMetaTileEntity(location("forge_hammer.zpm"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 7));
            FORGE_HAMMER[7] = create(2119, new SimpleMachineMetaTileEntity(location("forge_hammer.uv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 8));
            FORGE_HAMMER[8] = create(3369, new GASimpleMachineMetaTileEntity(location("forge_hammer.uhv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 9));
            FORGE_HAMMER[9] = create(3370, new GASimpleMachineMetaTileEntity(location("forge_hammer.uev"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 10));
            FORGE_HAMMER[10] = create(3371, new GASimpleMachineMetaTileEntity(location("forge_hammer.uiv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 11));
            FORGE_HAMMER[11] = create(3372, new GASimpleMachineMetaTileEntity(location("forge_hammer.umv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 12));
            FORGE_HAMMER[12] = create(3373, new GASimpleMachineMetaTileEntity(location("forge_hammer.uxv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierFormingPresses) {
            FORMING_PRESS[4] = create(2120, new SimpleMachineMetaTileEntity(location("forming_press.iv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 5));
            FORMING_PRESS[5] = create(2121, new SimpleMachineMetaTileEntity(location("forming_press.luv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 6));
            FORMING_PRESS[6] = create(2122, new SimpleMachineMetaTileEntity(location("forming_press.zpm"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 7));
            FORMING_PRESS[7] = create(2123, new SimpleMachineMetaTileEntity(location("forming_press.uv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 8));
            FORMING_PRESS[8] = create(3374, new GASimpleMachineMetaTileEntity(location("forming_press.uhv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 9));
            FORMING_PRESS[9] = create(3375, new GASimpleMachineMetaTileEntity(location("forming_press.uev"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 10));
            FORMING_PRESS[10] = create(3376, new GASimpleMachineMetaTileEntity(location("forming_press.uiv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 11));
            FORMING_PRESS[11] = create(3377, new GASimpleMachineMetaTileEntity(location("forming_press.umv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 12));
            FORMING_PRESS[12] = create(3378, new GASimpleMachineMetaTileEntity(location("forming_press.uxv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierLathes) {
            LATHE[4] = create(2124, new SimpleMachineMetaTileEntity(location("lathe.iv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 5));
            LATHE[5] = create(2125, new SimpleMachineMetaTileEntity(location("lathe.luv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 6));
            LATHE[6] = create(2126, new SimpleMachineMetaTileEntity(location("lathe.zpm"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 7));
            LATHE[7] = create(2127, new SimpleMachineMetaTileEntity(location("lathe.uv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 8));
            LATHE[8] = create(3379, new GASimpleMachineMetaTileEntity(location("lathe.uhv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 9));
            LATHE[9] = create(3380, new GASimpleMachineMetaTileEntity(location("lathe.uev"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 10));
            LATHE[10] = create(3381, new GASimpleMachineMetaTileEntity(location("lathe.uiv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 11));
            LATHE[11] = create(3382, new GASimpleMachineMetaTileEntity(location("lathe.umv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 12));
            LATHE[12] = create(3383, new GASimpleMachineMetaTileEntity(location("lathe.uxv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierMicrowaves) {
            MICROWAVE[4] = create(2128, new SimpleMachineMetaTileEntity(location("microwave.iv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 5));
            MICROWAVE[5] = create(2129, new SimpleMachineMetaTileEntity(location("microwave.luv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 6));
            MICROWAVE[6] = create(2130, new SimpleMachineMetaTileEntity(location("microwave.zpm"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 7));
            MICROWAVE[7] = create(2131, new SimpleMachineMetaTileEntity(location("microwave.uv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 8));
            MICROWAVE[8] = create(3384, new GASimpleMachineMetaTileEntity(location("microwave.uhv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 9));
            MICROWAVE[9] = create(3385, new GASimpleMachineMetaTileEntity(location("microwave.uev"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 10));
            MICROWAVE[10] = create(3386, new GASimpleMachineMetaTileEntity(location("microwave.uiv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 11));
            MICROWAVE[11] = create(3387, new GASimpleMachineMetaTileEntity(location("microwave.umv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 12));
            MICROWAVE[12] = create(3388, new GASimpleMachineMetaTileEntity(location("microwave.uxv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierMixers) {
            MIXER[4] = create(2132, new SimpleMachineMetaTileEntity(location("mixer.iv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 5));
            MIXER[5] = create(2133, new SimpleMachineMetaTileEntity(location("mixer.luv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 6));
            MIXER[6] = create(2134, new SimpleMachineMetaTileEntity(location("mixer.zpm"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 7));
            MIXER[7] = create(2135, new SimpleMachineMetaTileEntity(location("mixer.uv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 8));
            MIXER[8] = create(3389, new GASimpleMachineMetaTileEntity(location("mixer.uhv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 9));
            MIXER[9] = create(3390, new GASimpleMachineMetaTileEntity(location("mixer.uev"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 10));
            MIXER[10] = create(3391, new GASimpleMachineMetaTileEntity(location("mixer.uiv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 11));
            MIXER[11] = create(3392, new GASimpleMachineMetaTileEntity(location("mixer.umv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 12));
            MIXER[12] = create(3393, new GASimpleMachineMetaTileEntity(location("mixer.uxv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierOreWashers) {
            ORE_WASHER[4] = create(2136, new SimpleMachineMetaTileEntity(location("ore_washer.iv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 5));
            ORE_WASHER[5] = create(2137, new SimpleMachineMetaTileEntity(location("ore_washer.luv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 6));
            ORE_WASHER[6] = create(2138, new SimpleMachineMetaTileEntity(location("ore_washer.zpm"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 7));
            ORE_WASHER[7] = create(2139, new SimpleMachineMetaTileEntity(location("ore_washer.uv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 8));
            ORE_WASHER[8] = create(3394, new GASimpleMachineMetaTileEntity(location("ore_washer.uhv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 9));
            ORE_WASHER[9] = create(3395, new GASimpleMachineMetaTileEntity(location("ore_washer.uev"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 10));
            ORE_WASHER[10] = create(3396, new GASimpleMachineMetaTileEntity(location("ore_washer.uiv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 11));
            ORE_WASHER[11] = create(3397, new GASimpleMachineMetaTileEntity(location("ore_washer.umv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 12));
            ORE_WASHER[12] = create(3398, new GASimpleMachineMetaTileEntity(location("ore_washer.uxv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierPackers) {
            PACKER[4] = create(2140, new SimpleMachineMetaTileEntity(location("packer.iv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 5));
            PACKER[5] = create(2141, new SimpleMachineMetaTileEntity(location("packer.luv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 6));
            PACKER[6] = create(2142, new SimpleMachineMetaTileEntity(location("packer.zpm"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 7));
            PACKER[7] = create(2143, new SimpleMachineMetaTileEntity(location("packer.uv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 8));
            PACKER[8] = create(3399, new GASimpleMachineMetaTileEntity(location("packer.uhv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 9));
            PACKER[9] = create(3400, new GASimpleMachineMetaTileEntity(location("packer.uev"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 10));
            PACKER[10] = create(3401, new GASimpleMachineMetaTileEntity(location("packer.uiv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 11));
            PACKER[11] = create(3402, new GASimpleMachineMetaTileEntity(location("packer.umv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 12));
            PACKER[12] = create(3403, new GASimpleMachineMetaTileEntity(location("packer.uxv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierUnpackers) {
            UNPACKER[4] = create(2144, new SimpleMachineMetaTileEntity(location("unpacker.iv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 5));
            UNPACKER[5] = create(2145, new SimpleMachineMetaTileEntity(location("unpacker.luv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 6));
            UNPACKER[6] = create(2146, new SimpleMachineMetaTileEntity(location("unpacker.zpm"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 7));
            UNPACKER[7] = create(2147, new SimpleMachineMetaTileEntity(location("unpacker.uv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 8));
            UNPACKER[8] = create(3404, new GASimpleMachineMetaTileEntity(location("unpacker.uhv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 9));
            UNPACKER[9] = create(3405, new GASimpleMachineMetaTileEntity(location("unpacker.uev"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 10));
            UNPACKER[10] = create(3406, new GASimpleMachineMetaTileEntity(location("unpacker.uiv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 11));
            UNPACKER[11] = create(3407, new GASimpleMachineMetaTileEntity(location("unpacker.umv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 12));
            UNPACKER[12] = create(3408, new GASimpleMachineMetaTileEntity(location("unpacker.uxv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierPlasmaArcFurnaces) {
            PLASMA_ARC_FURNACE[4] = create(2148, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.iv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 5));
            PLASMA_ARC_FURNACE[5] = create(2149, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.luv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 6));
            PLASMA_ARC_FURNACE[6] = create(2150, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.zpm"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 7));
            PLASMA_ARC_FURNACE[7] = create(2151, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.uv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 8));
            PLASMA_ARC_FURNACE[8] = create(3409, new GASimpleMachineMetaTileEntity(location("plasma_arc_furnace.uhv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 9));
            PLASMA_ARC_FURNACE[9] = create(3410, new GASimpleMachineMetaTileEntity(location("plasma_arc_furnace.uev"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 10));
            PLASMA_ARC_FURNACE[10] = create(3411, new GASimpleMachineMetaTileEntity(location("plasma_arc_furnace.uiv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 11));
            PLASMA_ARC_FURNACE[11] = create(3412, new GASimpleMachineMetaTileEntity(location("plasma_arc_furnace.umv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 12));
            PLASMA_ARC_FURNACE[12] = create(3413, new GASimpleMachineMetaTileEntity(location("plasma_arc_furnace.uxv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierPolarizers) {
            POLARIZER[4] = create(2152, new SimpleMachineMetaTileEntity(location("polarizer.iv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 5));
            POLARIZER[5] = create(2153, new SimpleMachineMetaTileEntity(location("polarizer.luv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 6));
            POLARIZER[6] = create(2154, new SimpleMachineMetaTileEntity(location("polarizer.zpm"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 7));
            POLARIZER[7] = create(2155, new SimpleMachineMetaTileEntity(location("polarizer.uv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 8));
            POLARIZER[8] = create(3414, new GASimpleMachineMetaTileEntity(location("polarizer.uhv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 9));
            POLARIZER[9] = create(3415, new GASimpleMachineMetaTileEntity(location("polarizer.uev"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 10));
            POLARIZER[10] = create(3416, new GASimpleMachineMetaTileEntity(location("polarizer.uiv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 11));
            POLARIZER[11] = create(3417, new GASimpleMachineMetaTileEntity(location("polarizer.umv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 12));
            POLARIZER[12] = create(3418, new GASimpleMachineMetaTileEntity(location("polarizer.uxv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierLaserEngravers) {
            LASER_ENGRAVER[5] = create(2157, new SimpleMachineMetaTileEntity(location("laser_engraver.luv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 6));
            LASER_ENGRAVER[6] = create(2158, new SimpleMachineMetaTileEntity(location("laser_engraver.zpm"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 7));
            LASER_ENGRAVER[7] = create(2159, new SimpleMachineMetaTileEntity(location("laser_engraver.uv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 8));
            LASER_ENGRAVER[8] = create(3419, new GASimpleMachineMetaTileEntity(location("laser_engraver.uhv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 9));
            LASER_ENGRAVER[9] = create(3420, new GASimpleMachineMetaTileEntity(location("laser_engraver.uev"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 10));
            LASER_ENGRAVER[10] = create(3421, new GASimpleMachineMetaTileEntity(location("laser_engraver.uiv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 11));
            LASER_ENGRAVER[11] = create(3422, new GASimpleMachineMetaTileEntity(location("laser_engraver.umv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 12));
            LASER_ENGRAVER[12] = create(3423, new GASimpleMachineMetaTileEntity(location("laser_engraver.uxv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierSifters) {
            SIFTER[4] = create(2160, new SimpleMachineMetaTileEntity(location("sifter.iv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 5));
            SIFTER[5] = create(2161, new SimpleMachineMetaTileEntity(location("sifter.luv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 6));
            SIFTER[6] = create(2162, new SimpleMachineMetaTileEntity(location("sifter.zpm"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 7));
            SIFTER[7] = create(2163, new SimpleMachineMetaTileEntity(location("sifter.uv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 8));
            SIFTER[8] = create(3424, new GASimpleMachineMetaTileEntity(location("sifter.uhv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 9));
            SIFTER[9] = create(3425, new GASimpleMachineMetaTileEntity(location("sifter.uev"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 10));
            SIFTER[10] = create(3426, new GASimpleMachineMetaTileEntity(location("sifter.uiv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 11));
            SIFTER[11] = create(3427, new GASimpleMachineMetaTileEntity(location("sifter.umv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 12));
            SIFTER[12] = create(3428, new GASimpleMachineMetaTileEntity(location("sifter.uxv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierThermalCentrifuges) {
            THERMAL_CENTRIFUGE[4] = create(2164, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.iv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 5));
            THERMAL_CENTRIFUGE[5] = create(2165, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.luv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 6));
            THERMAL_CENTRIFUGE[6] = create(2166, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.zpm"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 7));
            THERMAL_CENTRIFUGE[7] = create(2167, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.uv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 8));
            THERMAL_CENTRIFUGE[8] = create(3429, new GASimpleMachineMetaTileEntity(location("thermal_centrifuge.uhv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 9));
            THERMAL_CENTRIFUGE[9] = create(3430, new GASimpleMachineMetaTileEntity(location("thermal_centrifuge.uev"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 10));
            THERMAL_CENTRIFUGE[10] = create(3431, new GASimpleMachineMetaTileEntity(location("thermal_centrifuge.uiv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 11));
            THERMAL_CENTRIFUGE[11] = create(3432, new GASimpleMachineMetaTileEntity(location("thermal_centrifuge.umv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 12));
            THERMAL_CENTRIFUGE[12] = create(3433, new GASimpleMachineMetaTileEntity(location("thermal_centrifuge.uxv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 13));

        }

        if (GAConfig.GT5U.highTierWiremills) {
            WIREMILL[4] = create(2168, new SimpleMachineMetaTileEntity(location("wiremill.iv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 5));
            WIREMILL[5] = create(2169, new SimpleMachineMetaTileEntity(location("wiremill.luv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 6));
            WIREMILL[6] = create(2170, new SimpleMachineMetaTileEntity(location("wiremill.zpm"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 7));
            WIREMILL[7] = create(2171, new SimpleMachineMetaTileEntity(location("wiremill.uv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 8));
            WIREMILL[8] = create(3434, new GASimpleMachineMetaTileEntity(location("wiremill.uhv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 9));
            WIREMILL[9] = create(3435, new GASimpleMachineMetaTileEntity(location("wiremill.uev"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 10));
            WIREMILL[10] = create(3436, new GASimpleMachineMetaTileEntity(location("wiremill.uiv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 11));
            WIREMILL[11] = create(3437, new GASimpleMachineMetaTileEntity(location("wiremill.umv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 12));
            WIREMILL[12] = create(3438, new GASimpleMachineMetaTileEntity(location("wiremill.uxv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 13));

        }

        if (GAConfig.Misc.naqEfficieny) {
            NAQUADAH_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2173, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk2"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 5, 100));
            NAQUADAH_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2174, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk3"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6, 80));
            NAQUADAH_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2191, new SimpleGeneratorWithLossMetaTileEntity(location("naquadah_reactor.mk4"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7, 50));
        } else {
            NAQUADAH_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2173, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk2"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 5));
            NAQUADAH_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2174, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk3"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6));
            NAQUADAH_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2191, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk4"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7));

        }

        MASS_FAB[0] = create(2175, new SimpleMachineMetaTileEntity(location("mass_fab.lv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 1));
        MASS_FAB[1] = create(2176, new SimpleMachineMetaTileEntity(location("mass_fab.mv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 2));
        MASS_FAB[2] = create(2177, new SimpleMachineMetaTileEntity(location("mass_fab.hv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 3));
        MASS_FAB[3] = create(2178, new SimpleMachineMetaTileEntity(location("mass_fab.ev"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 4));
        if (GAConfig.GT5U.highTierMassFabs) {
            MASS_FAB[4] = create(2179, new SimpleMachineMetaTileEntity(location("mass_fab.iv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 5));
            MASS_FAB[5] = create(2180, new SimpleMachineMetaTileEntity(location("mass_fab.luv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 6));
            MASS_FAB[6] = create(2181, new SimpleMachineMetaTileEntity(location("mass_fab.zpm"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 7));
            MASS_FAB[7] = create(2182, new SimpleMachineMetaTileEntity(location("mass_fab.uv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 8));
            MASS_FAB[8] = create(3449, new GASimpleMachineMetaTileEntity(location("mass_fab.uhv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 9));
            MASS_FAB[9] = create(3450, new GASimpleMachineMetaTileEntity(location("mass_fab.uev"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 10));
            MASS_FAB[10] = create(3451, new GASimpleMachineMetaTileEntity(location("mass_fab.uiv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 11));
            MASS_FAB[11] = create(3452, new GASimpleMachineMetaTileEntity(location("mass_fab.umv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 12));
            MASS_FAB[12] = create(3453, new GASimpleMachineMetaTileEntity(location("mass_fab.uxv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 13));

        }

        REPLICATOR[0] = create(2183, new SimpleMachineMetaTileEntity(location("replicator.lv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 1));
        REPLICATOR[1] = create(2184, new SimpleMachineMetaTileEntity(location("replicator.mv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 2));
        REPLICATOR[2] = create(2185, new SimpleMachineMetaTileEntity(location("replicator.hv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 3));
        REPLICATOR[3] = create(2186, new SimpleMachineMetaTileEntity(location("replicator.ev"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 4));
        if (GAConfig.GT5U.highTierReplicators) {
            REPLICATOR[4] = create(2187, new SimpleMachineMetaTileEntity(location("replicator.iv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 5));
            REPLICATOR[5] = create(2188, new SimpleMachineMetaTileEntity(location("replicator.luv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 6));
            REPLICATOR[6] = create(2189, new SimpleMachineMetaTileEntity(location("replicator.zpm"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 7));
            REPLICATOR[7] = create(2190, new SimpleMachineMetaTileEntity(location("replicator.uv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 8));
            REPLICATOR[8] = create(3444, new GASimpleMachineMetaTileEntity(location("replicator.uhv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 9));
            REPLICATOR[9] = create(3445, new GASimpleMachineMetaTileEntity(location("replicator.uev"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 10));
            REPLICATOR[10] = create(3446, new GASimpleMachineMetaTileEntity(location("replicator.uiv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 11));
            REPLICATOR[11] = create(3447, new GASimpleMachineMetaTileEntity(location("replicator.umv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 12));
            REPLICATOR[12] = create(3448, new GASimpleMachineMetaTileEntity(location("replicator.uxv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 13));

        }

        CENTRAL_MONITOR = GregTechAPI.registerMetaTileEntity(2499, new MetaTileEntityCentralMonitor(location("central_monitor")));

        ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2502, new TileEntityAssemblyLine(location("assembly_line")));

        FUSION_REACTOR[0] = GregTechAPI.registerMetaTileEntity(2504, new TileEntityFusionReactor(location("fusion_reactor.luv"), 6));
        FUSION_REACTOR[1] = GregTechAPI.registerMetaTileEntity(2505, new TileEntityFusionReactor(location("fusion_reactor.zpm"), 7));
        FUSION_REACTOR[2] = GregTechAPI.registerMetaTileEntity(2506, new TileEntityFusionReactor(location("fusion_reactor.uv"), 8));

        PROCESSING_ARRAY = GregTechAPI.registerMetaTileEntity(2507, new TileEntityProcessingArray(location("processing_array")));
        LARGE_THERMAL_CENTRIFUGE = GregTechAPI.registerMetaTileEntity(2508, new TileEntityLargeThermalCentrifuge(location("large_thermal_centrifuge")));
        LARGE_ELECTROLYZER = GregTechAPI.registerMetaTileEntity(2509, new TileEntityLargeElectrolyzer(location("large_electrolyzer")));
        //register 2 times for each recipe seems not working
        LARGE_BENDER_AND_FORMING = GregTechAPI.registerMetaTileEntity(2510, new TileEntityLargeBenderAndForming(location("large_bender_and_forming"), RecipeMaps.BENDER_RECIPES));
        GregTechAPI.registerMetaTileEntity(2511, new TileEntityLargeBenderAndForming(location("large_bender_and_forming"), RecipeMaps.FORMING_PRESS_RECIPES));
        LARGE_CENTRIFUGE = GregTechAPI.registerMetaTileEntity(2512, new TileEntityLargeCentrifuge(location("large_centrifuge")));
        LARGE_CUTTING = GregTechAPI.registerMetaTileEntity(2513, new TileEntityLargeCutting(location("large_cutting")));
        LARGE_MACERATOR = GregTechAPI.registerMetaTileEntity(2514, new TileEntityLargeMacerator(location("large_macerator")));
        LARGE_MIXER = GregTechAPI.registerMetaTileEntity(2515, new TileEntityLargeMixer(location("large_mixer")));
        LARGE_MULTI_USE = GregTechAPI.registerMetaTileEntity(2516, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.COMPRESSOR_RECIPES));
        GregTechAPI.registerMetaTileEntity(2517, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.COMPRESSOR_RECIPES));
        GregTechAPI.registerMetaTileEntity(2518, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.LATHE_RECIPES));
        GregTechAPI.registerMetaTileEntity(2519, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.POLARIZER_RECIPES));
        GregTechAPI.registerMetaTileEntity(2520, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.FERMENTING_RECIPES));
        GregTechAPI.registerMetaTileEntity(2521, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.FLUID_EXTRACTION_RECIPES));
        GregTechAPI.registerMetaTileEntity(2522, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.EXTRACTOR_RECIPES));
        GregTechAPI.registerMetaTileEntity(2523, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.LASER_ENGRAVER_RECIPES));
        GregTechAPI.registerMetaTileEntity(2524, new TileEntityLargeMultiUse(location("large_multi_use"), RecipeMaps.AUTOCLAVE_RECIPES));
        GregTechAPI.registerMetaTileEntity(2525, new TileEntityLargeMultiUse(location("large_multi_use"), GARecipeMaps.REPLICATOR_RECIPES));
        LARGE_SIFTER = GregTechAPI.registerMetaTileEntity(2526, new TileEntityLargeSifter(location("large_sifter")));
        LARGE_WASHING_PLANT = GregTechAPI.registerMetaTileEntity(2527, new TileEntityLargeWashingPlant(location("large_washing_plant"), RecipeMaps.ORE_WASHER_RECIPES));
        GregTechAPI.registerMetaTileEntity(2528, new TileEntityLargeWashingPlant(location("large_washing_plant"), RecipeMaps.CHEMICAL_BATH_RECIPES));
        LARGE_WIREMILL = GregTechAPI.registerMetaTileEntity(2529, new TileEntityLargeWiremill(location("large_wiremill")));
        LARGE_CHEMICAL_REACTOR = GregTechAPI.registerMetaTileEntity(2530, new TileEntityLargeChemicalReactor(location("large_chemical_reactor")));
        LARGE_EXTRUDER = GregTechAPI.registerMetaTileEntity(2531, new TileEntityLargeExtruder(location("large_extruder")));
        VOLCANUS = GregTechAPI.registerMetaTileEntity(2532, new TileEntityVolcanus(location("volcanus")));
        LARGE_ASSEMBLER = GregTechAPI.registerMetaTileEntity(2533, new TileEntityLargeAssembler(location("large_assembler")));

        ELECTRIC_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(2534, new MetaTileEntityElectricBlastFurnace(location("electric_blast_furnace")));
        VACUUM_FREEZER = GregTechAPI.registerMetaTileEntity(2535, new MetaTileEntityVacuumFreezer(location("vacuum_freezer")));
        IMPLOSION_COMPRESSOR = GregTechAPI.registerMetaTileEntity(2536, new MetaTileEntityImplosionCompressor(location("implosion_compressor")));
        DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(2537, new MetaTileEntityDistillationTower(location("distillation_tower")));
        CRACKER = GregTechAPI.registerMetaTileEntity(2538, new MetaTileEntityCrackingUnit(location("cracker")));
        MULTI_FURNACE = GregTechAPI.registerMetaTileEntity(2539, new MetaTileEntityMultiFurnace(location("multi_furnace")));
        DIESEL_ENGINE = GregTechAPI.registerMetaTileEntity(2540, new MetaTileEntityDieselEngine(location("diesel_engine")));

        LARGE_STEAM_TURBINE = GregTechAPI.registerMetaTileEntity(2541, new MetaTileEntityLargeTurbine(location("large_turbine.steam"), MetaTileEntityLargeTurbine.TurbineType.valueOf("STEAM_OVERRIDE")));
        LARGE_GAS_TURBINE = GregTechAPI.registerMetaTileEntity(2542, new MetaTileEntityLargeTurbine(location("large_turbine.gas"), MetaTileEntityLargeTurbine.TurbineType.valueOf("GAS_OVERRIDE")));
        LARGE_PLASMA_TURBINE = GregTechAPI.registerMetaTileEntity(2543, new MetaTileEntityLargeTurbine(location("large_turbine.plasma"), MetaTileEntityLargeTurbine.TurbineType.valueOf("PLASMA_OVERRIDE")));
        HOT_COOLANT_TURBINE = GregTechAPI.registerMetaTileEntity(2544, new MetaTileEntityHotCoolantTurbine(location("large_turbine.hot_coolant"), MetaTileEntityHotCoolantTurbine.TurbineType.HOT_COOLANT));

        NUCLEAR_REACTOR = GregTechAPI.registerMetaTileEntity(2545, new MetaTileEntityNuclearReactor(location("nuclear_reactor"), GARecipeMaps.NUCLEAR_REACTOR_RECIPES));
        NUCLEAR_BREEDER = GregTechAPI.registerMetaTileEntity(2546, new MetaTileEntityNuclearReactor(location("nuclear_breeder"), GARecipeMaps.NUCLEAR_BREEDER_RECIPES));
        Material basicMat = Material.MATERIAL_REGISTRY.getObject(GAConfig.multis.largeMiner.basicMinerCasingMaterial);
        LARGE_MINER[0] = GregTechAPI.registerMetaTileEntity(2548, new MetaTileEntityLargeMiner(location("miner.basic"), Miner.Type.BASIC, basicMat != null && basicMat.hasFlag(GAMaterials.GENERATE_METAL_CASING) ? basicMat : Materials.BlackSteel));
        Material largeMat = Material.MATERIAL_REGISTRY.getObject(GAConfig.multis.largeMiner.largeMinerCasingMaterial);
        LARGE_MINER[1] = GregTechAPI.registerMetaTileEntity(2549, new MetaTileEntityLargeMiner(location("miner.large"), Miner.Type.LARGE, largeMat != null && largeMat.hasFlag(GAMaterials.GENERATE_METAL_CASING) ? largeMat : Materials.HSSG));
        Material advancedMat = Material.MATERIAL_REGISTRY.getObject(GAConfig.multis.largeMiner.advancedMinerCasingMaterial);
        LARGE_MINER[2] = GregTechAPI.registerMetaTileEntity(2550, new MetaTileEntityLargeMiner(location("miner.advance"), Miner.Type.ADVANCE, advancedMat != null && advancedMat.hasFlag(GAMaterials.GENERATE_METAL_CASING) ? advancedMat : Materials.HSSS));
        VOID_MINER[0] = GregTechAPI.registerMetaTileEntity(2551, new MetaTileEntityVoidMiner(location("void_miner"), GAValues.UV, GAConfig.multis.voidMiner.maxTemp));
        LARGE_TRANSFORMER = GregTechAPI.registerMetaTileEntity(2552, new TileEntityLargeTransformer(location("large_transformer")));
        INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(2553, new MetaTileEntityIndustrialPrimitiveBlastFurnace(location("industrial_primitive_blast_furnace")));
        ADVANCED_DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(2554, new TileEntityAdvancedDistillationTower(location("advanced_distillation_tower"), RecipeMaps.DISTILLERY_RECIPES));
        GregTechAPI.registerMetaTileEntity(2555, new TileEntityAdvancedDistillationTower(location("advanced_distillation_tower"), RecipeMaps.DISTILLATION_RECIPES));
        CRYOGENIC_FREEZER = GregTechAPI.registerMetaTileEntity(2556, new TileEntityCryogenicFreezer(location("cryogenic_freezer")));
        CHEMICAL_PLANT = GregTechAPI.registerMetaTileEntity(2557, new MetaTileEntityChemicalPlant(location("chemical_plant")));
        LARGE_ROCKET_ENGINE = GregTechAPI.registerMetaTileEntity(2558, new MetaTileEntityLargeRocketEngine(location("large_rocket_engine")));
        ALLOY_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(2559, new TileEntityAlloyBlastFurnace(location("alloy_blast_furnace")));
        LARGE_FORGE_HAMMER = GregTechAPI.registerMetaTileEntity(2560, new TileEntityLargeForgeHammer(location("large_forge_hammer")));
        LARGE_NAQUADAH_REACTOR = GregTechAPI.registerMetaTileEntity(2561, new MetaTileEntityLargeNaquadahReactor(location("large_naquadah_reactor")));
        BATTERY_TOWER = GregTechAPI.registerMetaTileEntity(2562, new MetaTileEntityBatteryTower(location("battery_tower")));

        if (GAConfig.GT6.registerDrums) {
            WOODEN_DRUM = GregTechAPI.registerMetaTileEntity(2195, new MetaTileEntityDrum(location("drum.wood"), Materials.Wood, 16000));
            BRONZE_DRUM = GregTechAPI.registerMetaTileEntity(2196, new MetaTileEntityDrum(location("drum.bronze"), Materials.Bronze, 32000));
            STEEL_DRUM = GregTechAPI.registerMetaTileEntity(2197, new MetaTileEntityDrum(location("drum.steel"), Materials.Steel, 64000));
            STAINLESS_STEEL_DRUM = GregTechAPI.registerMetaTileEntity(2198, new MetaTileEntityDrum(location("drum.stainless_steel"), Materials.StainlessSteel, 128000));
            TITANIUM_DRUM = GregTechAPI.registerMetaTileEntity(2199, new MetaTileEntityDrum(location("drum.titanium"), Materials.Titanium, 192000));
            TUNGSTENSTEEL_DRUM = GregTechAPI.registerMetaTileEntity(2200, new MetaTileEntityDrum(location("drum.tungstensteel"), Materials.TungstenSteel, 256000));
        }

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

        if (GAConfig.Misc.registerCrates) {
            WOODEN_CRATE = GregTechAPI.registerMetaTileEntity(2207, new TileEntityCrate(location("crate.wood"), Materials.Wood, 36));
            BRONZE_CRATE = GregTechAPI.registerMetaTileEntity(2208, new TileEntityCrate(location("crate.bronze"), Materials.Bronze, 54));
            STEEL_CRATE = GregTechAPI.registerMetaTileEntity(2209, new TileEntityCrate(location("crate.steel"), Materials.Steel, 72));
            STAINLESS_STEEL_CRATE = GregTechAPI.registerMetaTileEntity(2210, new TileEntityCrate(location("crate.stainless_steel"), Materials.StainlessSteel, 90));
            TITANIUM_CRATE = GregTechAPI.registerMetaTileEntity(2211, new TileEntityCrate(location("crate.titanium"), Materials.Titanium, 108));
            TUNGSTENSTEEL_CRATE = GregTechAPI.registerMetaTileEntity(2212, new TileEntityCrate(location("crate.tungstensteel"), Materials.TungstenSteel, 126));
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
                for (int value : GAConfig.energyConverter.values) {
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
        ROTOR_HOLDER[2] = GregTechAPI.registerMetaTileEntity(3210, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.uhv"), GTValues.MAX, 1.7f));

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

        PYROLYSE_OVEN = GregTechAPI.registerMetaTileEntity(3233, new MetaTileEntityPyrolyseOven(location("pyrolyse_oven")));

        HYPER_REACTOR = GregTechAPI.registerMetaTileEntity(3234, new HyperReactor(location("hyper_reactor.uv"), GTValues.V[GTValues.UV]));
        HYPER_REACTOR_UHV = GregTechAPI.registerMetaTileEntity(3235, new HyperReactorUHV(location("hyper_reactor.uhv"), GTValues.V[GTValues.UV] * 4));
        HYPER_REACTOR_UEV = GregTechAPI.registerMetaTileEntity(3236, new HyperReactorUEV(location("hyper_reactor.uev"), GTValues.V[GTValues.UV] * 16));
        ADVANCED_FUSION_REACTOR = GregTechAPI.registerMetaTileEntity(3237, new TileEntityAdvFusionReactor(location("advanced_fusion_reactor")));
        GA_HULLS[0] = GregTechAPI.registerMetaTileEntity(3239, new GAMetaTileEntityHull(location("hull.uhv"), 9));
        GA_HULLS[1] = GregTechAPI.registerMetaTileEntity(3240, new GAMetaTileEntityHull(location("hull.uev"), 10));
        GA_HULLS[2] = GregTechAPI.registerMetaTileEntity(3241, new GAMetaTileEntityHull(location("hull.uiv"), 11));
        GA_HULLS[3] = GregTechAPI.registerMetaTileEntity(3242, new GAMetaTileEntityHull(location("hull.umv"), 12));
        GA_HULLS[4] = GregTechAPI.registerMetaTileEntity(3243, new GAMetaTileEntityHull(location("hull.uxv"), 13));
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


        DRILLING_RIG = GregTechAPI.registerMetaTileEntity(4023, new MetaTileEntityDrillingRig(location("drilling_rig")));
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
                for (int value : GAConfig.energyConverter.values) {
                    final String vn = GAValues.VN[tier].toLowerCase();
                    Long voltage = ((long) GAValues.V[tier] * value * GAConfig.energyConverter.RatioEUtoRF);
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
        GTLog.logger.info(id);
        id = 4127;
        for (int i = 9; i < GAValues.V.length - 1; i++) {
            OUTPUT_HATCH_FILTERED.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityOutputFilteredHatch(location("fluid_hatch.export_filtered." + GAValues.VN[i].toLowerCase()), i)));
        }

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
        GregTechAPI.registerMetaTileEntity(4173, new TileEntityLargePackager(location("large_packager"), RecipeMaps.UNPACKER_RECIPES));
        COSMIC_RAY_DETECTOR = GregTechAPI.registerMetaTileEntity(4174, new MetaTileEntityCosmicRayDetector(location("cosmic_ray_detector")));

        STEAM_HATCH = GregTechAPI.registerMetaTileEntity(4175, new MetaTileEntitySteamHatch(location("steam_hatch")));
        STEAM_INPUT_BUS = GregTechAPI.registerMetaTileEntity(4176, new MetaTileEntitySteamItemBus(location("steam_input_bus"), false));
        STEAM_OUTPUT_BUS = GregTechAPI.registerMetaTileEntity(4177, new MetaTileEntitySteamItemBus(location("steam_output_bus"), true));
        STEAM_GRINDER = GregTechAPI.registerMetaTileEntity(4178, new MetaTileEntitySteamGrinder(location("steam_grinder")));
        id = 4179;
        for (int i = 9; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier
            TRANSFORMER_1_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityTransformer(location("transformer." + GAValues.VN[i].toLowerCase()), i, 1, 4)));
        }
        id = 4184;
        for (int i = 1; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier, plus one because we dont want ULV
            DIODES.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityDiode(location("diode." + GAValues.VN[i].toLowerCase()), i)));
        }
        STEAM_OVEN = GregTechAPI.registerMetaTileEntity(4197, new MetaTileEntitySteamOven(location("steam_oven")));

        if (GAConfig.Misc.enableDisassembly) {
            id = 4198;
            for (int i = 1; i < GAValues.V.length - 1; i++) {
                final int tier = i; // used for inner class
                DISASSEMBLER.add(GregTechAPI.registerMetaTileEntity(id++, new GASimpleMachineMetaTileEntity(location("disassembler." + GAValues.VN[i].toLowerCase()), GARecipeMaps.DISASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, i) {
                    @Override
                    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
                        tooltip.add(I18n.format("gtadditions.machine.disassembler.tooltip", GAValues.VOLTAGE_NAMES[tier]));
                        super.addInformation(stack, player, tooltip, advanced);
                    }
                }));
            }
        }

        CVD_UNIT = GregTechAPI.registerMetaTileEntity(4213, new MetaTileEntityCVDUnit(location("cvd_unit")));
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
