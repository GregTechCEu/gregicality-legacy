package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class ChemicalPlantInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.CHEMICAL_PLANT;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
		for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {

			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("X###X", "EXXXX", "X###X", "XXXXX", "X###X")
					.aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
					.aisle("X###X", "XPPPX", "XMMMX", "XPPPX", "X###X")
					.aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
					.aisle("H###X", "SXXIO", "X###X", "XXXXX", "X###X")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.NORTH)
					.where('S', GATileEntities.CHEMICAL_PLANT, EnumFacing.SOUTH)
					.where('H', maintenanceIfEnabled(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CHEMICALLY_INERT)), EnumFacing.SOUTH)
					.where('X', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CHEMICALLY_INERT))
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE))
					.where('C', MetaBlocks.WIRE_COIL.getState(coilType))
					.build());
		}

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{I18n.format("gtadditions.multiblock.advanced_chemical_reactor.description")};
	}
}
