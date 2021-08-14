package gregicadditions.jei.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.CasingUtils;
import gregicadditions.machines.multi.simple.TileEntityLargeWiremill;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockTurbineCasing;
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

public class LargeWiremillInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_WIREMILL;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		List<MultiblockShapeInfo> shapeInfo = new ArrayList<>(); //todo make this face the player
		for (int i = 2; i < 7; i++) {
			MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
					.aisle("XXXXX", "XXXXX", "XXXXX");
			for(int j = 0; j < i; j++) {
				builder.aisle("IXXXX", "XMGMX", "OXXXX");
			}
			builder.aisle("XHX##", "XSX##", "EXX##")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
					.where('S', GATileEntities.LARGE_WIREMILL, EnumFacing.SOUTH)
					.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
					.where('X', TileEntityLargeWiremill.casingState)
					.where('#', Blocks.AIR.getDefaultState())
					.where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX))
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState());
			shapeInfo.add(builder.build());
		}

		return shapeInfo;
	}

	private static final ITextComponent componentCasingTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));

	@Override
	protected void generateBlockTooltips() {
		super.generateBlockTooltips();

		ITextComponent casingTooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 8).setStyle(new Style().setColor(TextFormatting.RED));

		ItemStack defaultCasingStack = METAL_CASING_1.getItemVariant(MetalCasing1.CasingType.MARAGING_STEEL_250);
		ItemStack casingStack = CasingUtils.getConfigCasingItemStack(GAConfig.multis.largeWiremill.casingMaterial, defaultCasingStack);

		this.addBlockTooltip(casingStack, casingTooltip);

		for (MotorCasing.CasingType casingType : MotorCasing.CasingType.values()) {
			this.addBlockTooltip(GAMetaBlocks.MOTOR_CASING.getItemVariant(casingType), componentCasingTooltip);
		}
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_wiremill.description")};
	}
}
