package gregicadditions.machines.energyconverter.traits;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregtech.api.metatileentity.MTETrait;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TraitEnergyConverter extends MTETrait {
	protected final MetaTileEntityEnergyConverter energyConverter;

	public TraitEnergyConverter(final MetaTileEntityEnergyConverter energyConverter) {
		super(energyConverter);
		this.energyConverter = energyConverter;
	}

	@Nullable
	protected abstract Capability<?> getImplementingCapability();

	@Nullable
	public <T> T getCapability(final Capability<T> capability) {
		return (T) ((this.getImplementingCapability() == null || this.getImplementingCapability() != capability) ? null : this);
	}

	public abstract static class TraitEnergyConverterCapabilityBasedEmitter<C> extends TraitEnergyConverter {
		public TraitEnergyConverterCapabilityBasedEmitter(final MetaTileEntityEnergyConverter energyConverter) {
			super(energyConverter);
		}

		public void update() {
			if (!this.energyConverter.getWorld().isRemote && this.energyConverter.isThisEnabled()) {
				final TileEntity tileEntity = this.energyConverter.getWorld().getTileEntity(this.energyConverter.getPos().offset(this.energyConverter.getFrontFacing()));
				if (tileEntity != null) {
					final C capability = tileEntity.getCapability(this.getImplementingCapability(), this.energyConverter.getFrontFacing().getOpposite());
					if (capability != null) {
						this.operate(capability);
					}
				}
			}
		}

		protected abstract void operate(final C p0);

		@Nonnull
		@Override
		protected abstract Capability<C> getImplementingCapability();
	}
}
