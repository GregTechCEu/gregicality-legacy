package gregicadditions.recipes.chain;

import gregicadditions.recipes.multiinput.MultiInputLists;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Red;
import static gregtech.api.unification.material.MarkerMaterials.Color.White;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class WaferChain {

    public static void init() {

        // LITHOGRAPHY MASKS ===========================================================================================
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(plateDense, Aluminium))
                .notConsumable(craftingLens, Red)
                .outputs(LITHOGRAPHY_MASK_ILC.getStackForm())
                .duration(900)
                .EUt(120)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(plateDense, Aluminium))
                .notConsumable(craftingLens, White)
                .outputs(LITHOGRAPHY_MASK_CPU.getStackForm())
                .duration(900)
                .EUt(120)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(plateDense, Aluminium))
                .notConsumable(craftingLens, Silver)
                .outputs(LITHOGRAPHY_MASK_RAM.getStackForm())
                .duration(900)
                .EUt(120)
                .buildAndRegister();

        // BOULES ===========================================================================================
        BLAST_RECIPES.recipeBuilder()
                .input(block, Silicon, 16)
                .input(ingot, Rutherfordium)
                .notConsumable(new IntCircuitIngredient(1))
                .EUt(7680)
                .duration(1500)
                .blastFurnaceTemp(7200)
                .outputs(BOULE_RUTHERFORDIUM.getStackForm())
                .fluidInputs(Krypton.getFluid(8000))
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .input(block, Silicon, 32)
                .input(ingot, Dubnium)
                .notConsumable(new IntCircuitIngredient(1))
                .EUt(30720)
                .duration(1500)
                .blastFurnaceTemp(8600)
                .outputs(BOULE_DUBNIUM.getStackForm())
                .fluidInputs(Xenon.getFluid(8000))
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .input(block, Silicon, 64)
                .input(ingot, Neutronium)
                .notConsumable(new IntCircuitIngredient(1))
                .EUt(122880)
                .duration(1500)
                .blastFurnaceTemp(9100)
                .outputs(BOULE_NEUTRONIUM.getStackForm())
                .fluidInputs(Radon.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .inputs(BOULE_RUTHERFORDIUM.getStackForm())
                .outputs(WAFER_RUTHERFORDIUM.getStackForm(64))
                .EUt(6144)
                .duration(1600)
                .buildAndRegister();
        CUTTER_RECIPES.recipeBuilder()
                .inputs(BOULE_DUBNIUM.getStackForm())
                .outputs(WAFER_DUBNIUM.getStackForm(64))
                .EUt(24576)
                .duration(1600)
                .buildAndRegister();
        CUTTER_RECIPES.recipeBuilder()
                .inputs(BOULE_NEUTRONIUM.getStackForm())
                .outputs(WAFER_NEUTRONIUM.getStackForm(64))
                .EUt(98304)
                .duration(1600)
                .buildAndRegister();

        // WAFERS =============================================================================================

        /*
            ILC Wafer
        */

        // Etching
        LASER_ENGRAVER_RECIPES.recipeBuilder()
                .inputs(MetaItems.SILICON_WAFER.getStackForm())
                .notConsumable(LITHOGRAPHY_MASK_ILC)
                .outputs(ILC_WAFER_ETCHED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        //// Doping
        // Low-efficiency recipe
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(ILC_WAFER_ETCHED.getStackForm(), OreDictUnifier.get(dustSmall, GalliumArsenide))
                .fluidInputs(VeryHotNitrogen.getFluid(250))
                .outputs(ILC_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        CVD_RECIPES.recipeBuilder()
                .inputs(ILC_WAFER_ETCHED.getStackForm())
                .fluidInputs(VeryHotNitrogen.getFluid(250), MultiInputLists.N_DOPANT.getIngredient(144), MultiInputLists.P_DOPANT.getIngredient(144))
                .outputs(ILC_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(480)
                .buildAndRegister();

        // Electroplating
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(ILC_WAFER_DOPED.getStackForm(), OreDictUnifier.get(dust, AnnealedCopper))
                .fluidInputs(Chlorine.getFluid(250))
                .outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        /*
            CPU Wafer
        */

        CVD_RECIPES.recipeBuilder()
                .inputs(CPU_WAFER_ETCHED.getStackForm())
                .fluidInputs(VeryHotNitrogen.getFluid(250), MultiInputLists.N_DOPANT.getIngredient(144), MultiInputLists.P_DOPANT.getIngredient(144))
                .outputs(CPU_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(480)
                .buildAndRegister();

        // Etching
        LASER_ENGRAVER_RECIPES.recipeBuilder()
                .inputs(MetaItems.SILICON_WAFER.getStackForm())
                .notConsumable(LITHOGRAPHY_MASK_CPU)
                .outputs(CPU_WAFER_ETCHED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        //// Doping
        // Low-efficiency recipe
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(CPU_WAFER_ETCHED.getStackForm(), OreDictUnifier.get(dustSmall, GalliumArsenide))
                .fluidInputs(VeryHotNitrogen.getFluid(250))
                .outputs(CPU_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        // Etching
        LASER_ENGRAVER_RECIPES.recipeBuilder()
                .inputs(MetaItems.SILICON_WAFER.getStackForm())
                .notConsumable(LITHOGRAPHY_MASK_RAM)
                .outputs(RAM_WAFER_ETCHED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        /*
            RAM Wafer
        */

        // Etching
        LASER_ENGRAVER_RECIPES.recipeBuilder()
                .inputs(MetaItems.SILICON_WAFER.getStackForm())
                .notConsumable(LITHOGRAPHY_MASK_RAM)
                .outputs(RAM_WAFER_ETCHED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        //// Doping
        // Low-efficiency recipe
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(RAM_WAFER_ETCHED.getStackForm(), OreDictUnifier.get(dustSmall, GalliumArsenide))
                .fluidInputs(VeryHotNitrogen.getFluid(250))
                .outputs(RAM_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        CVD_RECIPES.recipeBuilder()
                .inputs(RAM_WAFER_ETCHED.getStackForm())
                .fluidInputs(VeryHotNitrogen.getFluid(250), MultiInputLists.N_DOPANT.getIngredient(144), MultiInputLists.P_DOPANT.getIngredient(144))
                .outputs(RAM_WAFER_DOPED.getStackForm())
                .duration(400)
                .EUt(480)
                .buildAndRegister();

        // Electroplating
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(RAM_WAFER_DOPED.getStackForm(), OreDictUnifier.get(dust, AnnealedCopper))
                .fluidInputs(Chlorine.getFluid(250))
                .outputs(MetaItems.RANDOM_ACCESS_MEMORY_WAFER.getStackForm())
                .duration(400)
                .EUt(120)
                .buildAndRegister();


        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(20)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(8)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(16)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Lime).outputs(MetaItems.NOR_MEMORY_CHIP_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(20)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Yellow).outputs(MetaItems.SYSTEM_ON_CHIP_WAFER.getStackForm(8)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Yellow).outputs(MetaItems.SYSTEM_ON_CHIP_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Yellow).outputs(MetaItems.SYSTEM_ON_CHIP_WAFER.getStackForm(24)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Orange).outputs(MetaItems.ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Orange).outputs(MetaItems.ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(8)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Orange).outputs(MetaItems.ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(12)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Blue).outputs(MetaItems.POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(8)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Blue).outputs(MetaItems.POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Blue).outputs(MetaItems.POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(16)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Cyan).outputs(ARAM_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Cyan).outputs(ARAM_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Cyan).outputs(ARAM_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(2)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(UHPIC_WAFER.getStackForm(4)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HASOC_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(4)).buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().inputs(UHPIC_WAFER.getStackForm()).outputs(UHPIC.getStackForm(2)).EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(ARAM_WAFER.getStackForm()).outputs(ARAM.getStackForm(16)).EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(HASOC_WAFER.getStackForm()).outputs(HASOC.getStackForm(6)).EUt(48).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(UHASOC_WAFER.getStackForm()).outputs(UHASOC.getStackForm(6)).EUt(48).duration(600).buildAndRegister();

        // MISCELLANEOUS ===============================================================================================
        FLUID_HEATER_RECIPES.recipeBuilder().duration(120).EUt(120)
                .circuitMeta(0)
                .fluidInputs(HotNitrogen.getFluid(1000))
                .fluidOutputs(VeryHotNitrogen.getFluid(1000))
                .buildAndRegister();
    }
}
