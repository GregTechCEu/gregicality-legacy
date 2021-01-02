package gregicadditions.capabilities;

import gregicadditions.GAValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;

import java.util.function.Supplier;


public class GARecipeLogicEnergy extends RecipeLogicEnergy {
    public GARecipeLogicEnergy(MetaTileEntity tileEntity, RecipeMap<?> recipeMap, Supplier<IEnergyContainer> energyContainer) {
        super(tileEntity, recipeMap, energyContainer);
    }

    private static byte getTierByVoltage(long voltage) {
        byte tier = 0;
        while (++tier < GAValues.V.length) {
            if (voltage == GAValues.V[tier]) {
                return tier;
            } else if (voltage < GAValues.V[tier]) {
                return (byte) Math.max(0, tier - 1);
            }
        }
        return (byte) Math.min(GAValues.V.length -1, tier);
    }
    @Override
    protected int[] calculateOverclock(int EUt, long voltage, int duration) {
        if(!allowOverclocking) {
            return new int[] {EUt, duration};
        }
        boolean negativeEU = EUt < 0;
        int tier = getOverclockingTier(voltage);
        if (GAValues.V[tier] <= EUt || tier == 0)
            return new int[]{EUt, duration};
        if (negativeEU)
            EUt = -EUt;
        if (EUt <= 16) {
            int multiplier = EUt <= 8 ? tier : tier - 1;
            int resultEUt = EUt * (1 << multiplier) * (1 << multiplier);
            int resultDuration = duration / (1 << multiplier);
            return new int[]{negativeEU ? -resultEUt : resultEUt, resultDuration};
        } else {
            int resultEUt = EUt;
            double resultDuration = duration;
            //do not overclock further if duration is already too small
            while (resultDuration >= 3 && resultEUt <= GAValues.V[tier - 1]) {
                resultEUt *= 4;
                resultDuration /= 2.8;
            }
            return new int[]{negativeEU ? -resultEUt : resultEUt, (int) Math.ceil(resultDuration)};
        }
    }
    protected int getOverclockingTier(long voltage) {
        return getTierByVoltage(voltage);
    }

}
