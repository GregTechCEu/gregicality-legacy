package gregicadditions.recipes;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class ConfigCircuitRecipeRemoval {

    public static void init() {
        // Epoxid
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{HypochlorousAcid.getFluid(1000), AllylChloride.getFluid(1000)});

        // Chlorobenzene
        removeRecipesByInputs(CHEMICAL_RECIPES, Benzene.getFluid(1000), Chlorine.getFluid(4000));

        // Aluminium Ingot
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Aluminium));

        // Hot Tungsten
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, Tungsten));

        // Hot Lithium Titanate
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, LithiumTitanate));

        // Hot Tungstencarbide
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(ingot, Tungsten), OreDictUnifier.get(dust, Carbon));

        // Concrete
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Clay), OreDictUnifier.get(dust, Stone, 3)}, new FluidStack[]{Water.getFluid(500)});
    }
}