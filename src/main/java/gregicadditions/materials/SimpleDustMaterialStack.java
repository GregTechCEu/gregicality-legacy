package gregicadditions.materials;

import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.SmallDigits;

public class SimpleDustMaterialStack extends MaterialStack {

    private final SimpleDustMaterial simpleDustMaterial;

    public SimpleDustMaterialStack(SimpleDustMaterial material, long amount) {
        super(null, amount);
        this.simpleDustMaterial = material;
    }

    @Override
    public MaterialStack copy() {
        return new SimpleDustMaterialStack(simpleDustMaterial, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleDustMaterialStack that = (SimpleDustMaterialStack) o;

        if (amount != that.amount) return false;
        return simpleDustMaterial.equals(that.simpleDustMaterial);
    }

    @Override
    public int hashCode() {
        return simpleDustMaterial.hashCode();
    }

    @Override
    public String toString() {
        String string = "";
        if (simpleDustMaterial.chemicalFormula.isEmpty()) {
            string += "?";
        } else if (simpleDustMaterial.materialComponents.size() > 1) {
            string += '(' + simpleDustMaterial.chemicalFormula + ')';
        } else {
            string += simpleDustMaterial.chemicalFormula;
        }
        if (amount > 1) {
            string += SmallDigits.toSmallDownNumbers(Long.toString(amount));
        }
        return string;
    }
}
