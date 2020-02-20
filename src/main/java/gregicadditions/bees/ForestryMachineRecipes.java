package gregicadditions.bees;

import com.google.common.collect.ImmutableMap;

import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.ModuleApiculture;
import forestry.core.ModuleCore;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

public class ForestryMachineRecipes {
	public static void init() {
		RecipeManagers.centrifugeManager.addRecipe(20, ModuleApiculture.getItems().propolis.getItemStack(), ImmutableMap.of(MetaItems.RUBBER_DROP.getStackForm(), 1.0f));

		//Combs
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.LIGNITE, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.gem, Materials.Lignite), 0.9f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.COAL, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.gem, Materials.Coal), 0.4f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.RUBBERY, 1), ImmutableMap.of(MetaItems.RUBBER_DROP.getStackForm(), 0.7f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.squeezerManager.addRecipe(20, GTCombItem.getComb(GTCombs.OIL, 1), Materials.Oil.getFluid(150), ModuleCore.getItems().beeswax.getItemStack(), 30);
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.STONE, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dust, Materials.Stone), 0.7f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f, OreDictUnifier.get(OrePrefix.dust, Materials.Salt), 0.2f, OreDictUnifier.get(OrePrefix.dust, Materials.RockSalt), 0.2f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.SLAG, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dust, Materials.Stone), 0.5f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f, OreDictUnifier.get(OrePrefix.dust, Materials.GraniteBlack), 0.2f, OreDictUnifier.get(OrePrefix.dust, Materials.GraniteRed), 0.2f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.COPPON, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Copper), 0.7f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.TINE, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Tin), 0.6f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.PLUMBIA, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Lead), 0.45f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
		RecipeManagers.centrifugeManager.addRecipe(20, GTCombItem.getComb(GTCombs.ARGENTIA, 1), ImmutableMap.of(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Silver), 0.3f, ModuleCore.getItems().beeswax.getItemStack(), 0.3f));
	}
}