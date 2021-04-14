package gregicadditions.item.behaviors;

import gregicadditions.renderer.WorldRenderEventRenderer;
import gregicadditions.utils.BlockPatternChecker;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import scala.Int;

import java.util.List;

public class FreedomWrenchBehaviour implements IItemBehaviour {

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        NBTTagCompound nbt = player.getHeldItem(hand).getOrCreateSubCompound("GT.Detrav");
        byte mode = nbt.hasKey("mode") ? nbt.getByte("mode") : 0;
        if (pos != null && world.getTileEntity(pos) instanceof MetaTileEntityHolder) {
            MetaTileEntity mte = ((MetaTileEntityHolder) world.getTileEntity(pos)).getMetaTileEntity();
            if (mte instanceof MultiblockControllerBase && !player.isSneaking() && world.isRemote) {
                WorldRenderEventRenderer.renderMultiBlockPreview((MultiblockControllerBase) mte, 60000, mode);
            }
            else if (mte instanceof MultiblockControllerBase && player.isSneaking()) {
                boolean rotateSpin = false;
                EnumFacing facing = mte.getFrontFacing();
                if (side == EnumFacing.DOWN || side == EnumFacing.UP) {
                    if (0.25 < hitX && hitX < 0.75 && 0.25 < hitZ && hitZ < 0.75 ) {
                        if (facing == side) {
                            rotateSpin = true;
                        } else {
                            facing = side;
                        }
                    } else if (hitX < 0.25 && 0.25 < hitZ && hitZ < 0.75){
                        facing = EnumFacing.WEST;
                    } else if (hitX > 0.75 && 0.25 < hitZ && hitZ < 0.75){
                        facing = EnumFacing.EAST;
                    } else if (hitZ < 0.25 && 0.25 < hitX && hitX < 0.75){
                        facing = EnumFacing.NORTH;
                    } else if (hitZ > 0.75 && 0.25 < hitX && hitX < 0.75){
                        facing = EnumFacing.SOUTH;
                    } else {
                        facing = facing.getOpposite();
                    }
                } else {
                    if(0.25 < hitY && hitY < 0.75 && (side.getXOffset() == 0 ? 0.25 < hitX && hitX < 0.75 : 0.25 < hitZ && hitZ < 0.75)) {
                        if (facing == side) {
                            rotateSpin = true;
                        } else {
                            facing = side;
                        }
                    } else if ((side.getXOffset() == 0 ? 0.25 < hitX && hitX < 0.75 : 0.25 < hitZ && hitZ < 0.75) && hitY < 0.25){
                        facing = EnumFacing.DOWN;
                    } else if ((side.getXOffset() == 0 ? 0.25 < hitX && hitX < 0.75 : 0.25 < hitZ && hitZ < 0.75) && hitY > 0.75){
                        facing = EnumFacing.UP;
                    } else if (0.25 < hitY && hitY < 0.75 && (side.getXOffset() == 0
                            ? (side.getZOffset() < 0 ? 0.25 > hitX : 0.75 < hitX)
                            : (side.getXOffset() > 0 ? 0.25 > hitZ : 0.75 < hitZ))){
                        facing = side.rotateY().getOpposite();
                    } else if (0.25 < hitY && hitY < 0.75 && (side.getXOffset() == 0
                            ? (side.getZOffset() > 0 ? 0.25 > hitX : 0.75 < hitX)
                            : (side.getXOffset() < 0 ? 0.25 > hitZ : 0.75 < hitZ))){
                        facing = side.rotateY();
                    } else {
                        facing = side.getOpposite();
                    }
                }
                if (rotateSpin) {
                    EnumFacing next = BlockPatternChecker.getSpin(mte).rotateY();
                    BlockPatternChecker.setSpin(mte, next);
                    if (!world.isRemote) {
                        mte.notifyBlockUpdate();
                        mte.markDirty();
                    } else {
                        mte.scheduleRenderUpdate();
                        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP) {
                            player.sendMessage(new TextComponentString("Spin: " + (next == EnumFacing.NORTH ?
                                    "up" : next == EnumFacing.EAST ?
                                    "right" : next == EnumFacing.SOUTH ?
                                    "down" : "left")));
                        } else {
                            player.sendMessage(new TextComponentString("Spin: " + next));
                        }
                    }
                } else if (facing != mte.getFrontFacing()) {
                    if (!world.isRemote) {
                        mte.setFrontFacing(facing);
                    } else {
                        player.sendMessage(new TextComponentString("Facing: " + facing));
                    }
                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        NBTTagCompound nbt = player.getHeldItem(hand).getOrCreateSubCompound("GT.Detrav");
        byte mode = nbt.hasKey("mode") ? nbt.getByte("mode") : 0;
        if (player.isSneaking()) {
            mode = (byte)((mode + 1) % 3);
            nbt.setByte("mode", mode);
            if (world.isRemote) {
                player.sendMessage(new TextComponentTranslation("metaitem.freedom_wrench.mode." + mode));
            }
        } else {
            if (player.world.isRemote) {
                WorldRenderEventRenderer.renderMultiBlockPreview(null, 0, mode);
                player.sendMessage(new TextComponentTranslation("metaitem.freedom_wrench.clear"));
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        NBTTagCompound nbt = itemStack.getOrCreateSubCompound("GT.Detrav");
        byte mode = nbt.hasKey("mode") ? nbt.getByte("mode") : 0;
        lines.add(I18n.format("metaitem.freedom_wrench.mode." + mode));
        lines.add(I18n.format("metaitem.freedom_wrench.info.0"));
    }
}
