package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SeleniumChain {
    public static void init() {

        /*
         * Unknown compounds key:
         * ElectricallyImpureCopper: Cu
         * CopperRefiningSolution: CuH2SO4
         * AnodicSlime: TeSe
         * SelenateTellurateMix: TeSe(H2Na2CO3)2
         * SelenateSolution: SeH2CO3
         */

        // CuFeS2 + SiO2 + 5O -> Cu(EIC) + FeSiO3 + 2SO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1500)
                .input(crushedCentrifuged, Chalcopyrite)
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust, ElectricallyImpureCopper)
                .output(dust, Ferrosilite, 5)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        // Cu + H2SO4 -> CuH2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, ElectricallyImpureCopper)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CopperRefiningSolution.getFluid(1000))
                .buildAndRegister();

        // 2Cu + CuH2SO4 -> H2SO4 + 3Cu + TeSe(75%)
        ELECTROLYZER_RECIPES.recipeBuilder().duration(450).EUt(120)
                .input(plate, ElectricallyImpureCopper, 2)
                .fluidInputs(CopperRefiningSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .output(ingot, Copper, 3)
                .chancedOutput(dust, AnodicSlime, 7500, 0)
                .buildAndRegister();

        // TeSe + 2Na2CO3 + 4H -> TeSe(H2Na2CO3)2
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(2100)
                .input(dust, AnodicSlime)
                .input(dust, SodaAsh, 12)
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(dustTiny, Gold, 5)
                .fluidOutputs(SelenateTellurateMix.getFluid(1000))
                .buildAndRegister();

        // TeSe(H2Na2CO3)2 + H2SO4 -> TeO2 + 4Na + 2H2O + SO2 + CO + SeH2CO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1920)
                .fluidInputs(SelenateTellurateMix.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, TelluriumOxide, 3)
                .output(dust, Sodium, 4)
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .fluidOutputs(SelenateSolution.getFluid(1000))
                .buildAndRegister();

        // SeH2CO3 + 2Cl -> 2HCl + CO + SeO2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(SelenateSolution.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .output(dust, SeleniumOxide, 3)
                .buildAndRegister();

        // SeO2 + 2SO2 -> Se + 2SO3
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, SeleniumOxide, 3)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .output(dust, Selenium)
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();

        // TeO2 + 2SO2 -> Te + 2SO3
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, TelluriumOxide, 3)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .output(dust, Tellurium)
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();
    }
}
