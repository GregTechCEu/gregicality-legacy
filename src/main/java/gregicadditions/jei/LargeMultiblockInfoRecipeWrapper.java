package gregicadditions.jei;

import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class LargeMultiblockInfoRecipeWrapper extends MultiblockInfoRecipeWrapper {
    public LargeMultiblockInfoRecipeWrapper(MultiblockInfoPage infoPage) {
        super(infoPage);

    }

    @Override
    public void setRecipeLayout(RecipeLayout layout) {
        super.setRecipeLayout(layout);
        ObfuscationReflectionHelper.setPrivateValue(MultiblockInfoRecipeWrapper.class, this, 0.3f, "zoom");
    }
}
