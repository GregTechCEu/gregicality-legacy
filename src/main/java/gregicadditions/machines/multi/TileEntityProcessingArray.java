package gregicadditions.machines.multi;

import gregicadditions.GAEnums;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.simple.Tuple;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.*;
import gregtech.api.recipes.Recipe.ChanceEntry;
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

public class TileEntityProcessingArray extends RecipeMapMultiblockController {

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

    protected static class ProcessingArrayWorkable extends MultiblockRecipeLogic {

        int machineTierVoltage = 0;
        int numberOfMachines = 0;
        int numberOfOperations = 0;
        ItemStack machineItemStack = null;
        String machineName = "";
        Field wasActiveAndNeedsUpdateField = null;
        Field hasNotEnoughEnergyField = null;
        // Method setActiveMethod = null;

        public RecipeMap<?> getRecipeMaps(String machineName) {
            switch (machineName) {
                case "macerator":
                    return RecipeMaps.MACERATOR_RECIPES;
                case "cluster_mill":
                    return GARecipeMaps.CLUSTER_MILL_RECIPES;
                case "lathe":
                    return RecipeMaps.LATHE_RECIPES;
                case "extractor":
                    return RecipeMaps.EXTRACTOR_RECIPES;
                case "fluid_extractor":
                    return RecipeMaps.FLUID_EXTRACTION_RECIPES;
                case "alloy_smelter":
                    return RecipeMaps.ALLOY_SMELTER_RECIPES;
                case "ore_washer":
                    return RecipeMaps.ORE_WASHER_RECIPES;
                case "thermal_centrifuge":
                    return RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
                case "centrifuge":
                    return RecipeMaps.CENTRIFUGE_RECIPES;
                case "electrolyzer":
                    return RecipeMaps.ELECTROLYZER_RECIPES;
                case "electric_furnace":
                    return RecipeMaps.FURNACE_RECIPES;
                case "bender":
                    return RecipeMaps.BENDER_RECIPES;
                case "arc_furnace":
                    return RecipeMaps.ARC_FURNACE_RECIPES;
                case "autoclave":
                    return RecipeMaps.AUTOCLAVE_RECIPES;
                case "assembler":
                    return RecipeMaps.ASSEMBLER_RECIPES;
                case "brewery":
                    return RecipeMaps.BREWING_RECIPES;
                case "canner":
                    return RecipeMaps.CANNER_RECIPES;
                case "chemical_bath":
                    return RecipeMaps.CHEMICAL_BATH_RECIPES;
                case "chemical_reactor":
                    return RecipeMaps.CHEMICAL_RECIPES;
                case "compressor":
                    return RecipeMaps.COMPRESSOR_RECIPES;
                case "cutter":
                    return RecipeMaps.CUTTER_RECIPES;
                case "distillery":
                    return RecipeMaps.DISTILLATION_RECIPES;
                case "electromagnetic_separator":
                    return RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES;
                case "fermenter":
                    return RecipeMaps.FERMENTING_RECIPES;
                case "fluid_canner":
                    return RecipeMaps.FLUID_CANNER_RECIPES;
                case "fluid_heater":
                    return RecipeMaps.FLUID_HEATER_RECIPES;
                case "fluid_solidifier":
                    return RecipeMaps.FLUID_SOLIDFICATION_RECIPES;
                case "forge_hammer":
                    return RecipeMaps.FORGE_HAMMER_RECIPES;
                case "forming_press":
                    return RecipeMaps.FORMING_PRESS_RECIPES;
                case "microwave":
                    return RecipeMaps.MICROWAVE_RECIPES;
                case "mixer":
                    return RecipeMaps.MIXER_RECIPES;
                case "packer":
                    return RecipeMaps.PACKER_RECIPES;
                case "unpacker":
                    return RecipeMaps.UNPACKER_RECIPES;
                case "plasma_arc_furnace":
                    return RecipeMaps.PLASMA_ARC_FURNACE_RECIPES;
                case "polarizer":
                    return RecipeMaps.POLARIZER_RECIPES;
                case "laser_engraver":
                    return RecipeMaps.LASER_ENGRAVER_RECIPES;
                case "wiremill":
                    return RecipeMaps.WIREMILL_RECIPES;
//                case "mass_fab":              too easy
//                    return GARecipeMaps.MASS_FAB_RECIPES;
//                case "replicator":            too easy
//                    return GARecipeMaps.REPLICATOR_RECIPES;
//                case "circuit_assembler":     too easy
//                    return GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
                case "sieve":
                    return GARecipeMaps.SIEVE_RECIPES;
                case "dehydrator":
                    return GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
                case "simple_ore_washer":
                    return GARecipeMaps.SIMPLE_ORE_WASHER_RECIPES;
                case "attractor":
                    return GARecipeMaps.ATTRACTOR_RECIPES;
                case "decay_chamber":
                    return GARecipeMaps.DECAY_CHAMBERS_RECIPES;
                case "green_house":
                    return GARecipeMaps.GREEN_HOUSE_RECIPES;
                case "sifter":
                    return RecipeMaps.SIFTER_RECIPES;
                case "extruder":
                    return RecipeMaps.EXTRUDER_RECIPES;
                default:
                    return null;
            }

        }

        public void initReflection() {
            wasActiveAndNeedsUpdateField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "wasActiveAndNeedsUpdate");
            // setActiveMethod =
            // ObfuscationReflectionHelper.findMethod(AbstractRecipeLogic.class,
            // "setActive", null, Boolean.class);
            hasNotEnoughEnergyField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "hasNotEnoughEnergy");
        }

        public ProcessingArrayWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

            String machineName = findMachine(inputs, fluidInputs);
            RecipeMap<?> recipeM = getRecipeMaps(machineName);

            if (recipeM == null) {
                return null;
            }
            List<IItemHandlerModifiable> itemInputs = ((TileEntityProcessingArray) this.getMetaTileEntity()).getAbilities(MultiblockAbility.IMPORT_ITEMS);
            Tuple recipePerInput = itemInputs.stream()
                    .map(iItemHandlerModifiable -> new Tuple(recipeMap.findRecipe(maxVoltage, iItemHandlerModifiable, fluidInputs, 0), iItemHandlerModifiable))
                    .filter(tuple -> tuple.getRecipe() != null)
                    .findFirst().orElse(new Tuple(recipeM.findRecipe(machineTierVoltage, inputs, fluidInputs, this.getMinTankCapacity(this.getOutputTank())), inputs));


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

            RecipeBuilder<?> newRecipe = recipeM.recipeBuilder()
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

                if (ci.getCount() == 0) {
                    continue;
                }

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

        protected String findMachine(IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

            for (int slot = 0; slot < inputs.getSlots(); slot++) {
                // find tileentity
                ItemStack wholeItemStack = inputs.getStackInSlot(slot);
                String unlocalizedName = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);
                if (unlocalizedName.contains("gregtech.machine") || unlocalizedName.contains("gtadditions.machine")) {
                    this.numberOfMachines = Math.min(64, wholeItemStack.getCount());
                    String trimmedName = "";
                    String voltage = unlocalizedName.substring(unlocalizedName.lastIndexOf(".") + 1);
                    trimmedName = unlocalizedName.substring(0, unlocalizedName.lastIndexOf("."));

                    this.machineName = trimmedName.substring(trimmedName.lastIndexOf(".") + 1);
                    this.machineTierVoltage = GAEnums.voltageMap.get(voltage) == null ? 0 : GAEnums.voltageMap.get(voltage);
                    this.machineItemStack = wholeItemStack;
                    break;

                }
            }
            return machineName;
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {

            IItemHandlerModifiable importInventory = getInputInventory();
            IItemHandlerModifiable exportInventory = getOutputInventory();
            IMultipleTankHandler importFluids = getInputTank();
            IMultipleTankHandler exportFluids = getOutputTank();

            /*
             * this.numberOfOperations = 0;
             *
             * for (int i = 0; i < numberOfMachines; i++) { if
             * (MetaTileEntity.addItemsToItemHandler(exportInventory, true,
             * recipe.getAllItemOutputs(exportInventory.getSlots())) &&
             * MetaTileEntity.addFluidsToFluidHandler(exportFluids, true,
             * recipe.getFluidOutputs())) {
             *
             *
             * if (recipe.matches(true, importInventory, importFluids)) {
             * numberOfOperations++; } else { break; } } }
             */

            int[] resultOverclock = calculateOverclock(recipe.getEUt(), machineTierVoltage, recipe.getDuration());
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
            int[] resultOverclock = calculateOverclock(recipe.getEUt(), machineTierVoltage, recipe.getDuration());
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
                    // this.setActiveMethod.invoke(this,true);
                }
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
