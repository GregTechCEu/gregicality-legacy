package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanillaOverride {

    public static void init() {

        // Vanilla Armor
        if (GAConfig.GT6.BendingCylinders && GAConfig.GT6.addCurvedPlates) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET),
                    "ChC", " P ",
                    'C', new UnificationEntry(plateCurved, Iron),
                    'P', new UnificationEntry(plate, Iron));

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(plateCurved, Iron, 2)
                    .input(plate, Iron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4)
                    .input(plateCurved, WroughtIron, 2)
                    .input(plate, WroughtIron)
                    .outputs(new ItemStack(Items.BUCKET))
                    .buildAndRegister();

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(plateCurved, Iron));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET),
                    "PPP", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE),
                    "PhP", "CPC", "CPC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS),
                    "PCP", "ChC", "C C",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS),
                    "P P", "ChC",
                    'P', new UnificationEntry(plate, Gold),
                    'C', new UnificationEntry(plateCurved, Gold));

            ModHandler.addShapedRecipe("chain_helmet", new ItemStack(Items.CHAINMAIL_HELMET),
                    "RRR", "RhR",
                    'R', new UnificationEntry(ring, Iron));

            ModHandler.addShapedRecipe("chain_chestplate", new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                    "RhR", "RRR", "RRR",
                    'R', new UnificationEntry(ring, Iron));

            ModHandler.addShapedRecipe("chain_leggings", new ItemStack(Items.CHAINMAIL_LEGGINGS),
                    "RRR", "RhR", "R R",
                    'R', new UnificationEntry(ring, Iron));

            ModHandler.addShapedRecipe("chain_boots", new ItemStack(Items.CHAINMAIL_BOOTS),
                    "R R", "RhR",
                    'R', new UnificationEntry(ring, Iron));
        }
    }
}
