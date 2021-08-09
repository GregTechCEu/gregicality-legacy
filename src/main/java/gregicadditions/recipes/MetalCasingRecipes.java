package gregicadditions.recipes;

import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.item.metal.NuclearCasing;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

import static gregicadditions.item.GAMetaBlocks.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.ore.OrePrefix.frameGt;
import static gregtech.api.unification.ore.OrePrefix.plate;

public class MetalCasingRecipes {

    public static void init() {
        Arrays.stream(NuclearCasing.CasingType.values()).forEach(casing -> {
            registerMetalCasingRecipe(casing.getMaterial(), NUCLEAR_CASING, casing);
        });

        Arrays.stream(MetalCasing1.CasingType.values()).forEach(casing -> {
            registerMetalCasingRecipe(casing.getMaterial(), METAL_CASING_1, casing);
        });

        Arrays.stream(MetalCasing2.CasingType.values()).forEach(casing -> {
            registerMetalCasingRecipe(casing.getMaterial(), METAL_CASING_2, casing);
        });
    }

    private static <T extends Enum<T> & IStringSerializable> void registerMetalCasingRecipe(Material inputMaterial, VariantBlock<T> outputCasingType, T outputCasing) {

        ModHandler.addShapedRecipe(String.format("metal_casing_%s", inputMaterial), outputCasingType.getItemVariant(outputCasing, 3),
                "PhP", "PFP", "PwP",
                'P', OreDictUnifier.get(plate, inputMaterial),
                'F', OreDictUnifier.get(frameGt, inputMaterial));

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .input(plate, inputMaterial, 6)
                .input(frameGt, inputMaterial)
                .circuitMeta(0)
                .outputs(outputCasingType.getItemVariant(outputCasing, 3))
                .buildAndRegister();
    }
}
