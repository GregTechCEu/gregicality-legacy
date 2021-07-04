package gregicadditions.integrations.bees;

import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleSpeciesRegisterEvent;
import forestry.api.genetics.IAllele;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import forestry.core.genetics.mutations.MutationConditionRequiresResource;
import gregicadditions.GAMaterials;
import gregicadditions.Gregicality;
import gregicadditions.integrations.bees.effects.GTBeesEffects;
import gregicadditions.integrations.bees.mutation.MutationConditionFluid;
import gregicadditions.integrations.bees.mutation.MutationConditionMetaValueItem;
import gregicadditions.item.GAMetaItems;
import gregicadditions.materials.SimpleFluidMaterial;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import org.apache.commons.lang3.text.WordUtils;
import javax.annotation.Nonnull;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum GTBees implements IBeeDefinition {
	//FLUIDISs
	WATER(Materials.Water, 0.20f){
		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.ICY, 30).addMutationCondition(new MutationConditionFluid(Materials.Water));
		}
	},
	LAVA(Materials.Lava, 0.20f){
		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.IMPERIAL, BeeDefinition.SINISTER, 30).addMutationCondition(new MutationConditionFluid(Materials.Lava));
		}
	},
	NITROGEN(Materials.Nitrogen, 0.35f) {
		@Override
		protected void registerMutations() {
			registerMutation(WATER, LAVA, 20).addMutationCondition(new MutationConditionFluid(Materials.Nitrogen));
		}
	},
	HYDROGEN(Materials.Hydrogen, 0.35f) {
		@Override
		protected void registerMutations() {
			registerMutation(WATER, LAVA, 20).addMutationCondition(new MutationConditionFluid(Materials.Hydrogen));
		}
	},
	OXYGEN(Materials.Oxygen, 0.35f) {
		@Override
		protected void registerMutations() {
			registerMutation(WATER, LAVA, 20).addMutationCondition(new MutationConditionFluid(Materials.Oxygen));
		}
	},
	AIR(Materials.Air, 0.10f) {
		@Override
		protected void registerMutations() {
			registerMutation(OXYGEN, NITROGEN, 10).addMutationCondition(new MutationConditionFluid(Materials.Air));
		}
	},
	STEAM(Materials.Steam, 0.80f) {
		@Override
		protected void registerMutations() {
			registerMutation(COAL, WATER, 5).addMutationCondition(new MutationConditionFluid(Materials.Steam));
		}
	},
	CARBONDIOXIDE(Materials.CarbonDioxide, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(COAL, OXYGEN, 10).addMutationCondition(new MutationConditionFluid(Materials.CarbonDioxide));
		}
	},
	SALTWATER(Materials.SaltWater, 0.50f) {
		@Override
		protected void registerMutations() {
			registerMutation(WATER, HYDROGEN, 10).addMutationCondition(new MutationConditionFluid(Materials.SaltWater));
		}
	},
	AMMONIA(Materials.Ammonia, 0.10f) {
		@Override
		protected void registerMutations() {
			registerMutation(SALTWATER, NITROGEN, 5).addMutationCondition(new MutationConditionFluid(Materials.Ammonia));
		}
	},
	CHLORINE(Materials.Chlorine, 0.35f) {
		@Override
		protected void registerMutations() {
			registerMutation(SALTWATER, LAVA, 3).addMutationCondition(new MutationConditionFluid(Materials.Chlorine));
		}
	},
	SULFURICACID(Materials.SulfuricAcid, 0.35f) {
		@Override
		protected void registerMutations() {
			registerMutation(DIAMOND, AIR, 3).addMutationCondition(new MutationConditionFluid(Materials.SulfuricAcid));
		}
	},
	CRYOTHEUM(GAMaterials.Cryotheum, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(WATER, AMMONIA, 2).addMutationCondition(new MutationConditionFluid(GAMaterials.Cryotheum));
		}
	},
	METHANE(Materials.Methane, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(STEAM, AMMONIA, 5).addMutationCondition(new MutationConditionFluid(Materials.Methane));
		}
	},
	ETHYLENE(Materials.Ethylene, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(SULFURICACID, ETHANOL, 2).addMutationCondition(new MutationConditionFluid(Materials.Ethylene));
		}
	},
	ETHANOL(Materials.Ethanol, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(REDSTONE, CARBONDIOXIDE, 5).addMutationCondition(new MutationConditionFluid(Materials.Ethanol));
		}
	},
	PYROTHEUM(GAMaterials.Pyrotheum, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(LAVA, SULFURICACID, 2).addMutationCondition(new MutationConditionFluid(GAMaterials.Pyrotheum));
		}
	},
	RUBBERF(Materials.Rubber, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(RUBBER, ETHYLENE, 5).addMutationCondition(new MutationConditionFluid(5,100,Materials.Rubber));
		}
	},
	LUBRICANT(Materials.Lubricant, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(OIL, ETHANOL, 3).addMutationCondition(new MutationConditionFluid(5,100,Materials.Lubricant));
		}
	},
	MERCURY(Materials.Mercury, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(CHLORINE, BENZENE, 5).addMutationCondition(new MutationConditionFluid(5,100,Materials.Mercury));
		}
	},
	BENZENE(Materials.Benzene, 0.25f) {
		@Override
		protected void registerMutations() {
			registerMutation(RUBBERF, OIL, 1).addMutationCondition(new MutationConditionFluid(5,100,Materials.Benzene));
		}
	},
	FLUORINE(Materials.Fluorine, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(LUBRICANT, EMERALD, 1).addMutationCondition(new MutationConditionFluid(5,100,Materials.Fluorine));
		}
	},
	SUPERCOOLEDCRYOTHEUM(GAMaterials.SupercooledCryotheum, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(CRYOTHEUM, MERCURY, 1).addMutationCondition(new MutationConditionFluid(5,100,GAMaterials.SupercooledCryotheum));
		}
	},
	STYRENEBUTADIENERUBBER(Materials.StyreneButadieneRubber, 0.15f) {
		@Override
		protected void registerMutations() {
			registerMutation(RUBBERF, FLUORINE, 1).addMutationCondition(new MutationConditionFluid(5,100,Materials.StyreneButadieneRubber));
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_ACCELERATE);
		}
	},
	NEUTRALMATTER(GAMaterials.NeutralMatter, 0.01f) {
		@Override
		protected void registerMutations() {
			registerMutation(MERCURY, URANIUM, 1).addMutationCondition(new MutationConditionFluid(10,70, GAMaterials.NeutralMatter));
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_FIX);
		}
	},
	POSITIVEMATTER(GAMaterials.PositiveMatter, 0.01f) {
		@Override
		protected void registerMutations() {
			registerMutation(FLUORINE, PLUTONIUM, 1).addMutationCondition(new MutationConditionFluid(10,70, GAMaterials.PositiveMatter));
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_ENERGY);
		}
	},
	UUMATTER(Materials.UUMatter, 0.01f) {
		@Override
		protected void registerMutations() {
			registerMutation(NEUTRALMATTER, POSITIVEMATTER, 1).addMutationCondition(new MutationConditionFluid(20,50, Materials.UUMatter));
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_FLUID);
		}
	},

	//FUELISs
	CLAY(GTBranches.FUELIS, "clay", true, new Color(0x19d0ec), new Color(0xe0c113)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(ModuleApiculture.getItems().beeComb.get(EnumHoneyComb.HONEY, 1), 0.3f);
			beeSpecies.addProduct(new ItemStack(Items.CLAY_BALL), 0.45f).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.DILIGENT, 20);
		}
	},
	SLIME(GTBranches.FUELIS, "slime", true, new Color(0x4e9e55), new Color(0x00e012)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.RUBBERY, 1), 0.3f);
			beeSpecies.addProduct(ModuleApiculture.getItems().beeComb.get(EnumHoneyComb.MOSSY, 1), 0.3f).setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.MARSHY, CLAY, 15);
		}
	},
	LIGNITE(GTBranches.FUELIS, "lignite", true, new Color(0x906237), new Color(0x522d0a)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(ModuleApiculture.getItems().beeComb.get(EnumHoneyComb.HONEY, 1), 0.15f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.LIGNITE, 1), 0.3f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.RURAL, CLAY, 20);
		}
	},
	RUBBER(GTBranches.FUELIS, "rubbery", true, new Color(0x2e8f5b), new Color(0xdcc289)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(MetaItems.RUBBER_DROP.getStackForm(), 0.15f);
			beeSpecies.addProduct(ModuleApiculture.getItems().beeComb.get(EnumHoneyComb.HONEY, 1), 0.3f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(SLIME, LIGNITE, 25);
		}
	},
	COAL(GTBranches.FUELIS, "coal", true, new Color(0x666666), new Color(0x484848)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.LIGNITE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.COAL, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.INDUSTRIOUS, LIGNITE, 18);
		}
	},
	OIL(GTBranches.FUELIS, "oil", true, new Color(0x4c4c4c), new Color(0x2d2d2d)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.RUBBERY, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.OIL, 1), 0.15f).setNocturnal().setHumidity(EnumHumidity.DAMP);
		}

		@Override
		protected void registerMutations() {
			registerMutation(COAL, RUBBER, 8);
		}
	},
	REDSTONE(GTBranches.ORNAMENTIS, "redstone", true, new Color(0x7d0f0f), new Color(0xb81616)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.REDSTONE, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.INDUSTRIOUS, BeeDefinition.DEMONIC, 20);
		}
	},
	LAPIS(GTBranches.ORNAMENTIS, "lapis", true, new Color(0x1947d), new Color(0x3e5fbf)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.LAPIS, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.DEMONIC, BeeDefinition.IMPERIAL, 20);
		}
	},
	CERTUS(GTBranches.ORNAMENTIS, "certus", true, new Color(0x57cffb), new Color(0xaedded)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.CERTUS, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.HERMITIC, LAPIS, 20);
		}
	},
	RUBY(GTBranches.ORNAMENTIS, "ruby", true, new Color(0xe6005c), new Color(0xbe004c)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.RUBY, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(REDSTONE, DIAMOND, 10);
		}
	},
	SAPPHIRE(GTBranches.ORNAMENTIS, "sapphire", true, new Color(0x0033cc), new Color(0x002185)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SAPPHIRE, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(CERTUS, LAPIS, 10);
		}
	},
	DIAMOND(GTBranches.ORNAMENTIS, "diamond", true, new Color(0xccffff), new Color(0xa3cccc)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.DIAMOND, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(CERTUS, COAL, 6);
		}
	},
	OLIVINE(GTBranches.ORNAMENTIS, "olivine", true, new Color(0x248f24), new Color(0xbeedbe)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.OLIVINE, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(CERTUS, BeeDefinition.ENDED, 10);
		}
	},
	EMERALD(GTBranches.ORNAMENTIS, "emerald", true, new Color(0x248f24), new Color(0x2bab2b)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STONE, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.EMERALD, 1), 0.15f).setTemperature(EnumTemperature.COLD);
		}

		@Override
		protected void registerMutations() {
			registerMutation(OLIVINE, DIAMOND, 8);
		}
	},
	//Metaliferis
	COPPER(GTBranches.METALIFERIS, "copper", true, new Color(0xff6600), new Color(0xca5100), Materials.Copper) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.COPPON, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.MAJESTIC, CLAY, 25);
		}
	},
	TIN(GTBranches.METALIFERIS, "tin", true, new Color(0xd4d4d4), new Color(0xcdcdcd), Materials.Tin) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.TINE, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(CLAY, BeeDefinition.DILIGENT, 25);
		}
	},
	LEAD(GTBranches.METALIFERIS, "lead", true, new Color(0x666699), new Color(0x9393b8), Materials.Lead) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.PLUMBIA, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(COAL, COPPER, 25);
		}
	},
	IRON(GTBranches.METALIFERIS, "iron", true, new Color(0xda9147), new Color(0xde9c59), Materials.Iron) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.FERRU, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(TIN, COPPER, 25);
		}
	},
	STEEL(GTBranches.METALIFERIS, "steel", true, new Color(0x808080), new Color(0x8a8a8a), Materials.Steel) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STEELDUST, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(IRON, COAL, 20);
		}
	},
	NICKEL(GTBranches.METALIFERIS, "nickel", true, new Color(0x8585ad), new Color(0x7c7ca1), Materials.Nickel) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.NICKELDUST, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(IRON, COPPER, 25);
		}
	},
	ZINC(GTBranches.METALIFERIS, "zinc", true, new Color(0xf0def0), new Color(0xe1d1e1), Materials.Zinc) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.GALVANIA, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(IRON, TIN, 20);
		}
	},
	SILVER(GTBranches.METALIFERIS, "zinc", true, new Color(0xc2c2d6), new Color(0xbfbfce), Materials.Silver) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.ARGENTIA, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(LEAD, TIN, 20);
		}
	},
	GOLD(GTBranches.METALIFERIS, "gold", true, new Color(0xebc633), new Color(0xd6b840), Materials.Gold) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.AURELIA, 1), 0.15f);
		}

		@Override
		protected void registerMutations() {
			registerMutation(LEAD, COPPER, 20);
		}
	},
	//Minerallis
	ALUMINIUM(GTBranches.MINERALLIS, "aluminium", true, new Color(0xb8b8ff), new Color(0xc1c1e6)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.BAUXIA, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(NICKEL, ZINC, 18);
		}
	},
	TITANIUM(GTBranches.MINERALLIS, "titanium", true, new Color(0xcc99ff), new Color(0xccabed)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.TITANIUM, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(REDSTONE, ALUMINIUM, 5);
		}
	},
	CHROME(GTBranches.MINERALLIS, "chrome", true, new Color(0xeba1eb), new Color(0xe1b5e1)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.CHROMIUM, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(TITANIUM, RUBY, 5).addMutationCondition(new MutationConditionRequiresResource("blockChrome")).addMutationCondition(new MutationConditionMetaValueItem(1, 100,
					GAMetaItems.PROTACTINIUM_WASTE,
					GAMetaItems.NUCLEAR_WASTE,
					GAMetaItems.THORIUM_WASTE));
		}
	},
	MANGANESE(GTBranches.MINERALLIS, "manganese", true, new Color(0xd5d5d5), new Color(0x999999)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.PYROLUSIUM, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(TITANIUM, ALUMINIUM, 5).addMutationCondition(new MutationConditionRequiresResource("blockManganese")).addMutationCondition(new MutationConditionMetaValueItem(1, 100,
					GAMetaItems.PROTACTINIUM_WASTE,
					GAMetaItems.NUCLEAR_WASTE,
					GAMetaItems.THORIUM_WASTE));
		}
	},
	TUNGSTEN(GTBranches.MINERALLIS, "tungsten", true, new Color(0x5c5c8a), new Color(0x717191)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SCHEELINIUM, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.HEROIC, MANGANESE, 5).addMutationCondition(new MutationConditionRequiresResource("blockTungsten"))
					.addMutationCondition(new MutationConditionMetaValueItem(1, 100,
							GAMetaItems.URANIUM_WASTE,
							GAMetaItems.NEPTUNIUM_WASTE,
							GAMetaItems.PLUTONIUM_WASTE));
		}
	},
	PLATINUM(GTBranches.MINERALLIS, "platinum", true, new Color(0xe6e6e6), new Color(0xededbe)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.PLATINA, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HOT);
		}

		@Override
		protected void registerMutations() {
			registerMutation(DIAMOND, CHROME, 5).addMutationCondition(new MutationConditionMetaValueItem(1, 100,
					GAMetaItems.AMERICIUM_WASTE,
					GAMetaItems.CURIUM_WASTE,
					GAMetaItems.BERKELIUM_WASTE));
		}
	},
	IRIDIUM(GTBranches.MINERALLIS, "iridium", true, new Color(0xdadada), new Color(0xbcbcca)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.QUANTARIA, 1), 0.15f).setHumidity(EnumHumidity.ARID).setTemperature(EnumTemperature.HELLISH);
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.SPEED, EnumAllele.Speed.FASTEST);
		}

		@Override
		protected void registerMutations() {
			registerMutation(TUNGSTEN, PLATINUM, 5).addMutationCondition(new MutationConditionRequiresResource("blockIridium"))
					.addMutationCondition(new MutationConditionMetaValueItem(1, 100,
							GAMetaItems.CALIFORNIUM_WASTE,
							GAMetaItems.EINSTEINIUM_WASTE,
							GAMetaItems.FERMIUM_WASTE));
		}
	},
	URANIUM(GTBranches.CRITICALIS, "uranium", true, new Color(0x19af19), new Color(0x149314)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.URANIA, 1), 0.15f).setTemperature(EnumTemperature.COLD);
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_FIX);
		}

		@Override
		protected void registerMutations() {
			registerMutation(BeeDefinition.AVENGING, PLATINUM, 5).addMutationCondition(new MutationConditionRequiresResource("blockUranium"))
					.addMutationCondition(new MutationConditionMetaValueItem(1, 100,
							GAMetaItems.MENDELEVIUM_WASTE,
							GAMetaItems.NUCLEAR_WASTE_LANTHANIDE_A,
							GAMetaItems.NUCLEAR_WASTE_LANTHANIDE_B));
		}
	},
	PLUTONIUM(GTBranches.CRITICALIS, "platinum", true, new Color(0x335c33), new Color(0x638500)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.PLUTONIUM, 1), 0.15f).setTemperature(EnumTemperature.ICY);
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_ENERGY);
		}

		@Override
		protected void registerMutations() {
			registerMutation(URANIUM, EMERALD, 5).addMutationCondition(new MutationConditionRequiresResource("blockPlutonium"))
					.addMutationCondition(new MutationConditionMetaValueItem(1, 100,
							GAMetaItems.NUCLEAR_WASTE_METAL_A,
							GAMetaItems.NUCLEAR_WASTE_METAL_B,
							GAMetaItems.NUCLEAR_WASTE_METAL_C));
		}
	},
	NAQUADAH(GTBranches.CRITICALIS, "naquadah", true, new Color(0x003300), new Color(0x002000)) {
		@Override
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies) {
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.SLAG, 1), 0.3f);
			beeSpecies.addProduct(GTCombItem.getComb(GTCombs.STARGATIUM, 1), 0.15f).setTemperature(EnumTemperature.ICY).setHumidity(EnumHumidity.ARID);
		}

		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, GTBeesEffects.GT_ACCELERATE);
		}

		@Override
		protected void registerMutations() {
			registerMutation(PLUTONIUM, IRIDIUM, 3).addMutationCondition(new MutationConditionRequiresResource("blockNaquadah"))
					.addMutationCondition(new MutationConditionMetaValueItem(1, 100,
							GAMetaItems.NUCLEAR_WASTE_REACTIVE_NONMETAL,
							GAMetaItems.NUCLEAR_WASTE_METALOID,
							GAMetaItems.NUCLEAR_WASTE_HEAVY_METAL));
		}
	};
	private final GTBranches branch;
	private final IAlleleBeeSpecies species;
	private static final class Fluids {
		static Map<String, Fluid> fluidMap = new HashMap<>();
		static {
			fluidMap.put("extrabees.species.water", Materials.Water.getMaterialFluid());
		}
	}

	@Nullable
	private IAllele[] template;
	@Nullable
	private IBeeGenome genome;

	GTBees(GTBranches branch, String binomial, boolean dominant, Color primary, Color secondary) {
		String lowercaseName = this.toString().toLowerCase(Locale.ENGLISH);
		String species = "species" + WordUtils.capitalize(lowercaseName);

		String modId = Gregicality.MODID;
		String uid = modId + '.' + species;
		String description = "for.description." + lowercaseName;
		String name = "for.bees.species." + lowercaseName;

		this.branch = branch;
		IAlleleBeeSpeciesBuilder speciesBuilder = BeeManager.beeFactory.createSpecies(modId, uid, dominant, "Sengir", name, description, branch.getBranch(), binomial, primary.getRGB(), secondary.getRGB());
		if (isSecret()) {
			speciesBuilder.setIsSecret();
		}
		setSpeciesProperties(speciesBuilder);
		this.species = speciesBuilder.build();
	}

	GTBees(GTBranches branch, String binomial, boolean dominant, Color primary, Color secondary, FluidMaterial fluidMaterial) {
		this(branch, binomial, dominant, primary, secondary);
		Fluids.fluidMap.put(getUid(this.toString()), fluidMaterial.getMaterialFluid());
	}

	GTBees(Fluid fluid, String binomial, int color, float chance) {
		String lowercaseName = this.toString().toLowerCase(Locale.ENGLISH);
		String species = "species" + WordUtils.capitalize(lowercaseName);

		String modId = Gregicality.MODID;
		String uid = modId + '.' + species;
		String description = "for.description." + lowercaseName;
		String name = fluid.getUnlocalizedName();
		Fluids.fluidMap.put(uid, fluid);
		this.branch = GTBranches.FLUIDIS;
		IAlleleBeeSpeciesBuilder speciesBuilder = BeeManager.beeFactory.createSpecies(modId, uid, true, "Sengir", name, description, branch.getBranch(), binomial, color, new Color(color).darker().darker().getRGB());
		if (GTCombs.hasCombs(this.toString())) {
			speciesBuilder.addProduct(GTCombItem.getComb(GTCombs.get(this.toString()), 1), chance);
		}
		setSpeciesProperties(speciesBuilder);
		this.species = speciesBuilder.build();
	}

	GTBees(FluidMaterial fluidMaterial, float chance) {
		this(fluidMaterial.getMaterialFluid(), fluidMaterial.toString().toLowerCase(), fluidMaterial.materialRGB, chance);
	}

	GTBees(SimpleFluidMaterial simpleFluidMaterial, float chance) {
		this(simpleFluidMaterial.fluid, simpleFluidMaterial.name.toLowerCase(), simpleFluidMaterial.rgb, chance);
	}

	protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies){};

	protected void setAlleles(IAllele[] template) {};

	protected abstract void registerMutations();

	protected boolean isSecret() {
		return false;
	}

	public static void initBees() {
		for (GTBees bee : values()) {
			bee.init();
		}
		for (GTBees bee : values()) {
			bee.registerMutations();
		}
	}

	public static void preInit() {
		MinecraftForge.EVENT_BUS.post(new AlleleSpeciesRegisterEvent<>(BeeManager.beeRoot, IAlleleBeeSpecies.class));
	}

	public static String getUid(String name){
		return Gregicality.MODID + ".species" + WordUtils.capitalize(name.toLowerCase());
	}

	@Nullable
	public static Fluid getFluidMaterial(@Nonnull String uid){
		return Fluids.fluidMap.get(uid);
	}

	private void init() {
		template = branch.getTemplate();
		AlleleHelper.getInstance().set(template, EnumBeeChromosome.SPECIES, species);
		setAlleles(template);

		genome = BeeManager.beeRoot.templateAsGenome(template);

		BeeManager.beeRoot.registerTemplate(template);
	}

	protected final IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IBeeDefinition parent2, int chance) {
		return BeeManager.beeMutationFactory.createMutation(parent1.getGenome().getPrimary(), parent2.getGenome().getPrimary(), getTemplate(), chance);
	}

	@Override
	public final IAllele[] getTemplate() {
		return Arrays.copyOf(template, template.length);
	}

	@Override
	public final IBeeGenome getGenome() {
		return genome;
	}

	@Override
	public final IBee getIndividual() {
		return new Bee(genome);
	}

	@Override
	public final ItemStack getMemberStack(EnumBeeType beeType) {
		IBee bee = getIndividual();
		return BeeManager.beeRoot.getMemberStack(bee, beeType);
	}
}