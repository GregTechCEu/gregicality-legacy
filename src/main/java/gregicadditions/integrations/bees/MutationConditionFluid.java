package gregicadditions.integrations.bees;

import forestry.api.apiculture.IBeeHousing;
import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import forestry.core.tiles.TileUtil;
import gregtech.api.unification.material.type.FluidMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Arrays;
import java.util.List;

public class MutationConditionFluid implements IMutationCondition {
    private final List<FluidMaterial> conditions;
    private final int chance;

    public MutationConditionFluid(int chance, FluidMaterial... fluidMaterials) {
        conditions = Arrays.asList(fluidMaterials);
        this.chance = chance;
    }

    public MutationConditionFluid(FluidMaterial... fluidMaterials) {
        this(100, fluidMaterials);
    }

    @Override
    public float getChance(World world, BlockPos pos, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1, IClimateProvider climate) {
        TileEntity tile;
        do {
            pos = pos.down();
            tile = TileUtil.getTile(world, pos);
        } while (tile instanceof IBeeHousing);
        if (tile == null) return 0;
        IFluidHandler fluidHandler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (fluidHandler == null) {
            return 0;
        }
        return Arrays.stream(fluidHandler.getTankProperties()).anyMatch(iFluidTankProperties -> conditions.stream()
                .anyMatch(fluidMaterial -> iFluidTankProperties.getContents().getFluid() ==
                        fluidMaterial.getMaterialFluid())) ? world.rand.nextInt(100) < chance ? 1 : 0 : 0;
    }

    @Override
    public String getDescription() {
        StringBuilder displayName = new StringBuilder("[");
        for (FluidMaterial fluidMaterial : conditions){
            displayName.append(fluidMaterial.getLocalizedName()).append("/");
        }
        displayName.setCharAt(displayName.length() - 1, ']');
        return I18n.format("for.mutation.condition.fluid", displayName.toString());
    }
}
