package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class PEEKChain {
    public static void init() {
        LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BoricAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .fluidOutputs(FluoroBoricAcid.getFluid(3000))
                .EUt(500000)
                .duration(10)
                .buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, Cobalt)
                .input(dust, Carbon)
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(CoCABCatalyst.getFluid(3000))
                .EUt(500000)
                .duration(10)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Water.getFluid(10000))
                .input(dust, SodiumNitrate, 10)
                .fluidOutputs(SodiumNitrateSolution.getFluid(20000))
                .EUt(122880)
                .duration(20)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumNitrateSolution.getFluid(3000))
                .fluidInputs(CoCABCatalyst.getFluid(10))
                .outputs(SodiumNitrite.getItemStack())
                .fluidOutputs(Oxygen.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(500000)
                .duration(50)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt))
                .fluidOutputs(BenzenediazoniumTetrafluoroborate.getFluid(3000))
                .duration(150)
                .EUt(500000)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(BenzenediazoniumTetrafluoroborate.getFluid(3000))
                .fluidOutputs(BoronFluoride.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .fluidOutputs(FluoroBenzene.getFluid(1000))
                .EUt(500000)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidOutputs(Fluorotoluene.getFluid(3000))
                .EUt(500000)
                .duration(150)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Zinc)
                .input(dust, Iron)
                .input(dust, Aluminium)
                .fluidInputs(Chlorine.getFluid(1000))
                .outputs(ZnFeAlClCatalyst.getItemStack(4))
                .EUt(15000)
                .duration(500)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(ZnFeAlClCatalyst.getItemStack())
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Fluorotoluene.getFluid(1000))
                .outputs(Difluorobenzophenone.getItemStack(4))
                .EUt(500000)
                .duration(200)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydroquinone.getFluid(1000))
                .inputs(Difluorobenzophenone.getItemStack())
                .inputs(SodiumCarbonate.getItemStack())
                .fluidOutputs(Polyetheretherketone.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride))
                .EUt(500000)
                .duration(250)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Propylene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Acetone.getFluid(1000))
                .fluidOutputs(Hydroquinone.getFluid(1000))
                .fluidOutputs(Resorcinol.getFluid(1000))
                .EUt(500000)
                .duration(200)
                .buildAndRegister();
    }
}
