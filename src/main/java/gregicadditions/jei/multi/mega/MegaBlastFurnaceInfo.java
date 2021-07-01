package gregicadditions.jei.multi.mega;

import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.mega.MetaTileEntityMegaBlastFurnace;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import scala.tools.cmd.Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MegaBlastFurnaceInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.MEGA_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() { //TODO change pattern to have the controller face the viewer in jei
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
                continue;

            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder(RIGHT, FRONT, DOWN);

            builder.aisle("###############", "###############", "###############", "######TTT######", "####TTTTTTT####", "####TTTTTTT####", "###TTTTTTTTT###", "###TTTTmTTTT###", "###TTTTpTTTT###", "####TTTpTTT####", "####TTTpTTT####", "######TTT######", "###############", "###############", "###############");
            for (int i = 0; i < 6; i++)
                builder.aisle("###############", "###############", "###############", "###############", "#######f#######", "#####CCCCC#####", "#####C###C#####", "####fC###Cf####", "#####C###C#####", "#####CCCCC#####", "#######p#######", "###############", "###############", "###############", "###############");
            builder.aisle("###############", "###############", "###############", "#######T#######", "#######f#######", "#####CCCCC#####", "#####C###C#####", "###TfC###CfT###", "#####C###C#####", "#####CCCCC#####", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "###############")
                    .aisle("###############", "###############", "###############", "######TTT######", "####TTTTTTT####", "####TTTTTTT####", "###TTTTTTTTT###", "###TTTTTTTTT###", "###TTTTTTTTT###", "####TTTTTTT####", "####TTTTTTT####", "######TTT######", "###############", "#######p#######", "###############")
                    .aisle("###############", "#FFFFFFFFFFFFF#", "#FFFFFFFFFFFFF#", "#FF#########FF#", "#FF####T####FF#", "#FF##T###T##FF#", "#FF#########FF#", "#FF#T##P##T#FF#", "#FF#########FF#", "#FF##T###T##FF#", "#FF####T####FF#", "#FF#########FF#", "#FFFFFXXXFFFFF#", "#FFFFFXpXFFFFF#", "###############")
                    .aisle("#######p#######", "#F##ppppppp####", "##ppp#####ppp##", "##p#########p##", "#pp####T####pp#", "#pp##T###T###p#", "##pp#########p#", "##ppT##P##T##pp", "#############p#", "#pp##T###T###p#", "#pp####T####pp#", "##p#########p##", "##ppp#####ppp##", "####ppppppp####", "#######p#######")
                    .aisle("#####XXpXX#####", "#F#XpXXpXXpX###", "##pXpXXXXXpXp##", "#XXXXXXXXXXXXX#", "#ppXXXXXXXXXpp#", "oXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "GGGpXXXPXXXXXpp", "oXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "#ppXXXXXXXXXpp#", "#XXXXXXXXXXXXX#", "##pXpXXXXXpXp##", "###XpXXpXXpX###", "#####XXpXX#####")
                    .aisle("#####XXXXX#####", "#F#XXX#p#XXX###", "##XXp#X#X#pXX##", "#XXX##X#X##XXX#", "#Xp#X##X##X#pX#", "OX###X#X#X###XX", "XXXX##XXX##XXXX", "GGGpXXXPXXX##pX", "OXXX##XXX##XXXX", "XX###X#X#X###XX", "#Xp#X##X##X#pX#", "#XXX##X#X##XXX#", "##XXp#X#X#pXX##", "###XXX#p#XXX###", "#####XXXXX#####")
                    .aisle("#####XXGXX#####", "#F#XXX#R#XXX###", "##GXR#X#X#RXG##", "#XXX##X#X##XXX#", "#GR#X##X##X#RG#", "iX###X#X#X###XX", "HXXX##XXX##XXXX", "GGGpXXXPXXX##RG", "iXXX##XXX##XXXX", "XX###X#X#X###XX", "#GR#X##X##X#RG#", "#XXX##X#X##XXX#", "##GXR#X#X#RXG##", "###XXX#R#XXX###", "#####XXGXX#####")
                    .aisle("#####XXXXX#####", "#F#XXXBBBXXX###", "##XXBBXBXBBXX##", "#XXXBBXBXBBXXX#", "#XBBXBBXBBXBBX#", "IXBBBXBXBXBBBXX", "MXXXBBXXXBBXXBX", "SGGpXXXPXXXBBBX", "IXXXBBXXXBBXXBX", "XXBBBXBXBXBBBXX", "#XBBXBBXBBXBBX#", "#XXXBBXBXBBXXX#", "##XXBBXBXBBXX##", "###XXXBBBXXX###", "#####XXXXX#####")
                    .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.UV], EnumFacing.WEST)
                    .where('S', GATileEntities.MEGA_BLAST_FURNACE, EnumFacing.WEST)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.WEST)
                    .where('T', MetaTileEntityMegaBlastFurnace.secondaryCasingState)
                    .where('X', MetaTileEntityMegaBlastFurnace.casingState)
                    .where('f', MetaTileEntityMegaBlastFurnace.getFrameState())
                    .where('F', MetaTileEntityMegaBlastFurnace.getSecondaryFrameState())
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LuV], EnumFacing.WEST)
                    .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LuV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LuV], EnumFacing.WEST)
                    .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LuV], EnumFacing.WEST)
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .where('m', GATileEntities.MUFFLER_HATCH[2], EnumFacing.UP)
                    .where('B', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                    .where('R', MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX))
                    .where('g', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING));
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
        return 0.2f;
    }
}
