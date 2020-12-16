package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class FusionElementsChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(500)
                .input(dust, Lithium)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1800)
                .fluidInputs(LithiumHydroxideSolution.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(LithiumPeroxideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(LithiumPeroxideSolution.getFluid(1000))
                .fluidOutputs(LithiumCarbonatePureSolution.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(2000)
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Ozone.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(NitrogenPentoxide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .fluidInputs(NitrogenPentoxide.getFluid(2000))
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .outputs(TitaniumNitrate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1950).blastFurnaceTemp(3100)
                .inputs(TitaniumNitrate.getItemStack())
                .fluidInputs(LithiumCarbonatePureSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, LithiumTitanate))
                .buildAndRegister();

    }
}
