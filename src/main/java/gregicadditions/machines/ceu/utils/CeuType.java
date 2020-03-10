package gregicadditions.machines.ceu.utils;

import gregicadditions.GAConfig;
import gregicadditions.recipes.GACraftingComponents;
import gregtech.api.items.OreDictNames;

public enum CeuType implements CeuCraftingHelper.RecipeFunction {
	CEU(ConverterType.CEU_CEF, true) {
		@Override
		public Object[] createRecipe(final int tier, final int slots) {
			return new Object[]{"WTW", "RMR", "WSW", 'M', GACraftingComponents.HULL.getIngredient(tier), 'W', CeuCraftingHelper.HELPER.cable(tier, slots), 'T', OreDictNames.chestWood, 'R', CeuCraftingHelper.HELPER.redCable(slots), 'S', GACraftingComponents.CIRCUIT.getIngredient(tier)};
		}
	},
	CEF(ConverterType.CEU_CEF, false) {
		@Override
		public Object[] createRecipe(final int tier, final int slots) {
			return new Object[]{"WSW", "RMR", "WTW", 'M', GACraftingComponents.HULL.getIngredient(tier), 'W', CeuCraftingHelper.HELPER.cable(tier, slots), 'T', OreDictNames.chestWood, 'R', CeuCraftingHelper.HELPER.redCable(slots), 'S', GACraftingComponents.CIRCUIT.getIngredient(tier)};
		}
	};

	private final ConverterType type;
	private final boolean isCeu;

	CeuType(final ConverterType type, final boolean isCeu) {
		this.type = type;
		this.isCeu = isCeu;
	}

	public ConverterType getConverterType() {
		return this.type;
	}

	public boolean isCeu() {
		return this.isCeu;
	}

	public Energy getInput() {
		return this.type.getInput(this.isCeu);
	}

	public Energy getOutput() {
		return this.type.getOutput(this.isCeu);
	}

	public Ratio ratio() {
		return type.getOutput(isCeu) == Energy.FE ? Ratio.ratioOf(1, GAConfig.ceu.Ratio) : Ratio.ratioOf(GAConfig.ceu.Ratio, 1);
	}

	public boolean isDisabled() {
		return GAConfig.ceu.Disable;
	}


	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
