package gregicadditions.recipes.compat;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.FishOil;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ForestryCompat {

    public static void init() {

        final boolean forestryLoaded = Loader.isModLoaded(GAValues.MODID_FR);

        //Making BioDiesel
        if (forestryLoaded && GAConfig.Misc.ForestryIntegration) {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Fluids.BIO_ETHANOL.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        } else {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Ethanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        }
    }
}
