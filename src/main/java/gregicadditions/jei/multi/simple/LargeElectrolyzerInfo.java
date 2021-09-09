package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GAMetaTileEntities;
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

public class LargeElectrolyzerInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GAMetaTileEntities.LARGE_ELECTROLYZER;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
					.aisle("XXCXX", "XXCXX", "XXCXX", "XX#XX");
			for(int j = 0; j < i; j++) {
				builder.aisle("IXCXX", "IP#MX", "XXCXX", "X###X");
			}
			builder.aisle("EXHXX", "XXSXX", "XXCXX", "XX#XX")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
					.where('S', GAMetaTileEntities.LARGE_ELECTROLYZER, EnumFacing.SOUTH)
					.where('H', maintenanceIfEnabled(METAL_CASING_1.getState(MetalCasing1.CasingType.TALONITE)), EnumFacing.SOUTH)
					.where('X', METAL_CASING_1.getState(MetalCasing1.CasingType.TALONITE))
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.EAST)
					.where('o', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.EAST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('P', GAMetaBlocks.PUMP_CASING.getDefaultState())
					.where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE));
			shapeInfo.add(builder.build());
		}
		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_electrolyzer.description")};
	}
}
