package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class IodineChain {
    public static void init() {

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(1250)
                .input(dust, Caliche)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CalicheIodateBrine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(1250)
                .fluidInputs(CalicheIodateBrine.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .input(dust, Carbon)
                .fluidOutputs(IodideSolution.getFluid(1000))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 7))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(1250)
                .fluidInputs(CalicheIodateBrine.getFluid(167))
                .fluidInputs(IodideSolution.getFluid(833))
                .fluidOutputs(CalicheIodineBrine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(600)
                .fluidInputs(CalicheIodineBrine.getFluid(1000))
                .fluidInputs(Kerosene.getFluid(1000))
                .fluidOutputs(CalicheNitrateSolution.getFluid(1000))
                .fluidOutputs(KeroseneIodineSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(90).EUt(600)
                .fluidInputs(KeroseneIodineSolution.getFluid(1000))
                .fluidOutputs(Kerosene.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(750)
                .fluidInputs(CalicheNitrateSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Niter, 5))
                .outputs(OreDictUnifier.get(dust, RockSalt, 2))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(30)
                .fluidInputs(IodizedOil.getFluid(2000))
                .fluidOutputs(Oil.getFluid(1000))
                .fluidOutputs(IodizedBrine.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(1250)
                .fluidInputs(IodizedBrine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(300))
                .fluidOutputs(IodineBrineMix.getFluid(1300))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(150).EUt(1250)
                .fluidInputs(IodineBrineMix.getFluid(1300))
                .fluidOutputs(BrominatedBrine.getFluid(1000))
                .fluidOutputs(IodineSlurry.getFluid(300))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(230).EUt(600)
                .fluidInputs(IodineSlurry.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .buildAndRegister();
    }
}
