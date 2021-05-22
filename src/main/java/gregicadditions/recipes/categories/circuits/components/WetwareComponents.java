package gregicadditions.recipes.categories.circuits.components;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAMaterials.DepletedGrowthMedium;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.GAMetaItems.CONTAMINATED_PETRI_DISH;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_CYLINDER;

public class WetwareComponents {

    public static void init() { // TODO
        components();
        bacteriaCultures();
    }

    private static void components() {

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616)
                .input(wireFine, PEDOT, 8)
                .input(foil, Polytetrafluoroethylene, 4)
                .input(foil, BariumTitanate, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_CAPACITOR_WETWARE.getStackForm(32))
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616)
                .input(wireFine, NaquadahAlloy, 6)
                .input(plate, BismuthRuthenate)
                .input(plate, BismuthIridiate)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_RESISTOR_WETWARE.getStackForm(24))
                .buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720)
                .input(wireFine, Dubnium, 8)
                .input(plate, GermaniumTungstenNitride, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_TRANSISTOR_WETWARE.getStackForm(32))
                .buildAndRegister();

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7680)
                .input(wireFine, Osmiridium, 8)
                .inputs(AluminiumComplex.getItemStack())
                .inputs(CopperGalliumIndiumSelenide.getItemStack())
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_DIODE_WETWARE.getStackForm(32))
                .buildAndRegister();

        // Neuro Support Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 16)
                .input(plate, Tritanium, 2)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .input(pipeSmall, Polybenzimidazole, 2)
                .inputs(Shewanella.getItemStack(2))
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .outputs(NEURO_SUPPORT_UNIT.getStackForm())
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        // Cyber Processing Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .input(foil, SiliconeRubber, 8)
                .input(wireFine, Gold, 64)
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_DIODE_WETWARE.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(MASTER_BOARD.getStackForm())
                .inputs(STEM_CELLS.getStackForm())
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

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(24)
                .fluidInputs(Polystyrene.getFluid(L / 4))
                .notConsumable(SHAPE_MOLD_CYLINDER.getStackForm())
                .outputs(PETRI_DISH.getStackForm())
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(24)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L / 4))
                .notConsumable(SHAPE_MOLD_CYLINDER.getStackForm())
                .outputs(PETRI_DISH.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(7680).duration(100)
                .input(wireFine, Titanium)
                .fluidInputs(Plastic.getFluid(1008))
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .outputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(200)
                .fluidInputs(Biomass.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .outputs(GreenAlgae.getItemStack())
                .outputs(RedAlgae.getItemStack())
                .outputs(BrownAlgae.getItemStack())
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(150)
                .inputs(RedAlgae.getItemStack())
                .outputs(DryRedAlgae.getItemStack())
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(30).duration(75)
                .inputs(DryRedAlgae.getItemStack())
                .outputs(RedAlgaePowder.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(150)
                .inputs(RedAlgaePowder.getItemStack())
                .input(dustSmall, SodaAsh)
                .outputs(PreFreezeAgar.getItemStack())
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(PreFreezeAgar.getItemStack())
                .outputs(FrozenAgarCrystals.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .inputs(FrozenAgarCrystals.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(WaterAgarMix.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(50)
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .outputs(Agar.getItemStack())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(100)
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .inputs(Agar.getItemStack())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CLEAN_CULTURE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(30720).duration(500)
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stickLong, RhodiumPlatedPalladium)
                .outputs(ULTRASONIC_HOMOGENIZER.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(GreenAlgae.getItemStack())
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
                .outputs(Shewanella.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(StreptococcusPyogenes.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BifidobacteriumBreve.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(ESCHERICHIA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(EschericiaColi.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BrevibacteriumFlavium.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(CUPRIVADUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CupriavidusNecator.getItemStack())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(Shewanella.getItemStack())
                .outputs(Shewanella.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(BrevibacteriumFlavium.getItemStack())
                .outputs(BrevibacteriumFlavium.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(EschericiaColi.getItemStack())
                .outputs(EschericiaColi.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(StreptococcusPyogenes.getItemStack())
                .outputs(StreptococcusPyogenes.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(BifidobacteriumBreve.getItemStack())
                .outputs(BifidobacteriumBreve.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(CupriavidusNecator.getItemStack())
                .outputs(CupriavidusNecator.getItemStack(2))
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();
    }
}
