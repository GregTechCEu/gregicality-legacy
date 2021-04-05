package gregicadditions.recipes.chain.optical;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;


public class OpticalCircuits {
    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, LithiumNiobate, 4)
                .input(plate, Vibranium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, MetastableFlerovium, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(foil, Quantum, 4)
                .input(foil, Zylon, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Cinobite, 8)
                .inputs(SodiumSeaborgate.getItemStack(5))
                .input(dust, TriniumTitanium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(390).EUt(1600000)
                .inputs(UHASOC_WAFER.getStackForm())
                .fluidInputs(LiquidZBLAN.getFluid(144))
                .fluidInputs(CarbonNanotubes.getFluid(144))
                .fluidInputs(SeaborgiumDopedNanotubes.getFluid(144))
                .input(dust, IndiumPhospide)
                .fluidInputs(DielectricMirrorFormationMix.getFluid(400))
                .outputs(OPTICAL_SOC_WAFER.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(280).EUt(850000)
                .inputs(OPTICAL_SOC_WAFER.getStackForm())
                .outputs(OPTICAL_SOC.getStackForm(4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(1474560)
                .inputs(OPTICAL_SOC.getStackForm())
                .inputs(SMD_DIODE_OPTICAL.getStackForm(2))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(2))
                .input(foil, Polyetheretherketone, 2)
                .inputs(LOW_FREQUENCY_LASER.getStackForm())
                .inputs(MEDIUM_FREQUENCY_LASER.getStackForm())
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm(2))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(plate,Graphene,4)
                .fluidInputs(Polytetrafluoroethylene.getFluid(864))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(432))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(FullereneDopedNanotubes.getFluid(144))
                .outputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
    }
}
