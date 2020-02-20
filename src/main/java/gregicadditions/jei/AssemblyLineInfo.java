package gregicadditions.jei;

import java.util.ArrayList;
import java.util.List;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class AssemblyLineInfo extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.ASSEMBLY_LINE;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		List<MultiblockShapeInfo> shapes = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
			builder.aisle("COC", "RTR", "GAG", "#Y#");
			for (int num = 0; num < 3 + i; num++) {
				if (num == 4 || num == 9) builder.aisle("FIf", "RTR", "GAG", "#Y#");
				else builder.aisle("CIC", "RTR", "GAG", "#Y#");
			}
			builder.aisle("CIC", "RTR", "GSG", "#Y#").where('S', GATileEntities.ASSEMBLY_LINE, EnumFacing.SOUTH).where('C', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)).where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST).where('f', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.EAST).where('O', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.DOWN).where('Y', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.UP).where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.DOWN).where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)).where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING)).where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)).where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING)).where('#', Blocks.AIR.getDefaultState());
			shapes.add(builder.build());
		}
		return shapes;
	}

	@Override
	public String[] getDescription() {
		return new String[] { I18n.format("gregtech.multiblock.assembly_line.description") };
	}

}