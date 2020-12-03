package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;


import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(2400).EUt(500).blastFurnaceTemp(1500)
            .inputs(VanadiumMagnetite.getItemStack(2))
            .input(dust, Carbon)
            .fluidInputs(Oxygen.getFluid(1000))
            .output(OreDictUnifier(ingot, Iron))
            .output(VanadiumSlag.getItemStack())
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .buildAndRegister();
    }
}
