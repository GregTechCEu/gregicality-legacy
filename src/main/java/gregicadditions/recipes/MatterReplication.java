package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTLog;

public class MatterReplication {
	public static void init() {
		//Mass Fab
		GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (Materials.Hydrogen.getMass() * 100)).EUt(32).fluidInputs(Materials.Hydrogen.getFluid(1000)).fluidOutputs(GAMaterials.PositiveMatter.getFluid(1)).buildAndRegister();
		for (Material m : Material.MATERIAL_REGISTRY) {
			if (m.getProtons() > 0 && m.getNeutrons() > 0 && m.getMass() != 98 && m instanceof FluidMaterial && OreDictUnifier.get(OrePrefix.dust, m).isEmpty()) {
				GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (m.getMass() * 100)).EUt(32).fluidInputs(((FluidMaterial) m).getFluid(1000)).fluidOutputs(GAMaterials.PositiveMatter.getFluid((int) m.getProtons()), GAMaterials.NeutralMatter.getFluid((int) m.getNeutrons())).buildAndRegister();
			}
		}
		GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (GAMaterials.Neutronium.getMass() * 100)).EUt(32).inputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Neutronium)).fluidOutputs(GAMaterials.NeutralMatter.getFluid(5000)).buildAndRegister();
		for (Material m : Material.MATERIAL_REGISTRY) {
			if (m.getProtons() >= 1 && m.getNeutrons() >= 0 && m.getMass() != 98 && m instanceof DustMaterial && m != Materials.Sphalerite && m != Materials.Naquadria && m != Materials.Ash && m != Materials.DarkAsh && m != GAMaterials.Neutronium && m != Materials.Monazite && m != Materials.Bentonite && !OreDictUnifier.get(OrePrefix.dust, m).isEmpty()) {
				GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (m.getMass() * 100)).EUt(32).inputs(OreDictUnifier.get(OrePrefix.dust, m)).fluidOutputs(GAMaterials.PositiveMatter.getFluid((int) m.getProtons()), GAMaterials.NeutralMatter.getFluid((int) m.getNeutrons())).buildAndRegister();
			}
		}

		//Replicator
		GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (Materials.Hydrogen.getMass() * 100)).EUt(32).inputs(FluidCellIngredient.getIngredient(Materials.Hydrogen, 0)).fluidOutputs(Materials.Hydrogen.getFluid(1000)).fluidInputs(GAMaterials.PositiveMatter.getFluid(1)).buildAndRegister();
		for (Material m : Material.MATERIAL_REGISTRY) {
			if (m.getProtons() > 0 && m.getNeutrons() > 0 && m.getMass() != 98 && m instanceof FluidMaterial && OreDictUnifier.get(OrePrefix.dust, m).isEmpty() && m != Materials.Air && m != Materials.LiquidAir) {
				GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (m.getMass() * 100)).EUt(32).inputs(FluidCellIngredient.getIngredient((FluidMaterial) m, 0)).fluidOutputs(((FluidMaterial) m).getFluid(1000)).fluidInputs(GAMaterials.PositiveMatter.getFluid((int) m.getProtons()), GAMaterials.NeutralMatter.getFluid((int) m.getNeutrons())).buildAndRegister();
			}
		}
		GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (GAMaterials.Neutronium.getMass() * 100)).EUt(32).notConsumable(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Neutronium)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Neutronium)).fluidInputs(GAMaterials.NeutralMatter.getFluid(5000)).buildAndRegister();
		for (Material m : Material.MATERIAL_REGISTRY) {
			if (m.getProtons() >= 1 && m.getNeutrons() >= 0 && m.getMass() != 98 && m instanceof DustMaterial && m != Materials.Sphalerite && m != Materials.Naquadria && m != Materials.Ash && m != Materials.DarkAsh && m != GAMaterials.Neutronium && m != Materials.Monazite && m != Materials.Bentonite && !OreDictUnifier.get(OrePrefix.dust, m).isEmpty()) {
				GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (m.getMass() * 100)).EUt(32).notConsumable(OreDictUnifier.get(OrePrefix.dust, m)).outputs(OreDictUnifier.get(OrePrefix.dust, m)).fluidInputs(GAMaterials.PositiveMatter.getFluid((int) m.getProtons()), GAMaterials.NeutralMatter.getFluid((int) m.getNeutrons())).buildAndRegister();
			}
		}
	}
}
