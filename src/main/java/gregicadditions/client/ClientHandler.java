package gregicadditions.client;

import gregicadditions.GregicAdditions;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.CrateRenderer;
import gregicadditions.machines.DrumRenderer;
import gregicadditions.machines.ceu.utils.CeuType;
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

@EventBusSubscriber(modid = GregicAdditions.MODID, value = Side.CLIENT)
public class ClientHandler {

    public static SimpleCubeRenderer COKE_OVEN_BRICKS = new SimpleCubeRenderer("casings/solid/machine_coke_oven_bricks");
    public static SimpleCubeRenderer CHEMICALLY_INERT = new SimpleCubeRenderer("casings/solid/machine_casing_chemically_inert");
    public static SimpleCubeRenderer LARGE_ASSEMBLER = new SimpleCubeRenderer("casings/solid/machine_casing_large_assembler");
    public static SimpleCubeRenderer FUSION_TEXTURE = new SimpleCubeRenderer("casings/fusion/machine_casing_fusion_glass");
    public static SimpleCubeRenderer ACTIVE_FUSION_TEXTURE = new SimpleCubeRenderer("gregtech:casings/fusion/machine_casing_fusion_glass_yellow");
    public static OrientedOverlayRenderer COKE_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/coke_oven", OverlayFace.FRONT);
    public static OrientedOverlayRenderer NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor", OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP);
    public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", OverlayFace.FRONT);
    public static OrientedOverlayRenderer MASS_FAB_OVERLAY = new OrientedOverlayRenderer("machines/mass_fab", OverlayFace.FRONT);
    public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor", OverlayFace.FRONT);
    public static DrumRenderer BARREL = new DrumRenderer("storage/drums/barrel");
    public static DrumRenderer DRUM = new DrumRenderer("storage/drums/drum");
    public static CrateRenderer WOODEN_CRATE = new CrateRenderer("storage/crates/wooden_crate");
    public static CrateRenderer METAL_CRATE = new CrateRenderer("storage/crates/metal_crate");
    public static final TextureArea BRONZE_FLUID_SLOT = TextureArea.fullImage("textures/gui/steam/fluid_slot.png");
    public static final TextureArea COAL_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze/overlay_bronze_coal.png");
    public static final Map<CeuType, SimpleOverlayRenderer> CEU_FACES = new HashMap<>();
    public static final TextureArea BRONZE_DISPLAY = TextureArea.fullImage("textures/gui/steam/bronze_display.png");
    public static final TextureArea BRONZE_IN_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_in_slot_overlay.png");
    public static final TextureArea BRONZE_OUT_SLOT_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze_out_slot_overlay.png");
    public static final TextureArea BRONZE_TANK_ICON = TextureArea.fullImage("textures/gui/steam/bronze_tank_icon.png");
    public static SimpleOverlayRenderer STEAM_PUMP_OVERLAY = new SimpleOverlayRenderer("overlay/machine/overlay_steam_pump");
    public static OrientedOverlayRenderer BEE_ATTRACTOR = new OrientedOverlayRenderer("machines/attractor", OverlayFace.FRONT, OverlayFace.SIDE);


    static {
        for (final CeuType t : CeuType.values()) {
            CEU_FACES.put(t, new SimpleOverlayRenderer("overlay/machine/ceu/" + t.toString().toLowerCase()));
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GAMetaBlocks.registerStateMappers();
        GAMetaBlocks.registerItemModels();
    }
}
