package gregicadditions.client;

import gregicadditions.GregicAdditions;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.CrateRenderer;
import gregicadditions.machines.DrumRenderer;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.OrientedOverlayRenderer.OverlayFace;
import gregtech.api.render.SimpleCubeRenderer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = GregicAdditions.MODID, value = Side.CLIENT)
public class ClientHandler {

	public static SimpleCubeRenderer COKE_OVEN_BRICKS = new SimpleCubeRenderer("casings/solid/machine_coke_oven_bricks");
	public static SimpleCubeRenderer CHEMICALLY_INERT = new SimpleCubeRenderer("casings/solid/machine_casing_chemically_inert");
	public static SimpleCubeRenderer LARGE_ASSEMBLER = new SimpleCubeRenderer("casings/solid/machine_casing_large_assembler");
	public static SimpleCubeRenderer FUSION_TEXTURE = new SimpleCubeRenderer("casings/fusion/machine_casing_fusion_glass");
	public static SimpleCubeRenderer ACTIVE_FUSION_TEXTURE = new SimpleCubeRenderer("gregtech:casings/fusion/machine_casing_fusion_glass_yellow");
	public static OrientedOverlayRenderer COKE_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/coke_oven", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor", new OverlayFace[] { OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP });
	public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer MASS_FAB_OVERLAY = new OrientedOverlayRenderer("machines/mass_fab", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor", new OverlayFace[] { OverlayFace.FRONT });
	public static DrumRenderer BARREL = new DrumRenderer("storage/drums/barrel");
	public static DrumRenderer DRUM = new DrumRenderer("storage/drums/drum");
	public static CrateRenderer WOODEN_CRATE = new CrateRenderer("storage/crates/wooden_crate");
	public static CrateRenderer METAL_CRATE = new CrateRenderer("storage/crates/metal_crate");
	public static final TextureArea BRONZE_FLUID_SLOT = TextureArea.fullImage("textures/gui/steam/fluid_slot.png");
	public static final TextureArea COAL_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze/overlay_bronze_coal.png");

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		GAMetaBlocks.registerStateMappers();
		GAMetaBlocks.registerItemModels();
	}
}
