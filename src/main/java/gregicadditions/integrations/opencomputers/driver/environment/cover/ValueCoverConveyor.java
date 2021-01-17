package gregicadditions.integrations.opencomputers.driver.environment.cover;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverConveyor;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValueCoverConveyor extends ValueCoverBehavior {
    protected ValueCoverConveyor(CoverConveyor coverBehavior, EnumFacing side, String name) {
        super(coverBehavior, side, name);
    }

    public ValueCoverConveyor(CoverConveyor coverBehavior, EnumFacing side) {
        this(coverBehavior, side, "CoverConveyor");
    }

    public ValueCoverConveyor() {}

    @Callback(doc = "function():number --  Returns tier.")
    public Object[] getTier(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverConveyor)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverConveyor) coverBehavior).tier};
    }

    @Callback(doc = "function():number --  Returns transfer rate.")
    public Object[] getTransferRate(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverConveyor)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int transferRate = ReflectionHelper.getPrivateValue(CoverConveyor.class, (CoverConveyor)coverBehavior, "transferRate");
        return new Object[]{transferRate};
    }

    @Callback(doc = "function(number) --  Sets transfer rate.")
    public Object[] setTransferRate(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverConveyor)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int transferRate = args.checkInteger(0);
        int maxRate = ((CoverConveyor) coverBehavior).maxItemTransferRate;
        if (transferRate < 0 || transferRate > maxRate) throw new IllegalArgumentException(String.format("Expect a number between 0 and %d.", maxRate));
        Method setTransferRate = ReflectionHelper.findMethod(CoverConveyor.class, "setTransferRate", null, int.class);
        try {
            setTransferRate.invoke(coverBehavior, transferRate);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function(mode:number) --  Sets conveyor mode. (0:IMPORT, 1:EXPORT)")
    public Object[] setConveyorMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverConveyor)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 1) throw new IllegalArgumentException("Expect a number between 0 and 1.");
        Method setFilterMode = ReflectionHelper
                .findMethod(CoverConveyor.class, "setConveyorMode", null, CoverConveyor.ConveyorMode.class);
        try {
            setFilterMode.invoke(coverBehavior, CoverConveyor.ConveyorMode.values()[mode]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets conveyor mode. (0:IMPORT, 1:EXPORT)")
    public Object[] getConveyorMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverConveyor)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverConveyor) coverBehavior).getConveyorMode().ordinal()};
    }
}
