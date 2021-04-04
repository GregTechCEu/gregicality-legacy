package gregicadditions.item.behaviors;

import gregicadditions.renderer.WorldRenderEventRenderer;
import gregicadditions.utils.BlockPatternChecker;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class FreedomWrenchBehaviour implements IItemBehaviour {

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (pos != null && world.getTileEntity(pos) instanceof MetaTileEntityHolder) {
            MetaTileEntity mte = ((MetaTileEntityHolder) world.getTileEntity(pos)).getMetaTileEntity();
            if (mte instanceof RecipeMapMultiblockController) {
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
                    } else if (0.25 < hitY && hitY < 0.75 && (side.getXOffset() == 0 ? 0.25 > hitX : 0.25 > hitZ)){
                        facing = side.rotateY().getOpposite();
                    } else if (0.25 < hitY && hitY < 0.75 && (side.getXOffset() == 0 ? 0.75 < hitX : 0.75 < hitZ)){
                        facing = side.rotateY();
                    } else {
                        facing = facing.getOpposite();
                    }
                }
                if (rotateSpin) {
                    try {
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
                            WorldRenderEventRenderer.renderMultiBlockPreview((MultiblockControllerBase) mte, 5000);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (facing != mte.getFrontFacing()) {
                    if (!world.isRemote) {
                        mte.setFrontFacing(facing);
                    } else {
                        player.sendMessage(new TextComponentString("Facing: " + facing));
                    }
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }


}
