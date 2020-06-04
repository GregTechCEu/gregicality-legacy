package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.block.BlockCrop;
import gregicadditions.integrations.mysticalagriculture.block.CropBlockModelFactory;
import gregicadditions.integrations.mysticalagriculture.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Gregicality.MODID, value = Side.CLIENT)
public class MysticalClientProxy extends MysticalCommonProxy {

    static {
        CropBlockModelFactory.init();
    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void preInit() {
        super.preInit();
    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void init() {
        super.init();
        ModItems.registerColor();
    }

    @Mod.EventHandler
    public void postInit() {
        ModItems.CROPS.values().stream().distinct().forEach(block -> {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> ((BlockCrop) (state.getBlock())).getMaterial().materialRGB, block);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> ((BlockCrop) (((ItemBlock) stack.getItem()).getBlock())).getMaterial().materialRGB, block);
        });
    }


    @SubscribeEvent
    public static void registerBlockColors(final ColorHandlerEvent.Block event) {

    }

    @SubscribeEvent
    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    public static void registerModels(ModelRegistryEvent event) {
        ModItems.registerModels();
    }

}
