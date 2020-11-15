package gregicadditions.machines.multi.advance.hyper;

import gregicadditions.GAMaterials;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.item.GATransparentCasing;
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
import gregtech.common.blocks.MetaBlocks;
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

public class HyperReactorUEV extends FueledMultiblockController {


    public HyperReactorUEV(ResourceLocation metaTileEntityId, long maxVoltage) {
        super(metaTileEntityId, GARecipeMaps.HYPER_REACTOR_FUELS, maxVoltage);
        this.maxVoltage = maxVoltage;
    }

    long maxVoltage;

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new gregicadditions.machines.multi.advance.hyper.HyperReactorUEV(metaTileEntityId, this.maxVoltage);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new WorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack oxygenPlasma = importFluidHandler.drain(Materials.Oxygen.getPlasma(Integer.MAX_VALUE), false);
            FluidStack cryotheum = importFluidHandler.drain(GAMaterials.SupercooledCryotheum.getFluid(Integer.MAX_VALUE), false);
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
                .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCCCC###", "###CCCCC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C####H####C", "G###HHH###G", "G##HHHHH##G", "G###HHH###G", "C####H####C", "#C#######C#", "##C#####C##", "###CCCCC###")
                .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCCCC###", "###CCSCC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY)).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
                .where('#', (tile) -> true)
                .where('S', selfPredicate())
                .where('F', statePredicate(MetaBlocks.FRAMES.get(Naquadria).getDefaultState()))
                .where('H', statePredicate(GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE_3)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIUM_GLASS)))
                .setAmountAtLeast('c', 250)
                .where('c', statePredicate(getCasingState()))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.HYPER_CASING_2;
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CASING_2);
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
            FluidStack plasmaStack = GAMaterials.ElectronDegenerateRheniumPlasma.getFluid(2);
            FluidStack drainPlasmaStack = fluidTank.get().drain(plasmaStack, false);
            this.boosted = drainPlasmaStack != null && drainPlasmaStack.amount >= 2;
            return super.calculateFuelAmount(currentRecipe) * (boosted ? 2 : 1);
        }

        @Override
        protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
            if (boosted) {
                FluidStack plasmaStack = GAMaterials.ElectronDegenerateRheniumPlasma.getFluid(2);
                fluidTank.get().drain(plasmaStack, true);
            }
            return maxVoltage * (boosted ? 3 : 1);
        }

        public boolean isBoosted() {
            return boosted;
        }
    }
}
