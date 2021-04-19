package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.override.MetaTileEntityElectricBlastFurnace;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import static gregicadditions.GAMaterials.HastelloyN;

public class TileEntityVolcanus extends MetaTileEntityElectricBlastFurnace {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    private static final int DURATION_DECREASE_FACTOR = GAConfig.multis.volcanus.durationDecreasePercentage;

    private static final int ENERGY_DECREASE_FACTOR = GAConfig.multis.volcanus.energyDecreasePercentage;

    private final DecimalFormat formatter = new DecimalFormat("#0.0");

    public TileEntityVolcanus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.recipeMapWorkable = new VolcanusRecipeLogic(this, ENERGY_DECREASE_FACTOR, DURATION_DECREASE_FACTOR, 100, 1);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityVolcanus(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XXX")
                .aisle("XSX", "CCC", "CCC", "XXX")
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', heatingCoilPredicate().or(heatingCoilPredicate2()))
                .where('#', isAirPredicate())
                .build();
    }

    @Override
    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(HastelloyN);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.HastelloyN);
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

    public class VolcanusRecipeLogic extends LargeSimpleRecipeMapMultiblockController.LargeSimpleMultiblockRecipeLogic {

        public VolcanusRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage, stack);
        }

        @Override
        protected boolean drawEnergy(int recipeEUt) {
            int drain = 10 * getOverclockingTier(getMaxVoltage());
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
    }
}
