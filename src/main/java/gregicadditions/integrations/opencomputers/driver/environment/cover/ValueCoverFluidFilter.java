package gregicadditions.integrations.opencomputers.driver.environment.cover;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverFluidFilter;
import gregtech.common.covers.CoverItemFilter;
import gregtech.common.covers.FluidFilterMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValueCoverFluidFilter extends ValueCoverBehavior {
    public ValueCoverFluidFilter(CoverFluidFilter coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "CoverFluidFilter");
    }

    public ValueCoverFluidFilter() {}

    @Callback(doc = "function(mode:number) --  Sets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] setFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverFluidFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 2) throw new IllegalArgumentException("Expect a number between 0 and 2.");
        Method setFilterMode = ReflectionHelper.findMethod(CoverFluidFilter.class, "setFilterMode", null, FluidFilterMode.class);
        try {
            setFilterMode.invoke(coverBehavior, FluidFilterMode.values()[mode]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] getFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverFluidFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverFluidFilter) coverBehavior).getFilterMode().ordinal()};
    }

}
