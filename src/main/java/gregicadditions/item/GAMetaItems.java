package gregicadditions.item;

import gregicadditions.GregicAdditions;
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
	public static MetaItem<?>.MetaValueItem ENERGY_MODULE;
	public static MetaItem<?>.MetaValueItem ENERGY_CLUSTER;
	public static MetaItem<?>.MetaValueItem MAX_BATTERY;
	public static MetaItem<?>.MetaValueItem NEURO_PROCESSOR;
	public static MetaItem<?>.MetaValueItem INTEGRATED_COMPUTER;
	public static MetaItem<?>.MetaValueItem CRYSTAL_COMPUTER;
	public static MetaItem<?>.MetaValueItem NANO_COMPUTER;
	public static MetaItem<?>.MetaValueItem QUANTUM_COMPUTER;
	public static MetaItem<?>.MetaValueItem GOOD_CIRCUIT;
	public static MetaItem<?>.MetaValueItem ADVANCED_CIRCUIT;
	public static MetaItem<?>.MetaValueItem CRYSTAL_MAINFRAME;
	public static MetaItem<?>.MetaValueItem NANO_MAINFRAME;
	public static MetaItem<?>.MetaValueItem INTEGRATED_MAINFRAME;
	public static MetaItem<?>.MetaValueItem QUANTUM_MAINFRAME;
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

	public static void init() {
		GAMetaItem item = new GAMetaItem();
		item.setRegistryName("ga_meta_item");
		GAMetaTool tool = new GAMetaTool();
		tool.setRegistryName("ga_meta_tool");
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
			GregicAdditions.LOGGER.error("The fluid " + fluid.toString() + " failed to do something with getFilledCell");
			GregicAdditions.LOGGER.error(e);
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
