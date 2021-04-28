package gregicadditions.item.components;

import com.google.common.collect.ImmutableMap;
import gregicadditions.GAConfig;
import gregicadditions.blocks.GAMetalCasing;
import gregicadditions.client.model.IReTexturedModel;
import gregicadditions.client.model.ReTexturedModel;
import gregicadditions.client.model.ReTexturedModelLoader;
import gregicadditions.utils.BlockPatternChecker;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class ReTexturedCasing<T extends Enum<T> & IStringSerializable> extends VariantBlock<T> implements IReTexturedModel {

    private final static ResourceLocation FRAME_MODEL = new ResourceLocation("gtadditions","block/casing/frame");
    private final static ResourceLocation GLASS_MODEL = new ResourceLocation("gtadditions","block/casing/glass");
    private final ResourceLocation CORE_MODEL;
    private BlockPos lastPos = BlockPos.ORIGIN;
    private WeakReference<IBlockAccess> lastWorld = new WeakReference<>(null);


    public ReTexturedCasing(ResourceLocation core) {
        super(Material.IRON);
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        CORE_MODEL = core;
    }

    @Override
    public void register(IBlockState state, ResourceLocation path) {
        if (GAConfig.client.AdvancedCasingModel) {
            ReTexturedModelLoader.register(this, path, new ReTexturedModel(getModels(state)));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ResourceLocation[] getModels(IBlockState blockState) {
        return new ResourceLocation[]{CORE_MODEL, FRAME_MODEL, GLASS_MODEL};
    }

    private int color_casing = 0XFFFFFFFF; // aBGR

    @SideOnly(Side.CLIENT)
    @Override
    public ImmutableMap<String, String> reTextured(IBlockState blockState, EnumFacing enumFacing, ResourceLocation model) {
        if (model == FRAME_MODEL && blockState != null && enumFacing == null) {
            MultiblockControllerBase controller = findController();
            if (controller == null) return null;
            ICubeRenderer texture = controller.getBaseTexture(null);
            if (texture == null) return null;
            if (texture instanceof GAMetalCasing) {
                int color = ((GAMetalCasing) texture).blockState
                        .getBaseState()
                        .getValue(((GAMetalCasing) texture).variantProperty)
                        .materialRGB; // aRGB
                color_casing = 0XFF000000 | ((color & 0X00FF0000) >> 16) | (color & 0X0000FF00) | ((color & 0X000000FF) << 16);
            }
            return new ImmutableMap.Builder<String,String>()
                    .put("1", controller.getBaseTexture(null).getParticleSprite().getIconName())
                    .build();
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public List<BakedQuad> reBakedQuad(IBlockState blockState, EnumFacing side, ResourceLocation model, List<BakedQuad> base) {
        if (blockState != null && FRAME_MODEL == model && color_casing != 0XFFFFFFFF) {
            for (BakedQuad quad : base) {
                int[] vertexData = quad.getVertexData();
                vertexData[3] = color_casing;
                vertexData[10] = color_casing;
                vertexData[17] = color_casing;
                vertexData[24] = color_casing;
            }
            color_casing = 0XFFFFFFFF;
        }
        return base;
    }

    @Override
    public boolean shouldRenderInLayer(IBlockState blockState, EnumFacing side, ResourceLocation model, BlockRenderLayer layer) {
        if (blockState == null) return true;
        if (GLASS_MODEL == model && layer == BlockRenderLayer.TRANSLUCENT) {
            return true;
        } else if (GLASS_MODEL != model && layer == BlockRenderLayer.CUTOUT_MIPPED) {
            return true;
        }
        return false;
    }

    @Override
    public IModel loadModel(ResourceLocation variant, ResourceLocation modelRes, IModel model) {
        if (modelRes == CORE_MODEL && variant instanceof ModelResourceLocation) {
            String[] tierS = ((ModelResourceLocation) variant).getVariant().split("_");
            if (tierS.length > 0) {
                return model.retexture(new ImmutableMap.Builder<String,String>()
                        .put("0", "gtadditions:blocks/casing/"+tierS[tierS.length-1])
                        .build());
            }
        }
        return model;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        lastPos = new BlockPos(pos);
        lastWorld = new WeakReference<>(worldIn);
        return super.getActualState(state, worldIn, pos);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        Class<T> enumClass = GTUtility.getActualTypeParameter(this.getClass(), ReTexturedCasing.class, 0);
        this.VARIANT = PropertyEnum.create("variant", enumClass);
        this.VALUES = enumClass.getEnumConstants();
        return new BlockStateContainer(this, this.VARIANT);
    }

    @SideOnly(Side.CLIENT)
    protected MultiblockControllerBase findController() {
        IBlockAccess world = lastWorld.get();
        if (world == null) {
            return null;
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                for (int z = 0; z < 10; z++) {
                    for (BlockPos blockPos : new BlockPos[] {lastPos.add(x, y, z), lastPos.add(-x, y, z), lastPos.add(x, -y, z)
                            , lastPos.add(x, y, -z), lastPos.add(-x, -y, z), lastPos.add(x, -y, -z)
                            , lastPos.add(-x, y, -z), lastPos.add(-x, -y, -z)}) {
                        TileEntity te = world.getTileEntity(blockPos);
                        if (te instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) te).getMetaTileEntity() instanceof MultiblockControllerBase) {
                            MultiblockControllerBase controller = (MultiblockControllerBase) ((MetaTileEntityHolder) te).getMetaTileEntity();
                            BlockPattern structurePattern = ObfuscationReflectionHelper
                                    .getPrivateValue(MultiblockControllerBase.class, controller, "structurePattern");
                            if(structurePattern != null) {
                                PatternMatchContext result = BlockPatternChecker
                                        .checkPatternAt(structurePattern, controller.getWorld(), controller.getPos(),
                                                controller.getFrontFacing().getOpposite());
                                if (result != null && result.get("validPos") != null) {
                                    List<BlockPos> validPos = result.get("validPos");
                                    if (validPos.contains(lastPos)) {
                                        return controller;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        lastWorld = new WeakReference<>(null);
        return null;
    }

    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /** @deprecated */
    public boolean isFullCube(IBlockState state) {
        return true;
    }

}
