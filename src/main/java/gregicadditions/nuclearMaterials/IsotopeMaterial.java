package gregicadditions.nuclearMaterials;

import gregtech.api.unification.material.Material;

import java.util.HashMap;
import java.util.Map;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD;

public class IsotopeMaterial extends EnrichmentProcess {

    public static Map<Material, IsotopeMaterial> REGISTRY = new HashMap<>();

    private final int nuclide;
    private final RadioactiveMaterial radioactiveMaterial;

    public boolean fertile = false;
    public boolean fissile = false;
    public int baseHeat = 10;

    public final Map<IsotopeMaterial, Integer> isotopeDecay = new HashMap<>();

    public IsotopeMaterial(int metaItemSubId, RadioactiveMaterial material, int nuclide, long materialGenerationFlags) {
        this(new Material.Builder(metaItemSubId, material.getMaterial().toString() + "" + nuclide)
                .ingot(material.getMaterial().getHarvestLevel())
                .color(material.getMaterial().getMaterialRGB())
                .iconSet(material.getMaterial().getMaterialIconSet())
                .components(material.getMaterial().getMaterialComponents())
                //.element(material.getMaterial().getElement()) // todo
                .build(), material, nuclide);
    }

    public IsotopeMaterial(Material from, RadioactiveMaterial material, int nuclide) {
        this.material = from;
        this.material.addFlag(GENERATE_LONG_ROD, GENERATE_ISOTOPES_COMPOUND, GENERATE_NUCLEAR_COMPOUND); // disable replication
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
