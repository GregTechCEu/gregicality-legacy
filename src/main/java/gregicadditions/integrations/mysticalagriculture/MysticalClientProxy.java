package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.block.CropBlockModelFactory;
import gregicadditions.integrations.mysticalagriculture.items.MysticalAgricultureItems;
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
        MysticalAgricultureItems.registerColor();
    }


    @SubscribeEvent
    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    public static void registerModels(ModelRegistryEvent event) {
        MysticalAgricultureItems.registerModels();
    }

}
