package gregicadditions.materials;

import com.google.common.collect.ImmutableList;
import gregicadditions.GAMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.stack.MaterialStack;

import static com.google.common.collect.ImmutableList.of;
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

        // Fourth Degree Materials
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
                .flags(GAMaterials.GA_EXT2_METAL, DISABLE_DECOMPOSITION) // GENERATE_METAL_CASING
                .blastTemp(25000)
                .build();


        public static IngotMaterial Pikyonium = new IngotMaterial(852, "pikyonium", 0x3467BA, MaterialIconSet.SHINY, 7, of(new MaterialStack(Inconel792, 8), new MaterialStack(EglinSteel, 5), new MaterialStack(NaquadahEnriched, 4), new MaterialStack(Cerium, 3), new MaterialStack(Antimony, 2), new MaterialStack(Platinum, 2), new MaterialStack(Ytterbium, 1), new MaterialStack(TungstenSteel, 4)), GA_CORE_METAL | DISABLE_DECOMPOSITION, null, 9865);
        public static SimpleFluidMaterial DrillingMudMixture = new SimpleFluidMaterial("drilling_mud_mixture", (CaCBaSMixture.rgb + LubricantClaySlurry.rgb) / 2);
        public static SimpleDustMaterial CalciumSalts = new SimpleDustMaterial("calcium_salts", Calcium.materialRGB-10, (short) 137, MaterialIconSet.ROUGH, of(new MaterialStack(Calcite, 1), new MaterialStack(Gypsum, 1)));
        public static SimpleDustMaterial RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate = new SimpleDustMaterial("rhenium_hassium_thallium_isophtaloylbisdiethylthiourea", 0xa26a8b,(short) 384, MaterialIconSet.SHINY, "ReHsTlC60PN12H84S6O12F6");


        // Fifth Degree Materials
        public static DustMaterial Cryotheum = new DustMaterial(952, "cryotheum", 0x01F3F6, SAND, 1, ImmutableList.of(new MaterialStack(Redstone, 1), new MaterialStack(Blizz, 2), new MaterialStack(Water, 1)), DISABLE_DECOMPOSITION | EXCLUDE_BLOCK_CRAFTING_RECIPES | SMELT_INTO_FLUID);
        public static IngotMaterial HDCS = new IngotMaterial(720, "hdcs", 0x334433, MaterialIconSet.SHINY, 5, of(new MaterialStack(TungstenSteel, 12), new MaterialStack(HSSS, 9), new MaterialStack(HSSG, 6), new MaterialStack(Ruridit, 3), new MaterialStack(MagnetoResonatic, 2), new MaterialStack(Plutonium, 1)), GA_CORE_METAL | DISABLE_DECOMPOSITION | GENERATE_ROUND, null, 9000);

        // Sixth Degree Materials
        public static SimpleFluidMaterial SupercooledCryotheum = new SimpleFluidMaterial("supercooled_cryotheum", Cryotheum.materialRGB-10, of(new MaterialStack(Cryotheum, 1)));
    }
}
