package gregicadditions.machines.multi.nuclear;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Collections;
import java.util.List;


public class BoilingWaterReactor extends MultiblockWithDisplayBase {

	public enum RodType {
		THORIUM(3700, 3.0f, 31, 2000,
				MetaBlocks.COMPRESSED.get(Materials.Thorium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Thorium).variantProperty, Materials.Thorium)),

		URANIUM(7800, 5.4f, 32, 4000,
				MetaBlocks.COMPRESSED.get(Materials.Uranium235).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Uranium235).variantProperty, Materials.Uranium235)),

		PLUTONIUM(11500, 5.4f, 32, 6000,
				MetaBlocks.COMPRESSED.get(Materials.Plutonium241).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Plutonium241).variantProperty, Materials.Plutonium241));

		public final int baseSteamOutput;
		public final float fuelConsumptionMultiplier;
		public final int temperatureEffBuff;
		public final int maxTemperature;
		public final IBlockState casingState;

		RodType(int baseSteamOutput, float fuelConsumptionMultiplier, int temperatureEffBuff, int maxTemperature, IBlockState casingState) {
			this.baseSteamOutput = baseSteamOutput;
			this.fuelConsumptionMultiplier = fuelConsumptionMultiplier;
			this.temperatureEffBuff = temperatureEffBuff;
			this.maxTemperature = maxTemperature;
			this.casingState = casingState;
		}
	}


	public BoilingWaterReactor(ResourceLocation metaTileEntityId, RodType rodType) {
		super(metaTileEntityId);
		this.rodType = rodType;
		reinitializeStructurePattern();
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new BoilingWaterReactor(metaTileEntityId, rodType);
	}

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS};

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

	public IBlockState getCasingState() {
		return GAMetaBlocks.METAL_CASING.get(Materials.Lead).getDefaultState();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return GAMetaBlocks.METAL_CASING.get(Materials.Lead);
	}


	private int currentTemperature;
	private int fuelBurnTicksLeft;
	private int throttlePercentage = 100;
	private boolean isActive;
	private boolean wasActiveAndNeedsUpdate;
	private boolean hasNoWater;
	private int lastTickSteamOutput;
	public final RodType rodType;

	private FluidTankList fluidImportInventory;
	private ItemHandlerList itemImportInventory;
	private FluidTankList steamOutputTank;

	private static final int CONSUMPTION_MULTIPLIER = 100;
	private static final int BOILING_TEMPERATURE = 100;


	@Override
	public void invalidateStructure() {
		super.invalidateStructure();
		this.fluidImportInventory = new FluidTankList(true);
		this.itemImportInventory = new ItemHandlerList(Collections.emptyList());
		this.steamOutputTank = new FluidTankList(true);
		this.currentTemperature = 0; //reset temperature
		this.fuelBurnTicksLeft = 0;
		this.hasNoWater = false;
		this.isActive = false;
		this.throttlePercentage = 100;
	}


	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		this.fluidImportInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
		this.itemImportInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
		this.steamOutputTank = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		if (this.isStructureFormed()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.temperature", new Object[]{this.currentTemperature, this.rodType.maxTemperature}));
			int steamOutput = 0;
			if (this.currentTemperature >= 100) {
				double outputMultiplier = (double) this.currentTemperature / ((double) this.rodType.maxTemperature * 1.0D);
				steamOutput = (int) ((double) this.rodType.baseSteamOutput * outputMultiplier);
				if (this.fluidImportInventory.drain(ModHandler.getWater(1), false) == null && this.fluidImportInventory.drain(ModHandler.getDistilledWater(1), false) == null) {
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

	private double getHeatEfficiencyMultiplier() {
		double temperature = currentTemperature / (rodType.maxTemperature * 1.0);
		return 1.0 + Math.round((rodType.temperatureEffBuff * temperature) / 100.0);
	}

	@Override
	protected void updateFormedValid() {
		if (fuelBurnTicksLeft > 0 && currentTemperature < rodType.maxTemperature) {
			--this.fuelBurnTicksLeft;
			if (getTimer() % 20 == 0) {
				this.currentTemperature++;
			}
			if (fuelBurnTicksLeft == 0) {
				this.wasActiveAndNeedsUpdate = true;
			}
		} else if (currentTemperature > 0 && getTimer() % 20 == 0) {
			--this.currentTemperature;
		}

		this.lastTickSteamOutput = 0;
		if (currentTemperature >= BOILING_TEMPERATURE) {
			boolean doWaterDrain = getTimer() % 20 == 0;
			FluidStack drainedWater = fluidImportInventory.drain(ModHandler.getWater(1), doWaterDrain);
			if (drainedWater == null || drainedWater.amount == 0) {
				drainedWater = fluidImportInventory.drain(ModHandler.getDistilledWater(1), doWaterDrain);
			}
			if (drainedWater != null && drainedWater.amount > 0) {
				if (currentTemperature > BOILING_TEMPERATURE && hasNoWater) {
					float explosionPower = currentTemperature / (float) BOILING_TEMPERATURE * 2.0f;
					getWorld().setBlockToAir(getPos());
					getWorld().createExplosion(null, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5,
							explosionPower, true);
				}
				this.hasNoWater = false;
				if (currentTemperature >= BOILING_TEMPERATURE) {
					double outputMultiplier = currentTemperature / (rodType.maxTemperature * 1.0) * getThrottleMultiplier() * getThrottleEfficiency();
					int steamOutput = (int) (rodType.baseSteamOutput * outputMultiplier);
					FluidStack steamStack = ModHandler.getSteam(steamOutput);
					steamOutputTank.fill(steamStack, true);
					this.lastTickSteamOutput = steamOutput;
				}
			} else {
				this.hasNoWater = true;
			}
		} else {
			this.hasNoWater = false;
		}

		if (fuelBurnTicksLeft == 0) {
			double heatEfficiency = getHeatEfficiencyMultiplier();
			int fuelMaxBurnTime = (int) Math.round(setupRecipeAndConsumeInputs() * heatEfficiency);
			if (fuelMaxBurnTime > 0) {
				this.fuelBurnTicksLeft = fuelMaxBurnTime;
				if (wasActiveAndNeedsUpdate) {
					this.wasActiveAndNeedsUpdate = false;
				} else setActive(true);
				markDirty();
			}
		}

		if (wasActiveAndNeedsUpdate) {
			this.wasActiveAndNeedsUpdate = false;
			setActive(false);
		}
	}

	private int setupRecipeAndConsumeInputs() {
		for (IFluidTank fluidTank : fluidImportInventory.getFluidTanks()) {
			FluidStack fuelStack = fluidTank.drain(Integer.MAX_VALUE, false);
			if (fuelStack == null || ModHandler.isWater(fuelStack))
				continue; //ignore empty tanks and water
			FuelRecipe dieselRecipe = RecipeMaps.DIESEL_GENERATOR_FUELS.findRecipe(GTValues.V[9], fuelStack);
			if (dieselRecipe != null) {
				int fuelAmountToConsume = (int) Math.ceil(dieselRecipe.getRecipeFluid().amount * CONSUMPTION_MULTIPLIER * rodType.fuelConsumptionMultiplier * getThrottleMultiplier());
				if (fuelStack.amount >= fuelAmountToConsume) {
					fluidTank.drain(fuelAmountToConsume, true);
					long recipeVoltage = FuelRecipeLogic.getTieredVoltage(dieselRecipe.getMinVoltage());
					int voltageMultiplier = (int) Math.max(1L, recipeVoltage / GTValues.V[GTValues.LV]);
					return (int) Math.ceil(dieselRecipe.getDuration() * CONSUMPTION_MULTIPLIER / 2.0 * voltageMultiplier * getThrottleMultiplier());
				} else continue;
			}
			FuelRecipe denseFuelRecipe = RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.findRecipe(GTValues.V[9], fuelStack);
			if (denseFuelRecipe != null) {
				int fuelAmountToConsume = (int) Math.ceil(denseFuelRecipe.getRecipeFluid().amount * CONSUMPTION_MULTIPLIER * rodType.fuelConsumptionMultiplier * getThrottleMultiplier());
				if (fuelStack.amount >= fuelAmountToConsume) {
					fluidTank.drain(fuelAmountToConsume, true);
					long recipeVoltage = FuelRecipeLogic.getTieredVoltage(denseFuelRecipe.getMinVoltage());
					int voltageMultiplier = (int) Math.max(1L, recipeVoltage / GTValues.V[GTValues.LV]);
					return (int) Math.ceil(denseFuelRecipe.getDuration() * CONSUMPTION_MULTIPLIER * 2 * voltageMultiplier * getThrottleMultiplier());
				}
			}
		}
		for (int slotIndex = 0; slotIndex < itemImportInventory.getSlots(); slotIndex++) {
			ItemStack itemStack = itemImportInventory.getStackInSlot(slotIndex);
			int fuelBurnValue = (int) Math.ceil(TileEntityFurnace.getItemBurnTime(itemStack) / (50.0 * rodType.fuelConsumptionMultiplier * getThrottleMultiplier()));
			if (fuelBurnValue > 0) {
				if (itemStack.getCount() == 1) {
					ItemStack containerItem = itemStack.getItem().getContainerItem(itemStack);
					itemImportInventory.setStackInSlot(slotIndex, containerItem);
				} else {
					itemStack.shrink(1);
					itemImportInventory.setStackInSlot(slotIndex, itemStack);
				}
				return fuelBurnValue;
			}
		}
		return 0;
	}

	private void setActive(boolean active) {
		this.isActive = active;
		if (!getWorld().isRemote) {
			writeCustomData(100, buf -> buf.writeBoolean(isActive));
			markDirty();
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("CurrentTemperature", currentTemperature);
		data.setInteger("FuelBurnTicksLeft", fuelBurnTicksLeft);
		data.setBoolean("HasNoWater", hasNoWater);
		data.setInteger("ThrottlePercentage", throttlePercentage);
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.currentTemperature = data.getInteger("CurrentTemperature");
		this.fuelBurnTicksLeft = data.getInteger("FuelBurnTicksLeft");
		this.hasNoWater = data.getBoolean("HasNoWater");
		if (data.hasKey("ThrottlePercentage")) {
			this.throttlePercentage = data.getInteger("ThrottlePercentage");
		}
		this.isActive = fuelBurnTicksLeft > 0;
	}


}
