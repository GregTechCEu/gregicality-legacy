package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

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

        // C6H6 + C7H8 -> C12H10 + CH4
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(200000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Toluene.getFluid(1000))
                .fluidOutputs(Methane.getFluid(1000))
                .outputs(Biphenyl.getItemStack(22))
                .buildAndRegister();

        // (NH4)2SO4 + H2SO4 -> (NH4)2(SO4)2 + H2
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(3200)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(AmmoniumSulfate.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AmmoniumPersulfate.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // 2I + C12H10 + H2SO4 + (NH4)2SO4 -> C12H8I2 + (NH4)2(SO4)2 + 2H2
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(280000)
                .input(dust, Iodine, 2)
                .inputs(Biphenyl.getItemStack(22))
                .fluidInputs(AmmoniumSulfate.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(Diiodobiphenyl.getItemStack(22))
                .fluidOutputs(AmmoniumPersulfate.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        // Li + C4H10 -> C4H9Li + H
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(140000)
                .input(dust, Lithium)
                .fluidInputs(Butane.getFluid(1000))
                .fluidOutputs(ButylLithium.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        // Sn + 2Cl -> SnCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(65).EUt(19200)
                .input(dust, Tin)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(TinChloride.getItemStack(3))
                .buildAndRegister();

        // SnCl2 + 3CH4 + O -> C3H9SnCl + HCl(H2O)
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(260).EUt(180000)
                .inputs(TinChloride.getItemStack(3))
                .notConsumable(dust, Magnesium)
                .notConsumable(dust, Iodine)
                .fluidInputs(Methane.getFluid(3000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(TetramethyltinChloride.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // C2H2 + H2O -> C2H4O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(2400)
                .notConsumable(dust, Copper)
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Acetaldehyde.getFluid(1000))
                .buildAndRegister();

        // 2Tl + 2HCl -> 2TlCl + 2H
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30720)
                .input(dust, Thallium, 2)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(ThalliumChloride.getItemStack(4))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // CH2O + 2C2H4O + NH3 -> C5H5N + 3H2O + 2H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(75000)
                .notConsumable(ThalliumChloride.getItemStack())
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetaldehyde.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Pyridine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // Ni + Al -> NiAl
        MIXER_RECIPES.recipeBuilder().duration(150).EUt(400)
                .input(dust, Nickel)
                .input(dust, Aluminium)
                .outputs(NiAlCatalyst.getItemStack(2))
                .buildAndRegister();

        // 2C5H5N -> C10H8N2 + H2
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(95000)
                .fluidInputs(Pyridine.getFluid(2000))
                .notConsumable(NiAlCatalyst.getItemStack())
                .outputs(Bipyridine.getItemStack(20))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // C7H5ClO + 2H -> C7H6O + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(4800)
                .notConsumable(dust, Barite)
                .notConsumable(dust, Palladium)
                .fluidInputs(BenzoylChloride.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Benzaldehyde.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2C7H6O + C3H6O -> C17H14O + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(450).EUt(45000)
                .fluidInputs(Benzaldehyde.getFluid(2000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(Dibenzylideneacetone.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // Pd + 2Cl -> PdCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(10000)
                .input(dust, Palladium)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(PalladiumChloride.getItemStack(3))
                .buildAndRegister();

        // 3C17H14O + 2PdCl2 -> 4Cl + C51H42O3Pd2
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(140000)
                .fluidInputs(Dibenzylideneacetone.getFluid(3000))
                .inputs(PalladiumChloride.getItemStack(6))
                .fluidOutputs(Chlorine.getFluid(4000))
                .outputs(PalladiumBisDibenzylidieneacetone.getItemStack(16))
                .buildAndRegister();

        // Pt + 4HNO3 + 6HCl -> H2PtCl6 + 4NO2 + 4H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(145).EUt(40000)
                .input(dust, Platinum)
                .fluidInputs(NitricAcid.getFluid(4000))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(ChloroPlatinicAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // H2PtCl6 + 2K -> K2PtCl4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(50000)
                .fluidInputs(ChloroPlatinicAcid.getFluid(1000))
                .input(dust, Potassium, 2)
                .outputs(PotassiumTetrachloroplatinate.getItemStack(7))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Ni + 2Cl -> NiCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, Nickel)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(NickelChloride.getItemStack(3))
                .buildAndRegister();

        // NiCl2 -> Ni + 2Cl
        ELECTROLYZER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .inputs(NickelChloride.getItemStack(3))
                .output(dust, Nickel)
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();

        // NiCl2 + 6C6H5OH + 2PCl3 -> C36H30Cl2NiP2 + 6HCl + 6O
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(45000)
                .inputs(NickelChloride.getItemStack(3))
                .fluidInputs(Phenol.getFluid(6000))
                .fluidInputs(PhosphorusTrichloride.getFluid(2000))
                .outputs(NickelTriphenylPhosphite.getItemStack(16))
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .buildAndRegister();

        // 2C4H6 -> C8H12
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(55000)
                .fluidInputs(Butadiene.getFluid(2000))
                .notConsumable(NickelTriphenylPhosphite.getItemStack())
                .fluidOutputs(Cyclooctadiene.getFluid(1000))
                .buildAndRegister();

        // C8H12 + K2PtCl4 -> C8H12Cl2Pt + 2KCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(140000)
                .fluidInputs(Cyclooctadiene.getFluid(1000))
                .inputs(PotassiumTetrachloroplatinate.getItemStack(7))
                .outputs(Dichlorocycloctadieneplatinium.getItemStack(23))
                .output(dust, RockSalt, 4)
                .buildAndRegister();

        // 4C12H8I2 + 2C8H12Cl2Pt + 8C + 4AgBF4 + 4C3H9SnCl -> 10C6H4 + 4BF3 + 3C8H16 + 4HF + 2PtCl2 + 8I + 4AgCl + 4Sn
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(460).EUt(450000)
                .notConsumable(Bipyridine.getItemStack())
                .notConsumable(PalladiumBisDibenzylidieneacetone.getItemStack())
                .inputs(Diiodobiphenyl.getItemStack(4))
                .inputs(Dichlorocycloctadieneplatinium.getItemStack(2))
                .input(dust, Carbon, 8)
                .fluidInputs(Silvertetrafluoroborate.getFluid(4000))
                .fluidInputs(TetramethyltinChloride.getFluid(4000))
                .fluidOutputs(Cycloparaphenylene.getFluid(10000))
                .fluidOutputs(BoronFluoride.getFluid(4000))
                .fluidOutputs(Oct1ene.getFluid(3000))
                .fluidOutputs(HydrofluoricAcid.getFluid(4000))
                .output(dust, PlatinumRawPowder, 6)
                .output(dust, Iodine, 8)
                .output(dust, SilverChloride, 8)
                .output(dust, Tin, 4)
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
                .output(ingot, CarbonNanotubes)
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
                .output(dustSmall, CarbonNanotubes, 3)
                .outputs(GrapheneAlignedCNT.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(700000)
                .notConsumable(NANOTOME.getStackForm())
                .inputs(GrapheneAlignedCNT.getItemStack())
                .output(foil, Graphene)
                .fluidOutputs(Cycloparaphenylene.getFluid(250))
                .buildAndRegister();
    }
}
