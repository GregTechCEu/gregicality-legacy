package gregicadditions.materials;

import com.google.common.base.CaseFormat;
import gregtech.api.unification.material.MaterialIconSet;

import java.util.ArrayList;
import java.util.List;


public class SimpleDustMaterial {

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

    String toCamelCaseString(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }


}

