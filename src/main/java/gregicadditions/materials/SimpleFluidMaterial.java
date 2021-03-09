package gregicadditions.materials;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleFluidMaterial extends SimpleMaterial {

    public static List<SimpleFluidMaterial> GA_FLUIDS = new ArrayList<>();

    public int temperature;
    public Fluid fluid;
    public boolean hasPlasma;
    public Fluid plasma;


    public SimpleFluidMaterial(String name, int rgb) {
        this(name, rgb, 300, false);
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature) { this(name, rgb, temperature, false); }

    public SimpleFluidMaterial(String name, int rgb, boolean hasPlasma) { this(name, rgb, 300, hasPlasma); }

    public SimpleFluidMaterial(String name, int rgb, int temperature, boolean hasPlasma) {
        this.name = name;
        this.rgb = rgb;
        this.temperature = temperature;
        this.hasPlasma = hasPlasma;
        GA_FLUIDS.add(this);
    }

    public FluidStack getFluid(int amount) {
        return new FluidStack(fluid, amount);
    }

    public FluidStack getPlasma(int amount) { return hasPlasma ? new FluidStack(plasma, amount) : null; }

}
