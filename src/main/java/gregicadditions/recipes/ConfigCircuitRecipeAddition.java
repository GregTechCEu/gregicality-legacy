package gregicadditions.recipes;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class ConfigCircuitRecipeAddition {

    public static void init() {
        // Epoxid
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)});

        // Chlorobenzene
        removeRecipesByInputs(CHEMICAL_RECIPES, new FluidStack[]{Benzene.getFluid(1000), Chlorine.getFluid(4000)});

        // Aluminium Ingot
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Aluminium)});

        // Hot Tungsten
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Tungsten)});
    }
}
