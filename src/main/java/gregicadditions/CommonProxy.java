package gregicadditions;

import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaItems;
import gregicadditions.worldgen.WorldGenRegister;

public class CommonProxy {


    public void preLoad() {
        GAMetaItems.init();
        GAMetaFluids.init();
    }

    public void onLoad() {
        WorldGenRegister.init();
    }
}
