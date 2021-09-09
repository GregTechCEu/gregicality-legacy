package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.GAMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class LargeMaceratorInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GAMetaTileEntities.LARGE_MACERATOR;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("XXX", "XXX","XXX","XXX", "XXX", "XXX")
					.aisle("XXX", "XMX","X#X","XPX", "X#X", "XXX")
					.aisle("XSX", "XHX","XEX","XIX", "XOX", "XXX")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
					.where('S', GAMetaTileEntities.LARGE_MACERATOR, EnumFacing.SOUTH)
					.where('H', maintenanceIfEnabled(METAL_CASING_2.getState(MetalCasing2.CasingType.STELLITE)), EnumFacing.SOUTH)
					.where('X', METAL_CASING_2.getState(MetalCasing2.CasingType.STELLITE))
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('P', GAMetaBlocks.PISTON_CASING.getDefaultState())
					.build());


		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_macerator.description")};
	}

	@Override
	public float getDefaultZoom() {
		return 0.7f;
	}
}
