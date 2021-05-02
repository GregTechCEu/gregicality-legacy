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
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.helper.AdditionMethods.removeRecipesByInputs;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregicadditions.GAValues.*;

public class MachineCraftingRecipes {

    public static void init() {

        removeGTCEMachineRecipes();

        MultiblockCraftingRecipes.init();
        SimpleMachineRecipes.init();
        highAmpMachines();

        // Rotor Holders
        ModHandler.addShapedRecipe("ga_rotor_holder_hv",  GATileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[HV].getStackForm(),  'W', new UnificationEntry(wireGtHex, Gold),                 'R', new UnificationEntry(gear, BlackSteel));
        ModHandler.addShapedRecipe("ga_rotor_holder_luv", GATileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, YttriumBariumCuprate), 'R', new UnificationEntry(gear, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_rotor_holder_uhv", GATileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', GATileEntities.GA_HULLS[0].getStackForm(), 'W', new UnificationEntry(wireGtHex, Duranium),             'R', new UnificationEntry(gear, Seaborgium));

        // Machine Hulls
        ModHandler.addShapedRecipe("ga_casing_luv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_casing_zpm", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Osmiridium));
        ModHandler.addShapedRecipe("ga_casing_uv",  MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Tritanium));
        ModHandler.addShapedRecipe("ga_casing_uhv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
        ModHandler.addShapedRecipe("ga_casing_uev", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
        ModHandler.addShapedRecipe("ga_casing_uiv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));

        // Filtered Fluid Hatches
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

        // UHV+ Energy Hatches
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uhv",  GATileEntities.ENERGY_INPUT[0].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uev",  GATileEntities.ENERGY_INPUT[1].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uiv",  GATileEntities.ENERGY_INPUT[2].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uhv", GATileEntities.ENERGY_OUTPUT[0].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uev", GATileEntities.ENERGY_OUTPUT[1].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uiv", GATileEntities.ENERGY_OUTPUT[2].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));

        // Custom Hatches
        ModHandler.addShapedRecipe("ga_qubit_input_hatch", GATileEntities.QBIT_INPUT_HATCH[0].getStackForm(),   "   ", "CM ", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberHex));
        ModHandler.addShapedRecipe("ga_qubit_output_hatch", GATileEntities.QBIT_OUTPUT_HATCH[0].getStackForm(), "   ", " MC", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberSingle));
        ModHandler.addShapedRecipe("ga_steam_hatch", GATileEntities.STEAM_HATCH.getStackForm(), "BPB", "BTB", "BPB", 'B', new UnificationEntry(plate, Bronze), 'P', new UnificationEntry(pipeMedium, Bronze), 'T', MetaTileEntities.BRONZE_TANK.getStackForm());
        ModHandler.addShapedRecipe("ga_steam_input_bus", GATileEntities.STEAM_INPUT_BUS.getStackForm(), "BMB", "THT", "BMB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        ModHandler.addShapedRecipe("ga_steam_output_bus", GATileEntities.STEAM_OUTPUT_BUS.getStackForm(), "BTB", "MHM", "BTB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);

        // Machine casing assembler recipes
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(25).circuitMeta(8).input(plate, WroughtIron, 8)           .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Steel, 8)                 .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Aluminium, 8)             .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, StainlessSteel, 8)        .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Titanium, 8)              .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, TungstenSteel, 8)         .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, RhodiumPlatedPalladium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Osmiridium, 8)            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Tritanium, 8)             .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Seaborgium, 8)            .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Bohrium, 8)               .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)) .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Quantum, 8)               .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)) .buildAndRegister();

        // Hulls
        ModHandler.addShapedRecipe("ga_hull_ulv", MetaTileEntities.HULL[ULV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), 'C', new UnificationEntry(cableGtSingle, Lead),                    'H', new UnificationEntry(plate, WroughtIron),            'P', new UnificationEntry(plate, Wood));
        ModHandler.addShapedRecipe("ga_hull_lv",  MetaTileEntities.HULL[LV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  'C', new UnificationEntry(cableGtSingle, Tin),                     'H', new UnificationEntry(plate, Steel),                  'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_mv",  MetaTileEntities.HULL[MV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  'C', new UnificationEntry(cableGtSingle, Copper),                  'H', new UnificationEntry(plate, Aluminium),              'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_hv",  MetaTileEntities.HULL[HV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  'C', new UnificationEntry(cableGtSingle, Gold),                    'H', new UnificationEntry(plate, StainlessSteel),         'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_ev",  MetaTileEntities.HULL[EV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  'C', new UnificationEntry(cableGtSingle, Aluminium),               'H', new UnificationEntry(plate, Titanium),               'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_iv",  MetaTileEntities.HULL[IV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  'C', new UnificationEntry(cableGtSingle, Tungsten),                'H', new UnificationEntry(plate, TungstenSteel),          'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_luv", MetaTileEntities.HULL[LuV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium),         'H', new UnificationEntry(plate, RhodiumPlatedPalladium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_zpm", MetaTileEntities.HULL[ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah),                'H', new UnificationEntry(plate, Osmiridium),             'P', new UnificationEntry(plate, Polybenzimidazole));
        ModHandler.addShapedRecipe("ga_hull_uv",  MetaTileEntities.HULL[UV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  'C', new UnificationEntry(cableGtSingle, NaquadahAlloy),           'H', new UnificationEntry(plate, Tritanium),              'P', new UnificationEntry(plate, Polybenzimidazole));
        ModHandler.addShapedRecipe("ga_hull_uhv", GATileEntities.GA_HULLS[0].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium),             'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uev", GATileEntities.GA_HULLS[1].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  'C', new UnificationEntry(cableGtQuadruple, Pikyonium),            'H', new UnificationEntry(plate, Bohrium),                'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uiv", GATileEntities.GA_HULLS[2].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  'C', new UnificationEntry(cableGtQuadruple, Cinobite),             'H', new UnificationEntry(plate, Quantum),                'P', new UnificationEntry(plate, Zylon));

        // Chargers
        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();
        List<ItemStack> batteries = new ArrayList<ItemStack>() {{
            add(GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_MODULE.getStackForm() : MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm());
            add(GAConfig.GT5U.enableZPMandUVBats ? GAMetaItems.ENERGY_CLUSTER.getStackForm() : last_bat);
            add(last_bat);
        }};
        ModHandler.addShapedRecipe("ga_charger_ev",  MetaTileEntities.CHARGER[GAValues.EV].getStackForm(),  "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(),  'W', new UnificationEntry(wireGtHex, Aluminium),      'T', OreDictNames.chestWood, 'B', MetaItems.LAPOTRON_CRYSTAL, 'C', new UnificationEntry(circuit, Extreme));
        ModHandler.addShapedRecipe("ga_charger_zpm", MetaTileEntities.CHARGER[GAValues.ZPM].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'W', new UnificationEntry(wireGtHex, Naquadah),       'T', OreDictNames.chestWood, 'B', batteries.get(0),           'C', new UnificationEntry(circuit, Ultimate));
        ModHandler.addShapedRecipe("ga_charger_uv",  MetaTileEntities.CHARGER[GAValues.UV].getStackForm(),  "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GAValues.UV].getStackForm(),  'W', new UnificationEntry(wireGtHex, NaquadahAlloy),  'T', OreDictNames.chestWood, 'B', batteries.get(1),           'C', new UnificationEntry(circuit, Superconductor));
        ModHandler.addShapedRecipe("ga_charger_max", MetaTileEntities.CHARGER[GTValues.MAX].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'W', new UnificationEntry(wireGtHex, Superconductor), 'T', OreDictNames.chestWood, 'B', batteries.get(2),           'C', new UnificationEntry(circuit, Infinite));

        // Transformers
        ModHandler.addShapedRecipe("ga_transformer_ev",  MetaTileEntities.TRANSFORMER[EV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[HV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, Aluminium),       'B', new UnificationEntry(cableGtSingle, Gold),            'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_iv",  MetaTileEntities.TRANSFORMER[IV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[EV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, Tungsten),        'B', new UnificationEntry(cableGtSingle, Aluminium),       'K', MetaItems.SMALL_COIL);
        ModHandler.addShapedRecipe("ga_transformer_luv", MetaTileEntities.TRANSFORMER[LuV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[IV].getStackForm(),  'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'B', new UnificationEntry(cableGtSingle, Tungsten),        'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_zpm", MetaTileEntities.TRANSFORMER[ZPM - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[LuV].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Naquadah),        'B', new UnificationEntry(cableGtSingle, VanadiumGallium), 'K', MetaItems.POWER_INTEGRATED_CIRCUIT);
        ModHandler.addShapedRecipe("ga_transformer_uv",  MetaTileEntities.TRANSFORMER[UV - 1].getStackForm(),  "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(wireGtQuadruple, NaquadahAlloy), 'B', new UnificationEntry(cableGtSingle, Naquadah),        'K', MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT);

        //Steam Machines TODO
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
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(pipeMedium, Bronze), 'L', new UnificationEntry(pipeLarge, Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'R', new UnificationEntry(rotor, Bronze));

        // Drums
        if (GAConfig.GT6.registerDrums) {
            ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', Items.SLIME_BALL, 'P', "plankWood", 'R', new UnificationEntry(stickLong, Iron));

            OrePrefix prefix = (GAConfig.GT6.addCurvedPlates && GAConfig.GT6.BendingCylinders && GAConfig.GT6.BendingCurvedPlates) ? plateCurved : plate;
            ModHandler.addShapedRecipe("bronze_drum", GATileEntities.BRONZE_DRUM.getStackForm(),                   " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Bronze),         'R', new UnificationEntry(stickLong, Bronze));
            ModHandler.addShapedRecipe("steel_drum", GATileEntities.STEEL_DRUM.getStackForm(),                     " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Steel),          'R', new UnificationEntry(stickLong, Steel));
            ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
            ModHandler.addShapedRecipe("titanium_drum", GATileEntities.TITANIUM_DRUM.getStackForm(),               " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, Titanium),       'R', new UnificationEntry(stickLong, Titanium));
            ModHandler.addShapedRecipe("tungstensteel_drum", GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(),     " h ", "PRP", "PRP", 'P', new UnificationEntry(prefix, TungstenSteel),  'R', new UnificationEntry(stickLong, TungstenSteel));
        }

        // Crates
        if (GAConfig.Misc.registerCrates) { // TODO

            ModHandler.addShapedRecipe("wooden_crate", GATileEntities.WOODEN_CRATE.getStackForm(), "RPR", "PsP", "RPR", 'P', "plankWood", 'R', "screwIron");
            ModHandler.addShapedRecipe("bronze_crate", GATileEntities.BRONZE_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateBronze", 'R', "stickLongBronze");
            ModHandler.addShapedRecipe("steel_crate", GATileEntities.STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateSteel", 'R', "stickLongSteel");
            ModHandler.addShapedRecipe("stainless_steel_crate", GATileEntities.STAINLESS_STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateStainlessSteel", 'R', "stickLongStainlessSteel");
            ModHandler.addShapedRecipe("titanium_crate", GATileEntities.TITANIUM_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateTitanium", 'R', "stickLongTitanium");
            ModHandler.addShapedRecipe("tungstensteel_crate", GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', "plateTungstenSteel", 'R', "stickLongTungstenSteel");
        }

        //Generators TODO
        ModHandler.addShapedRecipe("ga_diesel_generator_lv", MetaTileEntities.DIESEL_GENERATOR[0].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_LV, 'E', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin), 'G', new UnificationEntry(gear, Steel));
        ModHandler.addShapedRecipe("ga_diesel_generator_mv", MetaTileEntities.DIESEL_GENERATOR[1].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_MV, 'E', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper), 'G', new UnificationEntry(gear, Aluminium));
        ModHandler.addShapedRecipe("ga_diesel_generator_hv", MetaTileEntities.DIESEL_GENERATOR[2].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_HV, 'E', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold), 'G', new UnificationEntry(gear, StainlessSteel));
        ModHandler.addShapedRecipe("ga_gas_turbine_lv", MetaTileEntities.GAS_TURBINE[0].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(rotor, Tin), 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin));
        ModHandler.addShapedRecipe("ga_gas_turbine_mv", MetaTileEntities.GAS_TURBINE[1].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(rotor, Bronze), 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper));
        ModHandler.addShapedRecipe("ga_gas_turbine_hv", MetaTileEntities.GAS_TURBINE[2].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(rotor, Steel), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold));
        ModHandler.addShapedRecipe("ga_steam_turbine_lv", MetaTileEntities.STEAM_TURBINE[0].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(rotor, Tin), 'C', new UnificationEntry(circuit, Tier.Basic), 'W', new UnificationEntry(cableGtSingle, Tin), 'P', new UnificationEntry(pipeMedium, Bronze));
        ModHandler.addShapedRecipe("ga_steam_turbine_mv", MetaTileEntities.STEAM_TURBINE[1].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(rotor, Bronze), 'C', new UnificationEntry(circuit, Tier.Good), 'W', new UnificationEntry(cableGtSingle, Copper), 'P', new UnificationEntry(pipeMedium, Steel));
        ModHandler.addShapedRecipe("ga_steam_turbine_hv", MetaTileEntities.STEAM_TURBINE[2].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(rotor, Steel), 'C', new UnificationEntry(circuit, Tier.Advanced), 'W', new UnificationEntry(cableGtSingle, Gold), 'P', new UnificationEntry(pipeMedium, StainlessSteel));

        ModHandler.addShapedRecipe("ga_simple_ore_washer", GATileEntities.SIMPLE_ORE_WASHER.getStackForm(), "PIP", "PTP", "PCP", 'C', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'T', MetaItems.ELECTRIC_PUMP_LV, 'I', new UnificationEntry(plate, Steel), 'P', new UnificationEntry(pipeLarge, Bronze));

        ModHandler.addShapedRecipe("buffer_lv", GATileEntities.BUFFER[LV - 1].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(LV), 'H', HULL.getIngredient(LV), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("buffer_mv", GATileEntities.BUFFER[MV - 1].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(MV), 'H', HULL.getIngredient(MV), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("buffer_hv", GATileEntities.BUFFER[HV - 1].getStackForm(), " G ", " H ", " C ", 'G', GLASS.getIngredient(HV), 'H', HULL.getIngredient(HV), 'C', OreDictNames.chestWood);

        for (final EnergyConverterType type : EnergyConverterType.values()) {
            if (GATileEntities.ENERGY_CONVERTER.containsKey(type)) {
                GATileEntities.ENERGY_CONVERTER.get(type).forEach(EnergyConverterCraftingHelper.HELPER.logic(type));
            }
        }
        GATileEntities.DIODES.forEach(diode -> {
            int tier = diode.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", diode.getMetaName()), diode.getStackForm(), "CCC", "XMX", "CCC", 'M', HULL.getIngredient(tier), 'C', CABLE_SINGLE.getIngredient(tier), 'X', MetaItems.SMALL_COIL);
        });
    }

    private static void removeGTCEMachineRecipes() {

        final String[] tiers = {"lv", "mv", "hv", "ev", "iv", "luv", "zpm", "uv", "max"};

        // Basic Machines
        for (String tier : tiers) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.canner." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.cutter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.extractor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.electrolyzer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.ore_washer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.chemical_reactor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_canner." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.brewery." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fermenter." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_extractor." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_solidifier." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.distillery." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.chemical_bath." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.autoclave." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.mixer." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.fluid_heater." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.plasma_arc_furnace." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.pump." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gregtech.machine.air_collector." + tier));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_" + tier));
        }

        // Steam Machines
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

        // Multiblocks
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

        // Generators
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_lv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_mv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:diesel_generator_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:gas_turbine_hv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:steam_turbine_hv"));

        // Transformers
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_ev"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_iv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_luv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:transformer_max"));

        // Chargers
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_ev"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:charger_max"));

        // Casings
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_luv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_zpm"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_uv"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, WroughtIron, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Steel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Aluminium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, StainlessSteel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Titanium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, TungstenSteel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Chrome, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Iridium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Osmium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Darmstadtium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), OreDictUnifier.get(wireGtSingle, Superconductor, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(288)});
    }

    // TODO These need to be done better than this
    private static void highAmpMachines() {

        GATileEntities.TRANSFORMER_1_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(),
                    "KBB", "CM ", "KBB",
                    'M', WORSE_HULL.getIngredient(tier),
                    'C', CABLE_DOUBLE.getIngredient(tier),
                    'B', CABLE_DOUBLE.getIngredient(tier - 1),
                    'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.TRANSFORMER_4_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(),
                    "KBB", "CM ", "KBB",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_DOUBLE.getIngredient(tier),
                    'B', CABLE_DOUBLE.getIngredient(tier - 1),
                    'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.TRANSFORMER_8_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(),
                    "KBB", "CM ", "KBB",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_QUAD.getIngredient(tier),
                    'B', CABLE_QUAD.getIngredient(tier - 1),
                    'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.TRANSFORMER_12_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(),
                    "KBB", "CM ", "KBB",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_OCTAL.getIngredient(tier),
                    'B', CABLE_OCTAL.getIngredient(tier - 1),
                    'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.TRANSFORMER_16_AMPS.forEach(transformer -> {
            int tier = transformer.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", transformer.getMetaName()), transformer.getStackForm(),
                    "KBB", "CM ", "KBB",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_HEX.getIngredient(tier),
                    'B', CABLE_HEX.getIngredient(tier - 1),
                    'K', MetaItems.SMALL_COIL);
        });

        GATileEntities.ENERGY_INPUT_HATCH_4_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(),
                    "CM ",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_DOUBLE.getIngredient(tier));
        });

        GATileEntities.ENERGY_INPUT_HATCH_16_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(),
                    "CM ",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_QUAD.getIngredient(tier));
        });

        GATileEntities.ENERGY_INPUT_HATCH_64_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(),
                    "CM ",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_OCTAL.getIngredient(tier));
        });

        GATileEntities.ENERGY_INPUT_HATCH_128_AMPS.forEach(energyInputHatch -> {
            int tier = energyInputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyInputHatch.getMetaName()), energyInputHatch.getStackForm(),
                    "CM ",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_HEX.getIngredient(tier));
        });

        GATileEntities.ENERGY_OUTPUT_HATCH_16_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(),
                    " MC",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_DOUBLE.getIngredient(tier));
        });

        GATileEntities.ENERGY_OUTPUT_HATCH_32_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(),
                    " MC",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_QUAD.getIngredient(tier));
        });

        GATileEntities.ENERGY_OUTPUT_HATCH_64_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(),
                    " MC",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_OCTAL.getIngredient(tier));
        });

        GATileEntities.ENERGY_OUTPUT_HATCH_128_AMPS.forEach(energyOutputHatch -> {
            int tier = energyOutputHatch.getTier();
            ModHandler.addShapedRecipe(String.format("ga_%s", energyOutputHatch.getMetaName()), energyOutputHatch.getStackForm(),
                    " MC",
                    'M', HULL.getIngredient(tier),
                    'C', CABLE_HEX.getIngredient(tier));
        });
    }
}
