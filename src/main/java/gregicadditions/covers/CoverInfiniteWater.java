package gregicadditions.covers;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregicadditions.client.ClientHandler;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Arrays;
import java.util.Optional;

import static gregtech.api.unification.material.Materials.Lava;
import static gregtech.api.unification.material.Materials.Water;

public class CoverInfiniteWater extends CoverBehavior implements ITickable {

    private final int speed;
    private long timer = 0L;
    private final IFluidHandler fluidHandler;

    public CoverInfiniteWater(ICoverable coverHolder, EnumFacing attachedSide, int speed) {
        super(coverHolder, attachedSide);
        this.speed = speed;
        fluidHandler = this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
    }

    @Override
    public boolean canAttach() {
        return this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null) != null;
    }

    @Override
    public void renderCover(CCRenderState ccRenderState, Matrix4 matrix4, IVertexOperation[] iVertexOperations, Cuboid6 cuboid6, BlockRenderLayer blockRenderLayer) {
        ClientHandler.COVER_INFINITE_WATER.renderSided(attachedSide, cuboid6, ccRenderState, iVertexOperations, matrix4);
    }


    @Override
    public void update() {
        if (timer++ % ((20 * speed) / (Math.pow(2, speed - 1))) == 0) {
            FluidStack fluidStack = fluidHandler.drain(Water.getFluid(Integer.MAX_VALUE), false);
            Arrays.stream(fluidHandler.getTankProperties())
                    .filter(iFluidTankProperties -> Optional.ofNullable(iFluidTankProperties.getContents()).orElse(Lava.getFluid(1)).isFluidEqual(Water.getFluid(1)) && iFluidTankProperties.getCapacity() > 64000)
                    .findFirst()
                    .ifPresent($ -> fluidHandler.fill(Water.getFluid(64000), true));
            int amount = fluidStack != null ? Math.max(64000 - fluidStack.amount, 0) : 64000;
            fluidHandler.fill(Water.getFluid(amount), true);
        }
    }
}
