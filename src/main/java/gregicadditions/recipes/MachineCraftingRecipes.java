package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.item.*;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.energyconverter.utils.EnergyConverterCraftingHelper;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GACraftingComponents.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMachineCasing.MachineCasingType.ZPM;
import static gregtech.common.blocks.BlockMachineCasing.MachineCasingType.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static gregtech.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;
import static gregtech.common.items.MetaItems.*;

public class MachineCraftingRecipes {

    private static final String[] tiers = {"lv", "mv", "hv", "ev", "iv", "luv", "zpm", "uv", "max"};

    public static void init() {
        //Removal
        for (String tier : tiers) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.alloy_smelter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.assembler." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.bender." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.canner." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.compressor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.cutter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.electric_furnace." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.extractor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.extruder." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.lathe." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.macerator." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.microwave." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.wiremill." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.centrifuge." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.electrolyzer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.thermal_centrifuge." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.ore_washer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.packer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.unpacker." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.chemical_reactor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_canner." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.brewery." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fermenter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_extractor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_solidifier." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.distillery." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.chemical_bath." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.polarizer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.electromagnetic_separator." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.autoclave." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.mixer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.laser_engraver." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.forming_press." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.forge_hammer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_heater." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.sifter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.arc_furnace." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.plasma_arc_furnace." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.pump." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.air_collector." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_" + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_" + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_" + tier));
        }

        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_boiler_solar_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_furnace_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_furnace_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_macerator_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_macerator_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_extractor_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_extractor_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_hammer_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_hammer_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_compressor_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_compressor_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_alloy_smelter_bronze"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_alloy_smelter_steel"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:bronze_primitive_blast_furnace"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:electric_blast_furnace"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:vacuum_freezer"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:implosion_compressor"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:distillation_tower"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:cracking_unit"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:pyrolyse_oven"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_engine"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:engine_intake_casing"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:multi_furnace"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_steam_turbine"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_gas_turbine"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_plasma_turbine"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_bronze_boiler"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_steel_boiler"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_titanium_boiler"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_tungstensteel_boiler"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:magic_energy_absorber"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_ev"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_iv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_luv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_max"));

        //rotor hot coolant
        ModHandler.addShapedRecipe("ga_rotor_holder_hv", GATileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Gold), 'R', new UnificationEntry(gear, BlackSteel));
        ModHandler.addShapedRecipe("ga_rotor_holder_luv", GATileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, YttriumBariumCuprate), 'R', new UnificationEntry(gear, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_rotor_holder_uhv", GATileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', GATileEntities.GA_HULLS[0].getStackForm(), 'W', new UnificationEntry(wireGtHex, Duranium), 'R', new UnificationEntry(gear, Seaborgium));

        //machine hull
        ModHandler.addShapedRecipe("ga_casing_ulv", MetaBlocks.MACHINE_CASING.getItemVariant(ULV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_casing_lv", MetaBlocks.MACHINE_CASING.getItemVariant(LV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Steel));
        ModHandler.addShapedRecipe("ga_casing_mv", MetaBlocks.MACHINE_CASING.getItemVariant(MV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Aluminium));
        ModHandler.addShapedRecipe("ga_casing_hv", MetaBlocks.MACHINE_CASING.getItemVariant(HV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, StainlessSteel));
        ModHandler.addShapedRecipe("ga_casing_ev", MetaBlocks.MACHINE_CASING.getItemVariant(EV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Titanium));
        ModHandler.addShapedRecipe("ga_casing_iv", MetaBlocks.MACHINE_CASING.getItemVariant(IV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, TungstenSteel));
        ModHandler.addShapedRecipe("ga_casing_luv", MetaBlocks.MACHINE_CASING.getItemVariant(LuV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_casing_zpm", MetaBlocks.MACHINE_CASING.getItemVariant(ZPM), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Osmiridium));
        ModHandler.addShapedRecipe("ga_casing_uv", MetaBlocks.MACHINE_CASING.getItemVariant(UV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Tritanium));
        ModHandler.addShapedRecipe("ga_casing_uhv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
        ModHandler.addShapedRecipe("ga_casing_uev", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
        ModHandler.addShapedRecipe("ga_casing_uiv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));

        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ulv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ULV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.ULV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_lv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.LV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_mv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.MV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.MV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_hv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.HV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ev", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.EV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_iv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.IV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_luv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LuV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_zpm", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ZPM).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.UV].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uhv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UHV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uev", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UEV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uiv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UIV + 1).getStackForm(), "F", "M", "G", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_max", GATileEntities.OUTPUT_HATCH_FILTERED.get(9).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'G', new ItemStack(Blocks.GLASS), 'F', FLUID_FILTER);

        //energy input hatch
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uhv", GATileEntities.ENERGY_INPUT[0].getStackForm(), "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uev", GATileEntities.ENERGY_INPUT[1].getStackForm(), "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uiv", GATileEntities.ENERGY_INPUT[2].getStackForm(), "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
//        ModHandler.addShapedRecipe("ga_energy_input_hatch_max", GATileEntities.ENERGY_INPUT[GTValues.MAX].getStackForm(), "   ", "CM ", "   ", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Neutronium));

        //energy output hatch
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uhv", GATileEntities.ENERGY_OUTPUT[0].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uev", GATileEntities.ENERGY_OUTPUT[1].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uiv", GATileEntities.ENERGY_OUTPUT[2].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
//        ModHandler.addShapedRecipe("ga_energy_output_hatch_max", GATileEntities.ENERGY_OUTPUT[GTValues.MAX].getStackForm(), "   ", " MC", "   ", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'C', new UnificationEntry(wireGtSingle, Neutronium));


        //qubit input hatch
        ModHandler.addShapedRecipe("ga_qubit_input_hatch", GATileEntities.QBIT_INPUT_HATCH[0].getStackForm(), "   ", "CM ", "   ", 'M', MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), 'C', new UnificationEntry(GAEnums.GAOrePrefix.opticalFiberHex));

        //qubit output hatch
        ModHandler.addShapedRecipe("ga_qubit_output_hatch", GATileEntities.QBIT_OUTPUT_HATCH[0].getStackForm(), "   ", " MC", "   ", 'M', MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), 'C', new UnificationEntry(GAEnums.GAOrePrefix.opticalFiberSingle));


        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, WroughtIron, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV)).circuitMeta(8).duration(25).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Steel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Aluminium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, StainlessSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Titanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, TungstenSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, RhodiumPlatedPalladium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Osmiridium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Tritanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Seaborgium, 8).outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Bohrium, 8).outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(plate, Quantum, 8).outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)).circuitMeta(8).duration(50).buildAndRegister();


        ModHandler.addShapedRecipe("ga_hull_ulv", MetaTileEntities.HULL[GAValues.ULV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(ULV), 'C', new UnificationEntry(cableGtSingle, RedAlloy), 'H', new UnificationEntry(plate, WroughtIron), 'P', new UnificationEntry(plate, Wood));
        ModHandler.addShapedRecipe("ga_hull_lv", MetaTileEntities.HULL[GAValues.LV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(LV), 'C', new UnificationEntry(cableGtSingle, Tin), 'H', new UnificationEntry(plate, Steel), 'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_mv", MetaTileEntities.HULL[GAValues.MV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(MV), 'C', new UnificationEntry(cableGtSingle, Copper), 'H', new UnificationEntry(plate, Aluminium), 'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_hv", MetaTileEntities.HULL[GAValues.HV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(HV), 'C', new UnificationEntry(cableGtSingle, Gold), 'H', new UnificationEntry(plate, StainlessSteel), 'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_ev", MetaTileEntities.HULL[GAValues.EV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(EV), 'C', new UnificationEntry(cableGtSingle, Aluminium), 'H', new UnificationEntry(plate, Titanium), 'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_iv", MetaTileEntities.HULL[GAValues.IV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(IV), 'C', new UnificationEntry(cableGtSingle, Tungsten), 'H', new UnificationEntry(plate, TungstenSteel), 'P', new UnificationEntry(plate, PolyvinylChloride));
        ModHandler.addShapedRecipe("ga_hull_luv", MetaTileEntities.HULL[GAValues.LuV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(LuV), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'H', new UnificationEntry(plate, RhodiumPlatedPalladium), 'P', new UnificationEntry(plate, PolyvinylChloride));
        ModHandler.addShapedRecipe("ga_hull_zpm", MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah), 'H', new UnificationEntry(plate, Osmiridium), 'P', new UnificationEntry(plate, PolyphenyleneSulfide));
        ModHandler.addShapedRecipe("ga_hull_uv", MetaTileEntities.HULL[GAValues.UV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(UV), 'C', new UnificationEntry(cableGtSingle, NaquadahAlloy), 'H', new UnificationEntry(plate, Tritanium), 'P', new UnificationEntry(plate, PolyphenyleneSulfide));
        ModHandler.addShapedRecipe("ga_hull_uhv", GATileEntities.GA_HULLS[0].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium), 'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uev", GATileEntities.GA_HULLS[1].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV), 'C', new UnificationEntry(cableGtQuadruple, Pikyonium), 'H', new UnificationEntry(plate, Bohrium), 'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uiv", GATileEntities.GA_HULLS[2].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV), 'C', new UnificationEntry(cableGtQuadruple, Cinobite), 'H', new UnificationEntry(plate, Quantum), 'P', new UnificationEntry(plate, Zylon));

        //Power Manipulation Machines
        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();
        ModHandler.addShapedRecipe("ga_charger_ulv", MetaTileEntities.CHARGER[GAValues.ULV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.ULV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Lead), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_ULV_TANTALUM, 'C', new UnificationEntry(circuit, Tier.Primitive));
        ModHandler.addShapedRecipe("ga_charger_lv", MetaTileEntities.CHARGER[GAValues.LV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.LV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tin), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_LV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Basic));
        ModHandler.addShapedRecipe("ga_charger_mv", MetaTileEntities.CHARGER[GAValues.MV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.MV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Copper), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_MV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Good));
        ModHandler.addShapedRecipe("ga_charger_hv", MetaTileEntities.CHARGER[GAValues.HV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Gold), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_HV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("ga_charger_ev", MetaTileEntities.CHARGER[GAValues.EV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Aluminium), 'T', OreDictNames.chestWood, 'B', MetaItems.LAPOTRON_CRYSTAL, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme));
        ModHandler.addShapedRecipe("ga_charger_iv", MetaTileEntities.CHARGER[GAValues.IV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tungsten), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB, 'C', new UnificationEntry(circuit, Tier.Elite));
        ModHandler.addShapedRecipe("ga_charger_luv", MetaTileEntities.CHARGER[GAValues.LuV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, VanadiumGallium), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB2, 'C', new UnificationEntry(circuit, Tier.Master));
        ModHandler.addShapedRecipe("ga_charger_zpm", MetaTileEntities.CHARGER[GAValues.ZPM].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'W', new UnificationEntry(wireGtHex, Naquadah), 'T', OreDictNames.chestWood, 'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE : MetaItems.ENERGY_LAPOTRONIC_ORB2, 'C', new UnificationEntry(circuit, Tier.Ultimate));
        ModHandler.addShapedRecipe("ga_charger_uv", MetaTileEntities.CHARGER[GAValues.UV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.UV].getStackForm(), 'W', new UnificationEntry(wireGtHex, NaquadahAlloy), 'T', OreDictNames.chestWood, 'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_CLUSTER : last_bat, 'C', new UnificationEntry(circuit, Tier.Superconductor));
        ModHandler.addShapedRecipe("ga_charger_max", MetaTileEntities.CHARGER[GTValues.MAX].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tier.Superconductor), 'T', OreDictNames.chestWood, 'B', last_bat, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite));

        ModHandler.addShapedRecipe("ga_transformer_ev", MetaTileEntities.TRANSFORMER[GAValues.EV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Aluminium), 'B', new UnificationEntry(cableGtSingle, Gold), 'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_iv", MetaTileEntities.TRANSFORMER[GAValues.IV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Tungsten), 'B', new UnificationEntry(cableGtSingle, Aluminium), 'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_luv", MetaTileEntities.TRANSFORMER[GAValues.LuV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'B', new UnificationEntry(cableGtSingle, Tungsten), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_zpm", MetaTileEntities.TRANSFORMER[GAValues.ZPM - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Naquadah), 'B', new UnificationEntry(cableGtSingle, VanadiumGallium), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_uv", MetaTileEntities.TRANSFORMER[GAValues.UV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'C', new UnificationEntry(wireGtQuadruple, NaquadahAlloy), 'B', new UnificationEntry(cableGtSingle, Naquadah), 'K', MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT);
        //ModHandler.addShapedRecipe("ga_transformer_max", MetaTileEntities.TRANSFORMER[GTValues.MAX - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtSingle, Tier.Superconductor), 'B', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy), 'K', MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT);

        //Steam Machines
        ModHandler.addShapedRecipe("ga_steam_boiler_solar_bronze", MetaTileEntities.STEAM_BOILER_SOLAR_BRONZE.getStackForm(), "GGG", "SSS", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_BRICKS_HULL), 'P', new UnificationEntry(pipeSmall, Bronze), 'S', new UnificationEntry(plate, Silver), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("ga_steam_furnace_bronze", MetaTileEntities.STEAM_FURNACE_BRONZE.getStackForm(), "XXX", "XMX", "XFX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_BRICKS_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("ga_steam_furnace_steel", MetaTileEntities.STEAM_FURNACE_STEEL.getStackForm(), "XXX", "XMX", "XFX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_BRICKS_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("ga_steam_macerator_bronze", MetaTileEntities.STEAM_MACERATOR_BRONZE.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'P', OreDictNames.craftingPiston, 'D', new ItemStack(Items.FLINT));
        ModHandler.addShapedRecipe("ga_steam_macerator_steel", MetaTileEntities.STEAM_MACERATOR_STEEL.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'P', OreDictNames.craftingPiston, 'D', new ItemStack(Items.FLINT));
        ModHandler.addShapedRecipe("ga_steam_extractor_bronze", MetaTileEntities.STEAM_EXTRACTOR_BRONZE.getStackForm(), "XXX", "PMG", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'P', OreDictNames.craftingPiston, 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("ga_steam_extractor_steel", MetaTileEntities.STEAM_EXTRACTOR_STEEL.getStackForm(), "XXX", "PMG", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'P', OreDictNames.craftingPiston, 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("ga_steam_hammer_bronze", MetaTileEntities.STEAM_HAMMER_BRONZE.getStackForm(), "XPX", "XMX", "XAX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'P', OreDictNames.craftingPiston, 'A', OreDictNames.craftingAnvil);
        ModHandler.addShapedRecipe("ga_steam_hammer_steel", MetaTileEntities.STEAM_HAMMER_STEEL.getStackForm(), "XPX", "XMX", "XAX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'P', OreDictNames.craftingPiston, 'A', OreDictNames.craftingAnvil);
        ModHandler.addShapedRecipe("ga_steam_compressor_bronze", MetaTileEntities.STEAM_COMPRESSOR_BRONZE.getStackForm(), "XXX", "PMP", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'P', OreDictNames.craftingPiston);
        ModHandler.addShapedRecipe("ga_steam_compressor_steel", MetaTileEntities.STEAM_COMPRESSOR_STEEL.getStackForm(), "XXX", "PMP", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'P', OreDictNames.craftingPiston);
        ModHandler.addShapedRecipe("ga_steam_alloy_smelter_bronze", MetaTileEntities.STEAM_ALLOY_SMELTER_BRONZE.getStackForm(), "XXX", "FMF", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_BRICKS_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("ga_steam_alloy_smelter_steel", MetaTileEntities.STEAM_ALLOY_SMELTER_STEEL.getStackForm(), "XXX", "FMF", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_BRICKS_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("ga_steam_mixer", GATileEntities.STEAM_MIXER.getStackForm(), "GRG", "GPG", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'P', new UnificationEntry(pipeSmall, Bronze), 'R', "rotorBronze", 'G', "blockGlass");

        //Multiblock compatibility
        ModHandler.addShapelessRecipe("ga_cracking_unit_compatibility", GATileEntities.CRACKER.getStackForm(), MetaTileEntities.CRACKER.getStackForm());
        ModHandler.addShapelessRecipe("ga_electric_blast_furnace_compatibility", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
        ModHandler.addShapelessRecipe("ga_vacuum_freezer_compatibility", GATileEntities.VACUUM_FREEZER.getStackForm(), MetaTileEntities.VACUUM_FREEZER.getStackForm());
        ModHandler.addShapelessRecipe("ga_implosion_compresso_compatibilityr", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(), MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm());
        ModHandler.addShapelessRecipe("ga_diesel_engine_compatibility", GATileEntities.DIESEL_ENGINE.getStackForm(), MetaTileEntities.DIESEL_ENGINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_multi_furnace_compatibility", GATileEntities.MULTI_FURNACE.getStackForm(), MetaTileEntities.MULTI_FURNACE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_steam_turbine_compatibility", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(), MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_gas_turbine_compatibility", GATileEntities.LARGE_GAS_TURBINE.getStackForm(), MetaTileEntities.LARGE_GAS_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_plasma_turbine_compatibility", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(), MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_pyrolyse_oven_compatibility", GATileEntities.PYROLYSE_OVEN.getStackForm(), MetaTileEntities.PYROLYSE_OVEN.getStackForm());
        ModHandler.addShapelessRecipe("ga_distillation_tower_compatibility", GATileEntities.DISTILLATION_TOWER.getStackForm(), MetaTileEntities.DISTILLATION_TOWER.getStackForm());


        //MultiBlocks
        ModHandler.addShapedRecipe("ga_distillation_tower", GATileEntities.DISTILLATION_TOWER.getStackForm(), "CBC", "FMF", "CBC", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'B', new UnificationEntry(OrePrefix.pipeLarge, Materials.StainlessSteel), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Extreme), 'F', MetaItems.ELECTRIC_PUMP_EV);
        ModHandler.addShapedRecipe("ga_cracking_unit", GATileEntities.CRACKER.getStackForm(), "CEC", "PHP", "CEC", 'C', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL), 'E', MetaItems.ELECTRIC_PUMP_HV, 'P', new UnificationEntry(circuit, Tier.Advanced), 'H', MetaTileEntities.HULL[GAValues.HV].getStackForm());
        ModHandler.addShapedRecipe("ga_primitive_blast_furnace", MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(), "hRS", "PBR", "dRS", 'R', OreDictUnifier.get(stick, Iron), 'S', OreDictUnifier.get(screw, Iron), 'P', "plateIron", 'B', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS));
        ModHandler.addShapedRecipe("ga_electric_blast_furnace", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), "FFF", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Invar), 'F', OreDictNames.craftingFurnace, 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin));
        ModHandler.addShapedRecipe("ga_vacuum_freezer", GATileEntities.VACUUM_FREEZER.getStackForm(), "PPP", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Aluminium), 'P', MetaItems.ELECTRIC_PUMP_HV, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold));
        ModHandler.addShapedRecipe("ga_implosion_compressor", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(), "OOO", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Steel), 'O', new UnificationEntry(stone, Obsidian), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Aluminium));
        ModHandler.addShapedRecipe("ga_pyrolyse_oven", GATileEntities.PYROLYSE_OVEN.getStackForm(), "WEP", "EME", "WCP", 'M', MetaTileEntities.HULL[GAValues.MV].getStackForm(), 'W', MetaItems.ELECTRIC_PISTON_MV, 'P', new UnificationEntry(wireGtQuadruple, Cupronickel), 'E', new UnificationEntry(circuit, Tier.Good), 'C', MetaItems.ELECTRIC_PUMP_MV);
        ModHandler.addShapedRecipe("ga_diesel_engine", GATileEntities.DIESEL_ENGINE.getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_EV, 'E', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(circuit, Tier.Elite), 'W', new UnificationEntry(wireGtSingle, TungstenSteel), 'G', new UnificationEntry(gear, Titanium));
        ModHandler.addShapedRecipe("ga_engine_intake_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING), "PhP", "RFR", "PwP", 'R', new UnificationEntry(pipeMedium, Titanium), 'F', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Titanium), 'P', new UnificationEntry(rotor, Titanium));
        ModHandler.addShapedRecipe("ga_multi_furnace", GATileEntities.MULTI_FURNACE.getStackForm(), "PPP", "ASA", "CAC", 'P', Blocks.FURNACE, 'A', new UnificationEntry(circuit, Tier.Advanced), 'S', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Invar), 'C', new UnificationEntry(cableGtSingle, AnnealedCopper));
        ModHandler.addShapedRecipe("ga_large_steam_turbine", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, Steel), 'P', new UnificationEntry(circuit, Tier.Advanced), 'A', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, Steel));
        ModHandler.addShapedRecipe("ga_large_high_pressure_steam_turbine", GATileEntities.HOT_COOLANT_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, Stellite), 'P', new UnificationEntry(circuit, Tier.Advanced), 'A', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, Ultimet));
        ModHandler.addShapedRecipe("ga_large_gas_turbine", GATileEntities.LARGE_GAS_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, StainlessSteel), 'P', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme), 'A', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, StainlessSteel));
        ModHandler.addShapedRecipe("ga_large_plasma_turbine", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, TungstenSteel), 'P', new UnificationEntry(circuit, Tier.Master), 'A', MetaTileEntities.HULL[GAValues.UV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, TungstenSteel));
        ModHandler.addShapedRecipe("ga_large_bronze_boiler", MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Tin), 'S', new UnificationEntry(circuit, Tier.Basic), 'A', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS));
        ModHandler.addShapedRecipe("ga_large_steel_boiler", MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Copper), 'S', new UnificationEntry(circuit, Tier.Advanced), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Steel));
        ModHandler.addShapedRecipe("ga_large_titanium_boiler", MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Gold), 'S', new UnificationEntry(circuit, Tier.Elite), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Titanium));
        ModHandler.addShapedRecipe("ga_large_tungstensteel_boiler", MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Aluminium), 'S', new UnificationEntry(circuit, Tier.Master), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), TungstenSteel));
        ModHandler.addShapedRecipe("ga_assline", GATileEntities.ASSEMBLY_LINE.getStackForm(), "CRC", "SAS", "CRC", 'A', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING), 'S', new UnificationEntry(circuit, Tier.Elite));
        ModHandler.addShapedRecipe("ga_processing_array", GATileEntities.PROCESSING_ARRAY.getStackForm(), "CBC", "RHR", "CDC", 'H', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C', new UnificationEntry(circuit, Tier.Elite), 'B', MetaItems.ENERGY_LAPOTRONIC_ORB, 'D', MetaItems.TOOL_DATA_ORB);
        ModHandler.addShapedRecipe("ga_large_thermal_centrifuge", GATileEntities.LARGE_THERMAL_CENTRIFUGE.getStackForm(), "CBC", "RHR", "CDC", 'H', GAConfig.GT5U.highTierThermalCentrifuges ? GATileEntities.THERMAL_CENTRIFUGE[4].getMetaTileEntity().getStackForm() : MetaTileEntities.THERMAL_CENTRIFUGE[3].getStackForm(), 'R', new UnificationEntry(stick, RedSteel), 'B', new UnificationEntry(circuit, Tier.Extreme), 'C', new UnificationEntry(plate, RedSteel), 'D', new UnificationEntry(gear, RedSteel));
        ModHandler.addShapedRecipe("ga_large_bender_and_forming", GATileEntities.LARGE_BENDER_AND_FORMING.getStackForm(), "CBC", "RHR", "CBC", 'H', MetaTileEntities.BENDER[3].getStackForm(), 'R', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Advanced), 'C', new UnificationEntry(plate, Titanium));
        ModHandler.addShapedRecipe("ga_large_centrifuge", GATileEntities.LARGE_CENTRIFUGE.getStackForm(), "CBC", "RHR", "DED", 'H', MetaTileEntities.CENTRIFUGE[3].getStackForm(), 'E', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'B', new UnificationEntry(pipeLarge, StainlessSteel), 'D', new UnificationEntry(plate, GAMaterials.Tumbaga), 'R', new UnificationEntry(plate, Titanium));
        ModHandler.addShapedRecipe("ga_large_chemical_reactor", GATileEntities.LARGE_CHEMICAL_REACTOR.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.CHEMICAL_REACTOR[3].getStackForm(), 'E', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'B', new UnificationEntry(pipeLarge, StainlessSteel), 'D', GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT));
        ModHandler.addShapedRecipe("ga_large_cutting", GATileEntities.LARGE_CUTTING.getStackForm(), "DBD", "CHC", "DED", 'H', GAConfig.GT5U.highTierCutters ? GATileEntities.CUTTER[4].getMetaTileEntity().getStackForm() : MetaTileEntities.CUTTER[3].getStackForm(), 'C', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Advanced), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.MaragingSteel250));
        ModHandler.addShapedRecipe("ga_large_electrolyzer", GATileEntities.LARGE_ELECTROLYZER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.ELECTROLYZER[3].getStackForm(), 'C', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Extreme), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, Stellite));
        ModHandler.addShapedRecipe("ga_large_extruder", GATileEntities.LARGE_EXTRUDER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.EXTRUDER[2].getStackForm(), 'C', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Extreme), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.Inconel625));
        ModHandler.addShapedRecipe("ga_large_macerator", GATileEntities.LARGE_MACERATOR.getStackForm(), "DBD", "CHC", "DED", 'C', MetaTileEntities.MACERATOR[2].getStackForm(), 'E', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'H', new UnificationEntry(circuit, Tier.Master), 'B', MetaTileEntities.MACERATOR[3].getStackForm(), 'D', new UnificationEntry(plate, TungstenCarbide));
        ModHandler.addShapedRecipe("ga_large_mixer", GATileEntities.LARGE_MIXER.getStackForm(), "DED", "CHC", "DED", 'C', new UnificationEntry(plate, TungstenCarbide), 'E', new UnificationEntry(circuit, Tier.Extreme), 'H', MetaTileEntities.MIXER[3].getStackForm(), 'D', new UnificationEntry(plate, GAMaterials.Staballoy));
        ModHandler.addShapedRecipe("ga_large_multi_use", GATileEntities.LARGE_MULTI_USE.getStackForm(), "ABC", "DED", "GHI", 'A', MetaTileEntities.COMPRESSOR[3].getStackForm(), 'B', MetaTileEntities.LATHE[3].getStackForm(), 'C', MetaTileEntities.POLARIZER[3].getStackForm(), 'D', new UnificationEntry(plate, GAMaterials.Staballoy), 'E', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'G', MetaTileEntities.FERMENTER[3].getStackForm(), 'H', MetaTileEntities.LASER_ENGRAVER[3].getStackForm(), 'I', MetaTileEntities.EXTRACTOR[3].getStackForm());
        ModHandler.addShapedRecipe("ga_large_sifter", GATileEntities.LARGE_SIFTER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.SIFTER[3].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Good), 'E', new UnificationEntry(circuit, Tier.Advanced), 'C', new UnificationEntry(cableGtSingle, Gold), 'D', new UnificationEntry(plate, GAMaterials.EglinSteel));
        ModHandler.addShapedRecipe("ga_large_washing_plant", GATileEntities.LARGE_WASHING_PLANT.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.ORE_WASHER[3].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Good), 'E', new UnificationEntry(circuit, Tier.Good), 'C', new UnificationEntry(plate, GAMaterials.Talonite), 'D', new UnificationEntry(plate, GAMaterials.Grisium));
        ModHandler.addShapedRecipe("ga_large_wiremill", GATileEntities.LARGE_WIREMILL.getStackForm(), "DED", "CHC", "DED", 'H', MetaTileEntities.WIREMILL[3].getStackForm(), 'E', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.MaragingSteel250));
        ModHandler.addShapedRecipe("ga_volcanus", GATileEntities.VOLCANUS.getStackForm(), "GCG", "IHI", "PCP", 'H', GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'P', new UnificationEntry(plateDense, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(GAMaterials.HastelloyN.getFluid(144 * 4)).input(OrePrefix.valueOf("gtMetalCasing"), GAMaterials.Staballoy, 2).inputs(MetaItems.EMITTER_LUV.getStackForm(2), MetaItems.SENSOR_LUV.getStackForm(2)).inputs(CountableIngredient.from(circuit, Tier.Elite)).outputs(GATileEntities.LARGE_ASSEMBLER.getStackForm()).duration(600).EUt(8000).buildAndRegister();
        ModHandler.addShapedRecipe("ga_boiling_water_thorium_reactor", GATileEntities.NUCLEAR_REACTOR.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', new UnificationEntry(block, Thorium), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_EV);
        ModHandler.addShapedRecipe("ga_boiling_water_uranium_reactor", GATileEntities.NUCLEAR_BREEDER.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(block, Uranium235), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        ModHandler.addShapedRecipe("ga_large_miner.basic", GATileEntities.LARGE_MINER[0].getStackForm(), "GCG", "IHI", "SCS", 'H', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'G', new UnificationEntry(gear, BlackSteel), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN, 'S', SENSOR_EV);
        ModHandler.addShapedRecipe("ga_large_miner.large", GATileEntities.LARGE_MINER[1].getStackForm(), "GCG", "IHI", "SCS", 'H', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'G', new UnificationEntry(gear, HSSG), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN, 'S', SENSOR_IV);
        ModHandler.addShapedRecipe("ga_large_miner.advance", GATileEntities.LARGE_MINER[2].getStackForm(), "GCG", "IHI", "SCS", 'H', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Master), 'G', new UnificationEntry(gear, HSSS), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN, 'S', SENSOR_LUV);
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(pipeMedium, Bronze), 'L', new UnificationEntry(pipeLarge, Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'R', new UnificationEntry(rotor, Bronze));
        if (GATileEntities.LARGE_CIRCUIT_ASSEMBLY_LINE != null)
            ModHandler.addShapedRecipe("ga_large_circuit_assembly", GATileEntities.LARGE_CIRCUIT_ASSEMBLY_LINE.getStackForm(), "CRC", "SAS", "CRC", 'A', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'R', MetaItems.ROBOT_ARM_LUV, 'C', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING), 'S', new UnificationEntry(circuit, Tier.Master));
        ModHandler.addShapedRecipe("ga_industrial_primitive_blast_furnace", GATileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.getStackForm(), "PFP", "FOF", "PFP", 'P', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS), 'F', OreDictNames.craftingFurnace, 'O', MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm());
        ModHandler.addShapedRecipe("ga_cryogenic_freezer", GATileEntities.CRYOGENIC_FREEZER.getStackForm(), "GCG", "IHI", "PCP", 'H', GATileEntities.VACUUM_FREEZER.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'P', new UnificationEntry(plateDense, GAMaterials.HG1223), 'G', new UnificationEntry(gear, GAMaterials.IncoloyMA956), 'I', MetaItems.ELECTRIC_PISTON_IV);
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30720).fluidInputs(Kanthal.getFluid(2304), Bronze.getFluid(9216), BabbittAlloy.getFluid(2304), SolderingAlloy.getFluid(L * 10)).inputs(CountableIngredient.from(circuit, Tier.Elite), CountableIngredient.from(circuit, Tier.Elite)).inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_IV, 2), GATileEntities.DISTILLATION_TOWER.getStackForm(2)).outputs(GATileEntities.ADVANCED_DISTILLATION_TOWER.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(Steel.getFluid(144 * 4)).input(OrePrefix.valueOf("gtMetalCasing"), Steel, 2).input(plate, Aluminium, 32).input(gear, Steel, 4).input(plate, CobaltBrass, 16).inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LV)).outputs(GATileEntities.CHEMICAL_PLANT.getStackForm()).duration(2400).EUt(120).buildAndRegister();
        ModHandler.addShapedRecipe("ga_large_forge_hammer", GATileEntities.LARGE_FORGE_HAMMER.getStackForm(), "PCP", "CHC", "PCP", 'H', MetaTileEntities.FORGE_HAMMER[0].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Basic), 'P', MetaItems.ELECTRIC_PISTON_MV);
        ModHandler.addShapedRecipe("ga_battery_tower", GATileEntities.BATTERY_TOWER.getStackForm(), "PCP", "CHC", "PCP", 'H', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'P', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Talonite));
        ModHandler.addShapedRecipe("ga_gas_centrifuge", GATileEntities.GAS_CENTRIFUGE.getStackForm(), "PCP", "CHC", "PDP", 'H', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'C', MetaTileEntities.THERMAL_CENTRIFUGE[GAValues.HV].getStackForm(), 'D', MetaTileEntities.CENTRIFUGE[GAValues.HV].getStackForm(), 'P', GAMetaBlocks.MOTOR_CASING.getItemVariant(MotorCasing.CasingType.MOTOR_HV));
        ModHandler.addShapedRecipe("ga_large_packager", GATileEntities.LARGE_PACKAGER.getStackForm(), "BCR", "PHU", "CMC", 'H', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Master), 'P', GAConfig.GT5U.highTierPackers ? GATileEntities.PACKER[GAValues.EV].getMetaTileEntity().getStackForm() : MetaTileEntities.PACKER[GAValues.EV - 1].getStackForm(), 'U', GAConfig.GT5U.highTierUnpackers ? GATileEntities.UNPACKER[GAValues.EV].getMetaTileEntity().getStackForm() : MetaTileEntities.UNPACKER[GAValues.EV - 1].getStackForm(), 'B' , MetaItems.CONVEYOR_MODULE_IV, 'R', MetaItems.ROBOT_ARM_IV, 'M', new UnificationEntry(plate, GAMaterials.HG1223));

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30720)
                .fluidInputs(Lubricant.getFluid(2304),
                        RP1RocketFuel.getFluid(9216),
                        SolderingAlloy.getFluid(L * 10))
                .inputs(CountableIngredient.from(circuit, Tier.Master),
                        CountableIngredient.from(circuit, Tier.Master),
                        CountableIngredient.from(circuit, Tier.Master),
                        CountableIngredient.from(circuit, Tier.Master))
                .input(wireGtQuadruple, IVSuperconductor, 64)
                .input(wireGtQuadruple, IVSuperconductor, 64)
                .inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_LUV, 2),
                        GATileEntities.ROCKET_GENERATOR[GTValues.LuV - 1].getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(16))
                .outputs(GATileEntities.LARGE_ROCKET_ENGINE.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                .input(OrePrefix.valueOf("gtMetalCasing"), ZirconiumCarbide, 2)
                .input(plate, Zirconium, 32)
                .input(gear, Staballoy, 4)
                .inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TIERED_HULL_EV))
                .outputs(GATileEntities.ALLOY_BLAST_FURNACE.getStackForm()).duration(2400).EUt(1920).buildAndRegister();


        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(130000)
                .fluidInputs(GAMaterials.HastelloyN.getFluid(144 * 4))
                .input(OrePrefix.valueOf("gtMetalCasing"), GAMaterials.Staballoy, 2)
                .inputs(MetaItems.SENSOR_UV.getStackForm(1))
                .inputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 64))
                .inputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 64))
                .inputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 64))
                .inputs(OreDictUnifier.get(wireGtSingle, UVSuperconductor, 64))
                .inputs(GATileEntities.LARGE_MINER[0].getStackForm())
                .inputs(GATileEntities.LARGE_MINER[1].getStackForm())
                .inputs(GATileEntities.LARGE_MINER[2].getStackForm())
                .inputs(CountableIngredient.from(circuit, Tier.Superconductor, 4))
                .outputs(GATileEntities.VOID_MINER[0].getStackForm()).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(500000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 27))
                .fluidInputs(Polyetheretherketone.getFluid(2592))
                .inputs(GAMetaItems.SENSOR_UHV.getStackForm(2))
                .inputs(GAMetaItems.ELECTRIC_MOTOR_UHV.getStackForm(8))
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .inputs(GATileEntities.VOID_MINER[0].getStackForm())
                .inputs(CountableIngredient.from(circuit, Tier.Infinite, 4))
                .input(gear, Incoloy813, 4)
                .input(screw, Incoloy813, 64)
                .input(screw, Incoloy813, 64)
                .input(plate, EnrichedNaquadahAlloy, 8)
                .input(plate, Ruridit, 8)
                .input(stick, EnrichedNaquadahAlloy, 16)
                .outputs(GATileEntities.VOID_MINER[1].getStackForm()).buildAndRegister();
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 27 * 2))
                .fluidInputs(Zylon.getFluid(2592))
                .inputs(GAMetaItems.SENSOR_UEV.getStackForm(2))
                .inputs(GAMetaItems.ELECTRIC_MOTOR_UEV.getStackForm(8))
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .inputs(GATileEntities.VOID_MINER[1].getStackForm())
                .inputs(CountableIngredient.from(circuit, UEV, 4))
                .input(gear, HastelloyX78, 4)
                .input(screw, Lafium, 64)
                .input(screw, Pikyonium, 64)
                .input(plate, HastelloyK243, 8)
                .input(stick, TitanSteel, 16)
                .outputs(GATileEntities.VOID_MINER[2].getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .fluidInputs(SolderingAlloy.getFluid(144 * 9))
                .inputs(SENSOR_ZPM.getStackForm(2))
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm(2))
                .inputs(ROBOT_ARM_ZPM.getStackForm(2))
                .inputs(EMITTER_ZPM.getStackForm(2))
                .input(plate, HSSS, 8)
                .input(screw, NaquadahEnriched, 16)
                .input(circuit, Tier.Ultimate, 8)
                .input(gear, HastelloyN, 8)
                .input(bolt, Enderium, 32)
                .input(screw, IncoloyMA956, 32)
                .input(plate, Nitinol60, 16)
                .outputs(GATileEntities.BIO_REACTOR.getStackForm())
                .EUt(122880)
                .duration(500)
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(SENSOR_LUV.getStackForm(2))
                .inputs(CONVEYOR_MODULE_LUV.getStackForm(2))
                .inputs(ROBOT_ARM_LUV.getStackForm(2))
                .inputs(EMITTER_LUV.getStackForm(2))
                .fluidInputs(SolderingAlloy.getFluid(9 * 144))
                .input(plate, Naquadah, 16)
                .input(gear, RhodiumPlatedPalladium, 8)
                .input(screw, ZirconiumCarbide, 32)
                .input(gear, Staballoy, 8)
                .input(screw, Grisium, 32)
                .input(circuit, Tier.Master, 4)
                .outputs(GATileEntities.LARGE_LASER_ENGRAVER.getStackForm())
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(24000000)
                .input(gear, Quantum, 12)
                .input(plateDense, TitanSteel, 9)
                .input(plate, Adamantium, 24)
                .input(foil, FullerenePolymerMatrix, 6)
                .inputs(SENSOR_UIV.getStackForm(4))
                .inputs(SCINTILLATOR.getStackForm(2))
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_3, 2))
                .fluidInputs(Cinobite.getFluid(864))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .outputs(GATileEntities.COSMIC_RAY_DETECTOR.getStackForm())
                .buildAndRegister();        
        ModHandler.addShapedRecipe("ga_large_transformer", GATileEntities.LARGE_TRANSFORMER.getStackForm(), "PPP", "IHO", "PPP", 'H', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'P', new UnificationEntry(plate, Aluminium), 'I', GATileEntities.ENERGY_INPUT_HATCH_4_AMPS.get(GTValues.MV).getStackForm(), 'O', GATileEntities.ENERGY_OUTPUT_HATCH_16_AMPS.get(GTValues.MV).getStackForm());
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(90000)
                .inputs(
                        MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        OreDictUnifier.get(plateDense, Naquadria, 4),
                        FIELD_GENERATOR_UV.getStackForm(2),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        OreDictUnifier.get(wireGtHex, UVSuperconductor, 32))
                .inputs(CountableIngredient.from(circuit, Tier.Infinite))
                .inputs(CountableIngredient.from(circuit, Tier.Infinite))
                .inputs(CountableIngredient.from(circuit, Tier.Infinite))
                .inputs(CountableIngredient.from(circuit, Tier.Infinite))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.LARGE_NAQUADAH_REACTOR.getStackForm()).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(30000)
                .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        OreDictUnifier.get(plate, Americium, 4),
                        OreDictUnifier.get(plate, NetherStar, 4),
                        FIELD_GENERATOR_IV.getStackForm(2),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32),
                        OreDictUnifier.get(wireGtSingle, LuVSuperconductor, 32))
                .input(circuit, Tier.Ultimate)
                .input(circuit, Tier.Ultimate)
                .input(circuit, Tier.Ultimate)
                .input(circuit, Tier.Ultimate)
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[0].getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(60000)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_2),
                        OreDictUnifier.get(plate, Rutherfordium, 4),
                        OreDictUnifier.get(plate, Curium.getMaterial(), 4),
                        FIELD_GENERATOR_LUV.getStackForm(2),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(48),
                        OreDictUnifier.get(wireGtDouble, ZPMSuperconductor, 32))
                .input(circuit, Tier.Superconductor)
                .input(circuit, Tier.Superconductor)
                .input(circuit, Tier.Superconductor)
                .input(circuit, Tier.Superconductor)
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[1].getStackForm()).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(90000)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_COIL_3),
                        OreDictUnifier.get(plate, Dubnium, 4),
                        OreDictUnifier.get(plate, Californium.getMaterial(), 4),
                        FIELD_GENERATOR_ZPM.getStackForm(2),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        OreDictUnifier.get(wireGtQuadruple, UVSuperconductor, 32))
                .input(circuit, Tier.Infinite)
                .input(circuit, Tier.Infinite)
                .input(circuit, Tier.Infinite)
                .input(circuit, Tier.Infinite)
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[2].getStackForm()).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(500000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 9))
                .input(plate, Tritanium, 8)
                .input(plate, Naquadria, 32)
                .input(screw, Naquadria, 64)
                .input(screw, Dubnium, 64)
                .input(foil, Polyetheretherketone, 64)
                .inputs(GATileEntities.LARGE_NAQUADAH_REACTOR.getStackForm())
                .inputs(UHPIC.getStackForm(16))
                .inputs(ELECTRIC_PUMP_UV.getStackForm(4))
                .inputs(FIELD_GENERATOR_UV.getStackForm(4))
                .input(circuit, Tier.Superconductor, 4)
                .outputs(GATileEntities.HYPER_REACTOR.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 9 * 4))
                .input(plate, Incoloy813, 32)
                .input(plate, EnrichedNaquadahAlloy, 32)
                .input(screw, Ruridit, 64)
                .input(stick, AbyssalAlloy, 16)
                .input(gear, TungstenTitaniumCarbide, 8)
                .input(circuit, Tier.Infinite, 4)
                .input(foil, Zylon, 64)
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_UHV.getStackForm(2))
                .inputs(GATileEntities.HYPER_REACTOR.getStackForm())
                .outputs(GATileEntities.HYPER_REACTOR_UHV.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 9 * 16))
                .input(plate, HastelloyX78, 32)
                .input(plate, HastelloyK243, 32)
                .input(screw, Zeron100, 64)
                .input(stick, TitanSteel, 16)
                .input(gear, Pikyonium, 8)
                .input(circuit, UEV, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .input(foil, Zylon, 64)
                .input(foil, Zylon, 64)
                .inputs(FIELD_GENERATOR_UEV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_UEV.getStackForm(2))
                .inputs(GATileEntities.HYPER_REACTOR_UHV.getStackForm())
                .outputs(GATileEntities.HYPER_REACTOR_UEV.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(144 * 9 * 16))
                .input(plate, Trinium, 32)
                .input(stick, HDCS, 16)
                .input(gear, TungstenTitaniumCarbide, 16)
                .input(screw, Incoloy813,32)
                .input(bolt, EnrichedNaquadahAlloy, 64)
                .input(circuit, Tier.Superconductor, 4)
                .inputs(SENSOR_UHV.getStackForm(4))
                .inputs(EMITTER_UHV.getStackForm(4))
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .outputs(GATileEntities.STELLAR_FORGE.getStackForm())
                .buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(250).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_UHV.getStackForm(2))
                .input(plate, Trinium, 8)
                .input(gear, TungstenTitaniumCarbide, 4)
                .input(screw, TungstenTitaniumCarbide, 16)
                .input(circuit, Tier.Superconductor, 2)
                .outputs(GATileEntities.PLASMA_CONDENSER.getStackForm())
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .input(frameGt, Trinium)
                .input(plate, HDCS, 6)
                .input(stick, EnrichedNaquadahAlloy, 4)
                .input(screw, Trinium, 8)
                .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.STELLAR_CONTAINMENT, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .input(frameGt, Naquadria)
                .input(screw, EnrichedNaquadahAlloy, 16)
                .input(plate, Incoloy813, 8)
                .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                .outputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CASING, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(2000000)
                .inputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CASING))
                .input(screw, Pikyonium, 16)
                .input(plate, TitanSteel, 8)
                .fluidInputs(SolderingAlloy.getFluid(144 * 4))
                .outputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CASING_2, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .inputs(SENSOR_UV.getStackForm())
                .inputs(EMITTER_UV.getStackForm())
                .input(frameGt, Naquadria, 4)
                .input(screw, Dubnium, 16)
                .input(plate, Naquadria, 4)
                .input(circuit, Tier.Superconductor)
                .outputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CORE, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(2000000)
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(EMITTER_UHV.getStackForm())
                .inputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CASING, 2))
                .input(screw, Rutherfordium, 16)
                .input(plate, TungstenTitaniumCarbide, 4)
                .input(circuit, Tier.Infinite)
                .outputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CORE_2, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8000000)
                .inputs(FIELD_GENERATOR_UEV.getStackForm(2))
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(EMITTER_UEV.getStackForm())
                .inputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CASING_2, 2))
                .input(screw, TriniumTitanium, 16)
                .input(plate, TitanSteel, 4)
                .input(circuit, UEV)
                .outputs(GAMetaBlocks.REACTOR_CASING.getItemVariant(GAReactorCasing.CasingType.HYPER_CORE_3, 4))
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(120000)
                .input(frameGt, HSSS)
                .input(plate, NaquadahAlloy, 4)
                .input(screw, Dubnium, 4)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(GAMultiblockCasing2.CasingType.BIO_REACTOR, 2))
                .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(3000).EUt(480).fluidInputs(SolderingAlloy.getFluid(GTValues.L * 10)).inputs(ELECTRIC_PUMP_HV.getStackForm(2), MetaTileEntities.PUMP[2].getStackForm()).input(circuit, Tier.Advanced, 2).input(GAEnums.GAOrePrefix.gtMetalCasing, StainlessSteel, 5).outputs(GATileEntities.DRILLING_RIG.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("ga_solar_sampler", GATileEntities.SOLAR_FLUID_SAMPLER.getStackForm(), "GGG", "PCP", "EDE", 'G', new ItemStack(Blocks.GLASS, 1), 'P', new UnificationEntry(plate, StainlessSteel), 'C', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'D', new UnificationEntry(toolHeadDrill, StainlessSteel), 'E', new UnificationEntry(cableGtSingle, Gold));

        OrePrefix roundOrScrew;
        if (GAConfig.GT6.addRounds)
            roundOrScrew = GAEnums.GAOrePrefix.round;
        else
            roundOrScrew = screw;
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(6000000).fluidInputs(SolderingAlloy.getFluid(GTValues.L * 10)).inputs(FIELD_GENERATOR_UEV.getStackForm(2), ROBOT_ARM_UEV.getStackForm()).input(circuit, UEV, 7).input(frameGt, Bohrium, 5).outputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(GAQuantumCasing.CasingType.COMPUTER, 3)).buildAndRegister();
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(8000000)
                .input(frameGt, Bohrium, 16)
                .input(plate, Bohrium, 16)
                .input(gearSmall, Bohrium, 16)
                .input(roundOrScrew, Bohrium, 16)
                .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(GAQuantumCasing.CasingType.COMPUTER, 4))
                .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(GAQuantumCasing.CasingType.COMPUTER, 4))
                .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(GAQuantumCasing.CasingType.COMPUTER, 4))
                .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(GAQuantumCasing.CasingType.COMPUTER, 4))
                .inputs(ROBOT_ARM_UEV.getStackForm(2))
                .inputs(FIELD_GENERATOR_UEV.getStackForm(2))
                .input(wireGtHex, BlackTitanium, 16)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(Zylon.getFluid(L * 20))
                .outputs(GATileEntities.QUBIT_COMPUTER.getStackForm()).buildAndRegister();

        List<Recipe> removals = new ArrayList<>();

        for (Recipe r : RecipeMaps.ASSEMBLER_RECIPES.getRecipeList()) {
            for (ItemStack s : r.getOutputs()) {
                if (s.getItem().getUnlocalizedNameInefficiently(s).contains("large_boiler")) {
                    removals.add(r);
                    break;
                }
            }
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm()).inputs(CountableIngredient.from(plate, Steel, 2), CountableIngredient.from(circuit, Tier.Advanced, 2)).outputs(MetaTileEntities.LARGE_STEEL_BOILER.getStackForm()).EUt(120).duration(600).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(MetaTileEntities.LARGE_STEEL_BOILER.getStackForm()).inputs(CountableIngredient.from(plate, Titanium, 2), CountableIngredient.from(circuit, Tier.Advanced, 2)).outputs(MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm()).EUt(500).duration(600).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm()).inputs(CountableIngredient.from(plate, TungstenSteel, 2), CountableIngredient.from(circuit, Tier.Advanced, 2)).outputs(MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm()).EUt(2000).duration(600).buildAndRegister();

        String plateB = new String("plate");
        if (GAConfig.GT6.addCurvedPlates) {
            plateB = "plateCurved";
        }

        //Storage
        if (GAConfig.GT6.registerDrums) {
            ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', "slimeball", 'P', "plankWood", 'R', "stickLongIron");
        }
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders && GAConfig.GT6.registerDrums) {
            ModHandler.addShapedRecipe("bronze_drum", GATileEntities.BRONZE_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', plateB + "Bronze", 'R', "stickLongBronze");
            ModHandler.addShapedRecipe("steel_drum", GATileEntities.STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', plateB + "Steel", 'R', "stickLongSteel");
            ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', plateB + "StainlessSteel", 'R', "stickLongStainlessSteel");
            ModHandler.addShapedRecipe("titanium_drum", GATileEntities.TITANIUM_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', plateB + "Titanium", 'R', "stickLongTitanium");
            ModHandler.addShapedRecipe("tungstensteel_drum", GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', plateB + "TungstenSteel", 'R', "stickLongTungstenSteel");
        } else if (!(GAConfig.GT6.BendingCurvedPlates || GAConfig.GT6.BendingCylinders) && GAConfig.GT6.registerDrums) {
            ModHandler.addShapedRecipe("bronze_drum", GATileEntities.BRONZE_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateBronze", 'R', "stickLongBronze");
            ModHandler.addShapedRecipe("steel_drum", GATileEntities.STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateSteel", 'R', "stickLongSteel");
            ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateStainlessSteel", 'R', "stickLongStainlessSteel");
            ModHandler.addShapedRecipe("titanium_drum", GATileEntities.TITANIUM_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateTitanium", 'R', "stickLongTitanium");
            ModHandler.addShapedRecipe("tungstensteel_drum", GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateTungstenSteel", 'R', "stickLongTungstenSteel");
        }
        if (GAConfig.Misc.registerCrates) {
            ModHandler.addShapedRecipe("wooden_crate", GATileEntities.WOODEN_CRATE.getStackForm(), "RPR", "PsP", "RPR", 'P', "plankWood", 'R', "screwIron");
            ModHandler.addShapedRecipe("bronze_crate", GATileEntities.BRONZE_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateBronze", 'R', "stickLongBronze");
            ModHandler.addShapedRecipe("steel_crate", GATileEntities.STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateSteel", 'R', "stickLongSteel");
            ModHandler.addShapedRecipe("stainless_steel_crate", GATileEntities.STAINLESS_STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateStainlessSteel", 'R', "stickLongStainlessSteel");
            ModHandler.addShapedRecipe("titanium_crate", GATileEntities.TITANIUM_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateTitanium", 'R', "stickLongTitanium");
            ModHandler.addShapedRecipe("tungstensteel_crate", GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateTungstenSteel", 'R', "stickLongTungstenSteel");
        }

        //Generators
        registerMachineRecipe(GATileEntities.NAQUADAH_REACTOR, "RCR", "FMF", "QCQ", 'M', HULL, 'Q', CABLE_QUAD, 'C', BETTER_CIRCUIT, 'F', FIELD_GENERATOR, 'R', STICK_RADIOACTIVE);
        registerMachineRecipe(GATileEntities.ROCKET_GENERATOR, "PCP", "MHM", "GAG", 'C', CIRCUIT, 'M', MOTOR, 'H', HULL, 'G', PLATE_DENSE, 'A', CABLE_DOUBLE, 'P', PISTON);
        ModHandler.addShapedRecipe("ga_diesel_generator_lv", MetaTileEntities.DIESEL_GENERATOR[0].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_LV, 'E', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin), 'G', new UnificationEntry(gear, Steel));
        ModHandler.addShapedRecipe("ga_diesel_generator_mv", MetaTileEntities.DIESEL_GENERATOR[1].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_MV, 'E', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper), 'G', new UnificationEntry(gear, Aluminium));
        ModHandler.addShapedRecipe("ga_diesel_generator_hv", MetaTileEntities.DIESEL_GENERATOR[2].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_HV, 'E', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold), 'G', new UnificationEntry(gear, StainlessSteel));
        ModHandler.addShapedRecipe("ga_gas_turbine_lv", MetaTileEntities.GAS_TURBINE[0].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(rotor, Tin), 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin));
        ModHandler.addShapedRecipe("ga_gas_turbine_mv", MetaTileEntities.GAS_TURBINE[1].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(rotor, Bronze), 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper));
        ModHandler.addShapedRecipe("ga_gas_turbine_hv", MetaTileEntities.GAS_TURBINE[2].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(rotor, Steel), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold));
        ModHandler.addShapedRecipe("ga_steam_turbine_lv", MetaTileEntities.STEAM_TURBINE[0].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(rotor, Tin), 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin), 'P', new UnificationEntry(pipeMedium, Bronze));
        ModHandler.addShapedRecipe("ga_steam_turbine_mv", MetaTileEntities.STEAM_TURBINE[1].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(rotor, Bronze), 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper), 'P', new UnificationEntry(pipeMedium, Steel));
        ModHandler.addShapedRecipe("ga_steam_turbine_hv", MetaTileEntities.STEAM_TURBINE[2].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(rotor, Steel), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold), 'P', new UnificationEntry(pipeMedium, StainlessSteel));
        ModHandler.addShapedRecipe("ga_magic_energy_absorber", MetaTileEntities.MAGIC_ENERGY_ABSORBER.getStackForm(), "PCP", "PMP", "PCP", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'P', MetaItems.SENSOR_EV, 'C', new UnificationEntry(circuit, Tier.Elite));
        if (MetaTileEntities.MAGIC_ENERGY_ABSORBER != null) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:magic_energy_absorber"));
            ModHandler.addShapedRecipe("ga_magic_energy_absorber", MetaTileEntities.MAGIC_ENERGY_ABSORBER.getStackForm(), "PCP", "SMS", "PCP", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'P', MetaItems.ELECTRIC_PUMP_MV, 'S', MetaItems.SENSOR_MV, 'C', new UnificationEntry(circuit, Tier.Good));
        }

        ModHandler.addShapedRecipe("ga_simple_ore_washer", GATileEntities.SIMPLE_ORE_WASHER.getStackForm(), "PIP", "PTP", "PCP", 'C', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'T', MetaItems.ELECTRIC_PUMP_LV, 'I', new UnificationEntry(plate, Steel), 'P', new UnificationEntry(pipeLarge, Bronze));

        ModHandler.addShapedRecipe("buffer_lv", GATileEntities.BUFFER[0].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(1), 'H', HULL.getIngredient(1), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("buffer_mv", GATileEntities.BUFFER[1].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(2), 'H', HULL.getIngredient(2), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("buffer_hv", GATileEntities.BUFFER[2].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(3), 'H', HULL.getIngredient(3), 'C', OreDictNames.chestWood);

        //Machines
        registerMachineRecipe(GATileEntities.CIRCUITASSEMBLER, "ACE", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', BETTER_CIRCUIT, 'W', CABLE_SINGLE, 'E', EMITTER);
        registerMachineRecipe(GATileEntities.CLUSTERMILL, "MMM", "CHC", "MMM", 'M', MOTOR, 'C', CIRCUIT, 'H', HULL);
        registerMachineRecipe(MetaTileEntities.ALLOY_SMELTER, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE_SINGLE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.BENDER, "PwP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        registerMachineRecipe(MetaTileEntities.ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE_SINGLE, 'C', COIL_HEATING);
        registerMachineRecipe(MetaTileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.EXTRUDER, "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON, 'E', CIRCUIT, 'P', PIPE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'D', DIAMOND);
        registerMachineRecipe(MetaTileEntities.MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GRINDER);
        registerMachineRecipe(MetaTileEntities.MICROWAVE, "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR, 'R', EMITTER, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'L', new UnificationEntry(plate, Lead));
        registerMachineRecipe(MetaTileEntities.WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'I', WIRE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.THERMAL_CENTRIFUGE, "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'O', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.PACKER, "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.UNPACKER, "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.LASER_ENGRAVER, "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'A', OreDictNames.craftingAnvil);
        registerMachineRecipe(MetaTileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F', MetaItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE_SINGLE);
        registerMachineRecipe(MetaTileEntities.ARC_FURNACE, "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'G', new UnificationEntry(ingot, Graphite));
        registerMachineRecipe(MetaTileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));
        registerMachineRecipe(MetaTileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);
        registerMachineRecipe(MetaTileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
        registerMachineRecipe(GATileEntities.WORLD_ACCELERATOR, "ABC", "DHE", "FGI", 'H', HULL, 'A', PISTON, 'B', ROBOT_ARM, 'C', PUMP, 'D', MOTOR, 'E', CONVEYOR, 'F', EMITTER, 'G', SENSOR, 'I', FIELD_GENERATOR);
        registerMachineRecipe(GATileEntities.MINER, "WPW", "CMC", "SPS", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', MetaItems.COMPONENT_GRINDER_DIAMOND, 'S', SENSOR);
        registerMachineRecipe(GATileEntities.DEHYDRATOR, "WCW", "MHM", "GAG", 'C', CIRCUIT, 'M', CABLE_QUAD, 'H', HULL, 'G', GEAR, 'A', ROBOT_ARM, 'W', COIL_HEATING_DOUBLE);
        registerMachineRecipe(GATileEntities.DECAY_CHAMBER, "RCR", "FMF", "QCQ", 'M', HULL, 'Q', CABLE_DOUBLE, 'C', CIRCUIT, 'F', FIELD_GENERATOR, 'R', STICK_RADIOACTIVE);
        registerMachineRecipe(GATileEntities.GREEN_HOUSE, "GGG", "AMA", "CQC", 'M', HULL, 'Q', CABLE_SINGLE, 'C', CIRCUIT, 'G', GLASS, 'A', ROBOT_ARM);
        registerMachineRecipe(GATileEntities.ROCK_BREAKER, "CPC", "CMC", "GGG", 'M', HULL, 'C', PIPE, 'G', GLASS, 'P', PISTON);
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
        registerMachineRecipe(GATileEntities.MASS_FAB, "CFC", "QMQ", "CFC", 'M', HULL, 'Q', CABLE_QUAD, 'C', BETTER_CIRCUIT, 'F', FIELD_GENERATOR);
        registerMachineRecipe(GATileEntities.REPLICATOR, "EFE", "CMC", "EQE", 'M', HULL, 'Q', CABLE_QUAD, 'C', BETTER_CIRCUIT, 'F', FIELD_GENERATOR, 'E', EMITTER);
        if (GAConfig.Misc.highTierCollector)
            registerMachineRecipe(GATileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
        for (final EnergyConverterType type : EnergyConverterType.values()) {
            if (GATileEntities.ENERGY_CONVERTER.containsKey(type)) {
                GATileEntities.ENERGY_CONVERTER.get(type).forEach(EnergyConverterCraftingHelper.HELPER.logic(type));
            }
        }

        GATileEntities.TRANSFORMER_1_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(), "KBB", "CM ", "KBB", 'M', WORSE_HULL.getIngredient(tier), 'C', CABLE_DOUBLE.getIngredient(tier), 'B', CABLE_DOUBLE.getIngredient(tier - 1), 'K', MetaItems.SMALL_COIL);
        });
        GATileEntities.TRANSFORMER_4_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(), "KBB", "CM ", "KBB", 'M', HULL.getIngredient(tier), 'C', CABLE_DOUBLE.getIngredient(tier), 'B', CABLE_DOUBLE.getIngredient(tier - 1), 'K', MetaItems.SMALL_COIL);
        });
        GATileEntities.TRANSFORMER_8_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(), "KBB", "CM ", "KBB", 'M', HULL.getIngredient(tier), 'C', CABLE_QUAD.getIngredient(tier), 'B', CABLE_QUAD.getIngredient(tier - 1), 'K', MetaItems.SMALL_COIL);
        });
        GATileEntities.TRANSFORMER_12_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(), "KBB", "CM ", "KBB", 'M', HULL.getIngredient(tier), 'C', CABLE_OCTAL.getIngredient(tier), 'B', CABLE_OCTAL.getIngredient(tier - 1), 'K', MetaItems.SMALL_COIL);
        });
        GATileEntities.TRANSFORMER_16_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(), "KBB", "CM ", "KBB", 'M', HULL.getIngredient(tier), 'C', CABLE_HEX.getIngredient(tier), 'B', CABLE_HEX.getIngredient(tier - 1), 'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.ENERGY_INPUT_HATCH_4_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(), "CM ", 'M', HULL.getIngredient(tier), 'C', CABLE_DOUBLE.getIngredient(tier));
        });
        GATileEntities.ENERGY_INPUT_HATCH_16_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(), "CM ", 'M', HULL.getIngredient(tier), 'C', CABLE_QUAD.getIngredient(tier));
        });
        GATileEntities.ENERGY_INPUT_HATCH_64_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(), "CM ", 'M', HULL.getIngredient(tier), 'C', CABLE_OCTAL.getIngredient(tier));
        });
        GATileEntities.ENERGY_INPUT_HATCH_128_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(), "CM ", 'M', HULL.getIngredient(tier), 'C', CABLE_HEX.getIngredient(tier));
        });

        GATileEntities.ENERGY_OUTPUT_HATCH_16_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(), " MC", 'M', HULL.getIngredient(tier), 'C', CABLE_DOUBLE.getIngredient(tier));
        });
        GATileEntities.ENERGY_OUTPUT_HATCH_32_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(), " MC", 'M', HULL.getIngredient(tier), 'C', CABLE_QUAD.getIngredient(tier));
        });
        GATileEntities.ENERGY_OUTPUT_HATCH_64_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(), " MC", 'M', HULL.getIngredient(tier), 'C', CABLE_OCTAL.getIngredient(tier));
        });
        GATileEntities.ENERGY_OUTPUT_HATCH_128_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(), " MC", 'M', HULL.getIngredient(tier), 'C', CABLE_HEX.getIngredient(tier));
        });
        GATileEntities.DIODES.forEach(diode -> {
            int tier = diode.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", diode.getMetaName()), diode.getStackForm(), "CCC", "XMX", "CCC", 'M', HULL.getIngredient(tier), 'C', CABLE_SINGLE.getIngredient(tier), 'X', SMALL_COIL);
        });

        GATileEntities.DISASSEMBLER.forEach(disassembler -> {
            int tier = disassembler.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", disassembler.getMetaName()), disassembler.getStackForm(), "RSV", "PMV", "ICI",
                    'M', HULL.getIngredient(tier), 'C', CABLE_SINGLE.getIngredient(tier), 'R', ROBOT_ARM.getIngredient(tier), 'P', PUMP.getIngredient(tier),
                    'S', SENSOR.getIngredient(tier), 'V', CONVEYOR.getIngredient(tier), 'I', CIRCUIT.getIngredient(tier));
        });

        ModHandler.addShapedRecipe("ga_steam_grinder", GATileEntities.STEAM_GRINDER.getStackForm(), "CGC", "CFC", "CGC", 'G', new UnificationEntry(gear, Potin), 'F', MetaTileEntities.STEAM_MACERATOR_BRONZE.getStackForm(), 'C', new UnificationEntry(GAEnums.GAOrePrefix.gtMetalCasing, Bronze));
        ModHandler.addShapedRecipe("ga_steam_hatch", GATileEntities.STEAM_HATCH.getStackForm(), "BPB", "BTB", "BPB", 'B', new UnificationEntry(plate, Bronze), 'P', new UnificationEntry(pipeMedium, Bronze), 'T', MetaTileEntities.BRONZE_TANK.getStackForm());
        ModHandler.addShapedRecipe("ga_steam_input_bus", GATileEntities.STEAM_INPUT_BUS.getStackForm(), "BMB", "THT", "BMB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        ModHandler.addShapedRecipe("ga_steam_output_bus", GATileEntities.STEAM_OUTPUT_BUS.getStackForm(), "BTB", "MHM", "BTB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        ModHandler.addShapedRecipe("ga_steam_oven", GATileEntities.STEAM_OVEN.getStackForm(), "CGC", "FMF", "CGC", 'F', MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(BlockFireboxCasing.FireboxCasingType.BRONZE_FIREBOX), 'C', new UnificationEntry(GAEnums.GAOrePrefix.gtMetalCasing, Bronze), 'M', MetaTileEntities.STEAM_FURNACE_BRONZE.getStackForm(), 'G', new UnificationEntry(gear, Invar));

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GATileEntities.IMPLOSION_COMPRESSOR.getStackForm())
                .input(block, Americium, 2)
                .input(stickLong, Osmium, 64)
                .input(ring, Osmium, 64)
                .input(wireGtSingle, UVSuperconductor, 16)
                .inputs(ELECTRIC_PISTON_UV.getStackForm(16))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .fluidInputs(Osmium.getFluid(1440))
                .fluidInputs(Americium.getFluid(1440))
                .outputs(GATileEntities.ELECTRIC_IMPLOSION.getStackForm())
                .EUt(491520)
                .duration(48000)
                .buildAndRegister();
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaName()), metaTileEntity.getStackForm(), prepareRecipe(metaTileEntity.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    public static void registerMachineRecipe(GATileEntities.MTE<?>[] metaTileEntities, Object... recipe) {
        for (GATileEntities.MTE<?> metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaTileEntity().getMetaName()), metaTileEntity.getMetaTileEntity().getStackForm(), prepareRecipe(metaTileEntity.getITieredMetaTileEntity().getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof GACraftingComponents) {
                recipe[i] = ((GACraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }
}
