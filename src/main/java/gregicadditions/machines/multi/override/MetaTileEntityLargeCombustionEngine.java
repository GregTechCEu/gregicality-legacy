package gregicadditions.machines.multi.override;

import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.machines.multi.GAFuelRecipeLogic;
import gregicadditions.machines.multi.GAFueledMultiblockController;
import gregicadditions.machines.multi.IMaintenance;
import gregicadditions.machines.multi.MultiUtils;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static gregtech.api.render.Textures.STABLE_TITANIUM_CASING;

public class MetaTileEntityLargeCombustionEngine extends GAFueledMultiblockController implements IMaintenance {

	public MetaTileEntityLargeCombustionEngine(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.DIESEL_GENERATOR_FUELS, GAValues.V[GAValues.EV]);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityLargeCombustionEngine(this.metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XDX", "XXX")
				.aisle("XHX", "HGH", "XHX")
				.aisle("XHX", "HGH", "XHX")
				.aisle("AAA", "AYA", "AAA")
				.where('X', statePredicate(this.getCasingState()))
				.where('G', statePredicate(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX)))
				.where('H', statePredicate(this.getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_HATCH)))
				.where('D', abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY))
				.where('A', this.intakeCasingPredicate())
				.where('Y', this.selfPredicate())
				.build();
	}

	protected Predicate<BlockWorldState> intakeCasingPredicate() {
		IBlockState blockState = MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING);
		return (blockWorldState) -> {
			if (blockWorldState.getBlockState() != blockState) {
				return false;
			} else {
				IBlockState offsetState = blockWorldState.getOffsetState(this.getFrontFacing());
				return offsetState.getBlock().isAir(offsetState, blockWorldState.getWorld(), blockWorldState.getPos());
			}
		};
	}

	@Override
	protected FuelRecipeLogic createWorkable(long maxVoltage) {
		return new LargeCombustionEngineWorkable(this, this.recipeMap, () -> {
			return this.energyContainer;
		}, () -> {
			return this.importFluidHandler;
		}, maxVoltage);
	}

	protected void addDisplayText(List<ITextComponent> textList) {
		super.addDisplayText(textList);
		if (this.isStructureFormed() && !this.hasProblems()) {
			FluidStack lubricantStack = this.importFluidHandler.drain(Materials.Lubricant.getFluid(Integer.MAX_VALUE), false);
			FluidStack oxygenStack = this.importFluidHandler.drain(Materials.Oxygen.getFluid(Integer.MAX_VALUE), false);
			FluidStack fuelStack = ((LargeCombustionEngineWorkable)this.workableHandler).getFuelStack();
			int lubricantAmount = lubricantStack == null ? 0 : lubricantStack.amount;
			int oxygenAmount = oxygenStack == null ? 0 : oxygenStack.amount;
			int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;
			ITextComponent fuelName = new TextComponentTranslation(fuelAmount == 0 ? "gregtech.fluid.empty" : fuelStack.getUnlocalizedName());
			textList.add(new TextComponentTranslation("gregtech.multiblock.diesel_engine.lubricant_amount", lubricantAmount));
			textList.add(new TextComponentTranslation("gregtech.multiblock.diesel_engine.fuel_amount", fuelAmount, fuelName));
			textList.add(new TextComponentTranslation("gregtech.multiblock.diesel_engine.oxygen_amount", oxygenAmount));
			textList.add(new TextComponentTranslation(oxygenAmount >= 2 ? "gregtech.multiblock.diesel_engine.oxygen_boosted" : "gregtech.multiblock.diesel_engine.supply_oxygen_to_boost", new Object[0]));
		}
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return MultiUtils.getConfigCasingTexture("", STABLE_TITANIUM_CASING);
	}

	public IBlockState getCasingState() {
		return MultiUtils.getConfigCasing("", MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE));
	}

	@Nonnull
	@Override
	protected OrientedOverlayRenderer getFrontOverlay() {
		return Textures.DIESEL_ENGINE_OVERLAY;
	}

	public static class LargeCombustionEngineWorkable extends GAFuelRecipeLogic {
		private static final int maxCycleLength = 20;
		private int currentCycle = 0;
		private boolean isUsingOxygen = false;

		public LargeCombustionEngineWorkable(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
			super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
		}

		public FluidStack getFuelStack() {
			if (this.previousRecipe == null) {
				return null;
			} else {
				FluidStack fuelStack = this.previousRecipe.getRecipeFluid();
				return this.fluidTank.get().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
			}
		}

		protected boolean checkRecipe(FuelRecipe recipe) {
			if (!((MetaTileEntityLargeCombustionEngine) this.metaTileEntity).hasProblems())
				return false;

			FluidStack lubricantStack = Materials.Lubricant.getFluid(2);
			FluidStack drainStack = this.fluidTank.get().drain(lubricantStack, false);
			return drainStack != null && drainStack.amount >= 2 || this.currentCycle < 20;
		}

		protected int calculateFuelAmount(FuelRecipe currentRecipe) {
			FluidStack oxygenStack = Materials.Oxygen.getFluid(2);
			FluidStack drainOxygenStack = this.fluidTank.get().drain(oxygenStack, false);
			this.isUsingOxygen = drainOxygenStack != null && drainOxygenStack.amount >= 2;
			return super.calculateFuelAmount(currentRecipe) * (this.isUsingOxygen ? 2 : 1);
		}

		protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
			FluidStack oxygenStack;
			if (this.currentCycle >= maxCycleLength) {
				oxygenStack = Materials.Lubricant.getFluid(2);
				this.fluidTank.get().drain(oxygenStack, true);
				this.currentCycle = 0;
			} else {
				++this.currentCycle;
			}

			if (this.isUsingOxygen) {
				oxygenStack = Materials.Oxygen.getFluid(2);
				this.fluidTank.get().drain(oxygenStack, true);
			}

			return this.maxVoltage * (long)(this.isUsingOxygen ? 3 : 1);
		}

		public NBTTagCompound serializeNBT() {
			NBTTagCompound compound = super.serializeNBT();
			compound.setInteger("Cycle", this.currentCycle);
			return compound;
		}

		public void deserializeNBT(NBTTagCompound compound) {
			super.deserializeNBT(compound);
			this.currentCycle = compound.getInteger("Cycle");
		}
	}
}
