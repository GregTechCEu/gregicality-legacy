package gregicadditions.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class GregicAdditionsCapabilities {


    @CapabilityInject(IMultiRecipe.class)
    public static Capability<IMultiRecipe> MULTI_RECIPE_CAPABILITY = null;

}
