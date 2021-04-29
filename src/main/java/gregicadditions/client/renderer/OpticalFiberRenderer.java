package gregicadditions.client.renderer;

import codechicken.lib.render.BlockRenderer;
import codechicken.lib.render.BlockRenderer.BlockFace;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.block.BlockRenderingRegistry;
import codechicken.lib.render.block.ICCBlockRenderer;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Translation;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import gregicadditions.Gregicality;
import gregicadditions.pipelike.opticalfiber.BlockOpticalFiber;
import gregicadditions.pipelike.opticalfiber.ItemBlockOpticalFiber;
import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregicadditions.pipelike.opticalfiber.OpticalFiberSize;
import gregicadditions.pipelike.opticalfiber.tile.TileEntityOpticalFiber;
import gregicadditions.utils.GALog;
import gregtech.api.cover.ICoverable;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ModCompatibility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

public class OpticalFiberRenderer implements ICCBlockRenderer, IItemRenderer {

    public static ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation(new ResourceLocation(Gregicality.MODID, "cable"), "normal");
    public static OpticalFiberRenderer INSTANCE = new OpticalFiberRenderer();
    public static EnumBlockRenderType BLOCK_RENDER_TYPE;
    private static ThreadLocal<BlockFace> blockFaces = ThreadLocal.withInitial(BlockFace::new);

    private TextureAtlasSprite wireTexture;

    public static void preInit() {
        BLOCK_RENDER_TYPE = BlockRenderingRegistry.createRenderType("ga_optical_fiber");
        BlockRenderingRegistry.registerRenderer(BLOCK_RENDER_TYPE, INSTANCE);
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        TextureUtils.addIconRegister(INSTANCE::registerIcons);
    }

    public void registerIcons(TextureMap map) {
        GALog.logger.info("Registering cable textures.");
        ResourceLocation wireLocation = new ResourceLocation(Gregicality.MODID, "blocks/cable/wire");
        this.wireTexture = map.registerSprite(wireLocation);

    }

    @SubscribeEvent
    public void onModelsBake(ModelBakeEvent event) {
        GALog.logger.info("Injected cable render model");
        event.getModelRegistry().putObject(MODEL_LOCATION, this);
    }

    @Override
    public void renderItem(ItemStack rawItemStack, TransformType transformType) {
        ItemStack stack = ModCompatibility.getRealItemStack(rawItemStack);
        if (!(stack.getItem() instanceof ItemBlockOpticalFiber)) {
            return;
        }
        GlStateManager.enableBlend();
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.startDrawing(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        BlockOpticalFiber blockOpticalFiber = (BlockOpticalFiber) ((ItemBlockOpticalFiber) stack.getItem()).getBlock();
        OpticalFiberSize opticalFiberSize = blockOpticalFiber.getItemPipeType(stack);
        if (opticalFiberSize != null) {
            renderCableBlock(opticalFiberSize, renderState, new IVertexOperation[0],
                    1 << EnumFacing.SOUTH.getIndex() | 1 << EnumFacing.NORTH.getIndex() |
                            1 << (6 + EnumFacing.SOUTH.getIndex()) | 1 << (6 + EnumFacing.NORTH.getIndex()));
        }
        renderState.draw();
        GlStateManager.disableBlend();
    }

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        renderState.setBrightness(world, pos);
        IVertexOperation[] pipeline = {new Translation(pos)};

        BlockOpticalFiber blockOpticalFiber = (BlockOpticalFiber) state.getBlock();
        TileEntityOpticalFiber tileEntityOpticalFiber = (TileEntityOpticalFiber) blockOpticalFiber.getPipeTileEntity(world, pos);
        if (tileEntityOpticalFiber == null) return false;
        int connectedSidesMask = blockOpticalFiber.getActualConnections(tileEntityOpticalFiber, world);
        OpticalFiberSize opticalFiberSize = tileEntityOpticalFiber.getPipeType();
        if (opticalFiberSize != null) {
            BlockRenderLayer renderLayer = MinecraftForgeClient.getRenderLayer();
            if (renderLayer == BlockRenderLayer.CUTOUT) {
                renderCableBlock(opticalFiberSize, renderState, pipeline, connectedSidesMask);
            }
            ICoverable coverable = tileEntityOpticalFiber.getCoverableImplementation();
            coverable.renderCovers(renderState, new Matrix4().translate(pos.getX(), pos.getY(), pos.getZ()), renderLayer);
        }
        return true;
    }

    public void renderCableBlock(OpticalFiberSize opticalFiberSize1, CCRenderState state, IVertexOperation[] pipeline, int connectMask) {
        int wireColor = GTUtility.convertRGBtoOpaqueRGBA_CL(0xFFFFFF);
        float thickness = opticalFiberSize1.thickness;

        IVertexOperation[] wire = ArrayUtils.addAll(pipeline, new IconTransformation(wireTexture), new ColourMultiplier(wireColor));

        Cuboid6 cuboid6 = BlockOpticalFiber.getSideBox(null, thickness);
        for (EnumFacing renderedSide : EnumFacing.VALUES) {
            if ((connectMask & 1 << renderedSide.getIndex()) == 0) {
                int oppositeIndex = renderedSide.getOpposite().getIndex();
                if ((connectMask & 1 << oppositeIndex) > 0 && (connectMask & ~(1 << oppositeIndex)) == 0) {
                    //if there is something on opposite side, render overlay + wire
                    renderCableSide(state, wire, renderedSide, cuboid6);
                    renderCableSide(state, wire, renderedSide, cuboid6);
                } else {
                    renderCableSide(state, wire, renderedSide, cuboid6);
                }
            }
        }

        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.DOWN, thickness);
        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.UP, thickness);
        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.WEST, thickness);
        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.EAST, thickness);
        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.NORTH, thickness);
        renderCableCube(connectMask, state, wire, wire, wire, EnumFacing.SOUTH, thickness);
    }

    private static void renderCableCube(int connections, CCRenderState renderState, IVertexOperation[] pipeline, IVertexOperation[] wire, IVertexOperation[] overlays, EnumFacing side, float thickness) {
        if ((connections & 1 << side.getIndex()) > 0) {
            boolean renderFrontSide = (connections & 1 << (6 + side.getIndex())) > 0;
            Cuboid6 cuboid6 = BlockOpticalFiber.getSideBox(side, thickness);
            for (EnumFacing renderedSide : EnumFacing.VALUES) {
                if (renderedSide == side) {
                    if (renderFrontSide) {
                        renderCableSide(renderState, wire, renderedSide, cuboid6);
                        renderCableSide(renderState, overlays, renderedSide, cuboid6);
                    }
                } else if (renderedSide != side.getOpposite()) {
                    renderCableSide(renderState, pipeline, renderedSide, cuboid6);
                }
            }
        }
    }

    private static void renderCableSide(CCRenderState renderState, IVertexOperation[] pipeline, EnumFacing side, Cuboid6 cuboid6) {
        BlockFace blockFace = blockFaces.get();
        blockFace.loadCuboidFace(cuboid6, side.getIndex());
        renderState.setPipeline(blockFace, 0, blockFace.verts.length, pipeline);
        renderState.render();
    }

    @Override
    public void renderBrightness(IBlockState state, float brightness) {
    }

    @Override
    public void handleRenderBlockDamage(IBlockAccess world, BlockPos pos, IBlockState state, TextureAtlasSprite sprite, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        renderState.setPipeline(new Vector3(new Vec3d(pos)).translation(), new IconTransformation(sprite));
        BlockOpticalFiber blockOpticalFiber = (BlockOpticalFiber) state.getBlock();
        IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntityCable = blockOpticalFiber.getPipeTileEntity(world, pos);
        if (tileEntityCable == null) {
            return;
        }
        OpticalFiberSize opticalFiberSize = tileEntityCable.getPipeType();
        if (opticalFiberSize == null) {
            return;
        }
        float thickness = opticalFiberSize.getThickness();
        int connectedSidesMask = blockOpticalFiber.getActualConnections(tileEntityCable, world);
        Cuboid6 baseBox = BlockOpticalFiber.getSideBox(null, thickness);
        BlockRenderer.renderCuboid(renderState, baseBox, 0);
        for (EnumFacing renderSide : EnumFacing.VALUES) {
            if ((connectedSidesMask & (1 << renderSide.getIndex())) > 0) {
                Cuboid6 sideBox = BlockOpticalFiber.getSideBox(renderSide, thickness);
                BlockRenderer.renderCuboid(renderState, sideBox, 0);
            }
        }
    }

    @Override
    public void registerTextures(TextureMap map) {
    }

    @Override
    public IModelState getTransforms() {
        return TransformUtils.DEFAULT_BLOCK;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return TextureUtils.getMissingSprite();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    public Pair<TextureAtlasSprite, Integer> getParticleTexture(IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntity) {
        if (tileEntity == null) {
            return Pair.of(TextureUtils.getMissingSprite(), 0xFFFFFF);
        }
        OpticalFiberSize opticalFiberSize = tileEntity.getPipeType();
        if (opticalFiberSize == null) {
            return Pair.of(TextureUtils.getMissingSprite(), 0xFFFFFF);
        }
        TextureAtlasSprite atlasSprite;
        atlasSprite = wireTexture;

        return Pair.of(atlasSprite, 0xFFFFFF);
    }
}
