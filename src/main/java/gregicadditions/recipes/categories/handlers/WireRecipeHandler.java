package gregicadditions.recipes.categories.handlers;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.utils.Tuple;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.INSULATION_WIRE_ASSEMBLY;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;
import static gregtech.api.GTValues.M;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.UNPACKER_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.oreprocessing.WireRecipeHandler.INSULATION_MATERIALS;

public class WireRecipeHandler {

    private static final List<Tuple<IngotMaterial, Integer>> GA_INSULATIONS = Arrays.asList(
            new Tuple<>(Rubber,                 GAValues.LV),
            new Tuple<>(Polycaprolactam,        GAValues.MV),
            new Tuple<>(Plastic,                GAValues.HV),
            new Tuple<>(PolyvinylChloride,      GAValues.EV),
            new Tuple<>(PolyphenyleneSulfide,   GAValues.LuV),
            new Tuple<>(Polybenzimidazole,      GAValues.UV),
            new Tuple<>(Polyetheretherketone,   GAValues.UEV),
            new Tuple<>(Zylon,                  GAValues.UMV),
            new Tuple<>(FullerenePolymerMatrix, GAValues.MAX)
    );

    private static final List<Integer> BASE_INSULATION = Arrays.asList(
            4,   // 1x
            4,   // 2x
            8,   // 4x
            8,   // 8x
            16   // 16x
    );

    private static final int INSULATION_ASSEMBLY_TIER = GAValues.UMV;

    private static final OrePrefix[] WIRE_DOUBLING_ORDER = new OrePrefix[]{wireGtSingle, wireGtDouble, wireGtQuadruple, wireGtOctal, wireGtHex};
    private static final OrePrefix[] CABLE_DOUBLING_ORDER = new OrePrefix[]{cableGtSingle, cableGtDouble, cableGtQuadruple, cableGtOctal, cableGtHex};
    private static final Map<Integer, Integer> MATERIAL_MAPPING = new HashMap<>();

    public static void register() {

        List<Integer> insulationCutoffs = GA_INSULATIONS.stream().map(Tuple::getValue).collect(Collectors.toList());
        int insulationIndex = 0;
        for (int i = GAValues.ULV; i <= GAValues.MAX; i++) {
            int cutoff = insulationCutoffs.get(insulationIndex);
            if (i <= cutoff)
                MATERIAL_MAPPING.put(i, insulationIndex);
            if (i == cutoff)
                insulationIndex++;
        }

        for (OrePrefix wirePrefix : WIRE_DOUBLING_ORDER)
            wirePrefix.addProcessingHandler(IngotMaterial.class, WireRecipeHandler::processWireGt);

        cableGtSingle.addProcessingHandler(IngotMaterial.class, WireRecipeHandler::processCableStripping);
    }

    // TODO Javadoc
    private static void processWireGt(OrePrefix wireGt, IngotMaterial material) {
        if (material.cableProperties == null) return;

        int cableAmount = (int) (wireGt.materialAmount * 2 / M);
        int cableTier = GAUtility.getTierByVoltage(material.cableProperties.voltage);
        int cableSize = ArrayUtils.indexOf(WIRE_DOUBLING_ORDER, wireGt);
        OrePrefix cablePrefix = valueOf("cable" + wireGt.name().substring(4)); // TODO

        // Removals of GTCE Recipes
        for (FluidMaterial fluid : INSULATION_MATERIALS.keySet()) {

            int materialAmount = getGTCECableMaterialAmount(cableTier, INSULATION_MATERIALS.get(fluid));
            if (materialAmount == -1) continue;

            // Remove the 1x -> larger cable recipe
            if (wireGt != wireGtSingle)
                removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, material, cableAmount), getIntegratedCircuit(24 + cableSize)}, new FluidStack[]{fluid.getFluid(materialAmount * cableAmount)});

            // Remove the normal cable covering recipe
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGt, material), getIntegratedCircuit(24)}, new FluidStack[]{fluid.getFluid(materialAmount * cableAmount)});
        }

        // Addition of new Recipes
        for (int i = 0; i < GA_INSULATIONS.size(); i++) {

            Tuple<IngotMaterial, Integer> insulationData = GA_INSULATIONS.get(i);
            if (cableTier > insulationData.getValue()) continue;

            int materialAmount = getCableMaterialAmount(cableTier, cableSize, i);
            if (materialAmount == -1) continue;

            if (wireGt != wireGtSingle) {

                RecipeBuilder<?> builder = ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                        .input(wireGtSingle, material, cableAmount)
                        .circuitMeta(24 + cableSize)
                        .input(foil, insulationData.getKey(), materialAmount)
                        .output(cablePrefix, material);

                if (cableTier >= INSULATION_ASSEMBLY_TIER)
                    builder.inputs(INSULATION_WIRE_ASSEMBLY.getStackForm(Math.max(1, materialAmount / 2)));

                builder.buildAndRegister();
            }

            RecipeBuilder<?> builder = ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8)
                    .circuitMeta(24)
                    .input(wireGt, material)
                    .input(foil, insulationData.getKey(), materialAmount)
                    .output(cablePrefix, material);

            if (cableTier >= INSULATION_ASSEMBLY_TIER)
                builder.inputs(INSULATION_WIRE_ASSEMBLY.getStackForm(Math.max(1, materialAmount / 2)));

            builder.buildAndRegister();
        }
    }

    /**
     * Cable Stripping Material Handler. Generates:
     *
     * + Cable -> Wire Stripping recipes for all Cables
     */
    private static void processCableStripping(OrePrefix prefix, IngotMaterial material) {
        if (material.cableProperties != null) {

            for (int i = 0; i < CABLE_DOUBLING_ORDER.length; i++) {

                UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(7)
                        .input(CABLE_DOUBLING_ORDER[i], material)
                        .notConsumable(new IntCircuitIngredient(0))
                        .output(WIRE_DOUBLING_ORDER[i], material)
                        .buildAndRegister();
            }
        }
    }

    // TODO Javadoc
    private static int getGTCECableMaterialAmount(int cableTier, int insulationTier) {
        if (cableTier > insulationTier)
            return -1;

        int insulationDiscount = (insulationTier - cableTier) / 2;
        return Math.max(36, 144 / (1 + insulationDiscount));
    }

    // TODO Javadoc
    private static int getCableMaterialAmount(int cableTier, int cableSize, int insulationIndex) {

        int insulationDiscount = insulationIndex - MATERIAL_MAPPING.get(cableTier);
        if (insulationDiscount < 0)
            return -1;

        return Math.max(1, BASE_INSULATION.get(cableSize) / (int) Math.pow(2, insulationDiscount));
    }
}
