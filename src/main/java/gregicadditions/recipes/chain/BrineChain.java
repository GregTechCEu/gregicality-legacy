package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAEnums.GAOrePrefix.dioxide;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LUV;

public class BrineChain {
    public static void init() {

        // NH3 + C3H6 + 3O -> 3H2O + C3H3N
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .notConsumable(dust, Platinum)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(AcryloNitrile.getFluid(1000))
                .buildAndRegister();

        // S + NaCN -> NaSCN
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Sulfur)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidOutputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        // NO + C3H3N + NaSCN -> [C3H3N]n(NaSCN) polymerized with Oxygen bond
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidInputs(AcryloNitrile.getFluid(1000))
                .fluidInputs(SodiumThiocyanate.getFluid(1000))
                .fluidOutputs(PolyacrylonitrileSolution.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(7680)
                .input(plate, RhodiumPlatedPalladium, 4)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .outputs(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .buildAndRegister();

        // [C3H3N]n -> NaSCN + (solidified)[C3H3N]n
        BLAST_RECIPES.recipeBuilder().duration(180).EUt(120).blastFurnaceTemp(600)
                .notConsumable(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .fluidInputs(PolyacrylonitrileSolution.getFluid(1000))
                .outputs(AcrylicFibers.getItemStack())
                .fluidOutputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(24)
                .inputs(AcrylicFibers.getItemStack())
                .input(wireFine, Gold)
                .outputs(ACRYLIC_YARN.getStackForm())
                .buildAndRegister();

        // CH2O2 + CH3OH -> H2O + C2H4O2
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(FormicAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(MethylFormate.getFluid(1000))
                .buildAndRegister();

        // C2H4O2 + 2NH3 + 2O -> 2CH3NO(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(120)
                .fluidInputs(MethylFormate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(WetFormamide.getFluid(1000))
                .buildAndRegister();

        // CH3NO(H2O) -> CH3NO + H2O
        DISTILLATION_RECIPES.recipeBuilder().duration(200).EUt(120)
                .fluidInputs(WetFormamide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Formamide.getFluid(1000))
                .buildAndRegister();

        // CH3NO(H2O) -> CH3NO + H2O
        FLUID_HEATER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(WetFormamide.getFluid(100))
                .fluidOutputs(Formamide.getFluid(100))
                .buildAndRegister();

        // NH4NO3 + 2 SO2 + 3 H2O + 2 NH3 = [(NH3OH)2SO4 + (NH4)2SO4]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .fluidInputs(Water.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(HydroxylamineDisulfate.getFluid(2000))
                .buildAndRegister();

        // [(NH3OH)2SO4 + (NH4)2SO4] + 2 NH3 -> 2H3NO + 2 (NH4)2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(HydroxylamineDisulfate.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(Hydroxylamine.getFluid(2000))
                .fluidOutputs(AmmoniumSulfate.getFluid(2000))
                .buildAndRegister();

        // CH3NO + H3NO -> H3N2O(CH) + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(Formamide.getFluid(1000))
                .fluidInputs(Hydroxylamine.getFluid(1000))
                .fluidOutputs(Amidoxime.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(40).EUt(480)
                .inputs(ACRYLIC_YARN.getStackForm())
                .fluidInputs(Amidoxime.getFluid(100))
                .outputs(HEAVY_METAL_ABSORBING_YARN.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .inputs(HEAVY_METAL_ABSORBING_YARN.getStackForm())
                .fluidInputs(SeaWater.getFluid(16000))
                .outputs(URANIUM_SATURATED_YARN.getStackForm())
                .fluidOutputs(SaltWater.getFluid(16000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1920)
                .inputs(URANIUM_SATURATED_YARN.getStackForm())
                .fluidInputs(NitricAcid.getFluid(100))
                .chancedOutput(HEAVY_METAL_ABSORBING_YARN.getStackForm(), 9900, 0)
                .fluidOutputs(PureUranylNitrateSolution.getFluid(100))
                .buildAndRegister();

        // ? -> UO2(NO3)2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(PureUranylNitrateSolution.getFluid(900))
                .outputs(UranylNitrate.getItemStack(11))
                .buildAndRegister();

        // UO2(NO3)2 + H2O -> UO2 + 2HNO3(dil.)
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(500)
                .inputs(UranylNitrate.getItemStack(11))
                .fluidInputs(Water.getFluid(1000))
                .output(dioxide, UraniumRadioactive.getMaterial(), 3)
                .fluidOutputs(DiluteNitricAcid.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(300).EUt(480)
                .fluidInputs(DiluteNitricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(DebrominatedWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(480)
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(SeaWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(160).EUt(480)
                .fluidInputs(Brine.getFluid(1000))
                .fluidOutputs(ConcentratedBrine.getFluid(800))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(BrominatedBrine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AcidicBrominatedBrine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(AcidicBrominatedBrine.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Brine.getFluid(1000))
                .fluidOutputs(SulfuricBromineSolution.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(150).EUt(120)
                .fluidInputs(ConcentratedBrine.getFluid(1000))
                .fluidOutputs(CalciumFreeBrine.getFluid(1000))
                .outputs(CalciumSalts.getItemStack(13))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(160).EUt(120)
                .fluidInputs(CalciumFreeBrine.getFluid(1000))
                .fluidOutputs(SodiumFreeBrine.getFluid(1000))
                .outputs(SodiumSalts.getItemStack(4))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(200).EUt(120)
                .fluidInputs(SodiumFreeBrine.getFluid(1000))
                .fluidOutputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(PotassiumMagnesiumSalts.getItemStack(30))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(480)
                .inputs(BORON_RETAINING_YARN.getStackForm())
                .fluidInputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(BORON_SATURATED_YARN.getStackForm())
                .fluidOutputs(BoronFreeSolution.getFluid(1000))
                .buildAndRegister();

        // Na2CO3, CaO
        MIXER_RECIPES.recipeBuilder().duration(80).EUt(1920)
                .input(dust, SodaAsh ,6)
                .input(dust, Quicklime, 2)
                .fluidInputs(BoronFreeSolution.getFluid(1000))
                .outputs(CalciumMagnesiumSalts.getItemStack(16))
                .fluidOutputs(SodiumLithiumSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(480)
                .inputs(LITHIUM_SIEVE.getStackForm())
                .fluidInputs(SodiumLithiumSolution.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(1000))
                .outputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .inputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .chancedOutput(LITHIUM_SIEVE.getStackForm(), 9000, 0)
                .fluidOutputs(LithiumChlorideSolution.getFluid(1000))
                .buildAndRegister();

        // Al + 3H -> AlH3
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .input(dust, Aluminium)
                .fluidInputs(Hydrogen.getFluid(3000))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        // NaH + AlH3 -> NaAlH4
        MIXER_RECIPES.recipeBuilder().duration(190).EUt(30)
                .inputs(SodiumHydride.getItemStack(2))
                .inputs(AluminiumHydride.getItemStack(4))
                .outputs(SodiumAluminiumHydride.getItemStack(6))
                .buildAndRegister();

        // LiCl + NaAlH4 + H2O -> NaCl(H2O) + LiAlH4
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(3000)
                .inputs(LithiumChloride.getItemStack(2))
                .inputs(SodiumAluminiumHydride.getItemStack(6))
                .fluidInputs(Water.getFluid(1000))
                .outputs(LithiumAluminiumHydride.getItemStack(6))
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();

        // C12H22O11 + H2O -> C6H12O6 + C6H12O6
        // Input 2 sugar, 45 is too hard
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, Sugar, 2)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Glucose.getItemStack(24))
                .outputs(Fructose.getItemStack(24))
                .buildAndRegister();

        // C6H10O5 + bacteria -> C6H12O6
        BIO_REACTOR_RECIPES.recipeBuilder().duration(150).EUt(120)
                .inputs(Cellulose.getItemStack(21))
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucose.getItemStack(24))
                .buildAndRegister();

        // Na + NH3 -> H + NaNH2
        CHEMICAL_RECIPES.recipeBuilder().duration(110).EUt(120)
                .input(dust, Sodium)
                .fluidInputs(Ammonia.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(SodiumAzanide.getItemStack(4))
                .buildAndRegister();

        // NH4NO3 -> N2O + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2NaNH2 + N2O -> NH3 + NaOH + NaN3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(120)
                .inputs(SodiumAzanide.getItemStack(8))
                .fluidInputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .outputs(SodiumAzide.getItemStack(4))
                .buildAndRegister();

        // NaOH + H2O -> NaOH(H2O)
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // C6H12O6 + LiAlH4 + NaN3 + H2SO4 + H2O -> LiOH(H2O) + C6H11O5NH2 + AlH3 + NaHSO4 + 2 N
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(120)
                .inputs(Glucose.getItemStack(24))
                .inputs(LithiumAluminiumHydride.getItemStack(6))
                .inputs(SodiumAzide.getItemStack(4))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .outputs(Glucosamine.getItemStack(25))
                .outputs(AluminiumHydride.getItemStack(4))
                .output(dust, SodiumBisulfate, 7)
                .buildAndRegister();

        // AlH3 + 3H2O -> Al(OH)3 + 6H
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(AluminiumHydride.getItemStack(4))
                .fluidInputs(Water.getFluid(3000))
                .outputs(AluminiumHydroxide.getItemStack(7))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        // [C6H11NO4]n + bacteria -> C6H13NO5
        BIO_REACTOR_RECIPES.recipeBuilder().duration(100).EUt(4096)
                .fluidInputs(Chitosan.getFluid(1000))
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucosamine.getItemStack(25))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(480)
                .inputs(Glucosamine.getItemStack(2))
                .input(wireFine, Gold)
                .fluidInputs(Polystyrene.getFluid(144))
                .outputs(BORON_RETAINING_YARN.getStackForm())
                .buildAndRegister();

        // 2NaOH + H2SO4 + HBO2 -> H3BO3 + Na2SO4(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(480)
                .inputs(BORON_SATURATED_YARN.getStackForm())
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(BoricAcid.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .chancedOutput(BORON_RETAINING_YARN.getStackForm(), 9900, 0)
                .buildAndRegister();

        // 2H3BO3 -> 3H2O + B2O3 (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(100).EUt(120)
                .notConsumable(dust, Boron)
                .fluidInputs(BoricAcid.getFluid(2000))
                .outputs(BoronOxide.getItemStack(5))
                .buildAndRegister();

        // B2O3 -> 2B + 3O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(400).EUt(120)
                .inputs(BoronOxide.getItemStack(5))
                .output(dust, Boron, 2)
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        // B2O3 + 6HF -> 3H2O + 2BF3
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120)
                .inputs(BoronOxide.getItemStack(5))
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(BoronFluoride.getFluid(2000))
                .buildAndRegister();

        // 4BF3 + 3LiAlH4 -> 2B2H6 + 3AlF4Li
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .fluidInputs(BoronFluoride.getFluid(4000))
                .inputs(LithiumAluminiumHydride.getItemStack(18))
                .fluidOutputs(Diborane.getFluid(2000))
                .outputs(LithiumAluminiumFluoride.getItemStack(18))
                .buildAndRegister();

        // LiAlF4 -> AlF3 + LiF
        ELECTROLYZER_RECIPES.recipeBuilder().duration(250).EUt(120)
                .inputs(LithiumAluminiumFluoride.getItemStack(6))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .output(dust, LithiumFluoride, 2)
                .buildAndRegister();

        // 2AlF3 + 3H2O -> Al2O3 + 6HF
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .inputs(AluminiumTrifluoride.getItemStack(8))
                .fluidInputs(Water.getFluid(3000))
                .outputs(Alumina.getItemStack(5))
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .buildAndRegister();

        // B2H6 -> 2B + 6H (H lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(60).EUt(480)
                .notConsumable(dust, Boron)
                .fluidInputs(Diborane.getFluid(1000))
                .output(dust, Boron, 2)
                .buildAndRegister();

        // Na + H -> NaH
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(30)
                .input(dust, Sodium)
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(SodiumHydride.getItemStack(2))
                .buildAndRegister();

        // C + 2S -> CS2
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1000)
                .input(dust, Carbon)
                .input(dust, Sulfur, 2)
                .fluidOutputs(CarbonSulfide.getFluid(1000))
                .output(dustTiny, Ash)
                .buildAndRegister();

        // 3C + 2HCl + 2CH3NH2 + CS2 -> 2C3H6ClNS
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .input(dust, Carbon, 3)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Methylamine.getFluid(2000))
                .fluidInputs(CarbonSulfide.getFluid(1000))
                .fluidOutputs(DimethylthiocarbamoilChloride.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .buildAndRegister();

        // C6H6O2 + (CH3)2NCClS  + LiAlH4 -> C6H6OS + (CH3)2NCHO + LiCl + AlH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(500)
                .notConsumable(dust, Palladium)
                .notConsumable(SodiumHydride.getItemStack())
                .notConsumable(Dimethylformamide.getFluid(500))
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(DimethylthiocarbamoilChloride.getFluid(1000))
                .inputs(LithiumAluminiumHydride.getItemStack(6))
                .fluidOutputs(Mercaptophenol.getFluid(1000))
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .outputs(LithiumChloride.getItemStack(2))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120)
                .fluidInputs(Hydrogen.getFluid(250))
                .fluidInputs(Dimethylformamide.getFluid(750))
                .fluidOutputs(Formaldehyde.getFluid(120))
                .fluidOutputs(Dimethylamine.getFluid(160))
                .buildAndRegister();

        // CH3OH + NH3 -> CH3OH(NH3)
        MIXER_RECIPES.recipeBuilder().duration(160).EUt(960)
                .notConsumable(dust, Nickel)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmineMixture.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(AmineMixture.getFluid(2000))
                .fluidOutputs(Trimethylamine.getFluid(500))
                .fluidOutputs(Dimethylamine.getFluid(800))
                .fluidOutputs(Methylamine.getFluid(700))
                .buildAndRegister();

        //(CH3)2NH + HCOOCH3 -> (CH3)2NCHO + CH3OH
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(MethylFormate.getFluid(1000))
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .fluidOutputs(Methanol.getFluid(1000))
                .buildAndRegister();

        // MoO3 + 2NaOH -> Na2MoO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30)
                .inputs(MolybdenumTrioxide.getItemStack(4))
                .input(dust, SodiumHydroxide, 6)
                .outputs(SodiumMolybdate.getItemStack(7))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // 12Na2MoO4 + H3PO4 -> Mo12Na3O40P + 10 Na2O + H2O + NaOH (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(1920)
                .inputs(SodiumMolybdate.getItemStack(84))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .outputs(SodiumPhosphomolybdate.getItemStack(56))
                .output(dust, SodiumHydroxide, 3)
                .outputs(SodiumOxide.getItemStack(30))
                .buildAndRegister();

        // 12Na2WO4 + H3PO4 -> Na3O40PW12 + 10 Na2O + H2O + NaOH (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(1920)
                .fluidInputs(SodiumTungstate.getFluid(12000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .outputs(SodiumPhosphotungstate.getItemStack(56))
                .output(dust, SodiumHydroxide, 3)
                .outputs(SodiumOxide.getItemStack(30))
                .buildAndRegister();

        // C3H6 + H2O -> C3H8O
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(IsopropylAlcohol.getFluid(1000))
                .notConsumable(SodiumPhosphomolybdate.getItemStack())
                .notConsumable(SodiumPhosphotungstate.getItemStack())
                .buildAndRegister();

        //2 IrCl3 + 2 C8H12 -> (C8H12)2Ir2Cl2 + 4 Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, IridiumChloride, 8)
                .fluidInputs(Cyclooctadiene.getFluid(2000))
                .outputs(IridiumCyclooctadienylChlorideDimer.getItemStack(44))
                .fluidOutputs(Chlorine.getFluid(4000))
                .buildAndRegister();

        //4 Li + PCl3 + 2 C3H8O + 2 H2O -> 2 LiCl + 2 [LiOH + H2O] + P(C3H7)2Cl
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(500)
                .input(dust, Lithium, 4)
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .outputs(LithiumChloride.getItemStack(4))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(2000))
                .fluidOutputs(ChlorodiisopropylPhosphine.getFluid(1000))
                .buildAndRegister();

        //4 P(C3H7)2Cl + (C8H12)2Ir2Cl2 + 2 C6H6OS -> 2 IrP2C18H32SOCl + 2 C8H12 + 4 HCl (divided by 2)
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1200)
                .inputs(IridiumCyclooctadienylChlorideDimer.getItemStack(22))
                .fluidInputs(ChlorodiisopropylPhosphine.getFluid(2000))
                .fluidInputs(Mercaptophenol.getFluid(1000))
                .notConsumable(dust, BerylliumFluoride)
                .outputs(DehydrogenationCatalyst.getItemStack(56))
                .fluidOutputs(Cyclooctadiene.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // C4H8 + C8H18 -> C4H10 + C8H16
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(120)
                .notConsumable(DehydrogenationCatalyst.getItemStack())
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidOutputs(Butane.getFluid(1000))
                .fluidOutputs(Oct1ene.getFluid(1000))
                .buildAndRegister();

        // N(CH3)3 + Br + C8H18 + C8H16 -> C19H42BrN + H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(480)
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidInputs(Oct1ene.getFluid(1000))
                .fluidOutputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .fluidInputs(Styrene.getFluid(1000))
                .notConsumable(AmmoniumPersulfate.getFluid(0))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(20))
                .outputs(PolystyreneNanoParticles.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, LithiumTitanate, 2)
                .inputs(PolystyreneNanoParticles.getItemStack(2))
                .outputs(LITHIUM_SIEVE.getStackForm())
                .fluidOutputs(Styrene.getFluid(2000))
                .buildAndRegister();

        // CaCO3, CaSO4(H2O)2
        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(CalciumSalts.getItemStack(13))
                .output(dust, Calcite, 5)
                .output(dust, Gypsum, 8)
                .buildAndRegister();

        // NaCl, NaF
        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(SodiumSalts.getItemStack(2))
                .output(dust, Salt, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, SodiumFluoride, 2), 400, 0)
                .buildAndRegister();

        // KCl, MgSO4, K2SO4
        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(PotassiumMagnesiumSalts.getItemStack(15))
                .output(dust, RockSalt, 2)
                .outputs(MagnesiumSulfate.getItemStack(6))
                .outputs(PotassiumSulfate.getItemStack(7))
                .chancedOutput(OreDictUnifier.get(dustTiny, PotassiumFluoride, 2), 400, 0)
                .buildAndRegister();

        // CaCO3, CO2, MgO
        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(CalciumMagnesiumSalts.getItemStack(8))
                .output(dust, Calcite, 5)
                .chancedOutput(StrontiumCarbonate.getItemStack(5), 40, 0)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, Magnesia, 2)
                .buildAndRegister();

        // MgSO4 -> Mg + S + 4O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(MagnesiumSulfate.getItemStack(6))
                .output(dust, Magnesium)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        // MgO + 2HCl -> 2H2O + MgCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(120)
                .input(dust, Magnesia, 2)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, MagnesiumChloride, 3)
                .buildAndRegister();

        // SrSO4 + Na2CO3 + 2C -> SrCO3 + 2CO2 + Na2S
        BLAST_RECIPES.recipeBuilder().duration(360).EUt(120).blastFurnaceTemp(1200)
                .input(dust, Celestine, 6)
                .input(dust, SodaAsh, 6)
                .input(dust, Carbon, 2)
                .outputs(StrontiumCarbonate.getItemStack(5))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .output(dust, SodiumSulfide, 3)
                .buildAndRegister();

        // SrCO3 -> SrO + CO2
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .inputs(StrontiumCarbonate.getItemStack(5))
                .outputs(StrontiumOxide.getItemStack(2))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // SrO -> Sr + O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(300).EUt(120)
                .inputs(StrontiumOxide.getItemStack(2))
                .output(dust, Strontium)
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(260).EUt(120)
                .fluidInputs(Brine.getFluid(6400))
                .fluidOutputs(ChilledBrine.getFluid(3000))
                .buildAndRegister();

        // CaCO3, CaSO4(H2O)2, NaCl, KCl
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(480)
                .fluidInputs(ChilledBrine.getFluid(2000))
                .fluidOutputs(MagnesiumContainingBrine.getFluid(1000))
                .output(dust, Calcite, 5)
                .output(dust, Gypsum, 8)
                .output(dust, Salt, 2)
                .output(dust, RockSalt, 2)
                .buildAndRegister();

        // MgCl2, MgSO4
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(270).EUt(480)
                .fluidInputs(MagnesiumContainingBrine.getFluid(1000))
                .output(dust, MagnesiumChloride, 3)
                .outputs(MagnesiumSulfate.getItemStack(6))
                .fluidOutputs(LithiumChlorideSolution.getFluid(200))
                .buildAndRegister();
    }
}
