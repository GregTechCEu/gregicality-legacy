package gregicadditions.pipelike.cable.net;

import gregicadditions.pipelike.cable.WireProperties;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class RoutePath {

    public BlockPos destination;
    public HashMap<BlockPos, WireProperties> path = new HashMap<>();
    public int maxAmperage = Integer.MAX_VALUE;
    public int minVoltage = Integer.MAX_VALUE;

    public RoutePath cloneAndCompute(BlockPos destination) {
        RoutePath newPath = new RoutePath();
        newPath.path = new HashMap<>(path);
        newPath.destination = destination;
        for (WireProperties wireProperties : path.values()) {
            newPath.maxAmperage = Math.min(newPath.maxAmperage, wireProperties.amperage);
            newPath.minVoltage = Math.min(newPath.minVoltage, wireProperties.voltage);
        }
        return newPath;
    }


}
