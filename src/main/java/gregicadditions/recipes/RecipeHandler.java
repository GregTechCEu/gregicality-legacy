package gregicadditions.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.GENERATE_METAL_CASING;

public class RecipeHandler {

	public static void register() {
		OrePrefix.valueOf("gtMetalCasing").addProcessingHandler(IngotMaterial.class, RecipeHandler::processMetalCasing);
	}

	private static void processMetalCasing(OrePrefix prefix, IngotMaterial material) {
		if (material.hasFlag(GENERATE_METAL_CASING)) {
			ItemStack metalCasingStack = OreDictUnifier.get(prefix, material, 3);
			ModHandler.addShapedRecipe(String.format("metal_casing_%s", material), metalCasingStack,
					"PhP", "PBP", "PwP",
					'P', new UnificationEntry(OrePrefix.plate, material),
					'B', new UnificationEntry(OrePrefix.frameGt, material));


			RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
					.input(OrePrefix.plate, material, 6)
					.input(OrePrefix.frameGt, material, 1)
					.outputs(metalCasingStack)
					.EUt(8).duration(200)
					.buildAndRegister();
		}
	}
}
