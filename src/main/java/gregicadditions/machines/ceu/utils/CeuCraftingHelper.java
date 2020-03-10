package gregicadditions.machines.ceu.utils;

import gregicadditions.machines.ceu.MTECeu;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CeuCraftingHelper {
	public static final CeuCraftingHelper HELPER;
	private UnificationEntry[] redCables;
	private UnificationEntry[][] cables;

	public Consumer<MTECeu> logic(final RecipeFunction function) {
		return ceu -> {
			Object[] objs = function.createRecipe(ceu.getTier(), ceu.getSize());
			if (objs != null) {
				ModHandler.addShapedRecipe(ceu.metaTileEntityId.toString(), ceu.getStackForm(), objs);
			}
		};
	}



	public UnificationEntry redCable(final int stack) {
		if (this.redCables == null) {
			this.redCables = new UnificationEntry[4];
			for (int slot = 0; slot < 4; ++slot) {
				this.redCables[slot] = new UnificationEntry(this.prefixCable(slot + 1), Materials.RedAlloy);
			}
		}
		return this.redCables[stack - 1];
	}

	public UnificationEntry cable(final int tier, final int stack) {
		if (this.cables == null) {
			this.cables = new UnificationEntry[GTValues.V.length][4];
			for (int t = 0; t < this.cables.length; ++t) {
				for (int s = 0; s < 4; ++s) {
					this.cables[t][s] = new UnificationEntry(this.prefixCable(s + 1), this.cableMaterialByTier(t));
				}
			}
		}
		return this.cables[tier][stack - 1];
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
			case 2: {
				return OrePrefix.cableGtQuadruple;
			}
			case 3: {
				return OrePrefix.cableGtOctal;
			}
			default: {
				return OrePrefix.cableGtHex;
			}
		}
	}

	static {
		HELPER = new CeuCraftingHelper();
	}

	@FunctionalInterface
	public interface RecipeFunction {
		@Nullable
		Object[] createRecipe(final int p0, final int p1);
	}
}
