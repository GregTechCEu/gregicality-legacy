package gregicadditions.machines;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IMultiRecipe;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class MultiRecipesTrait extends MTETrait implements IMultiRecipe {

    private final RecipeMap<?>[] recipes;
    private int pos = 0;
    private final RecipeMapMultiblockController multiblock;

    public MultiRecipesTrait(RecipeMapMultiblockController metaTileEntity, RecipeMap<?>[] recipes) {
        super(metaTileEntity);
        this.recipes = recipes;
        this.multiblock = metaTileEntity;
    }

    @Override
    public RecipeMap<?>[] getRecipes() {
        return recipes;
    }

    @Override
    public int getCurrentRecipe() {
        return pos;
    }

    @Nullable
    public RecipeMap<?> getNextRecipe() {
        boolean isEmpty = IntStream.range(0, multiblock.getInputInventory().getSlots())
                .mapToObj(i -> multiblock.getInputInventory().getStackInSlot(i))
                .allMatch(ItemStack::isEmpty);
        if (!isEmpty) {
            return null;
        }
        pos = ++pos % recipes.length;

        return recipes[pos];
    }

    @Override
    public String getName() {
        return "multi-recipe";
    }

    @Override
    public int getNetworkID() {
        return 0;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregicAdditionsCapabilities.MULTI_RECIPE_CAPABILITY) {
            return (T) this;
        }
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        pos = compound.getInteger("Recipe");
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        compound.setTag("Recipe", new NBTTagInt(pos));
        return compound;
    }
}
