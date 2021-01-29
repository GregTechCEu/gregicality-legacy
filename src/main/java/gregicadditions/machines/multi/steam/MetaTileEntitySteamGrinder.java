package gregicadditions.machines.multi.steam;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.machines.multi.impl.GAMultiblockAbility;
import gregicadditions.machines.multi.impl.RecipeMapSteamMultiblockController;
import gregicadditions.machines.multi.impl.SteamMultiblockRecipeLogic;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.InventoryUtils;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MetaTileEntitySteamGrinder extends RecipeMapSteamMultiblockController {

    private static final double CONVERSION_RATE = 2.0; // Can add Config value for this if needed.
                                                       // Currently is doing (1mb Steam -> 2EU) to be generous.

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            GAMultiblockAbility.STEAM_IMPORT_ITEMS, GAMultiblockAbility.STEAM_EXPORT_ITEMS, GAMultiblockAbility.STEAM
    };

    public MetaTileEntitySteamGrinder(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MACERATOR_RECIPES, CONVERSION_RATE);
        this.recipeMapWorkable = new SteamGrinderWorkable(this, CONVERSION_RATE);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntitySteamGrinder(metaTileEntityId);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .setAmountAtLeast('L', 14)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.BRONZE_PLATED_BRICKS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.ROCK_CRUSHER_OVERLAY.renderSided(getFrontFacing(), renderState, translation, pipeline);
        if (recipeMapWorkable.isActive())
            Textures.ROCK_CRUSHER_ACTIVE_OVERLAY.renderSided(getFrontFacing(), renderState, translation, pipeline);
    }

    protected class SteamGrinderWorkable extends SteamMultiblockRecipeLogic {

        public SteamGrinderWorkable(RecipeMapSteamMultiblockController tileEntity, double conversionRate) {
            super(tileEntity, tileEntity.recipeMap, tileEntity.getSteamFluidTank(), conversionRate);
        }

        @Override
        protected void trySearchNewRecipe() {
            long maxVoltage = getMaxVoltage(); // Will always be LV voltage
            Recipe currentRecipe = null;
            IItemHandlerModifiable importInventory = getInputInventory();
            boolean dirty = checkRecipeInputsDirty(importInventory, null);

            if(dirty || forceRecipeRecheck) {
                this.forceRecipeRecheck = false;

                currentRecipe = findRecipe(maxVoltage, importInventory, null);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            } else if (previousRecipe != null && previousRecipe.matches(false, importInventory, new FluidTankList(false))) {
                currentRecipe = previousRecipe;
            }

            if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
                setupRecipe(currentRecipe);
            }
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            int currentItemsEngaged = 0;
            final int maxItemsLimit = 8;
            final ArrayList<CountableIngredient> recipeInputs = new ArrayList<>();
            final ArrayList<ItemStack> recipeOutputs = new ArrayList<>();
            int recipeEUt = 0;
            int recipeDuration = 1;
            float speedBonusPercent = 0.0F; // Currently unused

            /* Iterate over input items looking for more items to process until we
             * have touched every item, or are at maximum item capacity
             */
            for (int index = 0; index < inputs.getSlots() && currentItemsEngaged < maxItemsLimit; index ++) {
                final ItemStack currentInputItem = inputs.getStackInSlot(index);

                // Skip slot if empty
                if (currentInputItem.isEmpty())
                    continue;

                // Check recipe for item in slot
                Recipe matchingRecipe = recipeMap.findRecipe(maxVoltage,
                        Collections.singletonList(currentInputItem),
                        Collections.emptyList(), 0);
                CountableIngredient inputIngredient;
                if (matchingRecipe != null) {
                    inputIngredient = matchingRecipe.getInputs().get(0);
                    recipeEUt = matchingRecipe.getEUt();
                    recipeDuration = matchingRecipe.getDuration();
                } else
                    continue;

                // Some error handling, probably unnecessary
                if (inputIngredient == null)
                    throw new IllegalStateException(
                            String.format("Recipe with null ingredient %s", matchingRecipe));

                // Check to see if we have enough output slots
                int itemsLeftUntilMax = (maxItemsLimit - currentItemsEngaged);
                if (itemsLeftUntilMax >= inputIngredient.getCount()) {

                    // Make sure we don't go over maximum of 8 items per craft
                    int recipeMultiplier = Math.min((currentInputItem.getCount() / inputIngredient.getCount()),
                                                    (itemsLeftUntilMax / inputIngredient.getCount()));

                    // Process to see how many slots the output will take
                    ArrayList<ItemStack> temp = new ArrayList<>(recipeOutputs);
                    computeOutputItemStacks(temp, matchingRecipe.getOutputs().get(0), recipeMultiplier);

                    // Check to see if we have output space available for the recipe
                    boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(temp, this.getOutputInventory());
                    if (!canFitOutputs)
                        break;

                    // Create output ItemStack list
                    temp.removeAll(recipeOutputs);
                    recipeOutputs.addAll(temp);

                    // Add ingredients to list of items to process
                    recipeInputs.add(new CountableIngredient(inputIngredient.getIngredient(),
                            inputIngredient.getCount() * recipeMultiplier));

                    currentItemsEngaged += inputIngredient.getCount() * recipeMultiplier;
                }
            }

            // No recipe was found
            if (recipeInputs.isEmpty()) {
                forceRecipeRecheck = true;
                return null;
            }

            return recipeMap.recipeBuilder()
                    .inputsIngredients(recipeInputs)
                    .outputs(recipeOutputs)
                    .EUt(Math.min(32, (int)Math.ceil(recipeEUt * 1.33)))
                    .duration(Math.max(recipeDuration, (int)(recipeDuration * (100.0F / (100.0F + speedBonusPercent)) * 1.5)))
                    .build().getResult();
        }

        private void computeOutputItemStacks(Collection<ItemStack> recipeOutputs, ItemStack outputStack, int recipeAmount) {
            if(!outputStack.isEmpty()) {
                int finalAmount = outputStack.getCount() * recipeAmount;
                int maxCount = outputStack.getMaxStackSize();
                int numStacks = finalAmount / maxCount;
                int remainder = finalAmount % maxCount;

                for(int fullStacks = numStacks; fullStacks > 0; fullStacks--) {
                    ItemStack full = outputStack.copy();
                    full.setCount(maxCount);
                    recipeOutputs.add(full);
                }

                if (remainder > 0) {
                    ItemStack partial = outputStack.copy();
                    partial.setCount(remainder);
                    recipeOutputs.add(partial);
                }
            }
        }
    }
}
