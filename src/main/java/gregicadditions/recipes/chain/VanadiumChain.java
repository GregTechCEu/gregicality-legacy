package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumChain {
    public static void init() {

        // Fe3O4V + C = 3Fe + (VO)C(TiO2) + CO
        BLAST_RECIPES.recipeBuilder().duration(220).EUt(120).blastFurnaceTemp(1500)
                .input(dust, VanadiumMagnetite, 4)
                .input(dust, Carbon)
                .output(ingot, Iron, 3)
                .outputs(VanadiumSlag.getItemStack(5))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // (VO)C(TiO2) = Dark Ash + TiO2 + VO
        MACERATOR_RECIPES.recipeBuilder().duration(80).EUt(24)
                .inputs(VanadiumSlag.getItemStack(5))
                .output(dust, DarkAsh)
                .output(dustSmall, Rutile)
                .outputs(VanadiumSlagDust.getItemStack(2))
                .buildAndRegister();

        // 2VO + 3Na2CO3 = 2Na3VO4 + 3CO
        BLAST_RECIPES.recipeBuilder().duration(150).EUt(120).blastFurnaceTemp(700)
                .inputs(VanadiumSlagDust.getItemStack(2))
                .input(dust, SodaAsh, 18)
                .outputs(SodiumVanadate.getItemStack(16))
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .buildAndRegister();

        // H2SO4 + NH4Cl + Na3VO4 = NH4VO3 + [Cl + 3Na + O + H2SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .inputs(SodiumVanadate.getItemStack(8))
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .outputs(AmmoniumVanadate.getItemStack(9))
                .fluidOutputs(VanadiumWasteSolution.getFluid(1000))
                .buildAndRegister();

        // [Cl + 3Na + O + H2SO4] = SiO2 + Al(OH)3 + NaCl + Na2SO4 + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(VanadiumWasteSolution.getFluid(4000))
                .output(dust, Salt, 2)
                .output(dust, SodiumSulfate, 7)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide, 3), 5000, 1200)
                .chancedOutput(AluminiumHydroxide.getItemStack(7), 5000, 1200)
                .buildAndRegister();

        // Na2SO4 + H2 -> H2SO4 + 2Na
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(30)
                .input(dust, SodiumSulfate, 7)
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, Sodium, 2)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // 2NH4VO3 = V2O5 + 2NH3 + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(120)
                .inputs(AmmoniumVanadate.getItemStack(18))
                .outputs(VanadiumOxide.getItemStack(7))
                .fluidOutputs(Ammonia.getFluid(2000))
                .buildAndRegister();

        // V2O5 + 2Al + C = Al2O3 + 2V + CO2
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(1200)
                .inputs(VanadiumOxide.getItemStack(7))
                .input(dust, Aluminium, 2)
                .input(dust, Carbon)
                .outputs(Alumina.getItemStack(5))
                .output(dust, Vanadium, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // Combined Step - Vanadium Magnetite
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(dust, VanadiumMagnetite, 4)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, Vanadium, 2)
                .output(dust, Magnetite, 7)
                .output(dust, Sulfur, 3)
                .buildAndRegister();
    }
}
