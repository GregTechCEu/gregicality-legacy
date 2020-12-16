package gregicadditions.integrations.bees.alveary;

import forestry.apiculture.multiblock.TileAlveary;
import forestry.core.tiles.IActivatable;

public class TileGTAlveary extends TileAlveary implements IActivatable {

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setActive(boolean active) {

    }
}
