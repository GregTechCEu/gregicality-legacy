package gregicadditions.jei;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class AssemblyLineInfo extends MultiblockInfoPage {

	private static final ITextComponent defaultText = new TextComponentTranslation("gregtech.multiblock.preview.any_hatch").setStyle(new Style().setColor(TextFormatting.GREEN));

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
			builder.aisle("CIC", "RTR", "GSG", "#Y#")
					.where('S', GATileEntities.ASSEMBLY_LINE, EnumFacing.SOUTH)
					.where('C', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
					.where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
					.where('f', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.EAST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.DOWN)
					.where('Y', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.UP)
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.DOWN)
					.where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
					.where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING))
					.where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
					.where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.ASSEMBLY_LINE_CASING))
					.where('#', Blocks.AIR.getDefaultState());
			shapes.add(builder.build());
		}
		return shapes;
	}

	@Override
	public String[] getDescription() {
		return new String[] { I18n.format("gregtech.multiblock.assembly_line.description") };
	}

	@Override
	protected void generateBlockTooltips() {

		ItemStack itemStack = MetaTileEntities.ITEM_IMPORT_BUS[0].getStackForm();

		ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.only", itemStack.getDisplayName()).setStyle(new Style().setColor(TextFormatting.RED));
		for(int i = 0; i < GTValues.V.length; ++i) {
			this.addBlockTooltip(MetaTileEntities.ITEM_EXPORT_BUS[i].getStackForm(), defaultText);
			this.addBlockTooltip(MetaTileEntities.ITEM_IMPORT_BUS[i].getStackForm(), tooltip);
			this.addBlockTooltip(MetaTileEntities.FLUID_EXPORT_HATCH[i].getStackForm(), defaultText);
			this.addBlockTooltip(MetaTileEntities.FLUID_IMPORT_HATCH[i].getStackForm(), defaultText);
		}
	}
}