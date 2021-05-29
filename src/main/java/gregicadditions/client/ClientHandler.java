package gregicadditions.client;

import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregicadditions.machines.CrateRenderer;
import gregicadditions.machines.DrumRenderer;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregtech.api.gui.resources.AdoptableTextureArea;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.OrientedOverlayRenderer.OverlayFace;
import gregtech.api.render.SimpleCubeRenderer;
import gregtech.api.render.SimpleOverlayRenderer;
import gregtech.api.render.SimpleSidedCubeRenderer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = Gregicality.MODID, value = Side.CLIENT)
public class ClientHandler {

    // Multiblock Casing
    public static SimpleCubeRenderer CHEMICALLY_INERT = new SimpleCubeRenderer("casings/solid/machine_casing_chemically_inert");
    public static SimpleCubeRenderer LARGE_ASSEMBLER = new SimpleCubeRenderer("casings/solid/machine_casing_large_assembler");
    public static SimpleCubeRenderer TIERED_HULL_ULV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_ulv");
    public static SimpleCubeRenderer TIERED_HULL_LV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_lv");
    public static SimpleCubeRenderer TIERED_HULL_MV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_mv");
    public static SimpleCubeRenderer TIERED_HULL_HV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_hv");
    public static SimpleCubeRenderer TIERED_HULL_EV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_ev");
    public static SimpleCubeRenderer TIERED_HULL_IV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_iv");
    public static SimpleCubeRenderer TIERED_HULL_LUV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_luv");
    public static SimpleCubeRenderer TIERED_HULL_ZPM = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_zpm");
    public static SimpleCubeRenderer TIERED_HULL_UV = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_uv");
    public static SimpleCubeRenderer TIERED_HULL_MAX = new SimpleCubeRenderer("casings/solid/machine_casing_tiered_hull_max");
    public static SimpleCubeRenderer QUANTUM_COMPUTER = new SimpleCubeRenderer("casings/solid/quantum/computer");
    public static SimpleCubeRenderer QUANTUM_GENERATOR = new SimpleCubeRenderer("casings/solid/quantum/generator");
    public static SimpleCubeRenderer CLADDED_REACTOR_CASING = new SimpleCubeRenderer("casings/solid/cladded_reactor_casing");
    public static SimpleCubeRenderer HYPER_CASING = new SimpleCubeRenderer("casings/solid/hyper_casing");
    public static SimpleCubeRenderer HYPER_CASING_2 = new SimpleCubeRenderer("casings/solid/hyper_casing_2");
    public static SimpleCubeRenderer FUSION_TEXTURE = new SimpleCubeRenderer("casings/fusion/machine_casing_fusion_glass");
    public static SimpleCubeRenderer BIO_REACTOR = new SimpleCubeRenderer("casings/solid/bio_reactor_casing");
    public static SimpleCubeRenderer LASER_ENGRAVER = new SimpleCubeRenderer("casings/solid/laser_engraver_casing");
    public static SimpleSidedCubeRenderer[] VOLTAGE_CASINGS = new SimpleSidedCubeRenderer[15];
    public static SimpleCubeRenderer THORIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/thorium");
    public static SimpleCubeRenderer PROTACTINIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/protactinium");
    public static SimpleCubeRenderer URANIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/uranium");
    public static SimpleCubeRenderer NEPTUNIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/neptunium");
    public static SimpleCubeRenderer PLUTONIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/plutonium");
    public static SimpleCubeRenderer AMERICIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/americium");
    public static SimpleCubeRenderer CURIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/curium");
    public static SimpleCubeRenderer BERKELIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/berkelium");
    public static SimpleCubeRenderer CALIFORNIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/californium");
    public static SimpleCubeRenderer EINSTEINIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/einsteinium");
    public static SimpleCubeRenderer FERMIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/fermium");
    public static SimpleCubeRenderer MENDELEVIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/mendelevium");
    public static SimpleCubeRenderer HASTELLOY_X78_CASING = new SimpleCubeRenderer("casings/metal_casings/hastelloy_x78");
    public static SimpleCubeRenderer HASTELLOY_N_CASING = new SimpleCubeRenderer("casings/metal_casings/hastelloy_n");
    public static SimpleCubeRenderer HASTELLOY_K243_CASING = new SimpleCubeRenderer("casings/metal_casings/hastelloy_k243");
    public static SimpleCubeRenderer INCOLOY_813_CASING = new SimpleCubeRenderer("casings/metal_casings/incoloy_813");
    public static SimpleCubeRenderer INCOLOY_MA956_CASING = new SimpleCubeRenderer("casings/metal_casings/incoloy_ma956");
    public static SimpleCubeRenderer MARAGING_STEEL_250_CASING = new SimpleCubeRenderer("casings/metal_casings/maraging_steel_250");
    public static SimpleCubeRenderer NITINOL_60_CASING = new SimpleCubeRenderer("casings/metal_casings/nitinol_60");
    public static SimpleCubeRenderer INCONEL_625_CASING = new SimpleCubeRenderer("casings/metal_casings/inconel_625");
    public static SimpleCubeRenderer GRISIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/grisium");
    public static SimpleCubeRenderer EGLIN_STEEL_CASING = new SimpleCubeRenderer("casings/metal_casings/eglin_steel");
    public static SimpleCubeRenderer BABBIT_ALLOY_CASING = new SimpleCubeRenderer("casings/metal_casings/babbit_alloy");
    public static SimpleCubeRenderer HG_1223_CASING = new SimpleCubeRenderer("casings/metal_casings/hg_1223");
    public static SimpleCubeRenderer TUMBAGA_CASING = new SimpleCubeRenderer("casings/metal_casings/tumbaga");
    public static SimpleCubeRenderer TALONITE_CASING = new SimpleCubeRenderer("casings/metal_casings/talonite");
    public static SimpleCubeRenderer ZIRCONIUM_CARBIDE_CASING = new SimpleCubeRenderer("casings/metal_casings/zirconium_carbide");
    public static SimpleCubeRenderer POTIN_CASING = new SimpleCubeRenderer("casings/metal_casings/potin");
    public static SimpleCubeRenderer STABALLOY_CASING = new SimpleCubeRenderer("casings/metal_casings/staballoy");
    public static SimpleCubeRenderer STELLITE_CASING = new SimpleCubeRenderer("casings/metal_casings/stellite");
    public static SimpleCubeRenderer ENRICHED_NAQUADAH_ALLOY_CASING = new SimpleCubeRenderer("casings/metal_casings/enriched_naquadah_alloy");
    public static SimpleCubeRenderer QUANTUM_CASING = new SimpleCubeRenderer("casings/metal_casings/quantum");
    public static SimpleCubeRenderer TRITANIUM_CASING = new SimpleCubeRenderer("casings/metal_casings/tritanium");
    public static SimpleCubeRenderer BLACK_STEEL_CASING = new SimpleCubeRenderer("casings/metal_casings/black_steel");
    public static SimpleCubeRenderer RED_STEEL_CASING = new SimpleCubeRenderer("casings/metal_casings/red_steel");
    public static SimpleCubeRenderer GOLD_CASING = new SimpleCubeRenderer("casings/metal_casings/gold");
    public static SimpleCubeRenderer IRON_CASING = new SimpleCubeRenderer("casings/metal_casings/iron");
    public static SimpleCubeRenderer HSS_G_CASING = new SimpleCubeRenderer("casings/metal_casings/hss_g");
    public static SimpleCubeRenderer HSS_S_CASING = new SimpleCubeRenderer("casings/metal_casings/hss_s");
    public static SimpleCubeRenderer LEAD_CASING = new SimpleCubeRenderer("casings/metal_casings/lead");
    public static SimpleCubeRenderer NAQUADRIA_CASING = new SimpleCubeRenderer("casings/metal_casings/naquadria");





    // Machine Overlays
    public static SimpleCubeRenderer ACTIVE_FUSION_TEXTURE = new SimpleCubeRenderer("gregtech:casings/fusion/machine_casing_fusion_glass_yellow");
    public static OrientedOverlayRenderer NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor", OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP);
    public static OrientedOverlayRenderer ROCKET_OVERLAY = new OrientedOverlayRenderer("machines/rocket_generator", OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP);
    public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", OverlayFace.FRONT);
    public static OrientedOverlayRenderer MASS_FAB_OVERLAY = new OrientedOverlayRenderer("machines/mass_fab", OverlayFace.FRONT);
    public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor", OverlayFace.FRONT);
    public static DrumRenderer BARREL = new DrumRenderer("storage/drums/barrel");
    public static DrumRenderer DRUM = new DrumRenderer("storage/drums/drum");
    public static CrateRenderer WOODEN_CRATE = new CrateRenderer("storage/crates/wooden_crate");
    public static CrateRenderer METAL_CRATE = new CrateRenderer("storage/crates/metal_crate");
    public static final TextureArea BRONZE_FLUID_SLOT = TextureArea.fullImage("textures/gui/steam/fluid_slot.png");
    public static OrientedOverlayRenderer STEAM_MIXER_OVERLAY = new OrientedOverlayRenderer("machines/steam_mixer", OverlayFace.FRONT, OverlayFace.SIDE, OverlayFace.TOP);
    public static SimpleOverlayRenderer STEAM_MINER_OVERLAY = new SimpleOverlayRenderer("overlay/machine/overlay_steam_miner");
    public static SimpleOverlayRenderer CHUNK_MINER_OVERLAY = new SimpleOverlayRenderer("overlay/machine/overlay_chunk_miner");
    public static final TextureArea COAL_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze/overlay_bronze_coal.png");
    public static final Map<EnergyConverterType, SimpleOverlayRenderer> CONVERTER_FACES = new HashMap<>();
    public static final TextureArea BRONZE_DISPLAY = TextureArea.fullImage("textures/gui/steam/bronze_display.png");
    public static final TextureArea BRONZE_IN_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_in_slot_overlay.png");
    public static final TextureArea BRONZE_OUT_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_out_slot_overlay.png");
    public static final TextureArea BRONZE_TANK_ICON = TextureArea.fullImage("textures/gui/steam/bronze_tank_icon.png");
    public static final TextureArea MAINTENANCE_ICON = TextureArea.fullImage("textures/gui/maintenance/button_maintenance.png");
    public static SimpleOverlayRenderer STEAM_PUMP_OVERLAY = new SimpleOverlayRenderer("overlay/machine/overlay_steam_pump");
    public static OrientedOverlayRenderer BEE_ATTRACTOR = new OrientedOverlayRenderer("machines/attractor", OverlayFace.FRONT, OverlayFace.SIDE);
    public static OrientedOverlayRenderer FREEZER_OVERLAY = new OrientedOverlayRenderer("machines/freezer", OverlayFace.FRONT);
    public static OrientedOverlayRenderer IMPLOSION_OVERLAY = new OrientedOverlayRenderer("machines/implosion", OverlayFace.FRONT);
    public static OrientedOverlayRenderer ORGANIC_REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/organic_replicator", OverlayFace.FRONT, OverlayFace.TOP);
    public static OrientedOverlayRenderer PULVERIZER_OVERLAY = new OrientedOverlayRenderer("machines/pulverizer", OverlayFace.FRONT, OverlayFace.TOP);
    public static OrientedOverlayRenderer PRINTER_OVERLAY = new OrientedOverlayRenderer("machines/printer", OverlayFace.FRONT);
    public static OrientedOverlayRenderer QUBIT_COMPUTER_OVERLAY = new OrientedOverlayRenderer("machines/qubit_computer", OverlayFace.FRONT);
    public static OrientedOverlayRenderer NUCLEAR_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/nuclear_reactor", OverlayFace.FRONT);
    public static OrientedOverlayRenderer ADVANCED_ALLOY_OVERLAY = new OrientedOverlayRenderer("machines/advanced_alloy_smelter", OverlayFace.FRONT);
    public static SimpleOverlayRenderer HIGH_ENERGY_IN = new SimpleOverlayRenderer("overlay/machine/transformer/overlay_energy_in");
    public static SimpleOverlayRenderer HIGH_ENERGY_OUT = new SimpleOverlayRenderer("overlay/machine/transformer/overlay_energy_out");
    public static SimpleOverlayRenderer MAINTENANCE_OVERLAY = new SimpleOverlayRenderer("overlay/machine/maintenance/overlay_maintenance");
    public static SimpleOverlayRenderer AUTO_MAINTENANCE_OVERLAY = new SimpleOverlayRenderer("overlay/machine/maintenance/overlay_auto_maintenance");
    public static SimpleOverlayRenderer FULLAUTO_MAINTENANCE_OVERLAY = new SimpleOverlayRenderer("overlay/machine/maintenance/overlay_fullauto_maintenance");
    // cover
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID = new SimpleOverlayRenderer("cover/cover_interface_fluid");
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID_GLASS = new SimpleOverlayRenderer("cover/cover_interface_fluid_glass");
    public static SimpleOverlayRenderer COVER_INTERFACE_ITEM = new SimpleOverlayRenderer("cover/cover_interface_item");
    public static SimpleOverlayRenderer COVER_INTERFACE_ENERGY = new SimpleOverlayRenderer("cover/cover_interface_energy");
    public static SimpleOverlayRenderer COVER_INTERFACE_MACHINE_ON = new SimpleOverlayRenderer("cover/cover_interface_machine_on");
    public static SimpleOverlayRenderer COVER_INTERFACE_MACHINE_OFF = new SimpleOverlayRenderer("cover/cover_interface_machine_off");
    public static SimpleOverlayRenderer COVER_INTERFACE_PROXY = new SimpleOverlayRenderer("cover/cover_interface_proxy");
    public static TextureArea BUTTON_FLUID = TextureArea.fullImage("textures/blocks/cover/cover_interface_fluid_button.png");
    public static TextureArea BUTTON_ITEM = TextureArea.fullImage("textures/blocks/cover/cover_interface_item_button.png");
    public static TextureArea BUTTON_ENERGY = TextureArea.fullImage("textures/blocks/cover/cover_interface_energy_button.png");
    public static TextureArea BUTTON_MACHINE = TextureArea.fullImage("textures/blocks/cover/cover_interface_machine_button.png");
    public static TextureArea BUTTON_INTERFACE = TextureArea.fullImage("textures/blocks/cover/cover_interface_computer_button.png");
    public static TextureArea COVER_INTERFACE_MACHINE_ON_PROXY = TextureArea.fullImage("textures/blocks/cover/cover_interface_machine_on_proxy.png");
    public static TextureArea COVER_INTERFACE_MACHINE_OFF_PROXY = TextureArea.fullImage("textures/blocks/cover/cover_interface_machine_off_proxy.png");
    public static SimpleOverlayRenderer COVER_INFINITE_WATER = new SimpleOverlayRenderer("cover/cover_water_overlay");
    public static final TextureArea CONTROLLER_SLOT = AdoptableTextureArea.fullImage("textures/gui/base/slot_mbc.png", 21, 21, 4, 4);


    static {
        for (final EnergyConverterType t : EnergyConverterType.values()) {
            CONVERTER_FACES.put(t, new SimpleOverlayRenderer("overlay/machine/converter/" + t.toString().toLowerCase()));
        }
        for (int i = 0; i < VOLTAGE_CASINGS.length; i++) {
            String voltageName = GAValues.VN[i].toLowerCase();
            VOLTAGE_CASINGS[i] = new SimpleSidedCubeRenderer("casings/voltage/" + voltageName);
        }
    }


}
