package gregicadditions.jei.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
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

public class VolcanusInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.VOLCANUS;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
			if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {

				shapeInfo.add(MultiblockShapeInfo.builder()
						.aisle("IFX", "CCC", "CCC", "XXX")
						.aisle("SXE", "C#C", "C#C", "MXX")
						.aisle("ODX", "CCC", "CCC", "XXX")
						.where('X', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N))
						.where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.WEST)
						.where('C', MetaBlocks.WIRE_COIL.getState(coilType))
						.where('S', GATileEntities.VOLCANUS, EnumFacing.WEST)
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
						.where('X', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N))
						.where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
						.where('S', GATileEntities.VOLCANUS, EnumFacing.WEST)
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
		return new String[]{I18n.format("gregtech.multiblock.volcanus.description")};
	}
}
