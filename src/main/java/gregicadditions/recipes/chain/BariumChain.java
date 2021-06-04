package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BariumChain {
    public static void init() {

        // Ba + 2HCl -> BaCl2 + 2H
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Barium)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .outputs(BariumChloride.getItemStack(3))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // BaCl2 + H2SO4 -> BaSO4 + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(480)
                .inputs(BariumChloride.getItemStack(3))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, Barite, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // BaSO4 + 2C -> BaS + 2CO2
        BLAST_RECIPES.recipeBuilder().duration(220).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Barite, 6)
                .input(dust, Carbon, 2)
                .outputs(BariumSulfide.getItemStack(2))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .buildAndRegister();

        // BaS + H2O + CO2 -> BaCO3 + H2S
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(30)
                .inputs(BariumSulfide.getItemStack(2))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .outputs(BariumCarbonate.getItemStack(5))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .buildAndRegister();

        // BaCO3 -> CO2 + BaO
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(BariumCarbonate.getItemStack(5))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(BariumOxide.getItemStack(2))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // 4BaO + 2Al -> 3Ba + Al2BaO4
        BLAST_RECIPES.recipeBuilder().duration(180).EUt(120).blastFurnaceTemp(700)
                .inputs(BariumOxide.getItemStack(8))
                .input(dust, Aluminium, 2)
                .output(ingot, Barium, 3)
                .outputs(BariumAluminate.getItemStack(7))
                .buildAndRegister();

        // 2Al2BaO4 -> BaO + Al2O3
        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(30)
                .inputs(BariumAluminate.getItemStack(14))
                .outputs(BariumOxide.getItemStack(2))
                .outputs(Alumina.getItemStack(5))
                .buildAndRegister();
    }
}
