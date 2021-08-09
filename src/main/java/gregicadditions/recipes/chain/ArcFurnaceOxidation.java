package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ArcFurnaceOxidation {

    public static void init() {
        // 2Sb + 3O = Sb2O3
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30).duration(60)
                .input(dust, Antimony, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, AntimonyTrioxide, 5))
                .buildAndRegister();

        // NiZnFe4 + 8O = NiZnFe4O8
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(120).duration(600)
                .input(dust, FerriteMixture, 6)
                .fluidInputs(Oxygen.getFluid(8000))
                .outputs(OreDictUnifier.get(ingot, NickelZincFerrite, 14))
                .buildAndRegister();


        // YBa2Cu3O6 + O -> YBa2Cu3O7
        ARC_FURNACE_RECIPES.recipeBuilder().duration(2509).EUt(7680)
                .inputs(WellMixedYBCOxides.getItemStack(12))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(ingotHot, YttriumBariumCuprate, 13)
                .buildAndRegister();
    }
}
