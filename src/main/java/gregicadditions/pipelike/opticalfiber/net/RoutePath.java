package gregicadditions.pipelike.opticalfiber.net;

import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class RoutePath {

    public BlockPos destination;
    public HashMap<BlockPos, OpticalFiberProperties> path = new HashMap<>();
    public int maxAmperage = Integer.MAX_VALUE;
    public int minVoltage = Integer.MAX_VALUE;

    public RoutePath cloneAndCompute(BlockPos destination) {
        RoutePath newPath = new RoutePath();
        newPath.path = new HashMap<>(path);
        newPath.destination = destination;
        for (OpticalFiberProperties opticalFiberProperties : path.values()) {
            newPath.maxAmperage = Math.min(newPath.maxAmperage, opticalFiberProperties.amperage);
            newPath.minVoltage = Math.min(newPath.minVoltage, opticalFiberProperties.voltage);
        }
        return newPath;
    }


}
