package gregicadditions.machines.multi.miner;

import gregicadditions.GAConfig;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.multi.CasingUtils;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.client.ClientHandler.HSS_G_CASING;
import static gregicadditions.client.ClientHandler.HSS_S_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.HSSG;
import static gregtech.api.unification.material.Materials.HSSS;

public class MetaTileEntityLargeMiner3 extends MetaTileEntityLargeMiner {

    public MetaTileEntityLargeMiner3(ResourceLocation metaTileEntityId, Type type) {
        super(metaTileEntityId, type);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeMiner3(metaTileEntityId, getType());
    }

    @Override
    public IBlockState getCasingState() {
        return CasingUtils.getConfigCasingBlockState(GAConfig.multis.largeMiner.advancedMinerCasingMaterial, METAL_CASING_2.getState(MetalCasing2.CasingType.HSS_S));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return CasingUtils.getConfigCasingTexture(GAConfig.multis.largeMiner.advancedMinerCasingMaterial, HSS_S_CASING);
    }

    @Override
    public IBlockState getFrameState() {

        Material possibleMaterial = CasingUtils.getCasingMaterial(GAConfig.multis.largeMiner.advancedMinerCasingMaterial, HSSS);
        Material material = possibleMaterial instanceof SolidMaterial && possibleMaterial.hasFlag(SolidMaterial.MatFlags.GENERATE_FRAME) ? possibleMaterial : HSSS;
        return MetaBlocks.FRAMES.get((SolidMaterial) material).getDefaultState();
    }
}
