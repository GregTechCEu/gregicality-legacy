package gregicadditions.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class GARecipeRemoval {
    public static void init() {
        ModHandler.removeFurnaceSmelting(MetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_block_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));

        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));
        ModHandler.removeRecipes(DYNAMITE.getStackForm());

        ModHandler.removeRecipes(INTEGRATED_CIRCUIT.getStackForm());

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_max"));

        ModHandler.removeRecipes(OreDictUnifier.get(dust, YttriumBariumCuprate, 6));
        ModHandler.removeRecipes(OreDictUnifier.get(dustTiny, YttriumBariumCuprate, 6));
    }
}
