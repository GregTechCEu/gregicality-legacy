package gregicadditions.integrations.mysticalagriculture.jei;

import com.blakebr0.mysticalagradditions.compat.jei.Tier6CropWrapper;
import gregicadditions.integrations.mysticalagriculture.CropType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Tier7CropRecipeMaker {

    public static List<Tier6CropWrapper> getRecipes() {
        List<Tier6CropWrapper> recipes = new ArrayList<Tier6CropWrapper>();

        for (CropType.Type type : CropType.Type.values()) {
            if (type.isEnabled()) {
                ItemStack input = new ItemStack(type.getSeed());
                ItemStack crop = new ItemStack(type.getPlant());
                ItemStack root = new ItemStack(type.getRoot().getBlock(), 1, type.getRootMeta());
                ItemStack output = new ItemStack(type.getCrop());

                recipes.add(new Tier6CropWrapper(input, crop, root, output));
            }
        }

        return recipes;
    }
}
