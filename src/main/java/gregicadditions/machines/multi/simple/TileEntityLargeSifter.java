package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.PistonCasing;
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
import gregtech.api.unification.material.type.Material;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicadditions.GAMaterials.EglinSteel;

public class TileEntityLargeSifter extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};


	public TileEntityLargeSifter(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.SIFTER_RECIPES, GAConfig.multis.largeSifter.euPercentage, GAConfig.multis.largeSifter.durationPercentage, GAConfig.multis.largeSifter.chancedBoostPercentage, GAConfig.multis.largeSifter.stack);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeSifter(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXXXX", "XXXXX", "XXXXX")
				.aisle("XXXXX", "X###X", "XXXXX")
				.aisle("XXXXX", "X###X", "XXXXX")
				.aisle("XXXXX", "X###X", "XXXXX")
				.aisle("XXXXX", "XPSPX", "XXXXX")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate())
				.where('#', isAirPredicate())
				.where('P', pistonPredicate())
				.build();
	}
	private static final Material defaultMaterial = EglinSteel;
	public static final Material casingMaterial = getCasingMaterial(defaultMaterial, GAConfig.multis.largeSifter.casingMaterial);


	public IBlockState getCasingState() {
		return GAMetaBlocks.getMetalCasingBlockState(casingMaterial);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(casingMaterial);
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
		return Textures.SIFTER_OVERLAY;
	}
}
