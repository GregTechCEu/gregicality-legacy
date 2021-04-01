package gregicadditions;

import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import gregicadditions.blocks.factories.GAMetalCasingBlockFactory;
import gregicadditions.blocks.factories.GAOreBlockFactory;
import gregicadditions.capabilities.SimpleCapabilityManager;
import gregicadditions.covers.CoverBehaviors;
import gregicadditions.input.Keybinds;
import gregicadditions.integrations.bees.ForestryCommonProxy;
import gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;
import gregicadditions.integrations.opencomputers.OpenComputersCommonProxy;
import gregicadditions.integrations.tconstruct.TinkersMaterials;
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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

@Mod(modid = Gregicality.MODID, name = Gregicality.NAME, version = Gregicality.VERSION,
        dependencies = "required-after:gregtech@[1.13.0.681,);" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio;" +
                "after:mysticalagradditions;" +
                "after:binniecore;" +
                "after:extrabees;" +
                "after:botany;" +
                "after:binniedesign;" +
                "after:extratrees;" +
                "after:botany;" +
                "after:genetics"
)
public class Gregicality {
    public static final String MODID = "gtadditions";
    public static final String NAME = "Gregicality";
    public static final String VERSION = "@VERSION@";


    static {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            GAMetalCasingBlockFactory.init();
            GAOreBlockFactory.init();
        }
    }

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.mysticalagriculture.MysticalClientProxy", serverSide = "gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy")
    public static MysticalCommonProxy mysticalCommonProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.bees.ForestryClientProxy", serverSide = "gregicadditions.integrations.bees.ForestryCommonProxy")
    public static ForestryCommonProxy forestryProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy", serverSide = "gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy")
    public static ExNihiloCreatioProxy exNihiloCreatioProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.opencomputers.OpenComputersCommonProxy", serverSide = "gregicadditions.integrations.opencomputers.OpenComputersCommonProxy")
    public static OpenComputersCommonProxy openComputersProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.ClientProxy", serverSide = "gregicadditions.CommonProxy")
    public static CommonProxy proxy;

    public Gregicality() {

        GAEnums.preInit();

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GALog.init(event.getModLog());
        NetworkHandler.preInit();
        proxy.preLoad();
        Keybinds.register();
        SimpleCapabilityManager.init();
        MinecraftForge.EVENT_BUS.register(new GAEventHandler());

        GAMetaBlocks.init();
        GATileEntities.init();
        if (GAConfig.GregsConstruct.EnableGregsConstruct && Loader.isModLoaded("tconstruct"))
            TinkersMaterials.preInit();
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            exNihiloCreatioProxy.preInit();
        }
        if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) {
            forestryProxy.preInit();
        }
        if (Loader.isModLoaded(MysticalAgradditions.MOD_ID) && !GAConfig.mysticalAgriculture.disable) {
            mysticalCommonProxy.preInit();
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();
        if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) {
            forestryProxy.init();
        }
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            exNihiloCreatioProxy.init(event);
        }
        if (Loader.isModLoaded(MysticalAgradditions.MOD_ID) && !GAConfig.mysticalAgriculture.disable) {
            mysticalCommonProxy.init();
        }
        if (GTValues.isModLoaded(GTValues.MODID_TOP)) {
            GALog.logger.info("TheOneProbe found. Enabling integration...");
            TheOneProbeCompatibility.registerCompatibility();
        }
        if (Loader.isModLoaded("opencomputers")) {
            openComputersProxy.init();
        }
        CoverBehaviors.init();
        GAConfig.addConfigReservoirs(GAConfig.extraction.reservoirs);
        PumpjackHandler.oilChance = GAConfig.Extraction.reservoirChance;
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PumpjackHandler.recalculateChances(true);

    }

    @Mod.EventHandler
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
