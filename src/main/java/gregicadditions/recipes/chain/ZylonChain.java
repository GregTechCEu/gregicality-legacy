package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class ZylonChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Xylene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Xylene.getFluid(1000))
                .input(dust, Zeolite)
                .fluidOutputs(XyleneZeoliteMixture.getFluid(1000))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(XyleneZeoliteMixture.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Zeolite))
                .fluidOutputs(PXylene.getFluid(1000))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PXylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(Dibromomethylbenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(2000000)
                .duration(150)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dibromomethylbenzene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Terephthalaldehyde.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold)
                .input(dust, Palladium)
                .input(dust, Carbon)
                .outputs(AuPdCCatalyst.getItemStack(3))
                .EUt(2000000)
                .duration(1)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(Isochloropropane.getFluid(2000))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethenone.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(AceticAnhydride.getFluid(1000))
                .EUt(30720)
                .duration(20)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(Isochloropropane.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .input(dust, SodiumHydroxide)
                .input(dust, Sodium)
                .fluidOutputs(Dinitrodipropanyloxybenzene.getFluid(1000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .fluidOutputs(SodiumAcetate.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .outputs(OreDictUnifier.get(dust, Carbon, 8))
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dinitrodipropanyloxybenzene.getFluid(1000))
                .inputs(Terephthalaldehyde.getItemStack())
                .notConsumable(AuPdCCatalyst.getItemStack())
                .outputs(PreZylon.getItemStack())
                .EUt(2000000)
                .duration(50)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(PreZylon.getItemStack(2))
                .fluidOutputs(Propane.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Zylon))
                .EUt(2000000)
                .duration(100)
                .blastFurnaceTemp(10000)
                .buildAndRegister();
    }
}
