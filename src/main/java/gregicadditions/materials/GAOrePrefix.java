package gregicadditions.materials;

import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

public class GAOrePrefix {
        /*
                //todo nuclear stuff
                public static final OrePrefix dioxide = new OrePrefix("dioxide", M / 3, null, GAMaterialIconType.dioxide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix hexafluoride = new OrePrefix("hexafluoride", M / 7, null, GAMaterialIconType.hexafluoride, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix nitrite = new OrePrefix("nitrite", M / 2, null, GAMaterialIconType.nitrite, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix nitride = new OrePrefix("nitride", M / 2, null, GAMaterialIconType.nitride, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix carbide = new OrePrefix("carbide", M / 2, null, GAMaterialIconType.carbide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix zirconiumAlloy = new OrePrefix("zirconiumAlloy", M / 2, null, GAMaterialIconType.zirconiumAlloy, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix oxide = new OrePrefix("oxide", M / 2, null, GAMaterialIconType.oxide, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelNitride = new OrePrefix("fuelNitride", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelCarbide = new OrePrefix("fuelCarbide", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelZirconiumAlloy = new OrePrefix("fuelZirconiumAlloy", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelOxide = new OrePrefix("fuelOxide", M / 2, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelPure = new OrePrefix("fuelPure", M, null, GAMaterialIconType.nuclearFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix fuelTRISO = new OrePrefix("fuelTRISO", M / 4, null, GAMaterialIconType.fuelTRISO, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix depletedFuelNitride = new OrePrefix("depletedFuelNitride", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix depletedFuelZirconiumAlloy = new OrePrefix("depletedFuelZirconiumAlloy", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix depletedFuelOxide = new OrePrefix("depletedFuelOxide", M / 2, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix depletedFuelTRISO = new OrePrefix("depletedFuelTRISO", M / 4, null, GAMaterialIconType.depletedFuelTRISO, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
                public static final OrePrefix depletedFuel = new OrePrefix("depletedFuel", M, null, GAMaterialIconType.depletedFuel, ENABLE_UNIFICATION, hasIngotProperty.and(isNuclear));
    */

    public static OrePrefix opticalFiberHex;
    public static OrePrefix opticalFiberOctal;
    public static OrePrefix opticalFiberQuadruple;
    public static OrePrefix opticalFiberDouble;
    public static OrePrefix opticalFiberSingle;

    //public static final Predicate<Material> isNuclear = mat -> mat.hasFlag(GENERATE_NUCLEAR_COMPOUND);

    public static void initPrefixes() {
        opticalFiberHex = new OrePrefix("opticalFiberHex", M * 8, null, null, ENABLE_UNIFICATION, null);
        opticalFiberOctal = new OrePrefix("opticalFiberOctal", M * 4, null, null, ENABLE_UNIFICATION, null);
        opticalFiberQuadruple = new OrePrefix("opticalFiberQuadruple", M * 2, null, null, ENABLE_UNIFICATION, null);
        opticalFiberDouble = new OrePrefix("opticalFiberDouble", M, null, null, ENABLE_UNIFICATION, null);
        opticalFiberSingle = new OrePrefix("opticalFiberSingle", M / 2, null, null, ENABLE_UNIFICATION, null);
    }

    public static void initIconSets() {

        //nuclear stuff
        //public final static MaterialIconType dioxide = new MaterialIconType("dioxide");
        //public final static MaterialIconType nitride = new MaterialIconType("nitride");
        //public final static MaterialIconType nitrite = new MaterialIconType("nitrite");
        //public final static MaterialIconType hexafluoride = new MaterialIconType("hexafluoride");
        //public final static MaterialIconType carbide = new MaterialIconType("carbide");
        //public final static MaterialIconType zirconiumAlloy = new MaterialIconType("zirconiumAlloy");
        //public final static MaterialIconType oxide = new MaterialIconType("oxide");
        //public final static MaterialIconType nuclearFuel = new MaterialIconType("nuclearFuel");
        //public final static MaterialIconType depletedFuel = new MaterialIconType("depletedFuel");
        //public final static MaterialIconType fuelTRISO = new MaterialIconType("fuelTriso");
        //public final static MaterialIconType depletedFuelTRISO = new MaterialIconType("depletedFuelTriso");
    }
}
