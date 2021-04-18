package gregicadditions.materials;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableList;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;


public class SimpleDustMaterial extends SimpleMaterial {
    //TODO convert GA_DUSTS into GTControlledRegistry
    public static Map<Short, SimpleDustMaterial> GA_DUSTS = new HashMap<>();

    public MaterialIconSet materialIconSet;
    public int id;

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet, ImmutableList<MaterialStack> materialComponents) {
        this(name, rgb, id, materialIconSet);
        this.chemicalFormula = calculateChemicalFormula(materialComponents);
    }

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet) {
        if (GA_DUSTS.containsKey(id))
            throw new IllegalArgumentException("Tried to reassign id " + id + " to " + name + ", but it is already assigned to " + GA_DUSTS.get(id).name);
        this.name = name;
        this.rgb = rgb;
        this.materialIconSet = materialIconSet;
        this.id = id;
        this.chemicalFormula = "";
        GA_DUSTS.put(id, this);
    }

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet, String formula) {
        this(name, rgb, id, materialIconSet);
        this.chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet, String formula, boolean fancy) {
        this(name, rgb, id, materialIconSet, formula);
        this.fancy = fancy;

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
}

