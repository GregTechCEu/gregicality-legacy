package gregicadditions.widgets;

import gregtech.api.gui.widgets.AbstractWidgetGroup;
import gregtech.api.util.Position;
import gregtech.common.covers.filter.FilterTypeRegistry;
import gregtech.common.covers.filter.FluidFilter;
import net.minecraft.network.PacketBuffer;

import java.util.function.Supplier;

// todo remove if unneeded
public class WidgetGroupFluidFilter extends AbstractWidgetGroup {

    private final Supplier<FluidFilter> itemFilterSupplier;
    private FluidFilter itemFilter;

    public WidgetGroupFluidFilter(int x, int y, Supplier<FluidFilter> itemFilterSupplier) {
        super(new Position(x, y));
        this.itemFilterSupplier = itemFilterSupplier;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        FluidFilter newItemFilter = itemFilterSupplier.get();
        if (itemFilter != newItemFilter) {
            clearAllWidgets();
            this.itemFilter = newItemFilter;
            if (itemFilter != null) {
                this.itemFilter.initUI(this::addWidget);
            }
            writeUpdateInfo(2, buffer -> {
                if (itemFilter != null) {
                    buffer.writeBoolean(true);
                    int filterId = FilterTypeRegistry.getIdForFluidFilter(itemFilter);
                    buffer.writeVarInt(filterId);
                } else {
                    buffer.writeBoolean(false);
                }
            });
        }
    }

    @Override
    public void readUpdateInfo(int id, PacketBuffer buffer) {
        super.readUpdateInfo(id, buffer);
        if (id == 2) {
            clearAllWidgets();
            if (buffer.readBoolean()) {
                int filterId = buffer.readVarInt();
                this.itemFilter = FilterTypeRegistry.createFluidFilterById(filterId);
                this.itemFilter.initUI(this::addWidget);
            }
        }
    }
}
