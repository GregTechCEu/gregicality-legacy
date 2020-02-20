package gregicadditions.machines;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.BACK;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.DOWN;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.LEFT;

import java.util.List;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.client.ClientHandler;
import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class TileEntityFusionReactor extends RecipeMapMultiblockController {
	private final int tier;
	private EnergyContainerList inputEnergyContainers;

	public TileEntityFusionReactor(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, RecipeMaps.FUSION_RECIPES);
		this.recipeMapWorkable = new MultiblockRecipeLogic(this) {
			@Override
			protected int getOverclockingTier(long voltage) {
				return 0;
			}
		};
		this.tier = tier;
		this.reinitializeStructurePattern();
		this.energyContainer = new EnergyContainerHandler(this, Integer.MAX_VALUE, 0, 0, 0, 0) {
			@Override
			public String getName() {
				return "EnergyContainerInternal";
			}
		};
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityFusionReactor(metaTileEntityId, tier);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		FactoryBlockPattern.start();
		return FactoryBlockPattern.start(LEFT, DOWN, BACK).aisle("###############", "######OCO######", "###############").aisle("######ICI######", "####CCcccCC####", "######ICI######").aisle("####CC###CC####", "###EccOCOccE###", "####CC###CC####").aisle("###C#######C###", "##EcEC###CEcE##", "###C#######C###").aisle("##C#########C##", "#CcE#######EcC#", "##C#########C##").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("#I###########I#", "OcO#########OcO", "#I###########I#").aisle("#C###########C#", "CcC#########CcC", "#C###########C#").aisle("#I###########I#", "OcO#########OcO", "#I###########I#").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("##C#########C##", "#CcE#######EcC#", "##C#########C##").aisle("###C#######C###", "##EcEC###CEcE##", "###C#######C###").aisle("####CC###CC####", "###EccOCOccE###", "####CC###CC####").aisle("######ICI######", "####CCcccCC####", "######ICI######").aisle("###############", "######OSO######", "###############").where('S', selfPredicate()).where('C', statePredicate(getCasingState())).where('c', statePredicate(getCoilState())).where('O', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS))).where('E', statePredicate(getCasingState()).or(tilePredicate((state, tile) -> {
			for (int i = tier; i < GTValues.V.length; i++) {
				if (tile.metaTileEntityId.equals(MetaTileEntities.ENERGY_INPUT_HATCH[i].metaTileEntityId)) return true;
			}
			return false;
		}))).where('I', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS))).where('#', (tile) -> true).build();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		return ClientHandler.FUSION_TEXTURE;
	}

	private IBlockState getCasingState() {

		switch (tier) {
		case 6:
			return MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.LuV);
		case 7:
			return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING);
		case 8:
		default:
			return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING_MK2);
		}
	}

	private IBlockState getCoilState() {

		switch (tier) {
		case 6:
			return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR);
		case 7:
		case 8:
		default:
			return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL);
		}
	}

	private long getMaxEU() {
		List<IEnergyContainer> eConts = ObfuscationReflectionHelper.getPrivateValue(EnergyContainerList.class, this.inputEnergyContainers, "energyContainerList");
		return eConts.size() * 10000000L * (long) Math.pow(2, tier - 6);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		long energyStored = this.energyContainer.getEnergyStored();
		super.formStructure(context);
		this.initializeAbilities();
		((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
	}

	private void initializeAbilities() {
		this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
		this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
		this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
		this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
		this.inputEnergyContainers = new EnergyContainerList(this.getAbilities(MultiblockAbility.INPUT_ENERGY));
		this.energyContainer = new EnergyContainerHandler(this, getMaxEU(), GTValues.V[tier], 0, 0, 0) {
			@Override
			public String getName() {
				return "EnergyContainerInternal";
			}
		};
	}

	@Override
	protected void updateFormedValid() {
		if (!getWorld().isRemote) {
			if (this.inputEnergyContainers.getEnergyStored() > 0) {
				long energyAdded = this.energyContainer.addEnergy(this.inputEnergyContainers.getEnergyStored());
				if (energyAdded > 0) this.inputEnergyContainers.addEnergy(-energyAdded);
			}

			if (this.recipeMapWorkable.isWorkingEnabled()) {
				if (this.recipeMapWorkable.isHasNotEnoughEnergy()) {
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "progressTime");
					recipeMapWorkable.setMaxProgress(0);
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "recipeEUt");
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "fluidOutputs");
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "itemOutputs");
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, false, "hasNotEnoughEnergy");
					ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, true, "wasActiveAndNeedsUpdate");
					return;
				}
				Recipe previousRecipe = ObfuscationReflectionHelper.getPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, "previousRecipe");
				this.recipeMapWorkable.updateWorkable();
				Recipe recipe = ObfuscationReflectionHelper.getPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, "previousRecipe");
				if (previousRecipe != recipe) {
					if (recipe != null) {
						long euToStart = ((Integer) recipe.getProperty("eu_to_start")).intValue();
						if (this.energyContainer.getEnergyStored() < euToStart) {
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "progressTime");
							recipeMapWorkable.setMaxProgress(0);
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "recipeEUt");
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "fluidOutputs");
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "itemOutputs");
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, false, "hasNotEnoughEnergy");
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, false, "hasNotEnoughEnergy");
							ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, true, "wasActiveAndNeedsUpdate");
						} else {
							this.energyContainer.addEnergy(-euToStart);
						}
					} else {
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "progressTime");
						recipeMapWorkable.setMaxProgress(0);
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, 0, "recipeEUt");
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "fluidOutputs");
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, null, "itemOutputs");
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, false, "hasNotEnoughEnergy");
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, false, "hasNotEnoughEnergy");
						ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, true, "wasActiveAndNeedsUpdate");
					}
				}
			}

		}
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		if (!this.isStructureFormed()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure", new Object[0]).setStyle(new Style().setColor(TextFormatting.RED)));
		}
		if (this.isStructureFormed()) {
			if (!this.recipeMapWorkable.isWorkingEnabled()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused", new Object[0]));
			} else if (this.recipeMapWorkable.isActive()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.running", new Object[0]));
				int currentProgress;
				if (energyContainer.getEnergyCapacity() > 0) {
					currentProgress = (int) (this.recipeMapWorkable.getProgressPercent() * 100.0D);
					textList.add(new TextComponentTranslation("gregtech.multiblock.progress", new Object[] { currentProgress }));
				} else {
					currentProgress = -this.recipeMapWorkable.getRecipeEUt();
					textList.add(new TextComponentTranslation("gregtech.multiblock.generation_eu", new Object[] { currentProgress }));
				}
			} else {
				textList.add(new TextComponentTranslation("gregtech.multiblock.idling", new Object[0]));
			}

			if (this.recipeMapWorkable.isHasNotEnoughEnergy()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy", new Object[0]).setStyle(new Style().setColor(TextFormatting.RED)));
			}
		}

		textList.add(new TextComponentString("EU: " + this.energyContainer.getEnergyStored() + " / " + this.energyContainer.getEnergyCapacity()));
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		this.getBaseTexture(null).render(renderState, translation, pipeline);
		ClientHandler.FUSION_REACTOR_OVERLAY.render(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive());
	}
}
