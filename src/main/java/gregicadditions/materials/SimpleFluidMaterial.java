package gregicadditions.materials;

import com.google.common.collect.ImmutableList;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

public class SimpleFluidMaterial extends SimpleMaterial {

    public static Map<String, SimpleFluidMaterial> GA_FLUIDS = new HashMap<>();
    public int temperature;
    public Fluid fluid;
    public boolean hasPlasma;
    public Fluid plasma;

    public SimpleFluidMaterial(String name, int rgb) {
        this(name, rgb, 300, false, "");
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature) {
        this(name, rgb, temperature, false, "");
    }

    // TODO Remove
    public SimpleFluidMaterial(String name, int rgb, ImmutableList<MaterialStack> formula) {
        this(name, rgb, 300, false);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleFluidMaterial(String name, int rgb, String formula) {
        this(name, rgb, 300, false);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleFluidMaterial(String name, int rgb, String formula, boolean fancy) {
        this(name, rgb, 300, false);
        chemicalFormula = calculateChemicalFormula(formula);
        this.fancy = fancy;
    }

    public SimpleFluidMaterial(String name, int rgb, boolean hasPlasma) {
        this(name, rgb, 300, hasPlasma);
    }

    public SimpleFluidMaterial(String name, int rgb, String formula, boolean hasPlasma, boolean fancy) {
        this(name, rgb, 300, hasPlasma);
        chemicalFormula = calculateChemicalFormula(formula);
        this.fancy = fancy;
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature, boolean hasPlasma, String formula) {
        this(name, rgb, temperature, hasPlasma);
        this.chemicalFormula = formula;
    }

    public SimpleFluidMaterial(String name, int rgb, boolean hasPlasma, String formula) {
        this(name, rgb, 300, hasPlasma);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature, boolean hasPlasma) {
        this.name = name;
        this.rgb = rgb;
        this.temperature = temperature;
        this.hasPlasma = hasPlasma;
        GA_FLUIDS.put(name, this);
    }

    public FluidStack getFluid(int amount) {
        return new FluidStack(fluid, amount);
    }

    public FluidStack getPlasma(int amount) {
        return hasPlasma ? new FluidStack(plasma, amount) : null;
    }
}
