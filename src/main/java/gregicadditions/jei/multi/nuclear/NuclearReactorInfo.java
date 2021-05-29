package gregicadditions.jei.multi.nuclear;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NuclearReactorInfo extends MultiblockInfoPage {

    public final MetaTileEntityNuclearReactor reactor;

    public NuclearReactorInfo(MetaTileEntityNuclearReactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public MultiblockControllerBase getController() {
        return reactor;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Arrays.stream(MetaTileEntityNuclearReactor.RodType.values()).map(rodType -> MultiblockShapeInfo.builder()
                .aisle("MEY", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "YYY")
                .aisle("YYY", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "YYY")
                .aisle("ISO", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "FYG")
                .where('S', reactor, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                .where('R', rodType.casingState)
                .where('Y', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CLADDED_REACTOR_CASING))
                .where('Z', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CLADDED_REACTOR_CASING))
                .where('X', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.SOUTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.SOUTH)
                .where('G', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.NORTH)
                .where('#', Blocks.AIR.getDefaultState())
                .build()).collect(Collectors.toList());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.reactor.description")};
    }

}
