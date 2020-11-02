package gregicadditions.materials;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAEnums.GAOrePrefix.*;

import javax.annotation.Nullable;

public class EnrichmentProcess {

    public Fluid fluidHexachloride;
    public Fluid fluidHexafluoride;

    protected IngotMaterial material;

    public IngotMaterial getMaterial() {
        return material;
    }


    public ItemStack getDustNitrate(int amount) {
        return OreDictUnifier.get(nitride, material, amount);
    }

    public ItemStack getDustDioxide(int amount) {
        return OreDictUnifier.get(dioxide, material, amount);
    }

    public ItemStack getDustHexafluoride(int amount) {
        return OreDictUnifier.get(hexafluoride, material, amount);
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
