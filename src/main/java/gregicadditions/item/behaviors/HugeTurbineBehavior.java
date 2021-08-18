package gregicadditions.item.behaviors;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.item.ItemStack;

public class HugeTurbineBehavior extends TurbineRotorBehavior {

    private static final int TOOL_DURABILITY_MULTIPLIER = 200;

    @Override
    public int getPartMaxDurability(ItemStack itemStack) {
        ToolProperty property = getPartMaterial(itemStack).getProperty(PropertyKey.TOOL);
        return property.toolDurability * TOOL_DURABILITY_MULTIPLIER;
    }

    @Override
    public double getRotorEfficiency(ItemStack itemStack) {
        Material primaryMaterial = getPartMaterial(itemStack);
        return primaryMaterial == null ? 0.01 : primaryMaterial.getProperty(PropertyKey.TOOL).toolSpeed / 8.0;
    }
}
