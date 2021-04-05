package gregicadditions.coremod.hooks;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XNetHooks {

    //origin: net.minecraft.block.Block.getItem()
    public static ItemStack getItem(Block block, World worldIn, BlockPos pos, IBlockState state) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof MetaTileEntityHolder) {
            MetaTileEntity metaTileEntity = ((MetaTileEntityHolder) te).getMetaTileEntity();
            if (metaTileEntity != null) {
                return metaTileEntity.getStackForm();
            }
        }
        return block.getItem(worldIn, pos, state);
    }
}
