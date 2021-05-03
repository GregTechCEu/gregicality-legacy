package gregicadditions.recipes.categories.machines;

import gregicadditions.item.*;
import gregicadditions.machines.GATileEntities;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.helper.AdditionMethods.*;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MachineCraftingRecipes {

    public static void init() {
        MachineRecipeOverride.init();
        MultiblockCraftingRecipes.init();
        SimpleMachineRecipes.init();
        highAmpMachines();
        highTierBasics();
    }

    private static void highTierBasics() {

        // Machine Casings
        ModHandler.addShapedRecipe("ga_casing_uhv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
        ModHandler.addShapedRecipe("ga_casing_uev", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
        ModHandler.addShapedRecipe("ga_casing_uiv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Seaborgium, 8).outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Bohrium, 8)   .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Quantum, 8)   .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)).buildAndRegister();

        // Hulls
        ModHandler.addShapedRecipe("ga_hull_uhv", GATileEntities.GA_HULLS[0].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium), 'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uev", GATileEntities.GA_HULLS[1].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  'C', new UnificationEntry(cableGtQuadruple, Pikyonium),            'H', new UnificationEntry(plate, Bohrium),    'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uiv", GATileEntities.GA_HULLS[2].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  'C', new UnificationEntry(cableGtQuadruple, Cinobite),             'H', new UnificationEntry(plate, Quantum),    'P', new UnificationEntry(plate, Zylon));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(GATileEntities.GA_HULLS[0].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)).input(cableGtQuadruple, Pikyonium, 2)           .fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(GATileEntities.GA_HULLS[1].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)).input(cableGtQuadruple, Cinobite, 2)            .fluidInputs(Zylon.getFluid(L * 2))               .outputs(GATileEntities.GA_HULLS[2].getStackForm()).buildAndRegister();

        // Energy Hatches
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uhv",  GATileEntities.ENERGY_INPUT[0].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uev",  GATileEntities.ENERGY_INPUT[1].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uiv",  GATileEntities.ENERGY_INPUT[2].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uhv", GATileEntities.ENERGY_OUTPUT[0].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uev", GATileEntities.ENERGY_OUTPUT[1].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uiv", GATileEntities.ENERGY_OUTPUT[2].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
    }

    private static void highAmpMachines() {

        registerMachineRecipe(GATileEntities.TRANSFORMER_1_AMPS,
                "KBB", "CM ", "KBB",
                'M', WORSE_HULL,
                'C', CABLE_DOUBLE,
                'B', CABLE_DOUBLE_WORSE,
                'K', MetaItems.SMALL_COIL);

        registerMachineRecipe(GATileEntities.TRANSFORMER_4_AMPS,
                "KBB", "CM ", "KBB",
                'M', WORSE_HULL,
                'C', CABLE_DOUBLE,
                'B', CABLE_DOUBLE_WORSE,
                'K', MetaItems.SMALL_COIL);

        registerMachineRecipe(GATileEntities.TRANSFORMER_8_AMPS,
                "KBB", "CM ", "KBB",
                'M', WORSE_HULL,
                'C', CABLE_QUAD,
                'B', CABLE_QUAD_WORSE,
                'K', MetaItems.SMALL_COIL);

        registerMachineRecipe(GATileEntities.TRANSFORMER_12_AMPS,
                "KBB", "CM ", "KBB",
                'M', WORSE_HULL,
                'C', CABLE_OCTAL,
                'B', CABLE_OCTAL_WORSE,
                'K', MetaItems.SMALL_COIL);

        registerMachineRecipe(GATileEntities.TRANSFORMER_16_AMPS,
                "KBB", "CM ", "KBB",
                'M', WORSE_HULL,
                'C', CABLE_HEX,
                'B', CABLE_HEX_WORSE,
                'K', MetaItems.SMALL_COIL);

        registerMachineRecipes(GATileEntities.ENERGY_INPUT_HATCH_4_AMPS,
                "CM ",
                'M', HULL,
                'C', CABLE_DOUBLE);

        registerMachineRecipes(GATileEntities.ENERGY_INPUT_HATCH_16_AMPS,
                "CM ",
                'M', HULL,
                'C', CABLE_QUAD);

        registerMachineRecipes(GATileEntities.ENERGY_INPUT_HATCH_64_AMPS,
                "CM ",
                'M', HULL,
                'C', CABLE_OCTAL);

        registerMachineRecipes(GATileEntities.ENERGY_INPUT_HATCH_128_AMPS,
                "CM ",
                'M', HULL,
                'C', CABLE_HEX);

        registerMachineRecipes(GATileEntities.ENERGY_OUTPUT_HATCH_16_AMPS,
                " MC",
                'M', HULL,
                'C', CABLE_DOUBLE);

        registerMachineRecipes(GATileEntities.ENERGY_OUTPUT_HATCH_32_AMPS,
                " MC",
                'M', HULL,
                'C', CABLE_QUAD);

        registerMachineRecipes(GATileEntities.ENERGY_OUTPUT_HATCH_64_AMPS,
                " MC",
                'M', HULL,
                'C', CABLE_OCTAL);

        registerMachineRecipes(GATileEntities.ENERGY_OUTPUT_HATCH_128_AMPS,
                " MC",
                'M', HULL,
                'C', CABLE_HEX);
    }
}
