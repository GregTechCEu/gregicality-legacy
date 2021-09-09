package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GAMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class PlasmaCondenserInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GAMetaTileEntities.PLASMA_CONDENSER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle( "#####", "#XXX#", "#XXX#", "#XXX#", "#####")
                .aisle("#XXX#", "iG#GX", "f#P#X", "XGpGX", "#XXX#")
                .aisle("#XXX#", "H#P#X", "SPPPX", "EpPpX", "#XXX#")
                .aisle("#XXX#", "IG#GX", "F#P#X", "XGpGX", "#XXX#")
                .aisle("#####", "#XXX#", "#XXX#", "#XXX#", "#####")
                .where('S', GAMetaTileEntities.PLASMA_CONDENSER, EnumFacing.WEST)
                .where('H', maintenanceIfEnabled(GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N)), EnumFacing.WEST)
                .where('X', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N))
                .where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('p', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_LUV))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UHV], EnumFacing.WEST)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.WEST)
                .where('i', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.WEST);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("gtadditions.multiblock.plasma_condenser.description")};
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent casingTooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 25).setStyle(new Style().setColor(TextFormatting.RED));

        this.addBlockTooltip(GAMetaBlocks.METAL_CASING_1.getItemVariant(MetalCasing1.CasingType.HASTELLOY_N), casingTooltip);
    }

    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }
}
