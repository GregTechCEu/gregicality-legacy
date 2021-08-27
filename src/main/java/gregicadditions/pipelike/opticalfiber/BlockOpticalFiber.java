package gregicadditions.pipelike.opticalfiber;

import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.pipelike.opticalfiber.net.WorldOpticalFiberNet;
import gregicadditions.pipelike.opticalfiber.tile.TileEntityOpticalFiber;
import gregicadditions.pipelike.opticalfiber.tile.TileEntityOpticalFiberTickable;
import gregicadditions.client.renderer.OpticalFiberRenderer;
import gregtech.api.pipenet.block.simple.BlockSimplePipe;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockOpticalFiber extends BlockSimplePipe<OpticalFiberSize, OpticalFiberProperties, WorldOpticalFiberNet> implements ITileEntityProvider {


    public BlockOpticalFiber() {
        setHarvestLevel("cutter", 1);
    }


    @Override
    public Class<OpticalFiberSize> getPipeTypeClass() {
        return OpticalFiberSize.class;
    }


    @Override
    public WorldOpticalFiberNet getWorldPipeNet(World world) {
        return WorldOpticalFiberNet.getWorldENet(world);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (OpticalFiberSize opticalFiberSize : OpticalFiberSize.values()) {
            items.add(getItem(opticalFiberSize));
        }
    }

    public ItemStack getItem(OpticalFiberSize pipeType) {
        if (pipeType == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(this, 1, pipeType.ordinal());
    }

    public int getActiveNodeConnections(IBlockAccess world, BlockPos nodePos, IPipeTile<OpticalFiberSize, OpticalFiberProperties> selfTileEntity) {
        int activeNodeConnections = 0;
        for (EnumFacing side : EnumFacing.VALUES) {
            BlockPos offsetPos = nodePos.offset(side);
            TileEntity tileEntity = world.getTileEntity(offsetPos);
            //do not connect to null cables and ignore cables
            if (tileEntity == null || getPipeTileEntity(tileEntity) != null) continue;
            EnumFacing opposite = side.getOpposite();
            IQubitContainer qubitContainer = tileEntity.getCapability(GregicalityCapabilities.QBIT_CAPABILITY, opposite);
            if (qubitContainer != null) {
                activeNodeConnections |= 1 << side.getIndex();
            }
        }
        return activeNodeConnections;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return OpticalFiberRenderer.BLOCK_RENDER_TYPE;
    }

    @Override
    public TileEntityPipeBase<OpticalFiberSize, OpticalFiberProperties> createNewTileEntity(boolean supportsTicking) {
        return supportsTicking ? new TileEntityOpticalFiberTickable() : new TileEntityOpticalFiber();
    }

    protected OpticalFiberProperties createProperties(OpticalFiberSize insulation) {
        return insulation.modifyProperties(getFallbackType());
    }

    @Override
    protected OpticalFiberProperties getFallbackType() {
        return new OpticalFiberProperties(1, 1);
    }

    //@Override
    //public void func_149666_a(@Nonnull CreativeTabs creativeTabs, @Nonnull NonNullList<ItemStack> nonNullList) {
        //todo optical fiber cables
    //}

    @Override
    public boolean canPipesConnect(IPipeTile<OpticalFiberSize, OpticalFiberProperties> iPipeTile, EnumFacing enumFacing, IPipeTile<OpticalFiberSize, OpticalFiberProperties> iPipeTile1) {
        return false; //todo optical fiber cables
    }

    @Override
    public boolean canPipeConnectToBlock(IPipeTile<OpticalFiberSize, OpticalFiberProperties> iPipeTile, EnumFacing enumFacing, @Nullable TileEntity tileEntity) {
        return false; //todo optical fiber cables
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(World world, BlockPos blockPos) {
        return OpticalFiberRenderer.INSTANCE.getParticleTexture((TileEntityOpticalFiber) world.getTileEntity(blockPos));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null; //todo optical fiber cables
    }
}
