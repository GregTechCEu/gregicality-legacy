package gregicadditions.item;

import gregicadditions.GAConfig;
import gregicadditions.item.behavior.*;
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
import static gregtech.api.unification.material.Materials.*;


public class GAMetaItem extends MaterialMetaItem {
    public GAMetaItem() {
        super(GAConfig.GT6.addCurvedPlates ? OrePrefix.valueOf("plateCurved") : null, GAConfig.GT6.addDoubleIngots ? OrePrefix.valueOf("ingotDouble") : null, GAConfig.GT6.addRounds ? OrePrefix.valueOf("round") : null, OrePrefix.valueOf("dioxide"), OrePrefix.valueOf("nitrate"), OrePrefix.valueOf("hexafluoride"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
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
        CIRCUIT_MAGNETIC_MAX = addItem(229, "circuit.resonatic.max").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
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


        WAFER_AMERICIUM = addItem(333, "wafer.americium");
        WAFER_EUROPIUM = addItem(334, "wafer.europium");
        WAFER_NEUTRONIUM = addItem(335, "wafer.neutronium");

        COSMIC_CIRCUIT = addItem(336, "circuit.circuit.cosmic");
        COSMIC_PROCESSOR = addItem(337, "circuit.processor.cosmic");
        COSMIC_PROCESSOR_ARRAY = addItem(338, "circuit.assembly.cosmic");
        COSMIC_MAINFRAME = addItem(339, "circuit.mainframe.cosmic");

        BOULE_AMERICIUM = addItem(340, "boule.americium");
        BOULE_EUROPIUM = addItem(341, "boule.europium");
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


        ThoriumRadioactive.waste = THORIUM_WASTE;
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

        NEURO_PROCESSOR = addItem(15, "processor.neuro");
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
        COMPRESSED_FIRECLAY = addItem(37, "compressed.fireclay");
        FIRECLAY_BRICK = addItem(38, "brick.fireclay").setUnificationData(OrePrefix.ingot, Materials.Fireclay);

        MetaItems.COMPRESSED_CLAY.setInvisible();
        MetaItems.COMPRESSED_FIRECLAY.setInvisible();


        MetaItems.DATA_CONTROL_CIRCUIT_IV.setInvisible();
        MetaItems.CRYSTAL_PROCESSOR_IV.setInvisible();
        MetaItems.ADVANCED_CIRCUIT_MV.setInvisible();
        MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.setInvisible();
        MetaItems.ADVANCED_CIRCUIT_PARTS_LV.setInvisible();
        MetaItems.TURBINE_ROTOR.setInvisible();




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
