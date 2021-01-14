package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SeleniumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1000).blastFurnaceTemp(1500)
                .input(crushedCentrifuged, Chalcopyrite)
                .input(dust, SiliconDioxide)
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, ElectricallyImpureCopper))
                .outputs(OreDictUnifier.get(dust, Ferrosilite))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1000)
                .input(dust, ElectricallyImpureCopper)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CopperRefiningSolution.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(450).EUt(1900)
                .input(plate, ElectricallyImpureCopper, 2)
                .fluidInputs(CopperRefiningSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(ingot, Copper, 4))
                .chancedOutput(AnodicSlime.getItemStack(), 7500, 0)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1750).blastFurnaceTemp(2100)
                .inputs(AnodicSlime.getItemStack())
                .input(dust, SodaAsh)
                .fluidInputs(Air.getFluid(1000))
                .outputs(OreDictUnifier.get(dustTiny, PreciousMetal, 5))
                .fluidOutputs(SelenateTellurateMix.getFluid(750))
                .buildAndRegister();

        LARGE_CHEMICAL_REACTOR.recipeBuilder().duration(350).EUt(1450)
                .fluidInputs(SelenateTellurateMix.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SelenateSolution.getFluid(1000))
                .outputs(TelluriumOxide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sodium))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1000)
                .fluidInputs(SelenateSolution.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .outputs(SeleniumOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1200)
                .inputs(SeleniumOxide.getItemStack())
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Selenium))
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1200)
                .inputs(TelluriumOxide.getItemStack())
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Tellurium))
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();

    }
}
