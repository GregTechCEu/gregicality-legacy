package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class Batteries {
    public static void init() {

        // 2Ni + 3H2SO4 + 6KOH -> 3K2SO4 + 2NiO2H + 6H + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1300)
                .input(dust, Nickel, 2)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidInputs(PotassiumHydroxide.getFluid(6000))
                .outputs(PotassiumSulfate.getItemStack(21))
                .outputs(NickelOxideHydroxide.getItemStack(8))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2Co + Li2CO3(H2O) + O -> 2LiCoO + CO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(4000)
                .input(dust, Cobalt, 2)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(LithiumCobaltOxide.getItemStack(6))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // BaO6S2C2F6 + Li2CO3(H2O) -> BaCO3 + 2LiCSO3F3 + H2O
        BLAST_RECIPES.recipeBuilder().duration(220).EUt(2800).blastFurnaceTemp(870)
                .inputs(BariumTriflate.getItemStack(17))
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .outputs(BariumCarbonate.getItemStack(5))
                .outputs(LithiumTriflate.getItemStack(20))
                .fluidOutputs(Steam.getFluid(1000))
                .buildAndRegister();

        //
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(820).EUt(16300)
                .inputs(BrownAlgae.getItemStack(10))
                .notConsumable(dust, Diatomite)
                .fluidInputs(SodiumCarbonateSolution.getFluid(6000))
                .outputs(Cellulose.getItemStack(84))
                .outputs(Xylose.getItemStack(20))
                .fluidOutputs(Biomass.getFluid(540))
                .fluidOutputs(SodiumAlginateSolution.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(6000))
                .fluidOutputs(Water.getFluid(6000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(290).EUt(3200)
                .fluidInputs(SodiumAlginateSolution.getFluid(2000))
                .input(dust, CalciumChloride, 3)
                .outputs(CalciumAlginate.getItemStack(39))
                .fluidOutputs(SaltWater.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(820).EUt(16300)
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .fluidInputs(CitricAcid.getFluid(5000))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(3000))
                .outputs(SiliconNanoparticles.getItemStack())
                .fluidOutputs(CarbonMonoxde.getFluid(27000))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(Methane.getFluid(25000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().duration(3200).EUt(60000)
                .inputs(Glucose.getItemStack(24))
                .inputs(StreptococcusPyogenes.getItemStack())
                .outputs(Sorbose.getItemStack(24))
                .buildAndRegister();

        // C6H12O6 + 2O -> C6H8O6 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(48000)
                .inputs(Sorbose.getItemStack(24))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .notConsumable(dust, Platinum)
                .buildAndRegister();

        // C6H6O6 + 2H -> C6H8O6
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(48000)
                .fluidInputs(DehydroascorbicAcid.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .notConsumable(dust, Nickel)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(390).EUt(81000)
                .inputs(GrapheneOxide.getItemStack(3))
                .inputs(SiliconNanoparticles.getItemStack())
                .inputs(CalciumAlginate.getItemStack())
                .input(dust, CarbonNanotubes)
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .fluidInputs(AscorbicAcid.getFluid(1000))
                .outputs(NANOSILICON_CATHODE.getStackForm())
                .fluidOutputs(DehydroascorbicAcid.getFluid(1000))
                .buildAndRegister();

        // Ga + 3Cl -> GaCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(360).EUt(750)
                .input(dust, Gallium)
                .fluidInputs(Chlorine.getFluid(3000))
                .outputs(GalliumChloride.getItemStack(4))
                .buildAndRegister();

        // 9AlCl3 + GaCl3 + 10SiO2 + 15H2O + 30NH3 + 15O -> Al9Si10O50Ga + 30NH4Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(870).EUt(64000)
                .inputs(AluminiumChloride.getItemStack(36))
                .inputs(GalliumChloride.getItemStack(4))
                .inputs(SilicaGel.getItemStack(30))
                .fluidInputs(Water.getFluid(15000))
                .fluidInputs(Ammonia.getFluid(30000))
                .fluidInputs(Oxygen.getFluid(15000))
                .outputs(Halloysite.getItemStack(90))
                .fluidOutputs(AmmoniumChloride.getFluid(30000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(200).EUt(72000)
                .inputs(Halloysite.getItemStack(9))
                .inputs(Xylose.getItemStack(40))
                .input(dust, Sulfur, 2)
                .outputs(SulfurCoatedHalloysite.getItemStack())
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        // LaF3 + BaF2 + 10C7H7F + 10CH2O -> 10H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(3200).EUt(250000)
                .inputs(LanthanumTrifluoride.getItemStack(36))
                .inputs(BariumDifluoride.getItemStack(3))
                .fluidInputs(Fluorotoluene.getFluid(10000))
                .fluidInputs(Formaldehyde.getFluid(10000))
                .outputs(FluorideBatteryElectrolyte.getItemStack(2))
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        // Ni + O -> NiO
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, Nickel)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Garnierite, 2)
                .buildAndRegister();

        // 7La2O3 + 7NiO + Ca + 2C10H16N2O8 -> 7La2NiO4 + CaO + 15CO + 5CH4 + 4NH3
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(420).EUt(240000)
                .inputs(LanthanumOxide.getItemStack(35))
                .input(dust, Garnierite, 14)
                .input(dust, Calcium)
                .fluidInputs(EDTA.getFluid(2000))
                .outputs(LanthanumNickelOxide.getItemStack(49))
                .output(dust, Quicklime, 2)
                .fluidOutputs(CarbonMonoxde.getFluid(15000))
                .fluidOutputs(Methane.getFluid(5000))
                .fluidOutputs(Ammonia.getFluid(4000))
                .buildAndRegister();

        OrePrefix plateB;
        if (GAConfig.GT6.addCurvedPlates)
            plateB = plateCurved;
        else
            plateB = plate;

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Titanium, 4)
                .input(plateB, Vanadium, 2)
                .input(cableGtSingle, Aluminium, 8)
                .inputs(NickelOxideHydroxide.getItemStack(4))
                .EUt(1920)
                .duration(150)
                .outputs(BATTERY_NIMH_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, TungstenSteel, 4)
                .input(plateB, Vanadium, 4)
                .input(cableGtSingle, Platinum, 8)
                .inputs(LithiumCobaltOxide.getItemStack(3))
                .inputs(AEROGRAPHENE.getStackForm(2))
                .EUt(1920 * 4)
                .duration(150)
                .outputs(BATTERY_SMALL_LITHIUM_ION_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, RhodiumPlatedPalladium, 4)
                .input(plateB, Vanadium, 6)
                .input(cableGtSingle, NiobiumTitanium, 8)
                .inputs(LithiumCobaltOxide.getItemStack(6))
                .inputs(AEROGRAPHENE.getStackForm(4))
                .EUt(1920 * 16)
                .duration(150)
                .outputs(BATTERY_MEDIUM_LITHIUM_ION_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, HSSS, 4)
                .input(plateB, Naquadria, 2)
                .input(cableGtSingle, Naquadah, 8)
                .inputs(LithiumCobaltOxide.getItemStack(12))
                .inputs(AEROGRAPHENE.getStackForm(8))
                .EUt(1920 * 64)
                .duration(150)
                .outputs(BATTERY_LARGE_LITHIUM_ION_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Tritanium, 4)
                .input(plateB, Naquadria, 4)
                .input(cableGtSingle, NaquadahAlloy, 8)
                .inputs(NANOSILICON_CATHODE.getStackForm())
                .inputs(SulfurCoatedHalloysite.getItemStack(13))
                .EUt(1920 * 64 * 4)
                .duration(150)
                .outputs(BATTERY_SMALL_LIS_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Seaborgium, 4)
                .input(plateB, Naquadria, 6)
                .input(cableGtSingle, AbyssalAlloy, 8)
                .inputs(NANOSILICON_CATHODE.getStackForm(2))
                .inputs(SulfurCoatedHalloysite.getItemStack(26))
                .EUt(1920 * 64 * 16)
                .duration(150)
                .outputs(BATTERY_MEDIUM_LIS_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Bohrium, 4)
                .input(cableGtSingle, TitanSteel, 8)
                .input(plateB, NaquadriaticTaranium, 2)
                .inputs(NANOSILICON_CATHODE.getStackForm(4))
                .inputs(SulfurCoatedHalloysite.getItemStack(52))
                .EUt(1920 * 64 * 64)
                .duration(150)
                .outputs(BATTERY_LARGE_LIS_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Quantum, 4)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(plateB, NaquadriaticTaranium, 4)
                .input(plate, Neutronium)
                .inputs(LanthanumNickelOxide.getItemStack(7))
                .EUt(1920 * 64 * 64 * 4)
                .duration(150)
                .outputs(BATTERY_SMALL_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, Neutronium, 6)
                .input(cableGtSingle, Neutronium, 8)
                .input(plate, Neutronium, 2)
                .inputs(LanthanumNickelOxide.getItemStack(14))
                .EUt(1920 * 64 * 64 * 16)
                .duration(150)
                .outputs(BATTERY_MEDIUM_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateB, CosmicNeutronium, 4)
                .input(cableGtSingle, UXVSuperconductor, 8)
                .input(plateB, Neutronium, 6)
                .input(plate, Neutronium, 4)
                .inputs(LanthanumNickelOxide.getItemStack(28))
                .EUt(1920 * 64 * 64 * 64)
                .duration(150)
                .outputs(BATTERY_LARGE_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .inputs(BATTERY_NIMH_EMPTY.getStackForm())
                .EUt(480)
                .duration(60)
                .outputs(BATTERY_NIMH.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(2))
                .inputs(BATTERY_SMALL_LITHIUM_ION_EMPTY.getStackForm())
                .EUt(480 * 4)
                .duration(60)
                .outputs(BATTERY_SMALL_LITHIUM_ION.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(4))
                .inputs(BATTERY_MEDIUM_LITHIUM_ION_EMPTY.getStackForm())
                .EUt(480 * 16)
                .duration(60)
                .outputs(BATTERY_MEDIUM_LITHIUM_ION.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(8))
                .inputs(BATTERY_LARGE_LITHIUM_ION_EMPTY.getStackForm())
                .EUt(480 * 64)
                .duration(60)
                .outputs(BATTERY_LARGE_LITHIUM_ION.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(8))
                .inputs(BATTERY_SMALL_LIS_EMPTY.getStackForm())
                .EUt(480 * 64 * 4)
                .duration(60)
                .outputs(BATTERY_SMALL_LIS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(16))
                .inputs(BATTERY_MEDIUM_LIS_EMPTY.getStackForm())
                .EUt(480 * 64 * 16)
                .duration(60)
                .outputs(BATTERY_MEDIUM_LIS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(LithiumTriflate.getItemStack(16))
                .inputs(BATTERY_LARGE_LIS_EMPTY.getStackForm())
                .EUt(480 * 64 * 64)
                .duration(60)
                .outputs(BATTERY_LARGE_LIS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(FluorideBatteryElectrolyte.getItemStack())
                .inputs(BATTERY_SMALL_FLUORIDE_EMPTY.getStackForm())
                .EUt(480 * 64 * 64 * 4)
                .duration(60)
                .outputs(BATTERY_SMALL_FLUORIDE.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(FluorideBatteryElectrolyte.getItemStack(2))
                .inputs(BATTERY_MEDIUM_FLUORIDE_EMPTY.getStackForm())
                .EUt(480 * 64 * 64 * 16)
                .duration(60)
                .outputs(BATTERY_MEDIUM_FLUORIDE.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(FluorideBatteryElectrolyte.getItemStack(4))
                .inputs(BATTERY_LARGE_FLUORIDE_EMPTY.getStackForm())
                .EUt(480 * 64 * 64 * 64)
                .duration(60)
                .outputs(BATTERY_LARGE_FLUORIDE.getStackForm())
                .buildAndRegister();


        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? MAX_BATTERY : ZPM2).getStackForm();
        if (GAConfig.GT5U.enableZPMandUVBats) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(100000)
                    .input(plate, Rutherfordium, 16)
                    .input(circuit, MarkerMaterials.Tier.Ultimate)
                    .input(circuit, MarkerMaterials.Tier.Ultimate)
                    .input(circuit, MarkerMaterials.Tier.Ultimate)
                    .input(circuit, MarkerMaterials.Tier.Ultimate)
                    .inputs(ENERGY_LAPOTRONIC_ORB2.getStackForm(8))
                    .inputs(FIELD_GENERATOR_LUV.getStackForm(2))
                    .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64))
                    .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64))
                    .inputs(SMD_DIODE.getStackForm(8))
                    .input(cableGtSingle, Naquadah, 32)
                    .fluidInputs(SolderingAlloy.getFluid(2880))
                    .fluidInputs(Water.getFluid(8000))
                    .outputs(ENERGY_MODULE.getStackForm())
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(200000)
                    .input(plate, Dubnium, 16)
                    .input(circuit, MarkerMaterials.Tier.Superconductor)
                    .input(circuit, MarkerMaterials.Tier.Superconductor)
                    .input(circuit, MarkerMaterials.Tier.Superconductor)
                    .input(circuit, MarkerMaterials.Tier.Superconductor)
                    .inputs(ENERGY_MODULE.getStackForm(8))
                    .inputs(FIELD_GENERATOR_ZPM.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .inputs(SMD_DIODE.getStackForm(16))
                    .input(cableGtSingle, NaquadahAlloy, 32)
                    .fluidInputs(SolderingAlloy.getFluid(2880))
                    .fluidInputs(Water.getFluid(16000))
                    .outputs(ENERGY_CLUSTER.getStackForm())
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000)
                    .input(plate, Neutronium, 16)
                    .inputs(ENERGY_CLUSTER.getStackForm(8))
                    .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .input(wireGtSingle, MarkerMaterials.Tier.Superconductor, 32)
                    .inputs(SMD_DIODE.getStackForm(16))
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .fluidInputs(SolderingAlloy.getFluid(2880))
                    .fluidInputs(Water.getFluid(16000))
                    .fluidInputs(Naquadria.getFluid(1152))
                    .outputs(last_bat)
                    .buildAndRegister();
        } else {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000)
                    .input(plate, Neutronium, 16)
                    .inputs(ENERGY_LAPOTRONIC_ORB2.getStackForm(8))
                    .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .inputs(SMD_DIODE.getStackForm(16))
                    .input(wireGtSingle, MarkerMaterials.Tier.Superconductor, 32)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .input(circuit, MarkerMaterials.Tier.Infinite)
                    .fluidInputs(SolderingAlloy.getFluid(2880))
                    .fluidInputs(Water.getFluid(16000))
                    .outputs(last_bat)
                    .buildAndRegister();
        }
    }
}
