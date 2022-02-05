package gregicadditions.machines.multi.advance.hyper;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.machines.multi.GABoostableWorkableHandler;
import gregicadditions.machines.multi.GAFueledMultiblockController;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.utils.GALog;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static gregtech.api.unification.material.Materials.Naquadria;
import static gregtech.api.unification.material.Materials.Radon;

public class MetaTileEntityHyperReactorII extends GAFueledMultiblockController {

    public static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.OUTPUT_ENERGY, MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    private long maxVoltage;
    private FluidStack booster;

    public MetaTileEntityHyperReactorII(ResourceLocation metaTileEntityId, long maxVoltage) {
        super(metaTileEntityId, GARecipeMaps.HYPER_REACTOR_FUELS, maxVoltage);
        this.maxVoltage = maxVoltage;
        this.booster = getBooster();
    }

    @Nonnull
    private FluidStack getBooster() {
        Fluid temp = FluidRegistry.getFluid(GAConfig.multis.hyperReactors.boosterFluid[1]);
        if (temp == null) {
            temp = Radon.getMaterialPlasma();
            GALog.logger.warn("Incorrect fluid given to hyper reactor: " + GAConfig.multis.hyperReactors.boosterFluid[1]);
        }
        return new FluidStack(Objects.requireNonNull(temp), GAConfig.multis.hyperReactors.boosterFluidAmounts[1]);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityHyperReactorII(metaTileEntityId, this.maxVoltage);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        int fuelMultiplier = GAConfig.multis.hyperReactors.boostedFuelAmount[1];
        int euMultiplier = GAConfig.multis.hyperReactors.boostedEuAmount[1];
        return new GABoostableWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler,
                maxVoltage, getBooster(), fuelMultiplier, euMultiplier);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack booster = importFluidHandler.drain(this.booster, false);
            FluidStack fuelStack = ((GABoostableWorkableHandler) workableHandler).getFuelStack();
            boolean isBoosted = ((GABoostableWorkableHandler) workableHandler).isBoosted();
            int boosterAmount = booster == null ? 0 : booster.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            if (fuelStack == null)
                textList.add(new TextComponentTranslation("gregtech.multiblock.large_rocket_engine.no_fuel").setStyle(new Style().setColor(TextFormatting.RED)));
            else
                textList.add(new TextComponentString(String.format("%s: %dmb", fuelStack.getLocalizedName(), fuelAmount)).setStyle(new Style().setColor(TextFormatting.GREEN)));

            if (isBoosted) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.large_rocket_engine.boost").setStyle(new Style().setColor(TextFormatting.GREEN)));
                if (booster != null)
                    textList.add(new TextComponentString(String.format("%s: %dmb", booster.getLocalizedName(), boosterAmount)).setStyle(new Style().setColor(TextFormatting.AQUA)));
            }
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.hyper_reactor.tooltip.1", Objects.requireNonNull(FluidRegistry.getFluidStack(GAConfig.multis.hyperReactors.boosterFluid[1], 1)).getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.hyper_reactor.tooltip.2", maxVoltage));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#######C#######", "#####CCCCC#####", "#######C#######")
                .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                .aisle("#CC####F####CC#", "C#C####H####C#C", "#CC#########CC#")
                .aisle("CCC###FHF###CCC", "C#C###HHH###C#C", "CCC####H####CCC")
                .aisle("#CC####F####CC#", "C#C####H####C#C", "#CC#########CC#")
                .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                .aisle("#######C#######", "#####CCSCC#####", "#######C#######")
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', (tile) -> true)
                .where('S', selfPredicate())
                .where('F', statePredicate(MetaBlocks.FRAMES.get(Naquadria).getDefaultState()))
                .where('H', statePredicate(GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE_2)))
                .setAmountAtLeast('c', 220)
                .where('c', statePredicate(getCasingState()))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.HYPER_CASING;
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CASING);
    }

}
