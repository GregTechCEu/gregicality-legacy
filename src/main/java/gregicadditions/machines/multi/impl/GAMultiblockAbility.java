package gregicadditions.machines.multi.impl;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GAMultiblockAbility<T> extends MultiblockAbility<T> {

    public static final MultiblockAbility<IFluidTank> STEAM = new MultiblockAbility();
    public static final MultiblockAbility<IItemHandlerModifiable> STEAM_IMPORT_ITEMS = new MultiblockAbility<>();
    public static final MultiblockAbility<IItemHandlerModifiable> STEAM_EXPORT_ITEMS = new MultiblockAbility<>();
}
