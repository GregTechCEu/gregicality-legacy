package gregicadditions.machines.energyconverter.utils;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.traits.TraitFeIn;
import gregicadditions.machines.energyconverter.traits.TraitFEOut;
import gregicadditions.machines.energyconverter.traits.TraitGTEUIn;
import gregicadditions.machines.energyconverter.traits.TraitGTEUOut;
import gregicadditions.machines.energyconverter.traits.charger.ChargeHandler;
import gregicadditions.machines.energyconverter.traits.charger.ChargeHandlerFE;
import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public enum Energy {
    FE("fe", 4, NumberType.INTEGER) {
        @Override
        public void createEnergyEmitterTrait(final MetaTileEntityEnergyConverter energyConverter) {
            new TraitFEOut(energyConverter);
        }

        @Override
        public void createEnergyReceiverTrait(final MetaTileEntityEnergyConverter energyConverter) {
            new TraitFeIn(energyConverter);
        }

        @Override
        public ChargeHandler createChargeHandler(final MetaTileEntityEnergyConverter energyConverter) {
            return new ChargeHandlerFE(energyConverter);
        }
    },
    GTEU("gteu", 1, NumberType.LONG) {
        @Override
        public void createEnergyEmitterTrait(final MetaTileEntityEnergyConverter energyConverter) {
            new TraitGTEUOut(energyConverter);
        }

        @Override
        public void createEnergyReceiverTrait(final MetaTileEntityEnergyConverter energyConverter) {
            new TraitGTEUIn(energyConverter);
        }

        @Override
        public ChargeHandler createChargeHandler(final MetaTileEntityEnergyConverter energyConverter) {
            throw new UnsupportedOperationException("No ChargeHandler for GTEU!");
        }
    };

    private final String name;
    private final int defaultConversionRate;
    private final NumberType numberType;

    Energy(final String name, final int defaultConversionRate, final NumberType numberType) {
        this.name = name;
        this.defaultConversionRate = defaultConversionRate;
        this.numberType = numberType;
    }

    public String getName() {
        return I18n.format("gtadditions.converter.energy." + this.name);
    }

    public int getDefaultConversionRate() {
        return this.defaultConversionRate;
    }

    public NumberType getNumberType() {
        return this.numberType;
    }

    @Nullable
    public Capability<?> getCapability() {
        switch (this) {
            case FE: {
                return CapabilityEnergy.ENERGY;
            }
            case GTEU: {
                return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public abstract void createEnergyEmitterTrait(final MetaTileEntityEnergyConverter p0);

    public abstract void createEnergyReceiverTrait(final MetaTileEntityEnergyConverter p0);

    public abstract ChargeHandler createChargeHandler(final MetaTileEntityEnergyConverter p0);


    @Override
    public String toString() {
        return this.getName();
    }
}
