package gregicadditions.item;

import gregicadditions.tools.BendingCylinder;
import gregicadditions.tools.SmallBendingCylinder;
import gregtech.api.items.toolitem.ToolMetaItem;

public class GAMetaTool extends ToolMetaItem<ToolMetaItem<?>.MetaToolValueItem> {

    @Override
    public void registerSubItems() {
        GAMetaItems.BENDING_CYLINDER = addItem(0, "tool.bending_cylinder")
                .setToolStats(new BendingCylinder())
                .addOreDict("craftingToolBendingCylinder");

        GAMetaItems.SMALL_BENDING_CYLINDER = addItem(1, "tool.bending_cylinder_small")
                .setToolStats(new SmallBendingCylinder())
                .addOreDict("craftingToolBendingCylinderSmall");
    }
}
