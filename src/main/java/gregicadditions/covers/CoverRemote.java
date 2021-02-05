package gregicadditions.covers;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.covers.facade.FacadeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.commons.lang3.ArrayUtils;

public class CoverRemote extends CoverBehavior implements IFastRenderMetaTileEntity {

    public CoverRemote(ICoverable coverHolder, EnumFacing attachedSide) {
        super(coverHolder, attachedSide);
    }

    @Override
    public boolean canAttach() {
        return this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, this.attachedSide) != null ||
                this.coverHolder.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, this.attachedSide) != null;
    }

    @Override
    public void renderCoverPlate(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 plateBox, BlockRenderLayer layer) {
    }

    @Override
    public void renderCover(CCRenderState ccRenderState, Matrix4 translation, IVertexOperation[] iVertexOperations, Cuboid6 cuboid6, BlockRenderLayer blockRenderLayer) {
//        FluidStack fluidStack = Materials.Water.getFluid(1000);
//        ItemStack renderStack = Item.getItemFromBlock(Blocks.GRASS).getDefaultInstance();
//        double fillPercent = 0.3D;
//        this.renderFluid(this.attachedSide, cuboid6.copy(), ccRenderState, translation, iVertexOperations, fillPercent, fluidStack);
//        this.renderItem(ccRenderState, translation.copy(), this.attachedSide, renderStack);
//        Textures.WOODEN_TANK.renderFluid(ccRenderState, translation, 0, fillPercent, fluidStack);
//        Textures.SCREEN.renderSided(this.attachedSide, cuboid6, ccRenderState, iVertexOperations, translation);
//        Textures.PUMP_OVERLAY.renderSided(this.attachedSide, cuboid6, ccRenderState, iVertexOperations, translation);
    }

    @SideOnly(Side.CLIENT)
    public void renderFluid(EnumFacing side, Cuboid6 resultFluidCuboid, CCRenderState renderState, Matrix4 translation, IVertexOperation[] ops, double fillPercent, FluidStack fluidStack) {
        if (fluidStack != null) {
            int fluidStackColor = fluidStack.getFluid().getColor(fluidStack);
            double fluidLevel = fillPercent * 1.0D;
            int resultFluidColor;
            resultFluidCuboid.max.y = resultFluidCuboid.min.y + fluidLevel;
            int opacity = 255;
            resultFluidColor = GTUtility.convertRGBtoRGBA_CL(fluidStackColor, opacity);
            ColourMultiplier multiplier = new ColourMultiplier(resultFluidColor);
            TextureAtlasSprite fluidSprite = TextureUtils.getTexture(fluidStack.getFluid().getStill(fluidStack));
            Textures.renderFace(renderState, adjustTrans(translation, side, 1), ArrayUtils.addAll(ops, multiplier), side, resultFluidCuboid, fluidSprite);
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderItem(CCRenderState ccrs, Matrix4 transform, EnumFacing side, ItemStack itemStack) {
        FacadeRenderer.renderItemCover(ccrs, side.getIndex(), itemStack, Cuboid6.full);
//        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(itemStack, null, null);
//        List<BakedQuad> quads = new ArrayList<>(model.getQuads(null, null, 0L));
//        for (EnumFacing face : EnumFacing.VALUES) {
//            quads.addAll(model.getQuads(null, face, 0L));
//        }
//        List<CCQuad> renderQuads = FacadeRenderer.applyItemTint(FacadeRenderer.sliceQuads(CCQuad.fromArray(quads), side.getIndex(), Cuboid6.full), itemStack);
//        AdvCCRSConsumer consumer = new AdvCCRSConsumer(ccrs);
//        consumer.setTranslation(transform);
//        for (CCQuad quad : renderQuads) {
//            quad.pipe(consumer);
//        }
//        itemStack = Item.getItemFromBlock(Blocks.GRASS).getDefaultInstance();
//        transform.translate(0.5, 2.5, 0.5);
////        Rotation rotation = new Rotation(-90.0f, 0.0f, 1.0f, 0.0f );
////        transform.rotate(rotation);
//        OpenGlHelper.setLightmapTextureCoords( OpenGlHelper.lightmapTexUnit, 240.f, 240.0f );
//        ccrs.pullLightmap();
//        ccrs.setColour(new ColourRGBA(255,255,255,255));
//        IBakedModel bakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(itemStack, null, null);
//        Pair<? extends IBakedModel, Matrix4f> pair = bakedmodel.handlePerspective(ItemCameraTransforms.TransformType.GUI);
//        if (pair.getRight() != null){
//            transform.multiply(new Matrix4(pair.getRight()));
//        }
//        bakedmodel = pair.getLeft();
//        //bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedmodel, ItemCameraTransforms.TransformType.GUI, false);
//        if(bakedmodel.isBuiltInRenderer()){
//            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//            GlStateManager.enableRescaleNormal();
//            itemStack.getItem().getTileEntityItemStackRenderer().renderByItem(itemStack);
//        } else {
//            List<BakedQuad> quads = new ArrayList<>(bakedmodel.getQuads(null, null, 0L));
//            for (EnumFacing face : EnumFacing.VALUES) {
//                quads.addAll(bakedmodel.getQuads(null, face, 0L));
//            }
//            List<CCQuad> renderQuads = CCQuad.fromArray(quads);
//            AdvCCRSConsumer consumer = new AdvCCRSConsumer(ccrs);
//            consumer.setTranslation(transform);
//            //consumer.setOffset(new BlockPos(1335,6,-44));
//            for (CCQuad quad : renderQuads) {
//                quad.pipe(consumer);
//            }
//        }
    }

    public static void renderItem2d( ItemStack itemStack, float scale )
    {
        if( !itemStack.isEmpty() )
        {
            OpenGlHelper.setLightmapTextureCoords( OpenGlHelper.lightmapTexUnit, 240.f, 240.0f );

            GlStateManager.pushMatrix();

            // The Z-scaling by 0.0001 causes the model to be visually "flattened"
            // This cannot replace a proper projection, but it's cheap and gives the desired
            // effect at least from head-on
            GlStateManager.scale( scale / 32.0f, scale / 32.0f, 0.0001f );
            // Position the item icon at the top middle of the panel
            GlStateManager.translate( -8, -11, 0 );

            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.renderItemAndEffectIntoGUI( itemStack, 0, 0 );

            GlStateManager.popMatrix();
        }
    }

    private Matrix4 adjustTrans(Matrix4 translation, EnumFacing side, int layer) {
        Matrix4 trans = translation.copy();
        switch (side){
            case DOWN:
                trans.translate(0 , -0.0001D * layer,0);
            break;
            case UP:
                trans.translate(0 , 0.0001D * layer,0);
                break;
            case NORTH:
                trans.translate(0 , 0,-0.0001D * layer);
                break;
            case SOUTH:
                trans.translate(0 , 0,0.0001D * layer);
                break;
            case EAST:
                trans.translate(0.0001D * layer, 0,0);
                break;
            case WEST:
                trans.translate(-0.0001D * layer, 0,0);
                break;
        }
        return trans;
    }

    @Override
    public void renderMetaTileEntityFast(CCRenderState ccRenderState, Matrix4 translation, float v) {
        Textures.PUMP_OVERLAY.renderSided(this.attachedSide, Cuboid6.full, ccRenderState, new IVertexOperation[]{}, translation);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }
}
