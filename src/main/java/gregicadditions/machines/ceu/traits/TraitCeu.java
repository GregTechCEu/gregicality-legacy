package gregicadditions.machines.ceu.traits;

import gregicadditions.machines.ceu.MTECeu;
import gregtech.api.metatileentity.MTETrait;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TraitCeu extends MTETrait {
	protected final MTECeu ceu;

	public TraitCeu(final MTECeu ceu) {
		super(ceu);
		this.ceu = ceu;
	}

	@Nullable
	protected abstract Capability<?> getImplementingCapability();

	@Nullable
	public <T> T getCapability(final Capability<T> capability) {
		return (T) ((this.getImplementingCapability() == null || this.getImplementingCapability() != capability) ? null : this);
	}

	public abstract static class TraitCeuCapabilityBasedEmitter<C> extends TraitCeu {
		public TraitCeuCapabilityBasedEmitter(final MTECeu ceu) {
			super(ceu);
		}

		public void update() {
			if (!this.ceu.getWorld().isRemote && this.ceu.isThisEnabled()) {
				final TileEntity te = this.ceu.getWorld().getTileEntity(this.ceu.getPos().offset(this.ceu.getFrontFacing()));
				if (te != null) {
					final C capability = (C) te.getCapability((Capability<C>) this.getImplementingCapability(), this.ceu.getFrontFacing().getOpposite());
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
