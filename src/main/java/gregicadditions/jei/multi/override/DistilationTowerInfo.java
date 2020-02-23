package gregicadditions.jei.multi.override;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class DistilationTowerInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.DISTILLATION_TOWER;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
				.aisle("EXX", "XXX", "XXX", "XXX", "XXX", "XXX")
				.aisle("SFX", "X#X", "X#X", "X#X", "X#X", "XXX")
				.aisle("IXX", "HXX", "HXX", "HXX", "HXX", "HXX")
				.where('#', Blocks.AIR.getDefaultState())
				.where('X', GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel).getDefaultState())
				.where('S', GATileEntities.DISTILLATION_TOWER, EnumFacing.WEST)
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV], EnumFacing.WEST)
				.where('I', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.EV], EnumFacing.WEST)
				.where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.EV], EnumFacing.DOWN)
				.where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.EV], EnumFacing.WEST)
				.build();
		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gregtech.multiblock.distillation_tower.description")};
	}
}
