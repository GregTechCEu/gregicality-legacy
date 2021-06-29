package gregicadditions.jei.multi.mega;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MegaDistillationTowerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.MEGA_DISTILLATION_TOWER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() { //TODO change pattern to have the controller face the viewer in jei
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder(RIGHT, FRONT, UP);
            builder.aisle("#XXX#", "XXXXX", "XXXXX", "OXXXH", "#FSM#");
            for (int i = 0; i < 11; i++) {
                builder.aisle("#XXX#", "XCpCX", "XpPpX", "XCpCX", "#XEX#");
            }
            builder.aisle("#XXX#", "XXXXX", "XXXXX", "XXXXX", "#XEX#")
                    .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.IV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.MEGA_DISTILLATION_TOWER, EnumFacing.SOUTH)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('C', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.NICHROME))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV));
            shapeInfo.add(builder.build());

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }

    @Override
    public float getDefaultZoom() {
        return 0.4f;
    }
}
