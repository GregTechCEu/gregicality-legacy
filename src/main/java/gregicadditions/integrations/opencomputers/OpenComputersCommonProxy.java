package gregicadditions.integrations.opencomputers;

import gregicadditions.GAConfig;
import gregicadditions.Gregicality;
import gregicadditions.integrations.opencomputers.driver.*;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class OpenComputersCommonProxy {

    @Optional.Method(modid = "opencomputers")
    @Mod.EventHandler
    public void init() {
        if (GAConfig.openComputers.disable || !Loader.isModLoaded("opencomputers")) return;
        if (!Loader.isModLoaded("gtce2oc")){ // avoid existing driver
            Driver.add(new DriverEnergyContainer());
            Driver.add(new DriverWorkable());
        }
        Driver.add(new DriverAbstractRecipeLogic());
        Driver.add(new DriverMultiblockRecipeLogic());
        Driver.add(new DriverICoverable());
        Driver.add(new DriverSimpleMachineMetaTileEntity());
    }
}
