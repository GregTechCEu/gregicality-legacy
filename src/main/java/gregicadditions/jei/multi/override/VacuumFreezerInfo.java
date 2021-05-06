package gregicadditions.jei.multi.override;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
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

import java.util.List;


public class VacuumFreezerInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.VACUUM_FREEZER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("XXX", "BXH", "XXX")
                .aisle("XXX", "C#E", "XXX")
                .aisle("XXX", "IXF", "XXX")
                .where('C', GATileEntities.VACUUM_FREEZER, EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.EAST)
                .where('B', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                .where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.vacuum_freezer.description")};
    }

}
