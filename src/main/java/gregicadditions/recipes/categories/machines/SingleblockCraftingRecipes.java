package gregicadditions.recipes.categories.machines;

import gregicadditions.machines.GATileEntities;
import gregtech.common.items.MetaItems;

import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class SingleblockCraftingRecipes {

    public static void init() {

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
                'Q', CABLE,
                'C', CIRCUIT,
                'G', GLASS,
                'A', ROBOT_ARM);

        registerMachineRecipe(GATileEntities.DISASSEMBLER,
                "RSV", "PMV", "ICI",
                'M', HULL,
                'C', CABLE,
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
}
