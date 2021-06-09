package gregicadditions.recipes.categories.circuits;

import gregicadditions.recipes.categories.circuits.components.*;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipeByName;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Good;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitComponentRecipes {

    public static void init() { // TODO

        circuitBoards();

        primitiveSMD();
        refinedSMD();
        microSMD();
        nanoSMD();
        quantumSMD();
        CrystalComponents.init();
        WetwareComponents.init();
        biowareSMD();
        OpticalComponents.init();
        ExoticComponents.init();
        CosmicComponents.init();
        SupracausalComponents.init();
    }

    private static void circuitBoards() {

        // Coated Circuit Board
        ModHandler.removeRecipes(COATED_BOARD.getStackForm(3));
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Wood, 8), RUBBER_DROP.getStackForm()}, new FluidStack[]{Glue.getFluid(100)});

        ModHandler.addShapedRecipe("coated_board_shaped", COATED_BOARD.getStackForm(3),
                "RRR", "BBB", "RRR",
                'R', RUBBER_DROP,
                'B', "plateWood");

        ModHandler.addShapelessRecipe("coated_board_shapeless", COATED_BOARD.getStackForm(),
                RUBBER_DROP, RUBBER_DROP, "plateWood");

        // Basic Circuit Board
        ModHandler.addShapedRecipe("basic_board", BASIC_BOARD.getStackForm(),
                "WWW", "WBW", "WWW",
                'W', new UnificationEntry(wireGtSingle, Copper),
                'B', COATED_BOARD);

        ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(20)
                .input(plate, Wood)
                .input(foil, Copper, 4)
                .fluidInputs(Glue.getFluid(L / 2))
                .outputs(BASIC_BOARD.getStackForm())
                .buildAndRegister();

        // Phenolic Circuit Board handled by GTCE

        // Good Phenolic Circuit Board
        ModHandler.addShapedRecipe("good_board", GOOD_PHENOLIC_BOARD.getStackForm(),
                "WWW", "WBW", "WWW",
                'W', new UnificationEntry(wireGtSingle, Copper),
                'B', PHENOLIC_BOARD);

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(PHENOLIC_BOARD.getStackForm())
                .input(foil, Copper, 4)
                .fluidInputs(SodiumPersulfate.getFluid(200))
                .outputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(PHENOLIC_BOARD.getStackForm())
                .input(foil, Copper, 4)
                .fluidInputs(IronChloride.getFluid(100))
                .outputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .buildAndRegister();

        // Plastic Circuit Board handled by GTCE

        // Good Plastic Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(PLASTIC_BOARD.getStackForm())
                .input(foil, Copper, 6)
                .fluidInputs(SodiumPersulfate.getFluid(500))
                .outputs(GOOD_PLASTIC_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(PLASTIC_BOARD.getStackForm())
                .input(foil, Copper, 6)
                .fluidInputs(IronChloride.getFluid(250))
                .outputs(GOOD_PLASTIC_BOARD.getStackForm())
                .buildAndRegister();

        // Epoxy Circuit Board handled by GTCE

        // Advanced Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30)
                .inputs(EPOXY_BOARD.getStackForm())
                .input(foil, Electrum, 8)
                .fluidInputs(SodiumPersulfate.getFluid(1000))
                .outputs(ADVANCED_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30)
                .inputs(EPOXY_BOARD.getStackForm())
                .input(foil, Electrum, 8)
                .fluidInputs(IronChloride.getFluid(500))
                .outputs(ADVANCED_BOARD.getStackForm())
                .buildAndRegister();

        // Fiber-Reinforced Circuit Board handled by GTCE

        // Extreme Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30)
                .inputs(FIBER_BOARD.getStackForm())
                .input(foil, AnnealedCopper, 12)
                .fluidInputs(SodiumPersulfate.getFluid(2000))
                .outputs(EXTREME_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30)
                .inputs(FIBER_BOARD.getStackForm())
                .input(foil, AnnealedCopper, 12)
                .fluidInputs(IronChloride.getFluid(1000))
                .outputs(EXTREME_BOARD.getStackForm())
                .buildAndRegister();

        // Multi-Layer Fiber-Reinforced Circuit Board handled by GTCE

        // Elite Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(120)
                .inputs(MULTILAYER_FIBER_BOARD.getStackForm())
                .input(foil, Platinum, 16)
                .fluidInputs(SodiumPersulfate.getFluid(4000))
                .outputs(ELITE_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(120)
                .inputs(MULTILAYER_FIBER_BOARD.getStackForm())
                .input(foil, Platinum, 16)
                .fluidInputs(IronChloride.getFluid(2000))
                .outputs(ELITE_BOARD.getStackForm())
                .buildAndRegister();

        // Kapton Circuit Board
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(240)
                .input(plate, Polyimide)
                .fluidInputs(FluorinatedEthylenePropylene.getFluid(L / 2))
                .outputs(KAPTON_BOARD.getStackForm())
                .buildAndRegister();

        // Insane Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(SodiumPersulfate.getFluid(6000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(IronChloride.getFluid(3000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        // Wetware Circuit Board
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MULTILAYER_FIBER_BOARD.getStackForm(), OreDictUnifier.get(circuit, Good)}, new FluidStack[]{Polystyrene.getFluid(L)});

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480)
                .inputs(ELITE_BOARD.getStackForm())
                .inputs(PETRI_DISH.getStackForm())
                .inputs(ELECTRIC_PUMP_LV.getStackForm())
                .inputs(SENSOR_LV.getStackForm())
                .input(circuit, Good)
                .fluidInputs(SterileGrowthMedium.getFluid(250))
                .outputs(WETWARE_BOARD.getStackForm())
                .buildAndRegister();

        // Master Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(240)
                .inputs(WETWARE_BOARD.getStackForm())
                .input(foil, NiobiumTitanium, 32)
                .fluidInputs(SodiumPersulfate.getFluid(10000))
                .outputs(MASTER_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(240)
                .inputs(WETWARE_BOARD.getStackForm())
                .input(foil, NiobiumTitanium, 32)
                .fluidInputs(IronChloride.getFluid(5000))
                .outputs(MASTER_BOARD.getStackForm())
                .buildAndRegister();
    }

    private static void primitiveSMD() {

        // Resistors
        ModHandler.removeRecipes(RESISTOR.getStackForm(3));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireFine, Copper, 4), OreDictUnifier.get(dust, Coal));

        for (Material m : new Material[]{Coal, Charcoal, Carbon}) {

            ModHandler.addShapedRecipe(String.format("resistor_%s", m.toString()), RESISTOR.getStackForm(),
                    "RWR", "CMC", " W ",
                    'M', new UnificationEntry(dust, m),
                    'R', RUBBER_DROP,
                    'W', new UnificationEntry(wireFine, Copper),
                    'C', new UnificationEntry(wireGtSingle, Copper));

            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16)
                    .input(dust, m)
                    .input(wireFine, Copper, 4)
                    .input(wireGtSingle, Copper, 4)
                    .fluidInputs(Glue.getFluid(200))
                    .outputs(RESISTOR.getStackForm(8))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16)
                    .input(dust, m)
                    .input(wireFine, AnnealedCopper, 4)
                    .input(wireGtSingle, Copper, 4)
                    .fluidInputs(Glue.getFluid(200))
                    .outputs(RESISTOR.getStackForm(8))
                    .buildAndRegister();
        }

        // Transistors and Capacitors handled by GTCE

        // Diodes
        removeRecipeByName("gregtech:diode");
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, AnnealedCopper, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(L * 2)});

        ModHandler.addShapedRecipe("ga_diode", DIODE.getStackForm(),
                " P ", "CGC", " P ",
                'P', "paneGlassColorless",
                'C', new UnificationEntry(wireFine, Copper),
                'G', new UnificationEntry(dustSmall, Gallium));

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).input(dustSmall, GalliumArsenide)   .fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide)   .fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).input(dustSmall, GalliumArsenide)   .fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide)   .fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm())        .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm())        .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(2)).buildAndRegister();
    }

    private static void refinedSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 6)
                .inputs(TRANSISTOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_REFINED.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 6)
                .input(plate, Silver)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_REFINED.getStackForm(32))
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 4)
                .inputs(RESISTOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_REFINED.getStackForm(12))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 4)
                .input(dust, Carbon)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_REFINED.getStackForm(24))
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96)
                .input(foil, Rubber, 4)
                .inputs(CAPACITOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_REFINED.getStackForm(8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120)
                .input(foil, Rubber, 4)
                .input(foil, Steel)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_REFINED.getStackForm(16))
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(wireFine, Gold, 8)
                .inputs(DIODE.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_REFINED.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(wireFine, Gold, 8)
                .input(dust, Lithium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_REFINED.getStackForm(32))
                .buildAndRegister();
    }

    private static void microSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, AnnealedCopper, 6)
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR.getStackForm(16))
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Electrum, 4)
                .inputs(SMD_RESISTOR_REFINED.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR.getStackForm(12))
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96)
                .input(foil, PolyvinylChloride, 4)
                .inputs(SMD_CAPACITOR_REFINED.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR.getStackForm(8))
                .buildAndRegister();

        // Diode
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, Platinum, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(L * 2)});

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(wireFine, Platinum, 8)
                .inputs(SMD_DIODE_REFINED.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(wireFine, Platinum, 8)
                .input(dust, GalliumArsenide)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE.getStackForm(32))
                .buildAndRegister();
    }

    private static void nanoSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Palladium, 12)
                .inputs(SMD_TRANSISTOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_NANO.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Palladium, 12)
                .input(plate, Magnalium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_NANO.getStackForm(32))
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Cerium, 8)
                .inputs(SMD_RESISTOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_NANO.getStackForm(12))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Cerium, 8)
                .input(dust, Graphite)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_NANO.getStackForm(24))
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .input(foil, Silicon, 4)
                .inputs(SMD_CAPACITOR.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_NANO.getStackForm(8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .input(foil, Silicon, 4)
                .input(foil, Titanium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_NANO.getStackForm(16))
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(wireFine, ReinforcedEpoxyResin, 8)
                .inputs(SMD_DIODE.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_NANO.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(wireFine, ReinforcedEpoxyResin, 8)
                .input(dust, Caesium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_NANO.getStackForm(32))
                .buildAndRegister();
    }

    private static void quantumSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Plutonium, 12)
                .inputs(SMD_TRANSISTOR_NANO.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Plutonium, 12)
                .input(plate, Americium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(32))
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Ruthenium, 8)
                .inputs(SMD_RESISTOR_NANO.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_QUANTUM.getStackForm(12))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Ruthenium, 8).input(plate, Graphene)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_QUANTUM.getStackForm(24))
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920)
                .input(foil, SiliconeRubber, 4)
                .inputs(SMD_CAPACITOR_NANO.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_QUANTUM.getStackForm(8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920)
                .input(foil, SiliconeRubber, 4)
                .input(foil, Tungsten)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_QUANTUM.getStackForm(16))
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(wireFine, HSSG, 8)
                .inputs(SMD_DIODE_NANO.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_QUANTUM.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(wireFine, HSSG, 8)
                .input(dust, Polonium)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_QUANTUM.getStackForm(32))
                .buildAndRegister();
    }

    // TODO
    private static void biowareSMD() {

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
