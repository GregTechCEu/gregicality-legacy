package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.multi.override.MetaTileEntityVacuumFreezer;
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

import static gregicadditions.GAMaterials.IncoloyMA956;

public class TileEntityCryogenicFreezer extends MetaTileEntityVacuumFreezer {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    private static final int ENERGY_DECREASE_FACTOR = GAConfig.multis.cryogenicFreezer.energyDecreasePercentage;

    private static final int DURATION_DECREASE_FACTOR = GAConfig.multis.cryogenicFreezer.durationDecreasePercentage;

    private final DecimalFormat formatter = new DecimalFormat("#0.0");

    public TileEntityCryogenicFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.recipeMapWorkable = new CryogenicFreezerRecipeLogic(this, ENERGY_DECREASE_FACTOR, DURATION_DECREASE_FACTOR, 100, 1);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityCryogenicFreezer(metaTileEntityId);
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


    @Override
    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(IncoloyMA956);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.IncoloyMA956);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.cryogenic_freezer.description"));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.2", formatter.format(GAConfig.multis.cryogenicFreezer.energyDecreasePercentage / 100.0)));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.3", formatter.format(GAConfig.multis.cryogenicFreezer.durationDecreasePercentage / 100.0)));
    }


    public class CryogenicFreezerRecipeLogic extends LargeSimpleRecipeMapMultiblockController.LargeSimpleMultiblockRecipeLogic {

        public CryogenicFreezerRecipeLogic(RecipeMapMultiblockController tileEntity, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
            super(tileEntity, EUtPercentage, durationPercentage, chancePercentage, stack);
        }


        @Override
        protected boolean drawEnergy(int recipeEUt) {
            int drain = 10 * getOverclockingTier(getMaxVoltage());
            long resultEnergy = this.getEnergyStored() - (long) recipeEUt;
            Optional<IFluidTank> fluidTank =
                    getInputFluidInventory().getFluidTanks().stream()
                            .filter(iFluidTank -> iFluidTank.getFluid() != null)
                            .filter(iFluidTank -> iFluidTank.getFluid().isFluidEqual(GAMaterials.Cryotheum.getFluid(drain)))
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
