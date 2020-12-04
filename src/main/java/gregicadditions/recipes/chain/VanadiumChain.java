package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;


import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumChain {
    public static void init() {
        BLAST_RECIPES.recipeBuilder().duration(480).EUt(125).blastFurnaceTemp(1500)
            .inputs(VanadiumMagnetite.getItemStack(2))
            .input(dust, Carbon)
            .fluidInputs(Oxygen.getFluid(1000))
            .output(OreDictUnifier(ingot, Iron))
            .output(VanadiumSlag.getItemStack())
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .buildAndRegister();
        MACERATOR.recipeBuilder().duration(2400).EU(125)
            .input(sVanadiumSlag.getItemStack())
            .outputs(OreDictUnifier(dust, Iron))
            .outputs(tiny_dust, Rutile)
            .outputs(VanadiumSlagDust.getItemStack());
        BLAST_RECIPES.recipeBuilder().duration(300).EUt(125).blastFurnaceTemp(700)
            .inputs(VanadiumSlagDust.getItemStack())
            .input(dust, Salt)
            .input(dust, SodaAsh)
            .output(SodiumVanadate.getItemStack());
        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(125)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .input(SodiumVanadate.getItemStack())
            .fluidInputs(AmmoniumChloride.getFluid(1000))
            .outputs(AmmoniumVanadate.getItemStack())
            .fluidOutputs(VanadiumWasteSolution.getFluid(1000));
        CHEMICAL_DEHYDRATOR.recipeBuilder().duration(720).EUt(125)
            .fluidInputs(VanadiumWasteSolution.getFluid(4000))
            .chancedOutputs(OreDictUnifier.get(dust, Salt),9000,0)
            .chancedOutputs(OreDictUnifier.get(dust, Salt),9000,0)
            .chancedOutputs(OreDictUnifier.get(dust, SodiumSulfate),9000,0)
            .chancedOutputs(OreDictUnifier.get(dust, SodiumSulfate),9000,0)
            .chancedOutputs(OreDictUnifier.get(dust, SilconDioxide),5000,0)
            .chancedOutputs(AluminiumHydroxide.getItemStack(),5000,0);
        CHEMICAL_DEHYDRATOR.recipeBuilder().duration(480).EUt(125)
            .input(AmmoniumVanadate.getItemStack())
            .outputs(VanadiumOxide.getItemStack(2))
            .fluidOutputs(Ammonium.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000));
        BLAST_RECIPES.recipeBuilder().duration(600).EUt(125).blastFurnaceTemp(1200)
            .input(VanadiumOxide.getItemStack())
            .input(dust, Aluminium)
            .outputs(AluminiumOxide.getItemStack())
            .outputs(OreDictUnifier(dust, Vanadium));
        
    }
}
