package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityVacuumFreezer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static gregicadditions.GAMaterials.IncoloyMA956;

public class TileEntityCryogenicFreezer extends MetaTileEntityVacuumFreezer {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    private static final int RECIPE_MULTIPLIER = GAConfig.multis.cryogenicFreezer.recipeMultiplier;

    private static final int DURATION_DECREASE_FACTOR = GAConfig.multis.cryogenicFreezer.durationDecreaseFactor;

    public TileEntityCryogenicFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.recipeMapWorkable = new CryogenicFreezerRecipeLogic(this);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityCryogenicFreezer(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .setAmountAtLeast('L', 14)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .build();
    }


    @Override
    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(IncoloyMA956);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.IncoloyMA956);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.cryogenic_freezer.description"));
    }


    public class CryogenicFreezerRecipeLogic extends MultiblockRecipeLogic {

        public CryogenicFreezerRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
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
            this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, recipe, RECIPE_MULTIPLIER);
            RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
                    .inputsIngredients(newRecipeInputs)
                    .fluidInputs(newFluidInputs)
                    .outputs(outputI)
                    .fluidOutputs(outputF)
                    .EUt((int) (recipe.getEUt()))
                    .duration((int) (recipe.getDuration() * ( DURATION_DECREASE_FACTOR / 100)));
            return newRecipe.build().getResult();
        }

        @Override
        protected boolean drawEnergy(int recipeEUt) {
            boolean enoughEnergy = super.drawEnergy(recipeEUt);
            Optional<IFluidTank> fluidTank =
                    getInputFluidInventory().getFluidTanks().stream()
                            .filter(iFluidTank -> iFluidTank.getFluid() != null)
                            .filter(iFluidTank -> iFluidTank.getFluid().isFluidEqual(GAMaterials.Cryotheum.getFluid(1)))
                            .findFirst();
            if (fluidTank.isPresent()) {
                IFluidTank tank = fluidTank.get();
                if (tank.getCapacity() > 1 && enoughEnergy) {
                    tank.drain(1, true);
                    return true;
                }
            }
            return false;
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
