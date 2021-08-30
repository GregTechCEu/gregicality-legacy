package gregicadditions.capabilities;

import gregicadditions.Gregicality;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;

import static gregtech.api.capability.SimpleCapabilityManager.registerCapabilityWithNoDefault;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class GregicalityCapabilities {

    @CapabilityInject(IMultiRecipe.class)
    public static Capability<IMultiRecipe> MULTI_RECIPE_CAPABILITY;

    @CapabilityInject(IQubitContainer.class)
    public static Capability<IQubitContainer> QBIT_CAPABILITY;

    public static MultiblockAbility<IQubitContainer> INPUT_QBIT = new MultiblockAbility();
    public static MultiblockAbility<IQubitContainer> OUTPUT_QBIT = new MultiblockAbility();

    public static void init() {
        registerCapabilityWithNoDefault(IMultiRecipe.class);
        registerCapabilityWithNoDefault(IQubitContainer.class);
    }
}
