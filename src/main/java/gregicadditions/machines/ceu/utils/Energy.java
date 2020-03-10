package gregicadditions.machines.ceu.utils;

import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.traits.TraitFeIn;
import gregicadditions.machines.ceu.traits.TraitFeOut;
import gregicadditions.machines.ceu.traits.TraitGteuIn;
import gregicadditions.machines.ceu.traits.TraitGteuOut;
import gregicadditions.machines.ceu.traits.charger.ChargeHandler;
import gregicadditions.machines.ceu.traits.charger.ChargeHandlerFE;
import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public enum Energy {
	FE("fe", 4, NumberType.INTEGER) {
		@Override
		public void createEnergyEmitterTrait(final MTECeu ceu) {
			new TraitFeOut(ceu);
		}

		@Override
		public void createEnergyReceiverTrait(final MTECeu ceu) {
			new TraitFeIn(ceu);
		}

		@Override
		public ChargeHandler createChargeHandler(final MTECeu ceu) {
			return new ChargeHandlerFE(ceu);
		}
	},
	GTEU("gteu", 1, NumberType.LONG) {
		@Override
		public void createEnergyEmitterTrait(final MTECeu ceu) {
			new TraitGteuOut(ceu);
		}

		@Override
		public void createEnergyReceiverTrait(final MTECeu ceu) {
			new TraitGteuIn(ceu);
		}

		@Override
		public ChargeHandler createChargeHandler(final MTECeu ceu) {
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
		return I18n.format("gtadditions.ceu.energy." + this.name);
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

	public abstract void createEnergyEmitterTrait(final MTECeu p0);

	public abstract void createEnergyReceiverTrait(final MTECeu p0);

	public abstract ChargeHandler createChargeHandler(final MTECeu p0);


	@Override
	public String toString() {
		return this.getName();
	}
}
