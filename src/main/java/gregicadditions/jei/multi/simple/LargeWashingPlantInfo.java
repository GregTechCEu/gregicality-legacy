package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.CasingUtils;
import gregicadditions.machines.multi.simple.TileEntityLargeWashingPlant;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
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

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class LargeWashingPlantInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_WASHING_PLANT;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("XXXXX", "XXXXX", "XXXXX")
					.aisle("XXXXX", "XP#PX", "X###X")
					.aisle("XXXXX", "XP#PX", "X###X")
					.aisle("XXXXX", "XP#PX", "X###X")
					.aisle("XXXXX", "XP#PX", "X###X")
					.aisle("XXXXX", "XP#PX", "X###X")
					.aisle("IOMEX", "XHSXX", "XXXXX")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
					.where('S', GATileEntities.LARGE_WASHING_PLANT, EnumFacing.SOUTH)
					.where('X', METAL_CASING_1.getState(MetalCasing1.CasingType.GRISIUM))
					.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
					.where('#', Blocks.WATER.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
					.build());


		return Lists.newArrayList(shapeInfo);
	}

	private static final ITextComponent componentCasingTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));

	@Override
	protected void generateBlockTooltips() {
		super.generateBlockTooltips();

		ITextComponent casingTooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 25).setStyle(new Style().setColor(TextFormatting.RED));

		ItemStack casingStack = METAL_CASING_1.getItemVariant(MetalCasing1.CasingType.GRISIUM);

		this.addBlockTooltip(casingStack, casingTooltip);

		for (MotorCasing.CasingType casingType : MotorCasing.CasingType.values()) {
			this.addBlockTooltip(GAMetaBlocks.MOTOR_CASING.getItemVariant(casingType), componentCasingTooltip);
		}
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_washing_plant.description")};
	}

	@Override
	public float getDefaultZoom() {
		return 0.7f;
	}
}
