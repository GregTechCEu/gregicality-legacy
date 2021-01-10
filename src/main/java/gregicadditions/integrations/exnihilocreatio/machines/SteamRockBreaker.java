package gregicadditions.integrations.exnihilocreatio.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FilteredFluidHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.recipes.ModHandler;
import gregtech.api.render.SimpleSidedCubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public class SteamRockBreaker extends MetaTileEntity {

    private FluidTank steamFluidTank;
    private static final int STEAM_DRAIN_PER_CYCLE = 50;

    public SteamRockBreaker(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamRockBreaker(metaTileEntityId);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            ItemStack output;
            int largestSignal = 0;

            for (EnumFacing face : EnumFacing.VALUES)
                largestSignal = Math.max(getInputRedstoneSignal(face, false), largestSignal);

            switch (largestSignal) {
                case 4:
                case 5:
                case 6:
                case 7:
                    output = new ItemStack(Blocks.STONE, 1, 3);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    output = new ItemStack(Blocks.STONE, 1, 1);
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                    output = new ItemStack(Blocks.STONE, 1, 5);
                    break;
                default:
                    output = new ItemStack(Blocks.COBBLESTONE);
            }
            if (checkSides(Blocks.LAVA) && checkSides(Blocks.WATER) && getTimer() % 32 == 0 && steamFluidTank.getFluidAmount() >= STEAM_DRAIN_PER_CYCLE * 4) {
                exportItems.insertItem(0, output, false);
                steamFluidTank.drain(STEAM_DRAIN_PER_CYCLE, true);
            }
            if (getTimer() % 5 == 0) {
                pushItemsIntoNearbyHandlers(frontFacing);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of( Textures.STEAM_CASING_BRONZE.getSpriteOnSide(SimpleSidedCubeRenderer.RenderSide.TOP), getPaintingColor());
    }


    public int getSteamCapacity() {
        return 16000;
    }

    public FluidTankList createImportFluidHandler() {
        this.steamFluidTank = (new FilteredFluidHandler(this.getSteamCapacity())).setFillPredicate(ModHandler::isSteam);
        return new FluidTankList(false, this.steamFluidTank);
    }

    private boolean checkSides(BlockStaticLiquid liquid) {
        EnumFacing frontFacing = getFrontFacing();
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side == frontFacing || side == EnumFacing.DOWN || side == EnumFacing.UP) continue;
            if (getWorld().getBlockState(getPos().offset(side)) == liquid.getDefaultState())
                return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        IVertexOperation[] colouredPipeline = ArrayUtils.add(pipeline, new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(this.getPaintingColorForRendering())));
        Textures.STEAM_CASING_BRONZE.render(renderState, translation, colouredPipeline);
        Textures.HAMMER_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), false);
        Textures.PIPE_OUT_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }
}
