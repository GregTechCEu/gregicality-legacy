package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_PLANT_RECIPES;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipeByName;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Lime;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Good;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitComponentRecipes {

    private static final MaterialStack[] LUBRICANTS = {
            new MaterialStack(Lubricant, 1L),
            new MaterialStack(DistilledWater, 3L),
            new MaterialStack(Water, 4L)
    };


    public static void init() {

        circuitBoards();

        primitiveSMD();
        electronicSMD();
        refinedSMD();
        microSMD();
        nanoSMD();
        quantumSMD();
        crystalSMD();
        wetwareSMD();
        biowareSMD();
        opticalSMD();
        exoticSMD(); // TBD
        cosmicSMD();
        supracausalSMD();

        magnetoComponents();
    }

    private static void circuitBoards() {

        // Coated Circuit Board
        ModHandler.removeRecipes(COATED_BOARD.getStackForm(3));
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Wood, 8), RUBBER_DROP.getStackForm()}, new FluidStack[]{Glue.getFluid(100)});

        ModHandler.addShapedRecipe("coated_board_shaped", COATED_BOARD.getStackForm(3),
                "RRR", "BBB", "RRR",
                'R', RUBBER_DROP,
                'B', "plateWood");

        ModHandler.addShapelessRecipe("coated_board_shapeless", COATED_BOARD.getStackForm(),
                RUBBER_DROP, RUBBER_DROP, "plateWood");

        // Basic Circuit Board
        ModHandler.addShapedRecipe("basic_board", BASIC_BOARD.getStackForm(),
                "WWW", "WBW", "WWW",
                'W', new UnificationEntry(wireGtSingle, Copper),
                'B', COATED_BOARD);

        ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(20)
                .input(plate, Wood)
                .input(foil, Copper, 4)
                .fluidInputs(Glue.getFluid(L / 2))
                .outputs(BASIC_BOARD.getStackForm())
                .buildAndRegister();

        // Phenolic Circuit Board handled by GTCE

        // Good Phenolic Circuit Board
        ModHandler.addShapedRecipe("good_board", GOOD_PHENOLIC_BOARD.getStackForm(),
                "WWW", "WBW", "WWW",
                'W', new UnificationEntry(wireGtSingle, Copper),
                'B', PHENOLIC_BOARD);

        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                .inputs(PHENOLIC_BOARD.getStackForm())
                .input(foil, Copper, 4)
                .fluidInputs(SodiumPersulfate.getFluid(200))
                .outputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                .inputs(PHENOLIC_BOARD.getStackForm())
                .input(foil, Copper, 4)
                .fluidInputs(IronChloride.getFluid(100))
                .outputs(GOOD_PHENOLIC_BOARD.getStackForm())
                .buildAndRegister();

        // Plastic Circuit Board handled by GTCE

        // Good Plastic Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30)
                .inputs(PLASTIC_BOARD.getStackForm())
                .input(foil, Copper, 6)
                .fluidInputs(SodiumPersulfate.getFluid(500))
                .outputs(GOOD_PLASTIC_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30)
                .inputs(PLASTIC_BOARD.getStackForm())
                .input(foil, Copper, 6)
                .fluidInputs(IronChloride.getFluid(250))
                .outputs(GOOD_PLASTIC_BOARD.getStackForm())
                .buildAndRegister();

        // Epoxy Circuit Board handled by GTCE

        // Advanced Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(EPOXY_BOARD.getStackForm())
                .input(foil, Electrum, 8)
                .fluidInputs(SodiumPersulfate.getFluid(1000))
                .outputs(ADVANCED_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .inputs(EPOXY_BOARD.getStackForm())
                .input(foil, Electrum, 8)
                .fluidInputs(IronChloride.getFluid(500))
                .outputs(ADVANCED_BOARD.getStackForm())
                .buildAndRegister();

        // Fiber-Reinforced Circuit Board handled by GTCE

        // Extreme Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30)
                .inputs(FIBER_BOARD.getStackForm())
                .input(foil, AnnealedCopper, 12)
                .fluidInputs(SodiumPersulfate.getFluid(2000))
                .outputs(EXTREME_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(30)
                .inputs(FIBER_BOARD.getStackForm())
                .input(foil, AnnealedCopper, 12)
                .fluidInputs(IronChloride.getFluid(1000))
                .outputs(EXTREME_BOARD.getStackForm())
                .buildAndRegister();

        // Multi-Layer Fiber-Reinforced Circuit Board handled by GTCE

        // Elite Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120)
                .inputs(MULTILAYER_FIBER_BOARD.getStackForm())
                .input(foil, Platinum, 16)
                .fluidInputs(SodiumPersulfate.getFluid(4000))
                .outputs(ELITE_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(120)
                .inputs(MULTILAYER_FIBER_BOARD.getStackForm())
                .input(foil, Platinum, 16)
                .fluidInputs(IronChloride.getFluid(2000))
                .outputs(ELITE_BOARD.getStackForm())
                .buildAndRegister();

        // Kapton Circuit Board
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(240)
                .input(plate, Polyimide)
                .fluidInputs(FluorinatedEthylenePropylene.getFluid(L / 2))
                .outputs(KAPTON_BOARD.getStackForm())
                .buildAndRegister();

        // Insane Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(2600).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(SodiumPersulfate.getFluid(6000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(2600).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(IronChloride.getFluid(3000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        // Wetware Circuit Board
        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480)
                .inputs(ELITE_BOARD.getStackForm())
                .inputs(PETRI_DISH.getStackForm())
                .inputs(ELECTRIC_PUMP_LV.getStackForm())
                .inputs(SENSOR_LV.getStackForm())
                .input(circuit, Good)
                .fluidInputs(SterileGrowthMedium.getFluid(250))
                .outputs(WETWARE_BOARD.getStackForm())
                .buildAndRegister();

        // Master Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480)
                .inputs(WETWARE_BOARD.getStackForm())
                .input(foil, NiobiumTitanium, 32)
                .fluidInputs(SodiumPersulfate.getFluid(10000))
                .outputs(MASTER_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(3000).EUt(480)
                .inputs(WETWARE_BOARD.getStackForm())
                .input(foil, NiobiumTitanium, 32)
                .fluidInputs(IronChloride.getFluid(5000))
                .outputs(MASTER_BOARD.getStackForm())
                .buildAndRegister();
    }

    private static void primitiveSMD() {

        // Resistors
        ModHandler.removeRecipes(RESISTOR.getStackForm(3));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireFine, Copper, 4), OreDictUnifier.get(dust, Coal));

        for (Material m : new Material[]{Coal, Charcoal, Carbon}) {

            if (GAConfig.GT6.BendingFoils)

                ModHandler.addShapedRecipe(String.format("resistor_%s", m.toString()), RESISTOR.getStackForm(),
                        "RWR", "CMC", " W ",
                        'M', new UnificationEntry(dust, m),
                        'R', RUBBER_DROP,
                        'W', new UnificationEntry(wireFine, Copper),
                        'C', new UnificationEntry(wireGtSingle, Copper));

            else

                ModHandler.addShapedRecipe(String.format("resistor_%s", m.toString()), RESISTOR.getStackForm(),
                        "RCR", "CMC", " C ",
                        'M', new UnificationEntry(dust, m),
                        'R', RUBBER_DROP,
                        'C', new UnificationEntry(wireGtSingle, Copper));


            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16)
                    .input(dust, m)
                    .input(wireFine, Copper, 4)
                    .input(wireGtSingle, Copper, 4)
                    .fluidInputs(Glue.getFluid(200))
                    .outputs(RESISTOR.getStackForm(8))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(16)
                    .input(dust, m)
                    .input(wireFine, AnnealedCopper, 4)
                    .input(wireGtSingle, Copper, 4)
                    .fluidInputs(Glue.getFluid(200))
                    .outputs(RESISTOR.getStackForm(8))
                    .buildAndRegister();
        }

        // Transistors and Capacitors handled by GTCE

        // Diodes
        removeRecipeByName("gregtech:diode");
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, AnnealedCopper, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(L * 2)});

        ModHandler.addShapedRecipe("ga_diode", DIODE.getStackForm(),
                " P ", "CGC", " P ",
                'P', "paneGlassColorless",
                'C', new UnificationEntry(wireFine, Copper),
                'G', new UnificationEntry(dustSmall, Gallium));

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).input(dustSmall, GalliumArsenide)   .fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide)   .fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).input(dustSmall, GalliumArsenide)   .fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).input(dustSmall, GalliumArsenide)   .fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(4)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm())        .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Glass.getFluid(L * 2)).outputs(DIODE.getStackForm())        .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Copper,         4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(2)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, AnnealedCopper, 4).inputs(SILICON_WAFER.getStackForm()).fluidInputs(Plastic.getFluid(L))         .outputs(DIODE.getStackForm(2)).buildAndRegister();
    }

    private static void electronicSMD() {
    }

    private static void refinedSMD() {
        //SMD REFINED
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 6).inputs(TRANSISTOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_REFINED.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 6).input(plate, Silver).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_REFINED.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 4).inputs(RESISTOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_REFINED.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Copper, 4).input(dust, Carbon).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_REFINED.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96).input(foil, Rubber, 4).inputs(CAPACITOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_REFINED.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120).input(foil, Rubber, 4).input(foil, Steel).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_REFINED.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Gold, 8).inputs(DIODE.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_REFINED.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Gold, 8).input(dust, Lithium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_REFINED.getStackForm(32)).buildAndRegister();
    }

    private static void microSMD() {
        //SMD MICRO
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, AnnealedCopper, 6).inputs(SMD_TRANSISTOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(wireFine, Electrum, 4).inputs(SMD_RESISTOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR.getStackForm(12)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(96).input(foil, PolyvinylChloride, 4).inputs(SMD_CAPACITOR_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR.getStackForm(8)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).inputs(SMD_DIODE_REFINED.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE.getStackForm(16)).buildAndRegister();

        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, Platinum, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(288)});
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).input(wireFine, Platinum, 8).input(dust, GalliumArsenide).fluidInputs(Plastic.getFluid(288)).outputs(SMD_DIODE.getStackForm(32)).buildAndRegister();

    }

    private static void nanoSMD() {
        //SMD NANO
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Palladium, 12).inputs(SMD_TRANSISTOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_NANO.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Palladium, 12).input(plate, Magnalium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_NANO.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Cerium, 8).inputs(SMD_RESISTOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_NANO.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494).input(wireFine, Cerium, 8).input(dust, Graphite).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_NANO.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480).input(foil, Silicon, 4).inputs(SMD_CAPACITOR.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_NANO.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480).input(foil, Silicon, 4).input(foil, Titanium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_NANO.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).input(wireFine, ReinforcedEpoxyResin, 8).inputs(SMD_DIODE.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_NANO.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).input(wireFine, ReinforcedEpoxyResin, 8).input(dust, Caesium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_NANO.getStackForm(32)).buildAndRegister();
    }

    private static void quantumSMD() {
        //SMD QUANTUM
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Plutonium, 12).inputs(SMD_TRANSISTOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Plutonium, 12).input(plate, Americium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Ruthenium, 8).inputs(SMD_RESISTOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_QUANTUM.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976).input(wireFine, Ruthenium, 8).input(plate, Graphene).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_QUANTUM.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920).input(foil, SiliconeRubber, 4).inputs(SMD_CAPACITOR_NANO.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_QUANTUM.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920).input(foil, SiliconeRubber, 4).input(foil, Tungsten).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_QUANTUM.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).input(wireFine, HSSG, 8).inputs(SMD_DIODE_NANO.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_QUANTUM.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).input(wireFine, HSSG, 8).input(dust, Polonium).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_QUANTUM.getStackForm(32)).buildAndRegister();
    }

    private static void crystalSMD() {
        //SMD CRYSTAL
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, Rutherfordium, 12).inputs(SMD_TRANSISTOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, Rutherfordium, 12).input(plate, NetherStar).fluidInputs(Plastic.getFluid(L)).outputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, NaquadahAlloy, 8).inputs(SMD_RESISTOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_CRYSTAL.getStackForm(12)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7904).input(wireFine, NaquadahAlloy, 8).input(plate, Graphene).input(plate, RutheniumDioxide).fluidInputs(Plastic.getFluid(L)).outputs(SMD_RESISTOR_CRYSTAL.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680).input(foil, Polybenzimidazole, 4).inputs(SMD_CAPACITOR_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(8)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(7680).input(foil, Polybenzimidazole, 4).input(foil, NaquadahAlloy).fluidInputs(Plastic.getFluid(L)).outputs(SMD_CAPACITOR_CRYSTAL.getStackForm(16)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).input(wireFine, HSSS, 8).inputs(SMD_DIODE_QUANTUM.getStackForm()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_CRYSTAL.getStackForm(16)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).input(wireFine, HSSS, 8).inputs(LanthanumCalciumManganate.getItemStack()).fluidInputs(Plastic.getFluid(L)).outputs(SMD_DIODE_CRYSTAL.getStackForm(32)).buildAndRegister();
    }

    private static void wetwareSMD() {
        //SMD WETWARE
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616).input(wireFine, PEDOT, 8).input(foil, Polybenzimidazole, 4).input(foil, BariumTitanate, 4).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_CAPACITOR_WETWARE.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(31616).input(wireFine, NaquadahAlloy, 6).input(plate, BismuthRuthenate).input(plate, BismuthIridiate).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_RESISTOR_WETWARE.getStackForm(24)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720).input(wireFine, Dubnium, 8).input(plate, GermaniumTungstenNitride, 4).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_TRANSISTOR_WETWARE.getStackForm(32)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(7680).input(wireFine, Osmiridium, 8).inputs(AluminiumComplex.getItemStack()).inputs(CopperGalliumIndiumSelenide.getItemStack()).fluidInputs(Polytetrafluoroethylene.getFluid(576)).outputs(SMD_DIODE_WETWARE.getStackForm(32)).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .fluidInputs(Polybenzimidazole.getFluid(1008))
                .fluidInputs(UUMatter.getFluid(100))
                .input(wireFine, NaquadahAlloy, 16)
                .input(plate, Tritanium, 2)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .input(pipeSmall, Polybenzimidazole, 2)
                .inputs(Shewanella.getItemStack(2))
                .outputs(NEURO_SUPPORT_UNIT.getStackForm())
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

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
                .fluidInputs(Polybenzimidazole.getFluid(1008))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(1296))
                .outputs(CYBER_PROCESSING_UNIT.getStackForm(8))
                .EUt(30720 * 4)
                .duration(250)
                .buildAndRegister();
    }

    private static void biowareSMD() {
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(plate, NaquadahEnriched)
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(foil, NaquadahEnriched, 4)
                .input(foil, Polybenzimidazole, 4)
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(dust, Naquadria)
                .input(dust, Tritanium)
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(1296))
                .input(plate, Naquadria)
                .input(plate, Tritanium)
                .outputs(SMD_RESISTOR_BIOWARE.getStackForm(32))
                .EUt(30720 * 4)
                .duration(100)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber, 32)
                .input(wireFine, NaquadahAlloy, 16)
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(2))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(2))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(2))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(STEM_CELLS.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(288))
                .outputs(NEURO_PROCESSOR.getStackForm())
                .EUt(30720 * 16)
                .duration(150)
                .buildAndRegister();
    }

    private static void opticalSMD() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, LithiumNiobate, 4)
                .input(plate, Vibranium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(plate, MetastableFlerovium, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Pikyonium, 8)
                .input(foil, Quantum, 4)
                .input(foil, Zylon, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(1200000)
                .input(wireFine, Cinobite, 8)
                .inputs(SodiumSeaborgate.getItemStack(5))
                .input(dust, TriniumTitanium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(390).EUt(1600000)
                .inputs(UHASOC_WAFER.getStackForm())
                .fluidInputs(LiquidZBLAN.getFluid(144))
                .fluidInputs(CarbonNanotubes.getFluid(144))
                .fluidInputs(SeaborgiumDopedNanotubes.getFluid(144))
                .input(dust, IndiumPhospide)
                .fluidInputs(DielectricMirrorFormationMix.getFluid(400))
                .outputs(OPTICAL_SOC_WAFER.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(280).EUt(850000)
                .inputs(OPTICAL_SOC_WAFER.getStackForm())
                .outputs(OPTICAL_SOC.getStackForm(4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(1474560)
                .inputs(OPTICAL_SOC.getStackForm())
                .inputs(SMD_DIODE_OPTICAL.getStackForm(2))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(2))
                .input(foil, Polyetheretherketone, 2)
                .inputs(LOW_FREQUENCY_LASER.getStackForm())
                .inputs(MEDIUM_FREQUENCY_LASER.getStackForm())
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm(2))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(2))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(plate,Graphene,4)
                .fluidInputs(Polytetrafluoroethylene.getFluid(864))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(432))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(FullereneDopedNanotubes.getFluid(144))
                .outputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
    }

    private static void exoticSMD() {
        // COMING SOON...
    }

    private static void cosmicSMD() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, SuperheavyHAlloy, 4)
                .input(plate, Vibranium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(1296))
                .outputs(SMD_DIODE_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, MetastableHassium, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(foil, Quantum, 4)
                .input(foil, FullerenePolymerMatrix, 4)
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_CAPACITOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, Cinobite, 8)
                .input(plate, SuperheavyLAlloy, 4)
                .input(plate, TriniumTitanium, 4)
                .fluidInputs(Zylon.getFluid(1296))
                .outputs(SMD_RESISTOR_COSMIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(11796480).qubit(32)
                .inputs(COSMIC_PROCESSING_UNIT_CORE.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(2))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(2))
                .input(foil, FullerenePolymerMatrix, 2)
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .input(wireGtSingle, Cinobite, 8)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(plate,BlackTitanium,4)
                .fluidInputs(Zylon.getFluid(864))
                .fluidInputs(Quantum.getFluid(432))
                .fluidInputs(SolderingAlloy.getFluid(1296))
                .fluidInputs(ProtoAdamantium.getFluid(432))
                .outputs(COSMIC_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
    }

    private static void supracausalSMD() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, ProtoAdamantium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, Vibranium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_DIODE_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(plate, Neutronium)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(134217728)
                .fluidInputs(FullerenePolymerMatrix.getFluid(144))
                .input(foil, FullerenePolymerMatrix)
                .notConsumable(MICROWORMHOLE_GENERATOR.getStackForm())
                .outputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(32))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(33550000)
                .inputs(NUCLEAR_CLOCK.getStackForm())
                .inputs(TOPOLOGICAL_MANIPULATOR_UNIT.getStackForm(2))
                .inputs(RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM.getStackForm(2))
                .inputs(GRAVITON_TRANSDUCER.getStackForm(4))
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm(3))
                .input(plate, Neutronium)
                .input(wireGtSingle, UMVSuperconductor, 2)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .fluidInputs(FullerenePolymerMatrix.getFluid(1296))
                .outputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .buildAndRegister();
    }

    private static void magnetoComponents() {

        // Raw Imprinted Resonatic Circuit Board
        FORMING_PRESS_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, CircuitCompoundMK3, 4)
                .input(dust, MagnetoResonatic)
                .outputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm())
                .buildAndRegister();

        // Imprinted Resonatic Circuit Board
        AUTOCLAVE_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .inputs(RAW_IMPRINT_SUPPORTED_BOARD.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 3))
                .outputs(IMPRINT_SUPPORTED_BOARD.getStackForm())
                .buildAndRegister();

        // TODO Should these even be in this class?
        BLAST_RECIPES.recipeBuilder().duration(56000).EUt(480).blastFurnaceTemp(2953).input(dust, Zirconium, 10).input(dust, YttriumOxide, 1).fluidInputs(Oxygen.getFluid(10000)).outputs(OreDictUnifier.get(gemFlawed, CubicZirconia, 40)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(1080).EUt(20).input(dust, Prasiolite, 3).input(dust, BismuthTellurite, 4).input(dust, CubicZirconia, 1).input(dust, SteelMagnetic).outputs(OreDictUnifier.get(dust, MagnetoResonatic, 9)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(570).EUt(90).input(dust, Bismuth, 2).input(dust, Boron).fluidInputs(Hydrogen.getFluid(1000)).outputs(OreDictUnifier.get(dust, Dibismusthydroborat, 4)).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(161).EUt(60).input(dust, Bismuth, 2).input(dust, Tellurium, 3).outputs(OreDictUnifier.get(dust, BismuthTellurite, 5)).buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(982).EUt(15).input(dust, IndiumGalliumPhosphide).input(dust, Dibismusthydroborat, 3).input(dust, BismuthTellurite, 2).outputs(OreDictUnifier.get(dust, CircuitCompoundMK3, 6)).buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Neon.getFluid(100)).outputs(OreDictUnifier.get(gemChipped, MagnetoResonatic, 9)).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(4500).EUt(7680).input(dust, MagnetoResonatic).fluidInputs(Krypton.getFluid(100)).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(30).input(gemChipped, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawed, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(120).input(gemFlawed, MagnetoResonatic, 3).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gem, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(1200).EUt(480).input(gem, MagnetoResonatic, 4).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemFlawless, MagnetoResonatic)).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(2400).EUt(1920).input(gemFlawless, MagnetoResonatic, 4).notConsumable(craftingLens, MarkerMaterials.Color.Magenta).outputs(OreDictUnifier.get(gemExquisite, MagnetoResonatic)).buildAndRegister();





        // TODO

        //Cutting Machine Recipes
        for (MaterialStack stack : LUBRICANTS) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;
            CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(60).inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).fluidInputs(material.getFluid(2 * multiplier)).outputs(RAW_CRYSTAL_CHIP.getStackForm(2)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3


        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Emerald, 10), OreDictUnifier.get(gemExquisite, Emerald)}, new FluidStack[]{Helium.getFluid(5000)});
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Olivine, 10), OreDictUnifier.get(gemExquisite, Olivine)}, new FluidStack[]{Helium.getFluid(5000)});
        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Emerald).fluidInputs(Rutherfordium.getFluid(72)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(450).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(plate, Olivine).fluidInputs(Rutherfordium.getFluid(72)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Emerald).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();
        BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(RAW_CRYSTAL_CHIP.getStackForm()).input(block, Olivine).fluidInputs(Helium.getFluid(1000)).outputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(30).input(dust, Iron).fluidInputs(HydrochloricAcid.getFluid(2000)).fluidOutputs(IronChloride.getFluid(3000), Hydrogen.getFluid(3000)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        ModHandler.removeRecipes(OreDictUnifier.get(dust, Materials.ReinforcedEpoxyResin));

        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 5000, 750).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Olivine)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 500).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Emerald)).fluidInputs(Rutherfordium.getFluid(72)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 2500, 500).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Olivine)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 500, 75).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemExquisite, Emerald)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 500, 75).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Olivine)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 250, 50).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(24000).EUt(320).inputs(OreDictUnifier.get(gemFlawless, Emerald)).fluidInputs(Helium.getFluid(1000)).chancedOutput(RAW_CRYSTAL_CHIP.getStackForm(), 250, 50).buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(dust, Carbon).fluidInputs(Cerium.getFluid(1)).chancedOutput(CARBON_FIBERS.getStackForm(2), 1250, 250).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000).inputs(ENGRAVED_CRYSTAL_CHIP.getStackForm()).notConsumable(craftingLens, Lime).outputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm()).buildAndRegister();

    }
}
