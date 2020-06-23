package gregicadditions.machines.energyconverter.utils;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class EnergyConverterCraftingHelper {
    public static final EnergyConverterCraftingHelper HELPER;

    public Consumer<MetaTileEntityEnergyConverter> logic(final RecipeFunction function) {
        return energyConverter -> {
            Object[] objs = function.createRecipe(energyConverter.getTier(), energyConverter.getSize());
            if (objs != null) {
                ModHandler.addShapedRecipe(energyConverter.metaTileEntityId.toString(), energyConverter.getStackForm(), objs);
            }
        };
    }


    public UnificationEntry redCable(final int invSize) {
        return new UnificationEntry(this.prefixCable(invSize), Materials.RedAlloy);
    }

    public UnificationEntry cable(final int tier, final int invSize) {
        return new UnificationEntry(this.prefixCable(invSize), this.cableMaterialByTier(tier));
    }

    public Material cableMaterialByTier(final int tier) {
        switch (tier) {
            case 0: {
                return Materials.Lead;
            }
            case 1: {
                return Materials.Tin;
            }
            case 2: {
                return Materials.Copper;
            }
            case 3: {
                return Materials.Gold;
            }
            case 4: {
                return Materials.Aluminium;
            }
            case 5: {
                return Materials.Platinum;
            }
            case 6: {
                return Materials.NiobiumTitanium;
            }
            case 7: {
                return Materials.Naquadah;
            }
            case 8: {
                return Materials.NaquadahAlloy;
            }
            default: {
                return MarkerMaterials.Tier.Superconductor;
            }
        }
    }

    public OrePrefix prefixCable(final int stack) {
        switch (stack) {
            case 1: {
                return OrePrefix.cableGtSingle;
            }
            case 4: {
                return OrePrefix.cableGtQuadruple;
            }
            case 9: {
                return OrePrefix.cableGtOctal;
            }
            default: {
                return OrePrefix.cableGtHex;
            }
        }
    }

    static {
        HELPER = new EnergyConverterCraftingHelper();
    }

    @FunctionalInterface
    public interface RecipeFunction {
        @Nullable
        Object[] createRecipe(final int p0, final int p1);
    }
}
