package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BariumChain {
    public static void init() {
         BLAST_RECIPES.recipeBuilder().duration(480).EUt(500).blastFurnaceTemp(1200)
            .input(Barite.getItemStack())
            .input(dust, Carbon)
            .output(BariumSulfide.getItemStack())
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .buildAndRegister();
         CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(500)
            .input(BariumSulfide.getItemStack())
            .fluidInputs(Water.getFluid(1000))
            .fluidInputs(CarbonDioxide.getFluid(1000))
            .output(BariumCarbonate.getItemStack())
            .fluidOutputs(HydrogenSulfide.getFluid(1000))
            .buildAndRegister();
         CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(250)
            .input(BariumCarbonate.getItemStack())
            .output(BariumOxide.getItemStack())
            .fluidOutputs(CarbonDioxide.getItemStacK())
            .buildAndRegister();
         BLAST_RECIPES.recipeBuilder().duration(240).EUt(500).blastFurnaceTemp(700)
            .input(BariumOxide.getItemStack(2))
            .input(dust, Aluminium, 2)
            .output(OreDictUnifier(dust, Barium))
            .output(BariumAluminate.getItemStack(2))
            .buildAndRegister();
         CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(500)
            .input(BariumAluminate.getItemStack(2))
            .output(BariumOxide.getItemStack())
            .output(Alumina.getItemStack(2))
            .buildAndRegister();
    }
        
}
