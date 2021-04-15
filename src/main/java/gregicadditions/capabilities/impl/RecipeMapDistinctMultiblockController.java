package gregicadditions.capabilities.impl;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * This class serves as an alternative to {@link gregicadditions.capabilities.impl.GARecipeMapMultiblockController},
 * except it treats its input buses as separate and distinct inventories for recipes.
 */
public abstract class RecipeMapDistinctMultiblockController extends MultiblockWithDisplayBase {

    public final RecipeMap<?> recipeMap;
    protected DistinctBusesMultiblockRecipeLogic recipeMapWorkable;

    protected List<IItemHandlerModifiable> inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;

    public RecipeMapDistinctMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
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
                String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
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

        protected int lastRecipeIndex = 0;
        protected ItemStack[][] lastItemInputsMatrix;

        public DistinctBusesMultiblockRecipeLogic(RecipeMapDistinctMultiblockController tileEntity) {
            super(tileEntity, tileEntity.recipeMap);
        }

        @Override
        public void update() {
        }

        public void updateWorkable() {
            super.update();
        }

        public IEnergyContainer getEnergyContainer() {
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
            return controller.getEnergyContainer();
        }

        protected List<IItemHandlerModifiable> getInputBuses() {
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
            return controller.getInputInventory();
        }

        @Override
        protected IItemHandlerModifiable getOutputInventory() {
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
            return controller.getOutputInventory();
        }

        @Override
        protected IMultipleTankHandler getInputTank() {
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
            return controller.getInputFluidInventory();
        }

        @Override
        protected IMultipleTankHandler getOutputTank() {
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
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
            // This guarantees that if we get a recipe cache hit, our efficiency is no different from other machines
            if (previousRecipe != null && previousRecipe.matches(false, importInventory.get(lastRecipeIndex), importFluids)) {
                currentRecipe = previousRecipe;
                if (setupAndConsumeRecipeInputs(currentRecipe, lastRecipeIndex)) {
                    setupRecipe(currentRecipe);
                    return;
                }
            }

            // On a cache miss, our efficiency is much worse, as it will check
            // each bus individually instead of the combined inventory all at once.
            for (int i = 0; i < importInventory.size(); i++) {
                IItemHandlerModifiable bus = importInventory.get(i);
                boolean dirty = checkRecipeInputsDirty(bus, importFluids, i);
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
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
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

        // Replacing this for optimization reasons
        protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs, int index) {
            boolean shouldRecheckRecipe = false;
            RecipeMapDistinctMultiblockController controller = (RecipeMapDistinctMultiblockController) metaTileEntity;
            if (lastItemInputsMatrix == null || lastItemInputsMatrix.length != controller.inputInventory.size()) {
                lastItemInputsMatrix = new ItemStack[controller.inputInventory.size()][];
                GALog.logger.info("Num buses: " + controller.inputInventory.size());
            }
            if (lastItemInputsMatrix[index] == null || lastItemInputsMatrix[index].length != inputs.getSlots()) {
                this.lastItemInputsMatrix[index] = new ItemStack[inputs.getSlots()];
                Arrays.fill(lastItemInputsMatrix[index], ItemStack.EMPTY);
            }
            if (lastFluidInputs == null || lastFluidInputs.length != fluidInputs.getTanks()) {
                this.lastFluidInputs = new FluidStack[fluidInputs.getTanks()];
            }
            for (int i = 0; i < lastItemInputsMatrix[index].length; i++) {
                ItemStack currentStack = inputs.getStackInSlot(i);
                ItemStack lastStack = lastItemInputsMatrix[index][i];
                if (!areItemStacksEqual(currentStack, lastStack)) {
                    this.lastItemInputsMatrix[index][i] = currentStack.isEmpty() ? ItemStack.EMPTY : currentStack.copy();
                    shouldRecheckRecipe = true;
                } else if (currentStack.getCount() != lastStack.getCount()) {
                    lastStack.setCount(currentStack.getCount());
                    shouldRecheckRecipe = true;
                }
            }
            for (int i = 0; i < lastFluidInputs.length; i++) {
                FluidStack currentStack = fluidInputs.getTankAt(i).getFluid();
                FluidStack lastStack = lastFluidInputs[i];
                if ((currentStack == null && lastStack != null) ||
                        (currentStack != null && !currentStack.isFluidEqual(lastStack))) {
                    this.lastFluidInputs[i] = currentStack == null ? null : currentStack.copy();
                    shouldRecheckRecipe = true;
                } else if (currentStack != null && lastStack != null &&
                        currentStack.amount != lastStack.amount) {
                    lastStack.amount = currentStack.amount;
                    shouldRecheckRecipe = true;
                }
            }
            return shouldRecheckRecipe;
        }

        // Dead methods

        @Override
        protected IItemHandlerModifiable getInputInventory() {
            GALog.logger.error("In old getInputInventory! Please report this error!");
            return null; // DO NOT USE!!!
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
            GALog.logger.error("In old setupAndConsumeRecipeInputs! Please report this error!");
            return false; // DO NOT USE!!!
        }

        @Override
        protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs) {
            GALog.logger.error("In old checkRecipeInputsDirty! Please report this error!");
            return false;
        }
    }
}
