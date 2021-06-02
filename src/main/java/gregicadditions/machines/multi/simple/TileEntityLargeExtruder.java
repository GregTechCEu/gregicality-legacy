package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicadditions.client.ClientHandler.INCONEL_625_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class TileEntityLargeExtruder extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
			MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
			MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


	public TileEntityLargeExtruder(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.EXTRUDER_RECIPES, GAConfig.multis.largeExtruder.euPercentage, GAConfig.multis.largeExtruder.durationPercentage, GAConfig.multis.largeExtruder.chancedBoostPercentage, GAConfig.multis.largeExtruder.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeExtruder(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXXX", "XXXX", "XXX#")
//				.aisle("XXXX", "XCPX", "XXX#").setRepeatable(0, 5)
				.aisle("XXXX", "XCPX", "XXX#").setRepeatable(2, 6)
				.aisle("XXXX", "XSXX", "XXX#")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
				.where('#', (tile) -> true)
				.where('P', pistonPredicate())
				.build();
	}

	private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.INCONEL_625);
	public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeExtruder.casingMaterial, defaultCasingState);


	public IBlockState getCasingState() {
		return casingState;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeExtruder.casingMaterial, INCONEL_625_CASING);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		PistonCasing.CasingType piston = context.getOrDefault("Piston", PistonCasing.CasingType.PISTON_LV);
		int min = piston.getTier();
		maxVoltage = (long) (Math.pow(4, min) * 8);
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.EXTRUDER_OVERLAY;
	}
}
