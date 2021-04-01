package gregicadditions.recipes.map;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.DoubleSupplier;

@MethodsReturnNonnullByDefault
public class RecipeMapExtended<R extends RecipeBuilder<R>> extends RecipeMap<R> {
    private TextureArea progressBarTexture;
    private ProgressWidget.MoveType moveType;

    public RecipeMapExtended(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, R defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
        this.progressBarTexture = GuiTextures.PROGRESS_BAR_ARROW;
        this.moveType = ProgressWidget.MoveType.HORIZONTAL;
    }

    @Override
    public RecipeMapExtended<R> setProgressBar(TextureArea progressBar, ProgressWidget.MoveType moveType) {
        this.progressBarTexture = progressBar;
        this.moveType = moveType;
        super.setProgressBar(progressBar, moveType);
        return this;
    }

    @Override
    public ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids) {
        ModularUI.Builder builder = new ModularUI.Builder(GuiTextures.BACKGROUND_EXTENDED, 176, 216) {
            @Override
            public ModularUI.Builder bindPlayerInventory(InventoryPlayer inventoryPlayer) {
                this.bindPlayerInventory(inventoryPlayer, 134);
                return this;
            }

        };
        builder.widget(new ProgressWidget(progressSupplier, 77, 22, 21, 20, progressBarTexture, moveType));
        this.addInventorySlotGroup(builder, importItems, importFluids, false);
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true);
        return builder;
    }
}
