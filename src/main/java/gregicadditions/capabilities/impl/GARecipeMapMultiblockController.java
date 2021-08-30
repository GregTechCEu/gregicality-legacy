package gregicadditions.capabilities.impl;

import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.util.ResourceLocation;

public abstract class GARecipeMapMultiblockController extends RecipeMapMultiblockController {

    /**
     * When false, this multiblock will behave like any other.
     * When true, this multiblock will treat each of its input buses as distinct,
     * checking recipes for them independently. This is useful for many machines, for example the
     * Large Extruder, where the player may want to put one extruder shape per bus, rather than
     * one machine per extruder shape.
     */
    protected boolean isDistinct = false;
    protected final boolean canDistinct;

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        this(metaTileEntityId, recipeMap, false, true, false);
    }

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean hasMuffler, boolean hasMaintenance, boolean canDistinct) {
        super(metaTileEntityId, recipeMap);
        this.canDistinct = canDistinct;
    }

    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        isDistinct = !isDistinct;
    }

    public boolean isActive() {
        return isStructureFormed() && recipeMapWorkable.isActive();
    }
}
