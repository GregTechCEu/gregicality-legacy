package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE;

public class ZincChain {
    public static void init() {

        // ZnS + C + H2O = [ZnS + C + H2O]
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(500)
                .input(dust, Sphalerite, 2)
                .input(dust, Coke)
                .fluidInputs(DistilledWater.getFluid(1000))
                .outputs(ZincCokePellets.getItemStack(3))
                .buildAndRegister();

        // 2[ZnS + C + H2O] + 6O = 2ZnO + Zinc Residual Slag + Zinc Exhaust [Contains: Dark Ash + CO2 + SO2]
        BLAST_RECIPES.recipeBuilder().duration(60).EUt(16000).blastFurnaceTemp(1500)
                .inputs(ZincCokePellets.getItemStack(6))
                .fluidInputs(Oxygen.getFluid(6000))
                .output(dust, Zincite, 4)
                .outputs(ZincResidualSlag.getItemStack())
                .fluidOutputs(ZincExhaustMixture.getFluid(1000))
                .buildAndRegister();

        // ZnO + H2SO4 = ZnSO4 + ZincLeachingResidue [Contains: H2O]
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(80).EUt(8000)
                .input(dust, Zincite, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, ZincSulfate, 6)
                .outputs(ZincLeachingResidue.getItemStack())
                .buildAndRegister();

        // Zinc Exhaust = Dark Ash + Zinc Flue Dust + CO2 + SO2
        CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(2000)
                .fluidInputs(ZincExhaustMixture.getFluid(1000))
                .chancedOutput(ZincFlueDust.getItemStack(), 4500, 750)
                .output(dustSmall, DarkAsh)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        // Zinc Residual Slag = Fine Zinc Slag Dust
        MACERATOR_RECIPES.recipeBuilder().duration(180).EUt(1250)
                .inputs(ZincResidualSlag.getItemStack())
                .outputs(FineZincSlagDust.getItemStack())
                .buildAndRegister();

        // Fine Zinc Slag Dust + H2O = Zinc Slag Slurry [Contains: Fine Zinc Slag Dust + H2O]
        MIXER_RECIPES.recipeBuilder().duration(110).EUt(1400)
                .inputs(FineZincSlagDust.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(ZincSlagSlurry.getFluid(1000))
                .buildAndRegister();

        // [Fine Zinc Slag Dust + H2O] = Metal Rich Slag Slurry [Contains: H2O] + Zinc ResidualS lag
        ELECTROLYZER_RECIPES.recipeBuilder().duration(230).EUt(1500)
                .fluidInputs(ZincSlagSlurry.getFluid(1000))
                .fluidOutputs(MetalRichSlagSlurry.getFluid(1000))
                .chancedOutput(ZincResidualSlag.getItemStack(), 2500, 0)
                .buildAndRegister();

        // Metal Rich Slag Slurry [Contains: H2O] + H3PO4 = Acidic Metal Slurry [Contains: H2O + H3PO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1600)
                .fluidInputs(MetalRichSlagSlurry.getFluid(1000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidOutputs(AcidicMetalSlurry.getFluid(1000))
                .buildAndRegister();

        // 2 Acidic Metal Slurry [Contains: H2O + H3PO4] + H3PO4 = Separated Metal Slurry [Contains: 3 H3PO4 + H2O] + Metal Rich Slag Slurry [Contains: H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .fluidInputs(AcidicMetalSlurry.getFluid(2000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidOutputs(SeparatedMetalSlurry.getFluid(1000))
                .fluidOutputs(MetalRichSlagSlurry.getFluid(1000))
                .buildAndRegister();

        // Separated Metal Slurry [Contains: 3 H3PO4 + H2O] + 2NaOH = Metal Hydroxide Mix + Na2HPO4 + 2H2O
        // 3 H3PO4 + 6 NaOH + H2O = 3 Na2HPO4 + 7 H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1900)
                .fluidInputs(SeparatedMetalSlurry.getFluid(1000))
                .input(dust, SodiumHydroxide, 6)
                .fluidOutputs(MetalHydroxideMix.getFluid(1000))
                .fluidOutputs(Water.getFluid(7000))
                .outputs(SodiumDiphosphate.getItemStack(24))
                .buildAndRegister();

        // Na2HPO4 = H + 4O + P + 2Na
        ELECTROLYZER_RECIPES.recipeBuilder().duration(50).EUt(500)
                .inputs(SodiumDiphosphate.getItemStack(8))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(4000))
                .output(dust, Phosphorus)
                .output(dust, Sodium, 2)
                .buildAndRegister();

        // Metal Hydroxide Mix = 0.25Zn + Zinc Poor Mix
        //
        // People were regularly confused about these three recipes, as it essentially just gave 0.5 of the metal per
        // recipe, so I changed it to not consume the fine wire, and output 2 Small Dusts of the metal instead.
        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(MetalHydroxideMix.getFluid(1000))
                .notConsumable(wireFine, Zinc)
                .output(dustSmall, Zinc, 2)
                .fluidOutputs(ZincPoorMix.getFluid(1000))
                .buildAndRegister();

        // Zinc Poor Mix = 0.25Fe + Iron Poor Mix
        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(ZincPoorMix.getFluid(1000))
                .notConsumable(wireFine, Iron)
                .output(dustSmall, Iron, 2)
                .fluidOutputs(IronPoorMix.getFluid(1000))
                .buildAndRegister();

        // Iron Poor Mix = 0.25Cu + Indium Hydroxide Concentrate [Contains: In(OH)3]
        ELECTROLYZER_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(IronPoorMix.getFluid(1000))
                .notConsumable(wireFine, Copper)
                .output(dustSmall, Copper, 2)
                .fluidOutputs(IndiumHydroxideConcentrate.getFluid(1000))
                .buildAndRegister();

        // Indium Hydroxide Concentrate [Contains: In(OH)3] = In(OH)3
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(230).EUt(950)
                .fluidInputs(IndiumHydroxideConcentrate.getFluid(1000))
                .outputs(IndiumHydroxide.getItemStack(7))
                .buildAndRegister();

        // In(OH)3 + 3H = In + 3H2O
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1150)
                .blastFurnaceTemp(4500)
                .inputs(IndiumHydroxide.getItemStack(7))
                .fluidInputs(Hydrogen.getFluid(3000))
                .output(dust, Indium)
                .fluidOutputs(Steam.getFluid(3000))
                .buildAndRegister();

        // Zinc Flue Dust + H2SO4 = Cadmium Zinc Dust [Contains: Cd + Zn + H2SO4]
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .inputs(ZincFlueDust.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(CadmiumZincDust.getItemStack(3))
                .buildAndRegister();

        // Cadmium Zinc Dust [Contains: Cd + Zn + H2SO4] + Hg = Cadmium Thallium Liquor [Contains: Cd + Tl + H2SO4] + Zinc Amalgam [Contains: Zn + Hg]
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(850)
                .inputs(CadmiumZincDust.getItemStack(3))
                .fluidInputs(Mercury.getFluid(1000))
                .fluidOutputs(CadmiumThalliumLiquor.getFluid(1000))
                .fluidOutputs(ZincAmalgam.getFluid(1000))
                .buildAndRegister();

        // Zinc Amalgam [Contains: Zn + Hg] = Zn + Hg
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(3200)
                .fluidInputs(ZincAmalgam.getFluid(1000))
                .fluidOutputs(Mercury.getFluid(1000))
                .chancedOutput(OreDictUnifier.get(dust, Zinc), 6500, 550)
                .buildAndRegister();

        // 9 Cadmium Thallium Liquor [Contains: Cd + Tl + H2SO4] = Thallium Residue [Contains: 2 Tl] + 9 Cadmium Sulfate Solution [Contains: CdSO4]
        DISTILLATION_RECIPES.recipeBuilder().duration(210).EUt(1800)
                .fluidInputs(CadmiumThalliumLiquor.getFluid(9000))
                .outputs(ThalliumResidue.getItemStack())
                .fluidOutputs(CadmiumSulfateSolution.getFluid(9000))
                .buildAndRegister();

        // 9 Cadmium Sulfate Solution [Contains: CdSO4] = 9 H2SO4 + Cd
        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(1600)
                .fluidInputs(CadmiumSulfateSolution.getFluid(9000))
                .fluidOutputs(SulfuricAcid.getFluid(9000))
                .output(dust, Cadmium)
                .buildAndRegister();

        // Thallium Residue [Contains: 2 Tl] + H2SO4 = Thallium Sulfate Solution [Contains: Tl2SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1400)
                .inputs(ThalliumResidue.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(ThalliumSulfateSolution.getFluid(1000))
                .buildAndRegister();

        // Thallium Sulfate Solution [Contains: Tl2SO4] + HCl + HClO = 2TlCl + H2SO4 + H2O (because solution)
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1100)
                .fluidInputs(ThalliumSulfateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .outputs(ThalliumChloride.getItemStack(4))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // 2TlCl + Zn = 2Tl + ZnCl2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(850).blastFurnaceTemp(750)
                .inputs(ThalliumChloride.getItemStack(4))
                .input(dust, Zinc)
                .output(dust, Thallium, 2)
                .outputs(ZincChloride.getItemStack(3))
                .buildAndRegister();

        // ZnCl2 = Zn + 2Cl
        ELECTROLYZER_RECIPES.recipeBuilder().duration(150).EUt(1150)
                .inputs(ZincChloride.getItemStack(3))
                .output(dust, Zinc)
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();

        // Na2CO3 + SO2 = Na2SO3 + CO2
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(250)
                .input(dust, SodaAsh, 6)
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .outputs(SodiumSulfite.getItemStack(6))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // 2 Wood + Na2SO4 + H2O2 + [NaOH + H2O] = 2C6H10O5 + Polyphenol Mix
        // This recipe is close enough
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(1200)
                .input(dust, Wood, 2)
                .inputs(SodiumSulfite.getItemStack(6))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(Cellulose.getItemStack(42))
                .fluidOutputs(PolyphenolMix.getFluid(1000))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(250).EUt(750)
                .inputs(Cellulose.getItemStack(4)) // BUFF!
                .notConsumable(SHAPE_MOLD_PLATE)
                .outputs(new ItemStack(Items.PAPER))
                .buildAndRegister();

        // Polyphenol Mix + 0.2HCl = Acidified Polyphenol Mix + 0.2 Dilute HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1250)
                .fluidInputs(PolyphenolMix.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(200))
                .fluidOutputs(AcidifiedPolyphenolMix.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(200))
                .buildAndRegister();

        // 2C2H5OH = (C2H5)2O + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(750)
                .fluidInputs(Ethanol.getFluid(2000))
                .notConsumable(SulfuricAcid.getFluid(0))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Diethylether.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // 0.3(C2H5)2O + Acidified Polyphenol Mix = 0.3 Tannic Acid + 0.7 Wood Tar
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(1150)
                .fluidInputs(Diethylether.getFluid(300))
                .fluidInputs(AcidifiedPolyphenolMix.getFluid(1000))
                .fluidOutputs(TannicAcid.getFluid(300))
                .fluidOutputs(WoodTar.getFluid(700))
                .buildAndRegister();

        // ZincLeachingResidue [Contains: H2O] + Tannic Acid = FeSO4 + 0.5H4GeO4
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(650)
                .inputs(ZincLeachingResidue.getItemStack())
                .fluidInputs(TannicAcid.getFluid(1000))
                .outputs(IronSulfateDust.getItemStack(6))
                .fluidOutputs(GermanicAcidSolution.getFluid(500))
                .buildAndRegister();

        // H4GeO4 + 4HCl = [GeCl4 + 4H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(850)
                .fluidInputs(GermanicAcidSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(GermaniumChloride.getFluid(1000))
                .buildAndRegister();

        // [GeCl4 + 4H2O] = 4HCl + GeO2 + 2H2O (lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(230).EUt(1500)
                .fluidInputs(GermaniumChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .outputs(GermaniumOxide.getItemStack(3))
                .buildAndRegister();

        // GeO2 + 4H = Ge + 2H2O
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1300).blastFurnaceTemp(1300)
                .inputs(GermaniumOxide.getItemStack(3))
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(dust, Germanium)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();
    }
}
