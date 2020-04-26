package gregicadditions.jei.multi.nuclear;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.nuclear.BoilingWaterReactor;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

import static gregtech.api.unification.material.Materials.Lead;

public class BoilingWaterReactorInfo extends MultiblockInfoPage {

	public final BoilingWaterReactor reactor;

	public BoilingWaterReactorInfo(BoilingWaterReactor boiler) {
		this.reactor = boiler;
	}

	@Override
	public MultiblockControllerBase getController() {
		return reactor;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
				.aisle("#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####")
				.aisle("###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###")
				.aisle("##CCCCCCC##", "###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###", "##CCCCCCC##")
				.aisle("#CCCCCCCCC#", "##CCCCCCC##", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "##CCCCCCC##", "#CCCCCCCCC#")
				.aisle("#CCCYYYCCC#", "##CCYYYCC##", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "##CC###CC##", "#CCC###CCC#")
				.aisle("CCCCYYYCCCC", "#CCCYRYCCC#", "##CCYRYCC##", "###CYRYC###", "###CYRYC###", "###CYRYC###", "###CYRYC###", "###CYRYC###", "##CCYYYCC##", "#CCC###CCC#", "CCCC###CCCC")
				.aisle("#CCCYYYCCC#", "##CCYYYCC##", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "##CC###CC##", "#CCC###CCC#")
				.aisle("#CCCCCCCCC#", "##CCCCCCC##", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCSCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "##CCCCCCC##", "#CCCCCCCCC#")
				.aisle("##CCCCCCC##", "###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###", "##CCCCCCC##")
				.aisle("###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###")
				.aisle("#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####")
				.where('S', reactor, EnumFacing.WEST)
				.where('R', reactor.rodType.casingState)
				.where('Y', GAMetaBlocks.getMetalCasingBlockState(Lead))
				.where('C', MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.LIGHT_CONCRETE, StoneBlock.ChiselingVariant.NORMAL))
				.where('#', Blocks.AIR.getDefaultState())
				.build();
		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.reactor.tooltip.1")};
	}

}
