package gregicadditions.jei.multi.advance;

import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
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

import java.util.Collections;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class LargeRocketEngineInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_ROCKET_ENGINE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("CMC", "CSC", "CCC");
        for (int num = 0; num < 8; num++) {
            builder.aisle("CCC", "C#F", "CAC");
        }
        builder.aisle("CCC", "CEC", "CCC")
                .where('S', GATileEntities.LARGE_ROCKET_ENGINE, EnumFacing.NORTH)
                .where('M', maintenanceIfEnabled(METAL_CASING_1.getState(MetalCasing1.CasingType.NITINOL_60)), EnumFacing.NORTH)
                .where('C', METAL_CASING_1.getState(MetalCasing1.CasingType.NITINOL_60))
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.EAST)
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[4], EnumFacing.SOUTH)
                .where('A', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING))
                .where('#', Blocks.AIR.getDefaultState());
        return Collections.singletonList(builder.build());

    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.1")};
    }

    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        for(int i = 0; i < GTValues.V.length; ++i) {
            this.addBlockTooltip(MetaTileEntities.FLUID_IMPORT_HATCH[i].getStackForm(), tooltip);
        }
    }

    private static final ITextComponent tooltip = new TextComponentTranslation("gtadditions.multiblock.preview.left_or_right").setStyle(new Style().setColor(TextFormatting.RED));
}
