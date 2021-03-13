package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class ArcFurnaceOxidation {

    public static void init() {
        // 2Sb + 3O = Sb2O3
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(1980).duration(200)
                .input(dust, Antimony, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, AntimonyTrioxide, 5))
                .buildAndRegister();

        // NiZnFe4 + 8O = NiZnFe4O8
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(400).duration(600)
                .input(dust, FerriteMixture, 6)
                .fluidInputs(Oxygen.getFluid(8000))
                .outputs(OreDictUnifier.get(ingot, NickelZincFerrite, 14))
                .buildAndRegister();
    }
}
