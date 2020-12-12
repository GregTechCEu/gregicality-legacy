package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ZincChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(500)
                .input(dust, Sphalerite)
                .input(dust, Coke)
                .fluidInputs(DistilledWater.getFluid(1000))
                .outputs(ZincCokePellets.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(60).EUt(16000).blastFurnaceTemp(1500)
                .inputs(ZincCokePellets.getItemStack())
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Zincite))
                .outputs(ZincResidualSlag.getItemStack())
                .fluidOutputs(ZincExhaustMixture.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(80).EUt(8000)
                .input(dust, Zincite)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, ZincSulfate, 3))
                .outputs(ZincLeachingResidue.getItemStack())
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(2000)
                .fluidInputs(ZincExhaustMixture.getFluid(2000))
                .chancedOutput(ZincFlueDust.getItemStack(), 4500, 750)
                .outputs(OreDictUnifier.get(dustSmall, DarkAsh))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(180).EUt(1250)
                .inputs(ZincResidualSlag.getItemStack())
                .outputs(FineZincSlagDust.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(110).EUt(1400)
                .inputs(FineZincSlagDust.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(ZincSlagSlurry.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(230).EUt(1500)
                .fluidInputs(ZincSlagSlurry.getFluid(2000))
                .fluidOutputs(MetalRichSlagSlurry.getFluid(1000))
                .outputs(ZincResidualSlag.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1600)
                .fluidInputs(MetalRichSlagSlurry.getFluid(1000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidOutputs(AcidicMetalSlurry.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .fluidInputs(AcidicMetalSlurry.getFluid(2000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidOutputs(SeparatedMetalSlurry.getFluid(1000))
                .fluidOutputs(MetalRichSlagSlurry.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1900)
                .fluidInputs(SeparatedMetalSlurry.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .fluidInputs(MetalHydroxideMix.getFluid(1000))
                .outputs(SodiumPhosphate.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(MetalHydroxideMix.getFluid(1000))
                .input(wireFine, Zinc)
                .outputs(OreDictUnifier.get(wireFine, Zinc))
                .fluidOutputs(ZincPoorMix.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(ZincPoorMix.getFluid(1000))
                .input(wireFine, Iron)
                .outputs(OreDictUnifier.get(wireFine, Iron))
                .fluidOutputs(IronPoorMix.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(IronPoorMix.getFluid(1000))
                .input(wireFine, Copper)
                .outputs(OreDictUnifier.get(wireFine, Copper))
                .fluidOutputs(IndiumHydroxideConcentrate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(230).EUt(950)
                .fluidInputs(IndiumHydroxideConcentrate.getFluid(1000))
                .outputs(IndiumHydroxide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1150)
                .inputs(IndiumHydroxide.getItemStack())
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Indium))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .inputs(ZincFlueDust.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(CadmiumZincDust.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(850)
                .inputs(CadmiumZincDust.getItemStack())
                .fluidInputs(Mercury.getFluid(1000))
                .fluidOutputs(CadmiumThalliumLiquor.getFluid(1000))
                .fluidOutputs(ZincAmalgam.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(3200)
                .fluidInputs(ZincAmalgam.getFluid(1000))
                .fluidOutputs(Mercury.getFluid(1000))
                .chancedOutput(OreDictUnifier.get(dust, Zinc), 6500, 550)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(210).EUt(1800)
                .fluidInputs(CadmiumThalliumLiquor.getFluid(9000))
                .outputs(ThalliumResidue.getItemStack())
                .fluidOutputs(CadmiumSulfateSolution.getFluid(9000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(1600)
                .fluidInputs(CadmiumSulfateSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Cadmium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .inputs(ThalliumResidue.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(ThalliumSulfateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1100)
                .fluidInputs(ThalliumSulfateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .outputs(ThalliumChloride.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(850).blastFurnaceTemp(750)
                .inputs(ThalliumChloride.getItemStack())
                .input(dust, Zinc)
                .outputs(OreDictUnifier.get(dust, Thallium))
                .outputs(ZincChloride.getItemStack())
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(1150)
                .inputs(ZincChloride.getItemStack())
                .outputs(OreDictUnifier.get(dust, Zinc))
                .fluidOutputs(Chlorine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(250)
                .input(dust, SodaAsh)
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .outputs(SodiumSulfite.getItemStack())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(1200)
                .input(dust, Wood, 2)
                .inputs(SodiumSulfite.getItemStack())
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(Cellulose.getItemStack(2))
                .fluidOutputs(PolyphenolMix.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1250)
                .fluidInputs(PolyphenolMix.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(200))
                .fluidOutputs(AcidifiedPolyphenolMix.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(200))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(750)
                .fluidInputs(Ethanol.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(50))
                .fluidOutputs(Diethylether.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(1150)
                .fluidInputs(Diethylether.getFluid(300))
                .fluidInputs(AcidifiedPolyphenolMix.getFluid(1000))
                .fluidOutputs(TannicAcid.getFluid(300))
                .fluidOutputs(WoodTar.getFluid(700))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(650)
                .inputs(ZincLeachingResidue.getItemStack())
                .fluidInputs(TannicAcid.getFluid(1000))
                .fluidOutputs(IronSulfate.getFluid(1000))
                .fluidOutputs(GermanicAcidSolution.getFluid(450))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(500)
                .fluidInputs(IronSulfate.getFluid(6000))
                .outputs(OreDictUnifier.get(dust, Iron))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(GermanicAcidSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(GermaniumChloride.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1500)
                .fluidInputs(GermaniumChloride.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .outputs(GermaniumOxide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1300).blastFurnaceTemp(1300)
                .inputs(GermaniumOxide.getItemStack())
                .fluidInputs(Hydrogen.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Germanium))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();


    }
}
