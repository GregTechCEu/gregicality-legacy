package gregicadditions.jei.multi.mega;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MegaVacuumFreezerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.MEGA_VACUUM_FREEZER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() { //TODO change pattern to have the controller face the viewer in jei, fix integral frameworks per page
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            builder.aisle("#HXMIO#", "#XXSfE#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#")
                    .aisle("XXXXXXX", "XPFFFPX", "XPFFFPX", "XPPPPPX", "XPFFFPX", "XPFFFPX", "XXXXXXX");
            for (int i = 0; i < 3; i++) {
                builder.aisle("XXXXXXX", "XFAAAFX", "XFAAAFX", "XPGGGPX", "XFAAAFX", "XFAAAFX", "XXXXXXX");
            }
            builder.aisle("XXXXXXX", "XPFFFPX", "XPFFFPX", "XPPPPPX", "XPFFFPX", "XPFFFPX", "XXXXXXX")
                    .aisle("#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#")
                    .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.IV], EnumFacing.NORTH)
                    .where('S', GATileEntities.MEGA_VACUUM_FREEZER, EnumFacing.NORTH)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('f', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('F', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                    .where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING));
            shapeInfo.add(builder.build());
        }
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
