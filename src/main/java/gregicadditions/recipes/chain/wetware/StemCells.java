package gregicadditions.recipes.chain.wetware;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.STEM_CELLS;
import static gregicadditions.item.GAMetaItems.ULTRASONIC_HOMOGENIZER;
import static gregicadditions.recipes.GARecipeMaps.BIO_REACTOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class StemCells {
    public static void init() {

        MIXER_RECIPES.recipeBuilder()
                .input(dust, Meat)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(AnimalCells.getFluid(1000))
                .EUt(480)
                .duration(400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, Naquadria)
                .fluidInputs(AnimalCells.getFluid(1000))
                .fluidOutputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .EUt(7680)
                .duration(500)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(1))
                .EUt(30720)
                .duration(200)
                .fluidOutputs(MycGene.getFluid(1000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .EUt(30720)
                .duration(200)
                .fluidOutputs(Oct4Gene.getFluid(1000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(3))
                .EUt(30720)
                .duration(200)
                .fluidOutputs(SOX2Gene.getFluid(1000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(4))
                .EUt(30720)
                .duration(200)
                .fluidOutputs(KFL4Gene.getFluid(1000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(AnimalCells.getFluid(1000))
                .fluidInputs(GeneTherapyFluid.getFluid(1000))
                .EUt(30720)
                .duration(1000)
                .outputs(STEM_CELLS.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(STEM_CELLS.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .outputs(STEM_CELLS.getStackForm(2))
                .fluidOutputs(DepletedGrowthMedium.getFluid(500))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(DepletedGrowthMedium.getFluid(1000))
                .fluidOutputs(FermentedBiomass.getFluid(1000))
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(StreptococcusPyogenes.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(Cas9.getFluid(1000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Cas9.getFluid(1000))
                .fluidInputs(MycGene.getFluid(1000))
                .fluidInputs(Oct4Gene.getFluid(1000))
                .fluidInputs(SOX2Gene.getFluid(1000))
                .fluidInputs(KFL4Gene.getFluid(1000))
                .inputs(EschericiaColi.getItemStack())
                .fluidOutputs(GenePlasmids.getFluid(5000))
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .input("listAllmushroom", 1)
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 1))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Chitin.getFluid(1000))
                .inputs(BifidobacteriumBreve.getItemStack())
                .fluidOutputs(Chitosan.getFluid(1000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(GenePlasmids.getFluid(1000))
                .fluidInputs(Chitosan.getFluid(1000))
                .fluidOutputs(GeneTherapyFluid.getFluid(2000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();
    }
}
