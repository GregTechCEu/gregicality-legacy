package gregicadditions;

import gregicadditions.worldgen.WorldGenRegister;

public class CommonProxy {


    public void preLoad() {
        WorldGenRegister.init();
    }

    public void onLoad() {

    }
}
