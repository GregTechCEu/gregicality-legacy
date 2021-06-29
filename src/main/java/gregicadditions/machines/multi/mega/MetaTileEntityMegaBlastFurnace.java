package gregicadditions.machines.multi.mega;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.multi.override.MetaTileEntityElectricBlastFurnace;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.recipeproperties.BlastTemperatureProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.api.util.InventoryUtils;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.*;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;

public class MetaTileEntityMegaBlastFurnace extends MegaMultiblockRecipeMapController {

    protected int blastFurnaceTemperature;
    private int bonusTemperature;

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY,
            GregicAdditionsCapabilities.MAINTENANCE_HATCH, MultiblockAbility.EXPORT_FLUIDS
    };

    public MetaTileEntityMegaBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, BLAST_RECIPES, 100, 100, 100, 0, true, true, true);
        this.recipeMapWorkable = new MegaBlastFurnaceRecipeLogic(this, 100, 100, 100);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMegaBlastFurnace(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("XXXXXXXSXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX")
                .aisle("GGGGGGGGGGGGGGG", "GCCCCCCCCCCCCCG", "GCP###ppp###PCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCp#########pCG", "GCp#########pCG", "GCp#########pCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCP###ppp###PCG", "GCCCCCCCCCCCCCG", "GGGGGGGGGGGGGGG").setRepeatable(18)
                .aisle("XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXggggXXXggggXX", "XXggggXmXggggXX", "XXggggXXXggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX")
                .setAmountAtLeast('L', 100)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate().or(MetaTileEntityElectricBlastFurnace.heatingCoilPredicate2()))
                .where('P', frameworkPredicate().or(frameworkPredicate2()))
                .where('p', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS)))
                .where('g', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('m', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH))
                .where('#', isAirPredicate())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        blastFurnaceTemperature = context.getOrDefault("blastFurnaceTemperature", 0);

        int energyTier = GAUtility.getTierByVoltage(getEnergyContainer().getInputVoltage());
        this.bonusTemperature = Math.max(0, 100 * (energyTier - 2));
        this.blastFurnaceTemperature += this.bonusTemperature;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
        this.bonusTemperature = 0;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int recipeRequiredTemp = recipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0); //todo why is this 0
        return this.blastFurnaceTemperature >= recipeRequiredTemp && super.checkRecipe(recipe, consumeIfSuccess);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed() && !hasProblems()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature));
            textList.add(new TextComponentTranslation("gtadditions.multiblock.blast_furnace.additional_temperature", bonusTemperature));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_blast_logic.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_blast_logic.tooltip.2"));
    }

    public static final IBlockState casingState = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);

    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
    }

    protected int getBlastFurnaceTemperature() {
        return this.blastFurnaceTemperature;
    }


    protected static class MegaBlastFurnaceRecipeLogic extends MegaMultiblockRecipeLogic {

        private static final double LOG_4 = Math.log(4);

        public MegaBlastFurnaceRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage);
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            long maxVoltage = ((MegaMultiblockRecipeMapController) metaTileEntity).maxVoltage;
            int[] resultOverclock = calculateOverclock(recipe.getEUt(), maxVoltage, recipe.getDuration());
            this.progressTime = 1;
            this.setMaxProgress(resultOverclock[1]);
            this.recipeEUt = resultOverclock[0];
            this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
            int tier = this.getMachineTierForRecipe(recipe);
            this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(this.getOutputInventory().getSlots(), this.random, tier));
            if (this.wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
            } else {
                this.setActive(true);
            }
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            int currentTemp = ((MetaTileEntityMegaBlastFurnace) metaTileEntity).getBlastFurnaceTemperature();
            if (recipe != null && recipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0) <= currentTemp)
                return createRecipe(maxVoltage, inputs, fluidInputs, recipe);
            return null;
        }

        @Override
        protected Recipe createRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe matchingRecipe) {
            long EUt;
            int duration;
            int minMultiplier = Integer.MAX_VALUE;
            int recipeTemp = matchingRecipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0);
            int tier = getOverclockingTier(maxVoltage);

            Set<ItemStack> countIngredients = new HashSet<>();
            if (matchingRecipe.getInputs().size() != 0) {
                this.findIngredients(countIngredients, inputs);
                minMultiplier = Math.min(MAX_ITEMS_LIMIT, this.getMinRatioItem(countIngredients, matchingRecipe, MAX_ITEMS_LIMIT));
            }

            Map<String, Integer> countFluid = new HashMap<>();
            if (matchingRecipe.getFluidInputs().size() != 0) {

                this.findFluid(countFluid, fluidInputs);
                minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, matchingRecipe, MAX_ITEMS_LIMIT));
            }

            if (minMultiplier == Integer.MAX_VALUE) {
                GALog.logger.error("Cannot calculate ratio of items for the mega blast furnace");
                return null;
            }

            EUt = matchingRecipe.getEUt();
            duration = matchingRecipe.getDuration();
            int currentTemp = ((MetaTileEntityMegaBlastFurnace) this.metaTileEntity).getBlastFurnaceTemperature();

            // Get amount of 900Ks over the recipe temperature
            int bonusAmount = Math.max(0, (currentTemp - recipeTemp) / 900);

            // Apply EUt discount for every 900K above the base recipe temperature
            EUt *= Math.pow(0.95, bonusAmount);

            // Get parallel recipes to run: [0, 256]
            int multiplier = Math.min(minMultiplier, (int) (getMaxVoltage() / EUt));

            // Change EUt to be the parallel amount
            EUt *= multiplier;

            // Modify bonus amount to prefer parallel logic
            bonusAmount = (int) Math.max(0, bonusAmount - Math.log(multiplier) / LOG_4 * 2);

            // Apply MEBF duration discount
            duration *= 0.5;

            // Apply Super Overclocks for every 1800k above the base recipe temperature
            for (int i = bonusAmount; EUt <= GAValues.V[tier - 1] && duration >= 3 && i > 0; i--) {
                if (i % 2 == 0) {
                    EUt *= 4;
                    duration *= 0.25;
                }
            }

            // Apply Regular Overclocking
            while (duration >= 3 && EUt <= GAValues.V[tier - 1]) {
                EUt *= 4;
                duration /= 2.8;
            }

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, matchingRecipe, multiplier);

            // determine if there is enough room in the output to fit all of this
            boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(outputI, this.getOutputInventory());
            // if there isn't, we can't process this recipe.
            if (!canFitOutputs)
                return null;

            if (duration < 3)
                duration = 3;

            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) Math.max(1, EUt * this.getEUtPercentage() / 100))
                    .duration((int) Math.max(3, duration * (this.getDurationPercentage() / 100.0)));

            copyChancedItemOutputs(newRecipe, matchingRecipe, multiplier);

            return newRecipe.build().getResult();
        }
    }
}
