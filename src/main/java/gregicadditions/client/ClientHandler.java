package gregicadditions.client;

import gregicadditions.Gregicality;
import gregicadditions.blocks.PowderBarrelRenderer;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.CrateRenderer;
import gregicadditions.machines.DrumRenderer;
import gregicadditions.machines.energyconverter.utils.EnergyConverterType;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.OrientedOverlayRenderer.OverlayFace;
import gregtech.api.render.SimpleCubeRenderer;
import gregtech.api.render.SimpleOverlayRenderer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = Gregicality.MODID, value = Side.CLIENT)
public class ClientHandler {

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
    public static SimpleCubeRenderer CLADDED_REACTOR_CASING = new SimpleCubeRenderer("casings/solid/cladded_reactor_casing");
    public static SimpleCubeRenderer FUSION_TEXTURE = new SimpleCubeRenderer("casings/fusion/machine_casing_fusion_glass");
    public static SimpleCubeRenderer ACTIVE_FUSION_TEXTURE = new SimpleCubeRenderer("gregtech:casings/fusion/machine_casing_fusion_glass_yellow");
    public static OrientedOverlayRenderer NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor", OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP);
    public static OrientedOverlayRenderer ROCKET_OVERLAY = new OrientedOverlayRenderer("machines/rocket_generator", OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP);
    public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", OverlayFace.FRONT);
    public static OrientedOverlayRenderer MASS_FAB_OVERLAY = new OrientedOverlayRenderer("machines/mass_fab", OverlayFace.FRONT);
    public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor", OverlayFace.FRONT);
    public static DrumRenderer BARREL = new DrumRenderer("storage/drums/barrel");
    public static DrumRenderer DRUM = new DrumRenderer("storage/drums/drum");
    public static PowderBarrelRenderer POWDER_BARREL = new PowderBarrelRenderer("powder_barrel");
    public static CrateRenderer WOODEN_CRATE = new CrateRenderer("storage/crates/wooden_crate");
    public static CrateRenderer METAL_CRATE = new CrateRenderer("storage/crates/metal_crate");
    public static final TextureArea BRONZE_FLUID_SLOT = TextureArea.fullImage("textures/gui/steam/fluid_slot.png");
    public static OrientedOverlayRenderer STEAM_MIXER_OVERLAY = new OrientedOverlayRenderer("machines/steam_mixer", OverlayFace.FRONT, OverlayFace.SIDE, OverlayFace.TOP);
    public static final TextureArea COAL_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze/overlay_bronze_coal.png");
    public static final Map<EnergyConverterType, SimpleOverlayRenderer> CONVERTER_FACES = new HashMap<>();
    public static final TextureArea BRONZE_DISPLAY = TextureArea.fullImage("textures/gui/steam/bronze_display.png");
    public static final TextureArea BRONZE_IN_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_in_slot_overlay.png");
    public static final TextureArea BRONZE_OUT_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_out_slot_overlay.png");
    public static final TextureArea BRONZE_TANK_ICON = TextureArea.fullImage("textures/gui/steam/bronze_tank_icon.png");
    public static SimpleOverlayRenderer STEAM_PUMP_OVERLAY = new SimpleOverlayRenderer("overlay/machine/overlay_steam_pump");
    public static OrientedOverlayRenderer BEE_ATTRACTOR = new OrientedOverlayRenderer("machines/attractor", OverlayFace.FRONT, OverlayFace.SIDE);


    static {
        for (final EnergyConverterType t : EnergyConverterType.values()) {
            CONVERTER_FACES.put(t, new SimpleOverlayRenderer("overlay/machine/converter/" + t.toString().toLowerCase()));
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GAMetaBlocks.registerItemModels();
    }
}
