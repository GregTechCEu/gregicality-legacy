package gregicadditions.machines.multi.advance.hyper;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.multi.GABoostableWorkableHandler;
import gregicadditions.machines.multi.GAFueledMultiblockController;
import gregicadditions.recipes.impl.BoostableWorkableHandler;
import gregicadditions.item.metal.MetalCasing2;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static gregicadditions.client.ClientHandler.NAQUADRIA_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.Helium;

public class MetaTileEntityHyperReactorI extends GAFueledMultiblockController {


    public MetaTileEntityHyperReactorI(ResourceLocation metaTileEntityId, long maxVoltage) {
        super(metaTileEntityId, GARecipeMaps.HYPER_REACTOR_FUELS, maxVoltage);
        this.maxVoltage = maxVoltage;
        Fluid temp = FluidRegistry.getFluid(GAConfig.multis.hyperReactors.boosterFluid[0]);
        if (temp == null) {
            temp = Helium.getMaterialPlasma();
            GALog.logger.warn("Incorrect fluid given to hyper reactor: " + GAConfig.multis.hyperReactors.boosterFluid[0]);
        }
        booster = new FluidStack(temp, GAConfig.multis.hyperReactors.boosterFluidAmounts[0]);
    }

    long maxVoltage;
    FluidStack booster;

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityHyperReactorI(metaTileEntityId, this.maxVoltage);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        int fuelMultiplier = GAConfig.multis.hyperReactors.boostedFuelAmount[0];
        int euMultiplier = GAConfig.multis.hyperReactors.boostedEuAmount[0];
        return new GABoostableWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler,
                maxVoltage, booster, fuelMultiplier, euMultiplier);
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
        tooltip.add(I18n.format("gtadditions.multiblock.hyper_reactor.tooltip.1", Objects.requireNonNull(FluidRegistry.getFluidStack(GAConfig.multis.hyperReactors.boosterFluid[0], 1)).getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.hyper_reactor.tooltip.2", maxVoltage));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CCCCC", "G#H#G", "GHHHG", "G#H#G", "CCCCC")
                .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                .aisle("CCSCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY, MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_HATCH)))
                .where('T', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_UV)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS)))
                .where('H', statePredicate(GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE)))
                .where('#', isAirPredicate())
                .setAmountAtLeast('c', 55)
                .where('c', statePredicate(getCasingState()))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return NAQUADRIA_CASING;
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA);
    }

}
