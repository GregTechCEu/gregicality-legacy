package gregicadditions.recipes.categories.machines;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.*;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.energyconverter.utils.EnergyConverterCraftingHelper;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAValues.*;
import static gregicadditions.GAValues.ZPM;
import static gregicadditions.recipes.helper.AdditionMethods.*;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Bronze;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MachineCraftingRecipes {

    public static void init() {
        MachineRecipeOverride.init();
        MultiblockCraftingRecipes.init();
        newMachines();
        highTierBasics();
        highTierMachines();
        highAmpMachines();
        otherMachines();
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

    private static void newMachines() {

        registerMachineRecipe(GATileEntities.CIRCUITASSEMBLER,
                "ACE", "VMV", "WCW",
                'M', HULL,
                'V', CONVEYOR,
                'A', ROBOT_ARM,
                'C', BETTER_CIRCUIT,
                'W', CABLE_SINGLE,
                'E', EMITTER);

        registerMachineRecipe(GATileEntities.CLUSTERMILL,
                "MMM", "CHC", "MMM",
                'M', MOTOR,
                'C', CIRCUIT,
                'H', HULL);

        registerMachineRecipe(GATileEntities.MASS_FAB,
                "CFC", "QMQ", "CFC",
                'M', HULL,
                'Q', CABLE_QUAD,
                'C', BETTER_CIRCUIT,
                'F', FIELD_GENERATOR);

        registerMachineRecipe(GATileEntities.REPLICATOR,
                "EFE", "CMC", "EQE",
                'M', HULL,
                'Q', CABLE_QUAD,
                'C', BETTER_CIRCUIT,
                'F', FIELD_GENERATOR,
                'E', EMITTER);

        registerMachineRecipe(GATileEntities.WORLD_ACCELERATOR,
                "ABC", "DHE", "FGI",
                'H', HULL,
                'A', PISTON,
                'B', ROBOT_ARM,
                'C', PUMP,
                'D', MOTOR,
                'E', CONVEYOR,
                'F', EMITTER,
                'G', SENSOR,
                'I', FIELD_GENERATOR);

        registerMachineRecipe(GATileEntities.MINER,
                "WPW", "CMC", "SPS",
                'M', HULL,
                'P', PISTON,
                'C', CIRCUIT,
                'W', MetaItems.COMPONENT_GRINDER_DIAMOND,
                'S', SENSOR);

        registerMachineRecipe(GATileEntities.DEHYDRATOR,
                "WCW", "MHM", "GAG",
                'C', CIRCUIT,
                'M', CABLE_QUAD,
                'H', HULL,
                'G', GEAR,
                'A', ROBOT_ARM,
                'W', COIL_HEATING_DOUBLE);

        registerMachineRecipe(GATileEntities.DECAY_CHAMBER,
                "RCR", "FMF", "QCQ",
                'M', HULL,
                'Q', CABLE_DOUBLE,
                'C', CIRCUIT,
                'F', FIELD_GENERATOR,
                'R', STICK_RADIOACTIVE);

        registerMachineRecipe(GATileEntities.GREEN_HOUSE,
                "GGG", "AMA", "CQC",
                'M', HULL,
                'Q', CABLE_SINGLE,
                'C', CIRCUIT,
                'G', GLASS,
                'A', ROBOT_ARM);

        registerMachineRecipe(GATileEntities.ROCK_BREAKER,
                "CPC", "CMC", "GGG",
                'M', HULL,
                'C', PIPE,
                'G', GLASS,
                'P', PISTON);

        registerMachineRecipe(GATileEntities.DISASSEMBLER,
                "RSV", "PMV", "ICI",
                'M', HULL,
                'C', CABLE_SINGLE,
                'R', ROBOT_ARM,
                'P', PUMP,
                'S', SENSOR,
                'V', CONVEYOR,
                'I', CIRCUIT);

        registerMachineRecipe(GATileEntities.NAQUADAH_REACTOR,
                "RCR", "FMF", "QCQ",
                'M', HULL,
                'Q', CABLE_QUAD,
                'C', BETTER_CIRCUIT,
                'F', FIELD_GENERATOR,
                'R', STICK_RADIOACTIVE);

        registerMachineRecipe(GATileEntities.ROCKET_GENERATOR,
                "PCP", "MHM", "GAG",
                'C', CIRCUIT,
                'M', MOTOR,
                'H', HULL,
                'G', PLATE_DENSE,
                'A', CABLE_DOUBLE,
                'P', PISTON);

        registerMachineRecipe(GATileEntities.DIODES,
                "CCC", "XMX", "CCC",
                'M', HULL,
                'C', CABLE_SINGLE,
                'X', MetaItems.SMALL_COIL);

        registerMachineRecipe(1, GATileEntities.BUFFER,
                " G ", " H ", " C ",
                'G', GLASS,
                'H', HULL,
                'C', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("ga_simple_ore_washer", GATileEntities.SIMPLE_ORE_WASHER.getStackForm(),
                "PIP", "PTP", "PCP",
                'C', MetaTileEntities.HULL[GTValues.LV].getStackForm(),
                'T', MetaItems.ELECTRIC_PUMP_LV,
                'I', new UnificationEntry(plate, Steel),
                'P', new UnificationEntry(pipeLarge, Bronze));
    }

    // Same recipes as default GT (includes our overrides seen below).
    // Bending Machine does not use a Wrench, instead uses another single tiered cable in its place.
    private static void highTierMachines() {

        if (GAConfig.GT5U.highTierPumps)
            registerMachineRecipe(GATileEntities.PUMP,                      "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT,               'G', PUMP,           'T', PIPE);
        if (GAConfig.GT5U.highTierAlloySmelter)
            registerMachineRecipe(GATileEntities.ALLOY_SMELTER,             "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT,               'W', CABLE_SINGLE,   'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierAssemblers)
            registerMachineRecipe(GATileEntities.ASSEMBLER,                 "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR,              'A', ROBOT_ARM,      'C', CIRCUIT,      'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierBenders)
            registerMachineRecipe(GATileEntities.BENDER,                    "PWP", "CMC", "EWE", 'M', HULL, 'E', MOTOR,                 'P', PISTON,         'C', CIRCUIT,      'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCanners)
            registerMachineRecipe(GATileEntities.CANNER,                    "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierCompressors)
            registerMachineRecipe(GATileEntities.COMPRESSOR,                " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON,                'C', CIRCUIT,        'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCutters)
            registerMachineRecipe(GATileEntities.CUTTER,                    "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR,                 'V', CONVEYOR,       'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        if (GAConfig.GT5U.highTierElectricFurnace)
            registerMachineRecipe(GATileEntities.ELECTRIC_FURNACE,          "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT,               'W', CABLE_SINGLE,   'C', COIL_HEATING);
        if (GAConfig.GT5U.highTierExtractors)
            registerMachineRecipe(GATileEntities.EXTRACTOR,                 "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON,                'P', PUMP,           'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierExtruders)
            registerMachineRecipe(GATileEntities.EXTRUDER,                  "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON,                'E', CIRCUIT,        'P', PIPE,         'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierLathes)
            registerMachineRecipe(GATileEntities.LATHE,                     "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR,                 'P', PISTON,         'C', CIRCUIT,      'W', CABLE_SINGLE, 'D', DIAMOND);
        if (GAConfig.GT5U.highTierMacerators)
            registerMachineRecipe(GATileEntities.MACERATOR,                 "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR,                 'P', PISTON,         'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GRINDER);
        if (GAConfig.GT5U.highTierMicrowaves)
            registerMachineRecipe(GATileEntities.MICROWAVE,                 "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR,                 'R', EMITTER,        'C', CIRCUIT,      'W', CABLE_SINGLE, 'L', new UnificationEntry(plate, Lead));
        if (GAConfig.GT5U.highTierWiremills)
            registerMachineRecipe(GATileEntities.WIREMILL,                  "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR,                 'C', CIRCUIT,        'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCentrifuges)
            registerMachineRecipe(GATileEntities.CENTRIFUGE,                "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR,                 'C', CIRCUIT,        'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierElectrolyzers)
            registerMachineRecipe(GATileEntities.ELECTROLYZER,              "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT,               'W', CABLE_SINGLE,   'I', WIRE,         'G', GLASS);
        if (GAConfig.GT5U.highTierThermalCentrifuges)
            registerMachineRecipe(GATileEntities.THERMAL_CENTRIFUGE,        "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR,                 'C', CIRCUIT,        'W', CABLE_SINGLE, 'O', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierOreWashers)
            registerMachineRecipe(GATileEntities.ORE_WASHER,                "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR,                 'E', MOTOR,          'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPackers)
            registerMachineRecipe(GATileEntities.PACKER,                    "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM,             'V', CONVEYOR,       'C', CIRCUIT,      'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierUnpackers)
            registerMachineRecipe(GATileEntities.UNPACKER,                  "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM,             'V', CONVEYOR,       'C', CIRCUIT,      'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierChemicalReactors)
            registerMachineRecipe(GATileEntities.CHEMICAL_REACTOR,          "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR,                 'E', MOTOR,          'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidCanners)
            registerMachineRecipe(GATileEntities.FLUID_CANNER,              "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierBreweries)
            registerMachineRecipe(GATileEntities.BREWERY,                   "GPG", "WMW", "CBC", 'M', HULL, 'B', STICK_DISTILLATION,    'P', PUMP,           'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFermenters)
            registerMachineRecipe(GATileEntities.FERMENTER,                 "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidExtractors)
            registerMachineRecipe(GATileEntities.FLUID_EXTRACTOR,           "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON,                'P', PUMP,           'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidSolidifiers)
            registerMachineRecipe(GATileEntities.FLUID_SOLIDIFIER,          "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierDistilleries)
            registerMachineRecipe(GATileEntities.DISTILLERY,                "GBG", "CMC", "WPW", 'M', HULL, 'B', STICK_DISTILLATION,    'P', PUMP,           'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierChemicalBaths)
            registerMachineRecipe(GATileEntities.CHEMICAL_BATH,             "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP,                  'V', CONVEYOR,       'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPolarizers)
            registerMachineRecipe(GATileEntities.POLARIZER,                 "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC,  'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierElectromagneticSeparators)
            registerMachineRecipe(GATileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC,  'V', CONVEYOR,     'C', CIRCUIT,      'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierAutoclaves)
            registerMachineRecipe(GATileEntities.AUTOCLAVE,                 "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'I', PLATE,        'G', GLASS);
        if (GAConfig.GT5U.highTierMixers)
            registerMachineRecipe(GATileEntities.MIXER,                     "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR,                 'R', ROTOR,          'C', CIRCUIT,      'G', GLASS);
        if (GAConfig.GT5U.highTierLaserEngravers)
            registerMachineRecipe(GATileEntities.LASER_ENGRAVER,            "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER,               'P', PISTON,         'C', CIRCUIT,      'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierFormingPresses)
            registerMachineRecipe(GATileEntities.FORMING_PRESS,             "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON,                'C', CIRCUIT,        'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierForgeHammers)
            registerMachineRecipe(GATileEntities.FORGE_HAMMER,              "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON,                'C', CIRCUIT,        'W', CABLE_SINGLE, 'A', OreDictNames.craftingAnvil);
        if (GAConfig.GT5U.highTierFluidHeaters)
            registerMachineRecipe(GATileEntities.FLUID_HEATER,              "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', CABLE_SINGLE, 'G', GLASS,        'O', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierSifters)
            registerMachineRecipe(GATileEntities.SIFTER,                    "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON,                'C', CIRCUIT,        'W', CABLE_SINGLE, 'F', MetaItems.ITEM_FILTER);
        if (GAConfig.GT5U.highTierArcFurnaces)
            registerMachineRecipe(GATileEntities.ARC_FURNACE,               "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE,                 'C', CIRCUIT,        'W', CABLE_QUAD,   'G', new UnificationEntry(ingot, Graphite));
        if (GAConfig.GT5U.highTierPlasmaArcFurnaces)
            registerMachineRecipe(GATileEntities.PLASMA_ARC_FURNACE,        "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE,                 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD,   'T', PUMP,         'G', new UnificationEntry(ingot, Graphite));
        if (GAConfig.Misc.highTierCollector)
            registerMachineRecipe(GATileEntities.AIR_COLLECTOR,             "WFW", "PHP", "WCW", 'H', HULL, 'P', PUMP,                  'C', CIRCUIT,        'W', Blocks.IRON_BARS,                'F', MetaItems.ITEM_FILTER);
    }

    private static void otherMachines() {

        // Fluid Export Hatch
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ulv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ULV).getStackForm(),     "F", "M", "G", 'M', MetaTileEntities.HULL[ULV].getStackForm(),          'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_lv",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[LV].getStackForm(),           'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_mv",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.MV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.MV].getStackForm(),  'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_hv",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.HV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.HV].getStackForm(),  'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ev",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.EV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(),  'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_iv",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.IV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.IV].getStackForm(),  'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_luv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LuV).getStackForm(),     "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_zpm", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ZPM).getStackForm(),     "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uv",  GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UV).getStackForm(),      "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.UV].getStackForm(),  'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uhv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UHV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[0].getStackForm(),          'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uev", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UEV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[1].getStackForm(),          'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uiv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UIV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[2].getStackForm(),          'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_max", GATileEntities.OUTPUT_HATCH_FILTERED.get(9).getStackForm(),                "F", "M", "G", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);

        // Drums
        if (GAConfig.GT6.registerDrums) {
            ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', Items.SLIME_BALL, 'P', "plankWood", 'R', new UnificationEntry(stickLong, Iron));

            OrePrefix prefix = (GAConfig.GT6.addCurvedPlates && GAConfig.GT6.BendingCylinders && GAConfig.GT6.BendingCurvedPlates) ? plateCurved : plate;
            ModHandler.addShapedRecipe("bronze_drum",          GATileEntities.BRONZE_DRUM.getStackForm(),          " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Bronze),         'R', new UnificationEntry(stickLong, Bronze));
            ModHandler.addShapedRecipe("steel_drum",           GATileEntities.STEEL_DRUM.getStackForm(),           " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Steel),          'R', new UnificationEntry(stickLong, Steel));
            ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
            ModHandler.addShapedRecipe("titanium_drum",        GATileEntities.TITANIUM_DRUM.getStackForm(),        " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Titanium),       'R', new UnificationEntry(stickLong, Titanium));
            ModHandler.addShapedRecipe("tungstensteel_drum",   GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(),   " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, TungstenSteel),  'R', new UnificationEntry(stickLong, TungstenSteel));
        }

        // Crates
        if (GAConfig.Misc.registerCrates) {

            ModHandler.addShapedRecipe("wooden_crate", GATileEntities.WOODEN_CRATE.getStackForm(), "RPR", "PsP", "RPR", 'P', "plankWood", 'R', new UnificationEntry(screw, Iron));

            ModHandler.addShapedRecipe("bronze_crate",          GATileEntities.BRONZE_CRATE.getStackForm(),          "RPR", "PhP", "RPR", 'P', new UnificationEntry(plate, Bronze),         'R', new UnificationEntry(stickLong, Bronze));
            ModHandler.addShapedRecipe("steel_crate",           GATileEntities.STEEL_CRATE.getStackForm(),           "RPR", "PhP", "RPR", 'P', new UnificationEntry(plate, Steel),          'R', new UnificationEntry(stickLong, Steel));
            ModHandler.addShapedRecipe("stainless_steel_crate", GATileEntities.STAINLESS_STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plate, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
            ModHandler.addShapedRecipe("titanium_crate",        GATileEntities.TITANIUM_CRATE.getStackForm(),        "RPR", "PhP", "RPR", 'P', new UnificationEntry(plate, Titanium),       'R', new UnificationEntry(stickLong, Titanium));
            ModHandler.addShapedRecipe("tungstensteel_crate",   GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm(),   "RPR", "PhP", "RPR", 'P', new UnificationEntry(plate, TungstenSteel),  'R', new UnificationEntry(stickLong, TungstenSteel));
        }

        // Energy Converters
        for (final EnergyConverterType type : EnergyConverterType.values()) {
            if (GATileEntities.ENERGY_CONVERTER.containsKey(type)) {
                GATileEntities.ENERGY_CONVERTER.get(type).forEach(EnergyConverterCraftingHelper.HELPER.logic(type));
            }
        }

        // Hot Coolant Rotor Holders
        ModHandler.addShapedRecipe("ga_rotor_holder_hv",  GATileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[HV].getStackForm(),  'W', new UnificationEntry(wireGtHex, Gold),                 'R', new UnificationEntry(gear, BlackSteel));
        ModHandler.addShapedRecipe("ga_rotor_holder_luv", GATileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, YttriumBariumCuprate), 'R', new UnificationEntry(gear, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_rotor_holder_uhv", GATileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', GATileEntities.GA_HULLS[0].getStackForm(), 'W', new UnificationEntry(wireGtHex, Duranium),             'R', new UnificationEntry(gear, Seaborgium));

        // Custom Hatches
        ModHandler.addShapedRecipe("ga_qubit_input_hatch",  GATileEntities.QBIT_INPUT_HATCH[0].getStackForm(),  "   ", "CM ", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberHex));
        ModHandler.addShapedRecipe("ga_qubit_output_hatch", GATileEntities.QBIT_OUTPUT_HATCH[0].getStackForm(), "   ", " MC", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberSingle));
        ModHandler.addShapedRecipe("ga_steam_hatch",      GATileEntities.STEAM_HATCH.getStackForm(),      "BPB", "BTB", "BPB", 'B', new UnificationEntry(plate, Bronze), 'P', new UnificationEntry(pipeMedium, Bronze), 'T', MetaTileEntities.BRONZE_TANK.getStackForm());
        ModHandler.addShapedRecipe("ga_steam_input_bus",  GATileEntities.STEAM_INPUT_BUS.getStackForm(),  "BMB", "THT", "BMB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin),       'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        ModHandler.addShapedRecipe("ga_steam_output_bus", GATileEntities.STEAM_OUTPUT_BUS.getStackForm(), "BTB", "MHM", "BTB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin),       'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);

        //Steam Machines
        ModHandler.addShapedRecipe("ga_steam_mixer", GATileEntities.STEAM_MIXER.getStackForm(), "GRG", "GPG", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'P', new UnificationEntry(pipeSmall, Bronze), 'R', new UnificationEntry(rotor, Bronze), 'G', Blocks.GLASS);
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(pipeMedium, Bronze), 'L', new UnificationEntry(pipeLarge, Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'R', new UnificationEntry(rotor, Bronze));
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
