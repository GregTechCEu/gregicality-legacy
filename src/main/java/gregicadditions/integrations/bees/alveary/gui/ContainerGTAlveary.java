package gregicadditions.integrations.bees.alveary.gui;

import forestry.core.gui.ContainerTile;
import gregicadditions.integrations.bees.alveary.TileGTAlveary;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGTAlveary extends ContainerTile<TileGTAlveary> {

    public ContainerGTAlveary(InventoryPlayer playerInventory, TileGTAlveary tileForestry) {
        super(tileForestry, playerInventory, 8, 87);

        this.addSlotToContainer(new SlotItemHandler(tile.getItemHandler(), 0, 79, 52));
        this.addSlotToContainer(new SlotItemHandler(tile.getItemHandler(), 1, 100, 39));
        this.addSlotToContainer(new SlotItemHandler(tile.getItemHandler(), 2, 58, 39));
        this.addSlotToContainer(new SlotItemHandler(tile.getItemHandler(), 3, 79, 26));
    }
}
