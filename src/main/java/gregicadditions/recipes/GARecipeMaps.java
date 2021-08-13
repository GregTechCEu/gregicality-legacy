package gregicadditions.recipes;

import crafttweaker.annotations.ZenRegister;
import gregicadditions.gui.GAGuiTextures;
import gregicadditions.integrations.exnihilocreatio.SieveRecipeMap;
import gregicadditions.recipes.impl.LargeRecipeBuilder;
import gregicadditions.recipes.impl.*;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
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

public class GARecipeMaps { // todo assembly line qubit?

    // Simple Recipe Maps ==============================================================================================

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new SieveRecipeMap("electric_sieve",
            2, 2, 1, 54, 0, 0, 0, 0, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ATTRACTOR_RECIPES = new RecipeMap<>("attractor",
            1, 6, 1, 0, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator",
            2, 9, 2, 2, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DECAY_CHAMBERS_RECIPES = new RecipeMap<>("decay_chamber",
            1, 1, 1, 1, new SimpleRecipeBuilder().EUt(32), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL);


    @ZenProperty
    public static final RecipeMap<IntCircuitRecipeBuilder> GREEN_HOUSE_RECIPES = new RecipeMap<>("green_house",
            3, 2, 1, 0, new IntCircuitRecipeBuilder().EUt(16), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DISASSEMBLER_RECIPES = new RecipeMap<>("disassembler",
            1, 9, 0, 0, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GAGuiTextures.DISASSEMBLER_IN_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GAGuiTextures.PROGRESS_BAR_DISASSEMBLER, ProgressWidget.MoveType.HORIZONTAL);


    // Multiblock Recipe Maps ==========================================================================================


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES = new RecipeMapExtended<>("chemical_plant",
            6, 4, 5, 4, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final LargeRecipeMap LARGE_MIXER_RECIPES = (LargeRecipeMap) new RecipeMapLargeExtended<>("large_mixer",
            9, 1, 6, 1, new LargeRecipeBuilder(RecipeMaps.MIXER_RECIPES), false)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMapExtended<BlastRecipeBuilder> BLAST_ALLOY_RECIPES = (RecipeMapExtended<BlastRecipeBuilder>) new RecipeMapExtended<>("blast_alloy",
            9, 0, 2, 1, new BlastRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY);

    @ZenProperty
    public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_REACTOR_RECIPES = new RecipeMap<>("nuclear_reactor",
            3, 2, 0, 0, new NuclearReactorBuilder().EUt(480), false);


    @ZenProperty
    public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_BREEDER_RECIPES = new RecipeMap<>("nuclear_breeder",
            3, 4, 0, 0, new NuclearReactorBuilder().EUt(480), false);


    @ZenProperty
    public static final LargeRecipeMap LARGE_CENTRIFUGE_RECIPES = (LargeRecipeMap) new LargeRecipeMap("large_centrifuge",
            1, 6, 2, 6, new LargeRecipeBuilder(RecipeMaps.CENTRIFUGE_RECIPES), false)
            .setSlotOverlay(false, false, true, GuiTextures.EXTRACTOR_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.DARK_CANISTER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES = new RecipeMap<>("bio_reactor",
            3, 3, 5, 2, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<QubitProducerRecipeBuilder> SIMPLE_QUBIT_GENERATOR = new RecipeMap<>("simple_qubit_generator",
            1, 0, 0, 0, new QubitProducerRecipeBuilder(), false);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STELLAR_FORGE_RECIPES = new RecipeMap<>("stellar_forge",
            3, 2, 3, 2, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> PLASMA_CONDENSER_RECIPES = new RecipeMap<>("plasma_condenser",
            2, 2, 2, 2, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<IntCircuitRecipeBuilder> GAS_CENTRIFUGE_RECIPES = new RecipeMap<>("gas_centrifuge",
            1, 0, 1, 3, new IntCircuitRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.VERTICAL_INVERTED);


    @ZenProperty
    public static final LargeRecipeMap LARGE_ENGRAVER_RECIPES = (LargeRecipeMap) new LargeRecipeMap("large_engraver",
            2, 1, 1, 1, new LargeRecipeBuilder(RecipeMaps.LASER_ENGRAVER_RECIPES), false)
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final RecipeMap<AdvFusionRecipeBuilder> ADV_FUSION_RECIPES = new RecipeMap<>("adv_fusion",
            0, 0, 3, 3, new AdvFusionRecipeBuilder(), false);


    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_RECIPES = new RecipeMap<>("electric_implosion",
            2, 2, 0, 0, new SimpleRecipeBuilder().duration(1).EUt(375000), false); // UV-tier, 1tick processing time



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
