package gregicadditions.materials;

import com.google.common.collect.ImmutableList;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.Element;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.stack.MaterialStack;

import java.util.*;

public class RadioactiveMaterial extends EnrichmentProcess {

    public static Map<IngotMaterial, RadioactiveMaterial> REGISTRY = new HashMap<>();

    private final IngotMaterial from;
    public final List<IsotopeMaterial> fissile = new ArrayList<>();
    public final List<IsotopeMaterial> fertile = new ArrayList<>();
    public final Map<IsotopeMaterial, Integer> composition = new HashMap<>(3);
    public int complexity = 100;
    public MetaItem<?>.MetaValueItem waste;


    public RadioactiveMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, float toolSpeed, float attackDamage, int toolDurability, int blastFurnaceTemperature) {
        this(new IngotMaterial(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element, toolSpeed, attackDamage, toolDurability, blastFurnaceTemperature));

    }

    public RadioactiveMaterial(IngotMaterial from) {
        this.from = from;
        REGISTRY.put(from, this);
    }


    public IngotMaterial getMaterial() {
        return from;
    }

    @Override
    public String toString() {
        return from.toString();
    }
}
