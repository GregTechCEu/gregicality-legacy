package gregicadditions.machines.multi.impl;

import gregicadditions.machines.multi.nuclear.MetaTileEntityHotCoolantTurbine;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipe;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.ConfigHolder;
import gregtech.common.MetaFluids;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

import static gregicadditions.machines.multi.nuclear.MetaTileEntityHotCoolantTurbine.ABILITY_ROTOR_HOLDER;


public class HotCoolantTurbineWorkableHandler extends HotCoolantRecipeLogic {

    private static final int CYCLE_LENGTH = 230;
    private static final int BASE_ROTOR_DAMAGE = 11;
    private static final int BASE_EU_OUTPUT = 2048;

    private final MetaTileEntityHotCoolantTurbine largeTurbine;
    private int rotorCycleLength = CYCLE_LENGTH;

    public HotCoolantTurbineWorkableHandler(MetaTileEntityHotCoolantTurbine metaTileEntity, HotCoolantRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, 0L);
        this.largeTurbine = metaTileEntity;
    }

    @Override
    public void update() {
        super.update();
        long totalEnergyOutput = getRecipeOutputVoltage();
        if (totalEnergyOutput > 0) {
            energyContainer.get().addEnergy(totalEnergyOutput);
        }
    }

    public FluidStack getFuelStack() {
        if (previousRecipe == null)
            return null;
        FluidStack fuelStack = previousRecipe.getRecipeFluid();
        return fluidTank.get().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
    }

    @Override
    public boolean checkRecipe(HotCoolantRecipe recipe) {
        MetaTileEntityRotorHolderForNuclearCoolant rotorHolder = largeTurbine.getAbilities(ABILITY_ROTOR_HOLDER).get(0);
        if (++rotorCycleLength >= CYCLE_LENGTH) {
            int damageToBeApplied = (int) Math.round(BASE_ROTOR_DAMAGE * rotorHolder.getRelativeRotorSpeed()) + 1;
            if (rotorHolder.applyDamageToRotor(damageToBeApplied, false)) {
                this.rotorCycleLength = 0;
                return true;
            } else return false;
        }
        return true;
    }

    @Override
    public long getMaxVoltage() {
        MetaTileEntityRotorHolderForNuclearCoolant rotorHolder = largeTurbine.getAbilities(ABILITY_ROTOR_HOLDER).get(0);
        if (rotorHolder.hasRotorInInventory()) {
            double rotorEfficiency = rotorHolder.getRotorEfficiency();
            double totalEnergyOutput = (BASE_EU_OUTPUT + getBonusForTurbineType(largeTurbine) * rotorEfficiency);
            return MathHelper.ceil(totalEnergyOutput);
        }
        return BASE_EU_OUTPUT + getBonusForTurbineType(largeTurbine);
    }

    @Override
    protected long startRecipe(HotCoolantRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        addOutputFluids(currentRecipe, fuelAmountUsed);
        return 0L; //energy is added each tick while the rotor speed is >0 RPM
    }

    private void addOutputFluids(HotCoolantRecipe currentRecipe, int fuelAmountUsed) {
        if (largeTurbine.turbineType == MetaTileEntityHotCoolantTurbine.TurbineType.HOT_COOLANT) {
            if (fuelAmountUsed > 0) {
                FluidMaterial material = MetaFluids.getMaterialFromFluid(currentRecipe.getRecipeFluid().getFluid());
                if (material != null) {
                    largeTurbine.exportFluidHandler.fill(material.getFluid(fuelAmountUsed), true);
                }
            }
        }
    }

    private int getBonusForTurbineType(MetaTileEntityHotCoolantTurbine turbine) {
        if (turbine.turbineType == MetaTileEntityHotCoolantTurbine.TurbineType.HOT_COOLANT) {
            return ConfigHolder.steamTurbineBonusOutput * 130 / 100;
        }
        return 1;
    }

    @Override
    public long getRecipeOutputVoltage() {
        MetaTileEntityRotorHolderForNuclearCoolant rotorHolder = largeTurbine.getAbilities(ABILITY_ROTOR_HOLDER).get(0);
        double relativeRotorSpeed = rotorHolder.getRelativeRotorSpeed();
        if (rotorHolder.getCurrentRotorSpeed() > 0 && rotorHolder.hasRotorInInventory()) {
            double rotorEfficiency = rotorHolder.getRotorEfficiency();
            double totalEnergyOutput = (BASE_EU_OUTPUT + getBonusForTurbineType(largeTurbine) * rotorEfficiency) * (relativeRotorSpeed * relativeRotorSpeed);
            return MathHelper.ceil(totalEnergyOutput);
        }
        return 0L;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tagCompound = super.serializeNBT();
        tagCompound.setInteger("CycleLength", rotorCycleLength);
        return tagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.rotorCycleLength = compound.getInteger("CycleLength");
    }

    @Override
    protected boolean shouldVoidExcessiveEnergy() {
        return true;
    }
}
