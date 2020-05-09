package gregicadditions;

import gregicadditions.item.GAMetaItems;
import gregicadditions.worldgen.WorldGenRegister;

public class CommonProxy {


    public void preLoad() {
        GAMetaItems.init();
    }

    public void onLoad() {
        WorldGenRegister.init();
    }
}
