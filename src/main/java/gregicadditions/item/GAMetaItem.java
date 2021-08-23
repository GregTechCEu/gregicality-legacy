package gregicadditions.item;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.behaviors.*;
import gregicadditions.item.behaviors.monitorPlugin.AdvancedMonitorPluginBehavior;
import gregicadditions.item.behaviors.monitorPlugin.FakeGuiPluginBehavior;
import gregicadditions.item.behaviors.monitorPlugin.OnlinePicPluginBehavior;
import gregicadditions.item.behaviors.monitorPlugin.TextPluginBehavior;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

import static gregicadditions.item.GAMetaItems.*;

public class GAMetaItem extends StandardMetaItem {

    @Override
    public void registerSubItems() {

        ////////////////////////////////////
        //      Components: IDs 1-49      //
        ////////////////////////////////////

        ELECTRIC_MOTOR_UHV = addItem(1, "electric.motor.uhv");
        ELECTRIC_MOTOR_UEV = addItem(2, "electric.motor.uev");
        ELECTRIC_MOTOR_UIV = addItem(3, "electric.motor.uiv");
        ELECTRIC_MOTOR_UMV = addItem(4, "electric.motor.umv");
        ELECTRIC_MOTOR_UXV = addItem(5, "electric.motor.uxv");
        ELECTRIC_MOTOR_MAX = addItem(6, "electric.motor.max");

        ELECTRIC_PUMP_UHV = addItem(7, "electric.pump.uhv");
        ELECTRIC_PUMP_UEV = addItem(8, "electric.pump.uev");
        ELECTRIC_PUMP_UIV = addItem(9, "electric.pump.uiv");
        ELECTRIC_PUMP_UMV = addItem(10, "electric.pump.umv");
        ELECTRIC_PUMP_UXV = addItem(11, "electric.pump.uxv");
        ELECTRIC_PUMP_MAX = addItem(12, "electric.pump.max");

        CONVEYOR_MODULE_UHV = addItem(13, "conveyor.module.uhv");
        CONVEYOR_MODULE_UEV = addItem(14, "conveyor.module.uev");
        CONVEYOR_MODULE_UIV = addItem(15, "conveyor.module.uiv");
        CONVEYOR_MODULE_UMV = addItem(16, "conveyor.module.umv");
        CONVEYOR_MODULE_UXV = addItem(17, "conveyor.module.uxv");
        CONVEYOR_MODULE_MAX = addItem(18, "conveyor.module.max");

        ELECTRIC_PISTON_UHV = addItem(19, "electric.piston.uhv");
        ELECTRIC_PISTON_UEV = addItem(20, "electric.piston.uev");
        ELECTRIC_PISTON_UIV = addItem(21, "electric.piston.uiv");
        ELECTRIC_PISTON_UMV = addItem(22, "electric.piston.umv");
        ELECTRIC_PISTON_UXV = addItem(23, "electric.piston.uxv");
        ELECTRIC_PISTON_MAX = addItem(24, "electric.piston.max");

        ROBOT_ARM_UHV = addItem(25, "robot.arm.uhv");
        ROBOT_ARM_UEV = addItem(26, "robot.arm.uev");
        ROBOT_ARM_UIV = addItem(27, "robot.arm.uiv");
        ROBOT_ARM_UMV = addItem(28, "robot.arm.umv");
        ROBOT_ARM_UXV = addItem(29, "robot.arm.uxv");
        ROBOT_ARM_MAX = addItem(30, "robot.arm.max");

        FIELD_GENERATOR_UHV = addItem(31, "field.generator.uhv");
        FIELD_GENERATOR_UEV = addItem(32, "field.generator.uev");
        FIELD_GENERATOR_UIV = addItem(33, "field.generator.uiv");
        FIELD_GENERATOR_UMV = addItem(34, "field.generator.umv");
        FIELD_GENERATOR_UXV = addItem(35, "field.generator.uxv");
        FIELD_GENERATOR_MAX = addItem(36, "field.generator.max");

        EMITTER_UHV = addItem(37, "emitter.uhv");
        EMITTER_UEV = addItem(38, "emitter.uev");
        EMITTER_UIV = addItem(39, "emitter.uiv");
        EMITTER_UMV = addItem(40, "emitter.umv");
        EMITTER_UXV = addItem(41, "emitter.uxv");
        EMITTER_MAX = addItem(42, "emitter.max");

        SENSOR_UHV = addItem(43, "sensor.uhv");
        SENSOR_UEV = addItem(44, "sensor.uev");
        SENSOR_UIV = addItem(45, "sensor.uiv");
        SENSOR_UMV = addItem(46, "sensor.umv");
        SENSOR_UXV = addItem(47, "sensor.uxv");
        SENSOR_MAX = addItem(48, "sensor.max");

        /////////////////////////////////////
        //       Circuits: IDs 50-99       //
        /////////////////////////////////////

        // T8: Bioware
        BIOWARE_PROCESSOR = addItem(50, "circuit.bioware_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
        BIOWARE_ASSEMBLY = addItem(51, "circuit.bioware_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
        BIOWARE_COMPUTER = addItem(52, "circuit.bioware_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        BIOWARE_MAINFRAME = addItem(53, "circuit.bioware_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultra);

        // T9: Optical
        OPTICAL_PROCESSOR = addItem(54,"circuit.optical_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
        OPTICAL_ASSEMBLY = addItem(55,"circuit.optical_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        OPTICAL_COMPUTER = addItem(56,"circuit.optical_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultra);
        OPTICAL_MAINFRAME = addItem(57,"circuit.optical_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Insane);

        // T10: Exotic
        EXOTIC_PROCESSOR = addItem(58, "circuit.exotic_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        EXOTIC_ASSEMBLY = addItem(59, "circuit.exotic_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultra);
        EXOTIC_COMPUTER = addItem(60, "circuit.exotic_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Insane);
        EXOTIC_MAINFRAME = addItem(61, "circuit.exotic_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UMVCircuit);

        // T11: Cosmic
        COSMIC_PROCESSOR = addItem(62, "circuit.cosmic_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultra);
        COSMIC_ASSEMBLY = addItem(63, "circuit.cosmic_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Insane);
        COSMIC_COMPUTER = addItem(64, "circuit.cosmic_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UMVCircuit);
        COSMIC_MAINFRAME = addItem(65, "circuit.cosmic_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXVCircuit);

        // T12: Supracausal
        SUPRACAUSAL_PROCESSOR = addItem(66, "circuit.supracausal_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Insane);
        SUPRACAUSAL_ASSEMBLY = addItem(67, "circuit.supracausal_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UMVCircuit);
        SUPRACAUSAL_COMPUTER = addItem(68, "circuit.supracausal_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXVCircuit);
        SUPRACAUSAL_MAINFRAME = addItem(69, "circuit.supracausal_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Maximum);

        /////////////////////////////////////
        // Circuit Components: IDs 100-999 //
        /////////////////////////////////////

        // SMDs: IDs 100-199
        SMD_TRANSISTOR_BIOWARE = addItem(100, "component.smd.transistor.bioware");
        SMD_RESISTOR_BIOWARE = addItem(101, "component.smd.resistor.bioware");
        SMD_CAPACITOR_BIOWARE = addItem(102, "component.smd.capacitor.bioware");
        SMD_DIODE_BIOWARE = addItem(103, "component.smd.diode.bioware");

        SMD_TRANSISTOR_OPTICAL = addItem(104,"component.smd.transistor.optical");
        SMD_RESISTOR_OPTICAL = addItem(105,"component.smd.resistor.optical");
        SMD_CAPACITOR_OPTICAL = addItem(106,"component.smd.capacitor.optical");
        SMD_DIODE_OPTICAL = addItem(107,"component.smd.diode.optical");

        SMD_TRANSISTOR_EXOTIC = addItem(108, "component.smd.transistor.exotic");
        SMD_RESISTOR_EXOTIC = addItem(109, "component.smd.resistor.exotic");
        SMD_CAPACITOR_EXOTIC = addItem(110, "component.smd.capacitor.exotic");
        SMD_DIODE_EXOTIC = addItem(111, "component.smd.diode.exotic");

        SMD_TRANSISTOR_COSMIC = addItem(112,"component.smd.transistor.cosmic");
        SMD_RESISTOR_COSMIC = addItem(113,"component.smd.resistor.cosmic");
        SMD_CAPACITOR_COSMIC = addItem(114,"component.smd.capacitor.cosmic");
        SMD_DIODE_COSMIC = addItem(115,"component.smd.diode.cosmic");

        SMD_TRANSISTOR_SUPRACAUSAL = addItem(116, "component.smd.transistor.supracausal");
        SMD_RESISTOR_SUPRACAUSAL = addItem(117, "component.smd.resistor.supracausal");
        SMD_CAPACITOR_SUPRACAUSAL = addItem(118, "component.smd.capacitor.supracausal");
        SMD_DIODE_SUPRACAUSAL = addItem(119, "component.smd.diode.supracausal");

        // Wafers and Boules: IDs 200-299
        BOULE_DUBNIUM = addItem(200, "boule.dubnium");
        BOULE_RUTHERFORDIUM = addItem(201, "boule.rutherfordium");
        HASSIUM_BOULE = addItem(202,"boule.hassium");
        NDYAG_BOULE = addItem(203,"boule.ndyag");
        PRHOYLF_BOULE = addItem(204,"boule.prhoylf");
        LUTMYVO_BOULE = addItem(205,"boule.lutmyvo");
        PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE = addItem(206,"boule.lithium_niobate");

        WAFER_DUBNIUM = addItem(220, "wafer.dubnium");
        WAFER_RUTHERFORDIUM = addItem(221, "wafer.rutherfordium");
        HASSIUM_WAFER = addItem(222,"wafer.hassium");
        COATED_HASSIUM_WAFER = addItem(223, "wafer.coated_hassium");
        PHOTOCOATED_HASSIUM_WAFER = addItem(224,"wafer.photocoated_hassium");
        NDYAG_ROD = addItem(225,"ndyag_rod");
        PRHOYLF_ROD = addItem(226,"prhoylf_rod");
        LUTMYVO_ROD = addItem(227,"lutmyvo_rod");
        ARAM_WAFER = addItem(228, "wafer.aram");
        HASOC_WAFER = addItem(229, "wafer.hasoc");
        UHASOC_WAFER = addItem(230, "wafer.uhasoc");
        UHPIC_WAFER = addItem(231, "wafer.uhpic");
        OPTICAL_SOC_WAFER = addItem(232,"wafer.optical_soc");
        UNTREATED_EXOTIC_WAFER = addItem(233, "wafer.untreated.exotic");
        EXOTIC_WAFER = addItem(234, "wafer.exotic");

        ARAM = addItem(260, "plate.aram");
        HASOC = addItem(261, "plate.hasoc");
        UHASOC = addItem(262, "plate.uhasoc");
        UHPIC = addItem(263, "plate.uhpic");
        OPTICAL_SOC = addItem(264,"plate.optical_soc");
        EXOTIC_CHIP = addItem(265, "plate.exotic_chip");

        // Circuit Boards: IDs 300-324
        KAPTON_BOARD = addItem(300, "board.kapton");
        KAPTON_CIRCUIT_BOARD = addItem(301, "circuit_board.kapton");

        // Circuit Cores: IDs 325-349
        NEURO_SUPPORT_UNIT = addItem(325, "neuro.support.unit");
        CYBER_PROCESSING_UNIT = addItem(326, "cyber.processing.unit");
        OPTICAL_PROCESSING_CORE = addItem(327,"optical.processing.core");
        EXOTIC_PROCESSING_CORE = addItem(328, "exotic.processing.core");
        COSMIC_PROCESSING_UNIT_CORE = addItem(329,"cosmic.processing.unit.core");
        COSMIC_PROCESSING_CORE = addItem(330,"cosmic.processing.core");
        SUPRACAUSAL_PROCESSING_CORE = addItem(331, "supracausal.processing.core");

        // Wetware/Bioware Process Items: IDs 350-399
        ULTRASONIC_HOMOGENIZER = addItem(350, "ultrasonic.homogenizer");
        STERILIZED_PETRI_DISH = addItem(351, "sterilized.petri.dish");
        CONTAMINATED_PETRI_DISH = addItem(352, "contaminated.petri.dish");
        ELECTRICALLY_WIRED_PETRI_DISH = addItem(353, "electrically.wired.petri.dish");
        CLEAN_CULTURE = addItem(354, "clean.culture");
        PIEZOELECTRIC_CRYSTAL = addItem(355, "piezoelectric.crystal");

        SHEWANELLA_CULTURE = addItem(356, "shewanella.culture");
        STREPTOCOCCUS_CULTURE = addItem(357, "streptococcus.culture");
        ESCHERICHIA_CULTURE = addItem(358, "eschericia.culture");
        BIFIDOBACTERIUM_CULTURE = addItem(359, "bifidobacterium.culture");
        BREVIBACTERIUM_CULTURE = addItem(360, "brevibacterium.culture");
        CUPRIVADUS_CULTURE = addItem(361, "cupriavidus.culture");

        // Optical Process Items: IDs 400-449
        CLADDED_OPTICAL_FIBER_CORE = addItem(405, "cladded_optical_fiber_core");

        LOW_FREQUENCY_LASER = addItem(406,"low_frequency_laser");
        MEDIUM_FREQUENCY_LASER = addItem(407,"medium_frequency_laser");
        HIGH_FREQUENCY_LASER = addItem(408,"high_frequency_laser");
        HIGHLY_REFLECTIVE_MIRROR = addItem(409,"highly_reflective_mirror");
        NON_LINEAR_OPTICAL_LENS = addItem(410,"non_linear_optical_lens");
        ROTATING_TRANSPARENT_SURFACE = addItem(411,"rotating_transparent_surface");
        ELECTRON_SOURCE = addItem(412,"electron_source");

        // Exotic Process Items: IDs 450-499
        FULLERENE_POLYMER_MATRIX_SOFT_TUBING = addItem(450, "fullerene.polymer.matrix.soft.tubing");
        FULLERENE_POLYMER_MATRIX_FINE_TUBING = addItem(451, "fullerene.polymer.matrix.fine.tubing");
        X_RAY_WAVEGUIDE = addItem(452, "xray.waveguide");
        MICROFOCUS_X_RAY_TUBE = addItem(453, "microfocus.xray.tube");
        X_RAY_MIRROR_PLATE = addItem(454, "xray.mirror.plate");
        EXCITATION_MAINTAINER = addItem(455, "excitation.maintainer");
        CRYOGENIC_INTERFACE = addItem(456, "cryogenic.interface");
        RYDBERG_SPINORIAL_ASSEMBLY = addItem(457, "rydberg.spinorial.assembly");
        X_RAY_LASER = addItem(458, "xray.laser");

        // Cosmic Process Items: IDs 500-549
        AEROGRAPHENE = addItem(500,"aerographene");
        SCINTILLATOR_CRYSTAL = addItem(501,"scintillator_crystal");
        SCINTILLATOR = addItem(502,"scintillator");
        LEPTON_TRAP_CRYSTAL =  addItem(503,"lepton_trap_crystal");
        HASSIUM_SEED_CRYSTAL = addItem(504, "hassium_seed_crystal");
        GRATING_LITHOGRAPHY_MASK = addItem(505, "grating_lithography_mask");
        DIFFRACTOR_GRATING_MIRROR = addItem(506,"diffractor_grating_mirror");
        ULTRASHORT_PULSE_LASER = addItem(507,"ultrashort_pulse_laser");

        // Supracausal Process Items: IDs 550-649
        NEUTRONIUM_SPHERE = addItem(550, "neutronium.sphere");
        TRIPLET_NEUTRONIUM_SPHERE = addItem(551, "triplet.neutronium.sphere");
        CHARGED_TRIPLET_NEUTRONIUM_SPHERE = addItem(552, "charged.triplet.neutronium.sphere");
        CONTAINED_REISSNER_NORDSTROM_SINGULARITY = addItem(553, "contained.reissner.nordstrom.singularity");
        CONTAINED_KERR_NEWMANN_SINGULARITY = addItem(554, "contained.kerr.newmann.singularity");
        CONTAINED_KERR_SINGULARITY = addItem(555, "contained.kerr.singularity");
        CONTAINED_HIGH_DENSITY_PROTONIC_MATTER = addItem(556, "contained.high.density.protonic.matter");
        TIME_DILATION_CONTAINMENT_UNIT = addItem(557, "time.dilation.containment.unit");
        MICROWORMHOLE_GENERATOR = addItem(558, "microwormhole.generator");
        MACROWORMHOLE_GENERATOR = addItem(559, "macrowormhole.generator");
        STABILIZED_WORMHOLE_GENERATOR = addItem(560, "stabilized.wormhole.generator");
        CONTAINED_EXOTIC_MATTER = addItem(561, "contained.exotic.matter");
        SEPARATION_ELECTROMAGNET = addItem(562, "separation.electromagnet");
        SPHERE_FIELD_SHAPE = addItem(563, "sphere.field.shape");

        TOPOLOGICAL_MANIPULATOR_UNIT = addItem(564, "topological.manipulator.unit");
        RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM = addItem(565, "relativistic.spinorial.memory.system");
        GRAVITON_TRANSDUCER = addItem(566, "graviton.transducer");
        NUCLEAR_CLOCK = addItem(567, "nuclear.clock");
        MANIFOLD_OSCILLATORY_POWER_CELL = addItem(568, "manifold.oscillatory.power.cell");
        QCD_PROTECTIVE_PLATING = addItem(569, "qcd.protective.plating");
        CTC_GUIDANCE_UNIT = addItem(570, "ctc.guidance.unit");
        CTC_COMPUTATIONAL_UNIT_CONTAINER = addItem(571, "ctc.computational.unit.container");
        EIGENFOLDED_KERR_MANIFOLD = addItem(572, "eigenfolded.kerr.manifold");
        CTC_COMPUTATIONAL_UNIT = addItem(573, "ctc.computational.unit");
        RECURSIVELY_FOLDED_NEGATIVE_SPACE = addItem(574, "recursively.folded.negative.space");

        // Misc Circuit Components: IDs  650-699
        UNSTABLE_STAR = addItem(650, "unstable.star");
        PLASMA_CONTAINMENT_CELL = addItem(651, "plasma.containment.cell");
        RHENIUM_PLASMA_CONTAINMENT_CELL = addItem(652, "rhenium.plasma.containment.cell");
        DEGENERATE_RHENIUM_PLATE = addItem(653, "degenerate.rhenium.plate");
        PLATE_FIELD_SHAPE = addItem(654, "plate.field.shape");
        LASER_DIODE = addItem(655,"laser_diode");
        LASER_COOLING_UNIT = addItem(656,"laser_cooling_unit");
        MAGNETIC_TRAP = addItem(657,"magnetic_trap");
        EMPTY_LASER_COOLING_CONTAINER = addItem(658,"empty_laser_cooling_container");
        BOSE_EINSTEIN_COOLING_CONTAINER = addItem(659,"bose_einstein_cooling_container");
        NEUTRON_PLASMA_CONTAINMENT_CELL = addItem(650, "neutron.plasma.containment.cell");
        INGOT_FIELD_SHAPE = addItem(651, "ingot.field.shape");

        ////////////////////////////////////
        //    Batteries: IDs 1000-1099    //
        ////////////////////////////////////

        // Hulls: IDs 1000-1049
        BATTERY_MEDIUM_LIS_EMPTY = addItem(1000, "medium.lithium.sulfide.battery.empty");
        BATTERY_LARGE_LIS_EMPTY = addItem(1001, "large.lithium.sulfide.battery.empty");

        BATTERY_SMALL_FLUORIDE_EMPTY = addItem(1002, "small.fluoride.battery.empty");
        BATTERY_MEDIUM_FLUORIDE_EMPTY = addItem(1003,"medium.fluoride.battery.empty");
        BATTERY_LARGE_FLUORIDE_EMPTY = addItem(1004,"large.fluoride.battery.empty");

        // Batteries: 1050-1099
        BATTERY_MEDIUM_LIS = addItem(1050, "medium.lithium.sulfide.battery").addComponents(ElectricStats.createRechargeableBattery(7372800000L, GTValues.UHV)).setModelAmount(8);
        BATTERY_LARGE_LIS = addItem(1051, "large.lithium.sulfide.battery").addComponents(ElectricStats.createRechargeableBattery(29491200000L, GTValues.UEV)).setModelAmount(8);

        BATTERY_SMALL_FLUORIDE = addItem(1052, "small.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(117964800000L, GTValues.UIV)).setModelAmount(8);
        BATTERY_MEDIUM_FLUORIDE = addItem(1053, "medium.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(471859200000L, GTValues.UMV)).setModelAmount(8);
        BATTERY_LARGE_FLUORIDE = addItem(1054, "large.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(1887436800000L, GTValues.UXV)).setModelAmount(8);

        ////////////////////////////////////
        // Misc MetaItems: IDs 1100-32767 //
        ////////////////////////////////////

        // Tools: IDs 1200-1299
        HUGE_TURBINE_ROTOR = addItem(1200, "huge_turbine_rotor").addComponents(new GATurbineRotorBehavior(200, 8.0));
        LARGE_TURBINE_ROTOR = addItem(1201, "large_turbine_rotor").addComponents(new GATurbineRotorBehavior(150, 16.0));
        MEDIUM_TURBINE_ROTOR = addItem(1202, "medium_turbine_rotor").addComponents(new GATurbineRotorBehavior(100, 24.0));
        SMALL_TURBINE_ROTOR = addItem(1203, "small_turbine_rotor").addComponents(new GATurbineRotorBehavior(50, 32.0));

        // Misc
        PYROLYTIC_CARBON = addItem(1300, "pyrolytic_carbon");
        COMPRESSED_COKE_CLAY = addItem(1301, "compressed.coke.clay"); // todo
        GRAPHENE_IRON_PLATE = addItem(1302,"graphene_iron_plate");
        MEMORY_FOAM_BLOCK = addItem(1303,"memory_foam_block");

        // Brine
        RAPIDLY_ROTATING_CRUCIBLE = addItem(1400, "rapidly_rotating_crucible");
        HEAVY_METAL_ABSORBING_YARN = addItem(1401, "heavy_metal_absorbing_yarn");
        URANIUM_SATURATED_YARN = addItem(1402, "uranium_saturated_yarn");
        BORON_RETAINING_YARN = addItem(1403, "boron_retaining_yarn");
        BORON_SATURATED_YARN = addItem(1404, "boron_saturated_yarn");
        LITHIUM_SIEVE = addItem(1405, "lithium_sieve");
        LITHIUM_SATURATED_LITHIUM_SIEVE = addItem(1406, "lithium_saturated_lithium_sieve");
        ACRYLIC_YARN = addItem(1408, "acrylic_yarn");

        // Nanotubes
        NANOTOME = addItem(1450, "nanotome");

        // SuperconductorSMD
        ALUMINO_SILICATE_GLASS_TUBE = addItem(1500,"alumino_silicate_glass_tube");
        INDUCTOR = addItem(1501,"inductor");
        BALLAST = addItem(1502,"ballast");
        UVA_HALIDE_LAMP = addItem(1503,"uva_halide_lamp");
        WHITE_HALIDE_LAMP = addItem(1504,"white_halide_lamp");
        RED_HALIDE_LAMP = addItem(1505,"red_halide_lamp");
        BLUE_HALIDE_LAMP = addItem(1506,"blue_halide_lamp");
        GREEN_HALIDE_LAMP = addItem(1507,"green_halide_lamp");
        UVA_LAMP_CORE = addItem(1508,"uva_lamp_core");
        WHITE_LAMP_CORE = addItem(1509,"white_lamp_core");
        RED_LAMP_CORE = addItem(1510,"red_lamp_core");
        BLUE_LAMP_CORE = addItem(1511,"blue_lamp_core");
        GREEN_LAMP_CORE = addItem(1512,"green_lamp_core");
        ACTINIUM_PLASMA_CONTAINMENT_CELL = addItem(1513, "actinium.plasma.containment.cell");

        // Insulation Wire Assembly
        PEEK_POLYAMIDE_FOIL = addItem(1600,"peek_polyamide_foil");
        HIGHLY_INSULATING_FOIL = addItem(1601,"highly_insulating_foil");
        INSULATION_WIRE_ASSEMBLY = addItem(1602,"insulation_wire_assembly");

        // Cosmic Neutronium
        WIRE_FIELD_SHAPE = addItem(1700,"wire.field.shape");
        EXTREMELY_DURABLE_PLASMA_CELL = addItem(1701,"extremely.durable.plasma.cell");
        DENSE_NEUTRON_PLASMA_CELL = addItem(1702,"dense.neutron.plasma.cell");
        COSMIC_NEUTRON_PLASMA_CELL = addItem(1703,"cosmic.neutron.plasma.cell");
        HIGHLY_DENSE_POLYMER_PLATE = addItem(1704,"highly.dense.polymer.plate");
        COSMIC_MESH_CONTAINMENT_UNIT = addItem(1705,"cosmic.mesh.containment.unit");
        COSMIC_MESH = addItem(1706,"cosmic.mesh");
        COSMIC_FABRIC = addItem(1707,"cosmic.fabric");
        ROD_FIELD_SHAPE = addItem(1708, "rod.field.shape");

        // Batteries
        CHARGED_LEPTON_TRAP_CRYSTAL = addItem(1800,"charged.lepton.trap.crystal");
        NANOSILICON_CATHODE = addItem(1801,"nanosilicon.cathode");

        /////////////////////////////////////
        //       GT MetaItem Changes       //
        /////////////////////////////////////

        MetaItems.TURBINE_ROTOR.setInvisible();
        MetaItems.TOOL_DATA_STICK.addComponents(new DataStickFluidSamplerBehavior());

        ////////////////////////////////////
        //              TODO              //
        ////////////////////////////////////

        // TODO Move to CEu
        COLOURED_LEDS = addItem(1400, "coloured.leds");
        DISPLAY = addItem(1401, "display");
        PLUGIN_ADVANCED_MONITOR = addItem(1100, "plugin.advanced_monitor").addComponents(new AdvancedMonitorPluginBehavior());
        PLUGIN_FAKE_GUI = addItem(1101, "plugin.fake_gui").addComponents(new FakeGuiPluginBehavior());
        PLUGIN_ONLINE_PIC = addItem(1102, "plugin.online_pic").addComponents(new OnlinePicPluginBehavior());
        PLUGIN_TEXT = addItem(1103, "plugin.text").addComponents(new TextPluginBehavior());
        COVER_DIGITAL_INTERFACE = addItem(1104, "cover.digital");
        FREEDOM_WRENCH = addItem(1210, "freedom_wrench").setMaxStackSize(1).addComponents(new FreedomWrenchBehaviour());

        // TODO Move to Gregification
        HYPERIUM_ESSENCE = addItem(344, "hyperium_essence");
        LUDICIUM_ESSENCE = addItem(345, "ludicium_essence");
        HYPERIUM_SEED = addItem(346, "hyperium_crafting_seed");
        LUDICIUM_SEED = addItem(347, "ludicium_crafting_seed");

        // TODO Nuclear Rework
        /*
        PROTACTINIUM_WASTE = addItem(311, "waste.nuclear").addComponents(new WasteBehavior(Protactinium.getMaterial()));
        NUCLEAR_WASTE = addItem(312, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.global", 0xDEDEDE));
        THORIUM_WASTE = addItem(313, "waste.nuclear").addComponents(new WasteBehavior(Thorium));
        URANIUM_WASTE = addItem(314, "waste.nuclear").addComponents(new WasteBehavior(UraniumRadioactive.getMaterial()));
        NEPTUNIUM_WASTE = addItem(315, "waste.nuclear").addComponents(new WasteBehavior(Neptunium.getMaterial()));
        PLUTONIUM_WASTE = addItem(316, "waste.nuclear").addComponents(new WasteBehavior(PlutoniumRadioactive.getMaterial()));
        AMERICIUM_WASTE = addItem(317, "waste.nuclear").addComponents(new WasteBehavior(Americium));
        CURIUM_WASTE = addItem(318, "waste.nuclear").addComponents(new WasteBehavior(Curium.getMaterial()));
        BERKELIUM_WASTE = addItem(319, "waste.nuclear").addComponents(new WasteBehavior(Berkelium.getMaterial()));
        CALIFORNIUM_WASTE = addItem(320, "waste.nuclear").addComponents(new WasteBehavior(Californium.getMaterial()));
        EINSTEINIUM_WASTE = addItem(321, "waste.nuclear").addComponents(new WasteBehavior(Einsteinium.getMaterial()));
        FERMIUM_WASTE = addItem(322, "waste.nuclear").addComponents(new WasteBehavior(Fermium.getMaterial()));
        MENDELEVIUM_WASTE = addItem(323, "waste.nuclear").addComponents(new WasteBehavior(Mendelevium.getMaterial()));
        NUCLEAR_WASTE_LANTHANIDE_A = addItem(324, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.lanthanide.a", 0xC9CBCF));
        NUCLEAR_WASTE_LANTHANIDE_B = addItem(325, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.lanthanide.b", 0xA9A8AA));
        NUCLEAR_WASTE_ALKALINE = addItem(326, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.alkaline", 0xDEDEDE));
        NUCLEAR_WASTE_METAL_A = addItem(327, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.a", 0x48484D));
        NUCLEAR_WASTE_METAL_B = addItem(328, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.b", 0x626065));
        NUCLEAR_WASTE_METAL_C = addItem(329, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.c", 0x828485));
        NUCLEAR_WASTE_HEAVY_METAL = addItem(330, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.heavy_metal", 0x738198));
        NUCLEAR_WASTE_METALOID = addItem(331, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metaloid", 0xD16D4F));
        NUCLEAR_WASTE_REACTIVE_NONMETAL = addItem(332, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.nonmetal", 0xD1CB4F));

        ThoriumRadioactive.waste = THORIUM_WASTE;
        Protactinium.waste = PROTACTINIUM_WASTE;
        UraniumRadioactive.waste = URANIUM_WASTE;
        Neptunium.waste = NEPTUNIUM_WASTE;
        PlutoniumRadioactive.waste = PLUTONIUM_WASTE;
        AmericiumRadioactive.waste = AMERICIUM_WASTE;
        Curium.waste = CURIUM_WASTE;
        Berkelium.waste = BERKELIUM_WASTE;
        Californium.waste = CALIFORNIUM_WASTE;
        Einsteinium.waste = EINSTEINIUM_WASTE;
        Fermium.waste = FERMIUM_WASTE;
        Mendelevium.waste = MENDELEVIUM_WASTE;
        */
    }

    @Override
    @Nonnull
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }

    @Override
    public boolean hasEffect(@Nonnull ItemStack itemStack) {
        return super.hasEffect(itemStack) || itemStack.getMetadata() == UNSTABLE_STAR.getStackForm().getMetadata();
    }
}
