package gregicadditions.materials;

import java.util.ArrayList;
import java.util.List;

public class SimpleFluidMaterial {

    public static List<SimpleFluidMaterial> GA_FLUIDS = new ArrayList<>();
    public String name;
    public int rgb;

    public SimpleFluidMaterial(String name, int rgb) {
        this.name = name;
        this.rgb = rgb;
        GA_FLUIDS.add(this);
    }

}
