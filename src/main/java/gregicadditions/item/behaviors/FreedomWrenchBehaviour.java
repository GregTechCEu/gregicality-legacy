package gregicadditions.item.behaviors;

import gregicadditions.jei.JEIGAPlugin;
import gregicadditions.renderer.WorldRenderEventRenderer;
import gregicadditions.utils.BlockPatternChecker;
import gregtech.api.block.machines.BlockMachine;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.render.scene.WorldSceneRenderer;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import scala.Int;

import java.util.List;

public class FreedomWrenchBehaviour implements IItemBehaviour {

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        NBTTagCompound nbt = player.getHeldItem(hand).getOrCreateSubCompound("GT.Detrav");
        byte mode = nbt.hasKey("mode") ? nbt.getByte("mode") : 0;
        if (pos != null && world.getTileEntity(pos) instanceof MetaTileEntityHolder) {
            MetaTileEntity mte = ((MetaTileEntityHolder) world.getTileEntity(pos)).getMetaTileEntity();
            if (mte instanceof MultiblockControllerBase && !player.isSneaking()) {
                if (mode == 3) {
                    IRecipeRegistry rr = JEIGAPlugin.jeiRuntime.getRecipeRegistry();
                    IFocus<ItemStack> focus = rr.createFocus(IFocus.Mode.INPUT, mte.getStackForm());
                    WorldSceneRenderer worldSceneRenderer = rr.getRecipeCategories(focus)
                            .stream()
                            .map(c -> (IRecipeCategory<IRecipeWrapper>) c)
                            .map(c -> rr.getRecipeWrappers(c, focus))
                            .flatMap(List::stream)
                            .filter(MultiblockInfoRecipeWrapper.class::isInstance)
                            .findFirst()
                            .map(MultiblockInfoRecipeWrapper.class::cast)
                            .map(MultiblockInfoRecipeWrapper::getCurrentRenderer)
                            .orElse(null);

                    if (worldSceneRenderer != null) {
                        List<BlockPos> renderedBlocks = ObfuscationReflectionHelper.getPrivateValue(WorldSceneRenderer.class, worldSceneRenderer,"renderedBlocks");
                        if (renderedBlocks != null) {
                            EnumFacing refFacing = EnumFacing.EAST;
                            BlockPos refPos = BlockPos.ORIGIN;
                            for(BlockPos blockPos : renderedBlocks) {
                                MetaTileEntity metaTE = BlockMachine.getMetaTileEntity(worldSceneRenderer.world, blockPos);
                                if(metaTE instanceof MultiblockControllerBase && metaTE.metaTileEntityId.equals(mte.metaTileEntityId)) {
                                    refPos = blockPos;
                                    refFacing = metaTE.getFrontFacing();
                                    break;
                                }
                            }
                            for(BlockPos blockPos : renderedBlocks) {
                                if (blockPos.equals(refPos)) continue;
                                BlockPos realPos = BlockPatternChecker.getActualPos(refFacing, mte.getFrontFacing(), BlockPatternChecker.getSpin(mte)
                                        , blockPos.getX() - refPos.getX()
                                        , blockPos.getY() - refPos.getY()
                                        , blockPos.getZ() - refPos.getZ()).add(mte.getPos());
                                if (!world.isAirBlock(realPos)) continue;
                                IBlockState blockState = worldSceneRenderer.world.getBlockState(blockPos);
                                MetaTileEntity metaTileEntity = BlockMachine.getMetaTileEntity(worldSceneRenderer.world, blockPos);
                                if (!player.isCreative()) {
                                    boolean find = false;
                                    ItemStack stack = blockState.getBlock().getItem(worldSceneRenderer.world, blockPos, blockState);
                                    if (metaTileEntity != null) {
                                        stack = metaTileEntity.getStackForm();
                                    }
                                    for (ItemStack itemStack : player.inventory.mainInventory) {
                                        if (itemStack.isItemEqual(stack) && itemStack.getCount() > 0) {
                                            itemStack.setCount(itemStack.getCount() - 1);
                                            find = true;
                                            break;
                                        }
                                    }
                                    if (!find) continue;
                                }
                                world.setBlockState(realPos, blockState);
                                if (metaTileEntity != null) {
                                    MetaTileEntityHolder newHolder = new MetaTileEntityHolder();
                                    newHolder.setMetaTileEntity(metaTileEntity.createMetaTileEntity(newHolder));
                                    newHolder.getMetaTileEntity().setFrontFacing(metaTileEntity.getFrontFacing());
                                    world.setTileEntity(realPos, newHolder);
                                }
                            }

                        }
                    }
                } else if (world.isRemote) {
                    WorldRenderEventRenderer.renderMultiBlockPreview((MultiblockControllerBase) mte, 60000, mode);
                }
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
            mode = (byte)((mode + 1) % 4);
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
