package gregicadditions.machines.energyconverter.utils;

public enum ConverterType {
    GTEU_TO_FORGE(0, 9, Energy.FE);

    private final int minTier;
    private final int maxTier;
    private final Energy energyOutput;

    ConverterType(final int minTier, final int maxTier, final Energy energyOutput) {
        if (energyOutput == Energy.GTEU) {
            throw new IllegalArgumentException();
        }
        this.minTier = minTier;
        this.maxTier = maxTier;
        this.energyOutput = energyOutput;
    }

    public EnergyConverterType getGTEUToForgeType() {
        if (this == ConverterType.GTEU_TO_FORGE) {
            return EnergyConverterType.CONVERT_GTEU;
        }
        throw new IllegalArgumentException();
    }

    public EnergyConverterType getForgeToGTEUType() {
        if (this == ConverterType.GTEU_TO_FORGE) {
            return EnergyConverterType.CONVERT_FORGE;
        }
        throw new IllegalArgumentException();
    }

    public int getMinTier() {
        return this.minTier;
    }

    public int getMaxTier() {
        return this.maxTier;
    }

    public Energy getEnergyOutput() {
        return this.energyOutput;
    }

    public Energy getInput(final boolean isGTEU) {
        return isGTEU ? Energy.GTEU : this.energyOutput;
    }

    public Energy getOutput(final boolean isGTEU) {
        return isGTEU ? this.energyOutput : Energy.GTEU;
    }


}
