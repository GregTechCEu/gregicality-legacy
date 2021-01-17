package gregicadditions.materials;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleFluidMaterial {

    public static List<SimpleFluidMaterial> GA_FLUIDS = new ArrayList<>();
    public String name;
    public int rgb;
    public int temperature;
    public Fluid fluid;

    public SimpleFluidMaterial(String name, int rgb) {
        this(name, rgb, 300);
    }
    
    public SimpleFluidMaterial(String name, int rgb, int temperature) {
        this.name = name;
        this.rgb = rgb;
        this.temperature = temperature;
        GA_FLUIDS.add(this);
    }

    public FluidStack getFluid(int amount) {
        return new FluidStack(fluid, amount);
    }

}
