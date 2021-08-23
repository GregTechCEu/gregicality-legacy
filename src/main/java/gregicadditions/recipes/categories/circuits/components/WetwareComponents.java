package gregicadditions.recipes.categories.circuits.components;

import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM;

public class WetwareComponents {

    public static void init() { // TODO
        components();
        bacteriaCultures();
    }

    private static void components() {

        // Neuro Support Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 16)
                .input(plate, Tritanium, 2)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .input(pipeSmallFluid, Polybenzimidazole, 2)
                .input(dust, Shewanella, 2)
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .outputs(NEURO_SUPPORT_UNIT.getStackForm())
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        // Cyber Processing Unit todo smds
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .input(foil, SiliconeRubber, 8)
                .input(wireFine, Gold, 64)
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_DIODE_WETWARE.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(MASTER_BOARD.getStackForm())
                .inputs(MetaItems.STEM_CELLS.getStackForm())
                .inputs(NEURO_SUPPORT_UNIT.getStackForm())
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .outputs(CYBER_PROCESSING_UNIT.getStackForm(8))
                .EUt(30720 * 4)
                .duration(250)
                .buildAndRegister();
    }

    private static void bacteriaCultures() {

        ASSEMBLER_RECIPES.recipeBuilder().EUt(7680).duration(100)
                .input(wireFine, Titanium)
                .fluidInputs(Polyethylene.getFluid(1008))
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .outputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(200)
                .fluidInputs(Biomass.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .output(dust, GreenAlgae)
                .output(dust, RedAlgae)
                .output(dust, BrownAlgae)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(150)
                .input(dust, RedAlgae)
                .output(dust, DryRedAlgae)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(30).duration(75)
                .input(dust, DryRedAlgae)
                .output(dust, RedAlgaePowder)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(150)
                .input(dust, RedAlgaePowder)
                .input(dustSmall, SodaAsh)
                .output(dust, PreFreezeAgar)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, PreFreezeAgar)
                .output(dust, FrozenAgarCrystals)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(dust, FrozenAgarCrystals)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(WaterAgarMix.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(50)
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .output(dust, Agar)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(100)
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .input(dust, Agar)
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CLEAN_CULTURE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(30720).duration(500)
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stickLong, RhodiumPlatedPalladium)
                .outputs(ULTRASONIC_HOMOGENIZER.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .input(dust, GreenAlgae)
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(SHEWANELLA_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Blocks.DIRT))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Blocks.DIRT, 1, 1))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CUPRIVADUS_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Items.BEEF))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(ESCHERICHIA_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .fluidInputs(Milk.getFluid(1000))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Items.ROTTEN_FLESH))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(SHEWANELLA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, Shewanella)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, StreptococcusPyogenes)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, BifidobacteriumBreve)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(ESCHERICHIA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, EschericiaColi)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, BrevibacteriumFlavium)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(CUPRIVADUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, CupriavidusNecator)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, Shewanella)
                .output(dust, Shewanella, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, BrevibacteriumFlavium)
                .output(dust, BrevibacteriumFlavium, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, EschericiaColi)
                .output(dust, EschericiaColi, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, StreptococcusPyogenes)
                .output(dust, StreptococcusPyogenes, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, BifidobacteriumBreve)
                .output(dust, BifidobacteriumBreve, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, CupriavidusNecator)
                .output(dust, CupriavidusNecator, 2)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();
    }
}
