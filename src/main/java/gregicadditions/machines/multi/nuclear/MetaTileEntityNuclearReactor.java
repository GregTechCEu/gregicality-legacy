package gregicadditions.machines.multi.nuclear;

import gregicadditions.fluid.GAMetaFluids;
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
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.MetaFluids;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.unification.material.Materials.Lead;


public class MetaTileEntityNuclearReactor extends RecipeMapMultiblockController {

    public enum RodType {
        THORIUM(GARecipeMaps.NUCLEAR_REACTOR_RECIPES, 4500, 3.0f, 31, 2000,
                MetaBlocks.COMPRESSED.get(Materials.Thorium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Thorium).variantProperty, Materials.Thorium)),

        URANIUM(GARecipeMaps.BOILING_URANIUM_REACTOR_RECIPES, 9700, 5.4f, 32, 4000,
                MetaBlocks.COMPRESSED.get(Materials.Uranium235).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Materials.Uranium235).variantProperty, Materials.Uranium235)),

        PLUTONIUM(GARecipeMaps.BOILING_PLUTONIUM_REACTOR_RECIPES, 14000, 9.0f, 32, 6000,
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


    public MetaTileEntityNuclearReactor(ResourceLocation metaTileEntityId, RodType rodType) {
        super(metaTileEntityId, rodType.recipeMap);
        this.rodType = rodType;
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityNuclearReactor(metaTileEntityId, rodType);
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    @Override
    protected BlockPattern createStructurePattern() {
        return rodType == null ? null : FactoryBlockPattern.start()
                .aisle("YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .aisle("YYY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YYY")
                .aisle("YSY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('R', statePredicate(rodType.casingState))
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.1"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.2"));
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Lead);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(Lead);
    }


    private int currentTemperature = 300;
    public final RodType rodType;
    private boolean notEnoughCoolant = true;
    private FluidMaterial coolant;
    private Fluid hotCoolant;
    private int recipeBaseHeat;
    private boolean overheat = false;


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.currentTemperature = 300; //reset temperature
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.temperature", this.currentTemperature - 275, hotCoolant != null ? hotCoolant.getTemperature() : 100));
            if (coolant != null)
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.coolant", coolant.getLocalizedName()));

            if (overheat) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.universal.overheat").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }

        super.addDisplayText(textList);
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        recipeBaseHeat = recipe.getIntegerProperty("base_heat_production");
        return true;
    }

    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();

        if (!recipeMapWorkable.isActive() || recipeMapWorkable.isHasNotEnoughEnergy() || overheat) {
            if (currentTemperature > 300)
                --currentTemperature;
            else
                overheat = false;
            return;
        }

        if (getTimer() % 20 == 0) {
            this.currentTemperature += recipeBaseHeat;

            FluidStack fluidStack = inputFluidInventory.drain(Integer.MAX_VALUE, false);
            if (fluidStack != null) {
                coolant = MetaFluids.getMaterialFromFluid(fluidStack.getFluid());
                hotCoolant = GAMetaFluids.HOT_FLUIDS.get(coolant);
                if (hotCoolant != null) {
                    fluidStack = inputFluidInventory.drain(Integer.MAX_VALUE, true);
                    int extraHeat = currentTemperature - hotCoolant.getTemperature();

                    if (extraHeat > 0) {
                        if (extraHeat >= fluidStack.amount / 1000) {
                            outputFluidInventory.fill(GAMetaFluids.getHotFluid(coolant, fluidStack.amount), true);
                        } else {
                            outputFluidInventory.fill(GAMetaFluids.getHotFluid(coolant, extraHeat * 1000), true);
                            outputFluidInventory.fill(coolant.getFluid(fluidStack.amount - extraHeat * 1000), true);
                        }
                    }
                    currentTemperature = Math.min(300, currentTemperature - fluidStack.amount / 1000);
                    if (currentTemperature > hotCoolant.getTemperature()) {
                        notEnoughCoolant = false;
                        //void recipe
                        return;
                    }
                }
            }

            if (currentTemperature > 373) {
                overheat = true;
                notEnoughCoolant = false;
                //void recipe
                return;
            }
        }


    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("CurrentTemperature", currentTemperature);
        data.setBoolean("overheat", overheat);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.currentTemperature = data.getInteger("CurrentTemperature");
        this.overheat = data.getBoolean("overheat");
    }


}
