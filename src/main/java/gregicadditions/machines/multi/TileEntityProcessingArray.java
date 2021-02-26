package gregicadditions.machines.multi;

import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregicadditions.GAValues;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.simple.Tuple;
import gregicadditions.machines.overrides.GATieredMetaTileEntity;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.utils.GALog;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.lang.reflect.Field;
import java.util.*;

import static gregtech.api.unification.material.Materials.TungstenSteel;

public class TileEntityProcessingArray extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    public TileEntityProcessingArray(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.PROCESSING_ARRAY_RECIPES);
        ProcessingArrayWorkable recipeLogic = new ProcessingArrayWorkable(this);
        recipeLogic.initReflection();
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
        // TODO Auto-generated method stub
        return GAMetaBlocks.METAL_CASING.get(TungstenSteel);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        // TODO Auto-generated method stub
        return new TileEntityProcessingArray(metaTileEntityId);
    }

    protected static class ProcessingArrayWorkable extends GAMultiblockRecipeLogic {

        long voltageTier;
        int numberOfMachines = 0;
        int numberOfOperations = 0;
        ItemStack machineItemStack = null;
        Field wasActiveAndNeedsUpdateField = null;
        Field hasNotEnoughEnergyField = null;

        public void initReflection() {
            wasActiveAndNeedsUpdateField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "wasActiveAndNeedsUpdate");
            hasNotEnoughEnergyField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "hasNotEnoughEnergy");
        }

        public ProcessingArrayWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

            RecipeMap<?> recipeMap = findRecipeMap(inputs);
            if (recipeMap == null) {
                return null;
            }

            List<IItemHandlerModifiable> itemInputs = ((TileEntityProcessingArray) this.getMetaTileEntity()).getAbilities(MultiblockAbility.IMPORT_ITEMS);
            Tuple recipePerInput = itemInputs.stream()
                    .map(iItemHandlerModifiable -> new Tuple(recipeMap.findRecipe(maxVoltage, iItemHandlerModifiable, fluidInputs, 0), iItemHandlerModifiable))
                    .filter(tuple -> tuple.getRecipe() != null)
                    .findFirst().orElse(new Tuple(recipeMap.findRecipe(voltageTier, inputs, fluidInputs, this.getMinTankCapacity(this.getOutputTank())), inputs));


            if (recipePerInput.getRecipe() == null) {
                return null;
            }


            int minMultiplier = Integer.MAX_VALUE;

            Set<ItemStack> countIngredients = new HashSet<>();
            if (recipePerInput.getRecipe().getInputs().size() != 0) {

                this.findIngredients(countIngredients, recipePerInput.getInput());
                minMultiplier = Math.min(minMultiplier, this.getMinRatioItem(countIngredients, recipePerInput.getRecipe(), recipePerInput.getInput(), numberOfMachines));

            }

            Map<String, Integer> countFluid = new HashMap<>();
            if (recipePerInput.getRecipe().getFluidInputs().size() != 0) {

                this.findFluid(countFluid, fluidInputs);
                minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, recipePerInput.getRecipe(), numberOfMachines));
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
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, recipePerInput.getRecipe(), numberOfOperations);

            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt(recipePerInput.getRecipe().getEUt())
                    .duration(recipePerInput.getRecipe().getDuration());

            copyChancedItemOutputs(newRecipe, recipePerInput.getRecipe(), numberOfOperations);
            newRecipe.notConsumable(machineItemStack);

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

        protected int getMinRatioFluid(Map<String, Integer> countFluid, Recipe r, int numberOfMachines) {
            int minMultiplier = Integer.MAX_VALUE;
            for (FluidStack fs : r.getFluidInputs()) {
                String name = fs.getFluid().getUnlocalizedName();
                int ratio = Math.min(numberOfMachines, countFluid.get(name) / fs.amount);

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

        protected RecipeMap<?> findRecipeMap(IItemHandlerModifiable inputs) {

            for (int slot = 0; slot < inputs.getSlots(); slot++) {

                ItemStack wholeItemStack = inputs.getStackInSlot(slot);
                String unlocalizedName = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);
                String recipeMapName = findRecipeMapName(unlocalizedName);


                if ((unlocalizedName.contains("gregtech.machine") || unlocalizedName.contains("gtadditions.machine"))
                        && !findMachineInBlacklist(recipeMapName)
                        && GregTechAPI.META_TILE_ENTITY_REGISTRY.getObjectById(wholeItemStack.getItemDamage()) != null) {

                    MetaTileEntity mte = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObjectById(wholeItemStack.getItemDamage());
                    if (mte instanceof TieredMetaTileEntity || mte instanceof GATieredMetaTileEntity) {

                        RecipeMap<?> rmap = RecipeMap.getByName(recipeMapName);

                        // Find the RecipeMap of the MTE and ensure that the Processing Array only works on SimpleRecipeBuilders
                        // For some reason GTCE has specialized recipe maps for some machines, when it does not need them
                        if (rmap != null && (rmap.recipeBuilder() instanceof SimpleRecipeBuilder ||
                                rmap.recipeBuilder() instanceof IntCircuitRecipeBuilder ||
                                rmap.recipeBuilder() instanceof ArcFurnaceRecipeBuilder ||
                                rmap.recipeBuilder() instanceof CutterRecipeBuilder ||
                                rmap.recipeBuilder() instanceof UniversalDistillationRecipeBuilder)) {
                            // Find the voltage tier of the machine
                            this.voltageTier = (mte instanceof TieredMetaTileEntity) ?
                                    GAValues.V[((TieredMetaTileEntity) mte).getTier()] :
                                    GAValues.V[((GATieredMetaTileEntity) mte).getTier()];
                            // Find the number of machines
                            this.numberOfMachines = Math.min(GAConfig.multis.processingArray.processingArrayMachineLimit, wholeItemStack.getCount());
                            // The machine Item Stack. Is this needed if we remove the machine from being found in the ingredients?
                            this.machineItemStack = wholeItemStack;

                            return rmap;
                        }
                    }
                }
            }
            return null;
        }

        public String findRecipeMapName(String unlocalizedName) {

            String trimmedName = unlocalizedName.substring(0, unlocalizedName.lastIndexOf("."));
            trimmedName = trimmedName.substring(trimmedName.lastIndexOf(".") + 1);

            // For some reason, the Cutting saw's machine name does not match the recipe map's unlocalized name, so correct it
            // Same with the Electric Furnace
            if(trimmedName.equals("cutter")) {
                trimmedName = "cutting_saw";
            }
            else if(trimmedName.equals("electric_furnace")) {
                trimmedName = "furnace";
            }
            else if(trimmedName.equals("dehydrator")) {
                trimmedName = "chemical_dehydrator";
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
            try {
                if (this.wasActiveAndNeedsUpdateField.getBoolean(this)) {
                    this.wasActiveAndNeedsUpdateField.set(this, false);
                } else {
                    setActive(true);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
