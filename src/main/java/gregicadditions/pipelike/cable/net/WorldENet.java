package gregicadditions.pipelike.cable.net;

import gregicadditions.pipelike.cable.WireProperties;
import gregtech.api.pipenet.WorldPipeNet;
import net.minecraft.world.World;

public class WorldENet extends WorldPipeNet<WireProperties, EnergyNet> {

    private static final String DATA_ID = "ga_optical_fiber_net";

    public static WorldENet getWorldENet(World world) {
        WorldENet eNetWorldData = (WorldENet) world.loadData(WorldENet.class, DATA_ID);
        if (eNetWorldData == null) {
            eNetWorldData = new WorldENet(DATA_ID);
            world.setData(DATA_ID, eNetWorldData);
        }
        eNetWorldData.setWorldAndInit(world);
        return eNetWorldData;
    }

    public WorldENet(String name) {
        super(name);
    }

    @Override
    protected EnergyNet createNetInstance() {
        return new EnergyNet(this);
    }

}
