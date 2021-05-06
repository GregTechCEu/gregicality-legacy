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


public class MultiSmelterInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.MULTI_FURNACE;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
			if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {

				shapeInfo.add(MultiblockShapeInfo.builder()
						.aisle("IXX", "CCC", "XXX")
						.aisle("SXE", "C#C", "XXX")
						.aisle("OXX", "CCC", "XXX")
						.where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
						.where('C', MetaBlocks.WIRE_COIL.getState(coilType))
						.where('S', GATileEntities.MULTI_FURNACE, EnumFacing.WEST)
						.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.MV], EnumFacing.EAST)
						.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('#', Blocks.AIR.getDefaultState())
						.build());
			}
		}
		for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
			if (!Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName())) {

				shapeInfo.add(MultiblockShapeInfo.builder()
						.aisle("IXX", "CCC", "XXX")
						.aisle("SXE", "C#C", "XXX")
						.aisle("OXX", "CCC", "XXX")
						.where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
						.where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
						.where('S', GATileEntities.MULTI_FURNACE, EnumFacing.WEST)
						.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.MV], EnumFacing.EAST)
						.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
						.where('#', Blocks.AIR.getDefaultState())
						.build());
			}
		}
		return shapeInfo;
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.multi_smelter.description")};
	}

}
