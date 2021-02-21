package gregicadditions.recipes.chain;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.util.ValidationResult;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class PlatinumSludgeGroupChain {

    public static void init() {

        platinumInit();
        palladiumInit();
        rhodiumInit();
        rutheniumInit();
        osmiumInit();
    }

    public static void platinumInit() {
        //Platinum Process

        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(775)
                .input(dust, IrLeachResidue)
                .outputs(OreDictUnifier.get(dust, IridiumDioxide, 3))
                .outputs(OreDictUnifier.get(dust, PGSDResidue, 5))
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumDioxide, 6)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AcidicIridiumSolution.getFluid(1000))
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcidicIridiumSolution.getFluid(1000))
                .fluidInputs(AmmoniumChloride.getFluid(3000))
                .fluidOutputs(Ammonia.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, IridiumChloride, 4))
                .duration(300)
                .EUt(30)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IridiumChloride, 4)
                .fluidInputs(Hydrogen.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, PGSDResidue2))
                .outputs(OreDictUnifier.get(dust, Iridium))
                .duration(300)
                .EUt(1920)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumMetallicPowder)
                .fluidInputs(AquaRegia.getFluid(1000))
                .fluidOutputs(PlatinumConcentrate.getFluid(1000))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumResidue, 4))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AquaRegia.getFluid(2000))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .input(dust, PotassiumDisulfate)
                .fluidOutputs(PotassiumDisulfate.getFluid(144)).buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
                .input(dust, Potassium, 2)
                .input(dust, Sulfur, 2)
                .fluidInputs(Oxygen.getFluid(7000))
                .outputs(OreDictUnifier.get(dust, PotassiumDisulfate, 11))
                .EUt(90)
                .duration(42)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumResidue, 4)
                .input(dust, PotassiumDisulfate, 33)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, LeachResidue))
                .outputs(OreDictUnifier.get(dust, Potassium, 6))
                .fluidOutputs(RhodiumSulfate.getFluid(1000))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, SodaAsh, 18)
                .input(dust, LeachResidue, 10)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, IrOsLeachResidue, 6))
                .outputs(OreDictUnifier.get(dust, SodiumRuthenate, 3))
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, IrOsLeachResidue, 2)
                .fluidOutputs(AcidicOsmiumSolution.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, IrLeachResidue, 2))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .EUt(30)
                .duration(15)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumConcentrate.getFluid(2000))
                .fluidInputs(AmmoniumChloride.getFluid(200))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumSaltCrude, 12))
                .outputs(OreDictUnifier.get(dustTiny, PlatinumRawPowder, 6))
                .fluidOutputs(PalladiumAmmonia.getFluid(200))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1200))
                .EUt(30)
                .duration(1200)
                .buildAndRegister();

        //PTSalt

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltCrude)
                .chancedOutput(OreDictUnifier.get(dust, PlatinumSaltRefined), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, PlatinumSaltRefined)
                .outputs(OreDictUnifier.get(dust, PlatinumMetallicPowder))
                .fluidOutputs(Chlorine.getFluid(133))
                .EUt(120)
                .blastFurnaceTemp(775)
                .duration(200)
                .buildAndRegister();

        //Platinum

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PlatinumRawPowder)
                .input(dust, Calcium)
                .outputs(OreDictUnifier.get(dust, Platinum))
                .outputs(OreDictUnifier.get(dust, CalciumChloride, 3))
                .EUt(30)
                .duration(250)
                .buildAndRegister();
    }

    public static void palladiumInit() {
        //Palldium
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder)
                .fluidOutputs(PalladiumAmmonia.getFluid(1000))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PalladiumSalt))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, PalladiumSalt)
                .chancedOutput(OreDictUnifier.get(dust, PalladiumMetallicPowder), 9500, 0)
                .EUt(2)
                .duration(400)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(PalladiumAmmonia.getFluid(1000))
                .input(dust, PalladiumMetallicPowder)
                .outputs(OreDictUnifier.get(dustTiny, PalladiumSalt, 12))
                .outputs(OreDictUnifier.get(dustTiny, PalladiumRawPowder, 6))
                .EUt(30)
                .duration(250)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumRawPowder, 2)
                .fluidInputs(FormicAcid.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(3000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Palladium, 2))
                .EUt(1920)
                .duration(300)
                .buildAndRegister();
    }

    public static void rhodiumInit() {
        //Rhodium
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfate.getFluid(30500))
                .fluidInputs(Water.getFluid(30500))
                .fluidOutputs(RhodiumSulfateSolution.getFluid(30500))
                .outputs(OreDictUnifier.get(dustTiny, LeachResidue, 10))
                .EUt(30)
                .duration(1200)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSulfateSolution.getFluid(5000))
                .input(dust, Zinc, 15)
                .outputs(OreDictUnifier.get(dust, ZincSulfate, 64))
                .outputs(OreDictUnifier.get(dust, ZincSulfate, 26))
                .outputs(OreDictUnifier.get(dust, CrudeRhodiumMetall, 2))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, CrudeRhodiumMetall, 10)
                .input(dust, Salt, 2)
                .outputs(OreDictUnifier.get(dust, RhodiumSalt))
                .blastFurnaceTemp(775)
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumSalt)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(RhodiumSaltSolution.getFluid(1000))
                .EUt(30)
                .duration(30)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 2)
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumNitrate, 10))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(60)
                .duration(8)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumSaltSolution.getFluid(1000))
                .input(dust, SodiumNitrate, 5)
                .outputs(OreDictUnifier.get(dust, Salt, 3))
                .outputs(OreDictUnifier.get(dust, RhodiumNitrate, 2))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        SIFTER_RECIPES.recipeBuilder()
                .input(dust, RhodiumNitrate)
                .chancedOutput(OreDictUnifier.get(dust, RhodiumFilterCake), 9500, 0)
                .EUt(30)
                .duration(600)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, RhodiumFilterCake)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(RhodiumFilterCakeSolution.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumFilterCakeSolution.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, ReRhodium))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ReRhodium)
                .outputs(OreDictUnifier.get(dust, Rhodium))
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();
    }

    public static void rutheniumInit() {
        //Ruthenium
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumRuthenate, 2)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(RutheniumTetroxideSolution.getFluid(4000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();
        CRACKING_RECIPES.recipeBuilder()
                .fluidInputs(Steam.getFluid(1000))
                .fluidInputs(RutheniumTetroxideSolution.getFluid(1000))
                .fluidOutputs(HotRutheniumTetroxideSolution.getFluid(2000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HotRutheniumTetroxideSolution.getFluid(12000))
                .outputs(OreDictUnifier.get(dust, Sodium, 6))
                .fluidOutputs(RutheniumTetroxide.getFluid(6000))
                .fluidOutputs(Chlorine.getFluid(3000))
                .fluidOutputs(Water.getFluid(6000))
                .duration(1500)
                .EUt(480)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BALL)
                .fluidInputs(RutheniumTetroxide.getFluid(800))
                .outputs(OreDictUnifier.get(dust, RutheniumTetroxide))
                .EUt(8)
                .duration(16)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RutheniumTetroxide)
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(Chlorine.getFluid(8000))
                .outputs(OreDictUnifier.get(dust, Ruthenium))
                .EUt(30)
                .duration(300)
                .buildAndRegister();
    }

    public static void osmiumInit() {
        //Osmium
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(AcidicOsmiumSolution.getFluid(2000))
                .fluidOutputs(OsmiumSolution.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(OsmiumSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .outputs(OreDictUnifier.get(dust, Osmium))
                .fluidOutputs(Chlorine.getFluid(8000))
                .fluidOutputs(Water.getFluid(5000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .input(ingot, Palladium, 3)
                .fluidInputs(Rhodium.getFluid(144))
                .outputs(OreDictUnifier.get(ingotHot, RhodiumPlatedPalladium, 4))
                .EUt(7980)
                .duration(200)
                .buildAndRegister();
    }

}
