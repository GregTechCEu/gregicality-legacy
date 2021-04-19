package gregicadditions.machines.multi.advance;


import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GAEnergyContainerHandler;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.fusion.GACryostatCasing;
import gregicadditions.item.fusion.GADivertorCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.fusion.GAVacuumCasing;
import gregicadditions.machines.multi.multiblockpart.GAMetaTileEntityEnergyHatch;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
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
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.*;

public class TileEntityAdvFusionReactor extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS};

    private int tier;
    private int coilTier;
    private int vacuumTier;
    private int divertorTier;
    private boolean canWork;

    private static List<Fluid> HOT = Arrays.asList(SupercriticalSteam.fluid, SupercriticalDeuterium.fluid,
            SupercriticalSodiumPotassiumAlloy.fluid, SupercriticalSodium.fluid,
            SupercriticalFLiNaK.fluid, SupercriticalFLiBe.fluid, SupercriticalLeadBismuthEutectic.fluid);

    private EnergyContainerList inputEnergyContainers;
    private int heat = 0;


    public TileEntityAdvFusionReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.ADV_FUSION_RECIPES);
        this.recipeMapWorkable = new AdvFusionRecipeLogic(this);
        this.energyContainer = new GAEnergyContainerHandler(this, Integer.MAX_VALUE, 0, 0, 0, 0) {
            @Override
            public String getName() {
                return "EnergyContainerInternal";
            }
        };
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "###############", "#####ccCcc#####", "#####ccCcc#####", "###############", "###############")
                .aisle("###############", "#######C#######", "###ccvvvvvcc###", "###ccvvvvvcc###", "#######C#######", "###############")
                .aisle("#######C#######", "##C##ddddd##C##", "##Cvv#####vvC##", "##Cvv#####vvC##", "##C##bbbbb##C##", "#######C#######")
                .aisle("###C###C###C###", "###ddddddddd###", "#cv#########vc#", "#cv#########vc#", "###bbbbbbbbb###", "###C###C###C###")
                .aisle("####C#####C####", "###ddd#C#ddd###", "#cv###vvv###vc#", "#cv###vvv###vc#", "###bbb#C#bbb###", "####C#####C####")
                .aisle("###############", "##dddC###Cddd##", "cv###v#C#v###vc", "cv###v#C#v###vc", "##bbbC###Cbbb##", "###############")
                .aisle("######XXX######", "##dd##CCC##dd##", "cv##v#CCC#v##vc", "cv##v#CCC#v##vc", "##bb##CCC##bb##", "######XXX######")
                .aisle("##CC##XXX##CC##", "#CddC#CCC#CddC#", "Cv##vCCCCCv##vC", "Cv##vCCCCCv##vC", "#CbbC#CCC#CbbC#", "##CC##XXX##CC##")
                .aisle("######XXX######", "##dd##CCC##dd##", "cv##v#CCC#v##vc", "cv##v#CCC#v##vc", "##bb##CCC##bb##", "######XXX######")
                .aisle("###############", "##dddC###Cddd##", "cv###v#C#v###vc", "cv###v#C#v###vc", "##bbbC###Cbbb##", "###############")
                .aisle("####C#####C####", "###ddd#C#ddd###", "#cv###vvv###vc#", "#cv###vvv###vc#", "###bbb#C#bbb###", "####C#####C####")
                .aisle("###C###C###C###", "###ddddddddd###", "#cv#########vc#", "#cv#########vc#", "###bbbbbbbbb###", "###C###C###C###")
                .aisle("#######C#######", "##C##ddddd##C##", "##Cvv#####vvC##", "##Cvv#####vvC##", "##C##bbbbb##C##", "#######C#######")
                .aisle("###############", "#######S#######", "###ccvvvvvcc###", "###ccvvvvvcc###", "#######C#######", "###############")
                .aisle("###############", "###############", "#####ccCcc#####", "#####ccCcc#####", "###############", "###############")
                .where('S', selfPredicate())
                .where('#', (tile) -> true)
                .where('C', coilPredicate())
                .where('X', statePredicate(getCasingState()))
                .where('d', divertorPredicate().or(tilePredicate((state, tile) -> tile instanceof GAMetaTileEntityEnergyHatch)).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('v', vacuumPredicate().or(tilePredicate((state, tile) -> tile instanceof GAMetaTileEntityEnergyHatch)).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('c', cryostatPredicate().or(tilePredicate((state, tile) -> tile instanceof GAMetaTileEntityEnergyHatch)).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('b', statePredicate(GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_BLANKET)).or(tilePredicate((state, tile) -> tile instanceof GAMetaTileEntityEnergyHatch)).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .setAmountAtMost('E', 16)
                .where('E', tilePredicate((state, tile) -> tile instanceof GAMetaTileEntityEnergyHatch))
                .setAmountAtMost('I', 3)
                .where('I', abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS))
                .setAmountAtMost('i', 2)
                .where('i', abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.FUSION_TEXTURE;
    }

    private IBlockState getCasingState() {
        return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.ADV_FUSION_CASING);
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (this.inputEnergyContainers.getEnergyStored() > 0) {
                long energyAdded = this.energyContainer.addEnergy(this.inputEnergyContainers.getEnergyStored());
                if (energyAdded > 0) this.inputEnergyContainers.removeEnergy(energyAdded);
            }
            super.updateFormedValid();
        }
    }


    public static Predicate<BlockWorldState> cryostatPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GACryostatCasing)) {
                return false;
            } else {
                GACryostatCasing cryostatCasing = (GACryostatCasing) blockState.getBlock();
                GACryostatCasing.CasingType tieredCasingType = cryostatCasing.getState(blockState);
                GACryostatCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Cryostat", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> divertorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GADivertorCasing)) {
                return false;
            } else {
                GADivertorCasing divertorCasing = (GADivertorCasing) blockState.getBlock();
                GADivertorCasing.CasingType tieredCasingType = divertorCasing.getState(blockState);
                GADivertorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Divertor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> vacuumPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAVacuumCasing)) {
                return false;
            } else {
                GAVacuumCasing vacuumCasing = (GAVacuumCasing) blockState.getBlock();
                GAVacuumCasing.CasingType tieredCasingType = vacuumCasing.getState(blockState);
                GAVacuumCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Vacuum", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }


    public static Predicate<BlockWorldState> coilPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAFusionCasing)) {
                return false;
            } else {
                GAFusionCasing coil = (GAFusionCasing) blockState.getBlock();
                GAFusionCasing.CasingType tieredCasingType = coil.getState(blockState);
                if (tieredCasingType.getName().startsWith("adv_fusion_coil")) {
                    GAFusionCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Coil", tieredCasingType);
                    return currentCasing.getName().equals(tieredCasingType.getName());
                } else {
                    return false;
                }
            }
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        GAFusionCasing.CasingType coil = context.getOrDefault("Coil", GAFusionCasing.CasingType.ADV_FUSION_COIL_1);
        coilTier = Integer.parseInt(coil.getName().substring(coil.getName().length() - 1));
        vacuumTier = context.getOrDefault("Vacuum", GAVacuumCasing.CasingType.VACUUM_1).getTier();
        divertorTier = context.getOrDefault("Divertor", GADivertorCasing.CasingType.DIVERTOR_1).getTier();
        int cryostatTier = context.getOrDefault("Cryostat", GACryostatCasing.CasingType.CRYOSTAT_1).getTier();
        canWork = Math.min(Math.min(vacuumTier, divertorTier), cryostatTier) >= coilTier;
        this.tier = coilTier + GAValues.UHV - 1;
        long energyStored = this.energyContainer.getEnergyStored();
        this.initializeAbilities();
        ((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
    }

    private void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        List<IEnergyContainer> energyInputs = getAbilities(MultiblockAbility.INPUT_ENERGY);
        this.inputEnergyContainers = new EnergyContainerList(energyInputs);
        long euCapacity = 0;
        for (IEnergyContainer energyContainer : energyInputs) {
            euCapacity += 10000000L * (long) Math.pow(2, Math.min(GAUtility.getTierByVoltage(energyContainer.getInputVoltage()), tier) - GAValues.LuV);
        }
        this.energyContainer = new GAEnergyContainerHandler(this, euCapacity, GAValues.V[tier], 0, 0, 0) {
            @Override
            public String getName() {
                return "EnergyContainerInternal";
            }
        };
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.coilTier = 0;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityAdvFusionReactor(metaTileEntityId);
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int requiredCoilTier = recipe.getIntegerProperty("coil_tier");
        return canWork && this.coilTier >= requiredCoilTier;
    }


    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (!this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure").setStyle(new Style().setColor(TextFormatting.RED)));
        }
        if (this.isStructureFormed()) {
            if (!this.canWork) {
                textList.add(new TextComponentTranslation("gregicality.multiblock.invalid_configuraion.1").setStyle(new Style().setColor(TextFormatting.RED)));
                textList.add(new TextComponentTranslation("gregicality.multiblock.invalid_configuraion.2"));
            } else {
                if (!this.recipeMapWorkable.isWorkingEnabled()) {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));
                } else if (this.recipeMapWorkable.isActive()) {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
                    int currentProgress;
                    if (energyContainer.getEnergyCapacity() > 0) {
                        currentProgress = (int) (this.recipeMapWorkable.getProgressPercent() * 100.0D);
                        textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
                    } else {
                        currentProgress = -this.recipeMapWorkable.getRecipeEUt();
                        textList.add(new TextComponentTranslation("gregtech.multiblock.generation_eu", currentProgress));
                    }
                } else {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.idling"));
                }

                if (this.recipeMapWorkable.isHasNotEnoughEnergy()) {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy").setStyle(new Style().setColor(TextFormatting.RED)));
                }
                textList.add(new TextComponentString("EU: " + this.energyContainer.getEnergyStored() + " / " + this.energyContainer.getEnergyCapacity()));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.fusion_reactor.heat", this.heat));
            }
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        this.getBaseTexture(null).render(renderState, translation, pipeline);
        ClientHandler.FUSION_REACTOR_OVERLAY.render(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive());
    }

    public class AdvFusionRecipeLogic extends GAMultiblockRecipeLogic {


        public AdvFusionRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override

        public void updateWorkable() {
            super.updateWorkable();
            if (!isActive && heat > 0) {
                heat = heat <= 10000 ? 0 : (heat - 10000);
            }
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            RecipeBuilder<?> newRecipe;
            if (recipe == null || (long) recipe.getProperty("eu_to_start") > energyContainer.getEnergyCapacity()) {
                return null;
            } else {
                int recipeTier = recipe.getIntegerProperty("coil_tier");
                int coilTierDifference = coilTier - recipeTier;
                int vacuumTierDifference = vacuumTier - recipeTier;
                int divertorTierDifference = divertorTier - recipeTier;
                newRecipe = recipeMap.recipeBuilder().duration((int) Math.max(1.0, recipe.getDuration() * (1 - GAConfig.multis.advFusion.coilDurationDiscount * coilTierDifference)));
                newRecipe.EUt((int) Math.max(1, recipe.getEUt() * (1 - vacuumTierDifference * GAConfig.multis.advFusion.vacuumEnergyDecrease)));

                newRecipe.fluidInputs(recipe.getFluidInputs().get(0), recipe.getFluidInputs().get(1));
                FluidStack newOutput = recipe.getFluidOutputs().get(0);
                newOutput.amount = (int) (newOutput.amount * (1 + divertorTierDifference * GAConfig.multis.advFusion.divertorOutputIncrease));
                newRecipe.fluidOutputs(newOutput);

                if (recipe.getFluidInputs().size() == 3) {

                    FluidStack newFluid = recipe.getFluidInputs().get(2).copy();
                    newFluid.amount = (int) (newFluid.amount * (1 + vacuumTierDifference * GAConfig.multis.advFusion.vacuumCoolantIncrease));
                    newRecipe.fluidInputs(newFluid);

                    newOutput = recipe.getFluidOutputs().get(1).copy();
                    newOutput.amount = (int) (newOutput.amount * (1 + divertorTierDifference * GAConfig.multis.advFusion.vacuumCoolantIncrease));
                    newRecipe.fluidOutputs(newOutput);
                }
            }
            return newRecipe.build().getResult();
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
            long heatDiff = ((long) recipe.getProperty("eu_to_start")) - (long) heat;
            if (heatDiff <= 0) {
                return super.setupAndConsumeRecipeInputs(recipe);
            }
            if (energyContainer.getEnergyStored() < heatDiff || !super.setupAndConsumeRecipeInputs(recipe)) {
                return false;
            }
            energyContainer.removeEnergy(heatDiff);
            heat += heatDiff;
            return true;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = super.serializeNBT();
            tag.setInteger("Heat", heat);
            return tag;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            super.deserializeNBT(compound);
            heat = compound.getInteger("Heat");
        }
    }

}
