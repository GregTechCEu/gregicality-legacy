package gregicadditions.recipes.categories.machines;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.*;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.energyconverter.utils.EnergyConverterCraftingHelper;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregtech.api.GTValues;
import gregtech.api.block.machines.BlockMachine;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
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

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAValues.ZPM;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.GTValues.EV;
import static gregtech.api.GTValues.HV;
import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.LuV;
import static gregtech.api.GTValues.MV;
import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.UV;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Superconductor;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MachineCraftingRecipes {

    public static void init() {
        MultiblockCraftingRecipes.init();
        SingleblockCraftingRecipes.init();
        hullOverride();
        misc();
    }

    private static void hullOverride() {

        // Hull Overrides
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
        ModHandler.addShapedRecipe("ga_hull_zpm", MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah),                'H', new UnificationEntry(plate, Osmiridium),             'P', new UnificationEntry(plate, Polybenzimidazole));
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


        // UHV+ Hulls
        ModHandler.addShapedRecipe("ga_hull_uhv", GATileEntities.GA_HULLS[0].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium), 'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uev", GATileEntities.GA_HULLS[1].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  'C', new UnificationEntry(cableGtQuadruple, Pikyonium),            'H', new UnificationEntry(plate, Bohrium),    'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("ga_hull_uiv", GATileEntities.GA_HULLS[2].getStackForm(), "PHP", "CMC", 'M', GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  'C', new UnificationEntry(cableGtQuadruple, Cinobite),             'H', new UnificationEntry(plate, Quantum),    'P', new UnificationEntry(plate, Zylon));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(GATileEntities.GA_HULLS[0].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)).input(cableGtQuadruple, Pikyonium, 2)           .fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(GATileEntities.GA_HULLS[1].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)).input(cableGtQuadruple, Cinobite, 2)            .fluidInputs(Zylon.getFluid(L * 2))               .outputs(GATileEntities.GA_HULLS[2].getStackForm()).buildAndRegister();


        // Casing Overrides
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


        // UHV+ Casings
        ModHandler.addShapedRecipe("ga_casing_uhv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
        ModHandler.addShapedRecipe("ga_casing_uev", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
        ModHandler.addShapedRecipe("ga_casing_uiv", GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Seaborgium, 8).outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UHV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Bohrium, 8)   .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UEV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Quantum, 8)   .outputs(GAMetaBlocks.MACHINE_CASING.getItemVariant(GAMachineCasing.CasingType.CASING_UIV)).buildAndRegister();

    }

    private static void misc() {

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

        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;
        // Drums
        ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', Items.SLIME_BALL, 'P', "plankWood", 'R', new UnificationEntry(stickLong, Iron));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).inputs(new ItemStack(Blocks.PLANKS, 4, GTValues.W)).inputs(new ItemStack(Items.SLIME_BALL)).input(stickLong, Iron, 2).outputs(GATileEntities.WOODEN_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();

        ModHandler.addShapedRecipe("bronze_drum",          GATileEntities.BRONZE_DRUM.getStackForm(),          " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Bronze),         'R', new UnificationEntry(stickLong, Bronze));
        ModHandler.addShapedRecipe("steel_drum",           GATileEntities.STEEL_DRUM.getStackForm(),           " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Steel),          'R', new UnificationEntry(stickLong, Steel));
        ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
        ModHandler.addShapedRecipe("titanium_drum",        GATileEntities.TITANIUM_DRUM.getStackForm(),        " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Titanium),       'R', new UnificationEntry(stickLong, Titanium));
        ModHandler.addShapedRecipe("tungstensteel_drum",   GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(),   " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, TungstenSteel),  'R', new UnificationEntry(stickLong, TungstenSteel));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Bronze, 4).input(stickLong, Bronze, 2).outputs(GATileEntities.BRONZE_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Steel, 4).input(stickLong, Steel, 2).outputs(GATileEntities.STEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, StainlessSteel, 4).input(stickLong, StainlessSteel, 2).outputs(GATileEntities.STAINLESS_STEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Titanium, 4).input(stickLong, Titanium, 2).outputs(GATileEntities.TITANIUM_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, TungstenSteel, 4).input(stickLong, TungstenSteel, 2).outputs(GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();

        // Crates
        ModHandler.addShapedRecipe("wooden_crate", GATileEntities.WOODEN_CRATE.getStackForm(), "RPR", "PsP", "RPR", 'P', "plankWood", 'R', new UnificationEntry(screw, Iron));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).inputs(new ItemStack(Blocks.PLANKS, 4, GTValues.W)).input(screw, Iron, 4).outputs(GATileEntities.WOODEN_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();

        ModHandler.addShapedRecipe("bronze_crate",          GATileEntities.BRONZE_CRATE.getStackForm(),          "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Bronze),         'R', new UnificationEntry(stickLong, Bronze));
        ModHandler.addShapedRecipe("steel_crate",           GATileEntities.STEEL_CRATE.getStackForm(),           "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Steel),          'R', new UnificationEntry(stickLong, Steel));
        ModHandler.addShapedRecipe("stainless_steel_crate", GATileEntities.STAINLESS_STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
        ModHandler.addShapedRecipe("titanium_crate",        GATileEntities.TITANIUM_CRATE.getStackForm(),        "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Titanium),       'R', new UnificationEntry(stickLong, Titanium));
        ModHandler.addShapedRecipe("tungstensteel_crate",   GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm(),   "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, TungstenSteel),  'R', new UnificationEntry(stickLong, TungstenSteel));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Bronze, 4).input(stickLong, Bronze, 4).outputs(GATileEntities.BRONZE_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Steel, 4).input(stickLong, Steel, 4).outputs(GATileEntities.STEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, StainlessSteel, 4).input(stickLong, StainlessSteel, 4).outputs(GATileEntities.STAINLESS_STEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Titanium, 4).input(stickLong, Titanium, 4).outputs(GATileEntities.TITANIUM_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, TungstenSteel, 4).input(stickLong, TungstenSteel, 4).outputs(GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();

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

        if (GAConfig.multis.steamMultis.useSteelMultis) {
            ModHandler.addShapedRecipe("ga_steam_hatch", GATileEntities.STEAM_HATCH.getStackForm(), "BPB", "BTB", "BPB", 'B', new UnificationEntry(plate, Steel), 'P', new UnificationEntry(pipeMedium, Steel), 'T', MetaTileEntities.STEEL_TANK.getStackForm());
            ModHandler.addShapedRecipe("ga_steam_input_bus", GATileEntities.STEAM_INPUT_BUS.getStackForm(), "BMB", "THT", "BMB", 'B', new UnificationEntry(plate, Steel), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
            ModHandler.addShapedRecipe("ga_steam_output_bus", GATileEntities.STEAM_OUTPUT_BUS.getStackForm(), "BTB", "MHM", "BTB", 'B', new UnificationEntry(plate, Steel), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        } else {
            ModHandler.addShapedRecipe("ga_steam_hatch", GATileEntities.STEAM_HATCH.getStackForm(), "BPB", "BTB", "BPB", 'B', new UnificationEntry(plate, Bronze), 'P', new UnificationEntry(pipeMedium, Bronze), 'T', MetaTileEntities.BRONZE_TANK.getStackForm());
            ModHandler.addShapedRecipe("ga_steam_input_bus", GATileEntities.STEAM_INPUT_BUS.getStackForm(), "BMB", "THT", "BMB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
            ModHandler.addShapedRecipe("ga_steam_output_bus", GATileEntities.STEAM_OUTPUT_BUS.getStackForm(), "BTB", "MHM", "BTB", 'B', new UnificationEntry(plate, Bronze), 'M', new UnificationEntry(plate, Potin), 'T', new UnificationEntry(plate, Tin), 'H', Blocks.CHEST);
        }
        //Steam Machines
        ModHandler.addShapedRecipe("ga_steam_mixer", GATileEntities.STEAM_MIXER.getStackForm(), "GRG", "GPG", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'P', new UnificationEntry(pipeSmall, Bronze), 'R', new UnificationEntry(rotor, Bronze), 'G', Blocks.GLASS);
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(pipeMedium, Bronze), 'L', new UnificationEntry(pipeLarge, Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'R', new UnificationEntry(rotor, Bronze));
        ModHandler.addShapedRecipe("ga_steam_miner", GATileEntities.STEAM_MINER.getStackForm(), "DSD", "SMS", "RSR", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'S', new UnificationEntry(pipeMedium, Bronze), 'D', Items.DIAMOND, 'R', new UnificationEntry(rotor, Bronze));

        // UHV+ Energy Input/Output Hatches
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uhv",  GATileEntities.ENERGY_INPUT[0].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uev",  GATileEntities.ENERGY_INPUT[1].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_input_hatch_uiv",  GATileEntities.ENERGY_INPUT[2].getStackForm(),  "   ", "CM ", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uhv", GATileEntities.ENERGY_OUTPUT[0].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[0].getStackForm(), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uev", GATileEntities.ENERGY_OUTPUT[1].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[1].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Pikyonium));
        ModHandler.addShapedRecipe("ga_energy_output_hatch_uiv", GATileEntities.ENERGY_OUTPUT[2].getStackForm(), "   ", " MC", "   ", 'M', GATileEntities.GA_HULLS[2].getStackForm(), 'C', new UnificationEntry(cableGtSingle, Cinobite));

    }
}
