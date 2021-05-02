package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAMaterials.FreeElectronGas;
import static gregicadditions.recipes.GARecipeMaps.MASS_FAB_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.REPLICATOR_RECIPES;
import static gregicadditions.recipes.helper.AdditionMethods.removeCraftingRecipes;
import static gregicadditions.recipes.helper.AdditionMethods.removeRecipeByName;
import static gregtech.api.unification.material.Materials.Flint;
import static gregtech.api.unification.material.Materials.UUMatter;
import static gregtech.api.unification.ore.OrePrefix.*;

/**
 * This class is for any recipes that need to be done as a loop over
 * the GTCE Material Registry. This way, we can minimize the number
 * of times that it is iterated over in one launch cycle.
 */
public class MaterialIteration {

    public static void init() {

        for (Material material : Material.MATERIAL_REGISTRY) {

            // Remove Old Wrench Recipes
            if (material instanceof IngotMaterial && !material.hasFlag(DustMaterial.MatFlags.NO_SMASHING) && GAConfig.GT6.ExpensiveWrenches) {
                removeRecipeByName(String.format("gtadditions:wrench_%s", material.toString())); // TODO test this
                ModHandler.addShapedRecipe(String.format("ga_wrench_%s", material.toString()), MetaItems.WRENCH.getStackForm((SolidMaterial) material),
                        "XhX", "XXX", " X ",
                        'X', new UnificationEntry(plate, material));
            }

            // Gem Tool Part Fixes
            if (!OreDictUnifier.get(gem, material).isEmpty() && !OreDictUnifier.get(toolHeadHammer, material).isEmpty() && material != Flint) {
                removeCraftingRecipes(OreDictUnifier.get(toolHeadAxe, material));
                ModHandler.addShapedRecipe("axe_head_" + material.toString(), OreDictUnifier.get(toolHeadAxe, material), "GG", "Gf", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadFile, material));
                ModHandler.addShapedRecipe("file_head_" + material.toString(), OreDictUnifier.get(toolHeadFile, material), "G", "G", "f", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadHammer, material));
                ModHandler.addShapedRecipe("hammer_head_" + material.toString(), OreDictUnifier.get(toolHeadHammer, material), "GG ", "GGf", "GG ", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadHoe, material));
                ModHandler.addShapedRecipe("hoe_head_" + material.toString(), OreDictUnifier.get(toolHeadHoe, material), "GGf", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadPickaxe, material));
                ModHandler.addShapedRecipe("pickaxe_head_" + material.toString(), OreDictUnifier.get(toolHeadPickaxe, material), "GGG", "f  ", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadSaw, material));
                ModHandler.addShapedRecipe("saw_head_" + material.toString(), OreDictUnifier.get(toolHeadSaw, material), "GG", "f ", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadSense, material));
                ModHandler.addShapedRecipe("sense_head_" + material.toString(), OreDictUnifier.get(toolHeadSense, material), "GGG", " f ", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadShovel, material));
                ModHandler.addShapedRecipe("shovel_head_" + material.toString(), OreDictUnifier.get(toolHeadShovel, material), "fG", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadSword, material));
                ModHandler.addShapedRecipe("sword_head_" + material.toString(), OreDictUnifier.get(toolHeadSword, material), " G", "fG", 'G', new UnificationEntry(gem, material));
                removeCraftingRecipes(OreDictUnifier.get(toolHeadUniversalSpade, material));
                ModHandler.addShapedRecipe("universal_spade_head_" + material.toString(), OreDictUnifier.get(toolHeadUniversalSpade, material), "GGG", "GfG", " G ", 'G', new UnificationEntry(gem, material));
            }

            // Dust Uncrafting Fixes
            if (!OreDictUnifier.get(dustSmall, material).isEmpty()) {
                removeCraftingRecipes(OreDictUnifier.get(dustSmall, material));
                ModHandler.addShapedRecipe("dust_small_" + material.toString(), OreDictUnifier.get(dustSmall, material, 4),
                        " D", "  ", 'D',
                        new UnificationEntry(dust, material));
            }

            // Matter Replication Recipes
            matterReplication(material);
        }
    }

    private static void matterReplication(Material material) {

        if (material.element == null)
            return;

        int mass = (int) material.getMass();
        FluidStack uuFluid = mass % 2 == 0 ? BosonicUUMatter.getFluid(mass) : FermionicUUMatter.getFluid(mass);

        if (((FluidMaterial) material).getMaterialFluid() != null) {

            int amount = material instanceof IngotMaterial ? GTValues.L : 1000;

            MASS_FAB_RECIPES.recipeBuilder()
                    .fluidInputs(((FluidMaterial) material).getFluid(amount))
                    .fluidOutputs(uuFluid)
                    .fluidOutputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            if (material.hasFlag(DISABLE_REPLICATION))
                return;

            REPLICATOR_RECIPES.recipeBuilder()
                    .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                    .notConsumable(((FluidMaterial) material).getFluid(amount))
                    .fluidInputs(uuFluid)
                    .fluidInputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            REPLICATOR_RECIPES.recipeBuilder()
                    .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                    .notConsumable(((FluidMaterial) material).getFluid(amount))
                    .fluidInputs(UUMatter.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();
        } else {

            MASS_FAB_RECIPES.recipeBuilder()
                    .input(dust, material)
                    .fluidOutputs(uuFluid)
                    .fluidOutputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            if (material.hasFlag(DISABLE_REPLICATION))
                return;

            REPLICATOR_RECIPES.recipeBuilder()
                    .output(dust, material)
                    .notConsumable(dust, material)
                    .fluidInputs(uuFluid)
                    .fluidInputs(FreeElectronGas.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();

            REPLICATOR_RECIPES.recipeBuilder()
                    .output(dust, material)
                    .notConsumable(dust, material)
                    .fluidInputs(UUMatter.getFluid(mass))
                    .duration(mass * GAConfig.Misc.replicationTimeFactor)
                    .EUt(32)
                    .buildAndRegister();
        }
    }
}
