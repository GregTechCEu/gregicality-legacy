package gregicadditions;

import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import gregicadditions.blocks.factories.GAMetalCasingBlockFactory;
import gregicadditions.blocks.factories.GAOreBlockFactory;
import gregicadditions.covers.CoverBehaviors;
import gregicadditions.input.Keybinds;
import gregicadditions.integrations.bees.ForestryCommonProxy;
import gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy;
import gregicadditions.integrations.mysticalagriculture.MysticalCommonProxy;
import gregicadditions.integrations.tconstruct.TinkersMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.network.NetworkHandler;
import gregicadditions.theoneprobe.TheOneProbeCompatibility;
import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

@Mod(modid = Gregicality.MODID, name = Gregicality.NAME, version = Gregicality.VERSION,
        dependencies = "required-after:gregtech;" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio;" +
                "after:mysticalagradditions"
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

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.ClientProxy", serverSide = "gregicadditions.CommonProxy")
    public static CommonProxy proxy;

    public Gregicality() {

        GAEnums.preInit();

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GALog.init(event.getModLog());
        NetworkHandler.init();
        proxy.preLoad();
        Keybinds.register();
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
        CoverBehaviors.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
