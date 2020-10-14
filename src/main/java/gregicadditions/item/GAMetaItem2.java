package gregicadditions.item;

import gregicadditions.GAValues;
import gregicadditions.capabilities.GAElectricStats;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import static gregicadditions.item.GAMetaItems.*;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class GAMetaItem2 extends StandardMetaItem {

    public GAMetaItem2(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        BATTERY_SMALL_VANADIUM = addItem(1, "small.vanadium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 4)).setModelAmount(8);
        BATTERY_SMALL_NEUTRONIUM = addItem(2, "small.neutronium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 9)).setModelAmount(8);
        BATTERY_MEDIUM_VANADIUM = addItem(3, "medium.vanadium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 5)).setModelAmount(8);
        BATTERY_MEDIUM_NAQUADRIA = addItem(4, "medium.naquadria.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 7)).setModelAmount(8);
        BATTERY_MEDIUM_NEUTRONIUM = addItem(5, "medium.neutronium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 10)).setModelAmount(8);
        BATTERY_LARGE_VANADIUM = addItem(6, "large.vanadium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 6)).setModelAmount(8);
        BATTERY_LARGE_NAQUADRIA = addItem(7, "large.naquadria.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 8)).setModelAmount(8);
        BATTERY_LARGE_NEUTRONIUM = addItem(8, "large.neutronium.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, 11)).setModelAmount(8);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> lines, ITooltipFlag tooltipFlag) {
        MetaItem<?>.MetaValueItem item = getItem(itemStack);
        if (item == null) return;
        String unlocalizedTooltip = "metaitem." + item.unlocalizedName + ".tooltip";
        if (I18n.hasKey(unlocalizedTooltip)) {
            lines.addAll(Arrays.asList(I18n.format(unlocalizedTooltip).split("/n")));
        }

        IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null) {
            lines.add(I18n.format("metaitem.generic.electric_item.tooltip",
                    electricItem.getCharge(),
                    electricItem.getMaxCharge(),
                    GAValues.VN[electricItem.getTier()]));
        }

        IFluidHandlerItem fluidHandler = ItemHandlerHelper.copyStackWithSize(itemStack, 1)
                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        if (fluidHandler != null) {
            IFluidTankProperties fluidTankProperties = fluidHandler.getTankProperties()[0];
            FluidStack fluid = fluidTankProperties.getContents();
            if (fluid != null) {
                lines.add(I18n.format("metaitem.generic.fluid_container.tooltip",
                        fluid.amount,
                        fluidTankProperties.getCapacity(),
                        fluid.getLocalizedName()));
            } else lines.add(I18n.format("metaitem.generic.fluid_container.tooltip_empty"));
        }

        for (IItemBehaviour behaviour : getBehaviours(itemStack)) {
            behaviour.addInformation(itemStack, lines);
        }
    }
}
