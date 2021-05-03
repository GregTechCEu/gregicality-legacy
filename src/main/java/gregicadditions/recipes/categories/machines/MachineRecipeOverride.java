package gregicadditions.recipes.categories.machines;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaItems;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.helper.AdditionMethods.*;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.BRONZE_BRICKS;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING;
import static gregtech.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;

public class MachineRecipeOverride {

    public static void init() {
        multiblockOverride();
        singleblockOverride();
        hullOverride();
    }

    private static void singleblockOverride() {

        // Simple Machines
        // These are overridden only to insert our tiered Glass
        removeTieredRecipeByName("gregtech:gregtech.machine.canner.",           LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.cutter.",           LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.extractor.",        LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.electrolyzer.",     LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.ore_washer.",       LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.chemical_reactor.", LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.fluid_canner.",     LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.brewery.",          LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.fermenter.",        LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.fluid_extractor.",  LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.fluid_solidifier.", LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.distillery.",       LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.chemical_bath.",    LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.autoclave.",        LV, IV);
        removeTieredRecipeByName("gregtech:gregtech.machine.mixer.",            LV, EV);
        removeTieredRecipeByName("gregtech:gregtech.machine.fluid_heater.",     LV, EV);

        registerMachineRecipe(MetaTileEntities.CANNER,           "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP,    'C', CIRCUIT,             'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CUTTER,           "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR,   'V', CONVEYOR,            'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS, 'B', OreDictNames.craftingDiamondBlade);
        registerMachineRecipe(MetaTileEntities.EXTRACTOR,        "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON,  'P', PUMP,                'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.ELECTROLYZER,     "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE_SINGLE,        'I', WIRE,         'G', GLASS);
        registerMachineRecipe(MetaTileEntities.ORE_WASHER,       "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR,   'E', MOTOR,               'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR,   'E', MOTOR,               'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_CANNER,     "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP,    'C', CIRCUIT,             'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.BREWERY,          "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP,    'B', STICK_DISTILLATION,  'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FERMENTER,        "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP,    'C', CIRCUIT,             'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_EXTRACTOR,  "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON,  'P', PUMP,                'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP,    'C', CIRCUIT,             'W', CABLE_SINGLE, 'G', GLASS,        'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.DISTILLERY,       "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP,    'B', STICK_DISTILLATION,  'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_BATH,    "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP,    'V', CONVEYOR,            'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.AUTOCLAVE,        "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP,    'C', CIRCUIT,             'I', PLATE,        'G', GLASS);
        registerMachineRecipe(MetaTileEntities.MIXER,            "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR,   'R', ROTOR,               'C', CIRCUIT,      'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_HEATER,     "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP,    'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT,      'W', CABLE_SINGLE, 'G', GLASS);


        // Plasma Arc Furnace
        // Replaces circuits to be tier+1 instead of normal tier
        removeTieredRecipeByName("gregtech:gregtech.machine.plasma_arc_furnace.", LV, EV);
        registerMachineRecipe(MetaTileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', BETTER_CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(ingot, Graphite));


        // Pump (Machine)
        // Replaces the original GT Piston with a GT Pipe
        removeTieredRecipeByName("gregtech:gregtech.machine.pump.", LV, EV);
        registerMachineRecipe(MetaTileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PIPE);


        // Air Collector
        // Replaces the original GT Fluid Filter with a GT Item Filter
        removeTieredRecipeByName("gregtech:gregtech.machine.air_collector.", LV, EV);
        registerMachineRecipe(MetaTileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.ITEM_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);


        // Casings
        // Metals changed from base GTCE
        removeTieredRecipeByName("gregtech:casing_", LuV, GTValues.MAX);
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Chrome, 8),       getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Iridium, 8),      getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Osmium, 8),       getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Darmstadtium, 8), getIntegratedCircuit(8)); // MAX doesn't have a recipe yet

        ModHandler.addShapedRecipe("ga_casing_luv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_casing_zpm", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Osmiridium));
        ModHandler.addShapedRecipe("ga_casing_uv",  MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Tritanium));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, RhodiumPlatedPalladium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Osmiridium, 8)            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Tritanium, 8)             .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)) .buildAndRegister();


        // Transformers
        // Replaces circuits with various power-related components
        removeTieredRecipeByName("gregtech:transformer_", EV, UV);
        ModHandler.addShapedRecipe("ga_transformer_ev",  MetaTileEntities.TRANSFORMER[EV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[HV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, Aluminium),       'B', new UnificationEntry(cableGtSingle, Gold),            'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_iv",  MetaTileEntities.TRANSFORMER[IV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[EV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, Tungsten),        'B', new UnificationEntry(cableGtSingle, Aluminium),       'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_luv", MetaTileEntities.TRANSFORMER[LuV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[IV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'B', new UnificationEntry(cableGtSingle, Tungsten),        'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_zpm", MetaTileEntities.TRANSFORMER[ZPM - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[LuV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Naquadah),        'B', new UnificationEntry(cableGtSingle, VanadiumGallium), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_uv",  MetaTileEntities.TRANSFORMER[UV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(wireGtQuadruple, NaquadahAlloy), 'B', new UnificationEntry(cableGtSingle, Naquadah),        'K', MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT);


        // Steam Turbines
        // Replaces a Small Pipe with a Medium Pipe
        removeTieredRecipeByName("gregtech:steam_turbine_", LV, HV);
        ModHandler.addShapedRecipe("ga_steam_turbine_lv", MetaTileEntities.STEAM_TURBINE[0].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(rotor, Tin), 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin), 'P', new UnificationEntry(pipeMedium, Bronze));
        ModHandler.addShapedRecipe("ga_steam_turbine_mv", MetaTileEntities.STEAM_TURBINE[1].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(rotor, Bronze), 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper), 'P', new UnificationEntry(pipeMedium, Steel));
        ModHandler.addShapedRecipe("ga_steam_turbine_hv", MetaTileEntities.STEAM_TURBINE[2].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(rotor, Steel), 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold), 'P', new UnificationEntry(pipeMedium, StainlessSteel));


        // Chargers
        // Adds in our ZPM / UV Batteries in place of the default ones, if configured
        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();
        List<ItemStack> batteries = new ArrayList<ItemStack>() {{
            add(GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE.getStackForm() : MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm());
            add(GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_CLUSTER.getStackForm() : last_bat);
            add(last_bat);
        }};
        removeTieredRecipeByName("gregtech:charger_", ZPM, GTValues.MAX);
        ModHandler.addShapedRecipe("ga_charger_zpm", MetaTileEntities.CHARGER[GTValues.ZPM].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), 'W', new UnificationEntry(wireGtHex, Naquadah),       'T', OreDictNames.chestWood, 'B', batteries.get(0), 'C', new UnificationEntry(circuit, Ultimate));
        ModHandler.addShapedRecipe("ga_charger_uv",  MetaTileEntities.CHARGER[GTValues.UV].getStackForm(),  "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(),  'W', new UnificationEntry(wireGtHex, NaquadahAlloy),  'T', OreDictNames.chestWood, 'B', batteries.get(1), 'C', new UnificationEntry(circuit, Superconductor));
        ModHandler.addShapedRecipe("ga_charger_max", MetaTileEntities.CHARGER[GTValues.MAX].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'W', new UnificationEntry(wireGtHex, Superconductor), 'T', OreDictNames.chestWood, 'B', batteries.get(2), 'C', new UnificationEntry(circuit, Infinite));


        // Steam Machines
        // Steel Furnace uses a Bronze Pipe (PR submitted to GTCE)
        // Steam Macerators now use Flint instead of Diamonds
        removeRecipeByName("gregtech:steam_furnace_steel");
        removeRecipeByName("gregtech:steam_macerator_bronze");
        removeRecipeByName("gregtech:steam_macerator_steel");
        ModHandler.addShapedRecipe("ga_steam_furnace_steel", MetaTileEntities.STEAM_FURNACE_STEEL.getStackForm(), "XXX", "XMX", "XFX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_BRICKS_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("ga_steam_macerator_bronze", MetaTileEntities.STEAM_MACERATOR_BRONZE.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'X', new UnificationEntry(pipeSmall, Bronze), 'P', OreDictNames.craftingPiston, 'D', new ItemStack(Items.FLINT));
        ModHandler.addShapedRecipe("ga_steam_macerator_steel", MetaTileEntities.STEAM_MACERATOR_STEEL.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.STEEL_HULL), 'X', new UnificationEntry(pipeSmall, Steel), 'P', OreDictNames.craftingPiston, 'D', new ItemStack(Items.FLINT));

    }

    private static void multiblockOverride() {

        // Distillation Tower
        removeRecipeByName("gregtech:distillation_tower");
        ModHandler.addShapedRecipe("ga_distillation_tower", GATileEntities.DISTILLATION_TOWER.getStackForm(),
                "CBC", "FMF", "CBC",
                'M', MetaTileEntities.HULL[EV].getStackForm(),
                'B', new UnificationEntry(pipeLarge, StainlessSteel),
                'C', new UnificationEntry(circuit, Extreme),
                'F', MetaItems.ELECTRIC_PUMP_EV);

        // Cracker Unit
        removeRecipeByName("gregtech:cracking_unit");
        ModHandler.addShapedRecipe("ga_cracking_unit", GATileEntities.CRACKER.getStackForm(),
                "CEC", "PHP", "CEC",
                'C', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL),
                'E', MetaItems.ELECTRIC_PUMP_HV,
                'P', new UnificationEntry(circuit, Advanced),
                'H', MetaTileEntities.HULL[HV].getStackForm());

        // Primitive Blast Furnace (PBF)
        removeRecipeByName("gregtech:bronze_primitive_blast_furnace");
        ModHandler.addShapedRecipe("ga_primitive_blast_furnace", MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(),
                "hRS", "PBR", "dRS",
                'R', new UnificationEntry(stick, Iron),
                'S', new UnificationEntry(screw, Iron),
                'P', new UnificationEntry(plate, Iron),
                'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS));

        // Electric Blast Furnace (EBF)
        removeRecipeByName("gregtech:electric_blast_furnace");
        ModHandler.addShapedRecipe("ga_electric_blast_furnace", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(),
                "FFF", "CMC", "WCW",
                'M', new UnificationEntry(gtMetalCasing, Invar),
                'F', Blocks.FURNACE,
                'C', new UnificationEntry(circuit, Basic),
                'W', new UnificationEntry(cableGtSingle, Tin));

        // Vacuum Freezer
        removeRecipeByName("gregtech:vacuum_freezer");
        ModHandler.addShapedRecipe("ga_vacuum_freezer", GATileEntities.VACUUM_FREEZER.getStackForm(),
                "PPP", "CMC", "WCW",
                'M', new UnificationEntry(gtMetalCasing, Aluminium),
                'P', MetaItems.ELECTRIC_PUMP_HV,
                'C', new UnificationEntry(circuit, Advanced),
                'W', new UnificationEntry(cableGtSingle, Gold));

        // Implosion Compressor
        removeRecipeByName("gregtech:implosion_compressor");
        ModHandler.addShapedRecipe("ga_implosion_compressor", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(),
                "OOO", "CMC", "WCW",
                'M', new UnificationEntry(gtMetalCasing, Steel),
                'O', new UnificationEntry(stone, Obsidian),
                'C', new UnificationEntry(circuit, Advanced),
                'W', new UnificationEntry(cableGtSingle, Aluminium));

        // Pyrolyse Oven
        removeRecipeByName("gregtech:pyrolyse_oven");
        ModHandler.addShapedRecipe("ga_pyrolyse_oven", GATileEntities.PYROLYSE_OVEN.getStackForm(),
                "WEP", "EME", "WCP",
                'M', MetaTileEntities.HULL[MV].getStackForm(),
                'W', MetaItems.ELECTRIC_PISTON_MV,
                'P', new UnificationEntry(wireGtQuadruple, Cupronickel),
                'E', new UnificationEntry(circuit, Good),
                'C', MetaItems.ELECTRIC_PUMP_MV);

        // Large Diesel Engine
        removeRecipeByName("gregtech:diesel_engine");
        ModHandler.addShapedRecipe("ga_diesel_engine", GATileEntities.DIESEL_ENGINE.getStackForm(),
                "PCP", "EME", "GWG",
                'M', MetaTileEntities.HULL[EV].getStackForm(),
                'P', MetaItems.ELECTRIC_PISTON_EV,
                'E', MetaItems.ELECTRIC_MOTOR_EV,
                'C', new UnificationEntry(circuit, Elite),
                'W', new UnificationEntry(wireGtSingle, TungstenSteel),
                'G', new UnificationEntry(gear, Titanium));

        // Engine Intake Casing TODO Move elsewhere?
        removeRecipeByName("gregtech:engine_intake_casing");
        ModHandler.addShapedRecipe("ga_engine_intake_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(ENGINE_INTAKE_CASING),
                "PhP", "RFR", "PwP",
                'R', new UnificationEntry(pipeMedium, Titanium),
                'F', new UnificationEntry(gtMetalCasing, Titanium),
                'P', new UnificationEntry(rotor, Titanium));

        // Multi-Smelter
        removeRecipeByName("gregtech:multi_furnace");
        ModHandler.addShapedRecipe("ga_multi_furnace", GATileEntities.MULTI_FURNACE.getStackForm(),
                "PPP", "ASA", "CAC",
                'P', Blocks.FURNACE,
                'A', new UnificationEntry(circuit, Advanced),
                'S', new UnificationEntry(gtMetalCasing, Invar),
                'C', new UnificationEntry(cableGtSingle, AnnealedCopper));

        // Large Steam Turbine
        removeRecipeByName("gregtech:large_steam_turbine");
        ModHandler.addShapedRecipe("ga_large_steam_turbine", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(),
                "PSP", "SAS", "CSC",
                'S', new UnificationEntry(gear, Steel),
                'P', new UnificationEntry(circuit, Advanced),
                'A', MetaTileEntities.HULL[HV].getStackForm(),
                'C', new UnificationEntry(pipeLarge, Steel));

        // Large Gas Turbine
        removeRecipeByName("gregtech:large_gas_turbine");
        ModHandler.addShapedRecipe("ga_large_gas_turbine", GATileEntities.LARGE_GAS_TURBINE.getStackForm(),
                "PSP", "SAS", "CSC",
                'S', new UnificationEntry(gear, StainlessSteel),
                'P', new UnificationEntry(circuit, Extreme),
                'A', MetaTileEntities.HULL[EV].getStackForm(),
                'C', new UnificationEntry(pipeLarge, StainlessSteel));

        // Large Plasma Turbine
        removeRecipeByName("gregtech:large_plasma_turbine");
        ModHandler.addShapedRecipe("ga_large_plasma_turbine", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(),
                "PSP", "SAS", "CSC",
                'S', new UnificationEntry(gear, TungstenSteel),
                'P', new UnificationEntry(circuit, Master),
                'A', MetaTileEntities.HULL[UV].getStackForm(),
                'C', new UnificationEntry(pipeLarge, TungstenSteel));

        // Large Bronze Boiler
        removeRecipeByName("gregtech:large_bronze_boiler");
        ModHandler.addShapedRecipe("ga_large_bronze_boiler", MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(),
                "PSP", "SAS", "PSP",
                'P', new UnificationEntry(cableGtSingle, Tin),
                'S', new UnificationEntry(circuit, Basic),
                'A', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS));

        // Large Steel Boiler
        removeRecipeByName("gregtech:large_steel_boiler");
        ModHandler.addShapedRecipe("ga_large_steel_boiler", MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(),
                "PSP", "SAS", "PSP",
                'P', new UnificationEntry(cableGtSingle, Copper),
                'S', new UnificationEntry(circuit, Advanced),
                'A', new UnificationEntry(gtMetalCasing, Steel));

        // Large Titanium Boiler
        removeRecipeByName("gregtech:large_titanium_boiler");
        ModHandler.addShapedRecipe("ga_large_titanium_boiler", MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(),
                "PSP", "SAS", "PSP",
                'P', new UnificationEntry(cableGtSingle, Gold),
                'S', new UnificationEntry(circuit, Elite),
                'A', new UnificationEntry(gtMetalCasing, Titanium));

        // Large Tungstensteel Boiler
        removeRecipeByName("gregtech:large_tungstensteel_boiler");
        ModHandler.addShapedRecipe("ga_large_tungstensteel_boiler", MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(),
                "PSP", "SAS", "PSP",
                'P', new UnificationEntry(cableGtSingle, Aluminium),
                'S', new UnificationEntry(circuit, Master),
                'A', new UnificationEntry(gtMetalCasing, TungstenSteel));

        // Recipes added to convert from GTCE to Gregicality versions of overridden multiblocks ========================
        ModHandler.addShapelessRecipe("ga_cracking_unit_compatibility", GATileEntities.CRACKER.getStackForm(), MetaTileEntities.CRACKER.getStackForm());
        ModHandler.addShapelessRecipe("ga_electric_blast_furnace_compatibility", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
        ModHandler.addShapelessRecipe("ga_vacuum_freezer_compatibility", GATileEntities.VACUUM_FREEZER.getStackForm(), MetaTileEntities.VACUUM_FREEZER.getStackForm());
        ModHandler.addShapelessRecipe("ga_implosion_compressor_compatibility", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(), MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm());
        ModHandler.addShapelessRecipe("ga_diesel_engine_compatibility", GATileEntities.DIESEL_ENGINE.getStackForm(), MetaTileEntities.DIESEL_ENGINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_multi_furnace_compatibility", GATileEntities.MULTI_FURNACE.getStackForm(), MetaTileEntities.MULTI_FURNACE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_steam_turbine_compatibility", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(), MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_gas_turbine_compatibility", GATileEntities.LARGE_GAS_TURBINE.getStackForm(), MetaTileEntities.LARGE_GAS_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_large_plasma_turbine_compatibility", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(), MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm());
        ModHandler.addShapelessRecipe("ga_pyrolyse_oven_compatibility", GATileEntities.PYROLYSE_OVEN.getStackForm(), MetaTileEntities.PYROLYSE_OVEN.getStackForm());
        ModHandler.addShapelessRecipe("ga_distillation_tower_compatibility", GATileEntities.DISTILLATION_TOWER.getStackForm(), MetaTileEntities.DISTILLATION_TOWER.getStackForm());
    }

    private static void hullOverride() {

        removeTieredRecipeByName("gregtech:hull_", ULV, GTValues.MAX);

        if (ConfigHolder.harderMachineHulls) {
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), OreDictUnifier.get(cableGtSingle, Lead, 2)},             new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  OreDictUnifier.get(cableGtSingle, Tin, 2)},              new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, Copper, 2)},           new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, AnnealedCopper, 2)},   new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  OreDictUnifier.get(cableGtSingle, Gold, 2)},             new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  OreDictUnifier.get(cableGtSingle, Aluminium, 2)},        new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  OreDictUnifier.get(cableGtSingle, Tungsten, 2)},         new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)},  new FluidStack[]{Plastic.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), OreDictUnifier.get(cableGtSingle, Naquadah, 2)},         new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  OreDictUnifier.get(cableGtQuadruple, NaquadahAlloy, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), OreDictUnifier.get(cableGtSingle, Superconductor, 2)},   new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});
        } else {
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), OreDictUnifier.get(cableGtSingle, Lead, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  OreDictUnifier.get(cableGtSingle, Tin, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, Copper, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, AnnealedCopper, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  OreDictUnifier.get(cableGtSingle, Gold, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  OreDictUnifier.get(cableGtSingle, Aluminium, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  OreDictUnifier.get(cableGtSingle, Tungsten, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), OreDictUnifier.get(cableGtSingle, Naquadah, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  OreDictUnifier.get(cableGtQuadruple, NaquadahAlloy, 2));
            removeRecipesByInputs(ASSEMBLER_RECIPES, MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), OreDictUnifier.get(cableGtSingle, Superconductor, 2));
        }

        ModHandler.addShapedRecipe("ga_hull_ulv", MetaTileEntities.HULL[ULV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), 'C', new UnificationEntry(cableGtSingle, Lead),                    'H', new UnificationEntry(plate, WroughtIron),            'P', new UnificationEntry(plate, Wood));
        ModHandler.addShapedRecipe("ga_hull_lv",  MetaTileEntities.HULL[LV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  'C', new UnificationEntry(cableGtSingle, Tin),                     'H', new UnificationEntry(plate, Steel),                  'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_mv",  MetaTileEntities.HULL[MV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  'C', new UnificationEntry(cableGtSingle, Copper),                  'H', new UnificationEntry(plate, Aluminium),              'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_hv",  MetaTileEntities.HULL[HV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  'C', new UnificationEntry(cableGtSingle, Gold),                    'H', new UnificationEntry(plate, StainlessSteel),         'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_ev",  MetaTileEntities.HULL[EV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  'C', new UnificationEntry(cableGtSingle, Aluminium),               'H', new UnificationEntry(plate, Titanium),               'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_iv",  MetaTileEntities.HULL[IV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  'C', new UnificationEntry(cableGtSingle, Tungsten),                'H', new UnificationEntry(plate, TungstenSteel),          'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_luv", MetaTileEntities.HULL[LuV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium),         'H', new UnificationEntry(plate, RhodiumPlatedPalladium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_zpm", MetaTileEntities.HULL[ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah),                'H', new UnificationEntry(plate, Osmiridium),             'P', new UnificationEntry(plate, Polybenzimidazole));
        ModHandler.addShapedRecipe("ga_hull_uv",  MetaTileEntities.HULL[UV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  'C', new UnificationEntry(cableGtSingle, NaquadahAlloy),           'H', new UnificationEntry(plate, Tritanium),              'P', new UnificationEntry(plate, Polybenzimidazole));

        ASSEMBLER_RECIPES.recipeBuilder().duration(25).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV)).input(cableGtSingle, Lead, 2)           .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[0].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV)) .input(cableGtSingle, Tin, 2)            .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[1].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)) .input(cableGtSingle, Copper, 2)         .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)) .input(cableGtSingle, AnnealedCopper, 2) .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV)) .input(cableGtSingle, Gold, 2)           .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[3].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV)) .input(cableGtSingle, Aluminium, 2)      .fluidInputs(Plastic.getFluid(L * 2))                .outputs(MetaTileEntities.HULL[4].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV)) .input(cableGtSingle, Tungsten, 2)       .fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[5].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).input(cableGtSingle, VanadiumGallium, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[6].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).input(cableGtSingle, Naquadah, 2)       .fluidInputs(Polybenzimidazole.getFluid(L * 2))      .outputs(MetaTileEntities.HULL[7].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)) .input(cableGtSingle, NaquadahAlloy, 2)  .fluidInputs(Polybenzimidazole.getFluid(L * 2))      .outputs(MetaTileEntities.HULL[8].getStackForm()).buildAndRegister();
    }
}
