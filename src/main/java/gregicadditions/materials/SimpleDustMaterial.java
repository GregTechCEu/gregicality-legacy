package gregicadditions.materials;

import gregtech.api.unification.material.MaterialIconSet;

import java.util.ArrayList;
import java.util.List;


public class SimpleDustMaterial {

        public static List<SimpleDustMaterial> GA_DUSTS = new ArrayList<>();
        public String name;
        public int rgb;
        public MaterialIconSet materialIconSet;

        public SimpleDustMaterial(String name, int rgb, MaterialIconSet materialIconSet) {
            this.name = name;
            this.rgb = rgb;
            this.materialIconSet = materialIconSet;
            GA_DUSTS.add(this);
        }

}

