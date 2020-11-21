package gregicadditions.integrations.opencomputers.driver.environment.cover;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverItemFilter;
import gregtech.common.covers.ItemFilterMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValueCoverItemFilter extends ValueCoverBehavior {
    public ValueCoverItemFilter(CoverItemFilter coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "CoverItemFilter");
    }

    public ValueCoverItemFilter() {}

    @Callback(doc = "function(mode:number) --  Sets filter mode. (0:FILTER_INSERT, 1:FILTER_EXTRACT, 2:FILTER_BOTH)")
    public Object[] setFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverItemFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 2) throw new IllegalArgumentException("Expect a number between 0 and 2.");
        Method setFilterMode = ReflectionHelper.findMethod(CoverItemFilter.class, "setFilterMode", null, ItemFilterMode.class);
        try {
            setFilterMode.invoke(coverBehavior, ItemFilterMode.values()[mode]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets filter mode. (0:FILTER_INSERT, 1:FILTER_EXTRACT, 2:FILTER_BOTH)")
    public Object[] getFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverItemFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverItemFilter) coverBehavior).getFilterMode().ordinal()};
    }

}
