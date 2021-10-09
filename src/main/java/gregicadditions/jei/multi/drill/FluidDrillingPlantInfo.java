package gregicadditions.jei.multi.drill;

import com.google.common.collect.Lists;
import gregicadditions.machines.multi.drill.MetaTileEntityFluidDrillingPlant;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.util.BlockInfo;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FluidDrillingPlantInfo extends MultiblockInfoPage {

    private final MetaTileEntityFluidDrillingPlant drillingPlant;
    private final int hatchTier;

    public FluidDrillingPlantInfo(MetaTileEntityFluidDrillingPlant drillingPlant) {
        this.drillingPlant = drillingPlant;
        this.hatchTier = drillingPlant.getRigTier();
    }

    @Override
    public MultiblockControllerBase getController() {
        return drillingPlant;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo multiblockShapeInfo = MultiblockShapeInfo.builder()
                .aisle("OCC", "#F#", "#F#", "#F#", "###", "###", "###")
                .aisle("SCE", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                .aisle("CCC", "#F#", "#F#", "#F#", "###", "###", "###")
                .where('S', getController(), EnumFacing.WEST)
                .where('C', drillingPlant.getCasingState())
                .where('F', drillingPlant.getFrameState())
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[hatchTier], EnumFacing.WEST)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[hatchTier], EnumFacing.EAST)
                .where('#', BlockInfo.EMPTY)
                .build();

        return Lists.newArrayList(multiblockShapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.drilling_rig.tooltip.1")};
    }
}
