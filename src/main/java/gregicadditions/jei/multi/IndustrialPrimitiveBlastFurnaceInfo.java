package gregicadditions.jei.multi;

import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
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

public class IndustrialPrimitiveBlastFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapes = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            builder.aisle("YYY", "YCY", "YYY", "YYY");
            for (int num = 0; num < 1 + i; num++) {
                builder.aisle("YYY", "I#Y", "Y#Y", "Y#Y");
            }
            builder.aisle("YYY", "YOY", "YYY", "YYY")
                    .where('Y', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                    .where('C', GATileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE, EnumFacing.NORTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[1], EnumFacing.SOUTH)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[1], EnumFacing.WEST)
                    .where('#', Blocks.AIR.getDefaultState());
            shapes.add(builder.build());
        }
        return shapes;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.industrial_primitive_blast_furnace.description")};
    }

    @Override
    protected void generateBlockTooltips() {
        ItemStack inputStack = MetaTileEntities.ITEM_IMPORT_BUS[1].getStackForm();
        ItemStack outputStack = MetaTileEntities.ITEM_EXPORT_BUS[1].getStackForm();

        ITextComponent inputTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only", inputStack.getDisplayName()).setStyle(new Style().setColor(TextFormatting.RED));
        ITextComponent outputTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only", outputStack.getDisplayName()).setStyle(new Style().setColor(TextFormatting.RED));

        for(int i = 0; i < GTValues.V.length; ++i) {
            this.addBlockTooltip(MetaTileEntities.ITEM_IMPORT_BUS[i].getStackForm(), inputTooltip);
            this.addBlockTooltip(MetaTileEntities.ITEM_IMPORT_BUS[i].getStackForm(), inputTooltipLocation);
            this.addBlockTooltip(MetaTileEntities.ITEM_EXPORT_BUS[i].getStackForm(), outputTooltip);
            this.addBlockTooltip(MetaTileEntities.ITEM_EXPORT_BUS[i].getStackForm(), outputTooltipLocation);
        }
    }

    private static final ITextComponent inputTooltipLocation = new TextComponentTranslation("gtadditions.multiblock.preview.left_or_right").setStyle(new Style().setColor(TextFormatting.RED));
    private static final ITextComponent outputTooltipLocation = new TextComponentTranslation("gtadditions.multiblock.preview.rear_output").setStyle(new Style().setColor(TextFormatting.RED));
}
