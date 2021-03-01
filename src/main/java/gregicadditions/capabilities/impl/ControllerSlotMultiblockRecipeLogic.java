package gregicadditions.capabilities.impl;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ControllerSlotMultiblockRecipeLogic extends GAMultiblockRecipeLogic {

    ItemStack lastControllerItem;

    public ControllerSlotMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    protected boolean checkRecipeInputsDirty(IItemHandler importInventory, IMultipleTankHandler importFluids) {
        if (lastControllerItem == null)
            lastControllerItem = ItemStack.EMPTY;

        ItemStack previousStack = lastControllerItem.copy();
        lastControllerItem = ((RecipeMapMultiblockWithSlotController) this.getMetaTileEntity()).getStackInSlot();

        return areItemStacksEqual(previousStack, lastControllerItem) || super.checkRecipeInputsDirty(importInventory, importFluids);
    }
}
