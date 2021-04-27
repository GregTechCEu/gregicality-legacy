package gregicadditions.client.model;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReTexturedBakedModel implements IBakedModel {
    protected final ReTexturedModel baseModel;
    private List<IBakedModel> CACHE;

    public ReTexturedBakedModel(ReTexturedModel model) {
        this.baseModel = model;
    }

    protected List<IBakedModel> getBakedModels() {
        if (CACHE == null){
            CACHE = Arrays.stream(baseModel.getModels()).map(model -> model.bake(TRSRTransformation.identity()
                    , DefaultVertexFormats.BLOCK
                    , location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString())))
                    .collect(Collectors.toList());
        }
        return CACHE;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        if (state != null && state.getBlock() instanceof IReTexturedModel) {
            BlockRenderLayer layer = MinecraftForgeClient.getRenderLayer();
            IReTexturedModel block = (IReTexturedModel) state.getBlock();
            List<BakedQuad> bakedQuads = new ArrayList<>();
            IModel[] models = baseModel.getModels();
            if (layer != null) {
                for (int i = 0; i < models.length; i++) {
                    IModel toBaked = models[i];
                    ResourceLocation resource = baseModel.resources[i];
                    if (block.shouldRenderInLayer(state, side, resource, layer)) {
                        ImmutableMap<String, String> map = block.reTextured(state, side, resource);
                        if (map != null) {
                            toBaked = toBaked.retexture(map);
                        }
                        List<BakedQuad> quads = toBaked.bake(TRSRTransformation.identity()
                                , DefaultVertexFormats.ITEM
                                , location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString())).getQuads(state, side, rand);
                        bakedQuads.addAll(block.reBakedQuad(state, side, resource, quads));
                    }
                }
            }
            return bakedQuads;
        } else {
            return getBakedModels().stream().flatMap(model -> model.getQuads(state, side, rand).stream()).collect(Collectors.toList());
        }
    }

    @Override
    public boolean isAmbientOcclusion() {
        return getBakedModels().get(0).isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return getBakedModels().get(0).isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return getBakedModels().get(0).isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return getBakedModels().get(0).getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return getBakedModels().get(0).getItemCameraTransforms();
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return ForgeHooksClient.handlePerspective(this, cameraTransformType);
    }

    @Override
    public ItemOverrideList getOverrides() {
        return getBakedModels().get(0).getOverrides();
    }

    public static IBakedModel getModel(IBlockState state) {
        return Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
    }

    public static IBakedModel getModel(ResourceLocation resourceLocation) throws RuntimeException {
        IBakedModel bakedModel;
        IModel model;
        try {
            model = ModelLoaderRegistry.getModel(resourceLocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bakedModel = model.bake(TRSRTransformation.identity(), DefaultVertexFormats.BLOCK,
                location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
        return bakedModel;
    }
}
