package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.override.MetaTileEntityElectricBlastFurnace;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import static gregicadditions.client.ClientHandler.*;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class MetaTileEntityVolcanus extends MetaTileEntityElectricBlastFurnace {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};

    private final DecimalFormat formatter = new DecimalFormat("#0.00");

    public MetaTileEntityVolcanus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.recipeMapWorkable = new VolcanusRecipeLogic(this, GAConfig.multis.volcanus.energyDecreasePercentage, GAConfig.multis.volcanus.durationDecreasePercentage, 100, 0);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityVolcanus(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XMX")
                .aisle("XSX", "CCC", "CCC", "XXX")
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('M', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH))
                .where('C', heatingCoilPredicate().or(heatingCoilPredicate2()))
                .where('#', isAirPredicate())
                .build();
    }

    public IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return HASTELLOY_N_CASING;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.blastFurnaceTemperature += 600;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description"));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.2", formatter.format(GAConfig.multis.volcanus.energyDecreasePercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.3", formatter.format(GAConfig.multis.volcanus.durationDecreasePercentage / 100.0)));
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    public class VolcanusRecipeLogic extends LargeSimpleRecipeMapMultiblockController.LargeSimpleMultiblockRecipeLogic {

        public VolcanusRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage, stack);
        }

        @Override
        protected boolean drawEnergy(int recipeEUt) {
            int drain = 10 + getOverclockingTier(getMaxVoltage());
            long resultEnergy = this.getEnergyStored() - (long) recipeEUt;
            Optional<IFluidTank> fluidTank =
                    getInputFluidInventory().getFluidTanks().stream()
                            .filter(iFluidTank -> iFluidTank.getFluid() != null)
                            .filter(iFluidTank -> iFluidTank.getFluid().isFluidEqual(GAMaterials.Pyrotheum.getFluid(drain)))
                            .findFirst();
            if (fluidTank.isPresent()) {
                IFluidTank tank = fluidTank.get();
                if (resultEnergy >= 0L && resultEnergy <= this.getEnergyCapacity() && tank.getCapacity() > 1) {
                    tank.drain(drain, true);
                    this.getEnergyContainer().changeEnergy(-recipeEUt);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            int[] resultOverclock = this.calculateOverclock(recipe.getEUt(), getMaxVoltage(), recipe.getDuration(), recipe.getIntegerProperty("blast_furnace_temperature"));
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

                if (!(this.metaTileEntity instanceof MetaTileEntityElectricBlastFurnace)) {
                    return calculateOverclock(EUt, voltage, duration);
                }

                int bonusAmount = Math.max(0, ((MetaTileEntityVolcanus) metaTileEntity).getBlastFurnaceTemperature() - recipeTemp) / 900;

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

                // Apply EUt discount for every 900K above the base recipe temperature
                resultEUt *= Math.pow(0.95, bonusAmount);

                return new int[]{negativeEU ? -resultEUt : resultEUt, (int) Math.ceil(resultDuration)};
            }
        }
    }
}
