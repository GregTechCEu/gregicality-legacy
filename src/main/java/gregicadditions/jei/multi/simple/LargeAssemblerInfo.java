package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeAssemblerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_ASSEMBLER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
            shapeInfo.add(MultiblockShapeInfo.builder()
                    .aisle("XXX", "XXX", "XXX", "XXX")
                    .aisle("XXX", "XCX", "XRX", "XXX")
                    .aisle("XXX", "XPX", "XPX", "XXX")
                    .aisle("XXX", "XCX", "XRX", "XXX")
                    .aisle("OEX", "ISX", "XFX", "###")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.LuV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.LARGE_ASSEMBLER, EnumFacing.SOUTH)
                    .where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .build());

            shapeInfo.add(MultiblockShapeInfo.builder()
                    .aisle("XXXX", "XXXX", "XXXX", "XXXX")
                    .aisle("XXXX", "XCKX", "XRRX", "XXXX")
                    .aisle("XXXX", "XPPX", "XPCX", "XXGX")
                    .aisle("XXXX", "XCKX", "XR#X", "XXGX")
                    .aisle("OEXX", "ISGX", "XFGX", "####")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.LuV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.LARGE_ASSEMBLER, EnumFacing.SOUTH)
                    .where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('K', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .build());

            shapeInfo.add(MultiblockShapeInfo.builder()
                    .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .aisle("XXXXX", "XCKKX", "XRRRX", "XXXXX")
                    .aisle("XXXXX", "XPPPX", "XPCCX", "XXGGX")
                    .aisle("XXXXX", "XCKKX", "XR##X", "XXGGX")
                    .aisle("OEXXX", "ISGGX", "XFGGX", "#####")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.LuV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.LARGE_ASSEMBLER, EnumFacing.SOUTH)
                    .where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('K', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .build());

            shapeInfo.add(MultiblockShapeInfo.builder()
                    .aisle("XXXXXXXXXXXX", "XXXXXXXXXXXX", "XXXXXXXXXXXX", "XXXXXXXXXXXX")
                    .aisle("XXXXXXXXXXXX", "XCKKKKKKKKKX", "XRRRRRRRRRRX", "XXXXXXXXXXXX")
                    .aisle("XXXXXXXXXXXX", "XPPPPPPPPPPX", "XPCCCCCCCCCX", "XXGGGGGGGGGX")
                    .aisle("XXXXXXXXXXXX", "XCKKKKKKKKKX", "XR#########X", "XXGGGGGGGGGX")
                    .aisle("OEXXXXXXXXXX", "ISGGGGGGGGGX", "XFGGGGGGGGGX", "############")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.LuV], EnumFacing.SOUTH)
                    .where('S', GATileEntities.LARGE_ASSEMBLER, EnumFacing.SOUTH)
                    .where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.LARGE_ASSEMBLER))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.SOUTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                    .where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
                    .where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('K', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .build());

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }

    @Override
    public float getDefaultZoom() {
        return super.getDefaultZoom();
    }
}
