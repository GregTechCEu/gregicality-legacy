package gregicadditions;

import gregicadditions.worldgen.WorldGenRegister;

public class CommonProxy {


    public void preLoad() {

    }

    public void onLoad() {
        WorldGenRegister.init();
    }
}
