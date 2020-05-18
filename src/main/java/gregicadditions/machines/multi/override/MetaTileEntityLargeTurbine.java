package gregicadditions.machines.multi.override;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.ConfigHolder;
import gregtech.common.MetaFluids;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityRotorHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class MetaTileEntityLargeTurbine extends gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine {

    public MetaTileEntityLargeTurbine(ResourceLocation metaTileEntityId, TurbineType turbineType) {
        super(metaTileEntityId, turbineType);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeTurbine(metaTileEntityId, turbineType);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new LargeTurbineWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler);
    }

    public class LargeTurbineWorkableHandler extends gregtech.common.metatileentities.multi.electric.generator.LargeTurbineWorkableHandler {

        private static final int BASE_EU_OUTPUT = 2048;

        public LargeTurbineWorkableHandler(gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank) {
            super(metaTileEntity, recipeMap, energyContainer, fluidTank);
        }

        @Override
        protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
            addOutputFluids(currentRecipe, fuelAmountUsed);
            return 0L; //energy is added each tick while the rotor speed is >0 RPM
        }

        private void addOutputFluids(FuelRecipe currentRecipe, int fuelAmountUsed) {
            if (MetaTileEntityLargeTurbine.this.turbineType.toString().equals(TurbineType.STEAM.toString()) ||
                    MetaTileEntityLargeTurbine.this.turbineType.toString().equals("STEAM_OVERRIDE")) {
                int waterFluidAmount = fuelAmountUsed / 15;
                if (waterFluidAmount > 0) {
                    FluidStack waterStack = Materials.Water.getFluid(waterFluidAmount);
                    MetaTileEntityLargeTurbine.this.exportFluidHandler.fill(waterStack, true);
                }
            } else if (MetaTileEntityLargeTurbine.this.turbineType.toString().equals(TurbineType.PLASMA.toString()) ||
                    MetaTileEntityLargeTurbine.this.turbineType.toString().equals("PLASMA_OVERRIDE")) {
                FluidMaterial material = MetaFluids.getMaterialFromFluid(currentRecipe.getRecipeFluid().getFluid());
                if (material != null) {
                    MetaTileEntityLargeTurbine.this.exportFluidHandler.fill(material.getFluid(fuelAmountUsed), true);
                }
            } else if (MetaTileEntityLargeTurbine.this.turbineType.toString().equals("HOT_COOLANT")) {
                if (fuelAmountUsed > 0) {
                    FluidMaterial material = MetaFluids.getMaterialFromFluid(currentRecipe.getRecipeFluid().getFluid());
                    if (material != null) {
                        MetaTileEntityLargeTurbine.this.exportFluidHandler.fill(material.getFluid(fuelAmountUsed), true);
                    }
                }
            }
        }

        private int getBonusForTurbineType(gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine turbine) {
            switch (turbine.turbineType) {
                case GAS:
                    return ConfigHolder.gasTurbineBonusOutput;
                case PLASMA:
                    return ConfigHolder.plasmaTurbineBonusOutput;
                case STEAM:
                    return ConfigHolder.steamTurbineBonusOutput;
            }

            switch (turbine.turbineType.toString()) {
                case "GAS_OVERRIDE":
                    return ConfigHolder.gasTurbineBonusOutput;
                case "STEAM_OVERRIDE":
                    return ConfigHolder.steamTurbineBonusOutput;
                case "HOT_COOLANT":
                    return (int) (ConfigHolder.steamTurbineBonusOutput * 1.3);
                case "PLASMA_OVERRIDE":
                    return ConfigHolder.plasmaTurbineBonusOutput;
                default:
                    return 1;
            }
        }


        @Override
        public long getRecipeOutputVoltage() {
            MetaTileEntityRotorHolder rotorHolder = MetaTileEntityLargeTurbine.this.getAbilities(gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine.ABILITY_ROTOR_HOLDER).get(0);
            double relativeRotorSpeed = rotorHolder.getRelativeRotorSpeed();
            if (rotorHolder.getCurrentRotorSpeed() > 0 && rotorHolder.hasRotorInInventory()) {
                double rotorEfficiency = rotorHolder.getRotorEfficiency();
                double totalEnergyOutput = (BASE_EU_OUTPUT + getBonusForTurbineType(MetaTileEntityLargeTurbine.this) * rotorEfficiency) * (relativeRotorSpeed * relativeRotorSpeed);
                return MathHelper.ceil(totalEnergyOutput);
            }
            return 0L;
        }


    }

}
