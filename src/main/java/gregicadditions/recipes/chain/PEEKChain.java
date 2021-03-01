package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.plate;

public class PEEKChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Borax, 23)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(BoricAcid.getFluid(4000))
                .fluidOutputs(Water.getFluid(5000))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoricAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
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
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(10000))
                .input(dust, SodiumNitrate, 10)
                .fluidOutputs(SodiumNitrateSolution.getFluid(10000))
                .EUt(122880)
                .duration(20)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumNitrateSolution.getFluid(3000))
                .notConsumable(CoAcABCatalyst.getItemStack())
                .outputs(SodiumNitrite.getItemStack(3))
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(500000)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(BenzenediazoniumTetrafluoroborate.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .duration(150)
                .EUt(500000)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(BenzenediazoniumTetrafluoroborate.getFluid(1000))
                .fluidOutputs(BoronFluoride.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(FluoroBenzene.getFluid(1000))
                .EUt(500000)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidOutputs(Fluorotoluene.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(3000))
                .outputs(AntimonyTrifluoride.getItemStack(4))
                .EUt(500000)
                .duration(150)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(AntimonyTrifluoride.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Antimony))
                .fluidOutputs(Fluorine.getFluid(3000))
                .EUt(48)
                .duration(360)
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
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Fluorotoluene.getFluid(1000))
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .notConsumable(ZnFeAlClCatalyst.getItemStack())
                .outputs(Difluorobenzophenone.getItemStack(1))
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .EUt(500000)
                .duration(100)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydroquinone.getFluid(1000))
                .inputs(Difluorobenzophenone.getItemStack())
                .input(dust, SodaAsh, 6)
                .fluidOutputs(Polyetheretherketone.getFluid(2592))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 2))
                .EUt(500000)
                .duration(250)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Acetone.getFluid(1000))
                .fluidOutputs(Hydroquinone.getFluid(500))
                .fluidOutputs(Resorcinol.getFluid(500))
                .EUt(30000)
                .duration(800)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(MgClBrominide.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Magnesium))
                .fluidOutputs(Chlorine.getFluid(2000))
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();
    }
}
