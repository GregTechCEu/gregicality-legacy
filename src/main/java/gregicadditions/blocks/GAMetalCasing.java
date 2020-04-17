package gregicadditions.blocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.common.blocks.DelayedStateBlock;
import gregtech.common.blocks.properties.PropertyMaterial;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;

public class GAMetalCasing extends DelayedStateBlock implements ICubeRenderer, TextureUtils.IIconRegister {

    public final PropertyMaterial variantProperty;
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sprite;

    public GAMetalCasing(Material materials) {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("metal_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        this.variantProperty = PropertyMaterial.create("variant", Collections.singleton(materials));
        Textures.iconRegisters.add(this);
        initBlockState();
    }

    @Override
    protected BlockStateContainer createStateContainer() {
        return new BlockStateContainer(this, variantProperty);
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "wrench";
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 2;
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    public IBlockState getStateFromMaterial(Material material) {
        int meta = variantProperty.getAllowedValues().indexOf(material);
        return getStateFromMeta(meta);
    }


    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        Material material = variantProperty.getAllowedValues().get(meta);
        return getDefaultState().withProperty(variantProperty, material);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        Material material = state.getValue(variantProperty);
        return variantProperty.getAllowedValues().indexOf(material);
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem(IBlockState blockState) {
        return new ItemStack(this, 1, getMetaFromState(blockState));
    }

    public ItemStack getItem(Material material) {
        return getItem(getDefaultState().withProperty(variantProperty, material));
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        blockState.getValidStates().stream()
                .filter(blockState -> blockState.getValue(variantProperty) != Materials._NULL)
                .forEach(blockState -> list.add(getItem(blockState)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public net.minecraft.block.material.Material getMaterial(IBlockState state) {
        return net.minecraft.block.material.Material.IRON;
    }

    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return getMaterial(state).getMaterialMapColor();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap textureMap) {
        Material material = blockState.getBaseState().getValue(variantProperty);
        this.sprite = textureMap.registerSprite(MaterialIconType.valueOf("gtMetalCasing").getBlockPath(material.materialIconSet));
    }

    @SideOnly(Side.CLIENT)
    public void renderSided(EnumFacing side, Matrix4 translation, Cuboid6 bounds, CCRenderState renderState, IVertexOperation[] pipeline) {
        Textures.renderFace(renderState, translation, pipeline, side, bounds, sprite);
    }

    @SideOnly(Side.CLIENT)
    public void renderSided(EnumFacing side, CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        renderSided(side, translation, Cuboid6.full, renderState, pipeline);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite() {
        return sprite;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 bounds) {
        Material material = blockState.getBaseState().getValue(variantProperty);

        int oldBaseColour = renderState.baseColour;
        int oldAlphaOverride = renderState.alphaOverride;
        renderState.baseColour = material.materialRGB << 8;
        renderState.alphaOverride = 0xFF;
        for (EnumFacing side : EnumFacing.values()) {
            renderSided(side, translation, bounds, renderState, pipeline);
        }
        renderState.baseColour = oldBaseColour;
        renderState.alphaOverride = oldAlphaOverride;
    }

}
