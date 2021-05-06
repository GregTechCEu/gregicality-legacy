package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class ProcessingArrayInfo extends MultiblockInfoPage {

	public ProcessingArrayInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.PROCESSING_ARRAY;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
				.aisle("EXX", "XXX", "XXX")
				.aisle("IXX", "S#X", "XXX")
				.aisle("OXX", "XXX", "XXX")
				.where('S', GATileEntities.PROCESSING_ARRAY, EnumFacing.WEST)
				.where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST))
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.MV], EnumFacing.WEST)
				.where('#', Blocks.AIR.getDefaultState())
				.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
				.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST).build();

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] { I18n.format("gregtech.multiblock.processing_array.description",
				GAConfig.multis.processingArray.processingArrayMachineLimit) };
	}

}
