package gregicadditions.machines.multi.simple;

import gregicadditions.GAMaterials;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.components.*;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTUtility;
import gregtech.api.util.InventoryUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static gregtech.api.gui.widgets.AdvancedTextWidget.withButton;
import static gregtech.api.gui.widgets.AdvancedTextWidget.withHoverTextTranslate;

abstract public class LargeSimpleRecipeMapMultiblockController extends GARecipeMapMultiblockController {

    private int EUtPercentage = 100;
    private int durationPercentage = 100;
    private int chancePercentage = 100;
    private int stack = 1;
    public long maxVoltage = 0;

    /**
     * When false, this multiblock will behave like any other.
     * When true, this multiblock will treat each of its input buses as distinct,
     * checking recipes for them independently. This is useful for many machines, for example the
     * Large Extruder, where the player may want to put one extruder shape per bus, rather than
     * one machine per extruder shape.
     */
    protected boolean isDistinct = false;

    DecimalFormat formatter = new DecimalFormat("#0.0");

    /**
     * Create large multiblock machine for simple machine.
     * <p>
     * Percentage : 80 => 0.8 mean lower
     * Percentage : 120 => 1.2 mean higher
     *
     * @param metaTileEntityId
     * @param recipeMap
     * @param EUtPercentage      should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param durationPercentage should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param chancePercentage   should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param stack              should be between 0 ~ Integer.MAX_VALUE, Default should be 1
     */
    public LargeSimpleRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
        super(metaTileEntityId, recipeMap);
        this.recipeMapWorkable = new LargeSimpleMultiblockRecipeLogic(this, EUtPercentage, durationPercentage, chancePercentage, stack);

        this.EUtPercentage = EUtPercentage;
        this.durationPercentage = durationPercentage;
        this.chancePercentage = chancePercentage;
        this.stack = stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.2", formatter.format(this.EUtPercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.3", formatter.format(this.durationPercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.4", this.stack));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.5", this.chancePercentage));
    }

    protected static Material getCasingMaterial(Material defaultMaterial, String materialString) {
        Material mat = Material.MATERIAL_REGISTRY.getObject(materialString);
        if (mat != null && mat.hasFlag(GAMaterials.GENERATE_METAL_CASING)) {
            return mat;
        }
        return defaultMaterial;
    }

    public static Predicate<BlockWorldState> motorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof MotorCasing)) {
                return false;
            } else {
                MotorCasing motorCasing = (MotorCasing) blockState.getBlock();
                MotorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                MotorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Motor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> emitterPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof EmitterCasing)) {
                return false;
            } else {
                EmitterCasing motorCasing = (EmitterCasing) blockState.getBlock();
                EmitterCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                EmitterCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Emitter", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> conveyorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof ConveyorCasing)) {
                return false;
            } else {
                ConveyorCasing motorCasing = (ConveyorCasing) blockState.getBlock();
                ConveyorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                ConveyorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Conveyor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> fieldGenPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof FieldGenCasing)) {
                return false;
            } else {
                FieldGenCasing motorCasing = (FieldGenCasing) blockState.getBlock();
                FieldGenCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                FieldGenCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("FieldGen", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> pistonPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof PistonCasing)) {
                return false;
            } else {
                PistonCasing motorCasing = (PistonCasing) blockState.getBlock();
                PistonCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                PistonCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Piston", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> pumpPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof PumpCasing)) {
                return false;
            } else {
                PumpCasing motorCasing = (PumpCasing) blockState.getBlock();
                PumpCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                PumpCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Pump", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> robotArmPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof RobotArmCasing)) {
                return false;
            } else {
                RobotArmCasing motorCasing = (RobotArmCasing) blockState.getBlock();
                RobotArmCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                RobotArmCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("RobotArm", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> sensorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof SensorCasing)) {
                return false;
            } else {
                SensorCasing motorCasing = (SensorCasing) blockState.getBlock();
                SensorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                SensorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Sensor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }


    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getEUt() < maxVoltage;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));

        ITextComponent buttonText = new TextComponentTranslation("gtadditions.multiblock.universal.distinct");
        buttonText.appendText(" ");
        ITextComponent button = withButton((isDistinct ?
                new TextComponentTranslation("gtadditions.multiblock.universal.distinct.yes") :
                new TextComponentTranslation("gtadditions.multiblock.universal.distinct.no")), "distinct");
        withHoverTextTranslate(button, "gtadditions.multiblock.universal.distinct.info");
        buttonText.appendSibling(button);
        textList.add(buttonText);
    }

    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        isDistinct = !isDistinct;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("Distinct", isDistinct);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isDistinct = data.getBoolean("Distinct");
    }

    public static class LargeSimpleMultiblockRecipeLogic extends GAMultiblockRecipeLogic {

        private final int EUtPercentage;
        private final int durationPercentage;
        private final int chancePercentage;
        private final int stack;
        public RecipeMap<?> recipeMap;

        // Fields used for distinct mode
        protected int lastRecipeIndex = 0;
        protected ItemStack[][] lastItemInputsMatrix;


        public LargeSimpleMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
            super(tileEntity);
            this.EUtPercentage = EUtPercentage;
            this.durationPercentage = durationPercentage;
            this.chancePercentage = chancePercentage;
            this.stack = stack;
            this.recipeMap = tileEntity.recipeMap;
        }

        public int getEUtPercentage() {
            return EUtPercentage;
        }

        public int getDurationPercentage() {
            return durationPercentage;
        }

        public int getChancePercentage() {
            return chancePercentage;
        }

        public int getStack() {
            return stack;
        }

        protected List<IItemHandlerModifiable> getInputBuses() {
            RecipeMapMultiblockController controller = (RecipeMapMultiblockController) metaTileEntity;
            return controller.getAbilities(MultiblockAbility.IMPORT_ITEMS);
        }

        @Override
        protected void trySearchNewRecipe() {
            if (metaTileEntity instanceof LargeSimpleRecipeMapMultiblockController && ((LargeSimpleRecipeMapMultiblockController) metaTileEntity).isDistinct) {
                    trySearchNewRecipeDistinct();
            } else trySearchNewRecipeCombined();
        }

        // Combined buses code =========================================================================================

        private void trySearchNewRecipeCombined() {
            long maxVoltage = getMaxVoltage();
            if (metaTileEntity instanceof LargeSimpleRecipeMapMultiblockController)
                maxVoltage = ((LargeSimpleRecipeMapMultiblockController) metaTileEntity).maxVoltage;
            Recipe currentRecipe = null;
            IItemHandlerModifiable importInventory = getInputInventory();
            IMultipleTankHandler importFluids = getInputTank();
            boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
            //inverse of logic in normal AbstractRecipeLogic
            //for MultiSmelter, we can reuse previous recipe if inputs didn't change
            //otherwise, we need to recompute it for new ingredients
            //but technically, it means we can cache multi smelter recipe, but changing inputs have more priority
            if (dirty || forceRecipeRecheck) {
                this.forceRecipeRecheck = false;
                //else, try searching new recipe for given inputs
                currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            } else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
                //if previous recipe still matches inputs, try to use it
                currentRecipe = previousRecipe;
            }
            if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
                setupRecipe(currentRecipe);
            }
        }

        // Distinct buses code =========================================================================================

        private void trySearchNewRecipeDistinct() {
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

        // Replacing this for optimization reasons
        protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs, int index) {
            boolean shouldRecheckRecipe = false;

            if (lastItemInputsMatrix == null || lastItemInputsMatrix.length != getInputBuses().size()) {
                lastItemInputsMatrix = new ItemStack[getInputBuses().size()][];
                GALog.logger.info("Num buses: " + getInputBuses().size());
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

        // Shared recipe generation code ===============================================================================

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            if (recipe != null)
                return createRecipe(maxVoltage, inputs, fluidInputs, recipe);
            return null;
        }

        protected Recipe createRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe matchingRecipe) {
            int maxItemsLimit = this.stack;
            int EUt;
            int duration;
            int currentTier = getOverclockingTier(maxVoltage);
            int tierNeeded;
            int minMultiplier = Integer.MAX_VALUE;

            tierNeeded = Math.max(1, getOverclockingTier(matchingRecipe.getEUt()));
            maxItemsLimit *= currentTier - tierNeeded;
            maxItemsLimit = Math.max(1, maxItemsLimit);


            Set<ItemStack> countIngredients = new HashSet<>();
            if (matchingRecipe.getInputs().size() != 0) {
                this.findIngredients(countIngredients, inputs);
                minMultiplier = Math.min(maxItemsLimit, this.getMinRatioItem(countIngredients, matchingRecipe, maxItemsLimit));
            }

            Map<String, Integer> countFluid = new HashMap<>();
            if (matchingRecipe.getFluidInputs().size() != 0) {

                this.findFluid(countFluid, fluidInputs);
                minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, matchingRecipe, maxItemsLimit));
            }

            if (minMultiplier == Integer.MAX_VALUE) {
                GALog.logger.error("Cannot calculate ratio of items for processing array");
                return null;
            }

            EUt = matchingRecipe.getEUt();
            duration = matchingRecipe.getDuration();

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, matchingRecipe, minMultiplier);

            // determine if there is enough room in the output to fit all of this
            boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(outputI, this.getOutputInventory());
            // if there isn't, we can't process this recipe.
            if (!canFitOutputs)
                return null;


            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) Math.max(1, EUt * this.EUtPercentage / 100))
                    .duration((int) Math.max(3, duration * (this.durationPercentage / 100.0)));

            copyChancedItemOutputs(newRecipe, matchingRecipe, minMultiplier);

            return newRecipe.build().getResult();
        }

        protected void copyChancedItemOutputs(RecipeBuilder<?> newRecipe, Recipe oldRecipe, int multiplier) {
            for (Recipe.ChanceEntry s : oldRecipe.getChancedOutputs()) {
                int chance = Math.min(10000, s.getChance() * this.chancePercentage / 100);
                int boost = s.getBoostPerTier() * this.chancePercentage / 100;
                IntStream.range(0, multiplier).forEach(value -> {
                    ItemStack itemStack = s.getItemStack().copy();
                    newRecipe.chancedOutput(itemStack, chance, boost);
                });
            }
        }


        protected void findIngredients(Set<ItemStack> countIngredients, IItemHandlerModifiable inputs) {
            for (int slot = 0; slot < inputs.getSlots(); slot++) {
                ItemStack wholeItemStack = inputs.getStackInSlot(slot);
                String name = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);
                // skip empty slots
                if (name.equals("tile.air")) {
                    continue;
                }
                boolean found = false;
                for (ItemStack i : countIngredients) {
                    if (ItemStack.areItemsEqual(i, wholeItemStack)) {
                        i.setCount(i.getCount() + wholeItemStack.getCount());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    countIngredients.add(wholeItemStack.copy());
                }
            }
        }

        protected int getMinRatioItem(Set<ItemStack> countIngredients, Recipe r, int maxItemsLimit) {
            int minMultiplier = Integer.MAX_VALUE;
            for (CountableIngredient ci : r.getInputs()) {
                if (ci.getCount() == 0) {
                    continue;
                }
                for (ItemStack wholeItemStack : countIngredients) {
                    if (ci.getIngredient().apply(wholeItemStack)) {
                        int ratio = Math.min(maxItemsLimit, wholeItemStack.getCount() / ci.getCount());
                        if (ratio < minMultiplier) {
                            minMultiplier = ratio;
                        }
                        break;
                    }
                }
            }
            return minMultiplier;
        }

        protected int getMinRatioFluid(Map<String, Integer> countFluid, Recipe r, int maxItemsLimit) {
            int minMultiplier = Integer.MAX_VALUE;
            for (FluidStack fs : r.getFluidInputs()) {
                if (fs.amount != 0) { // skip notConsumable fluids
                    String name = fs.getFluid().getUnlocalizedName();
                    int ratio = Math.min(maxItemsLimit, countFluid.get(name) / fs.amount);
                    if (ratio < minMultiplier) {
                        minMultiplier = ratio;
                    }
                }
            }
            return minMultiplier;
        }

        protected void findFluid(Map<String, Integer> countFluid, IMultipleTankHandler fluidInputs) {
            for (IFluidTank tank : fluidInputs) {
                if (tank.getFluid() != null) {
                    String name = tank.getFluid().getUnlocalizedName();
                    if (countFluid.containsKey(name)) {
                        int existingValue = countFluid.get(name);
                        countFluid.put(name, existingValue + tank.getFluidAmount());
                    } else {
                        countFluid.put(name, tank.getFluidAmount());
                    }
                }
            }
        }

        protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<FluidStack> newFluidInputs, List<ItemStack> outputI, List<FluidStack> outputF, Recipe r, int multiplier) {
            for (CountableIngredient ci : r.getInputs()) {
                CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * multiplier);
                newRecipeInputs.add(newIngredient);
            }
            for (FluidStack fs : r.getFluidInputs()) {
                FluidStack newFluid = new FluidStack(fs.getFluid(), fs.amount * multiplier);
                newFluidInputs.add(newFluid);
            }
            for (ItemStack s : r.getOutputs()) {
                int num = s.getCount() * multiplier;
                ItemStack itemCopy = s.copy();
                itemCopy.setCount(num);
                outputI.add(itemCopy);
            }
            for (FluidStack f : r.getFluidOutputs()) {
                int fluidNum = f.amount * multiplier;
                FluidStack fluidCopy = f.copy();
                fluidCopy.amount = fluidNum;
                outputF.add(fluidCopy);
            }
        }

        protected void setupRecipe(Recipe recipe) {
            long maxVoltage = getMaxVoltage();
            if (metaTileEntity instanceof LargeSimpleRecipeMapMultiblockController)
                maxVoltage = ((LargeSimpleRecipeMapMultiblockController) metaTileEntity).maxVoltage;
            int[] resultOverclock = calculateOverclock(recipe.getEUt(), maxVoltage, recipe.getDuration());
            this.progressTime = 1;
            setMaxProgress(resultOverclock[1]);
            this.recipeEUt = resultOverclock[0];
            this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
            int tier = getMachineTierForRecipe(recipe);
            this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(Integer.MAX_VALUE, random, tier));
            if (this.wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
            } else {
                this.setActive(true);
            }
        }

    }


}
