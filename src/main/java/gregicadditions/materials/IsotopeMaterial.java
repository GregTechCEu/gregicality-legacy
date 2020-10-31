package gregicadditions.materials;

import gregtech.api.unification.material.type.IngotMaterial;

import java.util.HashMap;
import java.util.Map;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.type.SolidMaterial.MatFlags.GENERATE_LONG_ROD;

public class IsotopeMaterial extends EnrichmentProcess {

    public static Map<IngotMaterial, IsotopeMaterial> REGISTRY = new HashMap<>();

    private final int nuclide;
    private final RadioactiveMaterial radioactiveMaterial;

    public boolean fertile = false;
    public boolean fissile = false;
    public int baseHeat = 10;

    public final Map<IsotopeMaterial, Integer> isotopeDecay = new HashMap<>();

    public IsotopeMaterial(int metaItemSubId, RadioactiveMaterial material, int nuclide, long materialGenerationFlags) {
        this(new IngotMaterial(metaItemSubId, material.getMaterial().toString() + "" + nuclide, material.getMaterial().materialRGB, material.getMaterial().materialIconSet, material.getMaterial().harvestLevel, material.getMaterial().materialComponents, materialGenerationFlags, material.getMaterial().element, 0, 0, 0, 0), material, nuclide);
    }

    public IsotopeMaterial(IngotMaterial from, RadioactiveMaterial material, int nuclide) {
        this.material = from;
        this.material.addFlag(GENERATE_LONG_ROD | GENERATE_ISOTOPES_COMPOUND | DISABLE_REPLICATION);
        this.radioactiveMaterial = material;
        this.nuclide = nuclide;
        REGISTRY.put(from, this);
    }

    public RadioactiveMaterial getRadioactiveMaterial() {
        return radioactiveMaterial;
    }

    @Override
    public String toString() {
        return material.toString();
    }
}
