package gregicadditions.machines.multi.nuclear;

import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.client.ClientHandler;
import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.common.MetaFluids;
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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.*;


public class MetaTileEntityNuclearReactor extends GARecipeMapMultiblockController {

    public enum RodType implements IStringSerializable {
        THORIUM(0, GAMetaBlocks.getMetalCasingBlockState(ThoriumRadioactive.getMaterial())),
        URANIUM(2, GAMetaBlocks.getMetalCasingBlockState(UraniumRadioactive.getMaterial())),
        PLUTONIUM(10, GAMetaBlocks.getMetalCasingBlockState(PlutoniumRadioactive.getMaterial())),
        AMERICIUM(15, GAMetaBlocks.getMetalCasingBlockState(AmericiumRadioactive.getMaterial())),
        CURIUM(25, GAMetaBlocks.getMetalCasingBlockState(Curium.getMaterial())),
        BERKELIUM(35, GAMetaBlocks.getMetalCasingBlockState(Berkelium.getMaterial())),
        CALIFORNIUM(50, GAMetaBlocks.getMetalCasingBlockState(Californium.getMaterial())),
        EINSTEINIUM(75, GAMetaBlocks.getMetalCasingBlockState(Einsteinium.getMaterial())),
        FERMIUM(100, GAMetaBlocks.getMetalCasingBlockState(Fermium.getMaterial())),
        MENDELEVIUM(200, GAMetaBlocks.getMetalCasingBlockState(Mendelevium.getMaterial()));


        public final int additionalTemperature;
        public final IBlockState casingState;

        RodType(int additionalTemperature, IBlockState casingState) {
            this.additionalTemperature = additionalTemperature;
            this.casingState = casingState;
        }

        @Override
        public String getName() {
            return name();
        }
    }


    public MetaTileEntityNuclearReactor(ResourceLocation metaTileEntityId, RecipeMap<?> recipe) {
        super(metaTileEntityId, recipe);
        recipeMapWorkable.setAllowOverclocking(false);
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
                .aisle("YYY", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "YYY")
                .aisle("YYY", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "YYY")
                .aisle("YSY", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "YYY")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('Z', statePredicate(getCasingState()))
                .where('X', statePredicate(getGlassCasing()).or(statePredicate(getCasingState())))
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
            blockWorldState.getMatchContext().increment("rodAdditionalTemperature", optionalRodType.get().additionalTemperature);
            return true;

        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.4"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.5"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.6"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.7"));
        tooltip.add(I18n.format("gtadditions.multiblock.reactor.tooltip.8"));
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CLADDED_REACTOR_CASING);
    }

    public IBlockState getGlassCasing() {
        return GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return ClientHandler.CLADDED_REACTOR_CASING;
    }

    private boolean notEnoughCoolant = true;
    private FluidMaterial coolant;
    private Fluid hotCoolant;
    private int recipeBaseHeat;
    private int rodAdditionalTemperature;


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        rodAdditionalTemperature = context.getOrDefault("rodAdditionalTemperature", 0);

    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.base_heat", recipeBaseHeat));
            textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.additional_temperature", rodAdditionalTemperature));
            if (hotCoolant != null) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.coolant_needed", coolantNeeded()));
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.coolant_ratio", coolantRatio()));
            }
            if (notEnoughCoolant) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.nuclear_reactor.not_enough_coolant").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }

        super.addDisplayText(textList);
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        recipeBaseHeat = recipe.getIntegerProperty("base_heat_production");
        return true;
    }

    public int coolantNeeded() {
        return (recipeBaseHeat + rodAdditionalTemperature) * coolantRatio();
    }

    public int coolantRatio() {
        return (15000 / (hotCoolant.getTemperature() - 273));
    }

    @Override
    protected void updateFormedValid() {
        recipeMapWorkable.updateWorkable();
        if (!getWorld().isRemote) {
            if (recipeMapWorkable.isHasNotEnoughEnergy() || !recipeMapWorkable.isWorkingEnabled() || !recipeMapWorkable.isActive()) {
                notEnoughCoolant = false;
                return;
            }
            if (getTimer() % 20 == 0) {
                FluidStack fluidStack = inputFluidInventory.drain(Integer.MAX_VALUE, false);
                if (fluidStack != null) {
                    coolant = MetaFluids.getMaterialFromFluid(fluidStack.getFluid());
                    hotCoolant = GAMetaFluids.HOT_FLUIDS.get(coolant);
                    if (hotCoolant != null) {
                        fluidStack = inputFluidInventory.drain(coolant.getFluid(coolantNeeded()), true);
                        if (fluidStack == null) {
                            hotCoolant = null;
                            coolant = null;
                            return;
                        }
                        if (fluidStack.amount < coolantNeeded()) {
                            notEnoughCoolant = true;
                            inputFluidInventory.fill(fluidStack, true);
                            return;
                        }
                        notEnoughCoolant = false;
                        outputFluidInventory.fill(new FluidStack(hotCoolant, fluidStack.amount), true);

                        boolean overclock = true;
                        do {
                            fluidStack = inputFluidInventory.drain(coolant.getFluid(coolantRatio()), true);
                            if (fluidStack == null || fluidStack.amount < coolantRatio()) {
                                overclock = false;
                                if (fluidStack != null)
                                    inputFluidInventory.fill(fluidStack, true);
                            } else {
                                int progressTime = ObfuscationReflectionHelper.getPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, "progressTime");
                                ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, progressTime + 20, "progressTime");
                                outputFluidInventory.fill(new FluidStack(hotCoolant, fluidStack.amount), true);
                            }

                        } while (overclock);

                    }
                } else {
                    hotCoolant = null;
                    coolant = null;
                }


            }
        }

    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("recipeBaseHeat", recipeBaseHeat);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.recipeBaseHeat = data.getInteger("recipeBaseHeat");
    }

    public int getRecipeBaseHeat() {
        return recipeBaseHeat;
    }

    public int getRodAdditionalTemperature() {
        return rodAdditionalTemperature;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.NUCLEAR_REACTOR_OVERLAY;
    }

}
