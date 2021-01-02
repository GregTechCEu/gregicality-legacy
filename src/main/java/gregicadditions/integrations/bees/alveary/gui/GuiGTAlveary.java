package gregicadditions.integrations.bees.alveary.gui;

import forestry.core.config.Constants;
import forestry.core.gui.GuiForestryTitled;
import gregicadditions.integrations.bees.alveary.TileGTAlveary;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiGTAlveary extends GuiForestryTitled<ContainerGTAlveary> {
    private final TileGTAlveary tile;

    public GuiGTAlveary(InventoryPlayer inventory, TileGTAlveary tile) {
        super(Constants.TEXTURE_PATH_GUI + "/swarmer.png", new ContainerGTAlveary(inventory, tile), tile);
        this.tile = tile;
    }

    @Override
    protected void addLedgers() {
        addErrorLedger(tile);
    }
}
