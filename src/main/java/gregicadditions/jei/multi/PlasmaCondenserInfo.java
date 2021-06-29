package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class PlasmaCondenserInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.PLASMA_CONDENSER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("#####", "#XXX#", "#XXX#", "#XXX#", "#####")
                .aisle("#XXX#", "XG#GX", "X#P#X", "XGpGX", "#XXX#")
                .aisle("#XXX#", "X#P#X", "XPPPX", "XpPpX", "#XXX#")
                .aisle("#XXX#", "XG#GX", "X#P#X", "XGpGX", "#XXX#")
                .aisle("#####", "#IHi#", "#FSf#", "#XEX#", "#####")
                .where('S', GATileEntities.PLASMA_CONDENSER, EnumFacing.SOUTH)
                .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N))
                .where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('p', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_LUV))
                .where('E', GATileEntities.ENERGY_INPUT[0], EnumFacing.NORTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.WEST)
                .where('i', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.WEST);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }


    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }
}
