package gregicadditions.theoneprobe;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IMultiRecipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTLog;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MultiRecipeProvider extends CapabilityInfoProvider<IMultiRecipe> {
    @Override
    public String getID() {
        return "gtadditions:screwdriver_provider";
    }

    protected Capability<IMultiRecipe> getCapability() {
        return GregicAdditionsCapabilities.MULTI_RECIPE_CAPABILITY;
    }

    protected void addProbeInfo(IMultiRecipe iMultiRecipe, IProbeInfo iProbeInfo, TileEntity tileEntity, EnumFacing enumFacing) {
        RecipeMap<?>[] recipes = iMultiRecipe.getRecipes();
        for (int i = 0; i < recipes.length; i++) {
            if (iMultiRecipe.getCurrentRecipe() == i) {
                iProbeInfo.text(TextStyleClass.INFOIMP + "{*" + recipes[i].getLocalizedName() + "*}");
            } else {
                iProbeInfo.text(TextStyleClass.INFO + recipes[i].getLocalizedName());
            }

        }
    }

}
