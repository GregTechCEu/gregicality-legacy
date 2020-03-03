package gregicadditions;

import com.google.common.collect.ImmutableList;
import gregicadditions.item.BasicMaterial;
import gregtech.api.unification.Element;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;

import static gregtech.api.unification.material.type.DustMaterial.MatFlags.GENERATE_PLATE;
import static gregtech.api.unification.material.type.SolidMaterial.MatFlags.*;
import static gregtech.api.util.GTUtility.createFlag;

@IMaterialHandler.RegisterMaterialHandler
public class GAMaterials implements IMaterialHandler {

	public static final long GENERATE_METAL_CASING = createFlag(46);

	public static long STD_METAL = GENERATE_PLATE;
	static long EXT2_METAL = GENERATE_PLATE | SolidMaterial.MatFlags.GENERATE_ROD | IngotMaterial.MatFlags.GENERATE_BOLT_SCREW | SolidMaterial.MatFlags.GENERATE_GEAR | IngotMaterial.MatFlags.GENERATE_FOIL | IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
	public static final FluidMaterial FishOil = new FluidMaterial(999, "fish_oil", 14467421, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final FluidMaterial RawGrowthMedium = new FluidMaterial(998, "raw_growth_medium", 10777425, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final FluidMaterial SterileGrowthMedium = new FluidMaterial(997, "sterilized_growth_medium", 11306862, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final DustMaterial Meat = new DustMaterial(996, "meat", 12667980, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final FluidMaterial NeutralMatter = new FluidMaterial(995, "neutral_matter", 3956968, MaterialIconSet.FLUID, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final FluidMaterial PositiveMatter = new FluidMaterial(994, "positive_matter", 11279131, MaterialIconSet.FLUID, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
	public static final IngotMaterial Neutronium = new IngotMaterial(993, "neutronium", 12829635, MaterialIconSet.METALLIC, 6, ImmutableList.of(), EXT2_METAL | IngotMaterial.MatFlags.GENERATE_RING | IngotMaterial.MatFlags.GENERATE_ROTOR | IngotMaterial.MatFlags.GENERATE_SMALL_GEAR | SolidMaterial.MatFlags.GENERATE_LONG_ROD | GENERATE_FRAME, Element.valueOf("Nt"), 24.0F, 12F, 655360);
	public static final GemMaterial LigniteCoke = new GemMaterial(992, "lignite_coke", 0x8b6464, MaterialIconSet.LIGNITE, 1, ImmutableList.of(new MaterialStack(Materials.Carbon, 1)), Material.MatFlags.DECOMPOSITION_BY_ELECTROLYZING | SolidMaterial.MatFlags.MORTAR_GRINDABLE | Material.MatFlags.FLAMMABLE | DustMaterial.MatFlags.NO_SMELTING | DustMaterial.MatFlags.NO_SMASHING);
	public static final DustMaterial Pyrotheum = new DustMaterial(991, "pyrotheum", 0xFF9A3C, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION | DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES | DustMaterial.MatFlags.SMELT_INTO_FLUID);
	public static final DustMaterial EglinSteelBase = new DustMaterial(990, "eglin_steel_base", 0x8B4513, MaterialIconSet.SAND, 6, ImmutableList.of(new MaterialStack(Materials.Iron, 4), new MaterialStack(Materials.Kanthal, 1), new MaterialStack(Materials.Invar, 5)), 0);
	public static final IngotMaterial EglinSteel = new IngotMaterial(989, "eglin_steel", 0x8B4513, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(GAMaterials.EglinSteelBase, 10), new MaterialStack(Materials.Sulfur, 1), new MaterialStack(Materials.Silicon, 1), new MaterialStack(Materials.Carbon, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 1048);
	public static final IngotMaterial Grisium = new IngotMaterial(987, "grisium", 0x355D6A, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Titanium, 9), new MaterialStack(Materials.Carbon, 9), new MaterialStack(Materials.Potassium, 9), new MaterialStack(Materials.Lithium, 9), new MaterialStack(Materials.Sulfur, 9), new MaterialStack(Materials.Hydrogen, 5)), EXT2_METAL | GENERATE_METAL_CASING, null, 3850);
	public static final IngotMaterial Inconel625 = new IngotMaterial(986, "inconel_a", 0x80C880, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Nickel, 3), new MaterialStack(Materials.Chrome, 7), new MaterialStack(Materials.Molybdenum, 10), new MaterialStack(Materials.Invar, 10), new MaterialStack(Materials.Nichrome, 13)), EXT2_METAL | GENERATE_METAL_CASING, null, 2425);
	public static final IngotMaterial MaragingSteel250 = new IngotMaterial(985, "maraging_steel_a", 0x92918D, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Steel, 16), new MaterialStack(Materials.Molybdenum, 1), new MaterialStack(Materials.Titanium, 1), new MaterialStack(Materials.Nickel, 4), new MaterialStack(Materials.Cobalt, 2)), EXT2_METAL | GENERATE_METAL_CASING, null, 2413);
	public static final IngotMaterial Potin = new IngotMaterial(984, "potin", 0xC99781, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Lead, 2), new MaterialStack(Materials.Bronze, 2), new MaterialStack(Materials.Tin, 1)), EXT2_METAL | GENERATE_METAL_CASING, null);
	public static final IngotMaterial Staballoy = new IngotMaterial(983, "staballoy", 0x444B42, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Uranium, 9), new MaterialStack(Materials.Titanium, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 3450);
	public static final IngotMaterial HastelloyN = new IngotMaterial(982, "hastelloy_n", 0xDDDDDD, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Yttrium, 2), new MaterialStack(Materials.Molybdenum, 4), new MaterialStack(Materials.Chrome, 2), new MaterialStack(Materials.Titanium, 2), new MaterialStack(Materials.Nickel, 15)), EXT2_METAL | GENERATE_METAL_CASING, null, 4350);
	public static final IngotMaterial Tumbaga = new IngotMaterial(981, "tumbaga", 0xFFB20F, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Gold, 7), new MaterialStack(Materials.Copper, 3)), EXT2_METAL | GENERATE_METAL_CASING, null, 1200);
	public static final IngotMaterial Stellite = new IngotMaterial(980, "stellite", 0x9991A5, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Cobalt, 9), new MaterialStack(Materials.Chrome, 9), new MaterialStack(Materials.Manganese, 5), new MaterialStack(Materials.Titanium, 2)), EXT2_METAL | GENERATE_METAL_CASING, null, 4310);
	public static final IngotMaterial Talonite = new IngotMaterial(979, "talonite", 0x9991A5, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Materials.Cobalt, 4), new MaterialStack(Materials.Chrome, 3), new MaterialStack(Materials.Phosphorus, 2), new MaterialStack(Materials.Molybdenum, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 3454);
	public static FluidMaterial IronChloride = new FluidMaterial(978, "iron_chloride", 0x060b0b, MaterialIconSet.FLUID, ImmutableList.of(new MaterialStack(Materials.Iron, 1), new MaterialStack(Materials.Chlorine, 3)), Material.MatFlags.DECOMPOSITION_BY_ELECTROLYZING);
	public static IngotMaterial MVSuperconductorBase = new IngotMaterial(976, "mv_superconductor_base", 0x535353, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Cadmium, 5), new MaterialStack(Materials.Magnesium, 1), new MaterialStack(Materials.Oxygen, 6)), STD_METAL, null, 2500);
	public static IngotMaterial HVSuperconductorBase = new IngotMaterial(975, "hv_superconductor_base", 0x4a2400, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Titanium, 1), new MaterialStack(Materials.Barium, 9), new MaterialStack(Materials.Copper, 10), new MaterialStack(Materials.Oxygen, 20)), STD_METAL, null, 3300);
	public static IngotMaterial EVSuperconductorBase = new IngotMaterial(974, "ev_superconductor_base", 0x005800, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Uranium, 1), new MaterialStack(Materials.Platinum, 3)), STD_METAL, null, 4400);
	public static IngotMaterial IVSuperconductorBase = new IngotMaterial(973, "iv_superconductor_base", 0x300030, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Vanadium, 1), new MaterialStack(Materials.Indium, 3)), STD_METAL, null, 5200);
	public static IngotMaterial LuVSuperconductorBase = new IngotMaterial(972, "luv_superconductor_base", 0x7a3c00, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Indium, 4), new MaterialStack(Materials.Bronze, 8), new MaterialStack(Materials.Barium, 2), new MaterialStack(Materials.Titanium, 1), new MaterialStack(Materials.Oxygen, 14)), STD_METAL, null, 6000);
	public static IngotMaterial ZPMSuperconductorBase = new IngotMaterial(971, "zpm_superconductor_base", 0x111111, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Materials.Naquadah, 4), new MaterialStack(Materials.Indium, 2), new MaterialStack(Materials.Palladium, 6), new MaterialStack(Materials.Osmium, 1)), STD_METAL, null, 8100);
	public static BasicMaterial MVSuperconductor = new BasicMaterial(970, "mv_superconductor", 0x535353, MaterialIconSet.SHINY);
	public static BasicMaterial HVSuperconductor = new BasicMaterial(969, "hv_superconductor", 0x4a2400, MaterialIconSet.SHINY);
	public static BasicMaterial EVSuperconductor = new BasicMaterial(968, "ev_superconductor", 0x005800, MaterialIconSet.SHINY);
	public static BasicMaterial IVSuperconductor = new BasicMaterial(967, "iv_superconductor", 0x300030, MaterialIconSet.SHINY);
	public static BasicMaterial LuVSuperconductor = new BasicMaterial(966, "luv_superconductor", 0x7a3c00, MaterialIconSet.SHINY);
	public static BasicMaterial ZPMSuperconductor = new BasicMaterial(964, "zpm_superconductor", 0x111111, MaterialIconSet.SHINY);
	public static IngotMaterial Enderium = new IngotMaterial(963, "enderium", 0x23524a, MaterialIconSet.METALLIC, 3, ImmutableList.of(new MaterialStack(Materials.Lead, 3), new MaterialStack(Materials.Platinum, 1), new MaterialStack(Materials.EnderPearl, 1)), EXT2_METAL | Material.MatFlags.DISABLE_DECOMPOSITION, null, 8.0F, 3.0F, 1280, 4500);
	public static DustMaterial MicaPulp = new DustMaterial(962, "mica_based", 0x917445, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
	public static DustMaterial AluminoSilicateWool = new DustMaterial(961, "alumino_silicate_wool", 0xbbbbbb, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);


	@Override
	public void onMaterialsInit() {
		LigniteCoke.setBurnTime(2400);
		Enderium.setFluidPipeProperties(650, 1500, true);
		Neutronium.setFluidPipeProperties(2800, 1000000, true);
		Materials.Naquadah.setFluidPipeProperties(1000, 19000, true);
		Materials.NiobiumTitanium.setFluidPipeProperties(450, 2900, true);
		MVSuperconductorBase.setCableProperties(128, 1, 2);
		HVSuperconductorBase.setCableProperties(512, 1, 2);
		EVSuperconductorBase.setCableProperties(2048, 2, 2);
		IVSuperconductorBase.setCableProperties(8192, 2, 2);
		LuVSuperconductorBase.setCableProperties(32768, 4, 2);
		ZPMSuperconductorBase.setCableProperties(131072, 4, 2);

		Materials.YttriumBariumCuprate.addFlag(IngotMaterial.MatFlags.GENERATE_FINE_WIRE);
		Materials.Manganese.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.Naquadah.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.NaquadahEnriched.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.Duranium.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.Graphene.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.Helium.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
		Materials.Oxygen.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
		Materials.Iron.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
		Materials.Nickel.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
		Materials.GreenSapphire.addFlag(GENERATE_PLATE);
		Materials.GreenSapphire.addFlag(GemMaterial.MatFlags.GENERATE_LENSE);
		Materials.Tritanium.addFlag(GENERATE_FRAME);
		Materials.RedSteel.addFlag(GENERATE_GEAR);
		Materials.RedSteel.addFlag(GENERATE_METAL_CASING);
		Materials.Titanium.addFlag(GENERATE_METAL_CASING);
		Materials.StainlessSteel.addFlag(GENERATE_METAL_CASING);
		Materials.Steel.addFlag(GENERATE_METAL_CASING);
		Materials.TungstenSteel.addFlag(GENERATE_METAL_CASING);
		Materials.Aluminium.addFlag(GENERATE_METAL_CASING);
		Materials.Invar.addFlag(GENERATE_METAL_CASING);
		Materials.Lead.addFlag(GENERATE_METAL_CASING);
		Materials.BlackSteel.addFlag(GENERATE_METAL_CASING);
		Materials.HSSG.addFlag(GENERATE_METAL_CASING);
		Materials.HSSS.addFlag(GENERATE_METAL_CASING);

		Materials.Apatite.addFlag(SolidMaterial.MatFlags.GENERATE_ROD);

		Materials.Iron.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
		Materials.Bronze.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
		Materials.Steel.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
		Materials.StainlessSteel.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);

		Materials.Steel.addFlag(Material.MatFlags.DISABLE_DECOMPOSITION);

		Materials.Rubber.addFlag(IngotMaterial.MatFlags.GENERATE_BOLT_SCREW);
		Materials.Apatite.addFlag(IngotMaterial.MatFlags.GENERATE_BOLT_SCREW);

		Materials.Salt.addOreByProducts(Materials.Borax);
		Materials.RockSalt.addOreByProducts(Materials.Borax);
		Materials.Lepidolite.addOreByProducts(Materials.Boron);

		OrePrefix.gemChipped.setIgnored(LigniteCoke);
		OrePrefix.gemFlawed.setIgnored(LigniteCoke);
		OrePrefix.gemFlawless.setIgnored(LigniteCoke);
		OrePrefix.gemExquisite.setIgnored(LigniteCoke);
		OrePrefix.block.setIgnored(Pyrotheum);

		Materials.Magnetite.setDirectSmelting(Materials.Iron);

		Materials.Mica.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
		Materials.Asbestos.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
		Materials.Kaolinite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);

		Materials.Duranium.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
		Materials.Graphene.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);

		Materials.Thorium.addFlag(SolidMaterial.MatFlags.GENERATE_ROD);

		for (Material material : Material.MATERIAL_REGISTRY) {
			if (material instanceof IngotMaterial && material.hasFlag(GENERATE_METAL_CASING)) {
				material.addFlag(GENERATE_FRAME);
				material.addFlag(GENERATE_PLATE);
			}
		}

	}
}