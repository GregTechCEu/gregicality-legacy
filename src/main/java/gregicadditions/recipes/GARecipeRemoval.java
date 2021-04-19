package gregicadditions.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class GARecipeRemoval {
    public static void init() {
        ModHandler.removeFurnaceSmelting(MetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:brick_block_to_dust"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));

        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID, 3));
        ModHandler.removeRecipes(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST, 3));

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:ingot_mixed_metal"));

        ModHandler.removeRecipes(EMITTER_LV.getStackForm());
        ModHandler.removeRecipes(EMITTER_MV.getStackForm());
        ModHandler.removeRecipes(EMITTER_HV.getStackForm());
        ModHandler.removeRecipes(EMITTER_EV.getStackForm());
        ModHandler.removeRecipes(EMITTER_IV.getStackForm());

        ModHandler.removeRecipes(SENSOR_LV.getStackForm());
        ModHandler.removeRecipes(SENSOR_MV.getStackForm());
        ModHandler.removeRecipes(SENSOR_HV.getStackForm());
        ModHandler.removeRecipes(SENSOR_EV.getStackForm());
        ModHandler.removeRecipes(SENSOR_IV.getStackForm());

        ModHandler.removeRecipes(ROBOT_ARM_LV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_MV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_HV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_EV.getStackForm());
        ModHandler.removeRecipes(ROBOT_ARM_IV.getStackForm());

        ModHandler.removeRecipes(FIELD_GENERATOR_LV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_MV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_HV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_EV.getStackForm());
        ModHandler.removeRecipes(FIELD_GENERATOR_IV.getStackForm());

        ModHandler.removeRecipes(ELECTRIC_PUMP_LV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_MV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_HV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_EV.getStackForm());
        ModHandler.removeRecipes(ELECTRIC_PUMP_IV.getStackForm());

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_assembler_casing"));

        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));
        ModHandler.removeRecipes(DYNAMITE.getStackForm());

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_1"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_c"));

        ModHandler.removeRecipes(INTEGRATED_CIRCUIT.getStackForm());

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_max"));

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_cupronickel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_kanthal"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_nichrome"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_tungstensteel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_hss_g"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_naquadah"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_naquadah_alloy"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:heating_coil_superconductor"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_cupronickel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_kanthal"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_nichrome"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_tungstensteel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_hss_g"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_naquadah"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_naquadah_alloy"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:wire_coil_superconductor"));

        ModHandler.removeRecipes(OreDictUnifier.get(dust, YttriumBariumCuprate, 6));
        ModHandler.removeRecipes(OreDictUnifier.get(dustTiny, YttriumBariumCuprate, 6));
    }
}
