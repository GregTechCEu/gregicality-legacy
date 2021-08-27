//package gregicadditions.nuclearMaterials;
//
//import com.google.common.collect.ImmutableList;
//import gregtech.api.items.metaitem.MetaItem;
//import gregtech.api.unification.Element;
//import gregtech.api.unification.material.Material;
//import gregtech.api.unification.material.info.MaterialIconSet;
//import gregtech.api.unification.stack.MaterialStack;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static gregicadditions.GAMaterials.*;
//
//public class RadioactiveMaterial extends EnrichmentProcess { todo nuclear rework
//
//    public static Map<Material, RadioactiveMaterial> REGISTRY = new HashMap<>();
//
//
//    public final Map<IsotopeMaterial, Integer> composition = new HashMap<>(3);
//    public int complexity = 100;
//    public MetaItem<?>.MetaValueItem waste;
//
//
//    public RadioactiveMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, float toolSpeed, float attackDamage, int toolDurability, int blastFurnaceTemperature) {
//        this(new IngotMaterial(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element, toolSpeed, attackDamage, toolDurability, blastFurnaceTemperature));
//
//    }
//
//    public RadioactiveMaterial(Material from) {
//        this.material = from;
//        this.material.addFlag(GENERATE_NUCLEAR_COMPOUND); // disable_replication
//        REGISTRY.put(from, this);
//    }
//
//
//    @Override
//    public String toString() {
//        return material.toString();
//    }
//}
