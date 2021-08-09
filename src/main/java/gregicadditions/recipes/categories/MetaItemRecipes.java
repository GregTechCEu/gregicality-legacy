package gregicadditions.recipes.categories;

import appeng.util.item.ItemList;
import gregicadditions.GAConfig;
import gregicadditions.armor.PowerlessJetpack;
import gregicadditions.item.GAExplosive;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class MetaItemRecipes {

    private static final OrePrefix plateB = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

    public static void init() {

        // Nightvision Goggles
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(128)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .inputs(SENSOR_MV.getStackForm(2))
                .inputs(EMITTER_MV.getStackForm(2))
                .inputs(BATTERY_RE_MV_LITHIUM.getStackForm())
                .inputs(INSULATING_TAPE.getStackForm(2))
                .outputs(NIGHTVISION_GOGGLES.getStackForm())
                .circuitMeta(3)
                .buildAndRegister();

        // NanoMuscle Suite
        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(7))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .circuitMeta(0)
                .outputs(NANO_MUSCLE_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(6))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .circuitMeta(1)
                .outputs(NANO_MUSCLE_SUITE_LEGGINS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced)
                .inputs(CARBON_PLATE.getStackForm(4))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .circuitMeta(2)
                .outputs(NANO_MUSCLE_SUITE_BOOTS.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(512)
                .input(circuit, Advanced, 2)
                .inputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .inputs(NIGHTVISION_GOGGLES.getStackForm())
                .inputs(CARBON_PLATE.getStackForm(5))
                .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
                .circuitMeta(3)
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
                'S', RUBBER_DROP);

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
        ModHandler.addShapedRecipe("battery_pack.lv", BATPACK_LV.getStackForm(),
                "BPB", "BCB", "B B",
                'B', BATTERY_RE_LV_LITHIUM,
                'C', new UnificationEntry(circuit, Basic),
                'P', new UnificationEntry(plate, Steel));

        ModHandler.addShapedRecipe("battery_pack.mv", BATPACK_MV.getStackForm(),
                "BPB", "BCB", "B B",
                'B', BATTERY_RE_MV_LITHIUM,
                'C', new UnificationEntry(circuit, Good),
                'P', new UnificationEntry(plate, Aluminium));

        ModHandler.addShapedRecipe("battery_pack.hv", BATPACK_HV.getStackForm(),
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
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .outputs(GRAVITATION_ENGINE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(12))
                .input(circuit, Elite, 4)
                .inputs(QUARK_TECH_SUITE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .outputs(ADVANCED_QAURK_TECH_SUITE_CHESTPLATE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(3600).EUt(8192)
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .input(wireGtSingle, IVSuperconductor, 8)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .inputs(PLATE_IRIDIUM_ALLOY.getStackForm(16))
                .input(circuit, Elite, 2)
                .inputs(ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
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
            ModHandler.addShapelessRecipe("clean_jetpack_" + fluid.getUnlocalizedName(), SEMIFLUID_JETPACK.getStackForm(), jetpack);
        }

        // Pyrolytic Carbon
        COMPRESSOR_RECIPES.recipeBuilder().EUt(120).duration(300)
                .input(ingot, Graphite)
                .outputs(PYROLYTIC_CARBON.getStackForm())
                .buildAndRegister();

        // Schematic
        ASSEMBLER_RECIPES.recipeBuilder().duration(180).EUt(4)
                .input(circuit, Basic, 2)
                .input(plate, Steel, 2)
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
                'B', BATTERY_MEDIUM_LITHIUM_ION);

        ModHandler.addShapedRecipe("ga_prospect_tool_zpm", PROSPECT_TOOL_ZPM.getStackForm(),
                "EDS", "CTC", "PBP",
                'E', EMITTER_ZPM,
                'D', new UnificationEntry(toolHeadDrill, HSSS),
                'S', SENSOR_ZPM,
                'C', new UnificationEntry(circuit, Ultimate),
                'T', COVER_MACHINE_CONTROLLER,
                'P', new UnificationEntry(plate, HSSS),
                'B', BATTERY_LARGE_LITHIUM_ION);

        // Hand Pump
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(24)
                .inputs(LARGE_FLUID_CELL_STEEL.getStackForm())
                .inputs(ELECTRIC_MOTOR_LV.getStackForm())
                .input(stick, Steel)
                .input(ring, Rubber, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .outputs(HAND_PUMP.getStackForm())
                .buildAndRegister();

        // Freedom Wrench
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(512)
                .inputs(ELECTRIC_MOTOR_HV.getStackForm())
                .input(circuit, Advanced, 2)
                .input(stick, StainlessSteel)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .outputs(FREEDOM_WRENCH.getStackForm())
                .buildAndRegister();

        // Data Stick
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90)
                .input(circuit, Good)
                .inputs(PLASTIC_BOARD.getStackForm())
                .inputs(NAND_MEMORY_CHIP.getStackForm(32))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(wireFine, RedAlloy, 8)
                .input(plate, Plastic, 4)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(TOOL_DATA_STICK.getStackForm())
                .buildAndRegister();

        // Cooling Containers
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(380).EUt(1150000)
                .input(plateB, Steel, 64)
                .input(plateB, Steel, 64)
                .input(plate, Steel, 64)
                .input(plate, Steel, 64)
                .inputs(LASER_COOLING_UNIT.getStackForm())
                .inputs(MAGNETIC_TRAP.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder().duration(280).EUt(90000)
                .inputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .fluidInputs(Rubidium.getFluid(L * 2))
                .outputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        // Laser Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(980000)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .inputs(SMD_DIODE_BIOWARE.getStackForm())
                .input(craftingLens, Magenta)
                .input(wireFine, Gold, 3)
                .outputs(LASER_DIODE.getStackForm())
                .buildAndRegister();

        // Laser Cooling Unit
        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1200000)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .input(wireFine, Gold, 4)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .inputs(LASER_DIODE.getStackForm())
                .input(circuit, Ultimate)
                .outputs(LASER_COOLING_UNIT.getStackForm())
                .buildAndRegister();

        // Magnetic Trap
        ASSEMBLER_RECIPES.recipeBuilder().duration(480).EUt(1000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 3))
                .input(wireGtDouble, UVSuperconductor, 2)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .outputs(MAGNETIC_TRAP.getStackForm())
                .buildAndRegister();

        // Gravi Star
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{new ItemStack(Items.NETHER_STAR)}, new FluidStack[]{Darmstadtium.getFluid(L * 2)});
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680)
                .inputs(new ItemStack(Items.NETHER_STAR))
                .fluidInputs(Dubnium.getFluid(L * 2))
                .outputs(GRAVI_STAR.getStackForm())
                .buildAndRegister();

        // Unstable Star
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(122880)
                .inputs(GRAVI_STAR.getStackForm())
                .fluidInputs(Adamantium.getFluid(L * 2))
                .outputs(UNSTABLE_STAR.getStackForm())
                .buildAndRegister();

        // Hot Wrought Iron
        ModHandler.addSmeltingRecipe(new UnificationEntry(ingot, Iron),
                HOT_IRON_INGOT.getStackForm());

        ModHandler.addShapelessRecipe("ga_wrought", OreDictUnifier.get(ingot, WroughtIron),
                'h',
                HOT_IRON_INGOT.getStackForm());

        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(8).duration(16)
                .inputs(HOT_IRON_INGOT.getStackForm())
                .output(ingot, WroughtIron)
                .buildAndRegister();

        // Plant Balls
        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllmushroom", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllfruit", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllveggie", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllspice", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllgrain", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllnut", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllpepper", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllherb", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .input("listAllfiber", 8)
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(BrownAlgae.getItemStack(8))
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(RedAlgae.getItemStack(8))
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
                .inputs(GreenAlgae.getItemStack(8))
                .outputs(PLANT_BALL.getStackForm())
                .buildAndRegister();

        // Glass Tube
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(80)
                .fluidInputs(Glass.getFluid(L))
                .notConsumable(SHAPE_MOLD_BALL.getStackForm())
                .outputs(GLASS_TUBE.getStackForm())
                .buildAndRegister();

        // Mince Meat
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.PORKCHOP))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.BEEF))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.RABBIT))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.CHICKEN))
                .output(dust, Meat)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.MUTTON))
                .output(dust, Meat)
                .buildAndRegister();

        // Explosives
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .fluidInputs(NitrationMixture.getFluid(200))
                .outputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.ITNT))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(150))
                .buildAndRegister();

        ModHandler.addShapedRecipe("powder_barrel", GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.POWDER_BARREL),
                "PSP", "GGG", "PGP",
                'P', new UnificationEntry(plate, Wood),
                'S', "string",
                'G', new UnificationEntry(dust, Gunpowder));

        // Glass Lens
        ModHandler.addShapedRecipe("glass_lens", OreDictUnifier.get(lens, Glass),
                "FfF", "FGF", "FDF",
                'F', new ItemStack(Items.FLINT),
                'G', new ItemStack(Blocks.GLASS),
                'D', new ItemStack(Items.DIAMOND));

        // Extruder Shapes
        ModHandler.addShapedRecipe("shape_extruder_gear_small", SHAPE_EXTRUDER_SMALL_GEAR.getStackForm(),
                "x S", "   ", "   ",
                'S', SHAPE_EMPTY.getStackForm());

        ModHandler.addShapedRecipe("shape_extruder_rotor", SHAPE_EXTRUDER_ROTOR.getStackForm(),
                "  S", " x ", "   ",
                'S', SHAPE_EMPTY.getStackForm());

        FORMING_PRESS_RECIPES.recipeBuilder().duration(120).EUt(22)
                .inputs(SHAPE_EMPTY.getStackForm())
                .notConsumable(SHAPE_EXTRUDER_SMALL_GEAR)
                .outputs(SHAPE_EXTRUDER_SMALL_GEAR.getStackForm())
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(120).EUt(22)
                .inputs(SHAPE_EMPTY.getStackForm())
                .notConsumable(SHAPE_EXTRUDER_ROTOR)
                .outputs(SHAPE_EXTRUDER_ROTOR.getStackForm())
                .buildAndRegister();
    }
}
