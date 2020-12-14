
package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class AluminiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(1000)
                .inputs(AluminiumHydroxide.getItemStack(2))
                .outputs(Alumina.getItemStack())
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumHydroxide, 3)
                .inputs(Alumina.getItemStack())
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .fluidOutputs(Water.getFluid(4500))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(600).EUt(125)
                .inputs(Alumina.getItemStack())
                .fluidInputs(SodiumHexafluoroaluminate.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Aluminium, 2))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride, 3))
                .outputs(AluminiumTrifluoride.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(125)
                .input(dust, SodiumFluoride, 3)
                .inputs(AluminiumTrifluoride.getItemStack())
                .fluidOutputs(SodiumHexafluoroaluminate.getFluid(1000))
                .buildAndRegister();
    }
}
