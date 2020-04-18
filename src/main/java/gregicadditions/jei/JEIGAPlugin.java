package gregicadditions.jei;

import gregtech.common.items.MetaItems;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEIGAPlugin implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GAMultiblockInfoCategory(registry.getJeiHelpers()));
	}

	@Override
	public void register(IModRegistry registry) {
		GAMultiblockInfoCategory.registerRecipes(registry);
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm());
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		IRecipeRegistry recipeRegistry = jeiRuntime.getRecipeRegistry();
		recipeRegistry.hideRecipeCategory("gregtech:multiblock_info");
	}
}
