package gregicadditions.machines.energyconverter.utils;

import gregicadditions.GAConfig;
import gregicadditions.recipes.helper.GACraftingComponents;
import gregtech.api.items.OreDictNames;

public enum EnergyConverterType implements EnergyConverterCraftingHelper.RecipeFunction {

    CONVERT_GTEU(ConverterType.GTEU_TO_FORGE, true) {
        @Override
        public Object[] createRecipe(final int tier, final int invSize) {
            return new Object[]{
                    "WTW", "RMR", "WSW",
                    'M', GACraftingComponents.HULL.getIngredient(tier),
                    'W', EnergyConverterCraftingHelper.HELPER.cable(tier, invSize),
                    'T', OreDictNames.chestWood,
                    'R', EnergyConverterCraftingHelper.HELPER.redCable(invSize),
                    'S', GACraftingComponents.CIRCUIT.getIngredient(tier)
            };
        }
    },

    CONVERT_FORGE(ConverterType.GTEU_TO_FORGE, false) {
        @Override
        public Object[] createRecipe(final int tier, final int invSize) {
            return new Object[]{
                    "WSW", "RMR", "WTW",
                    'M', GACraftingComponents.HULL.getIngredient(tier),
                    'W', EnergyConverterCraftingHelper.HELPER.cable(tier, invSize),
                    'T', OreDictNames.chestWood,
                    'R', EnergyConverterCraftingHelper.HELPER.redCable(invSize),
                    'S', GACraftingComponents.CIRCUIT.getIngredient(tier)
            };
        }
    };

    private final ConverterType type;
    private final boolean isGTEU;

    EnergyConverterType(final ConverterType type, final boolean isGTEU) {
        this.type = type;
        this.isGTEU = isGTEU;
    }

    public ConverterType getConverterType() {
        return this.type;
    }

    public boolean isGTEU() {
        return this.isGTEU;
    }

    public Energy getInput() {
        return this.type.getInput(this.isGTEU);
    }

    public Energy getOutput() {
        return this.type.getOutput(this.isGTEU);
    }

    public Ratio ratio() {

        // 1 EU == many RF
        if (GAConfig.EnergyConversion.RATIO > 1) {
            return type.getOutput(isGTEU) == Energy.FE ?
                    Ratio.ratioOf(1, (long) GAConfig.EnergyConversion.RATIO) : // EU to FE
                    Ratio.ratioOf((long) GAConfig.EnergyConversion.RATIO, 1); // FE to EU

        // 1 RF == many EU
        } else if (GAConfig.EnergyConversion.RATIO < 1) {
            int conversionRate = (int) (1 / GAConfig.EnergyConversion.RATIO);
            return type.getOutput(isGTEU) == Energy.FE ?
                    Ratio.ratioOf(conversionRate, 1) : // EU to FE
                    Ratio.ratioOf(1, conversionRate);   // FE to EU

        // EU to FE (and vice versa) are 1:1
        } else return Ratio.ratioOf(1, 1);
    }

    public boolean isDisabled() {
        return this.isGTEU() ?
                GAConfig.EnergyConversion.disableEUtoFEConverter :
                GAConfig.EnergyConversion.disableFEtoEUConverter;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
