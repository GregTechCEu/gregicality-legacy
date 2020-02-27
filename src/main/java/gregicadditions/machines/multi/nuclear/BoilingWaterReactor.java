package gregicadditions.machines.multi.nuclear;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;


public class BoilingWaterReactor extends RecipeMapMultiblockController {

	public enum RodType {
		THORIUM(GARecipeMaps.BOILING_THORIUM_REACTOR_RECIPES, 3700, 3.0f, 31, 2000,
				MetaBlocks.COMPRESSED.get(Materials.Thorium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Thorium).variantProperty, Materials.Thorium)),

		URANIUM(GARecipeMaps.BOILING_URANIUM_REACTOR_RECIPES, 7800, 5.4f, 32, 4000,
				MetaBlocks.COMPRESSED.get(Materials.Uranium235).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Uranium235).variantProperty, Materials.Uranium235)),

		PLUTONIUM(GARecipeMaps.BOILING_PLUTONIUM_REACTOR_RECIPES, 11500, 9.0f, 32, 6000,
				MetaBlocks.COMPRESSED.get(Materials.Plutonium241).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Plutonium241).variantProperty, Materials.Plutonium241));

		public final RecipeMap<?> recipeMap;
		public final int baseSteamOutput;
		public final float fuelConsumptionMultiplier;
		public final int temperatureEffBuff;
		public final int maxTemperature;
		public final IBlockState casingState;

		RodType(RecipeMap<?> recipeMap, int baseSteamOutput, float fuelConsumptionMultiplier, int temperatureEffBuff, int maxTemperature, IBlockState casingState) {
			this.recipeMap = recipeMap;
			this.baseSteamOutput = baseSteamOutput;
			this.fuelConsumptionMultiplier = fuelConsumptionMultiplier;
			this.temperatureEffBuff = temperatureEffBuff;
			this.maxTemperature = maxTemperature;
			this.casingState = casingState;
		}
	}


	public BoilingWaterReactor(ResourceLocation metaTileEntityId, RodType rodType) {
		super(metaTileEntityId, rodType.recipeMap);
		this.rodType = rodType;
		reinitializeStructurePattern();
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new BoilingWaterReactor(metaTileEntityId, rodType);
	}

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

	@Override
	protected BlockPattern createStructurePattern() {
		return rodType == null ? null : FactoryBlockPattern.start()
				.aisle("#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####")
				.aisle("###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###")
				.aisle("##CCCCCCC##", "###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###", "##CCCCCCC##")
				.aisle("#CCCCCCCCC#", "##CCCCCCC##", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "##CCCCCCC##", "#CCCCCCCCC#")
				.aisle("#CCCYYYCCC#", "##CCYYYCC##", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "##CC###CC##", "#CCC###CCC#")
				.aisle("CCCCYYYCCCC", "#CCCYRYCCC#", "##CCYRYCC##", "###CYRYC###", "###CYRYC###", "###CYRYC###", "###CYRYC###", "###CYRYC###", "##CCYYYCC##", "#CCC###CCC#", "CCCC###CCCC")
				.aisle("#CCCYYYCCC#", "##CCYYYCC##", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "###CYYYC###", "##CC###CC##", "#CCC###CCC#")
				.aisle("#CCCCCCCCC#", "##CCCCCCC##", "###CCCCC###", "###CCCCC###", "###CCCCC###", "###CCSCC###", "###CCCCC###", "###CCCCC###", "###CCCCC###", "##CCCCCCC##", "#CCCCCCCCC#")
				.aisle("##CCCCCCC##", "###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###", "##CCCCCCC##")
				.aisle("###CCCCC###", "#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####", "###CCCCC###")
				.aisle("#####C#####", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "###########", "#####C#####")
				.where('S', selfPredicate())
				.where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('C', statePredicate(MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.LIGHT_CONCRETE, StoneBlock.ChiselingVariant.NORMAL)))
				.where('R', statePredicate(rodType.casingState))
				.where('#', blockWorldState -> true)
				.build();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("gregtech.multiblock.reactor.description"));
	}

	public IBlockState getCasingState() {
		return GAMetaBlocks.METAL_CASING.get(Materials.Lead).getDefaultState();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Lead);
	}


	private int currentTemperature;
	private int throttlePercentage = 100;
	private boolean hasNoWater;
	public final RodType rodType;

	private static final int BOILING_TEMPERATURE = 100;


	@Override
	public void invalidateStructure() {
		super.invalidateStructure();
		this.currentTemperature = 0; //reset temperature
		this.hasNoWater = false;
		this.throttlePercentage = 100;
	}


	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		if (this.isStructureFormed()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.temperature", new Object[]{this.currentTemperature, this.rodType.maxTemperature}));
			int steamOutput = 0;
			if (this.currentTemperature >= 100) {
				double outputMultiplier = (double) this.currentTemperature / ((double) this.rodType.maxTemperature * 1.0D);
				steamOutput = (int) ((double) this.rodType.baseSteamOutput * outputMultiplier);
				if (this.inputFluidInventory.drain(ModHandler.getWater(1), false) == null && this.inputFluidInventory.drain(ModHandler.getDistilledWater(1), false) == null) {
					steamOutput = 0;
				}
			}

			textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.steam_output", new Object[]{steamOutput, this.rodType.baseSteamOutput}));
		}

		super.addDisplayText(textList);
	}

	private double getThrottleMultiplier() {
		return throttlePercentage / 100.0;
	}

	private double getThrottleEfficiency() {
		return MathHelper.clamp(1.0 + 0.3 * Math.log(getThrottleMultiplier()), 0.4, 1.0);
	}

	@Override
	protected void updateFormedValid() {
		super.updateFormedValid();

		if(!this.recipeMapWorkable.isActive()){
			--this.currentTemperature;
			return;
		}
		if ( currentTemperature < rodType.maxTemperature) {
			if (getTimer() % 5 == 0) {
				this.currentTemperature++;
			}
		}

		if (currentTemperature >= BOILING_TEMPERATURE) {
			boolean doWaterDrain = getTimer() % 20 == 0;
			FluidStack drainedWater = inputFluidInventory.drain(ModHandler.getWater(1), doWaterDrain);
			if (drainedWater == null || drainedWater.amount == 0) {
				drainedWater = inputFluidInventory.drain(ModHandler.getDistilledWater(1), doWaterDrain);
			}
			if (drainedWater != null && drainedWater.amount > 0) {
//				if (currentTemperature > BOILING_TEMPERATURE && hasNoWater) {
//					float explosionPower = currentTemperature / (float) BOILING_TEMPERATURE * 2.0f;
//					getWorld().setBlockToAir(getPos());
//					getWorld().createExplosion(null, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5,
//							explosionPower, true);
//				}
				this.hasNoWater = false;
				if (currentTemperature >= BOILING_TEMPERATURE) {
					double outputMultiplier = currentTemperature / (rodType.maxTemperature * 1.0) * getThrottleMultiplier() * getThrottleEfficiency();
					int steamOutput = (int) (rodType.baseSteamOutput * outputMultiplier);
					FluidStack steamStack = ModHandler.getSteam(steamOutput);
					outputFluidInventory.fill(steamStack, true);
				}
			} else {
				this.hasNoWater = true;
			}
		} else {
			this.hasNoWater = false;
		}
	}


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("CurrentTemperature", currentTemperature);
		data.setBoolean("HasNoWater", hasNoWater);
		data.setInteger("ThrottlePercentage", throttlePercentage);
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.currentTemperature = data.getInteger("CurrentTemperature");
		this.hasNoWater = data.getBoolean("HasNoWater");
		if (data.hasKey("ThrottlePercentage")) {
			this.throttlePercentage = data.getInteger("ThrottlePercentage");
		}
	}


}
