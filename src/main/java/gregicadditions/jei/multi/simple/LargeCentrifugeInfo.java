package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeCentrifuge;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeCentrifugeInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_CENTRIFUGE;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("#XEX#", "XXGXX", "#XXX#")
					.aisle("IXXXX", "i###X", "XXXXX")
					.aisle("XXMXX", "G#P#G", "XXmXX")
					.aisle("OXXXX", "o###X", "XXXXX")
					.aisle("#XHX#", "XXSXX", "#XXX#")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
					.where('S', GATileEntities.LARGE_CENTRIFUGE, EnumFacing.SOUTH)
					.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
					.where('X', TileEntityLargeCentrifuge.casingState)
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.WEST)
					.where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.UP)
					.where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
					.where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
					.build());

		shapeInfo.add(MultiblockShapeInfo.builder()
				.aisle("#XEX#", "XXGXX", "#XXX#")
				.aisle("IXXXX", "i###X", "XXXXX")
				.aisle("XXMXX", "m#P#G", "XXGXX")
				.aisle("OXXXX", "o###X", "XXXXX")
				.aisle("#XHX#", "XXSXX", "#XXX#")
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
				.where('S', GATileEntities.LARGE_CENTRIFUGE, EnumFacing.SOUTH)
				.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
				.where('X', TileEntityLargeCentrifuge.casingState)
				.where('#', Blocks.AIR.getDefaultState())
				.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
				.where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.WEST)
				.where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
				.where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
				.build());

		shapeInfo.add(MultiblockShapeInfo.builder()
				.aisle("#XEX#", "XXGXX", "#XXX#")
				.aisle("IXXXX", "i###X", "XXXXX")
				.aisle("XXMXX", "G#P#m", "XXGXX")
				.aisle("OXXXX", "o###X", "XXXXX")
				.aisle("#XHX#", "XXSXX", "#XXX#")
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
				.where('S', GATileEntities.LARGE_CENTRIFUGE, EnumFacing.SOUTH)
				.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
				.where('X', TileEntityLargeCentrifuge.casingState)
				.where('#', Blocks.AIR.getDefaultState())
				.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
				.where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.EAST)
				.where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
				.where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
				.build());
		shapeInfo.add(MultiblockShapeInfo.builder()
				.aisle("#XEX#", "XXmXX", "#XXX#")
				.aisle("IXXXX", "i###X", "XXXXX")
				.aisle("XXMXX", "G#P#G", "XXGXX")
				.aisle("OXXXX", "o###X", "XXXXX")
				.aisle("#XHX#", "XXSXX", "#XXX#")
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
				.where('S', GATileEntities.LARGE_CENTRIFUGE, EnumFacing.SOUTH)
				.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
				.where('X', TileEntityLargeCentrifuge.casingState)
				.where('#', Blocks.AIR.getDefaultState())
				.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.WEST)
				.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
				.where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.NORTH)
				.where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
				.where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
				.build());

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{"This multiblock only forms with at least 16 tumbaga casings."};
	}
}
