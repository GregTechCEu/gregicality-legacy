package gregicadditions.machines.multi.override;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityElectricBlastFurnace extends gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace {
	public MetaTileEntityElectricBlastFurnace(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityElectricBlastFurnace(this.metaTileEntityId);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Invar);
	}

	@Override
	public IBlockState getCasingState() {
		return GAMetaBlocks.METAL_CASING.get(Materials.Invar).getDefaultState();
	}
}
