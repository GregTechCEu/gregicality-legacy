package gregicadditions.materials;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;

import static gregicadditions.materials.GAMaterialFlags.*;
import static gregtech.api.unification.material.Material.FluidType;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregicadditions.GAMaterials.*;

/**
 * High Degree Materials, IDs 18500-19999
 */
public class GAHighDegreeMaterials {

    public static void register() {

        // Fourth Degree Materials, 18500-18999
        Pyrotheum = new Material.Builder(18500, "pyrotheum")
                .dust(1).fluid(FluidType.FLUID, true)
                .color(0xFF9A3C).iconSet(SAND)
                .components(Redstone, 1, Blaze, 2, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES)
                .build();

        Blizz = new Material.Builder(18501, "blizz")
                .dust(1).fluid(FluidType.FLUID, true)
                .color(0xA5F564)
                .components(Redstone, 1, Water, 1)
                .flags(NO_SMELTING, MORTAR_GRINDABLE) // BURNING
                .build();

        HastelloyK243 = new Material.Builder(18502, "hastelloy_k243")
                .ingot().fluid()
                .color(0xA5F564).iconSet(SHINY)
                .components(HastelloyX78, 5, NiobiumNitride, 2, Tritanium, 4, TungstenCarbide, 4, Promethium, 4, Mendelevium261, 1)
                .flags(GA_EXT2_METAL, DISABLE_DECOMPOSITION) // GENERATE_METAL_CASING
                .blastTemp(12400)
                .build();

        Pikyonium = new Material.Builder(18503, "pikyonium")
                .ingot(7).fluid()
                .color(0x3467BA).iconSet(SHINY)
                .components(Inconel792, 8, EglinSteel, 5, NaquadahEnriched, 4, Cerium, 3, Antimony, 2, Platinum, 2, Ytterbium, 1, TungstenSteel, 4)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION)
                .blastTemp(10500)
                .build();

        CalciumSalts = new Material.Builder(18505, "calcium_salts")
                .dust()
                .color(0xFFF5EB).iconSet(ROUGH)
                .components(Calcite, 1, Gypsum, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate = new Material.Builder(18506, "rhenium_hassium_thallium_isophtaloylbisdiethylthiourea")
                .dust()
                .color(0xA26A8B).iconSet(SHINY)
                .components(Rhenium, 1, Hassium, 1, Thallium, 1, Fullerene, 1, Phosphorus, 1, Nitrogen, 12, Hydrogen, 84, SulfurDioxide, 6, Fluorine, 6)
                .build();

        DrillingMudMixture = new Material.Builder(18507, "drilling_mud_mixture")
                .fluid()
                .colorAverage()
                .components(CaCBaSMixture, 1, LubricantClaySlurry, 1)
                .build();


        // Fifth Degree Materials, 19000-19499
        Cryotheum = new Material.Builder(19000, "cryotheum")
                .dust(1).fluid()
                .color(0x01F3F6).iconSet(SAND)
                .components(Redstone, 1, Blizz, 2, Water, 1)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES)
                .build();

        // TODO replacement for magneto resonatic
        HDCS = new Material.Builder(19001, "hdcs")
                .ingot(5).fluid()
                .color(0x334433).iconSet(SHINY)
                .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, /*MagnetoResonatic, 2,*/ Plutonium239, 1)
                .flags(GA_CORE_METAL, DISABLE_DECOMPOSITION, GENERATE_ROUND)
                .blastTemp(9900)
                .build()
                .setFormula("(FeW)12(((FeW)5CrMo2V)6CoMnSi)9((FeW)5CrMo2V)6(Ru2Ir)3((((SiO2)5Fe)3(Bi2Te3)4ZrO2Fe)2Pu");

        SuperheavyChargedBlackTitanium = new Material.Builder(19002, "superheavy_charged_black_titanium")
                .ingot()
                .color(0x883AFC)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(BlackTitanium, 3, SuperheavyHAlloy, 2, ChargedCesiumCeriumCobaltIndium, 3, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 6)
                .cableProperties(GTValues.V[GTValues.UMV], 64, 0, true)
                .blastTemp(12000)
                .build();

        NeutroniumLegendariumSuperhydride = new Material.Builder(19003, "neutronium_legendarium_superhydride")
                .ingot()
                .color(0xE34B5A)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Neutronium, 4, Legendarium, 5, ActiniumSuperhydride, 5, LanthanumFullereneNanotubes, 4, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 12)
                .cableProperties(GTValues.V[GTValues.UXV], 128, 0, true)
                .blastTemp(14000)
                .build();


        // Sixth Degree Materials, 19500-19999
        SupercooledCryotheum = new Material.Builder(19500, "supercooled_cryotheum")
                .fluid()
                .color(0x01F3EC)
                .components(Cryotheum, 1)
                .build();
    }
}
