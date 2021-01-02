package gregicadditions.item.behavior;

import gregicadditions.worldgen.DimensionChunkCoords;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;

import java.util.List;

public class DataStickFluidSamplerBehavior implements IItemBehaviour {

    public static DataStickFluidSamplerBehavior getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        }
        MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
        MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
        if (valueItem == null) {
            return null;
        }
        return (DataStickFluidSamplerBehavior) valueItem.getBehaviours().stream().filter(o -> o instanceof DataStickFluidSamplerBehavior).findFirst().orElse(null);
    }


    protected NBTTagCompound getFluidSampleTag(ItemStack itemStack) {
        return itemStack.getSubCompound("GA.FluidSample");
    }

    protected NBTTagCompound getOrCreateFluidSampleTag(ItemStack itemStack) {
        return itemStack.getOrCreateSubCompound("GA.FluidSample");
    }

    public DimensionChunkCoords getDimensionChunkCoords(ItemStack itemStack) {
        NBTTagCompound compound = getFluidSampleTag(itemStack);
        if (compound == null) {
            return null;
        }
        return DimensionChunkCoords.readFromNBT(compound.getCompoundTag("coords"));
    }

    public void setDimensionChunkCoords(ItemStack itemStack, DimensionChunkCoords oilWorldInfo) {
        NBTTagCompound compound = getOrCreateFluidSampleTag(itemStack);
        compound.setTag("coords", oilWorldInfo.writeToNBT());
    }

    public PumpjackHandler.OilWorldInfo getOilWorldInfo(ItemStack itemStack) {
        NBTTagCompound compound = getFluidSampleTag(itemStack);
        if (compound == null) {
            return null;
        }
        if (compound.getBoolean("isEmpty")) {
            return null;
        }

        return PumpjackHandler.OilWorldInfo.readFromNBT(compound.getCompoundTag("oilWorldInfo"));
    }

    public void setOilWorldInfo(ItemStack itemStack, PumpjackHandler.OilWorldInfo oilWorldInfo) {
        NBTTagCompound compound = getOrCreateFluidSampleTag(itemStack);
        if (oilWorldInfo == null) {
            compound.setBoolean("isEmpty", true);
        } else {
            compound.setBoolean("isEmpty", false);
            compound.setTag("oilWorldInfo", oilWorldInfo.writeToNBT());
        }
    }

    @Override
    public void addInformation(ItemStack stack, List<String> lines) {
        DimensionChunkCoords coords = getDimensionChunkCoords(stack);
        PumpjackHandler.OilWorldInfo oilWorldInfo = getOilWorldInfo(stack);
        if (oilWorldInfo == null) {
            return;
        }
        if (oilWorldInfo.getType() == null) {
            lines.add(I18n.format("metaitem.data_stick.tooltip.empty"));
        } else {
            String dimName;
            try {
                dimName = DimensionType.getById(coords.dimension).getName();
            } catch (IllegalArgumentException e) {
                dimName = "id" + coords.dimension;
            }
            lines.add(I18n.format("metaitem.data_stick.tooltip.coords", dimName, coords.getXStart(), coords.getZStart()));
            lines.add(I18n.format("metaitem.data_stick.tooltip.capacity", oilWorldInfo.capacity));
            lines.add(I18n.format("metaitem.data_stick.tooltip.current", oilWorldInfo.current));
            lines.add(I18n.format("metaitem.data_stick.tooltip.fluid", I18n.format(oilWorldInfo.getType().getFluid().getUnlocalizedName())));

        }
    }
}
