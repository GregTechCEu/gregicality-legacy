package gregicadditions.coremod.hooks;

import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Rotation;
import gregicadditions.covers.CoverDigitalInterface;
import gregicadditions.utils.BlockPatternChecker;
import gregtech.api.capability.impl.EnergyContainerBatteryBuffer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.items.behaviors.CoverPlaceBehavior;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityEnergyHatch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;

@SuppressWarnings("unused")
public class GregTechCEHooks {

    public static void updateCoverDigitalInterface(MetaTileEntity metaTileEntity, long energyAdded) {
        if (metaTileEntity != null ) {
            for (EnumFacing side : EnumFacing.VALUES) {
                CoverBehavior cover = metaTileEntity.getCoverAtSide(side);
                if (cover instanceof CoverDigitalInterface) {
                    ((CoverDigitalInterface) cover).setEnergyChanged(energyAdded);
                }
            }
            if (metaTileEntity instanceof MetaTileEntityEnergyHatch) {
                updateCoverDigitalInterface(((MetaTileEntityEnergyHatch) metaTileEntity).getController(), energyAdded);
            }
        }
    }

    //origin: gregtech.api.capability.impl.EnergyContainerHandler.setEnergyStored()
    public static void setEnergyStored(EnergyContainerHandler energyContainerHandler, long energyStored) {
        if (energyStored - energyContainerHandler.getEnergyStored() != 0) {
            updateCoverDigitalInterface(energyContainerHandler.getMetaTileEntity(), energyStored - energyContainerHandler.getEnergyStored());
        }
    }

    //origin: gregtech/api/capability/impl/EnergyContainerBatteryBuffer.changeEnergy() (though gtce not gonna use it. might be called, so hacking it too)
    public static void changeEnergy(EnergyContainerBatteryBuffer batteryBuffer, long energyAdded) {
        if (energyAdded != 0) {
            updateCoverDigitalInterface(batteryBuffer.getMetaTileEntity(), energyAdded);
        }
    }

    //origin: gregtech/api/capability/impl/EnergyContainerBatteryBuffer.acceptEnergyFromNetwork()
    public static void acceptEnergyFromNetwork(EnergyContainerBatteryBuffer batteryBuffer, long V, long amperageUsed) {
        if (amperageUsed != 0) {
            updateCoverDigitalInterface(batteryBuffer.getMetaTileEntity(), V * amperageUsed);
        }
    }

    //origin: gregtech/api/capability/impl/EnergyContainerBatteryBuffer.update()
    public static void update(EnergyContainerBatteryBuffer batteryBuffer, long V, long amperageUsed) {
        if (amperageUsed != 0) {
            updateCoverDigitalInterface(batteryBuffer.getMetaTileEntity(), -V * amperageUsed);
        }
    }

    //origin: gregtech/api/cover/ICoverable.canPlaceCoverOnSide()
    public static boolean canPlaceCoverOnSide(ICoverable coverable, EnumFacing side, CoverPlaceBehavior coverPlaceBehavior) {
        if (coverPlaceBehavior.coverDefinition.getCoverId().getPath().equals(CoverDigitalInterface.path)) {
            return true;
        }
        return coverable.canPlaceCoverOnSide(side);
    }

    //origin: gregtech/api/metatileentity/MetaTileEntity.canPlaceCoverOnSide()
    public static boolean canPlaceCoverOnSide2(MetaTileEntity coverable, EnumFacing side, CoverBehavior coverBehavior) {
        if (coverBehavior instanceof CoverDigitalInterface) {
            return true;
        }
        return coverable.canPlaceCoverOnSide(side);
    }

    //origin: gregtech.api.metatileentity.MetaTileEntity.writeInitialSyncData(PacketBuffer buf)
    public static void writeSpinBuf(PacketBuffer buf, EnumFacing spin) {
        buf.writeByte(spin == null? EnumFacing.NORTH.getIndex() : spin.getIndex());
    }

    //origin: gregtech.api.metatileentity.MetaTileEntity.receiveInitialSyncData(PacketBuffer buf)
    public static EnumFacing readSpinBuf(PacketBuffer buf) {
        return EnumFacing.VALUES[buf.readByte()];
    }

    //origin: gregtech.api.metatileentity.MetaTileEntity.writeToNBT(NBTTagCompound data)
    public static NBTTagCompound writeSpinNBT(NBTTagCompound data, EnumFacing spin) {
        data.setByte("sPin", (byte) (spin == null? EnumFacing.NORTH.getIndex() : spin.getIndex()));
        return data;
    }

    //origin: gregtech.api.metatileentity.MetaTileEntity.readFromNBT(NBTTagCompound data)
    public static EnumFacing readSpinNBT(NBTTagCompound data) {
        return data.hasKey("sPin") ? EnumFacing.VALUES[data.getByte("sPin")]:EnumFacing.NORTH;
    }

    //origin: gregtech.api.metatileentity.multiblock.MultiblockControllerBase.renderMetaTileEntity()
    public static void renderMetaTileEntity(MultiblockControllerBase controllerBase, Matrix4 translation) {
        EnumFacing facing = controllerBase.getFrontFacing();
        EnumFacing spin = BlockPatternChecker.getSpin(controllerBase);
        double degree = Math.PI/2 * (spin == EnumFacing.EAST? -1: spin == EnumFacing.SOUTH? 2: spin == EnumFacing.WEST? 1:0);
        Rotation rotation = new Rotation(degree, facing.getXOffset(), facing.getYOffset(), facing.getZOffset());
        translation.translate(0.5 , 0.5, 0.5);
        if(facing == EnumFacing.DOWN && spin.getAxis() == EnumFacing.Axis.Z) {
            translation.apply(new Rotation(Math.PI, 0, 1, 0));
        }
        translation.apply(rotation);
        translation.scale(1.0000f);
        translation.translate(-0.5 , -0.5, -0.5);
    }

    //origin: gregtech.api.metatileentity.MetaTileEntity.receiveCustomData(int dataId, PacketBuffer buf)
    public final static int SPIN_ID = -67;
    public static void receiveCustomData(MetaTileEntity mte, int dataId, PacketBuffer buf) {
        if (SPIN_ID == dataId) {
            BlockPatternChecker.setSpin(mte, EnumFacing.VALUES[buf.readByte()]);
        }
    }
}
