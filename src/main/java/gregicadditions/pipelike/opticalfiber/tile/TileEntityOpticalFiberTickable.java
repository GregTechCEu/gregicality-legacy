package gregicadditions.pipelike.opticalfiber.tile;

import net.minecraft.util.ITickable;

public class TileEntityOpticalFiberTickable extends TileEntityOpticalFiber implements ITickable {

    public TileEntityOpticalFiberTickable() {
    }

    @Override
    public void update() {
        getCoverableImplementation().update();
    }

    @Override
    public boolean supportsTicking() {
        return true;
    }
}
