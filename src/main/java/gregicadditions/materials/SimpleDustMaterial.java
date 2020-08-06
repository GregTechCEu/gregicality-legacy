package gregicadditions.materials;

import com.google.common.base.CaseFormat;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MaterialIconSet;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class SimpleDustMaterial {
    //TODO convert GA_DUSTS into GTControlledRegistry
    public static List<SimpleDustMaterial> GA_DUSTS = new ArrayList<>();
    public String name;
    public int rgb;
    public MaterialIconSet materialIconSet;
    public int id;

    public SimpleDustMaterial(String name, int rgb, short id, MaterialIconSet materialIconSet) {
        this.name = name;
        this.rgb = rgb;
        this.materialIconSet = materialIconSet;
        this.id = id;
        GA_DUSTS.add(this);
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

