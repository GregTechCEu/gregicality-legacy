package gregicadditions.materials;

import gregtech.api.unification.material.type.IngotMaterial;

import java.util.HashMap;
import java.util.Map;

public class IsotopeMaterial extends EnrichmentProcess {

    public static Map<IngotMaterial, IsotopeMaterial> REGISTRY = new HashMap<>();

    private final IngotMaterial from;

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
        this.from = from;
        this.radioactiveMaterial = material;
        this.nuclide = nuclide;
        REGISTRY.put(from, this);
    }

    public IngotMaterial getMaterial() {
        return from;
    }

    public RadioactiveMaterial getRadioactiveMaterial() {
        return radioactiveMaterial;
    }

    @Override
    public String toString() {
        return from.toString();
    }
}
