package gregicadditions.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.MultiRecipesTrait;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.*;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityDistillationTower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.BabbittAlloy;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class TileEntityAdvancedDistillationTower extends MetaTileEntityDistillationTower {

    public RecipeMap<?> recipeMap;

    private static final int DISTILLER_MULTIPLIER = GAConfig.multis.distillationTower.distillerMultiplier;
    private static final int DISTILLATION_MULTIPLIER = GAConfig.multis.distillationTower.distillationMultiplier;

    private static final RecipeMap<?>[] possibleRecipe = new RecipeMap<?>[]{
            RecipeMaps.DISTILLERY_RECIPES,
            RecipeMaps.DISTILLATION_RECIPES
    };

    private final MultiRecipesTrait multiRecipesTrait;

    public TileEntityAdvancedDistillationTower(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId);
        this.recipeMap = recipeMap;
        this.recipeMapWorkable = new AdvancedDistillationRecipeLogic(this, recipeMap);
        this.multiRecipesTrait = new MultiRecipesTrait(this, possibleRecipe);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityAdvancedDistillationTower(metaTileEntityId, multiRecipesTrait.getRecipes()[multiRecipesTrait.getCurrentRecipe()]);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<BlockWorldState> fluidExportPredicate = countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS));
        Predicate<PatternMatchContext> exactlyOneHatch = context -> context.getInt("HatchesAmount") == 1;
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("CSY", "YZY", "YYY")
                .aisle("XXX", "X#X", "XXX").setRepeatable(24)
                .aisle("XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('C', abilityPartPredicate(MultiblockAbility.IMPORT_ITEMS))
                .where('Z', abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS))
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY)))
                .where('X', fluidExportPredicate.or(statePredicate(getCasingState())))
                .where('#', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV)))
                .validateLayer(1, exactlyOneHatch)
                .validateLayer(2, exactlyOneHatch)
                .build();
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(BabbittAlloy);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(BabbittAlloy);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description1"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description2"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description3"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description4"));
    }


    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.recipe", new TextComponentTranslation("recipemap." + this.recipeMap.getUnlocalizedName() + ".name")));
        textList.add(new TextComponentTranslation("gregtech.multiblock.advanced_distillation_tower.multiplier", ((AdvancedDistillationRecipeLogic) (this.recipeMapWorkable)).multiplier));
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        RecipeMap<?> recipe = multiRecipesTrait.getNextRecipe();
        if (recipe == null) {
            return false;
        }
        recipeMap = recipe;
        recipeMapWorkable = new AdvancedDistillationRecipeLogic(this, recipeMap);
        return true;
    }


    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.recipeMap = multiRecipesTrait.getRecipes()[multiRecipesTrait.getCurrentRecipe()];
        this.recipeMapWorkable = new AdvancedDistillationRecipeLogic(this, recipeMap);
    }


    public static class AdvancedDistillationRecipeLogic extends MultiblockRecipeLogic {

        public RecipeMap<?> recipeMap;
        public int multiplier;

        public AdvancedDistillationRecipeLogic(RecipeMapMultiblockController tileEntity, RecipeMap<?> recipeMap) {
            super(tileEntity);
            this.recipeMap = recipeMap;
            if (recipeMap == RecipeMaps.DISTILLATION_RECIPES)
                multiplier = DISTILLATION_MULTIPLIER;
            if (recipeMap == RecipeMaps.DISTILLERY_RECIPES)
                multiplier = DISTILLER_MULTIPLIER;
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            if (recipe == null) {
                return null;
            }
            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<FluidStack> newFluidInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            List<FluidStack> outputF = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, recipe, multiplier);
            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) (recipe.getEUt()))
                    .duration((int) (recipe.getDuration()));
            return newRecipe.build().getResult();
        }

        protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<FluidStack> newFluidInputs, List<ItemStack> outputI, List<FluidStack> outputF, Recipe r, int multiplier) {
            for (CountableIngredient ci : r.getInputs()) {
                CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * multiplier);
                newRecipeInputs.add(newIngredient);
            }
            for (FluidStack fs : r.getFluidInputs()) {
                FluidStack newFluid = new FluidStack(fs.getFluid(), fs.amount * multiplier);
                newFluidInputs.add(newFluid);
            }
            for (ItemStack s : r.getOutputs()) {
                int num = s.getCount() * multiplier;
                ItemStack itemCopy = s.copy();
                itemCopy.setCount(num);
                outputI.add(itemCopy);
            }
            for (FluidStack f : r.getFluidOutputs()) {
                int fluidNum = f.amount * multiplier;
                FluidStack fluidCopy = f.copy();
                fluidCopy.amount = fluidNum;
                outputF.add(fluidCopy);
            }
        }
    }
}
