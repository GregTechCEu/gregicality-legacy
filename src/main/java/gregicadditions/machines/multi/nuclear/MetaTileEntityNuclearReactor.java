package gregicadditions.machines.multi.nuclear;

import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaBlocks;
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
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.MetaFluids;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;


public class MetaTileEntityNuclearReactor extends RecipeMapMultiblockController {

    public enum RodType implements IStringSerializable {
        THORIUM(110,
                MetaBlocks.COMPRESSED.get(Thorium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Thorium).variantProperty, Thorium)),
        URANIUM(115,
                MetaBlocks.COMPRESSED.get(Uranium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Uranium).variantProperty, Uranium)),
        PLUTONIUM(120,
                MetaBlocks.COMPRESSED.get(Plutonium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Plutonium).variantProperty, Plutonium)),
        AMERICIUM(127,
                MetaBlocks.COMPRESSED.get(Americium).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Americium).variantProperty, Americium)),
        CURIUM(135,
                MetaBlocks.COMPRESSED.get(Curium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Curium.getMaterial()).variantProperty, Curium.getMaterial())),
        BERKELIUM(145,
                MetaBlocks.COMPRESSED.get(Berkelium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Berkelium.getMaterial()).variantProperty, Berkelium.getMaterial())),
        CALIFORNIUM(155,
                MetaBlocks.COMPRESSED.get(Californium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Californium.getMaterial()).variantProperty, Californium.getMaterial())),
        EINSTEINIUM(170,
                MetaBlocks.COMPRESSED.get(Einsteinium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Einsteinium.getMaterial()).variantProperty, Einsteinium.getMaterial())),
        FERMIUM(185,
                MetaBlocks.COMPRESSED.get(Fermium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Fermium.getMaterial()).variantProperty, Fermium.getMaterial())),
        MENDELEVIUM(200,
                MetaBlocks.COMPRESSED.get(Mendelevium.getMaterial()).getDefaultState().withProperty(MetaBlocks.COMPRESSED.get(Mendelevium.getMaterial()).variantProperty, Mendelevium.getMaterial()));


        public final int maxTemperature;
        public final IBlockState casingState;

        RodType(int maxTemperature, IBlockState casingState) {
            this.maxTemperature = maxTemperature;
            this.casingState = casingState;
        }

        @Override
        public String getName() {
            return name();
        }
    }


    public MetaTileEntityNuclearReactor(ResourceLocation metaTileEntityId, RecipeMap<?> recipe) {
        super(metaTileEntityId, recipe);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityNuclearReactor(metaTileEntityId, recipeMap);
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .aisle("YYY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YRY", "YYY")
                .aisle("YSY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY", "YYY")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('R', heatingCoilPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            Optional<RodType> optionalRodType = Arrays.stream(RodType.values()).filter(rodType -> rodType.casingState == blockState).findFirst();
            if (!optionalRodType.isPresent()) {
                return false;
            }
            RodType rodType = blockWorldState.getMatchContext().getOrPut("rodType", optionalRodType.get());
            return rodType.getName().equals(optionalRodType.get().getName());

        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.1"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.2"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.3"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.4"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.5"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.6"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.7"));
        tooltip.add(I18n.format("gregtech.multiblock.reactor.tooltip.8"));
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Lead);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(Lead);
    }


    private int currentTemperature = 300;
    private boolean notEnoughCoolant = true;
    private FluidMaterial coolant;
    private Fluid hotCoolant;
    private int recipeBaseHeat;
    private boolean overheat = false;
    private RodType rodType;


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.currentTemperature = 300; //reset temperature
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.rodType = context.getOrDefault("rodType", RodType.THORIUM);

    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_boiler.temperature", this.currentTemperature - 273, hotCoolant != null ? hotCoolant.getTemperature() * rodType.maxTemperature / 100 - 273 : 100));
            textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.base_heat", recipeBaseHeat));
            textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.rod_type", rodType.maxTemperature - 100));
            if (hotCoolant != null && currentTemperature > hotCoolant.getTemperature() && currentTemperature < hotCoolant.getTemperature() * rodType.maxTemperature / 100) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.produce").setStyle(new Style().setColor(TextFormatting.GREEN)));
            }
            if (coolant != null)
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.coolant", new TextComponentTranslation(coolant.getUnlocalizedName()), hotCoolant.getTemperature() - 273));
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


        if (!recipeMapWorkable.isActive() || recipeMapWorkable.isHasNotEnoughEnergy() || overheat || !recipeMapWorkable.isWorkingEnabled()) {
            if (currentTemperature > 300)
                --currentTemperature;
            else
                overheat = false;

            if (!recipeMapWorkable.isActive()) {
                recipeBaseHeat = 0;
            }
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
                    } else {
                        outputFluidInventory.fill(coolant.getFluid(fluidStack.amount), true);
                    }

                    currentTemperature = Math.max(300, currentTemperature - fluidStack.amount / 1000);
                    if (currentTemperature > hotCoolant.getTemperature() * rodType.maxTemperature / 100) {
                        overheat = true;
                        notEnoughCoolant = false;
                        recipeMapWorkable.setWorkingEnabled(false);
                        return;
                    }
                }
            } else {
                coolant = null;
                hotCoolant = null;
                if (currentTemperature > 373) {
                    overheat = true;
                    notEnoughCoolant = false;
                    recipeMapWorkable.setWorkingEnabled(false);
                    return;
                }
            }


        }


    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("CurrentTemperature", currentTemperature);
        data.setInteger("recipeBaseHeat", recipeBaseHeat);
        data.setBoolean("overheat", overheat);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.currentTemperature = data.getInteger("CurrentTemperature");
        this.recipeBaseHeat = data.getInteger("recipeBaseHeat");
        this.overheat = data.getBoolean("overheat");
    }


}
