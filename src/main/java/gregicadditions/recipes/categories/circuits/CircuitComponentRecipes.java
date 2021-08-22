package gregicadditions.recipes.categories.circuits;

import gregicadditions.recipes.categories.circuits.components.*;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitComponentRecipes {

    public static void init() {
        WetwareComponents.init();
        biowareSMD();
        OpticalComponents.init();
        ExoticComponents.init();
        CosmicComponents.init();
        SupracausalComponents.init();
    }

    private static void biowareSMD() {

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, Dubnium, 8)
                .input(plate, GermaniumTungstenNitride, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, PEDOT, 8)
                .input(foil, Polytetrafluoroethylene, 4)
                .input(foil, BariumTitanate, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, Osmiridium, 8)
                .input(dust, AluminiumComplex)
                .input(dust, CopperGalliumIndiumSelenide)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 6)
                .input(plate, BismuthRuthenate)
                .input(plate, BismuthIridiate)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_RESISTOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber, 32)
                .input(wireFine, NaquadahAlloy, 16)
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(2))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(2))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(2))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(STEM_CELLS.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(288))
                .outputs(NEURO_PROCESSOR.getStackForm())
                .EUt(30720 * 16)
                .duration(150)
                .buildAndRegister();
    }
}
