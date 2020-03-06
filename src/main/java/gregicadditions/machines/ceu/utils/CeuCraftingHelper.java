package gregicadditions.machines.ceu.utils;

import gregicadditions.machines.ceu.MTECeu;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CeuCraftingHelper {
	public static final CeuCraftingHelper HELPER;
	private ItemStack[] hulls;
	private Object[] circuits;
	private UnificationEntry[][] wires;
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

	public ItemStack hull(final int tier) {
		if (this.hulls == null) {
			this.hulls = new ItemStack[MetaTileEntities.HULL.length];
			for (int i = 0; i < this.hulls.length; ++i) {
				this.hulls[i] = MetaTileEntities.HULL[i].getStackForm();
			}
		}
		return this.hulls[tier];
	}

	public Object circuit(final int tier) {
		if (this.circuits == null) {
			this.circuits = new Object[GTValues.V.length];
			for (int i = 0; i < GTValues.V.length; ++i) {
				this.circuits[i] = circuitGTCE(i);
			}
		}
		return this.circuits[tier];
	}

	private static Object circuitGTCE(final int tier) {
		switch (tier) {
			case 0: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Primitive);
			}
			case 1: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic);
			}
			case 2: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good);
			}
			case 3: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
			}
			case 4: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Extreme);
			}
			case 5: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite);
			}
			case 6: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Master);
			}
			case 7: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate);
			}
			case 8: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor);
			}
			default: {
				return new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Infinite);
			}
		}
	}

	public UnificationEntry redCable(final int stack) {
		if (this.redCables == null) {
			this.redCables = new UnificationEntry[4];
			for (int slot = 0; slot < 4; ++slot) {
				this.redCables[slot] = new UnificationEntry(this.prefixCable(slot + 1), (Material) Materials.RedAlloy);
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
				return (Material) Materials.Lead;
			}
			case 1: {
				return (Material) Materials.Tin;
			}
			case 2: {
				return (Material) Materials.Copper;
			}
			case 3: {
				return (Material) Materials.Gold;
			}
			case 4: {
				return (Material) Materials.Aluminium;
			}
			case 5: {
				return (Material) Materials.Tungsten;
			}
			case 6: {
				return (Material) Materials.VanadiumGallium;
			}
			case 7: {
				return (Material) Materials.Naquadah;
			}
			case 8: {
				return (Material) Materials.NaquadahAlloy;
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

	public OrePrefix prefixWire(final int stack) {
		switch (stack) {
			case 1: {
				return OrePrefix.wireGtSingle;
			}
			case 2: {
				return OrePrefix.wireGtQuadruple;
			}
			case 3: {
				return OrePrefix.wireGtOctal;
			}
			default: {
				return OrePrefix.wireGtHex;
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
