package gregicadditions.machines;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregicadditions.client.ClientHandler;
import gregicadditions.machines.energy.GAMetaTileEntityDiode;
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
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityQubitHatch;
import gregicadditions.machines.multi.miner.MetaTileEntityChunkMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityLargeMiner;
import gregicadditions.machines.multi.miner.MetaTileEntityVoidMiner;
import gregicadditions.machines.multi.miner.Miner;
import gregicadditions.machines.multi.multiblockpart.*;
import gregicadditions.machines.multi.nuclear.*;
import gregicadditions.machines.multi.qubit.*;
import gregicadditions.machines.multi.simple.*;
import gregicadditions.machines.multi.uumatter.*;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.Textures;
import gregtech.common.metatileentities.electric.MetaTileEntityHull;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;

public class GATileEntities {

    // TODO Removals
    public static MetaTileEntityHull[] GA_HULLS = new MetaTileEntityHull[5]; // todo remove, carefully

    // TODO Move to CEu
    public static MetaTileEntityMonitorScreen MONITOR_SCREEN; // todo move to CEu
    public static MetaTileEntityCentralMonitor CENTRAL_MONITOR; // todo move to CEu
    public static MetaTileEntityLargeMiner[] LARGE_MINER = new MetaTileEntityLargeMiner[3]; // todo move to CEu
    public static MetaTileEntityRockBreaker[] ROCK_BREAKER = new MetaTileEntityRockBreaker[8]; // todo move to CEu
    public static ListMultimap<EnergyConverterType, MetaTileEntityEnergyConverter> ENERGY_CONVERTER = ArrayListMultimap.create(); // todo move to CEu
    public static MetaTileEntityBatteryTower BATTERY_TOWER; // todo move to CEu, and rework
    public static MetaTileEntityFluidDrillingPlant[] FLUID_DRILLING_PLANT = new MetaTileEntityFluidDrillingPlant[3]; // todo move to CEu
    public static List<MetaTileEntityMultiFluidHatch> INPUT_HATCH_MULTI = new ArrayList<>(); // todo move to CEu
    public static List<MetaTileEntityMultiFluidHatch> OUTPUT_HATCH_MULTI = new ArrayList<>(); // todo move to CEu
    public static MetaTileEntityChunkMiner[] MINER = new MetaTileEntityChunkMiner[3]; // todo move to CEu
    public static List<GAMetaTileEntityDiode> DIODES = new ArrayList<>(); // todo move to CEu
    public static SimpleMachineMetaTileEntity SIMPLE_ORE_WASHER; // todo move to CEu
    public static SimpleMachineMetaTileEntity[] DISASSEMBLER = new SimpleMachineMetaTileEntity[14]; // todo move to CEu
    public static TileEntityBuffer[] BUFFER = new TileEntityBuffer[3]; // todo move to CEu
    public static TileEntityWorldAccelerator[] WORLD_ACCELERATOR = new TileEntityWorldAccelerator[8]; // todo move to CEu

    // TODO Organize
    public static SimpleMachineMetaTileEntity[] DEHYDRATOR = new SimpleMachineMetaTileEntity[14];
    public static SimpleMachineMetaTileEntity[] DECAY_CHAMBER = new SimpleMachineMetaTileEntity[14];
    public static SimpleMachineMetaTileEntity[] GREEN_HOUSE = new SimpleMachineMetaTileEntity[14];
    public static SimpleGeneratorMetaTileEntity[] NAQUADAH_REACTOR = new SimpleGeneratorMetaTileEntity[8]; // todo idk what but something needs to be done about this
    public static SimpleGeneratorMetaTileEntity[] ROCKET_GENERATOR = new SimpleGeneratorMetaTileEntity[8];
    public static MetaTileEntityRotorHolderForNuclearCoolant[] ROTOR_HOLDER = new MetaTileEntityRotorHolderForNuclearCoolant[4];
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
    public static MetaTileEntityAdvFusionReactor ADVANCED_FUSION_REACTOR;
    public static MetaTileEntityStellarForge STELLAR_FORGE;
    public static MetaTileEntityQubitComputer QUBIT_COMPUTER;
    public static MetaTileEntitySolarSampler SOLAR_FLUID_SAMPLER;
    public static MetaTileEntityBioReactor BIO_REACTOR;
    public static TileEntityLargePackager LARGE_PACKAGER;
    public static MetaTileEntityCosmicRayDetector COSMIC_RAY_DETECTOR;
    public static MetaTileEntityNuclearReactor NUCLEAR_REACTOR;
    public static MetaTileEntityNuclearReactor NUCLEAR_BREEDER;
    public static MetaTileEntityGasCentrifuge GAS_CENTRIFUGE;
    public static TileEntityLargeLaserEngraver LARGE_LASER_ENGRAVER;
    public static MetaTileEntityQubitHatch[] QBIT_INPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];
    public static MetaTileEntityQubitHatch[] QBIT_OUTPUT_HATCH = new MetaTileEntityQubitHatch[GAValues.QUBIT.length];
    public static MetaTileEntityHotCoolantTurbine HOT_COOLANT_TURBINE;
    public static SteamPump STEAM_PUMP;
    public static TileEntitySteamMixer STEAM_MIXER;
    public static MetaTileEntityPlasmaCondenser PLASMA_CONDENSER;
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

        // todo REORGANIZE IDs, there will be conflicts, and it is also a total mess right now

        // todo rename everything to start with "MetaTileEntity"

        // todo get rid of all of the dumb overrides for base classes. GAWorkableTieredMetaTileEntity, GATieredMetaTileEntity, etc etc etc

        MONITOR_SCREEN = GregTechAPI.registerMetaTileEntity(1999, new MetaTileEntityMonitorScreen(location("monitor_screen")));

        LARGE_CIRCUIT_ASSEMBLY_LINE = GregTechAPI.registerMetaTileEntity(2004, new TileEntityLargeCircuitAssemblyLine(location("large_circuit_assembly")));

        NAQUADAH_REACTOR[4] = GregTechAPI.registerMetaTileEntity(2173, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk2"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 5));
        NAQUADAH_REACTOR[5] = GregTechAPI.registerMetaTileEntity(2174, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk3"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6));
        NAQUADAH_REACTOR[6] = GregTechAPI.registerMetaTileEntity(2191, new SimpleGeneratorMetaTileEntity(location("naquadah_reactor.mk4"), GARecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7));

        CENTRAL_MONITOR = GregTechAPI.registerMetaTileEntity(2499, new MetaTileEntityCentralMonitor(location("central_monitor")));

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
        ROCKET_GENERATOR[3] = GregTechAPI.registerMetaTileEntity(2236, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk1"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 4));
        ROCKET_GENERATOR[4] = GregTechAPI.registerMetaTileEntity(2237, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk2"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 5));
        ROCKET_GENERATOR[5] = GregTechAPI.registerMetaTileEntity(2238, new SimpleGeneratorMetaTileEntity(location("rocket_generator.mk3"), GARecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 6));

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

        ROTOR_HOLDER[0] = GregTechAPI.registerMetaTileEntity(3208, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.hv"), GTValues.HV, 1.1f));
        ROTOR_HOLDER[1] = GregTechAPI.registerMetaTileEntity(3209, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.luv"), GTValues.LuV, 1.35f));
        ROTOR_HOLDER[2] = GregTechAPI.registerMetaTileEntity(3210, new MetaTileEntityRotorHolderForNuclearCoolant(location("rotor_holder.uhv"), GAValues.UHV, 1.7f));

        registerSimpleMetaTileEntity(DEHYDRATOR, 4000, "dehydrator", GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, true, GATileEntities::location);
        registerSimpleMetaTileEntity(DECAY_CHAMBER, 3201, "decay_chamber", GARecipeMaps.DECAY_CHAMBERS_RECIPES, Textures.REPLICATOR_OVERLAY, true, GATileEntities::location);
        registerSimpleMetaTileEntity(GREEN_HOUSE, 3211, "green_house", GARecipeMaps.GREEN_HOUSE_RECIPES, Textures.FERMENTER_OVERLAY, true, GATileEntities::location);

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

        LARGE_LASER_ENGRAVER = GregTechAPI.registerMetaTileEntity(4137, new TileEntityLargeLaserEngraver(location("large_laser_engraver")));
        BIO_REACTOR = GregTechAPI.registerMetaTileEntity(4170, new MetaTileEntityBioReactor(location("bio_reactor")));
        PLASMA_CONDENSER = GregTechAPI.registerMetaTileEntity(4171, new MetaTileEntityPlasmaCondenser(location("plasma_condenser")));
        LARGE_PACKAGER = GregTechAPI.registerMetaTileEntity(4172, new TileEntityLargePackager(location("large_packager"), RecipeMaps.PACKER_RECIPES));
        COSMIC_RAY_DETECTOR = GregTechAPI.registerMetaTileEntity(4174, new MetaTileEntityCosmicRayDetector(location("cosmic_ray_detector")));

        id = 4184;
        for (int i = 1; i < GAValues.V.length - 1; i++) { // minus 1 because we dont want MAX tier, plus one because we dont want ULV
            DIODES.add(GregTechAPI.registerMetaTileEntity(id++, new GAMetaTileEntityDiode(location("diode." + GAValues.VN[i].toLowerCase()), i)));
        }

        // todo this needs to deal with tiered tooltip
        if (GAConfig.Misc.enableDisassembly) registerSimpleMetaTileEntity(DISASSEMBLER, 4198, "disassembler", GARecipeMaps.DISASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, true, GATileEntities::location);

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
        LARGE_EXTRACTOR = GregTechAPI.registerMetaTileEntity(4227, new TileEntityLargeExtractor(location("large_extractor"), RecipeMaps.EXTRACTOR_RECIPES)); // todo make sure this multi is ok
        LARGE_ARC_FURNACE = GregTechAPI.registerMetaTileEntity(4228, new TileEntityLargeArcFurnace(location("large_arc_furnace"), RecipeMaps.ARC_FURNACE_RECIPES));
        LARGE_CANNING_MACHINE = GregTechAPI.registerMetaTileEntity(4229, new TileEntityLargeCanningMachine(location("large_canning_machine"), RecipeMaps.CANNER_RECIPES));
        LARGE_MASS_FABRICATOR = GregTechAPI.registerMetaTileEntity(4230, new TileEntityLargeMassFabricator(location("large_mass_fabricator"), RecipeMaps.MASS_FABRICATOR_RECIPES));
        LARGE_REPLICATOR = GregTechAPI.registerMetaTileEntity(4231, new TileEntityLargeReplicator(location("large_replicator"), RecipeMaps.REPLICATOR_RECIPES));
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

    public static ResourceLocation location(String name) {
        return new ResourceLocation(Gregicality.MODID, name);
    }
}
