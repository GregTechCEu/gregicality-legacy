package gregicadditions.item.behaviors;

import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.item.ItemStack;

public class LargeTurbineBehavior extends TurbineRotorBehavior {

    private static final int TOOL_DURABILITY_MULTIPLIER = 150;

    @Override
    public int getPartMaxDurability(ItemStack itemStack) {
        IngotMaterial material = getPartMaterial(itemStack);
        return material.toolDurability * TOOL_DURABILITY_MULTIPLIER;
    }

    public double getRotorEfficiency(ItemStack itemStack) {
        SolidMaterial primaryMaterial = getPartMaterial(itemStack);
        return primaryMaterial == null ? 0.01 : primaryMaterial.toolSpeed / 16.0;
    }
}
