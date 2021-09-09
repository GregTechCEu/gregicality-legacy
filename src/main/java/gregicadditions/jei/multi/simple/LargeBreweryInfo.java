package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GAMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeBreweryInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GAMetaTileEntities.LARGE_BREWERY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XGX#", "#XGX#", "#XXX#", "#####")
                .aisle("XXXXX", "X###X", "X###X", "X###X", "XXXXX")
                .aisle("XXMXX", "X#p#X", "P#p#P", "X#p#X", "XXmXX")
                .aisle("EXXXX", "X###X", "X###X", "X###X", "XXXXX")
                .aisle("#IHi#", "#XSX#", "#XGX#", "#OXo#", "#####")
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('S', GAMetaTileEntities.LARGE_BREWERY, EnumFacing.SOUTH)
                .where('H', maintenanceIfEnabled(GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.GRISIUM)), EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.GRISIUM))
                .where('m', MetaTileEntities.MUFFLER_HATCH[0], EnumFacing.UP)
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
                .where('P', GAMetaBlocks.PUMP_CASING.getDefaultState())
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE));
        shapeInfo.add(builder.build());
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_brewery.description")};
    }
}
