package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.ConveyorCasing;
import gregicadditions.item.components.RobotArmCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class LargeAssemblerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_ASSEMBLER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                    .aisle("EOXX", "OXXX", "IXXX", "FXXX")
                    .aisle("MXXX", "SCRX", "XPPX", "XXXX");

            for (int j = 0; j < 3*i; j++) {
                builder.aisle("XXXX", "RCPX", "G#PX", "GGGX");
            }
            shapeInfo.add(builder.aisle("XXXX", "XXXX", "XXXX", "XXXX")
                    .where('S', GATileEntities.LARGE_ASSEMBLER, EnumFacing.WEST)
                    .where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.WEST)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[6], EnumFacing.WEST)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[6], EnumFacing.WEST)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[6], EnumFacing.WEST)
                    .build());
        }

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_assembler.description")};
    }

    private static final ITextComponent componentCasingTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent casingTooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 25).setStyle(new Style().setColor(TextFormatting.RED));

        this.addBlockTooltip(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER), casingTooltip);

        for (RobotArmCasing.CasingType casingType : RobotArmCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.ROBOT_ARM_CASING.getItemVariant(casingType), componentCasingTooltip);
        }
        for (ConveyorCasing.CasingType casingType : ConveyorCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.CONVEYOR_CASING.getItemVariant(casingType), componentCasingTooltip);
        }
    }

    @Override
    public float getDefaultZoom() {
        return 0.9f;
    }
}
