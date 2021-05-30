package gregicadditions.machines.multi.advance;

import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.metal.MetalCasing2;
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
import gregtech.common.blocks.BlockWireCoil;
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

import static gregicadditions.client.ClientHandler.NAQUADRIA_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityLargeNaquadahReactor extends FueledMultiblockController { //todo generator maintenance


    public MetaTileEntityLargeNaquadahReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.NAQUADAH_REACTOR_FUELS, GAValues.V[GAValues.UV]);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeNaquadahReactor(metaTileEntityId);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new NaquadahReactorWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack potassiumPlasma = importFluidHandler.drain(Materials.Potassium.getPlasma(Integer.MAX_VALUE), false);
            FluidStack fuelStack = ((NaquadahReactorWorkableHandler) workableHandler).getFuelStack();
            boolean isBoosted = ((NaquadahReactorWorkableHandler) workableHandler).isUsingPotassium();
            int potassiumPlasmaAmount = potassiumPlasma == null ? 0 : potassiumPlasma.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.potassium_plasma_amount", potassiumPlasmaAmount));
            textList.add(new TextComponentString(fuelStack != null ? String.format("%dmb %s", fuelAmount, fuelStack.getLocalizedName()) : ""));
            textList.add(new TextComponentTranslation(isBoosted ? "gregtech.multiblock.large_rocket_engine.boost" : "").setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
        super.addDisplayText(textList);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.4"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.5"));
        tooltip.add(I18n.format("gtadditions.multiblock.large_naquadah_reactor.tooltip.6"));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(LEFT, DOWN, BACK)
                .aisle("CCCCC", "CCCCC", "CCECC", "CCCCC", "CCCCC")
                .aisle("CFFFC", "FYYYF", "FYTYF", "FYYYF", "CFFFC").setRepeatable(10)
                .aisle("CCCCC", "CCCCC", "CCSCC", "CCCCC", "CCCCC")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY)))
                .where('T', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_UV)))
                .where('Y', statePredicate(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL)))
                .where('E', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY)))
                .where('F', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
                .where('A', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING)))
                .where('#', isAirPredicate())
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
