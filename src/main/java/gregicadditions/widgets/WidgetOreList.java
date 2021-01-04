package gregicadditions.widgets;

import gregicadditions.gui.textures.ProspectingTexture;
import gregicadditions.network.ProspectingPacket;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.*;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.util.RenderUtil;
import gregtech.api.util.Size;
import gregtech.common.MetaFluids;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class WidgetOreList extends ScrollableListWidget {
    private WidgetGroup selected;
    private final Map<WidgetGroup, String> widgetMap;
    public Consumer<String> onSelected = null;

    public WidgetOreList(int xPosition, int yPosition, int width, int slotSize) {
        super(xPosition, yPosition, width, slotSize * 18);
        widgetMap = new HashMap<>();
        clear();
    }

    public void addOres(ProspectingPacket packet) {
        if (packet.ores != null) {
            switch (packet.pType) {
                case 0:
                    packet.ores.forEach(orePrefix-> addOre(OreDictUnifier.get(orePrefix), Objects.requireNonNull(OreDictUnifier.getMaterial(OreDictUnifier.get(orePrefix))).material.materialRGB));
                    break;
                case 1:
                    packet.ores.forEach(orePrefix-> addOil(new FluidStack(FluidRegistry.getFluid(orePrefix),1), getFluidColor(FluidRegistry.getFluid(orePrefix))));
                    break;
                default:
                    break;
            }
        }
    }

    private void addOre(ItemStack itemStack, int color) {
        ItemStackHandler itemStackHandler = new ItemStackHandler(1);
        itemStackHandler.insertItem(0, itemStack, false);
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new SlotWidget(itemStackHandler, 0, 0, 0, false, false));
        widgetGroup.addWidget(new LabelWidget(20, 5, itemStack.getDisplayName(), color));
        widgetMap.put(widgetGroup, itemStack.getDisplayName());
        if (widgetGroup.getSize().width + this.scrollPaneWidth > this.getSize().width)
            this.setSize(new Size(widgetGroup.getSize().width + this.scrollPaneWidth, this.getSize().height));
        this.addWidget(widgetGroup);
    }

    private void addOil(FluidStack fluidStack, int color) {
        FluidTank fluidTank = new FluidTank(1);
        fluidTank.setCanFill(false);
        fluidTank.fillInternal(fluidStack, true);
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new TankWidget(fluidTank, 0, 0, 18, 18)
                .setAlwaysShowFull(true)
                .setHideTooltip(true)
                .setContainerClicking(false, false));
        widgetGroup.addWidget(new LabelWidget(20, 5, fluidStack.getLocalizedName(), color));
        widgetMap.put(widgetGroup, fluidStack.getLocalizedName());
        if (widgetGroup.getSize().width + this.scrollPaneWidth > this.getSize().width)
            this.setSize(new Size(widgetGroup.getSize().width + this.scrollPaneWidth, this.getSize().height));
        this.addWidget(widgetGroup);
    }

    public void clear() {
        this.clearAllWidgets();
        widgetMap.clear();
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new ImageWidget(0, 0, 18, 18, GuiTextures.LOCK));
        widgetGroup.addWidget(new LabelWidget(20, 9, "All Resources"));
        selected = widgetGroup;
        widgetMap.put(widgetGroup, "all");
        this.addWidget(widgetGroup);
    }

    @Override
    public boolean mouseWheelMove(int mouseX, int mouseY, int wheelDelta) {
        return super.mouseWheelMove(mouseX - this.getPosition().x + this.gui.getGuiLeft(), mouseY, wheelDelta);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        boolean result = super.mouseClicked(mouseX, mouseY, button);
        if (!result && mouseY >= this.getPosition().y && mouseY <= this.getPosition().y + this.getSize().height) {
            Widget widget = this.widgets.stream().filter(it -> it.isMouseOverElement(mouseX, mouseY)).findFirst().orElse(null);
            if (widget instanceof WidgetGroup) {
                this.selected = (WidgetGroup) widget;
                if (onSelected != null) {
                    onSelected.accept(widgetMap.getOrDefault(widget, "all"));
                }
            }
        }
        return result;
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        RenderUtil.useScissor(this.getPosition().x, this.getPosition().y, this.getSize().width, this.getSize().height, () -> {
            widgets.forEach(widget -> {
                if (widget instanceof WidgetGroup) {
                    Widget widget1 = ((WidgetGroup) widget).getContainedWidgets(true).get(0);
                    if (widget1 instanceof SlotWidget)
                        ((SlotWidget) widget1).setEnabled(widget.getPosition().y >= this.getPosition().y - 9 && widget.getPosition().y <= this.getPosition().y + this.getSize().height - 9);
                }

            });
            super.drawInBackground(mouseX, mouseY, context);
            Gui.drawRect(selected.getPosition().x, selected.getPosition().y, selected.getPosition().x + this.getSize().width-this.scrollPaneWidth, selected.getPosition().y + selected.getSize().height, new Color(0x4BFFFFFF, true).getRGB());
        });
    }

    public static int getFluidColor(Fluid fluid) {
        if (fluid == FluidRegistry.WATER) {
            return 3183823;
        } else {
            return fluid == FluidRegistry.LAVA ? 16766720 : fluid.getColor();
        }
    }

}
