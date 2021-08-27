package gregicadditions.materials;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

public class GAOrePrefix {
        /*
                //nuclear stuff
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

    public static final List<OrePrefix> RICH_ORES = new ArrayList<>();
    public static final List<OrePrefix> POOR_ORES = new ArrayList<>();
    public static final List<OrePrefix> PURE_ORES = new ArrayList<>();

    //public static final Predicate<Material> isNuclear = mat -> mat.hasFlag(GENERATE_NUCLEAR_COMPOUND);

    public static void initPrefixes() {
        opticalFiberHex = new OrePrefix("opticalFiberHex", M * 8, null, null, ENABLE_UNIFICATION, null);
        opticalFiberOctal = new OrePrefix("opticalFiberOctal", M * 4, null, null, ENABLE_UNIFICATION, null);
        opticalFiberQuadruple = new OrePrefix("opticalFiberQuadruple", M * 2, null, null, ENABLE_UNIFICATION, null);
        opticalFiberDouble = new OrePrefix("opticalFiberDouble", M, null, null, ENABLE_UNIFICATION, null);
        opticalFiberSingle = new OrePrefix("opticalFiberSingle", M / 2, null, null, ENABLE_UNIFICATION, null);

        String[] stoneTypes = {"", "Blackgranite", "Redgranite", "Marble", "Basalt", "Sand", "Gravel", "Netherrack", "Endstone"};
        Material[] secondaryMaterials = {Materials.Stone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Marble,
                Materials.Basalt, Materials.SiliconDioxide, Materials.Flint, Materials.Netherrack, Materials.Endstone};

        // todo this still needs to be redone, but at least "works" now
        for (int i = 0; i < stoneTypes.length; i++) {
            MaterialIconType current = new MaterialIconType("oreRich" + stoneTypes[i]);
            OrePrefix currentPrefix = new OrePrefix("oreRich" + stoneTypes[i], M * 2, null, current, ENABLE_UNIFICATION, null);
            currentPrefix.addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            RICH_ORES.add(currentPrefix);

            current = new MaterialIconType("orePoor" + stoneTypes[i]);
            currentPrefix = new OrePrefix("orePoor" + stoneTypes[i], M / 2, null, current, ENABLE_UNIFICATION, null);
            currentPrefix.addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            POOR_ORES.add(currentPrefix);

            current = new MaterialIconType("orePure" + stoneTypes[i]);
            currentPrefix = new OrePrefix("orePure" + stoneTypes[i], M * 4, null, current, ENABLE_UNIFICATION, null);
            currentPrefix.addSecondaryMaterial(new MaterialStack(secondaryMaterials[i], OrePrefix.dust.materialAmount));
            PURE_ORES.add(currentPrefix);
        }
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
