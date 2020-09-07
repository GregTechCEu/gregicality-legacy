package gregicadditions;

import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaItems;
import gregicadditions.worldgen.WorldGenRegister;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class CommonProxy {


    public void preLoad() {
        GAMetaItems.init();
        GAMetaFluids.init();
        WorldGenRegister.preInit();

    }

    public void onLoad() throws IOException {
        WorldGenRegister.init();
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Gregicality.MODID)) {
            ConfigManager.sync(Gregicality.MODID, Config.Type.INSTANCE);
        }
    }
}
