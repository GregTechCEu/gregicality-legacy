package gregicadditions.recipes.categories.circuits;

import gregicadditions.GAConfig;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class MagnetoRecipes {

    public static void init() {
        circuits();
        components();
    }

    private static void circuits() {
        if (!GAConfig.Misc.enableMagnetoCircuits)
            return;

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(75).EUt(30)
                .inputs(VACUUM_TUBE.getStackForm())
                .input(gem, MagnetoResonatic)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .inputs(DIODE.getStackForm(4))
                .inputs(CAPACITOR.getStackForm(4))
                .inputs(TRANSISTOR.getStackForm(4))
                .fluidInputs(SolderingAlloy.getFluid(L / 4))
                .outputs(CIRCUIT_MAGNETIC_ULV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(120)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .input(gem, MagnetoResonatic)
                .inputs(CIRCUIT_MAGNETIC_ULV.getStackForm())
                .inputs(SMD_DIODE_REFINED.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(4))
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(CIRCUIT_MAGNETIC_LV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(225).EUt(480)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .input(gem, MagnetoResonatic)
                .inputs(CIRCUIT_MAGNETIC_LV.getStackForm())
                .inputs(SMD_DIODE.getStackForm(8))
                .inputs(SMD_CAPACITOR.getStackForm(8))
                .inputs(SMD_TRANSISTOR.getStackForm(8))
                .fluidInputs(SolderingAlloy.getFluid(L * 3/4))
                .outputs(CIRCUIT_MAGNETIC_MV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .input(gem, MagnetoResonatic)
                .inputs(CIRCUIT_MAGNETIC_MV.getStackForm())
                .inputs(SMD_DIODE_NANO.getStackForm(8))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(8))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(8))
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(CIRCUIT_MAGNETIC_HV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(375).EUt(7680)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .input(gem, MagnetoResonatic)
                .inputs(CIRCUIT_MAGNETIC_HV.getStackForm())
                .inputs(SMD_DIODE_QUANTUM.getStackForm(16))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(16))
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(16))
                .fluidInputs(SolderingAlloy.getFluid(L * 5/4))
                .outputs(CIRCUIT_MAGNETIC_EV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(450).EUt(30720)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6))
                .input(gem, MagnetoResonatic, 6)
                .inputs(CIRCUIT_MAGNETIC_EV.getStackForm())
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(16))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(16))
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(CIRCUIT_MAGNETIC_IV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(525).EUt(122880)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6))
                .input(gem, MagnetoResonatic, 6)
                .inputs(CIRCUIT_MAGNETIC_IV.getStackForm())
                .inputs(SMD_DIODE_WETWARE.getStackForm(24))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(24))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(24))
                .fluidInputs(SolderingAlloy.getFluid(L * 7))
                .outputs(CIRCUIT_MAGNETIC_LUV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(491520)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6))
                .input(gemExquisite, MagnetoResonatic)
                .inputs(CIRCUIT_MAGNETIC_LUV.getStackForm())
                .inputs(SMD_DIODE_BIOWARE.getStackForm(24))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(24))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(24))
                .fluidInputs(SolderingAlloy.getFluid(L * 32))
                .outputs(CIRCUIT_MAGNETIC_ZPM.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(675).EUt(1966080)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(6))
                .input(gemExquisite, MagnetoResonatic, 6)
                .inputs(CIRCUIT_MAGNETIC_ZPM.getStackForm())
                .inputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .fluidInputs(SolderingAlloy.getFluid(L * 36))
                .outputs(CIRCUIT_MAGNETIC_UV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(750).EUt(7864320)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(12))
                .input(gemExquisite, MagnetoResonatic, 12)
                .inputs(CIRCUIT_MAGNETIC_UV.getStackForm())
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .outputs(CIRCUIT_MAGNETIC_UHV.getStackForm(4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(825).EUt(31457280)
                .inputs(IMPRINT_SUPPORTED_BOARD.getStackForm(12))
                .input(gemExquisite, MagnetoResonatic, 12)
                .inputs(CIRCUIT_MAGNETIC_UHV.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .fluidInputs(SolderingAlloy.getFluid(L * 64))
                .outputs(CIRCUIT_MAGNETIC_UEV.getStackForm(4))
                .buildAndRegister();
    }

    private static void components() {
        // Cubic Zirconia
        BLAST_RECIPES.recipeBuilder().duration(2800).EUt(120).blastFurnaceTemp(2953)
                .input(dust, Zirconium)
                .notConsumable(dust, YttriumOxide)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, CubicZirconia, 3)
                .buildAndRegister();

        // Magneto Resonatic Dust
        MIXER_RECIPES.recipeBuilder().duration(80).EUt(20)
                .input(dust, Prasiolite, 3)
                .input(dust, BismuthTellurite, 4)
                .input(dust, CubicZirconia)
                .input(dust, SteelMagnetic)
                .output(dust, MagnetoResonatic, 9)
                .buildAndRegister();

        // Gem Recipes
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680)
                .input(dust, MagnetoResonatic)
                .fluidInputs(Neon.getFluid(100))
                .output(gem, MagnetoResonatic)
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680)
                .input(dust, MagnetoResonatic)
                .fluidInputs(Krypton.getFluid(100))
                .output(gem, MagnetoResonatic)
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(gemChipped, MagnetoResonatic, 3)
                .notConsumable(craftingLens, MarkerMaterials.Color.White)
                .output(gemFlawed, MagnetoResonatic)
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(gemFlawed, MagnetoResonatic, 3)
                .notConsumable(craftingLens, MarkerMaterials.Color.White)
                .output(gem, MagnetoResonatic)
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(1200).EUt(480)
                .input(gem, MagnetoResonatic, 4)
                .notConsumable(craftingLens, MarkerMaterials.Color.White)
                .output(gemFlawless, MagnetoResonatic)
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(1920)
                .input(gemFlawless, MagnetoResonatic, 4)
                .notConsumable(craftingLens, MarkerMaterials.Color.White)
                .output(gemExquisite, MagnetoResonatic)
                .buildAndRegister();

        if (!GAConfig.Misc.enableMagnetoCircuits)
            return;

        // 2Bi + B + H -> Bi2HB
        CHEMICAL_RECIPES.recipeBuilder().duration(570).EUt(90)
                .input(dust, Bismuth, 2)
                .input(dust, Boron)
                .fluidInputs(Hydrogen.getFluid(1000))
                .output(dust, Dibismusthydroborat, 4)
                .buildAndRegister();

        // 2Bi + + 3Te -> Bi2Te3
        CHEMICAL_RECIPES.recipeBuilder().duration(161).EUt(60)
                .input(dust, Bismuth, 2)
                .input(dust, Tellurium, 3)
                .output(dust, BismuthTellurite, 5)
                .buildAndRegister();

        // Circuit Compound
        MIXER_RECIPES.recipeBuilder().duration(982).EUt(15)
                .input(dust, IndiumGalliumPhosphide)
                .input(dust, Dibismusthydroborat, 3)
                .input(dust, BismuthTellurite, 2)
                .output(dust, CircuitCompoundMK3, 6)
                .buildAndRegister();

        // Raw Imprinted Resonatic Circuit Board
        FORMING_PRESS_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, CircuitCompoundMK3, 4)
                .input(dust, MagnetoResonatic)
                .outputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm())
                .buildAndRegister();

        // Imprinted Resonatic Circuit Board
        AUTOCLAVE_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .inputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 3))
                .outputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .buildAndRegister();
    }
}
