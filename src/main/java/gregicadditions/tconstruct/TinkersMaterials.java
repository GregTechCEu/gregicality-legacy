package gregicadditions.tconstruct;

import java.util.ArrayList;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Items;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

public class TinkersMaterials {
	private static ArrayList<slimeknights.tconstruct.library.materials.Material> ingotMaterials = new ArrayList<>();
	private static ArrayList<IngotMaterial> GtIngotmaterials = new ArrayList<>();

	private static ArrayList<slimeknights.tconstruct.library.materials.Material> gemMaterials = new ArrayList<>();
	private static ArrayList<GemMaterial> GtGemmaterials = new ArrayList<>();

	public static void preInit() {
		for (Material mat : Material.MATERIAL_REGISTRY) {
			if (mat instanceof IngotMaterial) {
				if (mat != gregtech.api.unification.material.Materials.Iron && mat != gregtech.api.unification.material.Materials.Cobalt && mat != gregtech.api.unification.material.Materials.Copper && mat != gregtech.api.unification.material.Materials.Bronze && mat != gregtech.api.unification.material.Materials.Lead && mat != gregtech.api.unification.material.Materials.Electrum && mat != gregtech.api.unification.material.Materials.Silver && mat != gregtech.api.unification.material.Materials.Steel && mat != gregtech.api.unification.material.Materials.PigIron) {
					if (((SolidMaterial) mat).toolDurability > 0) {
						ingotMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(true).setCraftable(false));
						GtIngotmaterials.add((IngotMaterial) mat);
					} else TinkerRegistry.integrate(((IngotMaterial) mat).getMaterialFluid(), upperCase(mat));
				}
				if (((IngotMaterial) mat).blastFurnaceTemperature <= 0 && GAConfig.GregsConstruct.TinkersMaterialsSmelting) {
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
				}
			}
			if (mat instanceof GemMaterial && ((GemMaterial) mat).toolDurability > 0) {
				gemMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(false).setCraftable(true));
				GtGemmaterials.add((GemMaterial) mat);
			}
			if (mat instanceof DustMaterial && !(mat instanceof IngotMaterial) && GAConfig.GregsConstruct.TinkersMaterialsSmelting) {
				DustMaterial dust = (DustMaterial) mat;
				if (dust.directSmelting != null) {
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
				} else if (dust.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID) && mat != gregtech.api.unification.material.Materials.Glass && mat != gregtech.api.unification.material.Materials.Ice) {
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, mat).toString(), dust.getMaterialFluid(), 144);
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustSmall, mat).toString(), dust.getMaterialFluid(), 36);
					TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustTiny, mat).toString(), dust.getMaterialFluid(), 16);
				}
			}
		}

		if (GAConfig.GregsConstruct.TinkersMetalTools) for (int i = 0; i < ingotMaterials.size(); i++)

		{
			slimeknights.tconstruct.library.materials.Material mat = ingotMaterials.get(i);
			IngotMaterial GtMat = GtIngotmaterials.get(i);
			mat.addCommonItems(upperCase(GtMat));
			mat.setFluid(GtMat.getMaterialFluid());
			mat.addItemIngot(new UnificationEntry(OrePrefix.ingot, GtMat).toString());
			mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, GtMat));
			if (TinkerRegistry.getMaterial(mat.identifier) == slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
				TinkerRegistry.addMaterial(mat);
				TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats((int) (GtMat.toolDurability * 0.8), GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats((GtMat.harvestLevel - 0.5f) / 2, GtMat.toolDurability / 3), new ExtraMaterialStats(GtMat.toolDurability / 4));
				TinkerRegistry.integrate(mat, mat.getFluid(), upperCase(GtMat));
			}
		}

		if (GAConfig.GregsConstruct.TinkersGemTools) for (int i = 0; i < gemMaterials.size(); i++)

		{
			slimeknights.tconstruct.library.materials.Material mat = gemMaterials.get(i);
			GemMaterial GtMat = GtGemmaterials.get(i);
			mat.addCommonItems(upperCase(GtMat));
			mat.addItemIngot(new UnificationEntry(OrePrefix.gem, GtMat).toString());
			mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.gem, GtMat));
			if (TinkerRegistry.getMaterial(mat.identifier) == slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
				TinkerRegistry.addMaterial(mat);
				TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats(GtMat.harvestLevel - 0.5f, GtMat.toolDurability / 4), new ExtraMaterialStats(GtMat.toolDurability / 100));
				TinkerRegistry.integrate(mat, upperCase(GtMat));
			}
		}

		if (GAConfig.GregsConstruct.TinkersMaterialAlloying) {
			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Brass.getFluid(4), gregtech.api.unification.material.Materials.Copper.getFluid(3), gregtech.api.unification.material.Materials.Zinc.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Cupronickel.getFluid(2), gregtech.api.unification.material.Materials.Copper.getFluid(1), gregtech.api.unification.material.Materials.Nickel.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.RedAlloy.getFluid(1), gregtech.api.unification.material.Materials.Copper.getFluid(1), gregtech.api.unification.material.Materials.Redstone.getFluid(4));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Brass.getFluid(4), gregtech.api.unification.material.Materials.AnnealedCopper.getFluid(3), gregtech.api.unification.material.Materials.Zinc.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Cupronickel.getFluid(2), gregtech.api.unification.material.Materials.AnnealedCopper.getFluid(1), gregtech.api.unification.material.Materials.Nickel.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.RedAlloy.getFluid(1), gregtech.api.unification.material.Materials.AnnealedCopper.getFluid(1), gregtech.api.unification.material.Materials.Redstone.getFluid(4));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.TinAlloy.getFluid(2), gregtech.api.unification.material.Materials.Iron.getFluid(1), gregtech.api.unification.material.Materials.Tin.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Invar.getFluid(3), gregtech.api.unification.material.Materials.Iron.getFluid(1), gregtech.api.unification.material.Materials.Nickel.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.TinAlloy.getFluid(2), gregtech.api.unification.material.Materials.WroughtIron.getFluid(1), gregtech.api.unification.material.Materials.Tin.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Invar.getFluid(3), gregtech.api.unification.material.Materials.WroughtIron.getFluid(1), gregtech.api.unification.material.Materials.Nickel.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.BatteryAlloy.getFluid(5), gregtech.api.unification.material.Materials.Lead.getFluid(4), gregtech.api.unification.material.Materials.Antimony.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.SolderingAlloy.getFluid(10), gregtech.api.unification.material.Materials.Tin.getFluid(9), gregtech.api.unification.material.Materials.Antimony.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Magnalium.getFluid(3), gregtech.api.unification.material.Materials.Aluminium.getFluid(2), gregtech.api.unification.material.Materials.Magnesium.getFluid(1));

			TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.CobaltBrass.getFluid(9), gregtech.api.unification.material.Materials.Brass.getFluid(7), gregtech.api.unification.material.Materials.Aluminium.getFluid(1), gregtech.api.unification.material.Materials.Sodium.getFluid(1));
		}

		TinkerRegistry.registerMelting("dustGlass", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 1000);
		TinkerRegistry.registerMelting("dustQuartzite", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 1000);
		TinkerRegistry.registerMelting("plateGlass", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 1000);
		TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 1000);
		TinkerRegistry.registerMelting("gemGlass", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 1000);
	}

	private static String upperCase(Material mat) {
		return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
	}
}