package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class WaferChain {

    public static void init() {

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

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Red).outputs(MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(20)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(1)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(4)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Silver).outputs(ARAM_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(8)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue).outputs(MetaItems.NAND_MEMORY_CHIP_WAFER.getStackForm(16)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.Lime).outputs(MetaItems.NOR_MEMORY_CHIP_WAFER.getStackForm(8)).buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.White).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(12)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.White).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(16)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(WAFER_NEUTRONIUM.getStackForm()).notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.White).outputs(MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(20)).buildAndRegister();

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

    }
}
