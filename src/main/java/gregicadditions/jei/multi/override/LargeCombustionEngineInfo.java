package gregicadditions.jei.multi.override;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;


public class LargeCombustionEngineInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_COMBUSTION_ENGINE[0];
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
				.aisle("AAA", "ACA", "AAA")
				.aisle("HHH", "HGH", "HHH")
				.aisle("HHH", "FGH", "HHH")
				.aisle("HHH", "HEH", "HHH")
				.where('H', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE))
				.where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX))
				.where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING))
				.where('C', GATileEntities.LARGE_COMBUSTION_ENGINE[0], EnumFacing.NORTH)
				.where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.EV], EnumFacing.WEST)
				.where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[GAValues.EV], EnumFacing.SOUTH)
				.where('#', Blocks.AIR.getDefaultState())
				.build();
		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.diesel_engine.description")};
	}
}
