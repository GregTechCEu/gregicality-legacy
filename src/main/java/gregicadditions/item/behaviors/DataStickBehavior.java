package gregicadditions.item.behaviors;

import gregicadditions.machines.overrides.GATieredMetaTileEntity;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.util.GTLog;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DataStickBehavior implements IItemBehaviour {

    public NBTTagCompound getOrCreateSubTag(ItemStack stack) {
        return stack.getOrCreateSubCompound("BlockPos");
    }

    @Nullable
    public NBTTagCompound getSubTag(ItemStack stack) {
        return stack.getSubCompound("BlockPos");
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof MetaTileEntityHolder) {
            if(((MetaTileEntityHolder) tile).getMetaTileEntity() instanceof TieredMetaTileEntity || ((MetaTileEntityHolder) tile).getMetaTileEntity() instanceof GATieredMetaTileEntity) {
                if(!stack.isEmpty()) {
                    GTLog.logger.info("Saving Pos");
                    NBTTagCompound subTag = getOrCreateSubTag(stack);
                    subTag.setInteger("x", pos.getX());
                    subTag.setInteger("y", pos.getY());
                    subTag.setInteger("z", pos.getZ());
                    subTag.setShort("dim", (short) world.provider.getDimension());

                    return EnumActionResult.PASS;
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        GTLog.logger.info("Add Data Stick Tooltip");
        if(!itemStack.isEmpty()) {
            NBTTagCompound tag = getSubTag(itemStack);
            if(tag != null) {
                lines.add(I18n.format("metaitem.data_stick.tooltip.blockpos", tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"), tag.getShort("dim")));
            }
        }
    }
}
