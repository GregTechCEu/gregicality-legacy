package gregicadditions.coremod.hooks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.vec.Matrix4;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class GregTechCEHooks {

    //origin: none
    public static boolean shouldCoverRenderPass(MetaTileEntityHolder holder, int pass) {
        MetaTileEntity metaTE = holder.getMetaTileEntity();
        if (metaTE == null) return false;
        for (EnumFacing side: EnumFacing.VALUES){
            CoverBehavior cover = metaTE.getCoverAtSide(side);
            if (cover instanceof IFastRenderMetaTileEntity && ((IFastRenderMetaTileEntity) cover).shouldRenderInPass(pass)) {
                return true;
            }
        }
        return false;
    }

    //origin: none
    public static void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks, BufferBuilder buffer) {
        if(te instanceof MetaTileEntityHolder){
            MetaTileEntity metaTE = ((MetaTileEntityHolder) te).getMetaTileEntity();
            if (metaTE != null){
                CCRenderState renderState = CCRenderState.instance();
                renderState.reset();
                renderState.bind(buffer);
                renderState.setBrightness(te.getWorld(), te.getPos());
                Matrix4 translation = (new Matrix4()).translate(x, y, z);
                for (EnumFacing side: EnumFacing.VALUES){
                    CoverBehavior cover = metaTE.getCoverAtSide(side);
                    if (cover instanceof IFastRenderMetaTileEntity) {
                        ((IFastRenderMetaTileEntity)cover).renderMetaTileEntityFast(renderState, translation, partialTicks);
                    }
                }
            }
        }

    }
}
