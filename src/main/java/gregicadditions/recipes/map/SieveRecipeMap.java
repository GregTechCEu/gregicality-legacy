package gregicadditions.recipes.map;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI.Builder;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.DoubleSupplier;

public class SieveRecipeMap extends RecipeMap<SimpleRecipeBuilder> {
	private static final int PROGRESS_LEFT = 51;
	private static final int PROGRESS_TOP = 24;
	private static final int ITEM_SLOT_WITH_EDGE_DIMENSION = 18;
	private static final int OUTPUT_TOP = 17;
	private static final int OUTPUT_LEFT = 79;
	private static final int MESH_TOP = 26;
	private static final int MESH_LEFT = 7;
	private static final int INPUT_TOP = 26;
	private static final int INPUT_LEFT = 25;

	public SieveRecipeMap(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs,
						  int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, SimpleRecipeBuilder defaultRecipe) {
		super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
	}

	@Override
	public Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems,
									IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids) {
		Builder builder = new Builder(GuiTextures.BACKGROUND, 176, 166)
				.widget(new ProgressWidget(progressSupplier, PROGRESS_LEFT, PROGRESS_TOP, 20, 20, this.progressBarTexture, this.moveType));
		this.addInventorySlotGroup(builder, importItems, importFluids, false);
		this.addInventorySlotGroup(builder, exportItems, exportFluids, true);
		return builder;
	}

	@Override
	protected void addInventorySlotGroup(Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs) {
		if (isOutputs) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 5; j++) {
					int slotIndex = i * 5 + j;
					addSlot(builder, OUTPUT_LEFT + ITEM_SLOT_WITH_EDGE_DIMENSION * j, OUTPUT_TOP + ITEM_SLOT_WITH_EDGE_DIMENSION * i, slotIndex, itemHandler, fluidHandler, false, isOutputs);
				}
			}
		} else {
			addSlot(builder, INPUT_LEFT, INPUT_TOP, 0, itemHandler, fluidHandler, false, isOutputs);
			addSlot(builder, MESH_LEFT, MESH_TOP, 1, itemHandler, fluidHandler, false, isOutputs);
		}
	}
}