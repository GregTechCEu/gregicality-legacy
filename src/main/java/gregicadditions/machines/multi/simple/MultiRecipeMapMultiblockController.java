package gregicadditions.machines.multi.simple;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAUtility;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IMultiRecipe;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.util.InventoryUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.IntStream;

import static gregicadditions.capabilities.MultiblockDataCodes.IS_TAPED;
import static gregicadditions.capabilities.MultiblockDataCodes.RECIPE_MAP_INDEX;


/**
 * To be used for multiblocks that can swap between multiple different RecipeMaps
 */
public abstract class MultiRecipeMapMultiblockController extends LargeSimpleRecipeMapMultiblockController implements IMultiRecipe {

    protected RecipeMap<?>[] recipeMaps; // array of possible recipes, specific to each multi
    private int recipeMapIndex; // index of the current selected recipe

    public MultiRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack, RecipeMap<?>[] recipeMaps) {
        super(metaTileEntityId, recipeMap, EUtPercentage, durationPercentage, chancePercentage, stack);
        this.recipeMaps = recipeMaps;
        this.recipeMapWorkable = new MultiRecipeMapMultiblockRecipeLogic(this, EUtPercentage, durationPercentage, chancePercentage, stack, recipeMaps);
        this.recipeMapIndex = 0;
    }

    public MultiRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack, RecipeMap<?>[] recipeMaps, boolean canDistinct, boolean hasMuffler, boolean hasMaintenance) {
        super(metaTileEntityId, recipeMap, EUtPercentage, durationPercentage, chancePercentage, stack, canDistinct, hasMuffler, hasMaintenance);
        this.recipeMaps = recipeMaps;
        this.recipeMapWorkable = new MultiRecipeMapMultiblockRecipeLogic(this, EUtPercentage, durationPercentage, chancePercentage, stack, recipeMaps);
        this.recipeMapIndex = 0;
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!getWorld().isRemote) {
            boolean isEmpty = IntStream.range(0, getInputInventory().getSlots())
                    .mapToObj(i -> getInputInventory().getStackInSlot(i))
                    .allMatch(ItemStack::isEmpty);
            if (!isEmpty) {
                return false;
            }

            int index;
            if (playerIn.isSneaking()) // cycle recipemaps backwards
                index = (recipeMapIndex - 1 < 0 ? recipeMaps.length - 1 : recipeMapIndex - 1) % recipeMaps.length;
            else // cycle recipemaps forwards
                index = (recipeMapIndex + 1) % recipeMaps.length;

            setRecipeMapIndex(index);
        }

        return true; // return true here on the client to keep the GUI closed
    }

    public abstract OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex);

    public RecipeMap<?>[] getRecipeMaps() {
        return recipeMaps;
    }

    public int getRecipeMapIndex() {
        return recipeMapIndex;
    }

    public void setRecipeMapIndex(int index) {
        this.recipeMapIndex = index;
        if (!getWorld().isRemote) {
            writeCustomData(RECIPE_MAP_INDEX, buf -> buf.writeInt(index));
            markDirty();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("RecipeMapIndex", new NBTTagInt(recipeMapIndex));
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        recipeMapIndex = data.getInteger("RecipeMapIndex");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeByte(recipeMapIndex);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        recipeMapIndex = buf.readByte();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == RECIPE_MAP_INDEX) {
            recipeMapIndex = buf.readInt();
            scheduleRenderUpdate();
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        T capabilityResult = super.getCapability(capability, side);
        if (capabilityResult == null && capability == GregicAdditionsCapabilities.MULTI_RECIPE_CAPABILITY) {
            return (T) this;
        }
        return capabilityResult;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed() && !hasProblems())
            textList.add(new TextComponentTranslation("gregtech.multiblock.recipe", new TextComponentTranslation("recipemap." + this.recipeMaps[this.recipeMapIndex].getUnlocalizedName() + ".name")
                    .setStyle(new Style().setColor(TextFormatting.AQUA))));
    }

    @SideOnly(Side.CLIENT)
    public String recipeMapsToString() {
        String recipeMapsString = "";
        for(int i = 0; i < recipeMaps.length; i++) {
            recipeMapsString += recipeMaps[i].getLocalizedName();
            if(recipeMaps.length - 1 != i)
                recipeMapsString += ", "; // For delimiting
        }
        return recipeMapsString;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMapsToString()));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.2", formatter.format(this.EUtPercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.3", formatter.format(this.durationPercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.4", this.stack));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.5", this.chancePercentage));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getRecipeMapOverlay(recipeMapIndex).render(renderState, translation, pipeline, getFrontFacing(), isActive());
    }

    public static class MultiRecipeMapMultiblockRecipeLogic extends LargeSimpleMultiblockRecipeLogic {

        private final RecipeMap<?>[] recipeMaps;

        public MultiRecipeMapMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack, RecipeMap<?>[] recipeMaps) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage, stack);
            this.recipeMaps = recipeMaps;
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            MultiRecipeMapMultiblockController metaTileEntity = (MultiRecipeMapMultiblockController) getMetaTileEntity();
            int recipeMapIndex = metaTileEntity.getRecipeMapIndex();

            // use the current recipemap for recipe finding
            Recipe recipe = this.recipeMaps[recipeMapIndex].findRecipe(maxVoltage, inputs, fluidInputs, this.getMinTankCapacity(this.getOutputTank()));

            if (recipe != null) {
                return createRecipe(maxVoltage, inputs, fluidInputs, recipe);
            }
            return null;
        }

        @Override
        protected Recipe createRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe matchingRecipe) {
            int maxItemsLimit = this.getStack();
            int EUt;
            int duration;
            int currentTier = getOverclockingTier(maxVoltage);
            int tierNeeded;
            int minMultiplier = Integer.MAX_VALUE;

            tierNeeded = Math.max(1, GAUtility.getTierByVoltage(matchingRecipe.getEUt()));
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
                GALog.logger.error("Cannot calculate ratio of items for multi-recipe multiblocks");
                return null;
            }

            EUt = matchingRecipe.getEUt();
            duration = matchingRecipe.getDuration();

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, matchingRecipe, minMultiplier);

            MultiRecipeMapMultiblockController metaTileEntity = (MultiRecipeMapMultiblockController) getMetaTileEntity();

            // determine if there is enough room in the output to fit all of this
            boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(outputI, metaTileEntity.getOutputInventory());
            // if there isn't, we can't process this recipe.
            if (!canFitOutputs)
                return null;

            // Get the currently selected RecipeMap
            int recipeMapIndex = metaTileEntity.getRecipeMapIndex();

            // use the current recipemap for recipe creation
            RecipeBuilder<?> newRecipe = this.recipeMaps[recipeMapIndex].recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) Math.max(1, EUt * this.getEUtPercentage() / 100))
                    .duration((int) Math.max(3, duration * (this.getDurationPercentage() / 100.0)));

            copyChancedItemOutputs(newRecipe, matchingRecipe, minMultiplier);

            return newRecipe.build().getResult();
        }
    }
}
