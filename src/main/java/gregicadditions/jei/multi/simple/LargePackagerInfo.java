package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargePackager;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class LargePackagerInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_PACKAGER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                    .aisle("XXX", "XXX", "XXX");
            for(int j = 0; j < i; j++) {
                builder.aisle("IXO", "XCX", "XRX");
            }
            builder.aisle("XHX", "XSX", "XEX")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.LARGE_PACKAGER, EnumFacing.SOUTH)
                    .where('H', maintenanceIfEnabled(METAL_CASING_1.getState(MetalCasing1.CasingType.HG_1223)), EnumFacing.SOUTH)
                    .where('X', METAL_CASING_1.getState(MetalCasing1.CasingType.HG_1223))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.EAST)
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState());
            shapeInfo.add(builder.build());
        }

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_packager.description")};
    }
}