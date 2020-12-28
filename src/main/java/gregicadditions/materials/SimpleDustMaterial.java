package gregicadditions.materials;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableList;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SimpleDustMaterial {
    //TODO convert GA_DUSTS into GTControlledRegistry
    public static Map<Short, SimpleDustMaterial> GA_DUSTS = new HashMap<>();
    public String name;
    public int rgb;
    public MaterialIconSet materialIconSet;
    public int id;
    public final ImmutableList<MaterialStack> materialComponents;
    public final String chemicalFormula;

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet, ImmutableList<MaterialStack> materialComponents) {
        this.name = name;
        this.rgb = rgb;
        this.materialIconSet = materialIconSet;
        this.id = id;
        this.materialComponents = materialComponents;
        this.chemicalFormula = calculateChemicalFormula();
        GA_DUSTS.put(id, this);
    }

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet) {
        this.name = name;
        this.rgb = rgb;
        this.materialIconSet = materialIconSet;
        this.id = id;
        this.materialComponents = null;
        this.chemicalFormula = "";
        GA_DUSTS.put(id, this);
    }

    public String getOre() {
        return "dust" + toCamelCaseString(name);
    }

    public ItemStack getItemStack(int amount) {
        ItemStack itemStack = OreDictUnifier.get(this.getOre());
        itemStack.setCount(amount);
        return itemStack;
    }

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    String toCamelCaseString(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }

    private String calculateChemicalFormula() {
        if (!materialComponents.isEmpty()) {
            StringBuilder components = new StringBuilder();
            for (MaterialStack component : materialComponents)
                components.append(component.toString());
            return components.toString();
        }
        return "";
    }


}

