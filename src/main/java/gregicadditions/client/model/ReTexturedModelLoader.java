package gregicadditions.client.model;

import gregicadditions.Gregicality;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

import java.util.HashMap;
import java.util.Map;

public class ReTexturedModelLoader implements ICustomModelLoader {

    private static final Map<ResourceLocation, ReTexturedModel> models = new HashMap<>();

    public static void register(IReTexturedModel provider, ResourceLocation path, ReTexturedModel model) {
        if (model == null) return;
        models.put(path, model);
        model.variant = path;
        model.provider = provider;
    }

    @Override
    public void onResourceManagerReload(IResourceManager iResourceManager) {

    }

    @Override
    public boolean accepts(ResourceLocation resourceLocation) {
        return resourceLocation.getNamespace().equals(Gregicality.MODID) && models.containsKey(resourceLocation);
    }

    @Override
    public IModel loadModel(ResourceLocation resourceLocation) {
        return models.get(resourceLocation);
    }
}
