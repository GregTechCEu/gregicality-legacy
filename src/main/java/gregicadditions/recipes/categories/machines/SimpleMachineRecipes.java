package gregicadditions.recipes.categories.machines;

import gregicadditions.GAConfig;
import gregicadditions.machines.GATileEntities;
import gregtech.api.items.OreDictNames;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;

import static gregicadditions.recipes.helper.AdditionMethods.registerMachineRecipe;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.api.unification.material.Materials.Graphite;
import static gregtech.api.unification.material.Materials.Lead;
import static gregtech.api.unification.ore.OrePrefix.ingot;
import static gregtech.api.unification.ore.OrePrefix.plate;

public class SimpleMachineRecipes {

    public static void init() {
        newMachines();
        highTierMachines();
        gtceRecipeOverride();
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
    }

    // Same recipes as default GT (includes our overrides seen below).
    // Bending Machine does not use a Wrench, instead uses another single tiered cable in its place.
    private static void highTierMachines() {

        if (GAConfig.GT5U.highTierPumps)
            registerMachineRecipe(GATileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);
        if (GAConfig.GT5U.highTierAlloySmelter)
            registerMachineRecipe(GATileEntities.ALLOY_SMELTER, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE_SINGLE, 'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierAssemblers)
            registerMachineRecipe(GATileEntities.ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierBenders)
            registerMachineRecipe(GATileEntities.BENDER, "PWP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCanners)
            registerMachineRecipe(GATileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierCompressors)
            registerMachineRecipe(GATileEntities.COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCutters)
            registerMachineRecipe(GATileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        if (GAConfig.GT5U.highTierElectricFurnace)
            registerMachineRecipe(GATileEntities.ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE_SINGLE, 'C', COIL_HEATING);
        if (GAConfig.GT5U.highTierExtractors)
            registerMachineRecipe(GATileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierExtruders)
            registerMachineRecipe(GATileEntities.EXTRUDER, "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON, 'E', CIRCUIT, 'P', PIPE, 'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierLathes)
            registerMachineRecipe(GATileEntities.LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'D', DIAMOND);
        if (GAConfig.GT5U.highTierMacerators)
            registerMachineRecipe(GATileEntities.MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GRINDER);
        if (GAConfig.GT5U.highTierMicrowaves)
            registerMachineRecipe(GATileEntities.MICROWAVE, "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR, 'R', EMITTER, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'L', new UnificationEntry(plate, Lead));
        if (GAConfig.GT5U.highTierWiremills)
            registerMachineRecipe(GATileEntities.WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierCentrifuges)
            registerMachineRecipe(GATileEntities.CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierElectrolyzers)
            registerMachineRecipe(GATileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'I', WIRE, 'G', GLASS);
        if (GAConfig.GT5U.highTierThermalCentrifuges)
            registerMachineRecipe(GATileEntities.THERMAL_CENTRIFUGE, "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'O', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierOreWashers)
            registerMachineRecipe(GATileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPackers)
            registerMachineRecipe(GATileEntities.PACKER, "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierUnpackers)
            registerMachineRecipe(GATileEntities.UNPACKER, "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierChemicalReactors)
            registerMachineRecipe(GATileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidCanners)
            registerMachineRecipe(GATileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierBreweries)
            registerMachineRecipe(GATileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFermenters)
            registerMachineRecipe(GATileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidExtractors)
            registerMachineRecipe(GATileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidSolidifiers)
            registerMachineRecipe(GATileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierDistilleries)
            registerMachineRecipe(GATileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierChemicalBaths)
            registerMachineRecipe(GATileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPolarizers)
            registerMachineRecipe(GATileEntities.POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierElectromagneticSeparators)
            registerMachineRecipe(GATileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierAutoclaves)
            registerMachineRecipe(GATileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        if (GAConfig.GT5U.highTierMixers)
            registerMachineRecipe(GATileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        if (GAConfig.GT5U.highTierLaserEngravers)
            registerMachineRecipe(GATileEntities.LASER_ENGRAVER, "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierFormingPresses)
            registerMachineRecipe(GATileEntities.FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierForgeHammers)
            registerMachineRecipe(GATileEntities.FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'A', OreDictNames.craftingAnvil);
        if (GAConfig.GT5U.highTierFluidHeaters)
            registerMachineRecipe(GATileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierSifters)
            registerMachineRecipe(GATileEntities.SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F', MetaItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        if (GAConfig.GT5U.highTierArcFurnaces)
            registerMachineRecipe(GATileEntities.ARC_FURNACE, "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'G', new UnificationEntry(ingot, Graphite));
        if (GAConfig.GT5U.highTierPlasmaArcFurnaces)
            registerMachineRecipe(GATileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));
        if (GAConfig.Misc.highTierCollector)
            registerMachineRecipe(GATileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
    }

    private static void gtceRecipeOverride() {

        // These are overridden only to insert our tiered Glass
        registerMachineRecipe(MetaTileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        registerMachineRecipe(MetaTileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'I', WIRE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);

        // Replaces circuits to be tier+1 instead of normal tier
        registerMachineRecipe(MetaTileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));

        // Replaces the original GT Piston with a GT Pipe
        registerMachineRecipe(MetaTileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);

        // Replaces the original GT Fluid Filter with a GT Item Filter
        registerMachineRecipe(MetaTileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
    }
}
