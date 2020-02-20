package gregicadditions.item;

import gregicadditions.GAConfig;
import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GAMetaItem extends MaterialMetaItem {

	public GAMetaItem() {
		super(OrePrefix.valueOf("plateCurved"), OrePrefix.valueOf("ingotDouble"), OrePrefix.valueOf("round"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	public void registerSubItems() {
		GAMetaItems.GLASS_FIBER = addItem(21, "component.glass.fiber");
		GAMetaItems.PETRI_DISH = addItem(23, "component.petri.dish");

		if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
			GAMetaItems.ELECTRODE_APATITE = addItem(108, "electrode.apatite");
			GAMetaItems.ELECTRODE_BLAZE = addItem(109, "electrode.blaze");
			GAMetaItems.ELECTRODE_BRONZE = addItem(110, "electrode.bronze");
			GAMetaItems.ELECTRODE_COPPER = addItem(111, "electrode.copper");
			GAMetaItems.ELECTRODE_DIAMOND = addItem(112, "electrode.diamond");
			GAMetaItems.ELECTRODE_EMERALD = addItem(113, "electrode.emerald");
			GAMetaItems.ELECTRODE_ENDER = addItem(114, "electrode.ender");
			GAMetaItems.ELECTRODE_GOLD = addItem(115, "electrode.gold");
			if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) GAMetaItems.ELECTRODE_IRON = addItem(116, "electrode.iron");
			GAMetaItems.ELECTRODE_LAPIS = addItem(117, "electrode.lapis");
			GAMetaItems.ELECTRODE_OBSIDIAN = addItem(118, "electrode.obsidian");
			if (Loader.isModLoaded("extrautils2")) GAMetaItems.ELECTRODE_ORCHID = addItem(119, "electrode.orchid");
			if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn") || Loader.isModLoaded("binniecore")) GAMetaItems.ELECTRODE_RUBBER = addItem(120, "electrode.rubber");
			GAMetaItems.ELECTRODE_TIN = addItem(121, "electrode.tin");
		}

		if (GAConfig.GT5U.enableZPMandUVBats) {
			GAMetaItems.ENERGY_MODULE = addItem(122, "energy.module").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(10000000000L, 7) }).setModelAmount(8);
			GAMetaItems.ENERGY_CLUSTER = addItem(123, "energy.cluster").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(100000000000L, 8) }).setModelAmount(8);
		}

		if (GAConfig.GT5U.replaceUVwithMAXBat) {
			GAMetaItems.MAX_BATTERY = addItem(124, "max.battery").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(9223372036854775807L, 9) }).setModelAmount(8);
			MetaItems.ZPM2.setInvisible();
		}

		GAMetaItems.SCHEMATIC = addItem(131, "schematic").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_2X2 = addItem(132, "schematic.2by2").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_3X3 = addItem(133, "schematic.3by3").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_DUST = addItem(134, "schematic.dust").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));

		GAMetaItems.ADVANCED_CIRCUIT = addItem(2, "circuit.advanced.regular").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
		GAMetaItems.GOOD_CIRCUIT = addItem(3, "circuit.good.regular").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
		GAMetaItems.CRYSTAL_COMPUTER = addItem(8, "computer.crystal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
		GAMetaItems.NANO_COMPUTER = addItem(9, "computer.nano").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
		GAMetaItems.QUANTUM_COMPUTER = addItem(10, "computer.quantum").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Master);
		GAMetaItems.CRYSTAL_MAINFRAME = addItem(11, "mainframe.crystal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
		GAMetaItems.NANO_MAINFRAME = addItem(12, "mainframe.nano").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Master);
		GAMetaItems.INTEGRATED_MAINFRAME = addItem(13, "mainframe.normal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
		GAMetaItems.QUANTUM_MAINFRAME = addItem(14, "mainframe.quantum").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
		GAMetaItems.INTEGRATED_COMPUTER = addItem(16, "computer.normal").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Extreme);
		GAMetaItems.NEURO_PROCESSOR = addItem(15, "processor.neuro");
		GAMetaItems.RAW_CRYSTAL_CHIP = addItem(17, "crystal.raw");
		GAMetaItems.STEM_CELLS = addItem(18, "stemcells");
		GAMetaItems.MICA_SHEET = addItem(26, "mica_sheet");
		GAMetaItems.MICA_INSULATOR_SHEET = addItem(27, "mica_insulator_sheet");
		GAMetaItems.MICA_INSULATOR_FOIL = addItem(28, "mica_insulator_foil");
		GAMetaItems.BASIC_BOARD = addItem(29, "board.basic");
		GAMetaItems.GOOD_PHENOLIC_BOARD = addItem(30, "board.good.phenolic");
		GAMetaItems.GOOD_PLASTIC_BOARD = addItem(31, "board.good.plastic");
		GAMetaItems.ADVANCED_BOARD = addItem(32, "board.advanced");
		GAMetaItems.EXTREME_BOARD = addItem(33, "board.extreme");
		GAMetaItems.ELITE_BOARD = addItem(34, "board.elite");
		GAMetaItems.MASTER_BOARD = addItem(35, "board.master");
		GAMetaItems.COMPRESSED_COKE_CLAY = addItem(36, "compressed.coke.clay");
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return stack.copy();
	}
}
