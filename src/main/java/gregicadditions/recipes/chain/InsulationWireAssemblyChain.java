package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.GAMetaItems.INSULATION_WIRE_ASSEMBLY;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.stick;

public class InsulationWireAssemblyChain {

    public static void init() {
        // 3C2H5OH + C2H4 + NH3 + HBr = C8H20NBr + 3H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(45000)
                .fluidInputs(Ethanol.getFluid(3000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(TetraethylammoniumBromide.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // H2O + C6H12O6 = C6H14O2
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(330000)
                .notConsumable(PdIrReOCeOS.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .inputs(Fructose.getItemStack(24))
                .fluidInputs(TetraethylammoniumBromide.getFluid(10))
                .fluidOutputs(Hexanediol.getFluid(1000))
                .buildAndRegister();

        // 2 NH3 + C6H14O2 = 2 H2O + C6H16N2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(250000)
                .fluidInputs(Hexanediol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Hexamethylenediamine.getFluid(1000))
                .notConsumable(dust, Ruthenium)
                .notConsumable(Alumina.getItemStack())
                .buildAndRegister();

        // C6H12O6 + 2CO = C6H10O8 + C2H2
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(74500)
                .inputs(Glucose.getItemStack(24))
                .fluidInputs(CarbonMonoxde.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(1000))
                .outputs(SaccharicAcid.getItemStack(24))
                .buildAndRegister();

        // C6H10O8 + 8H = C6H10O4 + 4H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(260).EUt(120000)
                .inputs(SaccharicAcid.getItemStack(24))
                .notConsumable(AuPdCCatalyst.getItemStack())
                .notConsumable(ScandiumTriflate.getItemStack())
                .fluidInputs(Hydrogen.getFluid(8000))
                .outputs(AdipicAcid.getItemStack(20))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // C3H6O + CH4 = C4H10O
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(230000)
                .notConsumable(dust, MagnesiumChloride)
                .inputs(ZeoliteSievingPellets.getItemStack())
                .fluidInputs(Acetone.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidOutputs(Tertbutanol.getFluid(2000))
                .outputs(WetZeoliteSievingPellets.getItemStack())
                .buildAndRegister();

        // 2 C4H10O + 2 CO2 = H2O + C10H18O5
        // CATALYST
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(320000)
                .fluidInputs(Tertbutanol.getFluid(2000))
                .fluidInputs(CarbonDioxide.getFluid(2000))
                .fluidInputs(Toluenesulfonate.getFluid(10))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(DitertbutylCarbonate.getItemStack(33))
                .buildAndRegister();

        // C4H8 + C10H18O5 + 4NH3 + 3C = 2C4H10O + C6H18N4 + 3CO
        // CATALYST
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(370).EUt(200000)
                .inputs(DitertbutylCarbonate.getItemStack(33))
                .input(dust, Carbon, 3)
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(4000))
                .fluidInputs(Trimethylchlorosilane.getFluid(10))
                .fluidOutputs(Tertbutanol.getFluid(2000))
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .fluidOutputs(Triaminoethaneamine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(290).EUt(420000)
                .input(foil, Polyetheretherketone)
                .input(foil, SiliconeRubber)
                .inputs(AdipicAcid.getItemStack(20))
                .fluidInputs(Hexamethylenediamine.getFluid(1000))
                .fluidInputs(Triaminoethaneamine.getFluid(500))
                .outputs(PEEK_POLYAMIDE_FOIL.getStackForm(3))
                .buildAndRegister();

        //
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(450000)
                .input(dust, Potassium,2)
                .inputs(SodiumAzide.getItemStack(2))
                .inputs(DitertbutylCarbonate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sodium, 2))
                .outputs(OreDictUnifier.get(dust, Potash, 2))
                .fluidOutputs(Tertbutylcarbonylazide.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(745000)
                .inputs(Fullerene.getItemStack())
                .inputs(Alumina.getItemStack(6))
                .fluidInputs(Tertbutylcarbonylazide.getFluid(12000))
                .fluidInputs(Water.getFluid(12000))
                .fluidOutputs(AminatedFullerene.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(12000))
                .fluidOutputs(CarbonDioxide.getFluid(12000))
                .outputs(AluminiumHydroxide.getItemStack(12))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(120).EUt(480000)
                .fluidInputs(AminatedFullerene.getFluid(1000))
                .fluidOutputs(Azafullerene.getFluid(1000))
                .notConsumable(wireFine, Rhenium)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(230).EUt(475000)
                .inputs(PEEK_POLYAMIDE_FOIL.getStackForm())
                .fluidInputs(Azafullerene.getFluid(10))
                .outputs(HIGHLY_INSULATING_FOIL.getStackForm())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(250).EUt(32000)
                .fluidInputs(IronChloride.getFluid(1000))
                .inputs(Glucose.getItemStack())
                .fluidOutputs(GlucoseIronSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(260).EUt(18000)
                .fluidInputs(GlucoseIronSolution.getFluid(1000))
                .outputs(GlucoseIronMixture.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(19000).blastFurnaceTemp(1800)
                .inputs(GlucoseIronMixture.getItemStack())
                .outputs(GRAPHENE_IRON_PLATE.getStackForm())
                .fluidOutputs(Chlorine.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(28000)
                .inputs(GRAPHENE_IRON_PLATE.getStackForm(2))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(IronChloride.getFluid(1000))
                .outputs(OreDictUnifier.get(plate, Graphene))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(28000)
                .inputs(GRAPHENE_IRON_PLATE.getStackForm(2))
                .fluidInputs(AquaRegia.getFluid(3000))
                .fluidOutputs(IronChloride.getFluid(1000))
                .outputs(GrapheneOxide.getItemStack())
                .fluidOutputs(NitrogenDioxide.getFluid(1500))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(260).EUt(19500)
                .inputs(PotassiumPermanganate.getItemStack())
                .input(dust, SodiumNitrate)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(GrapheneOxidationSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(31500)
                .input(dust, Graphite, 3)
                .notConsumable(dust, Osmium)
                .fluidInputs(GrapheneOxidationSolution.getFluid(500))
                .outputs(GrapheneOxide.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(29000)
                .input(dust, Graphene, 3)
                .fluidInputs(GrapheneOxidationSolution.getFluid(500))
                .outputs(GrapheneOxide.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(26500)
                .inputs(GraphiteOxide.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .outputs(GrapheneOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(32000)
                .inputs(GrapheneOxide.getItemStack())
                .notConsumable(WHITE_HALIDE_LAMP.getStackForm())
                .fluidInputs(Hydrazine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Graphene))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(15000)
                .fluidInputs(Resorcinol.getFluid(500))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .inputs(GrapheneOxide.getItemStack())
                .outputs(GrapheneGelSuspension.getItemStack())
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(260).EUt(19500)
                .inputs(GrapheneGelSuspension.getItemStack())
                .fluidInputs(Acetone.getFluid(500))
                .outputs(DryGrapheneGel.getItemStack())
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(320).EUt(29500)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SupercriticalCO2.getFluid(1000))
                .circuitMeta(0)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(400).EUt(22000).blastFurnaceTemp(5000)
                .inputs(DryGrapheneGel.getItemStack())
                .fluidInputs(SupercriticalCO2.getFluid(1000))
                .outputs(AEROGRAPHENE.getStackForm())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(210).EUt(425000)
                .input(stick, Polyurethane)
                .input(stick, ReinforcedEpoxyResin)
                .inputs(MEMORY_FOAM_BLOCK.getStackForm())
                .inputs(HIGHLY_INSULATING_FOIL.getStackForm())
                .inputs(AEROGRAPHENE.getStackForm())
                .fluidInputs(Argon.getFluid(1000))
                .outputs(INSULATION_WIRE_ASSEMBLY.getStackForm(2))
                .buildAndRegister();
    }
}
