package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.generator.FueledMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static gregicadditions.client.ClientHandler.NITINOL_60_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityLargeRocketEngine extends FueledMultiblockController { //todo generator maintenance


    public MetaTileEntityLargeRocketEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.ROCKET_FUEL_RECIPES, GAValues.V[GAValues.EV]);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeRocketEngine(metaTileEntityId);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new RocketEngineWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack carbonDioxide = importFluidHandler.drain(Materials.CarbonDioxide.getFluid(Integer.MAX_VALUE), false);
            FluidStack hydrogen = importFluidHandler.drain(GAMaterials.LiquidHydrogen.getFluid(Integer.MAX_VALUE), false);
            FluidStack air = importFluidHandler.drain(Materials.Air.getFluid(Integer.MAX_VALUE), false);
            FluidStack fuelStack = ((RocketEngineWorkableHandler) workableHandler).getFuelStack();
            int hydrogenNeededToBoost = ((RocketEngineWorkableHandler) workableHandler).getHydrogenNeededToBoost();
            boolean isBoosted = ((RocketEngineWorkableHandler) workableHandler).isUsingHydrogen();
            int carbonDioxideAmount = carbonDioxide == null ? 0 : carbonDioxide.amount;
            int hydrogenAmount = hydrogen == null ? 0 : hydrogen.amount;
            int airAmount = air == null ? 0 : air.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            if (fuelStack == null)
                textList.add(new TextComponentTranslation("gregtech.multiblock.large_rocket_engine.no_fuel").setStyle(new Style().setColor(TextFormatting.RED)));
            else
                textList.add(new TextComponentString(String.format("%s: %dmb", fuelStack.getLocalizedName(), fuelAmount)).setStyle(new Style().setColor(TextFormatting.GREEN)));

            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.air_amount", airAmount).setStyle(new Style().setColor(TextFormatting.AQUA)));
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.carbon_dioxide_amount", carbonDioxideAmount).setStyle(new Style().setColor(TextFormatting.AQUA)));

            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.liquid_hydrogen_amount", hydrogenAmount).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_rocket_engine.hydrogen_need", hydrogenNeededToBoost).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
            if (isBoosted)
                textList.add(new TextComponentTranslation("gregtech.multiblock.large_rocket_engine.boost").setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.4"));
        if (GAConfig.Misc.largeRocketEfficiency) {
            tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.5"));
            tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.6"));
            tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.7"));
            tooltip.add(I18n.format("gtadditions.multiblock.large_rocket_engine.tooltip.8"));
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(LEFT, DOWN, BACK)
                .aisle("CCC", "CEC", "CCC")
                .aisle("CAC", "F#F", "CCC").setRepeatable(8)
                .aisle("KKK", "KSK", "KKK")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()))
                .where('K', statePredicate(getCasingState()).or(abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY)))
                .where('E', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY)))
                .where('F', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
                .where('A', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING)))
                .where('#', isAirPredicate())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return NITINOL_60_CASING;
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.NITINOL_60);
    }

}
