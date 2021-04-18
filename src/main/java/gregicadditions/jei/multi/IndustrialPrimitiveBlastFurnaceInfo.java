package gregicadditions.jei.multi;

import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

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
            builder.aisle("YYY", "YOY", "YYY", "YYY");
            for (int num = 0; num < 1 + i; num++) {
                builder.aisle("YYY", "I#Y", "Y#Y", "Y#Y");
            }
            builder.aisle("YYY", "YCY", "YYY", "YYY")
                    .where('Y', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                    .where('C', GATileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE, EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[1], EnumFacing.EAST)
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
}
