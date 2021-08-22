package gregicadditions.recipes.categories;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

// todo check to see if LuV, ZPM, UV Component Recipes need to be overridden or left as-is
public class ComponentRecipes {

    public static void init() {
        pumpInit();
        motorInit();
        pistonInit();
        conveyorInit();
        robotArmInit();
        fieldGenInit();
        sensorInit();
        emitterInit();
    }

    private static void emitterInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSG)
                .input(dust, ZincSelenide, 16)
                .input(foil, Electrum, 64)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .input(dust, Fluorescein, 16)
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium)
                .input(dust, Stilbene, 16)
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS, 1)
                .input(dust, FranciumCaesiumCadmiumBromide, 16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(EMITTER_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, HDCS)
                .input(dust, RhodamineB, 16)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Ultra, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(EMITTER_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void sensorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSG)
                .input(dust, Germanium, 16)
                .input(foil, Electrum, 64)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .input(dust, LeadSelenide, 16)
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium, 1)
                .input(dust, BariumStrontiumTitanate, 16)
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS)
                .input(dust, LeadScandiumTantalate, 16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(circuit, Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(SENSOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, HDCS)
                .input(dust, MagnetorestrictiveAlloy, 16)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, Ultra, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(SENSOR_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void fieldGenInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(30720)
                .input(frameGt, HSSG)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireFine, Osmium, 16)
                .input(cableGtOctal, YttriumBariumCuprate, 4)
                .input(circuit, Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .input(frameGt, HSSE)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireFine, Osmium, 16)
                .input(cableGtOctal, Naquadah, 4)
                .input(circuit, Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(491520)
                .input(frameGt, Tritanium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireFine, Osmium, 64)
                .input(cableGtOctal, Duranium, 4)
                .input(circuit, Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1966080)
                .input(frameGt, Seaborgium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireFine, Osmium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 4)
                .input(circuit, Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(FIELD_GENERATOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Bohrium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireFine, Osmium, 64)
                .input(wireFine, Osmium, 64)
                .input(cableGtSingle, Pikyonium, 4)
                .input(circuit, Ultra, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(FIELD_GENERATOR_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void robotArmInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(20480)
                .input(cableGtDouble, YttriumBariumCuprate, 16)
                .input(screw, HSSG, 16)
                .input(stick, HSSG, 16)
                .input(ingot, HSSG)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_LUV.getStackForm())
                .input(circuit, Extreme, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(ROBOT_ARM_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(81920)
                .input(cableGtDouble, Naquadah, 16)
                .input(screw, HSSE, 16)
                .input(stick, HSSE, 16)
                .input(ingot, HSSE)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .inputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .input(circuit, Elite, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(ROBOT_ARM_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(327680)
                .input(cableGtDouble, Duranium, 16)
                .input(screw, Tritanium, 16)
                .input(stick, Tritanium, 16)
                .input(ingot, Tritanium)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UV.getStackForm())
                .input(circuit, Master, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1310720)
                .input(cableGtDouble, TungstenTitaniumCarbide, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UHV.getStackForm())
                .input(circuit, Ultimate, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(5242880)
                .input(cableGtDouble, Pikyonium, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UEV.getStackForm())
                .input(circuit, Superconductor, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void conveyorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm(2))
                .input(plate, HSSG, 8)
                .input(gear, HSSG, 4)
                .input(stick, HSSG, 4)
                .input(ingot, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 5))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(CONVEYOR_MODULE_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .input(plate, HSSE, 8)
                .input(gear, HSSE, 4)
                .input(stick, HSSE, 4)
                .input(ingot, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(CONVEYOR_MODULE_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .input(plate, Tritanium, 8)
                .input(gear, Tritanium, 4)
                .input(stick, Tritanium, 4)
                .input(ingot, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .input(plate, HDCS, 8)
                .input(gear, HDCS, 4)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .input(plate, HDCS, 8)
                .input(gear, HDCS, 4)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void pistonInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .input(plate, HSSG, 8)
                .input(gearSmall, HSSG, 8)
                .input(stick, HSSG, 4)
                .input(ingot, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(ELECTRIC_PISTON_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(plate, HSSE, 8)
                .input(gearSmall, HSSE, 8)
                .input(stick, HSSE, 4)
                .input(ingot, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(plate, Tritanium, 8)
                .input(gearSmall, Tritanium, 8)
                .input(stick, Tritanium, 4)
                .input(ingot, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(plate, HDCS, 8)
                .input(gearSmall, HDCS, 8)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(plate, HDCS, 8)
                .input(gearSmall, HDCS, 8)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UEV.getStackForm())
                .buildAndRegister();
    }

    private static void motorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10240)
                .outputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .input(stickLong, NeodymiumMagnetic)
                .input(stickLong, HSSG, 2)
                .input(ring, HSSG, 4)
                .input(round, HSSG, 16)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(40960)
                .outputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 16)
                .input(stickLong, HSSE, 2)
                .input(ring, HSSE, 4)
                .input(round, HSSE, 16)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(cableGtQuadruple, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(163840)
                .outputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, Tritanium, 2)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 16)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(cableGtQuadruple, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(655360)
                .outputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 2)
                .input(ring, HDCS, 4)
                .input(round, HDCS, 16)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(cableGtQuadruple, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2621440)
                .outputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 2)
                .input(ring, HDCS, 4)
                .input(round, HDCS, 16)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(cableGtQuadruple, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();
    }

    private static void pumpInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(ELECTRIC_PUMP_LUV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .input(pipeSmallFluid, Naquadah, 2)
                .input(screw, HSSG, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(pipeNormalFluid, Naquadah, 2)
                .input(screw, HSSE, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(ELECTRIC_PUMP_UV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(pipeLargeFluid, Naquadah, 2)
                .input(screw, Tritanium, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(pipeLargeFluid, Zeron100, 32)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(pipeLargeFluid, Lafium, 64)
                .input(pipeLargeFluid, Lafium, 64)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();
    }
}
