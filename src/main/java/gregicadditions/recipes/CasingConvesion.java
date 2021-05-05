package gregicadditions.recipes;

import gregicadditions.item.metal.NuclearCasing;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.MetaBlocks;

import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.item.GAMetaBlocks.*;
import static gregicadditions.item.metal.MetalCasing1.CasingType.*;
import static gregicadditions.item.metal.MetalCasing2.CasingType.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.*;
import static gregicadditions.GAMaterials.*;


public class CasingConvesion {

    public static void init() {
        // TODO Remove this class for the release after the casing changes release

        // Nuclear Casings
        ModHandler.addShapedRecipe("casing_convert_thorium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.THORIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, ThoriumRadioactive.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_protactinium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.PROTACTINIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Protactinium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_uranium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.URANIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, UraniumRadioactive.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_neptunium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.NEPTUNIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Neptunium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_plutonium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.PLUTONIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, PlutoniumRadioactive.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_americium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.AMERICIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, AmericiumRadioactive.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_curium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.CURIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Curium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_berkelium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.BERKELIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Berkelium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_californium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.CALIFORNIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Californium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_einsteinium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.EINSTEINIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Einsteinium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_fermium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.FERMIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Fermium.getMaterial()));

        ModHandler.addShapedRecipe("casing_convert_mendelevium", NUCLEAR_CASING.getItemVariant(NuclearCasing.CasingType.MENDELEVIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Mendelevium.getMaterial()));

        // Metal Casing 1
        ModHandler.addShapedRecipe("casing_convert_hastelloy_x78", METAL_CASING_1.getItemVariant(HASTELLOY_X78),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HastelloyX78));

        ModHandler.addShapedRecipe("casing_convert_hastelloy_n", METAL_CASING_1.getItemVariant(HASTELLOY_N),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HastelloyN));

        ModHandler.addShapedRecipe("casing_convert_hastelloy_k243", METAL_CASING_1.getItemVariant(HASTELLOY_K243),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HastelloyK243));

        ModHandler.addShapedRecipe("casing_convert_incoloy_813", METAL_CASING_1.getItemVariant(INCOLOY_813),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Incoloy813));

        ModHandler.addShapedRecipe("casing_convert_incoloy_ma956", METAL_CASING_1.getItemVariant(INCOLOY_MA956),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, IncoloyMA956));

        ModHandler.addShapedRecipe("casing_convert_maraging_steel_250", METAL_CASING_1.getItemVariant(MARAGING_STEEL_250),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, MaragingSteel250));

        ModHandler.addShapedRecipe("casing_convert_nitinol_60", METAL_CASING_1.getItemVariant(NITINOL_60),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Nitinol60));

        ModHandler.addShapedRecipe("casing_convert_inconel_625", METAL_CASING_1.getItemVariant(INCONEL_625),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Inconel625));

        ModHandler.addShapedRecipe("casing_convert_grisium", METAL_CASING_1.getItemVariant(GRISIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Grisium));

        ModHandler.addShapedRecipe("casing_convert_eglin_steel", METAL_CASING_1.getItemVariant(EGLIN_STEEL),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, EglinSteel));

        ModHandler.addShapedRecipe("casing_convert_babbit_alloy", METAL_CASING_1.getItemVariant(BABBIT_ALLOY),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, BabbittAlloy));

        ModHandler.addShapedRecipe("casing_convert_hg_1223", METAL_CASING_1.getItemVariant(HG_1223),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HG1223));

        ModHandler.addShapedRecipe("casing_convert_tumbaga", METAL_CASING_1.getItemVariant(TUMBAGA),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Tumbaga));

        ModHandler.addShapedRecipe("casing_convert_talonite", METAL_CASING_1.getItemVariant(TALONITE),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Talonite));

        ModHandler.addShapedRecipe("casing_convert_zirconium_carbide", METAL_CASING_1.getItemVariant(ZIRCONIUM_CARBIDE),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, ZirconiumCarbide));

        ModHandler.addShapedRecipe("casing_convert_potin", METAL_CASING_1.getItemVariant(POTIN),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Potin));

        // Metal Casing 2
        ModHandler.addShapedRecipe("casing_convert_staballoy", METAL_CASING_2.getItemVariant(STABALLOY),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Staballoy));

        ModHandler.addShapedRecipe("casing_convert_stellite", METAL_CASING_2.getItemVariant(STELLITE),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Stellite));

        ModHandler.addShapedRecipe("casing_convert_enriched_naquadah_alloy", METAL_CASING_2.getItemVariant(ENRICHED_NAQUADAH_ALLOY),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, EnrichedNaquadahAlloy));

        ModHandler.addShapedRecipe("casing_convert_quantum", METAL_CASING_2.getItemVariant(QUANTUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Quantum));

        ModHandler.addShapedRecipe("casing_convert_tritanium", METAL_CASING_2.getItemVariant(TRITANIUM),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Tritanium));

        ModHandler.addShapedRecipe("casing_convert_black_steel", METAL_CASING_2.getItemVariant(BLACK_STEEL),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, BlackSteel));

        ModHandler.addShapedRecipe("casing_convert_red_steel", METAL_CASING_2.getItemVariant(RED_STEEL),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, RedSteel));

        ModHandler.addShapedRecipe("casing_convert_gold", METAL_CASING_2.getItemVariant(GOLD),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Gold));

        ModHandler.addShapedRecipe("casing_convert_iron", METAL_CASING_2.getItemVariant(IRON),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Iron));

        ModHandler.addShapedRecipe("casing_convert_hss_g", METAL_CASING_2.getItemVariant(HSS_G),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HSSG));

        ModHandler.addShapedRecipe("casing_convert_hss_s", METAL_CASING_2.getItemVariant(HSS_S),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, HSSS));

        ModHandler.addShapedRecipe("casing_convert_lead", METAL_CASING_2.getItemVariant(LEAD),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Lead));

        ModHandler.addShapedRecipe("casing_convert_naquadria", METAL_CASING_2.getItemVariant(NAQUADRIA),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Naquadria));

        // GTCE Casings
        ModHandler.addShapedRecipe("casing_convert_bronze", MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Bronze));

        ModHandler.addShapedRecipe("casing_convert_invar", MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Invar));

        ModHandler.addShapedRecipe("casing_convert_aluminium", MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Aluminium));

        ModHandler.addShapedRecipe("casing_convert_steel", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Steel));

        ModHandler.addShapedRecipe("casing_convert_stainless_steel", MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, StainlessSteel));

        ModHandler.addShapedRecipe("casing_convert_titanium", MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, Titanium));

        ModHandler.addShapedRecipe("casing_convert_tungstensteel", MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST),
                "C", 'C', OreDictUnifier.get(gtMetalCasing, TungstenSteel));
    }
}
