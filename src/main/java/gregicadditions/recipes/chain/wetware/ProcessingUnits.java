package gregicadditions.recipes.chain.wetware;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM;

public class ProcessingUnits {
    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, Titanium)
                .fluidInputs(Plastic.getFluid(1008))
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .outputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .input(foil, SiliconeRubber, 8)
                .input(wireFine, Gold, 64)
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_DIODE_WETWARE.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(MASTER_BOARD.getStackForm())
                .inputs(STEM_CELLS.getStackForm())
                .inputs(NEURO_SUPPORT_UNIT.getStackForm())
                .fluidInputs(Polybenzimidazole.getFluid(1008))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(1296))
                .outputs(CYBER_PROCESSING_UNIT.getStackForm(8))
                .EUt(30720 * 4)
                .duration(250)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .fluidInputs(Polybenzimidazole.getFluid(1008))
                .fluidInputs(UUMatter.getFluid(100))
                .input(wireFine, NaquadahAlloy, 16)
                .input(plate, Tritanium, 2)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .input(pipeSmall, Polybenzimidazole, 2)
                .inputs(Shewanella.getItemStack(2))
                .outputs(NEURO_SUPPORT_UNIT.getStackForm())
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(plate, NaquadahEnriched)
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(foil, NaquadahEnriched, 4)
                .input(foil, Polybenzimidazole, 4)
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(dust, Naquadria)
                .input(dust, Tritanium)
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(plate, Naquadria)
                .input(plate, Tritanium)
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
