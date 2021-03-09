package gregicadditions.materials;

public enum EnumIonsGroups {

    // +1
    AMMONIUM("NH4"),
    HYDRONIUM("H3O"),
    MERCURY1("Hg2"),

    // -1
    DHPHOSPHITE("H2PO3"),
    DHPHOSPHATE("H2PO4"),
    HCARBONATE("HCO3"),
    HSULFITE("HSO3"),
    HSULFATE("HSO4"),
    NITRITE("NO2"),
    NITRATE("NO3"),
    HYDROXIDE("OH"),
    ACETATE("CH3COO"),
    CHROMITE("CrO2"),
    CYANIDE("CN"),
    CYANATE("CNO"),
    THIOCYANATE("CNS"),
    PERMANGANATE("MnO4"),
    HYPOCHLORITE("ClO"),
    CHLORITE("ClO2"),
    CHLORATE("ClO3"),
    PERCHLORATE("ClO4"),
    HYPOBROMITE("BrO"),
    BROMITE("BrO2"),
    BROMATE("BrO3"),
    PERBROMATE("BrO4"),
    HYPOIODITE("IO"),
    IODITE("IO2"),
    IODATE("IO3"),
    PERIODATE("IO4"),
    AZIDE("N3"),

    // -2
    HPHOSPHITE("HPO3"),
    HPHOSPHATE("HPO4"),
    CARBONATE("CO3"),
    SULFITE("SO3"),
    SULFATE("SO4"),
    THIOSULFATE("S2O3"),
    OXALATE("C2O4"),
    CHROMATE("CrO4"),
    DICHROMATE("Cr2O7"),
    DISULFIDE("S2"),
    SULFIDE("S"),

    // -3
    PHOSPHITE("PO3"),
    ARSENITE("AsO3"),
    ARSENATE("AsO4"),
    NITRIDE("N3"),

    // Oxidation states
    OXIDE("O"),
    OXIDE2("O2"),
    OXIDE3("O3"),
    OXIDE4("O4"),
    OXIDE5("O5"),
    OXIDE6("O6"),
    OXIDE7("O7"),
    OSIDE8("O8"),

    // Misc
    WATER("H2O"),
    PHOSPHATE("PO4"),
    AMMONIA("NH3"),

    // Simple OChem functional groups
    ALCOHOL("OH"),
    THIOL("SH"),
    ALDEHYDE("COH"),
    KETONE("COCH3"),
    CARBOXYLIC("COOH"),
    NITRILE("CN"),
    NITRO("NO2");

    public final String FORMULA;

    EnumIonsGroups(String formula) {
        this.FORMULA = formula;
    }

    public String f() {
        return FORMULA;
    }

    public String fGroup() {
        return "(" + FORMULA + ")";
    }

    public String fGroup(int count) {
        return "(" + FORMULA + ")" + count;
    }
}
