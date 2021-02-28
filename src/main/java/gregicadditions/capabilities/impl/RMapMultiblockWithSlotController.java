package gregicadditions.capabilities.impl;

import gregicadditions.gui.GAGuiTextures;
import gregtech.api.GregTechAPI;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class RMapMultiblockWithSlotController extends GARecipeMapMultiblockController {

    private LimitedItemStackHandler controllerSlot;

    public RMapMultiblockWithSlotController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, Class<?> itemsAllowed) {
        super(metaTileEntityId, recipeMap);
        controllerSlot = new LimitedItemStackHandler(itemsAllowed);
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer player) {
        ModularUI.Builder builder = super.createUITemplate(player);
        builder.image(148, 104, 21, 21, GAGuiTextures.CONTROLLER_SLOT);
        builder.widget(new SlotWidget(controllerSlot, 0, 151, 107).setBackgroundTexture(GuiTextures.SLOT));
        return builder;
    }

    protected ItemStack getStackInSlot() {
        return controllerSlot.getStackInSlot(0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        GTUtility.writeItems(controllerSlot, "ControllerSlot", data);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        GTUtility.readItems(controllerSlot, "ControllerSlot", data);
    }

    @Override
    public void onRemoval() {
        Block.spawnAsEntity(getWorld(), getPos(), getStackInSlot());
    }

    // This could use more work to handle more cases
    private class LimitedItemStackHandler extends ItemStackHandler {

        private final Class<?> itemsAllowed;

        public LimitedItemStackHandler(Class<?> itemsAllowed) {
            super(1);
            this.itemsAllowed = itemsAllowed;
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (isItemValid(slot, stack))
                super.insertItem(slot, stack, simulate);
            return ItemStack.EMPTY;
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if (itemsAllowed.isAssignableFrom(stack.getItem().getClass()))
                return true;
            else {
                MetaTileEntity mte;
                return (mte = getMTEFromIS(stack)) != null && itemsAllowed.isAssignableFrom(mte.getClass());
            }
        }

        private MetaTileEntity getMTEFromIS(ItemStack stack) {
            String unlocalizedName = stack.getItem().getUnlocalizedNameInefficiently(stack);
            MetaTileEntity mte = null;
            if ((unlocalizedName.contains("gtadditions.machine") || unlocalizedName.contains("gregtech.machine")))
                mte = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObjectById(stack.getItemDamage());
            return mte;
        }
    }
}
