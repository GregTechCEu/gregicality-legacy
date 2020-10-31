package gregicadditions.machines;

import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.render.OrientedOverlayRenderer;
import gregicadditions.utils.GALog;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;

public class SimpleGeneratorWithLossMetaTileEntity extends SimpleGeneratorMetaTileEntity {

    private int efficiency;
    private Field workableHandler;
    private FuelRecipeMap recipeMap;
    private OrientedOverlayRenderer renderer;

    public SimpleGeneratorWithLossMetaTileEntity(ResourceLocation metaTileEntityId, FuelRecipeMap recipeMap, OrientedOverlayRenderer renderer, int tier, int efficiency) {
        super(metaTileEntityId, recipeMap, renderer, tier);
        this.efficiency = efficiency;
        this.recipeMap = recipeMap;
        this.renderer = renderer;
        workableHandler = ObfuscationReflectionHelper.findField(SimpleGeneratorMetaTileEntity.class, "workableHandler");
        try {
            workableHandler.set(this, new FuelRecipeWithLossLogic(this, recipeMap,
                    () -> energyContainer, () -> importFluids, GTValues.V[tier], efficiency));
        } catch (IllegalAccessException e) {
            GALog.logger.error("Something is wrong with generator with loss", e);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SimpleGeneratorWithLossMetaTileEntity(metaTileEntityId, recipeMap, renderer, this.getTier(), efficiency);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.universal.tooltip.efficiency", efficiency));

    }

    static class FuelRecipeWithLossLogic extends FuelRecipeLogic {

        private int efficiency;

        public FuelRecipeWithLossLogic(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage, int efficiency) {
            super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
            this.efficiency = efficiency;
        }

        protected int calculateRecipeDuration(FuelRecipe currentRecipe) {
            return currentRecipe.getDuration() * efficiency / 100;
        }
    }
}
