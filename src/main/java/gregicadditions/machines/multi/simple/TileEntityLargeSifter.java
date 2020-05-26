package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.type.Material;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityElectricBlastFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static gregicadditions.GAMaterials.EglinSteel;
import static gregicadditions.GAMaterials.Grisium;

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
				.aisle("XXXXX", "XXSXX", "XXXXX")
				.setAmountAtLeast('L', 9)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', MetaTileEntityElectricBlastFurnace.heatingCoilPredicate())
				.where('#', isAirPredicate())
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("gregtech.multiblock.large_sifter.description"));
	}
}
