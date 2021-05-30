package gregicadditions.jei.multi.override;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ElectricBlastFurnaceInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.ELECTRIC_BLAST_FURNACE;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
			if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {

				shapeInfo.add(MultiblockShapeInfo.builder()
						.aisle("IFX", "CCC", "CCC", "XXX")
						.aisle("SXE", "C#C", "C#C", "XHX")
						.aisle("ODM", "CCC", "CCC", "XXX")
						.where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
						.where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
						.where('H', GATileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
						.where('C', MetaBlocks.WIRE_COIL.getState(coilType))
						.where('S', GATileEntities.ELECTRIC_BLAST_FURNACE, EnumFacing.WEST)
						.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.MV], EnumFacing.EAST)
						.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.NORTH)
						.where('D', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.SOUTH)
						.where('#', Blocks.AIR.getDefaultState())
						.build());
			}
		}
		for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
			if (!Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName())) {

				shapeInfo.add(MultiblockShapeInfo.builder()
						.aisle("IFX", "CCC", "CCC", "XXX")
						.aisle("SXE", "C#C", "C#C", "XXX")
						.aisle("ODX", "CCC", "CCC", "XXX")
						.where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
						.where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
						.where('S', GATileEntities.ELECTRIC_BLAST_FURNACE, EnumFacing.WEST)
						.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.MV], EnumFacing.EAST)
						.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.NORTH)
						.where('D', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.SOUTH)
						.where('#', Blocks.AIR.getDefaultState())
						.build());
			}
		}
		return shapeInfo;
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.electric_blast_furnace.description")};
	}
}
