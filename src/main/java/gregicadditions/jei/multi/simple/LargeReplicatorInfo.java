package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.uumatter.TileEntityLargeReplicator;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockWireCoil;
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

import java.util.ArrayList;
import java.util.List;

public class LargeReplicatorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_REPLICATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("#####XXEXX#####", "#####eXEXe#####", "#######X#######")
                .aisle("###XXXXXXXXX###", "###XXCCCCCXX###", "#####XPXPX#####")
                .aisle("##XXXXXEXXXXX##", "##XCCCXSHCCCX##", "###XXF#s#FXX###")
                .aisle("#XXXXX###XXXXX#", "#XCCXX###XXCCX#", "##XF#######FX##")
                .aisle("#XXX#######XXX#", "#XCX#######XCX#", "##X#########X##")
                .aisle("iXXX#######XXXI", "oCCX#######XCCO", "#XF#########FX#")
                .aisle("iXX#########XXI", "oCX#########XCO", "#P###########P#")
                .aisle("EXE#########EXE", "ECE#########ECE", "XXs#########sXX")
                .aisle("iXX#########XXI", "oCX#########XCO", "#P###########P#")
                .aisle("iXXX#######XXXI", "oCCX#######XCCO", "#XF#########FX#")
                .aisle("#XXX#######XXX#", "#XCX#######XCX#", "##X#########X##")
                .aisle("#XXXXX###XXXXX#", "#XCCXX###XXCCX#", "##XF#######FX##")
                .aisle("##XXXXXEXXXXX##", "##XCCCXEXCCCX##", "###XXF#s#FXX###")
                .aisle("###XXXXXXXXX###", "###XXCCCCCXX###", "#####XPXPX#####")
                .aisle("#####XXEXX#####", "#####XXEXX#####", "#######X#######")
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.UV], EnumFacing.NORTH)
                .where('S', GATileEntities.LARGE_REPLICATOR, EnumFacing.SOUTH)
                .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('X', TileEntityLargeReplicator.casingState)
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.EAST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.EAST)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.WEST)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.WEST)
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getDefaultState())
                .where('P', GAMetaBlocks.PUMP_CASING.getDefaultState())
                .where('s', GAMetaBlocks.SENSOR_CASING.getDefaultState())
                .where('E', GAMetaBlocks.EMITTER_CASING.getDefaultState())
                .where('C', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR));
        shapeInfo.add(builder.build());
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public float getDefaultZoom() {
        return 0.4f;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_replicator.description")};
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        for (FieldGenCasing.CasingType casingType : FieldGenCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.FIELD_GEN_CASING.getItemVariant(casingType), tieredCasingTooltip);
        }

        for (PumpCasing.CasingType casingType : PumpCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.PUMP_CASING.getItemVariant(casingType), tieredCasingTooltip);
        }

        for (SensorCasing.CasingType casingType : SensorCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.SENSOR_CASING.getItemVariant(casingType), tieredCasingTooltip);
        }

        for (EmitterCasing.CasingType casingType : EmitterCasing.CasingType.values()) {
            this.addBlockTooltip(GAMetaBlocks.EMITTER_CASING.getItemVariant(casingType), tieredCasingTooltip);
        }
    }

    private static final ITextComponent tieredCasingTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));
}
