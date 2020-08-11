package gregicadditions.integrations.tconstruct;

import gregicadditions.GAConfig;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.smeltery.Cast;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TinkersGtRecipes {
	public static void init() {
		//Glass
		if (!GAConfig.GregsConstruct.GregsConstructGlassProcessing) return;
		removeRecipesByInputs(new ItemStack(Blocks.GLASS));
		removeRecipesByInputs(new ItemStack(Blocks.STAINED_GLASS));
		removeRecipesByInputs(new ItemStack(Blocks.GLASS_PANE));
		removeRecipesByInputs(new ItemStack(Blocks.STAINED_GLASS_PANE));
		removeRecipesByInputs(new ItemStack(Items.GLASS_BOTTLE));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.dust, Materials.Quartzite));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.dust, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gem, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemFlawless, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemFlawed, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.gemChipped, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glass));
		removeRecipesByInputs(OreDictUnifier.get(OrePrefix.lens, Materials.Glass));

		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.block, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.dust, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.gem, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).inputs(new ItemStack(Items.GLASS_BOTTLE)).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(32).input(OrePrefix.gemChipped, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(250)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(40).EUt(32).input(OrePrefix.gemFlawed, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(500)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(32).input(OrePrefix.gemFlawless, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(2000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(320).EUt(32).input(OrePrefix.gemExquisite, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(4000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.plate, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(1000)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.lens, Materials.Glass).fluidOutputs(Materials.Glass.getFluid(750)).buildAndRegister();
		RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(28).input(OrePrefix.dust, Materials.Quartzite).fluidOutputs(Materials.Glass.getFluid(500)).buildAndRegister();

		removeRecipesByInputs(new ItemStack[] { MetaItems.SHAPE_MOLD_BLOCK.getStackForm() }, new FluidStack[] { Materials.Glass.getFluid(144) });
		removeRecipesByInputs(new ItemStack[] { MetaItems.SHAPE_MOLD_PLATE.getStackForm() }, new FluidStack[] { Materials.Glass.getFluid(144) }, 4);
		removeRecipesByInputs(new ItemStack[] { MetaItems.SHAPE_MOLD_PLATE.getStackForm() }, new FluidStack[] { Materials.Glass.getFluid(144) }, 8);

		RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(18).EUt(4).notConsumable(MetaItems.SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Glass.getFluid(1000)).outputs(new ItemStack(TinkerCommons.blockClearGlass)).buildAndRegister();
		RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(18).EUt(4).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(Materials.Glass.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).buildAndRegister();
	}

	public static void init2() {
		if (GAConfig.GregsConstruct.castingRecipes) {
			for (slimeknights.tconstruct.library.materials.Material material : TinkerRegistry.getAllMaterials()) {
				Fluid fluid = material.getFluid();
				for (IToolPart toolPart : TinkerRegistry.getToolParts()) {
					if (!toolPart.canBeCasted()) {
						continue;
					}
					if (!toolPart.canUseMaterial(material)) {
						continue;
					}
					if (toolPart instanceof MaterialItem) {
						ItemStack castItem = toolPart.getItemstackWithMaterial(material);
						ItemStack cast = new ItemStack(TinkerSmeltery.cast);
						Cast.setTagForPart(cast, castItem.getItem());
						if (fluid != null) {
							RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
									.notConsumable(cast)
									.fluidInputs(new FluidStack(fluid, toolPart.getCost()))
									.outputs(castItem)
									.EUt(32)
									.duration(Math.max(20 * toolPart.getCost() / 144, 1))
									.buildAndRegister();
						}
					}
				}
			}
		}
	}

	private static void removeRecipesByInputs(ItemStack... itemInputs) {
		List<ItemStack> inputs = new ArrayList<>();
		for (ItemStack s : itemInputs)
			inputs.add(s);
		RecipeMaps.FLUID_EXTRACTION_RECIPES.removeRecipe(RecipeMaps.FLUID_EXTRACTION_RECIPES.findRecipe(Integer.MAX_VALUE, inputs, Collections.emptyList(), Integer.MAX_VALUE));
	}

	private static void removeRecipesByInputs(ItemStack[] itemInputs, FluidStack[] fluidInputs) {
		List<ItemStack> itemIn = new ArrayList<>();
		for (ItemStack s : itemInputs)
			itemIn.add(s);
		List<FluidStack> fluidIn = new ArrayList<>();
		for (FluidStack s : fluidInputs)
			fluidIn.add(s);
		RecipeMaps.FLUID_SOLIDFICATION_RECIPES.removeRecipe(RecipeMaps.FLUID_SOLIDFICATION_RECIPES.findRecipe(Integer.MAX_VALUE, itemIn, fluidIn, Integer.MAX_VALUE));
	}

	private static void removeRecipesByInputs(ItemStack[] itemInputs, FluidStack[] fluidInputs, int voltage) {
		List<ItemStack> itemIn = new ArrayList<>();
		for (ItemStack s : itemInputs)
			itemIn.add(s);
		List<FluidStack> fluidIn = new ArrayList<>();
		for (FluidStack s : fluidInputs)
			fluidIn.add(s);
		RecipeMaps.FLUID_SOLIDFICATION_RECIPES.removeRecipe(RecipeMaps.FLUID_SOLIDFICATION_RECIPES.findRecipe(voltage, itemIn, fluidIn, Integer.MAX_VALUE));
	}
}