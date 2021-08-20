package gregicadditions.machines.multi;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.BlastTemperatureProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregicadditions.client.ClientHandler.ZIRCONIUM_CARBIDE_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityAlloyBlastFurnace extends GARecipeMapMultiblockController {

    private int blastFurnaceTemperature;
    private int bonusTemperature;

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY,
            GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    public TileEntityAlloyBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.BLAST_ALLOY_RECIPES, true, true, true);
        this.recipeMapWorkable = new AlloyBlastFurnaceWorkable(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityAlloyBlastFurnace(metaTileEntityId);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.alloy_blast_furnace.tooltip.1"));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#ccc#", "#ccc#", "#XXX#")
                .aisle("XXXXX", "cCCCc", "cCCCc", "XXXXX")
                .aisle("XXXXX", "cCACc", "cCACc", "XXmXX")
                .aisle("XXXXX", "cCCCc", "cCCCc", "XXXXX")
                .aisle("#XSX#", "#ccc#", "#ccc#", "#XXX#")
                .setAmountAtLeast('L', 14)
                .where('L', statePredicate(getCasingState()))
                .where('S', selfPredicate())
                .where('C', heatingCoilPredicate().or(heatingCoilPredicate2()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('c', statePredicate(getCasingState2()))
                .where('m', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH))
                .where('#', (tile) -> true)
                .where('A', isAirPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockWireCoil))
                return false;
            BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
            BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
                return false;

            int blastFurnaceTemperature = coilType.getCoilTemperature();
            int currentTemperature = blockWorldState.getMatchContext().getOrPut("blastFurnaceTemperature", blastFurnaceTemperature);

            BlockWireCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("coilType", coilType);

            return currentTemperature == blastFurnaceTemperature && coilType.equals(currentCoilType);
        };
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate2() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAHeatingCoil))
                return false;
            GAHeatingCoil blockWireCoil = (GAHeatingCoil) blockState.getBlock();
            GAHeatingCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName()))
                return false;
            int blastFurnaceTemperature = coilType.getCoilTemperature();
            int currentTemperature = blockWorldState.getMatchContext().getOrPut("blastFurnaceTemperature", blastFurnaceTemperature);

            GAHeatingCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("gaCoilType", coilType);

            return currentTemperature == blastFurnaceTemperature && coilType.equals(currentCoilType);
        };
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.ZIRCONIUM_CARBIDE);
    }

    protected IBlockState getCasingState2() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ZIRCONIUM_CARBIDE_CASING;
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
        int recipeRequiredTemp = recipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0);
        return this.blastFurnaceTemperature >= recipeRequiredTemp;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }


    protected int getBlastFurnaceTemperature() {
        return this.blastFurnaceTemperature;
    }

    protected static class AlloyBlastFurnaceWorkable extends GAMultiblockRecipeLogic {

        public AlloyBlastFurnaceWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            int[] resultOverclock = this.calculateOverclock(recipe.getEUt(), getMaxVoltage(), recipe.getDuration(), recipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0));
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

        protected int[] calculateOverclock(int EUt, long voltage, int duration, int recipeTemp) {
            int numMaintenanceProblems = (this.metaTileEntity instanceof GARecipeMapMultiblockController) ?
                    ((GARecipeMapMultiblockController) metaTileEntity).getNumProblems() : 0;

            double maintenanceDurationMultiplier = 1.0 + (0.1 * numMaintenanceProblems);
            int durationModified = (int) (duration * maintenanceDurationMultiplier);

            if (!allowOverclocking) {
                return new int[]{EUt, durationModified};
            }
            boolean negativeEU = EUt < 0;


            int bonusAmount = Math.max(0, ((TileEntityAlloyBlastFurnace) metaTileEntity).getBlastFurnaceTemperature() - recipeTemp) / 900;

            // Apply EUt discount for every 900K above the base recipe temperature
            EUt *= Math.pow(0.95, bonusAmount);

            int tier = getOverclockingTier(voltage);
            if (GAValues.V[tier] <= EUt || tier == 0)
                return new int[]{EUt, durationModified};
            if (negativeEU)
                EUt = -EUt;
            if (EUt <= 16) {
                int multiplier = EUt <= 8 ? tier : tier - 1;
                int resultEUt = EUt * (1 << multiplier) * (1 << multiplier);
                int resultDuration = durationModified / (1 << multiplier);
                previousRecipeDuration = resultDuration;
                return new int[]{negativeEU ? -resultEUt : resultEUt, resultDuration};
            } else {
                int resultEUt = EUt;
                double resultDuration = durationModified;
                previousRecipeDuration = (int) resultDuration;

                // Do not overclock further if duration is already too small
                // Apply Super Overclocks for every 1800k above the base recipe temperature
                for (int i = bonusAmount; resultEUt <= GAValues.V[tier - 1] && resultDuration >= 3 && i > 0; i--) {
                    if (i % 2 == 0) {
                        resultEUt *= 4;
                        resultDuration *= 0.25;
                    }
                }

                // Do not overclock further if duration is already too small
                // Apply Regular Overclocking
                while (resultDuration >= 3 && resultEUt <= GAValues.V[tier - 1]) {
                    resultEUt *= 4;
                    resultDuration /= 2.8;
                }

                if (resultDuration < 3)
                    resultDuration = 3;

                return new int[]{negativeEU ? -resultEUt : resultEUt, (int) Math.ceil(resultDuration)};
            }
        }
    }
}
