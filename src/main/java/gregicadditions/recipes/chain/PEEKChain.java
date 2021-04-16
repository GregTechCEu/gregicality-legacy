package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.plate;

public class PEEKChain {
    public static void init() {

        // Na2B4O7(H2O)10 + 2HCl -> 4H3BO3 + 5H2O + 2NaCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Borax, 23)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(BoricAcid.getFluid(4000))
                .fluidOutputs(Water.getFluid(5000))
                .output(dust, Salt, 4)
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        // H3BO3 + 4HF -> HBF4 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoricAcid.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidOutputs(FluoroBoricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(500000)
                .duration(10)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Cobalt)
                .input(dust, Charcoal, 2)
                .input(plate, Steel)
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .outputs(CoAcABCatalyst.getItemStack())
                .EUt(500000)
                .duration(10)
                .buildAndRegister();

        // H2O + NaNO3 -> NaNO3(H2O)
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .input(dust, SodiumNitrate, 5)
                .fluidOutputs(SodiumNitrateSolution.getFluid(1000))
                .EUt(122880)
                .duration(2)
                .buildAndRegister();

        // NaNO3(H2O) -> NaNO2 + H2O + O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumNitrateSolution.getFluid(1000))
                .notConsumable(CoAcABCatalyst.getItemStack())
                .outputs(SodiumNitrite.getItemStack(4))
                .fluidOutputs(Oxygen.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(500000)
                .duration(20)
                .buildAndRegister();

        // HBF4 + HCl + C6H5NH2 + NaNO2 -> NaCl + C6H5BF4N2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .output(dust, Salt, 2)
                .fluidOutputs(BenzenediazoniumTetrafluoroborate.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .duration(150)
                .EUt(500000)
                .buildAndRegister();

        // C6H5BF4N2 -> BF3 + 2N + C6H5F
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(BenzenediazoniumTetrafluoroborate.getFluid(1000))
                .fluidOutputs(BoronFluoride.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(FluoroBenzene.getFluid(1000))
                .EUt(500000)
                .duration(100)
                .buildAndRegister();

        // C6H5F + CH4 + SbHF6 -> C7H7F + 3F + SbF3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidOutputs(Fluorotoluene.getFluid(1000))
                .fluidOutputs(Fluorine.getFluid(3000))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .EUt(500000)
                .duration(150)
                .buildAndRegister();

        // SbF3 -> Sb + 3F
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(AntimonyTrifluoride.getItemStack(4))
                .output(dust, Antimony)
                .fluidOutputs(Fluorine.getFluid(3000))
                .EUt(48)
                .duration(360)
                .buildAndRegister();

        // Zn + Fe + Al + Cl -> ZnFeAlCl
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Zinc)
                .input(dust, Iron)
                .input(dust, Aluminium)
                .fluidInputs(Chlorine.getFluid(1000))
                .outputs(ZnFeAlClCatalyst.getItemStack(4))
                .EUt(15000)
                .duration(500)
                .buildAndRegister();

        // H2O + 6Cl + C7H7F + C6H5F -> C13H8OF2 + 6HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Fluorotoluene.getFluid(1000))
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .notConsumable(ZnFeAlClCatalyst.getItemStack())
                .outputs(Difluorobenzophenone.getItemStack(24))
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .EUt(500000)
                .duration(100)
                .buildAndRegister();

        // C6H6O2 + C13H8OF2 + Na2CO3 -> [(OC6H4)3C]n + H2O + 2NaF
        // Not perfectly balanced, but is probably fine
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydroquinone.getFluid(1000))
                .inputs(Difluorobenzophenone.getItemStack(24))
                .input(dust, SodaAsh, 6)
                .fluidOutputs(Polyetheretherketone.getFluid(2592))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, SodiumFluoride, 4)
                .EUt(500000)
                .duration(250)
                .buildAndRegister();

        // C3H6 + C6H6 + 3O -> C3H6O + C6H6O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Acetone.getFluid(1000))
                .fluidOutputs(Hydroquinone.getFluid(1000))
                .fluidOutputs(Resorcinol.getFluid(1000))
                .EUt(7860)
                .duration(800)
                .buildAndRegister();

        // MgClBr -> Mg + Cl + Br
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(MgClBromide.getItemStack(3))
                .output(dust, Magnesium)
                .fluidOutputs(Chlorine.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();
    }
}
