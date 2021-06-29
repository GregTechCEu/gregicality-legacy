package gregicadditions.recipes;

import crafttweaker.annotations.ZenRegister;
import gregicadditions.gui.GAGuiTextures;
import gregicadditions.integrations.exnihilocreatio.SieveRecipeMap;
import gregicadditions.recipes.impl.LargeRecipeBuilder;
import gregicadditions.recipes.impl.*;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregicadditions.recipes.impl.QubitConsumerRecipeBuilder;
import gregicadditions.recipes.impl.QubitProducerRecipeBuilder;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.IntCircuitRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.machines.FuelRecipeMap;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gtadditions.recipe.GARecipeMaps")
@ZenRegister

public class GARecipeMaps {

    // Simple Recipe Maps ==============================================================================================



    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CLUSTER_MILL_RECIPES = new RecipeMap<>("cluster_mill",
            1, 1, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder())
            .setSlotOverlay(false, false, GuiTextures.BENDER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> MASS_FAB_RECIPES = new RecipeMap<>("mass_fab",
            0, 1, 0, 0, 0, 1, 1, 2, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> REPLICATOR_RECIPES = new RecipeMap<>("replicator",
            0, 1, 0, 1, 1, 3, 0, 1, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<QubitConsumerRecipeBuilder> CIRCUIT_ASSEMBLER_RECIPES = new RecipeMap<>("circuit_assembler",
            1, 6, 1, 1, 0, 1, 0, 0, new QubitConsumerRecipeBuilder())
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GAGuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new SieveRecipeMap("electric_sieve",
            2, 2, 1, 54, 0, 0, 0, 0, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ATTRACTOR_RECIPES = new RecipeMap<>("attractor",
            0, 1, 1, 6, 1, 1, 0, 0, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator",
            0, 2, 0, 9, 0, 2, 0, 2, (new SimpleRecipeBuilder()))
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SIMPLE_ORE_WASHER_RECIPES = new RecipeMap<>("simple_ore_washer",
            1, 1, 1, 1, 0, 1, 0, 0, new SimpleRecipeBuilder()
            .duration(5).EUt(7))
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DECAY_CHAMBERS_RECIPES = new RecipeMap<>("decay_chamber",
            0, 1, 0, 1, 0, 1, 0, 1, new SimpleRecipeBuilder()
            .EUt(32))
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL);


    @ZenProperty
    public static final RecipeMap<IntCircuitRecipeBuilder> GREEN_HOUSE_RECIPES = new RecipeMap<>("green_house",
            2, 3, 1, 2, 1, 1, 0, 0, new IntCircuitRecipeBuilder()
            .EUt(16))
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DISASSEMBLER_RECIPES = new RecipeMap<>("disassembler",
            1, 1, 0, 9, 0, 0, 0,0, new SimpleRecipeBuilder())
            .setSlotOverlay(false, false, GAGuiTextures.DISASSEMBLER_IN_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GAGuiTextures.PROGRESS_BAR_DISASSEMBLER, ProgressWidget.MoveType.HORIZONTAL);



    // Multiblock Recipe Maps ==========================================================================================



    @ZenProperty
    public static final RecipeMapAssemblyLine<QubitConsumerRecipeBuilder> ASSEMBLY_LINE_RECIPES = (RecipeMapAssemblyLine<QubitConsumerRecipeBuilder>) new RecipeMapAssemblyLine<>("assembly_line",
            4, 16, 1, 1, 0, 4, 0, 0, new QubitConsumerRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
            .setSlotOverlay(false, false, GuiTextures.MOLD_OVERLAY);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CRACKER_UNIT_RECIPES = new RecipeMap<>("cracker_unit",
            0, 0, 0, 0, 2, 2, 1, 1, new SimpleRecipeBuilder());


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> PROCESSING_ARRAY_RECIPES = new RecipeMap<>("processing_array",
            0, 9, 0, 6, 0, 3, 0, 2, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final LargeRecipeMap LARGE_CHEMICAL_RECIPES = (LargeRecipeMap) new LargeRecipeMap("large_chemical_reactor",
            0, 3, 0, 3, 0, 5, 0, 4, (new LargeRecipeBuilder(RecipeMaps.CHEMICAL_RECIPES))
            .EUt(30))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES = new RecipeMapExtended<>("chemical_plant",
            0, 6, 0, 4, 0, 5, 0, 4, (new SimpleRecipeBuilder()))
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final LargeRecipeMap LARGE_MIXER_RECIPES = (LargeRecipeMap) new RecipeMapLargeExtended<>("large_mixer",
            0, 9, 0, 1, 0, 6, 0, 1, new LargeRecipeBuilder(RecipeMaps.MIXER_RECIPES))
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMapExtended<BlastRecipeBuilder> BLAST_ALLOY_RECIPES = (RecipeMapExtended<BlastRecipeBuilder>) new RecipeMapExtended<>("blast_alloy",
            0, 9, 0, 0, 0, 2, 1, 1, new BlastRecipeBuilder())
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY);

    @ZenProperty
    public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_REACTOR_RECIPES = new RecipeMap<>("nuclear_reactor",
            2, 3, 1, 2, 0, 0, 0, 0, new NuclearReactorBuilder()
            .EUt(480));


    @ZenProperty
    public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_BREEDER_RECIPES = new RecipeMap<>("nuclear_breeder",
            2, 3, 1, 4, 0, 0, 0, 0, new NuclearReactorBuilder()
            .EUt(480));


    @ZenProperty
    public static final LargeRecipeMap LARGE_CENTRIFUGE_RECIPES = (LargeRecipeMap) new LargeRecipeMap("large_centrifuge",
            0, 1, 0, 6, 0, 2, 0, 6, new LargeRecipeBuilder(RecipeMaps.CENTRIFUGE_RECIPES))
            .setSlotOverlay(false, false, true, GuiTextures.EXTRACTOR_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.DARK_CANISTER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES = new RecipeMap<>("bio_reactor",
            0, 3, 0, 3, 0, 5, 0, 2, (new SimpleRecipeBuilder()))
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<QubitProducerRecipeBuilder> SIMPLE_QUBIT_GENERATOR = new RecipeMap<>("simple_qubit_generator",
            1, 1, 0, 0, 0, 0, 0, 0, new QubitProducerRecipeBuilder());


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STELLAR_FORGE_RECIPES = new RecipeMap<>("stellar_forge",
            0, 3, 0, 2, 0, 3, 0, 2, (new SimpleRecipeBuilder()))
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> PLASMA_CONDENSER_RECIPES = new RecipeMap<>("plasma_condenser",
            1, 2, 0, 2, 1, 2, 1, 2, (new SimpleRecipeBuilder()))
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<IntCircuitRecipeBuilder> GAS_CENTRIFUGE_RECIPES = new RecipeMap<>("gas_centrifuge",
            0, 1, 0, 0, 1, 1, 1, 3, new IntCircuitRecipeBuilder())
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.VERTICAL_INVERTED);


    @ZenProperty
    public static final LargeRecipeMap LARGE_ENGRAVER_RECIPES = (LargeRecipeMap) new LargeRecipeMap("large_engraver",
            2, 2, 1, 1, 0, 1, 0, 1, new LargeRecipeBuilder(RecipeMaps.LASER_ENGRAVER_RECIPES))
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<AdvFusionRecipeBuilder> ADV_FUSION_RECIPES = new RecipeMap<>("adv_fusion",
            0, 0, 0, 0, 2, 3, 1, 3, new AdvFusionRecipeBuilder());


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_RECIPES = new RecipeMap<>("electric_implosion",
            1, 2, 1, 2, 0, 0, 0, 0, new SimpleRecipeBuilder()
                .duration(1).EUt(1500000)); // UHV-tier, 1tick processing time



    // Fuel Recipe Maps ================================================================================================



    @ZenProperty
    public static final FuelRecipeMap ROCKET_FUEL_RECIPES = new FuelRecipeMap("rocket_fuel");


    @ZenProperty
    public static final FuelRecipeMap NAQUADAH_REACTOR_FUELS = new FuelRecipeMap("naquadah_reactor");


    @ZenProperty
    public static final FuelRecipeMap HYPER_REACTOR_FUELS = new FuelRecipeMap("hyper_reactor");


    @ZenProperty
    public static final HotCoolantRecipeMap HOT_COOLANT_TURBINE_FUELS = new HotCoolantRecipeMap("hot_coolant_turbine");
}
