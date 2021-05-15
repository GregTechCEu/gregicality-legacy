package gregicadditions.integrations.mysticalagriculture;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.block.CropBlockModelFactory;
import gregicadditions.integrations.mysticalagriculture.items.MysticalAgricultureItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Gregicality.MODID, value = Side.CLIENT)
public class MysticalClientProxy extends MysticalCommonProxy {

    static {
        CropBlockModelFactory.init();
    }

    @Optional.Method(modid = Gregicality.MODID)
    @Mod.EventHandler
    public void preInit() {
        super.preInit();
    }

    @Optional.Method(modid = Gregicality.MODID)
    @Mod.EventHandler
    public void init() {
        super.init();
        if (Loader.isModLoaded(GAValues.MODID_MYSTAGGRA) && !GAConfig.mysticalAgriculture.disable) {
            MysticalAgricultureItems.registerColor();
        }
    }


    @SubscribeEvent
    @Optional.Method(modid = Gregicality.MODID)
    public static void registerModels(ModelRegistryEvent event) {
        if (Loader.isModLoaded(GAValues.MODID_MYSTAGGRA) && !GAConfig.mysticalAgriculture.disable) {
            MysticalAgricultureItems.registerModels();
        }
    }

}
