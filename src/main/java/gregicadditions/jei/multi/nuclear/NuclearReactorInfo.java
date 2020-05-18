package gregicadditions.jei.multi.nuclear;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.nuclear.MetaTileEntityNuclearReactor;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.material.Materials.Lead;

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
                .aisle("YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .aisle("YYY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YYY")
                .aisle("YSY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .where('S', reactor, EnumFacing.SOUTH)
                .where('R', rodType.casingState)
                .where('Y', GAMetaBlocks.getMetalCasingBlockState(Lead))
                .where('C', MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.LIGHT_CONCRETE, StoneBlock.ChiselingVariant.NORMAL))
                .where('#', Blocks.AIR.getDefaultState())
                .build()).collect(Collectors.toList());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.reactor.description")};
    }

}
