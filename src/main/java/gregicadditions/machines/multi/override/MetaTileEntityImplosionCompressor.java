package gregicadditions.machines.multi.override;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.client.ClientHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.api.recipes.RecipeMaps.IMPLOSION_RECIPES;
import static gregtech.api.render.Textures.SOLID_STEEL_CASING;

public class MetaTileEntityImplosionCompressor extends GARecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
			MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
			MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH,
			GregicAdditionsCapabilities.MUFFLER_HATCH};

	public MetaTileEntityImplosionCompressor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, IMPLOSION_RECIPES, true, true, false);
        this.recipeMapWorkable = new GAMultiblockRecipeLogic(this);
    }

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityImplosionCompressor(this.metaTileEntityId);
	}

	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XXX", "XXX")
				.aisle("XXX", "X#X", "XXX")
				.aisle("XXX", "XSX", "XXX")
				.setAmountAtLeast('L', 14)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('#', isAirPredicate())
				.where('C', statePredicate(getCasingState()))
				.build();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return SOLID_STEEL_CASING;
	}

	public IBlockState getCasingState() {
		return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return ClientHandler.IMPLOSION_OVERLAY;
	}
}
