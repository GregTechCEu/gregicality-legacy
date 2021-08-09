package gregicadditions.widgets;

import gregicadditions.network.ProspectingPacket;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.*;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.Position;
import gregtech.api.util.RenderUtil;
import gregtech.api.util.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class WidgetOreList extends ScrollableListWidget {
    private WidgetGroup selected;
    private final Map<WidgetGroup, String> widgetMap;
    public Consumer<String> onSelected = null;
    public Set<String> ores;

    public WidgetOreList(int xPosition, int yPosition, int width, int slotSize) {
        super(xPosition, yPosition, width, slotSize * 18);
        widgetMap = new HashMap<>();
        ores = new HashSet<>();
        clear();
    }

    public void addOres(Set<String> ores, int mode) {
        switch (mode) {
            case 0:
                ores.stream().sorted().forEach(this::addOre);
                break;
            case 1:
                ores.stream().sorted().forEach(this::addOil);
                break;
            default:
                break;
        }
    }

    private void addOre(String orePrefix) {
        if (ores.contains(orePrefix)) {
            return;
        } else {
           ores.add(orePrefix);
        }
        ItemStack itemStack = OreDictUnifier.get(orePrefix);
        MaterialStack mterialStack = OreDictUnifier.getMaterial(OreDictUnifier.get(orePrefix));
        ItemStackHandler itemStackHandler = new ItemStackHandler(1);
        itemStackHandler.insertItem(0, itemStack, false);
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new SlotWidget(itemStackHandler, 0, 0, 0, false, false));
        widgetGroup.addWidget(new LabelWidget(20, 5, itemStack.getDisplayName(), mterialStack==null? orePrefix.hashCode():mterialStack.material.materialRGB | 0XFF000000));
        widgetMap.put(widgetGroup, orePrefix);
        if (widgetGroup.getSize().width + this.scrollPaneWidth > this.getSize().width)
            this.setSize(new Size(widgetGroup.getSize().width + this.scrollPaneWidth, this.getSize().height));
        this.addWidget(widgetGroup);
        this.widgets.sort(Comparator.comparing(widgetMap::get));
    }

    private void addOil(String orePrefix) {
        if (ores.contains(orePrefix)) {
            return;
        } else {
            ores.add(orePrefix);
        }
        FluidStack fluidStack = FluidRegistry.getFluidStack(orePrefix, 1);
        if (fluidStack == null) return;
        FluidTank fluidTank = new FluidTank(1);
        fluidTank.setCanFill(false);
        fluidTank.fillInternal(fluidStack, true);
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new TankWidget(fluidTank, 0, 0, 18, 18)
                .setAlwaysShowFull(true)
                .setHideTooltip(true)
                .setContainerClicking(false, false));
        widgetGroup.addWidget(new LabelWidget(20, 5, fluidStack.getLocalizedName(), getFluidColor(fluidStack.getFluid())));
        widgetMap.put(widgetGroup, orePrefix);
        if (widgetGroup.getSize().width + this.scrollPaneWidth > this.getSize().width)
            this.setSize(new Size(widgetGroup.getSize().width + this.scrollPaneWidth, this.getSize().height));
        this.addWidget(widgetGroup);
        this.widgets.sort(Comparator.comparing(widgetMap::get));
    }

    public void clear() {
        this.clearAllWidgets();
        widgetMap.clear();
        WidgetGroup widgetGroup = new WidgetGroup();
        widgetGroup.addWidget(new ImageWidget(0, 0, 18, 18, GuiTextures.LOCK));
        widgetGroup.addWidget(new LabelWidget(20, 9, "metaitem.tool.prospect.gui.list"));
        selected = widgetGroup;
        widgetMap.put(widgetGroup, "[all]");
        this.addWidget(widgetGroup);
    }

    @Override
    public boolean mouseWheelMove(int mouseX, int mouseY, int wheelDelta) {
        if (this.isMouseOverElement(mouseX - this.getPosition().x + this.gui.getGuiLeft(), mouseY, true)) {
            int direction = -MathHelper.clamp(wheelDelta, -2, 2);
            int moveDelta = direction * (this.slotHeight / 2);
            addScrollOffset(moveDelta);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        boolean result = super.mouseClicked(mouseX, mouseY, button);
        if (!result && mouseY >= this.getPosition().y && mouseY <= this.getPosition().y + this.getSize().height) {
            Widget widget = this.widgets.stream().filter(it -> it.isMouseOverElement(mouseX, mouseY)).findFirst().orElse(null);
            if (widget instanceof WidgetGroup) {
                this.selected = (WidgetGroup) widget;
                if (onSelected != null) {
                    onSelected.accept(widgetMap.getOrDefault(widget, "[all]"));
                }
            }
        }
        return result;
    }

    @Override
    public boolean mouseDragged(int mouseX, int mouseY, int button, long timeDragged) {
        int mouseDelta = (mouseY - lastMouseY) * 5;
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        if (draggedOnScrollBar) {
            addScrollOffset(mouseDelta);
            return true;
        }
        if (isPositionInsideScissor(mouseX, mouseY)) {
            return super.mouseDragged(mouseX, mouseY, button, timeDragged);
        }
        return false;
    }

    private void addScrollOffset(int offset) {
        this.scrollOffset = MathHelper.clamp(scrollOffset + offset, 0, totalListHeight - getSize().height);
        onPositionUpdate();
    }

    private boolean isPositionInsideScissor(int mouseX, int mouseY) {
        return isMouseOverElement(mouseX, mouseY) && !isOnScrollPane(mouseX, mouseY);
    }

    private boolean isOnScrollPane(int mouseX, int mouseY) {
        Position pos = getPosition();
        Size size = getSize();
        return isMouseOver(pos.x + size.width - scrollPaneWidth, pos.y, scrollPaneWidth, size.height, mouseX, mouseY);
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        RenderUtil.useScissor(this.getPosition().x, this.getPosition().y, this.getSize().width, this.getSize().height, () -> {
            widgets.forEach(widget -> {
                if (widget instanceof WidgetGroup) {
                    Widget widget1 = ((WidgetGroup) widget).getContainedWidgets(true).get(0);
                    if (widget1 instanceof SlotWidget){
                        SlotWidget slotWidget = (SlotWidget) widget1;
                        int tick = (int) Minecraft.getSystemTime() / 50;
                        if (tick % 20 == 0) {
                            List<ItemStack> list = OreDictUnifier.getAllWithOreDictionaryName(widgetMap.get(widget));
                            if (list.size() > 0 ) {
                                slotWidget.getHandle().decrStackSize(64);
                                slotWidget.getHandle().putStack(list.get(Math.floorMod(tick / 20, list.size())));
                            }
                        }
                        slotWidget.setEnabled(widget.getPosition().y >= this.getPosition().y - 9 && widget.getPosition().y <= this.getPosition().y + this.getSize().height - 9);
                    }
                }

            });
            super.drawInBackground(mouseX, mouseY, context);
            Gui.drawRect(selected.getPosition().x, selected.getPosition().y, selected.getPosition().x + this.getSize().width - this.scrollPaneWidth, selected.getPosition().y + selected.getSize().height, new Color(0x4BFFFFFF, true).getRGB());
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
