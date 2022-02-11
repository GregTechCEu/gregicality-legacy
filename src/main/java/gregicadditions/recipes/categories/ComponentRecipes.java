package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAEnums.GAOrePrefix.round;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeCraftingRecipes;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class ComponentRecipes {

    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Rubber, 144),
            new MaterialStack(StyreneButadieneRubber, 108),
            new MaterialStack(SiliconeRubber, 72)
    };
    private static final OrePrefix roundOrScrew = GAConfig.GT6.addRounds ? round : screw;
    private static final OrePrefix plateB = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;

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

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(circuit, Basic, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(gem, Quartzite, 4)
                    .fluidInputs(Brass.getFluid(L * 2))
                    .outputs(EMITTER_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(circuit, Good, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(gem, NetherQuartz, 4)
                    .fluidInputs(Electrum.getFluid(L * 2))
                    .outputs(EMITTER_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(circuit, Advanced, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(gem, Emerald, 4)
                    .fluidInputs(Chrome.getFluid(L * 2))
                    .outputs(EMITTER_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(circuit, Extreme, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(gem, EnderPearl, 4)
                    .fluidInputs(Platinum.getFluid(L * 2))
                    .outputs(EMITTER_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(circuit, Elite, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(gem, EnderEye, 4)
                    .fluidInputs(Osmium.getFluid(L * 2))
                    .outputs(EMITTER_IV.getStackForm(16))
                    .buildAndRegister();
        }

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSG)
                .inputs(ZincSelenide.getItemStack(16))
                .input(foil, Electrum, 64)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, Master, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .inputs(Fluorescein.getItemStack(16))
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium)
                .inputs(Stilbene.getItemStack(16))
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS, 1)
                .inputs(FranciumCaesiumCadmiumBromide.getItemStack(16))
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(EMITTER_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, HDCS)
                .inputs(RhodamineB.getItemStack(16))
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(EMITTER_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HDCS)
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(EMITTER_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, HDCS)
                .inputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, ProtoAdamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UMV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .outputs(EMITTER_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void sensorInit() {

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(8000)
                    .input(circuit, Basic, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(foil, Steel, 8)
                    .input(foil, Steel, 8)
                    .input(foil, Steel, 8)
                    .input(foil, Steel, 8)
                    .input(gemExquisite, Quartzite, 2)
                    .fluidInputs(Brass.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 2))
                    .outputs(SENSOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(16000)
                    .input(circuit, Good, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(foil, Aluminium, 8)
                    .input(foil, Aluminium, 8)
                    .input(foil, Aluminium, 8)
                    .input(foil, Aluminium, 8)
                    .input(gemExquisite, NetherQuartz, 2)
                    .fluidInputs(Electrum.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 4))
                    .outputs(SENSOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(32000)
                    .input(circuit, Advanced, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(foil, StainlessSteel, 8)
                    .input(foil, StainlessSteel, 8)
                    .input(foil, StainlessSteel, 8)
                    .input(foil, StainlessSteel, 8)
                    .input(gemExquisite, Emerald, 2)
                    .fluidInputs(Chrome.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 8))
                    .outputs(SENSOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(64000)
                    .input(circuit, Extreme, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(foil, Titanium, 8)
                    .input(foil, Titanium, 8)
                    .input(foil, Titanium, 8)
                    .input(foil, Titanium, 8)
                    .input(gem, EnderPearl, 16)
                    .fluidInputs(Platinum.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 16))
                    .outputs(SENSOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(128000)
                    .input(circuit, Elite, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(foil, TungstenSteel, 8)
                    .input(foil, TungstenSteel, 8)
                    .input(foil, TungstenSteel, 8)
                    .input(foil, TungstenSteel, 8)
                    .input(gem, EnderEye, 16)
                    .fluidInputs(Osmium.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 32))
                    .outputs(SENSOR_IV.getStackForm(16))
                    .buildAndRegister();
        }

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
                .inputs(LeadSenenide.getItemStack(16))
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, Ultimate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium, 1)
                .inputs(BariumStrontiumTitanate.getItemStack(16))
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, Superconductor, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS)
                .inputs(LeadScandiumTantalate.getItemStack(16))
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(circuit, Infinite, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(SENSOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, HDCS)
                .inputs(MagnetorestrictiveAlloy.getItemStack(16))
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(2))
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(SENSOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HDCS)
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(LiquidCrystalDetector.getFluid(2500))
                .outputs(SENSOR_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, HDCS)
                .input(foil, ProtoAdamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, UMV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(FreeElectronGas.getFluid(2500))
                .outputs(SENSOR_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void fieldGenInit() {
        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(dust, EnderPearl)
                    .input(dust, EnderPearl)
                    .input(dust, EnderPearl)
                    .input(dust, EnderPearl)
                    .fluidInputs(Osmium.getFluid(L * 2))
                    .outputs(FIELD_GENERATOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(dust, EnderEye)
                    .input(dust, EnderEye)
                    .input(dust, EnderEye)
                    .input(dust, EnderEye)
                    .fluidInputs(Osmium.getFluid(L * 4))
                    .outputs(FIELD_GENERATOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .inputs(QUANTUM_EYE.getStackForm())
                    .inputs(QUANTUM_EYE.getStackForm())
                    .inputs(QUANTUM_EYE.getStackForm())
                    .inputs(QUANTUM_EYE.getStackForm())
                    .fluidInputs(Osmium.getFluid(L * 8))
                    .outputs(FIELD_GENERATOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(dust, NetherStar)
                    .input(dust, NetherStar)
                    .input(dust, NetherStar)
                    .input(dust, NetherStar)
                    .fluidInputs(Osmium.getFluid(L * 16))
                    .outputs(FIELD_GENERATOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .inputs(QUANTUM_STAR.getStackForm())
                    .inputs(QUANTUM_STAR.getStackForm())
                    .inputs(QUANTUM_STAR.getStackForm())
                    .inputs(QUANTUM_STAR.getStackForm())
                    .fluidInputs(Osmium.getFluid(L * 32))
                    .outputs(FIELD_GENERATOR_IV.getStackForm(16))
                    .buildAndRegister();
        }

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
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Osmium, 64)
                .input(wireFine, Osmium, 64)
                .input(cableGtSingle, Pikyonium, 4)
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(FIELD_GENERATOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Trinium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Trinium, 64)
                .input(wireFine, Trinium, 64)
                .input(cableGtSingle, BlackTitanium, 4)
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, Adamantium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Adamantium, 64)
                .input(wireFine, Adamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 4)
                .input(circuit, UMV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void robotArmInit() {

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(circuit, Basic, 2)
                    .input(screw, Steel, 8)
                    .input(screw, Steel, 8)
                    .input(cableGtSingle, Tin, 16)
                    .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                    .inputs(ELECTRIC_PISTON_LV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 2))
                    .outputs(ROBOT_ARM_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(circuit, Good, 2)
                    .input(screw, Aluminium, 8)
                    .input(screw, Aluminium, 8)
                    .input(cableGtSingle, Copper, 16)
                    .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                    .inputs(ELECTRIC_PISTON_MV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 4))
                    .outputs(ROBOT_ARM_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .input(circuit, Advanced, 2)
                    .input(screw, StainlessSteel, 8)
                    .input(screw, StainlessSteel, 8)
                    .input(cableGtSingle, Gold, 16)
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                    .inputs(ELECTRIC_PISTON_HV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 8))
                    .outputs(ROBOT_ARM_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(circuit, Extreme, 2)
                    .input(screw, Titanium, 8)
                    .input(screw, Titanium, 8)
                    .input(cableGtSingle, Aluminium, 16)
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                    .inputs(ELECTRIC_PISTON_EV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 16))
                    .outputs(ROBOT_ARM_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .input(circuit, Elite, 2)
                    .input(screw, TungstenSteel, 8)
                    .input(screw, TungstenSteel, 8)
                    .input(cableGtSingle, Tungsten, 16)
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                    .inputs(ELECTRIC_PISTON_IV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 32))
                    .outputs(ROBOT_ARM_IV.getStackForm(16))
                    .buildAndRegister();
        }

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

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(cableGtDouble, BlackTitanium, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UIV.getStackForm())
                .input(circuit, Infinite, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(cableGtDouble, NaquadriaticTaranium, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UMV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UMV.getStackForm())
                .input(circuit, UEV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void conveyorInit() {

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(L * 12))
                    .outputs(CONVEYOR_MODULE_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(L * 12))
                    .outputs(CONVEYOR_MODULE_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(cableGtSingle, Gold, 4)
                    .input( cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(L * 12))
                    .outputs(CONVEYOR_MODULE_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(L * 12))
                    .outputs(CONVEYOR_MODULE_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                    .fluidInputs(Rubber.getFluid(L * 12))
                    .outputs(CONVEYOR_MODULE_IV.getStackForm(16))
                    .buildAndRegister();
        }

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

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .input(plate, HDCS, 8)
                .input(gear, HDCS, 4)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .inputs(ELECTRIC_MOTOR_UMV.getStackForm(2))
                .input(plate, HDCS, 8)
                .input(gear, HDCS, 4)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void pistonInit() {

        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(cableGtSingle, Tin, 8)
                    .input(plateB, Steel, 2)
                    .input(plate, Steel, 2)
                    .input(ingot, Steel, 2)
                    .input(stickLong, Steel, 2)
                    .input(stickLong, Steel, 2)
                    .input(gearSmall, Steel, 4)
                    .input(gearSmall, Steel, 4)
                    .input(gearSmall, Steel, 4)
                    .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 2))
                    .outputs(ELECTRIC_PISTON_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(cableGtSingle, Copper, 8)
                    .input(plateB, Aluminium, 2)
                    .input(plate, Aluminium, 2)
                    .input(ingot, Aluminium, 2)
                    .input(stickLong, Aluminium, 2)
                    .input(stickLong, Aluminium, 2)
                    .input(gearSmall, Aluminium, 4)
                    .input(gearSmall, Aluminium, 4)
                    .input(gearSmall, Aluminium, 4)
                    .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 4))
                    .outputs(ELECTRIC_PISTON_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(cableGtSingle, Gold, 8)
                    .input(plateB, StainlessSteel, 2)
                    .input(plate, StainlessSteel, 2)
                    .input(ingot, StainlessSteel, 2)
                    .input(stickLong, StainlessSteel, 2)
                    .input(stickLong, StainlessSteel, 2)
                    .input(gearSmall, StainlessSteel, 4)
                    .input(gearSmall, StainlessSteel, 4)
                    .input(gearSmall, StainlessSteel, 4)
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 8))
                    .outputs(ELECTRIC_PISTON_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(cableGtSingle, Aluminium, 8)
                    .input(plateB, Titanium, 2)
                    .input(plate, Titanium, 2)
                    .input(ingot, Titanium, 2)
                    .input(stickLong, Titanium, 2)
                    .input(stickLong, Titanium, 2)
                    .input(gearSmall, Titanium, 4)
                    .input(gearSmall, Titanium, 4)
                    .input(gearSmall, Titanium, 4)
                    .input(gearSmall, Titanium, 4)
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 16))
                    .outputs(ELECTRIC_PISTON_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(cableGtSingle, Tungsten, 8)
                    .input(plateB, TungstenSteel, 2)
                    .input(plate, TungstenSteel, 2)
                    .input(ingot, TungstenSteel, 2)
                    .input(stickLong, TungstenSteel, 2)
                    .input(stickLong, TungstenSteel, 2)
                    .input(gearSmall, TungstenSteel, 4)
                    .input(gearSmall, TungstenSteel, 4)
                    .input(gearSmall, TungstenSteel, 4)
                    .input(gearSmall, TungstenSteel, 4)
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 32))
                    .outputs(ELECTRIC_PISTON_IV.getStackForm(16))
                    .buildAndRegister();
        }

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

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(plate, HDCS, 8)
                .input(gearSmall, HDCS, 8)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .inputs(ELECTRIC_MOTOR_UMV.getStackForm())
                .input(plate, HDCS, 8)
                .input(gearSmall, HDCS, 8)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UMV.getStackForm())
                .buildAndRegister();
    }

    private static void motorInit() {
        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(cableGtSingle, Tin, 4)
                    .input(stickLong, Steel, 4)
                    .input(roundOrScrew, Steel, 16)
                    .input(roundOrScrew, Steel, 16)
                    .input(roundOrScrew, Steel, 16)
                    .input(roundOrScrew, Steel, 16)
                    .input(stickLong, SteelMagnetic, 4)
                    .fluidInputs(Copper.getFluid(L * 2))
                    .outputs(ELECTRIC_MOTOR_LV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(cableGtSingle, Copper, 4)
                    .input(stickLong, Aluminium, 4)
                    .input(roundOrScrew, Aluminium, 16)
                    .input(roundOrScrew, Aluminium, 16)
                    .input(roundOrScrew, Aluminium, 16)
                    .input(roundOrScrew, Aluminium, 16)
                    .input(stickLong, SteelMagnetic, 4)
                    .fluidInputs(Copper.getFluid(L * 4))
                    .outputs(ELECTRIC_MOTOR_MV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(cableGtSingle, Gold, 4)
                    .input(stickLong, StainlessSteel, 4)
                    .input(roundOrScrew, StainlessSteel, 16)
                    .input(roundOrScrew, StainlessSteel, 16)
                    .input(roundOrScrew, StainlessSteel, 16)
                    .input(roundOrScrew, StainlessSteel, 16)
                    .input(stickLong, SteelMagnetic, 4)
                    .fluidInputs(Copper.getFluid(L * 8))
                    .outputs(ELECTRIC_MOTOR_HV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input(cableGtSingle, Aluminium, 4)
                    .input( cableGtSingle, Aluminium, 4)
                    .input(stickLong, Titanium, 4)
                    .input(roundOrScrew, Titanium, 16)
                    .input(roundOrScrew, Titanium, 16)
                    .input(roundOrScrew, Titanium, 16)
                    .input(roundOrScrew, Titanium, 16)
                    .input(stickLong, NeodymiumMagnetic, 4)
                    .fluidInputs(AnnealedCopper.getFluid(L * 16))
                    .outputs(ELECTRIC_MOTOR_EV.getStackForm(16))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(cableGtSingle, Tungsten, 4)
                    .input(stickLong, TungstenSteel, 4)
                    .input(roundOrScrew, TungstenSteel, 16)
                    .input(roundOrScrew, TungstenSteel, 16)
                    .input(roundOrScrew, TungstenSteel, 16)
                    .input(roundOrScrew, TungstenSteel, 16)
                    .input(stickLong, NeodymiumMagnetic, 4)
                    .fluidInputs(AnnealedCopper.getFluid(L * 32))
                    .outputs(ELECTRIC_MOTOR_IV.getStackForm(16))
                    .buildAndRegister();
        }

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10240)
                .outputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .input(stickLong, NeodymiumMagnetic)
                .input(stickLong, HSSG, 2)
                .input(ring, HSSG, 4)
                .input(roundOrScrew, HSSG, 16)
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
                .input(roundOrScrew, HSSE, 16)
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
                .input(roundOrScrew, Tritanium, 16)
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
                .input(roundOrScrew, HDCS, 16)
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
                .input(roundOrScrew, HDCS, 16)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(cableGtQuadruple, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .outputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 2)
                .input(ring, HDCS, 4)
                .input(roundOrScrew, HDCS, 16)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtQuadruple, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .outputs(ELECTRIC_MOTOR_UMV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 2)
                .input(ring, HDCS, 4)
                .input(roundOrScrew, HDCS, 16)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtQuadruple, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();
    }

    private static void pumpInit() {

        removeCraftingRecipes(ELECTRIC_PUMP_LV.getStackForm());
        removeCraftingRecipes(ELECTRIC_PUMP_MV.getStackForm());
        removeCraftingRecipes(ELECTRIC_PUMP_HV.getStackForm());
        removeCraftingRecipes(ELECTRIC_PUMP_EV.getStackForm());
        removeCraftingRecipes(ELECTRIC_PUMP_IV.getStackForm());

        ModHandler.addShapedRecipe("lv_electric_pump_paper", ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', new UnificationEntry(screw, Tin), 'R', new UnificationEntry(rotor, Tin), 'H', new UnificationEntry(ring, Paper), 'P', new UnificationEntry(pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(cableGtSingle, Tin));

        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            //Assembler removals
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(OrePrefix.plate, Tin, 2), OreDictUnifier.get(OrePrefix.cableGtSingle, Tin), OreDictUnifier.get(OrePrefix.screw, Tin), OreDictUnifier.get(OrePrefix.rotor, Tin), ELECTRIC_MOTOR_LV.getStackForm()}, new FluidStack[]{m.getFluid((int) stackFluid.amount)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(OrePrefix.plate, Bronze, 2), OreDictUnifier.get(OrePrefix.cableGtSingle, Copper), OreDictUnifier.get(OrePrefix.screw, Bronze), OreDictUnifier.get(OrePrefix.rotor, Bronze), ELECTRIC_MOTOR_MV.getStackForm()}, new FluidStack[]{m.getFluid((int) stackFluid.amount)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(OrePrefix.plate, Steel, 2), OreDictUnifier.get(OrePrefix.cableGtSingle, Gold), OreDictUnifier.get(OrePrefix.screw, Steel), OreDictUnifier.get(OrePrefix.rotor, Steel), ELECTRIC_MOTOR_HV.getStackForm()}, new FluidStack[]{m.getFluid((int) stackFluid.amount)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(OrePrefix.plate, StainlessSteel, 2), OreDictUnifier.get(OrePrefix.cableGtSingle, Aluminium), OreDictUnifier.get(OrePrefix.screw, StainlessSteel), OreDictUnifier.get(OrePrefix.rotor, StainlessSteel), ELECTRIC_MOTOR_EV.getStackForm()}, new FluidStack[]{m.getFluid((int) stackFluid.amount)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(OrePrefix.plate, TungstenSteel, 2), OreDictUnifier.get(OrePrefix.cableGtSingle, Tungsten), OreDictUnifier.get(OrePrefix.screw, TungstenSteel), OreDictUnifier.get(OrePrefix.rotor, TungstenSteel), ELECTRIC_MOTOR_IV.getStackForm()}, new FluidStack[]{m.getFluid((int) stackFluid.amount)});
            //Assembler additions
            ModHandler.addShapedRecipe(String.format("lv_electric_pump_%s", m.toString()), ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Tin), 'R', OreDictUnifier.get(OrePrefix.rotor, Tin), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Bronze), 'M', ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Tin));
            ModHandler.addShapedRecipe(String.format("mv_electric_pump_%s", m.toString()), ELECTRIC_PUMP_MV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Bronze), 'R', OreDictUnifier.get(OrePrefix.rotor, Bronze), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Steel), 'M', ELECTRIC_MOTOR_MV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Copper));
            ModHandler.addShapedRecipe(String.format("hv_electric_pump_%s", m.toString()), ELECTRIC_PUMP_HV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Steel), 'R', OreDictUnifier.get(OrePrefix.rotor, Steel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, StainlessSteel), 'M', ELECTRIC_MOTOR_HV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Gold));
            ModHandler.addShapedRecipe(String.format("ev_electric_pump_%s", m.toString()), ELECTRIC_PUMP_EV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, StainlessSteel), 'R', OreDictUnifier.get(OrePrefix.rotor, StainlessSteel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Titanium), 'M', ELECTRIC_MOTOR_EV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Aluminium));
            ModHandler.addShapedRecipe(String.format("iv_electric_pump_%s", m.toString()), ELECTRIC_PUMP_IV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, TungstenSteel), 'R', OreDictUnifier.get(OrePrefix.rotor, TungstenSteel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, TungstenSteel), 'M', ELECTRIC_MOTOR_IV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Tungsten));

                ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30).input(OrePrefix.rotor, Tin).input(OrePrefix.cableGtSingle, Tin).input(OrePrefix.screw, Tin).input(OrePrefix.pipeMedium, Bronze).inputs(ELECTRIC_MOTOR_LV.getStackForm()).fluidInputs(m.getFluid((int) stackFluid.amount)).outputs(ELECTRIC_PUMP_LV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(120).input(OrePrefix.rotor, Bronze).input(OrePrefix.cableGtSingle, Copper).input(OrePrefix.screw, Bronze).input(OrePrefix.pipeMedium, Steel).inputs(ELECTRIC_MOTOR_MV.getStackForm()).fluidInputs(m.getFluid((int) stackFluid.amount)).outputs(ELECTRIC_PUMP_MV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(480).input(OrePrefix.rotor, Steel).input(OrePrefix.cableGtSingle, Gold).input(OrePrefix.screw, Steel).input(OrePrefix.pipeMedium,  StainlessSteel).inputs(ELECTRIC_MOTOR_HV.getStackForm()).fluidInputs(m.getFluid((int) stackFluid.amount)).outputs(ELECTRIC_PUMP_HV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(1920).input(OrePrefix.rotor, StainlessSteel).input(OrePrefix.cableGtSingle, Aluminium).input(OrePrefix.screw, StainlessSteel).input(OrePrefix.pipeMedium, Titanium).inputs(ELECTRIC_MOTOR_EV.getStackForm()).fluidInputs(m.getFluid((int) stackFluid.amount)).outputs(ELECTRIC_PUMP_EV.getStackForm()).buildAndRegister();
                ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(7680).input(OrePrefix.rotor, TungstenSteel).input(OrePrefix.cableGtSingle, Tungsten).input(OrePrefix.screw, TungstenSteel).input(OrePrefix.pipeMedium, TungstenSteel).inputs(ELECTRIC_MOTOR_IV.getStackForm()).fluidInputs(m.getFluid((int) stackFluid.amount)).outputs(ELECTRIC_PUMP_IV.getStackForm()).buildAndRegister();

        }
        if (GAConfig.Misc.assemblyLineMakeCheaperComponents) {
            for (MaterialStack stackFluid : cableFluids) {
                IngotMaterial m = (IngotMaterial) stackFluid.material;
                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(8000)
                        .inputs(ELECTRIC_MOTOR_LV.getStackForm(4))
                        .input(cableGtSingle, Tin, 4)
                        .input(cableGtSingle, Tin, 4)
                        .input(cableGtSingle, Tin, 4)
                        .input(cableGtSingle, Tin, 4)
                        .input(pipeSmall, Bronze, 16)
                        .input(screw, Tin, 16)
                        .input(rotor, Tin, 4)
                        .input(ring, m, 8)
                        .fluidInputs(SolderingAlloy.getFluid(L * 2))
                        .outputs(ELECTRIC_PUMP_LV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(16000)
                        .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                        .input(cableGtSingle, Copper, 4)
                        .input(cableGtSingle, Copper, 4)
                        .input(cableGtSingle, Copper, 4)
                        .input(cableGtSingle, Copper, 4)
                        .input(pipeSmall, Steel, 16)
                        .input(screw, Bronze, 16)
                        .input(rotor, Bronze, 4)
                        .input( ring, m, 8)
                        .fluidInputs(SolderingAlloy.getFluid(L * 2))
                        .outputs(ELECTRIC_PUMP_MV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(32000)
                        .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                        .input(cableGtSingle, Gold, 4)
                        .input(cableGtSingle, Gold, 4)
                        .input(cableGtSingle, Gold, 4)
                        .input(cableGtSingle, Gold, 4)
                        .input(pipeSmall, StainlessSteel, 16)
                        .input(screw, Steel, 16)
                        .input(rotor, Steel, 4)
                        .input(ring, m, 8)
                        .fluidInputs(SolderingAlloy.getFluid(L * 2))
                        .outputs(ELECTRIC_PUMP_HV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(64000)
                        .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                        .input(cableGtSingle, Aluminium, 4)
                        .input(cableGtSingle, Aluminium, 4)
                        .input(cableGtSingle, Aluminium, 4)
                        .input(cableGtSingle, Aluminium, 4)
                        .input(pipeSmall, Titanium, 16)
                        .input(screw, StainlessSteel, 16)
                        .input(rotor, StainlessSteel, 4)
                        .input(ring, m, 8)
                        .fluidInputs(SolderingAlloy.getFluid(L * 2))
                        .outputs(ELECTRIC_PUMP_EV.getStackForm(16))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(6000).EUt(128000)
                        .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                        .input(cableGtSingle, Tungsten, 4)
                        .input(cableGtSingle, Tungsten, 4)
                        .input(cableGtSingle, Tungsten, 4)
                        .input(cableGtSingle, Tungsten, 4)
                        .input(pipeSmall, TungstenSteel, 16)
                        .input(screw, TungstenSteel, 16)
                        .input(rotor, TungstenSteel, 4)
                        .input(ring, m, 8)
                        .fluidInputs(SolderingAlloy.getFluid(L * 2))
                        .outputs(ELECTRIC_PUMP_IV.getStackForm(16))
                        .buildAndRegister();
            }
        }

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(ELECTRIC_PUMP_LUV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LUV.getStackForm())
                .input(pipeSmall, Ultimet, 2)
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
                .input(pipeMedium, Ultimet, 2)
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
                .input(pipeLarge, Ultimet, 2)
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
                .input(pipeLarge, Zeron100, 32)
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
                .input(pipeMedium, Lafium, 64)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .outputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(pipeMedium, TantalumHafniumSeaborgiumCarbide, 64)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .outputs(ELECTRIC_PUMP_UMV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UMV.getStackForm())
                .input(pipeLarge, EnrichedNaquadahAlloy, 64)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();
    }
}
