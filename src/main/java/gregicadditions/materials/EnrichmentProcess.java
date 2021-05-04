package gregicadditions.materials;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class EnrichmentProcess {

    public Fluid fluidHexachloride;
    public Fluid fluidHexafluoride;
    public Fluid depletedFuelNitrateSolution;
    public Fluid hexafluorideSteamCracked;

    protected IngotMaterial material;

    public IngotMaterial getMaterial() {
        return material;
    }


    public ItemStack getItemStack(OrePrefix orePrefix, int amount) {
        return OreDictUnifier.get(orePrefix, material, amount);
    }

    public ItemStack getItemStack(OrePrefix orePrefix) {
        return getItemStack(orePrefix, 1);
    }

    @Nullable
    public FluidStack getFluidDepletedFuelNitrateSolution(int amount) {
        return depletedFuelNitrateSolution != null ? new FluidStack(depletedFuelNitrateSolution, amount) : null;
    }


    @Nullable
    public FluidStack getFluidHexachloride(int amount) {
        return fluidHexachloride != null ? new FluidStack(fluidHexachloride, amount) : null;
    }

    @Nullable
    public FluidStack getFluidHexafluoride(int amount) {
        return fluidHexafluoride != null ? new FluidStack(fluidHexafluoride, amount) : null;
    }

    @Nullable
    public FluidStack getFluidHexafluorideSteamCracked(int amount) {
        return hexafluorideSteamCracked != null ? new FluidStack(hexafluorideSteamCracked, amount) : null;
    }
}
