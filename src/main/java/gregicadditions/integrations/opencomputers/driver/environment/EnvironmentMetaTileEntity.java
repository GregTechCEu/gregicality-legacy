package gregicadditions.integrations.opencomputers.driver.environment;

import gregtech.api.metatileentity.MetaTileEntityHolder;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.integration.ManagedTileEntityEnvironment;

public abstract class EnvironmentMetaTileEntity<T> extends ManagedTileEntityEnvironment<T> implements NamedBlock {
    private String preferredName = "gtce_metaTileEntity";

    public EnvironmentMetaTileEntity(MetaTileEntityHolder holder, T capability, String name) {
        super(capability, name);
        preferredName = holder.getMetaTileEntity().metaTileEntityId.getPath();
    }

    @Override
    public String preferredName() {
        return preferredName;
    }

    @Override
    public int priority() {
        return 0;
    }
}
