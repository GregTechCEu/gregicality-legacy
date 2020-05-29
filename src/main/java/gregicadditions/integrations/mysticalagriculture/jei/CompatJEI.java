package gregicadditions.integrations.mysticalagriculture.jei;

import com.blakebr0.mysticalagradditions.compat.jei.Tier6CropCategory;
import com.blakebr0.mysticalagradditions.compat.jei.Tier6CropWrapper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class CompatJEI implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        registry.handleRecipes(Tier6CropWrapper.class, recipe -> recipe.setHelper(jeiHelpers), Tier6CropCategory.UID);
        registry.addRecipes(Tier7CropRecipeMaker.getRecipes(), Tier6CropCategory.UID);
    }
}
