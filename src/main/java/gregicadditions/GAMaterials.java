package gregicadditions;

import com.google.common.collect.ImmutableList;
import gregicadditions.item.BasicMaterial;
import gregtech.api.unification.Element;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.GTLog;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

import static com.google.common.collect.ImmutableList.of;
import static gregtech.api.unification.Element.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.*;
import static gregtech.api.unification.material.type.FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK;
import static gregtech.api.unification.material.type.FluidMaterial.MatFlags.GENERATE_PLASMA;
import static gregtech.api.unification.material.type.GemMaterial.MatFlags.HIGH_SIFTER_OUTPUT;
import static gregtech.api.unification.material.type.IngotMaterial.MatFlags.GENERATE_DENSE;
import static gregtech.api.unification.material.type.Material.MatFlags.*;
import static gregtech.api.unification.material.type.SolidMaterial.MatFlags.*;
import static gregtech.api.util.GTUtility.createFlag;

@IMaterialHandler.RegisterMaterialHandler
public class GAMaterials implements IMaterialHandler {

    public static final long GENERATE_METAL_CASING = createFlag(46);

    public static long STD_METAL = GENERATE_PLATE;
    static long EXT2_METAL = GENERATE_PLATE | GENERATE_DENSE | SolidMaterial.MatFlags.GENERATE_ROD | IngotMaterial.MatFlags.GENERATE_BOLT_SCREW | SolidMaterial.MatFlags.GENERATE_GEAR | IngotMaterial.MatFlags.GENERATE_FOIL | IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
    public static final FluidMaterial FishOil = new FluidMaterial(999, "fish_oil", 14467421, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final FluidMaterial RawGrowthMedium = new FluidMaterial(998, "raw_growth_medium", 10777425, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final FluidMaterial SterileGrowthMedium = new FluidMaterial(997, "sterilized_growth_medium", 11306862, MaterialIconSet.FLUID, ImmutableList.of(), FluidMaterial.MatFlags.GENERATE_FLUID_BLOCK | Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final DustMaterial Meat = new DustMaterial(996, "meat", 12667980, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final FluidMaterial NeutralMatter = new FluidMaterial(995, "neutral_matter", 3956968, MaterialIconSet.FLUID, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final FluidMaterial PositiveMatter = new FluidMaterial(994, "positive_matter", 11279131, MaterialIconSet.FLUID, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final IngotMaterial Neutronium = new IngotMaterial(993, "neutronium", 12829635, MaterialIconSet.METALLIC, 6, ImmutableList.of(), EXT2_METAL | IngotMaterial.MatFlags.GENERATE_RING | IngotMaterial.MatFlags.GENERATE_ROTOR | IngotMaterial.MatFlags.GENERATE_SMALL_GEAR | SolidMaterial.MatFlags.GENERATE_LONG_ROD | GENERATE_FRAME, Element.valueOf("Nt"), 24.0F, 12F, 655360);
    public static final DustMaterial Pyrotheum = new DustMaterial(991, "pyrotheum", 0xFF9A3C, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION | DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES | DustMaterial.MatFlags.SMELT_INTO_FLUID);
    public static final DustMaterial EglinSteelBase = new DustMaterial(990, "eglin_steel_base", 0x8B4513, MaterialIconSet.SAND, 6, ImmutableList.of(new MaterialStack(Iron, 4), new MaterialStack(Kanthal, 1), new MaterialStack(Invar, 5)), 0);
    public static final IngotMaterial EglinSteel = new IngotMaterial(989, "eglin_steel", 0x8B4513, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(GAMaterials.EglinSteelBase, 10), new MaterialStack(Sulfur, 1), new MaterialStack(Silicon, 1), new MaterialStack(Carbon, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 1048);
    public static final IngotMaterial Grisium = new IngotMaterial(987, "grisium", 0x355D6A, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Titanium, 9), new MaterialStack(Carbon, 9), new MaterialStack(Potassium, 9), new MaterialStack(Lithium, 9), new MaterialStack(Sulfur, 9), new MaterialStack(Hydrogen, 5)), EXT2_METAL | GENERATE_METAL_CASING, null, 3850);
    public static final IngotMaterial Inconel625 = new IngotMaterial(986, "inconel_a", 0x80C880, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Nickel, 3), new MaterialStack(Chrome, 7), new MaterialStack(Molybdenum, 10), new MaterialStack(Invar, 10), new MaterialStack(Nichrome, 13)), EXT2_METAL | GENERATE_METAL_CASING, null, 2425);
    public static final IngotMaterial MaragingSteel250 = new IngotMaterial(985, "maraging_steel_a", 0x92918D, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Steel, 16), new MaterialStack(Molybdenum, 1), new MaterialStack(Titanium, 1), new MaterialStack(Nickel, 4), new MaterialStack(Cobalt, 2)), EXT2_METAL | GENERATE_METAL_CASING, null, 2413);
    public static final IngotMaterial Potin = new IngotMaterial(984, "potin", 0xC99781, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Lead, 2), new MaterialStack(Bronze, 2), new MaterialStack(Tin, 1)), EXT2_METAL | GENERATE_METAL_CASING, null);
    public static final IngotMaterial Staballoy = new IngotMaterial(983, "staballoy", 0x444B42, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Uranium, 9), new MaterialStack(Titanium, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 3450);
    public static final IngotMaterial HastelloyN = new IngotMaterial(982, "hastelloy_n", 0xDDDDDD, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Yttrium, 2), new MaterialStack(Molybdenum, 4), new MaterialStack(Chrome, 2), new MaterialStack(Titanium, 2), new MaterialStack(Nickel, 15)), EXT2_METAL | GENERATE_METAL_CASING | GENERATE_DENSE, null, 4350);
    public static final IngotMaterial Tumbaga = new IngotMaterial(981, "tumbaga", 0xFFB20F, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Gold, 7), new MaterialStack(Bronze, 3)), EXT2_METAL | GENERATE_METAL_CASING, null, 1200);
    public static final IngotMaterial Stellite = new IngotMaterial(980, "stellite", 0x9991A5, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Cobalt, 9), new MaterialStack(Chrome, 9), new MaterialStack(Manganese, 5), new MaterialStack(Titanium, 2)), EXT2_METAL | GENERATE_METAL_CASING, null, 4310);
    public static final IngotMaterial Talonite = new IngotMaterial(979, "talonite", 0x9991A5, MaterialIconSet.METALLIC, 6, ImmutableList.of(new MaterialStack(Cobalt, 4), new MaterialStack(Chrome, 3), new MaterialStack(Phosphorus, 2), new MaterialStack(Molybdenum, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 3454);
    public static final FluidMaterial IronChloride = new FluidMaterial(978, "iron_chloride", 0x060b0b, MaterialIconSet.FLUID, ImmutableList.of(new MaterialStack(Iron, 1), new MaterialStack(Chlorine, 3)), Material.MatFlags.DECOMPOSITION_BY_ELECTROLYZING);
    public static final IngotMaterial MVSuperconductorBase = new IngotMaterial(976, "mv_superconductor_base", 0x535353, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Cadmium, 5), new MaterialStack(Magnesium, 1), new MaterialStack(Oxygen, 6)), STD_METAL, null, 2500);
    public static final IngotMaterial HVSuperconductorBase = new IngotMaterial(975, "hv_superconductor_base", 0x4a2400, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Titanium, 1), new MaterialStack(Barium, 9), new MaterialStack(Copper, 10), new MaterialStack(Oxygen, 20)), STD_METAL, null, 3300);
    public static final IngotMaterial EVSuperconductorBase = new IngotMaterial(974, "ev_superconductor_base", 0x005800, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Uranium, 1), new MaterialStack(Platinum, 3)), STD_METAL, null, 4400);
    public static final IngotMaterial IVSuperconductorBase = new IngotMaterial(973, "iv_superconductor_base", 0x300030, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Vanadium, 1), new MaterialStack(Indium, 3)), STD_METAL, null, 5200);
    public static final IngotMaterial LuVSuperconductorBase = new IngotMaterial(972, "luv_superconductor_base", 0x7a3c00, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Indium, 4), new MaterialStack(Bronze, 8), new MaterialStack(Barium, 2), new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 14)), STD_METAL, null, 6000);
    public static final IngotMaterial ZPMSuperconductorBase = new IngotMaterial(971, "zpm_superconductor_base", 0x111111, MaterialIconSet.SHINY, 1, ImmutableList.of(new MaterialStack(Naquadah, 4), new MaterialStack(Indium, 2), new MaterialStack(Palladium, 6), new MaterialStack(Osmium, 1)), STD_METAL, null, 8100);
    public static final BasicMaterial MVSuperconductor = new BasicMaterial(970, "mv_superconductor", 0x535353, MaterialIconSet.SHINY);
    public static final BasicMaterial HVSuperconductor = new BasicMaterial(969, "hv_superconductor", 0x4a2400, MaterialIconSet.SHINY);
    public static final BasicMaterial EVSuperconductor = new BasicMaterial(968, "ev_superconductor", 0x005800, MaterialIconSet.SHINY);
    public static final BasicMaterial IVSuperconductor = new BasicMaterial(967, "iv_superconductor", 0x300030, MaterialIconSet.SHINY);
    public static final BasicMaterial LuVSuperconductor = new BasicMaterial(966, "luv_superconductor", 0x7a3c00, MaterialIconSet.SHINY);
    public static final BasicMaterial ZPMSuperconductor = new BasicMaterial(964, "zpm_superconductor", 0x111111, MaterialIconSet.SHINY);
    public static final IngotMaterial Enderium = new IngotMaterial(963, "enderium", 0x23524a, MaterialIconSet.METALLIC, 3, ImmutableList.of(new MaterialStack(Lead, 3), new MaterialStack(Platinum, 1), new MaterialStack(EnderPearl, 1)), EXT2_METAL | Material.MatFlags.DISABLE_DECOMPOSITION, null, 8.0F, 3.0F, 1280, 4500);
    public static final DustMaterial MicaPulp = new DustMaterial(962, "mica_based", 0x917445, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final DustMaterial AluminoSilicateWool = new DustMaterial(961, "alumino_silicate_wool", 0xbbbbbb, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final DustMaterial QuartzSand = new DustMaterial(960, "sand", 0xd2cfbc, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION);
    public static final DustMaterial Massicot = new DustMaterial(959, "massicot", 8943149, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Lead, 1), new MaterialStack(Oxygen, 1)), 0);
    public static final DustMaterial AntimonyTrioxide = new DustMaterial(958, "antimony_trioxide", 8092544, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Antimony, 2), new MaterialStack(Oxygen, 3)), 0);
    public static final DustMaterial Zincite = new DustMaterial(957, "zincite", 8947843, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Zinc, 1), new MaterialStack(Oxygen, 1)), 0);
    public static final DustMaterial CobaltOxide = new DustMaterial(956, "cobalt_oxide", 3556352, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Cobalt, 1), new MaterialStack(Oxygen, 1)), 0);
    public static final DustMaterial ArsenicTrioxide = new DustMaterial(955, "arsenic_trioxide", 15856113, MaterialIconSet.ROUGH, 1, ImmutableList.of(new MaterialStack(Arsenic, 2), new MaterialStack(Oxygen, 3)), 0);
    public static final DustMaterial CupricOxide = new DustMaterial(954, "cupric_oxide", 526344, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Copper, 1), new MaterialStack(Oxygen, 1)), 0);
    public static final DustMaterial Ferrosilite = new DustMaterial(953, "ferrosilite", 5256470, MaterialIconSet.SAND, 1, ImmutableList.of(new MaterialStack(Iron, 1), new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 3)), 0);
    public static final DustMaterial Cryotheum = new DustMaterial(952, "cryotheum", 0x01F3F6, MaterialIconSet.SAND, 1, ImmutableList.of(), Material.MatFlags.DISABLE_DECOMPOSITION | DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES | DustMaterial.MatFlags.SMELT_INTO_FLUID);
    public static final DustMaterial Blizz = new DustMaterial(951, "blizz", 0x01F3F6, MaterialIconSet.DULL, 1, ImmutableList.of(), NO_SMELTING | SMELT_INTO_FLUID | MORTAR_GRINDABLE | BURNING);
    public static final DustMaterial Snow = new DustMaterial(950, "snow", 0xFFFFFF, MaterialIconSet.OPAL, 1, ImmutableList.of(), NO_SMELTING);
    public static final FluidMaterial HighPressureSteam = new FluidMaterial(949, "high_pressure_steam", 0xFFFFFF, MaterialIconSet.GAS, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION).setFluidTemperature(1000);
    public static final FluidMaterial HighOctaneGasoline = new FluidMaterial(948, "high_octane", 0xC7860B, MaterialIconSet.FLUID, ImmutableList.of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Octane = new FluidMaterial(947, "octane", 0xCBCBCB, MaterialIconSet.FLUID, ImmutableList.of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial EthylTertButylEther = new FluidMaterial(946, "ethyl_tert_butyl_ether", 0xCBCBCB, MaterialIconSet.FLUID, ImmutableList.of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Gasoline = new FluidMaterial(945, "gasoline", 0xC7860B, MaterialIconSet.FLUID, ImmutableList.of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial RawGasoline = new FluidMaterial(944, "raw_gasoline", 0xC5560C, MaterialIconSet.FLUID, ImmutableList.of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final IngotMaterial Nitinol60 = new IngotMaterial(943, "nitinol_a", 0xCCB0EC, MaterialIconSet.METALLIC, 4, ImmutableList.of(new MaterialStack(Nickel, 2), new MaterialStack(Titanium, 3)), EXT2_METAL | GENERATE_METAL_CASING, null, 0);
    public static final IngotMaterial BabbittAlloy = new IngotMaterial(942, "babbitt_alloy", 0xA19CA4, MaterialIconSet.METALLIC, 4, ImmutableList.of(new MaterialStack(Tin, 5), new MaterialStack(Lead, 36), new MaterialStack(Antimony, 8), new MaterialStack(Arsenic, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 5925);
    public static final IngotMaterial HG1223 = new IngotMaterial(941, "hg_alloy", 0x245397, MaterialIconSet.METALLIC, 4, ImmutableList.of(new MaterialStack(Iron, 16), new MaterialStack(Aluminium, 3), new MaterialStack(Chrome, 5), new MaterialStack(Yttrium, 1)), EXT2_METAL | GENERATE_METAL_CASING | GENERATE_DENSE, null, 5925);
    public static final IngotMaterial IncoloyMA956 = new IngotMaterial(940, "incoloy_ma", 0xAABEBB, MaterialIconSet.METALLIC, 4, ImmutableList.of(new MaterialStack(Mercury, 1), new MaterialStack(Barium, 2), new MaterialStack(Calcium, 2), new MaterialStack(Copper, 3), new MaterialStack(Oxygen, 8)), EXT2_METAL | GENERATE_METAL_CASING, null, 5925);
    public static final FluidMaterial RocketFuelH8N4C2O4 = new FluidMaterial(939, "rocket_fuel_a", 0x5ECB22, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial NitrogenTetroxide = new FluidMaterial(938, "nitrogen_tetroxide", 0xBE6800, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial CoalTar = new FluidMaterial(937, "coal_tar", 0x5E3122, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial CoalTarOil = new FluidMaterial(936, "coal_tar_oil", 0xB5B553, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial SulfuricCoalTarOil = new FluidMaterial(935, "sulfuric_coal_tar_oil", 0xFFFFAD, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Anthracene = new FluidMaterial(934, "anthracene", 0xA2ACA2, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Kerosene = new FluidMaterial(933, "kerosene", 0xD570D5, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial EthylBenzene = new FluidMaterial(932, "ethylbenzene", 0xD5D5D5, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial MonoMethylHydrazine = new FluidMaterial(931, "monomethylhydrazine", 0xFFFFFF, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Hydrazine = new FluidMaterial(930, "hydrazine", 0xFFFFFF, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial HydrogenPeroxide = new FluidMaterial(929, "hydrogen_peroxide", 0xD1FFFF, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial EthylAnthraQuinone = new FluidMaterial(928, "ethylanthraquinone", 0xFFFF00, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial EthylAnthraHydroQuinone = new FluidMaterial(927, "ethylanthrahydroquinone", 0xFFFF47, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final DustMaterial PhthalicAnhydride = new DustMaterial(926, "phthalicanhydride", 0xD1D1D1, MaterialIconSet.SAND, 1, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial PhthalicAcid = new FluidMaterial(925, "phthalicacid", 0xD1D1D1, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial Naphtalene = new FluidMaterial(924, "naphtalene", 0xFFFFFF, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial DenseHydrazineFuelMixture = new FluidMaterial(923, "dense_hydrazine_fuel_mixture", 0x5E2B4A, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial RocketFuelCN3H7O3 = new FluidMaterial(922, "rocket_fuel_b", 0xBE46C5, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial RP1RocketFuel = new FluidMaterial(921, "rocket_fuel_c", 0xFF503C, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial RP1 = new FluidMaterial(920, "rp", 0xFF6E5D, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial LiquidOxygen = new FluidMaterial(919, "liquid_oxygen", 0x81FFFD, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION).setFluidTemperature(54);
    public static final FluidMaterial FermentationBase = new FluidMaterial(918, "fermentation_base", 0x3D5917, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static final FluidMaterial LiquidHydrogen = new FluidMaterial(917, "liquid_hydrogen", 0x3AFFC6, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION).setFluidTemperature(14);
    public static final FluidMaterial Xenon = new FluidMaterial(916, "xenon", 0x270095, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION, Xe);
    public static final FluidMaterial Neon = new FluidMaterial(915, "neon", 0xFF422A, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION, Ne);
    public static final FluidMaterial Krypton = new FluidMaterial(914, "krypton", 0x31C42F, MaterialIconSet.FLUID, of(), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION, Kr);
    public static final IngotMaterial Zirconium = new IngotMaterial(912, "zirconium", 0xE0E1E1, MaterialIconSet.METALLIC, 6, of(), EXT2_METAL, Zr);
    public static final GemMaterial CubicZirconia = new GemMaterial(911, "cubic_zirconia", 0xFFDFE2, MaterialIconSet.DIAMOND, 6, of(new MaterialStack(Zirconium, 1), new MaterialStack(Oxygen, 2)), NO_RECYCLING | NO_SMELTING);
    public static final GemMaterial Prasiolite = new GemMaterial(910, "prasiolite", 0x9EB749, MaterialIconSet.QUARTZ, 2, of(new MaterialStack(Silicon, 5), new MaterialStack(Oxygen, 10), new MaterialStack(Iron, 1)), GENERATE_ORE);
    public static final DustMaterial Dibismusthydroborat = new DustMaterial(909, "dibismuthhydroborat", 0x00B749, MaterialIconSet.SAND, 2, of(new MaterialStack(Bismuth, 2), new MaterialStack(Hydrogen, 1), new MaterialStack(Boron, 1)), 0);
    public static final DustMaterial BismuthTellurite = new DustMaterial(908, "bismuth_tellurite", 0x006B38, MaterialIconSet.SAND, 2, of(new MaterialStack(Bismuth, 2), new MaterialStack(Tellurium, 3)), 0);
    public static final DustMaterial CircuitCompoundMK3 = new DustMaterial(907, "circuit_compound_mkc", 0x003316, MaterialIconSet.SAND, 2, of(new MaterialStack(IndiumGalliumPhosphide, 1), new MaterialStack(Dibismusthydroborat, 3), new MaterialStack(BismuthTellurite, 2)), 0);
    public static final DustMaterial YttriumOxide = new DustMaterial(906, "yttrium_oxide", 0xC6EBB3, MaterialIconSet.SAND, 2, of(new MaterialStack(Yttrium, 2), new MaterialStack(Oxygen, 3)), 0);
    public static final GemMaterial MagnetoResonatic = new GemMaterial(913, "magneto_resonatic", 0xFF97FF, MaterialIconSet.MAGNETIC, 2, of(new MaterialStack(Prasiolite, 3), new MaterialStack(BismuthTellurite, 6), new MaterialStack(CubicZirconia, 1), new MaterialStack(SteelMagnetic, 1)), NO_RECYCLING | DISABLE_DECOMPOSITION | FLAMMABLE | HIGH_SIFTER_OUTPUT | NO_SMELTING);
    public static final IngotMaterial ZirconiumCarbide = new IngotMaterial(905, "zirconium_carbide", 0xFFDACD, MaterialIconSet.SHINY, 2, of(new MaterialStack(Zirconium, 1), new MaterialStack(Carbon, 1)), EXT2_METAL | GENERATE_METAL_CASING, null, 0);
    public static final DustMaterial Zirkelite = new DustMaterial(904, "zirkelite", 0x6B5E6A, MaterialIconSet.DULL, 4, of(new MaterialStack(Calcium, 1), new MaterialStack(Thorium, 1), new MaterialStack(Cerium, 1), new MaterialStack(Zirconium, 1), new MaterialStack(Rutile, 2), new MaterialStack(Niobium, 2), new MaterialStack(Oxygen, 7)), GENERATE_ORE);
    public static final FluidMaterial PlatinumConcentrate = new FluidMaterial(903, "platinum_concentrate", Platinum.materialRGB, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial PlatinumSaltCrude = new DustMaterial(902, "platinum_salt", Platinum.materialRGB, MaterialIconSet.DULL, 2, of(), 0);
    public static final DustMaterial PlatinumSaltRefined = new DustMaterial(901, "refined_platinum_salt", Platinum.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final DustMaterial PlatinumMetallicPowder = new DustMaterial(900, "platinum_metallic_powder", Platinum.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final FluidMaterial AquaRegia = new FluidMaterial(899, "aqua_regia", 0xFFB132, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial PlatinumResidue = new DustMaterial(898, "platinum_residue", 0x64632E, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final FluidMaterial AmmoniumChloride = new FluidMaterial(897, "ammonium_chloride", 0xFFFFFF, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial PlatinumRawPowder = new DustMaterial(896, "reprecipitated_platinum", Platinum.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final FluidMaterial PalladiumAmmonia = new FluidMaterial(895, "palladium_enriched_ammonia", Platinum.materialRGB, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial PalladiumMetallicPowder = new DustMaterial(894, "palladium_metallic_powder", Palladium.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final DustMaterial PalladiumRawPowder = new DustMaterial(893, "reprecipitated_palladium", Palladium.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final DustMaterial PalladiumSalt = new DustMaterial(892, "palladium_salt", Palladium.materialRGB, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final FluidMaterial Sodiumformate = new FluidMaterial(891, "sodium_formate", 0xFFAAAA, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial Sodiumsulfate = new FluidMaterial(890, "sodium_sulfate", 0xFFFFFF, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial FormicAcid = new FluidMaterial(889, "formic_acid", 0xFFAA77, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial PotassiumDisulfate = new FluidMaterial(888, "potassium_disulfate", 0xFBBB66, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial LeachResidue = new DustMaterial(887, "leach_residue", 0x644629, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final FluidMaterial RhodiumSulfate = new FluidMaterial(886, "rhodium_sulfate", 0xEEAA55, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial RhodiumSulfateSolution = new FluidMaterial(885, "rhodium_sulfate_solution", 0xFFBB66, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial CalciumChloride = new DustMaterial(884, "calcium_chloride", 0xFFFFFF, MaterialIconSet.DULL, 2, of(), 0);
    public static final IngotMaterial Ruthenium = new IngotMaterial(883, "ruthenium", 0x646464, MaterialIconSet.METALLIC, 2, of(), EXT2_METAL, null, 2607);
    public static final DustMaterial SodiumRuthenate = new DustMaterial(882, "sodium_ruthenate", 0x3A40CB, MaterialIconSet.SHINY, 2, of(), 0);
    public static final DustMaterial RutheniumTetroxide = new DustMaterial(881, "ruthenium_tetroxide", 0xC7C7C7, MaterialIconSet.DULL, 2, of(), SMELT_INTO_FLUID | GENERATE_FLUID_BLOCK);
    public static final FluidMaterial HotRutheniumTetroxideSolution = new FluidMaterial(880, "hot_ruthenium_tetroxide_solution", 0xC7C7C7, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial RutheniumTetroxideSolution = new FluidMaterial(879, "ruthenium_tetroxide_solution", 0xC7C7C7, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial IrOsLeachResidue = new DustMaterial(878, "rarest_metal_residue", 0x644629, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final DustMaterial IrLeachResidue = new DustMaterial(877, "iridium_metal_residue", 0x846649, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final DustMaterial PGSDResidue = new DustMaterial(876, "sludge_dust_residue", 0x846649, MaterialIconSet.DULL, 2, of(), 0);
    public static final FluidMaterial AcidicOsmiumSolution = new FluidMaterial(875, "acidic_osmium_solution", 0x846649, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial IridiumDioxide = new FluidMaterial(874, "iridium_dioxide", 0x846649, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial OsmiumSolution = new FluidMaterial(873, "osmium_solution", 0x846649, MaterialIconSet.FLUID, of(), 0);
    public static final FluidMaterial AcidicIridiumSolution = new FluidMaterial(872, "acidic_iridium_solution", 0x846649, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial IridiumChloride = new DustMaterial(871, "iridium_chloride", 0x846649, MaterialIconSet.LAPIS, 2, of(), 0);
    public static final DustMaterial PGSDResidue2 = new DustMaterial(870, "metallic_sludge_dust_residue", 0x846649, MaterialIconSet.DULL, 2, of(), 0);
    public static final IngotMaterial Rhodium = new IngotMaterial(869, "rhodium", 0xF4F4F4, MaterialIconSet.METALLIC, 2, of(), EXT2_METAL, null, 2237);
    public static final DustMaterial CrudeRhodiumMetall = new DustMaterial(868, "crude_rhodium_metal", 0x666666, MaterialIconSet.DULL, 2, of(), 0);
    public static final GemMaterial RhodiumSalt = new GemMaterial(867, "rhodium_salt", 0x848484, MaterialIconSet.GEM_VERTICAL, 2, of(), 0);
    public static final FluidMaterial RhodiumSaltSolution = new FluidMaterial(866, "rhodium_salt_solution", 0x667788, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial SodiumNitrate = new DustMaterial(865, "sodium_nitrate", 0x846684, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final DustMaterial RhodiumNitrate = new DustMaterial(864, "rhodium_nitrate", 0x776649, MaterialIconSet.QUARTZ, 2, of(), 0);
    public static final DustMaterial ZincSulfate = new DustMaterial(863, "zinc_sulfate", 0x846649, MaterialIconSet.QUARTZ, 2, of(), 0);
    public static final DustMaterial RhodiumFilterCake = new DustMaterial(862, "rhodium_filter_cake", 0x776649, MaterialIconSet.QUARTZ, 2, of(), 0);
    public static final FluidMaterial RhodiumFilterCakeSolution = new FluidMaterial(861, "rhodium_filter_cake_solution", 0x667788, MaterialIconSet.FLUID, of(), 0);
    public static final DustMaterial ReRhodium = new DustMaterial(860, "reprecipitated_rhodium", 0x776649, MaterialIconSet.QUARTZ, 2, of(), 0);
    public static final IngotMaterial RhodiumPlatedPalladium = new IngotMaterial(859, "rhodium_plated_palladium", Palladium.materialRGB, MaterialIconSet.METALLIC, 2, of(), EXT2_METAL);
    public static final DustMaterial Tiberium = new DustMaterial(858, "tiberium", 0x22EE22, MaterialIconSet.DIAMOND, 2, of(), 0);
    public static final IngotMaterial Ruridit = new IngotMaterial(857, "ruridit", 0xA4A4A4, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final GemMaterial Fluorspar = new GemMaterial(856, "fluorspar", 0xB945FB, MaterialIconSet.GEM_VERTICAL, 2, of(), 0);
    public static final DustMaterial HDCS = new DustMaterial(855, "high_durability_compound_steel", 0x334433, MaterialIconSet.SHINY, 2, of(), 0);
    public static final DustMaterial Atheneite = new DustMaterial(854, "atheneite", 0xAFAFAF, MaterialIconSet.SHINY, 2, of(), 0);
    public static final DustMaterial Temagamite = new DustMaterial(853, "temagamite", 0xF5F5F5, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final GemMaterial Terlinguaite = new GemMaterial(852, "terlinguaite", 0xF5F5F5, MaterialIconSet.GEM_HORIZONTAL, 2, of(), 0);
    public static final DustMaterial AdemicSteel = new DustMaterial(851, "ademic_steel", 0xCCCCCC, MaterialIconSet.METALLIC, 2, of(), 0);
    public static final DustMaterial RawAdemicSteel = new DustMaterial(850, "raw_ademic_steel", 0xEDEDED, MaterialIconSet.ROUGH, 2, of(), 0);
    public static final DustMaterial PotassiumNitrade = new DustMaterial(849, "potassium_nitrade", 0x81228D, MaterialIconSet.DULL, 0, of(), 0);
    public static final DustMaterial ChromiumTrioxide = new DustMaterial(848, "chromium_trioxide", 0xFFE4E1, MaterialIconSet.DULL, 0, of(), 0);
    public static final FluidMaterial Nitrochlorobenzene = new FluidMaterial(847, "nitrochlorobenzene", 0x8FB51A, MaterialIconSet.DULL, of(), 0);
    public static final FluidMaterial Dimethylbenzene = new FluidMaterial(846, "dimethylbenzene", 0x669C40, MaterialIconSet.DULL, of(), 0);
    public static final DustMaterial Potassiumdichromate = new DustMaterial(845, "potassiumdichromate", 0xFF087F, MaterialIconSet.DULL, 0, of(), 0);
    public static final FluidMaterial Dichlorobenzidine = new FluidMaterial(843, "dichlorobenzidine", 0xA1DEA6, MaterialIconSet.DULL, of(), 0);
    public static final FluidMaterial Diaminobenzidine = new FluidMaterial(842, "diaminobenzidine", 0x337D59, MaterialIconSet.DULL, of(), 0);
    public static final FluidMaterial Diphenylisophtalate = new FluidMaterial(841, "diphenylisophtalate", 0x246E57, MaterialIconSet.DULL, of(), 0);
    public static final DustMaterial Polybenzimidazole = new DustMaterial(840, "polybenzimidazole", 0x2D2D2D, MaterialIconSet.DULL, 0, of(), 0);


    @Override
    public void onMaterialsInit() {
        Enderium.setFluidPipeProperties(650, 1500, true);
        Neutronium.setFluidPipeProperties(2800, 1000000, true);
        Naquadah.setFluidPipeProperties(1000, 19000, true);
        NiobiumTitanium.setFluidPipeProperties(450, 2900, true);
        MVSuperconductorBase.setCableProperties(128, 1, 2);
        HVSuperconductorBase.setCableProperties(512, 1, 2);
        EVSuperconductorBase.setCableProperties(2048, 2, 2);
        IVSuperconductorBase.setCableProperties(8192, 2, 2);
        LuVSuperconductorBase.setCableProperties(32768, 4, 2);
        ZPMSuperconductorBase.setCableProperties(131072, 4, 2);

        Tellurium.addFlag(GENERATE_ORE);
        Radon.addFlag(GENERATE_PLASMA);
        Diatomite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        GarnetSand.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Mica.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Asbestos.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Kyanite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Pollucite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        BasalticMineralSand.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        GraniticMineralSand.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        FullersEarth.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Gypsum.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Zeolite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Kaolinite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Dolomite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Wollastonite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Trona.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Andradite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Vermiculite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        Alunite.addFlag(DustMaterial.MatFlags.GENERATE_ORE);
        GlauconiteSand.addFlag(DustMaterial.MatFlags.GENERATE_ORE);

//        if (!Misc.generatePlatinumAndPalladium) {
//            removeFlags(Platinum, GENERATE_ORE);
//            removeFlags(Palladium, GENERATE_ORE);
//        }


        YttriumBariumCuprate.addFlag(IngotMaterial.MatFlags.GENERATE_FINE_WIRE);
        Manganese.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Naquadah.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        NaquadahEnriched.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Duranium.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Graphene.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Polytetrafluoroethylene.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Helium.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
        Oxygen.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
        Iron.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
        Nickel.addFlag(FluidMaterial.MatFlags.GENERATE_PLASMA);
        GreenSapphire.addFlag(GENERATE_PLATE);
        GreenSapphire.addFlag(GemMaterial.MatFlags.GENERATE_LENSE);
        Iron.addFlag(GENERATE_METAL_CASING);
        Tritanium.addFlag(GENERATE_FRAME);
        RedSteel.addFlag(GENERATE_GEAR);
        RedSteel.addFlag(GENERATE_METAL_CASING);
        Titanium.addFlag(GENERATE_METAL_CASING);
        StainlessSteel.addFlag(GENERATE_METAL_CASING);
        Steel.addFlag(GENERATE_METAL_CASING);
        TungstenSteel.addFlag(GENERATE_METAL_CASING);
        Aluminium.addFlag(GENERATE_METAL_CASING);
        Invar.addFlag(GENERATE_METAL_CASING);
        Lead.addFlag(GENERATE_METAL_CASING);
        BlackSteel.addFlag(GENERATE_METAL_CASING | GENERATE_GEAR);
        HSSG.addFlag(GENERATE_METAL_CASING);
        HSSS.addFlag(GENERATE_METAL_CASING);

        Apatite.addFlag(SolidMaterial.MatFlags.GENERATE_ROD);

        Iron.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
        Bronze.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
        Steel.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);
        StainlessSteel.addFlag(SolidMaterial.MatFlags.GENERATE_LONG_ROD);

        Steel.addFlag(Material.MatFlags.DISABLE_DECOMPOSITION);

        Rubber.addFlag(IngotMaterial.MatFlags.GENERATE_BOLT_SCREW);

        Plastic.addFlag(IngotMaterial.MatFlags.GENERATE_ROTOR);

        Salt.addOreByProducts(Borax);
        RockSalt.addOreByProducts(Borax);
        Lepidolite.addOreByProducts(Boron);

        OrePrefix.block.setIgnored(Pyrotheum);
        OrePrefix.block.setIgnored(Cryotheum);
        OrePrefix.block.setIgnored(Snow);
        OrePrefix.block.setIgnored(MagnetoResonatic);
        OrePrefix.dust.setIgnored(Snow);
        OrePrefix.dustSmall.setIgnored(Snow);
        OrePrefix.dustTiny.setIgnored(Snow);

        Magnetite.setDirectSmelting(Iron);

        Duranium.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);
        Graphene.addFlag(IngotMaterial.MatFlags.GENERATE_FOIL);

        Thorium.addFlag(SolidMaterial.MatFlags.GENERATE_ROD);

        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material instanceof IngotMaterial && material.hasFlag(GENERATE_METAL_CASING)) {
                material.addFlag(GENERATE_FRAME);
                material.addFlag(GENERATE_PLATE);
            }
            if (material instanceof IngotMaterial && ((IngotMaterial) material).toolSpeed > 0) {
                material.addFlag(GENERATE_DENSE);
            }
        }
    }

    public static void removeFlags(Material material, long flags) {
        if (!material.hasFlag(flags)) {
            return;
        }
        try {
            Field materialGenerationFlags = ObfuscationReflectionHelper.findField(Material.class, "materialGenerationFlags");
            materialGenerationFlags.setLong(material, materialGenerationFlags.getLong(material) ^ flags);
        } catch (IllegalAccessException e) {
            GTLog.logger.error("Remove flags doesnt seems to works", e);
        }
    }

}