package gregicadditions.integrations.bees;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import gregicadditions.GAConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
	}

	@Override
	public void postInit() {

		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) itemColors.registerItemColorHandler(ColoredItemItemColor.INSTANCE, GTCombs.combItem);
		super.postInit();
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) GTCombs.combItem.registerModel(GTCombs.combItem, ForestryAPI.modelManager);
	}

	@SideOnly(Side.CLIENT)
	private static class ColoredItemItemColor implements IItemColor {
		public static final ClientProxy.ColoredItemItemColor INSTANCE = new ClientProxy.ColoredItemItemColor();

		private ColoredItemItemColor() {

		}

		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex) {
			Item item = stack.getItem();
			if (item instanceof IColoredItem && Loader.isModLoaded("forestry")) {
				return ((IColoredItem) item).getColorFromItemstack(stack, tintIndex);
			}
			return 0xffffff;
		}
	}
}