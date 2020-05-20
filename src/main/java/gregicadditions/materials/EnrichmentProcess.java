package gregicadditions.materials;

import gregicadditions.item.GAMetaItems;
import gregicadditions.item.behavior.DustNuclear;
import gregtech.api.unification.material.type.IngotMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class EnrichmentProcess {

    public Fluid fluidHexachloride;
    public Fluid fluidHexafluoride;

    protected IngotMaterial material;

    public IngotMaterial getMaterial() {
        return material;
    }


    public ItemStack getDustNitrate(int amount) {
        ItemStack nuclearDustNitrateStackForm = GAMetaItems.NUCLEAR_DUST_NITRATE.getStackForm(amount);
        DustNuclear.getInstanceFor(nuclearDustNitrateStackForm).setPartMaterial(nuclearDustNitrateStackForm, material);
        return nuclearDustNitrateStackForm;
    }

    public ItemStack getDustDioxide(int amount) {
        ItemStack nuclearDustDioxideStackForm = GAMetaItems.NUCLEAR_DUST_DIOXIDE.getStackForm(amount);
        DustNuclear.getInstanceFor(nuclearDustDioxideStackForm).setPartMaterial(nuclearDustDioxideStackForm, material);
        return nuclearDustDioxideStackForm;
    }

    public ItemStack getDustHexafluoride(int amount) {
        ItemStack nuclearDustHexafluorideStackForm = GAMetaItems.NUCLEAR_DUST_HEXAFLUORIDE.getStackForm(amount);
        DustNuclear.getInstanceFor(nuclearDustHexafluorideStackForm).setPartMaterial(nuclearDustHexafluorideStackForm, material);
        return nuclearDustHexafluorideStackForm;
    }


    @Nullable
    public FluidStack getFluidHexachloride(int amount) {
        return fluidHexachloride != null ? new FluidStack(fluidHexachloride, amount) : null;
    }

    @Nullable
    public FluidStack getFluidHexafluoride(int amount) {
        return fluidHexafluoride != null ? new FluidStack(fluidHexafluoride, amount) : null;
    }
}
