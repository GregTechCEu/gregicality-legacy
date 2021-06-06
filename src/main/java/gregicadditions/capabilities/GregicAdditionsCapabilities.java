package gregicadditions.capabilities;

import gregicadditions.Gregicality;
import gregicadditions.integrations.FECompat.EnergyProvider;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.IItemHandlerModifiable;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class GregicAdditionsCapabilities {


    @CapabilityInject(IMultiRecipe.class)
    public static Capability<IMultiRecipe> MULTI_RECIPE_CAPABILITY;

    @CapabilityInject(IQubitContainer.class)
    public static Capability<IQubitContainer> QBIT_CAPABILITY;

    public static MultiblockAbility<IQubitContainer> INPUT_QBIT = new MultiblockAbility();
    public static MultiblockAbility<IQubitContainer> OUTPUT_QBIT = new MultiblockAbility();

    public static final MultiblockAbility<IFluidTank> STEAM = new MultiblockAbility();
    public static final MultiblockAbility<IItemHandlerModifiable> STEAM_IMPORT_ITEMS = new MultiblockAbility<>();
    public static final MultiblockAbility<IItemHandlerModifiable> STEAM_EXPORT_ITEMS = new MultiblockAbility<>();

    private static final ResourceLocation CAPABILITY_EU_TO_RF = new ResourceLocation(Gregicality.MODID, "fecapability");

    @SubscribeEvent
    public static void attachTileCapability(AttachCapabilitiesEvent<TileEntity> event) {
        event.addCapability(CAPABILITY_EU_TO_RF, new EnergyProvider(event.getObject()));
    }
}
