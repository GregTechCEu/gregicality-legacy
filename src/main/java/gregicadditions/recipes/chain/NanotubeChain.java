package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.NANOTOME;
import static gregicadditions.item.GAMetaItems.PIEZOELECTRIC_CRYSTAL;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_PLANT_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV;
import static gregtech.common.items.MetaItems.SENSOR_UV;


public class NanotubeChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(200000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Toluene.getFluid(1000))
                .fluidOutputs(Methane.getFluid(1000))
                .outputs(Biphenyl.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(3200)
                .fluidInputs(AmmoniumSulfate.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AmmoniumPersulfate.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(280000)
                .input(dust, Iodine, 2)
                .inputs(Biphenyl.getItemStack())
                .fluidInputs(AmmoniumPersulfate.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(Diiodobiphenyl.getItemStack())
                .fluidOutputs(AmmoniumPersulfate.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(140000)
                .input(dust, Lithium)
                .fluidInputs(Butane.getFluid(1000))
                .fluidOutputs(ButhylLithium.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(65).EUt(19200)
                .input(dust, Tin)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(TinChloride.getItemStack(1))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(260).EUt(180000)
                .inputs(TinChloride.getItemStack())
                .notConsumable(dust, Magnesium)
                .notConsumable(dust, Iodine)
                .fluidInputs(Methane.getFluid(3000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(TetramethyltinChloride.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(2400)
                .notConsumable(dust, Copper)
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Acetaldehyde.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30720)
                .input(dust, Thallium, 2)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(ThalliumChloride.getItemStack(2))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(75000)
                .notConsumable(ThalliumChloride.getItemStack())
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetaldehyde.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Pyridine.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(150).EUt(400)
                .input(dust, Nickel)
                .input(dust, Aluminium)
                .outputs(NiAlCatalyst.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(95000)
                .fluidInputs(Pyridine.getFluid(2000))
                .notConsumable(NiAlCatalyst.getItemStack())
                .outputs(Bipyridine.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(4800)
                .notConsumable(dust, Barite)
                .notConsumable(dust, Palladium)
                .fluidInputs(BenzoylChloride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Benzaldehyde.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(450).EUt(45000)
                .fluidInputs(Benzaldehyde.getFluid(2000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(Dibenzyldieneacetone.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(10000)
                .input(dust, Palladium)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(PalladiumChloride.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(140000)
                .fluidInputs(Dibenzyldieneacetone.getFluid(2000))
                .inputs(PalladiumChloride.getItemStack())
                .fluidOutputs(Chlorine.getFluid(2000))
                .outputs(PalladiumBisDibenzylidieneacetone.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(145).EUt(40000)
                .input(dust, Platinum)
                .fluidInputs(NitricAcid.getFluid(4000))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(ChloroPlatinicAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(50000)
                .fluidInputs(ChloroPlatinicAcid.getFluid(1000))
                .input(dust, Potassium, 2)
                .outputs(PotassiumTetrachloroplatinate.getItemStack())
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(45000)
                .input(dust, Nickel)
                .fluidInputs(Phenol.getFluid(12000))
                .fluidInputs(PhosphorusTrichloride.getFluid(4000))
                .outputs(NickelTriphenylPhosphite.getItemStack())
                .fluidOutputs(HydrochloricAcid.getFluid(12000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(55000)
                .fluidInputs(Butadiene.getFluid(2000))
                .notConsumable(NickelTriphenylPhosphite.getItemStack())
                .fluidOutputs(Cyclooctadiene.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(140000)
                .fluidInputs(Cyclooctadiene.getFluid(1000))
                .inputs(PotassiumTetrachloroplatinate.getItemStack())
                .outputs(Dichlorocycloctadieneplatinium.getItemStack())
                .outputs(OreDictUnifier.get(dust, RockSalt, 2))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(460).EUt(450000)
                .notConsumable(Bipyridine.getItemStack())
                .notConsumable(PalladiumBisDibenzylidieneacetone.getItemStack())
                .inputs(Diiodobiphenyl.getItemStack(4))
                .inputs(Dichlorocycloctadieneplatinium.getItemStack(2))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidInputs(Silvertetrafluoroborate.getFluid(4000))
                .fluidInputs(TetramethyltinChloride.getFluid(4000))
                .fluidOutputs(Cycloparaphenylene.getFluid(1000))
                .fluidOutputs(BoronFluoride.getFluid(4000))
                .fluidOutputs(Oct1ene.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Platinum, 2))
                .outputs(OreDictUnifier.get(dust , Silver, 4))
                .outputs(OreDictUnifier.get(dust, Iodine, 8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(200000)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .inputs(SENSOR_UV.getStackForm())
                .input(gemExquisite, Diamond)
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stick, Duranium, 2)
                .input(plate, Polybenzimidazole)
                .fluidInputs(SolderingAlloy.getFluid(432))
                .outputs(NANOTOME.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(320000)
                .fluidInputs(Cycloparaphenylene.getFluid(200))
                .fluidInputs(Methane.getFluid(800))
                .notConsumable(plate, Rhenium)
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(OreDictUnifier.get(ingot, CarbonNanotubes))
                .buildAndRegister();
        
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(320000)
                .fluidInputs(Cycloparaphenylene.getFluid(200))
                .fluidInputs(Methane.getFluid(800))
                .input(dust, Seaborgium)
                .notConsumable(plate, Rhenium)
                .fluidOutputs(SeaborgiumDopedNanotubes.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(320000)
                .fluidInputs(Cycloparaphenylene.getFluid(3600))
                .fluidInputs(Methane.getFluid(14400))
                .inputs(Fullerene.getItemStack())
                .notConsumable(plate, Rhenium)
                .fluidOutputs(FullereneDopedNanotubes.getFluid(18000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(250000)
                .input(foil, Graphene)
                .input(dust, CarbonNanotubes)
                .outputs(GrapheneNanotubeMix.getItemStack())
                .buildAndRegister();

        ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(310).EUt(280000)
                .inputs(GrapheneNanotubeMix.getItemStack())
                .outputs(OreDictUnifier.get(dustSmall, CarbonNanotubes, 3))
                .outputs(GrapheneAlignedCNT.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(700000)
                .notConsumable(NANOTOME.getStackForm())
                .inputs(GrapheneAlignedCNT.getItemStack())
                .outputs(OreDictUnifier.get(foil, Graphene))
                .fluidOutputs(Cycloparaphenylene.getFluid(250))
                .buildAndRegister();

    }
}
