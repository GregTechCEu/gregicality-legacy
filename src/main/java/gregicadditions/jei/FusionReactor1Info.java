package gregicadditions.jei;

import java.util.List;

import com.google.common.collect.Lists;

import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class FusionReactor1Info extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.FUSION_REACTOR[0];
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder().aisle("###############", "######NCN######", "###############").aisle("######DCD######", "####CCcccCC####", "######UCU######").aisle("####CC###CC####", "###sccNCNccs###", "####CC###CC####").aisle("###C#######C###", "##wcnC###Cnce##", "###C#######C###").aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("#D###########D#", "WcE#########WcE", "#U###########U#").aisle("#C###########C#", "CcC#########CcC", "#C###########C#").aisle("#D###########D#", "WcE#########WcE", "#U###########U#").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##").aisle("###C#######C###", "##wcsC###Csce##", "###C#######C###").aisle("####CC###CC####", "###nccSCSccn###", "####CC###CC####").aisle("######DCD######", "####CCcccCC####", "######UCU######").aisle("###############", "######NMN######", "###############").where('M', GATileEntities.FUSION_REACTOR[0], EnumFacing.SOUTH).where('C', MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.LuV)).where('c', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR)).where('W', MetaTileEntities.FLUID_EXPORT_HATCH[6], EnumFacing.WEST).where('E', MetaTileEntities.FLUID_EXPORT_HATCH[6], EnumFacing.EAST).where('S', MetaTileEntities.FLUID_EXPORT_HATCH[6], EnumFacing.SOUTH).where('N', MetaTileEntities.FLUID_EXPORT_HATCH[6], EnumFacing.NORTH).where('w', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.WEST).where('e', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.EAST).where('s', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.SOUTH).where('n', MetaTileEntities.ENERGY_INPUT_HATCH[6], EnumFacing.NORTH).where('U', MetaTileEntities.FLUID_IMPORT_HATCH[6], EnumFacing.UP).where('D', MetaTileEntities.FLUID_IMPORT_HATCH[6], EnumFacing.DOWN).where('#', Blocks.AIR.getDefaultState()).build();

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[] { I18n.format("gregtech.multiblock.fusion_reactor_mk1.description") };
	}

}