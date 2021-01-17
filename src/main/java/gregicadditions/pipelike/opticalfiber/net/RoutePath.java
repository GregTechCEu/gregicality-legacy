package gregicadditions.pipelike.opticalfiber.net;

import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class RoutePath {

    public BlockPos destination;
    public HashMap<BlockPos, OpticalFiberProperties> path = new HashMap<>();
    public int maxParallel = Integer.MAX_VALUE;
    public int minQubit = Integer.MAX_VALUE;

    public RoutePath cloneAndCompute(BlockPos destination) {
        RoutePath newPath = new RoutePath();
        newPath.path = new HashMap<>(path);
        newPath.destination = destination;
        for (OpticalFiberProperties opticalFiberProperties : path.values()) {
            newPath.maxParallel = Math.min(newPath.maxParallel, opticalFiberProperties.parallel);
            newPath.minQubit = Math.min(newPath.minQubit, opticalFiberProperties.qubit);
        }
        return newPath;
    }


}
