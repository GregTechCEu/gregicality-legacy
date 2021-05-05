package gregicadditions.machines.multi;

import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.item.metal.NuclearCasing;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;

import static gregicadditions.item.GAMetaBlocks.*;
import static gregtech.api.render.Textures.*;
import static gregtech.api.render.Textures.SOLID_STEEL_CASING;

public class MultiUtils {

    /**
     * Expand this method when new casing classes are created
     *
     * @return Config Casing BlockState
     */
    public static IBlockState getConfigCasing(String casingMaterial, IBlockState defaultState) {
        switch (casingMaterial) {
            case "bronze": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS);
            }
            case "invar": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
            }
            case "aluminium": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF);
            }
            case "steel": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
            }
            case "stainless_steel": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
            }
            case "titanium": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
            }
            case "tungstensteel": {
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
            }
        }
        for (MetalCasing1.CasingType casingType : MetalCasing1.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return METAL_CASING_1.getState(casingType);
        }
        for (MetalCasing2.CasingType casingType : MetalCasing2.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return METAL_CASING_2.getState(casingType);
        }
        for (NuclearCasing.CasingType casingType : NuclearCasing.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return NUCLEAR_CASING.getState(casingType);
        }

        return defaultState;
    }

    /**
     * Expand this method when new casing classes are created
     *
     * @return Config Casing Texture
     */
    public static ICubeRenderer getConfigCasingTexture(String casingMaterial, ICubeRenderer defaultCasingTexture) {
        switch (casingMaterial) {
            case "bronze": {
                return BRONZE_PLATED_BRICKS;
            }
            case "invar": {
                return HEAT_PROOF_CASING;
            }
            case "aluminium": {
                return FROST_PROOF_CASING;
            }
            case "steel": {
                return SOLID_STEEL_CASING;
            }
            case "stainless_steel": {
                return CLEAN_STAINLESS_STEEL_CASING;
            }
            case "titanium": {
                return STABLE_TITANIUM_CASING;
            }
            case "tungstensteel": {
                return ROBUST_TUNGSTENSTEEL_CASING;
            }
        }
        for (MetalCasing1.CasingType casingType : MetalCasing1.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return casingType.getTexture();
        }
        for (MetalCasing2.CasingType casingType : MetalCasing2.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return casingType.getTexture();
        }
        for (NuclearCasing.CasingType casingType : NuclearCasing.CasingType.values()) {
            if (casingType.getName().equals("casing_" + casingMaterial))
                return casingType.getTexture();
        }

        return defaultCasingTexture;
    }
}
