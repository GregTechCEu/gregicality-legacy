package gregicadditions.machines.multi;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.impl.ControllerSlotMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.RecipeMapMultiblockWithSlotController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.utils.Tuple;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.utils.GALog;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.*;
import gregtech.api.recipes.Recipe.ChanceEntry;
import gregtech.api.recipes.builders.*;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.*;

import static gregtech.api.unification.material.Materials.TungstenSteel;

public class TileEntityProcessingArray extends RecipeMapMultiblockWithSlotController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    public TileEntityProcessingArray(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.PROCESSING_ARRAY_RECIPES, ITieredMetaTileEntity.class);
        ProcessingArrayWorkable recipeLogic = new ProcessingArrayWorkable(this);
        this.recipeMapWorkable = recipeLogic;
    }

    @Override
    protected BlockPattern createStructurePattern() {

        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate()).build();

    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(TungstenSteel);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart arg0) {
        return GAMetaBlocks.METAL_CASING.get(TungstenSteel);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityProcessingArray(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            IEnergyContainer energyContainer = recipeMapWorkable.getEnergyContainer();
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }

            String myRecipeMap = "recipemap." + ((ProcessingArrayWorkable) recipeMapWorkable).recipeMapName + ".name";
            if (myRecipeMap != null) {
                textList.add(new TextComponentTranslation("gtadditions.machine.pa.display1", myRecipeMap).setStyle(new Style().setColor(TextFormatting.GOLD)));
                textList.add(new TextComponentTranslation("gtadditions.machine.pa.display2",
                        Math.min(getStackInSlot().getCount(), GAConfig.multis.processingArray.processingArrayMachineLimit)).setStyle(new Style().setColor(TextFormatting.AQUA)));
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
        } else {
            ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.invalid_structure.tooltip");
            tooltip.setStyle(new Style().setColor(TextFormatting.GRAY));
            textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure")
                    .setStyle(new Style().setColor(TextFormatting.RED)
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip))));
        }
    }

    protected static class ProcessingArrayWorkable extends ControllerSlotMultiblockRecipeLogic {

        long voltageTier;
        int numberOfMachines = 0;
        int numberOfOperations = 0;
        ItemStack machineItemStack = null;
        private ItemStack lastMachine;
        private RecipeMap<?> lastRecipeMap;
        String recipeMapName = null;

        public ProcessingArrayWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

            RecipeMap<?> recipeMap = findRecipeMap(((TileEntityProcessingArray)this.getMetaTileEntity()).getStackInSlot());
            if (recipeMap == null) {
                recipeMapName = null;
                return null;
            }
            recipeMapName = recipeMap.getUnlocalizedName();

            List<IItemHandlerModifiable> itemInputs = ((TileEntityProcessingArray) this.getMetaTileEntity()).getAbilities(MultiblockAbility.IMPORT_ITEMS);
            Tuple<Recipe, IItemHandlerModifiable> recipePerInput = itemInputs.stream()
                    .map(iItemHandlerModifiable -> new Tuple<>(recipeMap.findRecipe(maxVoltage, iItemHandlerModifiable, fluidInputs, 0), iItemHandlerModifiable))
                    .filter(tuple -> tuple.getKey() != null)
                    .findFirst().orElse(new Tuple<>(recipeMap.findRecipe(voltageTier, inputs, fluidInputs, this.getMinTankCapacity(this.getOutputTank())), inputs));


            if (recipePerInput.getKey() == null) {
                return null;
            }


            int minMultiplier = Integer.MAX_VALUE;

            Set<ItemStack> countIngredients = new HashSet<>();
            if (recipePerInput.getKey().getInputs().size() != 0) {

                this.findIngredients(countIngredients, recipePerInput.getValue());
                minMultiplier = Math.min(minMultiplier, this.getMinRatioItem(countIngredients, recipePerInput.getKey(), recipePerInput.getValue(), numberOfMachines));

            }

            Map<Fluid, Integer> countFluid = new HashMap<>();
            if (recipePerInput.getKey().getFluidInputs().size() != 0) {

                this.findFluid(countFluid, fluidInputs);
                minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, recipePerInput.getKey(), numberOfMachines));
            }

            if (minMultiplier == Integer.MAX_VALUE) {
                GALog.logger.error("Cannot calculate ratio of items for processing array");
                return null;
            }

            this.numberOfOperations = minMultiplier;

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, recipePerInput.getKey(), numberOfOperations);

            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt(recipePerInput.getKey().getEUt())
                    .duration(recipePerInput.getKey().getDuration());

            copyChancedItemOutputs(newRecipe, recipePerInput.getKey(), numberOfOperations);

            return newRecipe.build().getResult();
        }


        protected void copyChancedItemOutputs(RecipeBuilder<?> newRecipe, Recipe oldRecipe, int numberOfOperations) {
            for (ChanceEntry s : oldRecipe.getChancedOutputs()) {
                int chance = s.getChance();
                ItemStack itemStack = s.getItemStack().copy();
                int boost = s.getBoostPerTier();
                itemStack.setCount(itemStack.getCount() * numberOfOperations);
                newRecipe.chancedOutput(itemStack, chance, boost);
            }
        }

        protected void findIngredients(Set<ItemStack> countIngredients, IItemHandlerModifiable inputs) {

            for (int slot = 0; slot < inputs.getSlots(); slot++) {
                ItemStack wholeItemStack = inputs.getStackInSlot(slot);
                String name = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);

                // skip empty slots
                if (wholeItemStack.isEmpty()) {
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

        protected int getMinRatioItem(Set<ItemStack> countIngredients, Recipe r, IItemHandlerModifiable inputs, int numberOfMachines) {

            int minMultiplier = Integer.MAX_VALUE;
            for (CountableIngredient ci : r.getInputs()) {

                if (ci.getCount() == 0)
                    continue;

                for (ItemStack wholeItemStack : countIngredients) {

                    if (ci.getIngredient().apply(wholeItemStack)) {
                        int ratio = Math.min(numberOfMachines, wholeItemStack.getCount() / ci.getCount());
                        if (ratio < minMultiplier) {
                            minMultiplier = ratio;
                        }
                        break;
                    }
                }
            }
            return minMultiplier;
        }

        protected void findFluid(Map<Fluid, Integer> countFluid, IMultipleTankHandler fluidInputs) {

            for (IFluidTank tank : fluidInputs) {

                if (tank.getFluid() != null) {

                    Fluid fluid = tank.getFluid().getFluid();

                    if (countFluid.containsKey(fluid)) {
                        int existingValue = countFluid.get(fluid);
                        countFluid.put(fluid, existingValue + tank.getFluidAmount());

                    } else {
                        countFluid.put(fluid, tank.getFluidAmount());
                    }
                }
            }
        }

        protected int getMinRatioFluid(Map<Fluid, Integer> countFluid, Recipe r, int numberOfMachines) {
            int minMultiplier = Integer.MAX_VALUE;
            for (FluidStack fs : r.getFluidInputs()) {
                Fluid fluid = fs.getFluid();
                int ratio = Math.min(numberOfMachines, countFluid.get(fluid) / fs.amount);

                if (ratio < minMultiplier) {
                    minMultiplier = ratio;
                }
            }
            return minMultiplier;
        }

        protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<FluidStack> newFluidInputs, List<ItemStack> outputI, List<FluidStack> outputF, Recipe r, int numberOfOperations) {
            for (CountableIngredient ci : r.getInputs()) {
                CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * numberOfOperations);
                newRecipeInputs.add(newIngredient);
            }
            for (FluidStack fs : r.getFluidInputs()) {
                FluidStack newFluid = new FluidStack(fs.getFluid(), fs.amount * numberOfOperations);
                newFluidInputs.add(newFluid);
            }
            for (ItemStack s : r.getOutputs()) {
                int num = s.getCount() * numberOfOperations;
                ItemStack itemCopy = s.copy();
                itemCopy.setCount(num);
                outputI.add(itemCopy);
            }
            for (FluidStack f : r.getFluidOutputs()) {
                int fluidNum = f.amount * numberOfOperations;
                FluidStack fluidCopy = f.copy();
                fluidCopy.amount = fluidNum;
                outputF.add(fluidCopy);
            }
        }

        protected RecipeMap<?> findRecipeMap(ItemStack machine) {
            if (machine.isEmpty())
                return null;

            if (lastMachine != null && machine.isItemEqual(lastMachine) && machine.getCount() == lastMachine.getCount())
                return this.lastRecipeMap;
            else this.lastMachine = machine.copy();

            ITieredMetaTileEntity mte = (ITieredMetaTileEntity) GregTechAPI.META_TILE_ENTITY_REGISTRY.getObjectById(machine.getItemDamage());
            String unlocalizedName = machine.getItem().getUnlocalizedNameInefficiently(machine); // can do this differently

            if (mte != null && !findMachineInBlacklist(unlocalizedName)) {
                RecipeMap<?> rmap = RecipeMap.getByName(findRecipeMapName(unlocalizedName));
                if (rmap != null && (rmap.recipeBuilder() instanceof SimpleRecipeBuilder ||
                        rmap.recipeBuilder() instanceof IntCircuitRecipeBuilder ||
                        rmap.recipeBuilder() instanceof ArcFurnaceRecipeBuilder ||
                        rmap.recipeBuilder() instanceof CutterRecipeBuilder ||
                        rmap.recipeBuilder() instanceof UniversalDistillationRecipeBuilder)) {
                    // Find the voltage tier of the machine
                    this.voltageTier = GAValues.V[mte.getTier()];
                    // Find the number of machines
                    this.numberOfMachines = Math.min(GAConfig.multis.processingArray.processingArrayMachineLimit, machine.getCount());
                    // The machine Item Stack. Is this needed if we remove the machine from being found in the ingredients?
                    this.machineItemStack = machine;

                    this.lastRecipeMap = rmap;
                    return rmap;
                }
            }
            return null;
        }

        public String findRecipeMapName(String unlocalizedName) {

            String trimmedName = unlocalizedName.substring(0, unlocalizedName.lastIndexOf("."));
            trimmedName = trimmedName.substring(trimmedName.lastIndexOf(".") + 1);

            // For some reason, the Cutting saw's machine name does not match the recipe map's unlocalized name, so correct it
            // Same with the Electric Furnace, Ore Washer, and our Chemical Dehydrator
            switch (trimmedName) {
                case "cutter":
                    trimmedName = "cutting_saw"; break;
                case "electric_furnace":
                    trimmedName = "furnace"; break;
                case "ore_washer":
                    trimmedName = "orewasher"; break;
                case "dehydrator":
                    trimmedName = "chemical_dehydrator"; break;
            }
            return trimmedName;
        }

        public boolean findMachineInBlacklist(String unlocalizedName) {
            String[] blacklist = GAConfig.multis.processingArray.machineBlackList;
            return Arrays.asList(blacklist).contains(unlocalizedName);
        }


        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {

            IItemHandlerModifiable importInventory = getInputInventory();
            IItemHandlerModifiable exportInventory = getOutputInventory();
            IMultipleTankHandler importFluids = getInputTank();
            IMultipleTankHandler exportFluids = getOutputTank();

            int[] resultOverclock = calculateOverclock(recipe.getEUt(), voltageTier, recipe.getDuration());
            int totalEUt = resultOverclock[0] * resultOverclock[1] * this.numberOfOperations;

            boolean enoughPower = totalEUt >= 0 ? getEnergyStored() >= (totalEUt > getEnergyCapacity() / 2 ? resultOverclock[0] : totalEUt) : getEnergyStored() - resultOverclock[0] * this.numberOfOperations <= getEnergyCapacity();

            if (!enoughPower) {
                return false;
            }
            return MetaTileEntity.addItemsToItemHandler(exportInventory, true, recipe.getAllItemOutputs(exportInventory.getSlots())) && MetaTileEntity.addFluidsToFluidHandler(exportFluids, true, recipe.getFluidOutputs()) && recipe.matches(true, importInventory, importFluids);
        }

        @Override
        protected void trySearchNewRecipe() {
            long maxVoltage = getMaxVoltage();
            Recipe currentRecipe = null;
            IItemHandlerModifiable importInventory = getInputInventory();
            IMultipleTankHandler importFluids = getInputTank();

            boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
            if (dirty || forceRecipeRecheck) {
                this.forceRecipeRecheck = false;

                // else, try searching new recipe for given inputs
                currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            } else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
                // if previous recipe still matches inputs, try to use it
                currentRecipe = previousRecipe;
            }
            if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
                setupRecipe(currentRecipe);
            }
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            int[] resultOverclock = calculateOverclock(recipe.getEUt(), voltageTier, recipe.getDuration());
            this.progressTime = 1;
            setMaxProgress(resultOverclock[1]);
            this.recipeEUt = resultOverclock[0] * this.numberOfOperations;
            this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
            int tier = getMachineTierForRecipe(recipe);
            this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(getOutputInventory().getSlots(), random, tier));
            if (this.wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
            } else {
                setActive(true);
            }
        }
    }
}
