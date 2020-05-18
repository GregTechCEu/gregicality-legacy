package gregicadditions.item.behavior;

import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.item.ItemStack;

public class HugeTurbineBehavior extends TurbineRotorBehavior {

    private static final int TOOL_DURABILITY_MULTIPLIER = 200;

    @Override
    public int getPartMaxDurability(ItemStack itemStack) {
        IngotMaterial material = getPartMaterial(itemStack);
        return material.toolDurability * TOOL_DURABILITY_MULTIPLIER;
    }

    @Override
    public double getRotorEfficiency(ItemStack itemStack) {
        SolidMaterial primaryMaterial = getPartMaterial(itemStack);
        return primaryMaterial == null ? 0.01 : primaryMaterial.toolSpeed / 8.0;
    }
}
