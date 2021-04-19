package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class PlatinumSludgeGroupChain {

    /*
     * Mole counts of certain "unknown" dusts.
     * Some of these I kept as a sum of their "components," some of which are
     * known and some of which are "?" (RareEarth in the material list).
     *
     * Others I left as 1 dust per mole to make stack sizes better, or to
     * preserve the ore byproduct rates. If it is staying at 1, I will explain
     * my reasoning for that specific case.
     *
     * ##### MOLE COUNTS #####
     *
     * - IrLeachResidue: 1
     *     - Ore byproduct
     * - PGSDResidue: 5
     * - PGSDResidue2: 2
     * - PlatinumMetallicPowder: 2
     * - PlatinumResidue: 5
     * - LeachResidue: 4
     * - IrOsLeachResidue: 1
     *     - Ore byproduct
     * - PlatinumSaltCrude: 2
     * - PlatinumRawPowder: 3
     * - PlatinumSaltRefined: 2
     * - PalladiumSalt: 2
     * - PalladiumMetallicPowder: 2
     * - PalladiumRawPowder: 2
     * - CrudeRhodiumMetal: 2
     * - RhodiumSalt: 3
     * - RhodiumNitrate: 13
     * - RhodiumFilterCake: 2
     * - ReRhodium: 2
     */

    public static void init() {
        platinumInit();
        palladiumInit();
        rhodiumInit();
        rutheniumInit();
        osmiumInit();
    }

    public static void platinumInit() {

        // Ir2O4(SiO2)2Au3 -> 2IrO2 + (SiO2)2Au3
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(775)
                .input(dust, IrLeachResidue)
                .output(dust, IridiumDioxide, 6)
                .output(dust, PGSDResidue, 5)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        // (SiO2)2Au3 -> 2SiO2 + 3Au
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, PGSDResidue, 5)
                .output(dust, SiliconDioxide, 6)
                .output(dust, Gold, 3)
                .EUt(10)
                .duration(226)
                .buildAndRegister();

        // 2IrO2 + HCl -> (HCl)2(IrO2)2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumDioxide, 6)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(AcidicIridiumSolution.getFluid(1000))
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        // (HCl)2(IrO2)2 + 4NH4Cl -> 4NH3 + 2IrCl3 + 3H2O
        // loses 1 Oxygen
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcidicIridiumSolution.getFluid(1000))
                .fluidInputs(AmmoniumChloride.getFluid(3000))
                .fluidOutputs(Ammonia.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .output(dust, IridiumChloride, 8)
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        // IrCl3 + 3H -> 3HCl + Ir + (CuNi)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumChloride, 4)
                .fluidInputs(Hydrogen.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .output(dust, PGSDResidue2, 2)
                .output(dust, Iridium)
                .duration(300)
                .EUt(1920)
                .buildAndRegister();

        // Pt[REE] + [HNO3 + HCl] -> Pt[REE] + Ir2[REE]3
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumMetallicPowder, 2)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(PlatinumConcentrate.getFluid(1000))
                .output(dustTiny, PlatinumResidue, 5)
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        // HNO3 + HCl -> [HNO3 + HCl]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AquaRegia.getFluid(2000))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        // [HNO3 + HCl] -> HNO3 + HCl
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(30)
                .duration(192) // copied duration from Nitration Mixture
                .buildAndRegister();

        // 2K + 2S + 7O -> K2S2O7
        MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
                .input(dust, Potassium, 2)
                .input(dust, Sulfur, 2)
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust, PotassiumDisulfate, 11)
                .EUt(90)
                .duration(42)
                .buildAndRegister();

        // Ir2[REE]3 + K2S2O7 + O -> Ir2[REE]2 + K2SO4 + RhSO4
        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumResidue, 5)
                .input(dust, PotassiumDisulfate, 11)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, LeachResidue, 4)
                .outputs(PotassiumSulfate.getItemStack(7))
                .fluidOutputs(RhodiumSulfate.getFluid(1000))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        // 3Na2CO3 + 10Ir2[REE]2 + 3O -> 6Ir2O2(SiO2)2Au3[REE] + 3Na2O4Ru + 3CO
        // 10 -> 6 of Leaches is to maintain previous balance
        // probably the worst recipe in the chain, most needing of a rework
        BLAST_RECIPES.recipeBuilder()
                .input(dust, SodaAsh, 18)
                .input(dust, LeachResidue, 40)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, IrOsLeachResidue, 6)
                .output(dust, SodiumRuthenate, 21)
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        // HCl + 2Ir2O2(SiO2)2Au3[REE] -> OsO4(H2O)(HCl) + 2Ir2O2(SiO2)2Au3
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, IrOsLeachResidue, 2)
                .fluidOutputs(AcidicOsmiumSolution.getFluid(2000))
                .output(dust, IrLeachResidue, 2)
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        // HCl + NH3 -> NH4Cl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        // This recipe is "good", but its formula is pretty dumb so I'm not gonna bother with it
        // could use a rework eventually
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumConcentrate.getFluid(2000))
                .fluidInputs(AmmoniumChloride.getFluid(200))
                .output(dustSmall, PlatinumSaltCrude, 10)
                .output(dust, PlatinumRawPowder, 2)
                .fluidOutputs(PalladiumAmmonia.getFluid(200))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1200))
                .EUt(30)
                .duration(1200)
                .buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltCrude, 2)
                .chancedOutput(OreDictUnifier.get(dust, PlatinumSaltRefined, 2), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltRefined, 2)
                .output(dust, PlatinumMetallicPowder, 2)
                .fluidOutputs(Chlorine.getFluid(133))
                .EUt(120)
                .blastFurnaceTemp(775)
                .duration(200)
                .buildAndRegister();

        // PtCl2 + Ca -> Pt + CaCl2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumRawPowder, 3)
                .input(dust, Calcium)
                .output(dust, Platinum)
                .output(dust, CalciumChloride, 3)
                .EUt(30)
                .duration(250)
                .buildAndRegister();
    }

    public static void palladiumInit() {

        // NH3 + Pd? -> NH3Pd?
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder, 2)
                .fluidOutputs(PalladiumAmmonia.getFluid(1000))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        // NH3Pd? -> Pd?
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .output(dust, PalladiumSalt, 2)
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        // Pd? -> Pd?
        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PalladiumSalt, 2)
                .chancedOutput(OreDictUnifier.get(dust, PalladiumMetallicPowder, 2), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        // NH3Pd? + Pd? ->
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder, 2)
                .output(dustSmall, PalladiumSalt, 10)
                .output(dustTiny, PalladiumRawPowder, 12)
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumRawPowder, 2)
                .fluidInputs(FormicAcid.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(3000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .output(dust, Palladium, 2)
                .EUt(1920)
                .duration(300)
                .buildAndRegister();
    }

    public static void rhodiumInit() {

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfate.getFluid(3000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(RhodiumSulfateSolution.getFluid(3000))
                .output(dustTiny, LeachResidue, 4)
                .EUt(30)
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfateSolution.getFluid(1000))
                .input(dust, Zinc)
                .output(dust, ZincSulfate, 6)
                .output(dust, CrudeRhodiumMetal, 2)
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        // Rh[NaCl] + NaCl -> Rh(NaCl)2
        BLAST_RECIPES.recipeBuilder()
                .input(dust, CrudeRhodiumMetal, 2)
                .input(dust, Salt, 2)
                .output(dust, RhodiumSalt, 3)
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        // Rh(NaCl)2 + Cl -> Rh(NaCl)2Cl
        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumSalt, 3)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(RhodiumSaltSolution.getFluid(1000))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        // 2Na + 2HNO3 + O -> 2NaNO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 2)
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, SodiumNitrate, 10)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(60)
                .duration(8)
                .buildAndRegister();

        // Rh(NaCl)2Cl + NaNO3 + 2NO2 + 2O -> 3NaCl + Rh(NH3)3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSaltSolution.getFluid(1000))
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .input(dust, SodiumNitrate, 5)
                .output(dust, Salt, 6)
                .output(dust, RhodiumNitrate, 13)
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        // Rh(NH3)3 -> Rh(NH3)3
        SIFTER_RECIPES.recipeBuilder()
                .input(dust, RhodiumNitrate, 13)
                .chancedOutput(OreDictUnifier.get(dust, RhodiumFilterCake, 2), 9500, 0)
                .EUt(30)
                .duration(600)
                .buildAndRegister();

        // Rh(NH3)3 + 2H2O -> Rh(NH3)3(H2O)2
        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumFilterCake, 2)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(RhodiumFilterCakeSolution.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        // Rh(NH3)2(H2O)2 -> Rh(H2O)2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumFilterCakeSolution.getFluid(1000))
                .output(dust, ReRhodium, 2)
                .fluidOutputs(Ammonia.getFluid(2000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        // Rh(H2O)2 -> Rh + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ReRhodium, 2)
                .output(dust, Rhodium)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();
    }

    public static void rutheniumInit() {

        // Na2O4Ru + 2Cl -> (NaCl)2RuO4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumRuthenate, 14)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(RutheniumTetroxideSolution.getFluid(1000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        // (NaCl)2RuO4 + H2O -> (NaCl)2RuO4(H2O)
        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(Steam.getFluid(1000))
                .fluidInputs(RutheniumTetroxideSolution.getFluid(1000))
                .fluidOutputs(HotRutheniumTetroxideSolution.getFluid(2000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();

        // Multiplying the mixture out
        // (NaCl)2RuO4(H2O) -> 2NaCl + RuO4 + H2O
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HotRutheniumTetroxideSolution.getFluid(2000))
                .output(dust, Salt, 4)
                .fluidOutputs(RutheniumTetroxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(1500)
                .EUt(480)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BALL)
                .fluidInputs(RutheniumTetroxide.getFluid(1000))
                .output(dust, RutheniumTetroxide, 5)
                .EUt(8)
                .duration(16)
                .buildAndRegister();

        // RuO4 + 8HCl -> 4H2O + 8Cl + Ru
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RutheniumTetroxide, 5)
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(Chlorine.getFluid(8000))
                .output(dust, Ruthenium)
                .EUt(30)
                .duration(300)
                .buildAndRegister();
    }

    public static void osmiumInit() {

        // OsO4(H2O)(HCl) -> OsO4(H2O) + HCl
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(AcidicOsmiumSolution.getFluid(2000))
                .fluidOutputs(OsmiumSolution.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        // OsO4(H2O) + 8HCl -> Os + 8Cl + 5H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(OsmiumSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .output(dust, Osmium)
                .fluidOutputs(Chlorine.getFluid(8000))
                .fluidOutputs(Water.getFluid(5000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .input(ingot, Palladium, 3)
                .fluidInputs(Rhodium.getFluid(144))
                .output(ingotHot, RhodiumPlatedPalladium, 4)
                .EUt(7980)
                .duration(200)
                .buildAndRegister();
    }
}
