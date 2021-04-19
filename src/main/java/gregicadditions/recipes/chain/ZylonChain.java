package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class ZylonChain {
    public static void init() {

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(OrthoXylene.getFluid(1000))
                .input(dust, Zeolite)
                .fluidOutputs(OrthoXyleneZeoliteMixture.getFluid(1000))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(OrthoXyleneZeoliteMixture.getFluid(1000))
                .output(dust, Zeolite)
                .fluidOutputs(ParaXylene.getFluid(1000))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();

        // C8H10 + O + 2Br -> C8H8Br2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ParaXylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(Dibromomethylbenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(2000000)
                .duration(150)
                .buildAndRegister();

        // C8H8Br2 + H2SO4 -> 2Br + 2H2O + C8H6O2 + S
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dibromomethylbenzene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Terephthalaldehyde.getItemStack(16))
                .output(dust, Sulfur)
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();

        // Au + Pd + C -> AuPdC
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold)
                .input(dust, Palladium)
                .input(dust, Carbon)
                .outputs(AuPdCCatalyst.getItemStack(3))
                .EUt(2000000)
                .duration(1)
                .buildAndRegister();

        // HCl + C3H6 -> C3H7Cl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(Isochloropropane.getFluid(1000))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();

        // C2H2O + CH3COOH -> C4H6O3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethenone.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(AceticAnhydride.getFluid(1000))
                .EUt(30720)
                .duration(20)
                .buildAndRegister();

        // C6H6O2 + C3H7Cl + C4H6O3 + 2HNO3 + Na2O + C3H6 -> C12H16O2(NO2)2 + 2H2O + CH3COOH + C2H3NaO2 + NaCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(Isochloropropane.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Propene.getFluid(1000))
                .inputs(SodiumOxide.getItemStack(3))
                .fluidOutputs(Dinitrodipropanyloxybenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .fluidOutputs(SodiumAcetate.getFluid(1000))
                .output(dust, Salt, 2)
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();

        // C12H16O2(NO2)2 + C8H6O2 -> C20H22N2O2 + 6O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dinitrodipropanyloxybenzene.getFluid(1000))
                .inputs(Terephthalaldehyde.getItemStack(16))
                .notConsumable(AuPdCCatalyst.getItemStack())
                .outputs(PreZylon.getItemStack())
                .fluidOutputs(Oxygen.getFluid(6000))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();

        // C20H22N2O2 -> 2C3H8 + C14H6N2O2
        BLAST_RECIPES.recipeBuilder()
                .inputs(PreZylon.getItemStack())
                .fluidOutputs(Propane.getFluid(2000))
                .output(dust, Zylon)
                .EUt(2000000)
                .duration(100)
                .blastFurnaceTemp(10000)
                .buildAndRegister();
    }
}
