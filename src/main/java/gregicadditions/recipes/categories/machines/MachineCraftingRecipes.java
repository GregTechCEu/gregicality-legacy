package gregicadditions.recipes.categories.machines;

import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockSteamCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.ModHandler.removeTieredRecipeByName;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
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
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), OreDictUnifier.get(cableGtSingle, Lead, 2)},             new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  OreDictUnifier.get(cableGtSingle, Tin, 2)},              new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, Copper, 2)},           new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  OreDictUnifier.get(cableGtSingle, AnnealedCopper, 2)},   new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  OreDictUnifier.get(cableGtSingle, Gold, 2)},             new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  OreDictUnifier.get(cableGtSingle, Aluminium, 2)},        new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  OreDictUnifier.get(cableGtSingle, Tungsten, 2)},         new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)},  new FluidStack[]{Polyethylene.getFluid(L * 2)});
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

        ModHandler.addShapedRecipe("hull_ulv", MetaTileEntities.HULL[ULV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), 'C', new UnificationEntry(cableGtSingle, Lead),                    'H', new UnificationEntry(plate, WroughtIron),            'P', new UnificationEntry(plate, Wood));
        ModHandler.addShapedRecipe("hull_lv",  MetaTileEntities.HULL[LV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV),  'C', new UnificationEntry(cableGtSingle, Tin),                     'H', new UnificationEntry(plate, Steel),                  'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("hull_mv",  MetaTileEntities.HULL[MV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV),  'C', new UnificationEntry(cableGtSingle, Copper),                  'H', new UnificationEntry(plate, Aluminium),              'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("hull_hv",  MetaTileEntities.HULL[HV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV),  'C', new UnificationEntry(cableGtSingle, Gold),                    'H', new UnificationEntry(plate, StainlessSteel),         'P', new UnificationEntry(plate, Polyethylene));
        ModHandler.addShapedRecipe("hull_ev",  MetaTileEntities.HULL[EV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV),  'C', new UnificationEntry(cableGtSingle, Aluminium),               'H', new UnificationEntry(plate, Titanium),               'P', new UnificationEntry(plate, Polyethylene));
        ModHandler.addShapedRecipe("hull_iv",  MetaTileEntities.HULL[IV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV),  'C', new UnificationEntry(cableGtSingle, Tungsten),                'H', new UnificationEntry(plate, TungstenSteel),          'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("hull_luv", MetaTileEntities.HULL[LuV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium),         'H', new UnificationEntry(plate, RhodiumPlatedPalladium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("hull_zpm", MetaTileEntities.HULL[ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah),                'H', new UnificationEntry(plate, Osmiridium),             'P', new UnificationEntry(plate, Polybenzimidazole));
        ModHandler.addShapedRecipe("hull_uv",  MetaTileEntities.HULL[UV].getStackForm(),  "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV),  'C', new UnificationEntry(cableGtSingle, NaquadahAlloy),           'H', new UnificationEntry(plate, Tritanium),              'P', new UnificationEntry(plate, Polybenzimidazole));

        ASSEMBLER_RECIPES.recipeBuilder().duration(25).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV)).input(cableGtSingle, Lead, 2)           .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[0].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV)) .input(cableGtSingle, Tin, 2)            .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[1].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)) .input(cableGtSingle, Copper, 2)         .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)) .input(cableGtSingle, AnnealedCopper, 2) .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV)) .input(cableGtSingle, Gold, 2)           .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[3].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV)) .input(cableGtSingle, Aluminium, 2)      .fluidInputs(Polyethylene.getFluid(L * 2))           .outputs(MetaTileEntities.HULL[4].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV)) .input(cableGtSingle, Tungsten, 2)       .fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[5].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).input(cableGtSingle, VanadiumGallium, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[6].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).input(cableGtSingle, Naquadah, 2)       .fluidInputs(Polybenzimidazole.getFluid(L * 2))      .outputs(MetaTileEntities.HULL[7].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)) .input(cableGtSingle, NaquadahAlloy, 2)  .fluidInputs(Polybenzimidazole.getFluid(L * 2))      .outputs(MetaTileEntities.HULL[8].getStackForm()).buildAndRegister();


        // UHV+ Hulls
        ModHandler.addShapedRecipe("hull_uhv", MetaTileEntities.HULL[UHV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV),  'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium), 'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("hull_uev", MetaTileEntities.HULL[UEV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV),  'C', new UnificationEntry(cableGtQuadruple, Pikyonium),            'H', new UnificationEntry(plate, Bohrium),    'P', new UnificationEntry(plate, Polyetheretherketone));
        ModHandler.addShapedRecipe("hull_uiv", MetaTileEntities.HULL[UIV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV),  'C', new UnificationEntry(cableGtQuadruple, Cinobite),             'H', new UnificationEntry(plate, Quantum),    'P', new UnificationEntry(plate, Zylon));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(MetaTileEntities.HULL[UHV].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV)).input(cableGtQuadruple, Pikyonium, 2)           .fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(MetaTileEntities.HULL[UEV].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV)).input(cableGtQuadruple, Cinobite, 2)            .fluidInputs(Zylon.getFluid(L * 2))               .outputs(MetaTileEntities.HULL[UIV].getStackForm()).buildAndRegister();


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
        ModHandler.addShapedRecipe("ga_casing_uhv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
        ModHandler.addShapedRecipe("ga_casing_uev", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
        ModHandler.addShapedRecipe("ga_casing_uiv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV),  "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Seaborgium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Bohrium, 8)   .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Quantum, 8)   .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV)).buildAndRegister();
    }

    private static void misc() {

        // Hot Coolant Rotor Holders
        ModHandler.addShapedRecipe("ga_rotor_holder_hv",  GATileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[HV].getStackForm(),  'W', new UnificationEntry(wireGtHex, Gold),                 'R', new UnificationEntry(gear, BlackSteel));
        ModHandler.addShapedRecipe("ga_rotor_holder_luv", GATileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, YttriumBariumCuprate), 'R', new UnificationEntry(gear, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_rotor_holder_uhv", GATileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[UHV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Duranium),             'R', new UnificationEntry(gear, Seaborgium));

        // Custom Hatches
        ModHandler.addShapedRecipe("ga_qubit_input_hatch",  GATileEntities.QBIT_INPUT_HATCH[0].getStackForm(),  "   ", "CM ", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberHex));
        ModHandler.addShapedRecipe("ga_qubit_output_hatch", GATileEntities.QBIT_OUTPUT_HATCH[0].getStackForm(), "   ", " MC", "   ", 'M', MetaTileEntities.HULL[ZPM].getStackForm(), 'C', new UnificationEntry(opticalFiberSingle));

        //Steam Machines
        ModHandler.addShapedRecipe("ga_steam_mixer", GATileEntities.STEAM_MIXER.getStackForm(), "GRG", "GPG", "PMP", 'M', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL), 'P', new UnificationEntry(pipeSmallFluid, Bronze), 'R', new UnificationEntry(rotor, Bronze), 'G', Blocks.GLASS);
        ModHandler.addShapedRecipe("ga_steam_pump", GATileEntities.STEAM_PUMP.getStackForm(), "NLN", "NMN", "LRL", 'N', new UnificationEntry(pipeNormalFluid, Bronze), 'L', new UnificationEntry(pipeLargeFluid, Bronze), 'M', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL), 'R', new UnificationEntry(rotor, Bronze));
        ModHandler.addShapedRecipe("ga_steam_miner", GATileEntities.STEAM_MINER.getStackForm(), "DSD", "SMS", "RSR", 'M', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL), 'S', new UnificationEntry(pipeNormalFluid, Bronze), 'D', Items.DIAMOND, 'R', new UnificationEntry(rotor, Bronze));

        //Quadruple and Nonuple Inputs and Outputs
        ASSEMBLER_RECIPES.recipeBuilder().EUt(120).duration(100).inputs(MetaTileEntities.HULL[HV].getStackForm()).input(pipeLargeFluid, Titanium, 4).circuitMeta(0).outputs(GATileEntities.INPUT_HATCH_MULTI.get(0).getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(120).duration(100).inputs(MetaTileEntities.HULL[HV].getStackForm()).input(pipeLargeFluid, Titanium, 4).circuitMeta(1).outputs(GATileEntities.OUTPUT_HATCH_MULTI.get(0).getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(1920).duration(100).inputs(MetaTileEntities.HULL[IV].getStackForm()).input(pipeLargeFluid, TungstenSteel, 9).circuitMeta(0).outputs(GATileEntities.INPUT_HATCH_MULTI.get(1).getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(1920).duration(100).inputs(MetaTileEntities.HULL[IV].getStackForm()).input(pipeLargeFluid, TungstenSteel, 9).circuitMeta(1).outputs(GATileEntities.OUTPUT_HATCH_MULTI.get(1).getStackForm()).buildAndRegister();


    }
}
