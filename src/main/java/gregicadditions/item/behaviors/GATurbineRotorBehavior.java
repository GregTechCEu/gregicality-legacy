package gregicadditions.item.behaviors;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.item.ItemStack;

public class GATurbineRotorBehavior extends TurbineRotorBehavior {

    private final int toolDurabilityModifier;
    private final double rotorEfficiencyDivisor;

    public GATurbineRotorBehavior(int durabilityModifier, double efficiencyDivisor) {
        this.toolDurabilityModifier = durabilityModifier;
        this.rotorEfficiencyDivisor = efficiencyDivisor;
    }

    @Override
    public int getPartMaxDurability(ItemStack stack) {
        ToolProperty property = getPartMaterial(stack).getProperty(PropertyKey.TOOL);
        return property.toolDurability * toolDurabilityModifier;
    }

    @Override
    public double getRotorEfficiency(ItemStack stack) {
        Material material = getPartMaterial(stack);
        return material == null ? 0.01 : material.getProperty(PropertyKey.TOOL).toolSpeed / rotorEfficiencyDivisor;
    }
}
