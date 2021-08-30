package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeExtruder;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class LargeExtruderInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_EXTRUDER;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (int i = 2; i < 7; i++) {
			MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
					.aisle("XXXX", "XXXX", "XXX#");
			for(int j = 0; j < i; j++) {
				builder.aisle("IXXX", "XCPX", "OXX#");
			}
			builder.aisle("EHXX", "XSXX", "XXX#")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
					.where('S', GATileEntities.LARGE_EXTRUDER, EnumFacing.SOUTH)
					.where('H', maintenanceIfEnabled(METAL_CASING_1.getState(MetalCasing1.CasingType.INCONEL_625)), EnumFacing.SOUTH)
					.where('X', METAL_CASING_1.getState(MetalCasing1.CasingType.INCONEL_625))
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('P', GAMetaBlocks.PISTON_CASING.getDefaultState())
					.where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE));
			shapeInfo.add(builder.build());
		}

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_extruder.description")};
	}
}
