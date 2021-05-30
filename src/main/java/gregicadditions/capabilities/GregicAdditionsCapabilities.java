package gregicadditions.capabilities;

import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMaintenanceHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMufflerHatch;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

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

    public static final MultiblockAbility<MetaTileEntityMaintenanceHatch> MAINTENANCE_CAPABILITY = new MultiblockAbility<>();
    public static final MultiblockAbility<MetaTileEntityMufflerHatch> MUFFLER_HATCH = new MultiblockAbility<>();

}
