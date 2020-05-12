package gregicadditions.item.behavior;

import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.metatileentities.storage.MetaTileEntityTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;


public class HandPumpBehavior implements IItemBehaviour {

    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (!world.isRemote && !world.isAirBlock(pos)) {
            ItemStack stack = player.getHeldItem(hand);
            IFluidHandlerItem fluidHandlerItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            int capacity = fluidHandlerItem.getTankProperties()[0].getCapacity();
            FluidStack content = fluidHandlerItem.getTankProperties()[0].getContents();
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity == null) {
                return EnumActionResult.PASS;
            }
            if (tileEntity instanceof MetaTileEntityHolder && !player.isSneaking()) {
                MetaTileEntity metaTileEntity = ObfuscationReflectionHelper.getPrivateValue(MetaTileEntityHolder.class, (MetaTileEntityHolder) tileEntity, "metaTileEntity");
                if (metaTileEntity instanceof MetaTileEntityTank) {
                    drainFromCapabilities(tileEntity, player, fluidHandlerItem, capacity, content);
                } else {
                    FluidStack fluidStack;
                    if (content == null) {
                        fluidStack = metaTileEntity.getExportFluids().drain(64000, true);
                        if (fluidStack == null) {
                            fluidStack = metaTileEntity.getImportFluids().drain(64000, true);
                        }
                    } else {
                        FluidStack toDrain = content.copy();
                        toDrain.amount = capacity - toDrain.amount;
                        fluidStack = metaTileEntity.getExportFluids().drain(toDrain, true);
                        if (fluidStack == null) {
                            fluidStack = metaTileEntity.getImportFluids().drain(toDrain, true);
                        }
                    }
                    fluidHandlerItem.fill(fluidStack, true);
                }
                return EnumActionResult.SUCCESS;
            } else if (tileEntity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
                drainFromCapabilities(tileEntity, player, fluidHandlerItem, capacity, content);
                return EnumActionResult.SUCCESS;
            }

        }
        return EnumActionResult.PASS;
    }

    public void drainFromCapabilities(TileEntity tileEntity, EntityPlayer player, IFluidHandlerItem fluidHandlerItem, int capacity, FluidStack content) {
        IFluidHandler fluidHandler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (player.isSneaking()) {
            FluidStack fluidStack = fluidHandlerItem.drain(Integer.MAX_VALUE, true);
            if (fluidStack == null) {
                return;
            }
            int rest = fluidHandler.fill(fluidStack, true);
            FluidStack refill = fluidStack.copy();
            refill.amount = refill.amount - rest;
            fluidHandlerItem.fill(refill, true);
        } else {
            FluidStack fluidStack;
            if (content == null) {
                fluidStack = fluidHandler.drain(64000, true);
            } else {
                FluidStack toDrain = content.copy();
                toDrain.amount = capacity - toDrain.amount;
                fluidStack = fluidHandler.drain(toDrain, true);
            }
            if (fluidStack != null)
                fluidHandlerItem.fill(fluidStack, true);
        }

    }
}
