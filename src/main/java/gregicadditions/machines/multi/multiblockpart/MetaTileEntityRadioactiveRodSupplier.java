package gregicadditions.machines.multi.multiblockpart;

import gregicadditions.item.behavior.RadioactiveRodBehavior;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class MetaTileEntityRadioactiveRodSupplier extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<MetaTileEntityRadioactiveRodSupplier> {

    private InventoryRodHolder inventory;

    public MetaTileEntityRadioactiveRodSupplier(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 0);
        this.inventory = new InventoryRodHolder();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityRadioactiveRodSupplier(metaTileEntityId);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return ModularUI.defaultBuilder()
                .label(6, 6, getMetaFullName())
                .slot(inventory, 0, 79, 36, GuiTextures.SLOT)
                .bindPlayerInventory(entityPlayer.inventory)
                .build(getHolder(), entityPlayer);
    }

    @Override
    public MultiblockAbility<MetaTileEntityRadioactiveRodSupplier> getAbility() {
        return MultiblockAbilities.ABILITY_RADIOACTIVE_ROD_HOLDER;
    }

    @Override
    public void registerAbilities(List<MetaTileEntityRadioactiveRodSupplier> list) {
        list.add(this);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("inventory", inventory.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.inventory.deserializeNBT(data.getCompoundTag("inventory"));
    }

    private class InventoryRodHolder extends ItemStackHandler {
        public InventoryRodHolder() {
            super(1);
        }

        public int getSlotLimit(int slot) {
            return 1;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (RadioactiveRodBehavior.getInstanceFor(stack) != null) {
                return super.insertItem(slot, stack, simulate);
            }
            return stack;
        }
    }


}
