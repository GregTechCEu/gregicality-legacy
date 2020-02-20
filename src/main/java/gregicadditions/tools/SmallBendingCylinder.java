package gregicadditions.tools;

import gregtech.common.tools.ToolBase;
import net.minecraft.item.ItemStack;

public class SmallBendingCylinder extends ToolBase {
	@Override
	public int getToolDamagePerBlockBreak(ItemStack stack) {
		return 2;
	}

	@Override
	public int getToolDamagePerContainerCraft(ItemStack stack) {
		return 5;
	}

	@Override
	public float getBaseDamage(ItemStack stack) {
		return 1.5F;
	}
}
