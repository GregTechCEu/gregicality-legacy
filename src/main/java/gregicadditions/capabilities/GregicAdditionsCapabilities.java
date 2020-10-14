package gregicadditions.capabilities;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class GregicAdditionsCapabilities {


    @CapabilityInject(IMultiRecipe.class)
    public static Capability<IMultiRecipe> MULTI_RECIPE_CAPABILITY;

    @CapabilityInject(IQubitContainer.class)
    public static Capability<IQubitContainer> QBIT_CAPABILITY;

    public static MultiblockAbility<IQubitContainer> INPUT_QBIT = new MultiblockAbility<>();
    public static MultiblockAbility<IQubitContainer> OUTPUT_QBIT = new MultiblockAbility<>();

}
