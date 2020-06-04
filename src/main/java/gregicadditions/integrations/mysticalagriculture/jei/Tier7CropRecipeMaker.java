package gregicadditions.integrations.mysticalagriculture.jei;

import com.blakebr0.mysticalagradditions.compat.jei.Tier6CropWrapper;
import gregicadditions.integrations.mysticalagriculture.block.BlockCrop;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Tier7CropRecipeMaker {

    public static List<Tier6CropWrapper> getRecipes() {
        List<Tier6CropWrapper> recipes = new ArrayList<Tier6CropWrapper>();
        for (BlockCrop blockCrop : BlockCrop.REGISTRY.values()) {
            recipes.add(new Tier6CropWrapper(new ItemStack(blockCrop.getSeed()), new ItemStack(blockCrop), OreDictUnifier.get(OrePrefix.block, blockCrop.getMaterial()), new ItemStack(blockCrop.getCrop())));
        }

        return recipes;
    }
}
