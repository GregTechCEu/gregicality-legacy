package gregicadditions.pipelike.opticalfiber;

import com.google.common.base.Preconditions;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IOpticalFiberContainer;
import gregicadditions.pipelike.opticalfiber.net.WorldOpticalFiberNet;
import gregicadditions.pipelike.opticalfiber.tile.TileEntityOpticalFiber;
import gregicadditions.pipelike.opticalfiber.tile.TileEntityOpticalFiberTickable;
import gregicadditions.renderer.OpticalFiberRenderer;
import gregtech.api.pipenet.block.material.BlockMaterialPipe;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import gregtech.api.unification.material.type.Material;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BlockOpticalFiber extends BlockMaterialPipe<OpticalFiberSize, OpticalFiberProperties, WorldOpticalFiberNet> implements ITileEntityProvider {

    private final Map<Material, OpticalFiberProperties> enabledMaterials = new TreeMap<>();

    public BlockOpticalFiber() {
        setHarvestLevel("cutter", 1);
    }

    public void addCableMaterial(Material material, OpticalFiberProperties opticalFiberProperties) {
        Preconditions.checkNotNull(material, "material");
        Preconditions.checkNotNull(opticalFiberProperties, "wireProperties");
        Preconditions.checkArgument(Material.MATERIAL_REGISTRY.getNameForObject(material) != null, "material is not registered");
        this.enabledMaterials.put(material, opticalFiberProperties);
    }

    public Collection<Material> getEnabledMaterials() {
        return Collections.unmodifiableSet(enabledMaterials.keySet());
    }

    @Override
    public Class<OpticalFiberSize> getPipeTypeClass() {
        return OpticalFiberSize.class;
    }

    @Override
    protected OpticalFiberProperties createProperties(OpticalFiberSize opticalFiberSize, Material material) {
        return opticalFiberSize.modifyProperties(enabledMaterials.getOrDefault(material, getFallbackType()));
    }

    @Override
    protected OpticalFiberProperties getFallbackType() {
        return enabledMaterials.values().iterator().next();
    }

    @Override
    public WorldOpticalFiberNet getWorldPipeNet(World world) {
        return WorldOpticalFiberNet.getWorldENet(world);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (Material material : enabledMaterials.keySet()) {
            for (OpticalFiberSize opticalFiberSize : OpticalFiberSize.values()) {
                items.add(getItem(opticalFiberSize, material));
            }
        }
    }

    @Override
    public int getActiveNodeConnections(IBlockAccess world, BlockPos nodePos, IPipeTile<OpticalFiberSize, OpticalFiberProperties> selfTileEntity) {
        int activeNodeConnections = 0;
        for (EnumFacing side : EnumFacing.VALUES) {
            BlockPos offsetPos = nodePos.offset(side);
            TileEntity tileEntity = world.getTileEntity(offsetPos);
            //do not connect to null cables and ignore cables
            if (tileEntity == null || getPipeTileEntity(tileEntity) != null) continue;
            EnumFacing opposite = side.getOpposite();
            IOpticalFiberContainer energyContainer = tileEntity.getCapability(GregicAdditionsCapabilities.OPTICAL_FIBER_CAPABILITY, opposite);
            if (energyContainer != null) {
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

    @Override
    @SideOnly(Side.CLIENT)
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(World world, BlockPos blockPos) {
        return OpticalFiberRenderer.INSTANCE.getParticleTexture((TileEntityOpticalFiber) world.getTileEntity(blockPos));
    }
}
