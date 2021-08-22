package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.unification.ore.OrePrefix;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class Batteries {
    public static void init() {

        // BaO6S2C2F6 + Li2CO3(H2O) -> BaCO3 + 2LiCSO3F3 + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(480)
                .input(dust, BariumTriflate, 17)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .output(dust, BariumCarbonate, 5)
                .output(dust, LithiumTriflate, 20)
                .buildAndRegister();

        // Algae + 6Na2CO3(H2O) -> 4C6H10O5 + 2C5H10O5 + 6NaC6H7O6(H2O) + 6CO2 + 6H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(dust, BrownAlgae, 10)
                .notConsumable(dust, Diatomite)
                .fluidInputs(SodiumCarbonateSolution.getFluid(6000))
                .output(dust, Cellulose, 84)
                .output(dust, Xylose, 20)
                .fluidOutputs(Biomass.getFluid(540))
                .fluidOutputs(SodiumAlginateSolution.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(6000))
                .fluidOutputs(Water.getFluid(6000))
                .buildAndRegister();

        // 2NaC6H7O6(H2O) + CaCl2 -> CaC12H14O12 + 2NaCl(H2O)
        MIXER_RECIPES.recipeBuilder().duration(290).EUt(30)
                .fluidInputs(SodiumAlginateSolution.getFluid(2000))
                .input(dust, CalciumChloride, 3)
                .output(dust, CalciumAlginate, 39)
                .fluidOutputs(SaltWater.getFluid(4000))
                .buildAndRegister();

        // C3H10Si + 5C6H8O7 + C19H42BrN + 3NH3 -> Si + 2CO + 4NO2 + HBr + 25C2H4O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(1920)
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .fluidInputs(CitricAcid.getFluid(5000))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(3000))
                .output(dust, SiliconNanoparticles)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(Acetaldehyde.getFluid(25000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().duration(3200).EUt(120)
                .input(dust, Glucose, 24)
                .input(dust, StreptococcusPyogenes)
                .output(dust, Sorbose, 24)
                .buildAndRegister();

        // C6H12O6 -> C6H8O6 + 4H (H lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(280).EUt(480)
                .input(dust, Sorbose, 24)
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .notConsumable(dust, Platinum)
                .buildAndRegister();

        // C6H6O6 + 2H -> C6H8O6
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(DehydroascorbicAcid.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .notConsumable(dust, Nickel)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(390).EUt(7680)
                .input(dust, GrapheneOxide, 3)
                .input(dust, SiliconNanoparticles)
                .input(dust, CalciumAlginate)
                .input(dust, CarbonNanotubes)
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .fluidInputs(AscorbicAcid.getFluid(1000))
                .outputs(NANOSILICON_CATHODE.getStackForm())
                .fluidOutputs(DehydroascorbicAcid.getFluid(1000))
                .buildAndRegister();

        // Ga + 3Cl -> GaCl3
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Gallium)
                .fluidInputs(Chlorine.getFluid(3000))
                .output(dust, GalliumChloride, 4)
                .buildAndRegister();

        // 9AlCl3 + GaCl3 + 10SiO2 + 15H2O + 30NH3 + 15O -> Al9Si10O50Ga + 30NH4Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(470).EUt(7680)
                .input(dust, AluminiumChloride, 36)
                .input(dust, GalliumChloride, 4)
                .input(dust, SilicaGel, 30)
                .fluidInputs(Water.getFluid(15000))
                .fluidInputs(Ammonia.getFluid(30000))
                .fluidInputs(Oxygen.getFluid(15000))
                .output(dust, Halloysite, 90)
                .fluidOutputs(AmmoniumChloride.getFluid(30000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(dust, Halloysite, 9)
                .input(dust, Xylose, 40)
                .input(dust, Sulfur, 2)
                .output(dust, SulfurCoatedHalloysite, 9)
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        // LaF3 + BaF2 + 10C7H7F + 10CH2O -> 10H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30720)
                .input(dust, LanthanumTrifluoride, 36)
                .input(dust, BariumDifluoride, 3)
                .fluidInputs(Fluorotoluene.getFluid(10000))
                .fluidInputs(Formaldehyde.getFluid(10000))
                .output(dust, FluorideBatteryElectrolyte, 2)
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        // Ni + O -> NiO todo this may be a problem, but may be fine
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, Nickel)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Garnierite, 2)
                .buildAndRegister();

        // 7La2O3 + 7NiO + Ca + 2C10H16N2O8 -> 7La2NiO4 + CaO + 15CO + 5CH4 + 4NH3
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(420).EUt(30720)
                .input(dust, LanthanumOxide, 35)
                .input(dust, Garnierite, 14)
                .input(dust, Calcium)
                .fluidInputs(EDTA.getFluid(2000))
                .output(dust, LanthanumNickelOxide, 49)
                .output(dust, Quicklime, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(15000))
                .fluidOutputs(Methane.getFluid(5000))
                .fluidOutputs(Ammonia.getFluid(4000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Seaborgium, 4)
                .input(plate, Naquadria, 6)
                .input(cableGtSingle, AbyssalAlloy, 8)
                .inputs(NANOSILICON_CATHODE.getStackForm(2))
                .input(dust, SulfurCoatedHalloysite, 6)
                .EUt(30720 * 16)
                .duration(100)
                .outputs(BATTERY_MEDIUM_LIS_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Bohrium, 4)
                .input(cableGtSingle, TitanSteel, 8)
                .input(plate, NaquadriaticTaranium, 2)
                .inputs(NANOSILICON_CATHODE.getStackForm(4))
                .input(dust, SulfurCoatedHalloysite, 9)
                .EUt(30720 * 64)
                .duration(100)
                .outputs(BATTERY_LARGE_LIS_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Quantum, 4)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(plate, NaquadriaticTaranium, 4)
                .input(plate, Neutronium)
                .input(dust, LanthanumNickelOxide, 7)
                .EUt(122880 * 4)
                .duration(100)
                .outputs(BATTERY_SMALL_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Neutronium, 6)
                .input(cableGtSingle, Neutronium, 8)
                .input(plate, Neutronium, 2)
                .input(dust, LanthanumNickelOxide, 14)
                .EUt(122880 * 16)
                .duration(100)
                .outputs(BATTERY_MEDIUM_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, CosmicNeutronium, 4)
                .input(cableGtSingle, NeutroniumLegendariumSuperhydride, 8)
                .input(plate, Neutronium, 6)
                .input(plate, Neutronium, 4)
                .input(dust, LanthanumNickelOxide, 28)
                .EUt(122880 * 64)
                .duration(100)
                .outputs(BATTERY_LARGE_FLUORIDE_EMPTY.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .input(dust, LithiumTriflate, 32)
                .inputs(BATTERY_MEDIUM_LIS_EMPTY.getStackForm())
                .EUt(30720 * 16)
                .duration(60)
                .outputs(BATTERY_MEDIUM_LIS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .input(dust, LithiumTriflate, 64)
                .inputs(BATTERY_LARGE_LIS_EMPTY.getStackForm())
                .EUt(30720 * 64)
                .duration(60)
                .outputs(BATTERY_LARGE_LIS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .input(dust, FluorideBatteryElectrolyte)
                .inputs(BATTERY_SMALL_FLUORIDE_EMPTY.getStackForm())
                .EUt(1966080 * 4)
                .duration(60)
                .outputs(BATTERY_SMALL_FLUORIDE.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .input(dust, FluorideBatteryElectrolyte, 2)
                .inputs(BATTERY_MEDIUM_FLUORIDE_EMPTY.getStackForm())
                .EUt(1966080 * 16)
                .duration(60)
                .outputs(BATTERY_MEDIUM_FLUORIDE.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .input(dust, FluorideBatteryElectrolyte, 4)
                .inputs(BATTERY_LARGE_FLUORIDE_EMPTY.getStackForm())
                .EUt(1966080 * 64)
                .duration(60)
                .outputs(BATTERY_LARGE_FLUORIDE.getStackForm())
                .buildAndRegister();
    }
}
