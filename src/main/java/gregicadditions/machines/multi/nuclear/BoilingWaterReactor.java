package gregicadditions.machines.multi.nuclear;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class BoilingWaterReactor extends RecipeMapMultiblockController {
	public BoilingWaterReactor(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.ALLOY_SMELTER_RECIPES);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new BoilingWaterReactor(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("S")
				.where('S', selfPredicate())
				.build();
	}

	public IBlockState getCasingState() {
		return MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.DARK_CONCRETE, StoneBlock.ChiselingVariant.NORMAL ) ;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		TextureMap textureMap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite atlasSprite = textureMap.getAtlasSprite("gregtech:textures/blocks/stones/concrete/concrete_dark_stone");
		for (EnumFacing face : EnumFacing.VALUES) {
			Textures.renderFace(renderState, translation, new IVertexOperation[0], face, Cuboid6.full, atlasSprite);
		}

	}
}
