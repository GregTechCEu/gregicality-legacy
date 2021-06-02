package gregicadditions.recipes.categories.circuits.components;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Lime;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CrystalComponents {

    public static void init() {

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904)
                .input(wireFine, Rutherfordium, 12)
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904)
                .input(wireFine, Rutherfordium, 12)
                .input(plate, NetherStar)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(32))
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904)
                .input(wireFine, NaquadahAlloy, 8)
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_CRYSTAL.getStackForm(12))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904)
                .input(wireFine, NaquadahAlloy, 8)
                .input(plate, Graphene)
                .input(plate, RutheniumDioxide)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_RESISTOR_CRYSTAL.getStackForm(24))
                .buildAndRegister();

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680)
                .input(foil, Polybenzimidazole, 4)
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(8))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680)
                .input(foil, Polybenzimidazole, 4)
                .input(foil, NaquadahAlloy)
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(16))
                .buildAndRegister();

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(wireFine, HSSS, 8)
                .inputs(SMD_DIODE_QUANTUM.getStackForm())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_CRYSTAL.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(wireFine, HSSS, 8)
                .inputs(LanthanumCalciumManganate.getItemStack())
                .fluidInputs(Plastic.getFluid(L))
                .outputs(SMD_DIODE_CRYSTAL.getStackForm(32))
                .buildAndRegister();

        // Raw Crystal CPU (MEANT TO BE DONE ONCE)
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(480).input(gemExquisite, Olivine).fluidInputs(Rutherfordium.getFluid(L / 2)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 1000).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(480).input(gemExquisite, Emerald).fluidInputs(Rutherfordium.getFluid(L / 2)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 1000).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(480).input(gemFlawless, Olivine) .fluidInputs(Rutherfordium.getFluid(L / 2)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(480).input(gemFlawless, Emerald) .fluidInputs(Rutherfordium.getFluid(L / 2)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 750).buildAndRegister();

        // Engraved Crystal CPU
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Emerald, 10), OreDictUnifier.get(gemExquisite, Emerald)}, new FluidStack[]{Helium.getFluid(5000)});
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Olivine, 10), OreDictUnifier.get(gemExquisite, Olivine)}, new FluidStack[]{Helium.getFluid(5000)});

        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Emerald).fluidInputs(Rutherfordium.getFluid(L / 2)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Olivine).fluidInputs(Rutherfordium.getFluid(L / 2)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Emerald).fluidInputs(Helium.getFluid(1000))        .outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Olivine).fluidInputs(Helium.getFluid(1000))        .outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();

        // Carbon Fibers
        AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6)
                .input(dust, Carbon)
                .fluidInputs(Cerium.getFluid(1))
                .chancedOutput(CARBON_FIBERS.getStackForm(2), 1250, 250)
                .buildAndRegister();

        // Crystal CPU Duplication Recipe
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, ENGRAVED_CRYSTAL_CHIP.getStackForm(), OreDictUnifier.get(craftingLens, Lime));

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000)
                .inputs(ENGRAVED_CRYSTAL_CHIP.getStackForm())
                .notConsumable(craftingLens, Lime)
                .outputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm())
                .buildAndRegister();

        final MaterialStack[] lubricants = {
                new MaterialStack(Lubricant, 1L),
                new MaterialStack(DistilledWater, 3L),
                new MaterialStack(Water, 4L)};

        for (MaterialStack stack : lubricants) {

            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;

            CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(60)
                    .inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm())
                    .fluidInputs(material.getFluid(2 * multiplier))
                    .outputs(RAW_CRYSTAL_CHIP.getStackForm(2))
                    .buildAndRegister();
        }
    }
}
