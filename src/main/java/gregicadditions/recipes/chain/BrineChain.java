package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAEnums.GAOrePrefix.dioxide;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LUV;


public class BrineChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(2400)
                .notConsumable(dust, Platinum)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(AcryloNitrile.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2700)
                .input(dust, Sulfur)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidInputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(230).EUt(2300)
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidInputs(AcryloNitrile.getFluid(1000))
                .fluidInputs(SodiumThiocyanate.getFluid(1000))
                .fluidOutputs(PolyacrylonitrileSolution.getFluid(2000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(8400)
                .input(plate, RhodiumPlatedPalladium, 4)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .outputs(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(180).EUt(10000).blastFurnaceTemp(600)
                .notConsumable(RAPIDLY_ROTATING_CRUCIBLE.getStackForm())
                .fluidInputs(PolyacrylonitrileSolution.getFluid(2000))
                .outputs(AcrylicFibers.getItemStack())
                .fluidOutputs(SodiumThiocyanate.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(9000)
                .inputs(AcrylicFibers.getItemStack())
                .input(wireFine, Gold)
                .outputs(ACRYLIC_YARN.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(FormicAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(MethylFormate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(650)
                .fluidInputs(MethylFormate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(WetFormamide.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(230).EUt(750)
                .fluidInputs(WetFormamide.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Formamide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(240).EUt(4500)
                .fluidInputs(AmmoniaNitrate.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(HydroxilamineDisulfate.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1050)
                .fluidInputs(HydroxilamineDisulfate.getFluid(4000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Hydroxilamine.getFluid(1000))
                .fluidOutputs(DilutedAmmonia.getFluid(4000))
                .outputs(PrecipitatedAmmoniumSulfate.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(125)
                .fluidInputs(Water.getFluid(1000))
                .inputs(PrecipitatedAmmoniumSulfate.getItemStack())
                .fluidOutputs(AmmoniumSulfate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(250).EUt(125)
                .fluidInputs(AmmoniumSulfate.getFluid(1000))
                .outputs(PrecipitatedAmmoniumSulfate.getItemStack())
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(300).EUt(375)
                .fluidInputs(DilutedAmmonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(3000)
                .fluidInputs(Formamide.getFluid(1000))
                .fluidInputs(Hydroxilamine.getFluid(1000))
                .fluidOutputs(Amidoxime.getFluid(2000))
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

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(125)
                .fluidInputs(PureUranylNitrateSolution.getFluid(900))
                .outputs(UranylNitrate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).blastFurnaceTemp(500)
                .inputs(UranylNitrate.getItemStack())
                .outputs(OreDictUnifier.get(dioxide, UraniumRadioactive.getMaterial()))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(DebrominatedWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(60))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(60))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(SeaWater.getFluid(1000))
                .fluidOutputs(Brine.getFluid(60))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(80).EUt(400)
                .fluidInputs(Brine.getFluid(1000))
                .fluidOutputs(ConcentratedBrine.getFluid(900))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SeaWater.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(100))
                .fluidOutputs(AcidicSaltWater.getFluid(1200))
                .EUt(480)
                .duration(200)
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
                .fluidInputs(Brine.getFluid(1000))
                .fluidOutputs(CalciumFreeBrine.getFluid(1000))
                .outputs(CalciumSalts.getItemStack(2))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(155).EUt(5000)
                .fluidInputs(CalciumFreeBrine.getFluid(1000))
                .fluidOutputs(SodiumFreeBrine.getFluid(1000))
                .outputs(SodiumSalts.getItemStack(2))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(160).EUt(5000)
                .fluidInputs(SodiumFreeBrine.getFluid(1000))
                .fluidOutputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(PotassiumMagnesiumSalts.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(16000)
                .inputs(BORON_RETAINING_YARN.getStackForm())
                .fluidInputs(PotassiumFreeBrine.getFluid(1000))
                .outputs(BORON_SATURATED_YARN.getStackForm())
                .fluidOutputs(BoronFreeSolution.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(160).EUt(5000)
                .input(dust, SodaAsh)
                .input(dust, Quicklime)
                .fluidInputs(BoronFreeSolution.getFluid(1000))
                .outputs(CalciumMagnesiumSalts.getItemStack(2))
                .fluidOutputs(SodiumLithiumSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(16000)
                .inputs(LITHIUM_SIEVE.getStackForm())
                .fluidInputs(SodiumLithiumSolution.getFluid(1000))
                .fluidOutputs(SodiumChlorideSolution.getFluid(1000))
                .outputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(8000)
                .inputs(LITHIUM_SATURATED_LITHIUM_SIEVE.getStackForm())
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .chancedOutput(LITHIUM_SIEVE.getStackForm(), 9000, 0)
                .fluidOutputs(LithiumChlorideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(3000)
                .input(dust, Sodium)
                .input(dust, Aluminium)
                .fluidInputs(Hydrogen.getFluid(2000))
                .outputs(SodiumAluminiumHydride.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(3000)
                .inputs(LithiumChloride.getItemStack())
                .inputs(SodiumAluminiumHydride.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt))
                .outputs(LithiumAluminiumHydride.getItemStack())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(3000).EUt(250)
                .inputs(Cellulose.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(50))
                .outputs(Glucose.getItemStack())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(320).EUt(500)
                .input(dust, Sugar, 2)
                .fluidInputs(DilutedHydrochloricAcid.getFluid(250))
                .outputs(Glucose.getItemStack())
                .outputs(Fructose.getItemStack())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().duration(150).EUt(50000)
                .inputs(Cellulose.getItemStack())
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucose.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(800)
                .input(dust, Sodium)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(SodiumAzanide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1150)
                .fluidInputs(AmmoniaNitrate.getFluid(1000))
                .fluidOutputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(900)
                .inputs(SodiumAzanide.getItemStack(2))
                .fluidInputs(NitrousOxide.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(SodiumAzide.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(3000).blastFurnaceTemp(1200)
                .inputs(Glucose.getItemStack())
                .inputs(LithiumAluminiumHydride.getItemStack())
                .inputs(SodiumAzanide.getItemStack())
                .fluidInputs(AceticAcid.getFluid(4000))
                .outputs(Glucosamine.getItemStack())
                .outputs(AluminiumHydride.getItemStack())
                .fluidOutputs(AcetateSolution.getFluid(5000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(1500)
                .fluidInputs(AcetateSolution.getFluid(5000))
                .fluidOutputs(AceticAcid.getFluid(4000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Sodium))
                .outputs(OreDictUnifier.get(dust, Lithium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(750)
                .inputs(AluminiumHydride.getItemStack())
                .fluidInputs(Water.getFluid(3000))
                .outputs(AluminiumHydroxide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().duration(100).EUt(50000)
                .fluidInputs(Chitosan.getFluid(1000))
                .inputs(EschericiaColi.getItemStack())
                .outputs(Glucosamine.getItemStack())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(5500)
                .inputs(Glucosamine.getItemStack())
                .input(wireFine, Gold)
                .fluidInputs(Styrene.getFluid(144))
                .outputs(BORON_RETAINING_YARN.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(3200)
                .inputs(BORON_SATURATED_YARN.getStackForm())
                .input(dust, SodiumHydroxide)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(BoricAcid.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .chancedOutput(BORON_RETAINING_YARN.getStackForm(), 9000, 0)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(300)
                .input(dust, Sodium)
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(SodiumHydride.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(220).EUt(500).blastFurnaceTemp(1000)
                .input(dust, Carbon, 2)
                .input(dust, Sulfur, 4)
                .outputs(OreDictUnifier.get(dustTiny, Ash))
                .fluidOutputs(CarbonSulfide.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(2200)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Methylamine.getFluid(2000))
                .fluidInputs(CarbonSulfide.getFluid(2000))
                .input(dust, SodiumHydroxide)
                .fluidOutputs(DimethylthiocarbamoilChloride.getFluid(5000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(3200)
                .notConsumable(dust, Palladium)
                .notConsumable(SodiumHydride.getItemStack())
                .fluidInputs(Resorcinol.getFluid(2000))
                .fluidInputs(DimethylthiocarbamoilChloride.getFluid(1000))
                .fluidOutputs(Mercaphenol.getFluid(1000))
                .fluidOutputs(Dimethylformamide.getFluid(2000))
                .buildAndRegister();

        CRACKER_UNIT_RECIPES.recipeBuilder().duration(230).EUt(1200)
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

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(700)
                .notConsumable(dust, Nickel)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmineMixture.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(650)
                .fluidInputs(AmineMixture.getFluid(1800))
                .fluidOutputs(Trimethylamine.getFluid(450))
                .fluidOutputs(Dimethylamine.getFluid(725))
                .fluidOutputs(Methylamine.getFluid(625))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1500)
                .inputs(MolybdenumTrioxide.getItemStack())
                .input(dust, SodiumHydroxide, 2)
                .outputs(SodiumMolybdate.getItemStack())
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1900)
                .inputs(SodiumMolybdate.getItemStack(12))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(13000))
                .outputs(SodiumPhosphomolybdate.getItemStack(11))
                .fluidOutputs(SodiumChlorideSolution.getFluid(13000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(1900)
                .fluidInputs(SodiumTungstate.getFluid(12000))
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(13000))
                .outputs(SodiumPhosphotungstate.getItemStack(11))
                .fluidOutputs(SodiumChlorideSolution.getFluid(13000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(500)
                .fluidInputs(Propene.getFluid(3000))
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(IsopropylAlcohol.getFluid(6000))
                .notConsumable(SodiumPhosphomolybdate.getItemStack())
                .notConsumable(SodiumPhosphotungstate.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(2400)
                .input(dust, Iridium)
                .fluidInputs(PhosphorusTrichloride.getFluid(2000))
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidInputs(Mercaphenol.getFluid(1000))
                .outputs(DehydrogenationCatalyst.getItemStack())
                .fluidOutputs(Chlorine.getFluid(500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(1800)
                .notConsumable(DehydrogenationCatalyst.getItemStack())
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidOutputs(Butane.getFluid(1000))
                .fluidOutputs(Oct1ene.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2400)
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidInputs(Oct1ene.getFluid(1000))
                .fluidOutputs(CetaneTrimethylAmmoniumBromide.getFluid(4000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(3000)
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(AmmoniumPersulfate.getFluid(10))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(20))
                .outputs(PolystyreneNanoParticles.getItemStack(1))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(300).EUt(9000).blastFurnaceTemp(500)
                .input(dust, LithiumTitanate, 2)
                .inputs(PolystyreneNanoParticles.getItemStack(2))
                .outputs(LITHIUM_SIEVE.getStackForm())
                .fluidOutputs(Styrene.getFluid(2000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(CalciumSalts.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Calcite))
                .outputs(OreDictUnifier.get(dust, Gypsum))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(SodiumSalts.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt))
                .chancedOutput(OreDictUnifier.get(dustTiny, SodiumFluoride), 400, 0)
                .buildAndRegister();


        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(PotassiumMagnesiumSalts.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .outputs(MagnesiumSulfate.getItemStack())
                .outputs(PotassiumSulfate.getItemStack())
                .chancedOutput(OreDictUnifier.get(dustTiny, PotassiumFluoride), 400, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(2500)
                .inputs(CalciumMagnesiumSalts.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Calcite))
                .chancedOutput(StrontiumSulfate.getItemStack(), 40, 0)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(MagnesiumHydroxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(250).EUt(300)
                .fluidInputs(SodiumChlorideSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .buildAndRegister();


        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(MagnesiumSulfate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Magnesium))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(250)
                .inputs(MagnesiumHydroxide.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, MagnesiumChloride))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(720).EUt(600).blastFurnaceTemp(1200)
                .inputs(StrontiumSulfate.getItemStack())
                .input(dust, SodaAsh)
                .input(dust, Carbon)
                .fluidInputs(Water.getFluid(1000))
                .outputs(StrontiumOxide.getItemStack())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfide))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(300).EUt(260)
                .inputs(StrontiumOxide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Strontium))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(280).EUt(500)
                .fluidInputs(Brine.getFluid(1000))
                .fluidOutputs(ChilledBrine.getFluid(750))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(ChilledBrine.getFluid(2000))
                .fluidOutputs(MagnesiumContainingBrine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Calcite))
                .outputs(OreDictUnifier.get(dust, Gypsum))
                .outputs(OreDictUnifier.get(dust, Salt))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(340).EUt(500)
                .fluidInputs(MagnesiumContainingBrine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, MagnesiumChloride))
                .outputs(MagnesiumSulfate.getItemStack())
                .fluidOutputs(LithiumChlorideSolution.getFluid(200))
                .buildAndRegister();
    }
}
