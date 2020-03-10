package gregicadditions.machines.ceu.utils;

public enum ConverterType {
    CEU_CEF(0, 9, Energy.FE);

    private final int minTier;
    private final int maxTier;
    private final Energy ceuOutput;

    ConverterType(final int minTier, final int maxTier, final Energy ceuOutput) {
        if (ceuOutput == Energy.GTEU) {
            throw new IllegalArgumentException();
        }
        this.minTier = minTier;
        this.maxTier = maxTier;
        this.ceuOutput = ceuOutput;
    }

    public CeuType getCeuType() {
        switch (this) {
            case CEU_CEF: {
                return CeuType.CEU;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public CeuType getCefType() {
        switch (this) {
            case CEU_CEF: {
                return CeuType.CEF;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public int getMinTier() {
        return this.minTier;
    }

    public int getMaxTier() {
        return this.maxTier;
    }

    public Energy getCeuOutput() {
        return this.ceuOutput;
    }

    public Energy getInput(final boolean isCeu) {
        return isCeu ? Energy.GTEU : this.ceuOutput;
    }

    public Energy getOutput(final boolean isCeu) {
        return isCeu ? this.ceuOutput : Energy.GTEU;
    }


}
