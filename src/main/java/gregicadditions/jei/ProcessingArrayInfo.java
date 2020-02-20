package gregicadditions.jei;

import java.util.List;

import com.google.common.collect.Lists;

import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

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
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder().aisle("XXX", "XXX", "XXX").aisle("IXX", "X#X", "XXX").aisle("OXX", "XSX", "XXX").where('S', GATileEntities.PROCESSING_ARRAY, EnumFacing.SOUTH).where('X', MetaBlocks.METAL_CASING.getState(MetalCasingType.TUNGSTENSTEEL_ROBUST)).where('#', Blocks.AIR.getDefaultState()).where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST).where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST).build();

		return Lists.newArrayList(shapeInfo);
	}

	public IBlockState getCasingState() {
		return MetaBlocks.METAL_CASING.getState(MetalCasingType.TUNGSTENSTEEL_ROBUST);
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] { I18n.format("gregtech.multiblock.processing_array.description") };
	}

}
