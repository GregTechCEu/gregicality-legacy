package gregicadditions.materials;

import gregicadditions.GAMaterials;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.SmallDigits;

public class SimpleDustMaterialStack extends MaterialStack {

    public final SimpleDustMaterial simpleDustMaterial;

    /**
     * Cant generate a new MarkerMaterial here because if we have
     * several SimpleDustMaterialStack of the same material
     * it will try to create another MarkerMaterial with same name and crash.
     * Instead use placeholder material. Technetium is used by GTCE
     * in Material::getAverageMass() for materials with empty
     * materialComponent lists. This material won't actually be used for anything
     * since we overwrite every method of MaterialStack.
     */
    public SimpleDustMaterialStack(SimpleDustMaterial material, long amount) {
        super(GAMaterials.Technetium, amount);
        this.simpleDustMaterial = material;
    }

    @Override
    public MaterialStack copy() {
        return new SimpleDustMaterialStack(simpleDustMaterial, amount);
    }

    @Override
    public MaterialStack copy(long amount) {
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
        } else if (simpleDustMaterial.chemicalFormula.length() > 1) {
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
