package gregicadditions.jei.multi.nuclear;

import com.google.common.collect.Lists;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;

import java.util.List;

public class BoilingWaterReactorInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.BOILING_WATER_REACTOR;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
				.aisle("S")
				.where('S', MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.DARK_CONCRETE, StoneBlock.ChiselingVariant.NORMAL ))
				.build();
		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.volcanus.description")};
	}

}
