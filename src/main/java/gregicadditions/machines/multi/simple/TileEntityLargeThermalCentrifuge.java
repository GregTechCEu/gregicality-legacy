package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.multi.MultiUtils;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregicadditions.client.ClientHandler.RED_STEEL_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityLargeThermalCentrifuge extends LargeSimpleRecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};

	private int speedBonus;


	public TileEntityLargeThermalCentrifuge(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, GAConfig.multis.largeThermalCentrifuge.euPercentage, GAConfig.multis.largeThermalCentrifuge.durationPercentage, GAConfig.multis.largeThermalCentrifuge.chancedBoostPercentage, GAConfig.multis.largeThermalCentrifuge.stack, true, true, true);
		this.recipeMapWorkable = new LargeThermalCentrifugeWorkableHandler(this);
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityLargeThermalCentrifuge(metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("AXXXA", "AXHXA", "AXXXA", "AAAAA")
				.aisle("XXXXX", "XCCCX", "X###X", "AXXXA")
				.aisle("XXMXX", "HCPCH", "X#P#X", "AXHXA")
				.aisle("XXXXX", "XCCCX", "X###X", "AXXXA")
				.aisle("AXXXA", "AXSXA", "AXXXA", "AAAAA")
				.setAmountAtLeast('L', 12)
				.setAmountAtLeast('G', 3)
				.where('S', selfPredicate())
				.where('L', statePredicate(getCasingState()))
				.where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', heatingCoilPredicate())
				.where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)))
				.where('H', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH).or(statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))))
				.where('#', isAirPredicate())
				.where('A', (tile) -> true)
				.where('M', motorPredicate())
				.build();
	}

	public static Predicate<BlockWorldState> heatingCoilPredicate() {
		return blockWorldState -> {
			IBlockState blockState = blockWorldState.getBlockState();
			if (!(blockState.getBlock() instanceof BlockWireCoil))
				return false;
			BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
			BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
			if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
				return false;

			int blastFurnaceTemperature = coilType.getCoilTemperature();
			int currentTemperature = blockWorldState.getMatchContext().getOrPut("blastFurnaceTemperature", blastFurnaceTemperature);

			BlockWireCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("coilType", coilType);

			return currentTemperature == blastFurnaceTemperature && coilType.equals(currentCoilType);
		};
	}

	private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.RED_STEEL);
	public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeThermalCentrifuge.casingMaterial, defaultCasingState);


	public IBlockState getCasingState() {
		return casingState;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeThermalCentrifuge.casingMaterial, RED_STEEL_CASING);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
		int min = motor.getTier();
		maxVoltage = (long) (Math.pow(4, min) * 8);

		int temperature = context.getOrDefault("reactorCoilTemperature", 0);
		if (temperature <= 2700)
			this.speedBonus = 0;
		else if (temperature == 3600)
			this.speedBonus = 5;
		else if (temperature == 5400)
			this.speedBonus = 10;
		else if (temperature <= 9700)
			this.speedBonus = 20;
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.THERMAL_CENTRIFUGE_OVERLAY;
	}

	protected int getSpeedBonus() {
		return this.speedBonus;
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		super.addDisplayText(textList);
		if (isStructureFormed() && !hasProblems()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.universal.speed_increase", this.speedBonus).setStyle(new Style().setColor(TextFormatting.AQUA)));
		}
	}

	private static class LargeThermalCentrifugeWorkableHandler extends LargeSimpleMultiblockRecipeLogic {

		public LargeThermalCentrifugeWorkableHandler(RecipeMapMultiblockController tileEntity) {
			super(tileEntity, GAConfig.multis.largeThermalCentrifuge.euPercentage, GAConfig.multis.largeThermalCentrifuge.durationPercentage, GAConfig.multis.largeThermalCentrifuge.chancedBoostPercentage, GAConfig.multis.largeThermalCentrifuge.stack);
		}

		@Override
		protected void setupRecipe(Recipe recipe) {
			long maxVoltage = getMaxVoltage();
			if (metaTileEntity instanceof TileEntityLargeThermalCentrifuge)
				maxVoltage = ((TileEntityLargeThermalCentrifuge) metaTileEntity).maxVoltage;
			int[] resultOverclock = calculateOverclock(recipe.getEUt(), maxVoltage, recipe.getDuration());
			this.progressTime = 1;

			TileEntityLargeThermalCentrifuge metaTileEntity = (TileEntityLargeThermalCentrifuge) getMetaTileEntity();
			int speedBonus = metaTileEntity.getSpeedBonus();

			// apply speed bonus
			resultOverclock[1] -= (int) (resultOverclock[0] * speedBonus * 0.01f);

			setMaxProgress(resultOverclock[1]);
			this.recipeEUt = resultOverclock[0];
			this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
			int tier = getMachineTierForRecipe(recipe);
			this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(Integer.MAX_VALUE, random, tier));
			if (this.wasActiveAndNeedsUpdate) {
				this.wasActiveAndNeedsUpdate = false;
			} else {
				this.setActive(true);
			}
		}
	}

}
