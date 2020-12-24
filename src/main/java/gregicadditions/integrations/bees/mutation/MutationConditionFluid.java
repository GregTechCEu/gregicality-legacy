package gregicadditions.integrations.bees.mutation;

import forestry.api.apiculture.IBeeHousing;
import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.core.tiles.TileUtil;
import forestry.core.utils.Translator;
import gregicadditions.materials.SimpleFluidMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MutationConditionFluid implements IMutationCondition {
    private final List<Fluid> conditions;
    private final int chance;
    private final int cost;

    public MutationConditionFluid(Integer cost, Integer chance, Object... objects) {
        conditions = new ArrayList<>();
        for (Object obj : objects){
            if (obj instanceof Fluid)
                conditions.add((Fluid) obj);
            else if (obj instanceof FluidMaterial)
                conditions.add(((FluidMaterial) obj).getMaterialFluid());
            else if (obj instanceof SimpleFluidMaterial)
                conditions.add(((SimpleFluidMaterial) obj).fluid);
            else if (obj instanceof FluidStack)
                conditions.add(((FluidStack) obj).getFluid());
        }
        this.chance = chance;
        this.cost = cost;
    }

    public MutationConditionFluid(Integer chance, Object... objects) {
        this(0, chance, objects);
    }

    public MutationConditionFluid(Object... objects) {
        this(100, objects);
    }

    @Override
    public float getChance(World world, BlockPos pos, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1, IClimateProvider climate) {
        TileEntity tile;
        do {
            pos = pos.down();
            tile = TileUtil.getTile(world, pos);
        } while (tile instanceof IBeeHousing);
        if (tile == null) return 0;
        IFluidHandler fluidHandler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
        if (fluidHandler == null) {
            return 0;
        }
        if (cost > 0) {
            return conditions.stream().anyMatch(condition -> {
                FluidStack fluidStack = fluidHandler.drain(new FluidStack(condition, cost), false);
                if (fluidStack == null || fluidStack.amount < cost) {
                    return false;
                }
                fluidHandler.drain(new FluidStack(condition, cost), true);
                return true;
            }) ? world.rand.nextInt(100) < chance ? 1 : 0 : 0;

        }
        return conditions.isEmpty()? 1 : Arrays.stream(fluidHandler.getTankProperties()).anyMatch(iFluidTankProperty ->
                iFluidTankProperty.getContents() != null && conditions.stream().anyMatch(fluid ->
                        fluid == iFluidTankProperty.getContents().getFluid()))
                ? world.rand.nextInt(100) < chance ? 1 : 0 : 0;
    }

    @Override
    public String getDescription() {
        StringBuilder displayName = new StringBuilder("[");
        for (Fluid fluid : conditions){
            displayName.append(Translator.translateToLocalFormatted(fluid.getUnlocalizedName())).append("/");
        }
        displayName.setCharAt(displayName.length() - 1, ']');
        return Translator.translateToLocalFormatted("for.mutation.condition.fluid", displayName.toString(), 2 * cost);
    }
}
