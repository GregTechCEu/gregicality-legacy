package gregicadditions.machines;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import gregicadditions.GAConfig;
import gregicadditions.Gregicality;
import gregicadditions.client.ClientHandler;
import gregicadditions.machines.energy.MetaTileEntityEnergyInputHatch;
import gregicadditions.machines.energy.MetaTileEntityEnergyOutputHatch;
import gregicadditions.machines.energy.MetaTileEntityTransformer;
import gregicadditions.machines.energy.TileEntityLargeTransformer;
import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.ConverterType;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregicadditions.machines.multi.MetaTileEntityBatteryTower;
import gregicadditions.machines.multi.MetaTileEntityChemicalPlant;
import gregicadditions.machines.multi.MetaTileEntityIndustrialPrimitiveBlastFurnace;
import gregicadditions.machines.multi.TileEntityAlloyBlastFurnace;
import gregicadditions.machines.multi.advance.*;
import gregicadditions.machines.multi.impl.MetaTileEntityRotorHolderForNuclearCoolant;
import gregicadditions.machines.multi.miner.MetaTileEntityChunkMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityLargeMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityOutputFilteredHatch;
import gregicadditions.machines.multi.nuclear.MetaTileEntityHotCoolantTurbine;
import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
import gregicadditions.machines.multi.override.*;
import gregicadditions.machines.multi.simple.*;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.electric.MetaTileEntityAirCollector;
import gregtech.common.metatileentities.electric.MetaTileEntityPump;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GATileEntities {
    public static SimpleMachineMetaTileEntity[] CIRCUITASSEMBLER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CLUSTERMILL = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ELECTRIC_FURNACE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] MACERATOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ALLOY_SMELTER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ARC_FURNACE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ASSEMBLER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] AUTOCLAVE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] BENDER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] BREWERY = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] CANNER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] CENTRIFUGE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_BATH = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_REACTOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] COMPRESSOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] CUTTER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] DISTILLERY = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ELECTROLYZER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ELECTROMAGNETIC_SEPARATOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] EXTRACTOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] EXTRUDER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FERMENTER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FLUID_CANNER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FLUID_EXTRACTOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FLUID_HEATER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FLUID_SOLIDIFIER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FORGE_HAMMER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] FORMING_PRESS = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] LATHE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] MICROWAVE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] MIXER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] ORE_WASHER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] PACKER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] UNPACKER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] PLASMA_ARC_FURNACE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] POLARIZER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] LASER_ENGRAVER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] SIFTER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] THERMAL_CENTRIFUGE = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] WIREMILL = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] DEHYDRATOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleGeneratorMetaTileEntity[] NAQUADAH_REACTOR = new SimpleGeneratorMetaTileEntity[8];
    public static SimpleGeneratorMetaTileEntity[] ROCKET_GENERATOR = new SimpleGeneratorMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] MASS_FAB = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] DECAY_CHAMBER = new SimpleMachineMetaTileEntity[8];
    public static SimpleMachineMetaTileEntity[] GREEN_HOUSE = new SimpleMachineMetaTileEntity[8];
    public static TileEntityFusionReactor[] FUSION_REACTOR = new TileEntityFusionReactor[3];
    public static ListMultimap<EnergyConverterType, MetaTileEntityEnergyConverter> ENERGY_CONVERTER = ArrayListMultimap.create();
    public static MetaTileEntityRotorHolderForNuclearCoolant[] ROTOR_HOLDER = new MetaTileEntityRotorHolderForNuclearCoolant[3];

    //multiblock
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
    public static MetaTileEntityVoidMiner VOID_MINER;
    public static TileEntityLargeTransformer LARGE_TRANSFORMER;
    public static MetaTileEntityIndustrialPrimitiveBlastFurnace INDUSTRIAL_PRIMITIVE_BLAST_FURNACE;
    public static TileEntityAdvancedDistillationTower ADVANCED_DISTILLATION_TOWER;
    public static TileEntityCryogenicFreezer CRYOGENIC_FREEZER;
    public static MetaTileEntityChemicalPlant CHEMICAL_PLANT;
    public static MetaTileEntityLargeRocketEngine LARGE_ROCKET_ENGINE;
    public static TileEntityAlloyBlastFurnace ALLOY_BLAST_FURNACE;
    public static TileEntityLargeForgeHammer LARGE_FORGE_HAMMER;
    public static MetaTileEntityLargeNaquadahReactor LARGE_NAQUADAH_REACTOR;
    public static MetaTileEntityBatteryTower BATTERY_TOWER;

    //Nuclear
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;
    public static MetaTileEntityNuclearReactor NUCLEAR_BREEDER;

    //multiblock
    public static List<MetaTileEntityOutputFilteredHatch> OUTPUT_HATCH_FILTERED = new ArrayList<>();

    //override from GTCE
    public static List<MetaTileEntityEnergyInputHatch> ENERGY_INPUT_HATCH_4_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyInputHatch> ENERGY_INPUT_HATCH_16_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyInputHatch> ENERGY_INPUT_HATCH_64_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyInputHatch> ENERGY_INPUT_HATCH_128_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyOutputHatch> ENERGY_OUTPUT_HATCH_16_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyOutputHatch> ENERGY_OUTPUT_HATCH_32_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyOutputHatch> ENERGY_OUTPUT_HATCH_64_AMPS = new ArrayList<>();
    public static List<MetaTileEntityEnergyOutputHatch> ENERGY_OUTPUT_HATCH_128_AMPS = new ArrayList<>();
    public static List<MetaTileEntityTransformer> TRANSFORMER_4_AMPS = new ArrayList<>();
    public static List<MetaTileEntityTransformer> TRANSFORMER_8_AMPS = new ArrayList<>();
    public static List<MetaTileEntityTransformer> TRANSFORMER_12_AMPS = new ArrayList<>();
    public static List<MetaTileEntityTransformer> TRANSFORMER_16_AMPS = new ArrayList<>();
    public static MetaTileEntityElectricBlastFurnace ELECTRIC_BLAST_FURNACE;
    public static MetaTileEntityVacuumFreezer VACUUM_FREEZER;
    public static MetaTileEntityImplosionCompressor IMPLOSION_COMPRESSOR;
    public static MetaTileEntityDistillationTower DISTILLATION_TOWER;
    public static MetaTileEntityCrackingUnit CRACKER;
    public static MetaTileEntityMultiFurnace MULTI_FURNACE;
    public static MetaTileEntityDieselEngine DIESEL_ENGINE;

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

    public static void init() {

        CIRCUITASSEMBLER[0] = GregTechAPI.registerMetaTileEntity(2000, new SimpleMachineMetaTileEntity(location("circuit_assembler.lv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 1));
        CIRCUITASSEMBLER[1] = GregTechAPI.registerMetaTileEntity(2001, new SimpleMachineMetaTileEntity(location("circuit_assembler.mv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 2));
        CIRCUITASSEMBLER[2] = GregTechAPI.registerMetaTileEntity(2002, new SimpleMachineMetaTileEntity(location("circuit_assembler.hv"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 3));
        CIRCUITASSEMBLER[3] = GregTechAPI.registerMetaTileEntity(2003, new SimpleMachineMetaTileEntity(location("circuit_assembler.ev"), GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 4));
        LARGE_CIRCUIT_ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2004, new TileEntityLargeCircuitAssemblyLine(location("large_circuit_assembly")));

        CLUSTERMILL[0] = GregTechAPI.registerMetaTileEntity(2008, new SimpleMachineMetaTileEntity(location("cluster_mill.lv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 1));
        CLUSTERMILL[1] = GregTechAPI.registerMetaTileEntity(2009, new SimpleMachineMetaTileEntity(location("cluster_mill.mv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 2));
        CLUSTERMILL[2] = GregTechAPI.registerMetaTileEntity(2010, new SimpleMachineMetaTileEntity(location("cluster_mill.hv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 3));
        CLUSTERMILL[3] = GregTechAPI.registerMetaTileEntity(2011, new SimpleMachineMetaTileEntity(location("cluster_mill.ev"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 4));
        if (GAConfig.GT5U.highTierClusterMills) {
            CLUSTERMILL[4] = GregTechAPI.registerMetaTileEntity(2012, new SimpleMachineMetaTileEntity(location("cluster_mill.iv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 5));
            CLUSTERMILL[5] = GregTechAPI.registerMetaTileEntity(2013, new SimpleMachineMetaTileEntity(location("cluster_mill.luv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 6));
            CLUSTERMILL[6] = GregTechAPI.registerMetaTileEntity(2014, new SimpleMachineMetaTileEntity(location("cluster_mill.zpm"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 7));
            CLUSTERMILL[7] = GregTechAPI.registerMetaTileEntity(2015, new SimpleMachineMetaTileEntity(location("cluster_mill.uv"), GARecipeMaps.CLUSTER_MILL_RECIPES, Textures.WIREMILL_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierElectricFurnace) {
            ELECTRIC_FURNACE[4] = GregTechAPI.registerMetaTileEntity(2016, new SimpleMachineMetaTileEntity(location("electric_furnace.iv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 5));
            ELECTRIC_FURNACE[5] = GregTechAPI.registerMetaTileEntity(2017, new SimpleMachineMetaTileEntity(location("electric_furnace.luv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 6));
            ELECTRIC_FURNACE[6] = GregTechAPI.registerMetaTileEntity(2018, new SimpleMachineMetaTileEntity(location("electric_furnace.zpm"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 7));
            ELECTRIC_FURNACE[7] = GregTechAPI.registerMetaTileEntity(2019, new SimpleMachineMetaTileEntity(location("electric_furnace.uv"), RecipeMaps.FURNACE_RECIPES, Textures.FURNACE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierMacerators) {
            MACERATOR[4] = GregTechAPI.registerMetaTileEntity(2020, new SimpleMachineMetaTileEntity(location("macerator.iv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 5));
            MACERATOR[5] = GregTechAPI.registerMetaTileEntity(2021, new SimpleMachineMetaTileEntity(location("macerator.luv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 6));
            MACERATOR[6] = GregTechAPI.registerMetaTileEntity(2022, new SimpleMachineMetaTileEntity(location("macerator.zpm"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 7));
            MACERATOR[7] = GregTechAPI.registerMetaTileEntity(2023, new SimpleMachineMetaTileEntity(location("macerator.uv"), RecipeMaps.MACERATOR_RECIPES, Textures.MACERATOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierAlloySmelter) {
            ALLOY_SMELTER[4] = GregTechAPI.registerMetaTileEntity(2024, new SimpleMachineMetaTileEntity(location("alloy_smelter.iv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 5));
            ALLOY_SMELTER[5] = GregTechAPI.registerMetaTileEntity(2025, new SimpleMachineMetaTileEntity(location("alloy_smelter.luv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 6));
            ALLOY_SMELTER[6] = GregTechAPI.registerMetaTileEntity(2026, new SimpleMachineMetaTileEntity(location("alloy_smelter.zpm"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 7));
            ALLOY_SMELTER[7] = GregTechAPI.registerMetaTileEntity(2027, new SimpleMachineMetaTileEntity(location("alloy_smelter.uv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierArcFurnaces) {
            ARC_FURNACE[4] = GregTechAPI.registerMetaTileEntity(2032, new SimpleMachineMetaTileEntity(location("arc_furnace.iv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 5));
            ARC_FURNACE[5] = GregTechAPI.registerMetaTileEntity(2033, new SimpleMachineMetaTileEntity(location("arc_furnace.luv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 6));
            ARC_FURNACE[6] = GregTechAPI.registerMetaTileEntity(2034, new SimpleMachineMetaTileEntity(location("arc_furnace.zpm"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 7));
            ARC_FURNACE[7] = GregTechAPI.registerMetaTileEntity(2035, new SimpleMachineMetaTileEntity(location("arc_furnace.uv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierAssemblers) {
            ASSEMBLER[5] = GregTechAPI.registerMetaTileEntity(2037, new SimpleMachineMetaTileEntity(location("assembler.luv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 6));
            ASSEMBLER[6] = GregTechAPI.registerMetaTileEntity(2038, new SimpleMachineMetaTileEntity(location("assembler.zpm"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 7));
            ASSEMBLER[7] = GregTechAPI.registerMetaTileEntity(2039, new SimpleMachineMetaTileEntity(location("assembler.uv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierAutoclaves) {
            AUTOCLAVE[5] = GregTechAPI.registerMetaTileEntity(2041, new SimpleMachineMetaTileEntity(location("autoclave.luv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 6));
            AUTOCLAVE[6] = GregTechAPI.registerMetaTileEntity(2042, new SimpleMachineMetaTileEntity(location("autoclave.zpm"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 7));
            AUTOCLAVE[7] = GregTechAPI.registerMetaTileEntity(2043, new SimpleMachineMetaTileEntity(location("autoclave.uv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierBenders) {
            BENDER[4] = GregTechAPI.registerMetaTileEntity(2044, new SimpleMachineMetaTileEntity(location("bender.iv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 5));
            BENDER[5] = GregTechAPI.registerMetaTileEntity(2045, new SimpleMachineMetaTileEntity(location("bender.luv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 6));
            BENDER[6] = GregTechAPI.registerMetaTileEntity(2046, new SimpleMachineMetaTileEntity(location("bender.zpm"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 7));
            BENDER[7] = GregTechAPI.registerMetaTileEntity(2047, new SimpleMachineMetaTileEntity(location("bender.uv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierBreweries) {
            BREWERY[4] = GregTechAPI.registerMetaTileEntity(2048, new SimpleMachineMetaTileEntity(location("brewery.iv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 5));
            BREWERY[5] = GregTechAPI.registerMetaTileEntity(2049, new SimpleMachineMetaTileEntity(location("brewery.luv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 6));
            BREWERY[6] = GregTechAPI.registerMetaTileEntity(2050, new SimpleMachineMetaTileEntity(location("brewery.zpm"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 7));
            BREWERY[7] = GregTechAPI.registerMetaTileEntity(2051, new SimpleMachineMetaTileEntity(location("brewery.uv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierCanners) {
            CANNER[4] = GregTechAPI.registerMetaTileEntity(2052, new SimpleMachineMetaTileEntity(location("canner.iv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 5));
            CANNER[5] = GregTechAPI.registerMetaTileEntity(2053, new SimpleMachineMetaTileEntity(location("canner.luv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 6));
            CANNER[6] = GregTechAPI.registerMetaTileEntity(2054, new SimpleMachineMetaTileEntity(location("canner.zpm"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 7));
            CANNER[7] = GregTechAPI.registerMetaTileEntity(2055, new SimpleMachineMetaTileEntity(location("canner.uv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierCentrifuges) {
            CENTRIFUGE[4] = GregTechAPI.registerMetaTileEntity(2056, new SimpleMachineMetaTileEntity(location("centrifuge.iv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 5));
            CENTRIFUGE[5] = GregTechAPI.registerMetaTileEntity(2057, new SimpleMachineMetaTileEntity(location("centrifuge.luv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 6));
            CENTRIFUGE[6] = GregTechAPI.registerMetaTileEntity(2058, new SimpleMachineMetaTileEntity(location("centrifuge.zpm"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 7));
            CENTRIFUGE[7] = GregTechAPI.registerMetaTileEntity(2059, new SimpleMachineMetaTileEntity(location("centrifuge.uv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierChemicalBaths) {
            CHEMICAL_BATH[4] = GregTechAPI.registerMetaTileEntity(2060, new SimpleMachineMetaTileEntity(location("chemical_bath.iv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 5));
            CHEMICAL_BATH[5] = GregTechAPI.registerMetaTileEntity(2061, new SimpleMachineMetaTileEntity(location("chemical_bath.luv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 6));
            CHEMICAL_BATH[6] = GregTechAPI.registerMetaTileEntity(2062, new SimpleMachineMetaTileEntity(location("chemical_bath.zpm"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 7));
            CHEMICAL_BATH[7] = GregTechAPI.registerMetaTileEntity(2063, new SimpleMachineMetaTileEntity(location("chemical_bath.uv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierChemicalReactors) {
            CHEMICAL_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2064, new SimpleMachineMetaTileEntity(location("chemical_reactor.iv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 5));
            CHEMICAL_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2065, new SimpleMachineMetaTileEntity(location("chemical_reactor.luv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 6));
            CHEMICAL_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2066, new SimpleMachineMetaTileEntity(location("chemical_reactor.zpm"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 7));
            CHEMICAL_REACTOR[7] = GregTechAPI.registerMetaTileEntity(2067, new SimpleMachineMetaTileEntity(location("chemical_reactor.uv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierCompressors) {
            COMPRESSOR[4] = GregTechAPI.registerMetaTileEntity(2068, new SimpleMachineMetaTileEntity(location("compressor.iv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 5));
            COMPRESSOR[5] = GregTechAPI.registerMetaTileEntity(2069, new SimpleMachineMetaTileEntity(location("compressor.luv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 6));
            COMPRESSOR[6] = GregTechAPI.registerMetaTileEntity(2070, new SimpleMachineMetaTileEntity(location("compressor.zpm"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 7));
            COMPRESSOR[7] = GregTechAPI.registerMetaTileEntity(2071, new SimpleMachineMetaTileEntity(location("compressor.uv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierCutters) {
            CUTTER[4] = GregTechAPI.registerMetaTileEntity(2072, new SimpleMachineMetaTileEntity(location("cutter.iv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 5));
            CUTTER[5] = GregTechAPI.registerMetaTileEntity(2073, new SimpleMachineMetaTileEntity(location("cutter.luv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 6));
            CUTTER[6] = GregTechAPI.registerMetaTileEntity(2074, new SimpleMachineMetaTileEntity(location("cutter.zpm"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 7));
            CUTTER[7] = GregTechAPI.registerMetaTileEntity(2075, new SimpleMachineMetaTileEntity(location("cutter.uv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierDistilleries) {
            DISTILLERY[4] = GregTechAPI.registerMetaTileEntity(2076, new SimpleMachineMetaTileEntity(location("distillery.iv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 5));
            DISTILLERY[5] = GregTechAPI.registerMetaTileEntity(2077, new SimpleMachineMetaTileEntity(location("distillery.luv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 6));
            DISTILLERY[6] = GregTechAPI.registerMetaTileEntity(2078, new SimpleMachineMetaTileEntity(location("distillery.zpm"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 7));
            DISTILLERY[7] = GregTechAPI.registerMetaTileEntity(2079, new SimpleMachineMetaTileEntity(location("distillery.uv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierElectrolyzers) {
            ELECTROLYZER[4] = GregTechAPI.registerMetaTileEntity(2080, new SimpleMachineMetaTileEntity(location("electrolyzer.iv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 5));
            ELECTROLYZER[5] = GregTechAPI.registerMetaTileEntity(2081, new SimpleMachineMetaTileEntity(location("electrolyzer.luv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 6));
            ELECTROLYZER[6] = GregTechAPI.registerMetaTileEntity(2082, new SimpleMachineMetaTileEntity(location("electrolyzer.zpm"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 7));
            ELECTROLYZER[7] = GregTechAPI.registerMetaTileEntity(2083, new SimpleMachineMetaTileEntity(location("electrolyzer.uv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierElectromagneticSeparators) {
            ELECTROMAGNETIC_SEPARATOR[4] = GregTechAPI.registerMetaTileEntity(2084, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.iv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 5));
            ELECTROMAGNETIC_SEPARATOR[5] = GregTechAPI.registerMetaTileEntity(2085, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.luv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 6));
            ELECTROMAGNETIC_SEPARATOR[6] = GregTechAPI.registerMetaTileEntity(2086, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.zpm"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 7));
            ELECTROMAGNETIC_SEPARATOR[7] = GregTechAPI.registerMetaTileEntity(2087, new SimpleMachineMetaTileEntity(location("electromagnetic_separator.uv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierExtractors) {
            EXTRACTOR[4] = GregTechAPI.registerMetaTileEntity(2088, new SimpleMachineMetaTileEntity(location("extractor.iv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 5));
            EXTRACTOR[5] = GregTechAPI.registerMetaTileEntity(2089, new SimpleMachineMetaTileEntity(location("extractor.luv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 6));
            EXTRACTOR[6] = GregTechAPI.registerMetaTileEntity(2090, new SimpleMachineMetaTileEntity(location("extractor.zpm"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 7));
            EXTRACTOR[7] = GregTechAPI.registerMetaTileEntity(2091, new SimpleMachineMetaTileEntity(location("extractor.uv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierExtruders) {
            EXTRUDER[4] = GregTechAPI.registerMetaTileEntity(2092, new SimpleMachineMetaTileEntity(location("extruder.iv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 5));
            EXTRUDER[5] = GregTechAPI.registerMetaTileEntity(2093, new SimpleMachineMetaTileEntity(location("extruder.luv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 6));
            EXTRUDER[6] = GregTechAPI.registerMetaTileEntity(2094, new SimpleMachineMetaTileEntity(location("extruder.zpm"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 7));
            EXTRUDER[7] = GregTechAPI.registerMetaTileEntity(2095, new SimpleMachineMetaTileEntity(location("extruder.uv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFermenters) {
            FERMENTER[4] = GregTechAPI.registerMetaTileEntity(2096, new SimpleMachineMetaTileEntity(location("fermenter.iv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 5));
            FERMENTER[5] = GregTechAPI.registerMetaTileEntity(2097, new SimpleMachineMetaTileEntity(location("fermenter.luv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 6));
            FERMENTER[6] = GregTechAPI.registerMetaTileEntity(2098, new SimpleMachineMetaTileEntity(location("fermenter.zpm"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 7));
            FERMENTER[7] = GregTechAPI.registerMetaTileEntity(2099, new SimpleMachineMetaTileEntity(location("fermenter.uv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFluidCanners) {
            FLUID_CANNER[4] = GregTechAPI.registerMetaTileEntity(2100, new SimpleMachineMetaTileEntity(location("fluid_canner.iv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 5));
            FLUID_CANNER[5] = GregTechAPI.registerMetaTileEntity(2101, new SimpleMachineMetaTileEntity(location("fluid_canner.luv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 6));
            FLUID_CANNER[6] = GregTechAPI.registerMetaTileEntity(2102, new SimpleMachineMetaTileEntity(location("fluid_canner.zpm"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 7));
            FLUID_CANNER[7] = GregTechAPI.registerMetaTileEntity(2103, new SimpleMachineMetaTileEntity(location("fluid_canner.uv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFluidExtractors) {
            FLUID_EXTRACTOR[4] = GregTechAPI.registerMetaTileEntity(2104, new SimpleMachineMetaTileEntity(location("fluid_extractor.iv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 5));
            FLUID_EXTRACTOR[5] = GregTechAPI.registerMetaTileEntity(2105, new SimpleMachineMetaTileEntity(location("fluid_extractor.luv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 6));
            FLUID_EXTRACTOR[6] = GregTechAPI.registerMetaTileEntity(2106, new SimpleMachineMetaTileEntity(location("fluid_extractor.zpm"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 7));
            FLUID_EXTRACTOR[7] = GregTechAPI.registerMetaTileEntity(2107, new SimpleMachineMetaTileEntity(location("fluid_extractor.uv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFluidHeaters) {
            FLUID_HEATER[4] = GregTechAPI.registerMetaTileEntity(2108, new SimpleMachineMetaTileEntity(location("fluid_heater.iv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 5));
            FLUID_HEATER[5] = GregTechAPI.registerMetaTileEntity(2109, new SimpleMachineMetaTileEntity(location("fluid_heater.luv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 6));
            FLUID_HEATER[6] = GregTechAPI.registerMetaTileEntity(2110, new SimpleMachineMetaTileEntity(location("fluid_heater.zpm"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 7));
            FLUID_HEATER[7] = GregTechAPI.registerMetaTileEntity(2111, new SimpleMachineMetaTileEntity(location("fluid_heater.uv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFluidSolidifiers) {
            FLUID_SOLIDIFIER[4] = GregTechAPI.registerMetaTileEntity(2112, new SimpleMachineMetaTileEntity(location("fluid_solidifier.iv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 5));
            FLUID_SOLIDIFIER[5] = GregTechAPI.registerMetaTileEntity(2113, new SimpleMachineMetaTileEntity(location("fluid_solidifier.luv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 6));
            FLUID_SOLIDIFIER[6] = GregTechAPI.registerMetaTileEntity(2114, new SimpleMachineMetaTileEntity(location("fluid_solidifier.zpm"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 7));
            FLUID_SOLIDIFIER[7] = GregTechAPI.registerMetaTileEntity(2115, new SimpleMachineMetaTileEntity(location("fluid_solidifier.uv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierForgeHammers) {
            FORGE_HAMMER[4] = GregTechAPI.registerMetaTileEntity(2116, new SimpleMachineMetaTileEntity(location("forge_hammer.iv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 5));
            FORGE_HAMMER[5] = GregTechAPI.registerMetaTileEntity(2117, new SimpleMachineMetaTileEntity(location("forge_hammer.luv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 6));
            FORGE_HAMMER[6] = GregTechAPI.registerMetaTileEntity(2118, new SimpleMachineMetaTileEntity(location("forge_hammer.zpm"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 7));
            FORGE_HAMMER[7] = GregTechAPI.registerMetaTileEntity(2119, new SimpleMachineMetaTileEntity(location("forge_hammer.uv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierFormingPresses) {
            FORMING_PRESS[4] = GregTechAPI.registerMetaTileEntity(2120, new SimpleMachineMetaTileEntity(location("forming_press.iv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 5));
            FORMING_PRESS[5] = GregTechAPI.registerMetaTileEntity(2121, new SimpleMachineMetaTileEntity(location("forming_press.luv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 6));
            FORMING_PRESS[6] = GregTechAPI.registerMetaTileEntity(2122, new SimpleMachineMetaTileEntity(location("forming_press.zpm"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 7));
            FORMING_PRESS[7] = GregTechAPI.registerMetaTileEntity(2123, new SimpleMachineMetaTileEntity(location("forming_press.uv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierLathes) {
            LATHE[4] = GregTechAPI.registerMetaTileEntity(2124, new SimpleMachineMetaTileEntity(location("lathe.iv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 5));
            LATHE[5] = GregTechAPI.registerMetaTileEntity(2125, new SimpleMachineMetaTileEntity(location("lathe.luv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 6));
            LATHE[6] = GregTechAPI.registerMetaTileEntity(2126, new SimpleMachineMetaTileEntity(location("lathe.zpm"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 7));
            LATHE[7] = GregTechAPI.registerMetaTileEntity(2127, new SimpleMachineMetaTileEntity(location("lathe.uv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierMicrowaves) {
            MICROWAVE[4] = GregTechAPI.registerMetaTileEntity(2128, new SimpleMachineMetaTileEntity(location("microwave.iv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 5));
            MICROWAVE[5] = GregTechAPI.registerMetaTileEntity(2129, new SimpleMachineMetaTileEntity(location("microwave.luv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 6));
            MICROWAVE[6] = GregTechAPI.registerMetaTileEntity(2130, new SimpleMachineMetaTileEntity(location("microwave.zpm"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 7));
            MICROWAVE[7] = GregTechAPI.registerMetaTileEntity(2131, new SimpleMachineMetaTileEntity(location("microwave.uv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierMixers) {
            MIXER[4] = GregTechAPI.registerMetaTileEntity(2132, new SimpleMachineMetaTileEntity(location("mixer.iv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 5));
            MIXER[5] = GregTechAPI.registerMetaTileEntity(2133, new SimpleMachineMetaTileEntity(location("mixer.luv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 6));
            MIXER[6] = GregTechAPI.registerMetaTileEntity(2134, new SimpleMachineMetaTileEntity(location("mixer.zpm"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 7));
            MIXER[7] = GregTechAPI.registerMetaTileEntity(2135, new SimpleMachineMetaTileEntity(location("mixer.uv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierOreWashers) {
            ORE_WASHER[4] = GregTechAPI.registerMetaTileEntity(2136, new SimpleMachineMetaTileEntity(location("ore_washer.iv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 5));
            ORE_WASHER[5] = GregTechAPI.registerMetaTileEntity(2137, new SimpleMachineMetaTileEntity(location("ore_washer.luv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 6));
            ORE_WASHER[6] = GregTechAPI.registerMetaTileEntity(2138, new SimpleMachineMetaTileEntity(location("ore_washer.zpm"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 7));
            ORE_WASHER[7] = GregTechAPI.registerMetaTileEntity(2139, new SimpleMachineMetaTileEntity(location("ore_washer.uv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierPackers) {
            PACKER[4] = GregTechAPI.registerMetaTileEntity(2140, new SimpleMachineMetaTileEntity(location("packer.iv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 5));
            PACKER[5] = GregTechAPI.registerMetaTileEntity(2141, new SimpleMachineMetaTileEntity(location("packer.luv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 6));
            PACKER[6] = GregTechAPI.registerMetaTileEntity(2142, new SimpleMachineMetaTileEntity(location("packer.zpm"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 7));
            PACKER[7] = GregTechAPI.registerMetaTileEntity(2143, new SimpleMachineMetaTileEntity(location("packer.uv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierUnpackers) {
            UNPACKER[4] = GregTechAPI.registerMetaTileEntity(2144, new SimpleMachineMetaTileEntity(location("unpacker.iv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 5));
            UNPACKER[5] = GregTechAPI.registerMetaTileEntity(2145, new SimpleMachineMetaTileEntity(location("unpacker.luv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 6));
            UNPACKER[6] = GregTechAPI.registerMetaTileEntity(2146, new SimpleMachineMetaTileEntity(location("unpacker.zpm"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 7));
            UNPACKER[7] = GregTechAPI.registerMetaTileEntity(2147, new SimpleMachineMetaTileEntity(location("unpacker.uv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierPlasmaArcFurnaces) {
            PLASMA_ARC_FURNACE[4] = GregTechAPI.registerMetaTileEntity(2148, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.iv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 5));
            PLASMA_ARC_FURNACE[5] = GregTechAPI.registerMetaTileEntity(2149, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.luv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 6));
            PLASMA_ARC_FURNACE[6] = GregTechAPI.registerMetaTileEntity(2150, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.zpm"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 7));
            PLASMA_ARC_FURNACE[7] = GregTechAPI.registerMetaTileEntity(2151, new SimpleMachineMetaTileEntity(location("plasma_arc_furnace.uv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierPolarizers) {
            POLARIZER[4] = GregTechAPI.registerMetaTileEntity(2152, new SimpleMachineMetaTileEntity(location("polarizer.iv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 5));
            POLARIZER[5] = GregTechAPI.registerMetaTileEntity(2153, new SimpleMachineMetaTileEntity(location("polarizer.luv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 6));
            POLARIZER[6] = GregTechAPI.registerMetaTileEntity(2154, new SimpleMachineMetaTileEntity(location("polarizer.zpm"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 7));
            POLARIZER[7] = GregTechAPI.registerMetaTileEntity(2155, new SimpleMachineMetaTileEntity(location("polarizer.uv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierLaserEngravers) {
            LASER_ENGRAVER[5] = GregTechAPI.registerMetaTileEntity(2157, new SimpleMachineMetaTileEntity(location("laser_engraver.luv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 6));
            LASER_ENGRAVER[6] = GregTechAPI.registerMetaTileEntity(2158, new SimpleMachineMetaTileEntity(location("laser_engraver.zpm"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 7));
            LASER_ENGRAVER[7] = GregTechAPI.registerMetaTileEntity(2159, new SimpleMachineMetaTileEntity(location("laser_engraver.uv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierSifters) {
            SIFTER[4] = GregTechAPI.registerMetaTileEntity(2160, new SimpleMachineMetaTileEntity(location("sifter.iv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 5));
            SIFTER[5] = GregTechAPI.registerMetaTileEntity(2161, new SimpleMachineMetaTileEntity(location("sifter.luv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 6));
            SIFTER[6] = GregTechAPI.registerMetaTileEntity(2162, new SimpleMachineMetaTileEntity(location("sifter.zpm"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 7));
            SIFTER[7] = GregTechAPI.registerMetaTileEntity(2163, new SimpleMachineMetaTileEntity(location("sifter.uv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierThermalCentrifuges) {
            THERMAL_CENTRIFUGE[4] = GregTechAPI.registerMetaTileEntity(2164, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.iv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 5));
            THERMAL_CENTRIFUGE[5] = GregTechAPI.registerMetaTileEntity(2165, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.luv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 6));
            THERMAL_CENTRIFUGE[6] = GregTechAPI.registerMetaTileEntity(2166, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.zpm"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 7));
            THERMAL_CENTRIFUGE[7] = GregTechAPI.registerMetaTileEntity(2167, new SimpleMachineMetaTileEntity(location("thermal_centrifuge.uv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, 8));
        }

        if (GAConfig.GT5U.highTierWiremills) {
            WIREMILL[4] = GregTechAPI.registerMetaTileEntity(2168, new SimpleMachineMetaTileEntity(location("wiremill.iv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 5));
            WIREMILL[5] = GregTechAPI.registerMetaTileEntity(2169, new SimpleMachineMetaTileEntity(location("wiremill.luv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 6));
            WIREMILL[6] = GregTechAPI.registerMetaTileEntity(2170, new SimpleMachineMetaTileEntity(location("wiremill.zpm"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 7));
            WIREMILL[7] = GregTechAPI.registerMetaTileEntity(2171, new SimpleMachineMetaTileEntity(location("wiremill.uv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, 8));
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

        MASS_FAB[0] = GregTechAPI.registerMetaTileEntity(2175, new SimpleMachineMetaTileEntity(location("mass_fab.lv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 1));
        MASS_FAB[1] = GregTechAPI.registerMetaTileEntity(2176, new SimpleMachineMetaTileEntity(location("mass_fab.mv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 2));
        MASS_FAB[2] = GregTechAPI.registerMetaTileEntity(2177, new SimpleMachineMetaTileEntity(location("mass_fab.hv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 3));
        MASS_FAB[3] = GregTechAPI.registerMetaTileEntity(2178, new SimpleMachineMetaTileEntity(location("mass_fab.ev"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 4));
        if (GAConfig.GT5U.highTierMassFabs) {
            MASS_FAB[4] = GregTechAPI.registerMetaTileEntity(2179, new SimpleMachineMetaTileEntity(location("mass_fab.iv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 5));
            MASS_FAB[5] = GregTechAPI.registerMetaTileEntity(2180, new SimpleMachineMetaTileEntity(location("mass_fab.luv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 6));
            MASS_FAB[6] = GregTechAPI.registerMetaTileEntity(2181, new SimpleMachineMetaTileEntity(location("mass_fab.zpm"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 7));
            MASS_FAB[7] = GregTechAPI.registerMetaTileEntity(2182, new SimpleMachineMetaTileEntity(location("mass_fab.uv"), GARecipeMaps.MASS_FAB_RECIPES, ClientHandler.MASS_FAB_OVERLAY, 8));
        }

        REPLICATOR[0] = GregTechAPI.registerMetaTileEntity(2183, new SimpleMachineMetaTileEntity(location("replicator.lv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 1));
        REPLICATOR[1] = GregTechAPI.registerMetaTileEntity(2184, new SimpleMachineMetaTileEntity(location("replicator.mv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 2));
        REPLICATOR[2] = GregTechAPI.registerMetaTileEntity(2185, new SimpleMachineMetaTileEntity(location("replicator.hv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 3));
        REPLICATOR[3] = GregTechAPI.registerMetaTileEntity(2186, new SimpleMachineMetaTileEntity(location("replicator.ev"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 4));
        if (GAConfig.GT5U.highTierReplicators) {
            REPLICATOR[4] = GregTechAPI.registerMetaTileEntity(2187, new SimpleMachineMetaTileEntity(location("replicator.iv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 5));
            REPLICATOR[5] = GregTechAPI.registerMetaTileEntity(2188, new SimpleMachineMetaTileEntity(location("replicator.luv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 6));
            REPLICATOR[6] = GregTechAPI.registerMetaTileEntity(2189, new SimpleMachineMetaTileEntity(location("replicator.zpm"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 7));
            REPLICATOR[7] = GregTechAPI.registerMetaTileEntity(2190, new SimpleMachineMetaTileEntity(location("replicator.uv"), GARecipeMaps.REPLICATOR_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 8));
        }

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

        MetaTileEntities.ELECTRIC_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(2534, new MetaTileEntityElectricBlastFurnace(gregtechId("electric_blast_furnace")));
        ELECTRIC_BLAST_FURNACE = (MetaTileEntityElectricBlastFurnace) MetaTileEntities.ELECTRIC_BLAST_FURNACE;
        MetaTileEntities.VACUUM_FREEZER = GregTechAPI.registerMetaTileEntity(2535, new MetaTileEntityVacuumFreezer(gregtechId("vacuum_freezer")));
        VACUUM_FREEZER = (MetaTileEntityVacuumFreezer) MetaTileEntities.VACUUM_FREEZER;
        MetaTileEntities.IMPLOSION_COMPRESSOR = GregTechAPI.registerMetaTileEntity(2536, new MetaTileEntityImplosionCompressor(gregtechId("implosion_compressor")));
        IMPLOSION_COMPRESSOR = (MetaTileEntityImplosionCompressor) MetaTileEntities.IMPLOSION_COMPRESSOR;
        MetaTileEntities.DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(2537, new MetaTileEntityDistillationTower(gregtechId("distillation_tower")));
        DISTILLATION_TOWER = (MetaTileEntityDistillationTower) MetaTileEntities.DISTILLATION_TOWER;
        MetaTileEntities.CRACKER = GregTechAPI.registerMetaTileEntity(2538, new MetaTileEntityCrackingUnit(gregtechId("cracker")));
        CRACKER = (MetaTileEntityCrackingUnit) MetaTileEntities.CRACKER;
        MetaTileEntities.MULTI_FURNACE = GregTechAPI.registerMetaTileEntity(2539, new MetaTileEntityMultiFurnace(gregtechId("multi_furnace")));
        MULTI_FURNACE = (MetaTileEntityMultiFurnace) MetaTileEntities.MULTI_FURNACE;
        MetaTileEntities.DIESEL_ENGINE = GregTechAPI.registerMetaTileEntity(2540, new MetaTileEntityDieselEngine(gregtechId("diesel_engine")));
        DIESEL_ENGINE = (MetaTileEntityDieselEngine) MetaTileEntities.DIESEL_ENGINE;

        MetaTileEntities.LARGE_STEAM_TURBINE = LARGE_STEAM_TURBINE = GregTechAPI.registerMetaTileEntity(2541, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.steam"), MetaTileEntityLargeTurbine.TurbineType.valueOf("STEAM_OVERRIDE")));
        MetaTileEntities.LARGE_GAS_TURBINE = LARGE_GAS_TURBINE = GregTechAPI.registerMetaTileEntity(2542, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.gas"), MetaTileEntityLargeTurbine.TurbineType.valueOf("GAS_OVERRIDE")));
        MetaTileEntities.LARGE_PLASMA_TURBINE = LARGE_PLASMA_TURBINE = GregTechAPI.registerMetaTileEntity(2543, new MetaTileEntityLargeTurbine(gregtechId("large_turbine.plasma"), MetaTileEntityLargeTurbine.TurbineType.valueOf("PLASMA_OVERRIDE")));
        HOT_COOLANT_TURBINE = GregTechAPI.registerMetaTileEntity(2544, new MetaTileEntityHotCoolantTurbine(location("large_turbine.hot_coolant"), MetaTileEntityHotCoolantTurbine.TurbineType.HOT_COOLANT));

        NUCLEAR_REACTOR = GregTechAPI.registerMetaTileEntity(2545, new MetaTileEntityNuclearReactor(location("nuclear_reactor"), GARecipeMaps.NUCLEAR_REACTOR_RECIPES));
        NUCLEAR_BREEDER = GregTechAPI.registerMetaTileEntity(2546, new MetaTileEntityNuclearReactor(location("nuclear_breeder"), GARecipeMaps.NUCLEAR_BREEDER_RECIPES));
        LARGE_MINER[0] = GregTechAPI.registerMetaTileEntity(2548, new MetaTileEntityLargeMiner(location("miner.basic"), Miner.Type.BASIC, Materials.BlackSteel));
        LARGE_MINER[1] = GregTechAPI.registerMetaTileEntity(2549, new MetaTileEntityLargeMiner(location("miner.large"), Miner.Type.LARGE, Materials.HSSG));
        LARGE_MINER[2] = GregTechAPI.registerMetaTileEntity(2550, new MetaTileEntityLargeMiner(location("miner.advance"), Miner.Type.ADVANCE, Materials.HSSS));
        VOID_MINER = GregTechAPI.registerMetaTileEntity(2551, new MetaTileEntityVoidMiner(location("void_miner")));
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

        DEHYDRATOR[0] = GregTechAPI.registerMetaTileEntity(2248, new SimpleMachineMetaTileEntity(location("dehydrator.lv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 1));
        DEHYDRATOR[1] = GregTechAPI.registerMetaTileEntity(2249, new SimpleMachineMetaTileEntity(location("dehydrator.mv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 2));
        DEHYDRATOR[2] = GregTechAPI.registerMetaTileEntity(2250, new SimpleMachineMetaTileEntity(location("dehydrator.hv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 3));
        DEHYDRATOR[3] = GregTechAPI.registerMetaTileEntity(2251, new SimpleMachineMetaTileEntity(location("dehydrator.ev"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 4));
        if (GAConfig.GT5U.highTierChemicalDehydrator) {
            DEHYDRATOR[4] = GregTechAPI.registerMetaTileEntity(2252, new SimpleMachineMetaTileEntity(location("dehydrator.iv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 5));
            DEHYDRATOR[5] = GregTechAPI.registerMetaTileEntity(2253, new SimpleMachineMetaTileEntity(location("dehydrator.luv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 6));
            DEHYDRATOR[6] = GregTechAPI.registerMetaTileEntity(2254, new SimpleMachineMetaTileEntity(location("dehydrator.zpm"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 7));
            DEHYDRATOR[7] = GregTechAPI.registerMetaTileEntity(2255, new SimpleMachineMetaTileEntity(location("dehydrator.uv"), GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, 8));

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
                TRANSFORMER_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".4"), i, 4, 16)));
                TRANSFORMER_8_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".8"), i, 8, 32)));
                TRANSFORMER_12_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".12"), i, 12, 48)));
                TRANSFORMER_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityTransformer(location("transformer." + GTValues.VN[i].toLowerCase() + ".16"), i, 16, 64)));
            }
        }
        for (int i = 0; i < GTValues.V.length - 1; i++) {
            ENERGY_INPUT_HATCH_4_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyInputHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".4"), i, 4)));
            ENERGY_INPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyInputHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".16"), i, 16)));
            ENERGY_INPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyInputHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".64"), i, 64)));
            ENERGY_INPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyInputHatch(location("energy_hatch.input." + GTValues.VN[i].toLowerCase() + ".128"), i, 128)));

            ENERGY_OUTPUT_HATCH_16_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyOutputHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".16"), i, 16)));
            ENERGY_OUTPUT_HATCH_32_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyOutputHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".32"), i, 32)));
            ENERGY_OUTPUT_HATCH_64_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyOutputHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".64"), i, 64)));
            ENERGY_OUTPUT_HATCH_128_AMPS.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityEnergyOutputHatch(location("energy_hatch.output." + GTValues.VN[i].toLowerCase() + ".128"), i, 128)));
        }

        //        DECAY_CHAMBER[0] = GregTechAPI.registerMetaTileEntity(3200, new SimpleMachineMetaTileEntity(location("decay_chamber.lv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 1));
        DECAY_CHAMBER[1] = GregTechAPI.registerMetaTileEntity(3201, new SimpleMachineMetaTileEntity(location("decay_chamber.mv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 2));
        DECAY_CHAMBER[2] = GregTechAPI.registerMetaTileEntity(3202, new SimpleMachineMetaTileEntity(location("decay_chamber.hv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 3));
        DECAY_CHAMBER[3] = GregTechAPI.registerMetaTileEntity(3203, new SimpleMachineMetaTileEntity(location("decay_chamber.ev"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 4));
        if (GAConfig.GT5U.highTierDecayChamber) {
            DECAY_CHAMBER[4] = GregTechAPI.registerMetaTileEntity(3204, new SimpleMachineMetaTileEntity(location("decay_chamber.iv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 5));
            DECAY_CHAMBER[5] = GregTechAPI.registerMetaTileEntity(3205, new SimpleMachineMetaTileEntity(location("decay_chamber.luv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 6));
            DECAY_CHAMBER[6] = GregTechAPI.registerMetaTileEntity(3206, new SimpleMachineMetaTileEntity(location("decay_chamber.zpm"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 7));
            DECAY_CHAMBER[7] = GregTechAPI.registerMetaTileEntity(3207, new SimpleMachineMetaTileEntity(location("decay_chamber.uv"), GARecipeMaps.DECAY_CHAMBERS_RECIPES, ClientHandler.REPLICATOR_OVERLAY, 8));
        }

        ROTOR_HOLDER[0] = GregTechAPI.registerMetaTileEntity(3208, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.hv"), GTValues.HV, 1.1f));
        ROTOR_HOLDER[1] = GregTechAPI.registerMetaTileEntity(3209, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.luv"), GTValues.LuV, 1.35f));
        ROTOR_HOLDER[2] = GregTechAPI.registerMetaTileEntity(3210, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.max"), GTValues.MAX, 1.7f));

        GREEN_HOUSE[0] = GregTechAPI.registerMetaTileEntity(3211, new SimpleMachineMetaTileEntity(location("green_house.lv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 1));
        GREEN_HOUSE[1] = GregTechAPI.registerMetaTileEntity(3212, new SimpleMachineMetaTileEntity(location("green_house.mv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 2));
        GREEN_HOUSE[2] = GregTechAPI.registerMetaTileEntity(3213, new SimpleMachineMetaTileEntity(location("green_house.hv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 3));
        GREEN_HOUSE[3] = GregTechAPI.registerMetaTileEntity(3214, new SimpleMachineMetaTileEntity(location("green_house.ev"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 4));
        if (GAConfig.GT5U.highTierGreenHouse) {
            GREEN_HOUSE[4] = GregTechAPI.registerMetaTileEntity(3215, new SimpleMachineMetaTileEntity(location("green_house.iv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 5));
            GREEN_HOUSE[5] = GregTechAPI.registerMetaTileEntity(3216, new SimpleMachineMetaTileEntity(location("green_house.luv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 6));
            GREEN_HOUSE[6] = GregTechAPI.registerMetaTileEntity(3217, new SimpleMachineMetaTileEntity(location("green_house.zpm"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 7));
            GREEN_HOUSE[7] = GregTechAPI.registerMetaTileEntity(3218, new SimpleMachineMetaTileEntity(location("green_house.uv"), GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, 8));
        }

        id = 3220;
        for (int i = 0; i < GTValues.V.length - 1; i++) {
            OUTPUT_HATCH_FILTERED.add(GregTechAPI.registerMetaTileEntity(id++, new MetaTileEntityOutputFilteredHatch(location("fluid_hatch.export_filtered." + GTValues.VN[i].toLowerCase()), i)));
        }


    }


    public static ResourceLocation location(String name) {
        return new ResourceLocation(Gregicality.MODID, name);
    }

    private static ResourceLocation gregtechId(String name) {
        return new ResourceLocation(GTValues.MODID, name);
    }
}
