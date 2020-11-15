package gregicadditions.machines.multi.advance.hyper;

import gregicadditions.GAMaterials;
import gregicadditions.item.*;
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
import gregtech.common.metatileentities.multi.electric.generator.FueledMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

import static gregtech.api.unification.material.Materials.Naquadria;

public class HyperReactor extends FueledMultiblockController {


    public HyperReactor(ResourceLocation metaTileEntityId, long maxVoltage) {
        super(metaTileEntityId, GARecipeMaps.HYPER_REACTOR_FUELS, maxVoltage);
        this.maxVoltage = maxVoltage;
    }

    long maxVoltage;

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new HyperReactor(metaTileEntityId, this.maxVoltage);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new WorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack oxygenPlasma = importFluidHandler.drain(Materials.Oxygen.getPlasma(Integer.MAX_VALUE), false);
            FluidStack cryotheum = importFluidHandler.drain(GAMaterials.Cryotheum.getFluid(Integer.MAX_VALUE), false);
            FluidStack fuelStack = ((WorkableHandler) workableHandler).getFuelStack();
            boolean isBoosted = ((WorkableHandler) workableHandler).isBoosted();
            int oxygenPlasmaAmount = oxygenPlasma == null ? 0 : oxygenPlasma.amount;
            int cryotheumAmount = cryotheum == null ? 0 : cryotheum.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.oxygen_plasma_amount", oxygenPlasmaAmount));
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.cryotheum_amount", cryotheumAmount));
            textList.add(new TextComponentString(fuelStack != null ? String.format("%dmb %s", fuelAmount, fuelStack.getLocalizedName()) : ""));
            textList.add(new TextComponentTranslation(isBoosted ? "gregtech.multiblock.large_rocket_engine.boost" : "").setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("Max Voltage: " + maxVoltage);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CCCCC", "G#H#G", "GHHHG", "G#H#G", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CCSCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY)).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
                .where('T', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_UV)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIUM_GLASS)))
                .where('H', statePredicate(GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE)))
                .where('#', isAirPredicate())
                .setAmountAtLeast('c', 55)
                .where('c', statePredicate(getCasingState()))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Naquadria);
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Naquadria);
    }

    static class WorkableHandler extends FuelRecipeLogic {

        private boolean boosted = false;


        public WorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
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
            return true;
        }

        @Override
        protected int calculateFuelAmount(FuelRecipe currentRecipe) {
            FluidStack oxygenStack = Materials.Oxygen.getPlasma(2);
            FluidStack drainOxygenStack = fluidTank.get().drain(oxygenStack, false);
            FluidStack cryotheumStack = GAMaterials.Cryotheum.getFluid(250);
            FluidStack drainCryotheumStack = fluidTank.get().drain(cryotheumStack, false);
            this.boosted = drainOxygenStack != null && drainOxygenStack.amount >= 2 && drainCryotheumStack != null && drainCryotheumStack.amount >= 250;
            return super.calculateFuelAmount(currentRecipe) * (boosted ? 2 : 1);
        }

        @Override
        protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
            if (boosted) {
                FluidStack oxygenStack = Materials.Oxygen.getPlasma(2);
                FluidStack cryotheumStack = GAMaterials.Cryotheum.getFluid(250);
                fluidTank.get().drain(oxygenStack, true);
                fluidTank.get().drain(cryotheumStack, true);
            }
            return maxVoltage * (boosted ? 3 : 1);
        }

        public boolean isBoosted() {
            return boosted;
        }
    }

}
