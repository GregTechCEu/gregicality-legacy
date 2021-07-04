package gregicadditions.item;

import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregicadditions.item.behaviors.*;
import gregicadditions.item.behaviors.monitorPlugin.TextPluginBehavior;
import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.FluidStats;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.unification.material.Materials.Americium;
import static gregtech.api.unification.material.Materials.Thorium;


public class GAMetaItem extends MaterialMetaItem {
    public GAMetaItem() {
        super(GAConfig.GT6.addCurvedPlates ? GAEnums.GAOrePrefix.plateCurved : null,
                GAConfig.GT6.addDoubleIngots ? GAEnums.GAOrePrefix.ingotDouble : null,
                GAConfig.GT6.addRounds ? GAEnums.GAOrePrefix.round : null,
                GAEnums.GAOrePrefix.dioxide, GAEnums.GAOrePrefix.nitride, GAEnums.GAOrePrefix.hexafluoride,
                GAEnums.GAOrePrefix.carbide, GAEnums.GAOrePrefix.nitrite, GAEnums.GAOrePrefix.oxide,
                GAEnums.GAOrePrefix.depletedFuel, GAEnums.GAOrePrefix.depletedFuelNitride, GAEnums.GAOrePrefix.depletedFuelOxide,
                GAEnums.GAOrePrefix.depletedFuelTRISO, GAEnums.GAOrePrefix.depletedFuelZirconiumAlloy, GAEnums.GAOrePrefix.fuelCarbide,
                GAEnums.GAOrePrefix.fuelNitride, GAEnums.GAOrePrefix.fuelOxide, GAEnums.GAOrePrefix.fuelPure, GAEnums.GAOrePrefix.fuelTRISO,
                GAEnums.GAOrePrefix.fuelZirconiumAlloy, GAEnums.GAOrePrefix.zirconiumAlloy, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public void registerSubItems() {
        GLASS_FIBER = addItem(21, "component.glass.fiber");
        PETRI_DISH = addItem(23, "component.petri.dish");

        if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
            ELECTRODE_APATITE = addItem(108, "electrode.apatite");
            ELECTRODE_BLAZE = addItem(109, "electrode.blaze");
            ELECTRODE_BRONZE = addItem(110, "electrode.bronze");
            ELECTRODE_COPPER = addItem(111, "electrode.copper");
            ELECTRODE_DIAMOND = addItem(112, "electrode.diamond");
            ELECTRODE_EMERALD = addItem(113, "electrode.emerald");
            ELECTRODE_ENDER = addItem(114, "electrode.ender");
            ELECTRODE_GOLD = addItem(115, "electrode.gold");
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore"))
                ELECTRODE_IRON = addItem(116, "electrode.iron");
            ELECTRODE_LAPIS = addItem(117, "electrode.lapis");
            ELECTRODE_OBSIDIAN = addItem(118, "electrode.obsidian");
            if (Loader.isModLoaded("extrautils2")) ELECTRODE_ORCHID = addItem(119, "electrode.orchid");
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn") || Loader.isModLoaded("binniecore"))
                ELECTRODE_RUBBER = addItem(120, "electrode.rubber");
            ELECTRODE_TIN = addItem(121, "electrode.tin");
        }

        if (GAConfig.GT5U.enableZPMandUVBats) {
            ENERGY_MODULE = addItem(122, "energy.module").addComponents(new IItemComponent[]{ElectricStats.createRechargeableBattery(10000000000L, 7)}).setModelAmount(8);
            ENERGY_CLUSTER = addItem(123, "energy.cluster").addComponents(new IItemComponent[]{ElectricStats.createRechargeableBattery(100000000000L, 8)}).setModelAmount(8);
        }

        if (GAConfig.GT5U.replaceUVwithMAXBat) {
            MAX_BATTERY = addItem(124, "max.battery").addComponents(new IItemComponent[]{ElectricStats.createRechargeableBattery(9223372036854775807L, 9)}).setModelAmount(8);
            MetaItems.ZPM2.setInvisible();
        }

        PLUGIN_TEXT = addItem(129, "plugin.text").addComponents(new TextPluginBehavior());
        COVER_DIGITAL_INTERFACE = addItem(130, "cover.digital");

        SCHEMATIC = addItem(131, "schematic").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
        SCHEMATIC_2X2 = addItem(132, "schematic.2by2").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
        SCHEMATIC_3X3 = addItem(133, "schematic.3by3").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
        SCHEMATIC_DUST = addItem(134, "schematic.dust").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));

        PRIMITIVE_ASSEMBLY = addItem(200, "circuit.assembly.primitive").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        ELECTRONIC_ASSEMBLY = addItem(201, "circuit.assembly.electronic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        ELECTRONIC_COMPUTER = addItem(202, "circuit.computer.electronic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
        REFINED_PROCESSOR = addItem(203, "circuit.processor.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Basic);
        REFINED_ASSEMBLY = addItem(204, "circuit.assembly.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        REFINED_COMPUTER = addItem(205, "circuit.computer.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
        REFINED_MAINFRAME = addItem(206, "circuit.mainframe.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Extreme);
        MICRO_PROCESSOR = addItem(207, "circuit.processor.micro").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        MICRO_COMPUTER = addItem(209, "circuit.computer.micro").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Extreme);
        MICRO_MAINFRAME = addItem(208, "circuit.mainframe.micro").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
        NANO_COMPUTER = addItem(211, "circuit.computer.nano").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
        NANO_MAINFRAME = addItem(212, "circuit.mainframe.nano").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Master);
        QUANTUM_ASSEMBLY = addItem(216, "circuit.assembly.quantum").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
        QUANTUM_COMPUTER = addItem(217, "circuit.computer.quantum").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Master);
        QUANTUM_MAINFRAME = addItem(218, "circuit.mainframe.quantum").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
        CRYSTAL_PROCESSOR = addItem(213, "circuit.processor.crystal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
        CRYSTAL_COMPUTER = addItem(214, "circuit.computer.crystal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
        CRYSTAL_MAINFRAME = addItem(215, "circuit.mainframe.crystal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);

        CIRCUIT_MAGNETIC_ULV = addItem(220, "circuit.resonatic.ulv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Primitive);
        CIRCUIT_MAGNETIC_LV = addItem(221, "circuit.resonatic.lv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Basic);
        CIRCUIT_MAGNETIC_MV = addItem(222, "circuit.resonatic.mv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        CIRCUIT_MAGNETIC_HV = addItem(223, "circuit.resonatic.hv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
        CIRCUIT_MAGNETIC_EV = addItem(224, "circuit.resonatic.ev").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Extreme);
        CIRCUIT_MAGNETIC_IV = addItem(225, "circuit.resonatic.iv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
        CIRCUIT_MAGNETIC_LUV = addItem(226, "circuit.resonatic.luv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Master);
        CIRCUIT_MAGNETIC_ZPM = addItem(227, "circuit.resonatic.zpm").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
        CIRCUIT_MAGNETIC_UV = addItem(228, "circuit.resonatic.uv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
        CIRCUIT_MAGNETIC_UHV = addItem(229, "circuit.resonatic.uhv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        CIRCUIT_MAGNETIC_UEV = addItem(232, "circuit.resonatic.uev").setUnificationData(OrePrefix.circuit, UEV);
        RAW_IMPRINT_SUPPORTED_BOARD = addItem(230, "board.raw.magnetic");
        IMPRINT_SUPPORTED_BOARD = addItem(231, "board.magnetic");


        SMD_TRANSISTOR_REFINED = addItem(240, "component.smd.transistor.refined");
        SMD_RESISTOR_REFINED = addItem(241, "component.smd.resistor.refined");
        SMD_CAPACITOR_REFINED = addItem(242, "component.smd.capacitor.refined");
        SMD_DIODE_REFINED = addItem(243, "component.smd.diode.refined");

        SMD_TRANSISTOR_NANO = addItem(244, "component.smd.transistor.nano");
        SMD_RESISTOR_NANO = addItem(245, "component.smd.resistor.nano");
        SMD_CAPACITOR_NANO = addItem(246, "component.smd.capacitor.nano");
        SMD_DIODE_NANO = addItem(247, "component.smd.diode.nano");

        SMD_TRANSISTOR_CRYSTAL = addItem(248, "component.smd.transistor.crystal");
        SMD_CAPACITOR_CRYSTAL = addItem(249, "component.smd.capacitor.crystal");
        SMD_RESISTOR_CRYSTAL = addItem(250, "component.smd.resistor.crystal");
        SMD_DIODE_CRYSTAL = addItem(251, "component.smd.diode.crystal");

        SMD_TRANSISTOR_QUANTUM = addItem(252, "component.smd.transistor.quantum");
        SMD_CAPACITOR_QUANTUM = addItem(253, "component.smd.capacitor.quantum");
        SMD_RESISTOR_QUANTUM = addItem(254, "component.smd.resistor.quantum");
        SMD_DIODE_QUANTUM = addItem(255, "component.smd.diode.quantum");

        SMD_TRANSISTOR_WETWARE = addItem(256, "component.smd.transistor.wetware");
        SMD_CAPACITOR_WETWARE = addItem(257, "component.smd.capacitor.wetware");
        SMD_RESISTOR_WETWARE = addItem(258, "component.smd.resistor.wetware");
        SMD_DIODE_WETWARE = addItem(259, "component.smd.diode.wetware");


        HUGE_TURBINE_ROTOR = addItem(300, "huge_turbine_rotor").addComponents(new HugeTurbineBehavior());
        LARGE_TURBINE_ROTOR = addItem(301, "large_turbine_rotor").addComponents(new LargeTurbineBehavior());
        MEDIUM_TURBINE_ROTOR = addItem(302, "medium_turbine_rotor").addComponents(new MediumTurbineBehavior());
        SMALL_TURBINE_ROTOR = addItem(303, "small_turbine_rotor").addComponents(new SmallTurbineBehavior());

        IMPELLER_MV = addItem(304, "impeller.mv");
        IMPELLER_HV = addItem(305, "impeller.hv");
        GRAVITATION_ENGINE = addItem(306, "gravitation_engine");
        INSULATING_TAPE = addItem(307, "insulating_tape");

        HAND_PUMP = addItem(308, "hand_pump")
                .setMaxStackSize(1)
                .addComponents(new FluidStats(64000, Integer.MIN_VALUE, Integer.MAX_VALUE, true))
                .addComponents(new HandPumpBehavior());

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


        WAFER_DUBNIUM = addItem(333, "wafer.dubnium");
        WAFER_RUTHERFORDIUM = addItem(334, "wafer.rutherfordium");
        WAFER_NEUTRONIUM = addItem(335, "wafer.neutronium");

        COSMIC_PROCESSOR = addItem(336, "circuit.processor.cosmic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        COSMIC_ASSEMBLY = addItem(337, "circuit.assembly.cosmic").setUnificationData(OrePrefix.circuit, UEV);
        COSMIC_COMPUTER = addItem(338, "circuit.computer.cosmic").setUnificationData(OrePrefix.circuit, UIV);
        COSMIC_MAINFRAME = addItem(339, "circuit.mainframe.cosmic").setUnificationData(OrePrefix.circuit, UMV);

        BOULE_DUBNIUM = addItem(340, "boule.dubnium");
        BOULE_RUTHERFORDIUM = addItem(341, "boule.rutherfordium");
        BOULE_NEUTRONIUM = addItem(342, "boule.neutronium");

        UNSTABLE_STAR = addItem(343, "unstable.star");

        HYPERIUM_ESSENCE = addItem(344, "hyperium_essence");
        LUDICIUM_ESSENCE = addItem(345, "ludicium_essence");

        HYPERIUM_SEED = addItem(346, "hyperium_crafting_seed");
        LUDICIUM_SEED = addItem(347, "ludicium_crafting_seed");

        CONVEYOR_MODULE_UHV = addItem(348, "conveyor.module.uhv");
        CONVEYOR_MODULE_UEV = addItem(349, "conveyor.module.uev");
        CONVEYOR_MODULE_UIV = addItem(350, "conveyor.module.uiv");
        CONVEYOR_MODULE_UMV = addItem(351, "conveyor.module.umv");
        CONVEYOR_MODULE_UXV = addItem(352, "conveyor.module.uxv");
        CONVEYOR_MODULE_MAX = addItem(353, "conveyor.module.max");

        ELECTRIC_MOTOR_UHV = addItem(354, "electric.motor.uhv");
        ELECTRIC_MOTOR_UEV = addItem(355, "electric.motor.uev");
        ELECTRIC_MOTOR_UIV = addItem(356, "electric.motor.uiv");
        ELECTRIC_MOTOR_UMV = addItem(357, "electric.motor.umv");
        ELECTRIC_MOTOR_UXV = addItem(358, "electric.motor.uxv");
        ELECTRIC_MOTOR_MAX = addItem(359, "electric.motor.max");

        ELECTRIC_PISTON_UHV = addItem(360, "electric.piston.uhv");
        ELECTRIC_PISTON_UEV = addItem(361, "electric.piston.uev");
        ELECTRIC_PISTON_UIV = addItem(362, "electric.piston.uiv");
        ELECTRIC_PISTON_UMV = addItem(363, "electric.piston.umv");
        ELECTRIC_PISTON_UXV = addItem(364, "electric.piston.uxv");
        ELECTRIC_PISTON_MAX = addItem(365, "electric.piston.max");

        ELECTRIC_PUMP_UHV = addItem(366, "electric.pump.uhv");
        ELECTRIC_PUMP_UEV = addItem(367, "electric.pump.uev");
        ELECTRIC_PUMP_UIV = addItem(368, "electric.pump.uiv");
        ELECTRIC_PUMP_UMV = addItem(369, "electric.pump.umv");
        ELECTRIC_PUMP_UXV = addItem(370, "electric.pump.uxv");
        ELECTRIC_PUMP_MAX = addItem(371, "electric.pump.max");

        EMITTER_UHV = addItem(372, "emitter.uhv");
        EMITTER_UEV = addItem(373, "emitter.uev");
        EMITTER_UIV = addItem(374, "emitter.uiv");
        EMITTER_UMV = addItem(375, "emitter.umv");
        EMITTER_UXV = addItem(376, "emitter.uxv");
        EMITTER_MAX = addItem(377, "emitter.max");

        FIELD_GENERATOR_UHV = addItem(378, "field.generator.uhv");
        FIELD_GENERATOR_UEV = addItem(379, "field.generator.uev");
        FIELD_GENERATOR_UIV = addItem(380, "field.generator.uiv");
        FIELD_GENERATOR_UMV = addItem(381, "field.generator.umv");
        FIELD_GENERATOR_UXV = addItem(382, "field.generator.uxv");
        FIELD_GENERATOR_MAX = addItem(383, "field.generator.max");

        ROBOT_ARM_UHV = addItem(384, "robot.arm.uhv");
        ROBOT_ARM_UEV = addItem(385, "robot.arm.uev");
        ROBOT_ARM_UIV = addItem(386, "robot.arm.uiv");
        ROBOT_ARM_UMV = addItem(387, "robot.arm.umv");
        ROBOT_ARM_UXV = addItem(388, "robot.arm.uxv");
        ROBOT_ARM_MAX = addItem(389, "robot.arm.max");

        SENSOR_UHV = addItem(390, "sensor.uhv");
        SENSOR_UEV = addItem(391, "sensor.uev");
        SENSOR_UIV = addItem(392, "sensor.uiv");
        SENSOR_UMV = addItem(393, "sensor.umv");
        SENSOR_UXV = addItem(394, "sensor.uxv");
        SENSOR_MAX = addItem(395, "sensor.max");

        ULTRASONIC_HOMOGENIZER = addItem(396, "ultrasonic.homogenizer");
        STERILIZED_PETRI_DISH = addItem(397, "sterilized.petri.dish");
        CONTAMINATED_PETRI_DISH = addItem(398, "contaminated.petri.dish");
        CLEAN_CULTURE = addItem(399, "clean.culture");
        PIEZOELECTRIC_CRYSTAL = addItem(400, "piezoelectric.crystal");

        BIOWARE_PROCESSOR = addItem(401, "circuit.processor.bioware").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
        BIOWARE_ASSEMBLY = addItem(402, "circuit.assembly.bioware").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
        BIOWARE_COMPUTER = addItem(403, "circuit.computer.bioware").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        BIOWARE_MAINFRAME = addItem(404, "circuit.mainframe.bioware").setUnificationData(OrePrefix.circuit, UEV);

        SMD_TRANSISTOR_BIOWARE = addItem(405, "component.smd.transistor.bioware");
        SMD_CAPACITOR_BIOWARE = addItem(406, "component.smd.capacitor.bioware");
        SMD_RESISTOR_BIOWARE = addItem(407, "component.smd.resistor.bioware");
        SMD_DIODE_BIOWARE = addItem(408, "component.smd.diode.bioware");

        ELECTRICALLY_WIRED_PETRI_DISH = addItem(409, "electrically.wired.petri.dish");
        NEURO_SUPPORT_UNIT = addItem(410, "neuro.support.unit");
        CYBER_PROCESSING_UNIT = addItem(411, "cyber.processing.unit");

        BATTERY_SMALL_VANADIUM_EMPTY = addItem(412, "small.vanadium.battery.empty");
        BATTERY_SMALL_NEUTRONIUM_EMPTY = addItem(413, "small.neutronium.battery.empty");
        BATTERY_MEDIUM_VANADIUM_EMPTY = addItem(414, "medium.vanadium.battery.empty");
        BATTERY_MEDIUM_NAQUADRIA_EMPTY = addItem(415, "medium.naquadria.battery.empty");
        BATTERY_MEDIUM_NEUTRONIUM_EMPTY = addItem(416, "medium.neutronium.battery.empty");
        BATTERY_LARGE_VANADIUM_EMPTY = addItem(417, "large.vanadium.battery.empty");
        BATTERY_LARGE_NAQUADRIA_EMPTY = addItem(418, "large.naquadria.battery.empty");
        BATTERY_LARGE_NEUTRONIUM_EMPTY = addItem(419, "large.neutronium.battery.empty");

        HASOC = addItem(420, "hasoc");
        HASOC_WAFER = addItem(421, "hasoc.wafer");
        UHASOC = addItem(422, "uhasoc");
        UHASOC_WAFER = addItem(423, "uhasoc.wafer");
        UHPIC = addItem(424, "uhpic");
        UHPIC_WAFER = addItem(425, "uhpic.wafer");
        ARAM = addItem(426, "plate.aram");
        ARAM_WAFER = addItem(427, "wafer.aram");

        PLASMA_CONTAINMENT_CELL = addItem(428, "plasma.containment.cell");
        RHENIUM_PLASMA_CONTAINMENT_CELL = addItem(429, "rhenium.plasma.containment.cell");
        DEGENERATE_RHENIUM_PLATE = addItem(430, "degenerate.rhenium.plate");
        DEGENERATE_RHENIUM_DUST = addItem(432, "degenerate.rhenium.dust");
        PLATE_FIELD_SHAPE = addItem(431, "plate.field.shape");
        DUST_FIELD_SHAPE = addItem(439, "dust.field.shape");

        ZBLAN = addItem(433, "zblan");
        ZBLAN_INGOT = addItem(434, "zblan_ingot");
        HOT_ANNEALED_ZBLAN_INGOT = addItem(435, "hot_annealed_zblan_ingot");
        ZBLAN_FIBER = addItem(436, "zblan_fiber");
        ERBIUM_DOPED_ZBLAN = addItem(437, "erbium_doped_zblan");
        CLADDED_OPTICAL_FIBER_CORE = addItem(438, "cladded_optical_fiber_core");

        RAPIDLY_ROTATING_CRUCIBLE = addItem(440, "rapidly_rotating_crucible");
        HEAVY_METAL_ABSORBING_YARN = addItem(441, "heavy_metal_absorbing_yarn");
        URANIUM_SATURATED_YARN = addItem(442, "uranium_saturated_yarn");
        BORON_RETAINING_YARN = addItem(443, "boron_retaining_yarn");
        BORON_SATURATED_YARN = addItem(444, "boron_saturated_yarn");
        LITHIUM_SIEVE = addItem(445, "lithium_sieve");
        LITHIUM_SATURATED_LITHIUM_SIEVE = addItem(446, "lithium_saturated_lithium_sieve");
        NANOTOME = addItem(447, "nanotome");
        ACRYLIC_YARN = addItem(448, "acrylic_yarn");
        NEUTRON_PLASMA_CONTAINMENT_CELL = addItem(449, "neutron.plasma.containment.cell");
        INGOT_FIELD_SHAPE = addItem(450, "ingot.field.shape");
        WELL_PIPE = addItem(451,"well_pipe");
        WELL_CONNECTOR_PIECE = addItem(452,"well_connector_pipe");
        RIG_DRILL = addItem(453,"rig_drill");
        MEMORY_FOAM_BLOCK = addItem(454,"memory_foam_block");
        LASER_DIODE = addItem(455,"laser_diode");
        LASER_COOLING_UNIT = addItem(456,"laser_cooling_unit");
        MAGNETIC_TRAP = addItem(457,"magnetic_trap");
        EMPTY_LASER_COOLING_CONTAINER = addItem(458,"empty_laser_cooling_container");
        BOSE_EINSTEIN_COOLING_CONTAINER = addItem(459,"bose_einstein_cooling_container");
        
        ALUMINO_SILICATE_GLASS_TUBE = addItem(460,"alumino_silicate_glass_tube");
        INDUCTOR = addItem(461,"inductor");
        BALLAST = addItem(462,"ballast");
        UVA_HALIDE_LAMP = addItem(463,"uva_halide_lamp");
        WHITE_HALIDE_LAMP = addItem(464,"white_halide_lamp");
        RED_HALIDE_LAMP = addItem(465,"red_halide_lamp");
        BLUE_HALIDE_LAMP = addItem(466,"blue_halide_lamp");
        GREEN_HALIDE_LAMP = addItem(467,"green_halide_lamp");
        UVA_LAMP_CORE = addItem(468,"uva_lamp_core");
        WHITE_LAMP_CORE = addItem(469,"white_lamp_core");
        RED_LAMP_CORE = addItem(470,"red_lamp_core");
        BLUE_LAMP_CORE = addItem(471,"blue_lamp_core");
        GREEN_LAMP_CORE = addItem(472,"green_lamp_core");
        ACTINIUM_PLASMA_CONTAINMENT_CELL = addItem(473, "actinium.plasma.containment.cell");

        SHEWANELLA_CULTURE = addItem(475, "shewanella.culture");
        STREPTOCOCCUS_CULTURE = addItem(476, "streptococcus.culture");
        ESCHERICHIA_CULTURE = addItem(477, "eschericia.culture");
        BIFIDOBACTERIUM_CULTURE = addItem(478, "bifidobacterium.culture");
        BREVIBACTERIUM_CULTURE = addItem(479, "brevibacterium.culture");
        
        NDYAG_BOULE = addItem(480,"ndyag_boule");
        PRHOYLF_BOULE = addItem(481,"prhoylf_boule");
        LUTMYVO_BOULE = addItem(482,"lutmyvo_boule");
        NDYAG_ROD = addItem(483,"ndyag_rod");
        PRHOYLF_ROD = addItem(484,"prhoylf_rod");
        LUTMYVO_ROD = addItem(485,"lutmyvo_rod");
        LOW_FREQUENCY_LASER = addItem(486,"low_frequency_laser");
        MEDIUM_FREQUENCY_LASER = addItem(487,"medium_frequency_laser");
        HIGH_FREQUENCY_LASER = addItem(488,"high_frequency_laser");
        PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE = addItem(489,"periodically_poled_lithium_niobate_boule");
        HIGHLY_REFLECTIVE_MIRROR = addItem(490,"highly_reflective_mirror");
        NON_LINEAR_OPTICAL_LENS = addItem(491,"non_linear_optical_lens");
        ROTATING_TRANSPARENT_SURFACE = addItem(492,"rotating_transparent_surface");
        ELECTRON_SOURCE = addItem(493,"electron_source");

        SMD_CAPACITOR_OPTICAL = addItem(494,"smd_capacitor_optical");
        SMD_DIODE_OPTICAL = addItem(495,"smd_diode_optical");
        SMD_RESISTOR_OPTICAL = addItem(496,"smd_resistor_optical");
        SMD_TRANSISTOR_OPTICAL = addItem(497,"smd_transistor_optical");
        OPTICAL_SOC_WAFER = addItem(498,"optical_soc_wafer");
        OPTICAL_SOC = addItem(499,"optical_soc");
        OPTICAL_PROCESSING_CORE = addItem(500,"optical_processing_core");
        OPTICAL_PROCESSOR = addItem(501,"circuit.processor.optical").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
        OPTICAL_ASSEMBLY = addItem(502,"circuit.assembly.optical").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
        OPTICAL_COMPUTER = addItem(503,"circuit.computer.optical").setUnificationData(OrePrefix.circuit, UEV);
        OPTICAL_MAINFRAME = addItem(504,"circuit.mainframe.optical").setUnificationData(OrePrefix.circuit, UIV);

        PEEK_POLYAMIDE_FOIL = addItem(505,"peek_polyamide_foil");
        HIGHLY_INSULATING_FOIL = addItem(506,"highly_insulating_foil");
        INSULATION_WIRE_ASSEMBLY = addItem(507,"insulation_wire_assembly");

        NEUTRONIUM_SPHERE = addItem(508, "neutronium.sphere");
        TRIPLET_NEUTRONIUM_SPHERE = addItem(509, "triplet.neutronium.sphere");
        CHARGED_TRIPLET_NEUTRONIUM_SPHERE = addItem(510, "charged.triplet.neutronium.sphere");
        CONTAINED_REISSNER_NORDSTROM_SINGULARITY = addItem(511, "contained.reissner.nordstrom.singularity");
        CONTAINED_KERR_NEWMANN_SINGULARITY = addItem(512, "contained.kerr.newmann.singularity");
        CONTAINED_KERR_SINGULARITY = addItem(513, "contained.kerr.singularity");
        CONTAINED_HIGH_DENSITY_PROTONIC_MATTER = addItem(514, "contained.high.density.protonic.matter");
        TIME_DILATION_CONTAINMENT_UNIT = addItem(515, "time.dilation.containment.unit");
        MICROWORMHOLE_GENERATOR = addItem(516, "microwormhole.generator");
        MACROWORMHOLE_GENERATOR = addItem(517, "macrowormhole.generator");
        STABILIZED_WORMHOLE_GENERATOR = addItem(518, "stabilized.wormhole.generator");
        CONTAINED_EXOTIC_MATTER = addItem(519, "contained.exotic.matter");
        SEPARATION_ELECTROMAGNET = addItem(521, "separation.electromagnet");
        SPHERE_FIELD_SHAPE = addItem(522, "sphere.field.shape");

        AEROGRAPHENE = addItem(523,"aerographene");
        SCINTILLATOR_CRYSTAL = addItem(524,"scintillator_crystal");
        SCINTILLATOR = addItem(525,"scintillator");
        LEPTON_TRAP_CRYSTAL =  addItem(526,"lepton_trap_crystal");
        HASSIUM_SEED_CRYSTAL = addItem(527, "hassium_seed_crystal");
        HASSIUM_BOULE = addItem(520,"hassium_boule");
        HASSIUM_WAFER = addItem(528,"hassium_wafer");
        COATED_HASSIUM_WAFER = addItem(529, "coated_hassium_wafer");
        PHOTOCOATED_HASSIUM_WAFER = addItem(530,"photocoated_hassium_wafer");
        GRATING_LITHOGRAPHY_MASK = addItem(531, "grating_lithography_mask");
        DIFFRACTOR_GRATING_MIRROR = addItem(532,"diffractor_grating_mirror");
        ULTRASHORT_PULSE_LASER = addItem(533,"ultrashort_pulse_laser");
        SMD_CAPACITOR_COSMIC = addItem(534,"smd_capacitor_cosmic");
        SMD_DIODE_COSMIC = addItem(535,"smd_diode_cosmic");
        SMD_RESISTOR_COSMIC = addItem(536,"smd_resistor_cosmic");
        SMD_TRANSISTOR_COSMIC = addItem(537,"smd_transistor_cosmic");
        COSMIC_PROCESSING_UNIT_CORE = addItem(538,"cosmic_processing_unit_core");
        COSMIC_PROCESSING_CORE = addItem(539,"cosmic_processing_core");
        GRAPHENE_IRON_PLATE = addItem(540,"graphene_iron_plate");
        CUPRIVADUS_CULTURE = addItem(541, "cupriavidus.culture");

        SMD_CAPACITOR_SUPRACAUSAL = addItem(542, "smd.capacitor.supracausal");
        SMD_RESISTOR_SUPRACAUSAL = addItem(543, "smd.resistor.supracausal");
        SMD_DIODE_SUPRACAUSAL = addItem(544, "smd.diode.supracausal");
        SMD_TRANSISTOR_SUPRACAUSAL = addItem(545, "smd.transistor.supracausal");

        SUPRACAUSAL_PROCESSOR = addItem(546, "circuit.processor.supracausal").setUnificationData(OrePrefix.circuit, UEV);
        SUPRACAUSAL_ASSEMBLY = addItem(547, "circuit.assembly.supracausal").setUnificationData(OrePrefix.circuit, UIV);
        SUPRACAUSAL_COMPUTER = addItem(548, "circuit.computer.supracausal").setUnificationData(OrePrefix.circuit, UMV);
        SUPRACAUSAL_MAINFRAME = addItem(549, "circuit.mainframe.supracausal").setUnificationData(OrePrefix.circuit, UXV);

        TOPOLOGICAL_MANIPULATOR_UNIT = addItem(550, "topological.manipulator.unit");
        RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM = addItem(551, "relativistic.spinorial.memory.system");
        GRAVITON_TRANSDUCER = addItem(552, "graviton.transducer");
        NUCLEAR_CLOCK = addItem(553, "nuclear.clock");
        SUPRACAUSAL_PROCESSING_CORE = addItem(554, "supracausal.processing.core");
        MANIFOLD_OSCILLATORY_POWER_CELL = addItem(555, "manifold.oscillatory.power.cell");
        QCD_PROTECTIVE_PLATING = addItem(556, "qcd.protective.plating");
        CTC_GUIDANCE_UNIT = addItem(557, "ctc.guidance.unit");
        CTC_COMPUTATIONAL_UNIT_CONTAINER = addItem(558, "ctc.computational.unit.container");
        EIGENFOLDED_KERR_MANIFOLD = addItem(559, "eigenfolded.kerr.manifold");
        CTC_COMPUTATIONAL_UNIT = addItem(560, "ctc.computational.unit");
        RECURSIVELY_FOLDED_NEGATIVE_SPACE = addItem(561, "recursively.folded.negative.space");
        
        WIRE_FIELD_SHAPE = addItem(562,"wire.field.shape");
        EXTREMELY_DURABLE_PLASMA_CELL = addItem(563,"extremely.durable.plasma.cell");
        DENSE_NEUTRON_PLASMA_CELL = addItem(564,"dense.neutron.plasma.cell");
        COSMIC_NEUTRON_PLASMA_CELL = addItem(565,"cosmic.neutron.plasma.cell");
        HIGHLY_DENSE_POLYMER_PLATE = addItem(566,"highly.dense.polymer.plate");
        COSMIC_MESH_CONTAINMENT_UNIT = addItem(567,"cosmic.mesh.containment.unit");
        COSMIC_MESH = addItem(568,"cosmic.mesh");
        COSMIC_FABRIC = addItem(569,"cosmic.fabric");
        ROD_FIELD_SHAPE = addItem(570, "rod.field.shape");

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

        MV_INFINITE_WATER_SOURCE = addItem(571, "infinite.water.mv");
        HV_INFINITE_WATER_SOURCE = addItem(572, "infinite.water.hv");
        EV_INFINITE_WATER_SOURCE = addItem(573, "infinite.water.ev");
        IV_INFINITE_WATER_SOURCE = addItem(574, "infinite.water.iv");
        LuV_INFINITE_WATER_SOURCE = addItem(575, "infinite.water.luv");
        ZPM_INFINITE_WATER_SOURCE = addItem(576, "infinite.water.zpm");
        UV_INFINITE_WATER_SOURCE = addItem(577, "infinite.water.uv");

        COLOURED_LEDS = addItem(578, "coloured.leds");
        DISPLAY = addItem(579, "display");
        
        CHARGED_LEPTON_TRAP_CRYSTAL = addItem(580,"charged.lepton.trap.crystal");

        // ids 609-623 reserved for lithography masks
        LITHOGRAPHY_MASK_ILC = addItem(609, "lithography.mask.ilc");
        LITHOGRAPHY_MASK_CPU = addItem(610, "lithography.mask.cpu");
        LITHOGRAPHY_MASK_RAM = addItem(611, "lithography.mask.ram");

        ILC_WAFER_ETCHED = addItem(624, "ilc.wafer.etched");
        ILC_WAFER_DOPED = addItem(625, "ilc.wafer.doped");
        CPU_WAFER_ETCHED = addItem(626, "cpu.wafer.etched");
        CPU_WAFER_DOPED = addItem(627, "cpu.wafer.doped");
        RAM_WAFER_ETCHED = addItem(628, "ram.wafer.etched");
        RAM_WAFER_DOPED = addItem(629, "ram.wafer.doped");

        NEURO_PROCESSOR = addItem(15, "processor.neuro");
        PYROLYTIC_CARBON = addItem(16, "pyrolytic_carbon");
        RAW_CRYSTAL_CHIP = addItem(17, "crystal.raw");
        STEM_CELLS = addItem(18, "stemcells");
        MICA_SHEET = addItem(26, "mica_sheet");
        MICA_INSULATOR_SHEET = addItem(27, "mica_insulator_sheet");
        MICA_INSULATOR_FOIL = addItem(28, "mica_insulator_foil");
        BASIC_BOARD = addItem(29, "board.basic");
        GOOD_PHENOLIC_BOARD = addItem(30, "board.good.phenolic");
        GOOD_PLASTIC_BOARD = addItem(31, "board.good.plastic");
        ADVANCED_BOARD = addItem(32, "board.advanced");
        EXTREME_BOARD = addItem(33, "board.extreme");
        ELITE_BOARD = addItem(34, "board.elite");
        MASTER_BOARD = addItem(35, "board.master");
        COMPRESSED_COKE_CLAY = addItem(36, "compressed.coke.clay");
        HOT_IRON_INGOT = addItem(37, "hot_iron_ingot");
//        COMPRESSED_FIRECLAY = addItem(37, "compressed.fireclay");
//        FIRECLAY_BRICK = addItem(38, "brick.fireclay").setUnificationData(OrePrefix.ingot, Materials.Fireclay);

        MetaItems.COMPRESSED_CLAY.setInvisible();
//        MetaItems.COMPRESSED_FIRECLAY.setInvisible();


        MetaItems.DATA_CONTROL_CIRCUIT_IV.setInvisible();
        MetaItems.CRYSTAL_PROCESSOR_IV.setInvisible();
        MetaItems.ADVANCED_CIRCUIT_MV.setInvisible();
        MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.setInvisible();
        MetaItems.ADVANCED_CIRCUIT_PARTS_LV.setInvisible();
        MetaItems.TURBINE_ROTOR.setInvisible();
        MetaItems.TOOL_DATA_STICK.addComponents(new DataStickFluidSamplerBehavior());

    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return super.hasEffect(itemStack) || itemStack.getMetadata() == UNSTABLE_STAR.getStackForm().getMetadata();
    }
}
