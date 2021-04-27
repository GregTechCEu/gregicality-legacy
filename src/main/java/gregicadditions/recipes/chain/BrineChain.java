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
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(2400)
                .notConsumable(dust, Platinum)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(AcryloNitrile.getFluid(1000))
                .buildAndRegister();

        // S + NaCN -> NaSCN
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2700)
                .input(dust, Sulfur)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidOutputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        // NO + C3H3N + NaSCN -> [C3H3N]n(NaSCN) assumes polymerized with Oxygen bond
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(230).EUt(2300)
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidInputs(AcryloNitrile.getFluid(1000))
                .fluidInputs(SodiumThiocyanate.getFluid(1000))
                .fluidOutputs(PolyacrylonitrileSolution.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(8400)
                .input(plate, RhodiumPlatedPalladium, 4)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .outputs(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .buildAndRegister();

        // [C3H3N]n -> NaSCN + (solidified)[C3H3N]n
        BLAST_RECIPES.recipeBuilder().duration(180).EUt(10000).blastFurnaceTemp(600)
                .notConsumable(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .fluidInputs(PolyacrylonitrileSolution.getFluid(1000))
                .outputs(AcrylicFibers.getItemStack())
                .fluidOutputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(9000)
                .inputs(AcrylicFibers.getItemStack())
                .input(wireFine, Gold)
                .outputs(ACRYLIC_YARN.getStackForm())
                .buildAndRegister();

        // CH2O2 + CH3OH -> H2O + C2H4O2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(FormicAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(MethylFormate.getFluid(1000))
                .buildAndRegister();

        // C2H4O2 + 2NH3 + 2O -> 2CH3NO(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(650)
                .fluidInputs(MethylFormate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(WetFormamide.getFluid(1000))
                .buildAndRegister();

        // CH3NO(H2O) -> CH3NO + H2O
        DISTILLATION_RECIPES.recipeBuilder().duration(230).EUt(750)
                .fluidInputs(WetFormamide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Formamide.getFluid(1000))
                .buildAndRegister();

        // 3NH4NO3 + 8SO2 + 7H2O + 2NH3 -> 4(NH3OH)2SO4
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(240).EUt(4500)
                .fluidInputs(AmmoniumNitrate.getFluid(3000))
                .fluidInputs(SulfurDioxide.getFluid(8000))
                .fluidInputs(Water.getFluid(7000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(HydroxylamineDisulfate.getFluid(4000))
                .buildAndRegister();

        // (NH3OH)2SO4 + 2NH3 -> 2H3NO + (NH4)2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1050)
                .fluidInputs(HydroxylamineDisulfate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(Hydroxylamine.getFluid(2000))
                .fluidOutputs(AmmoniumSulfate.getFluid(1000))
                .buildAndRegister();

        // CH3NO + H3NO -> H3N2O(CH) + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(3000)
                .fluidInputs(Formamide.getFluid(1000))
                .fluidInputs(Hydroxylamine.getFluid(1000))
                .fluidOutputs(Amidoxime.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(180).EUt(6000)
                .inputs(ACRYLIC_YARN.getStackForm())
                .fluidInputs(Amidoxime.getFluid(1000))
                .outputs(HEAVY_METAL_ABSORBING_YARN.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(10000)
                .inputs(HEAVY_METAL_ABSORBING_YARN.getStackForm())
                .fluidInputs(SeaWater.getFluid(16000))
                .outputs(URANIUM_SATURATED_YARN.getStackForm())
                .fluidOutputs(SaltWater.getFluid(16000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1600)
                .inputs(URANIUM_SATURATED_YARN.getStackForm())
                .fluidInputs(NitricAcid.getFluid(100))
                .chancedOutput(HEAVY_METAL_ABSORBING_YARN.getStackForm(), 9900, 0)
                .fluidOutputs(PureUranylNitrateSolution.getFluid(100))
                .buildAndRegister();

        // ? -> UO2(NO3)2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(125)
                .fluidInputs(PureUranylNitrateSolution.getFluid(900))
                .outputs(UranylNitrate.getItemStack(11))
                .buildAndRegister();

        // UO2(NO3)2 + H2O -> UO2 + 2HNO3(dil.)
        BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).blastFurnaceTemp(500)
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

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(DebrominatedWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(SeaWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(100))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(80).EUt(400)
                .fluidInputs(Brine.getFluid(1000))
                .fluidOutputs(ConcentratedBrine.getFluid(800))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BrominatedBrine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(100))
                .fluidOutputs(AcidicBrominatedBrine.getFluid(1200))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcidicBrominatedBrine.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(500))
                .fluidInputs(Water.getFluid(500))
                .fluidOutputs(Brine.getFluid(900))
                .fluidOutputs(SulfuricBromineSolution.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(150).EUt(5000)
                .fluidInputs(ConcentratedBrine.getFluid(1000))
                .fluidOutputs(CalciumFreeBrine.getFluid(1000))
                .outputs(CalciumSalts.getItemStack(13))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(155).EUt(5000)
                .fluidInputs(CalciumFreeBrine.getFluid(1000))
                .fluidOutputs(SodiumFreeBrine.getFluid(1000))
                .outputs(SodiumSalts.getItemStack(4))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(160).EUt(5000)
                .fluidInputs(SodiumFreeBrine.getFluid(1000))
                .fluidOutputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(PotassiumMagnesiumSalts.getItemStack(30))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(16000)
                .inputs(BORON_RETAINING_YARN.getStackForm())
                .fluidInputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(BORON_SATURATED_YARN.getStackForm())
                .fluidOutputs(BoronFreeSolution.getFluid(1000))
                .buildAndRegister();

        // Na2CO3, CaO
        MIXER_RECIPES.recipeBuilder().duration(160).EUt(5000)
                .input(dust, SodaAsh ,6)
                .input(dust, Quicklime, 2)
                .fluidInputs(BoronFreeSolution.getFluid(1000))
                .outputs(CalciumMagnesiumSalts.getItemStack(16))
                .fluidOutputs(SodiumLithiumSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(16000)
                .inputs(LITHIUM_SIEVE.getStackForm())
                .fluidInputs(SodiumLithiumSolution.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(1000))
                .outputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(8000)
                .inputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .chancedOutput(LITHIUM_SIEVE.getStackForm(), 9000, 0)
                .fluidOutputs(LithiumChlorideSolution.getFluid(1000))
                .buildAndRegister();

        // Al + 3H -> AlH3
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(60)
                .input(dust, Aluminium)
                .fluidInputs(Hydrogen.getFluid(3000))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        // NaH + AlH3 -> NaAlH4
        MIXER_RECIPES.recipeBuilder().duration(190).EUt(3000)
                .inputs(SodiumHydride.getItemStack(2))
                .inputs(AluminiumHydride.getItemStack(4))
                .outputs(SodiumAluminiumHydride.getItemStack(6))
                .buildAndRegister();

        // LiCl + NaAlH4 -> NaCl + LiAlH4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(3000)
                .inputs(LithiumChloride.getItemStack(2))
                .inputs(SodiumAluminiumHydride.getItemStack(6))
                .output(dust, Salt, 2)
                .outputs(LithiumAluminiumHydride.getItemStack(6))
                .buildAndRegister();

        // C6H10O5 + H2O -> C6H12O6
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(3000).EUt(250)
                .inputs(Cellulose.getItemStack(21))
                .fluidInputs(Water.getFluid(1000))
                .outputs(Glucose.getItemStack(24))
                .buildAndRegister();

        // C12H22O11 + H2O -> C6H12O6 + C6H12O6
        // Leaving sugar as 2 input since it would be rough to make it 45
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(320).EUt(500)
                .input(dust, Sugar, 2)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Glucose.getItemStack(24))
                .outputs(Fructose.getItemStack(24))
                .buildAndRegister();

        // C6H10O5 + bacteria -> C6H12O6
        BIO_REACTOR_RECIPES.recipeBuilder().duration(150).EUt(50000)
                .inputs(Cellulose.getItemStack(21))
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucose.getItemStack(24))
                .buildAndRegister();

        // Na + NH3 -> H + NaNH2
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(800)
                .input(dust, Sodium)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(SodiumAzanide.getItemStack(4))
                .buildAndRegister();

        // NH4NO3 -> N2O + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1150)
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2NaNH2 + N2O -> NH3 + NaOH + NaN3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(900)
                .inputs(SodiumAzanide.getItemStack(8))
                .fluidInputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .outputs(SodiumAzide.getItemStack(4))
                .buildAndRegister();

        // NaOH + H2O -> NaOH(H2O)
        MIXER_RECIPES.recipeBuilder().duration(50).EUt(30)
                .notConsumable(new IntCircuitIngredient(0))
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // C6H12O6 + LiAlH4 + NaNH2 + H2SO4 + O -> LiOH(H2O) + C6H13NO5 + AlH3 + NaHSO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(3000)
                .inputs(Glucose.getItemStack(24))
                .inputs(LithiumAluminiumHydride.getItemStack(6))
                .inputs(SodiumAzanide.getItemStack(4))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .outputs(Glucosamine.getItemStack(25))
                .outputs(AluminiumHydride.getItemStack(4))
                .output(dust, SodiumBisulfate, 7)
                .buildAndRegister();

        // AlH3 + 3H2O -> Al(OH)3 + 6H
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(750)
                .inputs(AluminiumHydride.getItemStack(4))
                .fluidInputs(Water.getFluid(3000))
                .outputs(AluminiumHydroxide.getItemStack(7))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        // [C6H11NO4]n + bacteria -> C6H13NO5
        BIO_REACTOR_RECIPES.recipeBuilder().duration(100).EUt(50000)
                .fluidInputs(Chitosan.getFluid(1000))
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucosamine.getItemStack(25))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(5500)
                .inputs(Glucosamine.getItemStack(25))
                .input(wireFine, Gold)
                .fluidInputs(Styrene.getFluid(144))
                .outputs(BORON_RETAINING_YARN.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(3200)
                .inputs(BORON_SATURATED_YARN.getStackForm())
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(BoricAcid.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .chancedOutput(BORON_RETAINING_YARN.getStackForm(), 9000, 0)
                .buildAndRegister();

        // 2H3BO3 -> 3H2O + B2O3
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(1800).blastFurnaceTemp(750)
                .notConsumable(dust, Boron)
                .fluidInputs(BoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3000))
                .outputs(BoronOxide.getItemStack(5))
                .buildAndRegister();

        // B2O3 + 6HF -> 3H2O + 2BF3
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1600)
                .inputs(BoronOxide.getItemStack(5))
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(BoronFluoride.getFluid(2000))
                .buildAndRegister();

        // 4BF3 + 3LiAlH4 -> 2B2H6 + 3AlF4Li
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(1300)
                .fluidInputs(BoronFluoride.getFluid(4000))
                .inputs(LithiumAluminiumHydride.getItemStack(18))
                .fluidOutputs(Diborane.getFluid(2000))
                .outputs(LithiumAluminiumFluoride.getItemStack(18))
                .buildAndRegister();

        // AlF4Li -> AlF3 + LiF
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(1200).blastFurnaceTemp(3400)
                .inputs(LithiumAluminiumFluoride.getItemStack(6))
                .outputs(AluminiumTrifluoride.getItemStack(4))
                .output(dust, LithiumFluoride, 2)
                .buildAndRegister();

        // 2AlF3 + 3H2O -> Al2O3 + 6HF
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1750)
                .inputs(AluminiumTrifluoride.getItemStack(8))
                .fluidInputs(Water.getFluid(3000))
                .outputs(Alumina.getItemStack(5))
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .buildAndRegister();

        // B2H6 -> 2B + 6H
        BLAST_RECIPES.recipeBuilder().duration(250).EUt(2000).blastFurnaceTemp(600)
                .notConsumable(dust, Boron)
                .fluidInputs(Diborane.getFluid(1000))
                .output(dust, Boron, 2)
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        // Na + H -> NaH
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(300)
                .input(dust, Sodium)
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(SodiumHydride.getItemStack(2))
                .buildAndRegister();

        // 2C + 4S -> 2CS2
        BLAST_RECIPES.recipeBuilder().duration(220).EUt(500).blastFurnaceTemp(1000)
                .input(dust, Carbon, 2)
                .input(dust, Sulfur, 4)
                .output(dustTiny, Ash)
                .fluidOutputs(CarbonSulfide.getFluid(2000))
                .buildAndRegister();

        // 3C + 2HCl + 2CH3NH2 + CS2 -> 2C3H6ClNS
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(300).EUt(2200)
                .input(dust, Carbon, 3)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Methylamine.getFluid(2000))
                .fluidInputs(CarbonSulfide.getFluid(1000))
                .fluidOutputs(DimethylthiocarbamoilChloride.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .buildAndRegister();

        // C6H6O2 + C3H6ClNS + 2H -> C6H6OS + (CH3)2NCH + HClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(3200)
                .notConsumable(dust, Palladium)
                .notConsumable(SodiumHydride.getItemStack())
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(DimethylthiocarbamoilChloride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Mercaphenol.getFluid(1000))
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .buildAndRegister();

        CRACKING_RECIPES.recipeBuilder().duration(230).EUt(1200)
                .fluidInputs(Hydrogen.getFluid(250))
                .fluidInputs(Dimethylformamide.getFluid(750))
                .fluidOutputs(HydrogenCrackedDMF.getFluid(1000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(340).EUt(600)
                .fluidInputs(HydrogenCrackedDMF.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(120))
                .fluidOutputs(Methanol.getFluid(120))
                .fluidOutputs(CarbonMonoxde.getFluid(70))
                .fluidOutputs(Methylamine.getFluid(330))
                .fluidOutputs(Dimethylamine.getFluid(160))
                .fluidOutputs(Methane.getFluid(100))
                .fluidOutputs(Ammonia.getFluid(100))
                .buildAndRegister();

        // CH3OH + NH3 -> CH3OH(NH3)
        MIXER_RECIPES.recipeBuilder().duration(180).EUt(700)
                .notConsumable(dust, Nickel)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmineMixture.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(650)
                .fluidInputs(AmineMixture.getFluid(2000))
                .fluidOutputs(Trimethylamine.getFluid(500))
                .fluidOutputs(Dimethylamine.getFluid(800))
                .fluidOutputs(Methylamine.getFluid(680))
                .buildAndRegister();

        // MoO3 + 2NaOH -> Na2MoO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .inputs(MolybdenumTrioxide.getItemStack(4))
                .input(dust, SodiumHydroxide, 6)
                .outputs(SodiumMolybdate.getItemStack(7))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // 12Na2MoO4 + H3PO4 + 21HCl -> Mo12Na3O40P + 21NaCl + 12H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1900)
                .inputs(SodiumMolybdate.getItemStack(84))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(13000))
                .outputs(SodiumPhosphomolybdate.getItemStack(56))
                .output(dust, Salt, 42)
                .fluidOutputs(Water.getFluid(12000))
                .buildAndRegister();

        // 12Na2WO4 + H3PO4 + 21HCl -> Na3O40PW12 + 21NaCl + 12H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1900)
                .fluidInputs(SodiumTungstate.getFluid(12000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(21000))
                .outputs(SodiumPhosphotungstate.getItemStack(56))
                .output(dust, Salt, 42)
                .fluidOutputs(Water.getFluid(12000))
                .buildAndRegister();

        // 3C3H6 + 3H2O -> 3C3H8O
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(Propene.getFluid(3000))
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(IsopropylAlcohol.getFluid(3000))
                .notConsumable(SodiumPhosphomolybdate.getItemStack())
                .notConsumable(SodiumPhosphotungstate.getItemStack())
                .buildAndRegister();

        // Ir + 2PCl3 + C3H8O + C6H6OS -> ? + 6Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2400)
                .input(dust, Iridium)
                .fluidInputs(PhosphorusTrichloride.getFluid(2000))
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidInputs(Mercaphenol.getFluid(1000))
                .outputs(DehydrogenationCatalyst.getItemStack())
                .fluidOutputs(Chlorine.getFluid(6000))
                .buildAndRegister();

        // C4H8 + C8H18 -> C4H10 + C8H16
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(1800)
                .notConsumable(DehydrogenationCatalyst.getItemStack())
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidOutputs(Butane.getFluid(1000))
                .fluidOutputs(Oct1ene.getFluid(1000))
                .buildAndRegister();

        // N(CH3)3 + Br + C8H18 + C8H16 -> C19H42BrN + H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2400)
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidInputs(Oct1ene.getFluid(1000))
                .fluidOutputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(3000)
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(AmmoniumPersulfate.getFluid(10))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(20))
                .outputs(PolystyreneNanoParticles.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(300).EUt(9000).blastFurnaceTemp(500)
                .input(dust, LithiumTitanate, 2)
                .inputs(PolystyreneNanoParticles.getItemStack(2))
                .outputs(LITHIUM_SIEVE.getStackForm())
                .fluidOutputs(Styrene.getFluid(2000))
                .buildAndRegister();

        // CaCO3, CaSO4(H2O)2
        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(CalciumSalts.getItemStack(13))
                .output(dust, Calcite, 5)
                .output(dust, Gypsum, 8)
                .buildAndRegister();

        // NaCl, NaF
        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(SodiumSalts.getItemStack(2))
                .output(dust, Salt, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, SodiumFluoride, 2), 400, 0)
                .buildAndRegister();

        // KCl, MgSO4, K2SO4
        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(PotassiumMagnesiumSalts.getItemStack(15))
                .output(dust, RockSalt, 2)
                .outputs(MagnesiumSulfate.getItemStack(6))
                .outputs(PotassiumSulfate.getItemStack(7))
                .chancedOutput(OreDictUnifier.get(dustTiny, PotassiumFluoride, 2), 400, 0)
                .buildAndRegister();

        // CaCO3, CO2, MgO
        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
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
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(250)
                .input(dust, Magnesia, 2)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, MagnesiumChloride, 3)
                .buildAndRegister();

        // SrSO4 + Na2CO3 + 2C -> SrCO3 + 2CO2 + Na2S
        BLAST_RECIPES.recipeBuilder().duration(720).EUt(600).blastFurnaceTemp(1200)
                .inputs(StrontiumSulfate.getItemStack(6))
                .input(dust, SodaAsh, 6)
                .input(dust, Carbon, 2)
                .outputs(StrontiumCarbonate.getItemStack(5))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .output(dust, SodiumSulfide, 3)
                .buildAndRegister();

        // SrCO3 -> SrO + CO2
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(250)
                .inputs(StrontiumCarbonate.getItemStack(5))
                .outputs(StrontiumOxide.getItemStack(2))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // SrO -> Sr + O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(300).EUt(260)
                .inputs(StrontiumOxide.getItemStack(2))
                .output(dust, Strontium)
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(280).EUt(500)
                .fluidInputs(Brine.getFluid(6400))
                .fluidOutputs(ChilledBrine.getFluid(3000))
                .buildAndRegister();

        // CaCO3, CaSO4(H2O)2, NaCl, KCl
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(ChilledBrine.getFluid(2000))
                .fluidOutputs(MagnesiumContainingBrine.getFluid(1000))
                .output(dust, Calcite, 5)
                .output(dust, Gypsum, 8)
                .output(dust, Salt, 2)
                .output(dust, RockSalt, 2)
                .buildAndRegister();

        // MgCl2, MgSO4
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(340).EUt(500)
                .fluidInputs(MagnesiumContainingBrine.getFluid(1000))
                .output(dust, MagnesiumChloride, 3)
                .outputs(MagnesiumSulfate.getItemStack(6))
                .fluidOutputs(LithiumChlorideSolution.getFluid(200))
                .buildAndRegister();
    }
}
