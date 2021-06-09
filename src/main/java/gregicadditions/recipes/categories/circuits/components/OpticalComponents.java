package gregicadditions.recipes.categories.circuits.components;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class OpticalComponents {

    public static void init() {

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, LithiumNiobate, 4)
                .input(plate, Vibranium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .outputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, MetastableFlerovium, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .outputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(foil, Quantum, 4)
                .input(foil, Zylon, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .outputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Cinobite, 8)
                .inputs(SodiumSeaborgate.getItemStack(5))
                .input(dust, TriniumTitanium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .outputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        // Optical Processing Core
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(1474560)
                .inputs(OPTICAL_SOC.getStackForm())
                .inputs(SMD_DIODE_OPTICAL.getStackForm(2))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(2))
                .input(foil, Polyetheretherketone, 2)
                .inputs(LOW_FREQUENCY_LASER.getStackForm())
                .inputs(MEDIUM_FREQUENCY_LASER.getStackForm())
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm(2))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(plate,Graphene,4)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L * 6))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 3))
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .fluidInputs(FullereneDopedNanotubes.getFluid(L))
                .outputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(750000)
                .input(dustSmall, Radium)
                .input(plate, Polybenzimidazole, 4)
                .input(plate, Polyetheretherketone, 6)
                .input(plate, Steel, 4)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(ELECTRON_SOURCE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(800000)
                .input(plate, Graphene, 2)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(ROTATING_TRANSPARENT_SURFACE.getStackForm())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(3200000)
                .inputs(LithiumNiobateNanoparticles.getItemStack(2))
                .notConsumable(ROTATING_TRANSPARENT_SURFACE)
                .notConsumable(ELECTRON_SOURCE)
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(260).EUt(1600000)
                .inputs(PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE.getStackForm())
                .outputs(NON_LINEAR_OPTICAL_LENS.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(710000)
                .input(plate, Germanium)
                .fluidInputs(Zinc.getFluid(144))
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(HIGHLY_REFLECTIVE_MIRROR.getStackForm())
                .buildAndRegister();

        ItemStack[][] laser_components = {{NDYAG_ROD.getStackForm(), RED_HALIDE_LAMP.getStackForm()},
                {LUTMYVO_ROD.getStackForm(), GREEN_HALIDE_LAMP.getStackForm()},
                {PRHOYLF_ROD.getStackForm(), BLUE_HALIDE_LAMP.getStackForm()}};
        ItemStack[] lasers = {LOW_FREQUENCY_LASER.getStackForm(), MEDIUM_FREQUENCY_LASER.getStackForm(), HIGH_FREQUENCY_LASER.getStackForm()};

        for (int i = 0; i < laser_components.length; i++) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(491520)
                    .inputs(laser_components[i])
                    .inputs(HIGHLY_REFLECTIVE_MIRROR.getStackForm())
                    .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm())
                    .inputs(SMD_DIODE_OPTICAL.getStackForm())
                    .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                    .fluidInputs(SolderingAlloy.getFluid(L * 4))
                    .outputs(lasers[i])
                    .buildAndRegister();
        }

        // ZBLAN
        LARGE_MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZirconiumTetrafluoride.getItemStack(90))
                .inputs(BariumDifluoride.getItemStack(21))
                .inputs(LanthanumTrifluoride.getItemStack(8))
                .inputs(AluminiumTrifluoride.getItemStack(4))
                .input(dust, SodiumFluoride, 14)
                .outputs(ZBLANDust.getItemStack(35))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(120).duration(3000)
                .inputs(ZBLANDust.getItemStack())
                .inputs(ErbiumTrifluoride.getItemStack(4))
                .outputs(ErbiumDopedZBLANDust.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ZBLANDust.getItemStack())
                .outputs(ZBLAN.getStackForm())
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(6000)
                .fluidInputs(Argon.getFluid(1000))
                .inputs(ErbiumDopedZBLANDust.getItemStack())
                .outputs(ERBIUM_DOPED_ZBLAN.getStackForm())
                .blastFurnaceTemp(2500)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(7680).duration(2000)
                .notConsumable(SHAPE_MOLD_INGOT)
                .inputs(ZBLAN.getStackForm())
                .outputs(ZBLAN_INGOT.getStackForm())
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().EUt(120).duration(10000)
                .fluidInputs(Oxygen.getFluid(1000))
                .inputs(ZBLAN_INGOT.getStackForm())
                .outputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm())
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder().EUt(7680).duration(2000)
                .notConsumable(SHAPE_EXTRUDER_WIRE)
                .inputs(HOT_ANNEALED_ZBLAN_INGOT.getStackForm())
                .outputs(ZBLAN_FIBER.getStackForm(2))
                .buildAndRegister();

        // Optical Fiber
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(4000).duration(2000)
                .inputs(ZBLAN_FIBER.getStackForm())
                .inputs(ERBIUM_DOPED_ZBLAN.getStackForm())
                .outputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(30).duration(150)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm())
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(OreDictUnifier.get(opticalFiberSingle.toString()))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("ga_optical_double", OreDictUnifier.get(opticalFiberDouble.toString()),
                OreDictUnifier.get(opticalFiberSingle.toString()),
                OreDictUnifier.get(opticalFiberSingle.toString()));

        ModHandler.addShapelessRecipe("ga_optical_quad", OreDictUnifier.get(opticalFiberQuadruple.toString()),
                OreDictUnifier.get(opticalFiberDouble.toString()),
                OreDictUnifier.get(opticalFiberDouble.toString()));

        ModHandler.addShapelessRecipe("ga_optical_octal", OreDictUnifier.get(opticalFiberOctal.toString()),
                OreDictUnifier.get(opticalFiberQuadruple.toString()),
                OreDictUnifier.get(opticalFiberQuadruple.toString()));

        ModHandler.addShapelessRecipe("ga_optical_hex", OreDictUnifier.get(opticalFiberHex.toString()),
                OreDictUnifier.get(opticalFiberOctal.toString()),
                OreDictUnifier.get(opticalFiberOctal.toString()));
    }
}
