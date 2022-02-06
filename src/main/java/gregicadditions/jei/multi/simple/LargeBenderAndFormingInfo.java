package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeBenderAndForming;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class LargeBenderAndFormingInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_BENDER_AND_FORMING;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder;
        for (int i = 2; i < 3; i++) {
            builder = MultiblockShapeInfo.builder()
                    .aisle("XXXX", "XXXX", "XXIX");
            for (int j = 0; j < i; j++) {
                builder.aisle("iXXX", "XPMX", "XXIO");
            }
            builder.aisle("EXXX", "XSHX", "XXIX")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.NORTH)
                    .where('S', getController(), EnumFacing.SOUTH)
                    .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                    .where('X', TileEntityLargeBenderAndForming.casingState)
                    .where('i', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.EAST)
                    .where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
                    .where('P', GAMetaBlocks.PISTON_CASING.getDefaultState())
                    .where('I', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE));
            shapeInfo.add(builder.build());
        }

        return Lists.newArrayList(shapeInfo);
    }

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.large_bender_and_forming.description")};
	}

    private static final ITextComponent componentTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        for (MotorCasing.CasingType casingType : MotorCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.MOTOR_CASING.getItemVariant(casingType), componentTooltip);
        }

        for (PistonCasing.CasingType casingType : PistonCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.PISTON_CASING.getItemVariant(casingType), componentTooltip);
        }
    }
}
