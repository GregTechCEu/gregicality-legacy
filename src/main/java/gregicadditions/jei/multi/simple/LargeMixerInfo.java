package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeMixer;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeMixerInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_MIXER;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (int i = 2; i < 5; i++) {
			MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
					.aisle("#XXX#", "#XXX#", "#XXX#");
			for (int j = 0; j < i; j++) {
					builder.aisle("IXXXi", "I#M#i", "#XXX#");
			}
			builder.aisle("IXXXi", "I#Y#i", "#XXX#");
			builder.aisle("#XHX#", "#OSo#", "#XEX#")
					.where('S', GATileEntities.LARGE_MIXER, EnumFacing.SOUTH)
					.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.EAST)
					.where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.EAST)
					.where('X', TileEntityLargeMixer.casingState)
					.where('Y', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING))
					.where('#', Blocks.AIR.getDefaultState())
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState());
			shapeInfo.add(builder.build());
		}
		return shapeInfo;
	}

	@Override
	public String[] getDescription() {
		return new String[]{"Temporary Placeholder"};
	}
}
