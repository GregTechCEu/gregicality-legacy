package gregicadditions.recipes;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMultiblockCasing.CasingType.TIERED_HULL_LV;
import static gregicadditions.machines.GATileEntities.*;
import static gregicadditions.recipes.GARecipeMaps.DISASSEMBLER_RECIPES;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Elite;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtech.common.metatileentities.MetaTileEntities.PUMP;

public class StagedRemovalRecipes {

    public static void init() {

        // Drilling Rig
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(DRILLING_RIG.getStackForm())
                .output(ingot, SolderingAlloy, 10)
                .outputs(ELECTRIC_PUMP_HV.getStackForm(2))
                .outputs(PUMP[2].getStackForm())
                .outputs(ELECTRONIC_COMPUTER.getStackForm())
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .buildAndRegister();

        // Processing Array
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(PROCESSING_ARRAY.getStackForm())
                .outputs(MICRO_MAINFRAME.getStackForm())
                .outputs(ENERGY_LAPOTRONIC_ORB.getStackForm())
                .outputs(MICRO_MAINFRAME.getStackForm())
                .outputs(ROBOT_ARM_IV.getStackForm())
                .outputs(HULL[IV].getStackForm())
                .outputs(ROBOT_ARM_IV.getStackForm())
                .outputs(MICRO_MAINFRAME.getStackForm())
                .outputs(TOOL_DATA_ORB.getStackForm())
                .outputs(MICRO_MAINFRAME.getStackForm())
                .buildAndRegister();


        // Volcanus
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(VOLCANUS.getStackForm())
                .output(gear, HastelloyN)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .output(gear, HastelloyN)
                .outputs(ROBOT_ARM_IV.getStackForm())
                .outputs(GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm())
                .outputs(ROBOT_ARM_IV.getStackForm())
                .output(plateDense, HastelloyN)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .output(plateDense, HastelloyN)
                .buildAndRegister();


        // Cryogenic Freezer
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(CRYOGENIC_FREEZER.getStackForm())
                .output(gear, IncoloyMA956)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .output(gear, IncoloyMA956)
                .outputs(ELECTRIC_PISTON_IV.getStackForm())
                .outputs(GATileEntities.VACUUM_FREEZER.getStackForm())
                .outputs(ELECTRIC_PISTON_IV.getStackForm())
                .output(plateDense, HG1223)
                .outputs(MICRO_MAINFRAME.getStackForm())
                .output(plateDense, HG1223)
                .buildAndRegister();

        // Chemical Plant
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(CHEMICAL_PLANT.getStackForm())
                .output(ingot, Steel, 4)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 2))
                .output(plate, Aluminium, 32)
                .output(gear, Steel, 4)
                .output(plate, CobaltBrass, 16)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_LV))
                .buildAndRegister();

        // Large Multi-Use Machine
        DISASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(LARGE_MULTI_USE.getStackForm())
                .outputs(MetaTileEntities.COMPRESSOR[EV - 1].getStackForm())
                .outputs(MetaTileEntities.LATHE[EV - 1].getStackForm())
                .outputs(MetaTileEntities.POLARIZER[EV - 1].getStackForm())
                .output(plate, Staballoy)
                .outputs(MetaTileEntities.HULL[EV].getStackForm())
                .output(plate, Staballoy)
                .outputs(MetaTileEntities.FERMENTER[EV - 1].getStackForm())
                .outputs(MetaTileEntities.LASER_ENGRAVER[EV - 1].getStackForm())
                .outputs(MetaTileEntities.EXTRACTOR[EV - 1].getStackForm())
                .buildAndRegister();
    }
}
