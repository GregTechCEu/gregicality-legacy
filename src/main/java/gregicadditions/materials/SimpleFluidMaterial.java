package gregicadditions.materials;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFluidMaterial extends SimpleMaterial {

    public static Map<String, SimpleFluidMaterial> GA_FLUIDS = new HashMap<>();
    public int temperature;
    public Fluid fluid;
    public boolean hasPlasma;
    public Fluid plasma;
    public final ImmutableList<MaterialStack> materialComponents;
    public String chemicalFormula;


    public SimpleFluidMaterial(String name, int rgb) {
        this(name, rgb, 300, false, null);
    }

    public SimpleFluidMaterial(String name, int rgb, ImmutableList<MaterialStack> materialComponents) {
        this(name, rgb, 300, false, materialComponents);
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature) {
        this(name, rgb, temperature, false, null);
    }
    // TODO Remove this once PR #398 is merged
    public SimpleFluidMaterial(String name, int rgb, ImmutableList<MaterialStack> formula) {
        this(name, rgb, 300, false);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleFluidMaterial(String name, int rgb, String formula) {
        this(name, rgb, 300, false);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature) { this(name, rgb, temperature, false); }

    public SimpleFluidMaterial(String name, int rgb, int temperature, ImmutableList<MaterialStack> materialComponents) {
        this(name, rgb, temperature, false, materialComponents);
    }

    public SimpleFluidMaterial(String name, int rgb, boolean hasPlasma) {
        this(name, rgb, 300, hasPlasma, null);
    }

    public SimpleFluidMaterial(String name, int rgb, boolean hasPlasma, ImmutableList<MaterialStack> materialComponents) {
        this(name, rgb, 300, hasPlasma, materialComponents);
    }

    public SimpleFluidMaterial(String name, int rgb, int temperature, boolean hasPlasma, ImmutableList<MaterialStack> materialComponents) {
        this.name = name;
        this.rgb = rgb;
        this.temperature = temperature;
        this.hasPlasma = hasPlasma;
        this.materialComponents = materialComponents;
        this.chemicalFormula = calculateChemicalFormula();
        GA_FLUIDS.put(name, this);
    }

    public FluidStack getFluid(int amount) {
        return new FluidStack(fluid, amount);
    }

    public FluidStack getPlasma(int amount) { return hasPlasma ? new FluidStack(plasma, amount) : null; }

    private String calculateChemicalFormula() {
        if (materialComponents != null && !materialComponents.isEmpty()) {
            StringBuilder components = new StringBuilder();
            for (MaterialStack component : materialComponents)
                components.append(component.toString());
            return components.toString();
        }
        return "";
    }

}
