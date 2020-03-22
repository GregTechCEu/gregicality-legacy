package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.ceu.utils.CeuCraftingHelper;
import gregicadditions.machines.ceu.utils.CeuType;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregicadditions.GAMaterials.Stellite;
import static gregicadditions.machines.GATileEntities.THERMAL_CENTRIFUGE;
import static gregicadditions.recipes.GACraftingComponents.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MachineCraftingRecipes {

    private static String[] tiers = {"lv", "mv", "hv", "ev"};

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
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.polarizor." + tier));
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
        //ModHandler.removeRecipeByName(new ResourceLocation("gregtech:distillation_tower"));
        //ModHandler.removeRecipeByName(new ResourceLocation("gregtech:cracking_unit"));
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
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_ulv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_ev"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_iv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_luv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_ev"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_iv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_luv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_max"));

        //Power Manipulation Machines
        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();
        ModHandler.addShapedRecipe("ga_charger_ulv", MetaTileEntities.CHARGER[GTValues.ULV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.ULV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Lead), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_ULV_TANTALUM, 'C', new UnificationEntry(circuit, Tier.Primitive));
        ModHandler.addShapedRecipe("ga_charger_lv", MetaTileEntities.CHARGER[GTValues.LV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tin), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_LV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Basic));
        ModHandler.addShapedRecipe("ga_charger_mv", MetaTileEntities.CHARGER[GTValues.MV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Copper), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_MV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Good));
        ModHandler.addShapedRecipe("ga_charger_hv", MetaTileEntities.CHARGER[GTValues.HV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Gold), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_HV_LITHIUM, 'C', new UnificationEntry(circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("ga_charger_ev", MetaTileEntities.CHARGER[GTValues.EV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Aluminium), 'T', OreDictNames.chestWood, 'B', MetaItems.LAPOTRON_CRYSTAL, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme));
        ModHandler.addShapedRecipe("ga_charger_iv", MetaTileEntities.CHARGER[GTValues.IV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tungsten), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB, 'C', new UnificationEntry(circuit, Tier.Elite));
        ModHandler.addShapedRecipe("ga_charger_luv", MetaTileEntities.CHARGER[GTValues.LuV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, VanadiumGallium), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB2, 'C', new UnificationEntry(circuit, Tier.Master));
        ModHandler.addShapedRecipe("ga_charger_zpm", MetaTileEntities.CHARGER[GTValues.ZPM].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), 'W', new UnificationEntry(wireGtHex, Naquadah), 'T', OreDictNames.chestWood, 'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE : MetaItems.ENERGY_LAPOTRONIC_ORB2, 'C', new UnificationEntry(circuit, Tier.Ultimate));
        ModHandler.addShapedRecipe("ga_charger_uv", MetaTileEntities.CHARGER[GTValues.UV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'W', new UnificationEntry(wireGtHex, NaquadahAlloy), 'T', OreDictNames.chestWood, 'B', GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_CLUSTER : last_bat, 'C', new UnificationEntry(circuit, Tier.Superconductor));
        ModHandler.addShapedRecipe("ga_charger_max", MetaTileEntities.CHARGER[GTValues.MAX].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'W', new UnificationEntry(wireGtHex, Tier.Superconductor), 'T', OreDictNames.chestWood, 'B', last_bat, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite));

        ModHandler.addShapedRecipe("ga_transformer_ev", MetaTileEntities.TRANSFORMER[GTValues.EV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Aluminium), 'B', new UnificationEntry(cableGtSingle, Gold), 'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_iv", MetaTileEntities.TRANSFORMER[GTValues.IV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Tungsten), 'B', new UnificationEntry(cableGtSingle, Aluminium), 'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_luv", MetaTileEntities.TRANSFORMER[GTValues.LuV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'B', new UnificationEntry(cableGtSingle, Tungsten), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_zpm", MetaTileEntities.TRANSFORMER[GTValues.ZPM - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Naquadah), 'B', new UnificationEntry(cableGtSingle, VanadiumGallium), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_uv", MetaTileEntities.TRANSFORMER[GTValues.UV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), 'C', new UnificationEntry(wireGtQuadruple, NaquadahAlloy), 'B', new UnificationEntry(cableGtSingle, Naquadah), 'K', MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT);
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

        //MultiBlocks
        ModHandler.addShapedRecipe("ga_primitive_blast_furnace", MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(), "hRS", "PBR", "dRS", 'R', OreDictUnifier.get(stick, Iron), 'S', OreDictUnifier.get(screw, Iron), 'P', "plateIron", 'B', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS));
        ModHandler.addShapedRecipe("ga_electric_blast_furnace", MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), "FFF", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Invar), 'F', OreDictNames.craftingFurnace, 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin));
        ModHandler.addShapedRecipe("ga_vacuum_freezer", MetaTileEntities.VACUUM_FREEZER.getStackForm(), "PPP", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Aluminium), 'P', MetaItems.ELECTRIC_PUMP_HV, 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme), 'W', new UnificationEntry(cableGtSingle, Gold));
        ModHandler.addShapedRecipe("ga_implosion_compressor", MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm(), "OOO", "CMC", "WCW", 'M', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Steel), 'O', new UnificationEntry(stone, Obsidian), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Aluminium));
        ModHandler.addShapedRecipe("ga_pyrolyse_oven", MetaTileEntities.PYROLYSE_OVEN.getStackForm(), "WEP", "EME", "WCP", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', MetaItems.ELECTRIC_PISTON_MV, 'P', new UnificationEntry(wireGtQuadruple, Cupronickel), 'E', new UnificationEntry(circuit, Tier.Good), 'C', MetaItems.ELECTRIC_PUMP_MV);
        ModHandler.addShapedRecipe("ga_diesel_engine", MetaTileEntities.DIESEL_ENGINE.getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_EV, 'E', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(circuit, Tier.Elite), 'W', new UnificationEntry(wireGtSingle, TungstenSteel), 'G', new UnificationEntry(gear, Titanium));
        ModHandler.addShapedRecipe("ga_engine_intake_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING), "PhP", "RFR", "PwP", 'R', new UnificationEntry(pipeMedium, Titanium), 'F', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Titanium), 'P', new UnificationEntry(rotor, Titanium));
        ModHandler.addShapedRecipe("ga_multi_furnace", MetaTileEntities.MULTI_FURNACE.getStackForm(), "PPP", "ASA", "CAC", 'P', Blocks.FURNACE, 'A', new UnificationEntry(circuit, Tier.Advanced), 'S', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Invar), 'C', new UnificationEntry(cableGtSingle, AnnealedCopper));
        ModHandler.addShapedRecipe("ga_large_steam_turbine", MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, Steel), 'P', new UnificationEntry(circuit, Tier.Advanced), 'A', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, Steel));
        ModHandler.addShapedRecipe("ga_large_high_pressure_steam_turbine", GATileEntities.LARGE_HIGH_PRESSURE_STEAM_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, Stellite), 'P', new UnificationEntry(circuit, Tier.Advanced), 'A', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, Ultimet));
        ModHandler.addShapedRecipe("ga_large_gas_turbine", MetaTileEntities.LARGE_GAS_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, StainlessSteel), 'P', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme), 'A', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, StainlessSteel));
        ModHandler.addShapedRecipe("ga_large_plasma_turbine", MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(gear, TungstenSteel), 'P', new UnificationEntry(circuit, Tier.Master), 'A', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', OreDictUnifier.get(pipeLarge, TungstenSteel));
        ModHandler.addShapedRecipe("ga_large_bronze_boiler", MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Tin), 'S', new UnificationEntry(circuit, Tier.Basic), 'A', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS));
        ModHandler.addShapedRecipe("ga_large_steel_boiler", MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Copper), 'S', new UnificationEntry(circuit, Tier.Advanced), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Steel));
        ModHandler.addShapedRecipe("ga_large_titanium_boiler", MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Gold), 'S', new UnificationEntry(circuit, Tier.Elite), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), Titanium));
        ModHandler.addShapedRecipe("ga_large_tungstensteel_boiler", MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(cableGtSingle, Aluminium), 'S', new UnificationEntry(circuit, Tier.Master), 'A', new UnificationEntry(OrePrefix.valueOf("gtMetalCasing"), TungstenSteel));
        ModHandler.addShapedRecipe("ga_assline", GATileEntities.ASSEMBLY_LINE.getStackForm(), "CRC", "SAS", "CRC", 'A', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING), 'S', new UnificationEntry(circuit, Tier.Elite));
        ModHandler.addShapedRecipe("ga_processing_array", GATileEntities.PROCESSING_ARRAY.getStackForm(), "CBC", "RHR", "CDC", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C', new UnificationEntry(circuit, Tier.Elite), 'B', MetaItems.ENERGY_LAPOTRONIC_ORB, 'D', MetaItems.TOOL_DATA_ORB);
        ModHandler.addShapedRecipe("ga_large_thermal_centrifuge", GATileEntities.LARGE_THERMAL_CENTRIFUGE.getStackForm(), "CBC", "RHR", "CDC", 'H', GATileEntities.THERMAL_CENTRIFUGE[4].getStackForm(), 'R', new UnificationEntry(stick, RedSteel), 'B', new UnificationEntry(circuit, Tier.Extreme), 'C', new UnificationEntry(plate, RedSteel), 'D', new UnificationEntry(gear, RedSteel));
        ModHandler.addShapedRecipe("ga_large_bender_and_forming", GATileEntities.LARGE_BENDER_AND_FORMING.getStackForm(), "CBC", "RHR", "CBC", 'H', MetaTileEntities.BENDER[3].getStackForm(), 'R', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Advanced), 'C', new UnificationEntry(plate, Titanium));
        ModHandler.addShapedRecipe("ga_large_centrifuge", GATileEntities.LARGE_CENTRIFUGE.getStackForm(), "CBC", "RHR", "DED", 'H', MetaTileEntities.CENTRIFUGE[3].getStackForm(), 'E', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'B', new UnificationEntry(pipeLarge, StainlessSteel), 'D', new UnificationEntry(plate, GAMaterials.Tumbaga), 'R', new UnificationEntry(plate, Titanium));
        ModHandler.addShapedRecipe("ga_large_chemical_reactor", GATileEntities.LARGE_CHEMICAL_REACTOR.getStackForm(), "DBD", "CHC", "DED", 'H', GATileEntities.CHEMICAL_REACTOR[4].getStackForm(), 'E', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'B', new UnificationEntry(pipeLarge, StainlessSteel), 'D', GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.CHEMICALLY_INERT));
        ModHandler.addShapedRecipe("ga_large_cutting", GATileEntities.LARGE_CUTTING.getStackForm(), "DBD", "CHC", "DED", 'H', GATileEntities.CUTTER[4].getStackForm(), 'C', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Advanced), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.MaragingSteel250));
        ModHandler.addShapedRecipe("ga_large_electrolyzer", GATileEntities.LARGE_ELECTROLYZER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.ELECTROLYZER[3].getStackForm(), 'C', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Extreme), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, Stellite));
        ModHandler.addShapedRecipe("ga_large_extruder", GATileEntities.LARGE_EXTRUDER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.EXTRUDER[2].getStackForm(), 'C', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Extreme), 'E', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.Inconel625));
        ModHandler.addShapedRecipe("ga_large_macerator", GATileEntities.LARGE_MACERATOR.getStackForm(), "DBD", "CHC", "DED", 'C', MetaTileEntities.MACERATOR[2].getStackForm(), 'E', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'H', new UnificationEntry(circuit, Tier.Master), 'B', MetaTileEntities.MACERATOR[3].getStackForm(), 'D', new UnificationEntry(plate, TungstenCarbide));
        ModHandler.addShapedRecipe("ga_large_mixer", GATileEntities.LARGE_MIXER.getStackForm(), "DED", "CHC", "DED", 'C', new UnificationEntry(plate, TungstenCarbide), 'E', new UnificationEntry(circuit, Tier.Extreme), 'H', MetaTileEntities.MIXER[3].getStackForm(), 'D', new UnificationEntry(plate, GAMaterials.Staballoy));
        ModHandler.addShapedRecipe("ga_large_multi_use", GATileEntities.LARGE_MULTI_USE.getStackForm(), "ABC", "DED", "GHI", 'A', MetaTileEntities.COMPRESSOR[3].getStackForm(), 'B', MetaTileEntities.LATHE[3].getStackForm(), 'C', MetaTileEntities.POLARIZER[3].getStackForm(), 'D', new UnificationEntry(plate, GAMaterials.Staballoy), 'E', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'G', MetaTileEntities.FERMENTER[3].getStackForm(), 'H', MetaTileEntities.LASER_ENGRAVER[3].getStackForm(), 'I', MetaTileEntities.EXTRACTOR[3].getStackForm());
        ModHandler.addShapedRecipe("ga_large_sifter", GATileEntities.LARGE_SIFTER.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.SIFTER[3].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Good), 'E', new UnificationEntry(circuit, Tier.Advanced), 'C', new UnificationEntry(cableGtSingle, Gold), 'D', new UnificationEntry(plate, GAMaterials.EglinSteel));
        ModHandler.addShapedRecipe("ga_large_washing_plant", GATileEntities.LARGE_WASHING_PLANT.getStackForm(), "DBD", "CHC", "DED", 'H', MetaTileEntities.ORE_WASHER[3].getStackForm(), 'B', new UnificationEntry(circuit, Tier.Good), 'E', new UnificationEntry(circuit, Tier.Good), 'C', new UnificationEntry(plate, GAMaterials.Talonite), 'D', new UnificationEntry(plate, GAMaterials.Grisium));
        ModHandler.addShapedRecipe("ga_large_wiremill", GATileEntities.LARGE_WIREMILL.getStackForm(), "DED", "CHC", "DED", 'H', MetaTileEntities.WIREMILL[3].getStackForm(), 'E', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'D', new UnificationEntry(plate, GAMaterials.MaragingSteel250));
        ModHandler.addShapedRecipe("ga_volcanus", GATileEntities.VOLCANUS.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().fluidInputs(GAMaterials.HastelloyN.getFluid(144 * 4)).input(OrePrefix.valueOf("gtMetalCasing"), GAMaterials.Staballoy, 2).inputs(MetaItems.EMITTER_LUV.getStackForm(2), MetaItems.SENSOR_LUV.getStackForm(2)).inputs(CountableIngredient.from(circuit, Tier.Elite)).outputs(GATileEntities.LARGE_ASSEMBLER.getStackForm()).duration(600).EUt(8000).buildAndRegister();
        ModHandler.addShapedRecipe("ga_boiling_water_thorium_reactor", GATileEntities.BOILING_WATER_THORIUM_REACTOR.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(block, Thorium), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_EV);
        ModHandler.addShapedRecipe("ga_boiling_water_uranium_reactor", GATileEntities.BOILING_WATER_URANIUM_REACTOR.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(block, Uranium235), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        ModHandler.addShapedRecipe("ga_boiling_water_plutomium_reactor", GATileEntities.BOILING_WATER_PLUTONIUM_REACTOR.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(block, Plutonium241), 'P', new UnificationEntry(plate, GAMaterials.HastelloyN), 'G', new UnificationEntry(gear, GAMaterials.HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        ModHandler.addShapedRecipe("ga_large_miner.basic", GATileEntities.LARGE_MINER[0].getStackForm(), "GCG", "IHI", "GCG", 'H', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Extreme), 'G', new UnificationEntry(gear, BlackSteel), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN);
        ModHandler.addShapedRecipe("ga_large_miner.large", GATileEntities.LARGE_MINER[1].getStackForm(), "GCG", "IHI", "GCG", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Elite), 'G', new UnificationEntry(gear, HSSG), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN);
        ModHandler.addShapedRecipe("ga_large_miner.advance", GATileEntities.LARGE_MINER[2].getStackForm(), "GCG", "IHI", "GCG", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(circuit, Tier.Master), 'G', new UnificationEntry(gear, HSSS), 'I', MetaItems.COMPONENT_GRINDER_TUNGSTEN);
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(OrePrefix.pipeMedium, Bronze), 'L', new UnificationEntry(OrePrefix.pipeLarge, Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'R', new UnificationEntry(OrePrefix.rotor, Bronze));
        ModHandler.addShapedRecipe("ga_large_circuit_assembly", GATileEntities.LARGE_CIRCUIT_ASSEMBLY_LINE.getStackForm(), "CRC", "SAS", "CRC", 'A', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING), 'S', new UnificationEntry(circuit, Tier.Elite));


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

        //Storage
        ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', "slimeball", 'P', "plankWood", 'R', "stickLongIron");
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders && GAConfig.GT6.registerDums) {
            ModHandler.addShapedRecipe("bronze_drum", GATileEntities.BRONZE_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateCurvedBronze", 'R', "stickLongBronze");
            ModHandler.addShapedRecipe("steel_drum", GATileEntities.STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateCurvedSteel", 'R', "stickLongSteel");
            ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateCurvedStainlessSteel", 'R', "stickLongStainlessSteel");
            ModHandler.addShapedRecipe("titanium_drum", GATileEntities.TITANIUM_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateCurvedTitanium", 'R', "stickLongTitanium");
            ModHandler.addShapedRecipe("tungstensteel_drum", GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', "plateCurvedTungstenSteel", 'R', "stickLongTungstenSteel");
        } else if (!(GAConfig.GT6.BendingCurvedPlates || GAConfig.GT6.BendingCylinders) && GAConfig.GT6.registerDums) {
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

        //Machines
        registerMachineRecipe(GATileEntities.CIRCUITASSEMBLER, "ACE", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', BETTER_CIRCUIT, 'W', CABLE, 'E', EMITTER);
        registerMachineRecipe(GATileEntities.CLUSTERMILL, "MMM", "CHC", "MMM", 'M', MOTOR, 'C', CIRCUIT, 'H', HULL);
        registerMachineRecipe(MetaTileEntities.ALLOY_SMELTER, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.BENDER, "PwP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        registerMachineRecipe(MetaTileEntities.ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING);
        registerMachineRecipe(MetaTileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.EXTRUDER, "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON, 'E', CIRCUIT, 'P', PIPE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'D', DIAMOND);
        registerMachineRecipe(MetaTileEntities.MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'G', GRINDER);
        registerMachineRecipe(MetaTileEntities.MICROWAVE, "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR, 'R', EMITTER, 'C', CIRCUIT, 'W', CABLE, 'L', new UnificationEntry(plate, Lead));
        registerMachineRecipe(MetaTileEntities.WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE, 'I', WIRE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.THERMAL_CENTRIFUGE, "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'O', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.PACKER, "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.UNPACKER, "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.LASER_ENGRAVER, "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'A', OreDictNames.craftingAnvil);
        registerMachineRecipe(MetaTileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F', MetaItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ARC_FURNACE, "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'G', new UnificationEntry(ingot, Graphite));
        registerMachineRecipe(MetaTileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));
        registerMachineRecipe(MetaTileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);
        registerMachineRecipe(GATileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
        registerMachineRecipe(GATileEntities.WORLD_ACCELERATOR, "ABC", "DHE", "FGI", 'H', HULL, 'A', PISTON, 'B', ROBOT_ARM, 'C', PUMP, 'D', MOTOR, 'E', CONVEYOR, 'F', EMITTER, 'G', SENSOR, 'I', FIELD_GENERATOR);
        registerMachineRecipe(GATileEntities.MINER, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', MetaItems.COMPONENT_GRINDER_DIAMOND);
        if (GAConfig.GT5U.highTierPumps)
            registerMachineRecipe(GATileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);
        if (GAConfig.GT5U.highTierAlloySmelter)
            registerMachineRecipe(GATileEntities.ALLOY_SMELTER, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierAssemblers)
            registerMachineRecipe(GATileEntities.ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierBenders)
            registerMachineRecipe(GATileEntities.BENDER, "PWP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierCanners)
            registerMachineRecipe(GATileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierCompressors)
            registerMachineRecipe(GATileEntities.COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierCutters)
            registerMachineRecipe(GATileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        if (GAConfig.GT5U.highTierElectricFurnace)
            registerMachineRecipe(GATileEntities.ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING);
        if (GAConfig.GT5U.highTierExtractors)
            registerMachineRecipe(GATileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierExtruders)
            registerMachineRecipe(GATileEntities.EXTRUDER, "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON, 'E', CIRCUIT, 'P', PIPE, 'C', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierLathes)
            registerMachineRecipe(GATileEntities.LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'D', DIAMOND);
        if (GAConfig.GT5U.highTierMacerators)
            registerMachineRecipe(GATileEntities.MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'G', GRINDER);
        if (GAConfig.GT5U.highTierMicrowaves)
            registerMachineRecipe(GATileEntities.MICROWAVE, "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR, 'R', EMITTER, 'C', CIRCUIT, 'W', CABLE, 'L', new UnificationEntry(plate, Lead));
        if (GAConfig.GT5U.highTierWiremills)
            registerMachineRecipe(GATileEntities.WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierCentrifuges)
            registerMachineRecipe(GATileEntities.CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierElectrolyzers)
            registerMachineRecipe(GATileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE, 'I', WIRE, 'G', GLASS);
        if (GAConfig.GT5U.highTierThermalCentrifuges)
            registerMachineRecipe(THERMAL_CENTRIFUGE, "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'O', COIL_HEATING_DOUBLE);
        if (GAConfig.GT5U.highTierOreWashers)
            registerMachineRecipe(GATileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPackers)
            registerMachineRecipe(GATileEntities.PACKER, "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierUnpackers)
            registerMachineRecipe(GATileEntities.UNPACKER, "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierChemicalReactors)
            registerMachineRecipe(GATileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidCanners)
            registerMachineRecipe(GATileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierBreweries)
            registerMachineRecipe(GATileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFermenters)
            registerMachineRecipe(GATileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidExtractors)
            registerMachineRecipe(GATileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierFluidSolidifiers)
            registerMachineRecipe(GATileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        if (GAConfig.GT5U.highTierDistilleries)
            registerMachineRecipe(GATileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierChemicalBaths)
            registerMachineRecipe(GATileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierPolarizers)
            registerMachineRecipe(GATileEntities.POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'W', CABLE);
        if (GAConfig.GT5U.highTierElectromagneticSeparators)
            registerMachineRecipe(GATileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK_ELECTROMAGNETIC, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierAutoclaves)
            registerMachineRecipe(GATileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        if (GAConfig.GT5U.highTierMixers)
            registerMachineRecipe(GATileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        if (GAConfig.GT5U.highTierLaserEngravers)
            registerMachineRecipe(GATileEntities.LASER_ENGRAVER, "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierFormingPresses)
            registerMachineRecipe(GATileEntities.FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierForgeHammers)
            registerMachineRecipe(GATileEntities.FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'A', OreDictNames.craftingAnvil);
        if (GAConfig.GT5U.highTierFluidHeaters)
            registerMachineRecipe(GATileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        if (GAConfig.GT5U.highTierSifters)
            registerMachineRecipe(GATileEntities.SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F', MetaItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE);
        if (GAConfig.GT5U.highTierArcFurnaces)
            registerMachineRecipe(GATileEntities.ARC_FURNACE, "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'G', new UnificationEntry(ingot, Graphite));
        if (GAConfig.GT5U.highTierPlasmaArcFurnaces)
            registerMachineRecipe(GATileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));
        registerMachineRecipe(GATileEntities.MASS_FAB, "CFC", "QMQ", "CFC", 'M', HULL, 'Q', CABLE_QUAD, 'C', BETTER_CIRCUIT, 'F', FIELD_GENERATOR);
        registerMachineRecipe(GATileEntities.REPLICATOR, "EFE", "CMC", "EQE", 'M', HULL, 'Q', CABLE_QUAD, 'C', BETTER_CIRCUIT, 'F', FIELD_GENERATOR, 'E', EMITTER);
        if (GAConfig.Misc.highTierCollector)
            registerMachineRecipe(GATileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);

        for (final CeuType type : CeuType.values()) {
            if (GATileEntities.CEUS.containsKey(type)) {
                GATileEntities.CEUS.get(type).forEach(CeuCraftingHelper.HELPER.logic(type));
            }
        }
    }

    public static <T extends MetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        for (int i = 0; i < metaTileEntities.length; i++) {
            if (metaTileEntities[i] != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntities[i].getMetaName()), metaTileEntities[i].getStackForm(), prepareRecipe(i + 1, Arrays.copyOf(recipe, recipe.length)));
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