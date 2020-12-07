package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;


import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(480).EUt(125).blastFurnaceTemp(1500)
            .input(dust, VanadiumMagnetite, 2)
            .input(dust, Carbon)
            .fluidInputs(Oxygen.getFluid(1000))
            .outputs(OreDictUnifier.get(ingot, Iron, 2))
            .outputs(VanadiumSlag.getItemStack())
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().duration(2400).EUt(125)
            .inputs(VanadiumSlag.getItemStack())
            .outputs(OreDictUnifier.get(dustTiny, DarkAsh))
            .outputs(OreDictUnifier.get(dustTiny, Rutile))
            .outputs(VanadiumSlagDust.getItemStack())
            .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(300).EUt(125).blastFurnaceTemp(700)
            .inputs(VanadiumSlagDust.getItemStack())
            .input(dust, Salt)
            .input(dust, SodaAsh)
            .outputs(SodiumVanadate.getItemStack())
            .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(125)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .inputs(SodiumVanadate.getItemStack())
            .fluidInputs(AmmoniumChloride.getFluid(1000))
            .outputs(AmmoniumVanadate.getItemStack())
            .fluidOutputs(VanadiumWasteSolution.getFluid(1000))
            .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(720).EUt(125)
            .fluidInputs(VanadiumWasteSolution.getFluid(4000))
            .chancedOutput(OreDictUnifier.get(dust, Salt),9000,0)
            .chancedOutput(OreDictUnifier.get(dust, Salt),9000,0)
            .chancedOutput(OreDictUnifier.get(dust, SodiumSulfate),9000,0)
            .chancedOutput(OreDictUnifier.get(dust, SodiumSulfate),9000,0)
            .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide),5000,0)
            .chancedOutput(AluminiumHydroxide.getItemStack(),5000,0)
            .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(480).EUt(125)
            .inputs(AmmoniumVanadate.getItemStack())
            .outputs(VanadiumOxide.getItemStack(2))
            .fluidOutputs(Ammonia.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(600).EUt(125).blastFurnaceTemp(1200)
            .inputs(VanadiumOxide.getItemStack())
            .input(dust, Aluminium)
            .outputs(Alumina.getItemStack())
            .outputs(OreDictUnifier.get(dust, Vanadium))
            .buildAndRegister();
    }
}

