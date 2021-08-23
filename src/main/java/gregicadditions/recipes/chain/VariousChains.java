package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK;

public class VariousChains {

    public static void init() {
        memoryFoam();
        drillingMud();
        hydrogenPeroxide();
        fuels();
        coalTar();
        misc();
    }

    private static void misc() {

        // UU-Matter ===================================================================================================
        LARGE_MIXER_RECIPES.recipeBuilder().duration(100).EUt(7680)
                .fluidInputs(BosonicUUMatter.getFluid(1000))
                .fluidInputs(FermionicUUMatter.getFluid(1000))
                .fluidInputs(FreeElectronGas.getFluid(2000))
                .fluidOutputs(UUMatter.getFluid(1000)) //todo UU-Matter
                .buildAndRegister();

        // Sodium Hypochlorite =========================================================================================
        // 2NaOH + 2Cl -> H2O + NaCl + NaClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(120)
                .fluidInputs(Chlorine.getFluid(2000))
                .input(dust, SodiumHydroxide, 6)
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, Salt, 2)
                .output(dust, SodiumHypochlorite, 3)
                .buildAndRegister();

        // HClO + NaOH -> H2O + NaClO
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(120)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, SodiumHypochlorite, 3)
                .buildAndRegister();

        // NaClO + HCl -> NaCl + HClO
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(120)
                .input(dust, SodiumHypochlorite, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .buildAndRegister();

        // Lignite Processing ==========================================================================================
        FLUID_HEATER_RECIPES.recipeBuilder().duration(120).EUt(120)
                .circuitMeta(0)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(HotNitrogen.getFluid(1000))
                .buildAndRegister();

        // C2(H2O)4C + N(hot) -> N + 3C + 4H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(120)
                .input(dust, Lignite)
                .fluidInputs(HotNitrogen.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .output(dust, DehydratedLignite, 3)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(40).EUt(30)
                .input(dust, DehydratedLignite, 3)
                .output(dust, BCEPellet)
                .buildAndRegister();

        // Graphene ====================================================================================================
        // FeCl3 + C6H12O6 = [FeCl3 + C6H12O6]
        MIXER_RECIPES.recipeBuilder().duration(80).EUt(30)
                .fluidInputs(Iron3Chloride.getFluid(1000))
                .input(dust, Glucose, 24)
                .fluidOutputs(GlucoseIronSolution.getFluid(1000))
                .buildAndRegister();

        // [FeCl3 + C6H12O6] -> [6CO + Fe] + 3Cl + 5H2O + H2 (H2O and H2 lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(40).EUt(120)
                .notConsumable(stickLong, YttriumBariumCuprate)
                .fluidInputs(GlucoseIronSolution.getFluid(1000))
                .outputs(GRAPHENE_IRON_PLATE.getStackForm())
                .fluidOutputs(Chlorine.getFluid(3000))
                .buildAndRegister();

        // KMnO4 + NaNO3 + H2SO4 = Graphene Oxidation Solution
        MIXER_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, PotassiumPermanganate, 6)
                .input(dust, SodiumNitrate, 5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(GrapheneOxidationSolution.getFluid(1000))
                .buildAndRegister();

        // Graphite + Oxidation Solution = Graphite Oxide + Residue
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Graphite, 3)
                .fluidInputs(GrapheneOxidationSolution.getFluid(100))
                .output(dust, GraphiteOxide)
                .chancedOutput(dust, GrapheneOxidationResidue, 8000, 1000)
                .buildAndRegister();

        // Graphene + Oxidation Solution = Graphene Oxide + Residue
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(20).EUt(24)
                .input(dust, Graphene)
                .fluidInputs(GrapheneOxidationSolution.getFluid(100))
                .output(dust, GrapheneOxide, 3)
                .chancedOutput(dust, GrapheneOxidationResidue, 8000, 1000)
                .buildAndRegister();

        // Graphene Oxidation Residue -> Graphene Oxidation Solution
        EXTRACTOR_RECIPES.recipeBuilder().duration(65).EUt(24)
                .input(dust, GrapheneOxidationResidue)
                .fluidOutputs(GrapheneOxidationSolution.getFluid(100))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(dust, GraphiteOxide)
                .fluidInputs(Water.getFluid(100))
                .output(dust, GrapheneOxide, 3)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(30).EUt(480)
                .input(dust, GrapheneOxide, 3)
                .notConsumable(WHITE_HALIDE_LAMP.getStackForm())
                .notConsumable(Hydrazine.getFluid(0))
                .output(dust, Graphene)
                .buildAndRegister();

        // [6CO + Fe] -> C6O + Fe
        ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(120).EUt(30)
                .inputs(GRAPHENE_IRON_PLATE.getStackForm())
                .output(dust, GrapheneOxide, 3)
                .output(dust, Iron)
                .buildAndRegister();

        // Pyrotheum ===================================================================================================
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .input(dust, Coal)
                .input(dust, Sulfur)
                .notConsumable(Lava.getFluid(0))
                .output(dust, Blaze, 2)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .input(dust, Charcoal)
                .input(dust, Sulfur)
                .notConsumable(Lava.getFluid(0))
                .output(dust, Blaze, 2)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .input(dust, Blaze, 2)
                .output(dust, Pyrotheum, 2)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(32).EUt(24)
                .input(dust, Pyrotheum)
                .fluidOutputs(Pyrotheum.getFluid(250))
                .buildAndRegister();

        // Cryotheum ===================================================================================================
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .inputs(new ItemStack(Items.SNOWBALL))
                .input(dust, Redstone)
                .notConsumable(Ice.getFluid(0))
                .output(dust, Blizz, 2)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Redstone)
                .inputs(new ItemStack(Items.SNOWBALL))
                .input(dust, Blizz, 2)
                .output(dust, Cryotheum, 2)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(32).EUt(24)
                .input(dust, Cryotheum)
                .fluidOutputs(Cryotheum.getFluid(250))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .duration(200)
                .EUt(480)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Cryotheum.getFluid(10000))
                .fluidOutputs(SupercooledCryotheum.getFluid(10000))
                .buildAndRegister();

        // Nuclear Process Distillation ================================================================================
        DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(120)
                .fluidInputs(RedOil.getFluid(3000))
                .output(dust, FerriteMixture)
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(RP1.getFluid(1000))
                .fluidOutputs(TributylPhosphate.getFluid(1000))
                .buildAndRegister();

        // 3Cl + P -> PCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(60)
                .fluidInputs(Chlorine.getFluid(3000))
                .input(dust, Phosphorus)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(PhosphorusTrichloride.getFluid(1000))
                .buildAndRegister();

        // PCl3 + O -> POCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(PhosphorylChloride.getFluid(1000))
                .buildAndRegister();

        // POCl3 + 3C4H10O -> C12H27O4P + 3HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .fluidInputs(PhosphorylChloride.getFluid(1000))
                .fluidInputs(Butanol.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(TributylPhosphate.getFluid(1000))
                .buildAndRegister();

        // Formic acid =================================================================================================
        // CO + NaOH -> HCOONa
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(60)
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(SodiumFormate.getFluid(1000))
                .buildAndRegister();

        // HCOONa + H2SO4 -> CH2O2 + NaHSO4
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(60)
                .fluidInputs(SodiumFormate.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FormicAcid.getFluid(1000))
                .output(dust, SodiumBisulfate, 7)
                .buildAndRegister();

        // Misc Reactions ==============================================================================================
        // 3Ca + 3PO4 + H + O -> [3Ca + 3PO4 + H + O]
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Calcium, 3)
                .input(dust, Phosphate, 3) // this is probably wrong
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, OrganicFertilizer, 10)
                .buildAndRegister();

        // 2HCl + CaCO3 -> H2O + CO2 + CaCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .input(dust, Calcite, 5)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, CalciumChloride, 3)
                .buildAndRegister();

        // BaSO4 + H2O -> [BaSO4 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .input(dust, Barite)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(BariumSulfateSolution.getFluid(1000))
                .buildAndRegister();

        // CaCO3 + H2O -> [CaCO3 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .input(dust, Calcite)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CalciumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // Bentonite + Clay + 2H2O -> 2[H2O + 0.5 Bentonite + 0.5 Clay]
        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .input(dust, Bentonite)
                .input(dust, Clay)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(BentoniteClaySlurry.getFluid(2000))
                .buildAndRegister();

        // Fe + 3HCl -> FeCl3 + 3H
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, Iron)
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(Iron3Chloride.getFluid(3000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .buildAndRegister();

        // LiCl -> Li + Cl
        ELECTROLYZER_RECIPES.recipeBuilder().duration(110).EUt(120)
                .input(dust, LithiumChloride, 2)
                .fluidOutputs(Chlorine.getFluid(1000))
                .output(dust, Lithium)
                .buildAndRegister();

        // Li + Cl -> LiCl
        CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(120)
                .input(dust, Lithium)
                .fluidInputs(Chlorine.getFluid(1000))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .output(dust, LithiumChloride, 2)
                .buildAndRegister();

        // Al + 3Cl -> AlCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(dust, Aluminium)
                .fluidInputs(Chlorine.getFluid(3000))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .output(dust, AluminiumChloride, 4)
                .buildAndRegister();

        // Cd + S -> CdS
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(300)
                .input(dust, Cadmium)
                .input(dust, Sulfur)
                .output(dust, CadmiumSulfide, 2)
                .buildAndRegister();

        // 2CoO + Al2O3 -> Al2Co2O5
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(500)
                .input(dust, CobaltOxide, 4)
                .input(dust, Alumina, 5)
                .output(dust, CobaltAluminate, 9)
                .buildAndRegister();

        // C7H8 + H2SO4 + NaCl -> C7H7SO3Na + (H2O)(HCl)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(950)
                .input(dust, Salt, 2)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Toluenesulfonate.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        //C8H4O3 + 2 C6H6O2 -> 	C20H12O5 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1150)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Resorcinol.getFluid(2000))
                .output(dust, Fluorescein, 37)
                .fluidOutputs(Water.getFluid(2000))
                .notConsumable(dust, ZincChloride)
                .buildAndRegister();
    }

    private static void hydrogenPeroxide() {

        // C16H12O2H2 + 2O + C14H10 (catalyst) -> H2O2 + C16H12O2
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(240)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidInputs(EthylAnthraHydroQuinone.getFluid(1000))
                .fluidOutputs(HydrogenPeroxide.getFluid(2000))
                .fluidOutputs(EthylAnthraQuinone.getFluid(1000))
                .buildAndRegister();

        // 2H + C16H12O2 -> C16H12O2H2
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(120)
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidInputs(EthylAnthraQuinone.getFluid(1000))
                .fluidOutputs(EthylAnthraHydroQuinone.getFluid(1000))
                .buildAndRegister();

        // C8H4O3 + C8H10 -> C16H12O2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Ethylbenzene.getFluid(1000))
                .fluidOutputs(EthylAnthraQuinone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C8H6O4 -> C8H4O3 + H2O (H2O voided - Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(600).EUt(120)
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .output(dust, PhthalicAnhydride, 15)
                .buildAndRegister();

        // 21O + 4 C10H8 -> 5C8H6O4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(120)
                .notConsumable(dust, Lithium)
                .fluidInputs(Oxygen.getFluid(21000))
                .fluidInputs(Naphthalene.getFluid(4000))
                .fluidOutputs(PhthalicAcid.getFluid(5000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // 2 C6H5F + C2H2 -> C14H10 + 2 HF
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .fluidInputs(FluoroBenzene.getFluid(2000))
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(Anthracene.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .buildAndRegister();
    }

    private static void drillingMud() {

        // HClO + C2H4 -> C2H5ClO
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(480)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidOutputs(Chloroethanol.getFluid(1000))
                .buildAndRegister();

        // C2H5ClO + C3H9N + Na -> C5H14NO + NaCl
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(480)
                .input(dust, Sodium)
                .fluidInputs(Chloroethanol.getFluid(1000))
                .fluidInputs(Trimethylamine.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(Choline.getFluid(1000))
                .buildAndRegister();

        // C2(H2O)4C + C5H14NO -> ATL
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, Lignite)
                .fluidInputs(Choline.getFluid(1000))
                .fluidOutputs(ATL.getFluid(1000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(120).EUt(480)
                .fluidInputs(BariumSulfateSolution.getFluid(1000))
                .fluidInputs(CalciumCarbonateSolution.getFluid(1000))
                .fluidInputs(BentoniteClaySlurry.getFluid(1000))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(ATL.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(DrillingMud.getFluid(6000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(BariumSulfateSolution.getFluid(1000))
                .fluidInputs(CalciumCarbonateSolution.getFluid(1000))
                .fluidOutputs(CaCBaSMixture.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(BentoniteClaySlurry.getFluid(1000))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidOutputs(LubricantClaySlurry.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(ATL.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(ATLEthylene.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(120)
                .fluidInputs(CaCBaSMixture.getFluid(2000))
                .fluidInputs(LubricantClaySlurry.getFluid(2000))
                .fluidOutputs(DrillingMudMixture.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(120)
                .fluidInputs(DrillingMudMixture.getFluid(4000))
                .fluidInputs(ATLEthylene.getFluid(2000))
                .fluidOutputs(DrillingMud.getFluid(6000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(UsedDrillingMud.getFluid(1000))
                .fluidOutputs(DrillingMud.getFluid(990))
                .outputs(new ItemStack(Blocks.GRAVEL))
                .buildAndRegister();
    }

    private static void memoryFoam() {

        // C2H4O + H2O -> [C2H4O + H2O]
        MIXER_RECIPES.recipeBuilder().duration(140).EUt(120)
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(WetEthyleneOxide.getFluid(1000))
                .buildAndRegister();

        // [C2H4O + H2O] -> C2H6O2
        FLUID_HEATER_RECIPES.recipeBuilder().duration(130).EUt(120)
                .circuitMeta(0)
                .fluidInputs(WetEthyleneOxide.getFluid(1000))
                .fluidOutputs(EthyleneGlycol.getFluid(1000))
                .buildAndRegister();

        // C7H8 + 2COCl2 + 2HNO3 -> C9H6N2O2 + 4HClO + 2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(480)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Phosgene.getFluid(2000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(TolueneDiisocyanate.getFluid(2000))
                .fluidOutputs(HypochlorousAcid.getFluid(4000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .buildAndRegister();

        // C9H6N2O2 + 4C2H6O2 + O -> C17H16N2O4 + 7H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(110).EUt(480)
                .fluidInputs(TolueneDiisocyanate.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(4000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Polyurethane.getFluid(1000))
                .fluidOutputs(Water.getFluid(7000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(110).EUt(120)
                .fluidInputs(Polyurethane.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .input(dust, Calcite, 5)
                .fluidOutputs(ViscoelasticPolyurethane.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(150).EUt(120)
                .fluidInputs(ViscoelasticPolyurethane.getFluid(1000))
                .fluidInputs(Air.getFluid(1000))
                .fluidOutputs(ViscoelasticPolyurethaneFoam.getFluid(2000))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(60).EUt(30)
                .fluidInputs(ViscoelasticPolyurethaneFoam.getFluid(1000))
                .notConsumable(SHAPE_MOLD_BLOCK)
                .outputs(MEMORY_FOAM_BLOCK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(8)
                .input(stick, Wood, 3)
                .inputs(MEMORY_FOAM_BLOCK.getStackForm(3))
                .outputs(new ItemStack(Items.BED))
                .buildAndRegister();
    }

    private static void fuels() {

        // Rocket Fuel Tier T4
        // C2H8N2 + N2O4 = H8N4C2O4 (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(Dimethylhydrazine.getFluid(1000))
                .fluidInputs(DinitrogenTetroxide.getFluid(1000))
                .fluidOutputs(RocketFuelH8N4C2O4.getFluid(1000))
                .buildAndRegister();

        // 2NO2 = N2O4
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .notConsumable(dust, Copper)
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(DinitrogenTetroxide.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 3
        // HNO3 + CH3(NH)NH2 = CN3H7O3 (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(240)
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(MonoMethylHydrazine.getFluid(1000))
                .fluidOutputs(RocketFuelCN3H7O3.getFluid(1000))
                .buildAndRegister();

        // C + 2H + N2H4 = CH3(NH)NH2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(480).EUt(240)
                .input(dust, Carbon)
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidOutputs(MonoMethylHydrazine.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 2
        // N2H4 + CH3OH = [N2H4 + CH3OH] (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(120).EUt(240)
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(DenseHydrazineFuelMixture.getFluid(1000))
                .buildAndRegister();

        // Rocket Fuel Tier 1
        // O + RP-1 = [O + RP-1] (treat like chem reactor recipes)
        MIXER_RECIPES.recipeBuilder().duration(16).EUt(240)
                .fluidInputs(LiquidOxygen.getFluid(1000))
                .fluidInputs(RP1.getFluid(1000))
                .fluidOutputs(RP1RocketFuel.getFluid(1000))
                .buildAndRegister();

        // Rocket fuel chemicals
        // 2NH3 + H2O2 = N2H4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // LOX
        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(LiquidOxygen.getFluid(1000))
                .buildAndRegister();

        // Liquid Hydrogen
        VACUUM_RECIPES.recipeBuilder().duration(16).EUt(540)
                .fluidInputs(Hydrogen.getFluid(500))
                .fluidOutputs(LiquidHydrogen.getFluid(500))
                .buildAndRegister();

        // RP1
        DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(120)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Kerosene.getFluid(50))
                .fluidOutputs(RP1.getFluid(25))
                .buildAndRegister();
    }

    private static void coalTar() {

        DISTILLERY_RECIPES.recipeBuilder().duration(200).EUt(30)
                .fluidInputs(Naphthalene.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(Kerosene.getFluid(1000))
                .buildAndRegister();
    }
}
