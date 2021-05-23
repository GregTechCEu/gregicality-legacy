package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class InsulationWireAssemblyChain {

    public static void init() {

        // 4C2H4 + NH3 + HBr -> C8H20NBr
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(Ethylene.getFluid(4000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(TetraethylammoniumBromide.getFluid(1000))
                .buildAndRegister();

        // H2O + C6H12O6 -> C6H14O2 + 5O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .notConsumable(PdIrReOCeOS.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .inputs(Fructose.getItemStack(24))
                .notConsumable(TetraethylammoniumBromide.getFluid(0))
                .fluidOutputs(Hexanediol.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(5000))
                .buildAndRegister();

        // 2NH3 + C6H14O2 -> 2H2O + C6H16N2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(Hexanediol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Hexamethylenediamine.getFluid(1000))
                .notConsumable(dust, Ruthenium)
                .notConsumable(Alumina.getItemStack())
                .buildAndRegister();

        // C6H12O6 + 2CO -> C6H10O8 + C2H2
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .inputs(Glucose.getItemStack(24))
                .fluidInputs(CarbonMonoxde.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(1000))
                .outputs(SaccharicAcid.getItemStack(24))
                .buildAndRegister();

        // C6H10O8 + 8H -> C6H10O4 + 4H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .inputs(SaccharicAcid.getItemStack(24))
                .notConsumable(AuPdCCatalyst.getItemStack())
                .notConsumable(ScandiumTriflate.getItemStack())
                .fluidInputs(Hydrogen.getFluid(8000))
                .outputs(AdipicAcid.getItemStack(20))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // C3H6O + CH4 -> C4H10O
        CHEMICAL_RECIPES.recipeBuilder().duration(125).EUt(120)
                .notConsumable(dust, MagnesiumChloride)
                .inputs(ZeoliteSievingPellets.getItemStack())
                .fluidInputs(Acetone.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidOutputs(Tertbutanol.getFluid(1000))
                .outputs(WetZeoliteSievingPellets.getItemStack())
                .buildAndRegister();

        // 2C4H10O + 2CO2 -> H2O + C10H18O5
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(480)
                .fluidInputs(Tertbutanol.getFluid(2000))
                .fluidInputs(CarbonDioxide.getFluid(2000))
                .notConsumable(Toluenesulfonate.getFluid(0))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(DitertbutylDicarbonate.getItemStack(33))
                .buildAndRegister();

        // C4H8 + C10H18O5 + 4NH3 + 3C -> 2C4H10O + C6H18N4 + 3CO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(370).EUt(480)
                .inputs(DitertbutylDicarbonate.getItemStack(33))
                .input(dust, Carbon, 3)
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(4000))
                .notConsumable(Trimethylchlorosilane.getFluid(0))
                .fluidOutputs(Tertbutanol.getFluid(2000))
                .fluidOutputs(CarbonMonoxde.getFluid(3000))
                .fluidOutputs(Triaminoethaneamine.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(30720)
                .input(foil, Polyetheretherketone)
                .input(foil, SiliconeRubber)
                .inputs(AdipicAcid.getItemStack(20))
                .fluidInputs(Hexamethylenediamine.getFluid(1000))
                .fluidInputs(Triaminoethaneamine.getFluid(500))
                .outputs(PEEK_POLYAMIDE_FOIL.getStackForm(3))
                .buildAndRegister();

        // 2K + 2NaN3 + C10H18O5 -> 2Na + K2O + 2C5H9N3O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .input(dust, Potassium,2)
                .inputs(SodiumAzide.getItemStack(8))
                .inputs(DitertbutylDicarbonate.getItemStack(33))
                .outputs(OreDictUnifier.get(dust, Sodium, 2))
                .outputs(OreDictUnifier.get(dust, Potash, 6))
                .fluidOutputs(TertButylAzidoformate.getFluid(2000))
                .buildAndRegister();

        // Aminated Fullerene is a Secondary Amine
        // C60 + 4C5H9N3O2 + 8H2O + 4CO -> C60N12H12 + 4C4H10O + 8CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30720)
                .inputs(Fullerene.getItemStack())
                .fluidInputs(TertButylAzidoformate.getFluid(4000))
                .fluidInputs(Water.getFluid(8000))
                .fluidInputs(CarbonMonoxde.getFluid(4000))
                .fluidOutputs(AminatedFullerene.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(8000))
                .fluidOutputs(Tertbutanol.getFluid(4000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(120).EUt(480)
                .fluidInputs(AminatedFullerene.getFluid(1000))
                .fluidOutputs(Azafullerene.getFluid(1000))
                .notConsumable(wireFine, Rhenium)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(30).EUt(7680)
                .inputs(PEEK_POLYAMIDE_FOIL.getStackForm())
                .fluidInputs(Azafullerene.getFluid(10))
                .outputs(HIGHLY_INSULATING_FOIL.getStackForm())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .fluidInputs(Resorcinol.getFluid(500))
                .fluidInputs(Formaldehyde.getFluid(1000))
                .inputs(GrapheneOxide.getItemStack(3))
                .outputs(GrapheneGelSuspension.getItemStack())
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(260).EUt(480)
                .inputs(GrapheneGelSuspension.getItemStack())
                .fluidInputs(Acetone.getFluid(100))
                .outputs(DryGrapheneGel.getItemStack())
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(80).EUt(480)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SupercriticalCO2.getFluid(1000))
                .circuitMeta(0)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(400).EUt(120).blastFurnaceTemp(5000)
                .inputs(DryGrapheneGel.getItemStack())
                .fluidInputs(SupercriticalCO2.getFluid(1000))
                .outputs(AEROGRAPHENE.getStackForm())
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(210).EUt(30720)
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
