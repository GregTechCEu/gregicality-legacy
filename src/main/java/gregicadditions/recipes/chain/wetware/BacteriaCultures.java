package gregicadditions.recipes.chain.wetware;

import gregtech.common.items.MetaItems;

import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class BacteriaCultures {
    public static void init() {
        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Biomass.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .outputs(GreenAlgae.getItemStack())
                .outputs(RedAlgae.getItemStack())
                .outputs(BrownAlgae.getItemStack())
                .EUt(1920)
                .duration(400)
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(RedAlgae.getItemStack())
                .outputs(DryRedAlgae.getItemStack())
                .EUt(7680)
                .duration(250)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(DryRedAlgae.getItemStack())
                .outputs(RedAlgaePowder.getItemStack())
                .EUt(7680)
                .duration(75)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Sodium)
                .input(dust, Carbon)
                .outputs(SodiumCarbonate.getItemStack(2))
                .EUt(120)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(RedAlgaePowder.getItemStack())
                .inputs(SodiumCarbonate.getItemStack())
                .outputs(PreFreezeAgar.getItemStack())
                .EUt(30720)
                .duration(150)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .inputs(PreFreezeAgar.getItemStack())
                .outputs(FrozenAgarCrystals.getItemStack())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .inputs(FrozenAgarCrystals.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(WaterAgarMix.getFluid(1000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .notConsumable(MetaItems.SHAPE_MOLD_BALL)
                .outputs(Agar.getItemStack())
                .EUt(7680)
                .duration(50)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .inputs(Agar.getItemStack())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CLEAN_CULTURE.getStackForm())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gemExquisite, Quartzite)
                .input(wireFine, Gold)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stick, RhodiumPlatedPalladium)
                .outputs(ULTRASONIC_HOMOGENIZER.getStackForm())
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
    }
}
