package gregicadditions.item;

import gregicadditions.Gregicality;
import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class GAMetaItems {

    private static List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

    public static MetaItem<?>.MetaValueItem GLASS_FIBER;
    public static MetaItem<?>.MetaValueItem PETRI_DISH;
    public static MetaItem<?>.MetaValueItem COMPRESSED_COKE_CLAY;
    //    public static MetaItem<?>.MetaValueItem COMPRESSED_FIRECLAY;
//    public static MetaItem<?>.MetaValueItem FIRECLAY_BRICK;
    public static MetaItem<?>.MetaValueItem ENERGY_MODULE;
    public static MetaItem<?>.MetaValueItem ENERGY_CLUSTER;
    public static MetaItem<?>.MetaValueItem MAX_BATTERY;

    //more smd
    public static MetaItem<?>.MetaValueItem ARRAY_RESISTOR;
    public static MetaItem<?>.MetaValueItem ARRAY_DIODE;
    public static MetaItem<?>.MetaValueItem ARRAY_CAPACITOR;
    public static MetaItem<?>.MetaValueItem ARRAY_TRANSISTOR;

    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_BIOWARE;

    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_BIOWARE;

    public static MetaItem<?>.MetaValueItem SMD_DIODE_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_NANO;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_BIOWARE;

    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_BIOWARE;


    //circuits
    public static MetaItem<?>.MetaValueItem PRIMITIVE_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem ELECTRONIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem ELECTRONIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem REFINED_PROCESSOR;
    public static MetaItem<?>.MetaValueItem REFINED_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem REFINED_COMPUTER;
    public static MetaItem<?>.MetaValueItem REFINED_MAINFRAME;
    public static MetaItem<?>.MetaValueItem MICRO_PROCESSOR;
    public static MetaItem<?>.MetaValueItem MICRO_COMPUTER;
    public static MetaItem<?>.MetaValueItem MICRO_MAINFRAME;
    public static MetaItem<?>.MetaValueItem NANO_COMPUTER;
    public static MetaItem<?>.MetaValueItem NANO_MAINFRAME;
    public static MetaItem<?>.MetaValueItem CRYSTAL_PROCESSOR;
    public static MetaItem<?>.MetaValueItem CRYSTAL_COMPUTER;
    public static MetaItem<?>.MetaValueItem CRYSTAL_MAINFRAME;
    public static MetaItem<?>.MetaValueItem QUANTUM_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem QUANTUM_COMPUTER;
    public static MetaItem<?>.MetaValueItem QUANTUM_MAINFRAME;

    //magneto circuit
    public static MetaItem<?>.MetaValueItem RAW_IMPRINT_SUPPORTED_BOARD;
    public static MetaItem<?>.MetaValueItem IMPRINT_SUPPORTED_BOARD;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_ULV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_LV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_MV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_HV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_EV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_IV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_LUV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_ZPM;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_UV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_MAX;


    public static MetaItem<?>.MetaValueItem NEURO_PROCESSOR;
    public static MetaItem<?>.MetaValueItem RAW_CRYSTAL_CHIP;

    public static MetaItem<?>.MetaValueItem MICA_SHEET;
    public static MetaItem<?>.MetaValueItem MICA_INSULATOR_SHEET;
    public static MetaItem<?>.MetaValueItem MICA_INSULATOR_FOIL;

    public static MetaItem<?>.MetaValueItem BASIC_BOARD;
    public static MetaItem<?>.MetaValueItem GOOD_PHENOLIC_BOARD;
    public static MetaItem<?>.MetaValueItem GOOD_PLASTIC_BOARD;
    public static MetaItem<?>.MetaValueItem ADVANCED_BOARD;
    public static MetaItem<?>.MetaValueItem EXTREME_BOARD;
    public static MetaItem<?>.MetaValueItem ELITE_BOARD;
    public static MetaItem<?>.MetaValueItem MASTER_BOARD;

    public static MetaItem<?>.MetaValueItem ELECTRODE_APATITE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BLAZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BRONZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_COPPER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_DIAMOND;
    public static MetaItem<?>.MetaValueItem ELECTRODE_EMERALD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ENDER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_GOLD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_IRON;
    public static MetaItem<?>.MetaValueItem ELECTRODE_LAPIS;
    public static MetaItem<?>.MetaValueItem ELECTRODE_OBSIDIAN;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ORCHID;
    public static MetaItem<?>.MetaValueItem ELECTRODE_RUBBER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_TIN;

    public static MetaItem<?>.MetaValueItem BENDING_CYLINDER;
    public static MetaItem<?>.MetaValueItem SMALL_BENDING_CYLINDER;

    public static MetaItem<?>.MetaValueItem SCHEMATIC;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_2X2;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_3X3;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_DUST;

    public static MetaItem<?>.MetaValueItem STEM_CELLS;

    public static MetaItem<?>.MetaValueItem HUGE_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem LARGE_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem MEDIUM_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem SMALL_TURBINE_ROTOR;


    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_LEGGINS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_BOOTS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_HELMET;

    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_LEGGINS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_BOOTS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_HELMET;

    public static ArmorMetaItem<?>.ArmorMetaValueItem SEMIFLUID_JETPACK;
    public static ArmorMetaItem<?>.ArmorMetaValueItem IMPELLER_JETPACK;

    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_LV;
    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_MV;
    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_HV;

    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_IMPELLER_JETPACK;
    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_NANO_MUSCLE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_QAURK_TECH_SUITE_CHESTPLATE;

    public static MetaItem<?>.MetaValueItem IMPELLER_MV;
    public static MetaItem<?>.MetaValueItem IMPELLER_HV;
    public static MetaItem<?>.MetaValueItem GRAVITATION_ENGINE;
    public static MetaItem<?>.MetaValueItem INSULATING_TAPE;

    public static MetaItem<?>.MetaValueItem HAND_PUMP;


    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_LANTHANIDE_A;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_LANTHANIDE_B;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_ALKALINE;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_A;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_B;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_C;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_HEAVY_METAL;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METALOID;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_REACTIVE_NONMETAL;
    public static MetaItem<?>.MetaValueItem THORIUM_WASTE;
    public static MetaItem<?>.MetaValueItem URANIUM_WASTE;
    public static MetaItem<?>.MetaValueItem NEPTUNIUM_WASTE;
    public static MetaItem<?>.MetaValueItem PLUTONIUM_WASTE;
    public static MetaItem<?>.MetaValueItem AMERICIUM_WASTE;
    public static MetaItem<?>.MetaValueItem CURIUM_WASTE;
    public static MetaItem<?>.MetaValueItem BERKELIUM_WASTE;
    public static MetaItem<?>.MetaValueItem CALIFORNIUM_WASTE;
    public static MetaItem<?>.MetaValueItem EINSTEINIUM_WASTE;
    public static MetaItem<?>.MetaValueItem FERMIUM_WASTE;
    public static MetaItem<?>.MetaValueItem MENDELEVIUM_WASTE;

    public static MetaItem<?>.MetaValueItem COSMIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem COSMIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem COSMIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem COSMIC_MAINFRAME;

    public static MetaItem<?>.MetaValueItem BIOWARE_PROCESSOR;
    public static MetaItem<?>.MetaValueItem BIOWARE_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem BIOWARE_COMPUTER;
    public static MetaItem<?>.MetaValueItem BIOWARE_MAINFRAME;

    public static MetaItem<?>.MetaValueItem WAFER_AMERICIUM;
    public static MetaItem<?>.MetaValueItem WAFER_EUROPIUM;
    public static MetaItem<?>.MetaValueItem WAFER_NEUTRONIUM;

    public static MetaItem<?>.MetaValueItem BOULE_AMERICIUM;
    public static MetaItem<?>.MetaValueItem BOULE_EUROPIUM;
    public static MetaItem<?>.MetaValueItem BOULE_NEUTRONIUM;

    public static MetaItem<?>.MetaValueItem UNSTABLE_STAR;

    public static MetaItem<?>.MetaValueItem HYPERIUM_ESSENCE;
    public static MetaItem<?>.MetaValueItem HYPERIUM_SEED;
    public static MetaItem<?>.MetaValueItem LUDICIUM_ESSENCE;
    public static MetaItem<?>.MetaValueItem LUDICIUM_SEED;

    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_UHV;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_UEV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_UHV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_UEV;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_UHV;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_UEV;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_UHV;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_UEV;
    public static MetaItem<?>.MetaValueItem EMITTER_UHV;
    public static MetaItem<?>.MetaValueItem EMITTER_UEV;
    public static MetaItem<?>.MetaValueItem SENSOR_UHV;
    public static MetaItem<?>.MetaValueItem SENSOR_UEV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_UHV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_UEV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_UHV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_UEV;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_UIV;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_UMV;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_UXV;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_UIV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_UMV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_UXV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_UIV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_UMV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_UXV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_UIV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_UMV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_UXV;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_MAX;
    public static MetaItem<?>.MetaValueItem EMITTER_UIV;
    public static MetaItem<?>.MetaValueItem EMITTER_UMV;
    public static MetaItem<?>.MetaValueItem EMITTER_UXV;
    public static MetaItem<?>.MetaValueItem EMITTER_MAX;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_UIV;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_UMV;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_UXV;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_MAX;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_UIV;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_UMV;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_UXV;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_MAX;
    public static MetaItem<?>.MetaValueItem SENSOR_UIV;
    public static MetaItem<?>.MetaValueItem SENSOR_UMV;
    public static MetaItem<?>.MetaValueItem SENSOR_UXV;
    public static MetaItem<?>.MetaValueItem SENSOR_MAX;

    public static MetaItem<?>.MetaValueItem ULTRASONIC_HOMOGENIZER;
    public static MetaItem<?>.MetaValueItem STERILIZED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem CONTAMINATED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem ELECTRICALLY_WIRED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem CLEAN_CULTURE;
    public static MetaItem<?>.MetaValueItem PIEZOELECTRIC_CRYSTAL;
    public static MetaItem<?>.MetaValueItem NEURO_SUPPORT_UNIT;
    public static MetaItem<?>.MetaValueItem CYBER_PROCESSING_UNIT;

    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_VANADIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_VANADIUM;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_VANADIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_VANADIUM;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_VANADIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_VANADIUM;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_NAQUADRIA_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_NAQUADRIA;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_NAQUADRIA_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_NAQUADRIA;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_NEUTRONIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_NEUTRONIUM;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_NEUTRONIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_NEUTRONIUM;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_NEUTRONIUM_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_NEUTRONIUM;

    public static MetaItem<?>.MetaValueItem HASOC;
    public static MetaItem<?>.MetaValueItem HASOC_WAFER;
    public static MetaItem<?>.MetaValueItem UHASOC;
    public static MetaItem<?>.MetaValueItem UHASOC_WAFER;
    public static MetaItem<?>.MetaValueItem UHPIC;
    public static MetaItem<?>.MetaValueItem UHPIC_WAFER;
    public static MetaItem<?>.MetaValueItem ARAM;
    public static MetaItem<?>.MetaValueItem ARAM_WAFER;

    public static void init() {
        GAMetaItem item = new GAMetaItem();
        item.setRegistryName("ga_meta_item");
        GAMetaTool tool = new GAMetaTool();
        tool.setRegistryName("ga_meta_tool");
        GAMetaArmor armor = new GAMetaArmor();
        armor.setRegistryName("ga_armor");
        GADustItem dustItem = new GADustItem((short) 0);
        dustItem.setRegistryName("ga_dust");
        GAMetaItem2 item2 = new GAMetaItem2((short) 0);
        item2.setRegistryName("ga_meta_item2");
    }

    public static void registerOreDict() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof GAMetaItem) {
                ((GAMetaItem) item).registerOreDict();
            }
        }
    }

    public static void registerRecipes() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof GAMetaTool) ((GAMetaTool) item).registerRecipes();
        }
    }

    public static ItemStack getFilledCell(Fluid fluid, int count) {
        ItemStack fluidCell = MetaItems.FLUID_CELL.getStackForm().copy();
        IFluidHandlerItem fluidHandlerItem = fluidCell.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        try {
            fluidHandlerItem.fill(new FluidStack(fluid, 1000), true);

        } catch (Exception e) {
            Gregicality.LOGGER.error("The fluid " + fluid.toString() + " failed to do something with getFilledCell");
            Gregicality.LOGGER.error(e);
            fluidHandlerItem.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
        }
        fluidCell = fluidHandlerItem.getContainer();
        fluidCell.setCount(count);
        return fluidCell;
    }

    public static ItemStack getFilledCell(Fluid fluid) {
        return getFilledCell(fluid, 1);
    }

    public static boolean hasPrefix(ItemStack stack, String prefix, String... ignore) {
        for (int i : OreDictionary.getOreIDs(stack)) {
            if (OreDictionary.getOreName(i).startsWith(prefix)) {
                boolean valid = true;
                for (String s : ignore) {
                    if (OreDictionary.getOreName(i).startsWith(s)) valid = false;
                }
                if (!valid) continue;
                return true;
            }
        }
        return false;
    }
}
