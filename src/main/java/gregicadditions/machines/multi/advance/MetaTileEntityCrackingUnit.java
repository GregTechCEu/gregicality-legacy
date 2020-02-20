package gregicadditions.machines.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityCrackingUnit extends gregtech.common.metatileentities.multi.electric.MetaTileEntityCrackingUnit {
	public MetaTileEntityCrackingUnit(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityCrackingUnit(this.metaTileEntityId);
	}

	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel);
	}

	protected IBlockState getCasingState() {
		return GAMetaBlocks.METAL_CASING.get(Materials.StainlessSteel).getDefaultState();
	}
}
