package gregicadditions.materials;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

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

    public FluidStack getFluid(int amount) {
        return new FluidStack(FluidRegistry.getFluid(this.name), amount);
    }

}
