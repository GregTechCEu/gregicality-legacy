package gregicadditions.item;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GAElectricStats;
import gregicadditions.item.behaviors.ProspectingToolBehaviour;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.items.metaitem.ElectricStats;
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

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static gregicadditions.item.GAMetaItems.*;

public class GAMetaItem2 extends StandardMetaItem {

    public GAMetaItem2(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        BATTERY_NIMH = addItem(1, "nickel.metal.hydride.battery").addComponents(GAElectricStats.createRechargeableBattery(7200000, GAValues.EV)).setModelAmount(8);

        BATTERY_SMALL_LITHIUM_ION = addItem(3, "small.lithium.ion.battery").addComponents(GAElectricStats.createRechargeableBattery(28800000, GAValues.IV)).setModelAmount(8);
        BATTERY_MEDIUM_LITHIUM_ION = addItem(6, "medium.lithium.ion.battery").addComponents(GAElectricStats.createRechargeableBattery(115200000, GAValues.LuV)).setModelAmount(8);
        BATTERY_LARGE_LITHIUM_ION = addItem(4, "large.lithium.ion.battery").addComponents(GAElectricStats.createRechargeableBattery(460800000, GAValues.ZPM)).setModelAmount(8);

        BATTERY_SMALL_LIS = addItem(7, "small.lithium.sulfide.battery").addComponents(GAElectricStats.createRechargeableBattery(1843200000, GAValues.UV)).setModelAmount(8);
        BATTERY_MEDIUM_LIS = addItem(2, "medium.lithium.sulfide.battery").addComponents(GAElectricStats.createRechargeableBattery(7372800000L, GAValues.UHV)).setModelAmount(8);
        BATTERY_LARGE_LIS = addItem(5, "large.lithium.sulfide.battery").addComponents(GAElectricStats.createRechargeableBattery(29491200000L, GAValues.UEV)).setModelAmount(8);

        BATTERY_SMALL_FLUORIDE = addItem(8, "small.fluoride.battery").addComponents(GAElectricStats.createRechargeableBattery(117964800000L, GAValues.UIV)).setModelAmount(8);
        BATTERY_MEDIUM_FLUORIDE = addItem(9, "medium.fluoride.battery").addComponents(GAElectricStats.createRechargeableBattery(471859200000L, GAValues.UMV)).setModelAmount(8);
        BATTERY_LARGE_FLUORIDE = addItem(10, "large.fluoride.battery").addComponents(GAElectricStats.createRechargeableBattery(1887436800000L, GAValues.UXV)).setModelAmount(8);

        GAMetaItems.PROSPECT_TOOL_MV = addItem(100, "tool.prospect.mv").addComponents(new ProspectingToolBehaviour(2, GAConfig.equipment.prospector.scanCosts[0], GAConfig.equipment.prospector.scanRadii[0])).addComponents(ElectricStats.createElectricItem(GAConfig.equipment.prospector.energyCapacity[0], 2)).setMaxStackSize(1);
        GAMetaItems.PROSPECT_TOOL_HV = addItem(101, "tool.prospect.hv").addComponents(new ProspectingToolBehaviour(3, GAConfig.equipment.prospector.scanCosts[1], GAConfig.equipment.prospector.scanRadii[1])).addComponents(ElectricStats.createElectricItem(GAConfig.equipment.prospector.energyCapacity[1], 3)).setMaxStackSize(1);
        GAMetaItems.PROSPECT_TOOL_LuV = addItem(102, "tool.prospect.luv").addComponents(new ProspectingToolBehaviour(6, GAConfig.equipment.prospector.scanCosts[2], GAConfig.equipment.prospector.scanRadii[2])).addComponents(ElectricStats.createElectricItem(GAConfig.equipment.prospector.energyCapacity[2], 6)).setMaxStackSize(1);
        GAMetaItems.PROSPECT_TOOL_ZPM = addItem(103, "tool.prospect.zpm").addComponents(new ProspectingToolBehaviour(7, GAConfig.equipment.prospector.scanCosts[3], GAConfig.equipment.prospector.scanRadii[3])).addComponents(ElectricStats.createElectricItem(GAConfig.equipment.prospector.energyCapacity[3], 7)).setMaxStackSize(1);
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
