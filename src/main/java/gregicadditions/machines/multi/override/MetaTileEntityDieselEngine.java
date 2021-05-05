package gregicadditions.machines.multi.override;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.render.Textures.STABLE_TITANIUM_CASING;
import static gregtech.api.unification.material.Materials.Titanium;

public class MetaTileEntityDieselEngine extends gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityDieselEngine {
	public MetaTileEntityDieselEngine(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityDieselEngine(this.metaTileEntityId);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return STABLE_TITANIUM_CASING;
	}

	@Override
	public IBlockState getCasingState() {
		return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
	}
}
