package gregicadditions.integrations.FECompat.Energy;

import gregicadditions.GAValues;
import gregicadditions.integrations.FECompat.Constants;
import gregtech.api.capability.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.function.BiConsumer;

import static gregicadditions.integrations.FECompat.Constants.safeCastLongToInt;

public class GregicItemContainerWrapper implements IElectricItem {

    private final IEnergyStorage itemStack;

    public GregicItemContainerWrapper(IEnergyStorage itemStack) {
        this.itemStack = itemStack;
    }

    public boolean isValid() {
        return itemStack != null && !(itemStack instanceof TileEntity);
    }

    @Override
    public boolean canProvideChargeExternally() {
        return itemStack.canExtract();
    }

    @Override
    public void addChargeListener(BiConsumer<ItemStack, Long> biConsumer) {
        // TODO Do we implement this? Probably not
    }

    @Override
    public long charge(long amount, int tier, boolean ignoreTransferLimit, boolean simulate) {
        return itemStack.receiveEnergy(safeCastLongToInt(amount * Constants.RATIO_LONG), simulate) / Constants.RATIO_LONG;

        //int rfAmount = safeCastLongToInt(amount * Constants.RATIO_LONG);
        //int chargeAmount = itemStack.receiveEnergy(rfAmount, true);

        //if (!simulate)
        //    itemStack.receiveEnergy(rfAmount, false);

        //return chargeAmount / Constants.RATIO_LONG;
    }

    @Override
    public long discharge(long amount, int tier, boolean ignoreTransferLimit, boolean externally, boolean simulate) {
        return itemStack.extractEnergy(safeCastLongToInt(amount * Constants.RATIO_LONG), simulate) / Constants.RATIO_LONG;
    }

    @Override
    public long getTransferLimit() {
        return itemStack.receiveEnergy(Integer.MAX_VALUE, true) / Constants.RATIO_LONG;
    }

    @Override
    public long getMaxCharge() {
        return itemStack.getMaxEnergyStored() / Constants.RATIO_LONG;
    }

    @Override
    public long getCharge() {
        return itemStack.getEnergyStored() / Constants.RATIO_LONG;
    }

    @Override
    public int getTier() {
        return GAValues.MAX; // TODO What should this really be?
    }
}
