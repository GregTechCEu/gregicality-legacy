package gregicadditions.recipes.chain.wetware;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.BIO_REACTOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.stick;

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

        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(RedAlgaePowder.getItemStack())
                .input(dust, SodaAsh)
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

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(WaterAgarMix.getFluid(1000))
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
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stick, RhodiumPlatedPalladium)
                .outputs(ULTRASONIC_HOMOGENIZER.getStackForm())
                .EUt(30720)
                .duration(500)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(GreenAlgae.getItemStack())
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(SHEWANELLA_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT, 1, 1))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CUPRIVADUS_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.BEEF))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(ESCHERICHIA_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Milk.getFluid(1000))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.ROTTEN_FLESH))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .EUt(30720)
                .duration(2400)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(SHEWANELLA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(Shewanella.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(StreptococcusPyogenes.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BifidobacteriumBreve.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(ESCHERICHIA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(EschericiaColi.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BrevibacteriumFlavium.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(CUPRIVADUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CupriavidusNecator.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(Shewanella.getItemStack())
                .outputs(Shewanella.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BrevibacteriumFlavium.getItemStack())
                .outputs(BrevibacteriumFlavium.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(EschericiaColi.getItemStack())
                .outputs(EschericiaColi.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(StreptococcusPyogenes.getItemStack())
                .outputs(StreptococcusPyogenes.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BifidobacteriumBreve.getItemStack())
                .outputs(BifidobacteriumBreve.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(CupriavidusNecator.getItemStack())
                .outputs(CupriavidusNecator.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();
    }
}
