package gregicadditions.jei.multi.nuclear;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.impl.MetaTileEntityRotorHolderForNuclearCoolant;
import gregicadditions.machines.multi.nuclear.MetaTileEntityHotCoolantTurbine;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.BlockInfo;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class HotCoolantTurbineInfo extends MultiblockInfoPage {

    public final MetaTileEntityHotCoolantTurbine turbine;

    public HotCoolantTurbineInfo(MetaTileEntityHotCoolantTurbine turbine) {
        this.turbine = turbine;
    }

    @Override
    public MultiblockControllerBase getController() {
        return turbine;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MetaTileEntityHolder holder = new MetaTileEntityHolder();
        holder.setMetaTileEntity(GATileEntities.ROTOR_HOLDER[2]);
        holder.getMetaTileEntity().setFrontFacing(EnumFacing.WEST);
        ItemStack rotorStack = MetaItems.TURBINE_ROTOR.getStackForm();
        //noinspection ConstantConditions
        TurbineRotorBehavior.getInstanceFor(rotorStack).setPartMaterial(rotorStack, Materials.Darmstadtium);
        ((MetaTileEntityRotorHolderForNuclearCoolant) holder.getMetaTileEntity()).getRotorInventory().setStackInSlot(0, rotorStack);
        MultiblockShapeInfo.Builder shapeInfo = MultiblockShapeInfo.builder()
                .aisle("CCCC", "CIOC", "CCCC")
                .aisle("CCCC", "R##D", "CCCC")
                .aisle("CCCC", "CSMC", "CCCC")
                .where('S', turbine, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('C', turbine.turbineType.casingState)
                .where('R', new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), holder))
                .where('D', MetaTileEntities.ENERGY_OUTPUT_HATCH[GAValues.EV], EnumFacing.EAST)
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.NORTH);
        if (turbine.turbineType.hasOutputHatch) {
            shapeInfo.where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.EV], EnumFacing.NORTH);
        } else {
            shapeInfo.where('O', turbine.turbineType.casingState);
        }
        return Lists.newArrayList(shapeInfo.build());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.large_turbine.description")};
    }

}