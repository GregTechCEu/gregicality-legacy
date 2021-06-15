package gregicadditions.recipes.categories.circuits;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Silver;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class WaferRecipes {

    public static void init() {

        // BOULES ======================================================================================================

        // Rutherfordium Boule
        BLAST_RECIPES.recipeBuilder().EUt(7680).duration(1500)
                .input(block, Silicon, 16)
                .input(ingot, Rutherfordium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(7200)
                .outputs(BOULE_RUTHERFORDIUM.getStackForm())
                .fluidInputs(Krypton.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(6144).duration(1600)
                .inputs(BOULE_RUTHERFORDIUM.getStackForm())
                .outputs(WAFER_RUTHERFORDIUM.getStackForm(64))
                .buildAndRegister();

        // Dubnium Boule
        BLAST_RECIPES.recipeBuilder().EUt(30720).duration(1500)
                .input(block, Silicon, 32)
                .input(ingot, Dubnium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(8600)
                .outputs(BOULE_DUBNIUM.getStackForm())
                .fluidInputs(Xenon.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(24576).duration(1600)
                .inputs(BOULE_DUBNIUM.getStackForm())
                .outputs(WAFER_DUBNIUM.getStackForm(64))
                .buildAndRegister();

        // Neutronium Boule
        BLAST_RECIPES.recipeBuilder().EUt(122880).duration(1500)
                .input(block, Silicon, 64)
                .input(ingot, Neutronium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(9100)
                .outputs(BOULE_NEUTRONIUM.getStackForm())
                .fluidInputs(Radon.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(98304).duration(1600)
                .inputs(BOULE_NEUTRONIUM.getStackForm())
                .outputs(WAFER_NEUTRONIUM.getStackForm(64))
                .buildAndRegister();

        // Hassium Boule
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(850000).blastFurnaceTemp(11800)
                .input(dustTiny, MetastableHassium)
                .fluidInputs(Nitrogen.getFluid(1000))
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(17000000).blastFurnaceTemp(11200)
                .input(dust, MetastableHassium, 2)
                .inputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(HASSIUM_BOULE.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(290).EUt(175000)
                .inputs(HASSIUM_BOULE.getStackForm())
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .outputs(HASSIUM_WAFER.getStackForm(8))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(345000)
                .inputs(HASSIUM_WAFER.getStackForm())
                .fluidInputs(Trichloroferane.getFluid(250))
                .outputs(COATED_HASSIUM_WAFER.getStackForm())
                .buildAndRegister();

        // WAFER ENGRAVING =============================================================================================

        // ILC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(20)).buildAndRegister();

        // ARAM Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(8)).buildAndRegister();

        // NAND Memory Water
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(8)) .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(16)).buildAndRegister();

        // NOR Memory Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Lime).outputs(NOR_MEMORY_CHIP_WAFER.getStackForm(8)).buildAndRegister();

        // CPU Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(20)).buildAndRegister();

        // RAM Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Silver).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, Silver).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, Silver).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(20)).buildAndRegister();

        // SoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(8)) .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(24)).buildAndRegister();

        // ASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(4)) .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(8)) .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(12)).buildAndRegister();

        // PIC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(8)) .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(16)).buildAndRegister();

        // UHPIC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(2)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(4)).buildAndRegister();

        // HASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680) .inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(8)).buildAndRegister();

        // UHASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720) .inputs(WAFER_DUBNIUM.getStackForm())      .notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm())   .notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(4)).buildAndRegister();

        // Misc Cutting Recipes
        CUTTER_RECIPES.recipeBuilder().inputs(UHPIC_WAFER.getStackForm()) .outputs(UHPIC.getStackForm(2)) .EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(ARAM_WAFER.getStackForm())  .outputs(ARAM.getStackForm(16)) .EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(HASOC_WAFER.getStackForm()) .outputs(HASOC.getStackForm(6)) .EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(UHASOC_WAFER.getStackForm()).outputs(UHASOC.getStackForm(6)).EUt(48).duration(600).buildAndRegister();

        // Optical SoC
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(30)
                .inputs(ZBLANDust.getItemStack())
                .fluidOutputs(LiquidZBLAN.getFluid(L))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(390).EUt(983040)
                .inputs(UHASOC_WAFER.getStackForm())
                .fluidInputs(LiquidZBLAN.getFluid(L))
                .fluidInputs(CarbonNanotubes.getFluid(L))
                .fluidInputs(SeaborgiumDopedNanotubes.getFluid(L))
                .input(dust, IndiumPhospide)
                .fluidInputs(DielectricMirrorFormationMix.getFluid(250))
                .outputs(OPTICAL_SOC_WAFER.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(280).EUt(850000)
                .inputs(OPTICAL_SOC_WAFER.getStackForm())
                .outputs(OPTICAL_SOC.getStackForm(4))
                .buildAndRegister();

        // Optical Boules
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .notConsumable(stick, Sapphire)
                .inputs(PrHoYLFNanoparticles.getItemStack())
                .fluidInputs(PrHoYLF.getFluid(L / 9))
                .outputs(PRHOYLF_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(PRHOYLF_BOULE.getStackForm())
                .outputs(PRHOYLF_ROD.getStackForm(2))
                .output(dustTiny, PrHoYLF)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(30)
                .inputs(PrHoYLFNanoparticles.getItemStack())
                .fluidOutputs(PrHoYLF.getFluid(L))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, PrHoYLF)
                .fluidOutputs(PrHoYLF.getFluid(L / 9))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .inputs(LuTmYVONanoparticles.getItemStack())
                .fluidInputs(LuTmYVO.getFluid(L / 2))
                .outputs(LUTMYVO_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(LUTMYVO_BOULE.getStackForm())
                .outputs(LUTMYVO_ROD.getStackForm(2))
                .output(dustTiny, LuTmYVO)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(30)
                .inputs(LuTmYVONanoparticles.getItemStack())
                .fluidOutputs(LuTmYVO.getFluid(L))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, LuTmYVO)
                .fluidOutputs(LuTmYVO.getFluid(L / 9))
                .buildAndRegister();

        // NdYAG Boules
        // 9Y2O3 + Nd2O3 -> 10 Neodymium Doped Yttrium
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(220).EUt(7680)
                .input(dust, YttriumOxide,45)
                .inputs(NeodymiumOxide.getItemStack(5))
                .outputs(NeodymiumDopedYttrium.getItemStack(10))
                .buildAndRegister();

        // [Al2O3  + CH2Cl2 + 2C12H27N] + Neodymium Doped Yttrium + (NH2)CO(NH2) -> 2Unprocessed Nd:YAG + 2C12H27N
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(7680)
                .fluidInputs(AluminaSolution.getFluid(1000))
                .inputs(NeodymiumDopedYttrium.getItemStack())
                .inputs(Urea.getItemStack(8))
                .fluidOutputs(UnprocessedNdYAGSolution.getFluid(2000))
                .fluidOutputs(Tributylamine.getFluid(2000))
                .buildAndRegister();

        // Unprocessed Nd:YAG -> Unprocessed Nd:YAG Dust + CH2Cl2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(7680)
                .fluidInputs(UnprocessedNdYAGSolution.getFluid(1000))
                .fluidOutputs(Dichloromethane.getFluid(1000))
                .outputs(UnprocessedNdYAGDust.getItemStack())
                .buildAndRegister();

        // Unprocessed Nd:YAG Dust -> Nd:YAG Nanoparticles
        BLAST_RECIPES.recipeBuilder().duration(3800).EUt(120).blastFurnaceTemp(300)
                .inputs(UnprocessedNdYAGDust.getItemStack())
                .outputs(NdYAGNanoparticles.getItemStack())
                .buildAndRegister();

        // Nd:YAG Nanoparticles + NdYAG -> Nd:YAG Boule
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .notConsumable(stick, Sapphire)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidInputs(NdYAG.getFluid(L / 9))
                .outputs(NDYAG_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(NDYAG_BOULE.getStackForm())
                .outputs(NDYAG_ROD.getStackForm(2))
                .output(dustTiny, NdYAG)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(30)
                .inputs(NdYAGNanoparticles.getItemStack())
                .fluidOutputs(NdYAG.getFluid(L))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, NdYAG)
                .fluidOutputs(NdYAG.getFluid(L / 9))
                .buildAndRegister();
    }
}
