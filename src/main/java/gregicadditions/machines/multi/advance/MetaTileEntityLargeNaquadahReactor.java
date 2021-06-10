package gregicadditions.machines.multi.advance;

import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.multi.electric.generator.FueledMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

import static gregicadditions.client.ClientHandler.NAQUADRIA_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.Naquadria;

public class MetaTileEntityLargeNaquadahReactor extends FueledMultiblockController { //todo generator maintenance

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.OUTPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };


    public MetaTileEntityLargeNaquadahReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.NAQUADAH_REACTOR_FUELS, GAValues.V[GAValues.UV]);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeNaquadahReactor(metaTileEntityId);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new NaquadahReactorWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack tritiumStack = importFluidHandler.drain(Materials.Tritium.getFluid(Integer.MAX_VALUE), false);
            FluidStack oxygenStack = importFluidHandler.drain(Materials.Oxygen.getPlasma(Integer.MAX_VALUE), false);
            FluidStack fuelStack = ((NaquadahReactorWorkableHandler) workableHandler).getFuelStack();
            int tritiumAmount = tritiumStack == null ? 0 : tritiumStack.amount;
            int oxygenAmount = oxygenStack == null ? 0 : oxygenStack.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            ITextComponent fuelName = new TextComponentTranslation(fuelAmount == 0 ? "gregtech.fluid.empty" : fuelStack.getUnlocalizedName());
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_naquadah_reactor.tritium_amount", tritiumAmount));
            textList.add(new TextComponentTranslation("gregtech.multiblock.diesel_engine.fuel_amount", fuelAmount, fuelName));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_naquadah_reactor.oxygen_amount", oxygenAmount));
            textList.add(new TextComponentTranslation(oxygenAmount >= 2 ? "gregtech.multiblock.large_naquadah_reactor.oxygen_boosted" : "gregtech.multiblock.large_naquadah_reactor.supply_tritium_to_boost"));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.3"));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#CCC#", "#CGC#", "#CCC#", "##C##", "##C##", "#CCC#", "#CGC#", "#####")
                .aisle("CCCCC", "CPAPC", "CgAgC", "#PAP#", "#PAP#", "CgAgC", "CPAPC", "#CCC#")
                .aisle("CCCCC", "GAFAG", "CAFAC", "CAFAC", "CAFAC", "CAFAC", "GAFAG", "#CmC#")
                .aisle("CCCCC", "CPAPC", "CgAgC", "#PAP#", "#PAP#", "CgAgC", "CPAPC", "#CCC#")
                .aisle("#CCC#", "#CSC#", "#CCC#", "##C##", "##C##", "#CCC#", "#CGC#", "#####")
                .setAmountAtLeast('L', 45)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('g', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING)))
                .where('F', statePredicate(MetaBlocks.FRAMES.get(Naquadria).getDefaultState()))
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('m', abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_HATCH).or(statePredicate(getCasingState())))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return NAQUADRIA_CASING;
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA);
    }

    public static class NaquadahReactorWorkableHandler extends FuelRecipeLogic {

        private final int maxCycleLength = 20;
        private int currentCycle = 0;
        private boolean isUsingOxygen = false;

        public NaquadahReactorWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                           Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
            super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
        }

        public FluidStack getFuelStack() {
            if (previousRecipe == null)
                return null;
            FluidStack fuelStack = previousRecipe.getRecipeFluid();
            return fluidTank.get().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
        }

        @Override
        protected boolean checkRecipe(FuelRecipe recipe) {
            FluidStack tritiumStack = Materials.Tritium.getFluid(1000);
            FluidStack drainStack = fluidTank.get().drain(tritiumStack, false);
            return (drainStack != null && drainStack.amount >= 2) || currentCycle < maxCycleLength;
        }

        @Override
        protected int calculateFuelAmount(FuelRecipe currentRecipe) {
            FluidStack oxygenStack = Materials.Oxygen.getPlasma(50);
            FluidStack drainOxygenStack = fluidTank.get().drain(oxygenStack, false);
            this.isUsingOxygen = drainOxygenStack != null && drainOxygenStack.amount >= 2;
            return super.calculateFuelAmount(currentRecipe) * (isUsingOxygen ? 2 : 1);
        }

        @Override
        protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
            if (this.currentCycle >= maxCycleLength) {
                FluidStack tritiumStack = Materials.Tritium.getFluid(1000);
                fluidTank.get().drain(tritiumStack, true);
                this.currentCycle = 0;
            } else this.currentCycle++;
            if (isUsingOxygen) {
                FluidStack oxygenStack = Materials.Oxygen.getPlasma(50);
                fluidTank.get().drain(oxygenStack, true);
            }
            return maxVoltage * (isUsingOxygen ? 4 : 1);
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setInteger("Cycle", currentCycle);
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.currentCycle = compound.getInteger("Cycle");
        }

    }

}
