package gregicadditions.machines.multi.simple;

import gregicadditions.GAMaterials;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

abstract public class LargeSimpleRecipeMapMultiblockController extends RecipeMapMultiblockController {

    private int EUtPercentage = 100;
    private int durationPercentage = 100;
    private int chancePercentage = 100;
    private int stack = 1;

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

    public static class LargeSimpleMultiblockRecipeLogic extends MultiblockRecipeLogic {

        private int EUtPercentage = 100;
        private int durationPercentage = 100;
        private int chancePercentage = 100;
        private int stack = 1;
        public RecipeMap<?> recipeMap;




        public LargeSimpleMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
            super(tileEntity);
            this.EUtPercentage = EUtPercentage;
            this.durationPercentage = durationPercentage;
            this.chancePercentage = chancePercentage;
            this.stack = stack;
            this.recipeMap = tileEntity.recipeMap;
        }

        @Override
        /**
         * From multi-smelter.
         *
         */
        protected void trySearchNewRecipe() {
            long maxVoltage = getMaxVoltage();
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

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            List<IItemHandlerModifiable> itemInputs = ((LargeSimpleRecipeMapMultiblockController) this.getMetaTileEntity()).getAbilities(MultiblockAbility.IMPORT_ITEMS);

            Tuple recipePerInput = itemInputs.stream()
                    .map(iItemHandlerModifiable -> new Tuple(recipeMap.findRecipe(maxVoltage, iItemHandlerModifiable, fluidInputs, 0), iItemHandlerModifiable))
                    .filter(tuple -> tuple.getRecipe() != null)
                    .findFirst().orElse(new Tuple(recipeMap.findRecipe(maxVoltage, inputs, fluidInputs, 0), inputs));

            if (recipePerInput.getRecipe() == null) {
                return null;
            }

            return createRecipe(maxVoltage, recipePerInput.getInput(), fluidInputs, recipePerInput.getRecipe());


        }


        protected Recipe createRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe matchingRecipe) {
            int maxItemsLimit = this.stack;
            int EUt = 0;
            int duration = 0;
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
                GTLog.logger.error("Cannot calculate ratio of items for processing array");
                return null;
            }

            EUt = matchingRecipe.getEUt();
            duration = matchingRecipe.getDuration();

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, matchingRecipe, minMultiplier);
            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) Math.max(1, EUt * this.EUtPercentage / 100))
                    .duration((int) Math.max(1.0, duration * (this.durationPercentage / 100.0)));

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
                if (fs.amount == 0) {
                    continue;
                }
                String name = fs.getFluid().getUnlocalizedName();
                int ratio = Math.min(maxItemsLimit, countFluid.get(name) / fs.amount);
                if (ratio < minMultiplier) {
                    minMultiplier = ratio;
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
            int[] resultOverclock = calculateOverclock(recipe.getEUt(), getMaxVoltage(), recipe.getDuration());
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
