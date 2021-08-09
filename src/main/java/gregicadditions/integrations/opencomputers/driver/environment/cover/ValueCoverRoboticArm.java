package gregicadditions.integrations.opencomputers.driver.environment.cover;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverRoboticArm;
import gregtech.common.covers.TransferMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverRoboticArm extends ValueCoverConveyor {
    public ValueCoverRoboticArm() {}

    public ValueCoverRoboticArm(CoverRoboticArm coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "CoverRoboticArm");
    }

    @Callback(doc = "function(mode:number) --  Sets transfer mode. (0:TRANSFER_ANY, 1:TRANSFER_EXACT, 2:KEEP_EXACT)")
    public Object[] setTransferMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverRoboticArm)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 2) throw new IllegalArgumentException("Expect a number between 0 and 2.");
        ((CoverRoboticArm) coverBehavior).setTransferMode(TransferMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets transfer mode. (0:TRANSFER_ANY, 1:TRANSFER_EXACT, 2:KEEP_EXACT)")
    public Object[] getTransferMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverRoboticArm)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverRoboticArm) coverBehavior).getTransferMode().ordinal()};
    }

}
