package gregicadditions.jei.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class HyperReactor1Info extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.HYPER_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
         builder.aisle("CCECC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CCCCC", "G#H#G", "GHHHG", "G#H#G", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CFSCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .where('S', GATileEntities.HYPER_REACTOR, EnumFacing.SOUTH)
                .where('C', METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA))
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[8], EnumFacing.NORTH)
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                .where('H', GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE))
                .where('#', Blocks.AIR.getDefaultState());

        return Collections.singletonList(builder.build());    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }
}
