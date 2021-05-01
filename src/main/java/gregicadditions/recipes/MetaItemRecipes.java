package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.armor.PowerlessJetpack;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import static gregicadditions.GAMaterials.IVSuperconductor;
import static gregicadditions.GAMaterials.RhodiumPlatedPalladium;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Plastic;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class MetaItemRecipes {

    public static void init() {

        // Nightvision Goggles
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(128)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .inputs(SENSOR_MV.getStackForm(2))
                .inputs(EMITTER_MV.getStackForm(2))
                .inputs(BATTERY_RE_MV_LITHIUM.getStackForm())
                .inputs(INSULATING_TAPE.getStackForm(2))
                .outputs(NIGHTVISION_GOGGLES.getStackForm())
                .notConsumable(new IntCircuitIngredient(3))
                .buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(7))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(6))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(4))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(2))
                .outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced, 2)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .inputs(NIGHTVISION_GOGGLES.getStackForm())
                .inputs(CARBON_PLATE.getStackForm(5))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .notConsumable(new IntCircuitIngredient(3))
                .outputs(NANO_MUSCLE_SUITE_HELMET.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1500).EUt(1024)
                .input(circuit, Advanced, 2)
                .inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .inputs(ADVANCED_IMPELLER_JETPACK.getStackForm())
                .inputs(INSULATING_TAPE.getStackForm(4))
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .outputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        // Tape
        ModHandler.addShapedRecipe("gtadditions:insulating_tape", INSULATING_TAPE.getStackForm(6),
                "RRR", "SSS",
                'R', new ItemStack(Items.PAPER),
                'S', MetaItems.RUBBER_DROP);

        // Jetpacks
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100)
                .input(circuit, Good, 6)
                .inputs(MetaTileEntities.STEEL_TANK.getStackForm())
                .inputs(ELECTRIC_PUMP_MV.getStackForm(2))
                .input(pipeSmall, Plastic, 2)
                .input(pipeMedium, Steel, 2)
                .input(plate, Aluminium)
                .input(screw, Aluminium, 4)
                .input(stick, Aluminium, 2)
                .outputs(SEMIFLUID_JETPACK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(100)
                .input(circuit, Good, 6)
                .inputs(BATTERY_RE_MV_CADMIUM.getStackForm(6))
                .inputs(IMPELLER_MV.getStackForm(4))
                .input(plate, Aluminium)
                .input(screw, Aluminium, 4)
                .input(stick, Aluminium, 2)
                .outputs(IMPELLER_JETPACK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .input(cableGtSingle, Copper)
                .inputs(ELECTRIC_MOTOR_MV.getStackForm())
                .input(stick, Steel)
                .input(rotor, Plastic, 2)
                .input(pipeMedium, Plastic)
                .outputs(IMPELLER_MV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .input(cableGtSingle, Gold)
                .inputs(ELECTRIC_MOTOR_HV.getStackForm())
                .input(stick, StainlessSteel)
                .input(rotor, Plastic, 2)
                .input(pipeMedium, Plastic)
                .outputs(IMPELLER_HV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(400)
                .input(circuit, Good, 4)
                .input(circuit, Advanced)
                .inputs(BATPACK_HV.getStackForm())
                .inputs(IMPELLER_HV.getStackForm(6))
                .inputs(BATTERY_RE_HV_CADMIUM.getStackForm())
                .input(plate, Aluminium)
                .input(screw, Aluminium, 4)
                .input(stick, Aluminium, 2)
                .outputs(ADVANCED_IMPELLER_JETPACK.getStackForm())
                .buildAndRegister();

        // Battery Packs
        ModHandler.addShapedRecipe("gtadditions:battery_pack.lv", BATPACK_LV.getStackForm(),
                "BPB", "BCB", "B B",
                'B', BATTERY_RE_LV_LITHIUM,
                'C', new UnificationEntry(circuit, Basic),
                'P', new UnificationEntry(plate, Steel));

        ModHandler.addShapedRecipe("gtadditions:battery_pack.mv", BATPACK_MV.getStackForm(),
                "BPB", "BCB", "B B",
                'B', BATTERY_RE_MV_LITHIUM,
                'C', new UnificationEntry(circuit, Good),
                'P', new UnificationEntry(plate, Aluminium));

        ModHandler.addShapedRecipe("gtadditions:battery_pack.hv", BATPACK_HV.getStackForm(),
                "BPB", "BCB", "B B",
                'B', BATTERY_RE_HV_LITHIUM,
                'C', new UnificationEntry(circuit, Advanced),
                'P', new UnificationEntry(plate, StainlessSteel));

        // QuarkTech Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input(circuit, Extreme, 2)
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(4))
                .inputs(ELECTRIC_PISTON_EV.getStackForm(2))
                .inputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
                .outputs(QUARK_TECH_SUITE_BOOTS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input(circuit, Extreme, 4)
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(6))
                .inputs(CONVEYOR_MODULE_EV.getStackForm(2))
                .inputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
                .outputs(QUARK_TECH_SUITE_LEGGINS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input(circuit, Extreme, 4)
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(8))
                .inputs(FIELD_GENERATOR_EV.getStackForm(2))
                .inputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .outputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(2400).EUt(1600)
                .input(circuit, Extreme, 2)
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(LAPOTRON_CRYSTAL.getStackForm())
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(4))
                .inputs(SENSOR_EV.getStackForm())
                .inputs(EMITTER_EV.getStackForm())
                .inputs(NANO_MUSCLE_SUITE_HELMET.getStackForm())
                .outputs(QUARK_TECH_SUITE_HELMET.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1800).EUt(7100)
                .inputs(FIELD_GENERATOR_IV.getStackForm())
                .inputs(FIELD_GENERATOR_EV.getStackForm(2))
                .input(circuit, Master, 4)
                .input(wireGtSingle, IVSuperconductor, 4)
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(4))
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GRAVITATION_ENGINE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(12))
                .input(circuit, Elite, 4)
                .inputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(16))
                .input(circuit, Elite, 2)
                .inputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        // Jetpack cleaning recipes
        // could be better, but....
        for (FuelRecipe recipe : PowerlessJetpack.fuels) {
            if (PowerlessJetpack.forbiddenFuels.contains(recipe.getRecipeFluid().getFluid())) continue;
            ItemStack jetpack = SEMIFLUID_JETPACK.getStackForm();
            IFluidHandlerItem cont = jetpack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            if (cont == null) continue;
            FluidStack fluid = recipe.getRecipeFluid();
            IFluidTankProperties[] prop = cont.getTankProperties();
            if (prop.length < 1) continue;
            if (prop[0] == null) continue;
            fluid.amount = prop[0].getCapacity();
            cont.fill(fluid, true);
            ModHandler.addShapelessRecipe("gtadditions:clean_jetpack_" + fluid.getUnlocalizedName(), SEMIFLUID_JETPACK.getStackForm(), jetpack);
        }

        // Pyrolytic Carbon
        COMPRESSOR_RECIPES.recipeBuilder().EUt(120).duration(300)
                .input(ingot, Graphite)
                .outputs(PYROLYTIC_CARBON.getStackForm())
                .buildAndRegister();

        //Schematic
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_1"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_c"));
        ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4)
                .input(circuit, Good, 4)
                .input(plate, StainlessSteel, 2)
                .outputs(SCHEMATIC.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("3x3_schematic", SCHEMATIC_3X3.getStackForm(),   "  d", " S ", "   ", 'S', SCHEMATIC);
        ModHandler.addShapedRecipe("2x2_schematic", SCHEMATIC_2X2.getStackForm(),   " d ", " S ", "   ", 'S', SCHEMATIC);
        ModHandler.addShapedRecipe("dust_schematic", SCHEMATIC_DUST.getStackForm(), "   ", " S ", "  d", 'S', SCHEMATIC);

        // Prospectors
        ModHandler.addShapedRecipe("ga_prospect_tool_mv", PROSPECT_TOOL_MV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_MV,
                'D', new UnificationEntry(toolHeadDrill, Aluminium),
                'S', SENSOR_MV,
                'C', new UnificationEntry(circuit, Good),
                'T', COVER_MACHINE_CONTROLLER,
                'P', new UnificationEntry(plate, Aluminium),
                'B', BATTERY_RE_MV_SODIUM);

        ModHandler.addShapedRecipe("ga_prospect_tool_hv", PROSPECT_TOOL_HV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_HV,
                'D', new UnificationEntry(toolHeadDrill, StainlessSteel),
                'S', SENSOR_HV,
                'C', new UnificationEntry(circuit, Advanced),
                'T', COVER_MACHINE_CONTROLLER,
                'P', new UnificationEntry(plate, StainlessSteel),
                'B', BATTERY_RE_HV_SODIUM);

        ModHandler.addShapedRecipe("ga_prospect_tool_luv", PROSPECT_TOOL_LuV.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_LUV,
                'D', new UnificationEntry(toolHeadDrill, RhodiumPlatedPalladium),
                'S', SENSOR_LUV,
                'C', new UnificationEntry(circuit, Master),
                'T', COVER_MACHINE_CONTROLLER,
                'P', new UnificationEntry(plate, RhodiumPlatedPalladium),
                'B', ENERGY_LAPOTRONIC_ORB2);

        ModHandler.addShapedRecipe("ga_prospect_tool_zpm", PROSPECT_TOOL_ZPM.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_ZPM,
                'D', new UnificationEntry(toolHeadDrill, HSSS),
                'S', SENSOR_ZPM,
                'C', new UnificationEntry(circuit, Ultimate),
                'T', COVER_MACHINE_CONTROLLER,
                'P', new UnificationEntry(plate, HSSS),
                'B', GAConfig.GT5U.enableZPMandUVBats ? ENERGY_MODULE : BATTERY_LARGE_LITHIUM_ION);

        // Hand Pump
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(24)
                .inputs(LARGE_FLUID_CELL_STEEL.getStackForm())
                .inputs(ELECTRIC_MOTOR_LV.getStackForm())
                .input(stick, Steel)
                .input(ring, Rubber, 2)
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(HAND_PUMP.getStackForm())
                .buildAndRegister();

        // Freedom Wrench
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(512)
                .inputs(ELECTRIC_MOTOR_HV.getStackForm())
                .input(circuit, Advanced, 2)
                .input(stick, StainlessSteel)
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(FREEDOM_WRENCH.getStackForm())
                .buildAndRegister();

        // Data Stick
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90)
                .input(circuit, Good)
                .inputs(PLASTIC_BOARD.getStackForm())
                .inputs(NAND_MEMORY_CHIP.getStackForm(32))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(wireFine, RedAlloy, 8)
                .input(plate, Plastic, 4)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(TOOL_DATA_STICK.getStackForm())
                .buildAndRegister();

        // Dynamite
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4)
                .inputs(new ItemStack(Items.PAPER))
                .inputs(new ItemStack(Items.STRING))
                .fluidInputs(Glyceryl.getFluid(500))
                .outputs(DYNAMITE.getStackForm())
                .buildAndRegister();

        // Lapotron Crystal Recipes
        for (GemMaterial gem : new GemMaterial[]{Lapis, Lazurite, Sodalite}) {
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(),
                    LAPOTRON_CRYSTAL.getStackForm(),
                    OreDictUnifier.get(gemExquisite, Sapphire),
                    OreDictUnifier.get(stick, gem),
                    CAPACITOR.getStackForm());
        }

        // Drilling Rig Pipe
        ASSEMBLER_RECIPES.recipeBuilder().duration(230).EUt(480)
                .input(pipeLarge, Steel)
                .input(ring, Steel)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(WELL_PIPE.getStackForm())
                .buildAndRegister();

        // Drilling Rig Drill
        ASSEMBLER_RECIPES.recipeBuilder().duration(340).EUt(480)
                .inputs(ELECTRIC_MOTOR_EV.getStackForm())
                .inputs(ELECTRIC_PUMP_EV.getStackForm())
                .input(stickLong, Steel, 6)
                .input(plate, Steel, 2)
                .input(toolHeadDrill, Steel)
                .outputs(RIG_DRILL.getStackForm())
                .buildAndRegister();
    }
}
