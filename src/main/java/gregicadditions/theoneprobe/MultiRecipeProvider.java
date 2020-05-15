package gregicadditions.theoneprobe;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IMultiRecipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTLog;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class MultiRecipeProvider implements IProbeInfoProvider {
    @Override
    public String getID() {
        return "gtadditions:screwdriver_provider";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        if (blockState.getBlock().hasTileEntity(blockState)) {
            EnumFacing sideHit = data.getSideHit();
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity == null) return;
            Capability<IMultiRecipe> capability = getCapability();
            try {
                IMultiRecipe resultCapability = tileEntity.getCapability(capability, null);
                if (resultCapability != null && allowDisplaying(resultCapability)) {
                    addProbeInfo(resultCapability, probeInfo, tileEntity, sideHit);
                }
            } catch (Throwable e) {
                GTLog.logger.error("Bad One probe Implem: {}", e.getStackTrace()[0].getClassName());
            }
        }
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

    protected boolean allowDisplaying(IMultiRecipe capability) {
        return true;
    }

}
