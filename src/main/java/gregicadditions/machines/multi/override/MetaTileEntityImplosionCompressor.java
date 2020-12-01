package gregicadditions.machines.multi.override;

import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityImplosionCompressor extends gregtech.common.metatileentities.multi.electric.MetaTileEntityImplosionCompressor {
	public MetaTileEntityImplosionCompressor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.recipeMapWorkable = new GAMultiblockRecipeLogic(this);
    }

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityImplosionCompressor(this.metaTileEntityId);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Steel);
	}

	@Override
	public IBlockState getCasingState() {
		return GAMetaBlocks.getMetalCasingBlockState(Steel);
	}
}
