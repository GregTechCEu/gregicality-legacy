package gregicadditions;

import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.covers.CoverBehaviors;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.network.IPSaveData;
import gregicadditions.network.NetworkHandler;
import gregicadditions.theoneprobe.TheOneProbeCompatibility;
import gregicadditions.utils.GALog;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.GTValues;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

@Mod(   modid        = Gregicality.MODID,
        name         = Gregicality.NAME,
        version      = Gregicality.VERSION,
        dependencies = "required-after:gregtech@[2.0,);")
public class Gregicality {

    public static final String MODID = "gregicality";
    public static final String NAME = "Gregicality";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.ClientProxy", serverSide = "gregicadditions.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        GAEnums.onConstruction();
        GAConfig.syncMachineConfigs();
        GTValues.HT = true; // force GTCEu to register UHV+ Hulls, Casings, Basic Electric Pieces like Transformers, Bat Buffers, etc.
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GALog.init(event.getModLog());
        NetworkHandler.preInit();
        proxy.preLoad();
        GregicalityCapabilities.init();
        MinecraftForge.EVENT_BUS.register(new GAEventHandler());

        GAMetaBlocks.init();
        GATileEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();
        if (GTValues.isModLoaded(GTValues.MODID_TOP)) {
            GALog.logger.info("TheOneProbe found. Enabling integration...");
            TheOneProbeCompatibility.registerCompatibility();
        }
        CoverBehaviors.init();
        GAConfig.addConfigReservoirs(GAConfig.extraction.reservoirs);
        PumpjackHandler.oilChance = GAConfig.extraction.reservoirChance;
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PumpjackHandler.recalculateChances(true);
    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
            if (!world.isRemote) {
                IPSaveData worldData = (IPSaveData) world.loadData(IPSaveData.class, IPSaveData.dataName);
                if (worldData == null) {
                    worldData = new IPSaveData(IPSaveData.dataName);
                    world.setData(IPSaveData.dataName, worldData);
                }
                IPSaveData.setInstance(world.provider.getDimension(), worldData);
            }
        }
    }
}
