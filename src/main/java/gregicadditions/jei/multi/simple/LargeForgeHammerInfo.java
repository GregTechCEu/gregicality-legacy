package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.GAMetaTileEntities;
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

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class LargeForgeHammerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GAMetaTileEntities.LARGE_FORGE_HAMMER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (int i = 0; i < 1; i++) { //TODO: set 1 to 5 once GTCE formation logic is well-done enough to reinstate the extendable LFH
            MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder();
            builder.aisle("OXS", "E#M", "IpX", "FXX");
            for (int j = 0; j < i; j++) {
                builder.aisle("OXX", "X#X", "IpX", "XXX");
            }
            builder.where('S', GAMetaTileEntities.LARGE_FORGE_HAMMER, EnumFacing.NORTH)
                    .where('X', METAL_CASING_2.getState(MetalCasing2.CasingType.IRON))
                    .where('M', maintenanceIfEnabled(METAL_CASING_2.getState(MetalCasing2.CasingType.IRON)), EnumFacing.NORTH)
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.EV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
                    .where('p', GAMetaBlocks.PISTON_CASING.getDefaultState());
            shapeInfo.add(builder.build());
        }
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.large_forge_hammer.description")};
    }
}
