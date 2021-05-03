package gregicadditions.recipes.categories;

import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.item.*;
import gregicadditions.item.components.*;
import gregicadditions.machines.GATileEntities;
import gregicadditions.recipes.helper.GACraftingComponents;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAValues.*;
import static gregicadditions.item.CellCasing.CellType.*;
import static gregicadditions.item.GAHeatingCoil.CoilType.*;
import static gregicadditions.item.GAMetaItems.MICA_INSULATOR_FOIL;
import static gregicadditions.item.GAMultiblockCasing.CasingType.*;
import static gregicadditions.item.GAMultiblockCasing2.CasingType.*;
import static gregicadditions.item.fusion.GAFusionCasing.CasingType.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.helper.AdditionMethods.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregicadditions.item.GATransparentCasing.CasingType.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.*;
import static gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.*;
import static gregtech.common.items.MetaItems.*;

public class CasingRecipes {

    public static void init() {
        componentCasings();
        multiblockCasings();
        coilCasings();
        tieredCasings();
        tieredGlass();
    }

    private static void tieredCasings() {

        // Integral Frameworks
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(circuit, Primitive, 2)
                .input(gear, Potin, 8)
                .input(plate, Potin, 8)
                .input(cableGtOctal, Tin)
                .inputs(MetaTileEntities.HULL[ULV].getStackForm())
                .fluidInputs(Steel.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_ULV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(32)
                .input(circuit, Basic, 2)
                .input(gear, Magnalium, 8)
                .input(plate, Magnalium, 8)
                .input(cableGtOctal, Cobalt)
                .inputs(MetaTileEntities.HULL[LV].getStackForm())
                .fluidInputs(Silicon.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_LV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(64)
                .input(circuit, Good, 2)
                .input(gear, EglinSteel, 8)
                .input(plate, EglinSteel, 8)
                .input(cableGtOctal, AnnealedCopper)
                .inputs(MetaTileEntities.HULL[MV].getStackForm())
                .fluidInputs(BabbittAlloy.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_MV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(128)
                .input(circuit, Advanced, 2)
                .input(gear, Inconel625, 8)
                .input(plate, Inconel625, 8)
                .input(cableGtOctal, Gold)
                .inputs(MetaTileEntities.HULL[HV].getStackForm())
                .fluidInputs(Inconel625.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_HV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(256)
                .input(circuit, Extreme, 2)
                .input(gear, TungstenCarbide, 8)
                .input(plate, TungstenCarbide, 8)
                .input(cableGtOctal, Titanium)
                .inputs(MetaTileEntities.HULL[EV].getStackForm())
                .fluidInputs(Stellite.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_EV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(512)
                .input(circuit, Elite, 2)
                .input(gear, Nitinol60, 8)
                .input(plate, Nitinol60, 8)
                .input(cableGtOctal, Nichrome)
                .inputs(MetaTileEntities.HULL[IV].getStackForm())
                .fluidInputs(Thorium.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_IV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(1024)
                .input(circuit, Master, 2)
                .input(gear, IncoloyMA956, 8)
                .input(plate, IncoloyMA956, 8)
                .input(cableGtOctal, Platinum)
                .inputs(MetaTileEntities.HULL[LuV].getStackForm())
                .fluidInputs(Uranium235.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_LUV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2048)
                .input(circuit, Ultimate, 2)
                .input(gear, BabbittAlloy, 8)
                .input(plate, BabbittAlloy, 8)
                .input(cableGtOctal, NiobiumTitanium)
                .inputs(MetaTileEntities.HULL[GAValues.ZPM].getStackForm())
                .fluidInputs(Plutonium241.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_ZPM))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4096)
                .input(circuit, Superconductor, 2)
                .input(gear, HG1223, 8)
                .input(plate, HG1223, 8)
                .input(cableGtOctal, YttriumBariumCuprate)
                .inputs(MetaTileEntities.HULL[UV].getStackForm())
                .fluidInputs(NaquadahEnriched.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TIERED_HULL_UV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, Infinite, 2)
                .input(gear, AbyssalAlloy, 8)
                .input(plate, AbyssalAlloy, 8)
                .input(cableGtSingle, TungstenTitaniumCarbide, 16)
                .inputs(GATileEntities.GA_HULLS[0].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(TIERED_HULL_UHV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, GAMaterials.UEV, 2)
                .input(gear, TitanSteel, 8)
                .input(plate, TitanSteel, 8)
                .input(cableGtSingle, Pikyonium, 16)
                .inputs(GATileEntities.GA_HULLS[1].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(TIERED_HULL_UEV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8192)
                .input(circuit, GAMaterials.UIV, 2)
                .input(gear, BlackTitanium, 8)
                .input(plate, BlackTitanium, 8)
                .input(cableGtSingle, Cinobite, 16)
                .inputs(GATileEntities.GA_HULLS[2].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 10))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(TIERED_HULL_UIV))
                .buildAndRegister();

        // Battery Tower Cells
        ASSEMBLER_RECIPES.recipeBuilder().EUt(240).duration(1200)
                .input(plateDense, Lead, 4)
                .fluidInputs(Oxygen.getFluid(16000))
                .input(gtMetalCasing, StainlessSteel)
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_HV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(960).duration(2400)
                .input(plateDense, Titanium, 4)
                .fluidInputs(Nitrogen.getFluid(16000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_HV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_EV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3840).duration(4800)
                .input(plateDense, TungstenSteel, 4)
                .fluidInputs(Helium.getFluid(8000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_EV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_IV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(15360).duration(9600)
                .input(plateDense, Iridium, 4)
                .fluidInputs(Argon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_IV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_LUV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(61440).duration(18200)
                .input(plateDense, NaquadahAlloy, 4)
                .fluidInputs(Radon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_LUV))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_ZPM))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(245760).duration(36400)
                .input(plateDense, Tritanium, 4)
                .fluidInputs(Xenon.getFluid(4000))
                .inputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_ZPM))
                .outputs(GAMetaBlocks.CELL_CASING.getItemVariant(CELL_UV))
                .buildAndRegister();
    }

    private static void tieredGlass() {

        final MaterialStack[] firstMetal = {
                new MaterialStack(Iron, 1),
                new MaterialStack(Nickel, 1),
                new MaterialStack(Invar, 2),
                new MaterialStack(Steel, 2),
                new MaterialStack(StainlessSteel, 3),
                new MaterialStack(Titanium, 3),
                new MaterialStack(Tungsten, 4),
                new MaterialStack(TungstenSteel, 5)
        };

        final MaterialStack[] lastMetal = {
                new MaterialStack(Tin, 0),
                new MaterialStack(Zinc, 0),
                new MaterialStack(Aluminium, 1)
        };

        // Mixed Metal Ingots
        removeRecipeByName("gregtech:ingot_mixed_metal");
        int multiplier2;
        for (MaterialStack metal1 : firstMetal) {
            IngotMaterial material1 = (IngotMaterial) metal1.material;
            int multiplier1 = (int) metal1.amount;
            for (MaterialStack metal2 : lastMetal) {
                IngotMaterial material2 = (IngotMaterial) metal2.material;
                if ((int) metal1.amount == 1) multiplier2 = 0;
                else multiplier2 = (int) metal2.amount;
                ModHandler.addShapedRecipe("mixed_metal_1_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2),
                        "F", "M", "L",
                        'F', new UnificationEntry(plate, material1),
                        'M', new UnificationEntry(plate, Bronze),
                        'L', new UnificationEntry(plate, material2));

                ModHandler.addShapedRecipe("mixed_metal_2_" + material1.toString() + "_" + material2.toString(), INGOT_MIXED_METAL.getStackForm(multiplier1 + multiplier2),
                        "F", "M", "L",
                        'F', new UnificationEntry(plate, material1),
                        'M', new UnificationEntry(plate, Brass),
                        'L', new UnificationEntry(plate, material2));

                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8)
                        .input(plate, material1)
                        .input(plank, Bronze)
                        .input(plate, material2)
                        .outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2))
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8)
                        .input(plate, material1)
                        .input(plate, Brass)
                        .input(plate, material2)
                        .outputs(INGOT_MIXED_METAL.getStackForm((multiplier1 + multiplier2) * 2))
                        .buildAndRegister();
            }
        }

        // Reinforced Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4)
                .inputs(ADVANCED_ALLOY_PLATE.getStackForm())
                .input(dust, Glass, 3)
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS, 4))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4)
                .inputs(ADVANCED_ALLOY_PLATE.getStackForm())
                .inputs(new ItemStack(Blocks.GLASS, 3))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS, 4))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS))
                .fluidInputs(BorosilicateGlass.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(BOROSILICATE_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(64)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(BOROSILICATE_GLASS))
                .fluidInputs(Nickel.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(NICKEL_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(256)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(NICKEL_GLASS))
                .fluidInputs(Chrome.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(CHROME_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(1024)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(CHROME_GLASS))
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(TUNGSTEN_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(4096)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(TUNGSTEN_GLASS))
                .fluidInputs(Iridium.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(IRIDIUM_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16384)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(IRIDIUM_GLASS))
                .fluidInputs(Osmiridium.getFluid(L))
                .outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(OSMIRIDIUM_GLASS))
                .buildAndRegister();
    }

    private static void coilCasings() {

        // Fusion Coil Recipes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(2))
                .inputs(FIELD_GENERATOR_LUV.getStackForm())
                .input(cableGtQuadruple, LuVSuperconductor, 4)
                .input(plate, Osmiridium, 2)
                .input(circuit, Master)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(FUSION_COIL))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(122880).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(4))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .input(cableGtQuadruple, ZPMSuperconductor, 4)
                .input(plate, Rutherfordium, 2)
                .input(circuit, Ultimate)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_2))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(491520).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(6))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm(2))
                .input(cableGtQuadruple, UVSuperconductor, 4)
                .input(plate, Tritanium, 2)
                .input(circuit, Superconductor)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_3))
                .buildAndRegister();

        // Standard Coils
        removeRecipeByName("gregtech:heating_coil_cupronickel");
        removeRecipeByName("gregtech:heating_coil_kanthal");
        removeRecipeByName("gregtech:heating_coil_nichrome");
        removeRecipeByName("gregtech:heating_coil_tungstensteel");
        removeRecipeByName("gregtech:heating_coil_hss_g");
        removeRecipeByName("gregtech:heating_coil_naquadah");
        removeRecipeByName("gregtech:heating_coil_naquadah_alloy");
        removeRecipeByName("gregtech:heating_coil_superconductor");
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Cupronickel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Kanthal, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Nichrome, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, TungstenSteel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, HSSG, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Naquadah, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, NaquadahAlloy, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Superconductor, 8), getIntegratedCircuit(8));

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .input(dust, AluminoSilicateWool, 12)
                .fluidInputs(Tin.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tin.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30)
                .input(wireGtDouble, Kanthal, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Copper.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(KANTHAL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120)
                .input(wireGtDouble, Nichrome, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Aluminium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(NICHROME))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                .input(wireGtDouble, TungstenSteel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Nichrome.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(TUNGSTENSTEEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(wireGtDouble, HSSG, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(HSS_G))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096)
                .input(wireGtDouble, Naquadah, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(HSSG.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(NAQUADAH))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680)
                .input(wireGtDouble, NaquadahAlloy, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Naquadah.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(NAQUADAH_ALLOY))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(500000)
                .input(wireGtDouble, TungstenTitaniumCarbide, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tritanium.getFluid(L))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(HEATING_COIL_1))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000000)
                .input(wireGtDouble, Pikyonium, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Adamantium.getFluid(L))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(HEATING_COIL_2))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8000000)
                .input(wireGtDouble, Cinobite, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Vibranium.getFluid(L))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(HEATING_COIL_3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(32000000)
                .input(wireGtDouble, Neutronium, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Neutronium.getFluid(L))
                .outputs(GAMetaBlocks.HEATING_COIL.getItemVariant(HEATING_COIL_4))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, LuVSuperconductor, 64)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(64))
                .fluidInputs(NaquadahAlloy.getFluid(L * 8))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, ZPMSuperconductor, 32)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(32))
                .fluidInputs(NaquadahAlloy.getFluid(L * 4))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, UVSuperconductor, 16)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(NaquadahAlloy.getFluid(L * 2))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, Superconductor, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(NaquadahAlloy.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();
    }

    private static void multiblockCasings() {

        // Tungstensteel Gearbox Casing
        ModHandler.addShapedRecipe("assline_casing", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TUNGSTENSTEEL_GEARBOX_CASING, 2),
                "PhP", "AFA", "PwP",
                'P', new UnificationEntry(plate, Steel),
                'A', ROBOT_ARM_IV,
                'F', new UnificationEntry(frameGt, TungstenSteel));

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8000)
                .inputs(ROBOT_ARM_IV.getStackForm(2))
                .input(plate, Steel, 4)
                .input(frameGt, TungstenSteel)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(TUNGSTENSTEEL_GEARBOX_CASING, 2))
                .buildAndRegister();

        // Assembly Line Casing
        removeRecipeByName("gregtech:casing_assembler_casing");
        ModHandler.addShapedRecipe("ga_assembler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(ASSEMBLER_CASING, 3),
                "CCC", "CFC", "CMC",
                'C', new UnificationEntry(circuit, Elite),
                'F', new UnificationEntry(frameGt, TungstenSteel),
                'M', ELECTRIC_MOTOR_IV);

        // Large Chemical Reactor Casing
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .input(gtMetalCasing, Steel)
                .fluidInputs(Polytetrafluoroethylene.getFluid(216))
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(CHEMICALLY_INERT))
                .buildAndRegister();

        // Reactor Casing
        FORMING_PRESS_RECIPES.recipeBuilder().duration(1500).EUt(500)
                .input(plateDense, Lead, 9)
                .input(plateDense, Lead, 9)
                .input(plateDense, ReactorSteel, 4)
                .input(plateDense, StainlessSteel, 2)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(CLADDED_REACTOR_CASING, 4))
                .buildAndRegister();

        // Large Assembler Casing
        ASSEMBLER_RECIPES.recipeBuilder().EUt(8000).duration(600)
                .fluidInputs(HastelloyN.getFluid(L * 4))
                .input(gtMetalCasing, Staballoy, 2)
                .input(circuit, Extreme)
                .outputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(LARGE_ASSEMBLER, 2))
                .buildAndRegister();

        // GTCE Casings
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF, 3));
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID, 3));
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF, 3));
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN, 3));
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE, 3));
        removeCraftingRecipes(MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST, 3));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Invar, 6), OreDictUnifier.get(frameGt, Invar));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Steel, 6), OreDictUnifier.get(frameGt, Steel));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Aluminium, 6), OreDictUnifier.get(frameGt, Aluminium));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, StainlessSteel, 6), OreDictUnifier.get(frameGt, StainlessSteel));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Titanium, 6), OreDictUnifier.get(frameGt, Titanium));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, TungstenSteel, 6), OreDictUnifier.get(frameGt, TungstenSteel));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, Bronze, 6)
                .input(frameGt, Bronze)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, Invar, 6)
                .input(frameGt, Invar)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, Steel, 6)
                .input(frameGt, Steel)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, Aluminium, 6)
                .input(frameGt, Aluminium)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, StainlessSteel, 6)
                .input(frameGt, StainlessSteel)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, Titanium, 6)
                .input(frameGt, Titanium)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE, 3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .circuitMeta(30)
                .input(plate, TungstenSteel, 6)
                .input(frameGt, TungstenSteel)
                .outputs(MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST, 3))
                .buildAndRegister();

        // Fusion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING),
                "PhP", "PHP", "PwP",
                'P', new UnificationEntry(plate, TungstenSteel),
                'H', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV));

        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING_MK2),
                "PhP", "PHP", "PwP",
                'P', new UnificationEntry(plate, Rutherfordium),
                'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING))
                .input(plate, Rutherfordium, 6)
                .outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING_MK2))
                .buildAndRegister();

        ModHandler.addShapedRecipe("fusion_casing_3", GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_3),
                "PhP", "PHP", "PwP",
                'P', new UnificationEntry(plate, Dubnium),
                'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING_MK2));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50)
                .inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(FUSION_CASING_MK2))
                .input(plate, Dubnium, 6)
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_3))
                .buildAndRegister();
    }

    private static void componentCasings() {

        Arrays.stream(EmitterCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.EMITTER, GAMetaBlocks.EMITTER_CASING));

        Arrays.stream(MotorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.MOTOR, GAMetaBlocks.MOTOR_CASING));

        Arrays.stream(PistonCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.PISTON, GAMetaBlocks.PISTON_CASING));

        Arrays.stream(SensorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.SENSOR, GAMetaBlocks.SENSOR_CASING));

        Arrays.stream(FieldGenCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.FIELD_GENERATOR, GAMetaBlocks.FIELD_GEN_CASING));

        Arrays.stream(PumpCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.PUMP, GAMetaBlocks.PUMP_CASING));

        Arrays.stream(ConveyorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.CONVEYOR, GAMetaBlocks.CONVEYOR_CASING));

        Arrays.stream(RobotArmCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.ROBOT_ARM, GAMetaBlocks.ROBOT_ARM_CASING));
    }

    private static <T extends Enum<T> & IStringSerializable> void registerComponentBlockRecipe(int tier, T inputComponent, GACraftingComponents inputStack, VariantBlock<T> outputCasing) {

        ItemStack stack = ((MetaItem<?>.MetaValueItem) inputStack.getIngredient(tier)).getStackForm(2);
        ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(tier);
        UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(tier);

        ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, tier - 1))).duration(200)
                .inputs(stack)
                .inputs(hull)
                .input(cableGtSingle, cable.material, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(outputCasing.getItemVariant(inputComponent))
                .buildAndRegister();
    }
}
