package gregicadditions;

import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaItems;
import gregicadditions.worldgen.WorldGenRegister;

import java.io.IOException;

public class CommonProxy {



    public void preLoad() {
        GAMetaItems.init();
        GAMetaFluids.init();
        WorldGenRegister.preInit();

    }

    public void onLoad() throws IOException {
        WorldGenRegister.init();
    }
}
