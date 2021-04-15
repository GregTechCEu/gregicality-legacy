package gregicadditions.capabilities.impl;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class RecipeMapDistinctBusesMultiblockController extends MultiblockWithDisplayBase {

    public final RecipeMap<?> recipeMap;
    protected DistinctBusesMultiblockRecipeLogic recipeMapWorkable;

    protected List<IItemHandlerModifiable> inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;

    public RecipeMapDistinctBusesMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId);
        this.recipeMap = recipeMap;
        this.recipeMapWorkable = new DistinctBusesMultiblockRecipeLogic(this); // TODO
        resetTileAbilities();
    }

    public IEnergyContainer getEnergyContainer() {
        return energyContainer;
    }

    public List<IItemHandlerModifiable> getInputInventory() {
        return inputInventory;
    }

    public IItemHandlerModifiable getOutputInventory() {
        return outputInventory;
    }

    public IMultipleTankHandler getInputFluidInventory() {
        return inputFluidInventory;
    }

    public IMultipleTankHandler getOutputFluidInventory() {
        return outputFluidInventory;
    }

    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return true;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    @Override
    protected void updateFormedValid() {
        this.recipeMapWorkable.updateWorkable();
    }

    private void initializeAbilities() {
        this.inputInventory = getAbilities(MultiblockAbility.IMPORT_ITEMS);
        this.inputFluidInventory = new FluidTankList(allowSameFluidFillForOutputs(), getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(allowSameFluidFillForOutputs(), getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.inputInventory = new ArrayList<>();
        this.inputFluidInventory = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.outputFluidInventory = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    protected boolean allowSameFluidFillForOutputs() {
        return true;
    }

    // TODO Maybe do more in here?
    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            IEnergyContainer energyContainer = recipeMapWorkable.getEnergyContainer();
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GTValues.VN[GTUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }

            if (!recipeMapWorkable.isWorkingEnabled()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));

            } else if (recipeMapWorkable.isActive()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
                int currentProgress = (int) (recipeMapWorkable.getProgressPercent() * 100);
                textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
            } else {
                textList.add(new TextComponentTranslation("gregtech.multiblock.idling"));
            }

            if (recipeMapWorkable.isHasNotEnoughEnergy()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        int itemInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_ITEMS, Collections.emptyList())
                .stream().map(it -> (IItemHandler) it).mapToInt(IItemHandler::getSlots).sum();
        int fluidInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        return itemInputsCount >= recipeMap.getMinInputs() &&
                fluidInputsCount >= recipeMap.getMinFluidInputs() &&
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().render(renderState, translation, pipeline, getFrontFacing(), recipeMapWorkable.isActive());
    }

    /**
     * Override this method to change the Controller overlay
     * @return The overlay to render on the Multiblock Controller
     */
    @Nonnull
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.MULTIBLOCK_WORKABLE_OVERLAY;
    }

    public static class DistinctBusesMultiblockRecipeLogic extends AbstractRecipeLogic {

        private int lastRecipeIndex = 0;

        public DistinctBusesMultiblockRecipeLogic(RecipeMapDistinctBusesMultiblockController tileEntity) {
            super(tileEntity, tileEntity.recipeMap);
        }

        @Override
        public void update() {
        }

        public void updateWorkable() {
            super.update();
        }

        public IEnergyContainer getEnergyContainer() {
            RecipeMapDistinctBusesMultiblockController controller = (RecipeMapDistinctBusesMultiblockController) metaTileEntity;
            return controller.getEnergyContainer();
        }

        @Override
        protected IItemHandlerModifiable getInputInventory() {
            return null; // DO NOT USE THIS!!!
        }

        public List<IItemHandlerModifiable> getInputBuses() {
            RecipeMapDistinctBusesMultiblockController controller = (RecipeMapDistinctBusesMultiblockController) metaTileEntity;
            return controller.getInputInventory();
        }

        @Override
        protected IItemHandlerModifiable getOutputInventory() {
            RecipeMapDistinctBusesMultiblockController controller = (RecipeMapDistinctBusesMultiblockController) metaTileEntity;
            return controller.getOutputInventory();
        }

        @Override
        protected IMultipleTankHandler getInputTank() {
            RecipeMapDistinctBusesMultiblockController controller = (RecipeMapDistinctBusesMultiblockController) metaTileEntity;
            return controller.getInputFluidInventory();
        }

        @Override
        protected IMultipleTankHandler getOutputTank() {
            RecipeMapDistinctBusesMultiblockController controller = (RecipeMapDistinctBusesMultiblockController) metaTileEntity;
            return controller.getOutputFluidInventory();
        }

        @Override
        protected long getEnergyStored() {
            return getEnergyContainer().getEnergyStored();
        }

        @Override
        protected long getEnergyCapacity() {
            return getEnergyContainer().getEnergyCapacity();
        }

        @Override
        protected boolean drawEnergy(int recipeEUt) {
            long resultEnergy = getEnergyStored() - recipeEUt;
            if (resultEnergy >= 0L && resultEnergy <= getEnergyCapacity()) {
                getEnergyContainer().changeEnergy(-recipeEUt);
                return true;
            } else return false;
        }

        @Override
        protected long getMaxVoltage() {
            return Math.max(getEnergyContainer().getInputVoltage(), getEnergyContainer().getOutputVoltage());
        }

        @Override
        protected void trySearchNewRecipe() {
            long maxVoltage = getMaxVoltage();
            Recipe currentRecipe = null;
            List<IItemHandlerModifiable> importInventory = getInputBuses();
            IMultipleTankHandler importFluids = getInputTank();

            // Our caching implementation
            if (previousRecipe != null && previousRecipe.matches(false, importInventory.get(lastRecipeIndex), importFluids)) {
                currentRecipe = previousRecipe;
                if (setupAndConsumeRecipeInputs(currentRecipe, lastRecipeIndex)) {
                    setupRecipe(currentRecipe);
                    return;
                }
            }
            for (int i = 0; i < importInventory.size(); i++) {
                IItemHandlerModifiable bus = importInventory.get(i);
                boolean dirty = checkRecipeInputsDirty(bus, importFluids);
                if (dirty || forceRecipeRecheck) {
                    this.forceRecipeRecheck = false;
                    currentRecipe = findRecipe(maxVoltage, bus, importFluids);
                    if (currentRecipe != null) {
                        this.previousRecipe = currentRecipe;
                    }
                }
                if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe, i)) {
                    lastRecipeIndex = i;
                    setupRecipe(currentRecipe);
                    break;
                }
            }
        }

        protected boolean setupAndConsumeRecipeInputs(Recipe recipe, int index) {
            RecipeMapMultiblockController controller = (RecipeMapMultiblockController) metaTileEntity;
            if (controller.checkRecipe(recipe, false)) {

                int[] resultOverclock = calculateOverclock(recipe.getEUt(), recipe.getDuration());
                int totalEUt = resultOverclock[0] * resultOverclock[1];
                IItemHandlerModifiable importInventory = getInputBuses().get(index);
                IItemHandlerModifiable exportInventory = getOutputInventory();
                IMultipleTankHandler importFluids = getInputTank();
                IMultipleTankHandler exportFluids = getOutputTank();
                boolean setup = (totalEUt >= 0 ? getEnergyStored() >= (totalEUt > getEnergyCapacity() / 2 ? resultOverclock[0] : totalEUt) :
                        (getEnergyStored() - resultOverclock[0] <= getEnergyCapacity())) &&
                        MetaTileEntity.addItemsToItemHandler(exportInventory, true, recipe.getAllItemOutputs(exportInventory.getSlots())) &&
                        MetaTileEntity.addFluidsToFluidHandler(exportFluids, true, recipe.getFluidOutputs()) &&
                        recipe.matches(true, importInventory, importFluids);

                if (setup) {
                    controller.checkRecipe(recipe, true);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
            return false; // DO NOT USE!!!
        }
    }
}
