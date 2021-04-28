package gregicadditions.client.model;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.animation.IClip;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReTexturedModel implements IModel {

    protected IReTexturedModel provider;
    protected ResourceLocation variant;
    private IModel[] models; // loading IModels of models
    protected final ResourceLocation[] resources; // resources of models

    private ReTexturedModel(IModel[] models, ResourceLocation[] resources, IReTexturedModel block, ResourceLocation variant) {
        this.models = models;
        this.resources = resources;
        this.provider = block;
        this.variant = variant;
    }

    public ReTexturedModel(ResourceLocation... resources) {
        this.resources = resources;
    }

    protected IModel[] getModels(){
        if (models == null) {
            models = new IModel[resources.length];
            try {
                for (int i = 0; i < resources.length; i++) {
                    models[i] = ModelLoaderRegistry.getModel(resources[i]);
                    if (provider != null) {
                        models[i] = ((IReTexturedModel) provider).loadModel(variant, resources[i], models[i]);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return models;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Arrays.stream(getModels()).flatMap(model -> model.getDependencies().stream()).collect(Collectors.toList());
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Arrays.stream(getModels()).flatMap(model -> model.getTextures().stream()).collect(Collectors.toList());
    }

    @Override
    public IBakedModel bake(IModelState iModelState, VertexFormat vertexFormat, Function<ResourceLocation, TextureAtlasSprite> function) {
        return new ReTexturedBakedModel(this);
    }

    @Override
    public IModelState getDefaultState() {
        return getModels()[0].getDefaultState();
    }

    @Override
    public Optional<? extends IClip> getClip(String name) {
        return getModels()[0].getClip(name);
    }

    @Override
    public ReTexturedModel process(ImmutableMap<String, String> customData) {
        return new ReTexturedModel(Arrays.stream(getModels()).map(model -> model.process(customData)).toArray(size->new IModel[size]), resources, provider, variant);
    }

    @Override
    public ReTexturedModel smoothLighting(boolean value) {
        return new ReTexturedModel(Arrays.stream(getModels()).map(model -> model.smoothLighting(value)).toArray(size->new IModel[size]), resources, provider, variant);
    }

    @Override
    public ReTexturedModel gui3d(boolean value) {
        return new ReTexturedModel(Arrays.stream(getModels()).map(model -> model.gui3d(value)).toArray(size->new IModel[size]), resources, provider, variant);
    }

    @Override
    public ReTexturedModel uvlock(boolean value) {
        return new ReTexturedModel(Arrays.stream(getModels()).map(model -> model.uvlock(value)).toArray(size->new IModel[size]), resources, provider, variant);
    }

    @Override
    public ReTexturedModel retexture(ImmutableMap<String, String> textures) {
        if (textures == null) return this;
        return new ReTexturedModel(Arrays.stream(getModels()).map(model -> model.retexture(textures)).toArray(size->new IModel[size]), resources, provider, variant);
    }

    @Override
    public Optional<ModelBlock> asVanillaModel() {
        return getModels()[0].asVanillaModel();
    }
}
