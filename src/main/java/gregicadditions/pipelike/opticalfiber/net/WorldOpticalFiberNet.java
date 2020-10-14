package gregicadditions.pipelike.opticalfiber.net;

import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregtech.api.pipenet.WorldPipeNet;
import net.minecraft.world.World;

public class WorldOpticalFiberNet extends WorldPipeNet<OpticalFiberProperties, OpticalFiberNet> {

    private static final String DATA_ID = "ga.optical_fiber_net";

    public static WorldOpticalFiberNet getWorldENet(World world) {
        WorldOpticalFiberNet eNetWorldData = (WorldOpticalFiberNet) world.loadData(WorldOpticalFiberNet.class, DATA_ID);
        if (eNetWorldData == null) {
            eNetWorldData = new WorldOpticalFiberNet(DATA_ID);
            world.setData(DATA_ID, eNetWorldData);
        }
        eNetWorldData.setWorldAndInit(world);
        return eNetWorldData;
    }

    public WorldOpticalFiberNet(String name) {
        super(name);
    }

    @Override
    protected OpticalFiberNet createNetInstance() {
        return new OpticalFiberNet(this);
    }

}
