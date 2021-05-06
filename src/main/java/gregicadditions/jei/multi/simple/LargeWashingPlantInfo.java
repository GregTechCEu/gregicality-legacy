package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeWashingPlant;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeWashingPlantInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_WASHING_PLANT;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("XXXXX", "XXXXX", "XXXXX")
					.aisle("XXXXX", "X###X", "X###X")
					.aisle("XXXXX", "X###X", "X###X")
					.aisle("XXXXX", "X###X", "X###X")
					.aisle("XXXXX", "X###X", "X###X")
					.aisle("XXXXX", "X###X", "X###X")
					.aisle("IOMEX", "XXSXX", "XXXXX")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.WEST)
					.where('S', GATileEntities.LARGE_WASHING_PLANT, EnumFacing.SOUTH)
					.where('X', TileEntityLargeWashingPlant.casingState)
					.where('#', Blocks.WATER.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())

					.build());


		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{"Temporary Placeholder"};
	}
}
